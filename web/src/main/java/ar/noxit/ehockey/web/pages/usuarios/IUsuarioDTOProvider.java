package ar.noxit.ehockey.web.pages.usuarios;

import java.util.List;

public interface IUsuarioDTOProvider {

    List<String> getListaTipos();
    UsuarioDTO createUsuarioDTO(String tipo);
}
