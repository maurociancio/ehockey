package ar.noxit.ehockey.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ar.noxit.ehockey.web.pages.usuarios.AltaUsuarioPage;
import ar.noxit.ehockey.web.pages.usuarios.IAltaUsuarioPagesProvider;
import ar.noxit.exceptions.NoxitRuntimeException;

public class AltaUsuarioPagesProvider implements IAltaUsuarioPagesProvider{

    private Map<String, Class<? extends AltaUsuarioPage>> usuarioPages;

    @Override
    public List<String> getListaPaginas() {
        Iterator<Entry<String, Class<? extends AltaUsuarioPage>>> it = usuarioPages.entrySet().iterator();
        List<String> opciones = new ArrayList<String>();
        while (it.hasNext()) {
            Entry<String, Class<? extends AltaUsuarioPage>> entry = it.next();
            opciones.add(entry.getKey());
        }
        return opciones;
    }

    @Override
    public AltaUsuarioPage getPagina(String tipo) {
        try {
            return usuarioPages.get(tipo).newInstance();
        } catch (InstantiationException e) {
            throw new NoxitRuntimeException("No se puede instanciar la página", e);
        } catch (IllegalAccessException e) {
            throw new NoxitRuntimeException("No se puede instanciar la página", e);
        }
    }

    public void setUsuarioPages(Map<String, Class<? extends AltaUsuarioPage>> usuarioPages) {
        this.usuarioPages = usuarioPages;
    }
}
