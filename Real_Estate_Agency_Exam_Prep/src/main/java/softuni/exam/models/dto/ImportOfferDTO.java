package softuni.exam.models.dto;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @Positive
    @NotNull
    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "agent")
    private ImportAgentNameDTO agentName;
    @XmlElement(name = "apartment")
    private ImportApartmentIdDTO apartmentId;

    @XmlElement
    @NotNull
    private String publishedOn;

    public ImportOfferDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ImportAgentNameDTO getAgentName() {
        return agentName;
    }

    public ImportApartmentIdDTO getApartmentId() {
        return apartmentId;
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
