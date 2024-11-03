package ch.supsi.connectfour.backend.service.preferences;

import java.nio.file.Path;
import java.util.Properties;

public interface PreferencesRepositoryInterface {

    Properties getPreferences();

    void setPreferences(String languageTag);

    Path getUserPreferencesDirectoryPath();
}