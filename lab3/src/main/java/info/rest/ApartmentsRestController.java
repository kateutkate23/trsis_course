package info.rest;

import info.rest.model.ApartmentsDTO;
import info.service.ApartmentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/apartments")
@RequiredArgsConstructor
public class ApartmentsRestController {

    private final ApartmentsService apartmentsService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Получить перечень квартир",
            description = "Получить перечень квартир - расширенное описание",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    public ResponseEntity<List<ApartmentsDTO>> browse() {
        return ResponseEntity.ok(apartmentsService.listAll());
    }

    @Operation(summary = "Удаление квартиры",
            description = "Удаление квартиры по ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("ID")
                       @Parameter(description = "Идентификатор квартиры") Integer ID) {
        apartmentsService.delete(ID);
    }

    @Operation(summary = "Создать новую квартиру",
            description = "Создать новую квартиру - расширенное описание",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{city}/{address}/{price}", method = RequestMethod.POST)
    public ResponseEntity<ApartmentsDTO> add(
            @PathVariable("city")
            @Parameter(description = "Город") String city,
            @PathVariable("address")
            @Parameter(description = "Адрес") String address,
            @PathVariable("price")
            @Parameter(description = "Цена, руб/мес") Integer price) {
        return ResponseEntity.ok(apartmentsService.add(city, address, price));
    }

    @Operation(summary = "Поиск квартиры по ID",
            description = "Поиск квартиры по ID - расширенное описание",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<ApartmentsDTO> findByNumber(@PathVariable("ID")
                                                @Parameter(description = "Идентификатор квартиры") Integer ID) {
        return ResponseEntity.ok(apartmentsService.findByID(ID));
    }
}