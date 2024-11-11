package ch.supsi.connectfour.frontend.model;


import ch.supsi.connectfour.backend.controller.PreferencesController;
import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.contracts.handler.ChangeLanguageHandler;
import ch.supsi.connectfour.frontend.contracts.observable.FeedbackObservable;
import ch.supsi.connectfour.frontend.contracts.observable.Observable;
import ch.supsi.connectfour.frontend.presentable.LanguageChangePresentable;

public class LanguageModel implements Observable, ChangeLanguageHandler, FeedbackObservable {

    private String currentLanguageTag;
    private static LanguageModel mySelf;
    private TranslationsController translationsController;
    private PreferencesController preferencesController;

    private LanguageModel(){
        super();
        this.translationsController = TranslationsController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
    }

    public static LanguageModel getInstance(){
        if(mySelf == null){
            mySelf = new LanguageModel();
        }
        return mySelf;
    }

    @Override
    public void changeLanguage(String languageTag) {
        preferencesController.changeLanguage(languageTag);
        this.currentLanguageTag = languageTag;
        this.notifyFeedbackObservers(new LanguageChangePresentable());
    }
}
