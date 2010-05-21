package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

import ar.noxit.ehockey.model.Usuario;

public class UsuarioBasePanel extends Panel {

    private String password;
    private IFormValidator passwordValidator;
    private IFormValidator passwordLenghtValidator;

    public UsuarioBasePanel(String id, IModel<Usuario> usuario) {
        super(id, usuario);

        FormComponent passText = new PasswordTextField("contrasena", new PropertyModel<String>(usuario, "password"))
                .add(new MinimumLengthValidator(6));
        FormComponent verifyText = new PasswordTextField("contrasena2", new PropertyModel<String>(usuario, "password"));
        add(new RequiredTextField<String>("usuario", new PropertyModel<String>(usuario, "user")));
        add(passText);
        add(verifyText);
        add(new RequiredTextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
        add(new RequiredTextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));

        passwordValidator = new EqualPasswordInputValidator(passText, verifyText);
        // org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator
        // org.apache.wicket.validation.validator.StringValidator.minimumLength(int)
        // JugadorBajaPage.properties para el validador y los mensajes
    }

    public void addValidators(Form<Void> form) {
        form.add(passwordValidator);
    }
}
