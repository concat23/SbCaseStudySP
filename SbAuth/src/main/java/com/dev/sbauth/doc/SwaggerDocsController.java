package com.dev.sbauth.doc;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/docs")
public class SwaggerDocsController {

    @GetMapping("/swagger.yaml")
    public ResponseEntity<Resource> getSwaggerYaml() throws IOException {
        Resource resource = new ClassPathResource("swagger.yaml");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/yaml")
                .body(resource);
    }
}