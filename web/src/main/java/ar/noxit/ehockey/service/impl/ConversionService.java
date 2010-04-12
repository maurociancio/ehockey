package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IConversionService;
import ar.noxit.exceptions.NoxitRuntimeException;
import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class ConversionService implements IConversionService {

    private OpenOfficeConnection openOfficeConnection;

    @Override
    public byte[] convertir(String html) {
        try {
            openOfficeConnection.connect();
            DocumentConverter documentConverter = new OpenOfficeDocumentConverter(openOfficeConnection);

            // entrada
            File tempFile = File.createTempFile("entrada", ".html");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(html);
            fileWriter.close();

            // salida
            File out = File.createTempFile("salida", ".pdf");

            DefaultDocumentFormatRegistry defaultDocumentFormatRegistry = new DefaultDocumentFormatRegistry();
            DocumentFormat inputFormat = defaultDocumentFormatRegistry.getFormatByFileExtension("html");
            DocumentFormat outputFormat = defaultDocumentFormatRegistry.getFormatByFileExtension("pdf");
            documentConverter.convert(tempFile, inputFormat, out, outputFormat);

            // array
            int length = Long.valueOf(out.length()).intValue();
            byte[] buffer = new byte[length];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(out));
            bufferedInputStream.read(buffer);

            return buffer;
        } catch (Exception e) {
            throw new NoxitRuntimeException(e);
        } finally {
            openOfficeConnection.disconnect();
        }
    }

    public void setOpenOfficeConnection(OpenOfficeConnection openOfficeConnection) {
        this.openOfficeConnection = openOfficeConnection;
    }
}
