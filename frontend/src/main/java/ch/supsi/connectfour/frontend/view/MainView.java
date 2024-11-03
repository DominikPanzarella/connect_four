package ch.supsi.connectfour.frontend.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainView {

    @FXML
    private AnchorPane connectFour;

    @FXML
    private AnchorPane menuBarPane;

    @FXML
    private AnchorPane infoBarPane;

    @FXML
    private AnchorPane columnSelectorPane;

    @FXML
    private AnchorPane playersPane;

    public AnchorPane getInfoBarPane() {
        return infoBarPane;
    }

    public AnchorPane getConnectFour(){
        return connectFour;
    }

    public AnchorPane getMenuBarPane() {
        return menuBarPane;
    }

    public AnchorPane getColumnSelectorPane(){ return columnSelectorPane; }

    public AnchorPane getPlayersPane(){return playersPane; }

    @FXML
    public void initialize() {

    }
}
