# Subject No. 49 — Cinema Chain — Module "Statistics of services"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Thống kê dịch vụ

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Service statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách món: mã, tên, tổng số lượng bán, tổng doanh thu. Sắp xếp: tổng doanh thu giảm dần. |
| 5 | Staff nhấn vào "Popcorn Caramel". |
| 6 | Hệ thống hiển thị chi tiết: ngày bán, đơn giá, số lượng, tổng tiền. Sắp xếp: ngày bán cũ → mới. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| FoodItem | id, code, name, price, size |
| FoodInvoice | id, invoiceDate, totalAmount |
| FoodInvoiceDetail | id, foodInvoiceId, foodItemId, quantity, amount |
| User | id, username, password, role |

### Quan hệ

```
FoodInvoice 1 --- n FoodInvoiceDetail n --- 1 FoodItem
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**ServiceStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outServiceTable`: bảng dịch vụ (click được)
- `outDetailTable`: bảng chi tiết

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| FoodItemDAO | `getServiceStat(startDate, endDate): List<ServiceStat>` |
| FoodInvoiceDetailDAO | `getServiceDetail(foodItemId, startDate, endDate): List<ServiceDetail>` |

**ServiceStat:** foodItem, totalQuantity, totalRevenue

**ServiceDetail:** invoiceDate, unitPrice, quantity, totalAmount

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:ServiceStatFrm` — boundary
3. `:FoodItemDAO` — control
4. `:FoodInvoiceDetailDAO` — control

**Message flow:**

1. Staff → ServiceStatFrm: `enterDates(01/01/2026, 31/12/2026)` + `clickView()`
2. ServiceStatFrm → FoodItemDAO: `getServiceStat(dates)`
3. FoodItemDAO → ServiceStatFrm: return `List<ServiceStat>`
4. Staff → ServiceStatFrm: `clickItem(Popcorn Caramel)`
5. ServiceStatFrm → FoodInvoiceDetailDAO: `getServiceDetail(foodItemId, dates)`
6. FoodInvoiceDetailDAO → ServiceStatFrm: return `List<ServiceDetail>`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê dịch vụ có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: Popcorn Caramel (500 phần, 32,500,000đ), Coca Cola (800 ly, 20,000,000đ) |
| 2 | Nhấn Popcorn Caramel | Chi tiết: 15/07, 65,000đ, 100 phần, 6,500,000đ; 16/07, 65,000đ, 80 phần, 5,200,000đ |

**Database sau:** Không thay đổi (chỉ đọc).
