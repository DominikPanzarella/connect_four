package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MakeMoveReceiver;

import javax.sound.midi.MidiMessage;

public class ColumnSelectorController implements MakeMoveReceiver<MakeMoveHandler>
{

    private MakeMoveHandler makeMoveHandler;
    private static ColumnSelectorController myself;

    protected ColumnSelectorController(MakeMoveHandler model)
    {
        this.makeMoveHandler = model;
    }

    public static ColumnSelectorController getInstance(MakeMoveHandler model)
    {
        if(myself == null)
        {
            myself = new ColumnSelectorController(model);
        }
        return myself;
    }

    @Override
    public void makeMove(int column) {
        makeMoveHandler.makeMove(column);
    }


}
