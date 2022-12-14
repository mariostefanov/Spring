package softuni.exam.models.entity;

import softuni.exam.models.enums.ApartmentType;

import javax.persistence.*;

@Entity
@Table
public class Apartment {

    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	apartment type – the enumeration, one of the following – two_rooms, three_rooms, four_rooms
•	area – accepts number values that are more than or equal to 40.00.
•	Constraint: The apartment table has а relation with the towns table.
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "apartament_type")
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private double area;

    @ManyToOne
    private Town town;

    public Apartment() {
    }

    public long getId() {
        return id;
    }

    public Apartment setId(long id) {
        this.id = id;
        return this;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public Apartment setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
        return this;
    }

    public double getArea() {
        return area;
    }

    public Apartment setArea(double area) {
        this.area = area;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Apartment setTown(Town town) {
        this.town = town;
        return this;
    }
}
