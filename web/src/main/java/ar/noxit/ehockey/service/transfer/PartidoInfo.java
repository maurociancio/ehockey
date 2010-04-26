package ar.noxit.ehockey.service.transfer;

import ar.noxit.ehockey.model.Partido;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class PartidoInfo implements Serializable {

    private Integer id;
    private Integer equipoLocalId;
    private Integer equipoVisitanteId;
    private Integer numeroFecha;
    private LocalDateTime fecha;

    public static PartidoInfo construir(Partido p) {
        PartidoInfo pi = new PartidoInfo();

        pi.setId(p.getId());
        pi.setEquipoLocalId(p.getLocal().getId());
        pi.setEquipoVisitanteId(p.getVisitante().getId());
        pi.setFecha(p.getInicio());
        pi.setNumeroFecha(p.getFechaDelTorneo());

        return pi;
    }

    public PartidoInfo() {
    }

    public Integer getEquipoLocalId() {
        return equipoLocalId;
    }

    public void setEquipoLocalId(Integer equipoLocalId) {
        this.equipoLocalId = equipoLocalId;
    }

    public Integer getEquipoVisitanteId() {
        return equipoVisitanteId;
    }

    public void setEquipoVisitanteId(Integer equipoVisitanteId) {
        this.equipoVisitanteId = equipoVisitanteId;
    }

    public Integer getNumeroFecha() {
        return numeroFecha;
    }

    public void setNumeroFecha(Integer numeroFecha) {
        this.numeroFecha = numeroFecha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
