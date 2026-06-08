# Subject No. 16 — Restaurant Order — Module "Book a table"

> **Domain:** Restaurant Order Management
> **Entity classes:** Table, Customer, Dish, Combo, Order, Invoice, User

---

## Câu 1: Scenario — Đặt bàn

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Book a table**. |
| 2 | Giao diện tìm bàn trống: ô nhập ngày, giờ, số khách, nút Search. |
| 3 | Staff nhập 15/07/2026, 18:00, 4 khách, nhấn Search. |
| 4 | Hệ thống hiển thị danh sách bàn trống: mã bàn, tên bàn, số khách tối đa, mô tả. |
| 5 | Staff chọn bàn "B03". |
| 6 | Giao diện nhập thông tin KH: ô nhập tên, nút Search. |
| 7 | Staff nhập "Nguyen Van A", nhấn Search. |
| 8 | Hệ thống hiển thị danh sách KH trùng tên. Staff chọn đúng KH (hoặc thêm mới). |
| 9 | Hệ thống hiển thị: thông tin bàn + thông tin KH + ngày giờ đặt. |
| 10 | Staff xác nhận với khách, nhấn **Confirm**. |
| 11 | Hệ thống lưu đặt bàn vào database. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**Booking:**
- `id: int`
- `tableId: int` (FK)
- `customerId: int` (FK)
- `bookingDate: Date`
- `bookingTime: String`
- `numGuests: int`
- `userId: int` (FK)

Quan hệ:
```
Table 1 --- n Booking
Customer 1 --- n Booking
User 1 --- n Booking
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**BookTableFrm:**
- `inDate`: ô nhập ngày
- `inTime`: ô nhập giờ
- `inNumGuests`: ô nhập số khách
- `subSearch`: nút Search bàn
- `outsubListTable`: bảng bàn trống
- `inCustomerName`: ô nhập tên KH
- `subSearchCustomer`: nút Search KH
- `outsubListCustomer`: bảng KH
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| TableDAO | `getAvailableTables(date, time, numGuests): List<Table>` |
| CustomerDAO | `searchCustomer(name): List<Customer>` |
| BookingDAO | `addBooking(booking): boolean` |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `BookTableFrm`, `TableDAO`, `CustomerDAO`, `BookingDAO`.
2. Message flow:
   - Staff → BookTableFrm: enter date, time, numGuests, click Search
   - BookTableFrm → TableDAO: getAvailableTables(date, time, numGuests)
   - Staff → BookTableFrm: select table "B03"
   - Staff → BookTableFrm: enter "Nguyen Van A", click Search
   - BookTableFrm → CustomerDAO: searchCustomer("Nguyen Van A")
   - Staff → BookTableFrm: select customer
   - Staff → BookTableFrm: click Confirm
   - BookTableFrm → BookingDAO: addBooking(booking)

---

## Câu 5: Blackbox Testcase

### TC01: Đặt bàn thành công

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Nhập ngày 15/07, 18:00, 4 khách, Search | Bàn trống: B01, B03, B05 |
| 2 | Chọn B03, tìm KH "A", chọn KH | Hiển thị: Bàn B03 + KH Nguyen Van A + 15/07/2026 18:00 |
| 3 | Confirm | Thông báo "Dat ban thanh cong" |

**Database sau:** Thêm 1 dòng tblBooking.
