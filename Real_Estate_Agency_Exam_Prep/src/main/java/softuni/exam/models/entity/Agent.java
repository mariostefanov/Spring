package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "ägents")
public class Agent {
    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	first name – accepts char sequences as values where their character length value higher than or equal to 2. The values are unique in the database.
•	last name – accepts char sequences as values where their character length value higher than or equal to 2.
•	email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
•	Constraint: The agents table has а relation with the towns table.
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true,name = "first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @ManyToOne
    private Town town;

    public Agent() {
    }

    public long getId() {
        return id;
    }

    public Agent setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Agent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Agent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Agent setEmail(String email) {
        this.email = email;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Agent setTown(Town town) {
        this.town = town;
        return this;
    }
}
