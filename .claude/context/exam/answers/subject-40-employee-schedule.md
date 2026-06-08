# Subject No. 40 — Part-time Employee — Module "Schedule next week"

> **Domain:** Part-time Employee Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Scenario — Xep lich tuan sau

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang co nhieu nhan vien tinh luong theo gio. Moi ngay lam viec co 2 ca: Ca 1 tu 8h-16h, Ca 2 tu 16h-0h. Sau khi ky hop dong, nhan vien duoc dang ky thoi gian lam viec. Quan ly dua vao lich dang ky de xep lich tuan sau. Moi ca can du N nhan vien. Neu so NV dang ky > N, uu tien NV co tong gio lam viec it hon.

### Scenario chinh — Xep lich tuan sau

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | He thong kiem tra dang nhap thanh cong. Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chon chuc nang **Schedule next week**. |
| 5 | Giao dien xep lich xuat hien voi bang 7 dong (Thu 2 - Chu Nhat), moi dong 2 cot (Ca 1: 8h-16h, Ca 2: 16h-0h). Moi cot hien thi ten cac NV da dang ky cho ca do. VD: Thu 2 Ca 1 chua co NV, Thu 2 Ca 2 co NV "C" (da dang ky). |
| 6 | Staff nhan vao o "Thu 2, Ca 1". |
| 7 | Giao dien hien thi danh sach NV da dang ky ca nay nhung chua duoc xep: ten NV, SĐT, tong gio da xep tuan sau. Sap xep theo tong gio tang dan. Danh sach: "A - 0911111111 - 0h", "B - 0922222222 - 0h", "D - 0944444444 - 8h". |
| 8 | Staff chon NV "A" (tong gio = 0h), nhan nut **Select**. |
| 9 | Giao dien quay lai trang xep lich, cot "Thu 2, Ca 1" hien thi them ten NV "A". |
| 10 | Staff tiep tuc nhan vao o "Thu 2, Ca 2". |
| 11 | Giao dien hien thi DS dang ky cho Ca 2: "B - 0922222222 - 0h", "E - 0955555555 - 0h", "C - 0933333333 - 8h". |
| 12 | Staff chon NV "B" (tong gio = 0h), nhan nut **Select**. NV "B" duoc them vao cot "Thu 2, Ca 2". |
| 13 | Staff tiep tuc xep cac con lai: Thu 3 Ca 1 = "E" (0h), Thu 3 Ca 2 = "C" (8h), Thu 4 Ca 1 = "D" (8h), Thu 4 Ca 2 = "A" (8h), Thu 5 Ca 1 = "B" (8h), Thu 5 Ca 2 = "E" (8h), Thu 6 Ca 1 = "C" (16h), Thu 6 Ca 2 = "D" (16h), Thu 7 Ca 1 = "A" (16h), Thu 7 Ca 2 = "B" (16h), CN Ca 1 = "E" (16h), CN Ca 2 = "C" (24h). |
| 14 | Staff nhan nut **Save**. |
| 15 | He thong kiem tra moi ca co du N nhan vien chua. Neu hop le, he thong luu lich vao database. |
| 16 | He thong thong bao "Xep lich thanh cong". |

### Kich ban ngoai le

- **EL1:** Nhan vien dang ky cho ca da du N nguoi, nhung NV chon co tong gio cao hon NV hien tai → He thong thong bao "Ca nay da du N nhan vien, khong the them".
- **EL2:** Khong co NV nao dang ky cho mot ca nao do → Cot do de trong, he thong canh bao "Ca X ngay Y chua co NV dang ky".
- **EL3:** Staff chua xep du tat ca cac ca da co NV dang ky → He thong canh bao "Con ca chua xep, ban co chac muon luu?".

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly nhan vien ban thoi gian cua chuoi nha hang. Moi nha hang (ten nha hang, dia chi, mo ta) co nhieu nhan vien (ma NV, ten, SĐT, email, ngay sinh, ngay ky hop dong). Moi ngay lam viec co 2 ca (ngay, so ca, mo ta, ngay bat dau tuan, ngay ket thuc tuan). Nhan vien dang ky ca lam viec (thoi gian dang ky, trang thai, mo ta). Quan ly xep lich dua tren dang ky. Moi ban ghi lich (nhan vien, ca, tuan) luu tru viec xep lich. Nguoi dung (ten dang nhap, mat khau, vai tro) la nhan vien thao tac tren he thong.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Restaurant | Entity class | Chi nhanh quan ly nhan vien |
| Employee | Entity class | Nhan vien ban thoi gian |
| Shift | Entity class | Ca lam viec cu the (ngay + so ca) |
| RegistrationShift | Entity class | Ban ghi dang ky ca cua NV |
| Schedule | Entity class | Ban ghi xep lich cua NV vao ca |
| User | Entity class | Nhan vien thao tac tren he thong |
| Ten nha hang, dia chi | Thuoc tinh | Thuoc tinh cua Restaurant |
| Ma NV, ten, SĐT | Thuoc tinh | Thuoc tinh cua Employee |
| Ngay, so ca | Thuoc tinh | Thuoc tinh cua Shift |
| Thoi gian dang ky, trang thai | Thuoc tinh | Thuoc tinh cua RegistrationShift |
| Tong gio xep | Thuoc tinh | Thuoc tinh cua Schedule |
| Ten dang nhap, mat khau | Thuoc tinh | Thuoc tinh cua User |

### Buoc 3: Xac dinh quan he

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu nhan vien |
| Employee → RegistrationShift | 1-n | Mot NV co nhieu dang ky ca |
| Shift → RegistrationShift | 1-n | Mot ca co nhieu NV dang ky |
| Employee → Schedule | 1-n | Mot NV co nhieu ban ghi lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| User → Schedule | 1-n | Mot staff tao nhieu ban ghi lich |
| Employee → Shift | n-n (qua RegistrationShift va Schedule) | NV lien ket voi nhieu ca |

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
+------------------+        +------------------+
|                  |                | 1
|                  |                |
|                  |                | n
|                  |                v
|                  |        +------------------+
|                  |        | RegistrationShift|
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -registeredTime: |
|                  |        |   DateTime       |
|                  |        | -status: String  |
|                  |        | -description:    |
|                  |        |   String         |
|                  |        +------------------+

+------------------+       +------------------+
|      Shift       |       |     Schedule     |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -date: Date      |       | -weekStartDate:  |
| -shiftNumber: int|       |   Date           |
| -description:    |       | -assignedTime:   |
|   String         |       |   DateTime       |
| -startDate: Date |       +------------------+
| -endDate: Date   |       | +addSchedule()   |
+------------------+       | +getSchedule()   |
         | 1               +------------------+
         |                          | 1
         | n                        |
         v                          | n
     (Schedule) <-------------------+

+------------------+
|      User        |
+------------------+
| -id: int         |
| -username: String|
| -password: String|
| -role: String    |
+------------------+
| +checkLogin()    |
+------------------+
```

### Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Restaurant → Employee | 1-n | Mot nha hang co nhieu NV |
| Employee → RegistrationShift | 1-n | Mot NV co nhieu dang ky |
| Shift → RegistrationShift | 1-n | Mot ca co nhieu NV dang ky |
| Employee → Schedule | 1-n | Mot NV co nhieu ban ghi lich |
| Shift → Schedule | 1-n | Mot ca co nhieu NV duoc xep |
| User → Schedule | 1-n | Mot staff tao nhieu lich |
| Employee → Shift | n-n (qua Schedule) | NV duoc xep nhieu ca |

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| ScheduleFrm | Giao dien xep lich tuan sau (chinh) |
| SelectEmployeeFrm | Giao dien chon NV cho ca (popup con) |

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

**ScheduleFrm:**
- `outScheduleTable`: bang 7 dong x 2 cot hien thi ten NV da duoc xep
- `subSave`: nut Save

**SelectEmployeeFrm:**
- `outsubListRegistered`: bang DS NV dang ky (click duoc) — cot: ten NV, SĐT, tong gio da xep
- `subSelect`: nut Select

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| ScheduleDAO | `getScheduleByWeek(weekStartDate): List<Schedule>` | Lay lich xep cua tuan |
| RegistrationShiftDAO | `getRegisteredEmployees(shiftId): List<Employee>` | Lay DS NV dang ky cho ca |
| ScheduleDAO | `getTotalScheduledHours(employeeId, weekStartDate): int` | Tong gio da xep cua NV trong tuan |
| ScheduleDAO | `addSchedule(schedule): boolean` | Luu ban ghi lich moi |
| ScheduleDAO | `deleteSchedule(scheduleId): boolean` | Xoa ban ghi lich |

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

**RegistrationShift:**
- `id: int` (PK)
- `registeredTime: DateTime`
- `status: String`
- `description: String`
- `employee: Employee` (object attribute)
- `shift: Shift` (object attribute)
- `user: User` (object attribute)

**Schedule:**
- `id: int` (PK)
- `weekStartDate: Date`
- `assignedTime: DateTime`
- `employee: Employee` (object attribute)
- `shift: Shift` (object attribute)
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

**tblRegistrationShift:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| registeredTime | datetime | |
| status | varchar | |
| description | varchar | |
| employeeID | int | FK → tblEmployee.ID |
| shiftID | int | FK → tblShift.ID |
| userID | int | FK → tblUser.ID |

**tblSchedule:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| weekStartDate | date | |
| assignedTime | datetime | |
| employeeID | int | FK → tblEmployee.ID |
| shiftID | int | FK → tblShift.ID |
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
- Tuong tu cho `HomeFrm`, `ScheduleFrm`, `SelectEmployeeFrm`.

**Vei DAO classes:**
- Ve hinh chu nhat 3 ngan cho `UserDAO`:
  - Ngan 1: `<<control>> UserDAO`
  - Ngan 2: de trong
  - Ngan 3: `+checkLogin(username: String, password: String): boolean`
- Tuong tu cho `ScheduleDAO`, `RegistrationShiftDAO`.

**Vei Entity classes:**
- Ve hinh chu nhat 3 ngan cho `Schedule`:
  - Ngan 1: `<<entity>> Schedule`
  - Ngan 2: `-id: int`, `-weekStartDate: Date`, `-assignedTime: DateTime`, `-employee: Employee`, `-shift: Shift`, `-user: User`
  - Ngan 3: `+addSchedule(): boolean`, `+getScheduleByWeek(weekStartDate: Date): List<Schedule>`

**Vei cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| LoginFrm | UserDAO | Duong lien net, mui ten tam giac rong | Dependency | LoginFrm su dung UserDAO |
| ScheduleFrm | ScheduleDAO | Duong lien net, mui ten tam giac rong | Dependency | ScheduleFrm su dung ScheduleDAO |
| ScheduleFrm | RegistrationShiftDAO | Duong lien net, mui ten tam giac rong | Dependency | ScheduleFrm su dung RegistrationShiftDAO |
| SelectEmployeeFrm | RegistrationShiftDAO | Duong lien net, mui ten tam giac rong | Dependency | SelectEmployeeFrm su dung RegistrationShiftDAO |
| Restaurant | Employee | Duong lien net, dau kim cuong rong | Aggregation 1-n | Nha hang co nhieu NV |
| Employee | RegistrationShift | Duong lien net, dau kim cuong rong | Aggregation 1-n | NV co nhieu dang ky |
| Shift | RegistrationShift | Duong lien net, dau kim cuong rong | Aggregation 1-n | Ca co nhieu dang ky |
| Employee | Schedule | Duong lien net, dau kim cuong rong | Aggregation 1-n | NV co nhieu lich |
| Shift | Schedule | Duong lien net, dau kim cuong rong | Aggregation 1-n | Ca co nhieu lich |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `ScheduleFrm`, `SelectEmployeeFrm`
   - Control: `UserDAO`, `ScheduleDAO`, `RegistrationShiftDAO`

3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Xep lich tuan sau (Scenario version 3)

```
Staff          LoginFrm      UserDAO     HomeFrm     ScheduleFrm    SelectEmployeeFrm  ScheduleDAO   RegistrationShiftDAO
  |               |             |           |               |                |               |               |
  |---login------>|             |           |               |                |               |               |
  |               |---checkLogin>|          |               |                |               |               |
  |               |             |--query DB |               |                |               |               |
  |               |             |<-return---|               |                |               |               |
  |               |<--true------|           |               |                |               |               |
  |               |---open------|---------->|               |                |               |               |
  |               |             |           |               |                |               |               |
  |---select------|---------------------------->|            |                |               |               |
  |               |             |           |---open--------|--------------->|               |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |--getScheduleByWeek------------>|               |
  |               |             |           |               |                |               |--query DB    |
  |               |             |           |               |                |               |<-return------|
  |               |             |           |               |<--List<Schedule>|               |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |--display 7x2   |               |               |
  |               |             |           |               |  table         |               |               |
  |               |             |           |               |                |               |               |
  |---click ca----|             |           |               |                |               |               |
  |               |             |           |               |--getRegisteredEmployees------->|               |
  |               |             |           |               |                |               |--query DB    |
  |               |             |           |               |                |               |<-return------|
  |               |             |           |               |<--List<Employee>|              |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |--getTotalScheduledHours------->|               |
  |               |             |           |               |                |               |--query DB    |
  |               |             |           |               |                |               |<-return------|
  |               |             |           |               |<--int hours----|               |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |--open----------|-------------->|               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |                |--display list |               |
  |               |             |           |               |                |               |               |
  |---select NV-->|             |           |               |                |               |               |
  |               |             |           |               |<--selected NV--|               |               |
  |               |             |           |               |--update table  |               |               |
  |               |             |           |               |                |               |               |
  |(lap lai cho cac ca khac)    |           |               |                |               |               |
  |               |             |           |               |                |               |               |
  |---save------->|             |           |               |                |               |               |
  |               |             |           |               |--addSchedule----------------->|               |
  |               |             |           |               |                |               |--INSERT DB   |
  |               |             |           |               |                |               |<-return true--|
  |               |             |           |               |<--true---------|               |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |(lap cho moi ban ghi lich)     |               |
  |               |             |           |               |                |               |               |
  |               |             |           |               |--show success  |               |               |
  |<--success-----|             |           |               |                |               |               |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Schedule | Staff | HomeFrm | Staff chon chuc nang Schedule |
| 7 | open | HomeFrm | ScheduleFrm | Mo giao dien xep lich |
| 8 | getScheduleByWeek() | ScheduleFrm | ScheduleDAO | Goi ScheduleDAO.getScheduleByWeek("09/06/2026") |
| 9 | query DB | ScheduleDAO | Database | Truy van tblSchedule JOIN tblEmployee JOIN tblShift |
| 10 | return List<Schedule> | ScheduleDAO | ScheduleFrm | Tra ve danh sach lich da xep |
| 11 | display 7x2 table | ScheduleFrm | UI | Hien thi bang 7x2 voi ten NV da xep |
| 12 | click "Thu 2, Ca 1" | Staff | ScheduleFrm | Staff nhan vao ca can xep |
| 13 | getRegisteredEmployees() | ScheduleFrm | RegistrationShiftDAO | Goi RegistrationShiftDAO.getRegisteredEmployees(shiftId) |
| 14 | query DB | RegistrationShiftDAO | Database | Truy van tblRegistrationShift JOIN tblEmployee |
| 15 | return List<Employee> | RegistrationShiftDAO | ScheduleFrm | Tra ve DS NV dang ky |
| 16 | getTotalScheduledHours() | ScheduleFrm | ScheduleDAO | Goi cho tung NV de lay tong gio |
| 17 | query DB | ScheduleDAO | Database | Truy van tblSchedule dem tong gio |
| 18 | return int | ScheduleDAO | ScheduleFrm | Tra ve tong gio da xep |
| 19 | open SelectEmployeeFrm | ScheduleFrm | SelectEmployeeFrm | Mo popup chon NV |
| 20 | display list | SelectEmployeeFrm | UI | Hien thi DS NV sap xep theo tong gio tang dan |
| 21 | select NV "A" | Staff | SelectEmployeeFrm | Staff chon NV co tong gio it nhat |
| 22 | return selected NV | SelectEmployeeFrm | ScheduleFrm | Tra ve NV da chon |
| 23 | update table | ScheduleFrm | UI | Cap nhat bang 7x2, them NV vao cot tuong ung |
| 24 | (loop) | Staff | ScheduleFrm | Lap lai cho cac ca khac |
| 25 | save | Staff | ScheduleFrm | Staff nhan nut Save |
| 26 | addSchedule() | ScheduleFrm | ScheduleDAO | Goi ScheduleDAO.addSchedule(schedule) cho moi ban ghi |
| 27 | INSERT DB | ScheduleDAO | Database | INSERT INTO tblSchedule |
| 28 | return true | ScheduleDAO | ScheduleFrm | Tra ve true |
| 29 | show success | ScheduleFrm | UI | Hien thi "Xep lich thanh cong" |
| 30 | return | ScheduleFrm | HomeFrm | Quay ve giao dien Home |

---

## Cau 5: Blackbox Testcase

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Schedule | Xep lich thanh cong voi NV da dang ky, chon NV co tong gio it nhat |
| 2 | Schedule | Khong co NV dang ky cho ca nao do |
| 3 | Schedule | NV da duoc xep vao ca roi, khong hien thi trong DS chon |
| 4 | Schedule | Staff chon NV co tong gio lon hon NV khac trong DS |
| 5 | Schedule | Luu lich khi chua xep du tat ca cac ca |

### Testcase chi tiet — TC01: Xep lich thanh cong

**Muc dich:** Kiem tra chuc nang xep lich hoat dong dung khi NV da dang ky, staff chon NV co tong gio it nhat, va luu thanh cong.

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
| 4 | E004 | Pham Thi D | 0944444444 | d@gmail.com | 04/04/2000 | 01/01/2026 | 1 |
| 5 | E005 | Hoang Van E | 0955555555 | e@gmail.com | 05/05/2000 | 01/01/2026 | 1 |

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

**tblRegistrationShift:**
| ID | registeredTime | status | description | employeeID | shiftID | userID |
|----|---------------|--------|-------------|------------|---------|--------|
| 1 | 01/06/2026 10:00 | registered | | 1 | 1 | 1 |
| 2 | 01/06/2026 10:00 | registered | | 2 | 1 | 1 |
| 3 | 01/06/2026 10:00 | registered | | 3 | 1 | 1 |
| 4 | 01/06/2026 10:00 | registered | | 2 | 2 | 1 |
| 5 | 01/06/2026 10:00 | registered | | 4 | 2 | 1 |
| 6 | 01/06/2026 10:00 | registered | | 5 | 2 | 1 |

**tblSchedule:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Register, Schedule, Checkin/Checkout, Wages, Statistics |
| 3 | Chon chuc nang **Schedule** | Giao dien xep lich xuat hien voi bang 7 dong (Thu 2-CN), moi dong 2 cot (Ca 1, Ca 2). Tat ca cac cot de trong (chua co lich) |
| 4 | Nhan vao o "Thu 2, Ca 1" | Giao dien hien thi danh sach NV dang ky: Nguyen Van A (0911111111 - 0h), Tran Thi B (0922222222 - 0h), Le Van C (0933333333 - 0h). Sap xep theo tong gio tang dan (ca 3 deu co 0h) |
| 5 | Chon NV "Nguyen Van A" (tong gio = 0h), nhan nut **Select** | Giao dien quay lai bang xep lich, cot "Thu 2, Ca 1" hien thi ten "Nguyen Van A" |
| 6 | Nhan vao o "Thu 2, Ca 2" | Giao dien hien thi DS dang ky: Tran Thi B (0922222222 - 0h), Pham Thi D (0944444444 - 0h), Hoang Van E (0955555555 - 0h) |
| 7 | Chon NV "Tran Thi B" (tong gio = 0h), nhan nut **Select** | Cot "Thu 2, Ca 2" hien thi ten "Tran Thi B" |
| 8 | Nhan nut **Save** | He thong thong bao "Xep lich thanh cong" |

### Database sau khi test

**tblSchedule:** (them 2 dong)
| ID | weekStartDate | assignedTime | employeeID | shiftID | userID |
|----|--------------|-------------|------------|---------|--------|
| 1 | 09/06/2026 | 08/06/2026 14:00 | 1 | 1 | 1 |
| 2 | 09/06/2026 | 08/06/2026 14:00 | 2 | 2 | 1 |

**tblUser:** (khong thay doi)
**tblRestaurant:** (khong thay doi)
**tblEmployee:** (khong thay doi)
**tblShift:** (khong thay doi)
**tblRegistrationShift:** (khong thay doi)
