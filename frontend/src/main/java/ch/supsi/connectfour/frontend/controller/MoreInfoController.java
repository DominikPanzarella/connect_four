package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.MoreInfoHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PropertiesHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MoreInfoReceiver;

public class MoreInfoController implements MoreInfoReceiver<MoreInfoHandler>
{
    private MoreInfoHandler model;
    private static MoreInfoController myself;
    private PropertiesHandler properties;

    protected MoreInfoController(MoreInfoHandler model, PropertiesHandler properties) {
        this.model = model;
        this.properties = properties;
    }


    public static MoreInfoController getInstance(MoreInfoHandler model, PropertiesHandler properties) {
        if (myself == null) {
            myself = new MoreInfoController(model, properties);
        }
        return myself;
    }

    @Override
    public void moreInfo() {
        model.moreInfo(properties.loadShortcuts());
    }
}
