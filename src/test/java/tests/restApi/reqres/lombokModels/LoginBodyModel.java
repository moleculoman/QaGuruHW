package tests.restApi.reqres.lombokModels;

import lombok.Data;


@Data
public class LoginBodyModel {
    String email;
    String password;
}
