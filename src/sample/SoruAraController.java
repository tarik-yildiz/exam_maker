package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import sample.kullanici_bilgilendirme.AlertOlustur;
import sample.soru_siniflari.CoktanSecmeliSoru;
import sample.soru_siniflari.Soru;

import java.net.URL;
import java.util.ResourceBundle;

public class SoruAraController implements Initializable {
    int secilenAramaSekli;
    @FXML
    private JFXListView<Soru> filtrelenmisListView;

    @FXML
    private JFXButton silBtn;

    @FXML
    private JFXRadioButton soruMetniRadio;

    @FXML
    private JFXRadioButton soruSiklariRadio;

    @FXML
    private JFXRadioButton dogruSiklarRadio;

    @FXML
    private JFXRadioButton puanRadio;

    @FXML
    private JFXRadioButton zorlukDerecesiRadio;

    @FXML
    private TextField aranacakMetinField;

    @FXML
    private JFXButton listeleBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aranacakMetinField.setDisable(true);
        silBtn.setDisable(true);
        listeleBtn.setDisable(true);
        filtrelenmisListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Soru>() {
            @Override
            public void changed(ObservableValue<? extends Soru> observableValue, Soru soru, Soru t1) {
                silBtn.setDisable(false);
            }
        });
    }

    @FXML
    void listeleButtonEvent(ActionEvent event) {
        filtrelenmisListView.getItems().clear();
        String metin = aranacakMetinField.getText().trim();
        switch (secilenAramaSekli) {
            case Constants.SORUMETNIRADIO:
                for (Soru temp : DataManager.getInstance().getSoruBankasi()) {
                    if (temp.getSoruMetni().toLowerCase().contains(metin.toLowerCase())) {
                        filtrelenmisListView.getItems().add(temp);
                    }
                }
                break;
            case Constants.SORUSIKLARIRADIO:
                for (Soru temp : DataManager.getInstance().getCoktanSecmeliSorular()) {
                    for (String secenek : ((CoktanSecmeliSoru) (temp)).getSecenekler()) {
                        if (secenek.toLowerCase().contains(metin.toLowerCase())) {
                            filtrelenmisListView.getItems().add(temp);
                        }
                    }
                }
                break;
            case Constants.DOGRUSIKLARIRADIO:
                for (Soru temp : DataManager.getInstance().getCoktanSecmeliSorular()) {
                    if(((CoktanSecmeliSoru)(temp)).secenekBelirle().equalsIgnoreCase(metin)){
                        filtrelenmisListView.getItems().add(temp);

                    }
                }
                break;
            case Constants.PUANRADIO:
                for (Soru temp : DataManager.getInstance().getSoruBankasi()) {
                    try{
                        int puan;
                        puan=Integer.parseInt(metin);
                        if (temp.getPuan()==puan) {
                            filtrelenmisListView.getItems().add(temp);
                        }
                    }catch (Exception e){

                    }

                }
                break;
            case Constants.ZORLUKRADIO:
                for (Soru temp: DataManager.getInstance().getSoruBankasi()){
                    if (temp.zorlukBelirle().equalsIgnoreCase(metin)){
                        filtrelenmisListView.getItems().add(temp);
                    }
                }
                break;
        }

    }

    @FXML
    void silButtonEvent(ActionEvent event) {
        Soru soru = filtrelenmisListView.getSelectionModel().getSelectedItem();
        DataManager.getInstance().soruSil(soru);
        filtrelenmisListView.getItems().remove(soru);
    }


    @FXML
    void fieldIcerikKontrol(KeyEvent event) {
        if (aranacakMetinField.getText().trim().equals("")) {
            listeleBtn.setDisable(true);
        } else {
            listeleBtn.setDisable(false);
        }
    }

    @FXML
    void radioController(ActionEvent event) {
        String chckId = ((JFXRadioButton) event.getSource()).getId();
        if (chckId.equals(soruMetniRadio.getId()) && soruMetniRadio.isSelected()) {
            soruSiklariRadio.setSelected(false);
            dogruSiklarRadio.setSelected(false);
            puanRadio.setSelected(false);
            zorlukDerecesiRadio.setSelected(false);
            listeleBtn.setDisable(false);
            aranacakMetinField.setDisable(false);
            secilenAramaSekli = Constants.SORUMETNIRADIO;
        } else if (chckId.equals(soruSiklariRadio.getId()) && soruSiklariRadio.isSelected()) {
            soruMetniRadio.setSelected(false);
            dogruSiklarRadio.setSelected(false);
            puanRadio.setSelected(false);
            zorlukDerecesiRadio.setSelected(false);
            aranacakMetinField.setDisable(false);
            listeleBtn.setDisable(false);
            secilenAramaSekli = Constants.SORUSIKLARIRADIO;
        } else if (chckId.equals(dogruSiklarRadio.getId()) && dogruSiklarRadio.isSelected()) {
            soruMetniRadio.setSelected(false);
            soruSiklariRadio.setSelected(false);
            puanRadio.setSelected(false);
            zorlukDerecesiRadio.setSelected(false);
            aranacakMetinField.setDisable(false);
            listeleBtn.setDisable(false);
            secilenAramaSekli = Constants.DOGRUSIKLARIRADIO;
        } else if (chckId.equals(puanRadio.getId()) && puanRadio.isSelected()) {
            soruMetniRadio.setSelected(false);
            dogruSiklarRadio.setSelected(false);
            soruSiklariRadio.setSelected(false);
            zorlukDerecesiRadio.setSelected(false);
            aranacakMetinField.setDisable(false);
            listeleBtn.setDisable(false);
            secilenAramaSekli = Constants.PUANRADIO;
        } else if (chckId.equals(zorlukDerecesiRadio.getId()) && zorlukDerecesiRadio.isSelected()) {
            soruMetniRadio.setSelected(false);
            dogruSiklarRadio.setSelected(false);
            soruSiklariRadio.setSelected(false);
            puanRadio.setSelected(false);
            aranacakMetinField.setDisable(false);
            listeleBtn.setDisable(false);
            secilenAramaSekli = Constants.ZORLUKRADIO;
        } else {
            aranacakMetinField.setDisable(true);
            listeleBtn.setDisable(true);
        }


    }


}
