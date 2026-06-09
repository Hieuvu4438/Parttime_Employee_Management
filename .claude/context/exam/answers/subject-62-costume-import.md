# Subject No. 62 — Costume Rental — Module "Import costume"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Nhập trang phục từ nhà cung cấp

**Mô tả:** Staff chọn chức năng nhập trang phục từ nhà cung cấp, tìm nhà cung cấp theo tên, chọn nhà cung cấp đúng (hoặc thêm mới), sau đó lặp lại thao tác tìm kiếm trang phục theo tên, chọn trang phục, nhập số lượng và đơn giá cho từng mặt hàng. Khi hoàn tất, staff xác nhận hóa đơn, thanh toán cho nhà cung cấp, nhận trang phục, hệ thống lưu hóa đơn và in phiếu nhập.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống với tài khoản `staff01` / `123456` |
| 2 | Staff chọn menu **Costume** → **Import costume** |
| 3 | Hệ thống hiển thị giao diện nhập hàng: ô nhập tên nhà cung cấp (`txtProviderName`), nút **Search**, nút **Add new provider** |
| 4 | Staff nhập "ABC Fashion" vào ô tìm kiếm nhà cung cấp, nhấn **Search** |
| 5 | Hệ thống hiển thị danh sách nhà cung cấp phù hợp: ABC Fashion (0903333333, 123 Le Loi, HCM) |
| 6 | Staff click chọn nhà cung cấp **ABC Fashion**. Hệ thống hiển thị giao diện nhập trang phục với thông tin nhà cung cấp đã chọn |
| 7 | Staff nhập "Vest nam" vào ô tìm kiếm trang phục, nhấn **Search** |
| 8 | Hệ thống hiển thị danh sách trang phục phù hợp: Vest nam đen (Vest, 500,000đ), Vest nam xanh (Vest, 520,000đ) |
| 9 | Staff click chọn **Vest nam đen**, nhập số lượng `10`, đơn giá `500,000đ`, nhấn nút **Add to invoice** |
| 10 | Hệ thống thêm dòng vào bảng hóa đơn: Vest nam đen × 10 = 5,000,000đ. Hiển thị tổng cộng: 5,000,000đ |
| 11 | Staff nhập "Váy dạ hội" vào ô tìm kiếm, nhấn **Search** |
| 12 | Hệ thống hiển thị danh sách: Váy dạ hội đỏ (Váy, 800,000đ), Váy dạ hội đen (Váy, 850,000đ) |
| 13 | Staff click chọn **Váy dạ hội đỏ**, nhập số lượng `5`, đơn giá `800,000đ`, nhấn nút **Add to invoice** |
| 14 | Hệ thống thêm dòng vào bảng hóa đơn: Vest nam đen × 10 = 5,000,000đ, Váy dạ hội đỏ × 5 = 4,000,000đ. Tổng cộng: 9,000,000đ |
| 15 | Staff nhấn **Confirm** |
| 16 | Hệ thống lưu hóa đơn nhập hàng vào database, cập nhật tồn kho trang phục, in hóa đơn để nhà cung cấp ký và lưu |

### Ngoại lệ

- **EX01:** Không tìm thấy nhà cung cấp → hệ thống hiển thị danh sách trống, staff có thể nhấn **Add new provider** để thêm nhà cung cấp mới
- **EX02:** Không tìm thấy trang phục → hệ thống hiển thị danh sách trống, staff cần kiểm tra lại tên trang phục
- **EX03:** Staff nhập số lượng <= 0 hoặc đơn giá <= 0 → hệ thống thông báo "Số lượng và đơn giá phải lớn hơn 0"
- **EX04:** Staff nhấn Confirm khi chưa có mặt hàng nào trong hóa đơn → hệ thống thông báo "Vui lòng thêm ít nhất 1 mặt hàng"
- **EX05:** Staff chưa chọn nhà cung cấp mà đã tìm trang phục → hệ thống thông báo "Vui lòng chọn nhà cung cấp trước"

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê trang phục bao gồm các thực thể chính: **Provider** (nhà cung cấp), **Costume** (trang phục), **ImportInvoice** (hóa đơn nhập hàng), **ImportInvoiceDetail** (chi tiết hóa đơn nhập hàng), **Customer** (khách hàng thuê), **RentalSlip** (phiếu thuê), **RentalSlipDetail** (chi tiết phiếu thuê), **User** (nhân viên).

Tập trung vào chức năng nhập hàng:
- Một nhà cung cấp có thể cung cấp nhiều hóa đơn nhập hàng.
- Một hóa đơn nhập hàng có thể chứa nhiều chi tiết (mỗi chi tiết là 1 loại trang phục với số lượng và đơn giá).
- Một loại trang phục có thể xuất hiện trong nhiều hóa đơn nhập hàng khác nhau.
- Một nhân viên có thể tạo nhiều hóa đơn nhập hàng.

### Trích xuất danh từ → Entity

| Danh từ trong mô tả | Entity | Thuộc tính |
|----------------------|--------|------------|
| Nhà cung cấp | **Provider** | providerId (PK), name, phone, address, email |
| Trang phục, trang phục cho thuê | **Costume** | costumeId (PK), name, category, unitPrice, quantityInStock, status |
| Hóa đơn nhập hàng | **ImportInvoice** | invoiceId (PK), invoiceDate, totalAmount, providerId (FK), staffId (FK) |
| Chi tiết hóa đơn nhập hàng | **ImportInvoiceDetail** | detailId (PK), invoiceId (FK), costumeId (FK), quantity, unitPrice, subtotal |
| Khách hàng | **Customer** | customerId (PK), name, phone, address, email |
| Phiếu thuê | **RentalSlip** | rentalSlipId (PK), rentalDate, totalDeposit, status, customerId (FK), staffId (FK) |
| Chi tiết phiếu thuê | **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, unitDeposit, subtotal |
| Nhân viên / người dùng | **User** | userId (PK), username, password, fullName, role |

### Mối quan hệ

| Entity 1 | Entity 2 | Cardinality | Mô tả |
|-----------|----------|-------------|-------|
| Provider | ImportInvoice | 1 : N | Một nhà cung cấp có nhiều hóa đơn nhập hàng |
| ImportInvoice | ImportInvoiceDetail | 1 : N | Một hóa đơn nhập có nhiều chi tiết trang phục |
| Costume | ImportInvoiceDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết hóa đơn |
| User | ImportInvoice | 1 : N | Một nhân viên tạo nhiều hóa đơn nhập hàng |
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu thuê có nhiều chi tiết |
| Costume | RentalSlipDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| User | RentalSlip | 1 : N | Một nhân viên tạo nhiều phiếu thuê |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeView:
  tùy chọn nhập trang phục → subImportCostume

Chọn Import costume → hiển thị ImportCostumeFrm:
  ô nhập tên nhà cung cấp → inProviderName
  nút tìm kiếm → subSearchProvider
  nút thêm nhà cung cấp mới → subAddNewProvider

Nhập tên nhà cung cấp "ABC Fashion", nhấn Search → hiển thị danh sách:
  bảng nhà cung cấp (click chọn) → outProviderList

Click chọn nhà cung cấp "ABC Fashion" → hiển thị giao diện nhập trang phục:
  nhãn nhà cung cấp đã chọn → outSelectedProvider
  ô nhập tên trang phục → inCostumeName
  nút tìm kiếm trang phục → subSearchCostume

Nhập "Vest nam", nhấn Search → hiển thị danh sách trang phục:
  bảng trang phục (click chọn) → outCostumeList

Chọn trang phục, nhập số lượng và đơn giá:
  ô nhập số lượng → inQuantity
  ô nhập đơn giá → inUnitPrice
  nút thêm vào hóa đơn → subAddItem

Nhấn Add to invoice → cập nhật hóa đơn:
  bảng chi tiết hóa đơn → outInvoiceTable
  tổng tiền hóa đơn → outTotal

(Lặp lại cho trang phục khác: "Váy dạ hội đỏ" × 5)

Nhấn Confirm → subConfirm
  hệ thống lưu hóa đơn, cập nhật tồn kho, in hóa đơn

### Tóm tắt
View classes: HomeView, ImportCostumeFrm
Methods: searchProvider(), addProvider(), searchCostume(), createInvoice(), addDetail(), updateStock()

### ASCII Class Diagram

```
+---------------------+         +---------------------+
|      Provider       |         |       Costume       |
+---------------------+         +---------------------+
| - providerId (PK)   |         | - costumeId (PK)    |
| - name              |         | - name              |
| - phone             |         | - category          |
| - address           |         | - unitPrice         |
| - email             |         | - quantityInStock   |
+---------------------+         | - status            |
           | 1                  +---------------------+
           |                           | 1        | 1
           | N                         | N        | N
+---------------------+    +-------------------+ +--------------------+
|   ImportInvoice     |    |ImportInvoiceDetail| | RentalSlipDetail   |
+---------------------+    +-------------------+ +--------------------+
| - invoiceId (PK)    |    | - detailId (PK)   | | - detailId (PK)    |
| - invoiceDate       |    | - invoiceId (FK)  | | - rentalSlipId(FK) |
| - totalAmount       |    | - costumeId (FK)  | | - costumeId (FK)   |
| - providerId (FK)   |    | - quantity        | | - quantity         |
| - staffId (FK)      |    | - unitPrice       | | - unitDeposit      |
+---------------------+    | - subtotal        | | - subtotal         |
           | 1             +-------------------+ +--------------------+
           |                        ^                     ^
           | N                      | 1:N                 | 1:N
+---------------------+             |                     |
|       User          |    +---------------------+
+---------------------+    |    RentalSlip       |
| - userId (PK)       |    +---------------------+
| - username          |    | - rentalSlipId (PK) |
| - password          |    | - rentalDate        |
| - fullName          |    | - totalDeposit      |
| - role              |    | - status            |
+---------------------+    | - customerId (FK)   |
           | 1             | - staffId (FK)      |
           |               +---------------------+
           | N                     | 1
+---------------------+            |
|   ImportInvoice /   |            | N
|   RentalSlip        |   +---------------------+
+---------------------+   |      Customer       |
                          +---------------------+
                          | - customerId (PK)   |
                          | - name              |
                          | - phone             |
                          | - address           |
                          | - email             |
                          +---------------------+
```

### Bảng quan hệ (Relation Schema)

| Bảng | Khóa chính | Khóa ngoại | Tham chiếu đến |
|------|-----------|------------|----------------|
| tblProvider | providerId | — | — |
| tblCostume | costumeId | — | — |
| tblCustomer | customerId | — | — |
| tblUser | userId | — | — |
| tblImportInvoice | invoiceId | providerId, staffId | tblProvider(providerId), tblUser(userId) |
| tblImportInvoiceDetail | detailId | invoiceId, costumeId | tblImportInvoice(invoiceId), tblCostume(costumeId) |
| tblRentalSlip | rentalSlipId | customerId, staffId | tblCustomer(customerId), tblUser(userId) |
| tblRentalSlipDetail | detailId | rentalSlipId, costumeId | tblRentalSlip(rentalSlipId), tblCostume(costumeId) |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Provider, Costume, ImportInvoice, ImportInvoiceDetail, Customer, RentalSlip, RentalSlipDetail, User
- Bước 3: Tạo các view class box từ giao diện: HomeView, ImportCostumeFrm
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> ImportInvoice`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-invoiceId: int`, `-totalAmount: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+createInvoice(invoice: ImportInvoice): int`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Provider | <<entity>> | -providerId: int, -name: String, -phone: String, -address: String, -email: String | +searchByName(keyword: String): List<Provider> |
| Costume | <<entity>> | -costumeId: int, -name: String, -category: String, -unitPrice: double, -quantityInStock: int, -status: String | +searchByName(keyword: String): List<Costume>, +updateStock(costumeId: int, qty: int): boolean |
| ImportInvoice | <<entity>> | -invoiceId: int, -invoiceDate: Date, -totalAmount: double, -provider: Provider, -staff: User | +createInvoice(invoice: ImportInvoice): int |
| ImportInvoiceDetail | <<entity>> | -detailId: int, -invoice: ImportInvoice, -costume: Costume, -quantity: int, -unitPrice: double, -subtotal: double | +addDetail(detail: ImportInvoiceDetail): boolean |
| Customer | <<entity>> | -customerId: int, -name: String, -phone: String, -address: String, -email: String | |
| RentalSlip | <<entity>> | -rentalSlipId: int, -rentalDate: Date, -totalDeposit: double, -status: String, -customer: Customer, -staff: User | |
| RentalSlipDetail | <<entity>> | -detailId: int, -rentalSlip: RentalSlip, -costume: Costume, -quantity: int, -unitDeposit: double, -subtotal: double | |
| User | <<entity>> | -userId: int, -username: String, -password: String, -fullName: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeView | subImportCostume: JButton (nút chọn Import costume) |
| ImportCostumeFrm | inProviderName: JTextField (ô nhập tên NCC), subSearchProvider: JButton (nút Search NCC), subAddNewProvider: JButton (nút thêm NCC mới), outProviderList: JTable (danh sách NCC click được), outSelectedProvider: JLabel (NCC đã chọn), inCostumeName: JTextField (ô nhập tên trang phục), subSearchCostume: JButton (nút Search trang phục), outCostumeList: JTable (danh sách trang phục click được), inQuantity: JTextField (ô nhập số lượng), inUnitPrice: JTextField (ô nhập đơn giá), subAddItem: JButton (nút Add to invoice), outInvoiceTable: JTable (bảng chi tiết hóa đơn), outTotal: JLabel (tổng tiền), subConfirm: JButton (nút Confirm) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Provider → ImportInvoice
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: ImportInvoice ◆→ ImportInvoiceDetail
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: ImportCostumeFrm ---> CostumeDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Provider (1) --- (n) ImportInvoice

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Provider | ImportInvoice | Association | 1 : n | Một nhà cung cấp có nhiều hóa đơn nhập hàng |
| ImportInvoice | ImportInvoiceDetail | Composition | 1 : n | Hóa đơn nhập chứa nhiều chi tiết trang phục |
| Costume | ImportInvoiceDetail | Association | 1 : n | Một trang phục xuất hiện trong nhiều chi tiết hóa đơn |
| User | ImportInvoice | Association | 1 : n | Một nhân viên tạo nhiều hóa đơn nhập hàng |
| Customer | RentalSlip | Association | 1 : n | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | Composition | 1 : n | Phiếu thuê chứa nhiều chi tiết trang phục |
| Costume | RentalSlipDetail | Association | 1 : n | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| User | RentalSlip | Association | 1 : n | Một nhân viên tạo nhiều phiếu thuê |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition ImportInvoice ◆→ ImportInvoiceDetail:
- Kéo class ImportInvoice vào canvas, kéo class ImportInvoiceDetail vào bên phải
- Chọn công cụ **Composition**, click vào ImportInvoice rồi kéo sang ImportInvoiceDetail
- Kim cương filled nằm ở phía ImportInvoice (parent)
- Đặt Multiplicity: ImportInvoice "1", ImportInvoiceDetail "n"

Ví dụ 2 — Vẽ quan hệ Association Provider → ImportInvoice (1:n):
- Kéo class Provider và ImportInvoice vào canvas
- Chọn công cụ **Association**, click vào Provider kéo sang ImportInvoice
- Đặt Multiplicity: Provider "1", ImportInvoice "n"

---

## Câu 3: Thiết kế tĩnh

### View Class: `ImportCostumeFrm`

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `txtProviderName` | JTextField | Ô nhập tên nhà cung cấp cần tìm |
| `btnSearchProvider` | JButton | Nút "Search" để tìm nhà cung cấp theo tên |
| `tblProviderList` | JTable | Bảng danh sách nhà cung cấp tìm được (tên, điện thoại, địa chỉ, email) |
| `btnAddNewProvider` | JButton | Nút "Add new provider" để thêm nhà cung cấp mới khi không tìm thấy |
| `lblSelectedProvider` | JLabel | Hiển thị tên nhà cung cấp đã chọn |
| `txtCostumeName` | JTextField | Ô nhập tên trang phục cần tìm |
| `btnSearchCostume` | JButton | Nút "Search" để tìm trang phục theo tên |
| `tblCostumeList` | JTable | Bảng danh sách trang phục tìm được (tên, danh mục, giá niêm yết) |
| `txtUnitPrice` | JTextField | Ô nhập đơn giá nhập (có thể khác giá niêm yết) |
| `txtQuantity` | JTextField | Ô nhập số lượng nhập |
| `btnAddItem` | JButton | Nút "Add to invoice" để thêm trang phục vào hóa đơn |
| `tblInvoiceDetail` | JTable | Bảng hiển thị chi tiết hóa đơn nhập hàng (tên trang phục, số lượng, đơn giá, thành tiền) |
| `lblTotal` | JLabel | Hiển thị tổng tiền hóa đơn (tự động tính) |
| `btnConfirm` | JButton | Nút "Confirm" để xác nhận và lưu hóa đơn |
| `btnRemoveItem` | JButton | Nút xóa 1 dòng khỏi hóa đơn (khi chọn sai) |
| `lblStatus` | JLabel | Hiển thị thông báo trạng thái (thành công, lỗi, v.v.) |

### DAO Classes

#### `ProviderDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Provider>` | Tìm kiếm nhà cung cấp theo tên (tìm gần đúng, LIKE '%name%') |
| `addProvider(provider)` | `boolean` | Thêm mới nhà cung cấp vào tblProvider |
| `getProviderById(providerId)` | `Provider` | Lấy thông tin nhà cung cấp theo mã |

#### `CostumeDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Costume>` | Tìm kiếm trang phục theo tên (tìm gần đúng, LIKE '%name%') |
| `updateStock(costumeId, additionalQty)` | `boolean` | Cập nhật tồn kho: quantityInStock = quantityInStock + additionalQty |
| `getCostumeById(costumeId)` | `Costume` | Lấy thông tin trang phục theo mã |

#### `ImportInvoiceDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `createInvoice(invoice)` | `int` | Tạo hóa đơn nhập hàng mới vào tblImportInvoice, trả về invoiceId vừa tạo |

#### `ImportInvoiceDetailDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `addDetail(detail)` | `boolean` | Thêm 1 chi tiết hóa đơn vào tblImportInvoiceDetail |
| `getDetailsByInvoiceId(invoiceId)` | `List<ImportInvoiceDetail>` | Lấy danh sách chi tiết theo mã hóa đơn |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Provider | Model | id: int, name: String, phone: String, address: String, email: String |
| Costume | Model | id: int, name: String, category: String, unitPrice: double, quantityInStock: int, status: String |
| ImportInvoice | Model | id: int, invoiceDate: Date, totalAmount: double, provider: Provider, staff: User |
| ImportInvoiceDetail | Model | id: int, invoice: ImportInvoice, costume: Costume, quantity: int, unitPrice: double, subtotal: double |
| User | Model | id: int, username: String, password: String, fullName: String, role: String |

### Database Design

**tblProvider:**

| Column | Type | Constraint |
|--------|------|------------|
| providerId | VARCHAR(10) | PRIMARY KEY |
| name | NVARCHAR(100) | NOT NULL |
| phone | VARCHAR(15) | |
| address | NVARCHAR(200) | |
| email | VARCHAR(100) | |

**tblCostume:**

| Column | Type | Constraint |
|--------|------|------------|
| costumeId | VARCHAR(10) | PRIMARY KEY |
| name | NVARCHAR(100) | NOT NULL |
| category | NVARCHAR(50) | |
| unitPrice | DECIMAL(15,2) | NOT NULL |
| quantityInStock | INT | DEFAULT 0 |
| status | VARCHAR(20) | |

**tblUser:**

| Column | Type | Constraint |
|--------|------|------------|
| userId | VARCHAR(10) | PRIMARY KEY |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| fullName | NVARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

**tblImportInvoice:**

| Column | Type | Constraint |
|--------|------|------------|
| invoiceId | VARCHAR(10) | PRIMARY KEY |
| invoiceDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |
| providerId | VARCHAR(10) | FOREIGN KEY → tblProvider(providerId) |
| staffId | VARCHAR(10) | FOREIGN KEY → tblUser(userId) |

**tblImportInvoiceDetail:**

| Column | Type | Constraint |
|--------|------|------------|
| detailId | VARCHAR(10) | PRIMARY KEY |
| invoiceId | VARCHAR(10) | FOREIGN KEY → tblImportInvoice(invoiceId) |
| costumeId | VARCHAR(10) | FOREIGN KEY → tblCostume(costumeId) |
| quantity | INT | NOT NULL |
| unitPrice | DECIMAL(15,2) | NOT NULL |
| subtotal | DECIMAL(15,2) | NOT NULL |

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Class Diagram**
2. Tạo 5 class: Provider, Costume, ImportInvoice, ImportInvoiceDetail, User
3. Thêm attributes cho từng class (xem bảng Entity Types)
4. Vẽ mối quan hệ:
   - Provider → ImportInvoice (1:N) với navigable arrow từ ImportInvoice sang Provider
   - ImportInvoice → ImportInvoiceDetail (1:N) với composition (filled diamond)
   - Costume → ImportInvoiceDetail (1:N) với navigable arrow từ ImportInvoiceDetail sang Costume
   - User → ImportInvoice (1:N) với navigable arrow từ ImportInvoice sang User
5. Ghi chú cardinality ở mỗi đầu mối quan hệ

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Sequence Diagram**
2. Tạo 5 lifelines theo thứ tự từ trái sang phải:
   - `:Staff` (actor stereotype)
   - `:ImportCostumeFrm` (boundary stereotype)
   - `:ProviderDAO` (control stereotype)
   - `:CostumeDAO` (control stereotype)
   - `:ImportInvoiceDAO` (control stereotype)
   - `:ImportInvoiceDetailDAO` (control stereotype)
3. Vẽ message flow theo bảng bên dưới
4. Sử dụng `loop` fragment cho phần lặp thêm trang phục vào hóa đơn
5. Sử dụng `alt` fragment cho trường hợp nhà cung cấp mới

### Lifelines

- **:Staff** — actor, người thực hiện thao tác
- **:ImportCostumeFrm** — boundary, giao diện nhập trang phục
- **:ProviderDAO** — control, truy vấn nhà cung cấp
- **:CostumeDAO** — control, truy vấn trang phục và cập nhật tồn kho
- **:ImportInvoiceDAO** — control, tạo hóa đơn nhập
- **:ImportInvoiceDetailDAO** — control, thêm chi tiết hóa đơn

### ASCII Sequence Diagram

```
Staff            ImportCostumeFrm     ProviderDAO   CostumeDAO  ImportInvoiceDAO  ImportInvoiceDetailDAO
  |                     |                  |             |              |                   |
  |--- selectFunction-->|                  |             |              |                   |
  |                     |                  |             |              |                   |
  | <-- showForm -------|                  |             |              |                   |
  |                     |                  |             |              |                   |
  |--- enterProviderName->                 |             |              |                   |
  |    ("ABC Fashion")  |                  |             |              |                   |
  |--- clickSearch ---->|                  |             |              |                   |
  |                     |---searchByName-->|             |              |                   |
  |                     |   ("ABC Fashion")|             |              |                   |
  |                     |                  |             |              |                   |
  |                     |<--List<Provider>-|             |              |                   |
  |                     |                  |             |              |                   |
  | <-- showProviderList|                  |             |              |                   |
  |                     |                  |             |              |                   |
  |---clickProvider ---->|                 |             |              |                   |
  |    (ABC Fashion)    |                  |             |              |                   |
  |                     |                  |             |              |                   |
  | <-- showImportForm -|                  |             |              |                   |
  |                     |                  |             |              |                   |
  |                     |                  |             |              |                   |
  |   <<loop: repeat for each costume>>   |             |              |                   |
  |                     |                  |             |              |                   |
  |---enterCostumeName->|                  |             |              |                   |
  |    ("Vest nam")     |                  |             |              |                   |
  |---clickSearch ---->|                  |             |              |                   |
  |                     |---searchByName------------>|              |                   |
  |                     |   ("Vest nam")  |             |              |                   |
  |                     |                  |             |              |                   |
  |                     |<--List<Costume>--------------|              |                   |
  |                     |                  |             |              |                   |
  | <-- showCostumeList |                  |             |              |                   |
  |                     |                  |             |              |                   |
  |---selectCostume --->|                  |             |              |                   |
  |    (Vest nam den)   |                  |             |              |                   |
  |---enterQty&Price -->|                  |             |              |                   |
  |    (10, 500000)     |                  |             |              |                   |
  |---clickAddItem ---->|                  |             |              |                   |
  |                     |                  |             |              |                   |
  | <-- updateInvoice --|                  |             |              |                   |
  |                     |                  |             |              |                   |
  |   <<end loop>>      |                  |             |              |                   |
  |                     |                  |             |              |                   |
  |---clickConfirm ---->|                  |             |              |                   |
  |                     |                  |             |              |                   |
  |                     |---createInvoice ------------------------------------------->     |
  |                     |                  |             |              |                   |
  |                     |<--invoiceId ------------------------------------------------      |
  |                     |                  |             |              |                   |
  |                     |---addDetail (Vest nam den x10) -------------------------------->  |
  |                     |<--true -----------------------------------------------------       |
  |                     |                  |             |              |                   |
  |                     |---addDetail (Vay da hoi do x5) ----------------------------------> |
  |                     |<--true -----------------------------------------------------       |
  |                     |                  |             |              |                   |
  |                     |---updateStock(CS001, +10) -------->|              |                   |
  |                     |<--true ---------------------------|              |                   |
  |                     |                  |             |              |                   |
  |                     |---updateStock(CS002, +5) --------->|              |                   |
  |                     |<--true ---------------------------|              |                   |
  |                     |                  |             |              |                   |
  | <-- printInvoice ---|                  |             |              |                   |
  | <-- saveSuccess ----|                  |             |              |                   |
```

### Bảng message flow chi tiết

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|-----|---------|---------|
| 1 | Staff | ImportCostumeFrm | `selectFunction()` | Staff chọn menu Costume → Import costume |
| 2 | ImportCostumeFrm | Staff | `showForm()` | Hiển thị giao diện tìm nhà cung cấp |
| 3 | Staff | ImportCostumeFrm | `enterProviderName("ABC Fashion")` | Nhập tên nhà cung cấp |
| 4 | Staff | ImportCostumeFrm | `clickSearch()` | Nhấn nút Search |
| 5 | ImportCostumeFrm | ProviderDAO | `searchByName("ABC Fashion")` | Truy vấn nhà cung cấp |
| 6 | ProviderDAO | ImportCostumeFrm | `return List<Provider>` | Trả về danh sách nhà cung cấp |
| 7 | ImportCostumeFrm | Staff | `showProviderList(list)` | Hiển thị danh sách nhà cung cấp |
| 8 | Staff | ImportCostumeFrm | `clickProvider("ABC Fashion")` | Chọn nhà cung cấp |
| 9 | ImportCostumeFrm | Staff | `showImportForm()` | Hiển thị giao diện nhập trang phục |
| 10 | Staff | ImportCostumeFrm | `enterCostumeName("Vest nam")` | Nhập tên trang phục |
| 11 | Staff | ImportCostumeFrm | `clickSearch()` | Nhấn nút Search |
| 12 | ImportCostumeFrm | CostumeDAO | `searchByName("Vest nam")` | Truy vấn trang phục |
| 13 | CostumeDAO | ImportCostumeFrm | `return List<Costume>` | Trả về danh sách trang phục |
| 14 | ImportCostumeFrm | Staff | `showCostumeList(list)` | Hiển thị danh sách trang phục |
| 15 | Staff | ImportCostumeFrm | `selectCostume("Vest nam den")` | Chọn trang phục |
| 16 | Staff | ImportCostumeFrm | `enterQtyAndPrice(10, 500000)` | Nhập số lượng và đơn giá |
| 17 | Staff | ImportCostumeFrm | `clickAddItem()` | Thêm vào hóa đơn |
| 18 | ImportCostumeFrm | ImportCostumeFrm | `addItemToTable(costume, qty, price)` | Cập nhật bảng hóa đơn |
| 19 | ImportCostumeFrm | Staff | `showUpdatedInvoice()` | Hiển thị hóa đơn cập nhật |
| 20 | (Lặp bước 10-19 cho Váy dạ hội đỏ × 5) | | | |
| 21 | Staff | ImportCostumeFrm | `clickConfirm()` | Xác nhận hóa đơn |
| 22 | ImportCostumeFrm | ImportInvoiceDAO | `createInvoice(invoice)` | Tạo hóa đơn nhập hàng |
| 23 | ImportInvoiceDAO | ImportCostumeFrm | `return invoiceId` | Trả về invoiceId |
| 24 | ImportCostumeFrm | ImportInvoiceDetailDAO | `addDetail(detail1)` | Thêm chi tiết: Vest nam den ×10 |
| 25 | ImportInvoiceDetailDAO | ImportCostumeFrm | `return true` | Thành công |
| 26 | ImportCostumeFrm | ImportInvoiceDetailDAO | `addDetail(detail2)` | Thêm chi tiết: Vay da hoi do ×5 |
| 27 | ImportInvoiceDetailDAO | ImportCostumeFrm | `return true` | Thành công |
| 28 | ImportCostumeFrm | CostumeDAO | `updateStock("CS001", 10)` | Cập nhật tồn kho Vest nam den |
| 29 | CostumeDAO | ImportCostumeFrm | `return true` | Thành công |
| 30 | ImportCostumeFrm | CostumeDAO | `updateStock("CS002", 5)` | Cập nhật tồn kho Vay da hoi do |
| 31 | CostumeDAO | ImportCostumeFrm | `return true` | Thành công |
| 32 | ImportCostumeFrm | Staff | `printInvoice()` | In hóa đơn |
| 33 | ImportCostumeFrm | Staff | `showSuccessMessage("Luu hoa don thanh cong")` | Thông báo thành công |

---

## Câu 5: Blackbox Testcase — TC01

### Testcase: Nhập hàng trang phục từ nhà cung cấp ABC Fashion

#### Database trước khi test

**tblUser**

| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | Staff |

**tblProvider**

| providerId | name | phone | address | email |
|------------|------|-------|---------|-------|
| P001 | ABC Fashion | 0903333333 | 123 Le Loi, HCM | abc@fashion.com |

**tblCostume**

| costumeId | name | category | unitPrice | quantityInStock | status |
|-----------|------|----------|-----------|-----------------|--------|
| CS001 | Vest nam den | Vest | 500,000 | 20 | Active |
| CS002 | Vay da hoi do | Vay | 800,000 | 15 | Active |

**tblImportInvoice** — rỗng

**tblImportInvoiceDetail** — rỗng

#### Database sau khi test

**tblUser** — không thay đổi

**tblProvider** — không thay đổi

**tblCostume**

| costumeId | name | category | unitPrice | quantityInStock | status |
|-----------|------|----------|-----------|-----------------|--------|
| CS001 | Vest nam den | Vest | 500,000 | **30** (+10) | Active |
| CS002 | Vay da hoi do | Vay | 800,000 | **20** (+5) | Active |

**tblImportInvoice**

| invoiceId | invoiceDate | totalAmount | providerId | staffId |
|-----------|-------------|-------------|------------|---------|
| INV001 | 2026-06-08 | 9,000,000 | P001 | U001 |

**tblImportInvoiceDetail**

| detailId | invoiceId | costumeId | quantity | unitPrice | subtotal |
|----------|-----------|-----------|----------|-----------|----------|
| D001 | INV001 | CS001 | 10 | 500,000 | 5,000,000 |
| D002 | INV001 | CS002 | 5 | 800,000 | 4,000,000 |

#### Bảng các bước test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập hệ thống | Username: `staff01`, Password: `123456` | Đăng nhập thành công, hiển thị giao diện chính (HomeFrm) |
| 2 | Staff chọn menu Costume → Import costume | — | Hiển thị giao diện ImportCostumeFrm: ô nhập tên nhà cung cấp, nút Search, nút Add new provider |
| 3 | Staff nhập tên nhà cung cấp | `ABC Fashion` | Ô txtProviderName hiển thị "ABC Fashion" |
| 4 | Staff nhấn nút Search | — | Bảng tblProviderList hiển thị 1 dòng: ABC Fashion (0903333333, 123 Le Loi, HCM, abc@fashion.com) |
| 5 | Staff click chọn nhà cung cấp ABC Fashion | — | Giao diện chuyển sang phần nhập trang phục. lblSelectedProvider hiển thị "ABC Fashion" |
| 6 | Staff nhập tên trang phục, nhấn Search | `Vest nam` | Bảng tblCostumeList hiển thị: Vest nam den (Vest, 500,000đ) |
| 7 | Staff chọn Vest nam den, nhập số lượng và đơn giá | Số lượng: `10`, Đơn giá: `500,000` | Nhấn "Add to invoice" → Bảng tblInvoiceDetail hiển thị 1 dòng: Vest nam den × 10 = 5,000,000đ. lblTotal = "5,000,000đ" |
| 8 | Staff nhập tên trang phục, nhấn Search | `Vay da hoi` | Bảng tblCostumeList hiển thị: Vay da hoi do (Vay, 800,000đ) |
| 9 | Staff chọn Vay da hoi do, nhập số lượng và đơn giá | Số lượng: `5`, Đơn giá: `800,000` | Nhấn "Add to invoice" → Bảng tblInvoiceDetail hiển thị 2 dòng: Vest nam den × 10 = 5,000,000đ, Vay da hoi do × 5 = 4,000,000đ. lblTotal = "9,000,000đ" |
| 10 | Staff nhấn nút Confirm | — | Hệ thống lưu hóa đơn vào tblImportInvoice (INV001, 2026-06-08, 9,000,000, P001, U001), lưu chi tiết vào tblImportInvoiceDetail (2 dòng), cập nhật tồn kho tblCostume (CS001: 20→30, CS002: 15→20), in hóa đơn thành công |
