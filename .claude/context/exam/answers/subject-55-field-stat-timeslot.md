# Subject No. 55 — Mini Football Field — Module "Statistics of time slot"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thống kê khung giờ được thuê nhiều nhất

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sân bóng đá mini cho phép nhân viên thống kê các khung giờ được khách hàng đặt thuê nhiều nhất trong một khoảng thời gian. Nhân viên đăng nhập, chọn chức năng thống kê, nhập ngày bắt đầu và ngày kết thúc. Hệ thống truy vấn dữ liệu đặt sân, tính tổng số lượt đặt và tổng doanh thu cho từng khung giờ, hiển thị bảng danh sách khung giờ sắp xếp giảm dần theo tổng số lượt đặt, sau đó giảm dần theo tổng doanh thu. Khi nhân viên click vào một khung giờ, hệ thống hiển thị danh sách chi tiết các lượt đặt sân trong khung giờ đó.

### Trích xuất danh từ

| Danh từ | Phân loại |
|---------|-----------|
| Nhân viên (Staff) | Actor |
| Hệ thống | Boundary |
| Khung giờ (time slot) | Attribute |
| Ngày bắt đầu, ngày kết thúc | Attribute |
| Sân bóng (Court) | Entity |
| Khách hàng (Customer) | Entity |
| Đặt sân (Booking) | Entity |
| Phiên đặt sân (BookingSession) | Entity |
| Thanh toán (Payment) | Entity |
| Tổng số lượt đặt | Attribute |
| Tổng doanh thu | Attribute |

### Kịch bản chính (Main scenario)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống với username "staff01" và password "123456" |
| 2 | Hệ thống xác thực thành công, hiển thị trang chủ với các menu chức năng |
| 3 | Staff chọn menu **"Statistics of time slot"** |
| 4 | Hệ thống hiển thị giao diện thống kê với ô nhập ngày bắt đầu (`inStartDate`) và ngày kết thúc (`inEndDate`) |
| 5 | Staff nhập ngày bắt đầu `01/01/2026` vào ô `inStartDate` |
| 6 | Staff nhập ngày kết thức `31/03/2026` vào ô `inEndDate` |
| 7 | Staff nhấn nút **Search** (`subSearch`) |
| 8 | Hệ thống truy vấn dữ liệu từ bảng tblBookingSession kết hợp tblBooking và tblPayment theo khoảng thời gian 01/01/2026 - 31/03/2026, nhóm theo khung giờ, tính tổng số lượt đặt và tổng doanh thu |
| 9 | Hệ thống hiển thị bảng danh sách khung giờ (`outTimeSlotTable`), mỗi dòng gồm: khung giờ, ngày, tổng số lượt đặt, tổng doanh thu. Sắp xếp giảm dần theo tổng số lượt đặt, sau đó giảm dần theo tổng doanh thu |
| 10 | Staff click vào dòng khung giờ **"18:00-19:00"** trong bảng |
| 11 | Hệ thống hiển thị bảng chi tiết đặt sân (`outBookingDetailTable`) của khung giờ 18:00-19:00, mỗi dòng gồm: mã đặt sân, tên khách hàng, tên sân, ngày/giờ, đơn giá, tổng tiền |
| 12 | Staff xem chi tiết và thoát khỏi chức năng |

### Kịch bản ngoại lệ (Exception scenario)

| Bước | Diễn biến |
|------|-----------|
| 7a | Staff nhấn nút Search mà chưa nhập ngày bắt đầu hoặc ngày kết thúc |
| 7a.1 | Hệ thống hiển thị thông báo lỗi "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc" |
| 7b | Ngày bắt đầu lớn hơn ngày kết thúc |
| 7b.1 | Hệ thống hiển thị thông báo lỗi "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc" |
| 7c | Không có dữ liệu đặt sân trong khoảng thời gian đã nhập |
| 7c.1 | Hệ thống hiển thị bảng rỗng với thông báo "Không có dữ liệu trong khoảng thời gian này" |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sân bóng đá mini. Mỗi sân bóng có mã sân, tên sân, loại sân (5 người, 7 người), giá thuê mỗi giờ và trạng thái. Khách hàng đăng ký thuê sân, lưu thông tin mã khách hàng, tên, số điện thoại, email, địa chỉ. Mỗi lần đặt sân tạo một phiếu đặt (Booking) liên kết với khách hàng, gồm ngày đặt, tổng tiền, trạng thái. Phiếu đặt chứa nhiều phiên đặt sân (BookingSession), mỗi phiên liên kết với một sân cụ thể, ghi nhận ngày giờ bắt đầu/kết thúc, giá thuê. Khách hàng thanh toán qua phiếu thanh toán (Payment) liên kết với phiếu đặt. Nhân viên sử dụng hệ thống với tài khoản User.

### Trích xuất danh từ và phân loại

| Danh từ | Phân loại | Ghi chú |
|---------|-----------|---------|
| Sân bóng (Court) | Entity | Đối tượng chính |
| Khách hàng (Customer) | Entity | Đối tượng chính |
| Phiếu đặt sân (Booking) | Entity | Đối tượng chính |
| Phiên đặt sân (BookingSession) | Entity | Đối tượng chính |
| Thanh toán (Payment) | Entity | Đối tượng chính |
| Tài khoản (User) | Entity | Đối tượng chính |
| Mã sân, tên sân, loại sân, giá mỗi giờ, trạng thái | Attribute | Thuộc tính Court |
| Mã KH, tên, SĐT, email, địa chỉ | Attribute | Thuộc tính Customer |
| Mã đặt, ngày đặt, tổng tiền, trạng thái | Attribute | Thuộc tính Booking |
| Mã phiên, ngày, giờ bắt đầu, giờ kết thúc, giá thuê | Attribute | Thuộc tính BookingSession |
| Mã thanh toán, ngày thanh toán, số tiền, phương thức | Attribute | Thuộc tính Payment |
| Username, password, họ tên, vai trò | Attribute | Thuộc tính User |

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu quan hệ | Mô tả |
|-----------|----------|---------------|--------|
| Customer | Booking | 1 --- n | Một khách hàng có nhiều phiếu đặt sân |
| Booking | BookingSession | 1 --- n | Một phiếu đặt chứa nhiều phiên đặt sân |
| Court | BookingSession | 1 --- n | Một sân được sử dụng trong nhiều phiên đặt |
| Booking | Payment | 1 --- n | Một phiếu đặt có nhiều phiếu thanh toán |

### ASCII Class Diagram

```
+------------------+          +------------------+
|     Customer     |          |       Court      |
+------------------+          +------------------+
| customerId (PK)  |          | courtId (PK)     |
| customerName     |          | courtName        |
| phoneNumber      |          | courtType        |
| email            |          | pricePerHour     |
| address          |          | status           |
+--------+---------+          +--------+---------+
         |                             |
         | 1                           | 1
         |                             |
         | n                           | n
+--------+---------+          +--------+---------+
|     Booking      |          | BookingSession   |
+------------------+          +------------------+
| bookingId (PK)   |--- 1 ---| sessionId (PK)   |
| customerId (FK)  |          | bookingId (FK)   |
| bookingDate      |          | courtId (FK)     |
| totalAmount      |          | sessionDate      |
| status           |          | startTime        |
+--------+---------+          | endTime          |
         |                    | rentAmount       |
         | 1                  | status           |
         |                    +------------------+
         | n
+--------+---------+
|     Payment      |
+------------------+
| paymentId (PK)   |
| bookingId (FK)   |
| paymentDate      |
| amount           |
| method           |
| status           |
+------------------+

+------------------+
|       User       |
+------------------+
| userId (PK)      |
| username         |
| password         |
| fullName         |
| role             |
+------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**Các bước vẽ tổng quan:**

1. Mở Visual Paradigm → New → Class Diagram (trong danh mục Diagrams).
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity: Customer, Court, Booking, BookingSession, Payment, User.
3. Tạo view class boxes từ các interface: HomeFrm, StatisticTimeSlotFrm.
4. Vẽ relationships giữa các class theo bảng quan hệ bên dưới.
5. Thêm multiplicities và role names cho mỗi đường kết nối.

**Cấu trúc 1 class box (3 ngăn):**

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> BookingSession`.
- **Ngăn 2 (thuộc tính):** Mỗi thuộc tính ghi dạng `-attributeName: Type`. Ví dụ: `-sessionDate: Date`, `-startTime: String`.
- **Ngăn 3 (phương thức):** Mỗi phương thức ghi dạng `+methodName(params): ReturnType`. Ví dụ: `+getTimeSlotStatistics(startDate: Date, endDate: Date): List<Object[]>`.

**Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Customer | `<<entity>>` | -id: int, -customerName: String, -phoneNumber: String, -email: String, -address: String | getter/setter |
| Court | `<<entity>>` | -id: int, -courtName: String, -courtType: String, -pricePerHour: double, -status: String | getter/setter |
| Booking | `<<entity>>` | -id: int, -bookingDate: Date, -totalAmount: double, -status: String | getter/setter |
| BookingSession | `<<entity>>` | -id: int, -sessionDate: Date, -startTime: String, -endTime: String, -rentAmount: double, -status: String | +getTimeSlotStatistics(startDate: Date, endDate: Date): List<Object[]>, +getBookingDetailsByTimeSlot(timeSlot: String, startDate: Date, endDate: Date): List<Object[]> |
| Payment | `<<entity>>` | -id: int, -paymentDate: Date, -amount: double, -method: String, -status: String | getter/setter |
| User | `<<entity>>` | -id: int, -username: String, -password: String, -fullName: String, -role: String | +checkLogin(username: String, password: String): boolean |

**Bảng chi tiết view classes:**

| View class | Stereotype | UI Elements |
|------------|-----------|-------------|
| HomeFrm | `<<boundary>>` | subStatisticTimeSlot: JButton |
| StatisticTimeSlotFrm | `<<boundary>>` | inStartDate: JDateChooser, inEndDate: JDateChooser, subSearch: JButton, outsubTimeSlotTable: JTable (clickable), outBookingDetailTable: JTable |

**Cách vẽ quan hệ:**

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Booking.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Booking ◆→ BookingSession.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: StatisticTimeSlotFrm → BookingSessionDAO.

**Cách ghi multiplicity:**

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Customer "1" --- "n" Booking.

**Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|--------------|------------|
| Customer | Booking | Association | 1 — n | Một khách hàng có nhiều phiếu đặt sân |
| Booking | BookingSession | Composition | 1 — n | Một phiếu đặt chứa nhiều phiên đặt sân, phiên không tồn tại nếu không có phiếu đặt |
| Court | BookingSession | Association | 1 — n | Một sân được sử dụng trong nhiều phiên đặt |
| Booking | Payment | Association | 1 — n | Một phiếu đặt có nhiều phiếu thanh toán |
| StatisticTimeSlotFrm | BookingSessionDAO | Dependency | — | Frm sử dụng BookingSessionDAO để thống kê khung giờ |

**Ví dụ cụ thể trên Visual Paradigm:**

1. **Vẽ quan hệ Booking → BookingSession (Composition 1-n):**
   - Kéo class Booking lên canvas, kéo class BookingSession bên dưới.
   - Chọn tool "Association" → click vào Booking, kéo đến BookingSession.
   - Đặt multiplicity "1" phía Booking, "n" phía BookingSession.
   - Click chuột phải → "Association End" → phía Booking đặt "filled diamond" (◆).

2. **Vẽ dependency StatisticTimeSlotFrm → BookingSessionDAO:**
   - Chọn tool "Dependency" (đường dashed) → click vào StatisticTimeSlotFrm, kéo đến BookingSessionDAO.
   - Mũi tên tam giác rỗng (▷) tự động hiển thị phía BookingSessionDAO.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> HomeFrm xuất hiện:
  một lựa chọn để thống kê khung giờ -> subStatisticTimeSlot

Chọn Statistics of time slot -> StatisticTimeSlotFrm xuất hiện:
  ô nhập ngày bắt đầu -> inStartDate
  ô nhập ngày kết thức -> inEndDate
  nút Search -> subSearch
  bảng thống kê khung giờ (click được) -> outsubTimeSlotTable
  bảng chi tiết đặt sân -> outBookingDetailTable

Nhập ngày bắt đầu, ngày kết thúc, nhấn Search -> cần phương thức:
  tên: getTimeSlotStatistics()
  đầu vào: startDate, endDate
  đầu ra: List<Object[]> (timeSlot, sessionDate, totalBookings, totalRevenue)
  gán cho entity class: BookingSession.

Nhấn vào dòng "18:00-19:00" -> cần phương thức:
  tên: getBookingDetailsByTimeSlot()
  đầu vào: timeSlot, startDate, endDate
  đầu ra: List<Object[]> (bookingId, customerName, courtName, sessionDate, startTime, rentAmount)
  gán cho entity class: BookingSession.

### Summary
View classes: HomeFrm, StatisticTimeSlotFrm
Methods: getTimeSlotStatistics(), getBookingDetailsByTimeSlot()

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatisticTimeSlotFrm:**
- `inStartDate`: Ô nhập ngày bắt đầu thống kê (JDateChooser)
- `inEndDate`: Ô nhập ngày kết thúc thống kê (JDateChooser)
- `subSearch`: Nút "Search" để thực hiện thống kê
- `outTimeSlotTable`: Bảng danh sách khung giờ thống kê (JTable), mỗi dòng: khung giờ, ngày, tổng số lượt đặt, tổng doanh thu, sắp xếp giảm dần theo lượt đặt rồi doanh thu
- `outBookingDetailTable`: Bảng chi tiết đặt sân khi click vào 1 dòng khung giờ (JTable), mỗi dòng: mã đặt sân, tên khách hàng, tên sân, ngày/giờ, đơn giá, tổng tiền

**HomeFrm:**
- `subStatisticTimeSlot`: Nút/menu "Statistics of time slot" để mở giao diện thống kê

### UI Elements

| UI Element | Loại | Vai trò |
|-----------|------|---------|
| inStartDate | JDateChooser | Nhập ngày bắt đầu |
| inEndDate | JDateChooser | Nhập ngày kết thúc |
| subSearch | JButton | Kích hoạt thống kê |
| outTimeSlotTable | JTable + JScrollPane | Hiển thị bảng thống kê khung giờ |
| outBookingDetailTable | JTable + JScrollPane | Hiển thị chi tiết đặt sân theo khung giờ |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| BookingSessionDAO | `getTimeSlotStatistics(Date startDate, Date endDate): List<Object[]>` | Thống kê khung giờ theo khoảng thời gian, trả về danh sách mỗi dòng gồm timeSlot, sessionDate, totalBookings, totalRevenue, sắp xếp giảm dần theo totalBookings rồi totalRevenue |
| BookingSessionDAO | `getBookingDetailsByTimeSlot(String timeSlot, Date startDate, Date endDate): List<Object[]>` | Lấy chi tiết đặt sân theo khung giờ và khoảng thời gian, trả về danh sách mỗi dòng gồm bookingId, customerName, courtName, sessionDate, startTime, rentAmount |

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Customer | Entity | id: int (PK), customerName: String, phoneNumber: String, email: String, address: String |
| Court | Entity | id: int (PK), courtName: String, courtType: String, pricePerHour: double, status: String |
| Booking | Entity | id: int (PK), bookingDate: Date, totalAmount: double, status: String, customer: Customer |
| BookingSession | Entity | id: int (PK), sessionDate: Date, startTime: String, endTime: String, rentAmount: double, status: String, booking: Booking, court: Court |
| Payment | Entity | id: int (PK), paymentDate: Date, amount: double, method: String, status: String, booking: Booking |
| User | Entity | id: int (PK), username: String, password: String, fullName: String, role: String |

### Entity types

| Entity | Kiểu | Attributes |
|--------|------|------------|
| BookingSession | Entity | sessionId (int), bookingId (int), courtId (int), sessionDate (Date), startTime (String), endTime (String), rentAmount (double), status (String) |
| Booking | Entity | bookingId (int), customerId (int), bookingDate (Date), totalAmount (double), status (String) |
| Customer | Entity | customerId (int), customerName (String), phoneNumber (String), email (String), address (String) |
| Court | Entity | courtId (int), courtName (String), courtType (String), pricePerHour (double), status (String) |
| Payment | Entity | paymentId (int), bookingId (int), paymentDate (Date), amount (double), method (String), status (String) |

### Database schema

**tblBookingSession:**
| Column | Type | Constraint |
|--------|------|------------|
| sessionId | INT | PK |
| bookingId | INT | FK → tblBooking |
| courtId | INT | FK → tblCourt |
| sessionDate | DATE | NOT NULL |
| startTime | VARCHAR(10) | NOT NULL |
| endTime | VARCHAR(10) | NOT NULL |
| rentAmount | DECIMAL(10,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblBooking:**
| Column | Type | Constraint |
|--------|------|------------|
| bookingId | INT | PK |
| customerId | INT | FK → tblCustomer |
| bookingDate | DATE | NOT NULL |
| totalAmount | DECIMAL(12,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblCourt:**
| Column | Type | Constraint |
|--------|------|------------|
| courtId | INT | PK |
| courtName | VARCHAR(50) | NOT NULL |
| courtType | VARCHAR(20) | NOT NULL |
| pricePerHour | DECIMAL(10,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| customerId | INT | PK |
| customerName | VARCHAR(100) | NOT NULL |
| phoneNumber | VARCHAR(15) | |
| email | VARCHAR(100) | |
| address | VARCHAR(200) | |

**tblPayment:**
| Column | Type | Constraint |
|--------|------|------------|
| paymentId | INT | PK |
| bookingId | INT | FK → tblBooking |
| paymentDate | DATE | NOT NULL |
| amount | DECIMAL(12,2) | NOT NULL |
| method | VARCHAR(20) | |
| status | VARCHAR(20) | NOT NULL |

### Visual Paradigm Guide — Static Design

Trong Visual Paradigm, tạo Class Diagram với:
1. Tạo 5 entity classes: Customer, Booking, BookingSession, Court, Payment — mỗi class có attributes như trên
2. Tạo 1 view class: StatisticTimeSlotFrm với các attributes inStartDate, inEndDate, subSearch, outTimeSlotTable, outBookingDetailTable
3. Tạo 2 DAO classes: BookingSessionDAO với 2 phương thức getTimeSlotStatistics và getBookingDetailsByTimeSlot
4. Vẽ mối quan hệ: Customer -- Booking (1-n), Booking -- BookingSession (1-n), Court -- BookingSession (1-n), Booking -- Payment (1-n)
5. Thêm dependency mũi tên từ StatisticTimeSlotFrm → BookingSessionDAO

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

**Tạo Sequence Diagram trong Visual Paradigm:**
1. Tạo 3 lifelines: `:Staff` (actor), `:StatisticTimeSlotFrm` (boundary), `:BookingSessionDAO` (control)
2. Thêm message arrows theo thứ tự bên dưới
3. Sử dụng return arrows (dashed) cho các message trả về
4. Thêm alt fragment cho trường hợp ngoại lệ (nhập thiếu ngày)

### ASCII Sequence Diagram

```
  Staff              StatisticTimeSlotFrm        BookingSessionDAO           Database
    |                          |                          |                      |
    |  selectMenu(             |                          |                      |
    |  "Statistics of          |                          |                      |
    |   time slot")            |                          |                      |
    |------------------------->|                          |                      |
    |                          |                          |                      |
    |  show(inStartDate,       |                          |                      |
    |       inEndDate)         |                          |                      |
    |<-------------------------|                          |                      |
    |                          |                          |                      |
    |  enter(01/01/2026,       |                          |                      |
    |        31/03/2026)       |                          |                      |
    |  click(subSearch)        |                          |                      |
    |------------------------->|                          |                      |
    |                          |                          |                      |
    |                          |  getTimeSlotStatistics(  |                      |
    |                          |    startDate, endDate)   |                      |
    |                          |------------------------->|                      |
    |                          |                          |                      |
    |                          |                          |  SELECT timeSlot,    |
    |                          |                          |  sessionDate,        |
    |                          |                          |  COUNT(*), SUM(...)  |
    |                          |                          |  GROUP BY timeSlot   |
    |                          |                          |  ORDER BY ... DESC   |
    |                          |                          |--------------------->|
    |                          |                          |                      |
    |                          |                          |  ResultSet           |
    |                          |                          |<---------------------|
    |                          |                          |                      |
    |                          |  List<Object[]>          |                      |
    |                          |<-------------------------|                      |
    |                          |                          |                      |
    |  show(outTimeSlotTable)  |                          |                      |
    |<-------------------------|                          |                      |
    |                          |                          |                      |
    |  click("18:00-19:00")    |                          |                      |
    |------------------------->|                          |                      |
    |                          |                          |                      |
    |                          |  getBookingDetailsByTimeSlot(                   |
    |                          |    "18:00-19:00",        |                      |
    |                          |    startDate, endDate)   |                      |
    |                          |------------------------->|                      |
    |                          |                          |                      |
    |                          |                          |  SELECT bookingId,   |
    |                          |                          |  customerName,       |
    |                          |                          |  courtName, ...      |
    |                          |                          |  WHERE timeSlot = ?  |
    |                          |                          |--------------------->|
    |                          |                          |                      |
    |                          |                          |  ResultSet           |
    |                          |                          |<---------------------|
    |                          |                          |                      |
    |                          |  List<Object[]>          |                      |
    |                          |<-------------------------|                      |
    |                          |                          |                      |
    |  show(outBookingDetail   |                          |                      |
    |       Table)             |                          |                      |
    |<-------------------------|                          |                      |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | StatisticTimeSlotFrm | `selectMenu("Statistics of time slot")` | Staff chọn menu chức năng |
| 2 | StatisticTimeSlotFrm | Staff | `show(inStartDate, inEndDate)` | Hiển thị giao diện nhập ngày |
| 3 | Staff | StatisticTimeSlotFrm | `enter(inStartDate="01/01/2026")` | Nhập ngày bắt đầu |
| 4 | Staff | StatisticTimeSlotFrm | `enter(inEndDate="31/03/2026")` | Nhập ngày kết thúc |
| 5 | Staff | StatisticTimeSlotFrm | `click(subSearch)` | Nhấn nút Search |
| 6 | StatisticTimeSlotFrm | StatisticTimeSlotFrm | `validateInput(startDate, endDate)` | Kiểm tra dữ liệu nhập |
| 7 | StatisticTimeSlotFrm | BookingSessionDAO | `getTimeSlotStatistics(startDate, endDate)` | Gọi DAO thống kê |
| 8 | BookingSessionDAO | Database | `SELECT timeSlot, sessionDate, COUNT(*), SUM(rentAmount) FROM tblBookingSession bs JOIN tblBooking b ON bs.bookingId=b.bookingId WHERE sessionDate BETWEEN ? AND ? GROUP BY timeSlot, sessionDate ORDER BY COUNT(*) DESC, SUM(rentAmount) DESC` | Truy vấn SQL |
| 9 | Database | BookingSessionDAO | `ResultSet` | Trả về dữ liệu thô |
| 10 | BookingSessionDAO | StatisticTimeSlotFrm | `List<Object[]>` | Trả về danh sách thống kê |
| 11 | StatisticTimeSlotFrm | Staff | `show(outTimeSlotTable)` | Hiển thị bảng thống kê |
| 12 | Staff | StatisticTimeSlotFrm | `clickRow("18:00-19:00")` | Click vào 1 dòng khung giờ |
| 13 | StatisticTimeSlotFrm | BookingSessionDAO | `getBookingDetailsByTimeSlot("18:00-19:00", startDate, endDate)` | Gọi DAO lấy chi tiết |
| 14 | BookingSessionDAO | Database | `SELECT bs.bookingId, c.customerName, ct.courtName, bs.sessionDate, bs.startTime, bs.rentAmount FROM tblBookingSession bs JOIN tblBooking b ON bs.bookingId=b.bookingId JOIN tblCustomer c ON b.customerId=c.customerId JOIN tblCourt ct ON bs.courtId=ct.courtId WHERE bs.startTime='18:00' AND bs.sessionDate BETWEEN ? AND ?` | Truy vấn SQL |
| 15 | Database | BookingSessionDAO | `ResultSet` | Trả về dữ liệu thô |
| 16 | BookingSessionDAO | StatisticTimeSlotFrm | `List<Object[]>` | Trả về danh sách chi tiết |
| 17 | StatisticTimeSlotFrm | Staff | `show(outBookingDetailTable)` | Hiển thị bảng chi tiết đặt sân |
| 18 | Staff | StatisticTimeSlotFrm | `clickRow(bookingId="B001")` | (Optional) Click xem chi tiết 1 booking |
| 19 | StatisticTimeSlotFrm | BookingSessionDAO | `getBookingById(bookingId)` | Lấy thông tin booking chi tiết |
| 20 | BookingSessionDAO | Database | `SELECT * FROM tblBooking WHERE bookingId = ?` | Truy vấn SQL |
| 21 | Database | BookingSessionDAO | `ResultSet` | Trả về dữ liệu |
| 22 | BookingSessionDAO | StatisticTimeSlotFrm | `Booking` | Trả về đối tượng Booking |
| 23 | StatisticTimeSlotFrm | Staff | `showBookingDetails(booking)` | Hiển thị chi tiết booking |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê khung giờ thành công và xem chi tiết đặt sân

**Database trước:**

tblBooking:
| bookingId | customerId | bookingDate | totalAmount | status |
|-----------|-----------|-------------|-------------|--------|
| B001 | C001 | 2026-01-10 | 500000 | Completed |
| B002 | C002 | 2026-01-15 | 600000 | Completed |
| B003 | C003 | 2026-02-20 | 450000 | Completed |
| B004 | C001 | 2026-03-05 | 300000 | Completed |

tblBookingSession:
| sessionId | bookingId | courtId | sessionDate | startTime | endTime | rentAmount | status |
|-----------|-----------|---------|-------------|-----------|---------|------------|--------|
| S001 | B001 | CT01 | 2026-01-10 | 18:00 | 19:00 | 250000 | Completed |
| S002 | B001 | CT01 | 2026-01-10 | 19:00 | 20:00 | 250000 | Completed |
| S003 | B002 | CT02 | 2026-01-15 | 18:00 | 19:00 | 300000 | Completed |
| S004 | B002 | CT02 | 2026-01-15 | 19:00 | 20:00 | 300000 | Completed |
| S005 | B003 | CT01 | 2026-02-20 | 18:00 | 19:00 | 250000 | Completed |
| S006 | B003 | CT03 | 2026-02-20 | 20:00 | 21:00 | 200000 | Completed |
| S007 | B004 | CT02 | 2026-03-05 | 19:00 | 20:00 | 300000 | Completed |

tblCustomer:
| customerId | customerName | phoneNumber | email | address |
|-----------|-------------|-------------|-------|---------|
| C001 | Nguyen Van A | 0901234567 | nva@gmail.com | Ha Noi |
| C002 | Tran Van B | 0912345678 | tvb@gmail.com | HCM |
| C003 | Le Thi C | 0923456789 | ltc@gmail.com | Da Nang |

tblCourt:
| courtId | courtName | courtType | pricePerHour | status |
|---------|-----------|-----------|--------------|--------|
| CT01 | San A | San 5 nguoi | 250000 | Active |
| CT02 | San B | San 7 nguoi | 300000 | Active |
| CT03 | San C | San 5 nguoi | 200000 | Active |

tblPayment:
| paymentId | bookingId | paymentDate | amount | method | status |
|-----------|-----------|-------------|--------|--------|--------|
| P001 | B001 | 2026-01-10 | 500000 | Cash | Completed |
| P002 | B002 | 2026-01-15 | 600000 | Cash | Completed |
| P003 | B003 | 2026-02-20 | 450000 | Transfer | Completed |
| P004 | B004 | 2026-03-05 | 300000 | Cash | Completed |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công với username "staff01", hiển thị trang chủ |
| 2 | Staff chọn menu "Statistics of time slot" — Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút Search |
| 3 | Staff nhập ngày bắt đầu "01/01/2026", ngày kết thúc "31/03/2026" |
| 4 | Staff nhấn nút Search — Hệ thống truy vấn dữ liệu và hiển thị bảng thống kê |
| 5 | Bảng hiển thị 3 khung giờ: "18:00-19:00" (3 lượt đặt, 800,000đ), "19:00-20:00" (3 lượt đặt, 850,000đ), "20:00-21:00" (1 lượt đặt, 200,000đ). Sắp xếp: 19:00-20:00 đứng đầu (3 lượt, 850,000đ), 18:00-19:00 thứ 2 (3 lượt, 800,000đ), 20:00-21:00 thứ 3 (1 lượt, 200,000đ) |
| 6 | Staff click vào dòng "19:00-20:00" |
| 7 | Hệ thống hiển thị bảng chi tiết 3 dòng: B001 (Nguyen Van A, San A, 10/01/2026 19:00, 250,000đ), B002 (Tran Van B, San B, 15/01/2026 19:00, 300,000đ), B004 (Nguyen Van A, San B, 05/03/2026 19:00, 300,000đ) |

**Database sau:**
- Không thay đổi (chức năng chỉ đọc dữ liệu thống kê, không có thao tác ghi)

---

### TC02: Nhập thiếu ngày kết thúc

**Database trước:**

tblBooking:
| bookingId | customerId | bookingDate | totalAmount | status |
|-----------|-----------|-------------|-------------|--------|
| B001 | C001 | 2026-01-10 | 500000 | Completed |

tblBookingSession:
| sessionId | bookingId | courtId | sessionDate | startTime | endTime | rentAmount | status |
|-----------|-----------|---------|-------------|-----------|---------|------------|--------|
| S001 | B001 | CT01 | 2026-01-10 | 18:00 | 19:00 | 250000 | Completed |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công |
| 2 | Staff chọn menu "Statistics of time slot" |
| 3 | Staff nhập ngày bắt đầu "01/01/2026" nhưng không nhập ngày kết thúc |
| 4 | Staff nhấn nút Search |
| 5 | Hệ thống hiển thị thông báo lỗi "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc" |

**Database sau:**
- Không thay đổi

---

### TC03: Khoảng thời gian không có dữ liệu

**Database trước:**

tblBooking:
| bookingId | customerId | bookingDate | totalAmount | status |
|-----------|-----------|-------------|-------------|--------|
| B001 | C001 | 2026-01-10 | 500000 | Completed |

tblBookingSession:
| sessionId | bookingId | courtId | sessionDate | startTime | endTime | rentAmount | status |
|-----------|-----------|---------|-------------|-----------|---------|------------|--------|
| S001 | B001 | CT01 | 2026-01-10 | 18:00 | 19:00 | 250000 | Completed |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công |
| 2 | Staff chọn menu "Statistics of time slot" |
| 3 | Staff nhập ngày bắt đầu "01/06/2026", ngày kết thúc "30/06/2026" |
| 4 | Staff nhấn nút Search |
| 5 | Hệ thống hiển thị bảng rỗng với thông báo "Không có dữ liệu trong khoảng thời gian này" |

**Database sau:**
- Không thay đổi
