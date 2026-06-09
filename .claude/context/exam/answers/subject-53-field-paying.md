# Subject No. 53 — Mini Football Field — Module "Customer paying"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Customer paying**. |
| 2 | Giao diện: ô nhập tên khách hàng, nút Search. |
| 3 | Staff nhập "Nguyen Van A", Search. Hệ thống hiển thị danh sách KH. |
| 4 | Staff click "Nguyen Van A". Hệ thống hiển thị danh sách booking đang hoạt động. |
| 5 | Staff click nút "Payment" của booking #1. |
| 6 | Hệ thống hiển thị hóa đơn: thông tin KH, thông tin sân, danh sách vật tư đã dùng mỗi session, tổng tiền. |
| 7 | Staff xác nhận, nhấn **Confirm**. Hệ thống cập nhật database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Court | id, code, name, type, pricePerSession |
| Customer | id, code, name, phone, address |
| Booking | id, courtId, customerId, startDate, endDate, timeSlot, totalAmount, deposit, status |
| BookingSession | id, bookingId, sessionDate, checkinTime, checkoutTime, rentFee, status |
| Product | id, code, name, price |
| SessionProduct | id, bookingSessionId, productId, quantity, unitPrice, amount |
| Payment | id, bookingId, paymentDate, totalAmount, depositDeducted |
| User | id, username, password, role |

### Quan hệ

```
Court 1 --- n Booking n --- 1 Customer
Booking 1 --- n BookingSession
BookingSession 1 --- n SessionProduct n --- 1 Product
Booking 1 --- n Payment
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Court, Customer, Booking, BookingSession, Product, SessionProduct, Payment, User
- Bước 3: Tạo các view class box từ giao diện: HomeFrm, CustomerPayFrm
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Booking`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-bookingId: int`, `-totalAmount: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+getActiveBookings(customerId: int): List<Booking>`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Court | <<entity>> | -courtId: int, -code: String, -name: String, -type: String, -pricePerSession: double | |
| Customer | <<entity>> | -customerId: int, -code: String, -name: String, -phone: String, -address: String | +searchCustomerByName(keyword: String): List<Customer> |
| Booking | <<entity>> | -bookingId: int, -court: Court, -customer: Customer, -startDate: Date, -endDate: Date, -timeSlot: String, -totalAmount: double, -deposit: double, -status: String | +getActiveBookings(customerId: int): List<Booking> |
| BookingSession | <<entity>> | -sessionId: int, -booking: Booking, -sessionDate: Date, -checkinTime: String, -checkoutTime: String, -rentFee: double, -status: String | +getSessionDetails(bookingId: int): List<BookingSession> |
| Product | <<entity>> | -productId: int, -code: String, -name: String, -price: double | |
| SessionProduct | <<entity>> | -id: int, -bookingSession: BookingSession, -product: Product, -quantity: int, -unitPrice: double, -amount: double | +getSessionProducts(sessionId: int): List<SessionProduct> |
| Payment | <<entity>> | -paymentId: int, -booking: Booking, -paymentDate: Date, -totalAmount: double, -depositDeducted: double | +createPayment(payment: Payment): boolean |
| User | <<entity>> | -userId: int, -username: String, -password: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeFrm | subCustomerPay: JButton (nút chọn Customer paying) |
| CustomerPayFrm | inCustomerName: JTextField (ô nhập tên KH), subSearch: JButton (nút Search), outsubCustomerList: JTable (danh sách KH click được), outsubBookingList: JTable (danh sách booking click được), outInvoice: JPanel (hóa đơn chi tiết), outTotal: JLabel (tổng tiền), outDeposit: JLabel (tiền cọc), outFinalAmount: JLabel (số tiền thực nhận), subConfirm: JButton (nút Confirm) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Court → Booking
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Booking ◆→ BookingSession
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: CustomerPayFrm ---> BookingDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Court (1) --- (n) Booking

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Court | Booking | Association | 1 : n | Một sân có nhiều booking |
| Customer | Booking | Association | 1 : n | Một khách hàng có nhiều booking |
| Booking | BookingSession | Composition | 1 : n | Booking chứa nhiều session |
| BookingSession | SessionProduct | Composition | 1 : n | Session chứa nhiều sản phẩm đã dùng |
| Product | SessionProduct | Association | 1 : n | Một sản phẩm xuất hiện trong nhiều session |
| Booking | Payment | Association | 1 : n | Một booking có nhiều phiếu thanh toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition Booking ◆→ BookingSession:
- Kéo class Booking vào canvas, kéo class BookingSession vào bên phải
- Chọn công cụ **Composition**, click vào Booking rồi kéo sang BookingSession
- Kim cương filled nằm ở phía Booking (parent)
- Đặt Multiplicity: Booking "1", BookingSession "n"

Ví dụ 2 — Vẽ quan hệ Association Court → Booking (1:n):
- Kéo class Court và Booking vào canvas
- Chọn công cụ **Association**, click vào Court kéo sang Booking
- Đặt Multiplicity: Court "1", Booking "n"

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> HomeFrm xuất hiện:
  một lựa chọn để thanh toán -> subCustomerPay

Chọn Customer paying -> CustomerPayFrm xuất hiện:
  ô nhập tên khách hàng -> inCustomerName
  nút Search -> subSearch
  danh sách khách hàng (click được) -> outsubCustomerList

Nhập tên khách hàng, nhấn Search -> cần phương thức:
  tên: searchCustomer()
  đầu vào: keyword
  đầu ra: List<Customer>
  gán cho entity class: Customer.

Nhấn vào khách hàng "Nguyen Van A" -> hiển thị danh sách booking:
  danh sách booking đang hoạt động (click được) -> outsubBookingList

Click vào khách hàng -> cần phương thức:
  tên: getActiveBookings()
  đầu vào: customerId
  đầu ra: List<Booking>
  gán cho entity class: Booking.

Nhấn nút Payment của booking #1 -> hiển thị hóa đơn:
  hóa đơn chi tiết -> outInvoice
  tổng tiền -> outTotal
  tiền cọc -> outDeposit
  số tiền thực nhận -> outFinalAmount
  nút Confirm -> subConfirm

Nhấn Payment -> cần phương thức:
  tên: getSessionDetails()
  đầu vào: bookingId
  đầu ra: List<BookingSession>
  gán cho entity class: BookingSession.

Hiển thị sản phẩm đã sử dụng -> cần phương thức:
  tên: getSessionProducts()
  đầu vào: sessionId
  đầu ra: List<SessionProduct>
  gán cho entity class: SessionProduct.

Nhấn Confirm -> cần phương thức:
  tên: createPayment()
  đầu vào: bookingId, totalAmount, depositDeducted
  đầu ra: boolean
  gán cho entity class: Payment.

### Summary
View classes: HomeFrm, CustomerPayFrm
Methods: searchCustomer(), getActiveBookings(), getSessionDetails(), getSessionProducts(), createPayment()

---

## Câu 3: Thiết kế tĩnh

### View classes

**CustomerPayFrm:**
- `inCustomerName`: ô nhập tên KH
- `subSearch`: nút Search
- `outCustomerList`: danh sách KH (click được)
- `outBookingList`: danh sách booking (click được)
- `outInvoice`: hóa đơn chi tiết
- `outTotal`: tổng tiền
- `outDeposit`: tiền cọc
- `outFinalAmount`: số tiền thực nhận/trả
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchCustomerByName(keyword): List<Customer>` |
| BookingDAO | `getActiveBookings(customerId): List<Booking>` |
| BookingSessionDAO | `getSessionDetails(bookingId): List<BookingSession>` |
| SessionProductDAO | `getSessionProducts(sessionId): List<SessionProduct>` |
| PaymentDAO | `createPayment(payment): boolean` |
| BookingDAO | `updateBookingStatus(bookingId, status): boolean` |

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Court | Entity | id: int (PK), code: String, name: String, type: String, pricePerSession: double |
| Customer | Entity | id: int (PK), code: String, name: String, phone: String, address: String |
| Booking | Entity | id: int (PK), startDate: Date, endDate: Date, timeSlot: String, totalAmount: double, deposit: double, status: String, court: Court, customer: Customer |
| BookingSession | Entity | id: int (PK), sessionDate: Date, checkinTime: String, checkoutTime: String, rentFee: double, status: String, booking: Booking |
| Product | Entity | id: int (PK), code: String, name: String, price: double |
| SessionProduct | Entity | id: int (PK), quantity: int, unitPrice: double, amount: double, bookingSession: BookingSession, product: Product |
| Payment | Entity | id: int (PK), paymentDate: Date, totalAmount: double, depositDeducted: double, booking: Booking |
| User | Entity | id: int (PK), username: String, password: String, role: String |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL, UNIQUE |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblCourt:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | nvarchar(100) | NOT NULL |
| type | varchar(50) | NOT NULL |
| pricePerSession | float | NOT NULL |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | nvarchar(100) | NOT NULL |
| phone | varchar(15) | |
| address | nvarchar(255) | |

**tblBooking:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| courtID | int | FOREIGN KEY → tblCourt(ID) |
| customerID | int | FOREIGN KEY → tblCustomer(ID) |
| startDate | date | NOT NULL |
| endDate | date | NOT NULL |
| timeSlot | varchar(20) | NOT NULL |
| totalAmount | float | NOT NULL |
| deposit | float | |
| status | varchar(20) | NOT NULL |

**tblBookingSession:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| bookingID | int | FOREIGN KEY → tblBooking(ID) |
| sessionDate | date | NOT NULL |
| checkinTime | varchar(10) | |
| checkoutTime | varchar(10) | |
| rentFee | float | DEFAULT 0 |
| status | varchar(20) | NOT NULL |

**tblProduct:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | nvarchar(100) | NOT NULL |
| price | float | NOT NULL |

**tblSessionProduct:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| sessionID | int | FOREIGN KEY → tblBookingSession(ID) |
| productID | int | FOREIGN KEY → tblProduct(ID) |
| quantity | int | NOT NULL |
| unitPrice | float | NOT NULL |
| amount | float | NOT NULL |

**tblPayment:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| bookingID | int | FOREIGN KEY → tblBooking(ID) |
| paymentDate | datetime | NOT NULL |
| totalAmount | float | NOT NULL |
| depositDeducted | float | DEFAULT 0 |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:CustomerPayFrm` — boundary
3. `:CustomerDAO` — control
4. `:BookingDAO` — control
5. `:BookingSessionDAO` — control
6. `:PaymentDAO` — control

**Message flow:**

1. Staff → CustomerPayFrm: `enterCustomerName("Nguyen Van A")` + `clickSearch()`
2. CustomerPayFrm → CustomerDAO: `searchCustomerByName("Nguyen Van A")`
3. CustomerDAO → CustomerPayFrm: return `List<Customer>`
4. Staff → CustomerPayFrm: `clickCustomer(A)`
5. CustomerPayFrm → BookingDAO: `getActiveBookings(customerId)`
6. BookingDAO → CustomerPayFrm: return `List<Booking>`
7. Staff → CustomerPayFrm: `clickPayment(booking#1)`
8. CustomerPayFrm → BookingSessionDAO: `getSessionDetails(bookingId)`
9. CustomerPayFrm → SessionProductDAO: `getSessionProducts(sessionIds)`
10. CustomerPayFrm: display invoice (sessions + products + total - deposit)
11. Staff → CustomerPayFrm: `clickConfirm()`
12. CustomerPayFrm → PaymentDAO: `createPayment(payment)`
13. CustomerPayFrm → BookingDAO: `updateBookingStatus(bookingId, "paid")`

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán thành công

**Database trước:**
- tblBooking: booking#1 (totalAmount=2400000, deposit=500000, status="active")
- tblSessionProduct: Coca Cola × 3 = 45000, Khăn × 2 = 20000
- tblPayment: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "Nguyen Van A" → Search, click KH | Danh sách booking |
| 2 | Click Payment booking#1 | Hóa đơn: tiền sân + vật tư - cọc = 2,400,000 + 65,000 - 500,000 = 1,965,000đ |
| 3 | Confirm | "Thanh toan thanh cong" |

**Database sau:**
- tblBooking: status = "paid"
- tblPayment: thêm 1 dòng (totalAmount=1965000, depositDeducted=500000)
