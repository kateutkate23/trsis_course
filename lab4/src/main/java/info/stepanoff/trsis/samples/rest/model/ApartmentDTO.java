/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Квартира")
public class ApartmentDTO {

    @Schema(description = "Идентификатор")
    private Integer id;
    @Schema(description = "Город")
    private String city;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Цена, руб./мес")
    private Integer price;
}
