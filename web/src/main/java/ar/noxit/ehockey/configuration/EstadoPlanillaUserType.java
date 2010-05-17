package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.model.EstadoPlanilla;
import ar.noxit.ehockey.model.EstadoPlanillaCargada;
import ar.noxit.ehockey.model.EstadoPlanillaFinalizada;
import ar.noxit.ehockey.model.EstadoPlanillaPublicada;
import ar.noxit.ehockey.model.EstadoPlanillaRechazada;
import ar.noxit.exceptions.NoxitRuntimeException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class EstadoPlanillaUserType implements UserType {

    private static final Map<Integer, Class<? extends EstadoPlanilla>> states = new HashMap<Integer, Class<? extends EstadoPlanilla>>();
    static {
        states.put(0, EstadoPlanillaCargada.class);
        states.put(1, EstadoPlanillaPublicada.class);
        states.put(2, EstadoPlanillaRechazada.class);
        states.put(3, EstadoPlanillaFinalizada.class);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        // si es la misma referencia entonces son iguales
        if (x == y) {
            return true;
        }

        // si alguno de los dos son null entonces no son iguales
        if (x == null || y == null) {
            return false;
        }

        // comos los estados son state-less basta con comparar que sean de la
        // misma clase
        return x.getClass().equals(y.getClass());
    }

    @Override
    public int hashCode(Object value) throws HibernateException {
        return value.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        final int stateValue = rs.getInt(names[0]);
        if (rs.wasNull()) {
            return null;
        }

        try {
            return states.get(stateValue).newInstance();
        } catch (Exception e) {
            throw new NoxitRuntimeException("Hibernate intentó crear un estado de planilla que no existe", e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        Validate.notNull(value, "No puede existir un estado nulo");
        try {
            Class<? extends Object> clazz = value.getClass();
            Integer stateId = searchClass(clazz);
            st.setInt(index, stateId);
        } catch (Exception ex) {
            throw new NoxitRuntimeException("Estado de planilla no reconocido", ex);
        }
    }

    private Integer searchClass(Class<? extends Object> clazz) {
        Iterator<Entry<Integer, Class<? extends EstadoPlanilla>>> it = states.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Integer, Class<? extends EstadoPlanilla>> next = it.next();
            if (next.getValue().equals(clazz)) {
                return next.getKey();
            }
        }
        throw new NoxitRuntimeException("no se encontro la clase " + clazz);
    }

    @Override
    public Class<?> returnedClass() {
        return EstadoPlanilla.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.INTEGER };
    }

    @Override
    public Object assemble(Serializable catched, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
