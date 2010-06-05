package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class SectoresListModel extends LoadableDetachableModel<List<Sector>> {

    private ISectorService sectorService;

    public SectoresListModel(ISectorService sectorService) {
        Validate.notNull(sectorService);

        this.sectorService = sectorService;
    }

    @Override
    protected List<Sector> load() {
        try {
            return sectorService.getAll();
        } catch (NoxitException ex) {
            // #TODO
            throw new NoxitRuntimeException(ex);
        }
    }
}