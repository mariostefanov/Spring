package exam.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {
    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	first name – accepts char sequences as values where their character length value higher than or equal to 2.
•	last name – accepts char sequences as values where their character length value higher than or equal to 2.
•	email  – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
•	registered on – a date when а customer registers in the shop.
•	Constraint: The customers table has а relation with the towns table.
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false, name = "registered_on")
    private LocalDate registeredOn;

    @ManyToOne
    private Town town;

    public Customer() {
    }

    public Customer(long id, String firstName, String lastName, String email, LocalDate registeredOn, Town town) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredOn = registeredOn;
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public Customer setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public Customer setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Customer setTown(Town town) {
        this.town = town;
        return this;
    }
}
