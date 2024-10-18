package info.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Квартира")
public class ApartmentsDTO {

    @Schema(description = "Идентификатор квартиры")
    private Integer ID;
    @Schema(description = "Город")
    private String city;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Цена")
    private Integer price;
}
