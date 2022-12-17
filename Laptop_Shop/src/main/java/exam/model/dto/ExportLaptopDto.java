package exam.model.dto;

import java.math.BigDecimal;

public class ExportLaptopDto {

    private String macAddress;

    private double cpuSpeed;

    private int ram;

    private int storage;

    private BigDecimal price;

    private String shopName;

    private String townName;

    public ExportLaptopDto() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public ExportLaptopDto setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public ExportLaptopDto setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    public int getRam() {
        return ram;
    }

    public ExportLaptopDto setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public int getStorage() {
        return storage;
    }

    public ExportLaptopDto setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ExportLaptopDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public ExportLaptopDto setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public ExportLaptopDto setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    @Override
    public String toString() {
        return 	String.format(
                "*Cpu speed - %.2f%n" +
                "**Ram - %d%n" +
                "***Storage - %d%n" +
                "****Price - %.2f%n" +
                "#Shop name - %s%n" +
                "##Town - %s%n",
                getCpuSpeed(),getRam(),getStorage(),getPrice(),getShopName(),getTownName());
    }
}
