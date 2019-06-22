import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@WebServlet(urlPatterns = "/upload")
@MultipartConfig(maxFileSize=1024,maxRequestSize=104857600)
public class UploadingImageServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        Part filePart = request.getPart("filename");

        File fileToSave = new File(filePart.getSubmittedFileName());

        InputStream fileInputStream = filePart.getInputStream();

        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("which path: " + fileToSave.toPath());

        response.getOutputStream().print("done!");

    }

}
