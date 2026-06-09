# Subject No. 63 — Costume Rental — Module "Costume renting"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Cho thuê trang phục

**Mô tả:** Khách hàng mang trang phục đến quầy thuê. Staff đăng nhập, chọn chức năng cho thuê trang phục, tìm khách hàng theo tên, chọn đúng khách hàng (hoặc thêm mới). Sau đó staff tìm kiếm từng loại trang phục theo tên, chọn trang phục, nhập số lượng. Hệ thống tự động tính tiền cọc (tiền cọc = tổng giá trị trang phục). Staff nhấn tạo phiếu thuê, hệ thống lưu phiếu thuê vào database, in phiếu thuê, staff nhận tiền cọc từ khách hàng.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Khách hàng mang trang phục đến quầy thuê tại cửa hàng |
| 2 | Staff đăng nhập hệ thống với tài khoản `staff01` / `123456` |
| 3 | Staff chọn menu **Costume** → **Costume renting** |
| 4 | Hệ thống hiển thị giao diện cho thuê: ô nhập tên khách hàng (`txtCustomerName`), nút **Search**, nút **Add new customer** |
| 5 | Staff nhập "Tran Thi B" vào ô tìm kiếm khách hàng, nhấn **Search** |
| 6 | Hệ thống hiển thị danh sách khách hàng phù hợp: Tran Thi B (0904444444, 456 Nguyen Hue, HCM) |
| 7 | Staff click chọn khách hàng **Tran Thi B**. Hệ thống hiển thị giao diện thêm trang phục vào phiếu thuê với thông tin khách hàng đã chọn |
| 8 | Staff nhập "Vest nam" vào ô tìm kiếm trang phục, nhấn **Search** |
| 9 | Hệ thống hiển thị danh sách trang phục phù hợp: Vest nam den (Vest, cọc 500,000đ, tồn kho 10), Vest nam xanh (Vest, cọc 520,000đ, tồn kho 8) |
| 10 | Staff click chọn **Vest nam den**, nhập số lượng `2` |
| 11 | Hệ thống tự động tính tiền cọc cho Vest nam den: 2 × 500,000đ = 1,000,000đ và thêm dòng vào bảng phiếu thuê |
| 12 | Staff nhập "Vay dạ hội" vào ô tìm kiếm, nhấn **Search** |
| 13 | Hệ thống hiển thị danh sách: Vay da hoi do (Váy, cọc 800,000đ, tồn kho 8), Vay da hoi den (Váy, cọc 850,000đ, tồn kho 5) |
| 14 | Staff click chọn **Vay da hoi do**, nhập số lượng `1` |
| 15 | Hệ thống tự động tính tiền cọc: 1 × 800,000đ = 800,000đ, cập nhật bảng phiếu thuê. Tổng tiền cọc = 1,000,000 + 800,000 = 1,800,000đ (tự động tính) |
| 16 | Staff nhấn **Create rental slip** |
| 17 | Hệ thống lưu phiếu thuê vào database, cập nhật tồn kho trang phục (giảm số lượng), in phiếu thuê. Staff nhận tiền cọc 1,800,000đ từ khách hàng |

### Ngoại lệ

- **EX01:** Không tìm thấy khách hàng → hệ thống hiển thị danh sách trống, staff có thể nhấn **Add new customer** để thêm khách hàng mới
- **EX02:** Không tìm thấy trang phục → hệ thống hiển thị danh sách trống, staff kiểm tra lại tên trang phục
- **EX03:** Staff nhập số lượng <= 0 → hệ thống thông báo "Số lượng phải lớn hơn 0"
- **EX04:** Staff nhập số lượng lớn hơn tồn kho hiện tại → hệ thống thông báo "Số lượng thuê vượt quá tồn kho. Tồn kho hiện tại: {n}"
- **EX05:** Staff nhấn Create rental slip khi chưa có trang phục nào trong phiếu → hệ thống thông báo "Vui lòng thêm ít nhất 1 trang phục vào phiếu thuê"
- **EX06:** Staff chưa chọn khách hàng mà đã tìm trang phục → hệ thống thông báo "Vui lòng chọn khách hàng trước"

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê trang phục bao gồm các thực thể chính: **Customer** (khách hàng thuê), **Costume** (trang phục cho thuê), **RentalSlip** (phiếu thuê), **RentalSlipDetail** (chi tiết phiếu thuê), **User** (nhân viên).

- Một khách hàng có thể có nhiều phiếu thuê.
- Một phiếu thuê chứa nhiều chi tiết trang phục (mỗi chi tiết là 1 loại trang phục với số lượng và tiền cọc).
- Một loại trang phục có thể xuất hiện trong nhiều phiếu thuê khác nhau.
- Một nhân viên có thể tạo nhiều phiếu thuê.
- Tiền cọc = tổng giá trị trang phục (tự động tính: quantity × depositPrice của từng loại trang phục).

### Trích xuất danh từ → Entity

| Danh từ trong mô tả | Entity | Thuộc tính |
|----------------------|--------|------------|
| Khách hàng | **Customer** | customerId (PK), name, phone, address, email, isReturning (boolean) |
| Trang phục, trang phục cho thuê | **Costume** | costumeId (PK), name, category, rentalPrice, depositPrice, quantityInStock, status |
| Phiếu thuê | **RentalSlip** | rentalSlipId (PK), rentalDate, totalDeposit, status, customerId (FK), staffId (FK) |
| Chi tiết phiếu thuê | **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, unitDeposit, subtotal |
| Nhân viên / người dùng | **User** | userId (PK), username, password, fullName, role |

### Mối quan hệ

| Entity 1 | Entity 2 | Cardinality | Mô tả |
|-----------|----------|-------------|-------|
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu thuê chứa nhiều chi tiết trang phục |
| Costume | RentalSlipDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| User | RentalSlip | 1 : N | Một nhân viên tạo nhiều phiếu thuê |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeView:
  tùy chọn cho thuê trang phục → subCostumeRenting

Chọn Costume renting → hiển thị RentalSlipFrm:
  ô nhập tên khách hàng → inCustomerName
  nút tìm kiếm khách hàng → subSearchCustomer
  nút thêm khách hàng mới → subAddNewCustomer

Nhập "Tran Thi B", nhấn Search → hiển thị danh sách:
  bảng khách hàng (click chọn) → outCustomerList

Click chọn khách hàng "Tran Thi B" → hiển thị giao diện thêm trang phục vào phiếu thuê:
  nhãn khách hàng đã chọn → outSelectedCustomer
  ô nhập tên trang phục → inCostumeName
  nút tìm kiếm trang phục → subSearchCostume

Nhập "Vest nam", nhấn Search → hiển thị danh sách trang phục:
  bảng trang phục (click chọn) → outCostumeList

Chọn trang phục, nhập số lượng:
  ô nhập số lượng → inQuantity
  nút thêm vào phiếu thuê → subAddItem

Nhấn Add to rental slip → cập nhật phiếu thuê:
  bảng chi tiết phiếu thuê → outRentalDetailTable
  tổng tiền cọc → outTotalDeposit

(Lặp lại cho trang phục khác: "Váy dạ hội đỏ" × 1)

Nhấn Create rental slip → subCreateSlip
  hệ thống lưu phiếu thuê, cập nhật tồn kho, in phiếu thuê, nhận tiền cọc

### Tóm tắt
View classes: HomeView, RentalSlipFrm
Methods: searchCustomer(), addCustomer(), searchCostume(), getCostumeById(), createSlip(), addDetail(), updateStock()

### ASCII Class Diagram

```
+---------------------+         +---------------------+
|      Customer       |         |       Costume       |
+---------------------+         +---------------------+
| - customerId (PK)   |         | - costumeId (PK)    |
| - name              |         | - name              |
| - phone             |         | - category          |
| - address           |         | - rentalPrice       |
| - email             |         | - depositPrice      |
| - isReturning       |         | - quantityInStock   |
+---------------------+         | - status            |
           | 1                  +---------------------+
           |                           | 1
           | N                         | N
+---------------------+    +---------------------+
|     RentalSlip      |    |  RentalSlipDetail   |
+---------------------+    +---------------------+
| - rentalSlipId (PK) |    | - detailId (PK)     |
| - rentalDate        |    | - rentalSlipId (FK) |
| - totalDeposit      |    | - costumeId (FK)    |
| - status            |    | - quantity          |
| - customerId (FK)   |    | - unitDeposit       |
| - staffId (FK)      |    | - subtotal          |
+---------------------+    +---------------------+
           | 1
           |
           | N
+---------------------+
|       User          |
+---------------------+
| - userId (PK)       |
| - username          |
| - password          |
| - fullName          |
| - role              |
+---------------------+
```

### Bảng quan hệ (Relation Schema)

| Bảng | Khóa chính | Khóa ngoại | Tham chiếu đến |
|------|-----------|------------|----------------|
| tblCustomer | customerId | — | — |
| tblCostume | costumeId | — | — |
| tblUser | userId | — | — |
| tblRentalSlip | rentalSlipId | customerId, staffId | tblCustomer(customerId), tblUser(userId) |
| tblRentalSlipDetail | detailId | rentalSlipId, costumeId | tblRentalSlip(rentalSlipId), tblCostume(costumeId) |

---

## Câu 3: Thiết kế tĩnh

### View Class: `RentalSlipFrm`

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `txtCustomerName` | JTextField | Ô nhập tên khách hàng cần tìm |
| `btnSearchCustomer` | JButton | Nút "Search" để tìm khách hàng theo tên |
| `tblCustomerList` | JTable | Bảng danh sách khách hàng tìm được (tên, điện thoại, địa chỉ) |
| `btnAddNewCustomer` | JButton | Nút "Add new customer" để thêm khách hàng mới khi không tìm thấy |
| `lblSelectedCustomer` | JLabel | Hiển thị tên khách hàng đã chọn |
| `txtCostumeName` | JTextField | Ô nhập tên trang phục cần tìm |
| `btnSearchCostume` | JButton | Nút "Search" để tìm trang phục theo tên |
| `tblCostumeList` | JTable | Bảng danh sách trang phục tìm được (tên, danh mục, giá cọc, tồn kho) |
| `txtQuantity` | JTextField | Ô nhập số lượng thuê |
| `btnAddItem` | JButton | Nút "Add to rental slip" để thêm trang phục vào phiếu thuê |
| `tblRentalDetail` | JTable | Bảng hiển thị chi tiết phiếu thuê (tên trang phục, số lượng, tiền cọc/chi tiết) |
| `lblTotalDeposit` | JLabel | Hiển thị tổng tiền cọc (tự động tính: tổng subtotal) |
| `btnCreateSlip` | JButton | Nút "Create rental slip" để lưu phiếu thuê và in |
| `btnRemoveItem` | JButton | Nút xóa 1 dòng khỏi phiếu thuê (khi chọn sai) |
| `lblStatus` | JLabel | Hiển thị thông báo trạng thái |

### DAO Classes

#### `CustomerDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Customer>` | Tìm kiếm khách hàng theo tên (tìm gần đúng, LIKE '%name%') |
| `addCustomer(customer)` | `boolean` | Thêm mới khách hàng vào tblCustomer |
| `getCustomerById(customerId)` | `Customer` | Lấy thông tin khách hàng theo mã |

#### `CostumeDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Costume>` | Tìm kiếm trang phục theo tên (tìm gần đúng, LIKE '%name%') |
| `getCostumeById(costumeId)` | `Costume` | Lấy thông tin trang phục theo mã |
| `updateStock(costumeId, quantityToSubtract)` | `boolean` | Cập nhật tồn kho: quantityInStock = quantityInStock - quantityToSubtract |

#### `RentalSlipDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `createSlip(slip)` | `int` | Tạo phiếu thuê mới vào tblRentalSlip, trả về rentalSlipId |

#### `RentalSlipDetailDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `addDetail(detail)` | `boolean` | Thêm 1 chi tiết phiếu thuê vào tblRentalSlipDetail |
| `getDetailsBySlipId(rentalSlipId)` | `List<RentalSlipDetail>` | Lấy danh sách chi tiết theo mã phiếu thuê |

### Entity Types

| Entity | Mô tả |
|--------|-------|
| `Customer` | Khách hàng: customerId, name, phone, address, email, isReturning |
| `Costume` | Trang phục: costumeId, name, category, rentalPrice, depositPrice, quantityInStock, status |
| `RentalSlip` | Phiếu thuê: rentalSlipId, rentalDate, totalDeposit, status, customerId, staffId |
| `RentalSlipDetail` | Chi tiết phiếu thuê: detailId, rentalSlipId, costumeId, quantity, unitDeposit, subtotal |
| `User` | Nhân viên: userId, username, password, fullName, role |

### Database Schema

```sql
CREATE TABLE tblCustomer (
    customerId  VARCHAR(10) PRIMARY KEY,
    name        NVARCHAR(100),
    phone       VARCHAR(15),
    address     NVARCHAR(200),
    email       VARCHAR(100),
    isReturning BOOLEAN DEFAULT FALSE
);

CREATE TABLE tblCostume (
    costumeId       VARCHAR(10) PRIMARY KEY,
    name            NVARCHAR(100),
    category        NVARCHAR(50),
    rentalPrice     DECIMAL(15,2),
    depositPrice    DECIMAL(15,2),
    quantityInStock INT,
    status          VARCHAR(20)
);

CREATE TABLE tblUser (
    userId      VARCHAR(10) PRIMARY KEY,
    username    VARCHAR(50),
    password    VARCHAR(100),
    fullName    NVARCHAR(100),
    role        VARCHAR(20)
);

CREATE TABLE tblRentalSlip (
    rentalSlipId VARCHAR(10) PRIMARY KEY,
    rentalDate   DATE,
    totalDeposit DECIMAL(15,2),
    status       VARCHAR(20),
    customerId   VARCHAR(10) REFERENCES tblCustomer(customerId),
    staffId      VARCHAR(10) REFERENCES tblUser(userId)
);

CREATE TABLE tblRentalSlipDetail (
    detailId      VARCHAR(10) PRIMARY KEY,
    rentalSlipId  VARCHAR(10) REFERENCES tblRentalSlip(rentalSlipId),
    costumeId     VARCHAR(10) REFERENCES tblCostume(costumeId),
    quantity      INT,
    unitDeposit   DECIMAL(15,2),
    subtotal      DECIMAL(15,2)
);
```

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Class Diagram**
2. Tạo 5 class: Customer, Costume, RentalSlip, RentalSlipDetail, User
3. Thêm attributes cho từng class (xem bảng Entity Types)
4. Vẽ mối quan hệ:
   - Customer → RentalSlip (1:N) với navigable arrow từ RentalSlip sang Customer
   - RentalSlip → RentalSlipDetail (1:N) với composition (filled diamond)
   - Costume → RentalSlipDetail (1:N) với navigable arrow từ RentalSlipDetail sang Costume
   - User → RentalSlip (1:N) với navigable arrow từ RentalSlip sang User
5. Ghi chú cardinality ở mỗi đầu mối quan hệ

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Sequence Diagram**
2. Tạo 5 lifelines theo thứ tự từ trái sang phải:
   - `:Staff` (actor stereotype)
   - `:RentalSlipFrm` (boundary stereotype)
   - `:CustomerDAO` (control stereotype)
   - `:CostumeDAO` (control stereotype)
   - `:RentalSlipDAO` (control stereotype)
   - `:RentalSlipDetailDAO` (control stereotype)
3. Vẽ message flow theo bảng bên dưới
4. Sử dụng `loop` fragment cho phần lặp thêm trang phục vào phiếu thuê
5. Sử dụng `alt` fragment cho trường hợp khách hàng mới

### Lifelines

- **:Staff** — actor, người thực hiện thao tác
- **:RentalSlipFrm** — boundary, giao diện cho thuê trang phục
- **:CustomerDAO** — control, truy vấn khách hàng
- **:CostumeDAO** — control, truy vấn trang phục và cập nhật tồn kho
- **:RentalSlipDAO** — control, tạo phiếu thuê
- **:RentalSlipDetailDAO** — control, thêm chi tiết phiếu thuê

### ASCII Sequence Diagram

```
Staff              RentalSlipFrm       CustomerDAO   CostumeDAO   RentalSlipDAO  RentalSlipDetailDAO
  |                     |                  |              |              |                   |
  |--- selectFunction-->|                  |              |              |                   |
  |                     |                  |              |              |                   |
  | <-- showForm -------|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |--- enterCustomerName->                |              |              |                   |
  |    ("Tran Thi B")   |                  |              |              |                   |
  |--- clickSearch ---->|                  |              |              |                   |
  |                     |---searchByName-->|              |              |                   |
  |                     |   ("Tran Thi B") |              |              |                   |
  |                     |                  |              |              |                   |
  |                     |<--List<Customer>-|              |              |                   |
  |                     |                  |              |              |                   |
  | <-- showCustomerList|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |---clickCustomer --->|                  |              |              |                   |
  |    (Tran Thi B)     |                  |              |              |                   |
  |                     |                  |              |              |                   |
  | <-- showRentalForm -|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |                     |                  |              |              |                   |
  |   <<loop: repeat for each costume>>   |              |              |                   |
  |                     |                  |              |              |                   |
  |---enterCostumeName->|                  |              |              |                   |
  |    ("Vest nam")     |                  |              |              |                   |
  |---clickSearch ---->|                  |              |              |                   |
  |                     |---searchByName------------>|              |                   |
  |                     |   ("Vest nam")  |              |              |                   |
  |                     |                  |              |              |                   |
  |                     |<--List<Costume>--------------|              |                   |
  |                     |                  |              |              |                   |
  | <-- showCostumeList |                  |              |              |                   |
  |                     |                  |              |              |                   |
  |---selectCostume --->|                  |              |              |                   |
  |    (Vest nam den)   |                  |              |              |                   |
  |---enterQuantity --->|                  |              |              |                   |
  |    (2)              |                  |              |              |                   |
  |---clickAddItem ---->|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |                     |---getCostumeById------------>|              |                   |
  |                     |                  |              |              |                   |
  |                     |<--Costume -------------------|              |                   |
  |                     |                  |              |              |                   |
  |                     | (calc subtotal = qty × depositPrice)        |                   |
  |                     |                  |              |              |                   |
  | <-- updateSlip -----|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |   <<end loop>>      |                  |              |              |                   |
  |                     |                  |              |              |                   |
  |---clickCreateSlip-->|                  |              |              |                   |
  |                     |                  |              |              |                   |
  |                     |---createSlip -------------------------------------------->         |
  |                     |                  |              |              |                   |
  |                     |<--rentalSlipId -----------------------------------------          |
  |                     |                  |              |              |                   |
  |                     |---addDetail (Vest nam den x2, deposit 1000000) ------------>       |
  |                     |<--true -----------------------------------------------------        |
  |                     |                  |              |              |                   |
  |                     |---addDetail (Vay da hoi do x1, deposit 800000) ---------->          |
  |                     |<--true -----------------------------------------------------        |
  |                     |                  |              |              |                   |
  |                     |---updateStock(CS001, -2) ---------->|              |                   |
  |                     |<--true ----------------------------|              |                   |
  |                     |                  |              |              |                   |
  |                     |---updateStock(CS002, -1) ---------->|              |                   |
  |                     |<--true ----------------------------|              |                   |
  |                     |                  |              |              |                   |
  | <-- printSlip ------|                  |              |              |                   |
  | <-- receiveDeposit -|                  |              |              |                   |
  |    (1,800,000)      |                  |              |              |                   |
```

### Bảng message flow chi tiết

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|-----|---------|---------|
| 1 | Staff | RentalSlipFrm | `selectFunction()` | Staff chọn menu Costume → Costume renting |
| 2 | RentalSlipFrm | Staff | `showForm()` | Hiển thị giao diện tìm khách hàng |
| 3 | Staff | RentalSlipFrm | `enterCustomerName("Tran Thi B")` | Nhập tên khách hàng |
| 4 | Staff | RentalSlipFrm | `clickSearch()` | Nhấn nút Search |
| 5 | RentalSlipFrm | CustomerDAO | `searchByName("Tran Thi B")` | Truy vấn khách hàng |
| 6 | CustomerDAO | RentalSlipFrm | `return List<Customer>` | Trả về danh sách khách hàng |
| 7 | RentalSlipFrm | Staff | `showCustomerList(list)` | Hiển thị danh sách khách hàng |
| 8 | Staff | RentalSlipFrm | `clickCustomer("Tran Thi B")` | Chọn khách hàng |
| 9 | RentalSlipFrm | Staff | `showRentalForm()` | Hiển thị giao diện thêm trang phục vào phiếu thuê |
| 10 | Staff | RentalSlipFrm | `enterCostumeName("Vest nam")` | Nhập tên trang phục |
| 11 | Staff | RentalSlipFrm | `clickSearch()` | Nhấn nút Search |
| 12 | RentalSlipFrm | CostumeDAO | `searchByName("Vest nam")` | Truy vấn trang phục |
| 13 | CostumeDAO | RentalSlipFrm | `return List<Costume>` | Trả về danh sách trang phục |
| 14 | RentalSlipFrm | Staff | `showCostumeList(list)` | Hiển thị danh sách trang phục |
| 15 | Staff | RentalSlipFrm | `selectCostume("Vest nam den")` | Chọn trang phục |
| 16 | Staff | RentalSlipFrm | `enterQuantity(2)` | Nhập số lượng thuê |
| 17 | Staff | RentalSlipFrm | `clickAddItem()` | Thêm vào phiếu thuê |
| 18 | RentalSlipFrm | CostumeDAO | `getCostumeById(costumeId)` | Lấy thông tin trang phục (bao gồm depositPrice) |
| 19 | CostumeDAO | RentalSlipFrm | `return Costume` | Trả về đối tượng Costume |
| 20 | RentalSlipFrm | RentalSlipFrm | `calculateSubtotal(quantity, depositPrice)` | Tính subtotal = 2 × 500,000 = 1,000,000 |
| 21 | RentalSlipFrm | Staff | `showUpdatedSlip()` | Hiển thị phiếu thuê cập nhật |
| 22 | (Lặp bước 10-21 cho Vay da hoi do × 1, subtotal = 800,000) | | | |
| 23 | RentalSlipFrm | RentalSlipFrm | `calculateTotalDeposit()` | Tổng cọc = 1,000,000 + 800,000 = 1,800,000 |
| 24 | Staff | RentalSlipFrm | `clickCreateSlip()` | Nhấn tạo phiếu thuê |
| 25 | RentalSlipFrm | RentalSlipDAO | `createSlip(slip)` | Tạo phiếu thuê mới |
| 26 | RentalSlipDAO | RentalSlipFrm | `return rentalSlipId` | Trả về rentalSlipId |
| 27 | RentalSlipFrm | RentalSlipDetailDAO | `addDetail(detail1)` | Thêm chi tiết: Vest nam den ×2, cọc 1,000,000 |
| 28 | RentalSlipDetailDAO | RentalSlipFrm | `return true` | Thành công |
| 29 | RentalSlipFrm | RentalSlipDetailDAO | `addDetail(detail2)` | Thêm chi tiết: Vay da hoi do ×1, cọc 800,000 |
| 30 | RentalSlipDetailDAO | RentalSlipFrm | `return true` | Thành công |
| 31 | RentalSlipFrm | CostumeDAO | `updateStock("CS001", -2)` | Giảm tồn kho Vest nam den: 10 → 8 |
| 32 | CostumeDAO | RentalSlipFrm | `return true` | Thành công |
| 33 | RentalSlipFrm | CostumeDAO | `updateStock("CS002", -1)` | Giảm tồn kho Vay da hoi do: 8 → 7 |
| 34 | CostumeDAO | RentalSlipFrm | `return true` | Thành công |
| 35 | RentalSlipFrm | Staff | `printSlip()` | In phiếu thuê |
| 36 | RentalSlipFrm | Staff | `receiveDeposit(1,800,000)` | Thông báo nhận tiền cọc từ khách hàng |

---

## Câu 5: Blackbox Testcase — TC01

### Testcase: Tạo phiếu thuê trang phục cho khách hàng Tran Thi B

#### Database trước khi test

**tblUser**

| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | Staff |

**tblCustomer**

| customerId | name | phone | address | email | isReturning |
|------------|------|-------|---------|-------|-------------|
| C001 | Tran Thi B | 0904444444 | 456 Nguyen Hue, HCM | ttb@email.com | TRUE |

**tblCostume**

| costumeId | name | category | rentalPrice | depositPrice | quantityInStock | status |
|-----------|------|----------|-------------|--------------|-----------------|--------|
| CS001 | Vest nam den | Vest | 100,000 | 500,000 | 10 | Active |
| CS002 | Vay da hoi do | Vay | 150,000 | 800,000 | 8 | Active |
| CS003 | Ao dai trang | Ao dai | 120,000 | 600,000 | 5 | Active |

**tblRentalSlip** — rỗng

**tblRentalSlipDetail** — rỗng

#### Database sau khi test

**tblUser** — không thay đổi

**tblCustomer** — không thay đổi

**tblCostume**

| costumeId | name | category | rentalPrice | depositPrice | quantityInStock | status |
|-----------|------|----------|-------------|--------------|-----------------|--------|
| CS001 | Vest nam den | Vest | 100,000 | 500,000 | **8** (-2) | Active |
| CS002 | Vay da hoi do | Vay | 150,000 | 800,000 | **7** (-1) | Active |
| CS003 | Ao dai trang | Ao dai | 120,000 | 600,000 | 5 | Active |

**tblRentalSlip**

| rentalSlipId | rentalDate | totalDeposit | status | customerId | staffId |
|-------------|------------|-------------|--------|------------|---------|
| RS001 | 2026-06-08 | 1,800,000 | Active | C001 | U001 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | unitDeposit | subtotal |
|----------|-------------|-----------|----------|-------------|----------|
| D001 | RS001 | CS001 | 2 | 500,000 | 1,000,000 |
| D002 | RS001 | CS002 | 1 | 800,000 | 800,000 |

#### Bảng các bước test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập hệ thống | Username: `staff01`, Password: `123456` | Đăng nhập thành công, hiển thị giao diện chính (HomeFrm) |
| 2 | Staff chọn menu Costume → Costume renting | — | Hiển thị giao diện RentalSlipFrm: ô nhập tên khách hàng, nút Search, nút Add new customer |
| 3 | Staff nhập tên khách hàng | `Tran Thi B` | Ô txtCustomerName hiển thị "Tran Thi B" |
| 4 | Staff nhấn nút Search | — | Bảng tblCustomerList hiển thị 1 dòng: Tran Thi B (0904444444, 456 Nguyen Hue, HCM) |
| 5 | Staff click chọn khách hàng Tran Thi B | — | Giao diện chuyển sang phần thêm trang phục. lblSelectedCustomer hiển thị "Tran Thi B" |
| 6 | Staff nhập tên trang phục, nhấn Search | `Vest nam` | Bảng tblCostumeList hiển thị: Vest nam den (Vest, cọc 500,000đ, tồn kho 10) |
| 7 | Staff chọn Vest nam den, nhập số lượng | Số lượng: `2` | Nhấn "Add to rental slip" → Bảng tblRentalDetail hiển thị 1 dòng: Vest nam den × 2, cọc 1,000,000đ. lblTotalDeposit = "1,000,000đ" |
| 8 | Staff nhập tên trang phục, nhấn Search | `Vay da hoi` | Bảng tblCostumeList hiển thị: Vay da hoi do (Vay, cọc 800,000đ, tồn kho 8) |
| 9 | Staff chọn Vay da hoi do, nhập số lượng | Số lượng: `1` | Nhấn "Add to rental slip" → Bảng tblRentalDetail hiển thị 2 dòng: Vest nam den × 2 (1,000,000đ), Vay da hoi do × 1 (800,000đ). lblTotalDeposit = "1,800,000đ" (tự động tính) |
| 10 | Staff nhấn nút Create rental slip | — | Hệ thống lưu phiếu thuê vào tblRentalSlip (RS001, 2026-06-08, 1,800,000, Active, C001, U001), lưu chi tiết vào tblRentalSlipDetail (2 dòng), cập nhật tồn kho tblCostume (CS001: 10→8, CS002: 8→7), in phiếu thuê, thông báo nhận cọc 1,800,000đ thành công |

#### Giải thích dữ liệu kỳ vọng

- **Vest nam den subtotal:** quantity(2) × depositPrice(500,000) = 1,000,000đ
- **Vay da hoi do subtotal:** quantity(1) × depositPrice(800,000) = 800,000đ
- **Total deposit:** 1,000,000 + 800,000 = 1,800,000đ
- **Tồn kho CS001:** 10 - 2 = 8
- **Tồn kho CS002:** 8 - 1 = 7
- **CS003 không thay đổi:** Không được chọn trong phiếu thuê
