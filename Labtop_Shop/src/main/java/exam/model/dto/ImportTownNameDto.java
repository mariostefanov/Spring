package exam.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownNameDto {

    @XmlElement(name = "name")
    private String name;

    public ImportTownNameDto() {
    }

    public String getName() {
        return name;
    }
}
