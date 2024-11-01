/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.rest.model.ApartmentDTO;

import java.util.List;

public interface ApartmentService {

    List<ApartmentDTO> listAll();

    void delete(Integer id);
    
    ApartmentDTO add(String city, String address, Integer price);
    
    ApartmentDTO findByCity(String city);

}
