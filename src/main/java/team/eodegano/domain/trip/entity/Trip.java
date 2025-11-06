package team.eodegano.domain.trip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import team.eodegano.domain.trip.entity.constant.Category;
import team.eodegano.domain.trip.entity.constant.Region;
import team.eodegano.domain.trip.entity.constant.Ride;

import java.time.LocalDate;

@Entity
@Table(name = "tb_trip")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Region region;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int person;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ride ride;

    public void update(Region region, String area, LocalDate startDate, LocalDate endDate, Integer person, Category category, Ride ride
    ) {
        this.region = region;
        this.area = area;
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.category = category;
        this.ride = ride;
    }
}