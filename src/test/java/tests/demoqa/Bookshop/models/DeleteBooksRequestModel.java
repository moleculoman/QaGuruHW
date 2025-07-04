package tests.demoqa.Bookshop.models;

import lombok.Data;

@Data
public class DeleteBooksRequestModel {
    String userId, isbn;
}
