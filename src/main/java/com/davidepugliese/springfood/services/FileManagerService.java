package com.davidepugliese.springfood.services;

import com.google.api.client.util.Charsets;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles requests for the application file upload requests
 */
@Service
public class FileManagerService {

    private static final Logger logger = LoggerFactory
            .getLogger(FileManagerService.class);


    /**
     * Upload single file using Spring Controller
     */
    public @ResponseBody
        Map<String, String> save(@RequestParam("name") String name,
                                                          @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + File.separator + "media");
                if (!dir.exists())
                    dir.mkdirs();
                String date = LocalDateTime.now().toString();
                String[] fileNameParts = name.split("\\.");
                int extensionIndex = fileNameParts.length - 1;
                String fileExtension = fileNameParts[extensionIndex];
                Hashing.md5().hashString(date, Charsets.UTF_8 ).toString();
                name = name + date;
                name = Hashing.sha1().hashString( name, Charsets.UTF_8 ).toString();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name + "." + fileExtension);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "You successfully uploaded successfully " + name + "." + fileExtension +"!");
                response.put("data", String.format("%s.%s", name, fileExtension));
                return response;

            } catch (Exception e) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "fail");
                response.put("reason", "You failed to upload " + name +  " => " + e.getMessage());
                return response;
            }
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            response.put("reason", "You failed to upload " + name + " because the file was empty.");
            return response;
        }
    }

    /**
     * Upload multiple file using Spring Controller
     */
    public @ResponseBody
    Map<String, String> saveMany(@RequestParam("name") String[] names,
                                     @RequestParam("file") MultipartFile[] files) {

        Map<String, String> response = new HashMap<>();


        if (files.length != names.length)
        {
            response.put("status", "fail");
            response.put("reason", "Mandatory information missing");
            return response;
        }

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("user.dir");
                File dir = new File(rootPath + File.separator + "media");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                String[] fileNameParts = name.split("\\.");
                int extensionIndex = fileNameParts.length - 1;
                String fileExtension = fileNameParts[extensionIndex];
                name = Hashing.sha1().hashString( name, Charsets.UTF_8 ).toString();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name + "." + fileExtension);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                message = message + "You successfully uploaded file " + name + ",";
            } catch (Exception e) {
                response.put("status", "fail");
                response.put("reason", "You failed to upload " + name + " => " + e.getMessage());
                return response;
            }
        }

        response.put("status", "success");
        response.put("message", message);
        return response;
    }

//    public @ResponseBody
//    ResponseEntity<Map<String, String>> feedFile(@RequestParam("name") String name,
//                                                          @RequestParam("file") MultipartFile file) {
//        String path = null;
//        Map<String, String> response = new HashMap<>();
////        final DefaultResourceLoader loader = new DefaultResourceLoader();
////        logger.info(loader.getResource("classpath:META-INF/resources/img/copyright.png").exists());
////        Resource resource = loader.getResource("classpath:META-INF/resources/img/copyright.png");
////        BufferedImage watermarkImage = ImageIO.read(resource.getFile());
//
//
////        if (!dir.exists())
////
////        {
////
////            response.put("status", "success");
////            response.put("message", path);
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
////        }
//
//        response.put("status", "success");
//        response.put("message", path);
//        return ResponseEntity.ok(response);
//
//    }
}
