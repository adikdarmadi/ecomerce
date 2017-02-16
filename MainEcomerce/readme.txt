Medifirst2000 (Sebagai parent project) terdiri dari beberapa sub-project :

core     : berisi Core aplikasi dan juga class helper, util dll
config   : berisi konfigurasi aplikasi
           File proeprties jdbc.properties, message.properties, dataSource, transactionManager, dsb
domain   : berisi class entity dan VO
business : berisi Service, Repository/DAO dan class Business
web      : berisi REST Controller
web-push : berisi implementasi untuk push notification using WebServlet async=true



Untuk menjalankan projectnya :

- Siapkan database Postgree
- Sesuaikan konfigurasi di sub projk ecomerce-config
  File jdbc.development.properties dan jdbc.production.properties
- Create DB : Medifirst2000
- Masuk ke projek Medifirst2000 lewat command prompt
  Run command : mvn clean install
- Masuk ke projek ecomerce-web lewat command prompt
  Run command : mvn tomcat:run