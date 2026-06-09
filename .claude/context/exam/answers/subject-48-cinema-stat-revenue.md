# Subject No. 48 — Cinema Chain — Module "Revenue Statistics"

> **Domain:** Cinema Chain Management

---

## Cau 1: Scenario — Thong ke doanh thu theo phim

### Mo ta he thong bang ngon ngu tu nhien

He thong thong ke doanh thu chuoi rap chieu phim. Nhan vien chon thong ke theo phim hoac theo rap, nhap khoang thoi gian. He thong hien thi danh sach phim/rap voi ma, ten, tong ve ban, tong doanh thu, sap xep giam dan theo doanh thu. Nhan vien click vao mot phim de xem chi tung tung suat chieu: gio chieu, so ve ban, tong thu nhap, sap xep tu cu den moi. Nhan vien click vao mot suat de xem danh sach hoa don: ma HD, ten KH, tong ve, tong tien.

### Scenario chinh

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Sell tickets, Schedule showing, Selling food, Revenue Statistics. |
| 4 | Staff chon chuc nang **Revenue Statistics**. |
| 5 | Giao dien thong ke xuat hien voi: combobox chon kieu thong ke (Theo phim / Theo rap), o nhap ngay bat dau, o nhap ngay ket thuc, nut View. |
| 6 | Staff chon "Theo phim" tu combobox. Nhap ngay bat dau `01/01/2026`, ngay ket thuc `31/12/2026`. |
| 7 | Staff nhan nut **View**. |
| 8 | He thong hien thi danh sach phim: cot Ma phim, Ten phim, Tong ve ban, Tong doanh thu. Sap xep giam dan theo tong doanh thu. |
| 9 | Staff thay: Avengers (500 ve, 40,000,000d), Spider-Man (300 ve, 24,000,000d), Batman (150 ve, 10,500,000d). |
| 10 | Staff click vao dong "Avengers". |
| 11 | He thong hien thi chi tiet tung suat chieu cua phim Avengers: cot Gio chieu, So ve ban, Tong thu nhap. Sap xep tu suat cu den suat moi. |
| 12 | Staff thay: 19:00 15/07 (200 ve, 16,000,000d), 21:00 15/07 (150 ve, 12,000,000d), 14:00 20/07 (150 ve, 12,000,000d). |
| 13 | Staff click vao suat "19:00 15/07/2026". |
| 14 | He thong hien thi danh sach hoa don cua suat do: cot Ma HD, Ten KH, Tong ve, Tong tien. Sap xep theo thoi gian thanh toan. |
| 15 | Staff thay: HD001 (Nguyen Van A, 3 ve, 240,000d), HD002 (Tran Thi B, 2 ve, 160,000d), HD003 (Le Van C, 5 ve, 400,000d). |

### Kich ban ngoai le

- **EL1:** Khong co du lieu trong khoang thoi gian → He thong hien thi bang trong, thong bao "Khong co du lieu thong ke".
- **EL2:** Ngay bat dau > ngay ket thuc → He thong thong bao "Thoi gian khong hop le".
- **EL3:** Nhap sai dinh dang ngay → He thong thong bao "Dinh dang ngay khong hop le".

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly chuoi rap chieu phim. Moi rap chieu phim (ma rap, ten rap, dia chi, gioi thieu) co nhieu phong chieu (ma phong, so ghe, dac diem phong). Moi phim (ma phim, ten phim, the loai, nam san xuat, mo ta) duoc chieu tai nhieu phong, nhieu khung gio. Moi suat chieu lien ket mot phim voi mot phong tai mot thoi diem. Khach hang mua ve xem phim, moi ve lien ket voi mot ghe cu the trong suat chieu. Hoa don chua nhieu ve, ghi thong tin khach hang va tong tien. Nguoi dung (staff) dang nhap he thong de xem thong ke doanh thu.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Rap chieu phim (Cinema) | Entity class | Doi tuong quan ly — chuoi rap |
| Phong chieu (ScreenRoom) | Entity class | Thuoc mot rap, chua nhieu ghe |
| Phim (Movie) | Entity class | Doi tuong duoc chieu, thong ke theo phim |
| Suat chieu (Showtime) | Entity class | Lien ket phim voi phong, co doanh thu |
| Ve (Ticket) | Entity class | Ban cho khach, lien ket suat chieu voi ghe |
| Hoa don (Invoice) | Entity class | Chua nhieu ve, ghi tong tien |
| Nguoi dung (User) | Entity class | Nhan vien xem thong ke |
| Ma rap, ten rap, dia chi | Thuoc tinh | Thuoc tinh cua Cinema |
| Ma phong, so ghe, dac diem | Thuoc tinh | Thuoc tinh cua ScreenRoom |
| Ma phim, ten phim, the loai | Thuoc tinh | Thuoc tinh cua Movie |
| Ngay chieu, gio chieu, gia ve | Thuoc tinh | Thuoc tinh cua Showtime |
| Gia ve | Thuoc tinh | Thuoc tinh cua Ticket |
| Ngay ban, tong tien, ten KH | Thuoc tinh | Thuoc tinh cua Invoice |

### Buoc 3: Xac dinh quan he

```
Cinema 1 --- n ScreenRoom
```
- Mot rap co nhieu phong chieu.
- Moi phong chieu thuoc mot rap.

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
Showtime 1 --- n Ticket
```
- Mot suat chieu ban nhieu ve.
- Moi ve thuoc mot suat chieu.

```
Invoice 1 --- n Ticket
```
- Mot hoa don chua nhieu ve.
- Moi ve thuoc mot hoa don.

```
User 1 --- n Invoice
```
- Mot nhan vien tao nhieu hoa don.
- Moi hoa don duoc tao boi mot nhan vien.

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
                            |                  |
                            |                  |
                            +------------------+

+------------------+       +------------------+
|     Movie        |       |    Showtime      |
+------------------+       +------------------+
| -code: String    |       | -time: String    |
| -title: String   |       | -date: Date      |
| -type: String    |       | -ticketPrice: float|
| -year: int       |       +------------------+
| -description: String|           | 1
+------------------+              |
         | 1                      | n
         |                        v
         | n               +------------------+
         +---------------->|     Ticket       |
                            +------------------+
                            | -price: float    |
                            +------------------+
                                    | n
                                    |
                                    | 1
                                    v
                            +------------------+
                            |     Invoice      |
                            +------------------+
                            | -invoiceDate: Date|
                            | -customerName: String|
                            | -totalAmount: float|
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
| Cinema → ScreenRoom | 1-n (Composition) | Mot rap co nhieu phong |
| Movie → Showtime | 1-n (Association) | Mot phim co nhieu suat chieu |
| ScreenRoom → Showtime | 1-n (Association) | Mot phong co nhieu suat chieu |
| Showtime → Ticket | 1-n (Association) | Mot suat chieu ban nhieu ve |
| Invoice → Ticket | 1-n (Composition) | Hoa don chua nhieu ve |
| User → Invoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don |

### Classes diagram (analysis)

Phan tich module nay (bo qua buoc dang nhap):

Sau khi dang nhap thanh cong -> Giao dien Home xuat hien:
- mot lua chon thong ke doanh thu -> subRevenueStatistics

Chon thong ke doanh thu -> Giao dien thong ke xuat hien:
- combobox chon kieu thong ke (Theo phim / Theo rap) -> inStatType
- o nhap ngay bat dau -> inStartDate
- o nhap ngay ket thuc -> inEndDate
- nut View -> subView
- bang phim/rap (click duoc) -> outsubMovieTable
- bang suat chieu (click duoc) -> outsubShowtimeTable
- bang hoa don -> outInvoiceTable

Chon kieu, nhap ngay, nhan View -> he thong thong ke theo phim -> can phuong thuc:
- ten: getMovieRevenue()
- dau vao: startDate, endDate
- dau ra: danh sach MovieRevenue
- gan cho entity class: Movie.

Neu chon theo rap -> can phuong thuc:
- ten: getCinemaRevenue()
- dau vao: startDate, endDate
- dau ra: danh sach CinemaRevenue
- gan cho entity class: Cinema.

Click vao phim -> he thong lay chi tiet suat chieu -> can phuong thuc:
- ten: getShowtimeRevenue()
- dau vao: movieId, startDate, endDate
- dau ra: danh sach ShowtimeRevenue
- gan cho entity class: Showtime.

Click vao suat chieu -> he thong lay danh sach hoa don -> can phuong thuc:
- ten: getInvoicesByShowtime()
- dau vao: showtimeId
- dau ra: danh sach Invoice
- gan cho entity class: Invoice.

### Tom tat
View classes: HomeFrm, RevenueStatFrm
Methods: getMovieRevenue(), getCinemaRevenue(), getShowtimeRevenue(), getInvoicesByShowtime()

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| RevenueStatFrm | Giao dien thong ke doanh thu (chinh) |
| ShowtimeDetailFrm | Giao dien chi tiet suat chieu |
| InvoiceListFrm | Giao dien danh sach hoa don |

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

**RevenueStatFrm:**
- `inStatType`: combobox chon kieu thong ke (Theo phim / Theo rap)
- `inStartDate`: o nhap ngay bat dau
- `inEndDate`: o nhap ngay ket thuc
- `subView`: nut View
- `outsubMovieTable`: bang phim/rap (click duoc): ma, ten, tong ve, tong doanh thu
- `outsubShowtimeTable`: bang suat chieu (click duoc): gio chieu, so ve, tong thu nhap
- `outInvoiceTable`: bang hoa don: ma HD, ten KH, tong ve, tong tien

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| MovieDAO | `getMovieRevenue(startDate, endDate): List<MovieRevenue>` | Thong ke doanh thu theo phim |
| CinemaDAO | `getCinemaRevenue(startDate, endDate): List<CinemaRevenue>` | Thong ke doanh thu theo rap |
| ShowtimeDAO | `getShowtimeRevenue(movieId, startDate, endDate): List<ShowtimeRevenue>` | Chi tiet doanh thu tung suat |
| InvoiceDAO | `getInvoicesByShowtime(showtimeId): List<Invoice>` | Danh sach hoa don theo suat |

**DTO classes:**
- **MovieRevenue:** movie (Movie), totalTickets (int), totalRevenue (float)
- **CinemaRevenue:** cinema (Cinema), totalTickets (int), totalRevenue (float)
- **ShowtimeRevenue:** showtime (Showtime), totalTickets (int), totalIncome (float)

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

**Ticket:**
- `id: int` (PK)
- `price: float`
- `showtimeId: int` (FK → Showtime)
- `invoiceId: int` (FK → Invoice)

**Invoice:**
- `id: int` (PK)
- `invoiceDate: Date`
- `customerName: String`
- `totalAmount: float`
- `userId: int` (FK → User)

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

**tblTicket:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| price | float | |
| showtimeID | int | FK → tblShowtime.ID |
| invoiceID | int | FK → tblInvoice.ID |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| invoiceDate | date | |
| customerName | varchar | |
| totalAmount | float | |
| userID | int | FK → tblUser.ID |

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
   - View classes: `LoginFrm`, `HomeFrm`, `RevenueStatFrm`
   - DAO classes: `UserDAO`, `MovieDAO`, `CinemaDAO`, `ShowtimeDAO`, `InvoiceDAO`
   - Entity classes: `Cinema`, `ScreenRoom`, `Movie`, `Showtime`, `Ticket`, `Invoice`, `User`

3. Ve View classes (Form):
   - Ve hinh chu nhat 3 ngan cho `RevenueStatFrm`:
     - Ngan 1 (ten): `<<boundary>> RevenueStatFrm`
     - Ngan 2 (thuoc tinh): `-inStatType: JComboBox`, `-inStartDate: JTextField`, `-inEndDate: JTextField`, `-subView: JButton`, `-outsubMovieTable: JTable`, `-outsubShowtimeTable: JTable`, `-outInvoiceTable: JTable`
     - Ngan 3 (phuong thuc): khong co

4. Ve DAO classes:
   - Ve hinh chu nhat 3 ngan cho `MovieDAO`:
     - Ngan 1: `<<control>> MovieDAO`
     - Ngan 2: khong co thuoc tinh
     - Ngan 3: `+getMovieRevenue(startDate: Date, endDate: Date): List<MovieRevenue>`
   - Tuong tu cho `ShowtimeDAO`: `+getShowtimeRevenue(movieId: int, startDate: Date, endDate: Date): List<ShowtimeRevenue>`
   - Tuong tu cho `InvoiceDAO`: `+getInvoicesByShowtime(showtimeId: int): List<Invoice>`

5. Ve Entity classes:
   - Ve hinh chu nhat 3 ngan cho `Invoice`:
     - Ngan 1: `<<entity>> Invoice`
     - Ngan 2: `-id: int`, `-invoiceDate: Date`, `-customerName: String`, `-totalAmount: float`
     - Ngan 3: getter/setter

6. Ve cac duong ket noi:

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| RevenueStatFrm | MovieDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung MovieDAO |
| RevenueStatFrm | ShowtimeDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung ShowtimeDAO |
| RevenueStatFrm | InvoiceDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung InvoiceDAO |
| Cinema → ScreenRoom | Duong lien net, dau kim cuong filled | Composition 1-n | ScreenRoom khong ton tai neu khong co Cinema |
| Invoice → Ticket | Duong lien net, dau kim cuong filled | Composition 1-n | Ticket khong ton tai neu khong co Invoice |
| Movie → Showtime | Duong lien net, mui tam giac rong | Association 1-n | Showtime tham chieu den Movie |
| ScreenRoom → Showtime | Duong lien net, mui tam giac rong | Association 1-n | Showtime tham chieu den ScreenRoom |
| Showtime → Ticket | Duong lien net, mui tam giac rong | Association 1-n | Ticket tham chieu den Showtime |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `RevenueStatFrm`
   - Control: `UserDAO`, `MovieDAO`, `ShowtimeDAO`, `InvoiceDAO`

3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Thong ke doanh thu (Scenario version 3)

```
Staff       LoginFrm   UserDAO   HomeFrm   RevenueStatFrm   MovieDAO   ShowtimeDAO   InvoiceDAO
  |             |          |         |            |              |            |             |
  |--login----->|          |         |            |              |            |             |
  |             |--checkL->|         |            |              |            |             |
  |             |<-true----|         |            |              |            |             |
  |             |--open----|-------->|            |              |            |             |
  |             |          |         |            |              |            |             |
  |--select-----|--------------------->          |              |            |             |
  |             |          |         |--open----->|              |            |             |
  |             |          |         |            |              |            |             |
  |--select "Theo phim"-->|         |            |              |            |             |
  |--enter dates--------->|         |            |              |            |             |
  |--click View|          |         |            |              |            |             |
  |             |          |         |            |              |            |             |
  |             |          |         |            |--getMovieRevenue()------->|             |
  |             |          |         |            |              |            |--query DB  |
  |             |          |         |            |              |            |<-return----|
  |             |          |         |            |<-List<MovieRevenue>-------|             |
  |             |          |         |            |              |            |             |
  |             |          |         |            |--display movie table      |             |
  |             |          |         |            | (sorted by revenue desc) |             |
  |             |          |         |            |              |            |             |
  |--click "Avengers"---->|         |            |              |            |             |
  |             |          |         |            |              |            |             |
  |             |          |         |            |--getShowtimeRevenue()---->|             |
  |             |          |         |            |              |            |--query DB  |
  |             |          |         |            |              |            |<-return----|
  |             |          |         |            |<-List<ShowtimeRevenue>----|             |
  |             |          |         |            |              |            |             |
  |             |          |         |            |--display showtime table   |             |
  |             |          |         |            | (sorted old to new)      |             |
  |             |          |         |            |              |            |             |
  |--click "19:00 15/07"->|         |            |              |            |             |
  |             |          |         |            |              |            |             |
  |             |          |         |            |--getInvoicesByShowtime()--|------------>|
  |             |          |         |            |              |            |             |--query DB
  |             |          |         |            |              |            |             |<-return---
  |             |          |         |            |              |            |<-List<Inv>--|
  |             |          |         |            |<-List<Invoice>|------------|-------------|
  |             |          |         |            |              |            |             |
  |             |          |         |            |--display invoice table    |             |
  |             |          |         |            | (sorted by payment time) |             |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Revenue Statistics | Staff | HomeFrm | Staff chon chuc nang Revenue Statistics |
| 7 | open | HomeFrm | RevenueStatFrm | Mo giao dien thong ke |
| 8 | selectStatType("Theo phim") | Staff | RevenueStatFrm | Staff chon kieu thong ke |
| 9 | enterDates(01/01/2026, 31/12/2026) | Staff | RevenueStatFrm | Staff nhap khoang thoi gian |
| 10 | clickView() | Staff | RevenueStatFrm | Staff nhan nut View |
| 11 | getMovieRevenue(startDate, endDate) | RevenueStatFrm | MovieDAO | Goi MovieDAO.getMovieRevenue() |
| 12 | query DB | MovieDAO | Database | Truy van tblMovie JOIN tblShowtime JOIN tblTicket |
| 13 | return List<MovieRevenue> | MovieDAO | RevenueStatFrm | Tra ve danh sach phim voi doanh thu |
| 14 | display movie table | RevenueStatFrm | UI | Hien thi bang phim, sap xep giam dan doanh thu |
| 15 | clickMovie("Avengers") | Staff | RevenueStatFrm | Staff click vao phim Avengers |
| 16 | getShowtimeRevenue(movieId, dates) | RevenueStatFrm | ShowtimeDAO | Goi ShowtimeDAO.getShowtimeRevenue() |
| 17 | query DB | ShowtimeDAO | Database | Truy van tblShowtime JOIN tblTicket |
| 18 | return List<ShowtimeRevenue> | ShowtimeDAO | RevenueStatFrm | Tra ve danh sach suat chieu voi doanh thu |
| 19 | display showtime table | RevenueStatFrm | UI | Hien thi bang suat chieu, sap xep cu → moi |
| 20 | clickShowtime("19:00 15/07") | Staff | RevenueStatFrm | Staff click vao suat chieu |
| 21 | getInvoicesByShowtime(showtimeId) | RevenueStatFrm | InvoiceDAO | Goi InvoiceDAO.getInvoicesByShowtime() |
| 22 | query DB | InvoiceDAO | Database | Truy van tblInvoice JOIN tblTicket |
| 23 | return List<Invoice> | InvoiceDAO | RevenueStatFrm | Tra ve danh sach hoa don |
| 24 | display invoice table | RevenueStatFrm | UI | Hien thi bang hoa don, sap xep theo thoi gian |

---

## Cau 5: Viet testcase blackbox chuan

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Revenue Statistics | Thong ke doanh thu theo phim voi du lieu ton tai |
| 2 | Revenue Statistics | Thong ke doanh thu theo rap voi du lieu ton tai |
| 3 | Revenue Statistics | Khong co du lieu trong khoang thoi gian |
| 4 | Revenue Statistics | Ngay bat dau > ngay ket thuc |
| 5 | Revenue Statistics | Xem chi tiet suat chieu va hoa don |

### Testcase chi tiet — TC01: Thong ke doanh thu theo phim voi du lieu ton tai

**Muc dich:** Kiem tra chuc nang thong ke doanh thu theo phim hoat dong dung khi co du lieu trong khoang thoi gian, bao gom drill-down den chi tiet suat chieu va hoa don.

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

**tblMovie:**
| ID | code | title | type | year | description |
|----|------|-------|------|------|-------------|
| 1 | M01 | Avengers: Endgame | Action | 2019 | Phim sieu anh hung |
| 2 | M02 | Spider-Man: No Way Home | Action | 2021 | Phim sieu anh hung |

**tblShowtime:**
| ID | time | date | ticketPrice | movieID | screenRoomID |
|----|------|------|-------------|---------|--------------|
| 1 | 19:00 | 15/07/2026 | 80000 | 1 | 1 |
| 2 | 21:00 | 15/07/2026 | 80000 | 1 | 1 |
| 3 | 14:00 | 20/07/2026 | 70000 | 2 | 2 |

**tblInvoice:**
| ID | invoiceDate | customerName | totalAmount | userID |
|----|-------------|--------------|-------------|--------|
| 1 | 15/07/2026 | Nguyen Van A | 240000 | 1 |
| 2 | 15/07/2026 | Tran Thi B | 160000 | 1 |
| 3 | 15/07/2026 | Le Van C | 400000 | 1 |
| 4 | 15/07/2026 | Pham Thi D | 320000 | 1 |
| 5 | 20/07/2026 | Hoang Van E | 210000 | 1 |

**tblTicket:**
| ID | price | showtimeID | invoiceID |
|----|-------|------------|-----------|
| 1 | 80000 | 1 | 1 |
| 2 | 80000 | 1 | 1 |
| 3 | 80000 | 1 | 1 |
| 4 | 80000 | 1 | 2 |
| 5 | 80000 | 1 | 2 |
| 6 | 80000 | 2 | 3 |
| 7 | 80000 | 2 | 3 |
| 8 | 80000 | 2 | 3 |
| 9 | 80000 | 2 | 3 |
| 10 | 80000 | 2 | 3 |
| 11 | 80000 | 2 | 4 |
| 12 | 80000 | 2 | 4 |
| 13 | 80000 | 2 | 4 |
| 14 | 80000 | 2 | 4 |
| 15 | 70000 | 3 | 5 |
| 16 | 70000 | 3 | 5 |
| 17 | 70000 | 3 | 5 |

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang |
| 3 | Chon chuc nang Revenue Statistics | Giao dien thong ke xuat hien voi combobox kieu thong ke, o ngay bat dau, o ngay ket thuc, nut View |
| 4 | Chon "Theo phim", nhap ngay bat dau `01/01/2026`, ngay ket thuc `31/12/2026`, nhan View | Bang phim hien thi: Avengers (M01, 10 ve, 800,000d), Spider-Man (M02, 3 ve, 210,000d). Sap xep giam dan theo doanh thu |
| 5 | Click vao dong "Avengers" | Bang chi tiet suat chieu hien thi: 19:00 15/07 (5 ve, 400,000d), 21:00 15/07 (5 ve, 400,000d). Sap xep tu cu den moi |
| 6 | Click vao suat "19:00 15/07/2026" | Bang hoa don hien thi: HD001 (Nguyen Van A, 3 ve, 240,000d), HD002 (Tran Thi B, 2 ve, 160,000d). Sap xep theo thoi gian thanh toan |
| 7 | Nhan nut Back de quay lai bang phim | Quay lai bang thong ke phim, giu nguyen ket qua: Avengers (10 ve, 800,000d), Spider-Man (3 ve, 210,000d) |

### Database sau khi test

**Khong thay doi** — day la chuc nang chi doc (read-only), khong thay doi du lieu trong database.

**tblUser:** (khong thay doi)

**tblCinema:** (khong thay doi)

**tblScreenRoom:** (khong thay doi)

**tblMovie:** (khong thay doi)

**tblShowtime:** (khong thay doi)

**tblInvoice:** (khong thay doi)

**tblTicket:** (khong thay doi)
