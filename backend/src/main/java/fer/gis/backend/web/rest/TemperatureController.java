package fer.gis.backend.web.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fer.gis.backend.model.enitity.temp.TemperatureRecord;
import fer.gis.backend.model.repository.temp.TemperatureRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.OPTIONS}, allowCredentials = "true")
public class TemperatureController {
    @Autowired
    private TemperatureRepository temperatureRepository;

    @PostMapping("/temp")
    public Collection<TemperatureRecord> getAllMeasurementsForTimestamp(@RequestBody TempGet body) {
        return temperatureRepository.findAllByMrTimeEquals(body.getTimestamp());
    }

    @PostMapping("/temp/average/{locid}")
    public double getAverageTemperature(@PathVariable int locid){
        return temperatureRepository.findAverageOfTemperatures(locid);
    }
    @Data
    private static class TempGet {

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp timestamp;
    }
}
