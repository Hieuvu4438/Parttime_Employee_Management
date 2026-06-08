# Subject No. 14 — Tour Booking — Module "Revenue statistics by site"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Viet scenario chuan cho module (1.5 diem)

### Scenario — Thong ke doanh thu theo diem tham quan

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien quan ly (Manager) dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Manager nhap username `manager01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics. |
| 4 | Manager chon **Statistics** → **Revenue statistics by site**. |
| 5 | Giao dien thong ke xuat hien voi o nhap ngay bat dau (start date), o nhap ngay ket thuc (end date), nut View. |
| 6 | Manager nhap ngay bat dau `01/01/2026` va ngay ket thuc `31/12/2026`, nhan nut View. |
| 7 | He thong hien thi danh sach diem tham quan sap xep theo tong doanh thu giam dan: ten diem tham quan, so tour di qua, tong so khach, tong doanh thu. |
| 8 | Manager click vao diem tham quan "Vinh Ha Long". |
| 9 | He thong hien thi chi tiet danh sach hoa don khach hang da dat tour qua diem nay: ma KH, ten KH, ngay khoi hanh, gio khoi hanh, ten tour, tong so khach, tong tien. |
| 10 | Manager xem chi tiet va nhan nut **Back** de quay lai bang thong ke. |
| 11 | He thong quay lai bang thong ke diem tham quan. |

### Kich ban ngoai le

- **EL1:** Khong co du lieu trong khoang thoi gian chon → He thong hien thi bang rong voi thong bao "Khong co du lieu trong khoang thoi gian nay".
- **EL2:** Ngay bat dau lon hon ngay ket thuc → He thong thong bao "Ngay bat dau phai truoc ngay ket thuc".
- **EL3:** De trong ngay bat dau hoac ngay ket thuc → He thong thong bao "Vui long nhap day du ngay bat dau va ket thuc".

---

## Cau 2: Trich xay dung class diagram cho cac entity class lien quan (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thong ke doanh thu theo diem tham quan. Quan ly chon khoang thoi gian, he thong tinh tong doanh thu cua moi diem tham quan dua tren cac hoa don (Invoice) trong khoang do. Moi tour di qua nhieu diem tham quan (TourSite), moi tour co nhieu ngay khoi hanh (TourDeparture). Doanh thu cua mot diem tham quan la tong cua tat ca InvoiceDetail lien ket voi cac TourDeparture cua cac tour di qua diem do. He thong cung dem so tour va tong so khach cua moi diem tham quan.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Tour | Entity class | Doi tuong tour du lich |
| TourDeparture | Entity class | Ngay khoi hanh cu the voi gia cu the |
| Site | Entity class | Diem tham quan |
| TourSite | Entity class | Bang lien ket n-n giua Tour va Site |
| Customer | Entity class | Khach hang mua ve |
| Invoice | Entity class | Hoa don mua ve |
| InvoiceDetail | Entity class | Chi tiet hoa don |
| User | Entity class | Nguoi dung he thong |
| SiteStat (DTO) | Khong phai entity | Doi tuong chuyen du lieu thong ke: site, totalTours, totalVisitors, totalRevenue |

### Buoc 3: Xac dinh quan he

```
Tour 1 --- n TourDeparture
```
- Mot tour co nhieu ngay khoi hanh.

```
Tour n --- n Site (qua TourSite)
```
- Mot tour di qua nhieu diem tham quan.
- Mot diem tham quan duoc nhieu tour di qua.

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
|      Site        |       |    TourSite      |
+------------------+       +------------------+
| -id: int         |       | -tourId: int     |
| -name: String    |       | -siteId: int     |
| -description: String|    +------------------+
+------------------+               ^
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
| Tour ↔ Site | n-n (qua TourSite) | Tour di qua nhieu diem tham quan va nguoc lai |
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
| SiteRevenueStatFrm | Giao dien thong ke doanh thu theo diem tham quan (chinh) |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subBuyTickets`: nut chon Buy tickets
- `subCancelTicket`: nut chon Cancel the ticket
- `subStatistics`: nut chon Statistics

**SiteRevenueStatFrm:**
- `inStartDate`: o nhap ngay bat dau (JFormattedTextField)
- `inEndDate`: o nhap ngay ket thuc (JFormattedTextField)
- `subView`: nut View
- `outsubListSiteStat`: bang thong ke diem tham quan (click duoc, hien thi ten diem, so tour, tong so khach, tong doanh thu)
- `outListInvoice`: bang chi tiet hoa don khach hang (hien thi khi click vao 1 diem: ma KH, ten KH, ngay khoi hanh, gio khoi hanh, ten tour, tong so khach, tong tien)
- `subBack`: nut Back de quay lai bang thong ke

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| SiteDAO | `getSiteRevenueStat(startDate, endDate): List<SiteStat>` | Thong ke doanh thu theo diem tham quan |
| InvoiceDAO | `getInvoicesBySite(siteId, startDate, endDate): List<InvoiceStat>` | Lay chi tiet hoa don theo diem tham quan |

**DTO — SiteStat:**
- `site: Site`
- `totalTours: int`
- `totalVisitors: int`
- `totalRevenue: float`

**DTO — InvoiceStat:**
- `customerCode: String`
- `customerName: String`
- `departureDate: Date`
- `departureTime: String`
- `tourName: String`
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

**Site:**
- `id: int` (PK)
- `name: String`
- `description: String`

**TourSite:**
- `tour: Tour` (FK, composite PK)
- `site: Site` (FK, composite PK)

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

**tblSite:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| name | varchar | |
| description | varchar | |

**tblTourSite:**
| Column | Type | Constraint |
|--------|------|------------|
| tourID | int | PK, FK → tblTour.ID |
| siteID | int | PK, FK → tblSite.ID |

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
2. Tao cac View class: `LoginFrm`, `HomeFrm`, `SiteRevenueStatFrm`.
3. Tao cac DAO class: `UserDAO`, `SiteDAO`, `InvoiceDAO`.
4. Tao cac Entity class: `Tour`, `TourDeparture`, `Site`, `TourSite`, `Customer`, `Invoice`, `InvoiceDetail`, `User`.
5. Tao cac DTO class: `SiteStat`, `InvoiceStat`.

**Ve View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `SiteRevenueStatFrm`:
  - Ngan 1 (ten): `<<boundary>> SiteRevenueStatFrm`
  - Ngan 2 (thuoc tinh): `-inStartDate: JFormattedTextField`, `-inEndDate: JFormattedTextField`, `-subView: JButton`, `-outsubListSiteStat: JTable`, `-outListInvoice: JTable`, `-subBack: JButton`
  - Ngan 3 (phuong thuc): +khong co

**Ve DAO classes:**
- Ve hinh chu nhat 3 ngan cho `SiteDAO`:
  - Ngan 1: `<<control>> SiteDAO`
  - Ngan 2: khong co thuoc tinh
  - Ngan 3: `+getSiteRevenueStat(startDate: Date, endDate: Date): List<SiteStat>`

**Ve cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| SiteRevenueStatFrm | SiteDAO | Duong lien net, mui tam tam giac rong | Dependency | SiteRevenueStatFrm su dung SiteDAO |
| SiteRevenueStatFrm | InvoiceDAO | Duong lien net, mui tam tam giac rong | Dependency | SiteRevenueStatFrm su dung InvoiceDAO |
| Tour | TourSite | Duong lien net, dau kim cuong rong | Aggregation | Tour tham gia nhieu TourSite |
| Site | TourSite | Duong lien net, dau kim cuong rong | Aggregation | Site tham gia nhieu TourSite |
| Tour | TourDeparture | Duong lien net, dau kim cuong filled | Composition 1-n | Tour chua nhieu TourDeparture |
| Invoice | InvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | Hoa don chua nhieu chi tiet |
| Invoice | Customer | Duong lien net, mui tam tam giac rong | Association | Hoa don tham chieu den Customer |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram (1.5 diem)

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Manager` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `SiteRevenueStatFrm`
   - Control: `UserDAO`, `SiteDAO`, `InvoiceDAO`
3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Thong ke doanh thu theo diem tham quan

```
Manager      LoginFrm     UserDAO    HomeFrm    SiteRevenueStatFrm  SiteDAO       InvoiceDAO
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
  |  Site stat |            |          |--open------|----------------->|               |
  |            |            |          |             |                  |               |
  |--enter---->|            |          |             |                  |               |
  |  dates     |            |          |             |                  |               |
  |--click---->|            |          |             |                  |               |
  |  View      |            |          |             |                  |               |
  |            |            |          |             |--getSiteRevenueStat------------>|
  |            |            |          |             |                  |---query DB    |
  |            |            |          |             |                  |<-return-------|
  |            |            |          |             |<--List<SiteStat>-|               |
  |            |            |          |             |                  |               |
  |            |            |          |             |--display table   |               |
  |            |            |          |             |                  |               |
  |--click site|            |          |             |                  |               |
  |  "Vinh HL" |            |          |             |                  |               |
  |            |            |          |             |--getInvoicesBySite------------->|
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
| 7 | select Site stat | Manager | HomeFrm | Manager chon Revenue statistics by site |
| 8 | open | HomeFrm | SiteRevenueStatFrm | Mo giao dien thong ke diem tham quan |
| 9 | enterStartDate("01/01/2026") | Manager | SiteRevenueStatFrm | Manager nhap ngay bat dau |
| 10 | enterEndDate("31/12/2026") | Manager | SiteRevenueStatFrm | Manager nhap ngay ket thuc |
| 11 | click View | Manager | SiteRevenueStatFrm | Manager nhan nut View |
| 12 | validateDates(startDate, endDate) | SiteRevenueStatFrm | SiteRevenueStatFrm | Kiem tra: startDate <= endDate, khong trong |
| 13 | getSiteRevenueStat() | SiteRevenueStatFrm | SiteDAO | Goi SiteDAO.getSiteRevenueStat(startDate, endDate) |
| 14 | query DB | SiteDAO | Database | Truy van: SELECT site, COUNT(tour), SUM(visitors), SUM(revenue) GROUP BY site ORDER BY revenue DESC |
| 15 | return List<SiteStat> | SiteDAO | SiteRevenueStatFrm | Tra ve danh sach thong ke diem tham quan |
| 16 | sortResults() | SiteRevenueStatFrm | SiteRevenueStatFrm | Sap xep danh sach theo doanh thu giam dan |
| 17 | display table | SiteRevenueStatFrm | UI | Hien thi bang thong ke: ten diem, so tour, so khach, tong doanh thu |
| 18 | click site "Vinh Ha Long" | Manager | SiteRevenueStatFrm | Manager click vao dong diem tham quan |
| 19 | highlightRow("Vinh Ha Long") | SiteRevenueStatFrm | SiteRevenueStatFrm | Highlight dong duoc chon |
| 20 | getInvoicesBySite() | SiteRevenueStatFrm | InvoiceDAO | Goi InvoiceDAO.getInvoicesBySite(siteId=1, startDate, endDate) |
| 21 | query DB | InvoiceDAO | Database | Truy van: SELECT invoice JOIN invoiceDetail JOIN tourDeparture JOIN tour JOIN tourSite WHERE siteId AND date range |
| 22 | return List<InvoiceStat> | InvoiceDAO | SiteRevenueStatFrm | Tra ve danh sach chi tiet hoa don |
| 23 | display detail | SiteRevenueStatFrm | UI | Hien thi chi tiet: ma KH, ten KH, ngay, ten tour, so khach, tong tien |
| 24 | click Back | Manager | SiteRevenueStatFrm | Manager nhan nut Back de quay lai |
| 25 | show main table | SiteRevenueStatFrm | UI | Quay lai bang thong ke diem tham quan, giu nguyen ket qua |

---

## Cau 5: Viet testcase blackbox chuan (1.5 diem)

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Site stat by revenue | Thong ke thanh cong voi du lieu ton tai trong khoang thoi gian |
| 2 | Site stat by revenue | Khong co du lieu trong khoang thoi gian chon |
| 3 | Site stat by revenue | Ngay bat dau lon hon ngay ket thuc |
| 4 | Site stat by revenue | De trong ngay bat dau hoac ngay ket thuc |
| 5 | Site stat by revenue | Diem tham quan khong co tour nao di qua trong khoang thoi gian |

### Testcase chi tiet — TC01: Thong ke thanh cong voi du lieu ton tai

**Muc dich:** Kiem tra chuc nang thong ke doanh thu theo diem tham quan hoat dong dung khi co du lieu trong khoang thoi gian chon.

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

**tblSite:**
| ID | name | description |
|----|------|-------------|
| 1 | Vinh Ha Long | Di san the gioi |
| 2 | Ngu Hanh Son | Danh thang Da Nang |

**tblTourSite:**
| tourID | siteID |
|--------|--------|
| 1 | 1 |
| 2 | 2 |

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
| 3 | Chon Statistics → Revenue statistics by site | Giao dien thong ke xuat hien voi o ngay bat dau, ngay ket thuc, nut View |
| 4 | Nhap ngay bat dau 01/01/2026, ngay ket thuc 31/12/2026, nhan View | Bang thong ke hien thi: Vinh Ha Long (1 tour, 6 khach, 15,000,000 VND), Ngu Hanh Son (1 tour, 2 khach, 4,000,000 VND). Sap xep theo doanh thu giam dan |
| 5 | Click vao "Vinh Ha Long" | Chi tiet hoa don hien thi: C001 (Nguyen Van A, 15/07/2026, Tour Ha Long 3 ngay, 2 khach, 5,000,000 VND), C002 (Tran Thi B, 20/07/2026, Tour Ha Long 3 ngay, 4 khach, 10,000,000 VND) |
| 6 | Nhan nut Back | Quay lai bang thong ke diem tham quan, giu nguyen ket qua truoc do |
| 7 | Kiem tra bang thong ke van con du lieu Vinh Ha Long va Ngu Hanh Son | Bang thong ke van hien thi binh thuong |

### Database sau khi test

**tblInvoice:** (khong thay doi)

**tblInvoiceDetail:** (khong thay doi)

**tblTour:** (khong thay doi)

**tblTourDeparture:** (khong thay doi)

**tblSite:** (khong thay doi)

**tblTourSite:** (khong thay doi)

**tblCustomer:** (khong thay doi)

**tblUser:** (khong thay doi)

> **Ghi chu:** Day la chuc nang thong ke (chi doc), khong co thay doi du lieu trong database.
