package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer {
    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	price – accepts a positive number.
•	published on – a date in the "dd/MM/yyyy" format.
•	Constraint: The offers table has a relation with the apartments table.
•	Constraint: The offers table has a relation with the agents table.
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "published_on")
    private LocalDate publishedOn;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Agent agent;

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public Offer setId(long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public Offer setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
        return this;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public Offer setApartment(Apartment apartment) {
        this.apartment = apartment;
        return this;
    }

    public Agent getAgent() {
        return agent;
    }

    public Offer setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }
}
