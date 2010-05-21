package ar.noxit.ehockey.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.usuarios.IUsuarioDTOProvider;
import ar.noxit.ehockey.web.pages.usuarios.TipoUsuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;

public class UsuarioDTOProvider implements IUsuarioDTOProvider{

    private Map<String, Class<? extends Usuario>> usuarioTypes;

    @Override
    public List<String> getListaTipos() {
        Iterator<Entry<String, Class<? extends Usuario>>> it = usuarioTypes.entrySet().iterator();
        List<String> opciones = new ArrayList<String>();
        while (it.hasNext()) {
            Entry<String, Class<? extends Usuario>> entry = it.next();
            opciones.add(entry.getKey());
        }
        return opciones;
    }

    @Override
    public UsuarioDTO createUsuarioDTO(String tipo) {
        return new UsuarioDTO(new TipoUsuario(usuarioTypes.get(tipo)));
    }

    public void setUsuarioTypes(Map<String, Class<? extends Usuario>> usuarioTypes) {
        this.usuarioTypes = usuarioTypes;
    }
}
