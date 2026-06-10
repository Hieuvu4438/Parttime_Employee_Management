# Huong dan chay Module 1 bang VS Code

Tai lieu nay huong dan chay module Java Swing MVC/DAO cho chuc nang dang ky ca lam tuan toi.

## 1. Yeu cau moi truong

Can cai dat:

- JDK 8 tro len
- MySQL Server
- VS Code
- VS Code extension: `Extension Pack for Java`

## 2. Mo project trong VS Code

Mo VS Code, chon:

```text
File -> Open Folder
```

Chon folder:

```text
D:\PROJECTS\Introduction to Software Engineer\Parttime_Employee_Management\module-1-code
```

## 3. Them MySQL JDBC driver

Trong folder `module-1-code`, tao folder `.vscode` neu chua co, sau do tao file:

```text
.vscode/settings.json
```

Noi dung:

```json
{
  "java.project.referencedLibraries": [
    "../se/mysql_jdbc/mysql-connector-java-8.0.30.jar"
  ]
}
```

Neu VS Code da mo project truoc do, hay reload lai VS Code sau khi them file nay.

## 4. Tao database

Mo MySQL Workbench hoac terminal MySQL, chay file:

```text
D:\PROJECTS\Introduction to Software Engineer\Parttime_Employee_Management\module-1-code\db.sql
```

File nay se tao database:

```text
parttime_employee_management
```

Va seed san du lieu mau gom user, nhan vien, ca lam tuan toi, va dang ky mau.

## 5. Kiem tra cau hinh ket noi database

Mo file:

```text
src/dao/DAO.java
```

Kiem tra cac dong cau hinh:

```java
protected static final String DB_USER = "root";
protected static final String DB_PASSWORD = "your_mysql_password";
```

Sua `DB_PASSWORD` thanh mat khau MySQL tren may cua ban truoc khi chay.

## 6. Chay ung dung bang VS Code

Mo file:

```text
src/view/user/LoginFrm.java
```

Trong file co ham:

```java
public static void main(String[] args)
```

Bam nut:

```text
Run
```

VS Code se compile va mo cua so `LoginFrm`.

## 7. Tai khoan dang nhap

Dung mot trong hai tai khoan mau:

```text
username: staff01
password: 123456
```

Hoac:

```text
username: staff02
password: 123456
```

## 8. Luong demo chuc nang

Sau khi dang nhap thanh cong:

```text
LoginFrm -> HomeFrm -> SearchEmployeeFrm -> RegisterShiftFrm -> HomeFrm
```

Thao tac demo:

1. Dang nhap bang `staff01 / 123456`.
2. Bam `Register for next week`.
3. O man hinh tim nhan vien, nhap:

```text
E001
```

Hoac:

```text
Nguyen
```

4. Double click vao dong nhan vien can dang ky.
5. Chon cac ca lam trong bang.
6. Bam `Save`.
7. Neu luu thanh cong, chuong trinh hien thong bao va quay ve `HomeFrm`.

## 9. Chay bang terminal trong VS Code

Neu nut `Run` khong hien, mo terminal tai folder `module-1-code` va chay:

```powershell
javac -encoding UTF-8 -cp "../se/mysql_jdbc/mysql-connector-java-8.0.30.jar" -d out/production src/model/*.java src/dao/*.java src/view/user/*.java src/view/registration/*.java
```

Sau do chay:

```powershell
java -cp "out/production;../se/mysql_jdbc/mysql-connector-java-8.0.30.jar" view.user.LoginFrm
```

## 10. Loi thuong gap

### Khong login duoc

Kiem tra:

- MySQL Server da chay chua.
- Da chay file `db.sql` chua.
- `DB_USER` va `DB_PASSWORD` trong `DAO.java` co dung voi MySQL local khong.
- Database `parttime_employee_management` co ton tai khong.

### VS Code bao khong tim thay MySQL driver

Kiem tra file:

```text
.vscode/settings.json
```

Va duong dan jar:

```text
../se/mysql_jdbc/mysql-connector-java-8.0.30.jar
```

### Loi Access denied for user root

Sua lai `DB_PASSWORD` trong:

```text
src/dao/DAO.java
```

cho dung voi mat khau MySQL tren may dang chay.

## 11. Chay bang IntelliJ IDEA

Neu dung IntelliJ IDEA, co the chay truc tiep project theo cac buoc sau.

### 11.1. Mo project

Mo IntelliJ IDEA, chon:

```text
File -> Open
```

Chon folder:

```text
D:\PROJECTS\Introduction to Software Engineer\Parttime_Employee_Management\module-1-code
```

Sau do bam `OK` hoac `Open`.

### 11.2. Kiem tra SDK

Vao:

```text
File -> Project Structure -> Project
```

Kiem tra `SDK` da chon JDK 8 tro len. Neu chua co JDK, bam `Add SDK` va chon thu muc JDK tren may.

### 11.3. Them MySQL JDBC driver

Vao:

```text
File -> Project Structure -> Modules -> Dependencies
```

Bam dau `+`, chon:

```text
JARs or Directories
```

Chon file:

```text
D:\PROJECTS\Introduction to Software Engineer\Parttime_Employee_Management\se\mysql_jdbc\mysql-connector-java-8.0.30.jar
```

Bam `Apply`, sau do bam `OK`.

### 11.4. Tao database

Mo MySQL Workbench hoac terminal MySQL va chay file:

```text
D:\PROJECTS\Introduction to Software Engineer\Parttime_Employee_Management\module-1-code\db.sql
```

File nay tao database `parttime_employee_management` va du lieu mau de dang nhap, tim nhan vien, dang ky ca.

### 11.5. Sua mat khau MySQL trong DAO.java

Mo file:

```text
src/dao/DAO.java
```

Kiem tra:

```java
protected static final String DB_USER = "root";
protected static final String DB_PASSWORD = "your_mysql_password";
```

Sua `DB_PASSWORD` thanh mat khau MySQL tren may. Vi du:

```java
protected static final String DB_PASSWORD = "hieu1205";
```

### 11.6. Chay chuong trinh

Mo file:

```text
src/view/user/LoginFrm.java
```

Tim ham:

```java
public static void main(String[] args)
```

Bam nut tam giac mau xanh ben canh ham `main`, chon:

```text
Run 'LoginFrm.main()'
```

Neu IntelliJ hoi module/classpath, chon module `module-1-code`.

### 11.7. Dang nhap va demo

Tai khoan dang nhap:

```text
username: staff01
password: 123456
```

Hoac:

```text
username: staff02
password: 123456
```

Luong demo:

```text
LoginFrm -> HomeFrm -> SearchEmployeeFrm -> RegisterShiftFrm -> HomeFrm
```

Co the tim nhan vien bang tu khoa:

```text
E001
```

Hoac:

```text
Nguyen
```

Double click vao nhan vien, chon ca lam, sau do bam `Save`.

### 11.8. Loi thuong gap tren IntelliJ

#### Khong tim thay package `com.mysql.cj.jdbc.Driver`

Nguyen nhan la chua add MySQL JDBC jar vao module. Lam lai buoc `11.3`.

#### Login bao Invalid username or password

Kiem tra:

- Ban co dang chay dung file `module-1-code/src/view/user/LoginFrm.java` khong.
- Da chay `db.sql` de tao database chua.
- MySQL Server da bat chua.
- `DB_PASSWORD` trong `src/dao/DAO.java` co dung voi MySQL local khong.
- Tai khoan nhap dung la `staff01 / 123456` hoac `staff02 / 123456`.

#### Chay nham project mau hotel

Trong repo co folder mau:

```text
se/demo CNPM hotel
```

Khong chay `LoginFrm` trong folder nay. Module can chay la:

```text
module-1-code/src/view/user/LoginFrm.java
```
