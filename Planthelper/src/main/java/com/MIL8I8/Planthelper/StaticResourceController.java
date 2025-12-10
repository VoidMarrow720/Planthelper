package com.MIL8I8.Planthelper;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class StaticResourceController {

    @GetMapping(path = "/bckgrnd.jpg")
    public ResponseEntity<Resource> backgroundImage() {
        try {
            // Try to serve bckgrnd.jpg from repository root (one level above the module)
            Path cwd = Path.of(System.getProperty("user.dir"));
            Path candidate = cwd.resolveSibling("bckgrnd.jpg");
            if (!Files.exists(candidate)) {
                // fallback: check module static folder
                candidate = cwd.resolve("src").resolve("main").resolve("resources").resolve("static").resolve("bckgrnd.jpg");
            }
            if (!Files.exists(candidate)) {
                return ResponseEntity.notFound().build();
            }
            Resource res = new UrlResource(candidate.toUri());
            String contentType = Files.probeContentType(candidate);
            if (contentType == null) contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(res);
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
