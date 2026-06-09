# Subject No. 39 — Part-time Employee — Module "Register for next week"

> **Domain:** Part-time Employee Management
> **Entity classes:** Restaurant, Employee, Shift, RegistrationShift, User
> **Ghi chú:** Đây là domain chính của project. Entity classes và flow giống với Module 1 trong report.

---

## Câu 1: Scenario — Đăng ký ca làm tuần sau

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống bằng tài khoản `staff01`, password `******`. Giao diện Login xuất hiện. |
| 2 | Staff nhấn nút **Login**. Hệ thống xác thực thành công. Giao diện Home xuất hiện với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 3 | Staff chọn chức năng **Register for next week**. |
| 4 | Giao diện tìm kiếm nhân viên xuất hiện: ô nhập tên nhân viên (`txtKeyword`), nút **Search** (`btnSearch`). |
| 5 | Staff nhập "Nguyen" vào ô tìm kiếm và nhấn **Search**. |
| 6 | Hệ thống truy vấn danh sách nhân viên từ tblEmployee theo tên. Hiển thị bảng danh sách: mã NV, tên NV, SĐT. Dữ liệu ví dụ: B23DCCE060 (Nguyen Van A, 0912345678), B23DCCE071 (Nguyen Thi B, 0923456789). |
| 7 | Staff nhấn chọn nhân viên "B23DCCE060 - Nguyen Van A" trong bảng. |
| 8 | Giao diện đăng ký ca xuất hiện: thông tin nhân viên (mã, tên, SĐT, nhà hàng) + bảng đăng ký ca 7 dòng (Thứ 2 đến Chủ nhật), mỗi dòng có 2 checkbox (Ca 1: 8h-16h, Ca 2: 16h-0h). Tất cả checkbox trống (chưa đăng ký). |
| 9 | Staff tick chọn các ca: Thứ 2 Ca 1, Thứ 4 Ca 2, Thứ 6 Ca 1, Thứ 7 Ca 2 (tổng 4 ca). |
| 10 | Staff nhấn nút **Save** (`btnSave`). |
| 11 | Hệ thống kiểm tra: tổng số ca (4) >= ngưỡng tối thiểu (3 ca/tuần). Hợp lệ. |
| 12 | Hệ thống lưu 4 bản ghi RegistrationShift vào database. |
| 13 | Hệ thống thông báo "Dang ky thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Nhân viên không tồn tại trong hệ thống → bảng kết quả trống, thông báo "Khong tim thay nhan vien".
- **EL2:** Số ca chọn nhỏ hơn ngưỡng tối thiểu (ví dụ chỉ chọn 2 ca) → hệ thống cảnh báo "So ca chua dat toi thieu. Vui long chon them ca." Không lưu vào database.
- **EL3:** Nhân viên đã đăng ký ca trước đó → hệ thống hiển thị các checkbox đã tick sẵn từ dữ liệu cũ. Staff có thể sửa đổi và lưu lại.

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý nhân viên bán thời gian cho chuỗi nhà hàng. Mỗi nhà hàng (Restaurant) có nhiều nhân viên (Employee). Mỗi nhân viên có thể đăng ký nhiều ca làm việc (Shift) trong tuần sau. Bản ghi đăng ký (RegistrationShift) liên kết nhân viên với ca làm việc cụ thể. Người dùng (User) là nhân viên văn phòng thực hiện thao tác đăng ký. Mỗi tuần có 7 ngày, mỗi ngày có 2 ca (Ca 1: 8h-16h, Ca 2: 16h-0h). Nhân viên phải đăng ký tối thiểu một số ca nhất định mỗi tuần.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Nhà hàng | Entity class (Restaurant) | Chi nhánh quản lý nhân viên |
| Nhân viên | Entity class (Employee) | NV bán thời gian đăng ký ca |
| Ca làm việc | Entity class (Shift) | Ca cụ thể trong tuần (ngày + ca số) |
| Đăng ký ca | Entity class (RegistrationShift) | Bản ghi NV đăng ký ca cụ thể |
| Người dùng | Entity class (User) | Nhân viên văn phòng thao tác trên hệ thống |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Restaurant | id (PK), restaurantName, address, description |
| Employee | id (PK), code, fullName, phoneNumber, email, dob, contractDate, restaurantID (FK) |
| Shift | id (PK), date, shiftNumber, description, startDate, endDate |
| RegistrationShift | id (PK), registeredTime, status, description, employeeID (FK), shiftID (FK), userID (FK) |
| User | id (PK), username, password, role, description |

### ASCII Class Diagram

```
+------------------+       +------------------+
|   Restaurant     |       |    Employee      |
+------------------+       +------------------+
| - id: int        |       | - id: int        |
| - restaurantName |       | - code: String   |
| - address: String|       | - fullName: String|
| - description    |       | - phoneNumber    |
+------------------+       | - email: String  |
        | 1                | - dob: Date      |
        |                  | - contractDate   |
        | n                | - restaurantID   |
        v                  +------------------+
+------------------+               | 1
|                  |               |
|                  |               | n
|                  |               v
|                  |       +------------------+
|                  |       |RegistrationShift |
|                  |       +------------------+
|                  |       | - id: int        |
|                  |       | - registeredTime |
|                  |       | - status: String |
|                  |       | - description    |
|                  |       | - employeeID: int|
|                  |       | - shiftID: int   |
|                  |       | - userID: int    |
|                  |       +------------------+
|                  |               | n
|                  |               |
|                  |               | 1
|                  |               v
+------------------+       +------------------+
|                  |       |     Shift        |
|                  |       +------------------+
|                  |       | - id: int        |
|                  |       | - date: Date     |
|                  |       | - shiftNumber: int|
|                  |       | - description    |
|                  |       | - startDate: Date|
|                  |       | - endDate: Date  |
|                  |       +------------------+

+------------------+
|      User        |
+------------------+
| - id: int        |
| - username: String|
| - password: String|
| - role: String   |
| - description    |
+------------------+
        | 1
        |
        | n
        v
+------------------+
|RegistrationShift |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Restaurant → Employee | 1-n | Một nhà hàng có nhiều nhân viên |
| Employee → RegistrationShift | 1-n | Một nhân viên có nhiều đăng ký ca |
| Shift → RegistrationShift | 1-n | Một ca có nhiều nhân viên đăng ký |
| User → RegistrationShift | 1-n | Một staff tạo nhiều đăng ký |
| Employee → Shift | n-n (qua RegistrationShift) | Nhân viên đăng ký nhiều ca, ca có nhiều NV đăng ký |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> Giao diện Home xuất hiện:
- một lựa chọn đăng ký ca -> subRegisterShift

Chọn đăng ký -> Giao diện tìm kiếm nhân viên xuất hiện:
- ô nhập từ khóa tìm nhân viên -> inKey
- nút tìm kiếm -> subSearch
- bảng danh sách nhân viên (click được) -> outsubListEmployee

Nhập từ khóa và tìm kiếm -> hệ thống tìm nhân viên -> cần phương thức:
- tên: searchEmployee()
- đầu vào: keyword
- đầu ra: danh sách Employee
- gán cho entity class: Employee.

Chọn nhân viên -> Giao diện đăng ký ca xuất hiện:
- thông tin nhân viên (chỉ đọc) -> outEmployee
- bảng checkbox ca 7 ngày x 2 ca -> inoutShifts
- nút lưu -> subSave

Chọn ca và lưu -> hệ thống lưu đăng ký -> cần phương thức:
- tên: addRegistrationShift()
- đầu vào: đối tượng RegistrationShift
- đầu ra: boolean
- gán cho entity class: RegistrationShift.

#### Tóm tắt
View classes: HomeFrm, SearchEmployeeFrm, RegisterShiftFrm
Methods: searchEmployee(), addRegistrationShift()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm** — Giao diện đăng nhập:
- `inUsername`: ô nhập tên đăng nhập (username)
- `inPassword`: ô nhập mật khẩu (password)
- `subLogin`: nút Login

**HomeFrm** — Giao diện chính:
- `mnuRegister`: menu-item "Register for next week"
- `mnuSchedule`: menu-item "Schedule"
- `mnuCheckin`: menu-item "Checkin/Checkout"
- `mnuWages`: menu-item "Wages"
- `mnuStatistics`: menu-item "Statistics"

**SearchEmployeeFrm** — Giao diện tìm kiếm nhân viên:
- `inKeyword`: ô nhập tên nhân viên (keyword)
- `subSearch`: nút Search
- `outsubListEmployee`: bảng danh sách nhân viên (click được) — hiển thị mã NV, tên, SĐT

**RegisterShiftFrm** — Giao diện đăng ký ca tuần sau:
- `outEmployeeInfo`: thông tin nhân viên (mã, tên, SĐT, nhà hàng)
- `inShiftTable`: bảng 7×2 checkbox (Thứ 2-CN × Ca 1/Ca 2) — hiển thị ca đã đăng ký (nếu có)
- `subSave`: nút Save

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| txtKeyword | TextBox | Nhập tên nhân viên để tìm kiếm |
| btnSearch | Button | Nhấn để tìm kiếm nhân viên |
| dgvEmployeeList | DataGridView | Bảng danh sách nhân viên (mã, tên, SĐT) |
| lblEmployeeInfo | Label | Hiển thị thông tin NV đã chọn |
| chkMonShift1 | CheckBox | Thứ 2 Ca 1 (8h-16h) |
| chkMonShift2 | CheckBox | Thứ 2 Ca 2 (16h-0h) |
| chkTueShift1 | CheckBox | Thứ 3 Ca 1 |
| chkTueShift2 | CheckBox | Thứ 3 Ca 2 |
| chkWedShift1 | CheckBox | Thứ 4 Ca 1 |
| chkWedShift2 | CheckBox | Thứ 4 Ca 2 |
| chkThuShift1 | CheckBox | Thứ 5 Ca 1 |
| chkThuShift2 | CheckBox | Thứ 5 Ca 2 |
| chkFriShift1 | CheckBox | Thứ 6 Ca 1 |
| chkFriShift2 | CheckBox | Thứ 6 Ca 2 |
| chkSatShift1 | CheckBox | Thứ 7 Ca 1 |
| chkSatShift2 | CheckBox | Thứ 7 Ca 2 |
| chkSunShift1 | CheckBox | Chủ nhật Ca 1 |
| chkSunShift2 | CheckBox | Chủ nhật Ca 2 |
| btnSave | Button | Nhấn để lưu đăng ký |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| EmployeeDAO | `searchEmployee(keyword): List<Employee>` | Tìm nhân viên theo tên |
| ShiftDAO | `getShiftNextWeek(): List<Shift>` | Lấy danh sách ca trong tuần sau |
| RegistrationShiftDAO | `getRegistrationByEmployee(employeeId, startDate, endDate): List<RegistrationShift>` | Lấy đăng ký hiện tại của NV trong tuần |
| RegistrationShiftDAO | `saveRegistration(employeeId, shiftIds, userId): boolean` | Lưu danh sách đăng ký ca mới |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Employee | Nhân viên cần đăng ký ca, hiển thị trong bảng tìm kiếm |
| Shift | Ca làm việc trong tuần, hiển thị trong bảng 7×2 checkbox |
| RegistrationShift | Bản ghi đăng ký, lưu vào database khi staff nhấn Save |
| Restaurant | Thông tin nhà hàng, hiển thị trong thông tin NV |
| User | Nhân viên văn phòng thực hiện đăng ký |

### Database Schema

```sql
CREATE TABLE tblUser (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(100),
    role VARCHAR(20),
    description VARCHAR(200)
);

CREATE TABLE tblRestaurant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    restaurantName VARCHAR(100),
    address VARCHAR(200),
    description VARCHAR(200)
);

CREATE TABLE tblEmployee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    fullName VARCHAR(100),
    phoneNumber VARCHAR(20),
    email VARCHAR(100),
    dob DATE,
    contractDate DATE,
    restaurantID INT,
    FOREIGN KEY (restaurantID) REFERENCES tblRestaurant(id)
);

CREATE TABLE tblShift (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    shiftNumber INT,
    description VARCHAR(200),
    startDate DATE,
    endDate DATE
);

CREATE TABLE tblRegistrationShift (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registeredTime DATETIME,
    status VARCHAR(20),
    description VARCHAR(200),
    employeeID INT,
    shiftID INT,
    userID INT,
    FOREIGN KEY (employeeID) REFERENCES tblEmployee(id),
    FOREIGN KEY (shiftID) REFERENCES tblShift(id),
    FOREIGN KEY (userID) REFERENCES tblUser(id)
);
```

### Hướng dẫn vẽ trên Visual Paradigm

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo các class: Restaurant, Employee, Shift, RegistrationShift, User.
3. Thêm attributes cho mỗi class theo bảng ở trên.
4. Vẽ quan hệ:
   - Restaurant → Employee (1-n, Association)
   - Employee → RegistrationShift (1-n, Composition)
   - Shift → RegistrationShift (1-n, Association)
   - User → RegistrationShift (1-n, Association)
5. Đặt tên cho mỗi association (ví dụ: "employs", "registers", "assignedTo", "createdBy").
6. Đặt multiplicity đúng: Employee(1) → RegistrationShift(n), Shift(1) → RegistrationShift(n).
7. Ghi chú: Employee và Shift có quan hệ n-n thông qua RegistrationShift (bảng trung gian).

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor (người sử dụng)
2. `:LoginFrm` — boundary (giao diện đăng nhập)
3. `:HomeFrm` — boundary (giao diện chính)
4. `:SearchEmployeeFrm` — boundary (giao diện tìm NV)
5. `:RegisterShiftFrm` — boundary (giao diện đăng ký ca)
6. `:UserDAO` — control (truy vấn người dùng)
7. `:EmployeeDAO` — control (truy vấn nhân viên)
8. `:ShiftDAO` — control (truy vấn ca làm việc)
9. `:RegistrationShiftDAO` — control (truy vấn / lưu đăng ký)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 9 lifelines theo thứ tự: Staff, LoginFrm, HomeFrm, SearchEmployeeFrm, RegisterShiftFrm, UserDAO, EmployeeDAO, ShiftDAO, RegistrationShiftDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng alt fragment cho phần kiểm tra số ca >= ngưỡng tối thiểu.

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | enterCredentials("staff01", "******") | synchronous |
| 2 | Staff | LoginFrm | clickLogin() | synchronous |
| 3 | LoginFrm | UserDAO | checkLogin("staff01", "******") | synchronous |
| 4 | UserDAO | LoginFrm | return true | return |
| 5 | LoginFrm | HomeFrm | show() | synchronous |
| 6 | Staff | HomeFrm | selectMenu("Register for next week") | synchronous |
| 7 | HomeFrm | SearchEmployeeFrm | show() | synchronous |
| 8 | SearchEmployeeFrm | Staff | displayForm(txtKeyword, btnSearch) | return |
| 9 | Staff | SearchEmployeeFrm | enterKeyword("Nguyen") | synchronous |
| 10 | Staff | SearchEmployeeFrm | clickSearch() | synchronous |
| 11 | SearchEmployeeFrm | EmployeeDAO | searchEmployee("Nguyen") | synchronous |
| 12 | EmployeeDAO | SearchEmployeeFrm | return List<Employee> | return |
| 13 | SearchEmployeeFrm | Staff | displayEmployeeList(data) | return |
| 14 | Staff | SearchEmployeeFrm | clickEmployee("B23DCCE060") | synchronous |
| 15 | SearchEmployeeFrm | RegisterShiftFrm | show(employeeId=1) | synchronous |
| 16 | RegisterShiftFrm | ShiftDAO | getShiftNextWeek() | synchronous |
| 17 | ShiftDAO | RegisterShiftFrm | return List<Shift> (14 ca: 7 ngày × 2 ca) | return |
| 18 | RegisterShiftFrm | RegistrationShiftDAO | getRegistrationByEmployee(employeeId=1, startDate, endDate) | synchronous |
| 19 | RegistrationShiftDAO | RegisterShiftFrm | return List<RegistrationShift> (hiện tại rỗng hoặc có dữ liệu cũ) | return |
| 20 | RegisterShiftFrm | RegisterShiftFrm | displayShiftTable(shifts, registrations) | self |
| 21 | RegisterShiftFrm | Staff | displayEmployeeInfo + shiftTable (7×2 checkbox) | return |
| 22 | Staff | RegisterShiftFrm | tickShifts(Thứ2-Ca1, Thứ4-Ca2, Thứ6-Ca1, Thứ7-Ca2) | synchronous |
| 23 | Staff | RegisterShiftFrm | clickSave() | synchronous |
| 24 | RegisterShiftFrm | RegisterShiftFrm | validateMinShifts(4 >= 3) | self |
| 25 | RegisterShiftFrm | RegistrationShiftDAO | saveRegistration(employeeId=1, [shiftId1,shiftId4,shiftId5,shiftId8], userId=1) | synchronous |
| 26 | RegistrationShiftDAO | RegistrationShiftDAO | INSERT INTO tblRegistrationShift (4 dòng) | self |
| 27 | RegistrationShiftDAO | RegisterShiftFrm | return true | return |
| 28 | RegisterShiftFrm | Staff | showMessage("Dang ky thanh cong") | return |

---

## Câu 5: Blackbox Testcase

### TC01: Đăng ký ca tuần sau thành công

**Database trước khi test:**

tblUser:
| id | username | password | role | description |
|----|----------|----------|------|-------------|
| 1 | staff01 | 123456 | staff | Nhan vien van phong |

tblRestaurant:
| id | restaurantName | address | description |
|----|---------------|---------|-------------|
| 1 | Nha hang A | 123 Le Loi, Q1, TP.HCM | Chi nhanh trung tam |

tblEmployee:
| id | code | fullName | phoneNumber | email | dob | contractDate | restaurantID |
|----|------|----------|-------------|-------|-----|-------------|--------------|
| 1 | B23DCCE060 | Nguyen Van A | 0912345678 | nguyenA@gmail.com | 2000-05-15 | 2025-01-01 | 1 |

tblShift:
| id | date | shiftNumber | description | startDate | endDate |
|----|------|-------------|-------------|-----------|---------|
| 1 | 2026-06-09 | 1 | Thu 2 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 2 | 2026-06-09 | 2 | Thu 2 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 3 | 2026-06-10 | 1 | Thu 3 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 4 | 2026-06-10 | 2 | Thu 3 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 5 | 2026-06-11 | 1 | Thu 4 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 6 | 2026-06-11 | 2 | Thu 4 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 7 | 2026-06-12 | 1 | Thu 5 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 8 | 2026-06-12 | 2 | Thu 5 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 9 | 2026-06-13 | 1 | Thu 6 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 10 | 2026-06-13 | 2 | Thu 6 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 11 | 2026-06-14 | 1 | Thu 7 Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 12 | 2026-06-14 | 2 | Thu 7 Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |
| 13 | 2026-06-15 | 1 | CN Ca 1 (8h-16h) | 2026-06-09 | 2026-06-15 |
| 14 | 2026-06-15 | 2 | CN Ca 2 (16h-0h) | 2026-06-09 | 2026-06-15 |

tblRegistrationShift: (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập username: staff01, password: 123456, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 3 | Chọn "Register for next week" | Hiển thị giao diện tìm NV với ô nhập tên, nút Search |
| 4 | Nhập "Nguyen" vào ô tìm kiếm, nhấn Search | Bảng hiển thị: B23DCCE060 - Nguyen Van A - 0912345678 |
| 5 | Nhấn chọn NV "B23DCCE060 - Nguyen Van A" | Hiển thị giao diện đăng ký ca: thông tin NV + bảng 7×2 checkbox (tất cả trống) |
| 6 | Tick chọn: Thứ 2 Ca 1 (chkMonShift1), Thứ 4 Ca 2 (chkThuShift2), Thứ 6 Ca 1 (chkSatShift1), Thứ 7 Ca 2 (chkSunShift2) | 4 checkbox được tick |
| 7 | Nhấn nút Save | Hệ thống kiểm tra: 4 >= 3 (ngưỡng tối thiểu) → hợp lệ |
| 8 | Hệ thống hiển thị thông báo "Dang ky thanh cong" | Thông báo xuất hiện |

### Database sau khi test

tblRegistrationShift: (thêm 4 dòng)
| id | registeredTime | status | description | employeeID | shiftID | userID |
|----|---------------|--------|-------------|------------|---------|--------|
| 1 | 2026-06-08 10:00:00 | registered | Dang ky ca lam | 1 | 1 | 1 |
| 2 | 2026-06-08 10:00:00 | registered | Dang ky ca lam | 1 | 6 | 1 |
| 3 | 2026-06-08 10:00:00 | registered | Dang ky ca lam | 1 | 9 | 1 |
| 4 | 2026-06-08 10:00:00 | registered | Dang ky ca lam | 1 | 14 | 1 |

**Giải thích mapping checkbox → shiftID:**
- Thứ 2 Ca 1 → shiftID 1 (date=2026-06-09, shiftNumber=1)
- Thứ 4 Ca 2 → shiftID 6 (date=2026-06-11, shiftNumber=2)
- Thứ 6 Ca 1 → shiftID 9 (date=2026-06-13, shiftNumber=1)
- Thứ 7 Ca 2 → shiftID 14 (date=2026-06-15, shiftNumber=2)

**Các bảng khác:** Không thay đổi (tblUser, tblRestaurant, tblEmployee, tblShift giữ nguyên).
