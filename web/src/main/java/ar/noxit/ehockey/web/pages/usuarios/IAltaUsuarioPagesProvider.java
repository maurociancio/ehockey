package ar.noxit.ehockey.web.pages.usuarios;

import java.util.List;

public interface IAltaUsuarioPagesProvider {

    List<String> getListaPaginas();
    AltaUsuarioPage getPagina(String tipo);
}
