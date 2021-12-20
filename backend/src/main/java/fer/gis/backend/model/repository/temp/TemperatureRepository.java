package fer.gis.backend.model.repository.temp;

import fer.gis.backend.model.enitity.temp.TempPK;
import fer.gis.backend.model.enitity.temp.TemperatureMesurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TemperatureRepository extends JpaRepository<TemperatureMesurement, TempPK> {
    @Override
    List<TemperatureMesurement> findAll();

    List<TemperatureMesurement> findAllByMrTimeEquals(Timestamp timestamp);
}
