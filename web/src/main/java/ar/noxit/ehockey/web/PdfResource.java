package ar.noxit.ehockey.web;

import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.protocol.http.WebResponse;

public abstract class PdfResource extends DynamicWebResource {

    @Override
    protected ResourceState getResourceState() {
        return new ResourceState() {

            @Override
            public String getContentType() {
                return "application/pdf";
            }

            @Override
            public byte[] getData() {
                return PdfResource.this.getData();
            }
        };
    }

    public abstract byte[] getData();

    @Override
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);
        response.setAttachmentHeader(getFileName());
    }

    protected String getFileName() {
        return "test.pdf";
    }
}