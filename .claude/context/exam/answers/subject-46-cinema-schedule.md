# Subject No. 46 — Cinema Chain — Module "Schedule showing"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Quản lý lịch chiếu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Showtime management** → **Add showtime**. |
| 2 | Giao diện: combobox chọn phim, combobox chọn phòng chiếu, ô nhập giờ chiếu, ô nhập giá vé, nút Add. |
| 3 | Staff chọn phim "Avengers", chọn phòng "P1", nhập giờ "19:00", ngày "15/07/2026", giá vé 80,000đ. |
| 4 | Staff nhấn **Add**. Hệ thống kiểm tra phòng P1 lúc 19:00 ngày 15/07 trống. |
| 5 | Hệ thống hiển thị giao diện giá vé mặc định cho tất cả ghế của suất chiếu. |
| 6 | Staff chọn ghế VIP (A1, A2) đặt giá 120,000đ, các ghế còn lại giữ mặc định 80,000đ. |
| 7 | Staff nhấn **Confirm**. Hệ thống lưu suất chiếu + giá vé từng ghế vào database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Cinema | id, code, name, address, intro |
| ScreenRoom | id, cinemaId, code, numSeats, characteristics |
| Movie | id, code, title, type, year, description |
| Showtime | id, movieId, screenRoomId, time, date, ticketPrice |
| Seat | id, screenRoomId, seatNumber |
| SeatPrice | id, showtimeId, seatId, price |
| User | id, username, password, role |

### Quan hệ

```
Cinema 1 --- n ScreenRoom
Movie 1 --- n Showtime n --- 1 ScreenRoom
ScreenRoom 1 --- n Seat
Showtime 1 --- n SeatPrice n --- 1 Seat
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**ScheduleShowtimeFrm:**
- `inMovie`: combobox chọn phim
- `inScreenRoom`: combobox chọn phòng chiếu
- `inTime`: ô nhập giờ chiếu
- `inDate`: ô nhập ngày chiếu
- `inTicketPrice`: ô nhập giá vé mặc định
- `outSeatPriceTable`: bảng giá vé từng ghế (editable)
- `subAdd`: nút Add
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| MovieDAO | `getAllMovies(): List<Movie>` |
| ScreenRoomDAO | `getAllScreenRooms(): List<ScreenRoom>` |
| ShowtimeDAO | `addShowtime(showtime): boolean` |
| SeatDAO | `getSeatsByRoom(screenRoomId): List<Seat>` |
| SeatPriceDAO | `addSeatPrice(seatPrice): boolean` |

### Database

**tblShowtime:** id (PK), movieId (FK), screenRoomId (FK), time, date, ticketPrice

**tblSeatPrice:** id (PK), showtimeId (FK), seatId (FK), price

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:ScheduleShowtimeFrm` — boundary
3. `:ShowtimeDAO` — control
4. `:SeatDAO` — control
5. `:SeatPriceDAO` — control

**Message flow:**

1. Staff → ScheduleShowtimeFrm: `selectMovie(movie)` + `selectRoom(room)` + `enterTime("19:00")` + `enterDate("15/07/2026")` + `enterPrice(80000)`
2. Staff → ScheduleShowtimeFrm: `clickAdd()`
3. ScheduleShowtimeFrm → ShowtimeDAO: `addShowtime(showtime)`
4. ShowtimeDAO: INSERT INTO tblShowtime
5. ShowtimeDAO → ScheduleShowtimeFrm: return `showtimeId`
6. ScheduleShowtimeFrm → SeatDAO: `getSeatsByRoom(screenRoomId)`
7. SeatDAO → ScheduleShowtimeFrm: return `List<Seat>`
8. ScheduleShowtimeFrm: display seat price table (default 80,000đ)
9. Staff → ScheduleShowtimeFrm: edit VIP seats A1, A2 → 120,000đ
10. Staff → ScheduleShowtimeFrm: `clickConfirm()`
11. ScheduleShowtimeFrm → SeatPriceDAO: `addSeatPrice(listSeatPrice)`
12. SeatPriceDAO: INSERT INTO tblSeatPrice (each seat)

---

## Câu 5: Blackbox Testcase

### TC01: Thêm suất chiếu thành công

**Database trước:**
- tblShowtime: 0 dòng
- tblSeatPrice: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Avengers, P1, 19:00, 15/07, 80,000đ → Add | Bảng giá vé: tất cả ghế = 80,000đ |
| 2 | Sửa A1, A2 = 120,000đ → Confirm | "Them lich chieu thanh cong" |

**Database sau:**
- tblShowtime: thêm 1 dòng (movieId=Avengers, screenRoomId=P1, time=19:00, date=15/07/2026, ticketPrice=80000)
- tblSeatPrice: thêm N dòng (mỗi ghế 1 dòng, A1=120000, A2=120000, còn lại=80000)

### TC02: Phòng đã có suất chiếu trùng giờ

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Avengers, P1, 19:00, 15/07 → Add | "Phong da co lich chieu vao gio nay" |
