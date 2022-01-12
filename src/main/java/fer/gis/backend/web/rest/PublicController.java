package fer.gis.backend.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.OPTIONS}, allowCredentials = "true")
public class PublicController {

    @Value("${:classpath:/static/index.html}")
    private Resource index;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity actions() throws IOException {
        return ResponseEntity.ok(new InputStreamResource(index.getInputStream()));
    }
}
