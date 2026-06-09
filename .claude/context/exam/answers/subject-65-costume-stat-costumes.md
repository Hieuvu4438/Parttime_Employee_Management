# Subject No. 65 — Costume Rental — Module "Statistics of costumes"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Thống kê trang phục

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Costume statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách trang phục: mã, tên, mẫu, thể loại, tổng số lần thuê, tổng tiền thu. Sắp xếp: số lần thuê giảm dần, sau đó tổng tiền giảm dần. |
| 5 | Staff nhấn vào "Váy công chúa". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: mã HĐ, tên người thuê, ngày thuê, ngày trả, tổng tiền. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Costume | id, code, name, category, model, quantity, price |
| Customer | id, code, name, phone |
| Rental | id, customerId, rentalDate, deposit, status |
| RentalDetail | id, rentalId, costumeId, quantity, pricePerDay, status |
| ReturnDetail | id, rentalDetailId, returnDate, daysBorrowed, rentalAmount, fine |
| Payment | id, rentalId, paymentDate, totalAmount |
| User | id, username, password, role |

### Quan hệ

```
Customer 1 --- n Rental
Rental 1 --- n RentalDetail n --- 1 Costume
RentalDetail 1 --- n ReturnDetail
Rental 1 --- n Payment
```

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeView:
  tùy chọn thống kê trang phục → subCostumeStat

Chọn Statistics → Costume statistics → hiển thị CostumeStatFrm:
  ô nhập ngày bắt đầu → inStartDate
  ô nhập ngày kết thúc → inEndDate
  nút xem thống kê → subView

Nhập khoảng thời gian (01/01/2026 - 31/12/2026), nhấn View → hiển thị thống kê:
  bảng trang phục (click chọn) → outCostumeTable
  (cột: mã, tên, mẫu, thể loại, tổng số lần thuê, tổng tiền thu, sắp xếp theo số lần thuê giảm dần)

Click chọn trang phục "Váy công chúa" → hiển thị chi tiết:
  bảng hóa đơn chi tiết → outInvoiceTable
  (cột: mã HĐ, tên người thuê, ngày thuê, ngày trả, tổng tiền)

Click chọn trang phục khác → cập nhật bảng chi tiết hóa đơn

### Tóm tắt
View classes: HomeView, CostumeStatFrm
Methods: getCostumeStat(), getCostumeInvoices()

---

## Câu 3: Thiết kế tĩnh

### View classes

**CostumeStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outCostumeTable`: bảng trang phục (click được)
- `outInvoiceTable`: bảng hóa đơn chi tiết

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CostumeDAO | `getCostumeStat(startDate, endDate): List<CostumeStat>` |
| RentalDAO | `getCostumeInvoices(costumeId, startDate, endDate): List<CostumeInvoice>` |

**CostumeStat:** costume, totalLoans, totalRevenue

**CostumeInvoice:** id, borrowerName, rentalDate, returnDate, totalAmount

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:CostumeStatFrm` — boundary
3. `:CostumeDAO` — control
4. `:RentalDAO` — control

**Message flow:**

1. Staff → CostumeStatFrm: `enterDates(01/01/2026, 31/12/2026)` + `clickView()`
2. CostumeStatFrm → CostumeDAO: `getCostumeStat(dates)`
3. CostumeDAO → CostumeStatFrm: return `List<CostumeStat>`
4. Staff → CostumeStatFrm: `clickCostume(Váy công chúa)`
5. CostumeStatFrm → RentalDAO: `getCostumeInvoices(costumeId, dates)`
6. RentalDAO → CostumeStatFrm: return `List<CostumeInvoice>`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê trang phục có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: Váy công chúa (50 lần, 5,000,000đ), Áo Superman (40 lần, 3,600,000đ) |
| 2 | Nhấn Váy công chúa | Chi tiết: HD001 (Tran Thi B, 01/07, 06/07, 500,000đ), HD005... |

**Database sau:** Không thay đổi (chỉ đọc).
