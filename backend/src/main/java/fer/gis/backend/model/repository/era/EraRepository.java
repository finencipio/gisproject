package fer.gis.backend.model.repository.era;

import fer.gis.backend.model.enitity.era.EraAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EraRepository extends JpaRepository<EraAnalysis, EraAnalysis.EraKey> {
    List<EraAnalysis> findAllByMrTimeEquals(Timestamp timestamp);
}
