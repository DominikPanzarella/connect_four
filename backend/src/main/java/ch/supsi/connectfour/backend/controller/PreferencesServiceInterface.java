package ch.supsi.connectfour.backend.controller;

import java.nio.file.Path;

public interface PreferencesServiceInterface {

    String getCurrentLanguage();

    Object getPreference(String key);
    void changeLanguage(String languageTag);
    Path getUserPreferencesDirectoryPath();

}
