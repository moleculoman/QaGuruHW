package tests.restApi.reqres.pojoModels;

public class LoginResponsePojoModel {
    String token;
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
