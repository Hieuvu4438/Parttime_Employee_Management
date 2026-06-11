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
| 9 | He thong tinh tien phat dua tren thoi diem huy: neu huy truoc 7 ngay khoi hanh → phat 10%, truoc 3 ngay → phat 30%, duoi 3 ngay → phat 50%. He thong hien thi hoa don phat gom: ma ve, ten tour, ngay khoi hanh, so khach, tong tien goc, muc phat (%), tien phat, tien hoan lai cho khach. |
| 10 | Staff nhan **OK** de xac nhan huy ve. |
| 11 | He thong luu vao database (cap nhat trang thai ve thanh "cancelled", tao CancellationInvoice), staff tra tien hoan lai cho khach hang. |
| 12 | He thong thong bao "Huy ve thanh cong" va quay ve giao dien Home. |

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
| Ten dai dien (representativeName) | Thuoc tinh | Thuoc tinh cua Invoice — ten nguoi dai dien mua ve |
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
| -idNumber: String|       | -representativeName: String|
| -idType: String  |       | -totalAmount: float|
| -phone: String   |       | -numGuests: int  |
| -email: String   |       | -status: String  |
| -address: String |       +------------------+
+------------------+                | 1
         | 1                        |
         |                          | n
         | n                        v
         +----------------->+------------------+
                            | InvoiceDetail    |
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
| Invoice → CancellationInvoice | 1-0..1 (Composition) | Mot hoa don co the co 1 hoa don huy; hoa don huy khong ton tai neu khong co hoa don goc |
| User → CancellationInvoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don huy |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity class.
3. Tạo view class boxes từ các interface (form) của module.
4. Vẽ mối quan hệ (relationship) giữa các class.
5. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Tour`, `<<boundary>> CancelTicketFrm`, `<<control>> InvoiceDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-name: String`, `-price: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+getInvoiceByCode(code: String): Invoice`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Tour | <<entity>> | -id: int, -code: String, -name: String, -departure: String, -destination: String, -description: String | (không có) |
| TourDeparture | <<entity>> | -id: int, -departureDate: Date, -price: float, -maxGuests: int | (không có) |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -idNumber: String, -idType: String, -phone: String, -email: String, -address: String | (không có) |
| Invoice | <<entity>> | -id: int, -code: String, -invoiceDate: Date, -representativeName: String, -totalAmount: float, -numGuests: int, -status: String | +getInvoiceByCode(code: String): Invoice, +updateStatus(invoiceId: int, status: String): boolean |
| InvoiceDetail | <<entity>> | -id: int, -guestCount: int, -unitPrice: float, -amount: float | +getDetailsByInvoice(invoiceId: int): List<InvoiceDetail> |
| CancellationInvoice | <<entity>> | -id: int, -cancelDate: Date, -fineRate: float, -fineAmount: float, -refundAmount: float | +addCancellation(ci: CancellationInvoice): boolean |
| User | <<entity>> | -id: int, -username: String, -password: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -subCancelTicket: JButton | Giao diện chính, chứa nút chọn chức năng Cancel the ticket |
| CancelTicketFrm | <<boundary>> | -inTicketCode: JTextField, -subSearch: JButton, -outTicketInfo: JTextArea, -subCancel: JButton, -outFineInvoice: JTextArea, -subOK: JButton | Giao diện tìm kiếm và hủy vé tour |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inTicketCode
- Tiền tố `out` → output (vùng hiển thị): outTicketInfo, outFineInvoice
- Tiền tố `sub` → submit (nút bấm): subSearch, subCancel, subOK

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Invoice (khách hàng tham chiếu đến hóa đơn).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập. Ví dụ: Customer → Invoice (hóa đơn tham chiếu khách hàng nhưng có thể tồn tại riêng khi khách hàng bị xóa).
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Tour → TourDeparture (ngày khởi hành không tồn tại nếu không có tour).
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: CancelTicketFrm → InvoiceDAO (form sử dụng DAO để truy vấn).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..1 → ghi "0..1" ở đầu kia.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Tour (1) → (n) TourDeparture nghĩa là một tour có nhiều ngày khởi hành.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Tour | TourDeparture | Composition | 1 - n | Một tour chứa nhiều ngày khởi hành; ngày khởi hành không tồn tại nếu không có tour |
| Customer | Invoice | Association | 1 - n | Một khách hàng có nhiều hóa đơn mua vé |
| Invoice | InvoiceDetail | Composition | 1 - n | Một hóa đơn chứa nhiều chi tiết; chi tiết không tồn tại nếu không có hóa đơn |
| TourDeparture | InvoiceDetail | Association | 1 - n | Một ngày khởi hành xuất hiện trong nhiều chi tiết hóa đơn |
| Invoice | CancellationInvoice | Association | 1 - 0..1 | Một hóa đơn có thể có tối đa 1 hóa đơn hủy |
| User | CancellationInvoice | Association | 1 - n | Một nhân viên tạo nhiều hóa đơn hủy |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Tour → TourDeparture (1-n, Composition)**
1. Tạo class `<<entity>> Tour` với các thuộc tính: -id: int, -code: String, -name: String, -departure: String, -destination: String, -description: String.
2. Tạo class `<<entity>> TourDeparture` với các thuộc tính: -id: int, -departureDate: Date, -price: float, -maxGuests: int.
3. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
4. Click vào class Tour → kéo đến class TourDeparture.
5. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
6. Kết quả: Tour (1) ◆----(*) TourDeparture.

**Ví dụ 2: Vẽ quan hệ Invoice → CancellationInvoice (1-0..1, Composition)**
1. Tạo class `<<entity>> Invoice` và `<<entity>> CancellationInvoice`.
2. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
3. Click vào class Invoice → kéo đến class CancellationInvoice.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = 0..1.
5. Đặt tên association: "has cancellation" (tùy chọn).
6. Kết quả: Invoice (1) ◆----(0..1) CancellationInvoice.

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
- `tour: Tour` (object attribute, FK — tham chiếu đến Tour cha)

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
- `representativeName: String`
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
| code | varchar(20) | NOT NULL, UNIQUE |
| name | varchar(100) | NOT NULL |
| departure | varchar(100) | NOT NULL |
| destination | varchar(100) | NOT NULL |
| description | varchar(500) | |

**tblTourDeparture:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| departureDate | date | NOT NULL |
| price | decimal(15,2) | NOT NULL |
| maxGuests | int | NOT NULL |
| tourID | int | FK → tblTour.ID, NOT NULL |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | varchar(100) | NOT NULL |
| idNumber | varchar(20) | NOT NULL |
| idType | varchar(20) | NOT NULL |
| phone | varchar(15) | |
| email | varchar(100) | |
| address | varchar(200) | |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar(20) | NOT NULL, UNIQUE |
| invoiceDate | date | NOT NULL |
| representativeName | varchar(100) | NOT NULL |
| totalAmount | decimal(15,2) | NOT NULL |
| numGuests | int | NOT NULL |
| status | varchar(20) | NOT NULL, DEFAULT 'active' |
| customerID | int | FK → tblCustomer.ID, NOT NULL |
| userID | int | FK → tblUser.ID, NOT NULL |

**tblInvoiceDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| guestCount | int | NOT NULL |
| unitPrice | decimal(15,2) | NOT NULL |
| amount | decimal(15,2) | NOT NULL |
| tourDepartureID | int | FK → tblTourDeparture.ID, NOT NULL |
| invoiceID | int | FK → tblInvoice.ID, NOT NULL |

**tblCancellationInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| cancelDate | date | NOT NULL |
| fineRate | decimal(5,2) | NOT NULL |
| fineAmount | decimal(15,2) | NOT NULL |
| refundAmount | decimal(15,2) | NOT NULL |
| invoiceID | int | FK → tblInvoice.ID, NOT NULL, UNIQUE |
| userID | int | FK → tblUser.ID, NOT NULL |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| username | varchar(50) | NOT NULL, UNIQUE |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

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
| Invoice | CancellationInvoice | Duong lien net, dau kim cuong filled | Composition 0..1 | Hoa don co the co hoa don huy; hoa don huy khong ton tai neu khong co hoa don goc |
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
  |            |            |          |             |--validate status (active?)   |                    |
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
| 13 | validate status | CancelTicketFrm | Logic | Kiem tra invoice.status == "active"; neu khong → hien thi loi "Ve da bi huy" hoac "Ve da su dung" |
| 14 | getDetailsByInvoice() | CancelTicketFrm | InvoiceDetailDAO | Goi InvoiceDetailDAO.getDetailsByInvoice(invoiceId) |
| 15 | query DB | InvoiceDetailDAO | Database | Truy van tblInvoiceDetail JOIN tblTourDeparture JOIN tblTour |
| 16 | return List<Detail> | InvoiceDetailDAO | CancelTicketFrm | Tra ve danh sach chi tiet |
| 17 | display ticket info | CancelTicketFrm | UI | Hien thi chi tiet ve |
| 18 | click Cancel | Staff | CancelTicketFrm | Staff nhan nut Cancel ticket |
| 19 | calculate fine | CancelTicketFrm | Logic | Tinh tien phat: (ngayKhoiHanh - ngayHuy) → neu > 7 ngay: 10%, 3-7 ngay: 30%, < 3 ngay: 50% |
| 20 | display fine invoice | CancelTicketFrm | UI | Hien thi hoa don phat: thong tin ve + muc phat + tien phat + tien hoan lai |
| 21 | click OK | Staff | CancelTicketFrm | Staff xac nhan huy ve |
| 22 | updateStatus() | CancelTicketFrm | InvoiceDAO | Goi InvoiceDAO.updateStatus(invoiceId, "cancelled") |
| 23 | UPDATE DB | InvoiceDAO | Database | UPDATE tblInvoice SET status = 'cancelled' |
| 24 | return true | InvoiceDAO | CancelTicketFrm | Tra ve true |
| 25 | addCancellation() | CancelTicketFrm | CancellationInvoiceDAO | Tao CancellationInvoice, goi addCancellation() |
| 26 | INSERT DB | CancellationInvoiceDAO | Database | INSERT INTO tblCancellationInvoice |
| 27 | return true | CancellationInvoiceDAO | CancelTicketFrm | Tra ve true |
| 28 | print refund slip | CancelTicketFrm | UI | In bien lai hoan tien |
| 29 | show success | CancelTicketFrm | UI | Hien thi thong bao "Huy ve thanh cong" |
| 30 | return | CancelTicketFrm | HomeFrm | Quay ve giao dien Home |

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
| ID | code | invoiceDate | representativeName | totalAmount | numGuests | status | customerID | userID |
|----|------|-------------|-------------------|-------------|-----------|--------|------------|--------|
| 1 | TK001 | 01/06/2026 | Nguyen Van A | 5000000 | 2 | active | 1 | 1 |

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
| 5 | Nhan nut Cancel ticket | He thong tinh phat: con 34 ngay truoc khoi hanh (15/07/2026 - 11/06/2026) → phat 10%. Hoa don phat hien thi: tong tien goc 5,000,000 VND, phat 10% = 500,000 VND, hoan lai 4,500,000 VND |
| 6 | Kiem tra DB truoc khi nhan OK | tblInvoice: status van la "active" (chua thay doi). tblCancellationInvoice: van rong (chua them dong moi). |
| 7 | Nhan nut OK | He thong luu vao database. Thong bao "Huy ve thanh cong". Bien lai hoan tien duoc in: ma ve TK001, tien phat 500,000 VND, tien hoan lai 4,500,000 VND |
| 8 | Nhan OK | Quay ve giao dien Home |

### Database sau khi test

**tblInvoice:** (cap nhat 1 dong)
| ID | code | invoiceDate | representativeName | totalAmount | numGuests | status | customerID | userID |
|----|------|-------------|-------------------|-------------|-----------|--------|------------|--------|
| 1 | TK001 | 01/06/2026 | Nguyen Van A | 5000000 | 2 | **cancelled** | 1 | 1 |

**tblCancellationInvoice:** (them 1 dong)
| ID | cancelDate | fineRate | fineAmount | refundAmount | invoiceID | userID |
|----|------------|----------|------------|--------------|-----------|--------|
| 1 | 11/06/2026 | 0.10 | 500000 | 4500000 | 1 | 1 |

**tblInvoiceDetail:** (khong thay doi)

**tblTour:** (khong thay doi)

**tblTourDeparture:** (khong thay doi)

**tblCustomer:** (khong thay doi)

**tblUser:** (khong thay doi)
