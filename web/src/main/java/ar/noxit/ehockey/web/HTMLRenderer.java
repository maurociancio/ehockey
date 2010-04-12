package ar.noxit.ehockey.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.protocol.http.BufferedWebResponse;
import org.apache.wicket.protocol.http.MockHttpServletRequest;
import org.apache.wicket.protocol.http.MockHttpServletResponse;
import org.apache.wicket.protocol.http.MockHttpSession;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.request.WebErrorCodeResponseTarget;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.target.component.BookmarkablePageRequestTarget;

public class HTMLRenderer {

    public String renderPage(Class<? extends Page> pageClass, PageParameters pageParameters) {
        // get the servlet context
        WebApplication application = WebApplication.get();

        ServletContext context = application.getServletContext();

        // fake a request/response cycle
        MockHttpSession servletSession = new MockHttpSession(context);
        servletSession.setTemporary(true);

        MockHttpServletRequest servletRequest = new MockHttpServletRequest(
                application, servletSession, context);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse(
                servletRequest);

        // initialize request and response
        servletRequest.initialize();
        servletResponse.initialize();

        WebRequest webRequest = new ServletWebRequest(servletRequest);

        BufferedWebResponse webResponse = new BufferedWebResponse(servletResponse);
        webResponse.setAjax(true);

        WebRequestCycle requestCycle = new WebRequestCycle(
                application, webRequest, webResponse);

        requestCycle.setRequestTarget(new BookmarkablePageRequestTarget(pageClass, pageParameters));

        try {
            requestCycle.request();

            if (requestCycle.wasHandled() == false) {
                requestCycle.setRequestTarget(new WebErrorCodeResponseTarget(
                        HttpServletResponse.SC_NOT_FOUND));
            }
            requestCycle.detach();

        } finally {
            requestCycle.getResponse().close();
        }

        return webResponse.toString();
    }
}
