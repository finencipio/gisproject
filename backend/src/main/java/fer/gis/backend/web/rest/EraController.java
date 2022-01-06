package fer.gis.backend.web.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import fer.gis.backend.model.enitity.era.EraAnalysis;
import fer.gis.backend.model.repository.era.EraRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.OPTIONS}, allowCredentials = "true")
public class EraController {
    @Autowired
    private EraRepository eraRepository;

    @PostMapping("/era")
    public Collection<EraAnalysis> getAllMeasurementsForTimestamp(@RequestBody EraGet body) {
        return eraRepository.findAllByMrTimeEquals(body.getTimestamp());
    }

    @Data
    private static class EraGet {
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp timestamp;
    }
}
