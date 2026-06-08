# Subject No. 51 — Mini Football Field — Module "Goods importing"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Nhập hàng hóa

### Diễn biến chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện với ô nhập username, password, nút Login. |
| 2 | Staff nhập `staff01`, password `******`, nhấn Login. Hệ thống xác thực, chuyển sang giao diện Home. |
| 3 | Giao diện Home hiển thị các menu: Booking, Import Goods, Customer Payment, Statistics. |
| 4 | Staff chọn **Import Goods**. |
| 5 | Giao diện nhập hàng xuất hiện: ô nhập tên nhà cung cấp (`inSupplierName`), nút **Search** nhà cung cấp. |
| 6 | Staff nhập "Coca", nhấn **Search**. Hệ thống tìm nhà cung cấp có tên chứa "Coca". |
| 7 | Hệ thống hiển thị danh sách nhà cung cấp: mã NCC, tên, địa chỉ, SĐT. Staff nhấn vào "NCC01 - Coca Cola Company - 789 Hai Ba Trung, Q3 - 02838123456". |
| 8 | Hệ thống xác nhận NCC đã chọn. Giao diện chuyển sang phần tìm kiếm hàng hóa: ô nhập tên hàng (`inProductName`), nút **Search** hàng, ô nhập số lượng, ô nhập đơn giá, nút **Add**. Bảng hóa đơn nhập bên dưới. |
| 9 | Staff nhập "Coca Cola", nhấn **Search** hàng. Hệ thống hiển thị danh sách hàng: mã, tên, giá bán hiện tại. |
| 10 | Staff chọn "SP01 - Coca Cola 330ml", nhập số lượng `100`, nhập đơn giá `8,000đ`, nhấn **Add**. Hàng thêm vào bảng hóa đơn nhập. |
| 11 | Staff nhập "Sprite", nhấn **Search** hàng. Hệ thống hiển thị: "SP02 - Sprite 330ml". |
| 12 | Staff chọn "SP02 - Sprite 330ml", nhập số lượng `50`, nhập đơn giá `8,000đ`, nhấn **Add**. Hàng thêm vào bảng hóa đơn. |
| 13 | Bảng hóa đơn nhập hiển thị: Coca Cola 330ml × 100 × 8,000đ = 800,000đ, Sprite 330ml × 50 × 8,000đ = 400,000đ. Tổng cộng: 1,200,000đ. |
| 14 | Staff nhấn **Submit**. |
| 15 | Hệ thống lưu hóa đơn nhập vào database, hiển thị thông báo "Nhap hang thanh cong", in hóa đơn nhập hàng. |

### Kịch bản ngoại lệ

- **EL1:** Nhà cung cấp không tồn tại → Staff nhấn nút **Add New Supplier** (tên, địa chỉ, email, SĐT, mô tả) để thêm mới, sau đó tiếp tục.
- **EL2:** Hàng hóa không tồn tại trong hệ thống → Staff không thể chọn, phải nhập hàng mới trước (ngoại lệ nghiệp vụ).
- **EL3:** Staff nhập số lượng <= 0 hoặc đơn giá <= 0 → hệ thống cảnh báo "So luong va don gia phai lon hon 0".
- **EL4:** Staff chưa chọn nhà cung cấp mà đã nhấn Search hàng → hệ thống cảnh báo "Vui long chon nha cung cap truoc".

---

## Câu 2: Entity Class Diagram

### Mô tả tự nhiên

Nhà cung cấp (Supplier) cung cấp hàng hóa cho sân bóng đá mini. Khi nhập hàng, nhân viên lập hóa đơn nhập (ImportInvoice) liên kết với một nhà cung cấp, chứa nhiều chi tiết (ImportInvoiceDetail), mỗi chi tiết liên kết với một sản phẩm (Product). Nhân viên thao tác trên hệ thống là User.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Supplier | Entity class | Nhà cung cấp hàng hóa |
| Product | Entity class | Mặt hàng đồ ăn/thức uống |
| ImportInvoice | Entity class | Hóa đơn nhập hàng tại một thời điểm |
| ImportInvoiceDetail | Entity class | Chi tiết từng mặt hàng trong hóa đơn nhập |
| User | Entity class | Nhân viên thao tác trên hệ thống |

### Class Diagram (ASCII)

```
+------------------+       +---------------------+
|    Supplier      |       |   ImportInvoice     |
+------------------+       +---------------------+
| -id: int         |  1  n | -id: int            |
| -code: String    |------>| -supplierId: int    |
| -name: String    |       | -importDate: DateTime|
| -address: String |       | -totalAmount: double|
| -email: String   |       | -userId: int        |
| -phone: String   |       +---------------------+
| -description: String|    | +getInvoiceDetails()|
+------------------+       +---------------------+
| +getProducts()   |                | 1
+------------------+                | n
                                    v
                           +---------------------+
                           | ImportInvoiceDetail  |
                           +---------------------+
                           | -id: int            |
                           | -importInvoiceId: int|
                           | -productId: int     |
                           | -quantity: int      |
                           | -unitPrice: double  |
                           | -amount: double     |
                           +---------------------+
                           | +getProduct()       |
                           +---------------------+
                                    | n
                                    | 1
                                    v
+------------------+       +---------------------+
|    Product       |       |    Product          |
+------------------+<------+
| -id: int         |
| -code: String    |
| -name: String    |
| -price: double   |
| -description: String|
+------------------+
| +getImportHistory|
+------------------+

+------------------+
|      User        |
+------------------+
| -id: int         |
| -username: String|
| -password: String|
| -role: String    |
+------------------+
| +checkLogin()    |
+------------------+
```

### Bảng quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Supplier → ImportInvoice | 1-n | Một nhà cung cấp có nhiều hóa đơn nhập |
| ImportInvoice → ImportInvoiceDetail | 1-n | Một hóa đơn nhập có nhiều chi tiết |
| Product → ImportInvoiceDetail | 1-n | Một sản phẩm xuất hiện trong nhiều chi tiết nhập |
| User → ImportInvoice | 1-n | Một nhân viên lập nhiều hóa đơn nhập |

---

## Câu 3: Thiết kế tĩnh

### View classes

**ImportGoodsFrm** (giao diện nhập hàng):

| Thành phần | Loại | Mô tả |
|------------|------|-------|
| `inSupplierName` | Text field | Ô nhập tên nhà cung cấp |
| `subSearchSupplier` | Button | Nút Search — tìm nhà cung cấp |
| `outSupplierTable` | Table (clickable) | Bảng nhà cung cấp: mã, tên, địa chỉ, SĐT. Mỗi dòng click được. |
| `subAddSupplier` | Button | Nút Add New — thêm NCC mới |
| `inProductName` | Text field | Ô nhập tên hàng hóa |
| `subSearchProduct` | Button | Nút Search — tìm hàng hóa |
| `outProductTable` | Table (clickable) | Bảng hàng hóa: mã, tên, giá bán. Mỗi dòng click được. |
| `inQuantity` | Text field | Ô nhập số lượng nhập |
| `inUnitPrice` | Text field | Ô nhập đơn giá nhập |
| `subAdd` | Button | Nút Add — thêm vào hóa đơn |
| `outInvoiceTable` | Table | Bảng hóa đơn nhập: tên hàng, số lượng, đơn giá, thành tiền. Chỉ đọc. |
| `outTotal` | Label | Hiển thị tổng tiền hóa đơn |
| `subSubmit` | Button | Nút Submit — lưu hóa đơn |

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| SupplierDAO | `searchSupplierByName(keyword): List<Supplier>` | List<Supplier> | Tìm nhà cung cấp theo tên |
| SupplierDAO | `addSupplier(supplier): boolean` | boolean | Thêm nhà cung cấp mới |
| ProductDAO | `searchProductByName(keyword): List<Product>` | List<Product> | Tìm hàng hóa theo tên |
| ImportInvoiceDAO | `createImportInvoice(invoice): int` | int | Tạo hóa đơn nhập, trả về invoiceId |
| ImportInvoiceDetailDAO | `addInvoiceDetails(details): boolean` | boolean | Thêm chi tiết hóa đơn (batch) |

### Entity types

| Entity | Bảng | Mô tả |
|--------|------|-------|
| Supplier | tblSupplier | Nhà cung cấp hàng hóa |
| Product | tblProduct | Mặt hàng đồ ăn/thức uống |
| ImportInvoice | tblImportInvoice | Hóa đơn nhập hàng |
| ImportInvoiceDetail | tblImportInvoiceDetail | Chi tiết hóa đơn nhập |
| User | tblUser | Người dùng hệ thống |

### Database schema

```
tblUser
├── ID (PK, int, identity)
├── username (varchar, unique)
├── password (varchar)
├── role (varchar)
└── description (varchar)

tblSupplier
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
├── address (nvarchar)
├── email (varchar)
├── phone (varchar)
└── description (nvarchar)

tblProduct
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
├── price (float)
└── description (nvarchar)

tblImportInvoice
├── ID (PK, int, identity)
├── supplierID (FK → tblSupplier.ID)
├── importDate (datetime)
├── totalAmount (float)
└── userID (FK → tblUser.ID)

tblImportInvoiceDetail
├── ID (PK, int, identity)
├── importInvoiceID (FK → tblImportInvoice.ID)
├── productID (FK → tblProduct.ID)
├── quantity (int)
├── unitPrice (float)
└── amount (float)
```

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package **View**: thêm boundary class `ImportGoodsFrm` với tất cả attribute input/output/sub.
2. Tạo package **Entity**: thêm entity classes `Supplier`, `Product`, `ImportInvoice`, `ImportInvoiceDetail`, `User` với các attribute.
3. Tạo package **DAO**: thêm control classes `SupplierDAO`, `ProductDAO`, `ImportInvoiceDAO`, `ImportInvoiceDetailDAO` với các method.
4. Vẽ dependency: `ImportGoodsFrm` → `SupplierDAO`, `ImportGoodsFrm` → `ProductDAO`, `ImportGoodsFrm` → `ImportInvoiceDAO`.
5. Vẽ association 1-n giữa `Supplier` → `ImportInvoice`, `ImportInvoice` → `ImportInvoiceDetail`, `Product` → `ImportInvoiceDetail`.
6. Thêm note mô tả quy trình: search supplier → loop (search product → enter qty/price → add) → submit.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo 5 lifelines: `:Staff` (actor), `:ImportGoodsFrm` (boundary), `:SupplierDAO` (control), `:ProductDAO` (control), `:ImportInvoiceDAO` (control).
2. Vẽ message từ Staff đến ImportGoodsFrm cho các thao tác.
3. Vẽ message từ ImportGoodsFrm đến DAO cho truy vấn và lưu dữ liệu.
4. Sử dụng `loop` fragment cho vòng lặp tìm kiếm và thêm hàng hóa.
5. Sử dụng `alt` fragment cho NCC mới / NCC cũ.

### ASCII Sequence Diagram

```
Staff            ImportGoodsFrm     SupplierDAO    ProductDAO    ImportInvoiceDAO
  |                    |                 |              |               |
  |--enterSupplier--->|                 |              |               |
  |  ("Coca")         |                 |              |               |
  |--clickSearchSup-->|                 |              |               |
  |                    |                 |              |               |
  |                    |--searchSupplier>|              |               |
  |                    |  ("Coca")       |              |               |
  |                    |                 |--query DB    |               |
  |                    |                 |<-return List-|               |
  |                    |<--List<Supplier>|              |               |
  |                    |                 |              |               |
  |<--showSuppliers----|                 |              |               |
  |                    |                 |              |               |
  |--clickSupplier---->|                 |              |               |
  |  (Coca Cola Co.)   |                 |              |               |
  |                    |                 |              |               |
  |                    |===LOOP: cho moi mat hang===|  |               |
  |                    |                 |              |               |
  |--enterProduct----->|                 |              |               |
  |  ("Coca Cola")     |                 |              |               |
  |--clickSearchProd-->|                 |              |               |
  |                    |                 |              |               |
  |                    |-----------------|------------->|               |
  |                    |--searchProduct->|              |               |
  |                    |  ("Coca Cola")  |              |--query DB     |
  |                    |                 |              |<-return List--|
  |                    |<--List<Product>----------------|               |
  |                    |                 |              |               |
  |<--showProducts-----|                 |              |               |
  |                    |                 |              |               |
  |--selectProduct---->|                 |              |               |
  |  (Coca Cola 330ml) |                 |              |               |
  |--enterQty(100)---->|                 |              |               |
  |--enterPrice(8000)->|                 |              |               |
  |--clickAdd--------->|                 |              |               |
  |                    |                 |              |               |
  |                    |===END LOOP===   |              |               |
  |                    |                 |              |               |
  |--clickSubmit------>|                 |              |               |
  |                    |                 |              |               |
  |                    |--createInvoice->|              |               |
  |                    |  (invoice)      |              |--INSERT inv   |
  |                    |                 |              |<-invoiceId----|
  |                    |<--invoiceId-----|              |               |
  |                    |                 |              |               |
  |                    |--addDetails---->|              |               |
  |                    |  (details)      |              |--INSERT details|
  |                    |                 |              |<-true---------|
  |                    |<--true----------|              |               |
  |                    |                 |              |               |
  |<--success----------|                 |              |               |
  |  "Nhap hang OK"    |                 |              |               |
```

### Bảng chi tiết message (>= 20 bước)

| # | Từ | Đến | Message | Ghi chú |
|---|-----|-----|---------|---------|
| 1 | Staff | ImportGoodsFrm | `enterSupplierName("Coca")` | Nhập tên NCC |
| 2 | Staff | ImportGoodsFrm | `clickSearchSupplier()` | Nhấn Search NCC |
| 3 | ImportGoodsFrm | SupplierDAO | `searchSupplierByName("Coca")` | Gọi tìm NCC |
| 4 | SupplierDAO | SupplierDAO | `executeSQL()` | Thực thi SQL LIKE |
| 5 | SupplierDAO | ImportGoodsFrm | `return List<Supplier>` | Trả về danh sách NCC |
| 6 | ImportGoodsFrm | ImportGoodsFrm | `displaySupplierTable(list)` | Hiển thị bảng NCC |
| 7 | Staff | ImportGoodsFrm | `clickSupplier("Coca Cola Company")` | Chọn NCC |
| 8 | ImportGoodsFrm | ImportGoodsFrm | `setSelectedSupplier()` | Lưu NCC đã chọn |
| 9 | ImportGoodsFrm | ImportGoodsFrm | `showProductSearchPanel()` | Hiển thị form tìm hàng |
| 10 | Staff | ImportGoodsFrm | `enterProductName("Coca Cola")` | Nhập tên hàng |
| 11 | Staff | ImportGoodsFrm | `clickSearchProduct()` | Nhấn Search hàng |
| 12 | ImportGoodsFrm | ProductDAO | `searchProductByName("Coca Cola")` | Gọi tìm hàng |
| 13 | ProductDAO | ProductDAO | `executeSQL()` | Thực thi SQL |
| 14 | ProductDAO | ImportGoodsFrm | `return List<Product>` | Trả về danh sách hàng |
| 15 | ImportGoodsFrm | ImportGoodsFrm | `displayProductTable(list)` | Hiển thị bảng hàng |
| 16 | Staff | ImportGoodsFrm | `selectProduct("Coca Cola 330ml")` | Chọn hàng |
| 17 | Staff | ImportGoodsFrm | `enterQuantity(100)` | Nhập số lượng |
| 18 | Staff | ImportGoodsFrm | `enterUnitPrice(8000)` | Nhập đơn giá |
| 19 | Staff | ImportGoodsFrm | `clickAdd()` | Nhấn Add |
| 20 | ImportGoodsFrm | ImportGoodsFrm | `addToInvoiceTable()` | Thêm vào bảng hóa đơn |
| 21 | Staff | ImportGoodsFrm | `enterProductName("Sprite")` | Nhập tên hàng tiếp |
| 22 | Staff | ImportGoodsFrm | `clickSearchProduct()` | Nhấn Search |
| 23 | ImportGoodsFrm | ProductDAO | `searchProductByName("Sprite")` | Gọi tìm hàng |
| 24 | ProductDAO | ImportGoodsFrm | `return List<Product>` | Trả về danh sách |
| 25 | Staff | ImportGoodsFrm | `selectProduct("Sprite 330ml")` | Chọn Sprite |
| 26 | Staff | ImportGoodsFrm | `enterQuantity(50)` | Nhập số lượng |
| 27 | Staff | ImportGoodsFrm | `enterUnitPrice(8000)` | Nhập đơn giá |
| 28 | Staff | ImportGoodsFrm | `clickAdd()` | Nhấn Add |
| 29 | ImportGoodsFrm | ImportGoodsFrm | `addToInvoiceTable()` | Thêm vào bảng |
| 30 | ImportGoodsFrm | ImportGoodsFrm | `calculateTotal()` | Tính tổng: 1,200,000đ |
| 31 | Staff | ImportGoodsFrm | `clickSubmit()` | Nhấn Submit |
| 32 | ImportGoodsFrm | ImportInvoiceDAO | `createImportInvoice(invoice)` | Gọi tạo hóa đơn |
| 33 | ImportInvoiceDAO | ImportInvoiceDAO | `INSERT INTO tblImportInvoice` | Lưu hóa đơn |
| 34 | ImportInvoiceDAO | ImportGoodsFrm | `return invoiceId` | Trả về ID |
| 35 | ImportGoodsFrm | ImportInvoiceDAO | `addInvoiceDetails(details)` | Gọi thêm chi tiết |
| 36 | ImportInvoiceDAO | ImportInvoiceDAO | `INSERT INTO tblImportInvoiceDetail` | Lưu 2 chi tiết |
| 37 | ImportInvoiceDAO | ImportGoodsFrm | `return true` | Thành công |
| 38 | ImportGoodsFrm | Staff | `showMessage("Nhap hang thanh cong")` | Thông báo |

---

## Câu 5: Blackbox Testcase

### TC01: Nhập hàng thành công với 2 mặt hàng

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblSupplier:**
| ID | code | name | address | email | phone | description |
|----|------|------|---------|-------|-------|-------------|
| 1 | NCC01 | Coca Cola Company | 789 Hai Ba Trung, Q3 | coca@email.com | 02838123456 | NCC nuoc giai khat |
| 2 | NCC02 | PepsiCo Vietnam | 321 Vo Van Tan, Q3 | pepsi@email.com | 02838654321 | NCC nuoc giai khat |

**tblProduct:**
| ID | code | name | price | description |
|----|------|------|-------|-------------|
| 1 | SP01 | Coca Cola 330ml | 15000 | Lon 330ml |
| 2 | SP02 | Sprite 330ml | 15000 | Lon 330ml |
| 3 | SP03 | Banh Oreo | 20000 | Hop 137g |

**tblImportInvoice:** (rỗng)
**tblImportInvoiceDetail:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Chuyển sang giao diện Home |
| 3 | Chọn **Import Goods** | Giao diện nhập hàng: ô tên NCC, nút Search |
| 4 | Nhập "Coca", nhấn Search | Bảng hiển thị: NCC01 - Coca Cola Company - 789 Hai Ba Trung, Q3 - 02838123456 |
| 5 | Nhấn vào "Coca Cola Company" | Xác nhận NCC. Hiển thị form tìm hàng: ô tên hàng, nút Search, ô SL, ô giá, nút Add |
| 6 | Nhập "Coca Cola", nhấn Search | Bảng hiển thị: SP01 - Coca Cola 330ml - 15,000đ |
| 7 | Chọn "Coca Cola 330ml", nhập SL=100, giá=8,000đ, nhấn Add | Bảng hóa đơn: Coca Cola 330ml × 100 × 8,000đ = 800,000đ. Tổng: 800,000đ |
| 8 | Nhập "Sprite", nhấn Search | Bảng hiển thị: SP02 - Sprite 330ml - 15,000đ |
| 9 | Chọn "Sprite 330ml", nhập SL=50, giá=8,000đ, nhấn Add | Bảng hóa đơn: Coca Cola 330ml (800,000đ), Sprite 330ml × 50 × 8,000đ = 400,000đ. Tổng: 1,200,000đ |
| 10 | Nhấn Submit | Thông báo "Nhap hang thanh cong". In hóa đơn nhập hàng. |

### Database sau khi test

**tblImportInvoice:** (thêm 1 dòng)
| ID | supplierID | importDate | totalAmount | userID |
|----|------------|------------|-------------|--------|
| 1 | 1 | 08/06/2026 10:30 | 1200000 | 1 |

**tblImportInvoiceDetail:** (thêm 2 dòng)
| ID | importInvoiceID | productID | quantity | unitPrice | amount |
|----|-----------------|-----------|----------|-----------|--------|
| 1 | 1 | 1 | 100 | 8000 | 800000 |
| 2 | 1 | 2 | 50 | 8000 | 400000 |

**tblUser, tblSupplier, tblProduct:** Không thay đổi.
