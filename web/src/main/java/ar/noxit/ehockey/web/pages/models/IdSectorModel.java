package ar.noxit.ehockey.web.pages.models;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class IdSectorModel extends IdLDM<Sector, Integer> {

    private ISectorService sectorService;

    public IdSectorModel(IModel<Integer> idModel, ISectorService sectorService) {
        super(idModel);
        this.sectorService = sectorService;
    }

    public IdSectorModel(IModel<Integer> idModel, Sector object) {
        super(idModel, object);
    }

    @Override
    protected Sector doLoad(Integer id) throws NoxitException {
        return sectorService.get(id);
    }

    @Override
    protected Integer getObjectId(Sector object) {
        return object.getId();
    }

}