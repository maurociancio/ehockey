package ar.noxit.ehockey.model;

import ar.noxit.exceptions.NoxitRuntimeException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.Validate;

public class PartidosComparator implements Comparator<Partido> {

    private List<Comparator<Partido>> comparators = new ArrayList<Comparator<Partido>>();
    private boolean throwExceptionOnEquals = false;

    public static PartidosComparator comparatorPorRuedayFecha() {
        return new PartidosComparator().
                add(new RuedaComparator()).
                add(new FechaDelTorneoComparator()).
                setThrowExceptionOnEquals(true);
    }

    public PartidosComparator() {
    }

    public PartidosComparator(Collection<Comparator<Partido>> comparators) {
        Validate.notNull(comparators);
        Validate.noNullElements(comparators);

        this.comparators.addAll(comparators);
    }

    public PartidosComparator add(Comparator<Partido> comparator) {
        Validate.notNull(comparator);

        comparators.add(comparator);
        return this;
    }

    @Override
    public int compare(Partido o1, Partido o2) {
        for (Comparator<Partido> c : comparators) {
            Integer result = c.compare(o1, o2);
            if (result != 0)
                return result;
        }

        if (throwExceptionOnEquals()) {
            throw new NoxitRuntimeException("se encontraron dos partidos iguales");
        }
        return 0;
    }

    public boolean throwExceptionOnEquals() {
        return throwExceptionOnEquals;
    }

    public PartidosComparator setThrowExceptionOnEquals(boolean throwExceptionOnEquals) {
        this.throwExceptionOnEquals = throwExceptionOnEquals;
        return this;
    }

    public static class RuedaComparator implements Comparator<Partido> {

        @Override
        public int compare(Partido o1, Partido o2) {
            return o1.getRueda() - o2.getRueda();
        }
    }

    public static class FechaDelTorneoComparator implements Comparator<Partido> {

        @Override
        public int compare(Partido o1, Partido o2) {
            return o1.getFechaDelTorneo() - o2.getFechaDelTorneo();
        }
    }

    public static class PartidoComparator implements Comparator<Partido> {

        @Override
        public int compare(Partido o1, Partido o2) {
            return o1.getPartido() - o2.getPartido();
        }
    }
}
