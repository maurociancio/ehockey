package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.TarjetaYaUsadaException;

public class Tarjeta {

    public enum TipoTarjeta {
        ROJA, AMARILLA, VERDE
    }

    private Integer id;
    private TipoTarjeta tipo;
    private Partido partido;
    private boolean usada = false;

    public Tarjeta(TipoTarjeta tipo, Partido partido) {
        Validate.notNull(tipo, "el tipo de la tarjeta no puede ser null");
        Validate.notNull(partido, "el partido de la tarjeta no puede ser null");

        this.tipo = tipo;
        this.partido = partido;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void usar() throws TarjetaYaUsadaException {
        if (this.usada) {
            throw new TarjetaYaUsadaException("la tarjeta ya esta usada");
        }
        this.usada = true;
    }

    public boolean isUsada() {
        return this.usada;
    }

    public Integer getId() {
        return id;
    }

    protected Tarjeta() {
    }
}
