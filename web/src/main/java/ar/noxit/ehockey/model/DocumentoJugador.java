package ar.noxit.ehockey.model;

public class DocumentoJugador {

    private String tipo;
    private String numero;

    public DocumentoJugador() {
    }

    public DocumentoJugador(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
