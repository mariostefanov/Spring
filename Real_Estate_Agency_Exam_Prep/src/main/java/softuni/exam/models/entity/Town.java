package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	town name – accepts char sequences as values where their character length value is higher than or equal to 2. The values are unique in the database.
•	population – accepts number values (must be positive), 0 as a value is exclusive.
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, name = "town_name")
    private String townName;

    private int population;

    public Town() {
    }

    public long getId() {
        return id;
    }

    public Town setId(long id) {
        this.id = id;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public Town setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    public int getPopulation() {
        return population;
    }

    public Town setPopulation(int population) {
        this.population = population;
        return this;
    }
}

