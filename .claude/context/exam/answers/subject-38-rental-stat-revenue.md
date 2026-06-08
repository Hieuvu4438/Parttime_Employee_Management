# Subject No. 38 — Book Rental — Module "Revenue Statistics"

> **Domain:** Book Rental Management
> **Entity classes:** BookTitle, Customer, RentalSlip, RentalSlipDetail, Payment, User

---

## Câu 1: Scenario — Thống kê doanh thu theo thời gian

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống bằng tài khoản `staff01`, password `******`. Giao diện Login xuất hiện. |
| 2 | Staff nhấn nút **Login**. Hệ thống xác thực thành công. Giao diện Home xuất hiện. |
| 3 | Staff chọn menu **Statistics** → **Revenue statistics**. |
| 4 | Giao diện thống kê doanh thu xuất hiện: combobox chọn kiểu thống kê (`cboStatType`: tháng/quý/năm). |
| 5 | Staff chọn "Theo tháng" trong combobox. Hệ thống hiển thị nút **View** (`btnView`). |
| 6 | Staff nhấn nút **View**. Hệ thống truy vấn dữ liệu doanh thu từ tblRentalSlip, tblPayment theo tháng. |
| 7 | Hệ thống nhóm doanh thu theo tháng, sắp xếp từ tháng gần nhất đến cũ nhất. |
| 8 | Hệ thống hiển thị bảng doanh thu theo tháng: tên tháng, tổng doanh thu. Dữ liệu ví dụ: 05/2026 (5,000,000đ), 04/2026 (4,200,000đ), 03/2026 (3,800,000đ), 02/2026 (2,500,000đ), 01/2026 (1,800,000đ). |
| 9 | Staff nhấn vào dòng tháng "05/2026" trong bảng. |
| 10 | Hệ thống truy vấn chi tiết hóa đơn trong tháng 05/2026 từ tblRentalSlip, tblCustomer, tblPayment. |
| 11 | Hệ thống hiển thị bảng chi tiết hóa đơn: mã phiếu mượn, tên khách hàng, ngày mượn, tổng sách mượn, tổng tiền. Dữ liệu ví dụ: PM005 (Nguyen Van A, 12/05/2026, 3 cuốn, 1,500,000đ), PM006 (Tran Thi B, 18/05/2026, 5 cuốn, 2,000,000đ), PM007 (Le Van C, 25/05/2026, 2 cuốn, 1,500,000đ). |
| 12 | Staff nhấn **Back** để quay về bảng thống kê doanh thu theo tháng. |

### Kịch bản ngoại lệ

- **EL1:** Không có dữ liệu mượn sách trong khoảng thời gian đã chọn → hệ thống hiển thị bảng trống, thông báo "Không có dữ liệu".
- **EL2:** Staff chọn kiểu thống kê "Theo quý" → hệ thống hiển thị bảng theo quý: Q1/2026, Q2/2026...
- **EL3:** Staff chọn kiểu thống kê "Theo năm" → hệ thống hiển thị bảng theo năm: 2026, 2025...

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. Mỗi cuốn sách (BookTitle) có thể được mượn nhiều lần. Mỗi lần mượn tạo ra một phiếu mượn (RentalSlip). Mỗi phiếu mượn có nhiều chi tiết (RentalSlipDetail), mỗi chi tiết liên kết đến một cuốn sách. Mỗi phiếu mượn có thể có nhiều lần thanh toán (Payment). Khách hàng (Customer) là người mượn sách. Người dùng (User) là nhân viên thực hiện thao tác.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Sách | Entity class (BookTitle) | Đối tượng chính được cho mượn |
| Khách hàng | Entity class (Customer) | Người mượn sách |
| Phiếu mượn | Entity class (RentalSlip) | Bản ghi mỗi lần mượn sách |
| Chi tiết phiếu mượn | Entity class (RentalSlipDetail) | Chi tiết từng cuốn sách trong phiếu |
| Thanh toán | Entity class (Payment) | Bản ghi thanh toán tiền mượn sách |
| Người dùng | Entity class (User) | Nhân viên thao tác trên hệ thống |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| BookTitle | id (PK), code, title, author, publisher, publishYear, price |
| Customer | id (PK), code, name, idCardNumber, phone, address |
| RentalSlip | id (PK), code, rentalDate, totalAmount, customerId (FK), userId (FK) |
| RentalSlipDetail | id (PK), rentalSlipId (FK), bookTitleId (FK), quantity, unitPrice, amount |
| Payment | id (PK), rentalSlipId (FK), paymentDate, amount, paymentMethod |
| User | id (PK), username, password, fullName, role |

### ASCII Class Diagram

```
+------------------+          +------------------+
|   BookTitle      |          |    Customer      |
+------------------+          +------------------+
| - id: int        |          | - id: int        |
| - code: String   |          | - code: String   |
| - title: String  |          | - name: String   |
| - author: String |          | - idCardNumber   |
| - publisher: String|        | - phone: String  |
| - publishYear: int|         | - address: String|
| - price: float   |          +------------------+
+------------------+                  |
        | n                            | 1
        |                              |
        | 1                            | n
+------------------+          +------------------+
| RentalSlipDetail |          |   RentalSlip     |
+------------------+          +------------------+
| - id: int        | n      1 | - id: int        |
| - rentalSlipId: int|--------| - code: String   |
| - bookTitleId: int|          | - rentalDate: Date|
| - quantity: int  |          | - totalAmount    |
| - unitPrice: float|         | - customerId: int|
| - amount: float  |          | - userId: int    |
+------------------+          +------------------+
                                     | 1
                                     |
                                     | n
                            +------------------+
                            |    Payment       |
                            +------------------+
                            | - id: int        |
                            | - rentalSlipId: int|
                            | - paymentDate    |
                            | - amount: float  |
                            | - paymentMethod  |
                            +------------------+

                            +------------------+
                            |      User        |
                            +------------------+
                            | - id: int        |
                            | - username: String|
                            | - password: String|
                            | - fullName: String|
                            | - role: String    |
                            +------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Customer → RentalSlip | 1-n | Một khách hàng có nhiều phiếu mượn |
| RentalSlip → RentalSlipDetail | 1-n | Một phiếu mượn có nhiều chi tiết |
| BookTitle → RentalSlipDetail | 1-n | Một cuốn sách xuất hiện trong nhiều chi tiết |
| RentalSlip → Payment | 1-n | Một phiếu mượn có nhiều lần thanh toán |
| User → RentalSlip | 1-n | Một nhân viên tạo nhiều phiếu mượn |

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm** — Giao diện đăng nhập:
- `inUsername`: ô nhập tên đăng nhập
- `inPassword`: ô nhập mật khẩu
- `subLogin`: nút Login

**HomeFrm** — Giao diện chính:
- `mnuStatistic`: menu thống kê
- `mnuRevenueStatistic`: menu-item thống kê doanh thu

**RevenueStatFrm** — Giao diện thống kê doanh thu:
- `cboStatType`: ComboBox — chọn kiểu thống kê (Tháng/Quý/Năm)
- `btnView`: Button — thực hiện thống kê
- `dgvRevenueStat`: DataGridView — hiển thị bảng doanh thu (tên tháng/quý/năm, tổng doanh thu)
- `dgvInvoiceDetail`: DataGridView — hiển thị chi tiết hóa đơn khi click vào 1 dòng (mã phiếu, tên KH, ngày mượn, tổng sách, tổng tiền)
- `btnBack`: Button — quay lại bảng thống kê

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| cboStatType | ComboBox | Chọn kiểu thống kê: Tháng, Quý, Năm |
| btnView | Button | Nhấn để xem thống kê |
| dgvRevenueStat | DataGridView | Bảng kết quả doanh thu theo tháng/quý/năm |
| dgvInvoiceDetail | DataGridView | Bảng chi tiết hóa đơn |
| btnBack | Button | Quay lại bảng thống kê |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| RentalSlipDAO | `getRevenueByMonth(): List<Object[]>` | Thống kê doanh thu theo tháng, trả về tên tháng, tổng doanh thu |
| RentalSlipDAO | `getRevenueByQuarter(): List<Object[]>` | Thống kê doanh thu theo quý |
| RentalSlipDAO | `getRevenueByYear(): List<Object[]>` | Thống kê doanh thu theo năm |
| RentalSlipDAO | `getRentalSlipsByMonth(month, year): List<RentalSlip>` | Lấy danh sách phiếu mượn theo tháng |
| CustomerDAO | `getCustomerById(customerId): Customer` | Lấy thông tin KH theo mã |
| RentalSlipDetailDAO | `getDetailsByRentalSlip(rentalSlipId): List<RentalSlipDetail>` | Lấy chi tiết phiếu mượn |
| PaymentDAO | `getPaymentsByRentalSlip(rentalSlipId): List<Payment>` | Lấy thanh toán theo phiếu mượn |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| RentalSlip | Nguồn dữ liệu chính để tính doanh thu, hiển thị mã phiếu và ngày mượn |
| RentalSlipDetail | Chi tiết từng cuốn sách, dùng để tính tổng sách mượn |
| Payment | Dữ liệu thanh toán, dùng để tính tổng doanh thu |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết |
| BookTitle | Thông tin sách, liên kết với RentalSlipDetail |
| User | Nhân viên đăng nhập và thực hiện thao tác |

### Database Schema

```sql
CREATE TABLE tblUser (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(100),
    fullName VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE tblBookTitle (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    title VARCHAR(200),
    author VARCHAR(100),
    publisher VARCHAR(100),
    publishYear INT,
    price FLOAT
);

CREATE TABLE tblCustomer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    name VARCHAR(100),
    idCardNumber VARCHAR(20),
    phone VARCHAR(20),
    address VARCHAR(200)
);

CREATE TABLE tblRentalSlip (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    rentalDate DATETIME,
    totalAmount FLOAT,
    customerId INT,
    userId INT,
    FOREIGN KEY (customerId) REFERENCES tblCustomer(id),
    FOREIGN KEY (userId) REFERENCES tblUser(id)
);

CREATE TABLE tblRentalSlipDetail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rentalSlipId INT,
    bookTitleId INT,
    quantity INT,
    unitPrice FLOAT,
    amount FLOAT,
    FOREIGN KEY (rentalSlipId) REFERENCES tblRentalSlip(id),
    FOREIGN KEY (bookTitleId) REFERENCES tblBookTitle(id)
);

CREATE TABLE tblPayment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rentalSlipId INT,
    paymentDate DATETIME,
    amount FLOAT,
    paymentMethod VARCHAR(20),
    FOREIGN KEY (rentalSlipId) REFERENCES tblRentalSlip(id)
);
```

### Hướng dẫn vẽ trên Visual Paradigm

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo các class: BookTitle, Customer, RentalSlip, RentalSlipDetail, Payment, User.
3. Thêm attributes cho mỗi class theo bảng ở trên.
4. Vẽ quan hệ:
   - Customer → RentalSlip (1-n, Association)
   - RentalSlip → RentalSlipDetail (1-n, Composition)
   - BookTitle → RentalSlipDetail (1-n, Association)
   - RentalSlip → Payment (1-n, Composition)
   - User → RentalSlip (1-n, Association)
5. Đặt tên cho mỗi association (ví dụ: "borrows", "contains", "paidBy", "createdBy").
6. Đặt multiplicity đúng: RentalSlip(1) → Payment(n).

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor (người sử dụng)
2. `:LoginFrm` — boundary (giao diện đăng nhập)
3. `:HomeFrm` — boundary (giao diện chính)
4. `:RevenueStatFrm` — boundary (giao diện thống kê doanh thu)
5. `:UserDAO` — control (truy vấn người dùng)
6. `:RentalSlipDAO` — control (truy vấn phiếu mượn / doanh thu)
7. `:CustomerDAO` — control (truy vấn khách hàng)
8. `:RentalSlipDetailDAO` — control (truy vấn chi tiết phiếu mượn)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 8 lifelines theo thứ tự: Staff, LoginFrm, HomeFrm, RevenueStatFrm, UserDAO, RentalSlipDAO, CustomerDAO, RentalSlipDetailDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng self-call cho các thao tác nội bộ của form (sắp xếp).

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | enterCredentials("staff01", "******") | synchronous |
| 2 | Staff | LoginFrm | clickLogin() | synchronous |
| 3 | LoginFrm | UserDAO | checkLogin("staff01", "******") | synchronous |
| 4 | UserDAO | LoginFrm | return true | return |
| 5 | LoginFrm | HomeFrm | show() | synchronous |
| 6 | Staff | HomeFrm | selectMenu("Statistics → Revenue statistics") | synchronous |
| 7 | HomeFrm | RevenueStatFrm | show() | synchronous |
| 8 | RevenueStatFrm | Staff | displayForm(cboStatType) | return |
| 9 | Staff | RevenueStatFrm | selectStatType("Theo tháng") | synchronous |
| 10 | Staff | RevenueStatFrm | clickView() | synchronous |
| 11 | RevenueStatFrm | RentalSlipDAO | getRevenueByMonth() | synchronous |
| 12 | RentalSlipDAO | RevenueStatFrm | return List<Object[]> (monthName, totalRevenue) | return |
| 13 | RevenueStatFrm | RevenueStatFrm | sortByDateDesc() | self |
| 14 | RevenueStatFrm | Staff | displayRevenueStatTable(data) | return |
| 15 | Staff | RevenueStatFrm | clickRow("05/2026") | synchronous |
| 16 | RevenueStatFrm | RentalSlipDAO | getRentalSlipsByMonth(5, 2026) | synchronous |
| 17 | RentalSlipDAO | RevenueStatFrm | return List<RentalSlip> | return |
| 18 | RevenueStatFrm | CustomerDAO | getCustomerById(customerId) | synchronous |
| 19 | CustomerDAO | RevenueStatFrm | return Customer | return |
| 20 | RevenueStatFrm | RentalSlipDetailDAO | getDetailsByRentalSlip(rentalSlipId) | synchronous |
| 21 | RentalSlipDetailDAO | RevenueStatFrm | return List<RentalSlipDetail> | return |
| 22 | RevenueStatFrm | RevenueStatFrm | buildInvoiceDetailList(rentalSlips, customers, details) | self |
| 23 | RevenueStatFrm | Staff | displayInvoiceDetail(data) | return |
| 24 | Staff | RevenueStatFrm | clickBack() | synchronous |
| 25 | RevenueStatFrm | Staff | displayRevenueStatTable(previousData) | return |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn

**Database trước khi test:**

tblUser:
| id | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | staff01 | 123456 | Nguyen Van Staff | staff |

tblCustomer:
| id | code | name | idCardNumber | phone | address |
|----|------|------|-------------|-------|---------|
| 1 | KH001 | Nguyen Van A | 123456789 | 0901234567 | Q1 TP.HCM |
| 2 | KH002 | Tran Thi B | 987654321 | 0912345678 | Q3 TP.HCM |
| 3 | KH003 | Le Van C | 456789123 | 0923456789 | Q7 TP.HCM |

tblBookTitle:
| id | code | title | author | publisher | publishYear | price |
|----|------|-------|--------|-----------|-------------|-------|
| 1 | BT001 | Lap trinh Java | Nguyen Van A | NXB DHQG | 2024 | 150000 |
| 2 | BT002 | Co so du lieu | Tran Van B | NXB DHQG | 2023 | 120000 |
| 3 | BT003 | Mang may tinh | Le Van C | NXB GTVT | 2024 | 130000 |

tblRentalSlip:
| id | code | rentalDate | totalAmount | customerId | userId |
|----|------|-----------|-------------|------------|--------|
| 1 | PM001 | 2026-01-10 | 450000 | 1 | 1 |
| 2 | PM002 | 2026-02-15 | 360000 | 2 | 1 |
| 3 | PM003 | 2026-03-20 | 300000 | 3 | 1 |
| 4 | PM004 | 2026-04-05 | 240000 | 1 | 1 |
| 5 | PM005 | 2026-05-12 | 1500000 | 1 | 1 |
| 6 | PM006 | 2026-05-18 | 2000000 | 2 | 1 |
| 7 | PM007 | 2026-05-25 | 1500000 | 3 | 1 |

tblRentalSlipDetail:
| id | rentalSlipId | bookTitleId | quantity | unitPrice | amount |
|----|-------------|-------------|----------|-----------|--------|
| 1 | 1 | 1 | 3 | 150000 | 450000 |
| 2 | 2 | 2 | 3 | 120000 | 360000 |
| 3 | 3 | 3 | 2 | 150000 | 300000 |
| 4 | 4 | 2 | 2 | 120000 | 240000 |
| 5 | 5 | 1 | 5 | 150000 | 750000 |
| 6 | 5 | 2 | 5 | 150000 | 750000 |
| 7 | 6 | 3 | 4 | 200000 | 800000 |
| 8 | 6 | 1 | 4 | 300000 | 1200000 |
| 9 | 7 | 2 | 3 | 250000 | 750000 |
| 10 | 7 | 3 | 3 | 250000 | 750000 |

tblPayment:
| id | rentalSlipId | paymentDate | amount | paymentMethod |
|----|-------------|-------------|--------|---------------|
| 1 | 1 | 2026-01-10 | 450000 | Cash |
| 2 | 2 | 2026-02-15 | 360000 | Card |
| 3 | 3 | 2026-03-20 | 300000 | Cash |
| 4 | 4 | 2026-04-05 | 240000 | Card |
| 5 | 5 | 2026-05-12 | 1500000 | Cash |
| 6 | 6 | 2026-05-18 | 2000000 | Card |
| 7 | 7 | 2026-05-25 | 1500000 | Cash |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập username: staff01, password: 123456, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 3 | Chọn menu Statistics → Revenue statistics | Hiển thị giao diện thống kê với combobox chọn kiểu (Tháng/Quý/Năm) |
| 4 | Chọn "Theo tháng" trong combobox | Combobox hiển thị "Theo tháng", nút View xuất hiện |
| 5 | Nhấn nút View | Hệ thống hiển thị bảng: 05/2026 (5,000,000đ), 04/2026 (240,000đ), 03/2026 (300,000đ), 02/2026 (360,000đ), 01/2026 (450,000đ). Sắp xếp từ tháng gần nhất đến cũ nhất. |
| 6 | Nhấn vào dòng "05/2026" trong bảng | Hệ thống hiển thị bảng chi tiết hóa đơn bên dưới |
| 7 | Bảng chi tiết hiển thị 3 dòng: PM005 (Nguyen Van A, 12/05/2026, 5 cuốn, 1,500,000đ); PM006 (Tran Thi B, 18/05/2026, 4 cuốn, 2,000,000đ); PM007 (Le Van C, 25/05/2026, 3 cuốn, 1,500,000đ). Tổng = 5,000,000đ | Dữ liệu hiển thị chính xác, khớp với database |
| 8 | Nhấn nút Back | Quay về bảng thống kê doanh thu theo tháng, bảng chi tiết ẩn đi |

**Database sau khi test:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc/thống kê).
