package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class DivisionListModel extends LoadableDetachableModel<List<Division>> {

    private IDivisionService divisionService;

    public DivisionListModel(IDivisionService divisionService) {
        Validate.notNull(divisionService);

        this.divisionService = divisionService;
    }

    @Override
    protected List<Division> load() {
        try {
            return divisionService.getAll();
        } catch (NoxitException ex) {
            throw new NoxitRuntimeException(ex);
        }
    }
}