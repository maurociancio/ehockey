package ar.noxit.ehockey.configuration;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import ar.noxit.ehockey.model.EstadoPlanilla;
import ar.noxit.ehockey.model.EstadoPlanillaCargada;
import ar.noxit.ehockey.model.EstadoPlanillaFinalizada;
import ar.noxit.ehockey.model.EstadoPlanillaPublicada;
import ar.noxit.ehockey.model.EstadoPlanillaRechazada;
import ar.noxit.exceptions.NoxitRuntimeException;

public class EstadoPlanillaUserType implements UserType {

    static final List<EstadoPlanilla> estados = new ArrayList<EstadoPlanilla>();
    static {
        estados.set(0, new EstadoPlanillaCargada());
        estados.set(1, new EstadoPlanillaPublicada());
        estados.set(2, new EstadoPlanillaRechazada());
        estados.set(3, new EstadoPlanillaFinalizada());
    }

    @Override
    public Object assemble(Serializable catched, Object owner) throws HibernateException {
        return catched;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
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
            return estados.get(stateValue);
        } catch (IndexOutOfBoundsException ex) {
            throw new NoxitRuntimeException("Hibernate intentó crear un estado de planilla que no existe");
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        Validate.notNull(value, "No puede existir un estado nulo");
        try {
            Integer stateId = estados.indexOf(value);
            if (stateId == -1) throw new NoxitRuntimeException("Estado de planilla no reconocido");
            st.setInt(index, stateId);
        } catch (Exception ex) {
            throw new NoxitRuntimeException("Estado de planilla no reconocido");
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class returnedClass() {
        return EstadoPlanilla.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.INTEGER};
    }

}
