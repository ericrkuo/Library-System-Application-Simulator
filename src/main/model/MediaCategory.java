package model;

public enum MediaCategory {
    KIDS("kids"),
    COMICS("comics"),
    COOKING("cooking"),
    HEALTH("health & fitness"),
    HISTORY("history"),
    HORROR("horror"),
    FICTION("fiction"),
    FANTASY("sci-Fi & fantasy"),
    ROMANCE("romance"),
    SPORTS("sports"),
    MYSTERY("mystery");


    private final String name;

    MediaCategory(String name) {
        this.name = name;
    }
}
