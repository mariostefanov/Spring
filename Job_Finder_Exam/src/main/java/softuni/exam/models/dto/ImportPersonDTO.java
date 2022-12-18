package softuni.exam.models.dto;

import softuni.exam.models.entity.StatusType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ImportPersonDTO {
    /*"email": "lrann0@t-online.de",
    "firstName": "Lorna",
    "lastName": "Rann",
    "phone": "462-463-0432",
    "statusType": "freelancer",
    "country": "21"*/

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 2,max = 30)
    private String firstName;

    @NotEmpty
    @Size(min = 2,max = 30)
    private String lastName;

    @NotEmpty
    @Size(min = 2,max = 13)
    private String phone;

    @NotNull
    private StatusType statusType;

    private long country;

    public ImportPersonDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public long getCountry() {
        return country;
    }
}
