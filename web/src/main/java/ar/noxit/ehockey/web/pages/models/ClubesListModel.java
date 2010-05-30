package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class ClubesListModel extends LoadableDetachableModel<List<Club>> {

    private IClubService clubService;

    public ClubesListModel(IClubService clubService) {
        Validate.notNull(clubService);

        this.clubService = clubService;
    }

    @Override
    protected List<Club> load() {
        try {
            return clubService.getAll();
        } catch (NoxitException ex) {
            // #TODO
            throw new NoxitRuntimeException(ex);
        }
    }
}