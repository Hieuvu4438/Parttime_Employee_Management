# Subject No. 62 — Costume Rental — Module "Import costume"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario (Bảng diễn biến)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn chức năng **Import costume** |
| 2 | Hệ thống hiển thị giao diện tìm nhà cung cấp với ô nhập tên nhà cung cấp và nút **Search** |
| 3 | Staff nhập "ABC Fashion" vào ô tìm kiếm, nhấn **Search**. Hệ thống hiển thị danh sách nhà cung cấp phù hợp |
| 4 | Staff click chọn nhà cung cấp **ABC Fashion** trong danh sách (nếu chưa có thì chuyển sang giao diện thêm mới nhà cung cấp, nhập thông tin và tiếp tục) |
| 5 | Hệ thống hiển thị giao diện nhập trang phục. Staff nhập "Vest nam" → nhấn **Search** → chọn **Vest nam đen**, giá 500,000đ, số lượng 10 |
| 6 | Staff nhập "Váy dạ hội" → nhấn **Search** → chọn **Váy dạ hội đỏ**, giá 800,000đ, số lượng 5 |
| 7 | Hệ thống hiển thị hóa đơn nhập hàng: Vest nam đen × 10 = 5,000,000đ, Váy dạ hội đỏ × 5 = 4,000,000đ. Tổng cộng: 9,000,000đ |
| 8 | Staff nhấn **Confirm**. Hệ thống lưu hóa đơn vào database, in hóa đơn để nhà cung cấp ký và lưu |

---

## Câu 2: Entity Class Diagram

### Bảng thuộc tính

| Entity | Thuộc tính |
|--------|-----------|
| **Provider** | providerId (PK), name, phone, address, email |
| **Costume** | costumeId (PK), name, category, unitPrice, quantityInStock, status |
| **ImportInvoice** | invoiceId (PK), invoiceDate, totalAmount, providerId (FK), staffId (FK) |
| **ImportInvoiceDetail** | detailId (PK), invoiceId (FK), costumeId (FK), quantity, unitPrice, subtotal |
| **User** | userId (PK), username, password, fullName, role |

### Mối quan hệ

| Entity 1 | Entity 2 | Mối quan hệ | Mô tả |
|-----------|----------|-------------|-------|
| Provider | ImportInvoice | 1 : N | Một nhà cung cấp có nhiều hóa đơn nhập hàng |
| ImportInvoice | ImportInvoiceDetail | 1 : N | Một hóa đơn có nhiều chi tiết sản phẩm |
| Costume | ImportInvoiceDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết hóa đơn |
| User | ImportInvoice | 1 : N | Một nhân viên tạo nhiều hóa đơn nhập hàng |

---

## Câu 3: Thiết kế tĩnh

### View Class: `ImportCostumeFrm`

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `inProviderName` | JTextField | Ô nhập tên nhà cung cấp cần tìm |
| `subSearchProvider` | JButton | Nút "Search" để tìm nhà cung cấp |
| `outProviderList` | JTable | Bảng danh sách nhà cung cấp tìm được |
| `subAddNewProvider` | JButton | Nút thêm mới nhà cung cấp (hiển thị khi không tìm thấy) |
| `inCostumeName` | JTextField | Ô nhập tên trang phục cần tìm |
| `subSearchCostume` | JButton | Nút "Search" để tìm trang phục |
| `outCostumeList` | JTable | Bảng danh sách trang phục tìm được |
| `inUnitPrice` | JTextField | Ô nhập đơn giá nhập |
| `inQuantity` | JTextField | Ô nhập số lượng nhập |
| `subAddItem` | JButton | Nút thêm trang phục vào hóa đơn |
| `outInvoiceTable` | JTable | Bảng hiển thị chi tiết hóa đơn nhập hàng |
| `outTotal` | JLabel | Hiển thị tổng tiền hóa đơn |
| `subConfirm` | JButton | Nút "Confirm" để xác nhận và lưu hóa đơn |

### DAO Classes

#### `ProviderDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Provider>` | Tìm kiếm nhà cung cấp theo tên (tìm gần đúng) |
| `addProvider(provider)` | `boolean` | Thêm mới nhà cung cấp vào database |

#### `CostumeDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `searchByName(name)` | `List<Costume>` | Tìm kiếm trang phục theo tên (tìm gần đúng) |

#### `ImportInvoiceDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `createInvoice(invoice)` | `int` | Tạo hóa đơn nhập hàng mới, trả về invoiceId |

#### `ImportInvoiceDetailDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `addDetail(detail)` | `boolean` | Thêm chi tiết hóa đơn (sản phẩm, số lượng, đơn giá) vào database |

---

## Câu 4: Sequence Diagram

### Lifelines

- **Staff** (Actor)
- **ImportCostumeFrm** (Boundary)
- **ProviderDAO** (Data Access)
- **CostumeDAO** (Data Access)
- **ImportInvoiceDAO** (Data Access)
- **ImportInvoiceDetailDAO** (Data Access)

### Message Flow

```
Staff              ImportCostumeFrm       ProviderDAO    CostumeDAO   ImportInvoiceDAO  ImportInvoiceDetailDAO
  |                      |                    |              |              |                   |
  |--- selectFunction -> |                    |              |              |                   |
  |                      |                    |              |              |                   |
  | <-- showForm ------- |                    |              |              |                   |
  |                      |                    |              |              |                   |
  |--- enterProvider --->|                    |              |              |                   |
  |--- clickSearch ----->|                    |              |              |                   |
  |                      |--- searchByName -->|              |              |                   |
  |                      |                    |              |              |                   |
  |                      | <-- List<Provider>-|              |              |                   |
  |                      |                    |              |              |                   |
  | <-- showProviderList |                    |              |              |                   |
  |                      |                    |              |              |                   |
  |--- clickProvider --->|                    |              |              |                   |
  |                      |                    |              |              |                   |
  | <-- showImportForm --|                    |              |              |                   |
  |                      |                    |              |              |                   |
  |--- enterCostume ---->|                    |              |              |                   |
  |--- clickSearch ----->|                    |              |              |                   |
  |                      |--- searchByName ---------------->|              |                   |
  |                      |                    |              |              |                   |
  |                      | <-- List<Costume> ---------------|              |                   |
  |                      |                    |              |              |                   |
  | <-- showCostumeList -|                    |              |              |                   |
  |                      |                    |              |              |                   |
  |--- selectCostume --->|                    |              |              |                   |
  |--- enterQty&Price -->|                    |              |              |                   |
  |--- clickAddItem ---->|                    |              |              |                   |
  |                      |                    |              |              |                   |
  | <-- updateInvoice ---|                    |              |              |                   |
  |                      |                    |              |              |                   |
  |   (repeat for more items)                |              |              |                   |
  |                      |                    |              |              |                   |
  |--- clickConfirm ---->|                    |              |              |                   |
  |                      |--- createInvoice ---------------------------------------------->  |
  |                      |                    |              |              |                   |
  |                      | <-- invoiceId -----------------------------------------------  |
  |                      |                    |              |              |                   |
  |                      |--- addDetail (for each item) -------------------------------->  |
  |                      |                    |              |              |                   |
  |                      | <-- true -----------------------------------------------------  |
  |                      |                    |              |              |                   |
  | <-- printInvoice ----|                    |              |              |                   |
  | <-- saveSuccess -----|                    |              |              |                   |
```

---

## Câu 5: Blackbox Testcase — TC01

### Testcase: Nhập hàng trang phục từ nhà cung cấp ABC Fashion

#### Database trước khi test

**tblProvider**

| providerId | name | phone | address |
|------------|------|-------|---------|
| P001 | ABC Fashion | 0903333333 | 123 Le Loi, HCM |

**tblCostume**

| costumeId | name | category | unitPrice | quantityInStock |
|-----------|------|----------|-----------|-----------------|
| CS001 | Vest nam đen | Vest | 500,000 | 20 |
| CS002 | Váy dạ hội đỏ | Váy | 800,000 | 15 |

**tblImportInvoice** (trống)

**tblImportInvoiceDetail** (trống)

#### Database sau khi test

**tblProvider** — không thay đổi

**tblCostume**

| costumeId | name | category | unitPrice | quantityInStock |
|-----------|------|----------|-----------|-----------------|
| CS001 | Vest nam đen | Vest | 500,000 | **30** (+10) |
| CS002 | Váy dạ hội đỏ | Váy | 800,000 | **20** (+5) |

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
| 1 | Staff đăng nhập | Username/password hợp lệ | Đăng nhập thành công |
| 2 | Staff chọn Import costume | — | Hiển thị giao diện tìm nhà cung cấp với ô nhập tên và nút Search |
| 3 | Staff nhập tên nhà cung cấp | "ABC Fashion" | Ô nhập hiển thị "ABC Fashion" |
| 4 | Staff nhấn Search | — | Hiển thị danh sách: ABC Fashion (P001, 0903333333, 123 Le Loi, HCM) |
| 5 | Staff click chọn "ABC Fashion" | — | Hiển thị giao diện nhập trang phục |
| 6 | Staff nhập tên trang phục, nhấn Search | "Vest nam" | Hiển thị danh sách: Vest nam đen |
| 7 | Staff chọn Vest nam đen, nhập giá và số lượng | Giá: 500,000đ, SL: 10 | Bảng hóa đơn hiển thị: Vest nam đen × 10 = 5,000,000đ. Tổng: 5,000,000đ |
| 8 | Staff nhập tên trang phục, nhấn Search | "Váy dạ hội" | Hiển thị danh sách: Váy dạ hội đỏ |
| 9 | Staff chọn Váy dạ hội đỏ, nhập giá và số lượng | Giá: 800,000đ, SL: 5 | Bảng hóa đơn hiển thị: Vest nam đen × 10 = 5,000,000đ, Váy dạ hội đỏ × 5 = 4,000,000đ. Tổng: 9,000,000đ |
| 10 | Staff nhấn Confirm | — | Hệ thống lưu hóa đơn vào DB, tồn kho tăng (Vest: 20→30, Váy: 15→20), in hóa đơn thành công |
