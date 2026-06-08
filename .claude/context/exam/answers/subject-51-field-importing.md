# Subject No. 51 — Mini Football Field — Module "Goods importing"

> **Domain:** Mini Football Field Rental Management

---

## Câu 1: Scenario — Nhập hàng

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Import goods**. |
| 2 | Giao diện: ô nhập tên nhà cung cấp, nút Search. |
| 3 | Staff nhập "Coca", Search. Hệ thống hiển thị danh sách NCC chứa "Coca". |
| 4 | Staff click "Coca Cola Company". |
| 5 | Staff nhập "Coca Cola" → Search → chọn "Coca Cola 330ml", giá 10,000đ, số lượng 100. Thêm vào hóa đơn. |
| 6 | Staff nhập "Sprite" → Search → chọn "Sprite 330ml", giá 10,000đ, số lượng 50. Thêm vào hóa đơn. |
| 7 | Hệ thống hiển thị hóa đơn: Coca Cola 330ml × 100 = 1,000,000đ, Sprite 330ml × 50 = 500,000đ. Tổng: 1,500,000đ. |
| 8 | Staff nhấn **Submit**. Hệ thống in hóa đơn nhập hàng, lưu database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Supplier | id, code, name, address, email, phone, description |
| Product | id, code, name, price |
| ImportInvoice | id, supplierId, importDate, totalAmount |
| ImportInvoiceDetail | id, importInvoiceId, productId, quantity, unitPrice, amount |
| User | id, username, password, role |

### Quan hệ

```
Supplier 1 --- n ImportInvoice
ImportInvoice 1 --- n ImportInvoiceDetail n --- 1 Product
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**ImportGoodsFrm:**
- `inSupplierName`: ô nhập tên NCC
- `subSearchSupplier`: nút Search NCC
- `outSupplierList`: danh sách NCC (click được)
- `inProductName`: ô nhập tên hàng
- `subSearchProduct`: nút Search hàng
- `outProductList`: danh sách hàng (click được)
- `inUnitPrice`: ô nhập giá
- `inQuantity`: ô nhập số lượng
- `subAdd`: nút Add
- `outInvoiceTable`: bảng hóa đơn nhập
- `outTotal`: tổng tiền
- `subSubmit`: nút Submit

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| SupplierDAO | `searchSupplierByName(keyword): List<Supplier>` |
| ProductDAO | `searchProductByName(keyword): List<Product>` |
| ImportInvoiceDAO | `createImportInvoice(invoice): boolean` |
| ImportInvoiceDetailDAO | `addInvoiceDetail(detail): boolean` |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:ImportGoodsFrm` — boundary
3. `:SupplierDAO` — control
4. `:ProductDAO` — control
5. `:ImportInvoiceDAO` — control

**Message flow:**

1. Staff → ImportGoodsFrm: `enterSupplierName("Coca")` + `clickSearchSupplier()`
2. ImportGoodsFrm → SupplierDAO: `searchSupplierByName("Coca")`
3. SupplierDAO → ImportGoodsFrm: return `List<Supplier>`
4. Staff → ImportGoodsFrm: `clickSupplier(Coca Cola Company)`
5. Staff → ImportGoodsFrm: `enterProductName("Coca Cola")` + `clickSearchProduct()`
6. ImportGoodsFrm → ProductDAO: `searchProductByName("Coca Cola")`
7. ProductDAO → ImportGoodsFrm: return `List<Product>`
8. Staff → ImportGoodsFrm: `selectProduct(Coca Cola 330ml)` + `enterPrice(10000)` + `enterQty(100)` + `clickAdd()`
9. Staff → ImportGoodsFrm: `clickSubmit()`
10. ImportGoodsFrm → ImportInvoiceDAO: `createImportInvoice(invoice)`
11. ImportInvoiceDAO: INSERT INTO tblImportInvoice + tblImportInvoiceDetail

---

## Câu 5: Blackbox Testcase

### TC01: Nhập hàng thành công

**Database trước:**
- tblImportInvoice: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "Coca" → Search, click Coca Cola Company | Chọn NCC |
| 2 | Nhập "Coca Cola" → Search, chọn Coca Cola 330ml, 10,000đ, 100 → Add | Hóa đơn: 1,000,000đ |
| 3 | Nhập "Sprite" → Search, chọn Sprite 330ml, 10,000đ, 50 → Add | Tổng: 1,500,000đ |
| 4 | Submit | "Nhap hang thanh cong" |

**Database sau:**
- tblImportInvoice: thêm 1 dòng (supplierId=Coca Cola Company, totalAmount=1500000)
- tblImportInvoiceDetail: thêm 2 dòng
