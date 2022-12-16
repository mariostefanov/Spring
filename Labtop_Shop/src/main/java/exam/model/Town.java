package exam.model;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private long population;

    @Column(name = "travel_guide", columnDefinition = "TEXT",nullable = false)
    private String travelGuide;


    public Town() {
    }

    public Town(long id, String name, long population, String travelGuide) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }


    public long getId() {
        return id;
    }

    public Town setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }

    public long getPopulation() {
        return population;
    }

    public Town setPopulation(long population) {
        this.population = population;
        return this;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public Town setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}

