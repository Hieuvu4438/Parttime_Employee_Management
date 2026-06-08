# Subject No. 54 — Mini Football Field — Module "Revenue statistics"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thống kê doanh thu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn menu "Revenue statistics" |
| 2 | Hệ thống hiển thị hộp chọn thống kê theo tháng, quý, hoặc năm |
| 3 | Staff chọn "Tháng" |
| 4 | Hệ thống hiển thị bảng thống kê doanh thu 12 tháng gần nhất, mỗi dòng: tên tháng, tổng doanh thu, sắp xếp theo thời gian |
| 5 | Staff click vào 1 dòng (ví dụ: 07/2026) |
| 6 | Hệ thống hiển thị chi tiết hóa đơn của khách hàng trong tháng đó, mỗi hóa đơn trên 1 dòng: id, tên khách hàng, tên sân, ngày giờ, tổng thanh toán |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | customerId, customerName, phoneNumber, address |
| Booking | bookingId, bookingDate, totalAmount, status, customerId (FK) |
| BookingSession | sessionId, bookingId (FK), courtId (FK), sessionDate, startTime, endTime, checkinTime, checkoutTime, rentAmount, status |
| Court | courtId, courtName, courtType, pricePerHour, status |
| Payment | paymentId, bookingId (FK), paymentDate, totalAmount, method, status |
| User | userId, username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Booking
Booking 1 --- n BookingSession
Court 1 --- n BookingSession
Booking 1 --- 1 Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**FieldStatRevenueFrm:**
- `inStatType`: combobox chọn kiểu thống kê (JComboBox: "Tháng", "Quý", "Năm")
- `outStatTable`: bảng thống kê doanh thu tổng hợp theo tháng/quý/năm (JTable)
- `outDetailTable`: bảng chi tiết hóa đơn khi click vào 1 dòng trong bảng thống kê (JTable)

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PaymentDAO | `getRevenueByPeriod(String periodType)`: thống kê doanh thu theo loại kỳ (tháng/quý/năm) cho 12 kỳ gần nhất, trả về `ArrayList` mỗi dòng gồm periodLabel và totalRevenue, sắp xếp theo thời gian |
| PaymentDAO | `getPaymentsByMonth(int year, int month)`: lấy chi tiết tất cả hóa đơn trong 1 tháng cụ thể, trả về `ArrayList<Payment>` mỗi payment bao gồm id, customerName, courtName, sessionDate, startTime, totalAmount |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:FieldStatRevenueFrm` — boundary
3. `:PaymentDAO` — control

**Message flow:**
1. Staff → FieldStatRevenueFrm: `chọnMenu("Revenue statistics")`
2. FieldStatRevenueFrm --> Staff: `hiểnThịGiaoDiện(inStatType)`
3. Staff → FieldStatRevenueFrm: `chọnLoạiThốngKê("Tháng")`
4. FieldStatRevenueFrm → PaymentDAO: `getRevenueByPeriod("month")`
5. PaymentDAO --> FieldStatRevenueFrm: `ArrayList<{periodLabel, totalRevenue}>`
6. FieldStatRevenueFrm --> Staff: `hiểnThịBảngThốngKê(bảngThốngKê 12 tháng)`
7. Staff → FieldStatRevenueFrm: `clickDòng("07/2026")`
8. FieldStatRevenueFrm → PaymentDAO: `getPaymentsByMonth(2026, 7)`
9. PaymentDAO --> FieldStatRevenueFrm: `ArrayList<Payment>`
10. FieldStatRevenueFrm --> Staff: `hiểnThịChiTiếtHóaĐơn(chiTietHóaĐơn)`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê doanh thu theo tháng và xem chi tiết thành công

**Database trước:**
- tblPayment: có các bản ghi — (INV001, bookingId=B001, paymentDate=2026-07-01, totalAmount=500000), (INV002, bookingId=B002, paymentDate=2026-07-05, totalAmount=400000), (INV003, bookingId=B003, paymentDate=2026-08-10, totalAmount=450000)
- tblBookingSession: S1 (Sân S1, 01/07 18:00-19:00, bookingId=B001), S3 (Sân S3, 05/07 19:00-20:00, bookingId=B002), S7 (Sân S2, 10/08 18:00-19:00, bookingId=B003)
- tblCustomer: Nguyen Van A (id=1), Tran Van B (id=2), Le Van C (id=3)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Revenue statistics" |
| 2 | Giao diện hiển thị combobox chọn kiểu thống kê |
| 3 | Staff chọn "Tháng" từ combobox |
| 4 | Hiển thị bảng thống kê 12 tháng gần nhất, có dòng: 07/2026: 900,000đ, 08/2026: 450,000đ, các tháng không có doanh thu hiển thị 0đ |
| 5 | Staff click vào dòng 07/2026 |
| 6 | Hiển thị bảng chi tiết: INV001, Nguyen Van A, Sân S1, 01/07 18:00, 500,000đ; INV002, Tran Van B, Sân S3, 05/07 19:00, 400,000đ |

**Database sau:**
- Không có thay đổi (chức năng chỉ đọc dữ liệu)

---

### TC02: Thống kê theo quý

**Database trước:**
- tblPayment: có các bản ghi — (INV001, 2026-07-01, 500000), (INV002, 2026-07-05, 400000), (INV003, 2026-08-10, 450000), (INV004, 2026-09-15, 300000)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Revenue statistics" |
| 2 | Staff chọn "Quý" từ combobox |
| 3 | Hiển thị bảng thống kê các quý gần nhất, có dòng: Quý 3/2026: 1,650,000đ (tổng 3 tháng 07+08+09) |
| 4 | Staff click vào Quý 3/2026. Hiển thị chi tiết hóa đơn trong Quý 3/2026 |

**Database sau:**
- Không có thay đổi (chức năng chỉ đọc dữ liệu)

---

### TC03: Tháng không có doanh thu

**Database trước:**
- tblPayment: có bản ghi (INV001, 2026-07-01, 500000). Không có payment nào trong tháng 06/2026

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, chọn menu "Revenue statistics" |
| 2 | Staff chọn "Tháng" từ combobox |
| 3 | Hiển thị bảng thống kê: 06/2026: 0đ, 07/2026: 500,000đ |
| 4 | Staff click vào dòng 06/2026. Hiển thị bảng chi tiết trống (không có hóa đơn nào) |

**Database sau:**
- Không có thay đổi (chức năng chỉ đọc dữ liệu)
