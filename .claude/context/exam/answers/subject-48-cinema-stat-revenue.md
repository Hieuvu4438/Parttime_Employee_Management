# Subject No. 48 — Cinema Chain — Module "Revenue Statistics"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Thống kê doanh thu theo phim

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Revenue by movie**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách phim: mã, tên phim, tổng vé bán, tổng doanh thu. Sắp xếp: tổng doanh thu giảm dần. |
| 5 | Staff nhấn vào phim "Avengers". |
| 6 | Hệ thống hiển thị chi tiết từng suất chiếu: giờ chiếu, số vé bán, tổng thu nhập. Sắp xếp: suất chiếu cũ → mới. |
| 7 | Staff nhấn vào suất "19:00 15/07/2026". |
| 8 | Hệ thống hiển thị danh sách hóa đơn: mã HĐ, tên KH, tổng vé, tổng tiền. Sắp xếp: thời gian thanh toán. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Cinema | id, code, name, address, intro |
| ScreenRoom | id, cinemaId, code, numSeats, characteristics |
| Movie | id, code, title, type, year, description |
| Showtime | id, movieId, screenRoomId, time, date, ticketPrice |
| Seat | id, screenRoomId, seatNumber |
| Ticket | id, showtimeId, seatId, customerId, price |
| Invoice | id, invoiceDate, customerId, totalAmount |
| InvoiceDetail | id, invoiceId, ticketId, amount |
| User | id, username, password, role |

### Quan hệ

```
Cinema 1 --- n ScreenRoom
Movie 1 --- n Showtime n --- 1 ScreenRoom
Showtime 1 --- n Ticket n --- 1 Seat
Invoice 1 --- n InvoiceDetail n --- 1 Ticket
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**RevenueStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outMovieTable`: bảng phim (click được)
- `outShowtimeTable`: bảng suất chiếu (click được)
- `outInvoiceTable`: bảng hóa đơn

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| MovieDAO | `getMovieRevenue(startDate, endDate): List<MovieRevenue>` |
| ShowtimeDAO | `getShowtimeRevenue(movieId, startDate, endDate): List<ShowtimeRevenue>` |
| InvoiceDAO | `getInvoicesByShowtime(showtimeId): List<Invoice>` |

**MovieRevenue:** movie, totalTickets, totalRevenue

**ShowtimeRevenue:** showtime, totalTickets, totalIncome

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:RevenueStatFrm` — boundary
3. `:MovieDAO` — control
4. `:ShowtimeDAO` — control
5. `:InvoiceDAO` — control

**Message flow:**

1. Staff → RevenueStatFrm: `enterDates(01/01/2026, 31/12/2026)` + `clickView()`
2. RevenueStatFrm → MovieDAO: `getMovieRevenue(dates)`
3. MovieDAO → RevenueStatFrm: return `List<MovieRevenue>`
4. Staff → RevenueStatFrm: `clickMovie(Avengers)`
5. RevenueStatFrm → ShowtimeDAO: `getShowtimeRevenue(movieId, dates)`
6. ShowtimeDAO → RevenueStatFrm: return `List<ShowtimeRevenue>`
7. Staff → RevenueStatFrm: `clickShowtime(19:00 15/07)`
8. RevenueStatFrm → InvoiceDAO: `getInvoicesByShowtime(showtimeId)`
9. InvoiceDAO → RevenueStatFrm: return `List<Invoice>`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê doanh thu có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: Avengers (500 vé, 40,000,000đ), Spider-Man (300 vé, 24,000,000đ) |
| 2 | Nhấn Avengers | Chi tiết: 19:00 15/07 (200 vé, 16,000,000đ), 21:00 15/07 (300 vé, 24,000,000đ) |
| 3 | Nhấn 19:00 15/07 | Hóa đơn: HD001 (Nguyen Van A, 3 vé, 240,000đ), HD002 (Tran B, 2 vé, 160,000đ) |

**Database sau:** Không thay đổi (chỉ đọc).
