package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOffersDTO {

    @XmlElement(name = "offer")
    List<ImportOfferDTO> offers;

    public ImportOffersDTO() {
    }

    public List<ImportOfferDTO> getOffers() {
        return offers;
    }
}
