package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class TorneoListModel extends LoadableDetachableModel<List<Torneo>> {

    private ITorneoService torneoService;

    public TorneoListModel(ITorneoService torneoService) {
        Validate.notNull(torneoService);
        this.torneoService = torneoService;
    }

    @Override
    protected List<Torneo> load() {
        try {
            return torneoService.getAll();
        } catch (NoxitException ex) {
            // #TODO
            throw new NoxitRuntimeException(ex);
        }
    }

}
