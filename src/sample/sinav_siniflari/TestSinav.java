package sample.sinav_siniflari;
import javafx.collections.ObservableList;
import sample.Constants;
import sample.soru_siniflari.CoktanSecmeliSoru;
import sample.soru_siniflari.Soru;

public class TestSinav extends Sinav{
	private int sinavdanAlinanNot = 0;

	public TestSinav(ObservableList<Soru> soruHavuzu) {
		super(soruHavuzu);
		sinavTipi= Constants.TESTSINAVI;
	}

	public int sinavdanAlinanNotuHesapla() {
		for(Soru soru:getSorular()) {
			CoktanSecmeliSoru soruTemp=(CoktanSecmeliSoru) soru;
			if(soruTemp.dogruIsaretlendiMiControl().equals(Constants.DOGRU)) {//burada siklari kontrol edicez
				sinavdanAlinanNot +=  soru.getPuan();
			}
		}
		return sinavdanAlinanNot;
	}



}
