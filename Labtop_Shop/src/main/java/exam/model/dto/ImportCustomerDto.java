package exam.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ImportCustomerDto {

    @Size(min = 2)
    @NotEmpty
    private String firstName;
    @Size(min = 2)
    @NotEmpty
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String registeredOn;
    private importJsonTownNameDto town;

    public ImportCustomerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public importJsonTownNameDto getTown() {
        return town;
    }
}
