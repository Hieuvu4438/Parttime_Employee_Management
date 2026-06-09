# Subject No. 11 — Tour Booking — Module "Buy tickets"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Viet scenario chuan cho module (1.5 diem)

### Scenario — Mua ve tour

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien (Staff) dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics. |
| 4 | Staff chon chuc nang **Buy tickets**. |
| 5 | Giao dien tim kiem tour xuat hien voi o nhap ten diem den (destination), nut Search. |
| 6 | Staff nhap "Ha Long" vao o destination va nhan Search. |
| 7 | He thong hien thi danh sach tour co san: ma tour, ten tour, noi khoi hanh, diem den, mo ta, ngay khoi hanh, gia ve. |
| 8 | Staff chon tour "Tour Ha Long 3 ngay" voi ngay khoi hanh 15/07/2026, gia 2,500,000 VND/khach. |
| 9 | Giao dien hoa don xuat hien: ten tour, noi khoi hanh, diem den, ngay khoi hanh, ten dai dien doan, CMND, loai CMND, dia chi, SDT, email, so khach, gia ve. |
| 10 | Staff nhap thong tin khach hang dai dien: ten "Nguyen Van A", CMND "012345678901", loai CMND "CCCD", dia chi "Ha Noi", SDT "0912345678", email "a@gmail.com", so khach 2. |
| 11 | He thong tinh tong tien: 2 x 2,500,000 = 5,000,000 VND. Hien thi tong tien tren hoa don. |
| 12 | Staff chon thanh toan. Khach hang thanh toan tien mat 5,000,000 VND. |
| 13 | He thong luu vao database (tblInvoice, tblInvoiceDetail, tblCustomer neu moi) va in ve cho khach. |
| 14 | He thong thong bao "Mua ve thanh cong" va quay ve giao dien Home. |

### Kich ban ngoai le

- **EL1:** Ten diem den khong co tour nao → He thong thong bao "Khong tim thay tour phu hop".
- **EL2:** Tour da het cho (so khach dang ky >= so cho toi da) → He thong thong bao "Tour da het cho".
- **EL3:** Khach hang nhap so khach <= 0 → He thong thong bao "So khach khong hop le".
- **EL4:** Thong tin khach hang khong day du (thieu CMND, thieu SDT) → He thong thong bao "Vui long nhap day du thong tin".

---

## Cau 2: Trich xay dung class diagram cho cac entity class lien quan (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly dat ve tour du lich. Moi tour (ma tour, ten tour, noi khoi hanh, diem den, mo ta) co the khoi hanh vao nhieu ngay khac nhau voi gia khac nhau. Moi khach hang (ma KH, ten, CMND, loai CMND, SDT, email, dia chi) co the mua ve cho nhieu tour. Cung mot khach hang co the di cung mot tour nhieu lan (ngay khac nhau). Moi lan mua ve, mot hoa don (Invoice) duoc tao chua thong tin khach hang va tong tien. Hoa don co chi tiet hoa don (InvoiceDetail) lien ket voi ngay khoi hanh cu the (TourDeparture). Khach hang co the tra ve voi muc phat phu thuoc vao thoi diem huy.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Tour | Entity class | Doi tuong chinh: tour du lich |
| TourDeparture | Entity class | Ngay khoi hanh cu the voi gia cu the |
| Customer | Entity class | Nguoi mua ve |
| Invoice (Hoa don) | Entity class | Phieu mua ve cua mot lan giao dich |
| InvoiceDetail | Entity class | Chi tiet hoa don, lien ket Invoice voi TourDeparture |
| User | Entity class | Nhan vien thuc hien giao dich |
| Ma tour, ten tour, noi khoi hanh | Thuoc tinh | Thuoc tinh cua Tour |
| Ngay khoi hanh, gia ve | Thuoc tinh | Thuoc tinh cua TourDeparture |
| Ten KH, CMND, loai CMND, SDT | Thuoc tinh | Thuoc tinh cua Customer |
| Tong tien, ngay mua | Thuoc tinh | Thuoc tinh cua Invoice |
| So khach | Thuoc tinh | Thuoc tinh cua InvoiceDetail |

### Buoc 3: Xac dinh quan he

```
Tour 1 --- n TourDeparture
```
- Mot tour co nhieu ngay khoi hanh khac nhau.
- Moi ngay khoi hanh thuoc ve mot tour.

```
Customer 1 --- n Invoice
```
- Mot khach hang co the co nhieu hoa don.
- Moi hoa don thuoc ve mot khach hang.

```
Invoice 1 --- n InvoiceDetail
```
- Mot hoa don co nhieu chi tiet.
- Moi chi tiet thuoc ve mot hoa don.

```
TourDeparture 1 --- n InvoiceDetail
```
- Mot ngay khoi hanh co the xuat hien trong nhieu chi tiet hoa don.
- Moi chi tiet hoa don lien ket voi mot ngay khoi hanh.

```
User 1 --- n Invoice
```
- Mot nhan vien tao nhieu hoa don.
- Moi hoa don duoc tao boi mot nhan vien.

```
Customer n --- n Tour
```
- Quan he n-n, duoc tach qua Invoice va InvoiceDetail.

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
| -idType: String  |       +------------------+
| -phone: String   |                | 1
| -email: String   |                |
| -address: String |                | n
+------------------+                v
         | 1               +------------------+
         |                  | InvoiceDetail    |
         | n                +------------------+
         +----------------->| -id: int         |
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

### Classes diagram (analysis)

Analysis this module (exclude login step):

Once login successful -> HomeFrm appears:
  an option to buy tickets -> subBuyTickets

Choose Buy tickets -> SearchTourFrm appears:
  input destination -> inDestination
  a button search -> subSearch
  a table of results (clickable) -> outsubListTour

Enter destination and search -> system searches tours by destination -> need a method:
  name: searchByDestination()
  input: destination (String)
  output: list of Tour
  assign to entity class: Tour.

Select a tour -> BuyTicketFrm appears:
  display tour info -> outTourInfo
  display departure info -> outDepartureInfo
  input representative name -> inRepresentative
  input id number -> inIdNumber
  input id type -> inIdType
  input address -> inAddress
  input phone -> inPhone
  input email -> inEmail
  input number of guests -> inNumGuests
  display total amount -> outTotalAmount
  a button pay -> subPay
  display/print invoice -> outInvoice

Enter customer info and click Pay -> system finds or creates customer -> need a method:
  name: findCustomer()
  input: idNumber (String)
  output: Customer
  assign to entity class: Customer.

Click Pay -> system creates invoice -> need a method:
  name: addInvoice()
  input: invoice (Invoice)
  output: boolean
  assign to entity class: Invoice.

Click Pay -> system creates invoice detail -> need a method:
  name: addInvoiceDetail()
  input: detail (InvoiceDetail)
  output: boolean
  assign to entity class: InvoiceDetail.

### Summary
View classes: HomeFrm, SearchTourFrm, BuyTicketFrm
Methods: searchByDestination(), findCustomer(), addInvoice(), addInvoiceDetail()

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet (1.5 diem)

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| SearchTourFrm | Giao dien tim kiem tour theo diem den |
| BuyTicketFrm | Giao dien nhap thong tin va thanh toan mua ve |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subBuyTickets`: nut chon Buy tickets
- `subCancelTicket`: nut chon Cancel the ticket
- `subStatistics`: nut chon Statistics

**SearchTourFrm:**
- `inDestination`: o nhap ten diem den
- `subSearch`: nut Search
- `outsubListTour`: bang danh sach tour (click duoc, hien thi ma tour, ten tour, noi khoi hanh, diem den, ngay khoi hanh, gia ve)

**BuyTicketFrm:**
- `outTourInfo`: vung hien thi thong tin tour (ten, noi khoi hanh, diem den)
- `outDepartureInfo`: vung hien thi ngay khoi hanh, gia ve
- `inRepresentative`: o nhap ten dai dien doan
- `inIdNumber`: o nhap so CMND
- `inIdType`: combobox chon loai CMND (CCCD/CMND/Passport)
- `inAddress`: o nhap dia chi
- `inPhone`: o nhap so dien thoai
- `inEmail`: o nhap email
- `inNumGuests`: o nhap so khach
- `outTotalAmount`: vung hien thi tong tien
- `subPay`: nut thanh toan
- `outInvoice`: vung hien thi/in hoa don va ve

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| TourDAO | `searchByDestination(keyword): List<Tour>` | Tim tour theo diem den |
| TourDepartureDAO | `getDepartures(tourId): List<TourDeparture>` | Lay ngay khoi hanh cua tour |
| CustomerDAO | `findCustomer(idNumber): Customer` | Tim khach hang theo CMND |
| CustomerDAO | `addCustomer(customer): boolean` | Them khach hang moi |
| InvoiceDAO | `addInvoice(invoice): boolean` | Tao hoa don moi |
| InvoiceDetailDAO | `addInvoiceDetail(detail): boolean` | Them chi tiet hoa don |

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
- `customer: Customer` (object attribute, FK)
- `user: User` (object attribute, FK)
- `invoiceDetails: List<InvoiceDetail>` (object attribute)

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
2. Tao cac class: `LoginFrm`, `HomeFrm`, `SearchTourFrm`, `BuyTicketFrm` (View classes).
3. Tao cac DAO class: `UserDAO`, `TourDAO`, `TourDepartureDAO`, `CustomerDAO`, `InvoiceDAO`, `InvoiceDetailDAO`.
4. Tao cac Entity class: `Tour`, `TourDeparture`, `Customer`, `Invoice`, `InvoiceDetail`, `User`.

**Ve View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `SearchTourFrm`:
  - Ngan 1 (ten): `<<boundary>> SearchTourFrm`
  - Ngan 2 (thuoc tinh): `-inDestination: JTextField`, `-subSearch: JButton`, `-outsubListTour: JTable`
  - Ngan 3 (phuong thuc): +khong co trong view class
- Tuong tu cho `BuyTicketFrm`, `LoginFrm`, `HomeFrm`.

**Ve DAO classes:**
- Ve hinh chu nhat 3 ngan cho `TourDAO`:
  - Ngan 1: `<<control>> TourDAO`
  - Ngan 2: khong co thuoc tinh
  - Ngan 3: `+searchByDestination(keyword: String): List<Tour>`
- Tuong tu cho cac DAO khac.

**Ve Entity classes:**
- Ve hinh chu nhat 3 ngan cho `Invoice`:
  - Ngan 1: `<<entity>> Invoice`
  - Ngan 2: `-id: int`, `-invoiceDate: Date`, `-totalAmount: float`, `-numGuests: int`
  - Ngan 3: `+addInvoice(): boolean`

**Ve cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| SearchTourFrm | TourDAO | Duong lien net, mui tam tam giac rong | Dependency | SearchTourFrm su dung TourDAO |
| BuyTicketFrm | InvoiceDAO | Duong lien net, mui tam tam giac rong | Dependency | BuyTicketFrm su dung InvoiceDAO |
| BuyTicketFrm | CustomerDAO | Duong lien net, mui tam tam giac rong | Dependency | BuyTicketFrm su dung CustomerDAO |
| Invoice | InvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | Invoice chua nhieu InvoiceDetail |
| Invoice | Customer | Duong lien net, mui tam tam giac rong | Association | Invoice tham chieu den Customer |
| Invoice | User | Duong lien net, mui tam tam giac rong | Association | Invoice tham chieu den User |
| InvoiceDetail | TourDeparture | Duong lien net, mui tam tam giac rong | Association | InvoiceDetail tham chieu den TourDeparture |
| Tour | TourDeparture | Duong lien net, dau kim cuong filled | Composition 1-n | Tour chua nhieu TourDeparture |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram (1.5 diem)

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `SearchTourFrm`, `BuyTicketFrm`
   - Control: `UserDAO`, `TourDAO`, `TourDepartureDAO`, `CustomerDAO`, `InvoiceDAO`, `InvoiceDetailDAO`
3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Mua ve tour

```
Staff       LoginFrm     UserDAO    HomeFrm    SearchTourFrm  TourDAO   TourDepartureDAO  BuyTicketFrm  CustomerDAO  InvoiceDAO  InvoiceDetailDAO
  |            |            |          |             |            |             |              |             |            |              |
  |--login---->|            |          |             |            |             |              |             |            |              |
  |            |--checkLogin>|         |             |            |             |              |             |            |              |
  |            |            |--query DB|             |            |             |              |             |            |              |
  |            |            |<-return--|             |            |             |              |             |            |              |
  |            |<--true-----|          |             |            |             |              |             |            |              |
  |            |--open------|--------->|             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |--select--->|------------|--------->|             |            |             |              |             |            |              |
  |            |            |          |--open------|------------>|             |              |             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |--enter---->|            |          |             |            |             |              |             |            |              |
  |  "Ha Long" |            |          |             |            |             |              |             |            |              |
  |--click---->|            |          |             |            |             |              |             |            |              |
  |  Search    |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |--searchByDest----------->|              |             |            |              |
  |            |            |          |             |            |---query DB  |              |             |            |              |
  |            |            |          |             |            |<-return-----|              |             |            |              |
  |            |            |          |             |<--List<Tour>|            |              |             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |--display list           |              |             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |--select--->|            |          |             |            |             |              |             |            |              |
  |  tour      |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |------------|-------------|------------->|             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |--enter info|            |          |             |            |             |              |             |            |              |
  |--click Pay>|            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |--findCust-->|            |              |
  |            |            |          |             |            |             |              |             |--query DB  |              |
  |            |            |          |             |            |             |              |             |<-return----|              |
  |            |            |          |             |            |             |              |<--Customer--|            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |--addInvoice-|----------->|              |
  |            |            |          |             |            |             |              |             |            |--INSERT DB    |
  |            |            |          |             |            |             |              |             |            |<-return true--|
  |            |            |          |             |            |             |              |<--true------|            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |--addDetail--|----------->|              |
  |            |            |          |             |            |             |              |             |            |--INSERT DB    |
  |            |            |          |             |            |             |              |             |            |<-return true--|
  |            |            |          |             |            |             |              |<--true------|            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |--print ve  |            |              |
  |            |            |          |             |            |             |              |--show success           |              |
  |<--success--|            |          |             |            |             |              |             |            |              |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Buy tickets | Staff | HomeFrm | Staff chon chuc nang Buy tickets |
| 7 | open | HomeFrm | SearchTourFrm | Mo giao dien tim kiem tour |
| 8 | enter "Ha Long" | Staff | SearchTourFrm | Staff nhap ten diem den |
| 9 | click Search | Staff | SearchTourFrm | Staff nhan nut Search |
| 10 | searchByDestination() | SearchTourFrm | TourDAO | Goi TourDAO.searchByDestination("Ha Long") |
| 11 | query DB | TourDAO | Database | Truy van tblTour WHERE destination LIKE '%Ha Long%' |
| 12 | return List<Tour> | TourDAO | SearchTourFrm | Tra ve danh sach tour tim duoc |
| 13 | display list | SearchTourFrm | UI | Hien thi danh sach tour tren bang |
| 14 | select tour | Staff | SearchTourFrm | Staff chon tour "Tour Ha Long 3 ngay" |
| 15 | open | SearchTourFrm | BuyTicketFrm | Mo giao dien mua ve voi thong tin tour |
| 16 | enter customer info | Staff | BuyTicketFrm | Staff nhap thong tin khach hang |
| 17 | click Pay | Staff | BuyTicketFrm | Staff nhan nut thanh toan |
| 18 | findCustomer() | BuyTicketFrm | CustomerDAO | Goi CustomerDAO.findCustomer(idNumber) |
| 19 | query DB | CustomerDAO | Database | Truy van tblCustomer |
| 20 | return Customer | CustomerDAO | BuyTicketFrm | Tra ve doi tuong Customer |
| 21 | addInvoice() | BuyTicketFrm | InvoiceDAO | Tao doi tuong Invoice, goi InvoiceDAO.addInvoice() |
| 22 | INSERT DB | InvoiceDAO | Database | INSERT INTO tblInvoice |
| 23 | return true | InvoiceDAO | BuyTicketFrm | Tra ve true |
| 24 | addInvoiceDetail() | BuyTicketFrm | InvoiceDetailDAO | Tao InvoiceDetail, goi InvoiceDetailDAO.addInvoiceDetail() |
| 25 | INSERT DB | InvoiceDetailDAO | Database | INSERT INTO tblInvoiceDetail |
| 26 | return true | InvoiceDetailDAO | BuyTicketFrm | Tra ve true |
| 27 | print ve | BuyTicketFrm | UI | In ve va hoa don cho khach |
| 28 | show success | BuyTicketFrm | UI | Hien thi thong bao thanh cong |
| 29 | return | BuyTicketFrm | HomeFrm | Quay ve giao dien Home |

---

## Cau 5: Viet testcase blackbox chuan (1.5 diem)

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Buy tickets | Mua ve thanh cong voi khach hang da ton tai va tour hop le |
| 2 | Buy tickets | Tim kiem tour voi diem den khong ton tai |
| 3 | Buy tickets | Tour da het cho (so khach dang ky >= maxGuests) |
| 4 | Buy tickets | So khach nhap <= 0 |
| 5 | Buy tickets | Thong tin khach hang khong day du |

### Testcase chi tiet — TC01: Mua ve thanh cong

**Muc dich:** Kiem tra chuc nang mua ve hoat dong dung khi khach hang ton tai, tour hop le, va con cho.

**Database truoc khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblTour:**
| ID | code | name | departure | destination | description |
|----|------|------|-----------|-------------|-------------|
| 1 | T001 | Tour Ha Long 3 ngay | Ha Noi | Ha Long | Tham quan Vinh Ha Long 3 ngay 2 dem |

**tblTourDeparture:**
| ID | tourID | departureDate | price | maxGuests |
|----|--------|---------------|-------|-----------|
| 1 | 1 | 15/07/2026 | 2500000 | 30 |

**tblCustomer:**
| ID | code | name | idNumber | idType | phone | email | address |
|----|------|------|----------|--------|-------|-------|---------|
| 1 | C001 | Nguyen Van A | 012345678901 | CCCD | 0912345678 | a@gmail.com | Ha Noi |

**tblInvoice:** (rong)

**tblInvoiceDetail:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics |
| 3 | Chon chuc nang Buy tickets | Giao dien tim kiem tour xuat hien voi o nhap diem den, nut Search |
| 4 | Nhap "Ha Long" vao o destination, nhan Search | Bang danh sach tour hien thi: T001 - Tour Ha Long 3 ngay - Ha Noi - Ha Long - 15/07/2026 - 2,500,000 VND |
| 5 | Click chon tour T001 | Giao dien hoa don xuat hien: ten tour "Tour Ha Long 3 ngay", noi khoi hanh "Ha Noi", diem den "Ha Long", ngay khoi hanh "15/07/2026", gia ve 2,500,000 VND/khach |
| 6 | Nhap thong tin khach: ten "Nguyen Van A", CMND "012345678901", loai "CCCD", dia chi "Ha Noi", SDT "0912345678", email "a@gmail.com", so khach 2 | He thong hien thi tong tien: 5,000,000 VND |
| 7 | Nhan nut thanh toan | He thong luu hoa don vao database. Thong bao "Mua ve thanh cong". Ve duoc in ra chua: ten tour, ngay khoi hanh, ten khach hang, so khach, tong tien |
| 8 | Nhan OK | Quay ve giao dien Home |

### Database sau khi test

**tblInvoice:** (them 1 dong)
| ID | invoiceDate | totalAmount | numGuests | customerID | userID |
|----|-------------|-------------|-----------|------------|--------|
| 1 | 08/06/2026 | 5000000 | 2 | 1 | 1 |

**tblInvoiceDetail:** (them 1 dong)
| ID | guestCount | unitPrice | amount | tourDepartureID | invoiceID |
|----|------------|-----------|--------|-----------------|-----------|
| 1 | 2 | 2500000 | 5000000 | 1 | 1 |

**tblTour:** (khong thay doi)

**tblTourDeparture:** (khong thay doi)

**tblCustomer:** (khong thay doi — khach hang da ton tai)

**tblUser:** (khong thay doi)
