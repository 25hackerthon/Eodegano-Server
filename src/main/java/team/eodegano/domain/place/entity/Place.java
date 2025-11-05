package team.eodegano.domain.place.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_place")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tripId;

    @Column(nullable = false)
    private String name;

    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Integer orderIndex;

    public void update(String name, String category, String address,
                       Double latitude, Double longitude, String description, Integer orderIndex) {
        if (name != null) this.name = name;
        if (category != null) this.category = category;
        if (address != null) this.address = address;
        if (latitude != null) this.latitude = latitude;
        if (longitude != null) this.longitude = longitude;
        if (description != null) this.description = description;
        if (orderIndex != null) this.orderIndex = orderIndex;
    }
}