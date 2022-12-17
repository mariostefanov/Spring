package exam.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownDTO {
    @XmlElement
    @Size(min = 2)
    @NotEmpty
    private String name;

    @XmlElement
    @Positive
    private long population;

    @XmlElement(name = "travel-guide")
    @Size(min = 10)
    @NotEmpty
    private String travelGuide;

    public String getName() {
        return name;
    }

    public ImportTownDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPopulation() {
        return population;
    }

    public ImportTownDTO setPopulation(long population) {
        this.population = population;
        return this;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public ImportTownDTO setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}
