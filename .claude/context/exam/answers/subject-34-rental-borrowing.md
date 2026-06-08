# Subject No. 34 — Book Rental — Module "Borrowing"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Mượn sách (thuê sách)

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. Cửa hàng có nhiều đầu sách với số lượng và giá thuê khác nhau (tính theo ngày). Mỗi đầu sách có thể được nhiều khách hàng thuê. Mỗi lần thuê, khách hàng nhận một phiếu mượn gồm: dòng đầu là tên khách hàng và ngày mượn, mỗi dòng tiếp theo là tên sách, tác giả, nhà xuất bản, năm xuất bản, giá thuê, dòng cuối là tổng số sách mượn.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Nhân viên (Staff) đăng nhập hệ thống, chọn chức năng **Borrowing**. |
| 2 | Hệ thống hiển thị giao diện Borrowing: ô nhập tên khách hàng (`inCustomerName`), nút Search (`subSearchCustomer`). |
| 3 | Staff nhập tên khách hàng "Nguyen Van A" vào ô `inCustomerName`, nhấn nút **Search**. |
| 4 | Hệ thống tìm kiếm trong bảng `tblCustomer`, hiển thị danh sách khách hàng phù hợp trong bảng `outsubListCustomer` (cột: mã KH, tên KH, CMND, điện thoại, địa chỉ). Staff chọn đúng khách hàng "Nguyen Van A" (hoặc nhấn nút **Add new** nếu chưa có). |
| 5 | Hệ thống hiển thị giao diện thêm sách mượn: ô nhập tên sách (`inBookName`), nút Search (`subSearchBook`), danh sách mượn (`outListRentalDetail`). |
| 6 | Staff nhập "Lap trinh Java" vào ô `inBookName`, nhấn nút **Search**. |
| 7 | Hệ thống tìm kiếm trong bảng `tblBookTitle`, hiển thị danh sách đầu sách phù hợp trong bảng `outsubListBook` (cột: mã sách, tên sách, tác giả, nhà xuất bản, năm xuất bản, giá thuê/ngày, số lượng còn). |
| 8 | Staff click chọn dòng "Lap trinh Java" (tac giả: Nguyen Van B, NXB: DHQG, năm: 2023, giá thuê: 5,000đ/ngày). Hệ thống thêm 1 dòng vào danh sách mượn `outListRentalDetail`. |
| 9 | Staff lặp lại: nhập "Co so du lieu", nhấn Search, chọn "Co so du lieu" (tac giả: Tran Van C, NXB: DHQG, năm: 2022, giá thuê: 4,000đ/ngày). Hệ thống thêm dòng thứ 2 vào danh sách mượn. |
| 10 | Staff xem lại danh sách mượn gồm 2 đầu sách, nhấn nút **Create loan slip** (`subCreateSlip`). |
| 11 | Hệ thống lưu thông tin vào bảng `tblRentalSlip` (1 phiếu) và `tblRentalSlipDetail` (2 chi tiết), sau đó in phiếu mượn. Phiếu mượn gồm: dòng đầu = tên KH "Nguyen Van A" + ngày mượn "08/06/2026", dòng 1 = "Lap trinh Java / Nguyen Van B / DHQG / 2023 / 5,000đ", dòng 2 = "Co so du lieu / Tran Van C / DHQG / 2022 / 4,000đ", dòng cuối = "Tong so sach: 2". |
| 12 | Hệ thống hiển thị thông báo "Tao phieu muon thanh cong" và phiếu mượn đã in. |

### Ngoại lệ

| Bước | Ngoại lệ | Xử lý |
|------|----------|-------|
| 4 | Không tìm thấy khách hàng nào | Hệ thống hiển thị "Khong tim thay khach hang". Staff nhấn **Add new** để thêm khách hàng mới. |
| 7 | Không tìm thấy sách nào | Hệ thống hiển thị "Khong tim thay dau sach". Staff nhập lại tên sách khác. |
| 8 | Số lượng sách còn = 0 | Hệ thống hiển thị "Sach da het hang", không cho phép chọn. |
| 10 | Danh sách mượn trống | Nút **Create loan slip** bị disable, Staff không thể nhấn. |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. Cửa hàng có nhiều **đầu sách** (BookTitle). Mỗi đầu sách có mã, tên, tác giả, nhà xuất bản, năm xuất bản, giá thuê mỗi ngày, số lượng tồn. **Khách hàng** (Customer) có mã, tên, CMND, điện thoại, địa chỉ. Mỗi lần thuê tạo một **phiếu mượn** (RentalSlip) ghi nhận ngày mượn và tổng số sách. Mỗi phiếu mượn có nhiều **chi tiết phiếu mượn** (RentalSlipDetail), mỗi chi tiết liên kết đến một đầu sách và lưu giá thuê tại thời điểm mượn. **Nhân viên** (User) là người lập phiếu mượn.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| BookTitle | Entity | Đối tượng chính, có thuộc tính riêng |
| Customer | Entity | Đối tượng chính, có thuộc tính riêng |
| RentalSlip | Entity | Đối tượng đại diện phiếu mượn |
| RentalSlipDetail | Entity | Đối tượng chi tiết phiếu mượn |
| User | Entity | Đối tượng nhân viên đăng nhập |
| id, code, name, author, publisher, pubYear, rentalPrice, quantity | Attribute | Thuộc tính của BookTitle |
| id, code, name, idCard, phone, address | Attribute | Thuộc tính của Customer |
| id, rentalDate, totalBooks | Attribute | Thuộc tính của RentalSlip |
| id, rentalPrice | Attribute | Thuộc tính của RentalSlipDetail |
| id, username, password, role | Attribute | Thuộc tính của User |

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
| RentalSlipDetail | rentalSlipDetailId (PK), rentalSlipId (FK), bookTitleId (FK), rentalPrice (double) |
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
        | 1                           |
        |                             | N
        | N                  +------------------+
+-------------------------+  |    RentalSlip    |
|   RentalSlipDetail      |  +------------------+
+-------------------------+  | rentalSlipId (PK)|
| rentalSlipDetailId (PK) |  | customerId (FK)  |
| rentalSlipId (FK)       |  | userId (FK)      |
| bookTitleId (FK)        |  | rentalDate       |
| rentalPrice             |  | totalBooks       |
+-------------------------+  +------------------+
                                     | 1
                                     |
                                     | N
                              +------------------+
                              |      User        |
                              +------------------+
                              | userId (PK)      |
                              | username         |
                              | password         |
                              | role             |
                              +------------------+
```

---

## Câu 3: Thiết kế tĩnh

### View Classes

**BorrowBookFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `inCustomerName` | JTextField | Ô nhập tên khách hàng để tìm kiếm |
| `subSearchCustomer` | JButton | Nút "Search" tìm kiếm khách hàng |
| `outsubListCustomer` | JTable | Bảng danh sách khách hàng tìm được (click để chọn) |
| `subAddCustomer` | JButton | Nút "Add new" thêm khách hàng mới |
| `inBookName` | JTextField | Ô nhập tên sách để tìm kiếm |
| `subSearchBook` | JButton | Nút "Search" tìm kiếm sách |
| `outsubListBook` | JTable | Bảng danh sách sách tìm được (click để chọn) |
| `outListRentalDetail` | JTable | Bảng danh sách sách đã chọn mượn (hiển thị: tên, tác giả, NXB, năm, giá thuê) |
| `subCreateSlip` | JButton | Nút "Create loan slip" tạo phiếu mượn |

**HomeFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `subBorrowing` | JButton | Nút mở chức năng Borrowing |

### DAO Classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| CustomerDAO | `searchCustomerByName(name)` | `List<Customer>` | Tìm khách hàng theo tên |
| CustomerDAO | `insertCustomer(customer)` | `boolean` | Thêm khách hàng mới |
| BookTitleDAO | `searchBookTitleByName(name)` | `List<BookTitle>` | Tìm đầu sách theo tên |
| BookTitleDAO | `getBookTitleById(bookTitleId)` | `BookTitle` | Lấy thông tin đầu sách theo mã |
| RentalSlipDAO | `insertRentalSlip(rentalSlip)` | `boolean` | Lưu phiếu mượn mới |
| RentalSlipDAO | `getLatestRentalSlipId()` | `int` | Lấy mã phiếu mượn vừa tạo |
| RentalSlipDetailDAO | `insertRentalSlipDetail(detail)` | `boolean` | Lưu chi tiết phiếu mượn |

### Entity Types (Design)

| Entity | Kiểu thuộc tính |
|--------|----------------|
| BookTitle | bookTitleId: int, code: String, name: String, author: String, publisher: String, pubYear: int, rentalPrice: double, quantity: int |
| Customer | customerId: int, code: String, name: String, idCard: String, phone: String, address: String |
| RentalSlip | rentalSlipId: int, customerId: int, userId: int, rentalDate: Date, totalBooks: int |
| RentalSlipDetail | rentalSlipDetailId: int, rentalSlipId: int, bookTitleId: int, rentalPrice: double |
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
4. Vẽ mối quan hệ Association từ `Customer` → `RentalSlip` (1..*), đánh multiplicity.
5. Vẽ mối quan hệ Association từ `RentalSlip` → `RentalSlipDetail` (1..*).
6. Vẽ mối quan hệ Association từ `BookTitle` → `RentalSlipDetail` (1..*).
7. Vẽ mối quan hệ Association từ `User` → `RentalSlip` (1..*).
8. Đặt tên cho mỗi association (ví dụ: "creates", "contains", "references", "borrows").

---

## Câu 4: Sequence Diagram

### Hướng dẫn vẽ trên Visual Paradigm

1. Tạo Sequence Diagram mới.
2. Tạo các lifeline: `:Staff` (Actor), `:BorrowBookFrm` (Boundary), `:CustomerDAO` (Control), `:BookTitleDAO` (Control), `:RentalSlipDAO` (Control), `:RentalSlipDetailDAO` (Control).
3. Vẽ message flow theo bảng bên dưới.
4. Sử dụng `alt` fragment cho nhánh ngoại lệ (không tìm thấy KH, sách hết hàng).
5. Sử dụng `loop` fragment cho bước lặp thêm sách.
6. Sử dụng synchronous message (mũi tên đầy) cho các request, return message (mũi tên nét đứt) cho response.

### ASCII Sequence Diagram

```
Staff          BorrowBookFrm     CustomerDAO    BookTitleDAO   RentalSlipDAO  RentalSlipDetailDAO
  |                 |                 |               |              |                |
  |--- select ----->|                 |               |              |                |
  |    Borrowing    |                 |               |              |                |
  |                 |                 |               |              |                |
  |--- enterName -->|                 |               |              |                |
  |    "Nguyen      |                 |               |              |                |
  |     Van A"      |                 |               |              |                |
  |                 |                 |               |              |                |
  |--- clickSearch->|                 |               |              |                |
  |                 |--- search ------>|               |              |                |
  |                 |    Customer()   |               |              |                |
  |                 |<-- List<Customer>|              |              |                |
  |                 |                 |               |              |                |
  |<-- showList ----|                 |               |              |                |
  |                 |                 |               |              |                |
  |--- selectCust-->|                 |               |              |                |
  |                 |                 |               |              |                |
  |--- enterBook--->|                 |               |              |                |
  |    "Lap trinh   |                 |               |              |                |
  |     Java"       |                 |               |              |                |
  |                 |                 |               |              |                |
  |--- clickSearch->|                 |               |              |                |
  |                 |--- search ----->|-------------->|              |                |
  |                 |    BookTitle()  |               |              |                |
  |                 |<-- List<BT> ----|---------------|              |                |
  |                 |                 |               |              |                |
  |<-- showList ----|                 |               |              |                |
  |                 |                 |               |              |                |
  |--- selectBook-->|                 |               |              |                |
  |                 |--- addToRentalList()            |              |                |
  |                 |                 |               |              |                |
  | (loop: repeat for more books)     |               |              |                |
  |                 |                 |               |              |                |
  |--- clickCreate->|                 |               |              |                |
  |                 |--- insertRentalSlip()---------->|------------->|                |
  |                 |<-- true -----------------------|--------------|                |
  |                 |                 |               |              |                |
  |                 |--- getLatestId()|-------------->|------------->|                |
  |                 |<-- rentalSlipId |---------------|--------------|                |
  |                 |                 |               |              |                |
  |                 |--- insertDetail()|--------------|--------------|--------------->|
  |                 |<-- true --------|---------------|--------------|----------------|
  |                 |                 |               |              |                |
  |                 |--- insertDetail()|--------------|--------------|--------------->|
  |                 |<-- true --------|---------------|--------------|----------------|
  |                 |                 |               |              |                |
  |<-- printSlip ---|                 |               |              |                |
  |<-- successMsg --|                 |               |              |                |
```

### Bảng message flow

| # | Từ | Đến | Message | Ghi chú |
|---|-----|------|---------|---------|
| 1 | Staff | BorrowBookFrm | `selectBorrowing()` | Chọn chức năng Borrowing từ HomeFrm |
| 2 | BorrowBookFrm | BorrowBookFrm | `showBorrowForm()` | Hiển thị giao diện mượn sách |
| 3 | Staff | BorrowBookFrm | `enterCustomerName("Nguyen Van A")` | Nhập tên KH vào ô inCustomerName |
| 4 | Staff | BorrowBookFrm | `clickSearchCustomer()` | Nhấn nút Search |
| 5 | BorrowBookFrm | CustomerDAO | `searchCustomerByName("Nguyen Van A")` | Gọi DAO tìm KH theo tên |
| 6 | CustomerDAO | BorrowBookFrm | `return List<Customer>` | Trả về danh sách KH |
| 7 | BorrowBookFrm | BorrowBookFrm | `displayCustomerList(list)` | Hiển thị danh sách KH ra outsubListCustomer |
| 8 | Staff | BorrowBookFrm | `selectCustomer(customerId=1)` | Click chọn KH "Nguyen Van A" |
| 9 | BorrowBookFrm | BorrowBookFrm | `showBookSearchPanel()` | Hiển thị phần tìm kiếm sách |
| 10 | Staff | BorrowBookFrm | `enterBookName("Lap trinh Java")` | Nhập tên sách vào ô inBookName |
| 11 | Staff | BorrowBookFrm | `clickSearchBook()` | Nhấn nút Search |
| 12 | BorrowBookFrm | BookTitleDAO | `searchBookTitleByName("Lap trinh Java")` | Gọi DAO tìm sách theo tên |
| 13 | BookTitleDAO | BorrowBookFrm | `return List<BookTitle>` | Trả về danh sách sách |
| 14 | BorrowBookFrm | BorrowBookFrm | `displayBookList(list)` | Hiển thị danh sách sách ra outsubListBook |
| 15 | Staff | BorrowBookFrm | `selectBook(bookTitleId=1)` | Click chọn sách "Lap trinh Java" |
| 16 | BorrowBookFrm | BorrowBookFrm | `addToRentalList(bookTitle)` | Thêm sách vào danh sách mượn |
| 17 | Staff | BorrowBookFrm | `clickCreateSlip()` | Nhấn nút Create loan slip |
| 18 | BorrowBookFrm | RentalSlipDAO | `insertRentalSlip(rentalSlip)` | Lưu phiếu mượn vào tblRentalSlip |
| 19 | RentalSlipDAO | BorrowBookFrm | `return true` | Xác nhận lưu thành công |
| 20 | BorrowBookFrm | RentalSlipDAO | `getLatestRentalSlipId()` | Lấy mã phiếu mượn vừa tạo |
| 21 | RentalSlipDAO | BorrowBookFrm | `return rentalSlipId` | Trả về mã phiếu mượn |
| 22 | BorrowBookFrm | RentalSlipDetailDAO | `insertRentalSlipDetail(detail1)` | Lưu chi tiết phiếu mượn dòng 1 |
| 23 | RentalSlipDetailDAO | BorrowBookFrm | `return true` | Xác nhận lưu thành công |
| 24 | BorrowBookFrm | RentalSlipDetailDAO | `insertRentalSlipDetail(detail2)` | Lưu chi tiết phiếu mượn dòng 2 |
| 25 | RentalSlipDetailDAO | BorrowBookFrm | `return true` | Xác nhận lưu thành công |
| 26 | BorrowBookFrm | BorrowBookFrm | `printLoanSlip()` | In phiếu mượn |
| 27 | BorrowBookFrm | Staff | `displayMessage("Tao phieu muon thanh cong")` | Hiển thị thông báo thành công |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên testcase | Mô tả |
|----|-------------|-------|
| TC01 | Mượn sách thành công | Staff tìm KH, chọn, tìm 2 sách, chọn, tạo phiếu mượn |
| TC02 | Thêm khách hàng mới khi chưa tồn tại | Staff tìm KH không thấy, thêm mới, rồi mượn sách |
| TC03 | Sách hết hàng | Staff tìm sách nhưng số lượng = 0, không cho phép chọn |

### TC01: Mượn sách thành công

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

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |
| 2 | BT002 | Co so du lieu | Tran Van C | DHQG | 2022 | 4000 | 8 |

**tblRentalSlip**: rỗng (0 dòng)

**tblRentalSlipDetail**: rỗng (0 dòng)

#### Kịch bản test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Staff chọn chức năng Borrowing | — | Hiển thị giao diện BorrowBookFrm với ô nhập tên KH, nút Search |
| 3 | Staff nhập tên KH | "Nguyen Van A" vào ô inCustomerName | Ô inCustomerName hiển thị "Nguyen Van A" |
| 4 | Staff nhấn Search | Nhấn nút SearchCustomer | Hệ thống hiển thị danh sách KH: 1 dòng — KH001, Nguyen Van A, 012345678901, 0901234567, Ha Noi |
| 5 | Staff chọn khách hàng | Click dòng KH001 | Hệ thống hiển thị phần tìm kiếm sách, customerId được chọn = 1 |
| 6 | Staff nhập tên sách | "Lap trinh Java" vào ô inBookName | Ô inBookName hiển thị "Lap trinh Java" |
| 7 | Staff nhấn Search | Nhấn nút SearchBook | Hệ thống hiển thị danh sách sách: 1 dòng — BT001, Lap trinh Java, Nguyen Van B, DHQG, 2023, 5000, quantity=10 |
| 8 | Staff chọn sách | Click dòng BT001 | Hệ thống thêm 1 dòng vào outListRentalDetail: Lap trinh Java, Nguyen Van B, DHQG, 2023, 5000 |
| 9 | Staff nhập tên sách tiếp | "Co so du lieu", nhấn Search | Hệ thống hiển thị: BT002, Co so du lieu, Tran Van C, DHQG, 2022, 4000, quantity=8 |
| 10 | Staff chọn sách | Click dòng BT002 | Hệ thống thêm dòng thứ 2 vào outListRentalDetail: Co so du lieu, Tran Van C, DHQG, 2022, 4000 |
| 11 | Staff tạo phiếu mượn | Nhấn nút Create loan slip | Hệ thống hiển thị thông báo "Tao phieu muon thanh cong" và in phiếu mượn với 2 sách |

#### Database sau khi test

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalBooks |
|--------------|------------|--------|------------|------------|
| 1 | 1 | 1 | 2026-06-08 | 2 |

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice |
|--------------------|-------------|-------------|-------------|
| 1 | 1 | 1 | 5000 |
| 2 | 1 | 2 | 4000 |

**tblCustomer**: không thay đổi (vẫn 2 dòng)

**tblBookTitle**: không thay đổi (vẫn 2 dòng, quantity không đổi vì chỉ ghi nhận mượn, chưa trừ tồn)

---

### TC02: Thêm khách hàng mới khi chưa tồn tại

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

**tblCustomer**: rỗng (0 dòng)

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |

**tblRentalSlip**: rỗng

**tblRentalSlipDetail**: rỗng

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công |
| 2 | Staff chọn Borrowing | — | Hiển thị BorrowBookFrm |
| 3 | Staff nhập tên KH | "Pham Van D", nhấn Search | Hệ thống hiển thị "Khong tim thay khach hang" |
| 4 | Staff nhấn Add new | — | Hệ thống hiển thị form thêm KH mới |
| 5 | Staff nhập thông tin KH mới | name: "Pham Van D", idCard: "011111111111", phone: "0933333333", address: "Da Nang" | Form hiển thị thông tin đã nhập |
| 6 | Staff lưu KH mới | Nhấn nút Save | Hệ thống lưu KH mới vào tblCustomer, customerId = 1 |
| 7 | Staff tìm sách | "Lap trinh Java", nhấn Search | Hiển thị BT001 |
| 8 | Staff chọn sách | Click BT001 | Thêm vào danh sách mượn |
| 9 | Staff tạo phiếu | Nhấn Create loan slip | Thông báo "Tao phieu muon thanh cong" |

#### Database sau khi test

**tblCustomer**

| customerId | code | name | idCard | phone | address |
|------------|------|------|--------|-------|---------|
| 1 | KH001 | Pham Van D | 011111111111 | 0933333333 | Da Nang |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalBooks |
|--------------|------------|--------|------------|------------|
| 1 | 1 | 1 | 2026-06-08 | 1 |

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice |
|--------------------|-------------|-------------|-------------|
| 1 | 1 | 1 | 5000 |
