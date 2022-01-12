package fer.gis.backend.model.enitity.location;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.Column;
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
    @Column(columnDefinition = "POINT")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point geom;
}
