package ch.supsi.connectfour.backend.controller;

import java.util.ResourceBundle;

public interface TranslationsServiceInterface {

    boolean isSupportedLanguageTag(String languageTag);

    boolean changeLanguage(String languageTag);

    String translate(String key);
    ResourceBundle getResourceBundle();
}
