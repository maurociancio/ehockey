package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IUsuarioDao;
import ar.noxit.ehockey.model.Usuario;

public class UsuarioDao extends HibernateDao<Usuario, String> implements IUsuarioDao {

    public UsuarioDao() {
        super(Usuario.class);
    }
}
