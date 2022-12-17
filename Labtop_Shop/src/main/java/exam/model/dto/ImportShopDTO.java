package exam.model.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
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
    @Min(1)
    @Max(50)
    private int employeeCount;

    @XmlElement
    @Min(20000)
    @NotNull
    private BigDecimal income;

    @XmlElement
    @Size(min = 4)
    @NotEmpty
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopAria;

    @XmlElement(name = "town")
    private ImportTownNameDto townNameDto;

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

    public ImportTownNameDto getTownNameDto() {
        return townNameDto;
    }
}
