# Subject No. 45 — Cinema Chain — Module "Selling movie tickets"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Bán vé phim

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Selling movie tickets**. |
| 2 | Giao diện: combobox chọn phòng chiếu hoặc tên phim + combobox chọn khung giờ. |
| 3 | Staff chọn phim "Avengers" + suất chiếu "19:00 15/07/2026". |
| 4 | Hệ thống hiển thị sơ đồ ghế: ghế trống (xanh), ghế đã bán (đỏ). |
| 5 | Staff hỏi khách chọn ghế. Khách chọn ghế A1, A2, A3. |
| 6 | Staff tick chọn ghế A1, A2, A3. |
| 7 | Hệ thống hiển thị hóa đơn: tên phim, phòng, suất chiếu, ghế, giá vé. Tổng tiền. |
| 8 | Staff nhấn **Pay**. Hệ thống in vé và hóa đơn. |

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
| FoodItem | id, code, name, price, size |
| FoodInvoice | id, ticketId, foodItemId, quantity, amount |
| User | id, username, password, role |

### Quan hệ

```
Cinema 1 --- n ScreenRoom
Movie 1 --- n Showtime n --- 1 ScreenRoom
Showtime 1 --- n Ticket n --- 1 Seat
Ticket 1 --- n FoodInvoice n --- 1 FoodItem
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**SellTicketFrm:**
- `inMovie`: combobox chọn phim
- `inShowtime`: combobox chọn suất
- `outSeatMap`: sơ đồ ghế
- `outInvoice`: hóa đơn
- `subPay`: nút Pay

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| MovieDAO | `getAllMovies(): List<Movie>` |
| ShowtimeDAO | `getShowtimes(movieId): List<Showtime>` |
| SeatDAO | `getAvailableSeats(showtimeId): List<Seat>` |
| TicketDAO | `addTicket(ticket): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → SellTicketFrm: select movie + showtime
2. SellTicketFrm → SeatDAO: getAvailableSeats(showtimeId)
3. Staff → SellTicketFrm: select seats A1, A2, A3
4. SellTicketFrm: display invoice
5. Staff → SellTicketFrm: click Pay
6. SellTicketFrm → TicketDAO: addTicket(tickets)

---

## Câu 5: Blackbox Testcase

### TC01: Bán vé thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Avengers, 19:00 15/07 | Sơ đồ ghế |
| 2 | Chọn A1, A2, A3 | Hóa đơn: 3 vé × 80,000đ = 240,000đ |
| 3 | Pay | In vé, "Ban ve thanh cong" |

**Database sau:** Thêm 3 dòng tblTicket.
