package ar.noxit.ehockey.model;

public interface ISancion {

    Integer getId();

    boolean puedeJugar(Partido partido);
}