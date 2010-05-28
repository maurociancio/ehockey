package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;

public class TerminarPartidoLink extends Link<Void> {

    private final IModel<Partido> rowModel;
    @SpringBean
    private IPartidoService partidoService;
    @SpringBean
    private IExceptionConverter converter;
    @SpringBean
    private IDateTimeProvider dateTimeProvider;

    public TerminarPartidoLink(String id, IModel<Partido> rowModel) {
        super(id);
        this.rowModel = rowModel;
    }

    @Override
    public void onClick() {
        try {
            partidoService.terminarPartido(rowModel.getObject().getId());
            rowModel.detach();
        } catch (NoxitException e) {
            error(converter.convert(e));
        }
    }

    @Override
    public boolean isVisible() {
        Partido partido = rowModel.getObject();
        LocalDateTime now = dateTimeProvider.getLocalDateTime();
        return !partido.isJugado() && partido.puedeTerminarPartido(now);
    }
}