package sample.sinav_siniflari;

import javafx.collections.ObservableList;
import sample.Constants;
import sample.soru_siniflari.Soru;

public class KarisikSinav extends Sinav{

    public KarisikSinav(ObservableList<Soru> soruHavuzu) {
        super(soruHavuzu);
        sinavTipi= Constants.KARISIKSINAV;
    }

}