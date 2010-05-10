package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.TarjetaYaUsadaException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.Validate;

public class Tarjeta {

    public enum TipoTarjeta {
        ROJA, AMARILLA, VERDE
    }

    private static final Map<TipoTarjeta, Integer> valores = new HashMap<TipoTarjeta, Integer>();
    static {
        valores.put(TipoTarjeta.ROJA, 10);
        valores.put(TipoTarjeta.AMARILLA, 5);
        valores.put(TipoTarjeta.VERDE, 2);
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

    public int getValor() {
        if (usada)
            return 0;
        return valores.get(tipo);
    }

    public Integer getId() {
        return id;
    }

    protected Tarjeta() {
    }
}
