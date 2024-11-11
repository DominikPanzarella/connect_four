package ch.supsi.connectfour.frontend.model;



import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;
import ch.supsi.connectfour.frontend.contracts.observable.AboutObservable;

import java.util.List;
import java.util.Map;


public class AboutModel implements AboutHandler, AboutObservable {

    private static AboutModel myself;


    protected AboutModel(){
    }

    public static AboutModel getInstance(){
        if(myself==null)
            myself = new AboutModel();

        return myself;

    }


    @Override
    public void about(List<String> infos, Map<String, String> developers) {
        notifyAboutObservers(infos, developers);
    }
}

