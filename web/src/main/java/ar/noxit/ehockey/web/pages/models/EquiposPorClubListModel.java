package ar.noxit.ehockey.web.pages.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class EquiposPorClubListModel extends LDM<List<Equipo>> {

    private IClubService clubService;
    private IModel<Integer> idClub;

    public EquiposPorClubListModel(IModel<Integer> idClub, IClubService clubService) {
        super();
        Validate.notNull(clubService);
        Validate.notNull(idClub);
        this.clubService = clubService;
        this.idClub = idClub;
    }

    @Override
    protected List<Equipo> doLoad() throws NoxitException {
        if (idClub.getObject() == null) {
            return new ArrayList<Equipo>();
        }
        return clubService.getEquiposPorClub(idClub.getObject());
    }
}