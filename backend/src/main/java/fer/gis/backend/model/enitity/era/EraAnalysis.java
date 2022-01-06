package fer.gis.backend.model.enitity.era;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "era")
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(EraAnalysis.EraKey.class)
public class EraAnalysis implements Serializable{
    @Id
    private double latitude;
    @Id
    private double longitude;
    @Id
    @Column(name = "measuredtime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mrTime;
    @Column(name="temperature")
    private double value;
    @Column(columnDefinition = "GEOMETRY")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Geometry geom;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EraKey implements Serializable {
        double latitude;
        double longitude;
        Timestamp mrTime;
    }
}
