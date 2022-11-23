package sample.soru_siniflari;

import sample.Constants;

public abstract class Soru implements Comparable<Soru> {
    private int id;
    private String soruMetni;
    private int puan;
    private int zorlukDerecesi;
    private String kullaniciCevabi;
    private String soruCevabi;
    protected int soruTipi;

    public abstract String yazdirmaBicimi();

    public Soru(String soruMetni, int puan, int zorlukDerecesi, String soruCevabi) {
        this.soruMetni = soruMetni;
        this.puan = puan;
        this.zorlukDerecesi = zorlukDerecesi;
        this.soruCevabi = soruCevabi;
    }

    public Soru(String soruMetni, int puan, int zorlukDerecesi) {
        this.soruMetni = soruMetni;
        this.puan = puan;
        this.zorlukDerecesi = zorlukDerecesi;
    }

    public String getSoruMetni() {
        return soruMetni;
    }

    public String getKullaniciCevabi() {
        return kullaniciCevabi;
    }

    public String getSoruCevabi() {
        return soruCevabi;
    }

    public String zorlukBelirle() {
        String zorluk = "";
        switch (zorlukDerecesi) {
            case Constants.KOLAYSEVIYE:
                zorluk = Constants.KOLAY;
                break;
            case Constants.ORTASEVIYE:
                zorluk = Constants.ORTA;
                break;
            case Constants.ZORSEVIYE:
                zorluk = Constants.ZOR;
                break;
            default:
                break;
        }
        return zorluk;
    }

    public String tipBelirle() {
        String tip = "";
        switch (soruTipi) {
            case Constants.TEST:
                tip = "Coktan Secmeli";
                break;
            case Constants.KLASIK:
                tip = "Klasik";
                break;
            case Constants.BOSLUKDOLDURMA:
                tip = "Bosluk doldurma";
                break;
            case Constants.DOGRUYANLIS:
                tip = "Dogru yanlis";
                break;
            default:
                break;
        }
        return tip;
    }

    public int getZorlukDerecesi() {
        return zorlukDerecesi;
    }

    public int getSoruTipi() {
        return soruTipi;
    }

    public int getPuan() {
        return puan;
    }


    public void setKullaniciCevabi(String kullaniciCevabi) {
        this.kullaniciCevabi = kullaniciCevabi;
    }

    public void setSoruCevabi(String soruCevabi) {
        this.soruCevabi = soruCevabi;
    }


    @Override
    public String toString() {
        return soruMetni + "( " + zorlukBelirle() + "-> " + puan + " Puan )";
    }

    @Override
    public int compareTo(Soru soru) {
        if (this.puan == soru.puan) return 0;
        else if (this.puan > soru.puan) return 1;
        else return -1;
    }


}
