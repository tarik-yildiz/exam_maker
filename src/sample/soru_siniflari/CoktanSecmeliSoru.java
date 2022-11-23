package sample.soru_siniflari;

import sample.Constants;

public class CoktanSecmeliSoru extends Soru {
    private String[] secenekler;
    private int hangiSecenekDogru;
    private boolean dogruIsaretlendiMi;

    public CoktanSecmeliSoru(String soruMetni, int puan, int zorlukDerecesi,
                             String[] secenekler, int hangiSecenekDogru) {
        super(soruMetni, puan, zorlukDerecesi);
        this.hangiSecenekDogru = hangiSecenekDogru;
        this.secenekler = secenekler;
        this.soruTipi = Constants.TEST;
        dogruIsaretlendiMi = false;
    }


    public String[] getSecenekler() {
        return secenekler;
    }
    public String dogruIsaretlendiMiControl() {
        if (secenekBelirle().equals(getKullaniciCevabi())) {
            dogruIsaretlendiMi = true;
            return Constants.DOGRU;
        } else {
            dogruIsaretlendiMi = false;
            return Constants.YANLIS;
        }
    }

    public String secenekBelirle() {
        switch (hangiSecenekDogru) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            default:
                return "x";
        }
    }

    public String getDogruSecenekIcerigi() {
        return secenekler[hangiSecenekDogru];
    }

    public int getHangiSecenekDogru() {
        return hangiSecenekDogru;
    }

    public void setHangiSecenekDogru(int hangiSecenekDogru) {
        this.hangiSecenekDogru = hangiSecenekDogru;
    }


    @Override
    public String yazdirmaBicimi() {
        String metin = "&{ ( " +
                tipBelirle() + "-->" +
                zorlukBelirle() + " " +
                getPuan() + " Puan ) " +
                "Soru: " + getSoruMetni() + "\t" +
                "Secenekler:" +
                " A: " + secenekler[0] +
                " B: " + secenekler[1] +
                " C: " + secenekler[2] +
                " D: " + secenekler[3] + "\t" +
                "Cevap: (" + secenekBelirle() + ":" + getDogruSecenekIcerigi() + ")\t" +
                "Verilen Cevap: " +
                getKullaniciCevabi() + "\t" +
                dogruIsaretlendiMiControl() + " }";
        return metin;
    }

    @Override
    public String toString() {
        return "SORU: " + getSoruMetni() + " (" + zorlukBelirle() + "-> " + getPuan() + " Puan)" + "\n" +
                "A: " + secenekler[0] + "\t\t\tB: " + secenekler[1] + "\n" +
                "C: " + secenekler[2] + "\t\t\tD: " + secenekler[3];
    }


}
