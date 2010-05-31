package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.web.wicket.model.AdapterModel;
import org.apache.wicket.model.IModel;

public class EquipoAdapterModel extends AdapterModel<EquipoPlano, Equipo> {

    private boolean loaded = false;
    private EquipoPlano plano = new EquipoPlano();

    public EquipoAdapterModel(IModel<Equipo> delegate) {
        super(delegate);
    }

    @Override
    protected EquipoPlano getObject(IModel<Equipo> delegate) {
        if (!loaded) {
            loaded = true;
            if (plano != null) {
                Equipo equipo = delegate.getObject();
                plano.setId(equipo.getId());
                plano.setNombre(equipo.getNombre());
                plano.setClubId(equipo.getClub().getId());
                plano.setDivisionId(equipo.getDivision().getId());
                plano.setSectorId(equipo.getSector().getId());
            }
        }
        return plano;
    }

    @Override
    protected void setObject(EquipoPlano object, IModel<Equipo> delegate) {
        loaded = true;
        this.plano = object;
    }
}
