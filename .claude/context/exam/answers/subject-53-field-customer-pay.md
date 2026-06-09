# Subject No. 53 — Mini Football Field — Module "Customer paying"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thanh toán cho khách hàng

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. Hệ thống xác thực thành công. |
| 3 | Giao diện Home xuất hiện với các menu chức năng: Booking, Import goods, Update used items, Customer paying, Revenue statistics, Statistics of time slot. |
| 4 | Staff chọn menu **Customer paying**. |
| 5 | Giao diện tìm kiếm khách hàng xuất hiện: ô nhập tên khách hàng (txtCustomerName), nút Search (btnSearchCustomer), bảng danh sách khách hàng (tblCustomerList). |
| 6 | Staff nhập "Nguyen Van A" vào ô Customer Name, nhấn nút Search. Hệ thống truy vấn database tìm khách hàng có tên chứa "Nguyen Van A". |
| 7 | Hệ thống hiển thị bảng danh sách khách hàng: cột Mã KH, Tên KH, Số điện thoại, Địa chỉ. Kết quả: KH001 — Nguyen Van A — 0912345678 — Quan 1, TP.HCM. |
| 8 | Staff click chọn dòng "Nguyen Van A" (KH001). Hệ thống hiển thị danh sách booking của khách hàng này: bảng gồm cột Mã booking, Ngày đặt, Sân, Khung giờ, Tổng tiền, Trạng thái. |
| 9 | Danh sách booking hiển thị: B001 — 01/06/2026 — Sân S1 — T6 18:00-19:00 — 2,400,000đ — Active (chưa thanh toán). |
| 10 | Staff click nút **Payment** cho booking B001. |
| 11 | Hệ thống hiển thị hóa đơn chi tiết. Phần đầu: thông tin khách hàng (Nguyen Van A, 0912345678, Quan 1, TP.HCM), thông tin booking (B001, Sân S1, T6 18:00-19:00, 01/07-30/09/2026). |
| 12 | Phần giữa hóa đơn: danh sách phiên thuê và sản phẩm đã sử dụng. Phiên 1 (10/07/2026): Sân S1, 18:00-19:15, Coca Cola 330ml × 2 = 30,000đ, Bánh mì × 1 = 20,000đ, tổng phiên 1 = 250,000đ (tiền sân 200,000đ + sản phẩm 50,000đ). |
| 13 | Tiếp tục: Phiên 2 (17/07/2026): Sân S1, 18:00-19:00, Nước suối × 3 = 30,000đ, tổng phiên 2 = 230,000đ (tiền sân 200,000đ + sản phẩm 30,000đ). |
| 14 | Phần cuối hóa đơn: Tổng tiền đặt cọc = 500,000đ. Tổng tiền thuê sân = 400,000đ. Tổng tiền sản phẩm = 80,000đ. Tổng thanh toán = 480,000đ (400,000 + 80,000). Đã đặt cọc = 500,000đ. Còn lại = -20,000đ (khách đã trả dư). |
| 15 | Staff kiểm tra hóa đơn. Nếu khách hàng phản ánh thay đổi số lượng sản phẩm (ví dụ: khách nói chỉ dùng 1 Coca Cola không phải 2), staff click vào dòng sản phẩm đó để chỉnh sửa: sửa số lượng Coca Cola từ 2 xuống 1, hệ thống cập nhật lại tổng. |
| 16 | Staff nhấn nút **Confirm**. Hệ thống tạo bản ghi Payment, cập nhật trạng thái booking thành "Paid", cập nhật database. Hệ thống thông báo "Thanh toan thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập tên khách hàng không tồn tại trong hệ thống → danh sách kết quả trống, hiển thị thông báo "Khong tim thay khach hang".
- **EL2:** Khách hàng đã thanh toán tất cả booking → danh sách booking không có nút Payment (chỉ hiển thị trạng thái "Paid").
- **EL3:** Khách hàng không có phiên thuê nào đã hoàn thành (chưa nhận/trả sân) → hệ thống thông báo "Chua co phien nao hoan thanh de thanh toan".

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý thuê sân bóng đá mini. Sân bóng có nhiều sân nhỏ, có thể gộp 2 hoặc 4 sân thành sân lớn. Mỗi sân được nhiều khách hàng thuê ở các khung giờ khác nhau. Khách hàng đặt sân theo phiên trong tuần hoặc theo tháng. Khi đặt sân, khách hàng nhận phiếu đặt và phải đặt cọc trước. Khi khách đến nhận sân và trả sân, nhân viên cập nhật giờ nhận, giờ trả, tiền thuê, và danh sách sản phẩm (đồ ăn, thức uống) mà khách đã sử dụng trong phiên đó. Khi khách hàng thanh toán, hệ thống tạo hóa đơn chi tiết bao gồm thông tin thuê sân và sản phẩm đã sử dụng. Chủ sân nhập hàng hóa từ nhiều nhà cung cấp khác nhau.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Sân bóng (Court) | Entity class | | Đối tượng quản lý chính, có thuộc tính riêng |
| Khách hàng (Customer) | Entity class | | Người thuê sân và thanh toán |
| Phiếu đặt (Booking) | Entity class | | Bản ghi đặt sân, liên kết KH và sân |
| Phiên thuê (BookingSession) | Entity class | | Mỗi lần khách đến nhận sân cụ thể |
| Sản phẩm (Product) | Entity class | | Đồ ăn/thức uống bán cho khách |
| Sản phẩm phiên (SessionProduct) | Entity class | | Sản phẩm đã sử dụng trong 1 phiên thuê |
| Thanh toán (Payment) | Entity class | | Hóa đơn thanh toán của booking |
| Người dùng (User) | Entity class | | Nhân viên thao tác trên hệ thống |
| Nhà cung cấp (Supplier) | Entity class | | Cung cấp hàng hóa cho sân bóng |
| Phiếu nhập (ImportInvoice) | Entity class | | Hóa đơn nhập hàng |
| Chi tiết phiếu nhập (ImportInvoiceDetail) | Entity class | | Chi tiết sản phẩm trong phiếu nhập |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Court | courtId (PK), code, courtName, courtType, pricePerSession, status |
| Customer | customerId (PK), code, customerName, phoneNumber, address |
| Booking | bookingId (PK), courtId (FK), customerId (FK), bookingDate, startDate, endDate, timeSlot, dayOfWeek, totalSessions, totalAmount, deposit, status |
| BookingSession | sessionId (PK), bookingId (FK), sessionDate, startTime, endTime, checkinTime, checkoutTime, rentAmount, status |
| Product | productId (PK), code, productName, unit, price, stockQuantity |
| SessionProduct | sessionProductId (PK), sessionId (FK), productId (FK), unitPrice, quantity, subtotal |
| Payment | paymentId (PK), bookingId (FK), paymentDate, rentTotal, productTotal, totalAmount, depositDeducted, finalAmount, method, status |
| User | userId (PK), username, password, fullName, role |
| Supplier | supplierId (PK), code, supplierName, address, email, phone, description |
| ImportInvoice | importInvoiceId (PK), supplierId (FK), importDate, totalAmount |
| ImportInvoiceDetail | detailId (PK), importInvoiceId (FK), productId (FK), unitPrice, quantity, amount |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|      Court       |       |     Customer     |
+------------------+       +------------------+
| - courtId: int   |       | - customerId: int|
| - code: String   |       | - code: String   |
| - courtName      |       | - customerName   |
| - courtType      |       | - phoneNumber    |
| - pricePerSession|       | - address        |
| - status         |       +------------------+
+------------------+               | 1
        | 1                        |
        |                          | n
        | n                        v
        v                  +------------------+
+------------------+       |     Booking      |
|                  |       +------------------+
|                  |       | - bookingId: int |
|                  |       | - bookingDate    |
|                  |       | - startDate      |
|                  |       | - endDate        |
|                  |       | - timeSlot       |
|                  |       | - totalSessions  |
|                  |       | - totalAmount    |
|                  |       | - deposit        |
|                  |       | - status         |
|                  |       +------------------+
|                  |        | 1         | 1
|                  |        |           |
|                  |        | n         | 1
|                  |        v           v
|                  |  +------------------+  +------------------+
|                  |  | BookingSession   |  |     Payment      |
|                  |  +------------------+  +------------------+
|                  |  | - sessionId: int |  | - paymentId: int |
|                  |  | - sessionDate    |  | - paymentDate    |
|                  |  | - startTime      |  | - rentTotal      |
|                  |  | - endTime        |  | - productTotal   |
|                  |  | - checkinTime    |  | - totalAmount    |
|                  |  | - checkoutTime   |  | - depositDeducted|
|                  |  | - rentAmount     |  | - finalAmount    |
|                  |  | - status         |  | - method         |
|                  |  +------------------+  | - status         |
|                  |        | 1             +------------------+
|                  |        |
|                  |        | n
|                  |        v
|                  |  +------------------+
|                  |  | SessionProduct   |
|                  |  +------------------+
|                  |  | - sessionProductId|
|                  |  | - unitPrice      |
|                  |  | - quantity       |
|                  |  | - subtotal       |
|                  |  +------------------+
+------------------+        | n
       Product              |
+------------------+        | 1
| - productId: int |        +
| - code: String   |
| - productName    |
| - unit: String   |
| - price: float   |
| - stockQuantity  |
+------------------+

+------------------+       +------------------+
|     Supplier     |       |       User       |
+------------------+       +------------------+
| - supplierId: int|       | - userId: int    |
| - code: String   |       | - username       |
| - supplierName   |       | - password       |
| - address        |       | - fullName       |
| - email          |       | - role           |
| - phone          |       +------------------+
| - description    |
+------------------+
        | 1
        | n
        v
+------------------+
| ImportInvoice    |
+------------------+
| - importInvoiceId|
| - importDate     |
| - totalAmount    |
+------------------+
        | 1
        | n
        v
+------------------+
| ImportInvoiceDetail|
+------------------+
| - detailId: int  |
| - unitPrice      |
| - quantity       |
| - amount         |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Court → Booking | 1-n | Một sân được nhiều khách đặt |
| Customer → Booking | 1-n | Một khách hàng có nhiều booking |
| Booking → BookingSession | 1-n | Một booking có nhiều phiên thuê theo tuần |
| BookingSession → SessionProduct | 1-n | Một phiên thuê có nhiều sản phẩm đã sử dụng |
| Product → SessionProduct | 1-n | Một sản phẩm xuất hiện trong nhiều phiên |
| Booking → Payment | 1-1 | Mỗi booking có 1 phiếu thanh toán |
| Supplier → ImportInvoice | 1-n | Một nhà cung cấp có nhiều phiếu nhập |
| ImportInvoice → ImportInvoiceDetail | 1-n | Một phiếu nhập có nhiều chi tiết sản phẩm |
| Product → ImportInvoiceDetail | 1-n | Một sản phẩm xuất hiện trong nhiều phiếu nhập |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**Các bước vẽ tổng quan:**

1. Mở Visual Paradigm → New → Class Diagram (trong danh mục Diagrams).
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity: Court, Customer, Booking, BookingSession, Product, SessionProduct, Payment, User, Supplier, ImportInvoice, ImportInvoiceDetail.
3. Tạo view class boxes từ các interface: HomeFrm, CustomerPayFrm.
4. Vẽ relationships giữa các class theo bảng quan hệ bên dưới.
5. Thêm multiplicities và role names cho mỗi đường kết nối.

**Cấu trúc 1 class box (3 ngăn):**

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Payment`.
- **Ngăn 2 (thuộc tính):** Mỗi thuộc tính ghi dạng `-attributeName: Type`. Ví dụ: `-rentTotal: double`, `-finalAmount: double`.
- **Ngăn 3 (phương thức):** Mỗi phương thức ghi dạng `+methodName(params): ReturnType`. Ví dụ: `+createPayment(payment: Payment): boolean`.

**Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Court | `<<entity>>` | -id: int, -code: String, -courtName: String, -courtType: String, -pricePerSession: double, -status: String | getter/setter |
| Customer | `<<entity>>` | -id: int, -code: String, -customerName: String, -phoneNumber: String, -address: String | +searchCustomer(keyword: String): List<Customer> |
| Booking | `<<entity>>` | -id: int, -bookingDate: Date, -startDate: Date, -endDate: Date, -timeSlot: String, -totalSessions: int, -totalAmount: double, -deposit: double, -status: String | +getBookingsByCustomer(customerId: int): List<Booking>, +updateBookingStatus(bookingId: int, status: String): boolean |
| BookingSession | `<<entity>>` | -id: int, -sessionDate: Date, -startTime: String, -endTime: String, -checkinTime: String, -checkoutTime: String, -rentAmount: double, -status: String | +getCompletedSessionsWithProducts(bookingId: int): List<BookingSession> |
| Product | `<<entity>>` | -id: int, -code: String, -productName: String, -unit: String, -price: double, -stockQuantity: int | getter/setter |
| SessionProduct | `<<entity>>` | -id: int, -unitPrice: double, -quantity: int, -subtotal: double | +updateSessionProduct(spId: int, quantity: int, subtotal: double): boolean |
| Payment | `<<entity>>` | -id: int, -paymentDate: Date, -rentTotal: double, -productTotal: double, -totalAmount: double, -depositDeducted: double, -finalAmount: double, -method: String, -status: String | +createPayment(payment: Payment): boolean |
| User | `<<entity>>` | -id: int, -username: String, -password: String, -fullName: String, -role: String | +checkLogin(username: String, password: String): boolean |
| Supplier | `<<entity>>` | -id: int, -code: String, -supplierName: String, -address: String, -email: String, -phone: String, -description: String | getter/setter |
| ImportInvoice | `<<entity>>` | -id: int, -importDate: Date, -totalAmount: double | getter/setter |
| ImportInvoiceDetail | `<<entity>>` | -id: int, -unitPrice: double, -quantity: int, -amount: double | getter/setter |

**Bảng chi tiết view classes:**

| View class | Stereotype | UI Elements |
|------------|-----------|-------------|
| HomeFrm | `<<boundary>>` | subCustomerPay: JButton |
| CustomerPayFrm | `<<boundary>>` | inCustomerName: JTextField, subSearchCustomer: JButton, outsubCustomerList: JTable (clickable), outBookingList: JTable, btnPayment: JButton, outCustomerInfo: JLabel, outBookingInfo: JLabel, outInvoiceDetail: JTable, outSubtotalRent: JLabel, outSubtotalProduct: JLabel, outDeposit: JLabel, outTotalAmount: JLabel, outFinalAmount: JLabel, btnEditItem: JButton, subConfirm: JButton |

**Cách vẽ quan hệ:**

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Booking.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Booking ◆→ BookingSession.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: CustomerPayFrm → CustomerDAO.

**Cách ghi multiplicity:**

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Court "1" --- "n" Booking.

**Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|--------------|------------|
| Court | Booking | Association | 1 — n | Một sân được nhiều khách đặt |
| Customer | Booking | Association | 1 — n | Một khách hàng có nhiều booking |
| Booking | BookingSession | Composition | 1 — n | Một booking có nhiều phiên thuê |
| BookingSession | SessionProduct | Composition | 1 — n | Một phiên có nhiều sản phẩm đã sử dụng |
| Product | SessionProduct | Association | 1 — n | Một sản phẩm xuất hiện trong nhiều phiên |
| Booking | Payment | Association | 1 — 1 | Mỗi booking có 1 phiếu thanh toán |
| Supplier | ImportInvoice | Association | 1 — n | Một nhà cung cấp có nhiều phiếu nhập |
| ImportInvoice | ImportInvoiceDetail | Composition | 1 — n | Một phiếu nhập có nhiều chi tiết |
| Product | ImportInvoiceDetail | Association | 1 — n | Một sản phẩm xuất hiện trong nhiều phiếu nhập |
| CustomerPayFrm | CustomerDAO | Dependency | — | Frm sử dụng CustomerDAO để tìm khách hàng |
| CustomerPayFrm | BookingDAO | Dependency | — | Frm sử dụng BookingDAO để lấy booking |
| CustomerPayFrm | BookingSessionDAO | Dependency | — | Frm sử dụng BookingSessionDAO để lấy phiên hoàn thành |
| CustomerPayFrm | SessionProductDAO | Dependency | — | Frm sử dụng SessionProductDAO để chỉnh sửa sản phẩm |
| CustomerPayFrm | PaymentDAO | Dependency | — | Frm sử dụng PaymentDAO để tạo thanh toán |

**Ví dụ cụ thể trên Visual Paradigm:**

1. **Vẽ quan hệ Booking → Payment (Association 1-1):**
   - Kéo class Booking lên canvas, kéo class Payment bên phải.
   - Chọn tool "Association" → click vào Booking, kéo đến Payment.
   - Đặt multiplicity "1" phía Booking, "1" phía Payment.
   - Không cần diamond vì đây là Association (không phải Composition).

2. **Vẽ dependency CustomerPayFrm → PaymentDAO:**
   - Chọn tool "Dependency" (đường dashed) → click vào CustomerPayFrm, kéo đến PaymentDAO.
   - Mũi tên tam giác rỗng (▷) tự động hiển thị phía PaymentDAO.

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

**CustomerPayFrm:**
- `inCustomerName`: ô nhập tên khách hàng (JTextField)
- `subSearchCustomer`: nút tìm kiếm khách hàng (JButton)
- `outsubCustomerList`: bảng danh sách khách hàng tìm được, click để chọn (JTable)
- `outBookingList`: bảng danh sách booking của khách hàng (JTable)
- `btnPayment`: nút Payment cho từng booking (JButton, trong bảng booking)
- `outCustomerInfo`: thông tin khách hàng trên hóa đơn (JLabel)
- `outBookingInfo`: thông tin booking trên hóa đơn (JLabel)
- `outInvoiceDetail`: bảng chi tiết hóa đơn — danh sách phiên thuê và sản phẩm (JTable)
- `outSubtotalRent`: tổng tiền thuê sân (JLabel)
- `outSubtotalProduct`: tổng tiền sản phẩm (JLabel)
- `outDeposit`: tiền đặt cọc (JLabel)
- `outTotalAmount`: tổng thanh toán (JLabel)
- `outFinalAmount`: số tiền còn lại phải thanh toán (JLabel)
- `btnEditItem`: nút chỉnh sửa sản phẩm (JButton, trong bảng chi tiết)
- `subConfirm`: nút xác nhận thanh toán (JButton)

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| txtCustomerName | JTextField | Ô nhập tên khách hàng cần tìm |
| btnSearchCustomer | JButton | Nút tìm kiếm khách hàng |
| tblCustomerList | JTable | Bảng kết quả tìm khách hàng (click được) |
| tblBookingList | JTable | Bảng danh sách booking của khách hàng |
| btnPayment | JButton | Nút thanh toán cho từng booking |
| lblCustomerInfo | JLabel | Hiển thị thông tin khách hàng trên hóa đơn |
| lblBookingInfo | JLabel | Hiển thị thông tin booking trên hóa đơn |
| tblInvoiceDetail | JTable | Bảng chi tiết hóa đơn (phiên thuê + sản phẩm) |
| lblSubtotalRent | JLabel | Tổng tiền thuê sân |
| lblSubtotalProduct | JLabel | Tổng tiền sản phẩm |
| lblDeposit | JLabel | Tiền đặt cọc |
| lblTotalAmount | JLabel | Tổng thanh toán |
| lblFinalAmount | JLabel | Số tiền còn lại |
| btnEditItem | JButton | Chỉnh sửa sản phẩm trong hóa đơn |
| btnConfirm | JButton | Xác nhận thanh toán |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| CustomerDAO | `searchByName(String name): ArrayList<Customer>` | Tìm kiếm khách hàng theo tên |
| BookingDAO | `getBookingsByCustomer(int customerId): ArrayList<Booking>` | Lấy danh sách booking của khách hàng |
| BookingSessionDAO | `getCompletedSessionsWithProducts(int bookingId): ArrayList<BookingSession>` | Lấy phiên thuê đã hoàn thành kèm sản phẩm đã sử dụng |
| SessionProductDAO | `updateSessionProduct(int sessionProductId, int quantity, double subtotal): boolean` | Cập nhật số lượng/thành tiền sản phẩm khi khách phản ánh |
| PaymentDAO | `createPayment(Payment payment): boolean` | Tạo bản ghi thanh toán |
| BookingDAO | `updateBookingStatus(int bookingId, String status): boolean` | Cập nhật trạng thái booking thành "Paid" |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Customer | Tìm kiếm và hiển thị thông tin khách hàng trên hóa đơn |
| Booking | Hiển thị danh sách booking, cập nhật trạng thái sau thanh toán |
| BookingSession | Hiển thị thông tin phiên thuê (ngày, giờ, sân, tiền thuê) |
| Product | Thông tin sản phẩm đã sử dụng (tên, giá) |
| SessionProduct | Chi tiết sản phẩm trong hóa đơn, có thể chỉnh sửa số lượng |
| Payment | Tạo bản ghi thanh toán mới |
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

**tblBookingSession:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| bookingID | int | FOREIGN KEY → tblBooking(ID) |
| sessionDate | date | NOT NULL |
| startTime | varchar(10) | NOT NULL |
| endTime | varchar(10) | NOT NULL |
| checkinTime | varchar(10) | NULLABLE |
| checkoutTime | varchar(10) | NULLABLE |
| rentAmount | float | DEFAULT 0 |
| status | varchar(20) | NOT NULL |

**tblCourt:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| courtName | nvarchar(100) | NOT NULL |
| courtType | varchar(50) | NOT NULL |
| pricePerSession | float | NOT NULL |
| status | varchar(20) | NOT NULL |

**tblProduct:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| productName | nvarchar(100) | NOT NULL |
| unit | varchar(20) | NOT NULL |
| price | float | NOT NULL |
| stockQuantity | int | DEFAULT 0 |

**tblSessionProduct:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| sessionID | int | FOREIGN KEY → tblBookingSession(ID) |
| productID | int | FOREIGN KEY → tblProduct(ID) |
| unitPrice | float | NOT NULL |
| quantity | int | NOT NULL |
| subtotal | float | NOT NULL |

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
2. Tạo package `view.payment`: chứa LoginFrm, HomeFrm, CustomerPayFrm.
3. Tạo package `model`: chứa Customer, Booking, BookingSession, Product, SessionProduct, Payment, User.
4. Tạo package `dao`: chứa UserDAO, CustomerDAO, BookingDAO, BookingSessionDAO, SessionProductDAO, PaymentDAO.
5. Vẽ association từ CustomerPayFrm → CustomerDAO, CustomerPayFrm → BookingDAO, CustomerPayFrm → BookingSessionDAO, CustomerPayFrm → SessionProductDAO, CustomerPayFrm → PaymentDAO.
6. Vẽ dependency từ DAO classes → Entity classes (dashed arrow).
7. Thêm kiểu trả về cho phương thức DAO.

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:LoginFrm` — boundary
3. `:HomeFrm` — boundary
4. `:CustomerPayFrm` — boundary
5. `:UserDAO` — control
6. `:CustomerDAO` — control
7. `:BookingDAO` — control
8. `:BookingSessionDAO` — control
9. `:SessionProductDAO` — control
10. `:PaymentDAO` — control

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 10 lifelines theo thứ tự: Staff, LoginFrm, UserDAO, HomeFrm, CustomerPayFrm, CustomerDAO, BookingDAO, BookingSessionDAO, SessionProductDAO, PaymentDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho giá trị trả về.
5. Sử dụng `alt` fragment cho nhánh chỉnh sửa sản phẩm (nếu khách phản ánh).
6. Sử dụng self-call cho các thao tác nội bộ của form.

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | `actionPerformed("Login")` — nhập username="staff01", password="******" | synchronous |
| 2 | LoginFrm | UserDAO | `checkLogin("staff01", "******")` | synchronous |
| 3 | UserDAO | LoginFrm | `return true` | return |
| 4 | LoginFrm | HomeFrm | `new HomeFrm().setVisible(true)` | synchronous |
| 5 | Staff | HomeFrm | `actionPerformed("Customer paying")` | synchronous |
| 6 | HomeFrm | CustomerPayFrm | `new CustomerPayFrm().setVisible(true)` | synchronous |
| 7 | Staff | CustomerPayFrm | `setText("Nguyen Van A")` + `actionPerformed("SearchCustomer")` | synchronous |
| 8 | CustomerPayFrm | CustomerDAO | `searchByName("Nguyen Van A")` | synchronous |
| 9 | CustomerDAO | CustomerPayFrm | `return ArrayList<Customer>` (1 kết quả: KH001) | return |
| 10 | CustomerPayFrm | CustomerPayFrm | `displayTable(customerList)` | self |
| 11 | Staff | CustomerPayFrm | `clickRow("Nguyen Van A")` | synchronous |
| 12 | CustomerPayFrm | BookingDAO | `getBookingsByCustomer(1)` | synchronous |
| 13 | BookingDAO | CustomerPayFrm | `return ArrayList<Booking>` (B001: Sân S1, T6 18:00-19:00, Active) | return |
| 14 | CustomerPayFrm | CustomerPayFrm | `displayTable(bookingList)` | self |
| 15 | Staff | CustomerPayFrm | `clickButton("Payment", B001)` | synchronous |
| 16 | CustomerPayFrm | BookingSessionDAO | `getCompletedSessionsWithProducts(1)` | synchronous |
| 17 | BookingSessionDAO | CustomerPayFrm | `return ArrayList<BookingSession>` (S1: 10/07, S2: 17/07, mỗi session kèm SessionProduct) | return |
| 18 | CustomerPayFrm | CustomerPayFrm | `buildInvoice(customerInfo, bookingInfo, sessions, products)` — tính tổng tiền thuê, tổng sản phẩm, trừ cọc | self |
| 19 | CustomerPayFrm | Staff | `displayInvoice(invoiceDetail, rentTotal=400000, productTotal=80000, deposit=500000, finalAmount=-20000)` | return |
| 20 | Staff | CustomerPayFrm | `clickEditItem(sessionProduct Coca Cola, newQuantity=1)` (nếu khách phản ánh) | synchronous |
| 21 | CustomerPayFrm | SessionProductDAO | `updateSessionProduct(spId=1, quantity=1, subtotal=15000)` | synchronous |
| 22 | SessionProductDAO | CustomerPayFrm | `return true` | return |
| 23 | CustomerPayFrm | CustomerPayFrm | `recalculateInvoice()` — cập nhật tổng tiền mới | self |
| 24 | CustomerPayFrm | Staff | `displayInvoice(updatedInvoice)` | return |
| 25 | Staff | CustomerPayFrm | `actionPerformed("Confirm")` | synchronous |
| 26 | CustomerPayFrm | new Payment() | `Payment(bookingId=1, paymentDate=now, rentTotal=400000, productTotal=65000, totalAmount=465000, depositDeducted=500000, finalAmount=-35000, method="cash", status="completed")` | self |
| 27 | CustomerPayFrm | PaymentDAO | `createPayment(payment)` | synchronous |
| 28 | PaymentDAO | CustomerPayFrm | `return true` | return |
| 29 | CustomerPayFrm | BookingDAO | `updateBookingStatus(1, "Paid")` | synchronous |
| 30 | BookingDAO | CustomerPayFrm | `return true` | return |
| 31 | CustomerPayFrm | Staff | `showMessage("Thanh toan thanh cong")` | return |

### ASCII Sequence Diagram

```
Staff      LoginFrm   UserDAO   HomeFrm   CustomerPayFrm   CustomerDAO   BookingDAO   BookingSessionDAO   SessionProductDAO   PaymentDAO
  |            |          |         |            |               |             |                |                   |                |
  |--login---->|          |         |            |               |             |                |                   |                |
  |            |--checkLogin()---->|            |               |             |                |                   |                |
  |            |<--true---|         |            |               |             |                |                   |                |
  |            |--open HomeFrm---->|            |               |             |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |--select "Customer paying"----->|            |               |             |                |                   |                |
  |            |          |         |--open Frm->|               |             |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |--enter "Nguyen Van A", Search->|            |               |             |                |                   |                |
  |            |          |         |            |--searchByName()----------->|                |                   |                |
  |            |          |         |            |<--List<Customer>|           |                |                   |                |
  |            |          |         |            |--display list  |             |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |--click customer------>|         |            |               |             |                |                   |                |
  |            |          |         |            |--getBookingsByCustomer()-->|                |                   |                |
  |            |          |         |            |<--List<Booking>|            |                |                   |                |
  |            |          |         |            |--display bookings           |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |--click Payment B001-->|         |            |               |             |                |                   |                |
  |            |          |         |            |--getCompletedSessionsWithProducts()--------->|                   |                |
  |            |          |         |            |<--List<BookingSession>      |                |                   |                |
  |            |          |         |            |--buildInvoice  |             |                |                   |                |
  |            |          |         |            |--display invoice            |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |  [alt: customer complains about Coca Cola qty]               |             |                |                   |                |
  |--edit Coca Cola qty=1>|         |            |               |             |                |                   |                |
  |            |          |         |            |--updateSessionProduct()----|----------------|------------------>|                |
  |            |          |         |            |<--true---------|             |                |                   |                |
  |            |          |         |            |--recalculate   |             |                |                   |                |
  |            |          |         |            |--display updated invoice    |                |                   |                |
  |  [end alt] |          |         |            |               |             |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |--click Confirm-------->|        |            |               |             |                |                   |                |
  |            |          |         |            |--createPayment(payment)-----|----------------|-------------------|--------------->|
  |            |          |         |            |<--true---------|             |                |                   |                |
  |            |          |         |            |--updateBookingStatus(1,"Paid")              |                   |                |
  |            |          |         |            |<--true---------|             |                |                   |                |
  |            |          |         |            |               |             |                |                   |                |
  |<--success--|          |         |            |               |             |                |                   |                |
```

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Customer paying | Thanh toán thành công booking với nhiều phiên và sản phẩm, có chỉnh sửa |
| TC02 | Customer paying | Tìm khách hàng không tồn tại |
| TC03 | Customer paying | Khách hàng có booking nhưng không có phiên thuê hoàn thành |

### TC01: Thanh toán thành công booking với nhiều phiên và sản phẩm

**Purpose:** Kiểm tra quy trình thanh toán hoàn chỉnh: tìm khách hàng, chọn booking, xem hóa đơn, chỉnh sửa sản phẩm khi khách phản ánh, xác nhận thanh toán.

**Database trước khi test:**

**tblUser:**
| ID | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | staff01 | 123456 | Nguyen Thi Staff | staff |

**tblCustomer:**
| ID | code | customerName | phoneNumber | address |
|----|------|-------------|-------------|---------|
| 1 | KH001 | Nguyen Van A | 0912345678 | Quan 1, TP.HCM |

**tblCourt:**
| ID | code | courtName | courtType | pricePerSession | status |
|----|------|-----------|-----------|-----------------|--------|
| 1 | S1 | San 1 | San nho | 200000 | active |

**tblBooking:**
| ID | courtID | customerID | bookingDate | startDate | endDate | timeSlot | dayOfWeek | totalSessions | totalAmount | deposit | status |
|----|---------|------------|-------------|-----------|---------|----------|-----------|---------------|-------------|---------|--------|
| 1 | 1 | 1 | 2026-06-01 | 2026-07-01 | 2026-09-30 | 18:00-19:00 | Thu 6 | 12 | 2400000 | 500000 | active |

**tblBookingSession:**
| ID | bookingID | sessionDate | startTime | endTime | checkinTime | checkoutTime | rentAmount | status |
|----|-----------|-------------|-----------|---------|-------------|--------------|------------|--------|
| 1 | 1 | 2026-07-10 | 18:00 | 19:00 | 18:00 | 19:15 | 200000 | completed |
| 2 | 1 | 2026-07-17 | 18:00 | 19:00 | 17:55 | 19:00 | 200000 | completed |
| 3 | 1 | 2026-07-24 | 18:00 | 19:00 | NULL | NULL | 0 | pending |

**tblProduct:**
| ID | code | productName | unit | price | stockQuantity |
|----|------|-------------|------|-------|---------------|
| 1 | P01 | Coca Cola 330ml | Chai | 15000 | 100 |
| 2 | P03 | Nuoc suoi 500ml | Chai | 10000 | 150 |
| 3 | P04 | Banh mi | Cai | 20000 | 50 |

**tblSessionProduct:**
| ID | sessionID | productID | unitPrice | quantity | subtotal |
|----|-----------|-----------|-----------|----------|----------|
| 1 | 1 | 1 | 15000 | 2 | 30000 |
| 2 | 1 | 3 | 20000 | 1 | 20000 |
| 3 | 2 | 2 | 10000 | 3 | 30000 |

**tblPayment:** (rỗng — chưa có thanh toán nào cho B001)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username=`staff01`, password=`123456`, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 3 | Chọn menu "Customer paying" | Giao diện tìm kiếm khách hàng hiển thị |
| 4 | Nhập "Nguyen Van A", nhấn Search | Bảng kết quả hiển thị: KH001 — Nguyen Van A — 0912345678 |
| 5 | Click chọn "Nguyen Van A" | Bảng booking hiển thị: B001 — Sân S1 — T6 18:00-19:00 — 2,400,000đ — Active |
| 6 | Click nút Payment cho B001 | Hóa đơn chi tiết hiển thị: thông tin KH + thông tin booking + bảng phiên thuê và sản phẩm |
| 7 | Hóa đơn hiển thị: Phiên 1 (10/07): Coca Cola × 2 = 30,000đ, Bánh mì × 1 = 20,000đ; Phiên 2 (17/07): Nước suối × 3 = 30,000đ. Tổng thuê: 400,000đ, Tổng SP: 80,000đ, Cọc: 500,000đ, Còn lại: -20,000đ | Dữ liệu chính xác theo database |
| 8 | Khách phản ánh chỉ dùng 1 Coca Cola. Staff click edit dòng Coca Cola, sửa SL từ 2 xuống 1 | Hệ thống cập nhật: Coca Cola × 1 = 15,000đ. Tổng SP: 65,000đ. Còn lại: -35,000đ |
| 9 | Nhấn Confirm | Hệ thống hiển thị thông báo "Thanh toan thanh cong" |

**Database sau khi test:**

**tblBooking:** (cập nhật 1 dòng)
| ID | courtID | customerID | bookingDate | startDate | endDate | timeSlot | dayOfWeek | totalSessions | totalAmount | deposit | status |
|----|---------|------------|-------------|-----------|---------|----------|-----------|---------------|-------------|---------|--------|
| 1 | 1 | 1 | 2026-06-01 | 2026-07-01 | 2026-09-30 | 18:00-19:00 | Thu 6 | 12 | 2400000 | 500000 | Paid |

**tblSessionProduct:** (cập nhật 1 dòng)
| ID | sessionID | productID | unitPrice | quantity | subtotal |
|----|-----------|-----------|-----------|----------|----------|
| 1 | 1 | 1 | 15000 | 1 | 15000 |

**tblPayment:** (thêm 1 dòng)
| ID | bookingID | paymentDate | rentTotal | productTotal | totalAmount | depositDeducted | finalAmount | method | status |
|----|-----------|-------------|-----------|--------------|-------------|-----------------|-------------|--------|--------|
| 1 | 1 | 2026-06-08 10:30 | 400000 | 65000 | 465000 | 500000 | -35000 | cash | completed |

---

### TC02: Tìm khách hàng không tồn tại

**Purpose:** Kiểm tra hệ thống xử lý đúng khi tìm kiếm khách hàng không có trong database.

**Database trước khi test:**
- tblCustomer: không có khách hàng nào có tên "Vo Thi C"

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Customer paying" | Giao diện tìm kiếm hiển thị |
| 2 | Nhập "Vo Thi C" vào ô Customer Name, nhấn Search | Bảng kết quả trống, thông báo "Khong tim thay khach hang" |

**Database sau khi test:**
- Không có thay đổi

---

### TC03: Khách hàng có booking nhưng không có phiên thuê hoàn thành

**Purpose:** Kiểm tra hệ thống khi khách hàng có booking nhưng chưa có phiên nào được nhận/trả sân.

**Database trước khi test:**
- tblCustomer: "Tran Thi B" (ID=2, code=KH002)
- tblBooking: B002 (ID=2, customerID=2, status="active", deposit=500000)
- tblBookingSession: S4 (ID=4, bookingID=2, checkinTime=NULL, checkoutTime=NULL, status="pending")
- tblSessionProduct: không có sản phẩm nào cho S4
- tblPayment: không có bản ghi nào cho B002

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Customer paying" | Giao diện tìm kiếm hiển thị |
| 2 | Nhập "Tran Thi B", nhấn Search | Bảng kết quả hiển thị: KH002 — Tran Thi B |
| 3 | Click chọn "Tran Thi B" | Bảng booking hiển thị: B002 — Active |
| 4 | Click nút Payment cho B002 | Hệ thống thông báo "Chua co phien nao hoan thanh de thanh toan" |

**Database sau khi test:**
- Không có thay đổi
