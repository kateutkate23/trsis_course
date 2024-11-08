package info.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "APARTMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentsPE implements Serializable {

    private static final long serialVersionUID = 1L;

    public ApartmentsPE(String city, String address, Integer price) {
        this.city = city;
        this.address = address;
        this.price = price;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PRICE")
    private Integer price;
}