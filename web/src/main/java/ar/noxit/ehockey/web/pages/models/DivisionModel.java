package ar.noxit.ehockey.web.pages.models;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class DivisionModel extends IdLDM<Division, Integer> {

    private IDivisionService divisionService;

    public DivisionModel(IModel<Integer> idModel,
            IDivisionService divisionService) {
        super(idModel);
        this.divisionService = divisionService;
    }

    @Override
    protected Division doLoad(Integer id) throws NoxitException {
        return divisionService.get(id);
    }

    @Override
    protected Integer getObjectId(Division object) {
        return object.getId();
    }

}