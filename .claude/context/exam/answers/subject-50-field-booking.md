# Subject No. 50 — Mini Football Field — Module "Booking"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Đặt sân

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Booking**. |
| 2 | Giao diện: ô nhập khung giờ, combobox chọn loại sân, nút Search. |
| 3 | Staff nhập "18:00-19:00", chọn "Sân nhỏ", nhấn Search. |
| 4 | Hệ thống hiển thị danh sách sân trống: S1, S3, S5. |
| 5 | Staff click vào sân S1. Giao diện tìm khách hàng. |
| 6 | Staff nhập "Nguyen Van A", Search. Hệ thống hiển thị danh sách KH. |
| 7 | Staff click đúng KH "Nguyen Van A". |
| 8 | Giao diện nhập thời gian đặt: ngày bắt đầu 01/07/2026, ngày kết thúc 30/09/2026 (quý). |
| 9 | Staff nhấn Confirm. Hệ thống hiển thị phiếu đặt sân: KH, sân S1, T6 18:00-19:00, 12 tuần, giá 200,000đ/tuần, tổng 2,400,000đ, cọc 500,000đ. |
| 10 | Staff nhấn Confirm. Hệ thống in phiếu đặt, lưu database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Court | id, code, name, type, pricePerSession |
| Customer | id, code, name, phone, address |
| Booking | id, courtId, customerId, startDate, endDate, timeSlot, dayOfWeek, totalSessions, totalAmount, deposit |
| BookingSession | id, bookingId, sessionDate, status |
| Supplier | id, code, name, address, email, phone, description |
| Product | id, code, name, price |
| ImportInvoice | id, supplierId, importDate, totalAmount |
| ImportInvoiceDetail | id, importInvoiceId, productId, quantity, unitPrice, amount |
| SessionProduct | id, bookingSessionId, productId, quantity, amount |
| Payment | id, bookingId, paymentDate, totalAmount |
| User | id, username, password, role |

### Quan hệ

```
Court 1 --- n Booking n --- 1 Customer
Booking 1 --- n BookingSession
BookingSession 1 --- n SessionProduct n --- 1 Product
Supplier 1 --- n ImportInvoice
ImportInvoice 1 --- n ImportInvoiceDetail n --- 1 Product
Booking 1 --- n Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**BookingFrm:**
- `inTimeSlot`: ô nhập khung giờ
- `inCourtType`: combobox loại sân
- `subSearch`: nút Search
- `outCourtList`: danh sách sân trống (click được)
- `inCustomerName`: ô nhập tên KH
- `subSearchCustomer`: nút Search KH
- `outCustomerList`: danh sách KH (click được)
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `outBookingSlip`: phiếu đặt sân
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CourtDAO | `getAvailableCourts(timeSlot, courtType): List<Court>` |
| CustomerDAO | `searchCustomerByName(keyword): List<Customer>` |
| BookingDAO | `createBooking(booking): boolean` |
| BookingSessionDAO | `addBookingSessions(sessions): boolean` |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:BookingFrm` — boundary
3. `:CourtDAO` — control
4. `:CustomerDAO` — control
5. `:BookingDAO` — control

**Message flow:**

1. Staff → BookingFrm: `enterTimeSlot("18:00-19:00")` + `selectCourtType("Sân nhỏ")` + `clickSearch()`
2. BookingFrm → CourtDAO: `getAvailableCourts(timeSlot, courtType)`
3. CourtDAO → BookingFrm: return `List<Court>`
4. Staff → BookingFrm: `clickCourt(S1)`
5. Staff → BookingFrm: `enterCustomerName("Nguyen Van A")` + `clickSearchCustomer()`
6. BookingFrm → CustomerDAO: `searchCustomerByName("Nguyen Van A")`
7. CustomerDAO → BookingFrm: return `List<Customer>`
8. Staff → BookingFrm: `clickCustomer(Nguyen Van A)`
9. Staff → BookingFrm: `enterDates(01/07/2026, 30/09/2026)` + `clickConfirm()`
10. BookingFrm: display booking slip (S1, 12 tuần, 2,400,000đ, cọc 500,000đ)
11. Staff → BookingFrm: `clickConfirm()`
12. BookingFrm → BookingDAO: `createBooking(booking)`
13. BookingDAO: INSERT INTO tblBooking

---

## Câu 5: Blackbox Testcase

### TC01: Đặt sân thành công

**Database trước:**
- tblBooking: 0 dòng
- tblBookingSession: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập 18:00-19:00, Sân nhỏ, Search | Danh sách: S1, S3, S5 |
| 2 | Click S1, nhập "Nguyen Van A" → Search, click KH | Hiển thị form nhập ngày |
| 3 | Nhập 01/07 - 30/09, Confirm | Phiếu đặt: S1, 12 tuần, 2,400,000đ, cọc 500,000đ |
| 4 | Confirm | "Dat san thanh cong", in phiếu |

**Database sau:**
- tblBooking: thêm 1 dòng (courtId=S1, customerId=A, startDate=01/07, endDate=30/09, totalAmount=2400000, deposit=500000)
- tblBookingSession: thêm 12 dòng (mỗi tuần 1 session)
