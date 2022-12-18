package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportAgentNameDTO {
    @Size(min = 2)
    @XmlElement
    private String name;

    public ImportAgentNameDTO() {
    }

    public String getName() {
        return name;
    }
}
