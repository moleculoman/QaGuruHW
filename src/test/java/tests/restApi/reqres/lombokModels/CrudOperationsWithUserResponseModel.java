package tests.restApi.reqres.lombokModels;

import lombok.Data;

@Data
public class CrudOperationsWithUserResponseModel {
    String name, job, id, createdAt, updatedAt;
}
