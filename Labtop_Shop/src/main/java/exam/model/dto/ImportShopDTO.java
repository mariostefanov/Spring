package exam.model.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopDTO {

    @XmlElement
    @Size(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Size(min = 1,max = 50)
    private int employeeCount;

    @XmlElement
    @Size(min = 20000)
    private BigDecimal income;

    @XmlElement
    @Size(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Size(min = 150)
    private int shopAria;

    @XmlElement
    @Size(min = 2)
    private String townName;

    public ImportShopDTO() {
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopAria() {
        return shopAria;
    }

    public String getTownName() {
        return townName;
    }
}
