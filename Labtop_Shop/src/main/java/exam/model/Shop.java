package exam.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop {
    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	name – accepts char sequences as values where their character length value higher than or equal to 4. The values are unique in the database.
•	income – accepts number values that are more than or equal to 20000.
•	address – accepts char sequences as values where their character length value higher than or equal to 4.
•	employee count – accepts number values that are between 1 and 50
o	(Larger than or equal to 1 and less than or equal to 50).
•	shop area – accepts number values that are more than or equal to 150.
•	Constraint: The shops table has a relation with the towns table.
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal income;

    @Column(nullable = false)
    private String address;

    @Column(name = "employee_count",nullable = false)
    private int employeeCount;

    @Column(name = "shop_area",nullable = false)
    private int shopArea;

    @ManyToOne
    private Town town;


    public Shop() {
    }

    public Shop(long id, String name, BigDecimal income, String address, int employeeCount, int shopArea, Town town) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.address = address;
        this.employeeCount = employeeCount;
        this.shopArea = shopArea;
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public Shop setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public Shop setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public Shop setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public int getShopArea() {
        return shopArea;
    }

    public Shop setShopArea(int shopArea) {
        this.shopArea = shopArea;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Shop setTown(Town town) {
        this.town = town;
        return this;
    }
}
