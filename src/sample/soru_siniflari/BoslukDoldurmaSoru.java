package sample.soru_siniflari;

import sample.Constants;

public class BoslukDoldurmaSoru extends Soru {
	public BoslukDoldurmaSoru(String soruMetni, int puan, int zorlukDerecesi, String soruCevabi) {
		super(soruMetni, puan, zorlukDerecesi, soruCevabi);
		this.soruTipi= Constants.BOSLUKDOLDURMA;
	}

	@Override
	public String yazdirmaBicimi() {
		String metin="&{ ( "+
				tipBelirle()+"-->"+
				zorlukBelirle()+" "+
				getPuan()+" Puan ) "+
				"Soru: "+ getSoruMetni()+"\t" +
				"Cevap: "+getSoruCevabi()+"\t"+
				"Verilen Cevap: "+
				getKullaniciCevabi()+" }";
		return metin;
	}


}

