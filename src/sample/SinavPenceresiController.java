package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.sinav_siniflari.KarisikSinav;
import sample.sinav_siniflari.KlasikSinav;
import sample.sinav_siniflari.Sinav;
import sample.sinav_siniflari.TestSinav;
import sample.soru_siniflari.Soru;

import java.net.URL;
import java.util.ResourceBundle;

public class SinavPenceresiController implements Initializable {
    @FXML
    private Label soruLabel;

    @FXML
    private TextField cevapField;

    @FXML
    private JFXComboBox<String> cevapBox;

    @FXML
    private JFXButton sonrakiSoruBtn;
    @FXML
    private JFXButton bosBirakBtn;
    @FXML
    private JFXButton sinaviKaydetBtn;

    private Soru siradakiSoru;
    private int soruNumarasi;
    private int sinavTipi;
    private Sinav sinav;
    private final int SINAVBITTI = 313445;


    @FXML
    void bosBirakButtonEvent(ActionEvent event) {
        siradakiSoru.setSoruCevabi(null);
        soruyuGetir(soruNumarasi++);
    }

    @FXML
    void sonrakiSoruButtonEvent(ActionEvent event) {
        if (soruNumarasi <= sinav.getSorular().size()) {
            switch (siradakiSoru.getSoruTipi()) {
                case Constants.TEST:
                case Constants.DOGRUYANLIS:
                    if (cevapBox.getSelectionModel().getSelectedItem() != null) {
                        siradakiSoru.setKullaniciCevabi(cevapBox.getSelectionModel().getSelectedItem());
                        System.out.println("soru cevaplandi: " + siradakiSoru.getKullaniciCevabi());
                        sonSoruKontroller();
                    } else {
                        System.out.println("henuz cevaplanmadi");
                    }
                    break;
                case Constants.BOSLUKDOLDURMA:
                case Constants.KLASIK:
                    if (!cevapField.getText().trim().equals("")) {
                        siradakiSoru.setKullaniciCevabi(cevapField.getText());
                        System.out.println("soru cevaplandi: " + siradakiSoru.getKullaniciCevabi());
                        sonSoruKontroller();
                    } else {
                        System.out.println("cevaplanmadi");
                    }
                    break;
                default:
                    System.out.println("bir hata olustu");
                    break;
            }
        } else {
            soruTipineGoreAyarla(SINAVBITTI);
        }

    }

    void sonSoruKontroller() {
        if (soruNumarasi == sinav.getSorular().size()) {
            soruTipineGoreAyarla(SINAVBITTI);
        } else {
            soruyuGetir(soruNumarasi++);
        }
    }

    @FXML
    void sinaviKaydetButtonEvent(ActionEvent event) {
        //sinav sinifinin kaydetme metodu cagirilacak.
        sinav.sinavSorulariniDosyayaYazdir();
        if (sinavTipi == Constants.TESTSINAVI) {
            TestSinav sinavim = (TestSinav) sinav;
            soruLabel.setText("Sınav Kaydedildi.\n\t Aldığınız puan: " + sinavim.sinavdanAlinanNotuHesapla());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sorulariCek();
        setCevapBoxForTrueFalse();
        soruNumarasi = 0;
        soruyuGetir(soruNumarasi++);
        sinaviKaydetBtn.setDisable(true);
    }

    public void setCevapBoxForTest() {
        cevapBox.getItems().clear();
        cevapBox.getItems().add("A");
        cevapBox.getItems().add("B");
        cevapBox.getItems().add("C");
        cevapBox.getItems().add("D");
    }

    public void setCevapBoxForTrueFalse() {
        cevapBox.getItems().clear();
        cevapBox.getItems().add(Constants.DOGRU);
        cevapBox.getItems().add(Constants.YANLIS);
    }

    public void sorulariCek() {

        sinavTipi = DataManager.getInstance().getSinavTipi();
        switch (sinavTipi) {
            case Constants.KARISIKSINAV:
                sinav = new KarisikSinav(DataManager.getInstance().getSinavSorulari());
                break;
            case Constants.KLASIKSINAV:
                sinav = new KlasikSinav(DataManager.getInstance().getSinavSorulari());
                break;
            case Constants.TESTSINAVI:
                sinav = new TestSinav(DataManager.getInstance().getSinavSorulari());
                break;
        }
    }

    public void soruyuGetir(int soruNumarasi) {
        if (soruNumarasi < sinav.getSorular().size()) {
            siradakiSoru = sinav.getSorular().get(soruNumarasi);
            soruLabel.setText(siradakiSoru.toString());
            soruTipineGoreAyarla(siradakiSoru.getSoruTipi());
        } else {
            soruTipineGoreAyarla(SINAVBITTI);
        }

    }

    public void soruTipineGoreAyarla(int soruTipi) {
        switch (soruTipi) {
            case Constants.DOGRUYANLIS:
                cevapField.clear();
                cevapBox.setDisable(false);
                setCevapBoxForTrueFalse();
                cevapField.setDisable(true);
                break;
            case Constants.TEST:
                cevapField.clear();
                setCevapBoxForTest();
                cevapBox.setDisable(false);
                cevapField.setDisable(true);
                break;
            case Constants.KLASIK:
            case Constants.BOSLUKDOLDURMA:
                cevapField.clear();
                cevapBox.setDisable(true);
                cevapField.setDisable(false);
                break;
            default:
                sinaviKaydetBtn.setDisable(false);
                cevapBox.setDisable(true);
                cevapField.setDisable(true);
                sonrakiSoruBtn.setDisable(true);
                bosBirakBtn.setDisable(true);
                soruLabel.setText("Sınav Tamamlandi.");
                break;
        }
    }
}
