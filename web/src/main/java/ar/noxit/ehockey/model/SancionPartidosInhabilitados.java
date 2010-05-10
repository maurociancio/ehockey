package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.SinPartidosException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class SancionPartidosInhabilitados implements ISancion {

    private Set<Partido> partidosInhabilitados = new HashSet<Partido>();

    public SancionPartidosInhabilitados(Collection<Partido> suspendidos) throws SinPartidosException {
        Validate.notNull(suspendidos, "partidos suspendidos no puede ser null");
        Validate.noNullElements(suspendidos, "ningun elemento de la colección puede ser null");

        if (suspendidos.isEmpty()) {
            throw new SinPartidosException("no se puede crear una sancion sin partidos");
        }

        partidosInhabilitados.addAll(suspendidos);
    }

    @Override
    public boolean puedeJugar(Partido partido) {
        Validate.notNull(partido, "partido no puede ser null");

        return !partidosInhabilitados.contains(partido);
    }
}
