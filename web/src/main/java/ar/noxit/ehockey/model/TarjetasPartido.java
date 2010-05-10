package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class TarjetasPartido {

    private Integer rojas;
    private Integer amarillas;
    private Integer verdes;

    public TarjetasPartido(Integer rojas, Integer amarillas, Integer verdes) {
        validarTarjeta(rojas);
        validarTarjeta(amarillas);
        validarTarjeta(verdes);

        this.rojas = rojas;
        this.amarillas = amarillas;
        this.verdes = verdes;
    }

    private void validarTarjeta(Integer tarjetas) {
        Validate.notNull(tarjetas);
        Validate.isTrue(tarjetas >= 0);
    }

    public Integer getRojas() {
        return rojas;
    }

    public void setRojas(Integer rojas) {
        validarTarjeta(rojas);

        this.rojas = rojas;
    }

    public Integer getAmarillas() {
        return amarillas;
    }

    public void setAmarillas(Integer amarillas) {
        validarTarjeta(amarillas);

        this.amarillas = amarillas;
    }

    public Integer getVerdes() {
        return verdes;
    }

    public void setVerdes(Integer verdes) {
        validarTarjeta(verdes);

        this.verdes = verdes;
    }

    protected TarjetasPartido() {
    }

    public static TarjetasPartido zeroed() {
        return new TarjetasPartido(0, 0, 0);
    }
}
