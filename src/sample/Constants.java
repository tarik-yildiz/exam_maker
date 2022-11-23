package sample;

public class Constants {

    public static final char[] SIK_HARFLERI = {'A', 'B', 'C', 'D'};

    public static final String KOLAY = "Kolay";
    public static final String ORTA = "Orta";
    public static final String ZOR = "Zor";

    //SORU TIPLERI
    public static final int KLASIK = -1;
    public static final int TEST = 0;
    public static final int BOSLUKDOLDURMA = 1;
    public static final int DOGRUYANLIS = 2;

    //ZORLUK SEVIYELERI
    public static final int KOLAYSEVIYE = 11;
    public static final int ORTASEVIYE = 22;
    public static final int ZORSEVIYE = 33;

    //SINAV TIPLERI
    public static final int KLASIKSINAV = 12;
    public static final int TESTSINAVI = 24;
    public static final int KARISIKSINAV = 36;

    //Dogru Yanlis
    public static final String DOGRU="DOĞRU";
    public static final String YANLIS="YANLIS";

    public static final String[] DYSorusu={DOGRU,YANLIS};

    //Soru Arama Penceresi icin secenekler
    public static final int SORUMETNIRADIO=9999;
    public static final int SORUSIKLARIRADIO=9998;
    public static final int DOGRUSIKLARIRADIO=9997;
    public static final int PUANRADIO=9996;
    public static final int ZORLUKRADIO=9995;

    //Hazir Alert Mesajlari
    public static final String BILGILENDIRME="BILGILENDIRME";
    public static final String PUANZORLUKSECILMEDI="Puani/Zorluk derecesi secilmeden soru yaratamazsınız!";
    public static final String EKSIKBIRSEYLER="Lütfen soru oluşturmak için gereken verileri eksiksiz giriniz.";
    public static final String EKLEMEBASARILI="Soru ekleme işlemi başarıyla tamamlandı.";

    public static final String[] ZORLUKSEVIYELERI = {
            KOLAY, ORTA, ZOR
    };


}
