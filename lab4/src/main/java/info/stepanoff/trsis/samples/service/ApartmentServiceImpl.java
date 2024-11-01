/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.stepanoff.trsis.samples.db.dao.ApartmentRepository;
import info.stepanoff.trsis.samples.db.model.ApartmentPE;
import info.stepanoff.trsis.samples.rest.model.ApartmentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository ApartmentRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<ApartmentDTO> listAll() {
        return ApartmentRepository.findAll().stream()
                .map(ApartmentPE -> objectMapper.convertValue(ApartmentPE, ApartmentDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        ApartmentRepository.deleteById(id);
    }

    @Override
    public ApartmentDTO add(String city, String address, Integer price) {
        return objectMapper.convertValue(ApartmentRepository.save(new ApartmentPE(city, address, price)), ApartmentDTO.class);
    }

    @Override
    public ApartmentDTO findByCity(String city) {
        var studentPE = ApartmentRepository.findByCity(city);
        return studentPE.map(Apartment -> objectMapper.convertValue(Apartment, ApartmentDTO.class)).orElse(null);
    }
}
