package ar.noxit.ehockey.web.pages.usuarios;

import java.io.Serializable;

import ar.noxit.ehockey.model.Usuario;

public class TipoUsuario implements Serializable {

    private Class<? extends Usuario> tipo;

    public TipoUsuario(Class<? extends Usuario> tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        return tipo.equals(obj);
    }
}
