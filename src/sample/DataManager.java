package sample;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import sample.soru_siniflari.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DataManager {
    private ObservableList<Soru> soruBankasi;
    private ObservableList<Soru> sinavSorulari;
    private ObservableList<Soru> coktanSecmeliSorular;
    private ObservableList<Soru> klasikSorular;
    private ObservableList<Soru> boslukDoldurmaSorular;
    private ObservableList<Soru> dogruYanlisSorular;
    private Label klasikLabel, testLabel, dyLabel, boslukLabel, toplamLabel;
    private static DataManager instance = new DataManager();
    public boolean sinavOlustuMu;
    private int sinavTipi;

    //singleton yapacağız

    public static DataManager getInstance() {
        return instance;
    }

    private DataManager() {
        sinavOlustuMu = false;
    }

    public int getSinavTipi() {
        return sinavTipi;
    }

    public void setSinavTipi(int sinavTipi) {
        this.sinavTipi = sinavTipi;
    }

    public ObservableList<Soru> getSoruBankasi() {
        return soruBankasi;
    }

    public void setSoruBankasi(ObservableList<Soru> soruBankasi) {
        this.soruBankasi = soruBankasi;
    }

    public ObservableList<Soru> getSinavSorulari() {
        return sinavSorulari;
    }

    public void setSinavSorulari(ObservableList<Soru> sinavSorulari) {
        this.sinavSorulari = sinavSorulari;
    }

    public ObservableList<Soru> getCoktanSecmeliSorular() {
        return coktanSecmeliSorular;
    }

    public void setCoktanSecmeliSorular(ObservableList<Soru> coktanSecmeliSorular) {
        this.coktanSecmeliSorular = coktanSecmeliSorular;
    }

    public ObservableList<Soru> getKlasikSorular() {
        return klasikSorular;
    }

    public void setKlasikSorular(ObservableList<Soru> klasikSorular) {
        this.klasikSorular = klasikSorular;
    }

    public ObservableList<Soru> getBoslukDoldurmaSorular() {
        return boslukDoldurmaSorular;
    }


    public void setBoslukDoldurmaSorular(ObservableList<Soru> boslukDoldurmaSorular) {
        this.boslukDoldurmaSorular = boslukDoldurmaSorular;
    }

    public ObservableList<Soru> getDogruYanlisSorular() {
        return dogruYanlisSorular;
    }

    public void setDogruYanlisSorular(ObservableList<Soru> dogruYanlisSorular) {
        this.dogruYanlisSorular = dogruYanlisSorular;
    }


    public void soruEkle(Soru s) {
        soruBankasi.add(s);
    }

    public void soruSil(Soru s) {
        if (!sinavOlustuMu) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Silmek istedigine emin misin?");
            alert.setHeaderText("Soru: " + s.getSoruMetni());
            Optional<ButtonType> sonuc = alert.showAndWait();
            if (sonuc.get() == ButtonType.OK) {
                soruBankasi.remove(s);
                sorulariGuncelle();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("UYARI");
            alert.setHeaderText("Sınav oluşturmuşken soru silemezsiniz. ");
            alert.show();
        }
    }

    public void baslangictaSorulariEkle(JFXListView<Soru> liste) {
        soruBankasi = FXCollections.observableArrayList();
        soruBankasi.addAll(randomSoruMaker());
        Collections.sort(soruBankasi);
        liste.setItems(soruBankasi);
        sorulariGuncelle();
    }

    //bu metod ile baslangicta rastgele sorular ekleyeceğiz...
    public Soru[] randomSoruMaker() {
        Soru[] sorularim = new Soru[100];
        for (int i = 0; i < 100; i++) {
            int sayi1 = (int) (Math.random() * (Math.random() * 100)) + 5;
            int sayi2 = (int) (Math.random() * 100) + 5;
            int cevap = sayi1 + sayi2;
            int cevapSirasi = (int) (Math.random() * 4);
            int seviye;
            int puan = (int) (Math.random() * 40) + 5;
            if (puan > 40) {
                seviye = Constants.ZORSEVIYE;
            } else if (puan > 20) {
                seviye = Constants.ORTASEVIYE;
            } else {
                seviye = Constants.KOLAYSEVIYE;
            }
            if (i < 30) {
                //30tane test sorusu ekleyecek
                String secenekler[] = new String[4];
                for (int j = 0; j < 4; j++) {
                    if (j == cevapSirasi) {
                        secenekler[j] = "" + cevap;
                    } else {
                        secenekler[j] = "" + (int) (Math.random() * cevap + (Math.random() * 15) + 5);
                        for (int k = 0; k < 4; k++) {
                            if (secenekler[j].equals(secenekler[k])) {
                                secenekler[j] = "" + (Integer.parseInt(secenekler[j]) + (int) (Math.random() * 20) + 7);
                            }
                        }

                    }
                }
                sorularim[i] = new CoktanSecmeliSoru(sayi1 + "+" + sayi2 + " Kaç eder?", puan, seviye, secenekler, cevapSirasi);
            } else if (i < 50) {//20 tane klasik soru ekleyecegiz.
                sorularim[i] = new KlasikSoru(sayi1 + "+" + sayi2 + " Kaç eder?", puan, seviye, "" + cevap);
            } else if (i < 70) {
                sorularim[i] = new BoslukDoldurmaSoru("'" + sayi1 + "+" + sayi2 + " =... eder' bosluga ne gelmelidir? ", puan, seviye, "" + cevap);
            } else {
                int zar = (int) (Math.random() * 6) + 1;
                if (zar > 3) {
                    sorularim[i] = new DogruYanlisSoru("'" + sayi1 + "+" + sayi2 + " = " + cevap + "' Dogru mu yanlis mi belirleyiniz ? ", puan, seviye, true);
                } else {
                    cevap = cevap + (int) ((Math.random() * cevap) + 10);
                    sorularim[i] = new DogruYanlisSoru("'" + sayi1 + "+" + sayi2 + " = " + cevap + "' Dogru mu yanlis mi belirleyiniz ? ", puan, seviye, false);

                }

            }

        }

        return sorularim;
    }


    public void sinavYap(JFXListView<Soru> sinavListesi, int sinavTipi) {
        int i = 0;
        sinavSorulari = FXCollections.observableArrayList();

        ArrayList<Soru> sorular = new ArrayList<>();
        sorular = soruBankasiniBelirle(sorular, sinavTipi);
        if (toplamKacPuan(sorular) >= 100) {
            while (toplamKacPuan(sinavSorulari) < 100) {
                Soru temp = sorular.get((int) ((Math.random() * (sorular.size()))));
                if (!sinavSorulari.contains(temp) && toplamKacPuan(sinavSorulari) + temp.getPuan() < 110) {
                    sinavSorulari.add(temp);
                }
                if (++i > 30) {
                    //sinav puanimiz bazen 100 olmayabiliyor ve bu sonsuz donguye sokuyor.
                    //eger dongumuz 30dan fazla calistiysa burasi aktif olacak
                    //ve sinav olusturma tekrardan baslayacak.
                    sinavSorulari.clear();
                    sinavListesi.getItems().clear();
                    sinavYap(sinavListesi, sinavTipi);
                }
            }
            Collections.sort(sinavSorulari);
            sinavListesi.getItems().setAll(sinavSorulari);
            sinavOlustuMu = true;
        } else {
            //yeterli soru yok uyarisi verilecek...
            System.out.println("hata");
        }
    }

    public void sinaviYokEt(JFXListView<Soru> sinavList) {
        sinavSorulari.clear();
        sinavList.getItems().clear();
        sinavOlustuMu = false;
    }

    public ArrayList<Soru> soruBankasiniBelirle(ArrayList<Soru> sorular, int sinavTipi) {
        sorulariGuncelle();
        switch (sinavTipi) {
            case Constants.TESTSINAVI:
                sorular.addAll(coktanSecmeliSorular);
                break;
            case Constants.KLASIKSINAV:
                sorular.addAll(klasikSorular);
                break;
            case Constants.KARISIKSINAV:
                sorular.addAll(klasikSorular);
                sorular.addAll(coktanSecmeliSorular);
                sorular.addAll(dogruYanlisSorular);
                sorular.addAll(boslukDoldurmaSorular);
                break;
        }
        return sorular;
    }

    public int toplamKacPuan(List<Soru> sorular) {
        //bu metod gelen soru listesinin içindeki soruların puanını hesaplayıp döndürür.
        //bu metod sayesinde seçilen sınav tipi için gerekli olan soruların puanı 100den düşük
        //olup olmadıgını kontrol edeceğiz. ilerde lazım olacak.
        int toplamPuan = 0;
        for (Soru temp : sorular) {
            toplamPuan += temp.getPuan();
        }
        return toplamPuan;
    }

    ObservableList<Soru> sorulariAyikla(int soruTipi) {
        ObservableList<Soru> tempList = FXCollections.observableArrayList();
        for (Soru temp : soruBankasi) {
            if (temp.getSoruTipi() == soruTipi) {
                tempList.add(temp);
            }
        }
        return tempList;
    }

    public void sorulariGuncelle() {
        dogruYanlisSorular = sorulariAyikla(Constants.DOGRUYANLIS);
        coktanSecmeliSorular = sorulariAyikla(Constants.TEST);
        boslukDoldurmaSorular = sorulariAyikla(Constants.BOSLUKDOLDURMA);
        klasikSorular = sorulariAyikla(Constants.KLASIK);
    }


}