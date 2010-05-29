package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import ar.noxit.ehockey.exception.ClubNoAccesibleException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.exceptions.NoxitException;

/**
 * Este interceptor solo filtra si se devuelven clubes que no se
 * pueden obtener. Si se pide al service información sobre un club
 * no permitido (como ser jugadores) el interceptor no hace nada.
 * @author tito
 *
 */
public class ClubByRolInterceptor implements MethodInterceptor {

    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Usuario logged = AuthSession.get().getUserLogged();
        Object resultado = invocation.proceed();

        //si es un representante solo dejo pasar el club del representante
        if (logged instanceof Representante) {
            Representante rep = (Representante)logged;
            if (resultado instanceof Club) {
                validarClub((Club)resultado, rep);
            } else if (esListaClubes(resultado)) {
                resultado = filtrarListaClubes((List<Club>)resultado, rep);
            }
        }

        return resultado;
    }

    private void validarClub(Club club, Representante rep) throws NoxitException {
        if (!rep.getClub().equals(club))
            throw new ClubNoAccesibleException();
    }

    private boolean esListaClubes(Object input) {
        if (input instanceof List<?>) {
            List<?> lista = (List<?>)input;

            Iterator<?> it = lista.iterator();
            if (it.hasNext()) {
                return (it.next() instanceof Club);
            } else {
                //si la lista esta vacía digo que no es lista de clubes
                return false;
            }
        } else {
            //si no es lista contesto false
            return false;
        }
    }

    private List<Club> filtrarListaClubes(List<Club> lista, Representante rep) {
        List<Club> nueva = new ArrayList<Club>();
        Club clubRep = rep.getClub();
        for (Club club : lista) {
            if (club.equals(clubRep)) nueva.add(club);
        }
        return nueva;
    }
}
