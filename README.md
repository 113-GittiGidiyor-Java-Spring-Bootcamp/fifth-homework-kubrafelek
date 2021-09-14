
## Nasıl kullanabilirim ?

* Cloneladıktan sonra veritabanı oluşturmaya gerek duymaksızın **localhost:8080/[swagger-ui](http://localhost:8080/swagger-ui.html)** şeklinde endpointlere erişim sağlanabilir.

* Database erişimi için **localhost:8080/[h2-console](http://localhost:8080/h2-console/)** diyerek in memory database yapısına erişim sağlanır.

* **application.yml** içinde ki configurasyon ayarları isteye bağlı değiştirilebilir veya düzenlenebilir.


## Kullanılan Teknolojiler

- Spring Boot
- Spring Web + Data Jpa
- Lombok
- Swagger UI
- H2 Database
- MapStruct
- Spring Boot Test

## Modeller (Entity)
 
* Student
* Instructor
* Course

-> Toplam da 3 adet model vardır. Controller üzerinde response ve request işlemleri DTO kapsamında gerçekleştirilmiştir.

-> Instructor-Course arasında **OneToMany** ilişkisi ve Student-Course arasında **ManyToMany** ilişkisi bulunmaktadır.

### - Salary Transaction işlemlerinin kayıtları
![](ss/Ekran%20Resmi%202021-09-14%2013.52.16.png)
---

![](ss/Ekran%20Resmi%202021-09-14%2016.29.01.png)

### - Örnek test case
![](ss/Ekran%20Resmi%202021-09-14%2018.34.21.png)
---

# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)
