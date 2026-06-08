# Subject No. 45 — Cinema Chain — Module "Selling movie tickets"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Bán vé phim

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Selling movie tickets, Showtime management, Food management, Statistics. |
| 4 | Staff chọn **Selling movie tickets**. |
| 5 | Giao diện bán vé xuất hiện: combobox chọn phòng chiếu hoặc tên phim (cboMovie), combobox chọn khung giờ chiếu (cboShowtime), sơ đồ ghế, nút Print ticket (btnPrint). |
| 6 | Staff mở combobox tên phim, chọn "Avengers: Endgame". |
| 7 | Hệ thống cập nhật combobox suất chiếu: hiển thị các suất chiếu của phim "Avengers: Endgame" — "19:00 15/07/2026 - Phòng P1", "21:00 15/07/2026 - Phòng P1", "19:00 16/07/2026 - Phòng P2". |
| 8 | Staff chọn suất chiếu "19:00 15/07/2026 - Phòng P1". |
| 9 | Hệ thống hiển thị sơ đồ ghế của phòng P1: ghế trống màu xanh (A1, A2, A3, A4, A5, B1, B2, B3, B4, B5, ...), ghế đã bán màu đỏ (A3, B2). Hiển thị giá vé: ghế thường 80,000đ, ghế VIP (A1, A2) 120,000đ. |
| 10 | Staff hỏi khách hàng chọn ghế. Khách chọn ghế A1, A2, A4. |
| 11 | Staff tick chọn ghế A1, A2, A4 trên sơ đồ. Ghế được đánh dấu (màu vàng). |
| 12 | Hệ thống hiển thị hóa đơn bên dưới: Tên phim: Avengers: Endgame; Phòng: P1; Suất chiếu: 19:00 15/07/2026; Ghế A1: 120,000đ; Ghế A2: 120,000đ; Ghế A4: 80,000đ. Tổng tiền: 320,000đ. |
| 13 | Staff xác nhận với khách hàng, nhấn **Print ticket**. |
| 14 | Hệ thống lưu 3 vé vào database, đánh dấu ghế A1, A2, A4 là đã bán. |
| 15 | Hệ thống in vé và hóa đơn: mã vé, tên phim, phòng chiếu, suất chiếu, ghế, giá vé từng ghế, tổng tiền. Thông báo "Ban ve thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Staff chọn suất chiếu mà tất cả ghế đã bán → hệ thống hiển thị sơ đồ toàn đỏ, thông báo "Het cho ngoi".
- **EL2:** Staff không chọn ghế nào mà nhấn Print → hệ thống cảnh báo "Vui long chon it nhat 1 ghe".
- **EL3:** Ghế đã được bán bởi giao dịch khác (đồng thời) → hệ thống thông báo "Ghe da duoc ban, vui long chon ghe khac" và cập nhật lại sơ đồ.
- **EL4:** Staff chọn phim mà không có suất chiếu nào → combobox suất chiếu trống, hệ thống thông báo "Khong co suat chieu".

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý chuỗi rạp chiếu phim. Mỗi rạp có nhiều phòng chiếu. Mỗi phòng chiếu có mã, số ghế, đặc điểm. Phim có mã, tên, thể loại, năm, mô tả. Cùng một phim và phòng chiếu nhưng khác giờ chiếu thì giá vé khác nhau. Cùng một suất chiếu nhưng ghế khác nhau thì giá vé khác nhau (ghế VIP giá cao hơn). Mỗi ghế trong phòng chiếu có mã và số ghế. Khi bán vé, hệ thống tạo vé cho từng ghế được chọn tại một suất chiếu cụ thể. Hóa đơn chứa thông tin in vé: tên phòng, số ghế, ngày giờ chiếu, tên phim, giá vé từng ghế, tổng tiền. Rạp cũng bán đồ ăn nhanh (bắp rang, nước ngọt) kèm vé hoặc bán riêng.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Cinema | Entity class | | Rạp chiếu phim |
| code | Attribute (Cinema) | String | Mã rạp |
| name | Attribute (Cinema) | String | Tên rạp |
| address | Attribute (Cinema) | String | Địa chỉ |
| intro | Attribute (Cinema) | String | Giới thiệu |
| ScreenRoom | Entity class | | Phòng chiếu |
| code | Attribute (ScreenRoom) | String | Mã phòng |
| numSeats | Attribute (ScreenRoom) | int | Số ghế |
| characteristics | Attribute (ScreenRoom) | String | Đặc điểm |
| Movie | Entity class | | Phim |
| code | Attribute (Movie) | String | Mã phim |
| title | Attribute (Movie) | String | Tên phim |
| type | Attribute (Movie) | String | Thể loại |
| year | Attribute (Movie) | int | Năm sản xuất |
| description | Attribute (Movie) | String | Mô tả |
| Showtime | Entity class | | Suất chiếu |
| time | Attribute (Showtime) | String | Giờ chiếu |
| date | Attribute (Showtime) | Date | Ngày chiếu |
| ticketPrice | Attribute (Showtime) | float | Giá vé mặc định |
| Seat | Entity class | | Ghế trong phòng chiếu |
| seatNumber | Attribute (Seat) | String | Số ghế |
| Ticket | Entity class | | Vé bán |
| price | Attribute (Ticket) | float | Giá vé thực tế |
| FoodItem | Entity class | | Đồ ăn nhanh |
| code | Attribute (FoodItem) | String | Mã đồ ăn |
| name | Attribute (FoodItem) | String | Tên đồ ăn |
| price | Attribute (FoodItem) | float | Giá |
| size | Attribute (FoodItem) | String | Kích cỡ |
| Invoice | Entity class | | Hóa đơn in vé |
| User | Entity class | | Nhân viên thao tác hệ thống |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|     Cinema       |       |      Movie       |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -code: String    |
| -name: String    |       | -title: String   |
| -address: String |       | -type: String    |
| -intro: String   |       | -year: int       |
+------------------+       | -description: String|
         | 1               +------------------+
         |                          | 1
         | n                        | n
         v                          v
+------------------+       +------------------+
|   ScreenRoom     |       |    Showtime      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -time: String    |
| -numSeats: int   |       | -date: Date      |
| -characteristics: String| | -ticketPrice: float|
+------------------+       +------------------+
         | 1                  | 1        | 1
         |                    |          |
         | n                  | n        | n
         v                    v          v
+------------------+       +------------------+
|      Seat        |       |     Ticket       |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -seatNumber: String|     | -price: float    |
+------------------+       +------------------+
                                  | 1
                                  | n
                                  v
                           +------------------+
                           |     Invoice      |
                           +------------------+
                           | -id: int         |
                           | -invoiceDate: Date|
                           | -totalAmount: float|
                           +------------------+

+------------------+       +------------------+
|    FoodItem      |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -username: String|
| -name: String    |       | -password: String|
| -price: float    |       | -role: String    |
| -size: String    |       +------------------+
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Cinema → ScreenRoom | 1-n | Một rạp có nhiều phòng chiếu |
| ScreenRoom → Seat | 1-n | Một phòng chiếu có nhiều ghế |
| Movie → Showtime | 1-n | Một phim có nhiều suất chiếu |
| ScreenRoom → Showtime | 1-n | Một phòng chiếu có nhiều suất chiếu |
| Showtime → Ticket | 1-n | Một suất chiếu có nhiều vé bán |
| Seat → Ticket | 1-n | Một ghế có thể bán nhiều vé (ở các suất chiếu khác nhau) |
| Ticket → Invoice | n-1 | Nhiều vé thuộc một hóa đơn |
| FoodItem → Ticket | n-n | Đồ ăn có thể bán kèm vé hoặc riêng |

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subSellTicket`: nút chọn Selling movie tickets (JButton)
- `subShowtime`: nút chọn Showtime management (JButton)
- `subFood`: nút chọn Food management (JButton)
- `subStatistics`: nút chọn Statistics (JButton)

**SellTicketFrm:**
- `inMovie`: combobox chọn phim (JComboBox<Movie>)
- `inShowtime`: combobox chọn suất chiếu (JComboBox<Showtime>)
- `outSeatMap`: sơ đồ ghế hiển thị trạng thái trống/đã bán (JPanel với các JButton)
- `outInvoice`: bảng hóa đơn hiển thị thông tin vé đã chọn (JTable) — các cột: Ghế, Giá vé
- `outTotalAmount`: ô hiển thị tổng tiền (JLabel)
- `subPrint`: nút Print ticket (JButton)

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Cinema | Model | id: int, code: String, name: String, address: String, intro: String |
| ScreenRoom | Model | id: int, cinemaId: int, code: String, numSeats: int, characteristics: String |
| Movie | Model | id: int, code: String, title: String, type: String, year: int, description: String |
| Showtime | Model | id: int, movieId: int, screenRoomId: int, time: String, date: Date, ticketPrice: float |
| Seat | Model | id: int, screenRoomId: int, seatNumber: String |
| Ticket | Model | id: int, showtimeId: int, seatId: int, price: float |
| Invoice | Model | id: int, invoiceDate: Date, totalAmount: float |
| FoodItem | Model | id: int, code: String, name: String, price: float, size: String |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| MovieDAO | `getAllMovies(): List<Movie>` | Lấy danh sách phim cho combobox |
| ShowtimeDAO | `getShowtimesByMovie(movieId): List<Showtime>` | Lấy suất chiếu theo phim |
| SeatDAO | `getSeatsByRoom(screenRoomId): List<Seat>` | Lấy danh sách ghế theo phòng |
| SeatDAO | `getAvailableSeats(showtimeId): List<Seat>` | Lấy ghế trống theo suất chiếu |
| SeatDAO | `getSeatPrice(showtimeId, seatId): float` | Lấy giá vé theo suất chiếu và ghế |
| TicketDAO | `addTicket(ticket): boolean` | Lưu vé vào database |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblCinema:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | nvarchar(100) | NOT NULL |
| address | nvarchar(255) | |
| intro | nvarchar(255) | |

**tblScreenRoom:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| cinemaID | int | FOREIGN KEY → tblCinema(ID) |
| code | varchar(20) | NOT NULL |
| numSeats | int | NOT NULL |
| characteristics | nvarchar(255) | |

**tblMovie:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| title | nvarchar(200) | NOT NULL |
| type | varchar(50) | |
| year | int | |
| description | nvarchar(500) | |

**tblShowtime:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| movieID | int | FOREIGN KEY → tblMovie(ID) |
| screenRoomID | int | FOREIGN KEY → tblScreenRoom(ID) |
| time | varchar(10) | NOT NULL |
| date | date | NOT NULL |
| ticketPrice | float | NOT NULL |

**tblSeat:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| screenRoomID | int | FOREIGN KEY → tblScreenRoom(ID) |
| seatNumber | varchar(10) | NOT NULL |

**tblSeatPrice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| showtimeID | int | FOREIGN KEY → tblShowtime(ID) |
| seatID | int | FOREIGN KEY → tblSeat(ID) |
| price | float | NOT NULL |

**tblTicket:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| showtimeID | int | FOREIGN KEY → tblShowtime(ID) |
| seatID | int | FOREIGN KEY → tblSeat(ID) |
| price | float | NOT NULL |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| invoiceDate | datetime | NOT NULL |
| totalAmount | float | NOT NULL |

**tblInvoiceDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| invoiceID | int | FOREIGN KEY → tblInvoice(ID) |
| ticketID | int | FOREIGN KEY → tblTicket(ID) |
| amount | float | NOT NULL |

**tblFoodItem:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | nvarchar(100) | NOT NULL |
| price | float | NOT NULL |
| size | varchar(20) | |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.ticket`: chứa LoginFrm, HomeFrm, SellTicketFrm.
2. Tạo package `model`: chứa Cinema, ScreenRoom, Movie, Showtime, Seat, Ticket, Invoice, InvoiceDetail, FoodItem, User.
3. Tạo package `dao`: chứa UserDAO, MovieDAO, ShowtimeDAO, SeatDAO, TicketDAO.
4. Vẽ association từ SellTicketFrm → MovieDAO, SellTicketFrm → ShowtimeDAO, SellTicketFrm → SeatDAO, SellTicketFrm → TicketDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dùng mũi tên dashed).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `SellTicketFrm`, `UserDAO`, `MovieDAO`, `ShowtimeDAO`, `SeatDAO`, `TicketDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `loop` fragment cho việc chọn nhiều ghế.
4. Sử dụng `alt` fragment cho nhánh ngoại lệ (ghế đã bán).

### Sequence Diagram (ASCII)

```
Staff      LoginFrm  UserDAO  HomeFrm  SellTicketFrm  MovieDAO  ShowtimeDAO  SeatDAO  TicketDAO
  |            |         |        |          |            |          |           |          |
  |--login---->|         |        |          |            |          |           |          |
  |            |--checkLogin()-->|          |            |          |           |          |
  |            |<--true--|        |          |            |          |           |          |
  |            |--open HomeFrm-->|          |            |          |           |          |
  |            |         |        |          |            |          |           |          |
  |--select Sell Ticket-->|       |          |            |          |           |          |
  |            |         |        |--open SellTicketFrm-->|          |           |          |
  |            |         |        |          |            |          |           |          |
  |            |         |        |          |--getAllMovies()------->|           |          |
  |            |         |        |          |<--List<Movie>|        |           |          |
  |            |         |        |          |--populate movie dropdown          |          |
  |            |         |        |          |            |          |           |          |
  |--select "Avengers"-->|        |          |            |          |           |          |
  |            |         |        |          |--getShowtimesByMovie(movieId)---->|          |
  |            |         |        |          |<--List<Showtime>|     |           |          |
  |            |         |        |          |--populate showtime dropdown       |          |
  |            |         |        |          |            |          |           |          |
  |--select "19:00 15/07"-->|     |          |            |          |           |          |
  |            |         |        |          |--getAvailableSeats(showtimeId)------------->|
  |            |         |        |          |<--List<Seat>|         |           |          |
  |            |         |        |          |--getSeatPrice(showtimeId, eachSeatId)------>|
  |            |         |        |          |<--prices----|         |           |          |
  |            |         |        |          |--display seat map (green=empty, red=sold)   |
  |            |         |        |          |            |          |           |          |
  |--tick A1, A2, A4---->|        |          |            |          |           |          |
  |            |         |        |          |--highlight selected seats (yellow)          |
  |            |         |        |          |--display invoice: A1=120k, A2=120k, A4=80k  |
  |            |         |        |          |--display total: 320,000đ                    |
  |            |         |        |          |            |          |           |          |
  |--click Print-------->|         |          |            |          |           |          |
  |            |         |        |          |--addTicket(ticket1: A1, 120k)--------------->|
  |            |         |        |          |            |          |           |--INSERT  |
  |            |         |        |          |<--true------|          |           |          |
  |            |         |        |          |--addTicket(ticket2: A2, 120k)--------------->|
  |            |         |        |          |            |          |           |--INSERT  |
  |            |         |        |          |<--true------|          |           |          |
  |            |         |        |          |--addTicket(ticket3: A4, 80k)---------------->|
  |            |         |        |          |            |          |           |--INSERT  |
  |            |         |        |          |<--true------|          |           |          |
  |            |         |        |          |            |          |           |          |
  |            |         |        |          |--print ticket + invoice                     |
  |            |         |        |          |--show "Ban ve thanh cong"                   |
  |<--success--|         |        |          |            |          |           |          |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | actionPerformed("Login") | Staff nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra thông tin đăng nhập |
| 3 | UserDAO | LoginFrm | return true | Đăng nhập thành công |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Staff | HomeFrm | actionPerformed("Selling movie tickets") | Chọn chức năng bán vé |
| 6 | HomeFrm | SellTicketFrm | new SellTicketFrm().setVisible(true) | Mở giao diện bán vé |
| 7 | SellTicketFrm | MovieDAO | getAllMovies() | Lấy danh sách phim |
| 8 | MovieDAO | SellTicketFrm | return List<Movie> | Trả về danh sách phim |
| 9 | SellTicketFrm | SellTicketFrm | populateComboBox(listMovie) | Đổ dữ liệu vào combobox phim |
| 10 | Staff | SellTicketFrm | selectMovie("Avengers: Endgame") | Chọn phim |
| 11 | SellTicketFrm | ShowtimeDAO | getShowtimesByMovie(movieId=1) | Lấy suất chiếu theo phim |
| 12 | ShowtimeDAO | SellTicketFrm | return List<Showtime> | Trả về danh sách suất chiếu |
| 13 | SellTicketFrm | SellTicketFrm | populateComboBox(listShowtime) | Đổ dữ liệu vào combobox suất chiếu |
| 14 | Staff | SellTicketFrm | selectShowtime("19:00 15/07/2026 - P1") | Chọn suất chiếu |
| 15 | SellTicketFrm | SeatDAO | getAvailableSeats(showtimeId=1) | Lấy ghế trống |
| 16 | SeatDAO | SellTicketFrm | return List<Seat> | Trả về danh sách ghế |
| 17 | SellTicketFrm | SeatDAO | getSeatPrice(showtimeId=1, seatId) | Lấy giá từng ghế |
| 18 | SeatDAO | SellTicketFrm | return prices | Trả về giá vé |
| 19 | SellTicketFrm | SellTicketFrm | displaySeatMap(seats, prices, soldSeats) | Hiển thị sơ đồ ghế |
| 20 | Staff | SellTicketFrm | tickSeats(A1, A2, A4) | Chọn 3 ghế |
| 21 | SellTicketFrm | SellTicketFrm | highlightSeats(A1, A2, A4) | Đánh dấu ghế chọn |
| 22 | SellTicketFrm | SellTicketFrm | displayInvoice(A1=120k, A2=120k, A4=80k, total=320k) | Hiển thị hóa đơn |
| 23 | Staff | SellTicketFrm | actionPerformed("Print ticket") | Nhấn nút in vé |
| 24 | SellTicketFrm | TicketDAO | addTicket(ticket1: showtimeId=1, seatId=A1, price=120000) | Lưu vé 1 |
| 25 | TicketDAO | SellTicketFrm | return true | Thành công |
| 26 | SellTicketFrm | TicketDAO | addTicket(ticket2: showtimeId=1, seatId=A2, price=120000) | Lưu vé 2 |
| 27 | TicketDAO | SellTicketFrm | return true | Thành công |
| 28 | SellTicketFrm | TicketDAO | addTicket(ticket3: showtimeId=1, seatId=A4, price=80000) | Lưu vé 3 |
| 29 | TicketDAO | SellTicketFrm | return true | Thành công |
| 30 | SellTicketFrm | Staff | showMessage("Ban ve thanh cong") | Thông báo thành công |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Selling movie tickets | Bán vé thành công (3 ghế) |
| TC02 | Selling movie tickets | Chọn suất chiếu hết ghế |
| TC03 | Selling movie tickets | Không chọn ghế nào |
| TC04 | Selling movie tickets | Ghế đã bị bán (đồng thời) |

### TC01: Bán vé thành công

**Purpose:** Kiểm tra quy trình bán vé hoàn chỉnh từ chọn phim, chọn suất chiếu, chọn ghế, hiển thị hóa đơn đến in vé.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblCinema:**
| ID | code | name | address | intro |
|----|------|------|---------|-------|
| 1 | CN01 | Rap Quan 1 | 123 Le Loi, Q1, TP.HCM | Rap chinh |

**tblScreenRoom:**
| ID | cinemaID | code | numSeats | characteristics |
|----|----------|------|----------|-----------------|
| 1 | 1 | P1 | 10 | Phong chuan, 2D |
| 2 | 1 | P2 | 8 | Phong VIP, 3D |

**tblMovie:**
| ID | code | title | type | year | description |
|----|------|-------|------|------|-------------|
| 1 | MV01 | Avengers: Endgame | Hanh dong | 2019 | Phim sieu anh hung |
| 2 | MV02 | Spider-Man: No Way Home | Hanh dong | 2021 | Phim sieu anh hung |

**tblShowtime:**
| ID | movieID | screenRoomID | time | date | ticketPrice |
|----|---------|--------------|------|------|-------------|
| 1 | 1 | 1 | 19:00 | 15/07/2026 | 80000 |
| 2 | 1 | 1 | 21:00 | 15/07/2026 | 90000 |
| 3 | 2 | 2 | 19:00 | 15/07/2026 | 100000 |

**tblSeat:**
| ID | screenRoomID | seatNumber |
|----|--------------|------------|
| 1 | 1 | A1 |
| 2 | 1 | A2 |
| 3 | 1 | A3 |
| 4 | 1 | A4 |
| 5 | 1 | A5 |
| 6 | 1 | B1 |
| 7 | 1 | B2 |
| 8 | 1 | B3 |
| 9 | 1 | B4 |
| 10 | 1 | B5 |

**tblSeatPrice:**
| ID | showtimeID | seatID | price |
|----|------------|--------|-------|
| 1 | 1 | 1 | 120000 |
| 2 | 1 | 2 | 120000 |
| 3 | 1 | 3 | 80000 |
| 4 | 1 | 4 | 80000 |
| 5 | 1 | 5 | 80000 |
| 6 | 1 | 6 | 80000 |
| 7 | 1 | 7 | 80000 |
| 8 | 1 | 8 | 80000 |
| 9 | 1 | 9 | 80000 |
| 10 | 1 | 10 | 80000 |

**tblTicket:**
| ID | showtimeID | seatID | price |
|----|------------|--------|-------|
| 1 | 1 | 3 | 80000 |
| 2 | 1 | 7 | 80000 |

**tblInvoice:** (rỗng)

**tblInvoiceDetail:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username = `staff01`, password = `123456`, nhấn Login | Giao diện Home hiển thị với các chức năng: Selling movie tickets, Showtime management, Food management, Statistics |
| 3 | Nhấn chọn **Selling movie tickets** | Giao diện bán vé hiển thị: combobox phim (Avengers, Spider-Man), combobox suất chiếu (trống), sơ đồ ghế (trống) |
| 4 | Chọn phim "Avengers: Endgame" từ combobox | Combobox suất chiếu cập nhật: "19:00 15/07/2026 - P1", "21:00 15/07/2026 - P1" |
| 5 | Chọn suất chiếu "19:00 15/07/2026 - P1" | Sơ đồ ghế hiển thị: A1(xanh, 120k), A2(xanh, 120k), A3(đỏ), A4(xanh, 80k), A5(xanh, 80k), B1(xanh, 80k), B2(đỏ), B3(xanh, 80k), B4(xanh, 80k), B5(xanh, 80k) |
| 6 | Tick chọn ghế A1, A2, A4 | Ghế A1, A2, A4 chuyển màu vàng. Hóa đơn hiển thị: A1 - 120,000đ; A2 - 120,000đ; A4 - 80,000đ. Tổng: 320,000đ |
| 7 | Nhấn **Print ticket** | Hệ thống hiển thị thông báo "Ban ve thanh cong". In vé: mã vé, Avengers: Endgame, Phòng P1, 19:00 15/07/2026, ghế A1/A2/A4, tổng 320,000đ |

### Database sau khi test

**tblTicket:** (thêm 3 dòng)
| ID | showtimeID | seatID | price |
|----|------------|--------|-------|
| 1 | 1 | 3 | 80000 |
| 2 | 1 | 7 | 80000 |
| 3 | 1 | 1 | 120000 |
| 4 | 1 | 2 | 120000 |
| 5 | 1 | 4 | 80000 |

**tblInvoice:** (thêm 1 dòng)
| ID | invoiceDate | totalAmount |
|----|-------------|-------------|
| 1 | 15/07/2026 18:30 | 320000 |

**tblInvoiceDetail:** (thêm 3 dòng)
| ID | invoiceID | ticketID | amount |
|----|-----------|----------|--------|
| 1 | 1 | 3 | 120000 |
| 2 | 1 | 4 | 120000 |
| 3 | 1 | 5 | 80000 |
