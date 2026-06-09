# Subject No. 46 — Cinema Chain — Module "Schedule showing"

> **Domain:** Cinema Chain Management

---

## Cau 1: Scenario — Quan ly lich chieu

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly chuoi rap chieu phim. Moi rap chieu phim (ma rap, ten rap, dia chi, gioi thieu) co nhieu phong chieu (ma phong, so ghe, dac diem phong). Moi phim (ma phim, ten phim, the loai, nam san xuat, mo ta) co the duoc chieu o nhieu rap, nhieu khung gio khac nhau. Tai mot thoi diem, trong mot phong chieu chi chieu mot phim voi mot gia ve co dinh. Cung mot phim, cung phong chieu, nhung khung gio/ngay khac nhau co the co gia ve khac nhau. Cung mot suat chieu, cac ghe khac nhau co the co gia ve khac nhau.

### Scenario chinh

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Sell tickets, Schedule showing, Selling food, Revenue Statistics. |
| 4 | Staff chon chuc nang **Showtime management**. |
| 5 | Giao dien quan ly suat chieu xuat hien voi: combobox chon phim, combobox chon phong chieu, o nhap ngay chieu, o nhap gio chieu, o nhap gia ve mac dinh, nut Add. |
| 6 | Staff chon phim "Avengers: Endgame" tu combobox. |
| 7 | Staff chon phong chieu "P01 - Phong IMAX 1" tu combobox. |
| 8 | Staff nhap ngay chieu `15/07/2026` va gio chieu `19:00`. |
| 9 | Staff nhap gia ve mac dinh `80000` vao o gia ve. |
| 10 | Staff nhan nut **Add**. He thong kiem tra phong P01 luc 19:00 ngay 15/07/2026 co trong (chua co suat chieu trung gio). |
| 11 | He thong hien thi giao dien gia ve tung ghe cua suat chieu vua tao: bang gom cot So ghe, Gia ve. Tat ca ghe mac dinh 80,000d. |
| 12 | Staff chon ghe VIP A1, A2, A3 va sua gia thanh `120000` cho tung ghe. Cac ghe con lai giu nguyen 80,000d. |
| 13 | Staff nhan nut **Confirm**. He thong luu suat chieu va bang gia tung ghe vao database. |
| 14 | He thong thong bao "Them lich chieu thanh cong" va lam moi bang danh sach suat chieu. |

### Kich ban ngoai le

- **EL1:** Phong da co suat chieu trung gio/ngay → He thong thong bao "Phong da co lich chieu vao gio nay".
- **EL2:** Phim khong ton tai trong he thong → He thong thong bao "Phim khong ton tai".
- **EL3:** Gia ve mac dinh <= 0 → He thong thong bao "Gia ve khong hop le".
- **EL4:** Ngay chieu la ngay trong qua khu → He thong thong bao "Ngay chieu khong hop le".

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly chuoi rap chieu phim. Moi rap chieu phim (ma rap, ten rap, dia chi, gioi thieu) co nhieu phong chieu (ma phong, so ghe, dac diem phong). Moi phong chieu co nhieu ghe (so ghe, loai ghe). Moi phim (ma phim, ten phim, the loai, nam san xuat, mo ta) co the chieu o nhieu phong, nhieu khung gio khac nhau. Moi suat chieu lien ket mot phim voi mot phong tai mot thoi diem cu the, voi gia ve mac dinh. Cung mot suat chieu, cac ghe khac nhau co the co gia ve khac nhau (ghe VIP gia cao hon). Nguoi dung (staff) dang nhap he thong de quan ly lich chieu.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Rap chieu phim (Cinema) | Entity class | Doi tuong quan ly chinh — chuoi rap |
| Phong chieu (ScreenRoom) | Entity class | Thuoc mot rap, chua nhieu ghe |
| Ghe (Seat) | Entity class | Thuoc mot phong, duoc gan gia theo suat chieu |
| Phim (Movie) | Entity class | Doi tuong duoc chieu tai nhieu rap |
| Suat chieu (Showtime) | Entity class | Lien ket phim voi phong tai mot thoi diem |
| Gia ghe (SeatPricing) | Entity class | Gia ve rieng biet cho tung ghe trong mot suat chieu |
| Nguoi dung (User) | Entity class | Nhan vien thao tac he thong |
| Ma rap, ten rap, dia chi | Thuoc tinh | Thuoc tinh cua Cinema |
| Ma phong, so ghe, dac diem | Thuoc tinh | Thuoc tinh cua ScreenRoom |
| So ghe, loai ghe | Thuoc tinh | Thuoc tinh cua Seat |
| Ma phim, ten phim, the loai, nam | Thuoc tinh | Thuoc tinh cua Movie |
| Ngay chieu, gio chieu, gia ve mac dinh | Thuoc tinh | Thuoc tinh cua Showtime |
| Gia rieng | Thuoc tinh | Thuoc tinh cua SeatPricing |

### Buoc 3: Xac dinh quan he

```
Cinema 1 --- n ScreenRoom
```
- Mot rap co nhieu phong chieu.
- Moi phong chieu thuoc mot rap.

```
ScreenRoom 1 --- n Seat
```
- Mot phong co nhieu ghe.
- Moi ghe thuoc mot phong.

```
Movie 1 --- n Showtime
```
- Mot phim co nhieu suat chieu.
- Moi suat chieu lien ket mot phim.

```
ScreenRoom 1 --- n Showtime
```
- Mot phong co nhieu suat chieu.
- Moi suat chieu lien ket mot phong.

```
Showtime 1 --- n SeatPricing
```
- Mot suat chieu co nhieu ban gia ghe.
- Moi ban gia ghe thuoc mot suat chieu.

```
Seat 1 --- n SeatPricing
```
- Mot ghe co ban gia o nhieu suat chieu khac nhau.
- Moi ban gia ghe lien ket mot ghe.

```
User 1 --- n Showtime
```
- Mot nhan vien tao nhieu suat chieu.
- Moi suat chieu duoc tao boi mot nhan vien.

### Buoc 4: Class Diagram (Analysis)

```
+------------------+       +------------------+
|     Cinema       |       |   ScreenRoom     |
+------------------+       +------------------+
| -code: String    |       | -code: String    |
| -name: String    |       | -numSeats: int   |
| -address: String |       | -characteristics: String|
| -intro: String   |       +------------------+
+------------------+               | 1
         | 1                       |
         |                         | n
         | n                       v
         +----------------->+------------------+
                            |      Seat        |
                            +------------------+
                            | -seatNumber: String|
                            | -seatType: String |
                            +------------------+

+------------------+       +------------------+       +------------------+
|     Movie        |       |    Showtime      |       |  SeatPricing     |
+------------------+       +------------------+       +------------------+
| -code: String    |       | -time: String    |       | -price: float    |
| -title: String   |       | -date: Date      |       +------------------+
| -type: String    |       | -ticketPrice: float|              ^
| -year: int       |       +------------------+               |
| -description: String|           | 1                    | 1
+------------------+              |                        |
         | 1                      | n                      |
         |                        v                        |
         | n               +------------------+            |
         +---------------->|                  |------------+
                            |                  |
                            +------------------+
                                    | n
                                    |
                                    v
                            +------------------+
                            |      Seat        |
                            +------------------+


+------------------+
|      User        |
+------------------+
| -username: String|
| -password: String|
| -role: String    |
+------------------+
| +checkLogin()    |
+------------------+
```

### Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Cinema → ScreenRoom | 1-n (Composition) | Mot rap co nhieu phong, phong khong ton tai neu khong co rap |
| ScreenRoom → Seat | 1-n (Composition) | Mot phong co nhieu ghe, ghe khong ton tai neu khong co phong |
| Movie → Showtime | 1-n (Association) | Mot phim co nhieu suat chieu |
| ScreenRoom → Showtime | 1-n (Association) | Mot phong co nhieu suat chieu |
| Showtime → SeatPricing | 1-n (Composition) | Mot suat chieu co nhieu ban gia ghe |
| Seat → SeatPricing | 1-n (Association) | Mot ghe co ban gia o nhieu suat chieu |
| User → Showtime | 1-n (Association) | Mot nhan vien tao nhieu suat chieu |
| Movie → ScreenRoom | n-n (qua Showtime) | Phim chieu o nhieu phong, phong chieu nhieu phim |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**Các bước vẽ tổng quan:**

1. Mở Visual Paradigm → New → Class Diagram (trong danh mục Diagrams).
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity: Cinema, ScreenRoom, Seat, Movie, Showtime, SeatPricing, User.
3. Tạo view class boxes từ các interface: HomeFrm, ScheduleShowtimeFrm, SeatPricingFrm.
4. Vẽ relationships giữa các class theo bảng quan hệ bên dưới.
5. Thêm multiplicities và role names cho mỗi đường kết nối.

**Cấu trúc 1 class box (3 ngăn):**

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Showtime`.
- **Ngăn 2 (thuộc tính):** Mỗi thuộc tính ghi dạng `-attributeName: Type`. Ví dụ: `-time: String`, `-ticketPrice: float`.
- **Ngăn 3 (phương thức):** Mỗi phương thức ghi dạng `+methodName(params): ReturnType`. Ví dụ: `+checkConflict(screenRoomId: int, date: Date, time: String): boolean`.

**Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Cinema | `<<entity>>` | -id: int, -code: String, -name: String, -address: String, -intro: String | getter/setter |
| ScreenRoom | `<<entity>>` | -id: int, -code: String, -numSeats: int, -characteristics: String | getter/setter |
| Seat | `<<entity>>` | -id: int, -seatNumber: String, -seatType: String | +getSeatsByRoom(screenRoomId: int): List<Seat> |
| Movie | `<<entity>>` | -id: int, -code: String, -title: String, -type: String, -year: int, -description: String | +getAllMovies(): List<Movie> |
| Showtime | `<<entity>>` | -id: int, -time: String, -date: Date, -ticketPrice: float | +checkConflict(screenRoomId: int, date: Date, time: String): boolean, +addShowtime(showtime: Showtime): int |
| SeatPricing | `<<entity>>` | -id: int, -price: float | +addSeatPricing(sp: SeatPricing): boolean |
| User | `<<entity>>` | -id: int, -username: String, -password: String, -role: String | +checkLogin(username: String, password: String): boolean |

**Bảng chi tiết view classes:**

| View class | Stereotype | UI Elements |
|------------|-----------|-------------|
| HomeFrm | `<<boundary>>` | subScheduleShowing: JButton |
| ScheduleShowtimeFrm | `<<boundary>>` | inMovie: JComboBox, inScreenRoom: JComboBox, inDate: JTextField, inTime: JTextField, inTicketPrice: JTextField, subAdd: JButton, outsubShowtimeTable: JTable |
| SeatPricingFrm | `<<boundary>>` | outShowtimeInfo: JLabel, outsubSeatTable: JTable, subConfirm: JButton |

**Cách vẽ quan hệ:**

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Movie → Showtime.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Cinema ◆→ ScreenRoom.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: ScheduleShowtimeFrm → MovieDAO.

**Cách ghi multiplicity:**

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Cinema "1" --- "n" ScreenRoom.

**Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|--------------|------------|
| Cinema | ScreenRoom | Composition | 1 — n | Một rạp có nhiều phòng, phòng không tồn tại nếu không có rạp |
| ScreenRoom | Seat | Composition | 1 — n | Một phòng có nhiều ghế, ghế không tồn tại nếu không có phòng |
| Movie | Showtime | Association | 1 — n | Một phim có nhiều suất chiếu |
| ScreenRoom | Showtime | Association | 1 — n | Một phòng có nhiều suất chiếu |
| Showtime | SeatPricing | Composition | 1 — n | Một suất chiếu có nhiều bảng giá ghế, giá ghế không tồn tại nếu không có suất chiếu |
| Seat | SeatPricing | Association | 1 — n | Một ghế có bảng giá ở nhiều suất chiếu |
| User | Showtime | Association | 1 — n | Một nhân viên tạo nhiều suất chiếu |
| ScheduleShowtimeFrm | MovieDAO | Dependency | — | Frm sử dụng MovieDAO |
| ScheduleShowtimeFrm | ScreenRoomDAO | Dependency | — | Frm sử dụng ScreenRoomDAO |
| ScheduleShowtimeFrm | ShowtimeDAO | Dependency | — | Frm sử dụng ShowtimeDAO |
| SeatPricingFrm | SeatDAO | Dependency | — | Frm sử dụng SeatDAO |
| SeatPricingFrm | SeatPricingDAO | Dependency | — | Frm sử dụng SeatPricingDAO |

**Ví dụ cụ thể trên Visual Paradigm:**

1. **Vẽ quan hệ Showtime → SeatPricing (Composition 1-n):**
   - Kéo class Showtime lên canvas, kéo class SeatPricing bên dưới.
   - Chọn tool "Association" → click vào Showtime, kéo đến SeatPricing.
   - Click chuột phải vào đường kết nối → đặt multiplicity "1" phía Showtime, "n" phía SeatPricing.
   - Click chuột phải → "Association End" → phía Showtime đặt "filled diamond" (◆).

2. **Vẽ dependency ScheduleShowtimeFrm → ShowtimeDAO:**
   - Chọn tool "Dependency" (đường dashed) → click vào ScheduleShowtimeFrm, kéo đến ShowtimeDAO.
   - Mũi tên tam giác rỗng (▷) tự động hiển thị phía ShowtimeDAO.

### Classes diagram (analysis)

Phan tich module nay (bo qua buoc dang nhap):

Sau khi dang nhap thanh cong -> Giao dien Home xuat hien:
- mot lua chon quan ly suat chieu -> subScheduleShowing

Chon quan ly suat chieu -> Giao dien quan ly xuat hien:
- combobox chon phim -> inMovie
- combobox chon phong chieu -> inScreenRoom
- o nhap ngay chieu -> inDate
- o nhap gio chieu -> inTime
- o nhap gia ve mac dinh -> inTicketPrice
- nut Add -> subAdd
- bang danh sach suat chieu (click duoc) -> outsubShowtimeTable

Mo giao dien -> he thong tai DS phim -> can phuong thuc:
- ten: getAllMovies()
- dau vao: khong
- dau ra: danh sach Movie
- gan cho entity class: Movie.

He thong tai DS phong -> can phuong thuc:
- ten: getAllScreenRooms()
- dau vao: khong
- dau ra: danh sach ScreenRoom
- gan cho entity class: ScreenRoom.

Nhan Add -> he thong kiem tra trung lich -> can phuong thuc:
- ten: checkConflict()
- dau vao: screenRoomId, date, time
- dau ra: boolean
- gan cho entity class: Showtime.

He thong them suat chieu -> can phuong thuc:
- ten: addShowtime()
- dau vao: doi tuong Showtime
- dau ra: int (ID suat chieu)
- gan cho entity class: Showtime.

Hien thi giao dien gia tung ghe:
- bang gia tung ghe (co the edit) -> outsubSeatTable
- nut Confirm -> subConfirm

He thong lay ghe theo phong -> can phuong thuc:
- ten: getSeatsByRoom()
- dau vao: screenRoomId
- dau ra: danh sach Seat
- gan cho entity class: Seat.

Nhan Confirm -> he thong luu gia tung ghe -> can phuong thuc:
- ten: addSeatPricing()
- dau vao: doi tuong SeatPricing
- dau ra: boolean
- gan cho entity class: SeatPricing.

### Tom tat
View classes: HomeFrm, ScheduleShowtimeFrm, SeatPricingFrm
Methods: getAllMovies(), getAllScreenRooms(), checkConflict(), addShowtime(), getSeatsByRoom(), addSeatPricing()

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| ScheduleShowtimeFrm | Giao dien quan ly lich chieu (chinh) |
| SeatPricingFrm | Giao dien thiet lap gia ve tung ghe |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subSellTickets`: nut chon Sell tickets
- `subScheduleShowing`: nut chon Schedule showing
- `subSellingFood`: nut chon Selling food
- `subRevenueStatistics`: nut chon Revenue Statistics

**ScheduleShowtimeFrm:**
- `inMovie`: combobox chon phim
- `inScreenRoom`: combobox chon phong chieu
- `inDate`: o nhap ngay chieu
- `inTime`: o nhap gio chieu
- `inTicketPrice`: o nhap gia ve mac dinh
- `subAdd`: nut Add
- `outsubShowtimeTable`: bang danh sach suat chieu (click duoc)

**SeatPricingFrm:**
- `outShowtimeInfo`: thong tin suat chieu (phim, phong, ngay, gio, gia mac dinh)
- `outsubSeatTable`: bang gia ve tung ghe (co the edit gia)
- `subConfirm`: nut Confirm

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| MovieDAO | `getAllMovies(): List<Movie>` | Lay danh sach phim |
| ScreenRoomDAO | `getAllScreenRooms(): List<ScreenRoom>` | Lay danh sach phong chieu |
| ShowtimeDAO | `checkConflict(screenRoomId, date, time): boolean` | Kiem tra trung lich |
| ShowtimeDAO | `addShowtime(showtime): int` | Them suat chieu moi, tra ve ID |
| SeatDAO | `getSeatsByRoom(screenRoomId): List<Seat>` | Lay danh sach ghe theo phong |
| SeatPricingDAO | `addSeatPricing(seatPricing): boolean` | Them gia ve tung ghe |

### Buoc 4: Xac dinh Entity class (Design phase)

**Cinema:**
- `id: int` (PK)
- `code: String`
- `name: String`
- `address: String`
- `intro: String`

**ScreenRoom:**
- `id: int` (PK)
- `code: String`
- `numSeats: int`
- `characteristics: String`
- `cinemaId: int` (FK → Cinema)

**Seat:**
- `id: int` (PK)
- `seatNumber: String`
- `seatType: String`
- `screenRoomId: int` (FK → ScreenRoom)

**Movie:**
- `id: int` (PK)
- `code: String`
- `title: String`
- `type: String`
- `year: int`
- `description: String`

**Showtime:**
- `id: int` (PK)
- `time: String`
- `date: Date`
- `ticketPrice: float`
- `movieId: int` (FK → Movie)
- `screenRoomId: int` (FK → ScreenRoom)
- `userId: int` (FK → User)

**SeatPricing:**
- `id: int` (PK)
- `price: float`
- `showtimeId: int` (FK → Showtime)
- `seatId: int` (FK → Seat)

**User:**
- `id: int` (PK)
- `username: String`
- `password: String`
- `role: String`

### Buoc 5: Database Design

**tblCinema:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| address | varchar | |
| intro | varchar | |

**tblScreenRoom:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| numSeats | int | |
| characteristics | varchar | |
| cinemaID | int | FK → tblCinema.ID |

**tblSeat:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| seatNumber | varchar | |
| seatType | varchar | |
| screenRoomID | int | FK → tblScreenRoom.ID |

**tblMovie:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| title | varchar | |
| type | varchar | |
| year | int | |
| description | varchar | |

**tblShowtime:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| time | varchar | |
| date | date | |
| ticketPrice | float | |
| movieID | int | FK → tblMovie.ID |
| screenRoomID | int | FK → tblScreenRoom.ID |
| userID | int | FK → tblUser.ID |

**tblSeatPricing:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| price | float | |
| showtimeID | int | FK → tblShowtime.ID |
| seatID | int | FK → tblSeat.ID |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| username | varchar | |
| password | varchar | |
| role | varchar | |

### Buoc 6: Mo ta cach ve Class Diagram (Design phase) bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Class Diagram** trong danh muc Diagrams.
2. Tao cac class:
   - View classes: `LoginFrm`, `HomeFrm`, `ScheduleShowtimeFrm`, `SeatPricingFrm`
   - DAO classes: `UserDAO`, `MovieDAO`, `ScreenRoomDAO`, `ShowtimeDAO`, `SeatDAO`, `SeatPricingDAO`
   - Entity classes: `Cinema`, `ScreenRoom`, `Seat`, `Movie`, `Showtime`, `SeatPricing`, `User`

3. Ve View classes (Form):
   - Ve hinh chu nhat 3 ngan cho `ScheduleShowtimeFrm`:
     - Ngan 1 (ten): `<<boundary>> ScheduleShowtimeFrm`
     - Ngan 2 (thuoc tinh): `-inMovie: JComboBox`, `-inScreenRoom: JComboBox`, `-inDate: JTextField`, `-inTime: JTextField`, `-inTicketPrice: JTextField`, `-subAdd: JButton`
     - Ngan 3 (phuong thuc): khong co
   - Tuong tu cho `SeatPricingFrm`: `-outShowtimeInfo: JLabel`, `-outsubSeatTable: JTable`, `-subConfirm: JButton`

4. Ve DAO classes:
   - Ve hinh chu nhat 3 ngan cho `ShowtimeDAO`:
     - Ngan 1: `<<control>> ShowtimeDAO`
     - Ngan 2: khong co thuoc tinh
     - Ngan 3: `+checkConflict(screenRoomId: int, date: Date, time: String): boolean`, `+addShowtime(showtime: Showtime): int`

5. Ve Entity classes:
   - Ve hinh chu nhat 3 ngan cho `Showtime`:
     - Ngan 1: `<<entity>> Showtime`
     - Ngan 2: `-id: int`, `-time: String`, `-date: Date`, `-ticketPrice: float`
     - Ngan 3: getter/setter

6. Ve cac duong ket noi:

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| ScheduleShowtimeFrm | MovieDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung MovieDAO |
| ScheduleShowtimeFrm | ScreenRoomDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung ScreenRoomDAO |
| ScheduleShowtimeFrm | ShowtimeDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung ShowtimeDAO |
| SeatPricingFrm | SeatDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung SeatDAO |
| SeatPricingFrm | SeatPricingDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung SeatPricingDAO |
| Cinema → ScreenRoom | Duong lien net, dau kim cuong filled | Composition 1-n | ScreenRoom khong ton tai neu khong co Cinema |
| ScreenRoom → Seat | Duong lien net, dau kim cuong filled | Composition 1-n | Seat khong ton tai neu khong co ScreenRoom |
| Showtime → SeatPricing | Duong lien net, dau kim cuong filled | Composition 1-n | SeatPricing khong ton tai neu khong co Showtime |
| Movie → Showtime | Duong lien net, mui tam giac rong | Association 1-n | Movie tham chieu den Showtime |
| ScreenRoom → Showtime | Duong lien net, mui tam giac rong | Association 1-n | ScreenRoom tham chieu den Showtime |
| Seat → SeatPricing | Duong lien net, mui tam giac rong | Association 1-n | Seat tham chieu den SeatPricing |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `ScheduleShowtimeFrm`, `SeatPricingFrm`
   - Control: `UserDAO`, `MovieDAO`, `ScreenRoomDAO`, `ShowtimeDAO`, `SeatDAO`, `SeatPricingDAO`

3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Quan ly lich chieu (Scenario version 3)

```
Staff     LoginFrm   UserDAO   HomeFrm   ScheduleShowtimeFrm  MovieDAO  ScreenRoomDAO  ShowtimeDAO  SeatPricingFrm  SeatDAO  SeatPricingDAO
  |          |          |         |              |                |            |              |             |            |            |
  |--login-->|          |         |              |                |            |              |             |            |            |
  |          |--checkL->|         |              |                |            |              |             |            |            |
  |          |<-true----|         |              |                |            |              |             |            |            |
  |          |--open----|-------->|              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |--select--|--------------------->            |                |            |              |             |            |            |
  |          |          |         |--open------->|                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |--getAllMovies()->|            |              |             |            |            |
  |          |          |         |              |<-List<Movie>----|            |              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |--getAllRooms()-->|----------->|              |             |            |            |
  |          |          |         |              |<-List<Room>-----|<-----------|              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |--display combos |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |--select movie, room, enter date/time/price->|                |            |              |             |            |            |
  |--click Add--------->|         |              |                |            |              |             |            |            |
  |          |          |         |              |--checkConflict()|------------|------------->|             |            |            |
  |          |          |         |              |                |            |              |--query DB   |            |            |
  |          |          |         |              |                |            |              |<-false------|            |            |
  |          |          |         |              |<-false(trong)---|------------|--------------|             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |--addShowtime()--|------------|------------->|             |            |            |
  |          |          |         |              |                |            |              |--INSERT DB  |            |            |
  |          |          |         |              |                |            |              |<-return id--|            |            |
  |          |          |         |              |<-showtimeId-----|------------|--------------|             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |---open----------|-------------------------------------->|            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |    SeatPricingFrm       |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |--getSeats()->|            |
  |          |          |         |              |                |            |              |             |<-List<Seat>--|            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |--display    |            |
  |          |          |         |              |                |            |              |             | table 80k   |            |
  |          |          |         |              |                |            |              |             |            |            |
  |--edit A1,A2,A3=120k|         |              |                |            |              |             |            |            |
  |--click Confirm----->|         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |--addPricing|----------->|
  |          |          |         |              |                |            |              |             |            |--INSERT DB |
  |          |          |         |              |                |            |              |             |            |<-true------|
  |          |          |         |              |                |            |              |             |<-true-------|            |
  |          |          |         |              |                |            |              |             |            |            |
  |          |          |         |              |                |            |              |             |--show success           |
  |<--success|          |         |              |                |            |              |             |            |            |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Schedule | Staff | HomeFrm | Staff chon chuc nang Schedule showing |
| 7 | open | HomeFrm | ScheduleShowtimeFrm | Mo giao dien quan ly lich chieu |
| 8 | getAllMovies() | ScheduleShowtimeFrm | MovieDAO | Goi MovieDAO.getAllMovies() |
| 9 | query DB | MovieDAO | Database | Truy van tblMovie |
| 10 | return List<Movie> | MovieDAO | ScheduleShowtimeFrm | Tra ve danh sach phim |
| 11 | getAllScreenRooms() | ScheduleShowtimeFrm | ScreenRoomDAO | Goi ScreenRoomDAO.getAllScreenRooms() |
| 12 | query DB | ScreenRoomDAO | Database | Truy van tblScreenRoom |
| 13 | return List<ScreenRoom> | ScreenRoomDAO | ScheduleShowtimeFrm | Tra ve danh sach phong |
| 14 | display combos | ScheduleShowtimeFrm | UI | Hien thi combobox phim va phong |
| 15 | select + enter | Staff | ScheduleShowtimeFrm | Staff chon phim, phong, nhap ngay, gio, gia |
| 16 | click Add | Staff | ScheduleShowtimeFrm | Staff nhan nut Add |
| 17 | checkConflict() | ScheduleShowtimeFrm | ShowtimeDAO | Kiem tra trung lich phong |
| 18 | query DB | ShowtimeDAO | Database | Truy van tblShowtime |
| 19 | return false | ShowtimeDAO | ScheduleShowtimeFrm | Phong trong, khong trung |
| 20 | addShowtime() | ScheduleShowtimeFrm | ShowtimeDAO | Them suat chieu moi |
| 21 | INSERT DB | ShowtimeDAO | Database | INSERT INTO tblShowtime |
| 22 | return showtimeId | ShowtimeDAO | ScheduleShowtimeFrm | Tra ve ID suat chieu vua tao |
| 23 | open | ScheduleShowtimeFrm | SeatPricingFrm | Mo giao dien gia ve tung ghe |
| 24 | getSeatsByRoom() | SeatPricingFrm | SeatDAO | Lay danh sach ghe cua phong |
| 25 | query DB | SeatDAO | Database | Truy van tblSeat |
| 26 | return List<Seat> | SeatDAO | SeatPricingFrm | Tra ve danh sach ghe |
| 27 | display table | SeatPricingFrm | UI | Hien thi bang gia ghe (mac dinh 80k) |
| 28 | edit VIP seats | Staff | SeatPricingFrm | Staff sua gia ghe A1, A2, A3 thanh 120k |
| 29 | click Confirm | Staff | SeatPricingFrm | Staff nhan nut Confirm |
| 30 | addSeatPricing() | SeatPricingFrm | SeatPricingDAO | Luu gia tung ghe (loop moi ghe) |
| 31 | INSERT DB | SeatPricingDAO | Database | INSERT INTO tblSeatPricing |
| 32 | return true | SeatPricingDAO | SeatPricingFrm | Tra ve true |
| 33 | show success | SeatPricingFrm | UI | Hien thi "Them lich chieu thanh cong" |
| 34 | return | SeatPricingFrm | ScheduleShowtimeFrm | Quay ve giao dien quan ly |

---

## Cau 5: Viet testcase blackbox chuan

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Schedule showing | Them suat chieu thanh cong voi phim, phong hop le, khong trung lich |
| 2 | Schedule showing | Phong da co suat chieu trung gio/ngay |
| 3 | Schedule showing | Gia ve mac dinh khong hop le (<= 0) |
| 4 | Schedule showing | Them gia rieng cho ghe VIP thanh cong |

### Testcase chi tiet — TC01: Them suat chieu thanh cong

**Muc dich:** Kiem tra chuc nang them lich chieu hoat dong dung khi phim ton tai, phong chieu trong, va gia ve hop le.

**Database truoc khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblCinema:**
| ID | code | name | address | intro |
|----|------|------|---------|-------|
| 1 | C01 | CGV Vincom | Ha Noi | Rap chieu phim CGV |

**tblScreenRoom:**
| ID | code | numSeats | characteristics | cinemaID |
|----|------|----------|-----------------|----------|
| 1 | P01 | 120 | IMAX | 1 |
| 2 | P02 | 80 | Standard | 1 |

**tblSeat:**
| ID | seatNumber | seatType | screenRoomID |
|----|------------|----------|--------------|
| 1 | A1 | VIP | 1 |
| 2 | A2 | VIP | 1 |
| 3 | A3 | VIP | 1 |
| 4 | B1 | Standard | 1 |
| 5 | B2 | Standard | 1 |
| ... | ... | ... | 1 |

**tblMovie:**
| ID | code | title | type | year | description |
|----|------|-------|------|------|-------------|
| 1 | M01 | Avengers: Endgame | Action | 2019 | Phim sieu anh hung |
| 2 | M02 | Spider-Man: No Way Home | Action | 2021 | Phim sieu anh hung |

**tblShowtime:** (rong)

**tblSeatPricing:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Sell tickets, Schedule showing, Selling food, Revenue Statistics |
| 3 | Chon chuc nang Schedule showing | Giao dien quan ly lich chieu xuat hien voi combobox phim, combobox phong, o ngay, o gio, o gia, nut Add |
| 4 | Chon phim "Avengers: Endgame" tu combobox | Combobox phim hien thi "Avengers: Endgame" |
| 5 | Chon phong "P01 - IMAX" tu combobox, nhap ngay `15/07/2026`, gio `19:00`, gia `80000` | Cac o duoc dien day du |
| 6 | Nhan nut Add | He thong kiem tra P01 luc 19:00 ngay 15/07 chua co suat chieu. Giao dien gia ve tung ghe xuat hien: bang gom cot So ghe, Gia ve. Tat ca ghe = 80,000d |
| 7 | Sua ghe A1, A2, A3 thanh 120,000d | Bang cap nhat: A1=120,000d, A2=120,000d, A3=120,000d, cac ghe con lai=80,000d |
| 8 | Nhan nut Confirm | He thong thong bao "Them lich chieu thanh cong". Bang gia ghe bien mat, quay ve giao dien quan ly lich chieu |

### Database sau khi test

**tblShowtime:** (them 1 dong)
| ID | time | date | ticketPrice | movieID | screenRoomID | userID |
|----|------|------|-------------|---------|--------------|--------|
| 1 | 19:00 | 15/07/2026 | 80000 | 1 | 1 | 1 |

**tblSeatPricing:** (them N dong, moi ghe 1 dong)
| ID | price | showtimeID | seatID |
|----|-------|------------|--------|
| 1 | 120000 | 1 | 1 |
| 2 | 120000 | 1 | 2 |
| 3 | 120000 | 1 | 3 |
| 4 | 80000 | 1 | 4 |
| 5 | 80000 | 1 | 5 |
| ... | 80000 | 1 | ... |

**tblUser:** (khong thay doi)

**tblCinema:** (khong thay doi)

**tblScreenRoom:** (khong thay doi)

**tblSeat:** (khong thay doi)

**tblMovie:** (khong thay doi)
