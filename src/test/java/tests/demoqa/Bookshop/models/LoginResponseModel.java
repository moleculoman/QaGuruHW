package tests.demoqa.Bookshop.models;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class LoginResponseModel {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    @JsonProperty("created_date")
    String createdDate;
    String isActive;
}
