package sample.sinav_siniflari;

import javafx.collections.ObservableList;
import sample.soru_siniflari.Soru;
import java.io.*;
import java.util.Iterator;

public abstract class Sinav {
    private ObservableList<Soru> sorular;
    public int sinavTipi;
    public Sinav(ObservableList<Soru> sorular) {
        this.sorular=sorular;
    }


   public void sinavSorulariniDosyayaYazdir() {
        try(BufferedWriter yazici=new BufferedWriter(new FileWriter("sinav.txt"))) {
            Iterator<Soru> iterator= sorular.iterator();
            while (iterator.hasNext()){
                Soru yazdirilacakSoru=iterator.next();
                yazici.write(yazdirilacakSoru.yazdirmaBicimi());
                yazici.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Soru> getSorular() {
        return sorular;
    }


}
