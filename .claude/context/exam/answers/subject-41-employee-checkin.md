# Subject No. 41 — Part-time Employee — Module "Checkin/Checkout"

> **Domain:** Part-time Employee Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Scenario — Checkin / Checkout

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang co nhieu nhan vien tinh luong theo gio. Moi ngay lam viec co 2 ca: Ca 1 tu 8h-16h, Ca 2 tu 16h-0h. Khi di lam, nhan vien quet the checkin. Khi ve, nhan vien quet the checkout. Nhan vien co the checkin/checkout bang cach quet the hoac nhan vien van phong cap nhat truc tiep tren may tinh. Luong duoc tinh dua tren so gio lam viec thuc te. Neu lam tren 8h, luong gio tang ca duoc tinh. Neu di tre hoac ve som, thoi gian vang mat bi tru.

### Scenario chinh — Checkin nhan vien

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | He thong kiem tra dang nhap thanh cong. Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chon chuc nang **Checkin**. |
| 5 | Giao dien checkin xuat hien voi o nhap ma nhan vien va nut Submit. |
| 6 | Staff nhap ma NV `E001` vao o nhan vien code. |
| 7 | Staff nhan nut **Submit**. |
| 8 | He thong kiem tra ma NV `E001` ton tai trong he thong va NV nay co lich lam viec hom nay (da duoc xep lich trong tblSchedule). |
| 9 | He thong kiem tra NV chua checkin cho ca nay hom nay (chua co ban ghi trong tblAttendance voi checkinTime cho ca hom nay). |
| 10 | He thong ghi nhan thoi gian checkin: `08:05:00` ngay `08/06/2026`. Luu vao tblAttendance. |
| 11 | He thong hien thi thong bao: "Checkin thanh cong luc 08:05:00". |

### Scenario — Checkout nhan vien

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Home xuat hien. |
| 2 | Staff chon chuc nang **Checkout**. |
| 3 | Giao dien checkout xuat hien voi o nhap ma nhan vien va nut Submit. |
| 4 | Staff nhap ma NV `E001` vao o nhan vien code. |
| 5 | Staff nhan nut **Submit**. |
| 6 | He thong kiem tra ma NV `E001` ton tai va NV nay da checkin hom nay (co ban ghi trong tblAttendance voi checkinTime khac null nhung checkoutTime = null). |
| 7 | He thong ghi nhan thoi gian checkout: `16:02:00` ngay `08/06/2026`. Cap nhat ban ghi trong tblAttendance. |
| 8 | He thong hien thi thong bao: "Checkout thanh cong luc 16:02:00". |

### Kich ban ngoai le

- **EL1:** Ma NV khong ton tai trong he thong → He thong thong bao "Ma nhan vien khong ton tai".
- **EL2:** NV khong co lich lam viec hom nay → He thong thong bao "Nhan vien khong co lich lam viec hom nay".
- **EL3:** NV da checkin roi ma checkin lai → He thong thong bao "Nhan vien da checkin vao luc XX:XX".
- **EL4:** NV chua checkin ma checkout → He thong thong bao "Nhan vien chua checkin, khong the checkout".
- **EL5:** NV da checkout roi ma checkout lai → He thong thong bao "Nhan vien da checkout vao luc XX:XX".

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang (ten, dia chi, mo ta) co nhieu nhan vien (ma NV, ten, SĐT, email, ngay sinh, ngay ky hop dong). Moi ngay lam viec co 2 ca (ngay, so ca, mo ta, ngay bat dau tuan, ngay ket thuc tuan). Nhan vien dang ky ca lam viec. Quan ly xep lich. Khi di lam, nhan vien quet the checkin, khi ve quet the checkout. Moi ban ghi cham cong (gio checkin, gio checkout) ghi nhan thoi gian lam viec thuc te cua NV trong mot ca. Nguoi dung (ten dang nhap, mat khau, vai tro) la nhan vien thao tac.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Restaurant | Entity class | Chi nhanh quan ly nhan vien |
| Employee | Entity class | Nhan vien ban thoi gian |
| Shift | Entity class | Ca lam viec cu the |
| Schedule | Entity class | Ban ghi xep lich |
| Attendance | Entity class | Ban ghi cham cong (checkin/checkout) |
| User | Entity class | Nhan vien thao tac |
| Ma NV, ten, SĐT | Thuoc tinh | Thuoc tinh cua Employee |
| Gio checkin, gio checkout | Thuoc tinh | Thuoc tinh cua Attendance |
| Ngay, so ca | Thuoc tinh | Thuoc tinh cua Shift |
| Ten dang nhap, mat khau | Thuoc tinh | Thuoc tinh cua User |

### Buoc 3: Xac dinh quan he

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu NV |
| Employee → Schedule | 1-n | Mot NV co nhieu ban ghi lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| Employee → Attendance | 1-n | Mot NV co nhieu ban ghi cham cong |
| Shift → Attendance | 1-n | Mot ca co nhieu ban ghi cham cong |
| Schedule → Attendance | 1-1 | Moi lich co mot ban ghi cham cong |
| User → Attendance | 1-n | Mot staff tao nhieu ban ghi cham cong |

### Buoc 4: Class Diagram (Analysis)

```
+------------------+       +------------------+
|   Restaurant     |       |    Employee      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -restaurantName: |       | -code: String    |
|   String         |       | -fullName: String|
| -address: String |       | -phoneNumber:    |
| -description:    |       |   String         |
|   String         |       | -email: String   |
+------------------+       | -dob: Date       |
         | 1               | -contractDate:   |
         |                  |   Date           |
         | n                +------------------+
         v                  | +searchEmployee()|
+------------------+        | +getEmployeeBy   |
|                  |        |  Code()          |
|                  |        +------------------+
|                  |                | 1
|                  |                |
|                  |                | n
|                  |                v
|                  |        +------------------+
|                  |        |    Schedule      |
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -weekStartDate:  |
|                  |        |   Date           |
|                  |        | -assignedTime:   |
|                  |        |   DateTime       |
|                  |        +------------------+
|                  |                | 1
|                  |                |
|                  |                | 0..1
|                  |                v
|                  |        +------------------+
|                  |        |   Attendance     |
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -checkinTime:    |
|                  |        |   DateTime       |
|                  |        | -checkoutTime:   |
|                  |        |   DateTime       |
|                  |        +------------------+

+------------------+       +------------------+
|      Shift       |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -date: Date      |       | -username: String|
| -shiftNumber: int|       | -password: String|
| -description:    |       | -role: String    |
|   String         |       +------------------+
| -startDate: Date |       | +checkLogin()    |
| -endDate: Date   |       +------------------+
+------------------+
         | 1
         | n
         v
     (Attendance)
```

### Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu NV |
| Employee → Schedule | 1-n | Mot NV co nhieu lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| Employee → Attendance | 1-n | Mot NV co nhieu ban ghi cham cong |
| Shift → Attendance | 1-n | Mot ca co nhieu ban ghi cham cong |
| Schedule → Attendance | 1-0..1 | Moi lich co the co hoac chua co ban ghi cham cong |
| User → Attendance | 1-n | Mot staff tao nhieu cham cong |

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| CheckinFrm | Giao dien checkin nhan vien |
| CheckoutFrm | Giao dien checkout nhan vien |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subRegister`: nut chon Register
- `subSchedule`: nut chon Schedule
- `subCheckin`: nut chon Checkin
- `subCheckout`: nut chon Checkout
- `subWages`: nut chon Wages
- `subStatistics`: nut chon Statistics

**CheckinFrm:**
- `inEmployeeCode`: o nhap ma nhan vien
- `subSubmit`: nut Submit

**CheckoutFrm:**
- `inEmployeeCode`: o nhap ma nhan vien
- `subSubmit`: nut Submit

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| EmployeeDAO | `getEmployeeByCode(code): Employee` | Lay NV theo ma |
| AttendanceDAO | `checkin(employeeId, shiftId, checkinTime): boolean` | Ghi nhan checkin |
| AttendanceDAO | `checkout(employeeId, shiftId, checkoutTime): boolean` | Ghi nhan checkout |
| AttendanceDAO | `getTodayAttendance(employeeId, shiftId): Attendance` | Lay ban ghi cham cong hom nay |
| ScheduleDAO | `getTodaySchedule(employeeId): List<Schedule>` | Lay lich lam viec hom nay cua NV |

### Buoc 4: Xac dinh Entity class (Design phase)

**Restaurant:**
- `id: int` (PK)
- `restaurantName: String`
- `address: String`
- `description: String`

**Employee:**
- `id: int` (PK)
- `code: String`
- `fullName: String`
- `phoneNumber: String`
- `email: String`
- `dob: Date`
- `contractDate: Date`
- `restaurant: Restaurant` (object attribute)

**Shift:**
- `id: int` (PK)
- `date: Date`
- `shiftNumber: int`
- `description: String`
- `startDate: Date`
- `endDate: Date`

**Schedule:**
- `id: int` (PK)
- `weekStartDate: Date`
- `assignedTime: DateTime`
- `employee: Employee` (object attribute)
- `shift: Shift` (object attribute)
- `user: User` (object attribute)

**Attendance:**
- `id: int` (PK)
- `checkinTime: DateTime`
- `checkoutTime: DateTime`
- `employee: Employee` (object attribute)
- `shift: Shift` (object attribute)
- `schedule: Schedule` (object attribute)
- `user: User` (object attribute)

**User:**
- `id: int` (PK)
- `username: String`
- `password: String`
- `role: String`

### Buoc 5: Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| username | varchar | |
| password | varchar | |
| role | varchar | |
| description | varchar | |

**tblRestaurant:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| restaurantName | varchar | |
| address | varchar | |
| description | varchar | |

**tblEmployee:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| fullName | varchar | |
| phoneNumber | varchar | |
| email | varchar | |
| dob | date | |
| contractDate | date | |
| restaurantID | int | FK → tblRestaurant.ID |

**tblShift:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| date | date | |
| shiftNumber | int | |
| description | varchar | |
| startDate | date | |
| endDate | date | |

**tblSchedule:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| weekStartDate | date | |
| assignedTime | datetime | |
| employeeID | int | FK → tblEmployee.ID |
| shiftID | int | FK → tblShift.ID |
| userID | int | FK → tblUser.ID |

**tblAttendance:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| checkinTime | datetime | |
| checkoutTime | datetime | |
| employeeID | int | FK → tblEmployee.ID |
| shiftID | int | FK → tblShift.ID |
| scheduleID | int | FK → tblSchedule.ID |
| userID | int | FK → tblUser.ID |

### Buoc 6: Mo ta cach ve Class Diagram (Design phase) bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Class Diagram** trong danh muc Diagrams.
2. Tao cac class:

**Vei View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `LoginFrm`:
  - Ngan 1 (ten): `<<boundary>> LoginFrm`
  - Ngan 2 (thuoc tinh): `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton`
  - Ngan 3 (phuong thuc): de trong
- Tuong tu cho `HomeFrm`, `CheckinFrm`, `CheckoutFrm`.

**Vei DAO classes:**
- Ve hinh chu nhat 3 ngan cho `AttendanceDAO`:
  - Ngan 1: `<<control>> AttendanceDAO`
  - Ngan 2: de trong
  - Ngan 3: `+checkin(employeeId: int, shiftId: int, checkinTime: DateTime): boolean`, `+checkout(employeeId: int, shiftId: int, checkoutTime: DateTime): boolean`, `+getTodayAttendance(employeeId: int, shiftId: int): Attendance`
- Tuong tu cho `UserDAO`, `EmployeeDAO`, `ScheduleDAO`.

**Vei Entity classes:**
- Ve hinh chu nhat 3 ngan cho `Attendance`:
  - Ngan 1: `<<entity>> Attendance`
  - Ngan 2: `-id: int`, `-checkinTime: DateTime`, `-checkoutTime: DateTime`, `-employee: Employee`, `-shift: Shift`, `-schedule: Schedule`, `-user: User`
  - Ngan 3: `+checkin(): boolean`, `+checkout(): boolean`, `+getTodayAttendance(): Attendance`

**Vei cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| LoginFrm | UserDAO | Duong lien net, mui ten tam giac rong | Dependency | LoginFrm su dung UserDAO |
| CheckinFrm | EmployeeDAO | Duong lien net, mui ten tam giac rong | Dependency | CheckinFrm su dung EmployeeDAO |
| CheckinFrm | AttendanceDAO | Duong lien net, mui ten tam giac rong | Dependency | CheckinFrm su dung AttendanceDAO |
| CheckinFrm | ScheduleDAO | Duong lien net, mui ten tam giac rong | Dependency | CheckinFrm su dung ScheduleDAO |
| CheckoutFrm | AttendanceDAO | Duong lien net, mui ten tam giac rong | Dependency | CheckoutFrm su dung AttendanceDAO |
| Employee | Attendance | Duong lien net, dau kim cuong rong | Aggregation 1-n | NV co nhieu ban ghi cham cong |
| Shift | Attendance | Duong lien net, dau kim cuong rong | Aggregation 1-n | Ca co nhieu ban ghi cham cong |
| Schedule | Attendance | Duong lien net, dau kim cuong filled | Composition 1-0..1 | Attendance khong ton tai neu khong co Schedule |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `CheckinFrm`, `CheckoutFrm`
   - Control: `UserDAO`, `EmployeeDAO`, `AttendanceDAO`, `ScheduleDAO`

3. Ve cac message (muiten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Checkin nhan vien (Scenario version 3)

```
Staff          LoginFrm      UserDAO     HomeFrm     CheckinFrm     EmployeeDAO    ScheduleDAO    AttendanceDAO
  |               |             |           |               |               |              |               |
  |---login------>|             |           |               |               |              |               |
  |               |---checkLogin>|          |               |               |              |               |
  |               |             |--query DB |               |               |              |               |
  |               |             |<-return---|               |               |              |               |
  |               |<--true------|           |               |               |              |               |
  |               |---open------|---------->|               |               |              |               |
  |               |             |           |               |               |              |               |
  |---select------|---------------------------->|            |               |              |               |
  |               |             |           |---open--------|-------------->|              |               |
  |               |             |           |               |               |              |               |
  |---enter code-->|            |           |               |               |              |               |
  |---submit------>|            |           |               |               |              |               |
  |               |             |           |               |               |              |               |
  |               |             |           |               |---getEmployeeBy|------------->|               |
  |               |             |           |               |   Code("E001")|              |               |
  |               |             |           |               |               |--query DB   |               |
  |               |             |           |               |               |<-return------|               |
  |               |             |           |               |<--Employee----|              |               |
  |               |             |           |               |               |              |               |
  |               |             |           |               |---getTodaySchedule----------->|               |
  |               |             |           |               |               |              |--query DB    |
  |               |             |           |               |               |              |<-return------|
  |               |             |           |               |<--List<Sched>-|              |               |
  |               |             |           |               |               |              |               |
  |               |             |           |               |---getTodayAttendance--------->|               |
  |               |             |           |               |               |              |--query DB    |
  |               |             |           |               |               |              |<-return------|
  |               |             |           |               |<--null--------|              |               |
  |               |             |           |               |               |              |               |
  |               |             |           |               |---checkin------------------->|               |
  |               |             |           |               |               |              |--INSERT DB   |
  |               |             |           |               |               |              |<-return true--|
  |               |             |           |               |<--true--------|              |               |
  |               |             |           |               |               |              |               |
  |               |             |           |               |--show success |              |               |
  |<--"Checkin thanh cong luc   |           |               |               |              |               |
  |   08:05:00"---|             |           |               |               |              |               |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Checkin | Staff | HomeFrm | Staff chon chuc nang Checkin |
| 7 | open | HomeFrm | CheckinFrm | Mo giao dien checkin |
| 8 | enter code | Staff | CheckinFrm | Staff nhap ma NV "E001" |
| 9 | submit | Staff | CheckinFrm | Staff nhan nut Submit |
| 10 | getEmployeeByCode() | CheckinFrm | EmployeeDAO | Goi EmployeeDAO.getEmployeeByCode("E001") |
| 11 | query DB | EmployeeDAO | Database | Truy van tblEmployee WHERE code = 'E001' |
| 12 | return Employee | EmployeeDAO | CheckinFrm | Tra ve doi tuong Employee |
| 13 | getTodaySchedule() | CheckinFrm | ScheduleDAO | Goi ScheduleDAO.getTodaySchedule(employeeId) |
| 14 | query DB | ScheduleDAO | Database | Truy van tblSchedule JOIN tblShift WHERE date = today |
| 15 | return List<Schedule> | ScheduleDAO | CheckinFrm | Tra ve DS lich lam viec hom nay |
| 16 | getTodayAttendance() | CheckinFrm | AttendanceDAO | Goi AttendanceDAO.getTodayAttendance(employeeId, shiftId) |
| 17 | query DB | AttendanceDAO | Database | Truy van tblAttendance WHERE employeeID va shiftID hom nay |
| 18 | return null | AttendanceDAO | CheckinFrm | Tra ve null (chua co ban ghi) |
| 19 | checkin() | CheckinFrm | AttendanceDAO | Goi AttendanceDAO.checkin(employeeId, shiftId, now()) |
| 20 | INSERT DB | AttendanceDAO | Database | INSERT INTO tblAttendance (checkinTime, employeeID, shiftID) |
| 21 | return true | AttendanceDAO | CheckinFrm | Tra ve true |
| 22 | show success | CheckinFrm | UI | Hien thi "Checkin thanh cong luc 08:05:00" |
| 23 | return | CheckinFrm | Staff | Hien thi ket qua cho staff |

---

## Cau 5: Blackbox Testcase

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Checkin | Checkin thanh cong voi NV co lich lam viec hom nay |
| 2 | Checkin | Ma NV khong ton tai trong he thong |
| 3 | Checkin | NV khong co lich lam viec hom nay |
| 4 | Checkin | NV da checkin roi, checkin lai |
| 5 | Checkout | Checkout thanh cong voi NV da checkin |
| 6 | Checkout | NV chua checkin ma checkout |
| 7 | Checkout | NV da checkout roi, checkout lai |

### Testcase chi tiet — TC01: Checkin thanh cong

**Muc dich:** Kiem tra chuc nang checkin hoat dong dung khi NV co lich lam viec hom nay va chua checkin.

**Database truoc khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblRestaurant:**
| ID | restaurantName | address | description |
|----|---------------|---------|-------------|
| 1 | Nha hang A | Ha Noi | Chi nhanh chinh |

**tblEmployee:**
| ID | code | fullName | phoneNumber | email | dob | contractDate | restaurantID |
|----|------|----------|-------------|-------|-----|-------------|--------------|
| 1 | E001 | Nguyen Van A | 0911111111 | a@gmail.com | 01/01/2000 | 01/01/2026 | 1 |
| 2 | E002 | Tran Thi B | 0922222222 | b@gmail.com | 02/02/2000 | 01/01/2026 | 1 |
| 3 | E003 | Le Van C | 0933333333 | c@gmail.com | 03/03/2000 | 01/01/2026 | 1 |

**tblShift:**
| ID | date | shiftNumber | description | startDate | endDate |
|----|------|-------------|-------------|-----------|---------|
| 1 | 08/06/2026 | 1 | Thu 2 Ca 1 | 09/06/2026 | 15/06/2026 |
| 2 | 08/06/2026 | 2 | Thu 2 Ca 2 | 09/06/2026 | 15/06/2026 |
| 3 | 09/06/2026 | 1 | Thu 3 Ca 1 | 09/06/2026 | 15/06/2026 |

**tblSchedule:**
| ID | weekStartDate | assignedTime | employeeID | shiftID | userID |
|----|--------------|-------------|------------|---------|--------|
| 1 | 09/06/2026 | 08/06/2026 14:00 | 1 | 1 | 1 |
| 2 | 09/06/2026 | 08/06/2026 14:00 | 2 | 1 | 1 |
| 3 | 09/06/2026 | 08/06/2026 14:00 | 3 | 2 | 1 |

**tblAttendance:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin, Checkout, Wages, Statistics |
| 3 | Chon chuc nang **Checkin** | Giao dien checkin xuat hien voi o nhap ma nhan vien va nut Submit |
| 4 | Nhap ma NV `E001` vao o nhan vien code | O nhan vien code hien thi "E001" |
| 5 | Nhan nut **Submit** | He thong tim thay NV Nguyen Van A (E001), kiem tra co lich lam viec Ca 1 hom nay (shiftID=1). NV chua checkin (tblAttendance chua co ban ghi). He thong ghi nhan checkin luc 08:05:00 |
| 6 | Hien thi ket qua | Thong bao "Checkin thanh cong luc 08:05:00" |
| 7 | Nhan nut **Checkout** (hoac tro ve Home chon Checkout) | Giao dien checkout xuat hien |
| 8 | Nhap ma NV `E001`, nhan **Submit** | He thong tim thay NV E001, kiem tra da checkin (checkinTime = 08:05:00). He thong ghi nhan checkout luc 16:02:00. Thong bao "Checkout thanh cong luc 16:02:00" |

### Database sau khi test

**tblAttendance:** (them 1 dong, cap nhat sau checkout)
| ID | checkinTime | checkoutTime | employeeID | shiftID | scheduleID | userID |
|----|------------|-------------|------------|---------|------------|--------|
| 1 | 08/06/2026 08:05:00 | 08/06/2026 16:02:00 | 1 | 1 | 1 | 1 |

**tblUser:** (khong thay doi)
**tblRestaurant:** (khong thay doi)
**tblEmployee:** (khong thay doi)
**tblShift:** (khong thay doi)
**tblSchedule:** (khong thay doi)
