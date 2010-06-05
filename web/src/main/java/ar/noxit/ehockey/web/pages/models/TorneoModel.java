package ar.noxit.ehockey.web.pages.models;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class TorneoModel extends IdLDM<Torneo, Integer> {

    private ITorneoService torneoService;

    public TorneoModel(IModel<Integer> idModel, ITorneoService torneoService) {
        super(idModel);
        this.torneoService = torneoService;
    }

    @Override
    protected Torneo doLoad(Integer id) throws NoxitException {
        return torneoService.get(id);
    }

    @Override
    protected Integer getObjectId(Torneo object) {
        return object.getId();
    }
}
