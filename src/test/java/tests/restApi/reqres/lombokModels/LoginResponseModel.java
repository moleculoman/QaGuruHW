package tests.restApi.reqres.lombokModels;

import lombok.Data;

@Data
public class LoginResponseModel {
    String token;
    String error;
}
