package com.stellarburger.user;

import java.util.Random;

abstract public class UserGenerator {
    private static final Random rnd = new Random();
    private static int getRnd() {
        return rnd.nextInt(100000);
    }

    public static User createDefaultUser() {
        return new User("monkey" + getRnd() + "@yandex.ru", "stellar" + getRnd(), "not" + getRnd());
    }

    public static User getWithPasswordOnly() {
        return new User(null, "next" + getRnd(), "Maks" + getRnd());
    }
    public static User getWithEmailOnly() {
        return new User("kri_55" + getRnd() + "@yandex.ru", null, "Maks" + getRnd());
    }
}
