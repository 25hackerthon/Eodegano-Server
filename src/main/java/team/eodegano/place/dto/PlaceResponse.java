package team.eodegano.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {
    private Long placeId;
    private Long tripId;
    private String name;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Integer order;
}