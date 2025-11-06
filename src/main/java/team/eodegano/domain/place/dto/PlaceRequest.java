package team.eodegano.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {
    private String name;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Integer order;
}