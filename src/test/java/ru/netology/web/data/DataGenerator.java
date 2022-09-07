package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru-RU"));

    public static @NotNull String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName().replaceAll("ё", "е");
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static String[] cities = new String[]{
            "Абакан", "Волгоград", "Вологда", "Кемерово", "Майкоп",
            "Москва", "Санкт-Петербург", "Самара", "Смоленск", "Тамбов"
    };

    public static String generateCity() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(10);
        return cities[randomIndex];
    }

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static RegistrationInfo getUser() {
        RegistrationInfo user = new RegistrationInfo(generateCity(), generateName(), generatePhone());
        return user;
    }

    @Value
    public static class RegistrationInfo {
        String login;
        String password;
        String status;

    }
}
