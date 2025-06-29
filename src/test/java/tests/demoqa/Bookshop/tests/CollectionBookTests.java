package tests.demoqa.Bookshop.tests;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.demoqa.Bookshop.models.AddBooksRequestModel;
import tests.demoqa.Bookshop.models.DeleteBooksRequestModel;
import tests.demoqa.Bookshop.models.IsbnModel;
import tests.demoqa.Bookshop.pages.ProfilePage;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static tests.demoqa.Bookshop.api.BooksApi.*;
import static tests.demoqa.Bookshop.tests.TestData.userId;

@Tag("Bookshop_delete_test")
@DisplayName("Тесты на удаление книг")
public class CollectionBookTests extends TestBase {

    @DisplayName("Успешное удаление книги из корзины")
    @WithLogin
    @Test
    public void successfullBookRemovalTest() {
        ProfilePage profilePage = new ProfilePage();

        IsbnModel isbn = new IsbnModel();
        isbn.setIsbn(bookISBN);
        List<IsbnModel> isbns = List.of(isbn);

        AddBooksRequestModel addBookData = new AddBooksRequestModel();
        addBookData.setUserId(userId);
        addBookData.setCollectionOfIsbns(isbns);

        DeleteBooksRequestModel deleteBookData = new DeleteBooksRequestModel();
        deleteBookData.setUserId(userId);
        deleteBookData.setIsbn(bookISBN);

        step("Очистка книг из корзины", () ->
                deleteAllBooks());

        step("Добавление книги в корзину", () ->
                addBook(addBookData));

        step("Удаление добавленной книги из корзины", () ->
                deleteBook(deleteBookData));

        profilePage.openPage()
                .checkEmptyTableWithoutBooks();
    }
}