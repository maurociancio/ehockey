package ar.noxit.ehockey.web.pages.partido;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import org.apache.wicket.model.IModel;

public class PartidoModel extends IdLDM<Partido, Integer> {

    private IPartidoService partidoService;

    public PartidoModel(IModel<Integer> idModel, IPartidoService partidoService) {
        super(idModel);
        this.partidoService = partidoService;
    }

    @Override
    protected Partido doLoad(Integer partidoId) throws NoxitException {
        return partidoService.get(partidoId);
    }

    @Override
    protected Integer getObjectId(Partido partido) {
        return partido.getId();
    }
}