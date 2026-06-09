# Subject No. 43 — Part-time Employee — Module "Statistics of employees"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Thống kê nhân viên

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chọn **Statistics** → **Employee statistics**. |
| 5 | Giao diện thống kê nhân viên xuất hiện: ô nhập ngày bắt đầu (txtStartDate), ô nhập ngày kết thúc (txtEndDate), nút View (btnView). |
| 6 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết đầu `31/12/2026`, nhấn **View**. |
| 7 | Hệ thống truy vấn database, tính toán tổng giờ ca, tổng giờ tăng ca, tổng giờ trễ, tổng giờ thực tế, tổng tiền thực nhận cho từng nhân viên trong khoảng thời gian. |
| 8 | Hệ thống hiển thị bảng danh sách nhân viên với các cột: Mã NV, Tên, SĐT, Tổng giờ ca, Tổng giờ tăng ca, Tổng giờ trễ, Tổng giờ thực tế, Tổng tiền thực nhận. Sắp xếp giảm dần theo tổng giờ thực tế. |
| 9 | Bảng hiển thị: NV01 - B - 0912345678 - 200h - 20h - 5h - 215h - 4,500,000đ; NV02 - C - 0987654321 - 160h - 10h - 2h - 168h - 3,600,000đ; NV03 - D - 0901234567 - 120h - 0h - 8h - 112h - 2,240,000đ. |
| 10 | Staff nhấn vào dòng nhân viên "B" (NV01). |
| 11 | Hệ thống hiển thị bảng chi tiết giờ làm việc của nhân viên B trong khoảng thời gian, mỗi dòng là 1 ca với các cột: Ngày, Thứ, Ca, Giờ checkin, Giờ checkout, Giờ ca, Giờ tăng ca, Giờ trễ, Tổng giờ thực tế, Tổng tiền. |
| 12 | Bảng chi tiết hiển thị: 02/06/2026 - Thứ 3 - Ca 1 - 08:00 - 16:00 - 8h - 0h - 0h - 8h - 160,000đ; 04/06/2026 - Thứ 5 - Ca 2 - 16:05 - 00:10 - 8h - 0h - 0h15p - 7h45p - 155,000đ; ... |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập ngày bắt đầu trống hoặc ngày kết thúc trống → hệ thống cảnh báo "Vui long nhap day du ngay bat dau va ket thuc".
- **EL2:** Staff nhập ngày bắt đầu lớn hơn ngày kết thúc → hệ thống cảnh báo "Ngay bat dau phai nho hon ngay ket thuc".
- **EL3:** Không có dữ liệu nhân viên trong khoảng thời gian → hệ thống hiển thị bảng trống với thông báo "Khong co du lieu".
- **EL4:** Staff nhấn vào dòng nhân viên mà không có chi tiết ca nào → hệ thống hiển thị bảng chi tiết trống.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý nhân viên bán thời gian cho chuỗi nhà hàng. Mỗi nhà hàng có nhiều nhân viên. Mỗi nhân viên có mã, tên, số điện thoại, email, ngày sinh, ngày ký hợp đồng. Hệ thống quản lý ca làm việc, mỗi ca có ngày, số ca (Ca 1: 8h-16h, Ca 2: 16h-0h), mô tả. Nhân viên đăng ký ca, được xếp lịch, và chấm công (checkin/checkout). Từ dữ liệu chấm công, hệ thống tính giờ làm thực tế, giờ tăng ca, giờ đi trễ, và lương. Nhân viên thao tác trên hệ thống có vai trò staff hoặc manager.

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
| -endDate: Date   |       | +getOvertime()   |
+------------------+       | +getLateHours()  |
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
| -role: String    |       | -totalShiftHours: float|
+------------------+       | -totalOvertime: float|
                           | -totalLateHours: float|
                           | -totalReceived: float|
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

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> Giao diện Home xuất hiện:
- một lựa chọn Statistics -> subStatistics

Chọn Statistics -> Giao diện thống kê nhân viên xuất hiện:
- ô nhập ngày bắt đầu -> inStartDate
- ô nhập ngày kết thúc -> inEndDate
- nút View -> subView
- bảng danh sách nhân viên thống kê (click được) -> outsubListEmployee
- bảng chi tiết giờ làm việc -> outListAttendanceDetail

Nhập ngày và nhấn View -> hệ thống thống kê nhân viên -> cần phương thức:
- tên: getEmployeeStat()
- đầu vào: startDate, endDate
- đầu ra: danh sách EmployeeStat
- gán cho entity class: Employee.

Nhấn vào dòng nhân viên -> hệ thống lấy chi tiết chấm công -> cần phương thức:
- tên: getAttendanceDetail()
- đầu vào: employeeId, startDate, endDate
- đầu ra: danh sách AttendanceDetail
- gán cho entity class: Attendance.

#### Tóm tắt
View classes: HomeFrm, EmployeeStatFrm
Methods: getEmployeeStat(), getAttendanceDetail()

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

**EmployeeStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu (JTextField)
- `inEndDate`: ô nhập ngày kết thúc (JTextField)
- `subView`: nút View (JButton)
- `outsubListEmployee`: bảng danh sách nhân viên thống kê, click được (JTable) — các cột: Mã NV, Tên, SĐT, Tổng giờ ca, Tổng giờ tăng ca, Tổng giờ trễ, Tổng giờ thực tế, Tổng tiền thực nhận
- `outListAttendanceDetail`: bảng chi tiết giờ làm việc từng ca (JTable) — các cột: Ngày, Thứ, Ca, Giờ checkin, Giờ checkout, Giờ ca, Giờ tăng ca, Giờ trễ, Tổng giờ thực tế, Tổng tiền

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Restaurant | Model | id: int, restaurantName: String, address: String, description: String |
| Employee | Model | id: int, code: String, fullName: String, phoneNumber: String, email: String, dob: Date, contractDate: Date, restaurantId: int |
| Shift | Model | id: int, date: Date, shiftNumber: int, description: String, startDate: Date, endDate: Date |
| Schedule | Model | id: int, employeeId: int, shiftId: int, weekStartDate: Date |
| Attendance | Model | id: int, employeeId: int, shiftId: int, checkinTime: DateTime, checkoutTime: DateTime |
| Wage | Model | id: int, employeeId: int, weekStart: Date, weekEnd: Date, totalShiftHours: float, totalOvertime: float, totalLateHours: float, totalReceived: float |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| EmployeeDAO | `getEmployeeStat(startDate, endDate): List<EmployeeStat>` | Lấy danh sách thống kê NV theo khoảng thời gian |
| AttendanceDAO | `getAttendanceDetail(employeeId, startDate, endDate): List<AttendanceDetail>` | Lấy chi tiết chấm công của NV theo khoảng thời gian |

**EmployeeStat:** employee (Employee), totalShiftHours (float), totalOvertime (float), totalLateHours (float), totalActualHours (float), totalReceived (float)

**AttendanceDetail:** date (Date), dayOfWeek (String), shiftNumber (int), checkinTime (DateTime), checkoutTime (DateTime), shiftHours (float), overtimeHours (float), lateHours (float), actualHours (float), actualAmount (float)

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
| totalShiftHours | float | NOT NULL |
| totalOvertime | float | NOT NULL |
| totalLateHours | float | NOT NULL |
| totalReceived | float | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.statistics`: chứa LoginFrm, HomeFrm, EmployeeStatFrm.
2. Tạo package `model`: chứa Restaurant, Employee, Shift, Schedule, Attendance, Wage, User.
3. Tạo package `dao`: chứa UserDAO, EmployeeDAO, AttendanceDAO.
4. Vẽ association từ EmployeeStatFrm → EmployeeDAO, EmployeeStatFrm → AttendanceDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dùng mũi tên dashed).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `EmployeeStatFrm`, `UserDAO`, `EmployeeDAO`, `AttendanceDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh ngoại lệ (ngày không hợp lệ).
4. Sử dụng `loop` fragment cho việc hiển thị chi tiết từng nhân viên.

### Sequence Diagram (ASCII)

```
Staff       LoginFrm    UserDAO   HomeFrm   EmployeeStatFrm  EmployeeDAO  AttendanceDAO
  |             |           |         |            |              |             |
  |--login----->|           |         |            |              |             |
  |             |--checkLogin()----->|            |              |             |
  |             |<--true----|         |            |              |             |
  |             |--open HomeFrm----->|            |              |             |
  |             |           |         |            |              |             |
  |--select Statistics----->|         |            |              |             |
  |             |           |         |--open EmployeeStatFrm--->|             |
  |             |           |         |            |              |             |
  |--enter dates(01/01/2026, 31/12/2026)--------->|              |             |
  |--click View------------>|         |            |              |             |
  |             |           |         |            |--getEmployeeStat(dates)--->|
  |             |           |         |            |              |--query DB   |
  |             |           |         |            |              |<-return-----|
  |             |           |         |            |<-List<EmployeeStat>--------|
  |             |           |         |            |              |             |
  |             |           |         |            |--display table (sorted)    |
  |             |           |         |            |              |             |
  |--click employee "B"---->|         |            |              |             |
  |             |           |         |            |--getAttendanceDetail(empId, dates)-->
  |             |           |         |            |              |--query DB   |
  |             |           |         |            |              |<-return-----|
  |             |           |         |            |<-List<AttendanceDetail>----|
  |             |           |         |            |              |             |
  |             |           |         |            |--display detail table      |
  |<--show detail----------|         |            |              |             |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | enterCredentials("staff01", "******") | Staff nhập username/password |
| 2 | Staff | LoginFrm | clickLogin() | Staff nhấn nút Login |
| 3 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra thông tin đăng nhập |
| 4 | UserDAO | Database | executeQuery("SELECT * FROM tblUser WHERE...") | Truy vấn tblUser |
| 5 | UserDAO | LoginFrm | return true | Đăng nhập thành công |
| 6 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 7 | Staff | HomeFrm | actionPerformed("Statistics") | Chọn chức năng Statistics |
| 8 | HomeFrm | EmployeeStatFrm | new EmployeeStatFrm().setVisible(true) | Mở giao diện thống kê NV |
| 9 | Staff | EmployeeStatFrm | setText("01/01/2026") | Nhập ngày bắt đầu |
| 10 | Staff | EmployeeStatFrm | setText("31/12/2026") | Nhập ngày kết thúc |
| 11 | Staff | EmployeeStatFrm | actionPerformed("View") | Nhấn nút View |
| 12 | EmployeeStatFrm | EmployeeDAO | getEmployeeStat(01/01/2026, 31/12/2026) | Truy vấn thống kê NV |
| 13 | EmployeeDAO | Database | executeQuery("SELECT employee, SUM(hours)...") | Truy vấn tblAttendance JOIN tblEmployee |
| 14 | EmployeeDAO | EmployeeStatFrm | return List<EmployeeStat> | Trả về danh sách thống kê |
| 15 | EmployeeStatFrm | EmployeeStatFrm | sortDescendingByActualHours() | Sắp xếp giảm dần theo tổng giờ thực tế |
| 16 | EmployeeStatFrm | EmployeeStatFrm | displayTable(listEmployeeStat) | Hiển thị bảng thống kê |
| 17 | Staff | EmployeeStatFrm | clickRow("B") | Nhấn vào dòng nhân viên B |
| 18 | EmployeeStatFrm | AttendanceDAO | getAttendanceDetail(employeeId=1, dates) | Truy vấn chi tiết chấm công |
| 19 | AttendanceDAO | EmployeeStatFrm | return List<AttendanceDetail> | Trả về danh sách chi tiết |
| 20 | EmployeeStatFrm | EmployeeStatFrm | displayDetailTable(listDetail) | Hiển thị bảng chi tiết giờ làm việc |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Employee Statistics | Thống kê nhân viên có dữ liệu |
| TC02 | Employee Statistics | Nhập ngày bắt đầu trống |
| TC03 | Employee Statistics | Ngày bắt đầu lớn hơn ngày kết thúc |
| TC04 | Employee Statistics | Không có dữ liệu trong khoảng thời gian |

### TC01: Thống kê nhân viên có dữ liệu

**Purpose:** Kiểm tra quy trình thống kê nhân viên hoàn chỉnh từ nhập ngày, hiển thị bảng thống kê đến xem chi tiết giờ làm việc.

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
| 1 | NV01 | B | 0912345678 | b@gmail.com | 01/01/2000 | 01/06/2025 | 1 |
| 2 | NV02 | C | 0987654321 | c@gmail.com | 15/03/2001 | 01/06/2025 | 1 |
| 3 | NV03 | D | 0901234567 | d@gmail.com | 20/07/1999 | 01/06/2025 | 1 |

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

**tblAttendance:**
| ID | employeeID | shiftID | checkinTime | checkoutTime |
|----|------------|---------|-------------|--------------|
| 1 | 1 | 1 | 02/06/2026 08:00 | 02/06/2026 16:00 |
| 2 | 1 | 3 | 04/06/2026 08:05 | 04/06/2026 16:10 |
| 3 | 1 | 5 | 06/06/2026 07:55 | 06/06/2026 17:00 |
| 4 | 2 | 1 | 02/06/2026 08:00 | 02/06/2026 16:00 |
| 5 | 2 | 4 | 04/06/2026 16:10 | 05/06/2026 00:05 |
| 6 | 3 | 2 | 02/06/2026 16:15 | 03/06/2026 00:10 |

**tblWage:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username = `staff01`, password = `123456`, nhấn Login | Giao diện Home hiển thị với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics |
| 3 | Chọn **Statistics** → **Employee statistics** | Giao diện thống kê NV hiển thị: ô nhập ngày bắt đầu, ô nhập ngày kết thúc, nút View |
| 4 | Nhập ngày bắt đầu = `01/01/2026`, ngày kết thúc = `31/12/2026`, nhấn View | Bảng thống kê hiển thị 3 nhân viên, sắp xếp giảm dần theo tổng giờ thực tế |
| 5 | Kiểm tra bảng thống kê | Dòng 1: NV01 - B - 0912345678 - 24h ca - 1h tăng ca - 0h15p trễ - 24h45p thực tế - 510,000đ; Dòng 2: NV02 - C - 0987654321 - 16h ca - 0h tăng ca - 0h20p trễ - 15h40p thực tế - 310,000đ; Dòng 3: NV03 - D - 0901234567 - 8h ca - 0h10p tăng ca - 0h25p trễ - 7h45p thực tế - 155,000đ |
| 6 | Nhấn vào dòng nhân viên "B" (NV01) | Bảng chi tiết hiển thị 3 ca của nhân viên B |
| 7 | Kiểm tra bảng chi tiết | Dòng 1: 02/06/2026 - Thứ 3 - Ca 1 - 08:00 - 16:00 - 8h - 0h - 0h - 8h - 160,000đ; Dòng 2: 04/06/2026 - Thứ 5 - Ca 1 - 08:05 - 16:10 - 8h - 0h10p - 0h05p - 8h05p - 161,667đ; Dòng 3: 06/06/2026 - Thứ 7 - Ca 1 - 07:55 - 17:00 - 8h - 1h - 0h - 9h - 188,333đ |

### Database sau khi test

Không thay đổi (chỉ đọc dữ liệu, không ghi).
