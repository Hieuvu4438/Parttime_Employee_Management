# Subject No. 52 — Mini Football Field — Module "Update used items of the rental session"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Cập nhật sản phẩm đã sử dụng của phiên thuê sân

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. Hệ thống xác thực thành công. |
| 3 | Giao diện Home xuất hiện với các menu chức năng: Booking, Import goods, Update used items, Customer paying, Revenue statistics, Statistics of time slot. |
| 4 | Staff chọn menu **Update used items**. |
| 5 | Giao diện tìm kiếm khách hàng xuất hiện: ô nhập tên khách hàng (txtCustomerName), nút Search (btnSearchCustomer), bảng danh sách khách hàng (tblCustomerList). |
| 6 | Staff nhập "Nguyen Van A" vào ô Customer Name, nhấn nút Search. Hệ thống truy vấn database tìm khách hàng có tên chứa "Nguyen Van A". |
| 7 | Hệ thống hiển thị bảng danh sách khách hàng: cột Mã KH, Tên KH, Số điện thoại, Địa chỉ. Kết quả: KH001 — Nguyen Van A — 0912345678 — Quan 1, TP.HCM. |
| 8 | Staff click chọn dòng "Nguyen Van A" (KH001). Hệ thống hiển thị danh sách booking đang hoạt động của khách hàng này: bảng gồm cột Mã booking, Ngày đặt, Sân, Khung giờ, Trạng thái. |
| 9 | Staff click nút **Checkout** cho booking B001 (phiên thuê S1, Thứ 6 ngày 10/07/2026, 18:00-19:00, Sân S1). |
| 10 | Hệ thống hiển thị giao diện cập nhật phiên thuê: thông tin phiên (Sân S1, Thứ 6 10/07/2026, 18:00-19:00), ô nhập giờ nhận sân (txtCheckinTime), ô nhập giờ trả sân (txtCheckoutTime), ô nhập tiền thuê (txtRentAmount), bảng danh sách sản phẩm đã sử dụng (tblSessionProduct), nút "Thêm sản phẩm" (btnAddItem), nút Confirm (btnConfirm). |
| 11 | Staff nhập giờ nhận sân: "18:00", giờ trả sân: "19:15" (trả muộn 15 phút so với giờ đặt). Hệ thống tự động tính tiền thuê phát sinh thêm do trả muộn. |
| 12 | Staff nhấn nút "Thêm sản phẩm". Giao diện tìm kiếm sản phẩm xuất hiện: ô nhập tên sản phẩm (txtProductName), nút Search (btnSearchProduct), bảng danh sách sản phẩm (tblProductList). |
| 13 | Staff nhập "Coca Cola" vào ô tên sản phẩm, nhấn Search. Hệ thống hiển thị danh sách: P01 — Coca Cola 330ml — Chai — 15,000đ; P02 — Coca Cola 500ml — Chai — 20,000đ. |
| 14 | Staff click chọn "Coca Cola 330ml" (P01). Giao diện nhập đơn giá và số lượng xuất hiện: ô nhập đơn giá (txtUnitPrice) mặc định 15,000đ, ô nhập số lượng (txtQuantity). |
| 15 | Staff nhập số lượng = 2, nhấn OK. Sản phẩm được thêm vào danh sách: Coca Cola 330ml — Đơn giá: 15,000đ — SL: 2 — Thành tiền: 30,000đ. |
| 16 | Staff nhấn nút "Thêm sản phẩm" lần 2. Nhập "Bánh mì" vào ô tên, nhấn Search. Hiển thị: P05 — Bánh mì — Cái — 20,000đ. |
| 17 | Staff click chọn "Bánh mì" (P05), nhập số lượng = 1, nhấn OK. Danh sách cập nhật thêm: Bánh mì — Đơn giá: 20,000đ — SL: 1 — Thành tiền: 20,000đ. |
| 18 | Staff nhấn nút "Thêm sản phẩm" lần 3. Nhập "Nước suối" vào ô tên, nhấn Search. Hiển thị: P03 — Nước suối 500ml — Chai — 10,000đ. |
| 19 | Staff click chọn "Nước suối 500ml" (P03), nhập số lượng = 3, nhấn OK. Danh sách cập nhật thêm: Nước suối 500ml — Đơn giá: 10,000đ — SL: 3 — Thành tiền: 30,000đ. |
| 20 | Hệ thống hiển thị tổng kết danh sách sản phẩm: Coca Cola 330ml × 2 = 30,000đ, Bánh mì × 1 = 20,000đ, Nước suối 500ml × 3 = 30,000đ. Dòng cuối: Tổng tiền sản phẩm = 80,000đ. |
| 21 | Staff nhấn nút **Confirm**. Hệ thống cập nhật giờ nhận/trả sân, tiền thuê, và danh sách sản phẩm đã sử dụng vào database. Hệ thống thông báo "Cap nhat thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập tên khách hàng không tồn tại trong hệ thống → danh sách kết quả trống, hiển thị thông báo "Khong tim thay khach hang".
- **EL2:** Khách hàng không có booking đang hoạt động → hiển thị danh sách booking trống, thông báo "Khong co booking dang hoat dong".
- **EL3:** Staff nhập số lượng sản phẩm <= 0 → hệ thống cảnh báo "So luong phai lon hon 0".
- **EL4:** Staff nhấn Confirm khi chưa nhập giờ nhận/trả sân → hệ thống yêu cầu nhập đầy đủ thông tin.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý thuê sân bóng đá mini. Sân bóng có nhiều sân nhỏ, có thể gộp 2 hoặc 4 sân thành sân lớn. Mỗi sân được nhiều khách hàng thuê ở các khung giờ khác nhau. Khách hàng đặt sân theo phiên trong tuần hoặc theo tháng. Khi đặt sân, khách hàng nhận phiếu đặt và phải đặt cọc trước. Khi khách đến nhận sân và trả sân, nhân viên cập nhật giờ nhận, giờ trả, tiền thuê, và danh sách sản phẩm (đồ ăn, thức uống) mà khách đã sử dụng trong phiên đó. Khi khách hàng thanh toán, hệ thống tạo hóa đơn chi tiết bao gồm thông tin thuê sân và sản phẩm đã sử dụng. Chủ sân nhập hàng hóa từ nhiều nhà cung cấp khác nhau.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Sân bóng (Court) | Entity class | | Đối tượng quản lý chính, có thuộc tính riêng (tên, loại, giá) |
| Khách hàng (Customer) | Entity class | | Người thuê sân, có thông tin cá nhân |
| Phiếu đặt (Booking) | Entity class | | Bản ghi đặt sân của khách, liên kết KH và sân |
| Phiên thuê (BookingSession) | Entity class | | Mỗi lần khách đến nhận sân cụ thể |
| Sản phẩm (Product) | Entity class | | Đồ ăn/thức uống bán cho khách |
| Sản phẩm phiên (SessionProduct) | Entity class | | Sản phẩm đã sử dụng trong 1 phiên thuê |
| Người dùng (User) | Entity class | | Nhân viên thao tác trên hệ thống |
| Nhà cung cấp (Supplier) | Entity class | | Cung cấp hàng hóa cho sân bóng |
| Phiếu nhập (ImportInvoice) | Entity class | | Hóa đơn nhập hàng từ nhà cung cấp |
| Chi tiết phiếu nhập (ImportInvoiceDetail) | Entity class | | Chi tiết từng sản phẩm trong phiếu nhập |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Court | courtId (PK), code, courtName, courtType, pricePerSession, status |
| Customer | customerId (PK), code, customerName, phoneNumber, address |
| Booking | bookingId (PK), courtId (FK), customerId (FK), bookingDate, startDate, endDate, timeSlot, dayOfWeek, totalSessions, totalAmount, deposit, status |
| BookingSession | sessionId (PK), bookingId (FK), sessionDate, startTime, endTime, checkinTime, checkoutTime, rentAmount, status |
| Product | productId (PK), code, productName, unit, price, stockQuantity |
| SessionProduct | sessionProductId (PK), sessionId (FK), productId (FK), unitPrice, quantity, subtotal |
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
| - courtName: String|     | - customerName   |
| - courtType: String|     | - phoneNumber    |
| - pricePerSession|       | - address: String|
| - status: String |       +------------------+
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
|                  |        | n         | n
|                  |        v           v
|                  |  +------------------+  +------------------+
|                  |  | BookingSession   |  |     Payment      |
|                  |  +------------------+  +------------------+
|                  |  | - sessionId: int |  | - paymentId: int |
|                  |  | - sessionDate    |  | - paymentDate    |
|                  |  | - startTime      |  | - totalAmount    |
|                  |  | - endTime        |  | - method         |
|                  |  | - checkinTime    |  | - status         |
|                  |  | - checkoutTime   |  +------------------+
|                  |  | - rentAmount     |
|                  |  | - status         |
|                  |  +------------------+
+------------------+        | 1
       Product              |
+------------------+        | n
| - productId: int |        v
| - code: String   |  +------------------+
| - productName    |  | SessionProduct   |
| - unit: String   |  +------------------+
| - price: float   |  | - sessionProductId|
| - stockQuantity  |  | - unitPrice      |
+------------------+  | - quantity       |
        | 1           | - subtotal       |
        |             +------------------+
        | n                  | n
        +--------------------+

+------------------+       +------------------+
|     Supplier     |       |       User       |
+------------------+       +------------------+
| - supplierId: int|       | - userId: int    |
| - code: String   |       | - username       |
| - supplierName   |       | - password       |
| - address        |       | - fullName       |
| - email          |       | - role: String   |
| - phone          |       +------------------+
| - description    |
+------------------+
        | 1
        |
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
        |
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
| Court → BookingSession | 1-n | Một sân có nhiều phiên thuê khác nhau |
| BookingSession → SessionProduct | 1-n | Một phiên thuê có nhiều sản phẩm đã sử dụng |
| Product → SessionProduct | 1-n | Một sản phẩm xuất hiện trong nhiều phiên |
| Booking → Payment | 1-1 | Mỗi booking có 1 phiếu thanh toán |
| Supplier → ImportInvoice | 1-n | Một nhà cung cấp có nhiều phiếu nhập |
| ImportInvoice → ImportInvoiceDetail | 1-n | Một phiếu nhập có nhiều chi tiết sản phẩm |
| Product → ImportInvoiceDetail | 1-n | Một sản phẩm xuất hiện trong nhiều phiếu nhập |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**Các bước vẽ tổng quan:**

1. Mở Visual Paradigm → New → Class Diagram (trong danh mục Diagrams).
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity: Court, Customer, Booking, BookingSession, Product, SessionProduct, User, Supplier, ImportInvoice, ImportInvoiceDetail.
3. Tạo view class boxes từ các interface: HomeFrm, UpdateSessionItemsFrm.
4. Vẽ relationships giữa các class theo bảng quan hệ bên dưới.
5. Thêm multiplicities và role names cho mỗi đường kết nối.

**Cấu trúc 1 class box (3 ngăn):**

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> BookingSession`.
- **Ngăn 2 (thuộc tính):** Mỗi thuộc tính ghi dạng `-attributeName: Type`. Ví dụ: `-checkinTime: String`, `-rentAmount: double`.
- **Ngăn 3 (phương thức):** Mỗi phương thức ghi dạng `+methodName(params): ReturnType`. Ví dụ: `+searchCustomer(keyword: String): List<Customer>`.

**Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Court | `<<entity>>` | -id: int, -code: String, -courtName: String, -courtType: String, -pricePerSession: double, -status: String | getter/setter |
| Customer | `<<entity>>` | -id: int, -code: String, -customerName: String, -phoneNumber: String, -address: String | +searchCustomer(keyword: String): List<Customer> |
| Booking | `<<entity>>` | -id: int, -bookingDate: Date, -startDate: Date, -endDate: Date, -timeSlot: String, -totalSessions: int, -totalAmount: double, -deposit: double, -status: String | +getActiveBookings(customerId: int): List<Booking> |
| BookingSession | `<<entity>>` | -id: int, -sessionDate: Date, -startTime: String, -endTime: String, -checkinTime: String, -checkoutTime: String, -rentAmount: double, -status: String | +updateSession(sessionId: int, checkinTime: String, checkoutTime: String, rentAmount: double): boolean |
| Product | `<<entity>>` | -id: int, -code: String, -productName: String, -unit: String, -price: double, -stockQuantity: int | +searchProduct(keyword: String): List<Product> |
| SessionProduct | `<<entity>>` | -id: int, -unitPrice: double, -quantity: int, -subtotal: double | +addSessionProduct(sp: SessionProduct): boolean |
| User | `<<entity>>` | -id: int, -username: String, -password: String, -fullName: String, -role: String | +checkLogin(username: String, password: String): boolean |
| Supplier | `<<entity>>` | -id: int, -code: String, -supplierName: String, -address: String, -email: String, -phone: String, -description: String | getter/setter |
| ImportInvoice | `<<entity>>` | -id: int, -importDate: Date, -totalAmount: double | getter/setter |
| ImportInvoiceDetail | `<<entity>>` | -id: int, -unitPrice: double, -quantity: int, -amount: double | getter/setter |

**Bảng chi tiết view classes:**

| View class | Stereotype | UI Elements |
|------------|-----------|-------------|
| HomeFrm | `<<boundary>>` | subUpdateItems: JButton |
| UpdateSessionItemsFrm | `<<boundary>>` | inCustomerName: JTextField, subSearchCustomer: JButton, outsubCustomerList: JTable (clickable), outBookingList: JTable, btnCheckout: JButton, outSessionInfo: JLabel, inCheckinTime: JTextField, inCheckoutTime: JTextField, inRentAmount: JTextField, inProductName: JTextField, subSearchProduct: JButton, outsubProductList: JTable (clickable), inUnitPrice: JTextField, inQuantity: JTextField, subOK: JButton, outItemList: JTable, outTotal: JLabel, subConfirm: JButton |

**Cách vẽ quan hệ:**

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Booking.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Booking ◆→ BookingSession.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: UpdateSessionItemsFrm → CustomerDAO.

**Cách ghi multiplicity:**

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Court "1" --- "n" Booking.

**Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|--------------|------------|
| Court | Booking | Association | 1 — n | Một sân được nhiều khách đặt |
| Customer | Booking | Association | 1 — n | Một khách hàng có nhiều booking |
| Booking | BookingSession | Composition | 1 — n | Một booking có nhiều phiên thuê, phiên không tồn tại nếu không có booking |
| BookingSession | SessionProduct | Composition | 1 — n | Một phiên có nhiều sản phẩm đã sử dụng |
| Product | SessionProduct | Association | 1 — n | Một sản phẩm xuất hiện trong nhiều phiên |
| Supplier | ImportInvoice | Association | 1 — n | Một nhà cung cấp có nhiều phiếu nhập |
| ImportInvoice | ImportInvoiceDetail | Composition | 1 — n | Một phiếu nhập có nhiều chi tiết |
| Product | ImportInvoiceDetail | Association | 1 — n | Một sản phẩm xuất hiện trong nhiều phiếu nhập |
| UpdateSessionItemsFrm | CustomerDAO | Dependency | — | Frm sử dụng CustomerDAO để tìm khách hàng |
| UpdateSessionItemsFrm | BookingDAO | Dependency | — | Frm sử dụng BookingDAO để lấy booking |
| UpdateSessionItemsFrm | BookingSessionDAO | Dependency | — | Frm sử dụng BookingSessionDAO để cập nhật phiên |
| UpdateSessionItemsFrm | ProductDAO | Dependency | — | Frm sử dụng ProductDAO để tìm sản phẩm |
| UpdateSessionItemsFrm | SessionProductDAO | Dependency | — | Frm sử dụng SessionProductDAO để thêm sản phẩm phiên |

**Ví dụ cụ thể trên Visual Paradigm:**

1. **Vẽ quan hệ Booking → BookingSession (Composition 1-n):**
   - Kéo class Booking lên canvas, kéo class BookingSession bên dưới.
   - Chọn tool "Association" → click vào Booking, kéo đến BookingSession.
   - Đặt multiplicity "1" phía Booking, "n" phía BookingSession.
   - Click chuột phải → "Association End" → phía Booking đặt "filled diamond" (◆).

2. **Vẽ dependency UpdateSessionItemsFrm → ProductDAO:**
   - Chọn tool "Dependency" (đường dashed) → click vào UpdateSessionItemsFrm, kéo đến ProductDAO.
   - Mũi tên tam giác rỗng (▷) tự động hiển thị phía ProductDAO.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Sau khi đăng nhập thành công -> HomeFrm xuất hiện:
  một lựa chọn để cập nhật sản phẩm đã sử dụng -> subUpdateItems

Chọn Update used items -> UpdateSessionItemsFrm xuất hiện:
  ô nhập tên khách hàng -> inCustomerName
  nút Search -> subSearchCustomer
  bảng danh sách khách hàng (click được) -> outsubCustomerList

Nhập tên khách hàng, nhấn Search -> cần phương thức:
  tên: searchCustomer()
  đầu vào: keyword
  đầu ra: List<Customer>
  gán cho entity class: Customer.

Nhấn vào khách hàng "Nguyen Van A" -> hiển thị danh sách booking:
  bảng danh sách booking đang hoạt động -> outBookingList
  nút Checkout cho mỗi booking -> subCheckout

Click khách hàng -> cần phương thức:
  tên: getActiveBookings()
  đầu vào: customerId
  đầu ra: List<Booking>
  gán cho entity class: Booking.

Nhấn nút Checkout -> hiển thị giao diện cập nhật phiên thuê:
  thông tin phiên (sân, ngày, giờ) -> outSessionInfo
  ô nhập giờ nhận sân -> inCheckinTime
  ô nhập giờ trả sân -> inCheckoutTime
  ô nhập tiền thuê -> inRentAmount
  nút Thêm sản phẩm -> subAddItem
  bảng danh sách sản phẩm đã sử dụng -> outItemList
  tổng tiền sản phẩm -> outTotal
  nút Confirm -> subConfirm

Nhập giờ nhận, giờ trả -> cập nhật nội bộ.

Nhấn Thêm sản phẩm -> hiển thị giao diện tìm sản phẩm:
  ô nhập tên sản phẩm -> inProductName
  nút Search -> subSearchProduct
  bảng danh sách sản phẩm (click được) -> outsubProductList
  ô nhập đơn giá -> inUnitPrice
  ô nhập số lượng -> inQuantity
  nút OK -> subOK

Nhập tên sản phẩm, nhấn Search -> cần phương thức:
  tên: searchProduct()
  đầu vào: keyword
  đầu ra: List<Product>
  gán cho entity class: Product.

Chọn sản phẩm, nhập số lượng, nhấn OK -> cần phương thức:
  tên: addSessionProduct()
  đầu vào: sessionId, productId, unitPrice, quantity
  đầu ra: boolean
  gán cho entity class: SessionProduct.

Nhấn Confirm -> cần phương thức:
  tên: updateSession()
  đầu vào: sessionId, checkinTime, checkoutTime, rentAmount
  đầu ra: boolean
  gán cho entity class: BookingSession.

### Summary
View classes: HomeFrm, UpdateSessionItemsFrm
Methods: searchCustomer(), getActiveBookings(), searchProduct(), addSessionProduct(), updateSession()

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

**UpdateSessionItemsFrm:**
- `inCustomerName`: ô nhập tên khách hàng (JTextField)
- `subSearchCustomer`: nút tìm kiếm khách hàng (JButton)
- `outsubCustomerList`: bảng danh sách khách hàng tìm được, click để chọn (JTable)
- `outBookingList`: bảng danh sách booking đang hoạt động của khách hàng (JTable)
- `btnCheckout`: nút Checkout cho từng phiên thuê trong bảng booking (JButton)
- `outSessionInfo`: thông tin phiên thuê: sân, ngày, giờ (JLabel)
- `inCheckinTime`: ô nhập giờ nhận sân (JTextField)
- `inCheckoutTime`: ô nhập giờ trả sân (JTextField)
- `inRentAmount`: ô nhập tiền thuê (JTextField)
- `inProductName`: ô nhập tên sản phẩm cần tìm (JTextField)
- `subSearchProduct`: nút tìm kiếm sản phẩm (JButton)
- `outsubProductList`: bảng danh sách sản phẩm tìm được, click để chọn (JTable)
- `inUnitPrice`: ô nhập đơn giá sản phẩm (JTextField)
- `inQuantity`: ô nhập số lượng sản phẩm (JTextField)
- `subOK`: nút OK thêm sản phẩm vào danh sách (JButton)
- `outItemList`: bảng danh sách sản phẩm đã sử dụng trong phiên (JTable) — cột: Tên SP, Đơn giá, SL, Thành tiền
- `outTotal`: nhãn hiển thị tổng tiền sản phẩm (JLabel)
- `subConfirm`: nút xác nhận cập nhật (JButton)

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| txtCustomerName | JTextField | Ô nhập tên khách hàng cần tìm |
| btnSearchCustomer | JButton | Nút tìm kiếm khách hàng |
| tblCustomerList | JTable | Bảng kết quả tìm khách hàng (click được) |
| tblBookingList | JTable | Bảng danh sách booking đang hoạt động |
| btnCheckout | JButton | Nút checkout cho từng phiên thuê |
| txtCheckinTime | JTextField | Ô nhập giờ nhận sân |
| txtCheckoutTime | JTextField | Ô nhập giờ trả sân |
| txtRentAmount | JTextField | Ô nhập tiền thuê |
| txtProductName | JTextField | Ô nhập tên sản phẩm tìm kiếm |
| btnSearchProduct | JButton | Nút tìm kiếm sản phẩm |
| tblProductList | JTable | Bảng kết quả tìm sản phẩm (click được) |
| txtUnitPrice | JTextField | Ô nhập đơn giá sản phẩm |
| txtQuantity | JTextField | Ô nhập số lượng sản phẩm |
| btnOK | JButton | Nút thêm sản phẩm vào danh sách |
| tblSessionProduct | JTable | Bảng sản phẩm đã sử dụng trong phiên |
| lblTotal | JLabel | Hiển thị tổng tiền sản phẩm |
| btnConfirm | JButton | Nút xác nhận cập nhật |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| CustomerDAO | `searchByName(String name): ArrayList<Customer>` | Tìm kiếm khách hàng theo tên |
| BookingDAO | `getActiveBookingsByCustomer(int customerId): ArrayList<Booking>` | Lấy danh sách booking đang hoạt động của khách hàng |
| BookingSessionDAO | `getSessionsByBooking(int bookingId): ArrayList<BookingSession>` | Lấy danh sách phiên thuê theo booking |
| BookingSessionDAO | `updateSession(int sessionId, String checkinTime, String checkoutTime, double rentAmount): boolean` | Cập nhật giờ nhận/trả sân và tiền thuê |
| ProductDAO | `searchByName(String name): ArrayList<Product>` | Tìm kiếm sản phẩm theo tên |
| SessionProductDAO | `addSessionProduct(SessionProduct sp): boolean` | Thêm sản phẩm đã sử dụng vào phiên thuê |
| SessionProductDAO | `getProductsBySession(int sessionId): ArrayList<SessionProduct>` | Lấy danh sách sản phẩm đã sử dụng trong phiên |

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Court | Entity | id: int (PK), code: String, courtName: String, courtType: String, pricePerSession: double, status: String |
| Customer | Entity | id: int (PK), code: String, customerName: String, phoneNumber: String, address: String |
| Booking | Entity | id: int (PK), bookingDate: Date, startDate: Date, endDate: Date, timeSlot: String, totalSessions: int, totalAmount: double, deposit: double, status: String, court: Court, customer: Customer |
| BookingSession | Entity | id: int (PK), sessionDate: Date, startTime: String, endTime: String, checkinTime: String, checkoutTime: String, rentAmount: double, status: String, booking: Booking |
| Product | Entity | id: int (PK), code: String, productName: String, unit: String, price: double, stockQuantity: int |
| SessionProduct | Entity | id: int (PK), unitPrice: double, quantity: int, subtotal: double, bookingSession: BookingSession, product: Product |
| User | Entity | id: int (PK), username: String, password: String, fullName: String, role: String |
| Supplier | Entity | id: int (PK), code: String, supplierName: String, address: String, email: String, phone: String, description: String |
| ImportInvoice | Entity | id: int (PK), importDate: Date, totalAmount: double, supplier: Supplier |
| ImportInvoiceDetail | Entity | id: int (PK), unitPrice: double, quantity: int, amount: double, importInvoice: ImportInvoice, product: Product |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Customer | Tìm kiếm và hiển thị thông tin khách hàng |
| Booking | Hiển thị danh sách booking đang hoạt động |
| BookingSession | Cập nhật giờ nhận/trả sân, tiền thuê |
| Product | Tìm kiếm và hiển thị sản phẩm (đồ ăn, thức uống) |
| SessionProduct | Lưu sản phẩm đã sử dụng trong phiên thuê |
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

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo package `view.updateitems`: chứa LoginFrm, HomeFrm, UpdateSessionItemsFrm.
3. Tạo package `model`: chứa Customer, Booking, BookingSession, Product, SessionProduct, User.
4. Tạo package `dao`: chứa UserDAO, CustomerDAO, BookingDAO, BookingSessionDAO, ProductDAO, SessionProductDAO.
5. Vẽ association từ UpdateSessionItemsFrm → CustomerDAO, UpdateSessionItemsFrm → BookingDAO, UpdateSessionItemsFrm → BookingSessionDAO, UpdateSessionItemsFrm → ProductDAO, UpdateSessionItemsFrm → SessionProductDAO.
6. Vẽ dependency từ DAO classes → Entity classes (dashed arrow).
7. Thêm kiểu trả về cho phương thức DAO (ArrayList<Customer>, ArrayList<Booking>, ...).

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:LoginFrm` — boundary
3. `:HomeFrm` — boundary
4. `:UpdateSessionItemsFrm` — boundary
5. `:UserDAO` — control
6. `:CustomerDAO` — control
7. `:BookingDAO` — control
8. `:BookingSessionDAO` — control
9. `:ProductDAO` — control
10. `:SessionProductDAO` — control

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 10 lifelines theo thứ tự: Staff, LoginFrm, UserDAO, HomeFrm, UpdateSessionItemsFrm, CustomerDAO, BookingDAO, BookingSessionDAO, ProductDAO, SessionProductDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho giá trị trả về.
5. Sử dụng `loop` fragment cho việc lặp thêm sản phẩm.
6. Sử dụng self-call cho các thao tác nội bộ của form (hiển thị, tính toán).

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | `actionPerformed("Login")` — nhập username="staff01", password="******" | synchronous |
| 2 | LoginFrm | UserDAO | `checkLogin("staff01", "******")` | synchronous |
| 3 | UserDAO | LoginFrm | `return true` | return |
| 4 | LoginFrm | HomeFrm | `new HomeFrm().setVisible(true)` | synchronous |
| 5 | Staff | HomeFrm | `actionPerformed("Update used items")` | synchronous |
| 6 | HomeFrm | UpdateSessionItemsFrm | `new UpdateSessionItemsFrm().setVisible(true)` | synchronous |
| 7 | Staff | UpdateSessionItemsFrm | `setText("Nguyen Van A")` + `actionPerformed("SearchCustomer")` | synchronous |
| 8 | UpdateSessionItemsFrm | CustomerDAO | `searchByName("Nguyen Van A")` | synchronous |
| 9 | CustomerDAO | UpdateSessionItemsFrm | `return ArrayList<Customer>` (1 kết quả: KH001) | return |
| 10 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `displayTable(customerList)` | self |
| 11 | Staff | UpdateSessionItemsFrm | `clickRow("Nguyen Van A")` | synchronous |
| 12 | UpdateSessionItemsFrm | BookingDAO | `getActiveBookingsByCustomer(1)` | synchronous |
| 13 | BookingDAO | UpdateSessionItemsFrm | `return ArrayList<Booking>` (B001: S1, T6 10/07, 18:00-19:00) | return |
| 14 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `displayTable(bookingList)` | self |
| 15 | Staff | UpdateSessionItemsFrm | `clickButton("Checkout", B001, session S1)` | synchronous |
| 16 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `displaySessionInfo(S1, Sân S1, T6 10/07, 18:00-19:00)` | self |
| 17 | Staff | UpdateSessionItemsFrm | `setText(checkinTime="18:00")` + `setText(checkoutTime="19:15")` | synchronous |
| 18 | Staff | UpdateSessionItemsFrm | `actionPerformed("AddItem")` | synchronous |
| 19 | Staff | UpdateSessionItemsFrm | `setText("Coca Cola")` + `actionPerformed("SearchProduct")` | synchronous |
| 20 | UpdateSessionItemsFrm | ProductDAO | `searchByName("Coca Cola")` | synchronous |
| 21 | ProductDAO | UpdateSessionItemsFrm | `return ArrayList<Product>` (P01: Coca Cola 330ml, P02: Coca Cola 500ml) | return |
| 22 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `displayTable(productList)` | self |
| 23 | Staff | UpdateSessionItemsFrm | `clickRow("Coca Cola 330ml")` + `setText(unitPrice=15000)` + `setText(quantity=2)` + `actionPerformed("OK")` | synchronous |
| 24 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `addToItemList("Coca Cola 330ml", 15000, 2, 30000)` | self |
| 25 | Staff | UpdateSessionItemsFrm | `actionPerformed("AddItem")` | synchronous |
| 26 | Staff | UpdateSessionItemsFrm | `setText("Bánh mì")` + `actionPerformed("SearchProduct")` | synchronous |
| 27 | UpdateSessionItemsFrm | ProductDAO | `searchByName("Bánh mì")` | synchronous |
| 28 | ProductDAO | UpdateSessionItemsFrm | `return ArrayList<Product>` (P05: Bánh mì) | return |
| 29 | Staff | UpdateSessionItemsFrm | `clickRow("Bánh mì")` + `setText(unitPrice=20000)` + `setText(quantity=1)` + `actionPerformed("OK")` | synchronous |
| 30 | UpdateSessionItemsFrm | UpdateSessionItemsFrm | `addToItemList("Bánh mì", 20000, 1, 20000)` + `calculateTotal(80000)` | self |
| 31 | Staff | UpdateSessionItemsFrm | `actionPerformed("Confirm")` | synchronous |
| 32 | UpdateSessionItemsFrm | BookingSessionDAO | `updateSession(sessionId=S1, "18:00", "19:15", rentAmount)` | synchronous |
| 33 | BookingSessionDAO | UpdateSessionItemsFrm | `return true` | return |
| 34 | UpdateSessionItemsFrm | SessionProductDAO | `addSessionProduct(S1, P01, 15000, 2, 30000)` | synchronous |
| 35 | SessionProductDAO | UpdateSessionItemsFrm | `return true` | return |
| 36 | UpdateSessionItemsFrm | SessionProductDAO | `addSessionProduct(S1, P05, 20000, 1, 20000)` | synchronous |
| 37 | SessionProductDAO | UpdateSessionItemsFrm | `return true` | return |
| 38 | UpdateSessionItemsFrm | Staff | `showMessage("Cap nhat thanh cong")` | return |

### ASCII Sequence Diagram

```
Staff      LoginFrm    UserDAO    HomeFrm    UpdateSessionItemsFrm    CustomerDAO    BookingDAO    BookingSessionDAO    ProductDAO    SessionProductDAO
  |            |           |          |               |                    |              |                |                |                |
  |--login---->|           |          |               |                    |              |                |                |                |
  |            |--checkLogin()------>|               |                    |              |                |                |                |
  |            |<--true----|          |               |                    |              |                |                |                |
  |            |--open HomeFrm------>|               |                    |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--select "Update items"---------->|               |                    |              |                |                |                |
  |            |           |          |--open Frm---->|                    |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--enter "Nguyen Van A", Search--->|               |                    |              |                |                |                |
  |            |           |          |               |--searchByName()-->|              |                |                |                |
  |            |           |          |               |<--List<Customer>--|              |                |                |                |
  |            |           |          |               |--display list     |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--click customer------->|          |               |                    |              |                |                |                |
  |            |           |          |               |--getActiveBookings()------------>|                |                |                |
  |            |           |          |               |<--List<Booking>---|              |                |                |                |
  |            |           |          |               |--display bookings |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--click Checkout B001-->|          |               |                    |              |                |                |                |
  |            |           |          |               |--display session form            |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--enter 18:00, 19:15--->|          |               |                    |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--AddItem, "Coca Cola", Search--->|               |                    |              |                |                |                |
  |            |           |          |               |--searchByName()---------------------------->|                |                |
  |            |           |          |               |<--List<Product>---|              |                |                |                |
  |--select, price 15000, qty 2, OK->|               |                    |              |                |                |                |
  |            |           |          |               |--add to list      |              |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--AddItem, "Bánh mì", Search----->|               |                    |              |                |                |                |
  |            |           |          |               |--searchByName()---------------------------->|                |                |
  |            |           |          |               |<--List<Product>---|              |                |                |                |
  |--select, price 20000, qty 1, OK->|               |                    |              |                |                |                |
  |            |           |          |               |--calculateTotal(80000)           |                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |--click Confirm-------->|          |               |                    |              |                |                |                |
  |            |           |          |               |--updateSession()-------------------------------->|                |                |
  |            |           |          |               |<--true-------------|--------------|                |                |                |
  |            |           |          |               |--addSessionProduct(S1,P01,15000,2)--------------|                |                |
  |            |           |          |               |<--true-------------|--------------|                |                |                |
  |            |           |          |               |--addSessionProduct(S1,P05,20000,1)--------------|                |                |
  |            |           |          |               |<--true-------------|--------------|                |                |                |
  |            |           |          |               |                    |              |                |                |                |
  |<--success--|           |          |               |                    |              |                |                |                |
```

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Update used items | Cập nhật thành công phiên thuê với 2 sản phẩm đã sử dụng |
| TC02 | Update used items | Tìm khách hàng không tồn tại |
| TC03 | Update used items | Khách hàng không có booking đang hoạt động |
| TC04 | Update used items | Nhập số lượng sản phẩm <= 0 |

### TC01: Cập nhật thành công phiên thuê có sản phẩm đã sử dụng

**Purpose:** Kiểm tra quy trình cập nhật phiên thuê hoàn chỉnh: tìm khách hàng, chọn booking, nhập giờ nhận/trả, thêm sản phẩm đã sử dụng, xác nhận cập nhật.

**Database trước khi test:**

**tblUser:**
| ID | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | staff01 | 123456 | Nguyen Thi Staff | staff |
| 2 | manager01 | admin123 | Tran Van Manager | manager |

**tblCustomer:**
| ID | code | customerName | phoneNumber | address |
|----|------|-------------|-------------|---------|
| 1 | KH001 | Nguyen Van A | 0912345678 | Quan 1, TP.HCM |
| 2 | KH002 | Tran Thi B | 0923456789 | Quan 3, TP.HCM |

**tblCourt:**
| ID | code | courtName | courtType | pricePerSession | status |
|----|------|-----------|-----------|-----------------|--------|
| 1 | S1 | San 1 | San nho | 200000 | active |
| 2 | S2 | San 2 | San nho | 200000 | active |

**tblBooking:**
| ID | courtID | customerID | bookingDate | startDate | endDate | timeSlot | dayOfWeek | totalSessions | totalAmount | deposit | status |
|----|---------|------------|-------------|-----------|---------|----------|-----------|---------------|-------------|---------|--------|
| 1 | 1 | 1 | 2026-06-01 | 2026-07-01 | 2026-09-30 | 18:00-19:00 | Thu 6 | 12 | 2400000 | 500000 | active |

**tblBookingSession:**
| ID | bookingID | sessionDate | startTime | endTime | checkinTime | checkoutTime | rentAmount | status |
|----|-----------|-------------|-----------|---------|-------------|--------------|------------|--------|
| 1 | 1 | 2026-07-10 | 18:00 | 19:00 | NULL | NULL | 0 | pending |
| 2 | 1 | 2026-07-17 | 18:00 | 19:00 | NULL | NULL | 0 | pending |
| 3 | 1 | 2026-07-24 | 18:00 | 19:00 | NULL | NULL | 0 | pending |

**tblProduct:**
| ID | code | productName | unit | price | stockQuantity |
|----|------|-------------|------|-------|---------------|
| 1 | P01 | Coca Cola 330ml | Chai | 15000 | 100 |
| 2 | P02 | Coca Cola 500ml | Chai | 20000 | 80 |
| 3 | P03 | Nuoc suoi 500ml | Chai | 10000 | 150 |
| 4 | P04 | Banh mi | Cai | 20000 | 50 |

**tblSessionProduct:** (rỗng — chưa có sản phẩm nào cho session S1)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username=`staff01`, password=`123456`, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm với các menu |
| 3 | Chọn menu "Update used items" | Giao diện tìm kiếm khách hàng hiển thị: ô Customer Name, nút Search |
| 4 | Nhập "Nguyen Van A" vào ô Customer Name, nhấn Search | Bảng kết quả hiển thị 1 dòng: KH001 — Nguyen Van A — 0912345678 — Quan 1, TP.HCM |
| 5 | Click chọn dòng "Nguyen Van A" | Bảng booking đang hoạt động hiển thị: B001 — Sân S1 — T6 10/07/2026 — 18:00-19:00 — Pending |
| 6 | Click nút Checkout cho booking B001 (session S1) | Giao diện cập nhật phiên thuê hiển thị: thông tin Sân S1, T6 10/07, 18:00-19:00. Các ô nhập giờ nhận, giờ trả, tiền thuê |
| 7 | Nhập giờ nhận = "18:00", giờ trả = "19:15" | Các ô được điền. Tiền thuê được tính (có phát sinh do trả muộn) |
| 8 | Nhấn nút "Thêm sản phẩm", nhập "Coca Cola", nhấn Search | Bảng sản phẩm hiển thị: P01 — Coca Cola 330ml — 15,000đ; P02 — Coca Cola 500ml — 20,000đ |
| 9 | Chọn "Coca Cola 330ml", nhập đơn giá = 15,000, số lượng = 2, nhấn OK | Sản phẩm thêm vào danh sách: Coca Cola 330ml — 15,000đ — SL: 2 — Thành tiền: 30,000đ |
| 10 | Nhấn "Thêm sản phẩm", nhập "Banh mi", Search, chọn "Banh mi", giá 20,000, SL 1, OK | Danh sách cập nhật thêm: Banh mi — 20,000đ — SL: 1 — 20,000đ. Tổng: 50,000đ |
| 11 | Nhấn Confirm | Hệ thống hiển thị thông báo "Cap nhat thanh cong" |

**Database sau khi test:**

**tblBookingSession:** (cập nhật 1 dòng)
| ID | bookingID | sessionDate | startTime | endTime | checkinTime | checkoutTime | rentAmount | status |
|----|-----------|-------------|-----------|---------|-------------|--------------|------------|--------|
| 1 | 1 | 2026-07-10 | 18:00 | 19:00 | 18:00 | 19:15 | 225000 | completed |

**tblSessionProduct:** (thêm 2 dòng)
| ID | sessionID | productID | unitPrice | quantity | subtotal |
|----|-----------|-----------|-----------|----------|----------|
| 1 | 1 | 1 | 15000 | 2 | 30000 |
| 2 | 1 | 4 | 20000 | 1 | 20000 |

---

### TC02: Tìm khách hàng không tồn tại

**Purpose:** Kiểm tra hệ thống xử lý đúng khi tìm kiếm khách hàng không có trong database.

**Database trước khi test:**
- tblCustomer: không có khách hàng nào có tên "Vo Thi X"

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Update used items" | Giao diện tìm kiếm khách hàng hiển thị |
| 2 | Nhập "Vo Thi X" vào ô Customer Name, nhấn Search | Bảng kết quả trống, hiển thị thông báo "Khong tim thay khach hang" |

**Database sau khi test:**
- Không có thay đổi

---

### TC03: Khách hàng không có booking đang hoạt động

**Purpose:** Kiểm tra hệ thống khi khách hàng tồn tại nhưng không có booking nào đang hoạt động.

**Database trước khi test:**
- tblCustomer: "Pham Van B" (ID=3, code=KH003)
- tblBooking: không có booking nào có status="active" cho customerID=3

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Update used items" | Giao diện tìm kiếm hiển thị |
| 2 | Nhập "Pham Van B", nhấn Search | Bảng kết quả hiển thị: KH003 — Pham Van B |
| 3 | Click chọn "Pham Van B" | Bảng booking trống, thông báo "Khong co booking dang hoat dong" |

**Database sau khi test:**
- Không có thay đổi
