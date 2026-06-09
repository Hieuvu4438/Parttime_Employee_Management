# Subject No. 12 — Tour Booking — Module "Cancel the ticket"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Viet scenario chuan cho module (1.5 diem)

### Scenario — Huy ve tour

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien (Staff) dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics. |
| 4 | Staff chon chuc nang **Cancel the ticket**. |
| 5 | Giao dien tim kiem ve xuat hien voi o nhap ma ve (ticket code), nut Search. |
| 6 | Staff nhap ma ve `TK001` vao o tim kiem va nhan Search. |
| 7 | He thong hien thi chi tiet ve: ten tour, noi khoi hanh, diem den, ngay khoi hanh, ten dai dien, CMND, loai CMND, dia chi, SDT, email, so khach, gia ve, tong tien. |
| 8 | Staff xac nhan thong tin ve va chon **Cancel ticket**. |
| 9 | He thong tinh tien phat dua tren thoi diem huy: neu huy truoc 7 ngay khoi hanh → phat 10%, truoc 3 ngay → phat 30%, duoi 3 ngay → phat 50%. He thong hien thi hoa don phat: thong tin ve + tien phat + tien hoan lai. |
| 10 | He thong hien thi hoa don phat: ma ve, ten tour, ngay khoi hanh, so khach, tong tien goc, muc phat, tien phat, tien hoan lai cho khach. |
| 11 | Staff nhan **OK** de xac nhan huy ve. |
| 12 | He thong luu vao database (cap nhat trang thai ve thanh "cancelled", tao CancellationInvoice), staff tra tien hoan lai cho khach hang. |
| 13 | He thong thong bao "Huy ve thanh cong" va quay ve giao dien Home. |

### Kich ban ngoai le

- **EL1:** Ma ve khong ton tai trong he thong → He thong thong bao "Ma ve khong ton tai".
- **EL2:** Ve da bi huy truoc do (status = "cancelled") → He thong thong bao "Ve da duoc huy truoc do".
- **EL3:** Ve da su dung (ngay khoi hanh da qua) → He thong thong bao "Khong the huy ve da su dung".

---

## Cau 2: Trich xay dung class diagram cho cac entity class lien quan (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly huy ve tour du lich. Khi khach hang muon huy ve, nhan vien nhap ma ve de tim kiem. He thong hien thi chi tiet ve va tinh tien phat dua tren thoi diem huy so voi ngay khoi hanh. Sau khi xac nhan, he thong cap nhat trang thai ve thanh "cancelled", tao hoa don huy (CancellationInvoice) ghi nhan tien phat, va staff tra tien hoan lai cho khach. Moi ve (Invoice) co the co hoa don huy lien ket. Khach hang co the co nhieu ve, moi ve thuoc ve mot ngay khoi hanh cu the cua tour.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Tour | Entity class | Doi tuong chinh: tour du lich |
| TourDeparture | Entity class | Ngay khoi hanh cu the voi gia cu the |
| Customer | Entity class | Nguoi mua ve |
| Invoice | Entity class | Phieu mua ve (hoa don goc) |
| InvoiceDetail | Entity class | Chi tiet hoa don |
| CancellationInvoice | Entity class | Hoa don huy ve, ghi nhan tien phat |
| User | Entity class | Nhan vien thuc hien huy ve |
| Ma ve, trang thai ve | Thuoc tinh | Thuoc tinh cua Invoice |
| Tien phat, ngay huy | Thuoc tinh | Thuoc tinh cua CancellationInvoice |

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
- Moi hoa don thuoc ve mot khach hang.

```
Invoice 1 --- n InvoiceDetail
```
- Mot hoa don co nhieu chi tiet.
- Moi chi tiet thuoc ve mot hoa don.

```
TourDeparture 1 --- n InvoiceDetail
```
- Mot ngay khoi hanh xuat hien trong nhieu chi tiet hoa don.

```
Invoice 1 --- 0..1 CancellationInvoice
```
- Mot hoa don co the co toi da 1 hoa don huy.
- Moi hoa don huy lien ket voi 1 hoa don goc.

```
User 1 --- n CancellationInvoice
```
- Mot nhan vien tao nhieu hoa don huy.

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
| -code: String    |       | -code: String    |
| -name: String    |       | -invoiceDate: Date|
| -idNumber: String|       | -totalAmount: float|
| -idType: String  |       | -numGuests: int  |
| -phone: String   |       | -status: String  |
| -email: String   |       +------------------+
| -address: String |                | 1
+------------------+                |
         | 1                        | n
         |                          v
         | n                +------------------+
         +----------------->| InvoiceDetail    |
                            +------------------+
                            | -id: int         |
                            | -guestCount: int |
                            | -unitPrice: float|
                            | -amount: float   |
                            +------------------+

+------------------------+       +------------------+
| CancellationInvoice    |       |      User        |
+------------------------+       +------------------+
| -id: int               |       | -id: int         |
| -cancelDate: Date      |       | -username: String|
| -fineRate: float       |       | -password: String|
| -fineAmount: float     |       | -role: String    |
| -refundAmount: float   |       +------------------+
+------------------------+
         | 0..1
         |
         | 1
         v
     Invoice
```

### Mo ta quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Tour → TourDeparture | 1-n (Composition) | Mot tour co nhieu ngay khoi hanh |
| Customer → Invoice | 1-n (Association) | Mot khach hang co nhieu hoa don |
| Invoice → InvoiceDetail | 1-n (Composition) | Mot hoa don co nhieu chi tiet |
| TourDeparture → InvoiceDetail | 1-n (Association) | Mot ngay khoi hanh xuat hien trong nhieu chi tiet |
| Invoice → CancellationInvoice | 1-0..1 (Association) | Mot hoa don co the co 1 hoa don huy |
| User → CancellationInvoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don huy |

### Classes diagram (analysis)

Analysis this module (exclude login step):

Once login successful -> HomeFrm appears:
  an option to cancel ticket -> subCancelTicket

Choose Cancel ticket -> CancelTicketFrm appears:
  input ticket code -> inTicketCode
  a button search -> subSearch
  display ticket info -> outTicketInfo
  a button cancel -> subCancel
  display fine invoice -> outFineInvoice
  a button OK -> subOK

Enter ticket code and search -> system finds invoice by code -> need a method:
  name: getInvoiceByCode()
  input: code (String)
  output: Invoice
  assign to entity class: Invoice.

Search -> system gets invoice details -> need a method:
  name: getDetailsByInvoice()
  input: invoiceId (int)
  output: list of InvoiceDetail
  assign to entity class: InvoiceDetail.

Click OK -> system updates invoice status to cancelled -> need a method:
  name: updateStatus()
  input: invoiceId (int), status (String)
  output: boolean
  assign to entity class: Invoice.

Click OK -> system creates cancellation invoice -> need a method:
  name: addCancellation()
  input: ci (CancellationInvoice)
  output: boolean
  assign to entity class: CancellationInvoice.

### Summary
View classes: HomeFrm, CancelTicketFrm
Methods: getInvoiceByCode(), getDetailsByInvoice(), updateStatus(), addCancellation()

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet (1.5 diem)

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| CancelTicketFrm | Giao dien tim kiem va huy ve (chinh) |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subBuyTickets`: nut chon Buy tickets
- `subCancelTicket`: nut chon Cancel the ticket
- `subStatistics`: nut chon Statistics

**CancelTicketFrm:**
- `inTicketCode`: o nhap ma ve
- `subSearch`: nut Search
- `outTicketInfo`: vung hien thi chi tiet ve (ten tour, noi khoi hanh, diem den, ngay khoi hanh, ten dai dien, CMND, loai CMND, dia chi, SDT, email, so khach, gia ve, tong tien)
- `subCancel`: nut Cancel ticket
- `outFineInvoice`: vung hien thi hoa don phat (thong tin ve + muc phat + tien phat + tien hoan lai)
- `subOK`: nut OK de xac nhan huy

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| InvoiceDAO | `getInvoiceByCode(code): Invoice` | Tim hoa don theo ma ve |
| InvoiceDetailDAO | `getDetailsByInvoice(invoiceId): List<InvoiceDetail>` | Lay chi tiet hoa don |
| InvoiceDAO | `updateStatus(invoiceId, status): boolean` | Cap nhat trang thai ve |
| CancellationInvoiceDAO | `addCancellation(ci): boolean` | Tao hoa don huy |

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
- `code: String`
- `invoiceDate: Date`
- `totalAmount: float`
- `numGuests: int`
- `status: String` (active/cancelled)
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

**CancellationInvoice:**
- `id: int` (PK)
- `cancelDate: Date`
- `fineRate: float`
- `fineAmount: float`
- `refundAmount: float`
- `invoice: Invoice` (object attribute, FK)
- `user: User` (object attribute, FK)

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
| code | varchar | |
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

**tblCancellationInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| cancelDate | date | |
| fineRate | float | |
| fineAmount | float | |
| refundAmount | float | |
| invoiceID | int | FK → tblInvoice.ID |
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
2. Tao cac View class: `LoginFrm`, `HomeFrm`, `CancelTicketFrm`.
3. Tao cac DAO class: `UserDAO`, `InvoiceDAO`, `InvoiceDetailDAO`, `CancellationInvoiceDAO`.
4. Tao cac Entity class: `Tour`, `TourDeparture`, `Customer`, `Invoice`, `InvoiceDetail`, `CancellationInvoice`, `User`.

**Ve View classes (Form):**
- Ve hinh chu nhat 3 ngan cho `CancelTicketFrm`:
  - Ngan 1 (ten): `<<boundary>> CancelTicketFrm`
  - Ngan 2 (thuoc tinh): `-inTicketCode: JTextField`, `-subSearch: JButton`, `-outTicketInfo: JTextArea`, `-subCancel: JButton`, `-outFineInvoice: JTextArea`, `-subOK: JButton`
  - Ngan 3 (phuong thuc): +khong co

**Ve DAO classes:**
- Ve hinh chu nhat 3 ngan cho `CancellationInvoiceDAO`:
  - Ngan 1: `<<control>> CancellationInvoiceDAO`
  - Ngan 2: khong co thuoc tinh
  - Ngan 3: `+addCancellation(ci: CancellationInvoice): boolean`

**Ve cac duong ket noi:**

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| CancelTicketFrm | InvoiceDAO | Duong lien net, mui tam tam giac rong | Dependency | CancelTicketFrm su dung InvoiceDAO |
| CancelTicketFrm | CancellationInvoiceDAO | Duong lien net, mui tam tam giac rong | Dependency | CancelTicketFrm su dung CancellationInvoiceDAO |
| Invoice | CancellationInvoice | Duong lien net, dau kim cuong rong | Aggregation 0..1 | Hoa don co the co hoa don huy |
| Invoice | InvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | Hoa don chua nhieu chi tiet |
| Invoice | Customer | Duong lien net, mui tam tam giac rong | Association | Hoa don tham chieu den Customer |
| CancellationInvoice | User | Duong lien net, mui tam tam giac rong | Association | Hoa don huy tham chieu den User |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram (1.5 diem)

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `CancelTicketFrm`
   - Control: `UserDAO`, `InvoiceDAO`, `InvoiceDetailDAO`, `CancellationInvoiceDAO`
3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Huy ve tour

```
Staff       LoginFrm     UserDAO    HomeFrm    CancelTicketFrm  InvoiceDAO  InvoiceDetailDAO  CancellationInvoiceDAO
  |            |            |          |             |               |              |                    |
  |--login---->|            |          |             |               |              |                    |
  |            |--checkLogin>|         |             |               |              |                    |
  |            |            |--query DB|             |               |              |                    |
  |            |            |<-return--|             |               |              |                    |
  |            |<--true-----|          |             |               |              |                    |
  |            |--open------|--------->|             |               |              |                    |
  |            |            |          |             |               |              |                    |
  |--select--->|------------|--------->|             |               |              |                    |
  |  Cancel    |            |          |--open------|-------------->|              |                    |
  |            |            |          |             |               |              |                    |
  |--enter---->|            |          |             |               |              |                    |
  |  "TK001"   |            |          |             |               |              |                    |
  |--click---->|            |          |             |               |              |                    |
  |  Search    |            |          |             |               |              |                    |
  |            |            |          |             |--getInvoiceByCode----------->|                    |
  |            |            |          |             |               |---query DB   |                    |
  |            |            |          |             |               |<-return------|                    |
  |            |            |          |             |<--Invoice-----|              |                    |
  |            |            |          |             |               |              |                    |
  |            |            |          |             |--getDetailsByInvoice--------->|                    |
  |            |            |          |             |               |---query DB   |                    |
  |            |            |          |             |               |<-return------|                    |
  |            |            |          |             |<--List<Detail>|              |                    |
  |            |            |          |             |               |              |                    |
  |            |            |          |             |--display ticket info         |                    |
  |            |            |          |             |               |              |                    |
  |--click---->|            |          |             |               |              |                    |
  |  Cancel    |            |          |             |               |              |                    |
  |            |            |          |             |--calculate fine             |                    |
  |            |            |          |             |--display fine invoice       |                    |
  |            |            |          |             |               |              |                    |
  |--click OK->|            |          |             |               |              |                    |
  |            |            |          |             |--updateStatus-|------------->|                    |
  |            |            |          |             |               |---UPDATE DB  |                    |
  |            |            |          |             |               |<-return true-|                    |
  |            |            |          |             |               |              |                    |
  |            |            |          |             |--addCancellation-----------|-->|                    |
  |            |            |          |             |               |              |----INSERT DB       |
  |            |            |          |             |               |              |<---return true-----|
  |            |            |          |             |<--true--------|              |                    |
  |            |            |          |             |               |              |                    |
  |            |            |          |             |--print refund slip          |                    |
  |            |            |          |             |--show success|              |                    |
  |<--success--|            |          |             |               |              |                    |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Cancel | Staff | HomeFrm | Staff chon chuc nang Cancel the ticket |
| 7 | open | HomeFrm | CancelTicketFrm | Mo giao dien huy ve |
| 8 | enter "TK001" | Staff | CancelTicketFrm | Staff nhap ma ve |
| 9 | click Search | Staff | CancelTicketFrm | Staff nhan nut Search |
| 10 | getInvoiceByCode() | CancelTicketFrm | InvoiceDAO | Goi InvoiceDAO.getInvoiceByCode("TK001") |
| 11 | query DB | InvoiceDAO | Database | Truy van tblInvoice WHERE code = 'TK001' |
| 12 | return Invoice | InvoiceDAO | CancelTicketFrm | Tra ve doi tuong Invoice |
| 13 | getDetailsByInvoice() | CancelTicketFrm | InvoiceDetailDAO | Goi InvoiceDetailDAO.getDetailsByInvoice(invoiceId) |
| 14 | query DB | InvoiceDetailDAO | Database | Truy van tblInvoiceDetail JOIN tblTourDeparture JOIN tblTour |
| 15 | return List<Detail> | InvoiceDetailDAO | CancelTicketFrm | Tra ve danh sach chi tiet |
| 16 | display ticket info | CancelTicketFrm | UI | Hien thi chi tiet ve |
| 17 | click Cancel | Staff | CancelTicketFrm | Staff nhan nut Cancel ticket |
| 18 | calculate fine | CancelTicketFrm | Logic | Tinh tien phat dua tren so ngay con lai truoc khoi hanh |
| 19 | display fine invoice | CancelTicketFrm | UI | Hien thi hoa don phat |
| 20 | click OK | Staff | CancelTicketFrm | Staff xac nhan huy ve |
| 21 | updateStatus() | CancelTicketFrm | InvoiceDAO | Goi InvoiceDAO.updateStatus(invoiceId, "cancelled") |
| 22 | UPDATE DB | InvoiceDAO | Database | UPDATE tblInvoice SET status = 'cancelled' |
| 23 | return true | InvoiceDAO | CancelTicketFrm | Tra ve true |
| 24 | addCancellation() | CancelTicketFrm | CancellationInvoiceDAO | Tao CancellationInvoice, goi addCancellation() |
| 25 | INSERT DB | CancellationInvoiceDAO | Database | INSERT INTO tblCancellationInvoice |
| 26 | return true | CancellationInvoiceDAO | CancelTicketFrm | Tra ve true |
| 27 | print refund slip | CancelTicketFrm | UI | In bien lai hoan tien |
| 28 | show success | CancelTicketFrm | UI | Hien thi thong bao "Huy ve thanh cong" |
| 29 | return | CancelTicketFrm | HomeFrm | Quay ve giao dien Home |

---

## Cau 5: Viet testcase blackbox chuan (1.5 diem)

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Cancel ticket | Huy ve thanh cong voi ve ton tai va con hieu luc |
| 2 | Cancel ticket | Ma ve khong ton tai trong he thong |
| 3 | Cancel ticket | Ve da bi huy truoc do |
| 4 | Cancel ticket | Ve da qua ngay khoi hanh |
| 5 | Cancel ticket | Huy ve gan ngay khoi hanh (phat 50%) |

### Testcase chi tiet — TC01: Huy ve thanh cong

**Muc dich:** Kiem tra chuc nang huy ve hoat dong dung khi ve ton tai, con hieu luc, va huy truoc ngay khoi hanh du xa (phat 10%).

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

**tblInvoice:**
| ID | code | invoiceDate | totalAmount | numGuests | status | customerID | userID |
|----|------|-------------|-------------|-----------|--------|------------|--------|
| 1 | TK001 | 01/06/2026 | 5000000 | 2 | active | 1 | 1 |

**tblInvoiceDetail:**
| ID | guestCount | unitPrice | amount | tourDepartureID | invoiceID |
|----|------------|-----------|--------|-----------------|-----------|
| 1 | 2 | 2500000 | 5000000 | 1 | 1 |

**tblCancellationInvoice:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics |
| 3 | Chon chuc nang Cancel the ticket | Giao dien tim kiem ve xuat hien voi o nhap ma ve, nut Search |
| 4 | Nhap ma ve `TK001`, nhan Search | Hien thi chi tiet ve: Tour Ha Long 3 ngay, Ha Noi → Ha Long, 15/07/2026, Nguyen Van A, CMND 012345678901, 2 khach, tong tien 5,000,000 VND |
| 5 | Nhan nut Cancel ticket | He thong tinh phat: con 37 ngay truoc khoi hanh → phat 10%. Hoa don phat hien thi: tong tien goc 5,000,000 VND, phat 10% = 500,000 VND, hoan lai 4,500,000 VND |
| 6 | Nhan nut OK | He thong luu vao database. Thong bao "Huy ve thanh cong". Bien lai hoan tien duoc in: ma ve TK001, tien phat 500,000 VND, tien hoan lai 4,500,000 VND |
| 7 | Nhan OK | Quay ve giao dien Home |

### Database sau khi test

**tblInvoice:** (cap nhat 1 dong)
| ID | code | invoiceDate | totalAmount | numGuests | status | customerID | userID |
|----|------|-------------|-------------|-----------|--------|------------|--------|
| 1 | TK001 | 01/06/2026 | 5000000 | 2 | **cancelled** | 1 | 1 |

**tblCancellationInvoice:** (them 1 dong)
| ID | cancelDate | fineRate | fineAmount | refundAmount | invoiceID | userID |
|----|------------|----------|------------|--------------|-----------|--------|
| 1 | 08/06/2026 | 0.10 | 500000 | 4500000 | 1 | 1 |

**tblInvoiceDetail:** (khong thay doi)

**tblTour:** (khong thay doi)

**tblTourDeparture:** (khong thay doi)

**tblCustomer:** (khong thay doi)

**tblUser:** (khong thay doi)
