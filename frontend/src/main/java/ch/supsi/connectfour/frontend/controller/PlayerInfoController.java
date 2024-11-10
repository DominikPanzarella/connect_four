package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PlayerInfoHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PropertiesHandler;
import ch.supsi.connectfour.frontend.contracts.handler.SaveNewInfoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.PlayerInfoReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.SaveNewInfoReceiver;

public class PlayerInfoController implements PlayerInfoReceiver<PlayerInfoHandler>, SaveNewInfoReceiver<SaveNewInfoHandler>
{
    private PlayerInfoHandler model;
    private SaveNewInfoHandler saveNewInfoModel;
    private int position;
    private static PlayerInfoController myself;

    public PlayerInfoController(int position,PlayerInfoHandler model,SaveNewInfoHandler saveNewInfoModel){
        this.model = model;
        this.saveNewInfoModel = saveNewInfoModel;
        this.position = position;
    }


    @Override
    public void playerInfo(final int position) {
        model.playerInfo(position);
    }


    @Override
    public void saveNewInfo(int position,String newName, MySymbolInterface newSymbol) {
        saveNewInfoModel.saveNewInfo(position,newName,newSymbol);
    }

    @Override
    public int getPosition() {
        return position;
    }
}
