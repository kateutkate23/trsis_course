/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.rest.model.ApartmentDTO;
import info.stepanoff.trsis.samples.rest.model.ApartmentDataWithoutID;
import info.stepanoff.trsis.samples.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/public/rest/apartments", produces = "application/json")
@RequiredArgsConstructor
public class ApartmentRestController {

    private final ApartmentService apartmentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Operation(summary = "Получить список квартир для сдачи",
            description = "Получить список квартир для сдачи - расширенное описание",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    public ResponseEntity<List<ApartmentDTO>> browse() {
        return ResponseEntity.ok(apartmentService.listAll());
    }

    @Operation(summary = "Удаление записи о квартире",
            description = "Удаление записи о квартире - расширенное описание",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")
            @Parameter(description = "Идентификатор квартиры") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        apartmentService.delete(id);
    }

    @Operation(summary = "Демонстрация работы delete при помощи метода get",
            description = "Демонстрационный метод")
    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        apartmentService.delete(id);
    }

    @Operation(summary = "Создать новую запись о квартире",
            description = "Создать новую запись о квартире - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ApartmentDTO> handleApartmentRequest(
            @Parameter(description = "JSON с данными о квартире", required = true)
            @RequestBody ApartmentDataWithoutID requestBody, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        String city = requestBody.getCity();
        String address = requestBody.getAddress();
        Integer price = requestBody.getPrice();

        return ResponseEntity.ok(apartmentService.add(city, address, price));
    }

    @Operation(summary = "Поиск квартиры по городу",
            description = "Поиск квартиры по городу - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    public ResponseEntity<ApartmentDTO> findByCity(@PathVariable("city")
            @Parameter(description = "Город квартиры") String city) {
        return ResponseEntity.ok(apartmentService.findByCity(city));
    }
}
