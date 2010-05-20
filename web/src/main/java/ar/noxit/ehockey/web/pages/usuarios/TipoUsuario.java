package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.model.Usuario;

public class TipoUsuario {

    Class<? extends Usuario> tipo;
    public TipoUsuario(Class<? extends Usuario> tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(tipo);
    }
}
