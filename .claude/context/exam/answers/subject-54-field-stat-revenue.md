# Subject No. 54 — Mini Football Field — Module "Revenue statistics"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thống kê doanh thu

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. Hệ thống xác thực thành công. |
| 3 | Giao diện Home xuất hiện với các menu chức năng: Booking, Import goods, Update used items, Customer paying, Revenue statistics, Statistics of time slot. |
| 4 | Staff chọn menu **Revenue statistics**. |
| 5 | Hệ thống hiển thị giao diện thống kê doanh thu: combobox chọn kiểu thống kê (cboStatType) với 3 lựa chọn "Tháng", "Quý", "Năm". |
| 6 | Staff mở combobox, chọn "Tháng". |
| 7 | Hệ thống truy vấn database: lấy tất cả Payment trong 12 tháng gần nhất, nhóm theo tháng, tính tổng doanh thu mỗi tháng. |
| 8 | Hệ thống hiển thị bảng thống kê doanh thu 12 tháng gần nhất (tblRevenueStat): cột Tháng/Năm, Tổng doanh thu, sắp xếp theo thời gian tăng dần. Dữ liệu ví dụ: 07/2025 — 1,200,000đ, 08/2025 — 950,000đ, 09/2025 — 0đ, 10/2025 — 1,800,000đ, ..., 06/2026 — 2,500,000đ. |
| 9 | Staff click vào dòng "04/2026" trong bảng thống kê. |
| 10 | Hệ thống truy vấn chi tiết hóa đơn trong tháng 04/2026: lấy tất cả Payment có paymentDate trong tháng 04/2026, JOIN với Booking, Customer, Court. |
| 11 | Hệ thống hiển thị bảng chi tiết hóa đơn (tblInvoiceDetail): cột Mã hóa đơn (ID), Tên khách hàng, Tên sân, Ngày giờ thanh toán, Tổng thanh toán. Dữ liệu ví dụ: PAY001 — Nguyen Van A — San 1 — 05/04/2026 10:30 — 480,000đ; PAY002 — Tran Thi B — San 2 — 12/04/2026 14:00 — 350,000đ; PAY003 — Le Van C — San 1 — 20/04/2026 16:30 — 520,000đ. |
| 12 | Staff có thể click vào dòng khác trong bảng thống kê (ví dụ: "05/2026") để xem chi tiết hóa đơn tháng đó. Bảng chi tiết cập nhật theo. |
| 13 | Staff có thể thay đổi kiểu thống kê: chọn "Quý" từ combobox → bảng thống kê hiển thị theo quý (Quý 1/2026, Quý 2/2026, ...). Chọn "Năm" → bảng thống kê hiển thị theo năm (2025, 2026). |

### Kịch bản ngoại lệ

- **EL1:** Tháng được chọn không có doanh thu → bảng chi tiết hóa đơn trống, hiển thị thông báo "Khong co hoa don trong thang nay".
- **EL2:** Hệ thống không có dữ liệu Payment nào trong 12 tháng gần nhất → bảng thống kê tất cả dòng đều hiển thị 0đ.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý thuê sân bóng đá mini. Sân bóng có nhiều sân nhỏ, có thể gộp 2 hoặc 4 sân thành sân lớn. Mỗi sân được nhiều khách hàng thuê ở các khung giờ khác nhau. Khách hàng đặt sân theo phiên trong tuần hoặc theo tháng. Khi đặt sân, khách hàng nhận phiếu đặt và phải đặt cọc trước. Khi khách đến nhận sân và trả sân, nhân viên cập nhật giờ nhận, giờ trả, tiền thuê, và danh sách sản phẩm (đồ ăn, thức uống) mà khách đã sử dụng trong phiên đó. Khi khách hàng thanh toán, hệ thống tạo hóa đơn (Payment) chi tiết. Chức năng thống kê doanh thu tổng hợp dữ liệu từ Payment để hiển thị doanh thu theo tháng, quý, hoặc năm.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Sân bóng (Court) | Entity class | | Đối tượng quản lý chính, hiển thị tên sân trong chi tiết hóa đơn |
| Khách hàng (Customer) | Entity class | | Người thuê sân, hiển thị tên KH trong chi tiết hóa đơn |
| Phiếu đặt (Booking) | Entity class | | Bản ghi đặt sân, liên kết Payment với Customer và Court |
| Phiên thuê (BookingSession) | Entity class | | Mỗi lần khách đến nhận sân cụ thể |
| Thanh toán (Payment) | Entity class | | Hóa đơn thanh toán, nguồn dữ liệu chính cho thống kê |
| Người dùng (User) | Entity class | | Nhân viên thao tác trên hệ thống |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Court | courtId (PK), code, courtName, courtType, pricePerSession, status |
| Customer | customerId (PK), code, customerName, phoneNumber, address |
| Booking | bookingId (PK), courtId (FK), customerId (FK), bookingDate, startDate, endDate, timeSlot, dayOfWeek, totalSessions, totalAmount, deposit, status |
| BookingSession | sessionId (PK), bookingId (FK), sessionDate, startTime, endTime, checkinTime, checkoutTime, rentAmount, status |
| Payment | paymentId (PK), bookingId (FK), paymentDate, rentTotal, productTotal, totalAmount, depositDeducted, finalAmount, method, status |
| User | userId (PK), username, password, fullName, role |

### Class Diagram (ASCII)

```
+------------------+          +------------------+
|      Court       |          |     Customer     |
+------------------+          +------------------+
| - courtId: int   |          | - customerId: int|
| - code: String   |          | - code: String   |
| - courtName      |          | - customerName   |
| - courtType      |          | - phoneNumber    |
| - pricePerSession|          | - address        |
| - status         |          +------------------+
+------------------+                  | 1
        | 1                           |
        |                             | n
        | n                           v
        v                     +------------------+
+------------------+          |     Booking      |
|                  |          +------------------+
|                  |          | - bookingId: int |
|                  |          | - bookingDate    |
|                  |          | - startDate      |
|                  |          | - endDate        |
|                  |          | - timeSlot       |
|                  |          | - totalSessions  |
|                  |          | - totalAmount    |
|                  |          | - deposit        |
|                  |          | - status         |
|                  |          +------------------+
|                  |           | 1         | 1
|                  |           |           |
|                  |           | n         | 1
|                  |           v           v
|                  |   +------------------+  +------------------+
|                  |   | BookingSession   |  |     Payment      |
|                  |   +------------------+  +------------------+
|                  |   | - sessionId: int | 1| - paymentId: int |
|                  |   | - sessionDate    |--| - paymentDate    |
|                  |   | - startTime      |  | - rentTotal      |
|                  |   | - endTime        |  | - productTotal   |
|                  |   | - checkinTime    |  | - totalAmount    |
|                  |   | - checkoutTime   |  | - depositDeducted|
|                  |   | - rentAmount     |  | - finalAmount    |
|                  |   | - status         |  | - method         |
|                  |   +------------------+  | - status         |
|                  |                         +------------------+
+------------------+

+------------------+
|       User       |
+------------------+
| - userId: int    |
| - username       |
| - password       |
| - fullName       |
| - role           |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Court → Booking | 1-n | Một sân được nhiều khách đặt |
| Customer → Booking | 1-n | Một khách hàng có nhiều booking |
| Booking → BookingSession | 1-n | Một booking có nhiều phiên thuê theo tuần |
| Booking → Payment | 1-1 | Mỗi booking có 1 phiếu thanh toán |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**Các bước vẽ tổng quan:**

1. Mở Visual Paradigm → New → Class Diagram (trong danh mục Diagrams).
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity: Court, Customer, Booking, BookingSession, Payment, User.
3. Tạo view class boxes từ các interface: HomeFrm, RevenueStatFrm.
4. Vẽ relationships giữa các class theo bảng quan hệ bên dưới.
5. Thêm multiplicities và role names cho mỗi đường kết nối.

**Cấu trúc 1 class box (3 ngăn):**

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Payment`.
- **Ngăn 2 (thuộc tính):** Mỗi thuộc tính ghi dạng `-attributeName: Type`. Ví dụ: `-paymentDate: Date`, `-totalAmount: double`.
- **Ngăn 3 (phương thức):** Mỗi phương thức ghi dạng `+methodName(params): ReturnType`. Ví dụ: `+getRevenueByMonth(): List<Object[]>`.

**Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Court | `<<entity>>` | -id: int, -code: String, -courtName: String, -courtType: String, -pricePerSession: double, -status: String | getter/setter |
| Customer | `<<entity>>` | -id: int, -code: String, -customerName: String, -phoneNumber: String, -address: String | getter/setter |
| Booking | `<<entity>>` | -id: int, -bookingDate: Date, -startDate: Date, -endDate: Date, -timeSlot: String, -dayOfWeek: String, -totalSessions: int, -totalAmount: double, -deposit: double, -status: String | getter/setter |
| BookingSession | `<<entity>>` | -id: int, -sessionDate: Date, -startTime: String, -endTime: String, -checkinTime: String, -checkoutTime: String, -rentAmount: double, -status: String | getter/setter |
| Payment | `<<entity>>` | -id: int, -paymentDate: Date, -rentTotal: double, -productTotal: double, -totalAmount: double, -depositDeducted: double, -finalAmount: double, -method: String, -status: String | +getRevenueByMonth(): List<Object[]>, +getRevenueByQuarter(): List<Object[]>, +getRevenueByYear(): List<Object[]>, +getPaymentsByMonth(year: int, month: int): List<Payment>, +getPaymentsByQuarter(year: int, quarter: int): List<Payment>, +getPaymentsByYear(year: int): List<Payment> |
| User | `<<entity>>` | -id: int, -username: String, -password: String, -fullName: String, -role: String | +checkLogin(username: String, password: String): boolean |

**Bảng chi tiết view classes:**

| View class | Stereotype | UI Elements |
|------------|-----------|-------------|
| HomeFrm | `<<boundary>>` | subRevenueStat: JButton |
| RevenueStatFrm | `<<boundary>>` | inStatType: JComboBox ("Tháng", "Quý", "Năm"), outsubRevenueTable: JTable (clickable), outDetailTable: JTable, outTotalRevenue: JLabel |

**Cách vẽ quan hệ:**

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Booking.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Booking ◆→ BookingSession.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: RevenueStatFrm → PaymentDAO.

**Cách ghi multiplicity:**

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Court "1" --- "n" Booking.

**Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|--------------|------------|
| Court | Booking | Association | 1 — n | Một sân được nhiều khách đặt |
| Customer | Booking | Association | 1 — n | Một khách hàng có nhiều booking |
| Booking | BookingSession | Composition | 1 — n | Một booking có nhiều phiên thuê theo tuần |
| Booking | Payment | Association | 1 — 1 | Mỗi booking có 1 phiếu thanh toán |
| RevenueStatFrm | PaymentDAO | Dependency | — | Frm sử dụng PaymentDAO để thống kê doanh thu |

**Ví dụ cụ thể trên Visual Paradigm:**

1. **Vẽ quan hệ Booking → Payment (Association 1-1):**
   - Kéo class Booking lên canvas, kéo class Payment bên phải.
   - Chọn tool "Association" → click vào Booking, kéo đến Payment.
   - Đặt multiplicity "1" phía Booking, "1" phía Payment.
   - Không cần diamond vì đây là Association.

2. **Vẽ dependency RevenueStatFrm → PaymentDAO:**
   - Chọn tool "Dependency" (đường dashed) → click vào RevenueStatFrm, kéo đến PaymentDAO.
   - Mũi tên tam giác rỗng (▷) tự động hiển thị phía PaymentDAO.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> HomeFrm xuất hiện:
  một lựa chọn để thống kê doanh thu -> subRevenueStat

Chọn Revenue statistics -> RevenueStatFrm xuất hiện:
  combobox chọn kiểu thống kê (Tháng/Quý/Năm) -> inStatType
  bảng thống kê doanh thu (click được) -> outsubRevenueTable
  bảng chi tiết hóa đơn -> outDetailTable
  tổng doanh thu -> outTotalRevenue

Chọn "Tháng" từ combobox -> cần phương thức:
  tên: getRevenueByMonth()
  đầu vào: không có (lấy 12 tháng gần nhất)
  đầu ra: List<Object[]> (monthYear, totalRevenue)
  gán cho entity class: Payment.

Nhấn vào dòng "04/2026" -> cần phương thức:
  tên: getPaymentsByMonth()
  đầu vào: year, month
  đầu ra: List<Payment>
  gán cho entity class: Payment.

Chuyển sang "Quý" -> cần phương thức:
  tên: getRevenueByQuarter()
  đầu vào: không có
  đầu ra: List<Object[]> (quarter, totalRevenue)
  gán cho entity class: Payment.

Nhấn vào dòng "Quý 2/2026" -> cần phương thức:
  tên: getPaymentsByQuarter()
  đầu vào: year, quarter
  đầu ra: List<Payment>
  gán cho entity class: Payment.

Chuyển sang "Năm" -> cần phương thức:
  tên: getRevenueByYear()
  đầu vào: không có
  đầu ra: List<Object[]> (year, totalRevenue)
  gán cho entity class: Payment.

Nhấn vào dòng "2026" -> cần phương thức:
  tên: getPaymentsByYear()
  đầu vào: year
  đầu ra: List<Payment>
  gán cho entity class: Payment.

### Summary
View classes: HomeFrm, RevenueStatFrm
Methods: getRevenueByMonth(), getPaymentsByMonth(), getRevenueByQuarter(), getPaymentsByQuarter(), getRevenueByYear(), getPaymentsByYear()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subBooking`: nút chọn chức năng Booking (JButton)
- `subImportGoods`: nút chọn Import goods (JButton)
- `subUpdateItems`: nút chọn Update used items (JButton)
- `subCustomerPay`: nút chọn Customer paying (JButton)
- `subRevenueStat`: nút chọn Revenue statistics (JButton)

**RevenueStatFrm:**
- `inStatType`: combobox chọn kiểu thống kê (JComboBox: "Tháng", "Quý", "Năm")
- `outRevenueTable`: bảng thống kê doanh thu theo tháng/quý/năm (JTable) — cột: Tháng/Năm, Tổng doanh thu
- `outDetailTable`: bảng chi tiết hóa đơn khi click vào 1 dòng thống kê (JTable) — cột: Mã HĐ, Tên KH, Tên sân, Ngày giờ, Tổng thanh toán
- `outTotalRevenue`: nhãn tổng doanh thu toàn bộ kỳ thống kê (JLabel)

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| cboStatType | JComboBox | Combobox chọn kiểu thống kê: Tháng, Quý, Năm |
| tblRevenueStat | JTable | Bảng thống kê doanh thu 12 tháng/gần nhất |
| tblInvoiceDetail | JTable | Bảng chi tiết hóa đơn theo tháng/quý/năm được chọn |
| lblTotalRevenue | JLabel | Hiển thị tổng doanh thu toàn bộ kỳ |
| lblDetailTitle | JLabel | Tiêu đề "Chi tiết hóa đơn tháng XX/YYYY" |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| PaymentDAO | `getRevenueByMonth(): ArrayList<Object[]>` | Thống kê doanh thu 12 tháng gần nhất, mỗi dòng gồm: monthYear (String), totalRevenue (double), sắp xếp theo thời gian tăng dần |
| PaymentDAO | `getRevenueByQuarter(): ArrayList<Object[]>` | Thống kê doanh thu theo quý gần nhất |
| PaymentDAO | `getRevenueByYear(): ArrayList<Object[]>` | Thống kê doanh thu theo năm gần nhất |
| PaymentDAO | `getPaymentsByMonth(int year, int month): ArrayList<Payment>` | Lấy chi tiết hóa đơn trong 1 tháng cụ thể, mỗi Payment bao gồm: paymentId, customerName, courtName, paymentDate, totalAmount |
| PaymentDAO | `getPaymentsByQuarter(int year, int quarter): ArrayList<Payment>` | Lấy chi tiết hóa đơn trong 1 quý cụ thể |
| PaymentDAO | `getPaymentsByYear(int year): ArrayList<Payment>` | Lấy chi tiết hóa đơn trong 1 năm cụ thể |

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Court | Entity | id: int (PK), code: String, courtName: String, courtType: String, pricePerSession: double, status: String |
| Customer | Entity | id: int (PK), code: String, customerName: String, phoneNumber: String, address: String |
| Booking | Entity | id: int (PK), bookingDate: Date, startDate: Date, endDate: Date, timeSlot: String, dayOfWeek: String, totalSessions: int, totalAmount: double, deposit: double, status: String, court: Court, customer: Customer |
| BookingSession | Entity | id: int (PK), sessionDate: Date, startTime: String, endTime: String, checkinTime: String, checkoutTime: String, rentAmount: double, status: String, booking: Booking |
| Payment | Entity | id: int (PK), paymentDate: Date, rentTotal: double, productTotal: double, totalAmount: double, depositDeducted: double, finalAmount: double, method: String, status: String, booking: Booking |
| User | Entity | id: int (PK), username: String, password: String, fullName: String, role: String |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Payment | Nguồn dữ liệu chính, dùng để tính tổng doanh thu theo tháng/quý/năm và hiển thị chi tiết hóa đơn |
| Customer | Thông tin khách hàng, hiển thị tên KH trong bảng chi tiết hóa đơn |
| Booking | Liên kết giữa Payment và Customer/Court |
| Court | Thông tin sân, hiển thị tên sân trong bảng chi tiết hóa đơn |
| User | Xác thực đăng nhập |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL, UNIQUE |
| password | varchar(100) | NOT NULL |
| fullName | nvarchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| customerName | nvarchar(100) | NOT NULL |
| phoneNumber | varchar(15) | |
| address | nvarchar(255) | |

**tblCourt:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| courtName | nvarchar(100) | NOT NULL |
| courtType | varchar(50) | NOT NULL |
| pricePerSession | float | NOT NULL |
| status | varchar(20) | NOT NULL |

**tblBooking:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| courtID | int | FOREIGN KEY → tblCourt(ID) |
| customerID | int | FOREIGN KEY → tblCustomer(ID) |
| bookingDate | datetime | NOT NULL |
| startDate | date | NOT NULL |
| endDate | date | NOT NULL |
| timeSlot | varchar(20) | NOT NULL |
| dayOfWeek | varchar(20) | |
| totalSessions | int | NOT NULL |
| totalAmount | float | NOT NULL |
| deposit | float | |
| status | varchar(20) | NOT NULL |

**tblPayment:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| bookingID | int | FOREIGN KEY → tblBooking(ID) |
| paymentDate | datetime | NOT NULL |
| rentTotal | float | NOT NULL |
| productTotal | float | NOT NULL |
| totalAmount | float | NOT NULL |
| depositDeducted | float | DEFAULT 0 |
| finalAmount | float | NOT NULL |
| method | varchar(20) | DEFAULT 'cash' |
| status | varchar(20) | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo package `view.revenue`: chứa LoginFrm, HomeFrm, RevenueStatFrm.
3. Tạo package `model`: chứa Customer, Booking, Court, Payment, User.
4. Tạo package `dao`: chứa UserDAO, PaymentDAO.
5. Vẽ association từ RevenueStatFrm → PaymentDAO.
6. Vẽ dependency từ PaymentDAO → Payment, Customer, Booking, Court (dashed arrow).
7. Thêm kiểu trả về cho phương thức DAO (ArrayList<Object[]>, ArrayList<Payment>).

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:LoginFrm` — boundary
3. `:HomeFrm` — boundary
4. `:RevenueStatFrm` — boundary
5. `:UserDAO` — control
6. `:PaymentDAO` — control

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 6 lifelines theo thứ tự: Staff, LoginFrm, UserDAO, HomeFrm, RevenueStatFrm, PaymentDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho giá trị trả về.
5. Sử dụng `alt` fragment cho nhánh chọn kiểu thống kê (Tháng/Quý/Năm).
6. Sử dụng self-call cho các thao tác nội bộ của form (hiển thị, sắp xếp).

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | `actionPerformed("Login")` — nhập username="staff01", password="******" | synchronous |
| 2 | LoginFrm | UserDAO | `checkLogin("staff01", "******")` | synchronous |
| 3 | UserDAO | LoginFrm | `return true` | return |
| 4 | LoginFrm | HomeFrm | `new HomeFrm().setVisible(true)` | synchronous |
| 5 | Staff | HomeFrm | `actionPerformed("Revenue statistics")` | synchronous |
| 6 | HomeFrm | RevenueStatFrm | `new RevenueStatFrm().setVisible(true)` | synchronous |
| 7 | RevenueStatFrm | RevenueStatFrm | `populateComboBox(["Tháng", "Quý", "Năm"])` | self |
| 8 | Staff | RevenueStatFrm | `selectComboBox("Tháng")` | synchronous |
| 9 | RevenueStatFrm | PaymentDAO | `getRevenueByMonth()` | synchronous |
| 10 | PaymentDAO | PaymentDAO | `SELECT DATE_FORMAT(paymentDate, '%m/%Y'), SUM(totalAmount) FROM tblPayment WHERE paymentDate >= DATE_SUB(NOW(), INTERVAL 12 MONTH) GROUP BY YEAR(paymentDate), MONTH(paymentDate) ORDER BY YEAR(paymentDate), MONTH(paymentDate)` | self |
| 11 | PaymentDAO | RevenueStatFrm | `return ArrayList<Object[]>` (mỗi dòng: monthYear, totalRevenue) | return |
| 12 | RevenueStatFrm | RevenueStatFrm | `displayTable(revenueData)` — hiển thị bảng thống kê 12 tháng | self |
| 13 | RevenueStatFrm | Staff | `hiểnThịBảngThốngKê(bảngThốngKê)` | return |
| 14 | Staff | RevenueStatFrm | `clickRow("04/2026")` | synchronous |
| 15 | RevenueStatFrm | RevenueStatFrm | `extractMonth("04/2026")` → year=2026, month=4 | self |
| 16 | RevenueStatFrm | PaymentDAO | `getPaymentsByMonth(2026, 4)` | synchronous |
| 17 | PaymentDAO | PaymentDAO | `SELECT p.ID, c.customerName, ct.courtName, p.paymentDate, p.totalAmount FROM tblPayment p JOIN tblBooking b ON p.bookingID=b.ID JOIN tblCustomer c ON b.customerID=c.ID JOIN tblCourt ct ON b.courtID=ct.ID WHERE YEAR(p.paymentDate)=2026 AND MONTH(p.paymentDate)=4` | self |
| 18 | PaymentDAO | RevenueStatFrm | `return ArrayList<Payment>` (PAY001, Nguyen Van A, San 1, 05/04, 480000; PAY002, Tran Thi B, San 2, 12/04, 350000) | return |
| 19 | RevenueStatFrm | RevenueStatFrm | `displayDetailTable(paymentList)` — hiển thị bảng chi tiết hóa đơn | self |
| 20 | RevenueStatFrm | Staff | `hiểnThịChiTiếtHóaĐơn(chiTietHóaĐơn)` | return |

### Bảng chi tiết các bước (tiếp — chuyển đổi kiểu thống kê)

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 21 | Staff | RevenueStatFrm | `selectComboBox("Quý")` | synchronous |
| 22 | RevenueStatFrm | PaymentDAO | `getRevenueByQuarter()` | synchronous |
| 23 | PaymentDAO | RevenueStatFrm | `return ArrayList<Object[]>` (Quý 1/2026, Quý 2/2026, ...) | return |
| 24 | RevenueStatFrm | RevenueStatFrm | `displayTable(quarterRevenueData)` | self |
| 25 | Staff | RevenueStatFrm | `clickRow("Quý 2/2026")` | synchronous |
| 26 | RevenueStatFrm | PaymentDAO | `getPaymentsByQuarter(2026, 2)` | synchronous |
| 27 | PaymentDAO | RevenueStatFrm | `return ArrayList<Payment>` | return |
| 28 | RevenueStatFrm | RevenueStatFrm | `displayDetailTable(paymentList)` | self |
| 29 | RevenueStatFrm | Staff | `hiểnThịChiTiếtHóaĐơn(chiTietHóaĐơn)` | return |

### ASCII Sequence Diagram

```
Staff      LoginFrm    UserDAO    HomeFrm    RevenueStatFrm    PaymentDAO
  |            |           |          |              |                |
  |--login---->|           |          |              |                |
  |            |--checkLogin()------>|              |                |
  |            |<--true----|          |              |                |
  |            |--open HomeFrm------>|              |                |
  |            |           |          |              |                |
  |--select "Revenue statistics"---->|              |                |
  |            |           |          |--open Frm--->|                |
  |            |           |          |              |--populate combo|
  |            |           |          |              |                |
  |--select "Tháng"------->|         |              |                |
  |            |           |          |              |--getRevenueByMonth()--->|
  |            |           |          |              |<--List<Object[]>|       |
  |            |           |          |              |--display table |       |
  |            |           |          |              |                |       |
  |--click "04/2026"------>|         |              |                |       |
  |            |           |          |              |--getPaymentsByMonth(2026,4)->|
  |            |           |          |              |<--List<Payment>|       |
  |            |           |          |              |--display detail|       |
  |            |           |          |              |                |       |
  |--select "Quý"--------->|         |              |                |       |
  |            |           |          |              |--getRevenueByQuarter()-->|
  |            |           |          |              |<--List<Object[]>|       |
  |            |           |          |              |--display table |       |
  |            |           |          |              |                |       |
  |--click "Quý 2/2026"--->|         |              |                |       |
  |            |           |          |              |--getPaymentsByQuarter(2026,2)->|
  |            |           |          |              |<--List<Payment>|       |
  |            |           |          |              |--display detail|       |
  |            |           |          |              |                |       |
```

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Revenue statistics | Thống kê theo tháng và xem chi tiết hóa đơn thành công |
| TC02 | Revenue statistics | Thống kê theo quý |
| TC03 | Revenue statistics | Tháng không có doanh thu |
| TC04 | Revenue statistics | Thay đổi kiểu thống kê từ tháng sang năm |

### TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn thành công

**Purpose:** Kiểm tra quy trình thống kê doanh thu hoàn chỉnh: chọn kiểu thống kê, hiển thị bảng 12 tháng, click xem chi tiết hóa đơn.

**Database trước khi test:**

**tblUser:**
| ID | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | staff01 | 123456 | Nguyen Thi Staff | staff |

**tblCustomer:**
| ID | code | customerName | phoneNumber | address |
|----|------|-------------|-------------|---------|
| 1 | KH001 | Nguyen Van A | 0912345678 | Quan 1, TP.HCM |
| 2 | KH002 | Tran Thi B | 0923456789 | Quan 3, TP.HCM |
| 3 | KH003 | Le Van C | 0934567890 | Quan 7, TP.HCM |
| 4 | KH004 | Pham Thi D | 0945678901 | Quan Binh Thanh, TP.HCM |

**tblCourt:**
| ID | code | courtName | courtType | pricePerSession | status |
|----|------|-----------|-----------|-----------------|--------|
| 1 | S1 | San 1 | San nho | 200000 | active |
| 2 | S2 | San 2 | San nho | 200000 | active |
| 3 | S3 | San 3 | San lon (2 san nho) | 350000 | active |

**tblBooking:**
| ID | courtID | customerID | bookingDate | startDate | endDate | timeSlot | dayOfWeek | totalSessions | totalAmount | deposit | status |
|----|---------|------------|-------------|-----------|---------|----------|-----------|---------------|-------------|---------|--------|
| 1 | 1 | 1 | 2026-01-15 | 2026-02-01 | 2026-04-30 | 18:00-19:00 | Thu 6 | 12 | 2400000 | 500000 | Paid |
| 2 | 2 | 2 | 2026-03-01 | 2026-03-15 | 2026-06-15 | 19:00-20:00 | Thu 7 | 12 | 2400000 | 500000 | Paid |
| 3 | 1 | 3 | 2026-04-10 | 2026-05-01 | 2026-07-31 | 18:00-19:00 | Thu 4 | 12 | 2400000 | 500000 | Paid |
| 4 | 3 | 4 | 2026-05-20 | 2026-06-01 | 2026-08-31 | 17:00-18:00 | Thu 6 | 12 | 4200000 | 800000 | Paid |

**tblPayment:**
| ID | bookingID | paymentDate | rentTotal | productTotal | totalAmount | depositDeducted | finalAmount | method | status |
|----|-----------|-------------|-----------|--------------|-------------|-----------------|-------------|--------|--------|
| 1 | 1 | 2026-04-05 10:30 | 2000000 | 400000 | 2400000 | 500000 | 1900000 | cash | completed |
| 2 | 2 | 2026-06-20 14:00 | 2000000 | 350000 | 2350000 | 500000 | 1850000 | card | completed |
| 3 | 3 | 2026-05-25 16:30 | 1800000 | 280000 | 2080000 | 500000 | 1580000 | cash | completed |
| 4 | 4 | 2026-06-08 11:00 | 3500000 | 600000 | 4100000 | 800000 | 3300000 | card | completed |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập staff01/123456, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 3 | Chọn menu "Revenue statistics" | Giao diện thống kê hiển thị, combobox mặc định rỗng |
| 4 | Chọn "Tháng" từ combobox | Hệ thống truy vấn dữ liệu 12 tháng gần nhất |
| 5 | Bảng thống kê hiển thị 12 dòng | 04/2026: 1,900,000đ; 05/2026: 1,580,000đ; 06/2026: 5,150,000đ (1,850,000 + 3,300,000); các tháng khác: 0đ |
| 6 | Click vào dòng "06/2026" | Bảng chi tiết hiển thị bên dưới |
| 7 | Bảng chi tiết hiển thị 2 dòng | PAY002 — Tran Thi B — San 2 — 20/06/2026 14:00 — 1,850,000đ; PAY004 — Pham Thi D — San 3 — 08/06/2026 11:00 — 3,300,000đ |
| 8 | Click vào dòng "04/2026" | Bảng chi tiết cập nhật: PAY001 — Nguyen Van A — San 1 — 05/04/2026 10:30 — 1,900,000đ |

**Database sau khi test:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).

---

### TC02: Thống kê theo quý

**Purpose:** Kiểm tra chức năng thống kê khi chọn kiểu "Quý".

**Database trước khi test:**
- tblPayment: cùng dữ liệu như TC01 (4 bản ghi: 04/2026, 05/2026, 06/2026 × 2)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công, chọn "Revenue statistics" | Giao diện thống kê hiển thị |
| 2 | Chọn "Quý" từ combobox | Hệ thống truy vấn dữ liệu theo quý |
| 3 | Bảng thống kê hiển thị | Quý 2/2026 (04+05+06): 8,630,000đ (1,900,000 + 1,580,000 + 5,150,000). Các quý khác: 0đ |
| 4 | Click vào "Quý 2/2026" | Bảng chi tiết hiển thị 4 hóa đơn: PAY001 (1,900,000đ), PAY003 (1,580,000đ), PAY002 (1,850,000đ), PAY004 (3,300,000đ) |

**Database sau khi test:**
- Không có thay đổi (chức năng chỉ đọc).

---

### TC03: Tháng không có doanh thu

**Purpose:** Kiểm tra hệ thống hiển thị đúng khi click vào tháng không có hóa đơn.

**Database trước khi test:**
- tblPayment: chỉ có 1 bản ghi PAY001 (paymentDate=2026-04-05, totalAmount=1,900,000đ). Không có payment nào trong tháng 03/2026.

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập, chọn "Revenue statistics" → "Tháng" | Bảng thống kê hiển thị |
| 2 | Bảng hiển thị | 03/2026: 0đ; 04/2026: 1,900,000đ; các tháng khác: 0đ |
| 3 | Click vào dòng "03/2026" | Bảng chi tiết trống, hiển thị thông báo "Khong co hoa don trong thang nay" |

**Database sau khi test:**
- Không có thay đổi (chức năng chỉ đọc).

---

### TC04: Thay đổi kiểu thống kê từ tháng sang năm

**Purpose:** Kiểm tra hệ thống cập nhật bảng thống kê khi thay đổi kiểu thống kê.

**Database trước khi test:**
- tblPayment: PAY001 (04/2026, 1,900,000đ), PAY002 (06/2026, 1,850,000đ), PAY003 (05/2026, 1,580,000đ), PAY004 (06/2026, 3,300,000đ)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập, chọn "Revenue statistics" | Giao diện thống kê hiển thị |
| 2 | Chọn "Tháng" | Bảng hiển thị: 04/2026 (1,900,000đ), 05/2026 (1,580,000đ), 06/2026 (5,150,000đ) |
| 3 | Chuyển sang "Năm" | Bảng cập nhật: 2026: 8,630,000đ (tổng cả năm) |
| 4 | Click "2026" | Bảng chi tiết hiển thị 4 hóa đơn |

**Database sau khi test:**
- Không có thay đổi (chức năng chỉ đọc).
