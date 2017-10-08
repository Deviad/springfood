package com.davidepugliese.springfood.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.davidepugliese.springfood.services.FileManagerService;
import com.google.api.client.util.Charsets;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;

/**
 * Handles requests for the application file upload requests
 */
@RestController
@RequestMapping("/api/")
public class FileManagerController {

    private static final Logger logger = LoggerFactory
            .getLogger(FileManagerController.class);

    private FileManagerService fileService;


    @Autowired
    public FileManagerController(FileManagerService fileService) {
        this.fileService = fileService;
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, String>> uploadFileHandler(@RequestParam("name") String name,
                                                          @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(fileService.save(name, file));

    }

    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "/image/uploadMultipleFile", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Map<String, String>> uploadMultipleImageHandler(@RequestParam("name") String[] names,
                                     @RequestParam("file") MultipartFile[] files) {

        return ResponseEntity.ok(fileService.saveMany(names, files));

    }

//    @RequestMapping(value = "/image/download/", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public @ResponseBody
//    ResponseEntity<Map<String, String>> downloadImageHandler(@RequestParam("name") String name,
//                                                          @RequestParam("file") MultipartFile file) {
//
//        return fileService.feedFile(name, file);
//
//    }
}
