# Subject No. 23 — Store Management — Module "Importing"

> **Domain:** Store Management

---

## Câu 1: Scenario — Nhập hàng

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Import**. |
| 2 | Giao diện nhập hàng: ô nhập tên nhà cung cấp, nút Search. |
| 3 | Staff nhập "NCC B", nhấn Search → danh sách NCC. Staff chọn "NCC B". |
| 4 | Giao diện tìm mặt hàng nhập: Staff nhập "Nuoc ngot", Search. |
| 5 | Hệ thống hiển thị danh sách mặt hàng. Staff chọn "Coca Cola". |
| 6 | Staff nhập số lượng 200, đơn giá 8,000đ → mặt hàng thêm vào danh sách nhập. |
| 7 | Staff lặp. Nhấn **Submit**. |
| 8 | Hệ thống lưu và in phiếu nhập. |

---

## Câu 2-3: Entity + Design

Cùng entity classes với Subject 22.

**ImportFrm:**
- `inSupplierName`, `subSearchSupplier`, `outsubListSupplier`
- `inItemName`, `subSearchItem`, `outsubListItem`
- `inQuantity`, `inUnitPrice`
- `outListImportDetail`
- `subSubmit`

DAO: `ImportBillDAO.addImportBill(bill): boolean`

---

## Câu 4: Sequence Diagram

1. Staff → ImportFrm: search supplier "NCC B"
2. ImportFrm → SupplierDAO: searchSupplier("NCC B")
3. Staff → ImportFrm: search item "Nuoc ngot"
4. ImportFrm → ItemDAO: searchItem("Nuoc ngot")
5. Staff → ImportFrm: select item, enter quantity, price
6. Staff → ImportFrm: Submit
7. ImportFrm → ImportBillDAO: addImportBill(bill)

---

## Câu 5: Blackbox Testcase

### TC01: Nhập hàng thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Tìm "NCC B", chọn |
| 2 | Tìm "Coca Cola", SL 200, giá 8,000 → 1,600,000đ |
| 3 | Submit | In phiếu nhập, "Nhap hang thanh cong" |

**Database sau:** Thêm tblImportBill, tblImportDetail.
