package fer.gis.backend.model.enitity.location;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="meteo_stations")
@Data
public class Location {
    @Id
    private long locid;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
}
