package ar.noxit.ehockey.model;

import java.io.Serializable;

public class DatosTabla implements Serializable {

    public static int PUNTOSPARTIDOGANADO = 3;
    public static int PUNTOSPARTIDOEMPATADO = 1;

    private String nombre;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;

    public DatosTabla(String equipo) {
        this.nombre = equipo;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getPuntos() {
        return ((PUNTOSPARTIDOEMPATADO * partidosEmpatados) + (PUNTOSPARTIDOGANADO * partidosGanados));
    }

    public int getDiferenciaGol() {
        return golesFavor - golesContra;
    }

    public DatosTabla jugarPartido(int golesFavor, int golesContra) {
        this.partidosJugados++;
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;
        if (golesFavor > golesContra) {
            this.partidosGanados++;
        } else if (golesFavor < golesContra) {
            this.partidosPerdidos++;
        } else {
            this.partidosEmpatados++;
        }
        return this;
    }
}
