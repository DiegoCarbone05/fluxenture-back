package com.servisub.core.cd.application;

import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.*;
import com.servisub.core.storage.service.storage.GoogleDriveService;
import com.servisub.core.storage.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import com.lowagie.text.Document;

@Service
public class ExportTntUseCase {

    @Autowired
    StorageService storageService;
    @Autowired
    GoogleDriveService googleDriveService;

    public ResponseEntity<?> execute(String base64, String fileId) throws IOException  {
        try{
            byte[] bytes = pdfLinker(base64, fileId);
            storageService.updateFile(fileId, bytes, "application/pdf");
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

    private byte[] pdfLinker(String base64, String fileId) throws IOException {

        InputStream pdfOriginal = googleDriveService.downloadFile(fileId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try{
            PdfReader reader = new PdfReader(pdfOriginal);
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            //PEGAR PAGINA 1
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader, 1);
            cb.addTemplate(page, 0, 0);

            //PEGAR PAGINA 2
            String cleanBase64 = base64.contains(",") ? base64.split(",")[1] : base64;
            Image img = Image.getInstance(Base64.getDecoder().decode(cleanBase64));

            document.newPage();
            img.scaleToFit(PageSize.A4.getWidth() - 40, PageSize.A4.getHeight() - 40);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);

            document.close();
            reader.close();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return outputStream.toByteArray();
    }


}
