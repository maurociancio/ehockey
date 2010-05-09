package ar.noxit.ehockey.service.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.dao.DataIntegrityViolationException;

import ar.noxit.ehockey.exception.JugadorExistenteException;

public class ConstraintExceptionInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (DataIntegrityViolationException ex) {
            throw new JugadorExistenteException(ex);
        }
    }
}
