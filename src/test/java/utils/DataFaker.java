package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataFaker {
    public Faker faker = new Faker(new Locale("ru"));

    public String getRandomFirstName(){
        return faker.name().firstName();
    }
    public String getRandomLastName(){
        return faker.name().lastName();
    }
    public String getRandomEmail(){
        return faker.internet().emailAddress("en");
    }
    public String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getRandomPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getRandomBirthDay() {
        return String.valueOf(faker.number().numberBetween(1,31));
    }
    public String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1980,2025));
    }

    public String getRandomSubject() {
        return faker.options().option(
                 "Maths", "English", "Physics", "Computer Science", "Civics");
    }
    public String getRandomHobbies() {
        return faker.options().option(
                 "Sports", "Reading", "Music");
    }
    public String getRandomAddress() {
        return faker.address().streetAddress();
    }

    public String getRandomState() {
        return faker.options().option(
                "NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomCity(String fakerState) {
        return switch (fakerState) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException(
                    "Unknown state: " + fakerState);
        };
    }
}
