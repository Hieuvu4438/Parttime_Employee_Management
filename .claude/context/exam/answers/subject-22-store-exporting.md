# Subject No. 22 — Store Management — Module "Exporting"

> **Domain:** Store Management

---

## Câu 1: Scenario — Xuất hàng

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Export**. |
| 2 | Giao diện xuất hàng: ô nhập tên đại lý, nút Search. |
| 3 | Staff nhập "Dai ly A", nhấn Search. |
| 4 | Hệ thống hiển thị danh sách đại lý: mã, tên, địa chỉ, SĐT. Staff chọn "Dai ly A". |
| 5 | Giao diện tìm mặt hàng xuất: Staff nhập "Banh keo", Search. |
| 6 | Hệ thống hiển thị danh sách mặt hàng: mã, tên, mô tả. Staff chọn "Banh Oreo". |
| 7 | Staff nhập số lượng 100, đơn giá 15,000đ → mặt hàng thêm vào danh sách xuất. |
| 8 | Staff lặp cho các mặt hàng khác. Nhấn **Submit**. |
| 9 | Hệ thống kiểm tra: số lượng xuất <= tồn kho. Lưu và in phiếu xuất. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Item | id, code, name, description |
| Supplier | id, code, name, address, phone |
| SubAgent | id, code, name, address, phone |
| ImportBill | id, supplierId, importDate, totalAmount |
| ImportDetail | id, importBillId, itemId, quantity, unitPrice, amount |
| ExportBill | id, subAgentId, exportDate, totalAmount |
| ExportDetail | id, exportBillId, itemId, quantity, unitPrice, amount |
| User | id, username, password, role |

### Quan hệ

```
SubAgent 1 --- n ExportBill 1 --- n ExportDetail n --- 1 Item
Supplier 1 --- n ImportBill 1 --- n ImportDetail n --- 1 Item
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**ExportFrm:**
- `inAgentName`: ô nhập tên đại lý
- `subSearchAgent`: nút Search
- `outsubListAgent`: bảng đại lý
- `inItemName`: ô nhập tên mặt hàng
- `subSearchItem`: nút Search
- `outsubListItem`: bảng mặt hàng
- `inQuantity`, `inUnitPrice`: ô nhập SL, giá
- `outListExportDetail`: danh sách xuất
- `subSubmit`: nút Submit

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| SubAgentDAO | `searchAgent(name): List<SubAgent>` |
| ItemDAO | `searchItem(name): List<Item>` |
| ExportBillDAO | `addExportBill(bill): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → ExportFrm: search agent "Dai ly A"
2. ExportFrm → SubAgentDAO: searchAgent("Dai ly A")
3. Staff → ExportFrm: select agent
4. Staff → ExportFrm: search item "Banh keo"
5. ExportFrm → ItemDAO: searchItem("Banh keo")
6. Staff → ExportFrm: select item, enter quantity, price
7. (lặp)
8. Staff → ExportFrm: click Submit
9. ExportFrm → ExportBillDAO: addExportBill(bill)

---

## Câu 5: Blackbox Testcase

### TC01: Xuất hàng thành công

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Tìm "Dai ly A", chọn | Thông tin đại lý hiển thị |
| 2 | Tìm "Banh Oreo", SL 100, giá 15,000 | Thêm vào danh sách: 100 × 15,000 = 1,500,000đ |
| 3 | Submit | In phiếu xuất, thông báo "Xuat hang thanh cong" |

**Database sau:** Thêm tblExportBill, tblExportDetail.
