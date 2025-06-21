package tests.restApi.reqres.lombokModels;

import lombok.Data;
import java.util.List;


@Data
public class OperationsWithManyUsersResponseModel {
    String page;
    String per_page;
    String total;
    String total_pages;
    List<UserResponseModel> data;
    private Support support;

    @Data
    public static class UserResponseModel {
        private String id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Data
    public static class Support {
        private String url;
        private String text;
    }



}