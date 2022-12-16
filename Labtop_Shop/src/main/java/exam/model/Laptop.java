package exam.model;

import exam.model.enums.WarrantyType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Laptop {

    /*•	id – accepts integer values, a primary identification field, an auto incremented field.
•	mac address – accepts char sequences as values where their character length value higher than 8.
The values are unique in the database.
•	cpu speed – accepts positive floating-point numbers.
•	ram – accepts number values that are more than or equal to 8 and less than or equal to 128
•	storage – accepts number values that are more than or equal to 128 and less than or equal to 1024
•	description – a long and detailed description of all known places with a character length value higher than or equal to 10.
•	price – accepts a positive number.
•	warranty type – the enumeration, one of the following – BASIC, PREMIUM, LIFETIME.
•	Constraint: The laptops table has a relation with the shops table.
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String macAddress;

    @Column(nullable = false,name = "cpu_speed")
    private double cpuSpeed;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int storage;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public Laptop(long id, String macAddress, double cpuSpeed, int ram, int storage, String description, BigDecimal price, WarrantyType warrantyType, Shop shop) {
        this.id = id;
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
        this.shop = shop;
    }

    public long getId() {
        return id;
    }

    public Laptop setId(long id) {
        this.id = id;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Laptop setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public Laptop setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    public int getRam() {
        return ram;
    }

    public Laptop setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public int getStorage() {
        return storage;
    }

    public Laptop setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Laptop setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Laptop setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public Laptop setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public Laptop setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}
