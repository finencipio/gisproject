package fer.gis.backend.model.enitity.temp;

import com.fasterxml.jackson.annotation.JsonFormat;
import fer.gis.backend.model.enitity.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "meteo_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(RecordPK.class)
public class TemperatureRecord implements Serializable {
    @Column(name = "mrsetid")
    private Long mSetId;
    @Id
    @Column(name = "mrtime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mrTime;

    private double value;

    @Column(name = "recordstatus")
    private int recordStatus;
    @Id
    @Column(name = "sysname")
    private String sysName;

    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "locid")
    @Id
    private Location location;
}
