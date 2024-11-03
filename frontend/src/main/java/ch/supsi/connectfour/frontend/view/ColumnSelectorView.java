package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import ch.supsi.connectfour.frontend.contracts.viewContracts.UncontrolledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnSelectorView implements ControlledViewFxml
{
    @FXML
    private GridPane columnPane;
    @FXML
    private Button col0;
    @FXML
    private Button col1;
    @FXML
    private Button col2;
    @FXML
    private Button col3;
    @FXML
    private Button col4;
    @FXML
    private Button col5;
    @FXML
    private Button col6;
    private static Parent parent;
    private static ColumnSelectorView myself;

    protected ColumnSelectorView()
    {
        //empty constructor
    }

    public static ColumnSelectorView getInstance(){
        if(myself==null){
            myself = new ColumnSelectorView();
            try{
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = ColumnSelectorView.class.getResource("/view/columnselector.fxml");
                if(fxmlurl != null){
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    @Override
    public void initialize() {
    }

    @Override
    public Parent getParent() {
        return parent;
    }
}
