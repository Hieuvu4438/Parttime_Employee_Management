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
