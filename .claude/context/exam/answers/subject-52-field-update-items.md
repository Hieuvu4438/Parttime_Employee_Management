# Subject No. 52 — Mini Football Field — Module "Update used items of the rental session"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Cập nhật sản phẩm đã sử dụng của phiên thuê sân

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn menu "Update used items" |
| 2 | Hệ thống hiển thị giao diện: ô nhập tên khách hàng, nút Search |
| 3 | Staff nhập "Nguyen Van A" vào ô Customer Name, nhấn nút Search. Hệ thống hiển thị danh sách khách hàng có tên "Nguyen Van A" |
| 4 | Staff click chọn "Nguyen Van A". Hệ thống hiển thị danh sách booking đang hoạt động của khách hàng này |
| 5 | Staff click nút Checkout cho booking ticket B001 (Session S1, Thứ 6 18:00-19:00) |
| 6 | Hệ thống hiển thị giao diện nhập giờ nhận sân, giờ trả sân, và danh sách sản phẩm đã sử dụng |
| 7 | Staff nhập giờ nhận: 18:00, giờ trả: 19:00 |
| 8 | Staff click "Thêm sản phẩm" → nhập "Coca Cola" → nhấn Search → chọn Coca Cola 330ml, nhập giá 15,000đ, số lượng 2 |
| 9 | Staff click "Thêm sản phẩm" → nhập "Bánh mì" → nhấn Search → chọn Bánh mì, nhập giá 20,000đ, số lượng 1 |
| 10 | Hệ thống hiển thị danh sách: Coca Cola 330ml × 2 = 30,000đ, Bánh mì × 1 = 20,000đ. Dòng cuối là Tổng tiền: 50,000đ |
| 11 | Staff nhấn nút Confirm. Hệ thống cập nhật thông tin giờ nhận/trả sân và các sản phẩm đã sử dụng vào database |

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
| User | userId, username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Booking
Booking 1 --- n BookingSession
Court 1 --- n BookingSession
BookingSession 1 --- n SessionProduct
Product 1 --- n SessionProduct
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**UpdateSessionItemsFrm:**
- `inCustomerName`: ô nhập tên khách hàng (JTextField)
- `subSearchCustomer`: nút tìm kiếm khách hàng (JButton)
- `outCustomerList`: bảng danh sách khách hàng tìm được (JTable)
- `outBookingList`: bảng danh sách booking đang hoạt động (JTable)
- `btnCheckout`: nút Checkout cho từng booking (JButton, trong bảng booking)
- `inCheckinTime`: ô nhập giờ nhận sân (JTextField)
- `inCheckoutTime`: ô nhập giờ trả sân (JTextField)
- `inProductName`: ô nhập tên sản phẩm cần tìm (JTextField)
- `subSearchProduct`: nút tìm kiếm sản phẩm (JButton)
- `outProductList`: bảng danh sách sản phẩm tìm được (JTable)
- `inUnitPrice`: ô nhập đơn giá sản phẩm (JTextField)
- `inQuantity`: ô nhập số lượng sản phẩm (JTextField)
- `subAddItem`: nút thêm sản phẩm vào danh sách (JButton)
- `outItemList`: bảng danh sách sản phẩm đã sử dụng trong phiên (JTable)
- `outTotal`: ô hiển thị tổng tiền sản phẩm đã sử dụng (JLabel)
- `subConfirm`: nút xác nhận cập nhật (JButton)

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchByName(String name)`: tìm kiếm khách hàng theo tên, trả về `ArrayList<Customer>` |
| BookingDAO | `getBookingsByCustomer(int customerId)`: lấy danh sách booking đang hoạt động của khách hàng, trả về `ArrayList<Booking>` |
| BookingSessionDAO | `updateSession(int sessionId, String checkinTime, String checkoutTime)`: cập nhật giờ nhận/trả sân của phiên thuê |
| ProductDAO | `searchByName(String name)`: tìm kiếm sản phẩm theo tên, trả về `ArrayList<Product>` |
| SessionProductDAO | `addSessionProduct(int sessionId, int productId, double unitPrice, int quantity)`: thêm sản phẩm đã sử dụng vào phiên thuê |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:UpdateSessionItemsFrm` — boundary
3. `:CustomerDAO` — control
4. `:BookingDAO` — control
5. `:BookingSessionDAO` — control
6. `:ProductDAO` — control
7. `:SessionProductDAO` — control

**Message flow:**
1. Staff → UpdateSessionItemsFrm: `nhậpTênKH("Nguyen Van A")`
2. Staff → UpdateSessionItemsFrm: `nhấnNutSearchCustomer()`
3. UpdateSessionItemsFrm → CustomerDAO: `searchByName("Nguyen Van A")`
4. CustomerDAO --> UpdateSessionItemsFrm: `ArrayList<Customer>`
5. UpdateSessionItemsFrm --> Staff: `hiểnThịDanhSáchKH(danhSachKH)`
6. Staff → UpdateSessionItemsFrm: `chọnKH("Nguyen Van A")`
7. UpdateSessionItemsFrm → BookingDAO: `getBookingsByCustomer(customerId)`
8. BookingDAO --> UpdateSessionItemsFrm: `ArrayList<Booking>`
9. UpdateSessionItemsFrm --> Staff: `hiểnThịDanhSáchBooking(danhSachBooking)`
10. Staff → UpdateSessionItemsFrm: `nhấnNutCheckout(booking B001, session S1)`
11. UpdateSessionItemsFrm --> Staff: `hiểnThịGiaoDiệnCậpNhậtPhiênThuê()`
12. Staff → UpdateSessionItemsFrm: `nhậpGiờNhận("18:00"), nhậpGiờTrả("19:00")`
13. Staff → UpdateSessionItemsFrm: `nhấnNutThêmSảnPhẩm()`
14. Staff → UpdateSessionItemsFrm: `nhậpTênSP("Coca Cola"), nhấnSearch()`
15. UpdateSessionItemsFrm → ProductDAO: `searchByName("Coca Cola")`
16. ProductDAO --> UpdateSessionItemsFrm: `ArrayList<Product>`
17. UpdateSessionItemsFrm --> Staff: `hiểnThịDanhSáchSP(danhSachSP)`
18. Staff → UpdateSessionItemsFrm: `chọnSP("Coca Cola 330ml"), nhậpGiá(15000), nhậpSL(2)`
19. Staff → UpdateSessionItemsFrm: `nhấnNutThêmSảnPhẩm()`
20. Staff → UpdateSessionItemsFrm: `nhậpTênSP("Bánh mì"), nhấnSearch()`
21. UpdateSessionItemsFrm → ProductDAO: `searchByName("Bánh mì")`
22. ProductDAO --> UpdateSessionItemsFrm: `ArrayList<Product>`
23. UpdateSessionItemsFrm --> Staff: `hiểnThịDanhSáchSP(danhSachSP)`
24. Staff → UpdateSessionItemsFrm: `chọnSP("Bánh mì"), nhậpGiá(20000), nhậpSL(1)`
25. UpdateSessionItemsFrm --> Staff: `hiểnThịTổngTiền(50000)`
26. Staff → UpdateSessionItemsFrm: `nhấnNutConfirm()`
27. UpdateSessionItemsFrm → BookingSessionDAO: `updateSession(sessionId, "18:00", "19:00")`
28. BookingSessionDAO --> UpdateSessionItemsFrm: `true`
29. UpdateSessionItemsFrm → SessionProductDAO: `addSessionProduct(sessionId, productId, 15000, 2)`
30. SessionProductDAO --> UpdateSessionItemsFrm: `true`
31. UpdateSessionItemsFrm → SessionProductDAO: `addSessionProduct(sessionId, productId, 20000, 1)`
32. SessionProductDAO --> UpdateSessionItemsFrm: `true`
33. UpdateSessionItemsFrm --> Staff: `thôngBáoCậpNhậtThànhCông()`

---

## Câu 5: Blackbox Testcase

### TC01: Cập nhật thành công phiên thuê có sản phẩm đã sử dụng

**Database trước:**
- tblBookingSession: session S1 có checkinTime = NULL, checkoutTime = NULL
- tblSessionProduct: không có sản phẩm nào cho session S1

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Update used items" |
| 2 | Giao diện hiển thị ô nhập Customer Name và nút Search |
| 3 | Staff nhập "Nguyen Van A", nhấn Search. Hiển thị danh sách chứa "Nguyen Van A" |
| 4 | Staff click "Nguyen Van A". Hiển thị danh sách booking đang hoạt động của khách hàng, bao gồm B001 |
| 5 | Staff click nút Checkout cho B001 (S1, T6 18:00-19:00). Hiển thị giao diện nhập giờ nhận/trả và danh sách sản phẩm |
| 6 | Staff nhập giờ nhận: 18:00, giờ trả: 19:00 |
| 7 | Staff tìm "Coca Cola", chọn Coca Cola 330ml, nhập giá 15,000đ, số lượng 2. Sản phẩm xuất hiện trong danh sách |
| 8 | Staff tìm "Bánh mì", chọn Bánh mì, nhập giá 20,000đ, số lượng 1. Sản phẩm xuất hiện trong danh sách |
| 9 | Hiển thị: Coca Cola 330ml × 2 = 30,000đ, Bánh mì × 1 = 20,000đ. Tổng: 50,000đ |
| 10 | Staff nhấn Confirm. Hiển thị thông báo cập nhật thành công |

**Database sau:**
- tblBookingSession: session S1 có checkinTime = "18:00", checkoutTime = "19:00"
- tblSessionProduct: thêm mới 2 dòng — (sessionId=S1, productId=CocaCola330ml, unitPrice=15000, quantity=2) và (sessionId=S1, productId=BanhMi, unitPrice=20000, quantity=1)

---

### TC02: Tìm khách hàng không tồn tại

**Database trước:**
- tblCustomer: không có khách hàng nào có tên "Le Thi X"

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Update used items" |
| 2 | Giao diện hiển thị ô nhập Customer Name và nút Search |
| 3 | Staff nhập "Le Thi X", nhấn Search. Hiển thị danh sách trống (không có kết quả) |

**Database sau:**
- Không có thay đổi

---

### TC03: Khách hàng không có booking đang hoạt động

**Database trước:**
- tblCustomer: có khách hàng "Pham Van B" với customerId = 5
- tblBooking: không có booking nào có status = "active" cho customerId = 5

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Update used items" |
| 2 | Staff nhập "Pham Van B", nhấn Search. Hiển thị danh sách chứa "Pham Van B" |
| 3 | Staff click "Pham Van B". Hiển thị thông báo "Không có booking đang hoạt động" và danh sách booking trống |

**Database sau:**
- Không có thay đổi
