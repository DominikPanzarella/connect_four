package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.viewContracts.UncontrolledViewFxml;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InfoBarView implements UncontrolledViewFxml
{
    @FXML
    private Text infobarText;
    @FXML
    private ScrollPane scrollPane;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


    public void addToDisplay(String feedback) {
        // Ottieni l'ora corrente e formattala
        String currentTime = LocalTime.now().format(TIME_FORMATTER);

        // Crea la stringa del feedback con l'ora
        String feedbackWithTime = String.format("[%s] %s", currentTime, feedback);

        infobarText.setText(infobarText.getText()+"\n"+feedbackWithTime);
        // Scorri fino alla fine dopo aver aggiornato il contenuto
        scrollPane.layout();
        scrollPane.setVvalue(1.0); // Scorri verticalmente fino alla fine
    }


    @Override
    public void initialize() {
        //empty method
    }

    @Override
    public Parent getParent() {
        return null;
    }
}
