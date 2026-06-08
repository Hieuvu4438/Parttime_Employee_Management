# Subject No. 44 — Part-time Employee — Module "Statistics of best employees"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Thống kê nhân viên xuất sắc

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chọn **Statistics** → **Best employees**. |
| 5 | Giao diện thống kê nhân viên xuất sắc xuất hiện: ô nhập ngày bắt đầu (txtStartDate), ô nhập ngày kết thúc (txtEndDate), nút View (btnView). |
| 6 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết thúc `31/12/2026`, nhấn **View**. |
| 7 | Hệ thống truy vấn database, tính toán tổng giờ thực tế, tổng tiền, tổng giờ trễ/về sớm, tổng phạt cho từng nhân viên trong khoảng thời gian. |
| 8 | Hệ thống hiển thị bảng danh sách nhân viên với các cột: Mã NV, Tên, SĐT, Tổng giờ thực tế, Tổng tiền, Tổng giờ trễ, Tổng phạt. Sắp xếp tăng dần theo tổng giờ trễ/về sớm (ít trễ nhất là xuất sắc nhất). |
| 9 | Bảng hiển thị: NV01 - A - 0912345678 - 220h - 4,800,000đ - 0h - 0đ; NV02 - B - 0987654321 - 200h - 4,200,000đ - 2h - 60,000đ; NV03 - C - 0901234567 - 180h - 3,600,000đ - 5h - 150,000đ. |
| 10 | Staff nhấn vào dòng nhân viên "A" (NV01). |
| 11 | Hệ thống hiển thị bảng chi tiết giờ làm việc của nhân viên A trong khoảng thời gian, mỗi dòng là 1 ca với các cột: Ngày, Thứ, Ca, Giờ checkin, Giờ checkout, Giờ thực tế, Tiền, Giờ trễ, Phạt. |
| 12 | Bảng chi tiết hiển thị: 02/06/2026 - Thứ 3 - Ca 1 - 08:00 - 16:00 - 8h - 160,000đ - 0h - 0đ; 04/06/2026 - Thứ 5 - Ca 2 - 16:00 - 00:00 - 8h - 160,000đ - 0h - 0đ; 06/06/2026 - Thứ 7 - Ca 1 - 08:00 - 17:00 - 9h - 190,000đ - 0h - 0đ; ... |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập ngày bắt đầu trống hoặc ngày kết thúc trống → hệ thống cảnh báo "Vui long nhap day du ngay bat dau va ket thuc".
- **EL2:** Staff nhập ngày bắt đầu lớn hơn ngày kết thúc → hệ thống cảnh báo "Ngay bat dau phai nho hon ngay ket thuc".
- **EL3:** Không có dữ liệu nhân viên trong khoảng thời gian → hệ thống hiển thị bảng trống với thông báo "Khong co du lieu".
- **EL4:** Staff nhấn vào dòng nhân viên mà không có chi tiết ca nào → hệ thống hiển thị bảng chi tiết trống.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý nhân viên bán thời gian cho chuỗi nhà hàng. Mỗi nhà hàng có nhiều nhân viên. Mỗi nhân viên có mã, tên, số điện thoại, email, ngày sinh, ngày ký hợp đồng. Hệ thống quản lý ca làm việc, mỗi ca có ngày, số ca (Ca 1: 8h-16h, Ca 2: 16h-0h), mô tả. Nhân viên đăng ký ca, được xếp lịch, và chấm công (checkin/checkout). Từ dữ liệu chấm công, hệ thống tính giờ làm thực tế, giờ đi trễ/về sớm, và tiền phạt. Hệ thống xếp hạng nhân viên xuất sắc dựa trên tổng giờ trễ tăng dần (ít trễ nhất là tốt nhất).

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Restaurant | Entity class | | Chi nhánh quản lý nhân viên |
| Employee | Entity class | | Nhân viên bán thời gian |
| code | Attribute (Employee) | String | Mã nhân viên |
| fullName | Attribute (Employee) | String | Tên nhân viên |
| phoneNumber | Attribute (Employee) | String | Số điện thoại |
| email | Attribute (Employee) | String | Email |
| dob | Attribute (Employee) | Date | Ngày sinh |
| contractDate | Attribute (Employee) | Date | Ngày ký hợp đồng |
| Shift | Entity class | | Ca làm việc cụ thể |
| date | Attribute (Shift) | Date | Ngày của ca |
| shiftNumber | Attribute (Shift) | int | Số ca (1 hoặc 2) |
| description | Attribute (Shift) | String | Mô tả ca |
| startDate | Attribute (Shift) | Date | Ngày bắt đầu tuần |
| endDate | Attribute (Shift) | Date | Ngày kết thúc tuần |
| Schedule | Entity class | | Lịch xếp ca cho nhân viên |
| Attendance | Entity class | | Bản ghi chấm công |
| checkinTime | Attribute (Attendance) | DateTime | Giờ checkin |
| checkoutTime | Attribute (Attendance) | DateTime | Giờ checkout |
| Wage | Entity class | | Bảng lương tuần |
| User | Entity class | | Nhân viên thao tác hệ thống |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|   Restaurant     |       |    Employee      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -restaurantName: String|  | -code: String    |
| -address: String |       | -fullName: String|
| -description: String|    | -phoneNumber: String|
+------------------+       | -email: String   |
         | 1               | -dob: Date       |
         |                  | -contractDate: Date|
         | n                +------------------+
         v                  | +searchEmployee()|
+------------------+        +------------------+
|                  |                | 1
|                  |                |
|                  |                | n
|                  |                v
|                  |        +------------------+
|                  |        |    Schedule      |
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -weekStartDate: Date|
|                  |        +------------------+

+------------------+       +------------------+
|      Shift       |       |   Attendance     |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -date: Date      |       | -checkinTime: DateTime|
| -shiftNumber: int|       | -checkoutTime: DateTime|
| -description: String|    +------------------+
| -startDate: Date |       | +getShiftHours() |
| -endDate: Date   |       | +getLateHours()  |
+------------------+       | +getFine()       |
         | 1               +------------------+
         |                       | n
         | n                      |
         +------------------------+
                                  | 1
+------------------+       +------+---------+
|      User        |       |     Wage       |
+------------------+       +----------------+
| -id: int         |       | -id: int       |
| -username: String|       | -weekStart: Date|
| -password: String|       | -weekEnd: Date |
| -role: String    |       | -totalActualHours: float|
+------------------+       | -totalReceived: float|
                           | -totalLateHours: float|
                           | -totalFines: float|
                           +----------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Restaurant → Employee | 1-n | Một nhà hàng có nhiều nhân viên |
| Employee → Schedule | 1-n | Một nhân viên có nhiều lịch xếp ca |
| Employee → Attendance | 1-n | Một nhân viên có nhiều bản ghi chấm công |
| Shift → Schedule | 1-n | Một ca có nhiều nhân viên được xếp |
| Shift → Attendance | 1-n | Một ca có nhiều bản ghi chấm công |
| Employee → Wage | 1-n | Một nhân viên có nhiều bảng lương tuần |
| User → Schedule | 1-n | Một staff tạo nhiều lịch xếp |

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subRegister`: nút chọn Register for next week (JButton)
- `subSchedule`: nút chọn Schedule next week (JButton)
- `subCheckin`: nút chọn Checkin/Checkout (JButton)
- `subWages`: nút chọn Calculate wages (JButton)
- `subStatistics`: nút chọn Statistics (JButton)

**BestEmployeeStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu (JTextField)
- `inEndDate`: ô nhập ngày kết thúc (JTextField)
- `subView`: nút View (JButton)
- `outsubListEmployee`: bảng danh sách nhân viên thống kê, click được (JTable) — các cột: Mã NV, Tên, SĐT, Tổng giờ thực tế, Tổng tiền, Tổng giờ trễ, Tổng phạt
- `outListAttendanceDetail`: bảng chi tiết giờ làm việc từng ca (JTable) — các cột: Ngày, Thứ, Ca, Giờ checkin, Giờ checkout, Giờ thực tế, Tiền, Giờ trễ, Phạt

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Restaurant | Model | id: int, restaurantName: String, address: String, description: String |
| Employee | Model | id: int, code: String, fullName: String, phoneNumber: String, email: String, dob: Date, contractDate: Date, restaurantId: int |
| Shift | Model | id: int, date: Date, shiftNumber: int, description: String, startDate: Date, endDate: Date |
| Schedule | Model | id: int, employeeId: int, shiftId: int, weekStartDate: Date |
| Attendance | Model | id: int, employeeId: int, shiftId: int, checkinTime: DateTime, checkoutTime: DateTime |
| Wage | Model | id: int, employeeId: int, weekStart: Date, weekEnd: Date, totalActualHours: float, totalReceived: float, totalLateHours: float, totalFines: float |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| EmployeeDAO | `getBestEmployeeStat(startDate, endDate): List<BestEmployeeStat>` | Lấy danh sách thống kê NV xuất sắc theo khoảng thời gian |
| AttendanceDAO | `getAttendanceDetail(employeeId, startDate, endDate): List<AttendanceDetail>` | Lấy chi tiết chấm công của NV theo khoảng thời gian |

**BestEmployeeStat:** employee (Employee), totalActualHours (float), totalReceived (float), totalLateHours (float), totalFines (float)

**AttendanceDetail:** date (Date), dayOfWeek (String), shiftNumber (int), checkinTime (DateTime), checkoutTime (DateTime), actualHours (float), actualAmount (float), lateHours (float), fine (float)

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblRestaurant:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| restaurantName | nvarchar(100) | NOT NULL |
| address | nvarchar(255) | |
| description | nvarchar(255) | |

**tblEmployee:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| fullName | nvarchar(100) | NOT NULL |
| phoneNumber | varchar(15) | |
| email | varchar(100) | |
| dob | date | |
| contractDate | date | |
| restaurantID | int | FOREIGN KEY → tblRestaurant(ID) |

**tblShift:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| date | date | NOT NULL |
| shiftNumber | int | NOT NULL |
| description | nvarchar(255) | |
| startDate | date | NOT NULL |
| endDate | date | NOT NULL |

**tblSchedule:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| employeeID | int | FOREIGN KEY → tblEmployee(ID) |
| shiftID | int | FOREIGN KEY → tblShift(ID) |
| weekStartDate | date | NOT NULL |

**tblAttendance:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| employeeID | int | FOREIGN KEY → tblEmployee(ID) |
| shiftID | int | FOREIGN KEY → tblShift(ID) |
| checkinTime | datetime | |
| checkoutTime | datetime | |

**tblWage:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| employeeID | int | FOREIGN KEY → tblEmployee(ID) |
| weekStart | date | NOT NULL |
| weekEnd | date | NOT NULL |
| totalActualHours | float | NOT NULL |
| totalReceived | float | NOT NULL |
| totalLateHours | float | NOT NULL |
| totalFines | float | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.statistics`: chứa LoginFrm, HomeFrm, BestEmployeeStatFrm.
2. Tạo package `model`: chứa Restaurant, Employee, Shift, Schedule, Attendance, Wage, User.
3. Tạo package `dao`: chứa UserDAO, EmployeeDAO, AttendanceDAO.
4. Vẽ association từ BestEmployeeStatFrm → EmployeeDAO, BestEmployeeStatFrm → AttendanceDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dùng mũi tên dashed).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `BestEmployeeStatFrm`, `UserDAO`, `EmployeeDAO`, `AttendanceDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh ngoại lệ (ngày không hợp lệ).
4. Sử dụng `loop` fragment cho việc hiển thị chi tiết từng nhân viên.

### Sequence Diagram (ASCII)

```
Staff       LoginFrm    UserDAO   HomeFrm  BestEmployeeStatFrm  EmployeeDAO  AttendanceDAO
  |             |           |         |            |                |             |
  |--login----->|           |         |            |                |             |
  |             |--checkLogin()----->|            |                |             |
  |             |<--true----|         |            |                |             |
  |             |--open HomeFrm----->|            |                |             |
  |             |           |         |            |                |             |
  |--select Statistics----->|         |            |                |             |
  |             |           |         |--open BestEmployeeStatFrm->|             |
  |             |           |         |            |                |             |
  |--enter dates(01/01/2026, 31/12/2026)---------->|                |             |
  |--click View------------>|         |            |                |             |
  |             |           |         |            |--getBestEmployeeStat(dates)->|
  |             |           |         |            |                |--query DB   |
  |             |           |         |            |                |<-return-----|
  |             |           |         |            |<-List<BestEmployeeStat>------|
  |             |           |         |            |                |             |
  |             |           |         |            |--sort ascending by lateHours |
  |             |           |         |            |--display table              |
  |             |           |         |            |                |             |
  |--click employee "A"---->|         |            |                |             |
  |             |           |         |            |--getAttendanceDetail(empId, dates)-->
  |             |           |         |            |                |--query DB   |
  |             |           |         |            |                |<-return-----|
  |             |           |         |            |<-List<AttendanceDetail>------|
  |             |           |         |            |                |             |
  |             |           |         |            |--display detail table        |
  |<--show detail----------|         |            |                |             |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | actionPerformed("Login") | Staff nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra thông tin đăng nhập |
| 3 | UserDAO | LoginFrm | return true | Đăng nhập thành công |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Staff | HomeFrm | actionPerformed("Statistics") | Chọn chức năng Statistics |
| 6 | HomeFrm | BestEmployeeStatFrm | new BestEmployeeStatFrm().setVisible(true) | Mở giao diện thống kê NV xuất sắc |
| 7 | Staff | BestEmployeeStatFrm | setText("01/01/2026") | Nhập ngày bắt đầu |
| 8 | Staff | BestEmployeeStatFrm | setText("31/12/2026") | Nhập ngày kết thúc |
| 9 | Staff | BestEmployeeStatFrm | actionPerformed("View") | Nhấn nút View |
| 10 | BestEmployeeStatFrm | EmployeeDAO | getBestEmployeeStat(01/01/2026, 31/12/2026) | Truy vấn thống kê NV xuất sắc |
| 11 | EmployeeDAO | BestEmployeeStatFrm | return List<BestEmployeeStat> | Trả về danh sách thống kê |
| 12 | BestEmployeeStatFrm | BestEmployeeStatFrm | sortAscendingByLateHours() | Sắp xếp tăng dần theo tổng giờ trễ |
| 13 | BestEmployeeStatFrm | BestEmployeeStatFrm | displayTable(listBestEmployeeStat) | Hiển thị bảng thống kê |
| 14 | Staff | BestEmployeeStatFrm | clickRow("A") | Nhấn vào dòng nhân viên A |
| 15 | BestEmployeeStatFrm | AttendanceDAO | getAttendanceDetail(employeeId=1, dates) | Truy vấn chi tiết chấm công |
| 16 | AttendanceDAO | BestEmployeeStatFrm | return List<AttendanceDetail> | Trả về danh sách chi tiết |
| 17 | BestEmployeeStatFrm | BestEmployeeStatFrm | displayDetailTable(listDetail) | Hiển thị bảng chi tiết |
| 18 | BestEmployeeStatFrm | Staff | showDetailTable() | Hiển thị chi tiết giờ làm việc |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Best Employee Statistics | Thống kê NV xuất sắc có dữ liệu |
| TC02 | Best Employee Statistics | Nhập ngày bắt đầu trống |
| TC03 | Best Employee Statistics | Ngày bắt đầu lớn hơn ngày kết thúc |
| TC04 | Best Employee Statistics | Không có dữ liệu trong khoảng thời gian |

### TC01: Thống kê nhân viên xuất sắc có dữ liệu

**Purpose:** Kiểm tra quy trình thống kê nhân viên xuất sắc hoàn chỉnh từ nhập ngày, hiển thị bảng thống kê (sắp xếp tăng dần theo giờ trễ) đến xem chi tiết giờ làm việc.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblRestaurant:**
| ID | restaurantName | address | description |
|----|----------------|---------|-------------|
| 1 | CN Quan 1 | 123 Le Loi, Q1, TP.HCM | Chi nhanh trung tam |

**tblEmployee:**
| ID | code | fullName | phoneNumber | email | dob | contractDate | restaurantID |
|----|------|----------|-------------|-------|-----|--------------|--------------|
| 1 | NV01 | A | 0912345678 | a@gmail.com | 01/01/2000 | 01/06/2025 | 1 |
| 2 | NV02 | B | 0987654321 | b@gmail.com | 15/03/2001 | 01/06/2025 | 1 |
| 3 | NV03 | C | 0901234567 | c@gmail.com | 20/07/1999 | 01/06/2025 | 1 |

**tblShift:**
| ID | date | shiftNumber | description | startDate | endDate |
|----|------|-------------|-------------|-----------|---------|
| 1 | 02/06/2026 | 1 | Ca 1: 8h-16h | 01/06/2026 | 07/06/2026 |
| 2 | 02/06/2026 | 2 | Ca 2: 16h-0h | 01/06/2026 | 07/06/2026 |
| 3 | 04/06/2026 | 1 | Ca 1: 8h-16h | 01/06/2026 | 07/06/2026 |
| 4 | 04/06/2026 | 2 | Ca 2: 16h-0h | 01/06/2026 | 07/06/2026 |
| 5 | 06/06/2026 | 1 | Ca 1: 8h-16h | 01/06/2026 | 07/06/2026 |

**tblSchedule:**
| ID | employeeID | shiftID | weekStartDate |
|----|------------|---------|---------------|
| 1 | 1 | 1 | 01/06/2026 |
| 2 | 1 | 3 | 01/06/2026 |
| 3 | 1 | 5 | 01/06/2026 |
| 4 | 2 | 1 | 01/06/2026 |
| 5 | 2 | 4 | 01/06/2026 |
| 6 | 3 | 2 | 01/06/2026 |
| 7 | 3 | 3 | 01/06/2026 |

**tblAttendance:**
| ID | employeeID | shiftID | checkinTime | checkoutTime |
|----|------------|---------|-------------|--------------|
| 1 | 1 | 1 | 02/06/2026 08:00 | 02/06/2026 16:00 |
| 2 | 1 | 3 | 04/06/2026 08:00 | 04/06/2026 16:00 |
| 3 | 1 | 5 | 06/06/2026 08:00 | 06/06/2026 17:00 |
| 4 | 2 | 1 | 02/06/2026 08:05 | 02/06/2026 16:00 |
| 5 | 2 | 4 | 04/06/2026 16:10 | 05/06/2026 00:05 |
| 6 | 3 | 2 | 02/06/2026 16:20 | 03/06/2026 00:15 |
| 7 | 3 | 3 | 04/06/2026 08:10 | 04/06/2026 15:50 |

**tblWage:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username = `staff01`, password = `123456`, nhấn Login | Giao diện Home hiển thị với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics |
| 3 | Chọn **Statistics** → **Best employees** | Giao diện thống kê NV xuất sắc hiển thị: ô nhập ngày bắt đầu, ô nhập ngày kết thúc, nút View |
| 4 | Nhập ngày bắt đầu = `01/01/2026`, ngày kết thúc = `31/12/2026`, nhấn View | Bảng thống kê hiển thị 3 nhân viên, sắp xếp tăng dần theo tổng giờ trễ |
| 5 | Kiểm tra bảng thống kê | Dòng 1: NV01 - A - 0912345678 - 25h - 510,000đ - 0h - 0đ; Dòng 2: NV02 - B - 0987654321 - 15h55p - 318,333đ - 0h20p - 10,000đ; Dòng 3: NV03 - C - 0901234567 - 15h40p - 313,333đ - 0h35p - 17,500đ |
| 6 | Nhấn vào dòng nhân viên "A" (NV01) | Bảng chi tiết hiển thị 3 ca của nhân viên A |
| 7 | Kiểm tra bảng chi tiết | Dòng 1: 02/06/2026 - Thứ 3 - Ca 1 - 08:00 - 16:00 - 8h - 160,000đ - 0h - 0đ; Dòng 2: 04/06/2026 - Thứ 5 - Ca 1 - 08:00 - 16:00 - 8h - 160,000đ - 0h - 0đ; Dòng 3: 06/06/2026 - Thứ 7 - Ca 1 - 08:00 - 17:00 - 9h - 190,000đ - 0h - 0đ |

### Database sau khi test

Không thay đổi (chỉ đọc dữ liệu, không ghi).
