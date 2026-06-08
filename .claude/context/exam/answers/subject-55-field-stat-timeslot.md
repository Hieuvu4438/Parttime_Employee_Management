# Subject No. 55 — Mini Football Field — Module "Statistics of time slot"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Thống kê khung giờ

| Bước | Diễn biến |
|------|-----------|
| 1 | Nhân viên đăng nhập hệ thống |
| 2 | Nhân viên chọn chức năng "Thống kê khung giờ được thuê nhiều nhất" |
| 3 | Hệ thống hiển thị giao diện nhập thời gian: ngày bắt đầu - ngày kết thúc |
| 4 | Nhân viên nhập khoảng thời gian (ngày bắt đầu, ngày kết thúc) và nhấn nút "Thống kê" |
| 5 | Hệ thống truy vấn dữ liệu từ bảng tblBooking, tblBookingSession, tblPayment theo khoảng thời gian |
| 6 | Hệ thống tính tổng số lượt đặt và tổng doanh thu cho mỗi khung giờ |
| 7 | Hệ thống hiển thị danh sách khung giờ dưới dạng bảng, mỗi dòng gồm: khung giờ, ngày, tổng số lượt đặt, tổng doanh thu, sắp xếp giảm dần theo tổng số lượt đặt, sau đó giảm dần theo tổng doanh thu |
| 8 | Nhân viên nhấn vào một dòng trong bảng |
| 9 | Hệ thống hiển thị danh sách chi tiết đặt sân của khung giờ đó, mỗi dòng gồm: mã đặt sân, tên khách hàng, tên sân, ngày và giờ, đơn giá, thành tiền |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | CustomerID (PK), CustomerName, Phone, Email, Address |
| Booking | BookingID (PK), CustomerID (FK), CourtID (FK), BookingDate, TotalAmount, Status |
| BookingSession | SessionID (PK), BookingID (FK), SessionDate, TimeSlot, Price |
| Court | CourtID (PK), CourtName, CourtType, HourlyRate, Status |
| Payment | PaymentID (PK), BookingID (FK), PaymentDate, Amount, PaymentMethod, Status |
| User | UserID (PK), Username, Password, FullName, Role |

### Quan hệ
```
Customer 1 --- n Booking
Court 1 --- n Booking
Booking 1 --- n BookingSession
Booking 1 --- n Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatisticTimeSlotFrm:**
- `dtpStartDate`: DateTimePicker — nhập ngày bắt đầu thống kê
- `dtpEndDate`: DateTimePicker — nhập ngày kết thúc thống kê
- `btnStatistic`: Button — thực hiện thống kê
- `dgvTimeSlotList`: DataGridView — hiển thị danh sách khung giờ (khung giờ, ngày, tổng số lượt đặt, tổng doanh thu)
- `dgvBookingDetail`: DataGridView — hiển thị chi tiết đặt sân (mã đặt sân, tên khách hàng, tên sân, ngày giờ, đơn giá, thành tiền)

**HomeFrm:**
- `btnStatisticTimeSlot`: Button — mở giao diện thống kê khung giờ

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| BookingDAO | `getTimeSlotStatistic(startDate, endDate): List<Object[]>` — lấy thống kê khung giờ theo khoảng thời gian |
| BookingDAO | `getBookingDetailByTimeSlot(timeSlot, date): List<Booking>` — lấy chi tiết đặt sân theo khung giờ |
| BookingSessionDAO | `getSessionsByDateRange(startDate, endDate): List<BookingSession>` — lấy danh sách phiên đặt sân theo khoảng thời gian |
| PaymentDAO | `getPaymentsByBookingId(bookingId): List<Payment>` — lấy danh sách thanh toán theo mã đặt sân |
| CustomerDAO | `getCustomerById(customerId): Customer` — lấy thông tin khách hàng theo mã |
| CourtDAO | `getCourtById(courtId): Court` — lấy thông tin sân theo mã |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:HomeFrm` — boundary
3. `:StatisticTimeSlotFrm` — boundary
4. `:BookingDAO` — control
5. `:BookingSessionDAO` — control
6. `:PaymentDAO` — control

**Message flow:**
1. Staff → HomeFrm: `selectStatisticTimeSlot()`
2. HomeFrm → StatisticTimeSlotFrm: `show()`
3. Staff → StatisticTimeSlotFrm: `enterDateRange(startDate, endDate)`
4. Staff → StatisticTimeSlotFrm: `clickStatistic()`
5. StatisticTimeSlotFrm → BookingDAO: `getTimeSlotStatistic(startDate, endDate)`
6. BookingDAO → BookingDAO: `query and aggregate data`
7. BookingDAO → StatisticTimeSlotFrm: `return List<Object[]>`
8. StatisticTimeSlotFrm → StatisticTimeSlotFrm: `displayTimeSlotTable(data)`
9. Staff → StatisticTimeSlotFrm: `selectRow(timeSlot, date)`
10. StatisticTimeSlotFrm → BookingDAO: `getBookingDetailByTimeSlot(timeSlot, date)`
11. BookingDAO → StatisticTimeSlotFrm: `return List<Booking>`
12. StatisticTimeSlotFrm → CustomerDAO: `getCustomerById(customerId)`
13. CustomerDAO → StatisticTimeSlotFrm: `return Customer`
14. StatisticTimeSlotFrm → CourtDAO: `getCourtById(courtId)`
15. CourtDAO → StatisticTimeSlotFrm: `return Court`
16. StatisticTimeSlotFrm → StatisticTimeSlotFrm: `displayBookingDetail(data)`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê khung giờ với khoảng thời gian hợp lệ

**Database trước:**
- tblBooking: (1, 1, 1, '2024-01-10', 500000, 'Completed'), (2, 2, 2, '2024-01-11', 300000, 'Completed'), (3, 3, 1, '2024-01-12', 600000, 'Completed')
- tblBookingSession: (1, 1, '2024-01-10', '18:00-19:00', 250000), (2, 1, '2024-01-10', '19:00-20:00', 250000), (3, 2, '2024-01-11', '18:00-19:00', 300000), (4, 3, '2024-01-12', '18:00-19:00', 300000), (5, 3, '2024-01-12', '19:00-20:00', 300000)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhân viên nhập ngày bắt đầu: 2024-01-10, ngày kết thúc: 2024-01-12 |
| 2 | Nhân viên nhấn nút "Thống kê" |
| 3 | Hệ thống hiển thị bảng với 2 khung giờ: "18:00-19:00" (3 lượt đặt, 850000 doanh thu), "19:00-20:00" (2 lượt đặt, 550000 doanh thu), sắp xếp giảm dần theo tổng số lượt đặt |

**Database sau:**
- Không có thay đổi dữ liệu

### TC02: Xem chi tiết đặt sân của khung giờ

**Database trước:**
- tblBooking: (1, 1, 1, '2024-01-10', 500000, 'Completed'), (2, 2, 2, '2024-01-11', 300000, 'Completed')
- tblBookingSession: (1, 1, '2024-01-10', '18:00-19:00', 250000), (2, 1, '2024-01-10', '19:00-20:00', 250000), (3, 2, '2024-01-11', '18:00-19:00', 300000)
- tblCustomer: (1, 'Nguyễn Văn A', '0901234567'), (2, 'Trần Thị B', '0912345678')
- tblCourt: (1, 'Sân A', 'Sân 5 người', 250000), (2, 'Sân B', 'Sân 5 người', 300000)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhân viên nhập ngày bắt đầu: 2024-01-10, ngày kết thúc: 2024-01-11, nhấn "Thống kê" |
| 2 | Hệ thống hiển thị bảng thống kê khung giờ |
| 3 | Nhân viên nhấn vào dòng khung giờ "18:00-19:00" |
| 4 | Hệ thống hiển thị chi tiết gồm 2 dòng: (1, Nguyễn Văn A, Sân A, 2024-01-10 18:00, 250000, 250000) và (2, Trần Thị B, Sân B, 2024-01-11 18:00, 300000, 300000) |

**Database sau:**
- Không có thay đổi dữ liệu
