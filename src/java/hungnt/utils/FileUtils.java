/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author hungn
 */
public class FileUtils {
    private static final String SAVE_DIR = "/web/resources/images";
   
    public static String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        // gets absolute path of the web application
        String realPath = request.getServletContext().getRealPath("");
        String appPath = realPath.substring(0, realPath.indexOf("\\build"));

        // constructs path of the directory to save uploaded file
        String savePath = appPath + SAVE_DIR;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part filePart = request.getPart("image");
        if (filePart == null) {
            return "";
        }

        fileName = extractFileName(filePart);
        if (fileName.isEmpty()) {
            return "";
        } else {
            OutputStream outputStream = null;
            InputStream fileContent = filePart.getInputStream();

            File targetFile = new File(savePath + File.separator + fileName);

            outputStream = new FileOutputStream(targetFile);

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
        return fileName;
    }
    
    private static String extractFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
