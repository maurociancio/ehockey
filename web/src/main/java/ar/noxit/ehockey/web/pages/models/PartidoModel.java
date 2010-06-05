package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import org.apache.wicket.model.IModel;

public class PartidoModel extends IdLDM<Partido, Integer> {

    private IPartidoService partidoService;
    private IModel<Integer> id;

    public PartidoModel(IModel<Integer> idModel, IPartidoService partidoService) {
        super(idModel);
        this.partidoService = partidoService;
        this.id = idModel;
    }

    @Override
    protected Partido doLoad(Integer partidoId) throws NoxitException {
        return partidoService.get(partidoId);
    }

    @Override
    protected Integer getObjectId(Partido partido) {
        return partido.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!obj.getClass().equals(PartidoModel.class)) {
            return false;
        }
        PartidoModel other = (PartidoModel) obj;
        return id.getObject().equals(other.id.getObject());
    }

    @Override
    public int hashCode() {
        return this.id.getObject().hashCode();
    }
}