/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "APARTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentPE implements Serializable {

    private static final long serialVersionUID = 1L;

    public ApartmentPE(String city, String address, Integer price) {
        this.city = city;
        this.address = address;
        this.price = price;
    }

    @Id
    @Column(name = "APARTMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CITY")
    private String city;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PRICE")
    private Integer price;

}
