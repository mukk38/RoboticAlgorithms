# Tez Projesi - Görsel Algoritma Karşılaştırma Uygulaması

## Proje Hakkında
Bu proje, **Java Swing** kullanılarak geliştirilmiş bir **masaüstü uygulamasıdır**. Kullanıcılar, **başlangıç ve bitiş noktalarını seçerek** sabit veya hareketli engellerle farklı algoritmaların karşılaştırmasını görsel olarak yapabilirler.

Proje, iki farklı senaryoyu desteklemektedir:
1. **Sabit Engeller Senaryosu**: Kullanıcı, başlangıç ve bitiş noktalarını belirler, ardından sabit engeller çizer. Bu durumda **A* (A Star), RRT, RRT* ve Lee algoritmaları** kıyaslanarak yollar görsel olarak çizilir.
2. **Hareketli Engeller Senaryosu**: Kullanıcı, hareketli engeller tanımlayabilir ve **Potential Field** ile **Dynamic Windows Approach (DWA)** algoritmalarını karşılaştırabilir.

## Kullanılan Teknolojiler
- **Java Swing** (Grafik arayüzü için)
- **Java** (Algoritmaların implementasyonu için)
- **OOP (Nesne Yönelimli Programlama)**

## Özellikler
- **Kullanıcı dostu arayüz** ile başlangıç ve bitiş noktalarını belirleme
- **Engel çizme aracı** ile sabit veya hareketli engeller oluşturma
- **Farklı algoritmaların kıyaslanması** ve animasyonlu olarak gösterimi
- **Hareketli engellerle dinamik rota planlaması**

## Kurulum ve Çalıştırma
### Gereksinimler
- **Java 8+**
- ** IntelliJ IDEA/Eclipse gibi bir IDE**

### Kurulum
1. **Depoyu klonlayın:**
   ```sh
   git clone <repo-url>
   cd <project-directory>
   ```
2. **Projeyi derleyin ve çalıştırın:**
   ```sh
   java -jar ***
   ```

## Algoritmaların Açıklamaları
### Sabit Engeller İçin Karşılaştırılan Algoritmalar
1. **A* (A Star)**: Heuristik bir arama algoritmasıdır.
2. **RRT (Rapidly-exploring Random Tree)**: Rastgele örnekleme tabanlı bir yol planlama algoritmasıdır.
3. **RRT***: RRT algoritmasının geliştirilmiş versiyonudur.
4. **Lee Algoritması**: Dalga yayılımı yöntemiyle çalışan bir yol planlama algoritmasıdır.

### Hareketli Engeller İçin Karşılaştırılan Algoritmalar
1. **Potential Field**: Robotların engellerden kaçınmasını sağlamak için potansiyel alanları kullanır.
2. **Dynamic Windows Approach (DWA)**: Robotun hız, yön ve engel bilgilerini değerlendirerek dinamik olarak karar vermesini sağlar.

## Ekran Görüntüleri

![DynamicWindowsApproach](https://github.com/user-attachments/assets/acf78228-9246-4c2a-9ddc-09736164bec0)
![potentialFieldAlgorithm](https://github.com/user-attachments/assets/275dd9d1-f7bc-46f8-b8de-f82f019c0247)
![RRT](https://github.com/user-attachments/assets/3e79abf2-3802-43dc-93d9-f58c65119672)
![RRTStar](https://github.com/user-attachments/assets/3337345c-a0a8-4944-81b2-c0f3519581d6)

## Gelecekteki Geliştirmeler
- **Farklı yol planlama algoritmalarının eklenmesi**
- **Engellerin daha gerçekçi fiziksel modellerle simüle edilmesi**
- **Gerçek zamanlı haritalama ve SLAM entegrasyonu**
