package fer.gis.backend.service;

import fer.gis.backend.model.enitity.Landuse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanduseService {
//    @Autowired
//    private final LanduseRepository landuseRepository;
//
//    public List<Landuse> findAll() {
//        Pageable limit = PageRequest.of(0,10);
//        return landuseRepository.findAll(limit).toList();
//    }
}
