# Subject No. 39 — Part-time Employee — Module "Register for next week"

> **Domain:** Part-time Employee Management
> **Ghi chú:** Đây là domain chính của project. Entity classes và flow giống với Module 1 trong report.

---

## Câu 1: Scenario — Đăng ký ca làm tuần sau

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register, Schedule, Checkin/Checkout, Wages, Statistics. |
| 4 | Staff chọn chức năng **Register for next week**. |
| 5 | Giao diện tìm kiếm nhân viên xuất hiện: ô nhập tên nhân viên, nút Search. |
| 6 | Staff nhập "B" và nhấn Search. |
| 7 | Hệ thống hiển thị danh sách nhân viên: mã NV, tên, SĐT. Staff chọn nhân viên "B23DCCE060 - B". |
| 8 | Giao diện đăng ký ca xuất hiện: thông tin nhân viên + bảng 7 dòng (Thứ 2-CN), mỗi dòng 2 checkbox (Ca 1: 8h-16h, Ca 2: 16h-0h). |
| 9 | Staff tick chọn: Thứ 2 Ca 1, Thứ 4 Ca 2, Thứ 6 Ca 1, Thứ 7 Ca 2 (4 ca). |
| 10 | Staff nhấn **Save**. |
| 11 | Hệ thống kiểm tra: tổng số ca >= ngưỡng tối thiểu (3 ca/tuần). Nếu hợp lệ, lưu vào database. |
| 12 | Hệ thống thông báo "Dang ky thanh cong". |

### Kịch bản ngoại lệ
- **EL1:** Nhân viên không tồn tại.
- **EL2:** Số ca chọn < ngưỡng tối thiểu → cảnh báo, yêu cầu chọn thêm.

---

## Câu 2: Entity Class Diagram

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Restaurant | Entity class | Chi nhánh quản lý NV |
| Employee | Entity class | NV bán thời gian |
| Shift | Entity class | Ca làm việc cụ thể |
| RegistrationShift | Entity class | Bản ghi đăng ký ca |
| User | Entity class | Nhân viên thao tác |

### Class Diagram

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
|                  |                | n
|                  |                v
|                  |        +------------------+
|                  |        | RegistrationShift|
|                  |        +------------------+
|                  |        | -id: int         |
|                  |        | -registeredTime: DateTime|
|                  |        | -status: String  |
|                  |        | -description: String|
|                  |        +------------------+

+------------------+       +------------------+
|      Shift       |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -date: Date      |       | -username: String|
| -shiftNumber: int|       | -password: String|
| -description: String|    | -role: String    |
| -startDate: Date |       +------------------+
| -endDate: Date   |       | +checkLogin()    |
+------------------+       +------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Restaurant → Employee | 1-n | Một nhà hàng có nhiều NV |
| Employee → RegistrationShift | 1-n | Một NV có nhiều đăng ký |
| Shift → RegistrationShift | 1-n | Một ca có nhiều NV đăng ký |
| User → RegistrationShift | 1-n | Một staff tạo nhiều đăng ký |
| Employee → Shift | n-n (qua RegistrationShift) | NV đăng ký nhiều ca |

---

## Câu 3: Thiết kế tĩnh

### View classes

**SearchEmployeeFrm:**
- `inKeyword`: ô nhập tên NV
- `subSearch`: nút Search
- `outsubListEmployee`: bảng NV (click được)

**RegisterShiftFrm:**
- `outEmployeeInfo`: thông tin NV
- `inShiftTable`: bảng 7×2 checkbox (Thứ 2-CN × Ca 1/Ca 2)
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| EmployeeDAO | `searchEmployee(keyword): List<Employee>` | Tìm NV theo tên |
| ShiftDAO | `getShiftNextWeek(): List<Shift>` | Lấy ca tuần sau |
| RegistrationShiftDAO | `getRegistrationByEmployee(employeeId, week): List<RegistrationShift>` | Lấy đăng ký hiện tại |
| RegistrationShiftDAO | `saveRegistration(employeeId, shifts, userId): boolean` | Lưu đăng ký |

### Database Design

**tblUser:** ID (PK), username, password, role, description
**tblRestaurant:** ID (PK), restaurantName, address, description
**tblEmployee:** ID (PK), code, fullName, phoneNumber, email, dob, contractDate, restaurantID (FK)
**tblShift:** ID (PK), date, shiftNumber, description, startDate, endDate
**tblRegistrationShift:** ID (PK), registeredTime, status, description, employeeID (FK), shiftID (FK), userID (FK)

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `SearchEmployeeFrm`, `RegisterShiftFrm`, `UserDAO`, `EmployeeDAO`, `ShiftDAO`, `RegistrationShiftDAO`.
2. Message flow:

```
Staff          LoginFrm      UserDAO     HomeFrm     SearchEmployeeFrm  RegisterShiftFrm  EmployeeDAO    ShiftDAO     RegistrationShiftDAO
  |               |             |           |               |                |               |              |               |
  |---login------>|             |           |               |                |               |              |               |
  |               |--checkLogin>|           |               |                |               |              |               |
  |               |<--true------|           |               |                |               |              |               |
  |               |---open------|---------->|               |                |               |              |               |
  |               |             |           |               |                |               |              |               |
  |---select------|---------------------------->|            |                |               |              |               |
  |               |             |           |---open--------|--------------->|               |              |               |
  |               |             |           |               |                |               |              |               |
  |---search "B"->|             |           |               |                |               |              |               |
  |               |             |           |               |--searchEmployee|-------------->|              |               |
  |               |             |           |               |                |               |--query DB    |               |
  |               |             |           |               |                |               |<-return------|               |
  |               |             |           |               |<--List<Employee>|              |              |               |
  |               |             |           |               |                |               |              |               |
  |---select emp->|             |           |               |                |               |              |               |
  |               |             |           |               |---open---------|-------------->|              |               |
  |               |             |           |               |                |               |              |               |
  |               |             |           |               |                |--getShiftNextWeek------------>|               |
  |               |             |           |               |                |               |              |--query DB     |
  |               |             |           |               |                |               |              |<-return-------|
  |               |             |           |               |                |<--List<Shift>--|              |               |
  |               |             |           |               |                |               |              |               |
  |               |             |           |               |                |--getRegistrationByEmployee--->|               |
  |               |             |           |               |                |               |              |--query DB     |
  |               |             |           |               |                |               |              |<-return-------|
  |               |             |           |               |                |<--List<Reg>----|              |               |
  |               |             |           |               |                |               |              |               |
  |               |             |           |               |                |--display table|              |               |
  |               |             |           |               |                |               |              |               |
  |---tick shifts>|             |           |               |                |               |              |               |
  |---save------->|             |           |               |                |               |              |               |
  |               |             |           |               |                |--saveRegistration------------>|               |
  |               |             |           |               |                |               |              |--INSERT DB    |
  |               |             |           |               |                |               |              |<-return true--|
  |               |             |           |               |                |<--true---------|              |               |
  |               |             |           |               |                |               |              |               |
  |               |             |           |               |                |--show success |              |               |
  |<--success-----|             |           |               |                |               |              |               |
```

---

## Câu 5: Blackbox Testcase

### TC01: Đăng ký ca thành công

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblEmployee:**
| ID | code | fullName | phoneNumber | restaurantID |
|----|------|----------|-------------|--------------|
| 1 | B23DCCE060 | B | 0912345678 | 1 |

**tblShift:**
| ID | date | shiftNumber | startDate | endDate |
|----|------|-------------|-----------|---------|
| 1 | 09/06/2026 | 1 | 09/06/2026 | 15/06/2026 |
| 2 | 09/06/2026 | 2 | 09/06/2026 | 15/06/2026 |
| 3 | 11/06/2026 | 1 | 09/06/2026 | 15/06/2026 |
| 4 | 11/06/2026 | 2 | 09/06/2026 | 15/06/2026 |
| 5 | 13/06/2026 | 1 | 09/06/2026 | 15/06/2026 |
| 6 | 13/06/2026 | 2 | 09/06/2026 | 15/06/2026 |
| 7 | 14/06/2026 | 1 | 09/06/2026 | 15/06/2026 |
| 8 | 14/06/2026 | 2 | 09/06/2026 | 15/06/2026 |
| ... | ... | ... | ... | ... |

**tblRegistrationShift:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login |
| 2 | Nhập staff01/123456, Login | Giao diện Home |
| 3 | Chọn Register for next week | Giao diện tìm NV |
| 4 | Nhập "B", Search | Bảng: B23DCCE060 - B - 0912345678 |
| 5 | Chọn NV "B" | Bảng 7×2 checkbox hiện ra (trống) |
| 6 | Tick: Thứ 2 Ca 1, Thứ 4 Ca 2, Thứ 6 Ca 1, Thứ 7 Ca 2 | 4 ca được tick |
| 7 | Nhấn Save | Hệ thống kiểm tra: 4 >= 3 ✓. Thông báo "Dang ky thanh cong" |

### Database sau khi test

**tblRegistrationShift:** (thêm 4 dòng)
| ID | registeredTime | status | employeeID | shiftID | userID |
|----|---------------|--------|------------|---------|--------|
| 1 | 08/06/2026 10:00 | registered | 1 | 1 | 1 |
| 2 | 08/06/2026 10:00 | registered | 1 | 4 | 1 |
| 3 | 08/06/2026 10:00 | registered | 1 | 5 | 1 |
| 4 | 08/06/2026 10:00 | registered | 1 | 8 | 1 |
