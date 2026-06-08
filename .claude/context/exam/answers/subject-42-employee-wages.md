# Subject No. 42 — Part-time Employee — Module "Calculate this week's wages"

> **Domain:** Part-time Employee Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Scenario — Tinh luong tuan

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang co nhieu nhan vien tinh luong theo gio. Moi ngay lam viec co 2 ca: Ca 1 tu 8h-16h, Ca 2 tu 16h-0h. Luong NV duoc tinh dua tren so gio lam viec thuc te va tra theo tuan. Neu NV lam tren 8h/ngay, luong gio tang ca duoc tinh. Neu NV di tre hoac ve som, thoi gian vang mat bi tru va bi phat. Staff chon chuc nang tinh luong → nhap khoang thoi gian → he thong hien thi bang luong tong hop → staff click vao NV de xem chi tiet.

### Scenario chinh — Tinh luong tuan

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | He thong kiem tra dang nhap thanh cong. Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chon chuc nang **Calculate wages**. |
| 5 | Giao dien tinh luong xuat hien voi o nhap ngay bat dau tuan va ngay ket thuc tuan. |
| 6 | Staff nhap ngay bat dau `09/06/2026` va ngay ket thuc `15/06/2026`. |
| 7 | Staff nhan nut **View** (hoac Search). |
| 8 | He thong tinh toan luong cho tat ca NV trong tuan do. Giao dien hien thi bang luong tong hop: moi NV 1 dong, sap xep theo ten AZ: ma NV, ten, SĐT, tong gio lam ca, tong tien ca, tong gio tang ca, tong tien tang ca, tong gio di tre/ve som, tong phat, tong thuc nhan. |
| 9 | Bang hien thi: "E001 - Nguyen Van A - 0911111111 - 32h - 640,000 - 4h - 120,000 - 1h - 30,000 - 730,000", "E002 - Tran Thi B - 0922222222 - 24h - 480,000 - 0h - 0 - 0h - 0 - 480,000", "E003 - Le Van C - 0933333333 - 40h - 800,000 - 8h - 240,000 - 2h - 60,000 - 980,000". |
| 10 | Staff click vao dong cua NV "Nguyen Van A". |
| 11 | Giao dien hien thi bang chi tiet lam viec cua NV "Nguyen Van A" trong tuan: moi dong 1 ca lam viec: ngay, thu, ca, gio checkin, gio checkout, so gio trong ca, tien ca, so gio tang ca, tien tang ca, so gio di tre/ve som, phat, tong tien cho ca do. |
| 12 | Bang chi tiet: "09/06 - Thu 2 - Ca 1 - 08:05 - 16:02 - 8h - 160,000 - 0h - 0 - 5 phut tre - 0 - 160,000", "11/06 - Thu 4 - Ca 2 - 16:00 - 00:15 - 8h - 160,000 - 15 phut - 0 - 0 - 0 - 160,000", "13/06 - Thu 6 - Ca 1 - 07:55 - 17:00 - 8h - 160,000 - 1h - 30,000 - 0 - 0 - 190,000", "14/06 - Thu 7 - Ca 2 - 16:10 - 00:05 - 8h - 160,000 - 5 phut - 0 - 10 phut tre - 30,000 - 130,000". |

### Kich ban ngoai le

- **EL1:** NV khong co lich lam viec trong tuan do → Dong hien thi tat ca = 0.
- **EL2:** NV chua checkin/checkout → Khong tinh duoc gio lam, hien thi "Chua cham cong" cho ca do.
- **EL3:** Ngay bat dau > ngay ket thuc → He thong thong bao "Khoang thoi gian khong hop le".
- **EL4:** Staff nhap ngay khong phai dau tuan (khong phai Thu 2) → He thong tu dong chinh ve Thu 2 cua tuan do.

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang (ten, dia chi, mo ta) co nhieu nhan vien (ma NV, ten, SĐT, email, ngay sinh, ngay ky hop dong). Moi ngay lam viec co 2 ca (ngay, so ca, mo ta). NV dang ky ca, quan ly xep lich, NV checkin/checkout. Luong duoc tinh dua tren so gio lam viec thuc te trong tuan. Moi ban ghi luong (tuan bat dau, tuan ket thuc, tong gio ca, tong tien ca, tong gio tang ca, tong tien tang ca, tong gio tre, tong phat, tong thuc nhan). Chi tiet luong moi ca (ngay, ca, gio checkin, checkout, gio ca, tien ca, gio tang ca, tien tang ca, gio tre, phat, tong ca). Nguoi dung (ten dang nhap, mat khau, vai tro) la nhan vien thao tac.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Restaurant | Entity class | Chi nhanh quan ly nhan vien |
| Employee | Entity class | Nhan vien ban thoi gian |
| Shift | Entity class | Ca lam viec cu the |
| Schedule | Entity class | Ban ghi xep lich |
| Attendance | Entity class | Ban ghi cham cong |
| Wage | Entity class | Ban ghi luong tuan cua NV |
| WageDetail | Entity class | Chi tiet luong theo ca lam viec |
| User | Entity class | Nhan vien thao tac |
| Tong gio ca, tien ca | Thuoc tinh | Thuoc tinh cua Wage |
| Gio checkin, checkout | Thuoc tinh | Thuoc tinh cua WageDetail (lay tu Attendance) |
| Tong gio tang ca, tien tang ca | Thuoc tinh | Thuoc tinh cua Wage va WageDetail |
| Tong gio tre, tong phat | Thuoc tinh | Thuoc tinh cua Wage va WageDetail |
| Tong thuc nhan | Thuoc tinh | Tinh toan tu cac thuoc tinh khac |

### Buoc 3: Xac dinh quan he

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu NV |
| Employee → Wage | 1-n | Mot NV co nhieu ban ghi luong (nhieu tuan) |
| Wage → WageDetail | 1-n | Mot ban ghi luong co nhieu chi tiet (moi ca) |
| Employee → Schedule | 1-n | Mot NV co nhieu lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| Employee → Attendance | 1-n | Mot NV co nhieu ban ghi cham cong |
| Shift → Attendance | 1-n | Mot ca co nhieu ban ghi cham cong |
| User → Wage | 1-n | Mot staff tao nhieu ban ghi luong |

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
|                  |        |      Wage        |
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -weekStartDate:  |
|                  |        |   Date           |
|                  |        | -weekEndDate:    |
|                  |        |   Date           |
|                  |        | -totalShiftHours:|
|                  |        |   float          |
|                  |        | -totalShiftMoney:|
|                  |        |   float          |
|                  |        | -totalOvertime   |
|                  |        |   Hours: float   |
|                  |        | -totalOvertime   |
|                  |        |   Money: float   |
|                  |        | -totalLateHours: |
|                  |        |   float          |
|                  |        | -totalFines:     |
|                  |        |   float          |
|                  |        | -totalReceived:  |
|                  |        |   float          |
|                  |        +------------------+
|                  |                | 1
|                  |                |
|                  |                | n
|                  |                v
|                  |        +------------------+
|                  |        |   WageDetail     |
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -day: String     |
|                  |        | -date: Date      |
|                  |        | -shiftNumber: int|
|                  |        | -checkinTime:    |
|                  |        |   DateTime       |
|                  |        | -checkoutTime:   |
|                  |        |   DateTime       |
|                  |        | -shiftHours:     |
|                  |        |   float          |
|                  |        | -shiftMoney:     |
|                  |        |   float          |
|                  |        | -overtimeHours:  |
|                  |        |   float          |
|                  |        | -overtimeMoney:  |
|                  |        |   float          |
|                  |        | -lateHours:      |
|                  |        |   float          |
|                  |        | -fines: float    |
|                  |        | -totalShift:     |
|                  |        |   float          |
|                  |        +------------------+

+------------------+       +------------------+
|      Shift       |       |    Attendance    |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -date: Date      |       | -checkinTime:    |
| -shiftNumber: int|       |   DateTime       |
| -description:    |       | -checkoutTime:   |
|   String         |       |   DateTime       |
| -startDate: Date |       +------------------+
| -endDate: Date   |       +------------------+
+------------------+

+------------------+       +------------------+
|     Schedule     |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -weekStartDate:  |       | -username: String|
|   Date           |       | -password: String|
| -assignedTime:   |       | -role: String    |
|   DateTime       |       +------------------+
+------------------+       | +checkLogin()    |
                           +------------------+
```

### Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu NV |
| Employee → Wage | 1-n (Composition) | Mot NV co nhieu ban ghi luong |
| Wage → WageDetail | 1-n (Composition) | Mot ban ghi luong co nhieu chi tiet |
| Employee → Schedule | 1-n | Mot NV co nhieu lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| Employee → Attendance | 1-n | Mot NV co nhieu cham cong |
| Shift → Attendance | 1-n | Mot ca co nhieu cham cong |
| User → Wage | 1-n | Mot staff tao nhieu luong |

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| WageFrm | Giao dien tinh luong tuan (chinh) |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subRegister`: nut chon Register
- `subSchedule`: nut chon Schedule
- `subCheckin`: nut chon Checkin/Checkout
- `subWages`: nut chon Wages
- `subStatistics`: nut chon Statistics

**WageFrm:**
- `inStartDate`: o nhap ngay bat dau tuan
- `inEndDate`: o nhap ngay ket thuc tuan
- `subView`: nut View/Search
- `outsubListWage`: bang luong tong hop (click duoc) — cot: ma NV, ten, SĐT, tong gio ca, tong tien ca, tong gio tang ca, tong tien tang ca, tong gio tre, tong phat, tong thuc nhan
- `outListWageDetail`: bang chi tiet luong (hien thi khi click vao NV) — cot: ngay, thu, ca, gio checkin, gio checkout, gio ca, tien ca, gio tang ca, tien tang ca, gio tre, phat, tong ca

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| WageDAO | `calculateWages(startDate, endDate): List<Wage>` | Tinh luong cho tat ca NV trong tuan |
| WageDAO | `getWageDetail(employeeId, startDate, endDate): List<WageDetail>` | Lay chi tiet luong cua NV trong tuan |
| AttendanceDAO | `getAttendance(employeeId, startDate, endDate): List<Attendance>` | Lay ban ghi cham cong cua NV trong tuan |
| ScheduleDAO | `getSchedule(employeeId, startDate, endDate): List<Schedule>` | Lay lich lam viec cua NV trong tuan |

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

**Attendance:**
- `id: int` (PK)
- `checkinTime: DateTime`
- `checkoutTime: DateTime`
- `employee: Employee` (object attribute)
- `shift: Shift` (object attribute)

**Wage:**
- `id: int` (PK)
- `weekStartDate: Date`
- `weekEndDate: Date`
- `totalShiftHours: float`
- `totalShiftMoney: float`
- `totalOvertimeHours: float`
- `totalOvertimeMoney: float`
- `totalLateHours: float`
- `totalFines: float`
- `totalReceived: float`
- `employee: Employee` (object attribute)
- `wageDetails: List<WageDetail>` (object attribute)

**WageDetail:**
- `id: int` (PK)
- `day: String`
- `date: Date`
- `shiftNumber: int`
- `checkinTime: DateTime`
- `checkoutTime: DateTime`
- `shiftHours: float`
- `shiftMoney: float`
- `overtimeHours: float`
- `overtimeMoney: float`
- `lateHours: float`
- `fines: float`
- `totalShift: float`
- `wage: Wage` (object attribute)
- `shift: Shift` (object attribute)
- `attendance: Attendance` (object attribute)

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

**tblWage:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| weekStartDate | date | |
| weekEndDate | date | |
| totalShiftHours | float | |
| totalShiftMoney | float | |
| totalOvertimeHours | float | |
| totalOvertimeMoney | float | |
| totalLateHours | float | |
| totalFines | float | |
| totalReceived | float | |
| employeeID | int | FK → tblEmployee.ID |
| userID | int | FK → tblUser.ID |

**tblWageDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| day | varchar | |
| date | date | |
| shiftNumber | int | |
| checkinTime | datetime | |
| checkoutTime | datetime | |
| shiftHours | float | |
| shiftMoney | float | |
| overtimeHours | float | |
| overtimeMoney | float | |
| lateHours | float | |
| fines | float | |
| totalShift | float | |
| wageID | int | FK → tblWage.ID |
| shiftID | int | FK → tblShift.ID |
| attendanceID | int | FK → tblAttendance.ID |

### Buoc 6: Mo ta cach ve Class Diagram (Design phase) bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Class Diagram** trong danh muc Diagrams.
2. Tao cac class:

**Vei View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `WageFrm`:
  - Ngan 1 (ten): `<<boundary>> WageFrm`
  - Ngan 2 (thuoc tinh): `-inStartDate: JTextField`, `-inEndDate: JTextField`, `-subView: JButton`, `-outsubListWage: JTable`, `-outListWageDetail: JTable`
  - Ngan 3 (phuong thuc): de trong
- Tuong tu cho `LoginFrm`, `HomeFrm`.

**Vei DAO classes:**
- Ve hinh chu nhat 3 ngan cho `WageDAO`:
  - Ngan 1: `<<control>> WageDAO`
  - Ngan 2: de trong
  - Ngan 3: `+calculateWages(startDate: Date, endDate: Date): List<Wage>`, `+getWageDetail(employeeId: int, startDate: Date, endDate: Date): List<WageDetail>`
- Tuong tu cho `UserDAO`, `AttendanceDAO`, `ScheduleDAO`.

**Vei Entity classes:**
- Ve hinh chu nhat 3 ngan cho `Wage`:
  - Ngan 1: `<<entity>> Wage`
  - Ngan 2: `-id: int`, `-weekStartDate: Date`, `-weekEndDate: Date`, `-totalShiftHours: float`, `-totalShiftMoney: float`, `-totalOvertimeHours: float`, `-totalOvertimeMoney: float`, `-totalLateHours: float`, `-totalFines: float`, `-totalReceived: float`, `-employee: Employee`, `-wageDetails: List<WageDetail>`
  - Ngan 3: `+calculateWages(): List<Wage>`, `+getWageDetail(): List<WageDetail>`

**Vei cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| WageFrm | WageDAO | Duong lien net, mui ten tam giac rong | Dependency | WageFrm su dung WageDAO |
| Wage | WageDetail | Duong lien net, dau kim cuong filled | Composition 1-n | WageDetail khong ton tai neu khong co Wage |
| Employee | Wage | Duong lien net, dau kim cuong rong | Aggregation 1-n | NV co nhieu ban ghi luong |
| WageDetail | Shift | Duong lien net, mui ten tam giac rong | Association | WageDetail tham chieu Shift |
| WageDetail | Attendance | Duong lien net, mui ten tam giac rong | Association | WageDetail tham chieu Attendance |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `WageFrm`
   - Control: `UserDAO`, `WageDAO`
   - Entity: `Wage`, `WageDetail`

3. Ve cac message (muiten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Tinh luong tuan (Scenario version 3)

```
Staff          LoginFrm      UserDAO     HomeFrm     WageFrm        WageDAO
  |               |             |           |               |               |
  |---login------>|             |           |               |               |
  |               |---checkLogin>|          |               |               |
  |               |             |--query DB |               |               |
  |               |             |<-return---|               |               |
  |               |<--true------|           |               |               |
  |               |---open------|---------->|               |               |
  |               |             |           |               |               |
  |---select------|---------------------------->|            |               |
  |               |             |           |---open--------|-------------->|
  |               |             |           |               |               |
  |---enter dates->|            |           |               |               |
  |               |             |           |               |               |
  |---click View-->|            |           |               |               |
  |               |             |           |               |               |
  |               |             |           |               |--calculateWages|
  |               |             |           |               |  (09/06, 15/06)|
  |               |             |           |               |               |
  |               |             |           |               |  --getSchedule |
  |               |             |           |               |  --getAttendance
  |               |             |           |               |  --calculate   |
  |               |             |           |               |               |
  |               |             |           |               |  --query DB    |
  |               |             |           |               |  <-return------|
  |               |             |           |               |               |
  |               |             |           |               |<--List<Wage>---|
  |               |             |           |               |               |
  |               |             |           |               |--display table|
  |               |             |           |               |               |
  |---click NV----|             |           |               |               |
  |               |             |           |               |               |
  |               |             |           |               |--getWageDetail|
  |               |             |           |               |  (empId, dates)|
  |               |             |           |               |               |
  |               |             |           |               |  --query DB    |
  |               |             |           |               |  <-return------|
  |               |             |           |               |               |
  |               |             |           |               |<--List<Detail>-|
  |               |             |           |               |               |
  |               |             |           |               |--display      |
  |               |             |           |               |  detail table |
  |<--result------|             |           |               |               |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Wages | Staff | HomeFrm | Staff chon chuc nang Wages |
| 7 | open | HomeFrm | WageFrm | Mo giao dien tinh luong |
| 8 | enter dates | Staff | WageFrm | Staff nhap ngay bat dau "09/06/2026" va ngay ket thuc "15/06/2026" |
| 9 | click View | Staff | WageFrm | Staff nhan nut View |
| 10 | calculateWages() | WageFrm | WageDAO | Goi WageDAO.calculateWages("09/06/2026", "15/06/2026") |
| 11 | getSchedule() | WageDAO | Database | Truy van tblSchedule JOIN tblShift WHERE date BETWEEN startDate AND endDate |
| 12 | getAttendance() | WageDAO | Database | Truy van tblAttendance WHERE date BETWEEN startDate AND endDate |
| 13 | calculate | WageDAO | WageDAO | Tinh toan: gio ca = checkout - checkin (max 8h), gio tang ca = max(0, gio ca - 8h), gio tre = tre/ve som, phat = gio tre * don gia phat |
| 14 | query DB | WageDAO | Database | Truy van du lieu tu tblSchedule, tblAttendance, tblShift |
| 15 | return List<Wage> | WageDAO | WageFrm | Tra ve danh sach luong cua tat ca NV |
| 16 | display table | WageFrm | UI | Hien thi bang luong tong hop: ma, ten, SĐT, tong gio, tien, tang ca, tre, phat, thuc nhan. Sap xep theo ten AZ |
| 17 | click NV "Nguyen Van A" | Staff | WageFrm | Staff click vao dong cua NV |
| 18 | getWageDetail() | WageFrm | WageDAO | Goi WageDAO.getWageDetail(employeeId, "09/06/2026", "15/06/2026") |
| 19 | query DB | WageDAO | Database | Truy van tblAttendance JOIN tblShift JOIN tblSchedule cho NV nay |
| 20 | return List<WageDetail> | WageDAO | WageFrm | Tra ve chi tiet luong theo ca |
| 21 | display detail table | WageFrm | UI | Hien thi chi tiet: ngay, thu, ca, checkin, checkout, gio ca, tien ca, tang ca, tien tang ca, tre, phat, tong |

---

## Cau 5: Blackbox Testcase

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Wages | Tinh luong thanh cong voi NV co du lieu cham cong day du |
| 2 | Wages | NV khong co lich lam viec trong tuan |
| 3 | Wages | NV chua checkin/checkout cho ca nao |
| 4 | Wages | NV lam tang ca (>8h/ngay) |
| 5 | Wages | NV di tre hoac ve som |
| 6 | Wages | Khoang thoi gian khong hop le (start > end) |
| 7 | Wages | Chi tiet luong khi click vao NV |

### Testcase chi tiet — TC01: Tinh luong thanh cong

**Muc dich:** Kiem tra chuc nang tinh luong hoat dong dung khi NV co du lieu cham cong day du trong tuan. Bang tong hop hien thi dung va chi tiet luong khi click vao NV chinh xac.

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
| 1 | 09/06/2026 | 1 | Thu 2 Ca 1 | 09/06/2026 | 15/06/2026 |
| 2 | 09/06/2026 | 2 | Thu 2 Ca 2 | 09/06/2026 | 15/06/2026 |
| 3 | 10/06/2026 | 1 | Thu 3 Ca 1 | 09/06/2026 | 15/06/2026 |
| 4 | 10/06/2026 | 2 | Thu 3 Ca 2 | 09/06/2026 | 15/06/2026 |
| 5 | 11/06/2026 | 1 | Thu 4 Ca 1 | 09/06/2026 | 15/06/2026 |
| 6 | 11/06/2026 | 2 | Thu 4 Ca 2 | 09/06/2026 | 15/06/2026 |
| 7 | 12/06/2026 | 1 | Thu 5 Ca 1 | 09/06/2026 | 15/06/2026 |
| 8 | 12/06/2026 | 2 | Thu 5 Ca 2 | 09/06/2026 | 15/06/2026 |
| 9 | 13/06/2026 | 1 | Thu 6 Ca 1 | 09/06/2026 | 15/06/2026 |
| 10 | 13/06/2026 | 2 | Thu 6 Ca 2 | 09/06/2026 | 15/06/2026 |
| 11 | 14/06/2026 | 1 | Thu 7 Ca 1 | 09/06/2026 | 15/06/2026 |
| 12 | 14/06/2026 | 2 | Thu 7 Ca 2 | 09/06/2026 | 15/06/2026 |
| 13 | 15/06/2026 | 1 | CN Ca 1 | 09/06/2026 | 15/06/2026 |
| 14 | 15/06/2026 | 2 | CN Ca 2 | 09/06/2026 | 15/06/2026 |

**tblSchedule:**
| ID | weekStartDate | assignedTime | employeeID | shiftID | userID |
|----|--------------|-------------|------------|---------|--------|
| 1 | 09/06/2026 | 08/06/2026 14:00 | 1 | 1 | 1 |
| 2 | 09/06/2026 | 08/06/2026 14:00 | 1 | 6 | 1 |
| 3 | 09/06/2026 | 08/06/2026 14:00 | 1 | 9 | 1 |
| 4 | 09/06/2026 | 08/06/2026 14:00 | 1 | 12 | 1 |
| 5 | 09/06/2026 | 08/06/2026 14:00 | 2 | 2 | 1 |
| 6 | 09/06/2026 | 08/06/2026 14:00 | 2 | 5 | 1 |
| 7 | 09/06/2026 | 08/06/2026 14:00 | 2 | 8 | 1 |
| 8 | 09/06/2026 | 08/06/2026 14:00 | 3 | 1 | 1 |
| 9 | 09/06/2026 | 08/06/2026 14:00 | 3 | 3 | 1 |
| 10 | 09/06/2026 | 08/06/2026 14:00 | 3 | 7 | 1 |
| 11 | 09/06/2026 | 08/06/2026 14:00 | 3 | 11 | 1 |
| 12 | 09/06/2026 | 08/06/2026 14:00 | 3 | 13 | 1 |

**tblAttendance:**
| ID | checkinTime | checkoutTime | employeeID | shiftID | scheduleID | userID |
|----|------------|-------------|------------|---------|------------|--------|
| 1 | 09/06/2026 08:05 | 09/06/2026 16:02 | 1 | 1 | 1 | 1 |
| 2 | 11/06/2026 16:00 | 12/06/2026 00:15 | 1 | 6 | 2 | 1 |
| 3 | 13/06/2026 07:55 | 13/06/2026 17:00 | 1 | 9 | 3 | 1 |
| 4 | 14/06/2026 16:10 | 15/06/2026 00:05 | 1 | 12 | 4 | 1 |
| 5 | 09/06/2026 16:00 | 10/06/2026 00:00 | 2 | 2 | 5 | 1 |
| 6 | 11/06/2026 08:00 | 11/06/2026 16:00 | 2 | 5 | 6 | 1 |
| 7 | 12/06/2026 16:00 | 13/06/2026 00:00 | 2 | 8 | 7 | 1 |
| 8 | 09/06/2026 08:00 | 09/06/2026 16:00 | 3 | 1 | 8 | 1 |
| 9 | 10/06/2026 08:00 | 10/06/2026 16:00 | 3 | 3 | 9 | 1 |
| 10 | 12/06/2026 08:00 | 12/06/2026 16:30 | 3 | 7 | 10 | 1 |
| 11 | 14/06/2026 08:00 | 14/06/2026 16:00 | 3 | 11 | 11 | 1 |
| 12 | 15/06/2026 08:00 | 15/06/2026 16:00 | 3 | 13 | 12 | 1 |

**tblWage:** (rong)
**tblWageDetail:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin/Checkout, Wages, Statistics |
| 3 | Chon chuc nang **Wages** | Giao dien tinh luong xuat hien voi o nhap ngay bat dau, ngay ket thuc, nut View |
| 4 | Nhap ngay bat dau `09/06/2026`, ngay ket thuc `15/06/2026` | O ngay bat dau hien thi "09/06/2026", o ngay ket thuc hien thi "15/06/2026" |
| 5 | Nhan nut **View** | He thong tinh toan luong cho tat ca NV. Bang luong tong hop hien thi 3 dong (sap xep theo ten): E001 Nguyen Van A (32h ca, 640,000, 1.25h tang ca, 37,500, 15 phut tre, 0 phat, 677,500), E002 Tran Thi B (24h ca, 480,000, 0h tang ca, 0, 0h tre, 0 phat, 480,000), E003 Le Van C (40h ca, 800,000, 0.5h tang ca, 15,000, 0h tre, 0 phat, 815,000) |
| 6 | Click vao dong cua NV "Nguyen Van A" | Bang chi tiet hien thi 4 dong: Thu 2 Ca 1 (08:05-16:02, 7.95h, 159,000, 0h tang ca, 0, 5 phut tre, 0 phat, 159,000), Thu 4 Ca 2 (16:00-00:15, 8h, 160,000, 15 phut, 0, 0h tre, 0 phat, 160,000), Thu 6 Ca 1 (07:55-17:00, 8h, 160,000, 1h tang ca, 30,000, 0h tre, 0 phat, 190,000), Thu 7 Ca 2 (16:10-00:05, 7.92h, 158,333, 5 phut, 0, 10 phut tre, 0 phat, 158,333) |
| 7 | Ket qua cuoi cung | Bang tong hop va chi tiet hien thi chinh xac, khong co loi |

### Database sau khi test

**tblWage:** (khong thay doi — day la tinh nang doc du lieu, khong luu vao DB)
**tblWageDetail:** (khong thay doi)
**tblUser:** (khong thay doi)
**tblRestaurant:** (khong thay doi)
**tblEmployee:** (khong thay doi)
**tblShift:** (khong thay doi)
**tblSchedule:** (khong thay doi)
**tblAttendance:** (khong thay doi)

> **Ghi chu:** Module "Calculate wages" la tinh nang doc du lieu (read-only). He thong tinh toan tren bo dua tren du lieu cham cong va lich, khong tao ban ghi moi trong database. Neu he thong co thiet ke luu ket qua tinh toan vao tblWage va tblWageDetail thi database se them cac ban ghi moi.
