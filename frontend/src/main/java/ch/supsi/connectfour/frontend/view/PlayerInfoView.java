package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.player.*;
import ch.supsi.connectfour.frontend.command.ExitCommand;
import ch.supsi.connectfour.frontend.command.SaveNewInfoCommand;
import ch.supsi.connectfour.frontend.contracts.handler.ExitHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PlayerInfoHandler;
import ch.supsi.connectfour.frontend.contracts.handler.SaveNewInfoHandler;
import ch.supsi.connectfour.frontend.contracts.observable.SaveNewInfoObservable;
import ch.supsi.connectfour.frontend.contracts.observer.PlayerInfoObserver;
import ch.supsi.connectfour.frontend.contracts.receiver.ExitReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.PlayerInfoReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.SaveNewInfoReceiver;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import ch.supsi.connectfour.frontend.controller.PlayerInfoController;
import ch.supsi.connectfour.frontend.model.PropertiesModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class PlayerInfoView implements ControlledViewFxml, PlayerInfoObserver, SaveNewInfoObservable
{
    @FXML
    private VBox playerVBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> symbolComboBox;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button saveButton;

    private Stage stage;
    private Parent parent;
    private List<Character> allSysmbols = PropertiesModel.getInstance().getSymbols();
    private int position;

    public PlayerInfoView(final int position) throws IOException {
        TranslationsController translationsController = TranslationsController.getInstance();
        ResourceBundle bundle = translationsController.getResourceBundle();
        URL fxmlurl = PlayerInfoView.class.getResource("/view/playerInfoView.fxml");
        if (fxmlurl != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
            fxmlLoader.setController(this);
            parent = fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(parent));
            this.position = position;
        }
    }

    /*
    public static PlayerInfoView getInstance() {
        if (myself == null) {
            try {
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = PlayerInfoView.class.getResource("/view/playerInfoView.fxml");
                if (fxmlurl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    myself = new PlayerInfoView(
                    );
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    stage.setScene(new Scene(parent));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }*/



    @Override
    public void initialize() {
        // Clear any existing items (if necessary)
        symbolComboBox.getItems().clear();

        // Add all symbols to the ComboBox (converted to String)
        for (Character symbol : allSysmbols) {
            symbolComboBox.getItems().add(symbol.toString());
        }

        // Set default selection (optional)
        if (!allSysmbols.isEmpty()) {
            symbolComboBox.getSelectionModel().select(0);  // Select the first symbol by default
        }

    }



    @Override
    public Parent getParent() {
        return parent;
    }

    @Override
    public void playerInfo(final int position, Player player) {
        if(this.position == position){
            nameTextField.setText("");

            nameTextField.setText(player.getName());

            final double red = player.getPlayerColors().getRedChannel();
            final double blue = player.getPlayerColors().getBlueChannel();
            final double green = player.getPlayerColors().getGreenChannel();
            colorPicker.setValue(Color.color(red, green, blue));

            // Set the ComboBox symbol to the player's current symbol
            String playerSymbol = player.getPlayerCharacter().toString();  // Assuming player has a method to get the symbol as a character
            symbolComboBox.setValue(playerSymbol);

            stage.show();
        }

    }

    public <T extends SaveNewInfoCommand<? extends SaveNewInfoReceiver<SaveNewInfoHandler>>> void createSaveNewInfoBehavior(T command){
        saveButton.setOnAction(action->{
            String newName = nameTextField.getText();

            String symbol = symbolComboBox.getValue();
            Color color = colorPicker.getValue();
            MyColorInterface newColor = new MyColor(color.getRed(), color.getGreen(), color.getBlue());

            MySymbolInterface newSymbol = new MySymbol(symbol.charAt(0),newColor);

            command.setNewName(newName);
            command.setNewSymbol(newSymbol);
            command.execute();

        });
    }


}
