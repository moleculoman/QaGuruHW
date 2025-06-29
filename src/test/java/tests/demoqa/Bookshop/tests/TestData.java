package tests.demoqa.Bookshop.tests;

import java.util.Random;

public class TestData {
    public static String LOGIN = "MyTestUser1234";
    public static String PASSWORD = "Test123456!";
    public static String userId,TOKEN,EXPIRES;
    public static String[] bookISBNArray = {
            "9781449325862",
            "9781449331818",
            "9781449337711",
            "9781449365035",
            "9781491904244",
            "9781491950296",
            "9781593275846",
            "9781593277574",
    };

    public static String bookISBN = getRandomValueFromArray(bookISBNArray);
    public static String getRandomValueFromArray(String[] bookISBNArray) {
        if (bookISBNArray == null || bookISBNArray.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым или равен null");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(bookISBNArray.length);
        return bookISBNArray[randomIndex];
    }
}
