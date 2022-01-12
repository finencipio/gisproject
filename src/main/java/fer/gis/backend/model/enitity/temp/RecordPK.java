package fer.gis.backend.model.enitity.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordPK implements Serializable {
    @NotNull
    long location;
    @NotNull
    Timestamp mrTime;
    @NotNull
    String sysName;
}
