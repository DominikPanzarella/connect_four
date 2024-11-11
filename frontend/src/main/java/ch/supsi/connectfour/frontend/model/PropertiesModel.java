package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.frontend.contracts.handler.PropertiesHandler;
import ch.supsi.connectfour.frontend.contracts.observable.Observable;

import java.io.IOException;
import java.util.*;

public class PropertiesModel implements Observable, PropertiesHandler
{
    private static PropertiesModel myself;
    private final Properties properties;
    private List<Character> symbols;

    private String version;
    private String dateTimeBuild;

    private List<String> infos = new LinkedList<>();
    private Map<String, String> developers = new HashMap<>();
    private Map<String,String> shortcuts = new HashMap<>();

    protected PropertiesModel(){
        properties = new Properties();
        try{
            properties.load(PropertiesModel.class.getClassLoader().getResourceAsStream("project.properties"));

            version = properties.getProperty("version");
            dateTimeBuild = properties.getProperty("build.timestamp");
            symbols = properties.getProperty("symbols").chars().mapToObj(c -> (char) c).toList();

            String[] devs = properties.getProperty("devs").split(" - ");
            for(String dev : devs){
                final String name = dev.split(",")[0];
                final String email = dev.split(",")[1];
                developers.putIfAbsent(email, name);
            }

            String[] sh = properties.getProperty("shortcuts").split(" - ");
            for(String element : sh){
                final String name = element.split(",")[0];
                final String command = element.split(",")[1];
                shortcuts.putIfAbsent(name, command);
            }


            infos.add(version);
            infos.add(dateTimeBuild);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PropertiesModel getInstance(){
        if(myself == null)
            myself = new PropertiesModel();
        return myself;
    }


    public String getVersion(){
        return version;
    }

    public String getDateTimeBuild(){
        return dateTimeBuild;
    }


    @Override
    public List<String> loadInfos() {
        return infos;
    }

    @Override
    public Map<String, String> loadDevelopers() {
        return developers;
    }

    @Override
    public List<Character> getSymbols() {
        return symbols;
    }

    @Override
    public Map<String, String> loadShortcuts() {
        return shortcuts;
    }
}
