# Subject No. 37 — Book Rental — Module "Statistics of borrowers"

> **Domain:** Book Rental Management
> **Entity classes:** BookTitle, Customer, RentalSlip, RentalSlipDetail, User

---

## Câu 1: Scenario — Thống kê người mượn nhiều

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống bằng tài khoản `staff01`, password `******`. Giao diện Login xuất hiện. |
| 2 | Staff nhấn nút **Login**. Hệ thống xác thực thành công. Giao diện Home xuất hiện. |
| 3 | Staff chọn menu **Statistics** → **Borrower statistics**. |
| 4 | Giao diện thống kê người mượn xuất hiện: ô nhập ngày bắt đầu (`dtpStartDate`), ô nhập ngày kết thúc (`dtpEndDate`), nút **View** (`btnView`). |
| 5 | Staff nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026. |
| 6 | Staff nhấn nút **View**. Hệ thống truy vấn dữ liệu từ tblRentalSlip, tblRentalSlipDetail, tblCustomer theo khoảng thời gian đã chọn. |
| 7 | Hệ thống tính tổng số lần mượn và tổng tiền đã trả cho mỗi khách hàng. Sắp xếp giảm dần theo tổng lần mượn, nếu bằng nhau thì sắp xếp theo tổng tiền giảm dần. |
| 8 | Hệ thống hiển thị bảng danh sách khách hàng: mã KH, tên KH, CMND, SĐT, địa chỉ, tổng lần mượn, tổng tiền đã trả. Dữ liệu ví dụ: KH001 Nguyễn Văn A (CMND: 123456789, SĐT: 0901234567, địa chỉ: Q1 TP.HCM, 10 lần mượn, 5,000,000đ), KH002 Trần Thị B (CMND: 987654321, SĐT: 0912345678, địa chỉ: Q3 TP.HCM, 8 lần mượn, 3,200,000đ), KH003 Lê Văn C (CMND: 456789123, SĐT: 0923456789, địa chỉ: Q7 TP.HCM, 8 lần mượn, 4,500,000đ). |
| 9 | Staff nhấn vào dòng khách hàng "Nguyễn Văn A" trong bảng. |
| 10 | Hệ thống truy vấn chi tiết hóa đơn mượn sách của khách hàng đó trong khoảng thời gian đã chọn từ tblRentalSlip, tblRentalSlipDetail. |
| 11 | Hệ thống hiển thị bảng chi tiết hóa đơn: ngày mượn, tổng sách mượn, tổng tiền. Dữ liệu ví dụ: 15/01/2026 (3 cuốn, 450,000đ), 20/02/2026 (5 cuốn, 750,000đ), 10/03/2026 (2 cuốn, 300,000đ). |
| 12 | Staff nhấn **Back** để quay về bảng thống kê danh sách khách hàng. |

### Kịch bản ngoại lệ

- **EL1:** Không có dữ liệu mượn sách trong khoảng thời gian đã chọn → hệ thống hiển thị bảng trống, thông báo "Không có dữ liệu trong khoảng thời gian này".
- **EL2:** Staff nhập ngày bắt đầu lớn hơn ngày kết thúc → hệ thống cảnh báo "Ngày bắt đầu phải nhỏ hơn ngày kết thúc".
- **EL3:** Staff chưa nhập ngày mà nhấn View → hệ thống cảnh báo "Vui lòng nhập khoảng thời gian".

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. Mỗi cuốn sách (BookTitle) có thể được mượn nhiều lần bởi nhiều khách hàng (Customer). Mỗi lần mượn tạo ra một phiếu mượn (RentalSlip) chứa thông tin ngày mượn, tổng tiền. Mỗi phiếu mượn có nhiều chi tiết (RentalSlipDetail), mỗi chi tiết liên kết đến một cuốn sách cụ thể với số lượng và đơn giá. Người dùng (User) là nhân viên thực hiện các thao tác trên hệ thống.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Sách | Entity class (BookTitle) | Đối tượng chính được cho mượn |
| Khách hàng | Entity class (Customer) | Người mượn sách |
| Phiếu mượn | Entity class (RentalSlip) | Bản ghi mỗi lần mượn sách |
| Chi tiết phiếu mượn | Entity class (RentalSlipDetail) | Chi tiết từng cuốn sách trong phiếu |
| Người dùng | Entity class (User) | Nhân viên thao tác trên hệ thống |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| BookTitle | id (PK), code, title, author, publisher, publishYear, price |
| Customer | id (PK), code, name, idCardNumber, phone, address |
| RentalSlip | id (PK), rentalDate, totalAmount, customerId (FK), userId (FK) |
| RentalSlipDetail | id (PK), rentalSlipId (FK), bookTitleId (FK), quantity, unitPrice, amount |
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
| - rentalSlipId: int|--------| - rentalDate: Date|
| - bookTitleId: int|          | - totalAmount    |
| - quantity: int  |          | - customerId: int|
| - unitPrice: float|         | - userId: int    |
| - amount: float  |          +------------------+
+------------------+                  | n
                                     |
                                     | 1
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
Bước 3: Staff chọn menu Statistics → Borrower statistics. View class: **BorrowerStatFrm**.
Bước 4: Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. UI: `inStartDate` (DateTimePicker — chọn ngày bắt đầu), `inEndDate` (DateTimePicker — chọn ngày kết thúc), `subView` (Button — xem thống kê).
Bước 5-6: Staff nhập ngày, nhấn View.
Bước 7-8: Hệ thống hiển thị bảng danh sách KH. UI: `outsubListBorrowerStat` (DataGridView — bảng thống kê KH, click chọn dòng được).
Bước 9: Staff nhấn vào dòng "Nguyen Van A".
Bước 10-11: Hệ thống hiển thị bảng chi tiết hóa đơn. UI: `outListInvoiceDetail` (DataGridView — chi tiết hóa đơn: ngày mượn, tổng sách, tổng tiền).
Bước 12: Staff nhấn Back. UI: `subBack` (Button — quay về bảng thống kê).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Borrower statistics |
| BorrowerStatFrm | Form | Giao diện thống kê người mượn nhiều |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inStartDate` | DateTimePicker | BorrowerStatFrm | Chọn ngày bắt đầu kỳ thống kê |
| `inEndDate` | DateTimePicker | BorrowerStatFrm | Chọn ngày kết thúc kỳ thống kê |
| `subView` | Button | BorrowerStatFrm | Nút View — xem thống kê |
| `outsubListBorrowerStat` | DataGridView | BorrowerStatFrm | Bảng thống kê KH (mã, tên, CMND, SĐT, địa chỉ, tổng lần mượn, tổng tiền), click chọn dòng |
| `outListInvoiceDetail` | DataGridView | BorrowerStatFrm | Bảng chi tiết hóa đơn (ngày mượn, tổng sách mượn, tổng tiền) |
| `subBack` | Button | BorrowerStatFrm | Nút Back — quay về bảng thống kê |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getBorrowerStat()` | startDate, endDate | List\<Object[]\> | Customer, RentalSlip |
| `getRentalSlipsByCustomer()` | customerId, startDate, endDate | List\<RentalSlip\> | RentalSlip |
| `getDetailsByRentalSlip()` | rentalSlipId | List\<RentalSlipDetail\> | RentalSlipDetail |

**Tong hop:**

- View classes: HomeFrm, BorrowerStatFrm
- Methods: getBorrowerStat(), getRentalSlipsByCustomer(), getDetailsByRentalSlip()

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Customer → RentalSlip | 1-n | Một khách hàng có nhiều phiếu mượn |
| RentalSlip → RentalSlipDetail | 1-n | Một phiếu mượn có nhiều chi tiết |
| BookTitle → RentalSlipDetail | 1-n | Một cuốn sách xuất hiện trong nhiều chi tiết phiếu mượn |
| User → RentalSlip | 1-n | Một nhân viên tạo nhiều phiếu mượn |
| Customer → RentalSlipDetail | n-n (qua RentalSlip) | Khách hàng mượn nhiều sách qua phiếu mượn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác |
|------|----------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class box (hình chữ nhật 3 ngăn): BookTitle, Customer, RentalSlip, RentalSlipDetail, User |
| 3 | Tạo các view class box từ giao diện: HomeFrm, BorrowerStatFrm |
| 4 | Vẽ các đường quan hệ (association) giữa các class |
| 5 | Ghi multiplicity và role name cho mỗi quan hệ |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>` hoặc `<<boundary>>` rồi đến tên class. Ví dụ: `<<entity>> Customer`, `<<boundary>> BorrowerStatFrm`
- **Ngăn 2 (thuộc tính):** Ghi từng thuộc tính theo format `-tenThuocTinh: KieuDuLieu`. Dùng `-` cho private. Ví dụ: `-customerId: int`, `-name: String`
- **Ngăn 3 (phương thức):** Ghi từng phương thức theo format `+tenPhuongThuc(thamSo): KieuTraVe`. Dùng `+` cho public. Ví dụ: `+getBorrowerStat(startDate: Date, endDate: Date): List<Object[]>`

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| BookTitle | `<<entity>>` | `-id: int`, `-code: String`, `-title: String`, `-author: String`, `-publisher: String`, `-publishYear: int`, `-price: float` | — |
| Customer | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-idCardNumber: String`, `-phone: String`, `-address: String` | `+getBorrowerStat(startDate: Date, endDate: Date): List<Object[]>` |
| RentalSlip | `<<entity>>` | `-id: int`, `-rentalDate: Date`, `-totalAmount: float`, `-customerId: int`, `-userId: int` | `+getRentalSlipsByCustomer(customerId: int, startDate: Date, endDate: Date): List<RentalSlip>` |
| RentalSlipDetail | `<<entity>>` | `-id: int`, `-rentalSlipId: int`, `-bookTitleId: int`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | `+getDetailsByRentalSlip(rentalSlipId: int): List<RentalSlipDetail>` |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-fullName: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

**HomeFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `mnuStatistic` | sub | JMenu | Menu thống kê |
| `mnuBorrowerStatistic` | sub | JMenuItem | Menu-item thống kê người mượn |

**BorrowerStatFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `dtpStartDate` | in | DateTimePicker | Chọn ngày bắt đầu thống kê |
| `dtpEndDate` | in | DateTimePicker | Chọn ngày kết thúc thống kê |
| `btnView` | sub | Button | Nút View — xem thống kê |
| `dgvBorrowerStat` | outsub | DataGridView | Bảng thống kê KH (click chọn dòng) |
| `dgvInvoiceDetail` | out | DataGridView | Bảng chi tiết hóa đơn |
| `btnBack` | sub | Button | Nút Back — quay về bảng thống kê |

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

Ví dụ: Customer(1) ←——→ (n) RentalSlip nghĩa là 1 khách hàng có nhiều phiếu mượn.

#### 7. Bảng quan hệ chi tiết (Visual Paradigm)

| Từ | Đến | Kiểu quan hệ | Multiplicity | Role name | Giải thích |
|----|-----|---------------|-------------|-----------|------------|
| Customer | RentalSlip | Association | 1 — n | borrows | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | Composition | 1 — n | contains | Chi tiết không tồn tại nếu không có phiếu mượn |
| BookTitle | RentalSlipDetail | Association | 1 — n | references | Một cuốn sách xuất hiện trong nhiều chi tiết |
| User | RentalSlip | Association | 1 — n | creates | Một nhân viên tạo nhiều phiếu mượn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Customer → RentalSlip (Association 1-n)**

1. Kéo class `Customer` và `RentalSlip` lên canvas.
2. Chọn công cụ **Association** (đường liền nét mũi tên rỗng) từ toolbox.
3. Click vào `Customer`, kéo sang `RentalSlip`.
4. Click chuột phải vào đường kết nối → **Open Specification**.
5. Tại mục **From**: multiplicity = `1`, role name = `borrows`.
6. Tại mục **To**: multiplicity = `*`, role name = `borrowedBy`.
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

### View classes

**LoginFrm** — Giao diện đăng nhập:
- `inUsername`: ô nhập tên đăng nhập
- `inPassword`: ô nhập mật khẩu
- `subLogin`: nút Login

**HomeFrm** — Giao diện chính:
- `mnuStatistic`: menu thống kê
- `mnuBorrowerStatistic`: menu-item thống kê người mượn

**BorrowerStatFrm** — Giao diện thống kê người mượn:
- `dtpStartDate`: DateTimePicker — chọn ngày bắt đầu thống kê
- `dtpEndDate`: DateTimePicker — chọn ngày kết thúc thống kê
- `btnView`: Button — thực hiện thống kê
- `dgvBorrowerStat`: DataGridView — hiển thị danh sách KH (mã, tên, CMND, SĐT, địa chỉ, tổng lần mượn, tổng tiền đã trả)
- `dgvInvoiceDetail`: DataGridView — hiển thị chi tiết hóa đơn khi click vào 1 dòng KH (ngày mượn, tổng sách mượn, tổng tiền)
- `btnBack`: Button — quay lại bảng thống kê

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| dtpStartDate | DateTimePicker | Chọn ngày bắt đầu |
| dtpEndDate | DateTimePicker | Chọn ngày kết thúc |
| btnView | Button | Nhấn để xem thống kê |
| dgvBorrowerStat | DataGridView | Bảng kết quả thống kê KH |
| dgvInvoiceDetail | DataGridView | Bảng chi tiết hóa đơn |
| btnBack | Button | Quay lại bảng thống kê |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| CustomerDAO | `getBorrowerStat(startDate, endDate): List<Object[]>` | Thống kê KH theo khoảng thời gian, trả về mã, tên, CMND, SĐT, địa chỉ, tổng lần mượn, tổng tiền |
| RentalSlipDAO | `getRentalSlipsByCustomer(customerId, startDate, endDate): List<RentalSlip>` | Lấy danh sách phiếu mượn của 1 KH theo khoảng thời gian |
| RentalSlipDetailDAO | `getDetailsByRentalSlip(rentalSlipId): List<RentalSlipDetail>` | Lấy chi tiết phiếu mượn |
| BookTitleDAO | `getBookTitleById(bookTitleId): BookTitle` | Lấy thông tin sách theo mã |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Customer | Entity | id: int (PK), code: String, name: String, idCardNumber: String, phone: String, address: String |
| RentalSlip | Entity | id: int (PK), rentalDate: Date, totalAmount: float, customer: Customer (object), user: User (object) |
| RentalSlipDetail | Entity | id: int (PK), rentalSlip: RentalSlip (object), bookTitle: BookTitle (object), quantity: int, unitPrice: float, amount: float |
| BookTitle | Entity | id: int (PK), code: String, title: String, author: String, publisher: String, publishYear: int, price: float |
| User | Entity | id: int (PK), username: String, password: String, fullName: String, role: String |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Customer | Hiển thị trong bảng thống kê (mã, tên, CMND, SĐT, địa chỉ) |
| RentalSlip | Nguồn dữ liệu chính để đếm tổng lần mượn và tính tổng tiền |
| RentalSlipDetail | Chi tiết từng cuốn sách trong phiếu mượn |
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
```

### Hướng dẫn vẽ trên Visual Paradigm

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo các class: BookTitle, Customer, RentalSlip, RentalSlipDetail, User.
3. Thêm attributes cho mỗi class theo bảng ở trên.
4. Vẽ quan hệ:
   - Customer → RentalSlip (1-n, Association)
   - RentalSlip → RentalSlipDetail (1-n, Composition)
   - BookTitle → RentalSlipDetail (1-n, Association)
   - User → RentalSlip (1-n, Association)
5. Đặt tên cho mỗi association (ví dụ: "borrows", "contains", "createdBy").
6. Đặt multiplicity đúng: Customer(1) → RentalSlip(n), RentalSlip(1) → RentalSlipDetail(n).

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor (người sử dụng)
2. `:LoginFrm` — boundary (giao diện đăng nhập)
3. `:HomeFrm` — boundary (giao diện chính)
4. `:BorrowerStatFrm` — boundary (giao diện thống kê người mượn)
5. `:UserDAO` — control (truy vấn người dùng)
6. `:CustomerDAO` — control (truy vấn khách hàng)
7. `:RentalSlipDAO` — control (truy vấn phiếu mượn)
8. `:RentalSlipDetailDAO` — control (truy vấn chi tiết phiếu mượn)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 8 lifelines theo thứ tự: Staff, LoginFrm, HomeFrm, BorrowerStatFrm, UserDAO, CustomerDAO, RentalSlipDAO, RentalSlipDetailDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng self-call cho các thao tác nội bộ của form (sắp xếp, tính toán).

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Staff | LoginFrm | enterCredentials("staff01", "******") | synchronous |
| 2 | Staff | LoginFrm | clickLogin() | synchronous |
| 3 | LoginFrm | UserDAO | checkLogin("staff01", "******") | synchronous |
| 4 | UserDAO | LoginFrm | return true | return |
| 5 | LoginFrm | HomeFrm | show() | synchronous |
| 6 | Staff | HomeFrm | selectMenu("Statistics → Borrower statistics") | synchronous |
| 7 | HomeFrm | BorrowerStatFrm | show() | synchronous |
| 8 | BorrowerStatFrm | Staff | displayForm(dtpStartDate, dtpEndDate, btnView) | return |
| 9 | Staff | BorrowerStatFrm | enterDateRange(01/01/2026, 31/12/2026) | synchronous |
| 10 | Staff | BorrowerStatFrm | clickView() | synchronous |
| 11 | BorrowerStatFrm | CustomerDAO | getBorrowerStat(01/01/2026, 31/12/2026) | synchronous |
| 12 | CustomerDAO | BorrowerStatFrm | return List<Object[]> (code, name, idCard, phone, address, totalLoans, totalPaid) | return |
| 13 | BorrowerStatFrm | BorrowerStatFrm | sortByTotalLoansDesc() | self |
| 14 | BorrowerStatFrm | BorrowerStatFrm | sortByTotalPaidDesc() | self |
| 15 | BorrowerStatFrm | Staff | displayBorrowerStatTable(data) | return |
| 16 | Staff | BorrowerStatFrm | clickRow("Nguyen Van A") | synchronous |
| 17 | BorrowerStatFrm | RentalSlipDAO | getRentalSlipsByCustomer(customerId=1, 01/01/2026, 31/12/2026) | synchronous |
| 18 | RentalSlipDAO | BorrowerStatFrm | return List<RentalSlip> | return |
| 19 | BorrowerStatFrm | RentalSlipDetailDAO | getDetailsByRentalSlip(rentalSlipId) | synchronous |
| 20 | RentalSlipDetailDAO | BorrowerStatFrm | return List<RentalSlipDetail> | return |
| 21 | BorrowerStatFrm | BorrowerStatFrm | buildInvoiceDetailList(rentalSlips, details) | self |
| 22 | BorrowerStatFrm | Staff | displayInvoiceDetail(data) | return |
| 23 | Staff | BorrowerStatFrm | clickBack() | synchronous |
| 24 | BorrowerStatFrm | Staff | displayBorrowerStatTable(previousData) | return |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê người mượn và xem chi tiết hóa đơn

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
| id | rentalDate | totalAmount | customerId | userId |
|----|-----------|-------------|------------|--------|
| 1 | 2026-01-15 | 450000 | 1 | 1 |
| 2 | 2026-02-20 | 750000 | 1 | 1 |
| 3 | 2026-03-10 | 300000 | 1 | 1 |
| 4 | 2026-04-05 | 320000 | 2 | 1 |
| 5 | 2026-05-12 | 390000 | 2 | 1 |
| 6 | 2026-03-25 | 280000 | 3 | 1 |

tblRentalSlipDetail:
| id | rentalSlipId | bookTitleId | quantity | unitPrice | amount |
|----|-------------|-------------|----------|-----------|--------|
| 1 | 1 | 1 | 3 | 150000 | 450000 |
| 2 | 2 | 1 | 5 | 150000 | 750000 |
| 3 | 3 | 2 | 2 | 120000 | 240000 |
| 4 | 3 | 3 | 1 | 60000 | 60000 |
| 5 | 4 | 2 | 2 | 120000 | 240000 |
| 6 | 4 | 3 | 1 | 80000 | 80000 |
| 7 | 5 | 1 | 3 | 130000 | 390000 |
| 8 | 6 | 2 | 2 | 140000 | 280000 |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập username: staff01, password: 123456, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 3 | Chọn menu Statistics → Borrower statistics | Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút View |
| 4 | Nhập ngày bắt đầu: 01/01/2026, ngày kết thức: 31/12/2026 | Các ô nhập hiển thị giá trị đã chọn |
| 5 | Nhấn nút View | Hệ thống hiển thị bảng: KH001 Nguyen Van A (CMND: 123456789, SĐT: 0901234567, Q1 TP.HCM, 3 lần mượn, 1,500,000đ); KH002 Tran Thi B (CMND: 987654321, SĐT: 0912345678, Q3 TP.HCM, 2 lần mượn, 710,000đ); KH003 Le Van C (CMND: 456789123, SĐT: 0923456789, Q7 TP.HCM, 1 lần mượn, 280,000đ). Sắp xếp theo tổng lần mượn giảm dần. |
| 6 | Nhấn vào dòng "Nguyen Van A" trong bảng | Hệ thống hiển thị bảng chi tiết hóa đơn bên dưới |
| 7 | Bảng chi tiết hiển thị 3 dòng: 15/01/2026 (3 cuốn, 450,000đ); 20/02/2026 (5 cuốn, 750,000đ); 10/03/2026 (2 cuốn, 300,000đ). Tổng = 1,500,000đ | Dữ liệu hiển thị chính xác, khớp với database |
| 8 | Nhấn nút Back | Quay về bảng thống kê danh sách KH, bảng chi tiết ẩn đi |

**Database sau khi test:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc/thống kê).
