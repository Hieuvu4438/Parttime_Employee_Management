# Subject No. 11 — Tour Booking — Module "Buy tickets"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Cau 1: Viet scenario chuan cho module (1.5 diem)

### Scenario — Mua ve tour

| Buoc | Dien bien | Giao dien hien thi |
|------|-----------|---------------------|
| 1 | Nhan vien (Staff) mo ung dung. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. | **LoginFrm** |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. | **LoginFrm** |
| 3 | He thong xac thuc thanh cong. Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics. | **HomeFrm** |
| 4 | Staff chon chuc nang **Buy tickets**. | **HomeFrm** |
| 5 | Giao dien tim kiem tour **SearchTourFrm** xuat hien voi o nhap ten diem den (destination), nut Search. | **SearchTourFrm** |
| 6 | Staff nhap "Ha Long" vao o destination va nhan Search. | **SearchTourFrm** |
| 7 | He thong hien thi danh sach tour co san: ma tour, ten tour, noi khoi hanh, diem den, mo ta, ngay khoi hanh, gia ve. | **SearchTourFrm** — bang danh sach |
| 8 | Staff chon tour "Tour Ha Long 3 ngay" voi ngay khoi hanh 15/07/2026, gia 2,500,000 VND/khach. | **SearchTourFrm** |
| 9 | Giao dien hoa don **BuyTicketFrm** xuat hien: ten tour, noi khoi hanh, diem den, ngay khoi hanh, ten dai dien doan, CMND, loai CMND, dia chi, SDT, email, so khach, gia ve. | **BuyTicketFrm** |
| 10 | Staff nhap thong tin khach hang dai dien: ten "Nguyen Van A", CMND "012345678901", loai CMND "CCCD", dia chi "Ha Noi", SDT "0912345678", email "a@gmail.com", so khach 2. | **BuyTicketFrm** |
| 11 | He thong tinh tong tien: 2 x 2,500,000 = 5,000,000 VND. Hien thi tong tien tren hoa don. | **BuyTicketFrm** |
| 12 | Staff chon thanh toan. Khach hang thanh toan tien mat 5,000,000 VND. | **BuyTicketFrm** |
| 13 | He thong kiem tra so cho con trong (maxGuests >= so khach dang ky). He thong luu vao database: tblInvoice (1 dong moi), tblInvoiceDetail (1 dong moi), tblCustomer neu moi. | **BuyTicketFrm** |
| 14 | He thong in ve cho khach chua: ten tour, ngay khoi hanh, ten khach hang, so khach, tong tien. | **BuyTicketFrm** |
| 15 | He thong thong bao "Mua ve thanh cong" va quay ve giao dien Home. | **HomeFrm** |

### Kich ban ngoai le

| Ma | Tinh huong | Dien bien |
|----|-----------|-----------|
| EL1 | Ten diem den khong co tour nao | He thong thong bao "Khong tim thay tour phu hop". |
| EL2 | Tour da het cho (so khach dang ky >= so cho toi da) | He thong thong bao "Tour da het cho". |
| EL3 | Khach hang nhap so khach <= 0 | He thong thong bao "So khach khong hop le". |
| EL4 | Thong tin khach hang khong day du (thieu CMND, thieu SDT) | He thong thong bao "Vui long nhap day du thong tin". |

---

## Cau 2: Trich xay dung class diagram cho cac entity class lien quan (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly dat ve tour du lich. Moi tour (ma tour, ten tour, noi khoi hanh, diem den, mo ta) co the khoi hanh vao nhieu ngay khac nhau voi gia khac nhau. Moi tour co lich trinh di qua nhieu diem tham quan (Site). Tai moi diem tham quan, tour co the su dung dich vu (Service) cua cac nha cung cap (Provider) khac nhau. Moi khach hang (ma KH, ten, CMND, loai CMND, SDT, email, dia chi) co the mua ve cho nhieu tour. Cung mot khach hang co the di cung mot tour nhieu lan (ngay khac nhau). Moi lan mua ve, mot hoa don (Invoice) duoc tao chua thong tin khach hang va tong tien. Hoa don co chi tiet hoa don (InvoiceDetail) lien ket voi ngay khoi hanh cu the (TourDeparture). Khach hang co the tra ve voi muc phat phu thuoc vao thoi diem huy.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Tour | Entity class | Doi tuong chinh: tour du lich, co ma, ten, noi khoi hanh, diem den, mo ta |
| TourDeparture | Entity class | Ngay khoi hanh cu the voi gia cu the, so cho toi da |
| TourSite | Entity class | Lich trinh tour di qua diem tham quan (thu tu tham quan) |
| Site | Entity class | Diem tham quan, co ten, dia chi, mo ta |
| Service | Entity class | Dich vu tai diem tham quan, co ten, gia, mo ta |
| Provider | Entity class | Nha cung cap dich vu, co ten, SDT, dia chi |
| Customer | Entity class | Nguoi mua ve, co ma KH, ten, CMND, loai CMND, SDT, email, dia chi |
| Invoice | Entity class | Hoa don mua ve, co ngay, tong tien, so khach |
| InvoiceDetail | Entity class | Chi tiet hoa don, lien ket voi TourDeparture |
| User | Entity class | Nhan vien thuc hien giao dich |
| Ma tour, ten tour, noi khoi hanh, diem den, mo ta | Thuoc tinh | Thuoc tinh cua Tour |
| Ngay khoi hanh, gia ve, so cho toi da | Thuoc tinh | Thuoc tinh cua TourDeparture |
| Thu tu tham quan | Thuoc tinh | Thuoc tinh cua TourSite |
| Ten dia diem, dia chi, mo ta | Thuoc tinh | Thuoc tinh cua Site |
| Ten dich vu, gia, mo ta | Thuoc tinh | Thuoc tinh cua Service |
| Ten nha cung cap, SDT, dia chi | Thuoc tinh | Thuoc tinh cua Provider |
| Ten KH, CMND, loai CMND, SDT, email, dia chi | Thuoc tinh | Thuoc tinh cua Customer |
| Tong tien, ngay mua, so khach | Thuoc tinh | Thuoc tinh cua Invoice |
| So khach, don gia, thanh tien | Thuoc tinh | Thuoc tinh cua InvoiceDetail |

### Buoc 3: Xac dinh quan he

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| Tour → TourDeparture | 1-n (Composition) | Mot tour co nhieu ngay khoi hanh. TourDeparture bi xoa khi Tour bi xoa |
| Tour → TourSite | 1-n (Composition) | Mot tour co nhieu diem tham quan trong lich trinh |
| Site → TourSite | 1-n (Association) | Mot diem tham quan xuat hien trong nhieu tour |
| TourSite → Service | n-n | Tai moi diem tham quan, tour co the su dung nhieu dich vu |
| Provider → Service | 1-n (Association) | Mot nha cung cap co nhieu dich vu |
| Customer → Invoice | 1-n (Association) | Mot khach hang co nhieu hoa don |
| Invoice → InvoiceDetail | 1-n (Composition) | Mot hoa don co nhieu chi tiet. InvoiceDetail bi xoa khi Invoice bi xoa |
| TourDeparture → InvoiceDetail | 1-n (Association) | Mot ngay khoi hanh xuat hien trong nhieu chi tiet hoa don |
| User → Invoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don |

### Buoc 4: Class Diagram (Analysis)

```
+------------------+       +---------------------+
|      Tour        |       |   TourDeparture     |
+------------------+       +---------------------+
| -id: int         |  1  n | -id: int            |
| -code: String    |-------| -departureDate: Date|
| -name: String    |       | -price: float       |
| -departure: String|      | -maxGuests: int     |
| -destination: String|    | -currentGuests: int |
| -description: String|    +---------------------+
+------------------+
         | 1
         |                    +------------------+
         | n                  |      Site        |
+------------------+          +------------------+
|    TourSite      |     n  1 | -id: int         |
+------------------+----------| -name: String    |
| -id: int         |          | -address: String |
| -visitOrder: int |          | -description: String|
+------------------+          +------------------+
         | n                        | 1
         |                          |
         | 1                        | n
+------------------+          +------------------+
|    Service       |     n  1 |    Provider      |
+------------------+----------+------------------+
| -id: int         |          | -id: int         |
| -name: String    |          | -name: String    |
| -price: float    |          | -phone: String   |
| -description: String|       | -address: String |
+------------------+          +------------------+

+------------------+       +------------------+
|    Customer      |       |     Invoice      |
+------------------+       +------------------+
| -id: int         |  1  n | -id: int         |
| -code: String    |-------| -invoiceDate: Date|
| -name: String    |       | -totalAmount: float|
| -idNumber: String|       | -numGuests: int  |
| -idType: String  |       +------------------+
| -phone: String   |                | 1
| -email: String   |                |
| -address: String |                | n
+------------------+                v
                            +------------------+
                            | InvoiceDetail    |
                            +------------------+
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
| Tour → TourSite | 1-n (Composition) | Mot tour co nhieu diem tham quan trong lich trinh |
| Site → TourSite | 1-n (Association) | Mot diem tham quan xuat hien trong nhieu tour |
| TourSite → Service | n-n (Association) | Tai moi diem tham quan, tour su dung nhieu dich vu |
| Provider → Service | 1-n (Association) | Mot nha cung cap co nhieu dich vu |
| Customer → Invoice | 1-n (Association) | Mot khach hang co nhieu hoa don |
| Invoice → InvoiceDetail | 1-n (Composition) | Mot hoa don co nhieu chi tiet |
| TourDeparture → InvoiceDetail | 1-n (Association) | Mot ngay khoi hanh xuat hien trong nhieu chi tiet |
| User → Invoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don |

### Huong dan ve Class Diagram tren Visual Paradigm

**1. Cac buoc ve tong quan:**

| Buoc | Thao tac | Mo ta |
|------|----------|-------|
| 1 | Mo Visual Paradigm → New → Class Diagram | Tao diagram moi, dat ten "Tour_BuyTickets" |
| 2 | Tao entity class boxes | Keo "Class" tu toolbar vao canvas, tao 10 class: Tour, TourDeparture, TourSite, Site, Service, Provider, Customer, Invoice, InvoiceDetail, User |
| 3 | Tao view class boxes | Keo "Boundary" vao canvas, tao: LoginFrm, HomeFrm, SearchTourFrm, BuyTicketFrm |
| 4 | Ve relationships | Keo duong ket noi giua cac class theo bang quan he |
| 5 | Them multiplicities | Click vao duong ket noi → Properties → dat Source/Target Multiplicity |

**2. Cau truc 1 class box (3 ngan):**

Moi class trong Visual Paradigm la hinh chu nhat chia 3 ngan:

| Ngan | Noi dung | Vi du (class Invoice) |
|------|----------|------------------------|
| Ngan 1 — Ten class | Stereotype + ten class | `<<entity>> Invoice` |
| Ngan 2 — Thuoc tinh | `-attributeName: Type` | `-id: int`, `-invoiceDate: Date`, `-totalAmount: float`, `-numGuests: int` |
| Ngan 3 — Phuong thuc | `+methodName(params): ReturnType` | `+addInvoice(): boolean`, `+calculateTotal(): float` |

Stereotype su dung: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bang chi tung entity class:**

| Class | Stereotype | Thuoc tinh (Ngan 2) | Phuong thuc (Ngan 3) |
|-------|-----------|----------------------|----------------------|
| Tour | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-departure: String`, `-destination: String`, `-description: String` | — |
| TourDeparture | `<<entity>>` | `-id: int`, `-departureDate: Date`, `-price: float`, `-maxGuests: int`, `-currentGuests: int`, `-tourId: int` | — |
| TourSite | `<<entity>>` | `-id: int`, `-visitOrder: int`, `-tourId: int`, `-siteId: int` | — |
| Site | `<<entity>>` | `-id: int`, `-name: String`, `-address: String`, `-description: String` | — |
| Service | `<<entity>>` | `-id: int`, `-name: String`, `-price: float`, `-description: String`, `-providerId: int` | — |
| Provider | `<<entity>>` | `-id: int`, `-name: String`, `-phone: String`, `-address: String` | — |
| Customer | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-idNumber: String`, `-idType: String`, `-phone: String`, `-email: String`, `-address: String` | — |
| Invoice | `<<entity>>` | `-id: int`, `-invoiceDate: Date`, `-totalAmount: float`, `-numGuests: int`, `-customerId: int`, `-userId: int` | — |
| InvoiceDetail | `<<entity>>` | `-id: int`, `-guestCount: int`, `-unitPrice: float`, `-amount: float`, `-tourDepartureId: int`, `-invoiceId: int` | — |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |

**4. Bang chi tiet view classes:**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-subBuyTickets: JButton`, `-subCancelTicket: JButton`, `-subStatistics: JButton` |
| SearchTourFrm | `<<boundary>>` | `-inDestination: JTextField`, `-subSearch: JButton`, `-outsubListTour: JTable` |
| BuyTicketFrm | `<<boundary>>` | `-outTourInfo: JLabel`, `-outDepartureInfo: JLabel`, `-inRepresentative: JTextField`, `-inIdNumber: JTextField`, `-inIdType: JComboBox`, `-inAddress: JTextField`, `-inPhone: JTextField`, `-inEmail: JTextField`, `-inNumGuests: JTextField`, `-outTotalAmount: JLabel`, `-subPay: JButton`, `-outInvoice: JLabel` |

Quy uoc dat ten UI elements: `in` = nhap lieu, `out` = hien thi, `sub` = nut bam, `outsub` = bang click duoc.

**5. Cach ve quan he:**

| Kieu quan he | Ky hieu Visual Paradigm | Khi nao dung |
|---------------|--------------------------|---------------|
| **Association** | Duong lien net, mui tam tam giac rong (▷) | Quan he tham chieu thong thuong (Customer → Invoice) |
| **Aggregation** | Duong lien net, dau kim cuong rong (◇) | "Contain" nhung child co the ton tai doc lap |
| **Composition** | Duong lien net, dau kim cuong filled (◆) | "Contain" nhung child KHONG ton tai neu khong co parent (Invoice → InvoiceDetail) |
| **Dependency** | Duong dashed, mui tam tam giac rong (▷) | "Su dung" tam thoi (SearchTourFrm → TourDAO) |

**6. Cach ghi multiplicity:**

| Multiplicity | Cach ghi trong VP | Vi du |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" o mot dau | Invoice co dung 1 Customer |
| 0..* hoac 1..* | Ghi "*" hoac "1..*" o dau kia | Customer co nhieu Invoice |
| 0..1 | Ghi "0..1" | (hiem dung) |

Ghi multiplicity o ca 2 dau cua duong ket noi. Click vao duong → Properties → Source Multiplicity / Target Multiplicity.

**7. Bang quan he chi tiet:**

| Tu | Den | Kieu quan he | Multiplicity | Giai thich |
|----|-----|---------------|--------------|------------|
| Tour | TourDeparture | Composition | 1 → * | Moi tour co nhieu ngay khoi hanh |
| Tour | TourSite | Composition | 1 → * | Moi tour co nhieu diem tham quan |
| Site | TourSite | Association | 1 → * | Moi diem tham quan xuat hien trong nhieu tour |
| Service | TourSite | Association | * → * | Moi dich vu duoc su dung tai nhieu diem |
| Provider | Service | Association | 1 → * | Moi nha cung cap co nhieu dich vu |
| Customer | Invoice | Association | 1 → * | Moi khach hang co nhieu hoa don |
| Invoice | InvoiceDetail | Composition | 1 → * | Moi hoa don co nhieu chi tiet |
| TourDeparture | InvoiceDetail | Association | 1 → * | Moi ngay khoi hanh xuat hien trong nhieu chi tiet |
| User | Invoice | Association | 1 → * | Moi nhan vien tao nhieu hoa don |

**8. Vi du cu the tren Visual Paradigm:**

*Vi du 1: Ve quan he Invoice → InvoiceDetail (1-n, Composition)*

1. Click chuot phai vao class Invoice → chon **Association** → keo den class InvoiceDetail.
2. Click vao duong ket noi → chon **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Click vao dau mui ten o phia InvoiceDetail → chon **Composition** (filled diamond ◆).
5. Dat ten association: `contains`.

*Vi du 2: Ve quan he Customer → Invoice (1-n, Association)*

1. Click chuot phai vao class Customer → chon **Association** → keo den class Invoice.
2. Click vao duong ket noi → chon **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giu mac dinh mui tam tam giac rong (▷) — day la Association.
5. Dat ten association: `purchases`.

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
  assign to DAO class: TourDAO.

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
  assign to DAO class: CustomerDAO.

Click Pay -> system validates available seats -> need a method:
  name: checkAvailability()
  input: tourDepartureId (int), numGuests (int)
  output: boolean
  assign to DAO class: TourDepartureDAO.

Click Pay -> system creates invoice -> need a method:
  name: addInvoice()
  input: invoice (Invoice)
  output: boolean
  assign to DAO class: InvoiceDAO.

Click Pay -> system creates invoice detail -> need a method:
  name: addInvoiceDetail()
  input: detail (InvoiceDetail)
  output: boolean
  assign to DAO class: InvoiceDetailDAO.

Click Pay -> system updates current guests count -> need a method:
  name: updateGuestCount()
  input: tourDepartureId (int), numGuests (int)
  output: boolean
  assign to DAO class: TourDepartureDAO.

### Summary
View classes: HomeFrm, SearchTourFrm, BuyTicketFrm
Methods: searchByDestination(), findCustomer(), checkAvailability(), addInvoice(), addInvoiceDetail(), updateGuestCount()

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

| DAO class | Phuong thuc | Kieu tra ve | Mo ta |
|-----------|-------------|-------------|-------|
| UserDAO | `checkLogin(username: String, password: String): User` | `User` hoac `null` | Kiem tra dang nhap |
| TourDAO | `searchByDestination(keyword: String): List<Tour>` | `List<Tour>` | Tim tour theo diem den |
| TourDepartureDAO | `getDepartures(tourId: int): List<TourDeparture>` | `List<TourDeparture>` | Lay ngay khoi hanh cua tour |
| TourDepartureDAO | `checkAvailability(tourDepartureId: int, numGuests: int): boolean` | `boolean` | Kiem tra so cho con trong |
| TourDepartureDAO | `updateGuestCount(tourDepartureId: int, numGuests: int): boolean` | `boolean` | Cap nhat so khach dang ky |
| CustomerDAO | `findCustomer(idNumber: String): Customer` | `Customer` hoac `null` | Tim khach hang theo CMND |
| CustomerDAO | `addCustomer(customer: Customer): boolean` | `boolean` | Them khach hang moi |
| InvoiceDAO | `addInvoice(invoice: Invoice): boolean` | `boolean` | Tao hoa don moi |
| InvoiceDetailDAO | `addInvoiceDetail(detail: InvoiceDetail): boolean` | `boolean` | Them chi tiet hoa don |

### Buoc 4: Xac dinh Entity class (Design phase)

**Tour:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| code | String | Ma tour |
| name | String | Ten tour |
| departure | String | Noi khoi hanh |
| destination | String | Diem den |
| description | String | Mo ta |
| departures | List<TourDeparture> | Danh sach ngay khoi hanh |
| tourSites | List<TourSite> | Lich trinh diem tham quan |

**TourDeparture:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| departureDate | Date | Ngay khoi hanh |
| price | float | Gia ve |
| maxGuests | int | So cho toi da |
| currentGuests | int | So khach hien tai dang ky |
| tour | Tour | Doi tuong tour (FK) |

**TourSite:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| visitOrder | int | Thu tu tham quan |
| tour | Tour | Doi tuong tour (FK) |
| site | Site | Doi tuong diem tham quan (FK) |
| services | List<Service> | Danh dich vu su dung tai diem nay |

**Site:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| name | String | Ten diem tham quan |
| address | String | Dia chi |
| description | String | Mo ta |

**Service:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| name | String | Ten dich vu |
| price | float | Gia dich vu |
| description | String | Mo ta |
| provider | Provider | Doi tuong nha cung cap (FK) |

**Provider:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| name | String | Ten nha cung cap |
| phone | String | So dien thoai |
| address | String | Dia chi |

**Customer:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| code | String | Ma khach hang |
| name | String | Ten khach hang |
| idNumber | String | So CMND/CCCD |
| idType | String | Loai CMND |
| phone | String | So dien thoai |
| email | String | Email |
| address | String | Dia chi |
| invoices | List<Invoice> | Danh sach hoa don |

**Invoice:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| invoiceDate | Date | Ngay mua |
| totalAmount | float | Tong tien |
| numGuests | int | So khach |
| customer | Customer | Doi tuong khach hang (FK) |
| user | User | Doi tuong nhan vien (FK) |
| invoiceDetails | List<InvoiceDetail> | Danh sach chi tiet |

**InvoiceDetail:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| guestCount | int | So khach |
| unitPrice | float | Don gia |
| amount | float | Thanh tien |
| tourDeparture | TourDeparture | Doi tuong ngay khoi hanh (FK) |
| invoice | Invoice | Doi tuong hoa don (FK) |

**User:**
| Thuoc tinh | Kieu | Mo ta |
|------------|------|-------|
| id | int | Khoa chinh |
| username | String | Ten dang nhap |
| password | String | Mat khau |
| role | String | Vai tro |

### Buoc 5: Database Design

**tblTour:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| departure | varchar(100) | |
| destination | varchar(100) | NOT NULL |
| description | varchar(500) | |

**tblTourDeparture:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| departureDate | date | NOT NULL |
| price | float | NOT NULL |
| maxGuests | int | NOT NULL |
| currentGuests | int | DEFAULT 0 |
| tourID | int | FK → tblTour.ID, NOT NULL |

**tblTourSite:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| visitOrder | int | NOT NULL |
| tourID | int | FK → tblTour.ID, NOT NULL |
| siteID | int | FK → tblSite.ID, NOT NULL |

**tblSite:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| name | varchar(100) | NOT NULL |
| address | varchar(200) | |
| description | varchar(500) | |

**tblService:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| name | varchar(100) | NOT NULL |
| price | float | |
| description | varchar(500) | |
| providerID | int | FK → tblProvider.ID, NOT NULL |

**tblProvider:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| name | varchar(100) | NOT NULL |
| phone | varchar(20) | |
| address | varchar(200) | |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| idNumber | varchar(20) | UNIQUE, NOT NULL |
| idType | varchar(20) | NOT NULL |
| phone | varchar(20) | |
| email | varchar(100) | |
| address | varchar(200) | |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| invoiceDate | date | NOT NULL |
| totalAmount | float | NOT NULL |
| numGuests | int | NOT NULL |
| customerID | int | FK → tblCustomer.ID, NOT NULL |
| userID | int | FK → tblUser.ID, NOT NULL |

**tblInvoiceDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| guestCount | int | NOT NULL |
| unitPrice | float | NOT NULL |
| amount | float | NOT NULL |
| tourDepartureID | int | FK → tblTourDeparture.ID, NOT NULL |
| invoiceID | int | FK → tblInvoice.ID, NOT NULL |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| username | varchar(50) | UNIQUE, NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

### Buoc 6: Mo ta cach ve Class Diagram (Design phase) bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Class Diagram** trong danh muc Diagrams.
2. Tao cac class: `LoginFrm`, `HomeFrm`, `SearchTourFrm`, `BuyTicketFrm` (View classes).
3. Tao cac DAO class: `UserDAO`, `TourDAO`, `TourDepartureDAO`, `CustomerDAO`, `InvoiceDAO`, `InvoiceDetailDAO`.
4. Tao cac Entity class: `Tour`, `TourDeparture`, `TourSite`, `Site`, `Service`, `Provider`, `Customer`, `Invoice`, `InvoiceDetail`, `User`.

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
| SearchTourFrm | TourDAO | Duong dashed, mui tam tam giac rong | Dependency | SearchTourFrm su dung TourDAO |
| BuyTicketFrm | InvoiceDAO | Duong dashed, mui tam tam giac rong | Dependency | BuyTicketFrm su dung InvoiceDAO |
| BuyTicketFrm | CustomerDAO | Duong dashed, mui tam tam giac rong | Dependency | BuyTicketFrm su dung CustomerDAO |
| Invoice | InvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | Invoice chua nhieu InvoiceDetail |
| Invoice | Customer | Duong lien net, mui tam tam giac rong | Association | Invoice tham chieu den Customer |
| Invoice | User | Duong lien net, mui tam tam giac rong | Association | Invoice tham chieu den User |
| InvoiceDetail | TourDeparture | Duong lien net, mui tam tam giac rong | Association | InvoiceDetail tham chieu den TourDeparture |
| Tour | TourDeparture | Duong lien net, dau kim cuong filled | Composition 1-n | Tour chua nhieu TourDeparture |
| Tour | TourSite | Duong lien net, dau kim cuong filled | Composition 1-n | Tour chua nhieu diem tham quan |
| Site | TourSite | Duong lien net, mui tam tam giac rong | Association 1-n | Moi diem xuat hien trong nhieu tour |
| Provider | Service | Duong lien net, mui tam tam giac rong | Association 1-n | Moi nha cung cap co nhieu dich vu |

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
4. Ve cac return message (mui ten net dut) tuong ung.

### Sequence Diagram — Mua ve tour

```
Staff       LoginFrm     UserDAO    HomeFrm    SearchTourFrm  TourDAO   TourDepartureDAO  BuyTicketFrm  CustomerDAO  InvoiceDAO  InvoiceDetailDAO
  |            |            |          |             |            |             |              |             |            |              |
  |--login---->|            |          |             |            |             |              |             |            |              |
  |            |--checkLogin>|         |             |            |             |              |             |            |              |
  |            |            |--query DB|             |            |             |              |             |            |              |
  |            |            |<-return--|             |            |             |              |             |            |              |
  |            |<--User-----|          |             |            |             |              |             |            |              |
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
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |             |              |--checkAvail->|            |              |
  |            |            |          |             |            |             |              |             |            |              |
  |            |            |          |             |            |<-query DB---|              |             |            |              |
  |            |            |          |             |            |--return true|              |             |            |              |
  |            |            |          |             |            |             |<--true--------|             |            |              |
  |            |            |          |             |            |             |              |             |            |              |
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
  |            |            |          |             |            |             |              |--updateGuest|            |              |
  |            |            |          |             |            |<-UPDATE DB--|              |             |            |              |
  |            |            |          |             |            |--return true|              |             |            |              |
  |            |            |          |             |            |             |<--true--------|             |            |              |
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
| 4 | return User | UserDAO | LoginFrm | Tra ve doi tuong User neu dang nhap hop le |
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
| 18 | checkAvailability() | BuyTicketFrm | TourDepartureDAO | Kiem tra so cho con trong (maxGuests - currentGuests >= numGuests) |
| 19 | query DB | TourDepartureDAO | Database | Truy van tblTourDeparture |
| 20 | return true | TourDepartureDAO | BuyTicketFrm | Con cho → tiep tuc |
| 21 | findCustomer() | BuyTicketFrm | CustomerDAO | Goi CustomerDAO.findCustomer(idNumber) |
| 22 | query DB | CustomerDAO | Database | Truy van tblCustomer |
| 23 | return Customer | CustomerDAO | BuyTicketFrm | Tra ve doi tuong Customer |
| 24 | addInvoice() | BuyTicketFrm | InvoiceDAO | Tao doi tuong Invoice, goi InvoiceDAO.addInvoice() |
| 25 | INSERT DB | InvoiceDAO | Database | INSERT INTO tblInvoice |
| 26 | return true | InvoiceDAO | BuyTicketFrm | Tra ve true |
| 27 | addInvoiceDetail() | BuyTicketFrm | InvoiceDetailDAO | Tao InvoiceDetail, goi InvoiceDetailDAO.addInvoiceDetail() |
| 28 | INSERT DB | InvoiceDetailDAO | Database | INSERT INTO tblInvoiceDetail |
| 29 | return true | InvoiceDetailDAO | BuyTicketFrm | Tra ve true |
| 30 | updateGuestCount() | BuyTicketFrm | TourDepartureDAO | Cap nhat currentGuests += numGuests |
| 31 | UPDATE DB | TourDepartureDAO | Database | UPDATE tblTourDeparture SET currentGuests = currentGuests + numGuests |
| 32 | return true | TourDepartureDAO | BuyTicketFrm | Tra ve true |
| 33 | print ve | BuyTicketFrm | UI | In ve va hoa don cho khach |
| 34 | show success | BuyTicketFrm | UI | Hien thi thong bao thanh cong |
| 35 | return | BuyTicketFrm | HomeFrm | Quay ve giao dien Home |

---

## Cau 5: Viet testcase blackbox chuan (1.5 diem)

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Buy tickets | Mua ve thanh cong voi khach hang da ton tai va tour hop le |
| 2 | Buy tickets | Tim kiem tour voi diem den khong ton tai |
| 3 | Buy tickets | Tour da het cho (currentGuests + numGuests > maxGuests) |
| 4 | Buy tickets | So khach nhap <= 0 |
| 5 | Buy tickets | Thong tin khach hang khong day du |
| 6 | Buy tickets | Mua ve voi khach hang moi (chua co trong DB) |

### Testcase chi tiet — TC01: Mua ve thanh cong

**Purpose:** Kiem tra chuc nang mua ve hoat dong dung khi khach hang ton tai, tour hop le, va con cho.

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
| ID | tourID | departureDate | price | maxGuests | currentGuests |
|----|--------|---------------|-------|-----------|---------------|
| 1 | 1 | 2026-07-15 | 2500000 | 30 | 10 |

**tblCustomer:**
| ID | code | name | idNumber | idType | phone | email | address |
|----|------|------|----------|--------|-------|-------|---------|
| 1 | C001 | Nguyen Van A | 012345678901 | CCCD | 0912345678 | a@gmail.com | Ha Noi |

**tblInvoice:**
| ID | invoiceDate | totalAmount | numGuests | customerID | userID |
|----|-------------|-------------|-----------|------------|--------|
| (0 dong) | | | | | |

**tblInvoiceDetail:**
| ID | guestCount | unitPrice | amount | tourDepartureID | invoiceID |
|----|------------|-----------|--------|-----------------|-----------|
| (0 dong) | | | | | |

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang: Buy tickets, Cancel the ticket, Statistics |
| 3 | Chon chuc nang Buy tickets | Giao dien tim kiem tour xuat hien voi o nhap diem den, nut Search |
| 4 | Nhap "Ha Long" vao o destination, nhan Search | Bang danh sach tour hien thi: T001 - Tour Ha Long 3 ngay - Ha Noi - Ha Long - 15/07/2026 - 2,500,000 VND |
| 5 | **Kiem tra DB truoc:** tblTourDeparture co 1 dong, currentGuests = 10, maxGuests = 30. Con 20 cho trong. | Du lieu dung nhu Database truoc khi test |
| 6 | Click chon tour T001 | Giao dien hoa don xuat hien: ten tour "Tour Ha Long 3 ngay", noi khoi hanh "Ha Noi", diem den "Ha Long", ngay khoi hanh "15/07/2026", gia ve 2,500,000 VND/khach |
| 7 | Nhap thong tin khach: ten "Nguyen Van A", CMND "012345678901", loai "CCCD", dia chi "Ha Noi", SDT "0912345678", email "a@gmail.com", so khach 2 | He thong hien thi tong tien: 5,000,000 VND |
| 8 | Nhan nut thanh toan | He thong kiem tra cho trong (20 >= 2 → OK). Tim thay khach hang "Nguyen Van A". Luu hoa don vao database. Cap nhat currentGuests = 12. Ve duoc in ra. Thong bao "Mua ve thanh cong" |
| 9 | Nhan OK | Quay ve giao dien Home |
| 10 | **Kiem tra DB sau:** tblInvoice co 1 dong moi, tblInvoiceDetail co 1 dong moi, tblTourDeparture.currentGuests = 12 | Du lieu thay doi nhu Database sau khi test |

### Database sau khi test

**tblInvoice:** (them 1 dong)
| ID | invoiceDate | totalAmount | numGuests | customerID | userID |
|----|-------------|-------------|-----------|------------|--------|
| 1 | 2026-06-08 | 5000000 | 2 | 1 | 1 |

**tblInvoiceDetail:** (them 1 dong)
| ID | guestCount | unitPrice | amount | tourDepartureID | invoiceID |
|----|------------|-----------|--------|-----------------|-----------|
| 1 | 2 | 2500000 | 5000000 | 1 | 1 |

**tblTourDeparture:** (thay doi 1 dong)
| ID | tourID | departureDate | price | maxGuests | currentGuests |
|----|--------|---------------|-------|-----------|---------------|
| 1 | 1 | 2026-07-15 | 2500000 | 30 | **12** (was 10) |

**tblTour:** (khong thay doi)

**tblCustomer:** (khong thay doi — khach hang da ton tai)

**tblUser:** (khong thay doi)
