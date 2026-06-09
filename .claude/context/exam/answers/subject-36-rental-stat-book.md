# Subject No. 36 — Book Rental — Module "Statistics of book"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Thống kê sách thuê nhiều nhất

### Mô tả ngôn ngữ tự nhiên

Nhân viên chọn chức năng thống kê sách được thuê nhiều nhất. Nhân viên nhập khoảng thời gian (ngày bắt đầu, ngày kết thúc). Hệ thống hiển thị danh sách các đầu sách được thuê trong khoảng thời gian đó, mỗi dòng gồm: mã sách, tên sách, tác giả, nhà xuất bản, năm xuất bản, tổng lần thuê, tổng doanh thu. Danh sách sắp xếp giảm dần theo tổng lần thuê, nếu bằng thì sắp xếp giảm dần theo tổng doanh thu. Nhân viên click vào một dòng sách để xem chi tiết các hóa đơn mượn sách đó: mã hóa đơn, tên người mượn, ngày mượn, ngày trả, tổng tiền thanh toán.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Nhân viên (Staff) đăng nhập hệ thống, chọn **Statistics** → **Statistics of book**. |
| 2 | Hệ thống hiển thị giao diện BookStatFrm: ô nhập ngày bắt đầu (`inStartDate`), ô nhập ngày kết thúc (`inEndDate`), nút View (`subView`). |
| 3 | Staff nhập ngày bắt đầu "01/01/2026" vào ô `inStartDate`, nhập ngày kết thúc "31/12/2026" vào ô `inEndDate`. |
| 4 | Staff nhấn nút **View** (`subView`). |
| 5 | Hệ thống truy vấn database, hiển thị bảng thống kê `outStatTable` gồm các cột: mã sách (code), tên sách (name), tác giả (author), nhà xuất bản (publisher), năm xuất bản (pubYear), tổng lần thuê (totalTimes), tổng doanh thu (totalRevenue). Sắp xếp giảm dần theo totalTimes, nếu bằng thì giảm dần theo totalRevenue. |
| 6 | Bảng hiển thị: "Lap trinh Java" (50 lần, 2,500,000đ), "Co so du lieu" (40 lần, 1,600,000đ), "Cau truc du lieu" (30 lần, 1,200,000đ), ... |
| 7 | Staff click vào dòng "Lap trinh Java". |
| 8 | Hệ thống hiển thị bảng chi tiết hóa đơn `outDetailTable` của sách "Lap trinh Java" trong kỳ thống kê: mã hóa đơn (rentalSlipId), tên người mượn (customerName), ngày mượn (rentalDate), ngày trả (returnDate), tổng tiền thanh toán (totalAmount). |
| 9 | Bảng chi tiết hiển thị: RS001 - Nguyen Van A - 01/03/2026 - 08/03/2026 - 35,000đ, RS005 - Tran Thi B - 15/04/2026 - 22/04/2026 - 35,000đ, ... |

### Ngoại lệ

| Bước | Ngoại lệ | Xử lý |
|------|----------|-------|
| 5 | Không có sách nào được thuê trong kỳ | Hệ thống hiển thị bảng trống với thông bákhong co du lieu". |
| 3 | Ngày bắt đầu > ngày kết thúc | Hệ thống hiển thị loi"Ngay bat dau phai truoc ngay ket thuc". |
| 3 | Để trống ngày bắt đầu hoặc kết thúc | Hệ thống hiển thị loi"Vui long nhap day du ngay bat dau va ket thuc". |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. **Đầu sách** (BookTitle) có mã, tên, tác giả, nhà xuất bản, năm xuất bản, giá thuê/ngày, số lượng. **Khách hàng** (Customer) có mã, tên, CMND, điện thoại, địa chỉ. Mỗi lần thuê tạo **phiếu mượn** (RentalSlip) với ngày mượn và tổng số sách. Mỗi phiếu mượn có nhiều **chi tiết phiếu mượn** (RentalSlipDetail), mỗi chi tiết liên kết đến một đầu sách, lưu giá thuê, ngày trả, trạng thái, tiền phạt. **Nhân viên** (User) là người lập phiếu mượn.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| BookTitle | Entity | Đối tượng chính, có thuộc tính riêng |
| Customer | Entity | Đối tượng chính, có thuộc tính riêng |
| RentalSlip | Entity | Đối tượng phiếu mượn |
| RentalSlipDetail | Entity | Đối tượng chi tiết phiếu mượn |
| User | Entity | Đối tượng nhân viên |
| totalTimes | Derived attribute | Được tính từ RentalSlipDetail (COUNT) |
| totalRevenue | Derived attribute | Được tính từ RentalSlipDetail (SUM) |

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1: Staff đăng nhập, chọn Statistics → Statistics of book. View class: **HomeFrm**, **BookStatFrm**.
Bước 2: Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. UI: `inStartDate` (JTextField — nhập ngày bắt đầu), `inEndDate` (JTextField — nhập ngày kết thúc), `subView` (JButton — xem thống kê).
Bước 3-4: Staff nhập ngày, nhấn View.
Bước 5-6: Hệ thống hiển thị bảng thống kê sách. UI: `outStatTable` (JTable — bảng thống kê sách: mã, tên, tác giả, NXB, năm, tổng lần thuê, tổng doanh thu).
Bước 7: Staff click vào dòng sách "Lap trinh Java".
Bước 8-9: Hệ thống hiển thị bảng chi tiết hóa đơn. UI: `outDetailTable` (JTable — chi tiết hóa đơn: mã hóa đơn, tên người mượn, ngày mượn, ngày trả, tổng tiền).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Statistics of book |
| BookStatFrm | Form | Giao diện thống kê sách thuê nhiều nhất |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inStartDate` | JTextField | BookStatFrm | Ô nhập ngày bắt đầu kỳ thống kê |
| `inEndDate` | JTextField | BookStatFrm | Ô nhập ngày kết thúc kỳ thống kê |
| `subView` | JButton | BookStatFrm | Nút View — xem thống kê |
| `outStatTable` | JTable | BookStatFrm | Bảng thống kê sách (code, name, author, publisher, pubYear, totalTimes, totalRevenue) |
| `outDetailTable` | JTable | BookStatFrm | Bảng chi tiết hóa đơn của 1 sách (rentalSlipId, customerName, rentalDate, returnDate, totalAmount) |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getBookStatistics()` | startDate, endDate | List\<BookStatDTO\> | BookTitle, RentalSlipDetail |
| `getRentalDetailsByBookTitle()` | bookTitleId, startDate, endDate | List\<RentalDetailDTO\> | RentalSlipDetail, RentalSlip, Customer |

**Tong hop:**

- View classes: HomeFrm, BookStatFrm
- Methods: getBookStatistics(), getRentalDetailsByBookTitle()

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu | Mô tả |
|-----------|----------|------|-------|
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu mượn có nhiều chi tiết |
| BookTitle | RentalSlipDetail | 1 : N | Một đầu sách xuất hiện trong nhiều chi tiết phiếu mượn |
| User | RentalSlip | 1 : N | Một nhân viên lập nhiều phiếu mượn |

### Bảng thuộc tính entity

| Entity | Thuộc tính |
|--------|-----------|
| BookTitle | bookTitleId (PK), code, name, author, publisher, pubYear, rentalPrice (double), quantity (int) |
| Customer | customerId (PK), code, name, idCard, phone, address |
| RentalSlip | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate (Date), totalBooks (int) |
| RentalSlipDetail | rentalSlipDetailId (PK), rentalSlipId (FK), bookTitleId (FK), rentalPrice (double), returnDate (Date), status (String), fine (double) |
| User | userId (PK), username, password, role |

### ASCII Class Diagram

```
+------------------+         +------------------+
|    BookTitle     |         |     Customer     |
+------------------+         +------------------+
| bookTitleId (PK) |         | customerId (PK)  |
| code             |         | code             |
| name             |         | name             |
| author           |         | idCard           |
| publisher        |         | phone            |
| pubYear          |         | address          |
| rentalPrice      |         +------------------+
| quantity         |                  |
+------------------+                  | 1
        |                             |
        | 1                           | N
        |                    +------------------+
        | N                  |    RentalSlip    |
+-------------------------+  +------------------+
|   RentalSlipDetail      |  | rentalSlipId (PK)|
+-------------------------+  | customerId (FK)  |
| rentalSlipDetailId (PK) |  | userId (FK)      |
| rentalSlipId (FK)       |  | rentalDate       |
| bookTitleId (FK)        |  | totalBooks       |
| rentalPrice             |  +------------------+
| returnDate              |          | 1
| status                  |          |
| fine                    |          | N
+-------------------------+   +------------------+
                              |      User        |
                              +------------------+
                              | userId (PK)      |
                              | username         |
                              | password         |
                              | role             |
                              +------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác |
|------|----------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class box (hình chữ nhật 3 ngăn): BookTitle, Customer, RentalSlip, RentalSlipDetail, User |
| 3 | Tạo các view class box từ giao diện: HomeFrm, BookStatFrm |
| 4 | Vẽ các đường quan hệ (association) giữa các class |
| 5 | Ghi multiplicity và role name cho mỗi quan hệ |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>` hoặc `<<boundary>>` rồi đến tên class. Ví dụ: `<<entity>> BookTitle`, `<<boundary>> BookStatFrm`
- **Ngăn 2 (thuộc tính):** Ghi từng thuộc tính theo format `-tenThuocTinh: KieuDuLieu`. Dùng `-` cho private. Ví dụ: `-bookTitleId: int`, `-rentalPrice: double`
- **Ngăn 3 (phương thức):** Ghi từng phương thức theo format `+tenPhuongThuc(thamSo): KieuTraVe`. Dùng `+` cho public. Ví dụ: `+getBookStatistics(startDate: Date, endDate: Date): List<BookStatDTO>`

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| BookTitle | `<<entity>>` | `-bookTitleId: int`, `-code: String`, `-name: String`, `-author: String`, `-publisher: String`, `-pubYear: int`, `-rentalPrice: double`, `-quantity: int` | `+getBookStatistics(startDate: Date, endDate: Date): List<BookStatDTO>` |
| Customer | `<<entity>>` | `-customerId: int`, `-code: String`, `-name: String`, `-idCard: String`, `-phone: String`, `-address: String` | — |
| RentalSlip | `<<entity>>` | `-rentalSlipId: int`, `-customerId: int`, `-userId: int`, `-rentalDate: Date`, `-totalBooks: int` | — |
| RentalSlipDetail | `<<entity>>` | `-rentalSlipDetailId: int`, `-rentalSlipId: int`, `-bookTitleId: int`, `-rentalPrice: double`, `-returnDate: Date`, `-status: String`, `-fine: double` | `+getRentalDetailsByBookTitle(bookTitleId: int, startDate: Date, endDate: Date): List<RentalDetailDTO>` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

**HomeFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `subStatBook` | sub | JButton | Nút mở chức năng Statistics of book |

**BookStatFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `inStartDate` | in | JTextField | Ô nhập ngày bắt đầu kỳ thống kê |
| `inEndDate` | in | JTextField | Ô nhập ngày kết thúc kỳ thống kê |
| `subView` | sub | JButton | Nút View — xem thống kê |
| `outStatTable` | out | JTable | Bảng thống kê sách (code, name, author, publisher, pubYear, totalTimes, totalRevenue) |
| `outDetailTable` | out | JTable | Bảng chi tiết hóa đơn của 1 sách |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường giữa 2 class |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) ở đầu "chứa" | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) ở đầu "chứa" | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed (nét đứt), mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (view class gọi DAO class) |

#### 6. Cách ghi multiplicity

- **1..1** → ghi `1` ở một đầu của đường kết nối
- **0..\*** hoặc **1..\*** → ghi `n` hoặc `*` ở đầu kia
- Ghi multiplicity ở **cả 2 đầu** của đường kết nối

Ví dụ: BookTitle(1) ←——→ (n) RentalSlipDetail nghĩa là 1 đầu sách xuất hiện trong nhiều chi tiết phiếu mượn.

#### 7. Bảng quan hệ chi tiết (Visual Paradigm)

| Từ | Đến | Kiểu quan hệ | Multiplicity | Role name | Giải thích |
|----|-----|---------------|-------------|-----------|------------|
| Customer | RentalSlip | Association | 1 — n | borrows | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | Composition | 1 — n | contains | Chi tiết không tồn tại nếu không có phiếu mượn |
| BookTitle | RentalSlipDetail | Association | 1 — n | references | Một đầu sách xuất hiện trong nhiều chi tiết |
| User | RentalSlip | Association | 1 — n | creates | Một nhân viên lập nhiều phiếu mượn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Customer → RentalSlip (Association 1-n)**

1. Kéo class `Customer` và `RentalSlip` lên canvas.
2. Chọn công cụ **Association** (đường liền nét mũi tên rỗng) từ toolbox.
3. Click vào `Customer`, kéo sang `RentalSlip`.
4. Click chuột phải vào đường kết nối → **Open Specification**.
5. Tại mục **From**: multiplicity = `1`, role name = `borrows`.
6. Tại mục **To**: multiplicity = `*`.
7. Nhấn OK.

**Ví dụ 2: Vẽ quan hệ RentalSlip → RentalSlipDetail (Composition 1-n)**

1. Chọn công cụ **Composition** (đường liền nét đầu kim cương filled ◆) từ toolbox.
2. Click vào `RentalSlip` (phía "chứa"), kéo sang `RentalSlipDetail` (phía "bị chứa").
3. Click chuột phải → **Open Specification**.
4. Tại mục **From** (RentalSlip): multiplicity = `1`.
5. Tại mục **To** (RentalSlipDetail): multiplicity = `*`, role name = `contains`.
6. Nhấn OK. Đầu kim cương filled (◆) nằm phía `RentalSlip`.

---

## Câu 3: Thiết kế tĩnh

### View Classes

**BookStatFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `inStartDate` | JTextField | Ô nhập ngày bắt đầu kỳ thống kê |
| `inEndDate` | JTextField | Ô nhập ngày kết thúc kỳ thống kê |
| `subView` | JButton | Nút "View" xem thống kê |
| `outStatTable` | JTable | Bảng thống kê sách (code, name, author, publisher, pubYear, totalTimes, totalRevenue) |
| `outDetailTable` | JTable | Bảng chi tiết hóa đơn của 1 sách (rentalSlipId, customerName, rentalDate, returnDate, totalAmount) |

**HomeFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `subStatBook` | JButton | Nút mở chức năng Statistics of book |

### DAO Classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| BookTitleDAO | `getBookStatistics(startDate, endDate)` | `List<BookStatDTO>` | Lấy danh sách sách kèm tổng lần thuê và tổng doanh thu trong kỳ, sắp xếp giảm dần theo totalTimes rồi totalRevenue |
| RentalSlipDetailDAO | `getRentalDetailsByBookTitle(bookTitleId, startDate, endDate)` | `List<RentalDetailDTO>` | Lấy danh sách chi tiết hóa đơn mượn sách theo đầu sách trong kỳ thống kê |
| CustomerDAO | `getCustomerById(customerId)` | `Customer` | Lấy thông tin khách hàng theo mã |

**DTO Classes:**

| DTO | Thuộc tính |
|-----|-----------|
| BookStatDTO | bookTitleId, code, name, author, publisher, pubYear, totalTimes (int), totalRevenue (double) |
| RentalDetailDTO | rentalSlipId, customerName, rentalDate, returnDate, totalAmount (double) |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| BookTitle | Entity | id: int (PK), code: String, name: String, author: String, publisher: String, pubYear: int, rentalPrice: double, quantity: int |
| Customer | Entity | id: int (PK), code: String, name: String, idCard: String, phone: String, address: String |
| RentalSlip | Entity | id: int (PK), customer: Customer (object), user: User (object), rentalDate: Date, totalBooks: int |
| RentalSlipDetail | Entity | id: int (PK), rentalSlip: RentalSlip (object), bookTitle: BookTitle (object), rentalPrice: double, returnDate: Date, status: String, fine: double |
| User | Entity | id: int (PK), username: String, password: String, role: String |

### Entity Types (Design)

| Entity | Kiểu thuộc tính |
|--------|----------------|
| BookTitle | bookTitleId: int, code: String, name: String, author: String, publisher: String, pubYear: int, rentalPrice: double, quantity: int |
| Customer | customerId: int, code: String, name: String, idCard: String, phone: String, address: String |
| RentalSlip | rentalSlipId: int, customerId: int, userId: int, rentalDate: Date, totalBooks: int |
| RentalSlipDetail | rentalSlipDetailId: int, rentalSlipId: int, bookTitleId: int, rentalPrice: double, returnDate: Date, status: String, fine: double |
| User | userId: int, username: String, password: String, role: String |

### Database Schema

**tblCustomer**

| Column | Type | Constraint |
|--------|------|------------|
| customerId | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | VARCHAR(100) | NOT NULL |
| idCard | VARCHAR(20) | |
| phone | VARCHAR(15) | |
| address | VARCHAR(200) | |

**tblBookTitle**

| Column | Type | Constraint |
|--------|------|------------|
| bookTitleId | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | VARCHAR(200) | NOT NULL |
| author | VARCHAR(100) | |
| publisher | VARCHAR(100) | |
| pubYear | INT | |
| rentalPrice | DOUBLE | NOT NULL |
| quantity | INT | NOT NULL, DEFAULT 0 |

**tblRentalSlip**

| Column | Type | Constraint |
|--------|------|------------|
| rentalSlipId | INT | PK, AUTO_INCREMENT |
| customerId | INT | FK → tblCustomer(customerId) |
| userId | INT | FK → tblUser(userId) |
| rentalDate | DATE | NOT NULL |
| totalBooks | INT | NOT NULL |

**tblRentalSlipDetail**

| Column | Type | Constraint |
|--------|------|------------|
| rentalSlipDetailId | INT | PK, AUTO_INCREMENT |
| rentalSlipId | INT | FK → tblRentalSlip(rentalSlipId) |
| bookTitleId | INT | FK → tblBookTitle(bookTitleId) |
| rentalPrice | DOUBLE | NOT NULL |
| returnDate | DATE | NULL |
| status | VARCHAR(50) | NULL |
| fine | DOUBLE | DEFAULT 0 |

**tblUser**

| Column | Type | Constraint |
|--------|------|------------|
| userId | INT | PK, AUTO_INCREMENT |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

### Hướng dẫn vẽ trên Visual Paradigm

1. Tạo Class Diagram mới.
2. Tạo 5 class: `BookTitle`, `Customer`, `RentalSlip`, `RentalSlipDetail`, `User`.
3. Thêm attributes cho từng class (dùng visibility `-` cho private).
4. Vẽ Association từ `Customer` → `RentalSlip` (1..*).
5. Vẽ Association từ `RentalSlip` → `RentalSlipDetail` (1..*).
6. Vẽ Association từ `BookTitle` → `RentalSlipDetail` (1..*).
7. Vẽ Association từ `User` → `RentalSlip` (1..*).
8. Đặt tên association: "borrows", "contains", "references", "processes".

---

## Câu 4: Sequence Diagram

### Hướng dẫn vẽ trên Visual Paradigm

1. Tạo Sequence Diagram mới.
2. Tạo lifelines: `:Staff` (Actor), `:BookStatFrm` (Boundary), `:BookTitleDAO` (Control), `:RentalSlipDetailDAO` (Control), `:CustomerDAO` (Control).
3. Vẽ message flow theo bảng bên dưới.
4. Sử dụng synchronous message cho request, return message cho response.
5. Sử dụng `alt` fragment cho nhánh ngoại lệ (không có dữ liệu).

### ASCII Sequence Diagram

```
Staff              BookStatFrm          BookTitleDAO       RentalSlipDetailDAO    CustomerDAO
  |                     |                     |                     |                    |
  |--- select --------->|                     |                     |                    |
  |    StatBook         |                     |                     |                    |
  |                     |                     |                     |                    |
  |--- enterDates ----->|                     |                     |                    |
  |    (startDate,      |                     |                     |                    |
  |     endDate)        |                     |                     |                    |
  |                     |                     |                     |                    |
  |--- clickView ------>|                     |                     |                    |
  |                     |--- getBookStat -----|                     |                    |
  |                     |    (dates) -------->|                     |                    |
  |                     |                     |                     |                    |
  |                     |<-- List<BookStatDTO>|                     |                    |
  |                     |                     |                     |                    |
  |<-- showStatTable ---|                     |                     |                    |
  |                     |                     |                     |                    |
  |--- clickBook ------>|                     |                     |                    |
  |    (bookTitleId)    |                     |                     |                    |
  |                     |--- getRentalDetails |                     |                    |
  |                     |    ByBookTitle() ---|-------------------->|                    |
  |                     |                     |                     |                    |
  |                     |<-- List<RentalDetail|--------------------|                    |
  |                     |     DTO>            |                     |                    |
  |                     |                     |                     |                    |
  |                     |--- getCustomerById()|                     |                    |
  |                     |                     |----------------------------------------->|
  |                     |                     |                     |                    |
  |                     |<-- Customer --------|------------------------------------------|
  |                     |                     |                     |                    |
  |<-- showDetailTable -|                     |                     |                    |
```

### Bảng message flow

| # | Từ | Đến | Message | Ghi chú |
|---|-----|------|---------|---------|
| 1 | Staff | HomeFrm | `clickMenu("Statistics")` | Chọn menu Statistics từ giao diện Home |
| 2 | HomeFrm | BookStatFrm | `show()` | Mở giao diện thống kê sách |
| 3 | BookStatFrm | BookStatFrm | `showStatForm()` | Hiển thị form với ô ngày bắt đầu, ô ngày kết thúc, nút View |
| 4 | Staff | BookStatFrm | `enterStartDate("01/01/2026")` | Nhập ngày bắt đầu vào ô inStartDate |
| 5 | Staff | BookStatFrm | `enterEndDate("31/12/2026")` | Nhập ngày kết thúc vào ô inEndDate |
| 6 | Staff | BookStatFrm | `clickView()` | Nhấn nút View |
| 7 | BookStatFrm | BookStatFrm | `validateDates()` | Kiểm tra: startDate <= endDate, không trống |
| 8 | BookStatFrm | BookTitleDAO | `getBookStatistics(startDate, endDate)` | Gọi DAO lấy thống kê sách theo kỳ |
| 9 | BookTitleDAO | Database | `executeQuery(SQL)` | Truy vấn: SELECT bookTitle, COUNT(detail), SUM(amount) GROUP BY bookTitle |
| 10 | BookTitleDAO | BookStatFrm | `return List<BookStatDTO>` | Trả về danh sách thống kê (bookTitleId, code, name, author, publisher, pubYear, totalTimes, totalRevenue) |
| 11 | BookStatFrm | BookStatFrm | `sortStatList(list)` | Sắp xếp: totalTimes giảm, rồi totalRevenue giảm |
| 12 | BookStatFrm | BookStatFrm | `displayStatTable(list)` | Hiển thị bảng thống kê ra outStatTable |
| 13 | Staff | BookStatFrm | `clickBook(bookTitleId=1)` | Click vào dòng "Lap trinh Java" |
| 14 | BookStatFrm | RentalSlipDetailDAO | `getRentalDetailsByBookTitle(bookTitleId=1, startDate, endDate)` | Lấy chi tiết hóa đơn mượn sách này |
| 15 | RentalSlipDetailDAO | Database | `executeQuery(SQL)` | Truy vấn: SELECT slip, customer, dates, amount FROM detail JOIN slip JOIN customer |
| 16 | RentalSlipDetailDAO | BookStatFrm | `return List<RentalDetailDTO>` | Trả về danh sách chi tiết (rentalSlipId, customerName, rentalDate, returnDate, totalAmount) |
| 17 | BookStatFrm | BookStatFrm | `sortDetailList(list)` | Sắp xếp chi tiết theo ngày mượn |
| 18 | BookStatFrm | BookStatFrm | `displayDetailTable(list)` | Hiển thị bảng chi tiết hóa đơn ra outDetailTable |
| 19 | Staff | BookStatFrm | `clickBack()` | Nhấn nút Back để quay lại bảng tổng hợp |
| 20 | BookStatFrm | BookStatFrm | `showStatTable()` | Hiển thị lại bảng thống kê tổng hợp |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên testcase | Mô tả |
|----|-------------|-------|
| TC01 | Thống kê sách trong kỳ có dữ liệu | Staff nhập kỳ thống kê, xem danh sách sách và chi tiết hóa đơn |
| TC02 | Thống kê sách trong kỳ không có dữ liệu | Staff nhập kỳ thống kê không có phiếu mượn nào |
| TC03 | Ngày bắt đầu > ngày kết thúc | Staff nhập ngày không hợp lệ |

### TC01: Thống kê sách trong kỳ có dữ liệu

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

**tblCustomer**

| customerId | code | name | idCard | phone | address |
|------------|------|------|--------|-------|---------|
| 1 | KH001 | Nguyen Van A | 012345678901 | 0901234567 | Ha Noi |
| 2 | KH002 | Tran Thi B | 098765432101 | 0912345678 | TP.HCM |
| 3 | KH003 | Pham Van D | 011111111111 | 0933333333 | Da Nang |

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |
| 2 | BT002 | Co so du lieu | Tran Van C | DHQG | 2022 | 4000 | 8 |
| 3 | BT003 | Cau truc du lieu | Le Van E | DHQG | 2021 | 3000 | 12 |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalBooks |
|--------------|------------|--------|------------|------------|
| 1 | 1 | 1 | 2026-03-01 | 2 |
| 2 | 2 | 1 | 2026-04-15 | 1 |
| 3 | 3 | 1 | 2026-05-10 | 2 |
| 4 | 1 | 1 | 2026-06-01 | 1 |

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice | returnDate | status | fine |
|--------------------|-------------|-------------|-------------|------------|--------|------|
| 1 | 1 | 1 | 5000 | 2026-03-08 | binh thuong | 0 |
| 2 | 1 | 2 | 4000 | 2026-03-08 | binh thuong | 0 |
| 3 | 2 | 1 | 5000 | 2026-04-22 | binh thuong | 0 |
| 4 | 3 | 1 | 5000 | 2026-05-17 | binh thuong | 0 |
| 5 | 3 | 3 | 3000 | 2026-05-17 | binh thuong | 0 |
| 6 | 4 | 1 | 5000 | NULL | NULL | 0 |

#### Kịch bản test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Staff chọn Statistics of book | — | Hiển thị BookStatFrm với ô nhập ngày bắt đầu, ngày kết thúc, nút View |
| 3 | Staff nhập ngày bắt đầu | "01/01/2026" vào ô inStartDate | Ô inStartDate hiển thị "01/01/2026" |
| 4 | Staff nhập ngày kết thúc | "31/12/2026" vào ô inEndDate | Ô inEndDate hiển thị "31/12/2026" |
| 5 | Staff nhấn View | Nhấn nút View | Hệ thống hiển thị bảng thống kê gồm 3 dòng: BT001 Lap trinh Java (4 lần, 1,315,000đ), BT002 Co so du lieu (1 lần, 28,000đ), BT003 Cau truc du lieu (1 lần, 21,000đ) — sắp xếp giảm dần theo tổng lần thuê |
| 6 | Staff click dòng "Lap trinh Java" | Click dòng BT001 | Hệ thống hiển thị bảng chi tiết hóa đơn: 4 dòng — RS001 (Nguyen Van A, 01/03, 08/03, 35,000đ), RS002 (Tran Thi B, 15/04, 22/04, 35,000đ), RS003 (Pham Van D, 10/05, 17/05, 35,000đ), RS004 (Nguyen Van A, 01/06, NULL, đang mượn) |
| 7 | Staff click dòng "Co so du lieu" | Click dòng BT002 | Hệ thống hiển thị bảng chi tiết hóa đơn: 1 dòng — RS001 (Nguyen Van A, 01/03, 08/03, 28,000đ) |

#### Database sau khi test

- Không thay đổi (chức năng chỉ đọc dữ liệu thống kê)

**tblUser**: không thay đổi

**tblCustomer**: không thay đổi

**tblBookTitle**: không thay đổi

**tblRentalSlip**: không thay đổi

**tblRentalSlipDetail**: không thay đổi

---

### TC02: Thống kê sách trong kỳ không có dữ liệu

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |

**tblRentalSlip**: rỗng

**tblRentalSlipDetail**: rỗng

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công |
| 2 | Staff chọn Statistics of book | — | Hiển thị BookStatFrm |
| 3 | Staff nhập ngày bắt đầu | "01/01/2026" | Ô inStartDate hiển thị "01/01/2026" |
| 4 | Staff nhập ngày kết thúc | "31/12/2026" | Ô inEndDate hiển thị "31/12/2026" |
| 5 | Staff nhấn View | Nhấn nút View | Hệ thống hiển thị bảng trống với thông bkhong co du lieu trong khoang thoi gian nay" |

#### Database sau khi test

- Không thay đổi (chức năng chỉ đọc dữ liệu thống kê)

**tblUser**: không thay đổi

**tblBookTitle**: không thay đổi

**tblRentalSlip**: không thay đổi

**tblRentalSlipDetail**: không thay đổi

---

### TC03: Ngày bắt đầu sau ngày kết thúc

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công |
| 2 | Staff chọn Statistics of book | — | Hiển thị BookStatFrm |
| 3 | Staff nhập ngày bắt đầu | "31/12/2026" | Ô inStartDate hiển thị "31/12/2026" |
| 4 | Staff nhập ngày kết thúc | "01/01/2026" | Ô inEndDate hiển thị "01/01/2026" |
| 5 | Staff nhấn View | Nhấn nút View | Hệ thống hiệnh loi"Ngay bat dau phai truoc ngay ket thuc" |

#### Database sau khi test

- Không thay đổi
