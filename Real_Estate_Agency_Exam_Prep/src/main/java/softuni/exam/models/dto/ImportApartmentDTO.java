package softuni.exam.models.dto;

import softuni.exam.models.enums.ApartmentType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentDTO {


    @XmlElement
    @NotNull
    private ApartmentType apartmentType;

    @XmlElement
    @DecimalMin(value = "40.00")
    private double area;

    @XmlElement
    private String town;

    public ImportApartmentDTO() {
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
