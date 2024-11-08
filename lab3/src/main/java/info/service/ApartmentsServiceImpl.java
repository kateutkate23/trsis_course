package info.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.db.dao.ApartmentsRepository;
import info.db.model.ApartmentsPE;
import info.rest.model.ApartmentsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApartmentsServiceImpl implements ApartmentsService {

    private final ApartmentsRepository apartmentsRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<ApartmentsDTO> listAll() {
        return apartmentsRepository.findAll().stream()
                .map(apartmentsPE -> objectMapper.convertValue(apartmentsPE, ApartmentsDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer ID) {
        apartmentsRepository.deleteById(ID);
    }

    @Override
    public ApartmentsDTO add(String city, String address, Integer price) {
        return objectMapper.convertValue(apartmentsRepository.save(new ApartmentsPE(city, address, price)), ApartmentsDTO.class);
    }

    @Override
    public ApartmentsDTO findByID(Integer ID) {
        var ApartmentsPE = apartmentsRepository.findByID(ID);
        return ApartmentsPE.map(apartmentsPE -> objectMapper.convertValue(apartmentsPE, ApartmentsDTO.class)).orElse(null);
    }
}