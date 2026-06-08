# Subject No. 53 — Mini Football Field — Module "Customer paying"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thanh toán cho khách hàng

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn menu "Customer paying" |
| 2 | Hệ thống hiển thị giao diện: ô nhập tên khách hàng, nút Search |
| 3 | Staff nhập "Nguyen Van A" vào ô Customer Name, nhấn nút Search. Hệ thống hiển thị danh sách khách hàng có tên "Nguyen Van A" |
| 4 | Staff click chọn "Nguyen Van A". Hệ thống hiển thị danh sách booking đang hoạt động của khách hàng này |
| 5 | Staff click nút Payment cho booking B001 |
| 6 | Hệ thống hiển thị hóa đơn với đầy đủ thông tin khách hàng + danh sách sản phẩm food & beverage đã sử dụng trong các phiên thuê + dòng cuối là tổng tiền thanh toán (nếu khách hàng phản ánh thay đổi, staff phải cập nhật) |
| 7 | Staff nhấn nút Confirm. Hệ thống cập nhật Payment vào database |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | customerId, customerName, phoneNumber, address |
| Booking | bookingId, bookingDate, totalAmount, status, customerId (FK) |
| BookingSession | sessionId, bookingId (FK), courtId (FK), sessionDate, startTime, endTime, checkinTime, checkoutTime, rentAmount, status |
| Product | productId, productName, unit, price, stockQuantity |
| SessionProduct | sessionProductId, sessionId (FK), productId (FK), unitPrice, quantity, subtotal |
| Court | courtId, courtName, courtType, pricePerHour, status |
| Payment | paymentId, bookingId (FK), paymentDate, totalAmount, method, status |
| User | userId, username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Booking
Booking 1 --- n BookingSession
Court 1 --- n BookingSession
BookingSession 1 --- n SessionProduct
Product 1 --- n SessionProduct
Booking 1 --- 1 Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**CustomerPayFrm:**
- `inCustomerName`: ô nhập tên khách hàng (JTextField)
- `subSearchCustomer`: nút tìm kiếm khách hàng (JButton)
- `outCustomerList`: bảng danh sách khách hàng tìm được (JTable)
- `outBookingList`: bảng danh sách booking đang hoạt động của khách hàng (JTable)
- `btnPayment`: nút Payment cho từng booking (JButton, trong bảng booking)
- `outInvoiceDetails`: bảng chi tiết hóa đơn hiển thị các session và sản phẩm đã sử dụng (JTable)
- `outTotalAmount`: ô hiển thị tổng tiền thanh toán (JLabel)
- `subConfirm`: nút xác nhận thanh toán (JButton)

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchByName(String name)`: tìm kiếm khách hàng theo tên, trả về `ArrayList<Customer>` |
| BookingDAO | `getBookingsByCustomer(int customerId)`: lấy danh sách booking đang hoạt động của khách hàng, trả về `ArrayList<Booking>` |
| BookingSessionDAO | `getSessionsWithProducts(int bookingId)`: lấy tất cả phiên thuê và sản phẩm đã sử dụng của một booking, trả về `ArrayList<BookingSession>` mỗi phiên bao gồm `ArrayList<SessionProduct>` |
| PaymentDAO | `createPayment(int bookingId, double totalAmount, String method)`: tạo mới bản ghi Payment, cập nhật trạng thái booking thành "Paid", trả về `Payment` |

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
1. Staff → CustomerPayFrm: `nhậpTênKH("Nguyen Van A")`
2. Staff → CustomerPayFrm: `nhấnNutSearchCustomer()`
3. CustomerPayFrm → CustomerDAO: `searchByName("Nguyen Van A")`
4. CustomerDAO --> CustomerPayFrm: `ArrayList<Customer>`
5. CustomerPayFrm --> Staff: `hiểnThịDanhSáchKH(danhSachKH)`
6. Staff → CustomerPayFrm: `chọnKH("Nguyen Van A")`
7. CustomerPayFrm → BookingDAO: `getBookingsByCustomer(customerId)`
8. BookingDAO --> CustomerPayFrm: `ArrayList<Booking>`
9. CustomerPayFrm --> Staff: `hiểnThịDanhSáchBooking(danhSachBooking)`
10. Staff → CustomerPayFrm: `nhấnNutPayment(booking B001)`
11. CustomerPayFrm → BookingSessionDAO: `getSessionsWithProducts(bookingId)`
12. BookingSessionDAO --> CustomerPayFrm: `ArrayList<BookingSession>` với các `SessionProduct`
13. CustomerPayFrm --> Staff: `hiểnThịHóaĐơn(invoiceDetails, tổngTiền 60000)`
14. Staff → CustomerPayFrm: `nhấnNutConfirm()`
15. CustomerPayFrm → PaymentDAO: `createPayment(bookingId, 60000, "cash")`
16. PaymentDAO --> CustomerPayFrm: `Payment`
17. CustomerPayFrm --> Staff: `thôngBáoThanhToánThànhCông()`

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán thành công booking với nhiều session và sản phẩm

**Database trước:**
- tblBooking: B001 có status = "active", customerId = 1
- tblBookingSession: S1 (01/07, Sân S1, 18:00-19:00), S2 (08/07, Sân S1, 18:00-19:00)
- tblSessionProduct: S1 có Coca Cola × 2 = 30,000đ và Bánh mì × 1 = 20,000đ; S2 có Nước suối × 1 = 10,000đ
- tblPayment: không có bản ghi nào cho B001

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Customer paying" |
| 2 | Giao diện hiển thị ô nhập Customer Name và nút Search |
| 3 | Staff nhập "Nguyen Van A", nhấn Search. Hiển thị danh sách chứa "Nguyen Van A" |
| 4 | Staff click "Nguyen Van A". Hiển thị danh sách booking đang hoạt động, bao gồm B001 |
| 5 | Staff click nút Payment cho B001. Hiển thị hóa đơn chi tiết |
| 6 | Hóa đơn hiển thị: Session 1 (01/07): Sân S1, 18:00-19:00, Coca Cola × 2 = 30,000đ, Bánh mì × 1 = 20,000đ; Session 2 (08/07): Sân S1, 18:00-19:00, Nước suối × 1 = 10,000đ. Tổng thanh toán: 60,000đ |
| 7 | Staff nhấn Confirm. Hiển thị thông báo thanh toán thành công |

**Database sau:**
- tblBooking: B001 có status = "Paid"
- tblPayment: thêm mới 1 dòng — (paymentId=mới, bookingId=B001, paymentDate=ngày hiện tại, totalAmount=60000, method="cash", status="completed")

---

### TC02: Khách hàng không tồn tại trong hệ thống

**Database trước:**
- tblCustomer: không có khách hàng nào có tên "Vo Thi C"

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Customer paying" |
| 2 | Giao diện hiển thị ô nhập Customer Name và nút Search |
| 3 | Staff nhập "Vo Thi C", nhấn Search. Hiển thị danh sách trống (không có kết quả) |

**Database sau:**
- Không có thay đổi

---

### TC03: Khách hàng có booking nhưng không có sản phẩm đã sử dụng

**Database trước:**
- tblCustomer: có khách hàng "Tran Van D" với customerId = 3
- tblBooking: B003 có status = "active", customerId = 3
- tblBookingSession: S5 có checkinTime và checkoutTime nhưng không có SessionProduct nào
- tblPayment: không có bản ghi nào cho B003

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Customer paying" |
| 2 | Staff nhập "Tran Van D", nhấn Search. Hiển thị danh sách chứa "Tran Van D" |
| 3 | Staff click "Tran Van D". Hiển thị danh sách booking đang hoạt động, bao gồm B003 |
| 4 | Staff click nút Payment cho B003. Hiển thị hóa đơn chi tiết |
| 5 | Hóa đơn hiển thị: Session 5 với chỉ tiền sân (rentAmount), không có sản phẩm nào. Tổng thanh toán = rentAmount |
| 6 | Staff nhấn Confirm. Hiển thị thông báo thanh toán thành công |

**Database sau:**
- tblBooking: B003 có status = "Paid"
- tblPayment: thêm mới 1 dòng — (paymentId=mới, bookingId=B003, paymentDate=ngày hiện tại, totalAmount=rentAmount, method="cash", status="completed")
