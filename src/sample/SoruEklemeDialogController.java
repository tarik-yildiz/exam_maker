package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import sample.kullanici_bilgilendirme.AlertOlustur;
import sample.soru_siniflari.*;


import javax.xml.crypto.Data;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

public class SoruEklemeDialogController implements Initializable {
    private int secilenSoruTipi;
    private final int INIT = 2131;
    private final int SECILMEDI = 3131;
    @FXML
    private JFXToggleButton toggleKlasik;
    @FXML
    private JFXToggleButton toggleTest;
    @FXML
    private JFXToggleButton toggleDY;
    @FXML
    private JFXToggleButton toggleBosluk;

    @FXML
    private TextField secenekA;
    @FXML
    private TextField secenekB;
    @FXML
    private TextField secenekC;
    @FXML
    private TextField secenekD;

    @FXML
    private JFXComboBox<Integer> puanBox;
    @FXML
    private JFXComboBox<String> zorlukBox;

    @FXML
    private JFXTextArea areaSoru;

    @FXML
    private JFXTextArea areaCevap;

    @FXML
    private JFXRadioButton radioDogru;

    @FXML
    private JFXRadioButton radioYanlis;
    @FXML
    private JFXComboBox<String> comboCevap;

    @FXML
    private JFXButton soruEkleBtn;


    @FXML
    void radioController(ActionEvent event) {
        String chckId = ((JFXRadioButton) event.getSource()).getId();
        if (chckId.equals(radioDogru.getId())) {
            radioYanlis.setSelected(false);
        } else {
            radioDogru.setSelected(false);
        }
    }


    @FXML
    //soru girilmediyse soru ekle butonunu deaktif ediyor.
    void soruGirildiMi(KeyEvent event) {
        if (!areaSoru.getText().trim().equals("")) {
            soruEkleBtn.setDisable(false);
        } else {
            soruEkleBtn.setDisable(true);
        }
    }

    @FXML
    void soruEkleMetod(ActionEvent event) {
        //soru ne olursa olsun puani secilmeli
        //soru ne olursa olsun zorluk derecesi secilmeli
        if (puanBox.getSelectionModel().getSelectedItem() != null
                && zorlukBox.getSelectionModel().getSelectedItem() != null) {
            Soru eklenecekSoru;
            String soruMetni = areaSoru.getText().trim();
            int puani = puanBox.getSelectionModel().getSelectedItem();
            int zorluk;
            switch (zorlukBox.getSelectionModel().getSelectedItem()) {
                case Constants.KOLAY:
                    zorluk = Constants.KOLAYSEVIYE;
                    break;
                case Constants.ORTA:
                    zorluk = Constants.ORTASEVIYE;
                    break;
                case Constants.ZOR:
                    zorluk = Constants.ZORSEVIYE;
                    break;
                default:
                    zorluk = -2134441;
                    break;
            }
            switch (secilenSoruTipi) {
                case Constants.KLASIK:
                    if (!areaCevap.getText().trim().equals("")) {
                        //klasik sorunun eklenecegi yer.
                        eklenecekSoru = new KlasikSoru(soruMetni, puani, zorluk, areaCevap.getText().trim());
                        DataManager.getInstance().soruEkle(eklenecekSoru);
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKLEMEBASARILI, Alert.AlertType.INFORMATION).show();
                        temizle();
                    } else {
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKSIKBIRSEYLER, Alert.AlertType.INFORMATION).show();
                    }
                    break;
                case Constants.TEST:
                    String secenekler[] = new String[4];
                    secenekler[0] = secenekA.getText().trim();
                    secenekler[1] = secenekB.getText().trim();
                    secenekler[2] = secenekC.getText().trim();
                    secenekler[3] = secenekD.getText().trim();
                    if (!secenekler[0].equals("") &&
                            !secenekler[1].equals("") &&
                            !secenekler[2].equals("") &&
                            !secenekler[3].equals("") &&
                            comboCevap.getSelectionModel().getSelectedItem() != null) {
                        int dSecenek = comboCevap.getSelectionModel().getSelectedIndex();
                        //seceneklerde veri varsa ve dogru cevap secildiyse
                        //coktansecmeli soruyu ekleyecegi yer.
                        DataManager.getInstance().soruEkle(new CoktanSecmeliSoru(soruMetni, puani, zorluk, secenekler, dSecenek));
                        temizle();
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKLEMEBASARILI, Alert.AlertType.INFORMATION).show();
                    } else {
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKSIKBIRSEYLER, Alert.AlertType.INFORMATION).show();
                    }
                    break;
                case Constants.DOGRUYANLIS:
                    if (radioDogru.isSelected()) {
                        //dogru yanlis sorusunda cevabi dogru olanin eklenecegi yer
                        eklenecekSoru = new DogruYanlisSoru(soruMetni, puani, zorluk, true);
                        DataManager.getInstance().soruEkle(eklenecekSoru);
                        temizle();
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKLEMEBASARILI, Alert.AlertType.INFORMATION).show();
                    } else if (radioYanlis.isSelected()) {
                        //dogru yanlis sorusunda cevabi yanlis olanin eklenecegi yer
                        eklenecekSoru = new DogruYanlisSoru(soruMetni, puani, zorluk, false);
                        DataManager.getInstance().soruEkle(eklenecekSoru);
                        temizle();
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKLEMEBASARILI, Alert.AlertType.INFORMATION).show();
                    } else {
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKSIKBIRSEYLER, Alert.AlertType.INFORMATION).show();
                    }
                    break;
                case Constants.BOSLUKDOLDURMA:
                    String cevap;
                    cevap = areaCevap.getText().trim();
                    if (!cevap.equals("")) {
                        //cevap girildiyse soru eklenecek.
                        eklenecekSoru = new BoslukDoldurmaSoru(soruMetni, puani, zorluk, cevap);
                        DataManager.getInstance().soruEkle(eklenecekSoru);
                        temizle();
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKLEMEBASARILI, Alert.AlertType.INFORMATION).show();
                    } else {
                        AlertOlustur.AlertYarat(Constants.BILGILENDIRME, Constants.EKSIKBIRSEYLER, Alert.AlertType.INFORMATION).show();
                    }
                    break;
            }
            Collections.sort(DataManager.getInstance().getSoruBankasi());
        } else {
            AlertOlustur.AlertYarat(Constants.BILGILENDIRME,
                    Constants.PUANZORLUKSECILMEDI,
                    Alert.AlertType.INFORMATION).show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soruEkleBtn.setDisable(true);
        etkilesimiKapat(INIT);
        setComboCevap();
        boxlariAyarla();
    }

    public void etkilesimiKapat(int kimCagirdi) {
        temizle();
        //soru tipine gore belirli alanlara etkilesimi kisitliyoruz.
        switch (kimCagirdi) {
            case INIT://baslangicta hicbir soru tipi secili olmadigi icin init metodunda hepsini kapatiyoruz.
                secenekA.setDisable(true);
                secenekB.setDisable(true);
                secenekC.setDisable(true);
                secenekD.setDisable(true);
                areaSoru.setDisable(true);
                areaCevap.setDisable(true);
                radioDogru.setDisable(true);
                radioYanlis.setDisable(true);
                comboCevap.setDisable(true);
                temizle();
                break;
            case Constants.KLASIK:
                //klasik ve bosluk doldurma soru tipinde ayni input yerleri kullaniliyor.
            case Constants.BOSLUKDOLDURMA:
                if (toggleBosluk.isSelected() || toggleKlasik.isSelected()) {
                    areaSoru.setDisable(false);
                    areaCevap.setDisable(false);
                    secenekA.setDisable(true);
                    secenekB.setDisable(true);
                    secenekC.setDisable(true);
                    secenekD.setDisable(true);
                    radioDogru.setDisable(true);
                    radioYanlis.setDisable(true);
                    comboCevap.setDisable(true);
                } else {
                    etkilesimiKapat(INIT);
                }

                break;
            case Constants.TEST:
                if (toggleTest.isSelected()) {
                    areaCevap.setDisable(true);
                    secenekA.setDisable(false);
                    secenekB.setDisable(false);
                    secenekC.setDisable(false);
                    secenekD.setDisable(false);
                    areaSoru.setDisable(false);
                    radioDogru.setDisable(true);
                    radioYanlis.setDisable(true);
                    comboCevap.setDisable(false);
                } else {
                    etkilesimiKapat(INIT);
                }
                break;
            case Constants.DOGRUYANLIS:
                if (toggleDY.isSelected()) {
                    areaCevap.setDisable(true);
                    areaSoru.setDisable(false);
                    secenekA.setDisable(true);
                    secenekB.setDisable(true);
                    secenekC.setDisable(true);
                    secenekD.setDisable(true);
                    radioDogru.setDisable(false);
                    comboCevap.setDisable(true);
                    radioYanlis.setDisable(false);
                } else {
                    etkilesimiKapat(INIT);
                }
                break;
            default:
                break;
        }

    }

    public void temizle() {
        secenekA.setText("");
        secenekB.setText("");
        secenekD.setText("");
        secenekC.setText("");
        areaSoru.setText("");
        areaCevap.setText("");
        radioYanlis.setSelected(false);
        radioDogru.setSelected(false);
    }

    public void soruTuru(ActionEvent event) {
        String secilenId = ((JFXToggleButton) (event).getSource()).getId();
        if (secilenId.equals(toggleTest.getId())) {
            soruTuruIslemleri(Constants.TEST);
        } else if (secilenId.equals(toggleKlasik.getId())) {
            soruTuruIslemleri(Constants.KLASIK);
        } else if (secilenId.equals(toggleBosluk.getId())) {
            soruTuruIslemleri(Constants.BOSLUKDOLDURMA);
        } else if (secilenId.equals(toggleDY.getId())) {
            soruTuruIslemleri(Constants.DOGRUYANLIS);
        } else {
            secilenSoruTipi = SECILMEDI;
        }
    }

    public void soruTuruIslemleri(int tur) {
        etkilesimiKapat(tur);
        toggleController(tur);
        secilenSoruTipi = tur;
    }

    private void toggleController(int toggle) {
        if (toggle == Constants.TEST) {
            toggleKlasik.setSelected(false);
            toggleDY.setSelected(false);
            toggleBosluk.setSelected(false);
        } else if (toggle == Constants.KLASIK) {
            toggleDY.setSelected(false);
            toggleTest.setSelected(false);
            toggleBosluk.setSelected(false);
        } else if (toggle == Constants.DOGRUYANLIS) {
            toggleBosluk.setSelected(false);
            toggleKlasik.setSelected(false);
            toggleTest.setSelected(false);
        } else if (toggle == Constants.BOSLUKDOLDURMA) {
            toggleKlasik.setSelected(false);
            toggleTest.setSelected(false);
            toggleDY.setSelected(false);
        } else {
            System.out.println("hatali metod kullanimi.");
        }
    }


    private void setComboCevap() {
        comboCevap.getItems().addAll("A", "B", "C", "D");
    }

    //baslangicta comboBoxlarimiza degerleri atiyor
    private void boxlariAyarla() {
        for (int i = 1; i < 101; i++) {
            puanBox.getItems().add(i);
        }
        zorlukBox.getItems().addAll(Constants.ZORLUKSEVIYELERI);
    }

}
