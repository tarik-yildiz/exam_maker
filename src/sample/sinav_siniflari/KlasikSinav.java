package sample.sinav_siniflari;
import javafx.collections.ObservableList;
import sample.Constants;
import sample.soru_siniflari.Soru;


public class KlasikSinav extends Sinav{

	public KlasikSinav(ObservableList<Soru> soruHavuzu) {
		super(soruHavuzu);
		sinavTipi= Constants.KLASIKSINAV;
	}

}
