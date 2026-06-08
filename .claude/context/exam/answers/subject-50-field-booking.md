# Subject No. 50 — Mini Football Field — Module "Booking"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Đặt sân bóng đá mini

### Diễn biến chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện với ô nhập username, password, nút Login. |
| 2 | Staff nhập `staff01`, password `******`, nhấn Login. Hệ thống xác thực, chuyển sang giao diện Home. |
| 3 | Giao diện Home hiển thị các menu: Booking, Update Items, Customer Payment, Statistics. |
| 4 | Staff chọn chức năng **Booking**. |
| 5 | Giao diện tìm sân trống xuất hiện: ô nhập khung giờ (`inTimeSlot`), combobox chọn loại sân (`inCourtType`), nút **Search**. |
| 6 | Staff nhập khung giờ "18:00-19:00", chọn loại sân "Sân nhỏ (5 người)" từ combobox, nhấn **Search**. |
| 7 | Hệ thống truy vấn database, hiển thị danh sách sân trống trong khung giờ đó. Bảng gồm các cột: mã sân, tên sân, sức chứa tối đa, mô tả. |
| 8 | Bảng hiển thị: S1 (Sân 1, 10 người, Sân bên trái), S3 (Sân 3, 10 người, Sân bên phải), S5 (Sân 5, 10 người, Sân sau). |
| 9 | Staff nhấn vào sân "S1" trên bảng. |
| 10 | Giao diện tìm khách hàng xuất hiện: ô nhập tên khách hàng (`inCustomerName`), nút **Search**. |
| 11 | Staff nhập "Nguyen Van A", nhấn **Search**. |
| 12 | Hệ thống hiển thị danh sách khách hàng: mã KH, tên, SĐT, địa chỉ. Staff nhấn vào "KH001 - Nguyen Van A - 0901234567". |
| 13 | Hệ thống hiển thị thông tin phiếu đặt: sân S1, khách hàng Nguyen Van A, ô nhập ngày bắt đầu, ô nhập ngày kết thúc, combobox chọn ngày trong tuần, combobox chọn khung giờ. |
| 14 | Staff nhập ngày bắt đầu `01/07/2026`, ngày kết thức `30/09/2026`, chọn "Thứ 6", chọn "18:00-19:00", nhấn **Confirm**. |
| 15 | Hệ thống tính toán và hiển thị phiếu đặt sân: sân S1, khách hàng Nguyen Van A, Thứ 6 18:00-19:00, 12 tuần, giá 200,000đ/tuần, tổng 2,400,000đ, đặt cọc 500,000đ. |
| 16 | Staff kiểm tra thông tin, nhấn **Confirm** lần cuối. |
| 17 | Hệ thống lưu phiếu đặt vào database, hiển thị thông báo "Dat san thanh cong", in phiếu đặt sân. |

### Kịch bản ngoại lệ

- **EL1:** Không có sân trống trong khung giờ đã chọn → bảng trống, thông báo "Khong co san trong".
- **EL2:** Khách hàng chưa có trong hệ thống → Staff nhấn nút **Add New Customer** để thêm mới (họ tên, SĐT, địa chỉ), sau đó tiếp tục đặt sân.
- **EL3:** Staff nhập ngày kết thúc trước ngày bắt đầu → hệ thống cảnh báo "Ngay ket thuc phai sau ngay bat dau".
- **EL4:** Khung giờ đã được đặt bởi khách hàng khác (race condition) → hệ thống thông báo "San da duoc dat trong khung gio nay".

---

## Câu 2: Entity Class Diagram

### Mô tả tự nhiên

Sân bóng đá mini (Court) có nhiều loại: sân nhỏ 5 người, sân vừa 7 người, sân lớn 11 người (có thể kết hợp 2 hoặc 4 sân nhỏ). Khách hàng (Customer) đặt sân theo khung giờ, mỗi lần đặt tạo một phiếu đặt (Booking) chứa nhiều phiên (BookingSession). Mỗi phiên có thể thuê đồ ăn/thức uống (Product) qua SessionProduct. Nhà cung cấp (Supplier) bán hàng cho sân bóng qua hóa đơn nhập (ImportInvoice). Hóa đơn thanh toán (Payment) ghi nhận tiền thuê và tiền đồ ăn.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Court | Entity class | Sân bóng đá mini |
| Customer | Entity class | Khách hàng đặt sân |
| Booking | Entity class | Phiếu đặt sân của khách hàng |
| BookingSession | Entity class | Phiên chơi cụ thể (mỗi tuần 1 phiên) |
| Product | Entity class | Mặt hàng đồ ăn/thức uống cho thuê |
| SessionProduct | Entity class | Chi tiết đồ ăn/thức uống trong 1 phiên |
| Supplier | Entity class | Nhà cung cấp hàng hóa |
| ImportInvoice | Entity class | Hóa đơn nhập hàng từ nhà cung cấp |
| ImportInvoiceDetail | Entity class | Chi tiết từng mục trong hóa đơn nhập |
| Payment | Entity class | Hóa đơn thanh toán của khách hàng |
| User | Entity class | Nhân viên thao tác trên hệ thống |

### Class Diagram (ASCII)

```
+------------------+       +------------------+       +------------------+
|     Court        |       |    Customer      |       |     User         |
+------------------+       +------------------+       +------------------+
| -id: int         |       | -id: int         |       | -id: int         |
| -code: String    |       | -code: String    |       | -username: String|
| -name: String    |       | -fullName: String|       | -password: String|
| -type: String    |       | -phoneNumber: String|    | -role: String    |
| -maxGuests: int  |       | -address: String |       +------------------+
| -description: String|    +------------------+
+------------------+       | +searchCustomer()|
         | 1               +------------------+
         |                          | 1
         | n                        | n
         v                          v
+------------------+       +------------------+
|    Booking       |       |    Booking       |
+------------------+<------+
| -id: int         |
| -courtId: int    |
| -customerId: int |
| -startDate: Date |
| -endDate: Date   |
| -timeSlot: String|
| -dayOfWeek: String|
| -totalSessions: int|
| -totalAmount: double|
| -deposit: double |
| -userId: int     |
+------------------+
         | 1
         | n
         v
+------------------+       +------------------+
| BookingSession   |       |    Product       |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -bookingId: int  |       | -code: String    |
| -sessionDate: Date|      | -name: String    |
| -status: String  |       | -price: double   |
+------------------+       +------------------+
         | 1                        | 1
         | n                        | n
         v                          v
+------------------+       +------------------+
| SessionProduct   |       | SessionProduct   |
+------------------+<------+
| -id: int         |
| -bookingSessionId: int|
| -productId: int  |
| -quantity: int   |
| -amount: double  |
+------------------+

+------------------+       +------------------+
|    Supplier      |       |   ImportInvoice  |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |  1  n | -supplierId: int |
| -name: String    |------>| -importDate: Date|
| -address: String |       | -totalAmount: double|
| -email: String   |       +------------------+
| -phone: String   |                | 1
| -description: String|             | n
+------------------+                v
                           +------------------+
                           |ImportInvoiceDetail|
                           +------------------+
                           | -id: int         |
                           | -importInvoiceId: int|
                           | -productId: int  |
                           | -quantity: int   |
                           | -unitPrice: double|
                           | -amount: double  |
                           +------------------+

+------------------+
|    Payment       |
+------------------+
| -id: int         |
| -bookingId: int  |
| -paymentDate: Date|
| -rentalAmount: double|
| -foodAmount: double|
| -totalAmount: double|
+------------------+
```

### Bảng quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Court → Booking | 1-n | Một sân có nhiều phiếu đặt |
| Customer → Booking | 1-n | Một khách hàng có nhiều phiếu đặt |
| Booking → BookingSession | 1-n | Một phiếu đặt có nhiều phiên (mỗi tuần 1 phiên) |
| BookingSession → SessionProduct | 1-n | Một phiên có nhiều sản phẩm sử dụng |
| Product → SessionProduct | 1-n | Một sản phẩm được dùng trong nhiều phiên |
| Supplier → ImportInvoice | 1-n | Một nhà cung cấp có nhiều hóa đơn nhập |
| ImportInvoice → ImportInvoiceDetail | 1-n | Một hóa đơn nhập có nhiều chi tiết |
| Product → ImportInvoiceDetail | 1-n | Một sản phẩm xuất hiện trong nhiều chi tiết nhập |
| Booking → Payment | 1-n | Một phiếu đặt có thể thanh toán nhiều lần |
| User → Booking | 1-n | Một nhân viên tạo nhiều phiếu đặt |

---

## Câu 3: Thiết kế tĩnh

### View classes

**BookingFrm** (giao diện đặt sân):

| Thành phần | Loại | Mô tả |
|------------|------|-------|
| `inTimeSlot` | Text field / Combobox | Ô nhập/chọn khung giờ (ví dụ: 18:00-19:00) |
| `inCourtType` | Combobox | Chọn loại sân: Sân nhỏ (5 người), Sân vừa (7 người), Sân lớn (11 người) |
| `subSearch` | Button | Nút Search — tìm sân trống |
| `outCourtTable` | Table (clickable) | Bảng sân trống: mã, tên, sức chứa, mô tả. Mỗi dòng click được. |
| `inCustomerName` | Text field | Ô nhập tên khách hàng |
| `subSearchCustomer` | Button | Nút Search — tìm khách hàng |
| `outCustomerTable` | Table (clickable) | Bảng khách hàng: mã, tên, SĐT, địa chỉ. Mỗi dòng click được. |
| `subAddCustomer` | Button | Nút Add New — thêm khách hàng mới |
| `inStartDate` | Text field | Ô nhập ngày bắt đầu |
| `inEndDate` | Text field | Ô nhập ngày kết thúc |
| `inDayOfWeek` | Combobox | Chọn ngày trong tuần (Thứ 2 - Chủ nhật) |
| `inTimeSlotDetail` | Combobox | Chọn khung giờ chi tiết |
| `outBookingSlip` | Panel | Phiếu đặt sân: thông tin sân, KH, thời gian, tổng tiền, cọc |
| `subConfirm` | Button | Nút Confirm — xác nhận đặt sân |

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| CourtDAO | `getAvailableCourts(timeSlot, courtType): List<Court>` | List<Court> | Tìm sân trống theo khung giờ và loại sân |
| CustomerDAO | `searchCustomerByName(keyword): List<Customer>` | List<Customer> | Tìm khách hàng theo tên |
| CustomerDAO | `addCustomer(customer): boolean` | boolean | Thêm khách hàng mới |
| BookingDAO | `createBooking(booking): int` | int | Tạo phiếu đặt, trả về bookingId |
| BookingSessionDAO | `addBookingSessions(sessions): boolean` | boolean | Thêm các phiên chơi |
| PaymentDAO | `createPayment(payment): boolean` | boolean | Tạo phiếu đặt cọc |

### Entity types

| Entity | Bảng | Mô tả |
|--------|------|-------|
| Court | tblCourt | Sân bóng đá mini |
| Customer | tblCustomer | Khách hàng |
| Booking | tblBooking | Phiếu đặt sân |
| BookingSession | tblBookingSession | Phiên chơi cụ thể |
| Product | tblProduct | Mặt hàng đồ ăn/thức uống |
| SessionProduct | tblSessionProduct | Sản phẩm sử dụng trong phiên |
| Supplier | tblSupplier | Nhà cung cấp |
| ImportInvoice | tblImportInvoice | Hóa đơn nhập hàng |
| ImportInvoiceDetail | tblImportInvoiceDetail | Chi tiết hóa đơn nhập |
| Payment | tblPayment | Hóa đơn thanh toán |
| User | tblUser | Người dùng hệ thống |

### Database schema

```
tblUser
├── ID (PK, int, identity)
├── username (varchar, unique)
├── password (varchar)
├── role (varchar)
└── description (varchar)

tblCourt
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
├── type (varchar)           -- 'small', 'medium', 'large'
├── maxGuests (int)
└── description (nvarchar)

tblCustomer
├── ID (PK, int, identity)
├── code (varchar, unique)
├── fullName (nvarchar)
├── phoneNumber (varchar)
└── address (nvarchar)

tblBooking
├── ID (PK, int, identity)
├── courtID (FK → tblCourt.ID)
├── customerID (FK → tblCustomer.ID)
├── startDate (date)
├── endDate (date)
├── timeSlot (varchar)
├── dayOfWeek (varchar)
├── totalSessions (int)
├── totalAmount (float)
├── deposit (float)
└── userID (FK → tblUser.ID)

tblBookingSession
├── ID (PK, int, identity)
├── bookingID (FK → tblBooking.ID)
├── sessionDate (date)
└── status (varchar)         -- 'booked', 'played', 'cancelled'

tblProduct
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
└── price (float)

tblSessionProduct
├── ID (PK, int, identity)
├── bookingSessionID (FK → tblBookingSession.ID)
├── productID (FK → tblProduct.ID)
├── quantity (int)
└── amount (float)

tblSupplier
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
├── address (nvarchar)
├── email (varchar)
├── phone (varchar)
└── description (nvarchar)

tblImportInvoice
├── ID (PK, int, identity)
├── supplierID (FK → tblSupplier.ID)
├── importDate (datetime)
└── totalAmount (float)

tblImportInvoiceDetail
├── ID (PK, int, identity)
├── importInvoiceID (FK → tblImportInvoice.ID)
├── productID (FK → tblProduct.ID)
├── quantity (int)
├── unitPrice (float)
└── amount (float)

tblPayment
├── ID (PK, int, identity)
├── bookingID (FK → tblBooking.ID)
├── paymentDate (datetime)
├── rentalAmount (float)
├── foodAmount (float)
└── totalAmount (float)
```

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package **View**: thêm boundary class `BookingFrm` với tất cả attribute input/output/sub.
2. Tạo package **Entity**: thêm entity classes `Court`, `Customer`, `Booking`, `BookingSession`, `Product`, `SessionProduct`, `Supplier`, `ImportInvoice`, `ImportInvoiceDetail`, `Payment`, `User`.
3. Tạo package **DAO**: thêm control classes `CourtDAO`, `CustomerDAO`, `BookingDAO`, `BookingSessionDAO`, `PaymentDAO` với các method.
4. Vẽ dependency: `BookingFrm` → `CourtDAO`, `BookingFrm` → `CustomerDAO`, `BookingFrm` → `BookingDAO`.
5. Vẽ association 1-n giữa các entity theo bảng quan hệ ở trên.
6. Thêm note mô tả quy trình: search court → select customer → enter dates → confirm.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo 6 lifelines: `:Staff` (actor), `:BookingFrm` (boundary), `:CourtDAO` (control), `:CustomerDAO` (control), `:BookingDAO` (control), `:BookingSessionDAO` (control).
2. Vẽ message từ Staff đến BookingFrm cho các thao tác nhập liệu.
3. Vẽ message từ BookingFrm đến các DAO cho truy vấn và lưu dữ liệu.
4. Vẽ return message (dashed line) từ DAO về BookingFrm.
5. Sử dụng `loop` fragment cho bước lặp thêm sản phẩm trong phiên (nếu có).
6. Sử dụng `alt` fragment cho trường hợp khách hàng mới / khách hàng cũ.

### ASCII Sequence Diagram

```
Staff          BookingFrm        CourtDAO      CustomerDAO     BookingDAO    BookingSessionDAO
  |                |                 |               |               |               |
  |--enterTimeSlot>|                 |               |               |               |
  |  ("18:00-19:00)|                 |               |               |               |
  |--selectType--->|                 |               |               |               |
  |  ("San nho")   |                 |               |               |               |
  |--clickSearch-->|                 |               |               |               |
  |                |                 |               |               |               |
  |                |--getAvailCourt->|               |               |               |
  |                |  (slot, type)   |               |               |               |
  |                |                 |--query DB     |               |               |
  |                |                 |<-return List--|               |               |
  |                |<--List<Court>---|               |               |               |
  |                |                 |               |               |               |
  |<--showCourts---|                 |               |               |               |
  |  S1, S3, S5    |                 |               |               |               |
  |                |                 |               |               |               |
  |--clickCourt(S1)|                 |               |               |               |
  |                |                 |               |               |               |
  |--enterCustName>|                 |               |               |               |
  |  ("Nguyen A")  |                 |               |               |               |
  |--clickSearchCus>                 |               |               |               |
  |                |--searchCustomer>|               |               |               |
  |                |                 |               |--query DB     |               |
  |                |                 |               |<-return List--|               |
  |                |<--List<Customer>|               |               |               |
  |                |                 |               |               |               |
  |<--showCusts----|                 |               |               |               |
  |                |                 |               |               |               |
  |--clickCust(A)-->                 |               |               |               |
  |--enterDates--->|                 |               |               |               |
  |  (01/07-30/09) |                 |               |               |               |
  |--clickConfirm->|                 |               |               |               |
  |                |                 |               |               |               |
  |<--showSlip-----|                 |               |               |               |
  |  S1,12tuan,2.4M|                 |               |               |               |
  |                |                 |               |               |               |
  |--clickConfirm->|                 |               |               |               |
  |                |--createBooking->|               |               |               |
  |                |                 |               |               |--INSERT booking|
  |                |                 |               |               |<-bookingId-----|
  |                |<--bookingId-----|               |               |               |
  |                |                 |               |               |               |
  |                |--addSessions--->|               |               |               |
  |                |                 |               |               |--INSERT 12 rows|
  |                |                 |               |               |<-true----------|
  |                |<--true-----------|               |               |               |
  |                |                 |               |               |               |
  |<--success------|                 |               |               |               |
  |  "Dat san OK"  |                 |               |               |               |
```

### Bảng chi tiết message (>= 20 bước)

| # | Từ | Đến | Message | Ghi chú |
|---|-----|-----|---------|---------|
| 1 | Staff | BookingFrm | `enterTimeSlot("18:00-19:00")` | Nhập khung giờ |
| 2 | Staff | BookingFrm | `selectCourtType("Sân nhỏ")` | Chọn loại sân |
| 3 | Staff | BookingFrm | `clickSearch()` | Nhấn nút Search |
| 4 | BookingFrm | CourtDAO | `getAvailableCourts("18:00-19:00", "small")` | Gọi truy vấn sân trống |
| 5 | CourtDAO | CourtDAO | `executeSQL()` | Thực thi SQL tìm sân trống |
| 6 | CourtDAO | BookingFrm | `return List<Court>` | Trả về danh sách sân: S1, S3, S5 |
| 7 | BookingFrm | BookingFrm | `displayCourtTable(list)` | Hiển thị bảng sân trống |
| 8 | Staff | BookingFrm | `clickCourt("S1")` | Chọn sân S1 |
| 9 | BookingFrm | BookingFrm | `showCustomerSearchPanel()` | Hiển thị form tìm KH |
| 10 | Staff | BookingFrm | `enterCustomerName("Nguyen Van A")` | Nhập tên KH |
| 11 | Staff | BookingFrm | `clickSearchCustomer()` | Nhấn Search KH |
| 12 | BookingFrm | CustomerDAO | `searchCustomerByName("Nguyen Van A")` | Gọi tìm KH |
| 13 | CustomerDAO | CustomerDAO | `executeSQL()` | Thực thi SQL tìm KH |
| 14 | CustomerDAO | BookingFrm | `return List<Customer>` | Trả về danh sách KH |
| 15 | BookingFrm | BookingFrm | `displayCustomerTable(list)` | Hiển thị bảng KH |
| 16 | Staff | BookingFrm | `clickCustomer("KH001")` | Chọn KH Nguyen Van A |
| 17 | Staff | BookingFrm | `enterStartDate("01/07/2026")` | Nhập ngày bắt đầu |
| 18 | Staff | BookingFrm | `enterEndDate("30/09/2026")` | Nhập ngày kết thúc |
| 19 | Staff | BookingFrm | `selectDayOfWeek("Thứ 6")` | Chọn ngày trong tuần |
| 20 | Staff | BookingFrm | `selectTimeSlot("18:00-19:00")` | Chọn khung giờ |
| 21 | Staff | BookingFrm | `clickConfirm()` | Nhấn Confirm lần 1 |
| 22 | BookingFrm | BookingFrm | `calculateBooking()` | Tính toán: 12 tuần, 2,400,000đ |
| 23 | BookingFrm | BookingFrm | `displayBookingSlip()` | Hiển thị phiếu đặt |
| 24 | Staff | BookingFrm | `clickConfirm()` | Nhấn Confirm lần 2 |
| 25 | BookingFrm | BookingDAO | `createBooking(booking)` | Gọi tạo phiếu đặt |
| 26 | BookingDAO | BookingDAO | `INSERT INTO tblBooking` | Lưu phiếu đặt |
| 27 | BookingDAO | BookingFrm | `return bookingId` | Trả về ID phiếu đặt |
| 28 | BookingFrm | BookingSessionDAO | `addBookingSessions(12 sessions)` | Gọi tạo 12 phiên |
| 29 | BookingSessionDAO | BookingSessionDAO | `INSERT INTO tblBookingSession` | Lưu 12 phiên |
| 30 | BookingSessionDAO | BookingFrm | `return true` | Thành công |
| 31 | BookingFrm | Staff | `showMessage("Dat san thanh cong")` | Thông báo thành công |

---

## Câu 5: Blackbox Testcase

### TC01: Đặt sân thành công cho khách hàng cũ

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblCourt:**
| ID | code | name | type | maxGuests | description |
|----|------|------|------|-----------|-------------|
| 1 | S1 | Sân 1 | small | 10 | Sân bên trái |
| 2 | S2 | Sân 2 | small | 10 | Sân giữa |
| 3 | S3 | Sân 3 | small | 10 | Sân bên phải |
| 4 | S4 | Sân 4 | medium | 14 | Sân VIP |
| 5 | S5 | Sân 5 | small | 10 | Sân sau |

**tblCustomer:**
| ID | code | fullName | phoneNumber | address |
|----|------|----------|-------------|---------|
| 1 | KH001 | Nguyen Van A | 0901234567 | 123 Le Loi, Q1 |
| 2 | KH002 | Tran Van B | 0912345678 | 456 Nguyen Hue, Q1 |

**tblBooking:** (rỗng)
**tblBookingSession:** (rỗng)
**tblPayment:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Chuyển sang giao diện Home |
| 3 | Chọn **Booking** | Giao diện đặt sân: ô khung giờ, combobox loại sân, nút Search |
| 4 | Nhập "18:00-19:00", chọn "Sân nhỏ", nhấn Search | Bảng hiển thị 4 sân trống: S1, S2, S3, S5 |
| 5 | Nhấn vào sân "S1" | Hiển thị form tìm khách hàng |
| 6 | Nhập "Nguyen Van A", nhấn Search | Bảng hiển thị: KH001 - Nguyen Van A - 0901234567 |
| 7 | Nhấn vào KH "Nguyen Van A" | Hiển thị form nhập ngày: ô ngày bắt đầu, ngày kết thúc, ngày trong tuần, khung giờ |
| 8 | Nhập ngày bắt đầu 01/07/2026, ngày kết thức 30/09/2026, chọn "Thứ 6", chọn "18:00-19:00", nhấn Confirm | Hiển thị phiếu đặt: sân S1, KH Nguyen Van A, Thứ 6 18:00-19:00, 12 tuần, 200,000đ/tuần, tổng 2,400,000đ, cọc 500,000đ |
| 9 | Nhấn Confirm lần cuối | Thông báo "Dat san thanh cong". In phiếu đặt sân. |

### Database sau khi test

**tblBooking:** (thêm 1 dòng)
| ID | courtID | customerID | startDate | endDate | timeSlot | dayOfWeek | totalSessions | totalAmount | deposit | userID |
|----|---------|------------|-----------|---------|----------|-----------|---------------|-------------|---------|--------|
| 1 | 1 | 1 | 01/07/2026 | 30/09/2026 | 18:00-19:00 | Friday | 12 | 2400000 | 500000 | 1 |

**tblBookingSession:** (thêm 12 dòng)
| ID | bookingID | sessionDate | status |
|----|-----------|-------------|--------|
| 1 | 1 | 03/07/2026 | booked |
| 2 | 1 | 10/07/2026 | booked |
| 3 | 1 | 17/07/2026 | booked |
| 4 | 1 | 24/07/2026 | booked |
| 5 | 1 | 31/07/2026 | booked |
| 6 | 1 | 07/08/2026 | booked |
| 7 | 1 | 14/08/2026 | booked |
| 8 | 1 | 21/08/2026 | booked |
| 9 | 1 | 28/08/2026 | booked |
| 10 | 1 | 04/09/2026 | booked |
| 11 | 1 | 11/09/2026 | booked |
| 12 | 1 | 18/09/2026 | booked |

**tblUser, tblCourt, tblCustomer:** Không thay đổi.
