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

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-2: Staff đăng nhập → giao diện Home. View class: **HomeFrm**.
Bước 3: Staff chọn menu Statistics → Revenue statistics. View class: **RevenueStatFrm**.
Bước 4: Giao diện thống kê doanh thu: combobox chọn kiểu thống kê. UI: `cboStatType` (ComboBox — chọn kiểu: Tháng/Quý/Năm).
Bước 5-6: Staff chọn "Theo tháng", nhấn View. UI: `subView` (Button — xem thống kê).
Bước 7-8: Hệ thống hiển thị bảng doanh thu theo tháng. UI: `outsubListRevenueStat` (DataGridView — bảng doanh thu, click chọn dòng được).
Bước 9: Staff nhấn vào dòng "05/2026".
Bước 10-11: Hệ thống hiển thị bảng chi tiết hóa đơn. UI: `outListInvoiceDetail` (DataGridView — chi tiết hóa đơn: mã phiếu, tên KH, ngày mượn, tổng sách, tổng tiền).
Bước 12: Staff nhấn Back. UI: `subBack` (Button — quay về bảng thống kê).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Revenue statistics |
| RevenueStatFrm | Form | Giao diện thống kê doanh thu |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `cboStatType` | ComboBox | RevenueStatFrm | Chọn kiểu thống kê: Tháng, Quý, Năm |
| `subView` | Button | RevenueStatFrm | Nút View — xem thống kê |
| `outsubListRevenueStat` | DataGridView | RevenueStatFrm | Bảng doanh thu theo tháng/quý/năm, click chọn dòng |
| `outListInvoiceDetail` | DataGridView | RevenueStatFrm | Bảng chi tiết hóa đơn (mã phiếu, tên KH, ngày mượn, tổng sách, tổng tiền) |
| `subBack` | Button | RevenueStatFrm | Nút Back — quay về bảng thống kê |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getRevenueByMonth()` | - | List\<Object[]\> | RentalSlip, Payment |
| `getRevenueByQuarter()` | - | List\<Object[]\> | RentalSlip, Payment |
| `getRevenueByYear()` | - | List\<Object[]\> | RentalSlip, Payment |
| `getRentalSlipsByMonth()` | month, year | List\<RentalSlip\> | RentalSlip |
| `getCustomerById()` | customerId | Customer | Customer |
| `getDetailsByRentalSlip()` | rentalSlipId | List\<RentalSlipDetail\> | RentalSlipDetail |

**Tong hop:**

- View classes: HomeFrm, RevenueStatFrm
- Methods: getRevenueByMonth(), getRevenueByQuarter(), getRevenueByYear(), getRentalSlipsByMonth(), getCustomerById(), getDetailsByRentalSlip()

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Customer → RentalSlip | 1-n | Một khách hàng có nhiều phiếu mượn |
| RentalSlip → RentalSlipDetail | 1-n | Một phiếu mượn có nhiều chi tiết |
| BookTitle → RentalSlipDetail | 1-n | Một cuốn sách xuất hiện trong nhiều chi tiết |
| RentalSlip → Payment | 1-n | Một phiếu mượn có nhiều lần thanh toán |
| User → RentalSlip | 1-n | Một nhân viên tạo nhiều phiếu mượn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác |
|------|----------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class box (hình chữ nhật 3 ngăn): BookTitle, Customer, RentalSlip, RentalSlipDetail, Payment, User |
| 3 | Tạo các view class box từ giao diện: HomeFrm, RevenueStatFrm |
| 4 | Vẽ các đường quan hệ (association) giữa các class |
| 5 | Ghi multiplicity và role name cho mỗi quan hệ |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>` hoặc `<<boundary>>` rồi đến tên class. Ví dụ: `<<entity>> Payment`, `<<boundary>> RevenueStatFrm`
- **Ngăn 2 (thuộc tính):** Ghi từng thuộc tính theo format `-tenThuocTinh: KieuDuLieu`. Dùng `-` cho private. Ví dụ: `-paymentId: int`, `-amount: float`
- **Ngăn 3 (phương thức):** Ghi từng phương thức theo format `+tenPhuongThuc(thamSo): KieuTraVe`. Dùng `+` cho public. Ví dụ: `+getRevenueByMonth(): List<Object[]>`

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| BookTitle | `<<entity>>` | `-id: int`, `-code: String`, `-title: String`, `-author: String`, `-publisher: String`, `-publishYear: int`, `-price: float` | — |
| Customer | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-idCardNumber: String`, `-phone: String`, `-address: String` | `+getCustomerById(customerId: int): Customer` |
| RentalSlip | `<<entity>>` | `-id: int`, `-code: String`, `-rentalDate: Date`, `-totalAmount: float`, `-customerId: int`, `-userId: int` | `+getRevenueByMonth(): List<Object[]>`, `+getRevenueByQuarter(): List<Object[]>`, `+getRevenueByYear(): List<Object[]>`, `+getRentalSlipsByMonth(month: int, year: int): List<RentalSlip>` |
| RentalSlipDetail | `<<entity>>` | `-id: int`, `-rentalSlipId: int`, `-bookTitleId: int`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | `+getDetailsByRentalSlip(rentalSlipId: int): List<RentalSlipDetail>` |
| Payment | `<<entity>>` | `-id: int`, `-rentalSlipId: int`, `-paymentDate: Date`, `-amount: float`, `-paymentMethod: String` | — |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-fullName: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

**HomeFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `mnuStatistic` | sub | JMenu | Menu thống kê |
| `mnuRevenueStatistic` | sub | JMenuItem | Menu-item thống kê doanh thu |

**RevenueStatFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `cboStatType` | in | ComboBox | Chọn kiểu thống kê: Tháng, Quý, Năm |
| `subView` | sub | Button | Nút View — xem thống kê |
| `outsubListRevenueStat` | outsub | DataGridView | Bảng doanh thu (click chọn dòng) |
| `outListInvoiceDetail` | out | DataGridView | Bảng chi tiết hóa đơn |
| `subBack` | sub | Button | Nút Back — quay về bảng thống kê |

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

Ví dụ: RentalSlip(1) ←——→ (n) Payment nghĩa là 1 phiếu mượn có nhiều lần thanh toán.

#### 7. Bảng quan hệ chi tiết (Visual Paradigm)

| Từ | Đến | Kiểu quan hệ | Multiplicity | Role name | Giải thích |
|----|-----|---------------|-------------|-----------|------------|
| Customer | RentalSlip | Association | 1 — n | borrows | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | Composition | 1 — n | contains | Chi tiết không tồn tại nếu không có phiếu mượn |
| BookTitle | RentalSlipDetail | Association | 1 — n | references | Một cuốn sách xuất hiện trong nhiều chi tiết |
| RentalSlip | Payment | Composition | 1 — n | paidBy | Một phiếu mượn có nhiều lần thanh toán |
| User | RentalSlip | Association | 1 — n | creates | Một nhân viên tạo nhiều phiếu mượn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ RentalSlip → Payment (Composition 1-n)**

1. Chọn công cụ **Composition** (đường liền nét đầu kim cương filled ◆) từ toolbox.
2. Click vào `RentalSlip` (phía "chứa"), kéo sang `Payment` (phía "bị chứa").
3. Click chuột phải vào đường kết nối → **Open Specification**.
4. Tại mục **From** (RentalSlip): multiplicity = `1`.
5. Tại mục **To** (Payment): multiplicity = `*`, role name = `paidBy`.
6. Nhấn OK. Đầu kim cương filled (◆) nằm phía `RentalSlip`.

**Ví dụ 2: Vẽ quan hệ Customer → RentalSlip (Association 1-n)**

1. Chọn công cụ **Association** (đường liền nét mũi tên rỗng) từ toolbox.
2. Click vào `Customer`, kéo sang `RentalSlip`.
3. Click chuột phải → **Open Specification**.
4. Tại mục **From** (Customer): multiplicity = `1`, role name = `borrows`.
5. Tại mục **To** (RentalSlip): multiplicity = `*`.
6. Nhấn OK.

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

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| RentalSlip | Entity | id: int (PK), code: String, rentalDate: Date, totalAmount: float, customer: Customer (object), user: User (object) |
| RentalSlipDetail | Entity | id: int (PK), rentalSlip: RentalSlip (object), bookTitle: BookTitle (object), quantity: int, unitPrice: float, amount: float |
| Payment | Entity | id: int (PK), rentalSlip: RentalSlip (object), paymentDate: Date, amount: float, paymentMethod: String |
| Customer | Entity | id: int (PK), code: String, name: String, idCardNumber: String, phone: String, address: String |
| BookTitle | Entity | id: int (PK), code: String, title: String, author: String, publisher: String, publishYear: int, price: float |
| User | Entity | id: int (PK), username: String, password: String, fullName: String, role: String |

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
