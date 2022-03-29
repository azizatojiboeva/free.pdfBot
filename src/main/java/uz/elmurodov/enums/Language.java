package uz.elmurodov.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
public enum Language {

    UZ("uz", "Uzbek"),
    RU("ru", "Russian"),
    EN("en", "English");

    private final String code;
    private final String name;

    public static Language getByCode(String lang) {
        for (Language language : values()) {
            if (language.getCode().equalsIgnoreCase(lang)) return language;
        }
        return null;
    }
}
