package ch.supsi.connectfour.frontend.contracts.handler;

import java.util.List;
import java.util.Map;

public interface PropertiesHandler extends Handler
{
    List<String> loadInfos();
    Map<String,String> loadDevelopers();
    List<Character> getSymbols();
    Map<String,String> loadShortcuts();

}
