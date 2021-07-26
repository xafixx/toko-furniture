# PROJECT TOKO FURNITURE


Project dibuat menggunakan Apache Netbeans IDE 12.3 Java/JDK 16, database Mysql(xampp v3.3.0)

## Hal-hal yang perlu dilakukan sebelum membuka/menjalankan project pada Apache Netbeans IDE:
   * Tambahkan library
      Library yang dibutuhkan disini adalah Mysql-connector,
      file Mysql-connector berada di \Project_TokoFurniture\dist\lib\ 
      file berekstensi .jar.
       
   * Untuk menambahkan libraries: 
       Buka project pada Apache Netbeans klik kanan libraries,
       klik ADD JAR/Folder, pilih file mysql-connector-java-8.0.25.jar
       yang berada di folder \Project_TokoFurniture\dist\lib\

## Jika ingin menjalankan jar file tanpa IDE:
    - Pastikan menggunakan JDK 16
    - Jalankan xampp start Mysql
    - Import database tokofurnituredb.sql di folder \database\
    - Buka cmd menuju ke \Project_TokoFurniture\dist\
    - Terdapat file Project_TokoFurniture.jar
    - Jalankan dengan perintah java -jarProject_TokoFurniture.jar [ENTER] 
