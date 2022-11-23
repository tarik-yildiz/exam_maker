package sample.soru_siniflari;

import sample.Constants;

public class DogruYanlisSoru extends Soru {
    private boolean isTrue;

    public DogruYanlisSoru(String soruMetni, int puan, int zorlukDerecesi, boolean isTrue) {
        super(soruMetni, puan, zorlukDerecesi);
        this.isTrue = isTrue;
        this.soruTipi = Constants.DOGRUYANLIS;
    }
    @Override
    public String yazdirmaBicimi() {
        String metin = "&{ ( " +
                tipBelirle() + "-->" +
                zorlukBelirle() + " " +
                getPuan() + " Puan ) " +
                "Soru: " + getSoruMetni() + "\t" +
                "Cevap: " + isTrue + "\t" +
                "Verilen Cevap: " +
                getKullaniciCevabi() + " }";
        return metin;
    }


}

