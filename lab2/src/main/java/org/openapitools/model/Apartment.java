package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Apartment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-27T18:19:31.380153700+03:00[Europe/Moscow]", comments = "Generator version: 7.8.0")
public class Apartment {

  private String city;

  private String address;

  private Integer price;

  public Apartment(String city, String address, Integer price) {
    this.city = city;
    this.address = address;
    this.price = price;
  }

  public Apartment city(String city) {
    this.city = city;
    return this;
  }

  /**
   * apartment's city
   * @return city
   */
  
  @Schema(name = "city", description = "apartment's city", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Apartment address(String address) {
    this.address = address;
    return this;
  }

  /**
   * apartment's address
   * @return address
   */
  
  @Schema(name = "address", description = "apartment's address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Apartment price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * rent's price per month
   *
   * @return price
   */
  
  @Schema(name = "price", description = "rent's price per month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Apartment apartment = (Apartment) o;
    return Objects.equals(this.city, apartment.city) &&
        Objects.equals(this.address, apartment.address) &&
        Objects.equals(this.price, apartment.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, address, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Apartment {\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

