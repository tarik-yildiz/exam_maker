# exam_maker
NDP- Sinav uygulamasi ödevi
Ödevde bir sınav uygulaması yapmanız gerekmektedir.
Buna göre, aşağıdaki istenenler gerçekleştirilebilmelidir:
++1. Soru bankasına soru ekleme: Soru bankasına farklı türden sorular eklenebilmelidir.
    Soru tipleri çoktan seçmeli, doğru/yanlış tipinde sorular,
    boşluk doldurma şeklinde sorular ve klasik tipte sorular eklenebilmelidir.
Sorular ile ilgili sınıfların yazılmasında ve nesnelerinin kullanımında kalıtım ve polimorfizm kullanmak zorunludur.
Örneğin çoktan seçmeli bir soruda aşağıdaki bilgiler olmalıdır:
a) Soru metni
b) a,b,c,d gibi cevap şıkları
c) Cevabın hangi şık olduğu
d) Puan
e) Zorluk derecesi (kolay, normal ve zor gibi)
Diğer soru tiplerinin içeriğini de göz önüne alarak üst sınıflarda ve alt sınıflar hangi özellikler olması gerektiğini belirlenmelidir.
++2. Soru bankasından soru çıkarma: Soru bankasından soru çıkartılırken öncelikle silinecek sorunun soru bankasından bulunması gereklidir.
    Bunun için silinmek istenen sorunun bulunmasında “soru metni” üzerinden arama yapılacaktır.
    Arama yapılacak kelime soru metinleri içerisinden aranarak soru metni içerisinde aranan kelimenin geçtiği sorular
    listelenecektir. Daha sonra kullanıcı filtrelenmiş listeden istediği soruyu seçerek silebilecektir.
3. Soru bankasındaki soruları listeleme: Soru bankasındaki soruların tümü listelenebileceği gibi belli bir kritere uyan sorular da listelenebilmelidir.
    Bu kriterler aşağıdaki gibidir:
    a. Soru metni içinde arama
    b. Soru şıklarının metinleri içinde
    c. Doğru şıkları üzerinden arama (örneğin cevabı A şıkkı olanları listele gibi)
    d. Puan üzerinden arama (örneğin puanı 10 olan soruları listele gibi)
    e. Zorluk derecesi üzerinden listeleme (örneğin zor soruları listele gibi)
    Sorular listelenirken puanlarına göre küçükten büyüğe doğru olacak şekilde listelenmelidir.
    Bunun için Comparable arayüzünü gerçekleştirmesi zorunludur.
++4. Sınav oluşturma: Puan toplamı 100-110 arasında olacak şekilde soru bankasından rasgele sorular seçilerek bir
    sınav oluşturulacaktır. Üç farklı sınav türü olacaktır.
    Bunlardan birincisi test (sadece çoktan seçmeli sorulardan üretilmeli),
    ikincisi klasik sınav (sadece klasik sorulardan üretilmeli) ve
    üçüncüsü karışık sınav (tüm soru tiplerinden içerisinde bulunabilmeli) türüdür.
    Sınav türleri için sınıflar oluşturulurken kalıtım kullanımı zorunludur.
    Sınav türü kullanıcıdan alınarak sınav oluşturulmalıdır.
    Sınav oluşturulduğunda ekranda sorular sırasıyla kullanıcılara sorulmalı ve sınav cevapları ile birlikte "sinav.txt"
    adlı bir dosyaya kaydedilmelidir. Sadece test sınavları için sınav sonunda sınavdan alınan not ekranda yazılmalıdır
    (cevap şıkları belli olduğu için).
5. Çıkış: Çıkış seçildiğinde program sonlandırılmalıdır.
UYARILAR
• Programda veritabanı kullanmayınız.
• Görsel arayüz tasarlayanlara sadece 5 puan ek olarak verilecektir.
• Ödev grup halinde de yapılabilir. Grup üyesi sayısı maksimum 3 kişi olabilir.
• Ödev oys.dpu.edu.tr uzaktan eğitim sistemi üzerinden ve sıkıştırılmış bir dosya içinde yüklenmeli,
sıkıştırılmış dosya içerisinde sadece .java uzantılı sınıf dosyaları ve
 grup üyelerini tanıtan bir uyeler.txt dosyası bulunmalıdır.
 • Ödev kontrolü dersin asistanı Arş. Gör. Safa DÖRTERLER tarafından uzaktan ve
  her grubu ayrı ayrı oturuma çağırarak gerçekleştirecektir. Ödev kontrolü ile ilgili detaylı
  bilgilendirme daha sonra yapılacaktır.

ÖDEV SON GÖNDERİM TARİHİ 05 HAZİRAN 2021 24:00’DÜR.  EK SÜRE VERİLMEYECEKTİR.
