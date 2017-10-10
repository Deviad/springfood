package com.davidepugliese.springfood.services;

import com.davidepugliese.springfood.adt.FormFileName;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.Charsets;
import com.google.common.hash.Hashing;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //TODO: this feature needs refactoring for supporting application/JSON.
    // I will send a compressed archive from the front-end and then
    // I will use system tools to unzip it in the backend

    /**
     * Upload multiple file using Spring Controller
     */
    public @ResponseBody
    Map<String, Object> saveMany(@RequestParam("names") String names,
                                     @RequestParam("files") MultipartFile[] files) {

        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse(names).getAsJsonObject();
        JsonArray namesArray = rootObj.getAsJsonArray("names");
        Map<String, Object> response = new HashMap<>();

        System.out.println(namesArray.size());
        System.out.println(files.length);
        if (files.length != namesArray.size())
        {
            response.put("status", "fail");
            response.put("reason", "Mandatory information missing");
            return response;
        }

        List<String> message = new ArrayList<>();
        Map<String, String> data = new ArrayMap<>();
        for (MultipartFile file: files) {
            for(JsonElement nameEl: namesArray) {
                String name = nameEl.toString();
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
                    name = Hashing.sha1().hashString(name, Charsets.UTF_8).toString();
                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + name + "." + fileExtension);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());

                    message.add(String.format("You successfully uploaded file %s", name));
                    data.put("data", String.format("%s.%s", name, fileExtension));
                } catch (Exception e) {
                    response.put("status", "fail");
                    response.put("reason", "You failed to upload " + name + " => " + e.getMessage());
                    return response;
                }
            }
        }

        response.put("status", "success");
        response.put("data", data);
        response.put("message", message);
        return response;
    }
}
