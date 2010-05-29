package ar.noxit.ehockey.web.pages.models;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class ClubModel extends IdLDM<Club, Integer> {

    private IClubService clubService;

    public ClubModel(IModel<Integer> idModel, IClubService clubService) {
        super(idModel);
        this.clubService = clubService;
    }

    @Override
    protected Club doLoad(Integer id) throws NoxitException {
        return clubService.get(id);
    }

    @Override
    protected Integer getObjectId(Club object) {
        return object.getId();
    }

}