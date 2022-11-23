package sample.kullanici_bilgilendirme;

import javafx.scene.control.Alert;

public class AlertOlustur {
    public static Alert AlertYarat(String title, String headerText, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        return alert;
    }
}
