package exam.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class importJsonTownNameDto {

    @Size(min = 2)
    @NotEmpty
    private String name;

    public importJsonTownNameDto() {
    }

    public String getName() {
        return name;
    }
}
