package ar.noxit.ehockey.model;

public class Partido {

    private Equipo local;
    private Equipo visitante;

    /**
     * Dummy partido, ya que necesito los equipos para cada ronda.
     */
    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    @Override
    public boolean equals(Object o) {
        Partido obj = (Partido) o;
        return ((this.local == obj.local) && (this.visitante == obj.visitante));

    }

}
