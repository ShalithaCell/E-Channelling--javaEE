# Online-E-channeling System

Technologies used: HTML, java web, jquery, ajax, MySQL database.Created for best practice Java EE.

---

# Purpose

The purpose of this project is for best practice OOP and Java EE.This is SLIIT | Sri Lanka Institute of Information Technology Second Year Semester 1 Web Project (Module - OOP).

---

### Pre requisites

   If you have not done so please refer to the Download and Installation instructions,

  * Maven
    * [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
  
  * MySql
    * [Download MySQL For Windows](https://dev.mysql.com/downloads/installer/)
    * [Download MySQL For Mac](https://dev.mysql.com/downloads/mysql/)
    
  * IDE (Eclipse IDE for Java EE or IntelliJ IDEA) Recommended - IntelliJ IDEA
      * [Download Eclipse IDE for Java EE](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)
      * [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
    
  
# Development setup

### 1. Retrieve our project (if you haven't done so already)

```git
 $ git clone git@github.com:ShalithaCell/E-Channelling--javaEE.git
```

### 2. Import project to your IDE

   Please follow below instructions to import project,
   
   **Make sure your Internet connection is working... (Internet connection is required )**

  * In Eclipse
    * [Importing maven project into eclipse](https://vaadin.com/tutorials/import-maven-project-eclipse)
  * In IntelliJ IDEA
    * [Importing maven project into IntelliJ IDEA](https://www.lagomframework.com/documentation/1.5.x/java/IntellijMaven.html)
    

### 3. Restore database

   * Goto MySql work bench and Import EChannelDB.sql file which is located under project folder/DB_Backup.
     * [How do I import my MySQL database with MySQL Workbench](https://www.linode.com/docs/databases/mysql/deploy-mysql-workbench-for-database-administration/#to-import)
   
### 4.Setup configurations

  * Open project in your IDE.
  * Goto src -> main -> java -> com.echanneling.delegate package and open config.properties file.
    * setup your configurations,
        * add your email credentials and MySql connection string in here. 
        make sure you should enable Enable less secure apps in gmail account.
                * [How to enable less secure apps](https://support.google.com/a/answer/6260879?hl=en)
  
 
 ### 5. Start server
  * start the server and run;
    * http://localhost:8080/ (replace the port number 8080 to your port).
  * login using following credentials,
    * username - admin@gmail.com
    * password - 12345678
    
# Screenshots

  * <img width="1280" alt="Screen Shot 2019-10-07 at 10 00 01 PM" src="https://user-images.githubusercontent.com/43614338/66330379-e7aae100-e94d-11e9-8f09-1c5cf020bbf0.png">

  * <img width="1264" alt="Screen Shot 2019-10-07 at 10 02 32 PM" src="https://user-images.githubusercontent.com/43614338/66330521-2fca0380-e94e-11e9-9881-41f07eb9d61d.png">
  
  * <img width="1263" alt="Screen Shot 2019-10-07 at 10 03 58 PM" src="https://user-images.githubusercontent.com/43614338/66330634-6a33a080-e94e-11e9-8713-478bd2094747.png">
    
 # Contribute
 
 The best way to contribute is by spreading the word about the library:
 
 * Blog it
 * Comment it
 * Fork it
 * Star it
 * Share it
 
 A **HUGE THANKS** for your help.