package  team.eodegano.place.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tripId;
    private String name;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Integer orderIndex;
}
