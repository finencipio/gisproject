package fer.gis.backend.model.repository.temp;

import fer.gis.backend.model.enitity.temp.RecordPK;
import fer.gis.backend.model.enitity.temp.TemperatureRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TemperatureRepository extends JpaRepository<TemperatureRecord, RecordPK> {
    @Override
    List<TemperatureRecord> findAll();

    List<TemperatureRecord> findAllByMrTimeEquals(Timestamp timestamp);

    @Query("SELECT AVG(value) FROM meteo_records WHERE sysName = 'OutTemp' AND locid = :id")
    double findAverageOfTemperatures(@Param("id") int locid);
}
