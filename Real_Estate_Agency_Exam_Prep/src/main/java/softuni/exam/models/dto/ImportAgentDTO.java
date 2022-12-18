package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ImportAgentDTO {
    /*"firstName": "Kimberlee",
    "lastName": "Goshawk",
    "town": "Oslo",
    "email": "alosty2@slate.com"*/
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Size(min = 2)
    private String town;
    @Email
    private String email;

    public ImportAgentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTownName() {
        return town;
    }

    public String getEmail() {
        return email;
    }
}
