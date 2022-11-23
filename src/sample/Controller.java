package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.kullanici_bilgilendirme.AlertOlustur;
import sample.soru_siniflari.Soru;
import java.io.IOException;

public class Controller {

    private static int secilenSinavTuru;
    private ContextMenu soruSilmeMenusu;
    @FXML
    private JFXListView<Soru> soruBankListView;
    @FXML
    private JFXListView<Soru> sinavListView;
    @FXML
    private Label sinavTuruLabel;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label sinavSoruSayisi;
    @FXML
    private JFXButton sinavaBaslaBtn;

    @FXML
    private JFXToggleButton testTg;
    @FXML
    private JFXToggleButton karisikTg;
    @FXML
    private JFXToggleButton klasikTg;
    @FXML
    private JFXButton sinavOlusturBtn;
    @FXML
    private Label sinavPuani;
    @FXML
    private JFXButton sinavSilBtn;

    @FXML
    public void initialize() {
        setSoruSilmeMenusu();
        baslangicta();
        setSoruBankListView();
        labelGuncelle();
        sorulariZorluklarinaGoreRenklendir(soruBankListView);
        sorulariZorluklarinaGoreRenklendir(sinavListView);
    }

    @FXML
    void sinavOlusturButtonEvent(ActionEvent event) {
        if (!DataManager.getInstance().sinavOlustuMu) {
            sinavSilBtn.setDisable(false);
            if (secilenSinavTuru == Constants.TESTSINAVI) {
                sinavTuruLabel.setText("Sınav Türü: TEST");
                testTg.setSelected(false);
            } else if (secilenSinavTuru == Constants.KLASIKSINAV) {
                sinavTuruLabel.setText("Sınav Türü: KLASIK");
                klasikTg.setSelected(false);
            } else if (secilenSinavTuru == Constants.KARISIKSINAV) {
                sinavTuruLabel.setText("Sınav Türü: KARISIK");
                karisikTg.setSelected(false);
            }
            sinavOlusturBtn.setDisable(true);
            sinavaBaslaBtn.setDisable(false);
            DataManager.getInstance().sinavYap(sinavListView, secilenSinavTuru);
            toggleDisable(true);
            labelGuncelle();
        } else {
            AlertOlustur.AlertYarat("Uyarı",
                    "Lütfen öncelikle var olan sınavı silin!",
                    Alert.AlertType.INFORMATION).show();
        }
    }

    @FXML
    void sinaviSilButtonEvent(ActionEvent event) {
        sinavSilBtn.setDisable(true);
        sinavaBaslaBtn.setDisable(true);
        DataManager.getInstance().sinaviYokEt(sinavListView);
        toggleDisable(false);
        labelGuncelle();
    }

    @FXML
    void toggleController(ActionEvent event) {
        //toggle butonların acılıp kapanmasıyla calısacak metod icinde yaptigim islem
        // gelen eventi bir togglebtn olarak gorup o butonun idsini secilenId isimli degiskene atamak
        //boylelikle hangisinin secili oldugunu gorup ona gore sınav olusturacagiz
        String secilenId = ((JFXToggleButton) (event).getSource()).getId();
        if (secilenId.equals(testTg.getId())) {
            karisikTg.setSelected(false);
            klasikTg.setSelected(false);
            sinavOlusturBtn.setDisable(false);
            secilenSinavTuru = Constants.TESTSINAVI;
        } else if (secilenId.equals(karisikTg.getId())) {
            testTg.setSelected(false);
            klasikTg.setSelected(false);
            sinavOlusturBtn.setDisable(false);
            secilenSinavTuru = Constants.KARISIKSINAV;
        } else if (secilenId.equals(klasikTg.getId())) {
            karisikTg.setSelected(false);
            testTg.setSelected(false);
            sinavOlusturBtn.setDisable(false);
            secilenSinavTuru = Constants.KLASIKSINAV;
        }
        if (!karisikTg.isSelected() && !testTg.isSelected() && !klasikTg.isSelected()) {
            //bu blokta butun seceneklerin kapali oldugu zaman sinav olusturma butonunu deaktif ediyor.
            sinavOlusturBtn.setDisable(true);
            //secilenSinavTuru = SECILMEDI;
        }
        // System.out.println(!karisikTg.isSelected() && !testTg.isSelected() && !klasikTg.isSelected());
    }

    @FXML
    void soruEkle(ActionEvent event) throws IOException {
        FXMLLoader loader = dialogAc("SORU EKLE", "pencereler/soru_ekle.fxml");
    }

    @FXML
    void soruAraMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = dialogAc("SORU ARA", "pencereler/soru_ara.fxml");
    }

    @FXML
    void sinavaBaslaButtonEvent(ActionEvent event) throws IOException {
        DataManager.getInstance().setSinavTipi(secilenSinavTuru);
        FXMLLoader sayfa = dialogAc("SINAV", "pencereler/sinav_penceresi.fxml");

    }

    private void setSoruSilmeMenusu() {
        soruSilmeMenusu = new ContextMenu();
        MenuItem soruSil = new MenuItem("Soruyu sil");
        soruSil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Soru silinecekSoru = soruBankListView.getSelectionModel().getSelectedItem();
                DataManager.getInstance().soruSil(silinecekSoru);
                labelGuncelle();
            }
        });
        soruSilmeMenusu.getItems().add(soruSil);
    }

    private void labelGuncelle() {
        if (!DataManager.getInstance().sinavOlustuMu) {
            //sinav olusmadiysa bu labeller belirli degildir.
            sinavPuani.setText("Toplam Sınav Puanı: 0");
            sinavTuruLabel.setText("Sınav Türü: ");
            sinavSoruSayisi.setText("Toplam Soru Sayısı: 0");
        } else {
            sinavPuani.setText("Toplam Sınav Puanı: " + DataManager.getInstance().toplamKacPuan(DataManager.getInstance().getSinavSorulari()));
            sinavSoruSayisi.setText("Toplam Soru Sayısı: "+DataManager.getInstance().getSinavSorulari().size());
        }
    }

    FXMLLoader dialogAc(String title, String konum) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(konum));
        dialog.setTitle(title);
        dialog.getDialogPane().setContent(loader.load());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.show();
        return loader;
    }

    private void sorulariZorluklarinaGoreRenklendir(JFXListView<Soru> listView) {
        listView.setCellFactory(new Callback<ListView<Soru>, ListCell<Soru>>() {
            @Override
            public ListCell<Soru> call(ListView<Soru> soruListView) {
                ListCell<Soru> yeniListe = new ListCell<>() {
                    @Override
                    protected void updateItem(Soru soru, boolean b) {
                        super.updateItem(soru, b);
                        if (b || soru == null) {
                            setText(null);
                        } else {
                            setText(soru.toString());
                            if (soru.getZorlukDerecesi() == Constants.KOLAYSEVIYE) {
                                setTextFill(Color.BLACK);
                            } else if (soru.getZorlukDerecesi() == Constants.ORTASEVIYE) {
                                setTextFill(Color.BLUE);
                            } else if (soru.getZorlukDerecesi() == Constants.ZORSEVIYE) {
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
                yeniListe.setContextMenu(soruSilmeMenusu);
                return yeniListe;
            }
        });

    }

    private void baslangicta() {
        sinavSilBtn.setDisable(true);
        sinavaBaslaBtn.setDisable(true);
        sinavOlusturBtn.setDisable(true);
    }

    private void setSoruBankListView() {
        DataManager.getInstance().baslangictaSorulariEkle(soruBankListView);
        soruBankListView.getSelectionModel().selectFirst();
    }
    public void toggleDisable(boolean deger){
        testTg.setDisable(deger);
        karisikTg.setDisable(deger);
        klasikTg.setDisable(deger);
    }
}
