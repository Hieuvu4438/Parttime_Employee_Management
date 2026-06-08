# Subject No. 13 — Tour Booking — Module "Tour statistics by revenue"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Viet scenario chuan cho module (1.5 diem)

### Scenario — Thong ke tour theo doanh thu

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien quan ly (Manager) dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Manager nhap username `manager01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics. |
| 4 | Manager chon **Statistics** → **Tour statistics by revenue**. |
| 5 | Giao dien thong ke xuat hien voi o nhap ngay bat dau (start date), o nhap ngay ket thuc (end date), nut View. |
| 6 | Manager nhap ngay bat dau `01/01/2026` va ngay ket thuc `31/12/2026`, nhan nut View. |
| 7 | He thong hien thi danh sach tour sap xep theo tong doanh thu giam dan: ma tour, ten tour, noi khoi hanh, diem den, so khach trung binh/tour, tong doanh thu. |
| 8 | Manager click vao tour "T001 — Tour Ha Long 3 ngay". |
| 9 | He thong hien thi chi tiet danh sach hoa don khach hang da dat tour nay: ma KH, ten KH, ngay khoi hanh, gio khoi hanh, tong so khach, tong tien. |
| 10 | Manager xem chi tiet va nhan nut **Back** de quay lai bang thong ke. |
| 11 | He thong quay lai bang thong ke tour. |

### Kich ban ngoai le

- **EL1:** Khong co du lieu trong khoang thoi gian chon → He thong hien thi bang rong voi thong bao "Khong co du lieu trong khoang thoi gian nay".
- **EL2:** Ngay bat dau lon hon ngay ket thuc → He thong thong bao "Ngay bat dau phai truoc ngay ket thuc".
- **EL3:** De trong ngay bat dau hoac ngay ket thuc → He thong thong bao "Vui long nhap day du ngay bat dau va ket thuc".

---

## Cau 2: Trich xay dung class diagram cho cac entity class lien quan (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thong ke doanh thu theo tour du lich. Quan ly chon khoang thoi gian, he thong tinh tong doanh thu cua moi tour dua tren cac hoa don (Invoice) trong khoang do. Moi tour co nhieu ngay khoi hanh (TourDeparture), moi ngay khoi hanh co the co nhieu chi tiet hoa don (InvoiceDetail). Doanh thu cua mot tour la tong cua tat ca InvoiceDetail lien ket voi cac TourDeparture cua tour do. He thong cung tinh so khach trung binh moi tour de danh gia hieu qua kinh doanh.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Tour | Entity class | Doi tuong chinh: tour du lich |
| TourDeparture | Entity class | Ngay khoi hanh cu the voi gia cu the |
| Customer | Entity class | Khach hang mua ve |
| Invoice | Entity class | Hoa don mua ve |
| InvoiceDetail | Entity class | Chi tiet hoa don, lien ket voi TourDeparture |
| User | Entity class | Nguoi dung he thong (quan ly) |
| TourStat (DTO) | Khong phai entity | Doi tuong chuyen du lieu thong ke: tour, avgGuests, totalRevenue |
| InvoiceStat (DTO) | Khong phai entity | Doi tuong chuyen du lieu chi tiet hoa don |

### Buoc 3: Xac dinh quan he

```
Tour 1 --- n TourDeparture
```
- Mot tour co nhieu ngay khoi hanh.
- Moi ngay khoi hanh thuoc ve mot tour.

```
Customer 1 --- n Invoice
```
- Mot khach hang co nhieu hoa don.

```
Invoice 1 --- n InvoiceDetail
```
- Mot hoa don co nhieu chi tiet.

```
TourDeparture 1 --- n InvoiceDetail
```
- Mot ngay khoi hanh xuat hien trong nhieu chi tiet hoa don.

```
User 1 --- n Invoice
```
- Mot nhan vien tao nhieu hoa don.

### Buoc 4: Class Diagram (Analysis)

```
+------------------+       +---------------------+
|      Tour        |       |   TourDeparture     |
+------------------+       +---------------------+
| -id: int         |       | -id: int            |
| -code: String    |       | -departureDate: Date|
| -name: String    |       | -price: float       |
| -departure: String|      | -maxGuests: int     |
| -destination: String|    +---------------------+
| -description: String|            ^
+------------------+               |
         | 1                       | n
         |                         |
         +-----------+-------------+
                     |
                     n
```

```
+------------------+       +------------------+
|    Customer      |       |     Invoice      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -invoiceDate: Date|
| -name: String    |       | -totalAmount: float|
| -idNumber: String|       | -numGuests: int  |
| -idType: String  |       | -status: String  |
| -phone: String   |       +------------------+
| -email: String   |                | 1
| -address: String |                |
+------------------+                | n
         | 1                        v
         |                 +------------------+
         | n               | InvoiceDetail    |
         +---------------->+------------------+
                           | -id: int         |
                           | -guestCount: int |
                           | -unitPrice: float|
                           | -amount: float   |
                           +------------------+

+------------------+
|      User        |
+------------------+
| -id: int         |
| -username: String|
| -password: String|
| -role: String    |
+------------------+
```

### Mo ta quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Tour → TourDeparture | 1-n (Composition) | Mot tour co nhieu ngay khoi hanh |
| Customer → Invoice | 1-n (Association) | Mot khach hang co nhieu hoa don |
| Invoice → InvoiceDetail | 1-n (Composition) | Mot hoa don co nhieu chi tiet |
| TourDeparture → InvoiceDetail | 1-n (Association) | Mot ngay khoi hanh xuat hien trong nhieu chi tiet |
| User → Invoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don |

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet (1.5 diem)

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| TourRevenueStatFrm | Giao dien thong ke doanh thu theo tour (chinh) |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subBuyTickets`: nut chon Buy tickets
- `subCancelTicket`: nut chon Cancel the ticket
- `subStatistics`: nut chon Statistics

**TourRevenueStatFrm:**
- `inStartDate`: o nhap ngay bat dau (JFormattedTextField)
- `inEndDate`: o nhap ngay ket thuc (JFormattedTextField)
- `subView`: nut View
- `outsubListTourStat`: bang thong ke tour (click duoc, hien thi ma tour, ten tour, noi khoi hanh, diem den, so khach TB/tour, tong doanh thu)
- `outListInvoice`: bang chi tiet hoa don khach hang (hien thi khi click vao 1 tour: ma KH, ten KH, ngay khoi hanh, gio khoi hanh, tong so khach, tong tien)
- `subBack`: nut Back de quay lai bang thong ke

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| TourDAO | `getTourRevenueStat(startDate, endDate): List<TourStat>` | Thong ke doanh thu theo tour |
| InvoiceDAO | `getInvoicesByTour(tourId, startDate, endDate): List<InvoiceStat>` | Lay chi tiet hoa don theo tour |

**DTO — TourStat:**
- `tour: Tour`
- `avgGuests: float`
- `totalRevenue: float`

**DTO — InvoiceStat:**
- `customerCode: String`
- `customerName: String`
- `departureDate: Date`
- `departureTime: String`
- `totalGuests: int`
- `totalAmount: float`

### Buoc 4: Xac dinh Entity class (Design phase)

**Tour:**
- `id: int` (PK)
- `code: String`
- `name: String`
- `departure: String`
- `destination: String`
- `description: String`

**TourDeparture:**
- `id: int` (PK)
- `departureDate: Date`
- `price: float`
- `maxGuests: int`
- `tour: Tour` (object attribute, FK)

**Customer:**
- `id: int` (PK)
- `code: String`
- `name: String`
- `idNumber: String`
- `idType: String`
- `phone: String`
- `email: String`
- `address: String`

**Invoice:**
- `id: int` (PK)
- `invoiceDate: Date`
- `totalAmount: float`
- `numGuests: int`
- `status: String`
- `customer: Customer` (object attribute, FK)
- `user: User` (object attribute, FK)

**InvoiceDetail:**
- `id: int` (PK)
- `guestCount: int`
- `unitPrice: float`
- `amount: float`
- `tourDeparture: TourDeparture` (object attribute, FK)
- `invoice: Invoice` (object attribute, FK)

**User:**
- `id: int` (PK)
- `username: String`
- `password: String`
- `role: String`

### Buoc 5: Database Design

**tblTour:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| departure | varchar | |
| destination | varchar | |
| description | varchar | |

**tblTourDeparture:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| departureDate | date | |
| price | float | |
| maxGuests | int | |
| tourID | int | FK → tblTour.ID |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| idNumber | varchar | |
| idType | varchar | |
| phone | varchar | |
| email | varchar | |
| address | varchar | |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| invoiceDate | date | |
| totalAmount | float | |
| numGuests | int | |
| status | varchar | |
| customerID | int | FK → tblCustomer.ID |
| userID | int | FK → tblUser.ID |

**tblInvoiceDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| guestCount | int | |
| unitPrice | float | |
| amount | float | |
| tourDepartureID | int | FK → tblTourDeparture.ID |
| invoiceID | int | FK → tblInvoice.ID |

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
2. Tao cac View class: `LoginFrm`, `HomeFrm`, `TourRevenueStatFrm`.
3. Tao cac DAO class: `UserDAO`, `TourDAO`, `InvoiceDAO`.
4. Tao cac Entity class: `Tour`, `TourDeparture`, `Customer`, `Invoice`, `InvoiceDetail`, `User`.
5. Tao cac DTO class: `TourStat`, `InvoiceStat`.

**Ve View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `TourRevenueStatFrm`:
  - Ngan 1 (ten): `<<boundary>> TourRevenueStatFrm`
  - Ngan 2 (thuoc tinh): `-inStartDate: JFormattedTextField`, `-inEndDate: JFormattedTextField`, `-subView: JButton`, `-outsubListTourStat: JTable`, `-outListInvoice: JTable`, `-subBack: JButton`
  - Ngan 3 (phuong thuc): +khong co

**Ve DAO classes:**
- Ve hinh chu nhat 3 ngan cho `TourDAO`:
  - Ngan 1: `<<control>> TourDAO`
  - Ngan 2: khong co thuoc tinh
  - Ngan 3: `+getTourRevenueStat(startDate: Date, endDate: Date): List<TourStat>`

**Ve cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| TourRevenueStatFrm | TourDAO | Duong lien net, mui tam tam giac rong | Dependency | TourRevenueStatFrm su dung TourDAO |
| TourRevenueStatFrm | InvoiceDAO | Duong lien net, mui tam tam giac rong | Dependency | TourRevenueStatFrm su dung InvoiceDAO |
| Tour | TourDeparture | Duong lien net, dau kim cuong filled | Composition 1-n | Tour chua nhieu TourDeparture |
| Invoice | InvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | Hoa don chua nhieu chi tiet |
| TourDeparture | InvoiceDetail | Duong lien net, mui tam tam giac rong | Association | TourDeparture xuat hien trong InvoiceDetail |
| Invoice | Customer | Duong lien net, mui tam tam giac rong | Association | Hoa don tham chieu den Customer |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram (1.5 diem)

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Manager` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `TourRevenueStatFrm`
   - Control: `UserDAO`, `TourDAO`, `InvoiceDAO`
3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Thong ke tour theo doanh thu

```
Manager      LoginFrm     UserDAO    HomeFrm    TourRevenueStatFrm  TourDAO       InvoiceDAO
  |            |            |          |             |                  |               |
  |--login---->|            |          |             |                  |               |
  |            |--checkLogin>|         |             |                  |               |
  |            |            |--query DB|             |                  |               |
  |            |            |<-return--|             |                  |               |
  |            |<--true-----|          |             |                  |               |
  |            |--open------|--------->|             |                  |               |
  |            |            |          |             |                  |               |
  |--select--->|------------|--------->|             |                  |               |
  |  Statistics|            |          |             |                  |               |
  |--select--->|------------|--------->|             |                  |               |
  |  Tour stat |            |          |--open------|----------------->|               |
  |            |            |          |             |                  |               |
  |--enter---->|            |          |             |                  |               |
  |  dates     |            |          |             |                  |               |
  |--click---->|            |          |             |                  |               |
  |  View      |            |          |             |                  |               |
  |            |            |          |             |--getTourRevenueStat------------>|
  |            |            |          |             |                  |---query DB    |
  |            |            |          |             |                  |<-return-------|
  |            |            |          |             |<--List<TourStat>-|               |
  |            |            |          |             |                  |               |
  |            |            |          |             |--display table   |               |
  |            |            |          |             |                  |               |
  |--click tour|            |          |             |                  |               |
  |  "T001"    |            |          |             |                  |               |
  |            |            |          |             |--getInvoicesByTour------------->|
  |            |            |          |             |                  |---query DB    |
  |            |            |          |             |                  |<-return-------|
  |            |            |          |             |<--List<InvoiceStat>|              |
  |            |            |          |             |                  |               |
  |            |            |          |             |--display detail  |               |
  |            |            |          |             |                  |               |
  |--click Back|            |          |             |                  |               |
  |            |            |          |             |--show main table |               |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Manager | LoginFrm | Manager nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("manager01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Statistics | Manager | HomeFrm | Manager chon muc Statistics |
| 7 | select Tour stat | Manager | HomeFrm | Manager chon Tour statistics by revenue |
| 8 | open | HomeFrm | TourRevenueStatFrm | Mo giao dien thong ke tour |
| 9 | enterStartDate("01/01/2026") | Manager | TourRevenueStatFrm | Manager nhap ngay bat dau |
| 10 | enterEndDate("31/12/2026") | Manager | TourRevenueStatFrm | Manager nhap ngay ket thuc |
| 11 | click View | Manager | TourRevenueStatFrm | Manager nhan nut View |
| 12 | validateDates(startDate, endDate) | TourRevenueStatFrm | TourRevenueStatFrm | Kiem tra: startDate <= endDate, khong trong |
| 13 | getTourRevenueStat() | TourRevenueStatFrm | TourDAO | Goi TourDAO.getTourRevenueStat(startDate, endDate) |
| 14 | query DB | TourDAO | Database | Truy van: SELECT tour, AVG(guests), SUM(revenue) GROUP BY tour ORDER BY revenue DESC |
| 15 | return List<TourStat> | TourDAO | TourRevenueStatFrm | Tra ve danh sach thong ke tour |
| 16 | sortResults() | TourRevenueStatFrm | TourRevenueStatFrm | Sap xep danh sach theo doanh thu giam dan |
| 17 | display table | TourRevenueStatFrm | UI | Hien thi bang thong ke tour voi cot: ma tour, ten tour, noi khoi hanh, diem den, avg khach, tong doanh thu |
| 18 | click tour "T001" | Manager | TourRevenueStatFrm | Manager click vao dong tour T001 |
| 19 | highlightRow(T001) | TourRevenueStatFrm | TourRevenueStatFrm | Highlight dong duoc chon |
| 20 | getInvoicesByTour() | TourRevenueStatFrm | InvoiceDAO | Goi InvoiceDAO.getInvoicesByTour(tourId=1, startDate, endDate) |
| 21 | query DB | InvoiceDAO | Database | Truy van: SELECT invoice JOIN invoiceDetail JOIN tourDeparture WHERE tourId AND date range |
| 22 | return List<InvoiceStat> | InvoiceDAO | TourRevenueStatFrm | Tra ve danh sach chi tiet hoa don |
| 23 | display detail | TourRevenueStatFrm | UI | Hien thi chi tiet hoa don: ma KH, ten KH, ngay khoi hanh, so khach, tong tien |
| 24 | click Back | Manager | TourRevenueStatFrm | Manager nhan nut Back de quay lai |
| 25 | show main table | TourRevenueStatFrm | UI | Quay lai bang thong ke tour, giu nguyen ket qua |

---

## Cau 5: Viet testcase blackbox chuan (1.5 diem)

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Tour stat by revenue | Thong ke thanh cong voi du lieu ton tai trong khoang thoi gian |
| 2 | Tour stat by revenue | Khong co du lieu trong khoang thoi gian chon |
| 3 | Tour stat by revenue | Ngay bat dau lon hon ngay ket thuc |
| 4 | Tour stat by revenue | De trong ngay bat dau hoac ngay ket thuc |
| 5 | Tour stat by revenue | Chi co 1 tour co doanh thu trong khoang thoi gian |

### Testcase chi tiet — TC01: Thong ke thanh cong voi du lieu ton tai

**Muc dich:** Kiem tra chuc nang thong ke tour theo doanh thu hoat dong dung khi co du lieu trong khoang thoi gian chon.

**Database truoc khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | manager01 | 123456 | manager |

**tblTour:**
| ID | code | name | departure | destination | description |
|----|------|------|-----------|-------------|-------------|
| 1 | T001 | Tour Ha Long 3 ngay | Ha Noi | Ha Long | Tham quan Vinh Ha Long |
| 2 | T002 | Tour Da Nang 2 ngay | Ha Noi | Da Nang | Tham quan Da Nang |

**tblTourDeparture:**
| ID | tourID | departureDate | price | maxGuests |
|----|--------|---------------|-------|-----------|
| 1 | 1 | 15/07/2026 | 2500000 | 30 |
| 2 | 1 | 20/07/2026 | 2500000 | 30 |
| 3 | 2 | 18/07/2026 | 2000000 | 25 |

**tblCustomer:**
| ID | code | name | idNumber | idType | phone | email | address |
|----|------|------|----------|--------|-------|-------|---------|
| 1 | C001 | Nguyen Van A | 012345678901 | CCCD | 0912345678 | a@gmail.com | Ha Noi |
| 2 | C002 | Tran Thi B | 098765432100 | CCCD | 0987654321 | b@gmail.com | HCM |

**tblInvoice:**
| ID | invoiceDate | totalAmount | numGuests | status | customerID | userID |
|----|-------------|-------------|-----------|--------|------------|--------|
| 1 | 01/06/2026 | 5000000 | 2 | active | 1 | 1 |
| 2 | 05/06/2026 | 10000000 | 4 | active | 2 | 1 |
| 3 | 03/06/2026 | 4000000 | 2 | active | 1 | 1 |

**tblInvoiceDetail:**
| ID | guestCount | unitPrice | amount | tourDepartureID | invoiceID |
|----|------------|-----------|--------|-----------------|-----------|
| 1 | 2 | 2500000 | 5000000 | 1 | 1 |
| 2 | 4 | 2500000 | 10000000 | 2 | 2 |
| 3 | 2 | 2000000 | 4000000 | 3 | 3 |

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `manager01`, password `123456`, nhan Login | Giao dien Home xuat hien |
| 3 | Chon Statistics → Tour statistics by revenue | Giao dien thong ke xuat hien voi o ngay bat dau, ngay ket thuc, nut View |
| 4 | Nhap ngay bat dau 01/01/2026, ngay ket thuc 31/12/2026, nhan View | Bang thong ke hien thi: T001 (Tour Ha Long 3 ngay, Ha Noi, Ha Long, avg 3 khach/tour, 15,000,000 VND), T002 (Tour Da Nang 2 ngay, Ha Noi, Da Nang, avg 2 khach/tour, 4,000,000 VND). Sap xep theo doanh thu giam dan |
| 5 | Click vao tour T001 | Chi tiet hoa don hien thi: C001 (Nguyen Van A, 15/07/2026, 2 khach, 5,000,000 VND), C002 (Tran Thi B, 20/07/2026, 4 khach, 10,000,000 VND) |
| 6 | Nhan nut Back | Quay lai bang thong ke tour, giu nguyen ket qua truoc do |
| 7 | Kiem tra bang thong ke van con du lieu T001 va T002 | Bang thong ke van hien thi binh thuong |

### Database sau khi test

**tblInvoice:** (khong thay doi)

**tblInvoiceDetail:** (khong thay doi)

**tblTour:** (khong thay doi)

**tblTourDeparture:** (khong thay doi)

**tblCustomer:** (khong thay doi)

**tblUser:** (khong thay doi)

> **Ghi chu:** Day la chuc nang thong ke (chi doc), khong co thay doi du lieu trong database.
