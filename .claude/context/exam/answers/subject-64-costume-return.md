# Subject No. 64 — Costume Rental — Module "Customer returns and pays"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Trả trang phục và thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Customer returns and pays**. |
| 2 | Giao diện: ô nhập tên khách hàng, nút Search. |
| 3 | Staff nhập "Tran Thi B", Search. Hệ thống hiển thị danh sách KH. |
| 4 | Staff click "Tran Thi B". Hệ thống hiển thị danh sách trang phục đang thuê. |
| 5 | Bảng: Váy công chúa × 2, ngày thuê 01/07, giá 50,000đ/ngày, số ngày thuê 5, tiền thuê 500,000đ. Áo Superman × 3, ngày thuê 01/07, giá 30,000đ/ngày, số ngày 5, tiền thuê 450,000đ. |
| 6 | Staff tick chọn Váy công chúa (trả), trạng thái "Bình thường", phạt 0đ. |
| 7 | Staff tick chọn Áo Superman (trả), trạng thái "Rách 1 áo", phạt 300,000đ. |
| 8 | Staff nhấn **Payment**. Hệ thống hiển thị hóa đơn: tổng thuê 950,000đ + phạt 300,000đ = 1,250,000đ. Cọc 1,900,000đ. KH nhận lại 650,000đ. |
| 9 | Staff nhấn **Confirm**. Hệ thống cập nhật database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Costume | id, code, name, category, quantity, price |
| Customer | id, code, name, phone, address |
| Rental | id, customerId, rentalDate, deposit, status |
| RentalDetail | id, rentalId, costumeId, quantity, pricePerDay, status |
| ReturnDetail | id, rentalDetailId, returnDate, daysBorrowed, rentalAmount, fine, condition |
| Payment | id, rentalId, paymentDate, totalAmount, depositReturned, customerPay |
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
  tùy chọn trả trang phục và thanh toán → subReturnCostume

Chọn Customer returns and pays → hiển thị ReturnCostumeFrm:
  ô nhập tên khách hàng → inCustomerName
  nút tìm kiếm → subSearch

Nhập "Tran Thi B", nhấn Search → hiển thị danh sách khách hàng:
  bảng khách hàng (click chọn) → outCustomerList

Click chọn khách hàng "Tran Thi B" → hiển thị trang phục đang thuê:
  bảng trang phục đang thuê (có checkbox) → outRentalTable

Tick chọn trang phục trả, nhập trạng thái và tiền phạt:
  combobox trạng thái (Bình thường/Hỏng/Rách) → inCondition
  ô nhập tiền phạt → inFine

Nhấn Payment → hiển thị hóa đơn:
  hóa đơn → outInvoice
  tổng tiền thuê + phạt → outTotal
  tiền cọc → outDeposit
  tiền trả lại khách hàng → outRefund

Nhấn Confirm → subConfirm
  hệ thống lưu chi tiết trả, tạo thanh toán, cập nhật trạng thái phiếu thuê, cập nhật tồn kho

### Tóm tắt
View classes: HomeView, ReturnCostumeFrm
Methods: searchCustomerByName(), getActiveRentals(), getRentalDetails(), addReturnDetail(), createPayment(), updateRentalStatus(), updateCostumeQuantity()

---

## Câu 3: Thiết kế tĩnh

### View classes

**ReturnCostumeFrm:**
- `inCustomerName`: ô nhập tên KH
- `subSearch`: nút Search
- `outCustomerList`: danh sách KH (click được)
- `outRentalTable`: bảng trang phục đang thuê (checkbox)
- `inCondition`: combobox trạng thái (Bình thường/Hỏng/Rách)
- `inFine`: ô nhập tiền phạt
- `outInvoice`: hóa đơn
- `outTotal`: tổng tiền thuê + phạt
- `outDeposit`: tiền cọc
- `outRefund`: tiền trả lại KH
- `subPayment`: nút Payment
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchCustomerByName(keyword): List<Customer>` |
| RentalDAO | `getActiveRentals(customerId): List<Rental>` |
| RentalDetailDAO | `getRentalDetails(rentalId): List<RentalDetail>` |
| ReturnDetailDAO | `addReturnDetail(returnDetail): boolean` |
| PaymentDAO | `createPayment(payment): boolean` |
| RentalDAO | `updateRentalStatus(rentalId, status): boolean` |
| CostumeDAO | `updateCostumeQuantity(costumeId, +quantity): boolean` |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:ReturnCostumeFrm` — boundary
3. `:CustomerDAO` — control
4. `:RentalDAO` — control
5. `:ReturnDetailDAO` — control
6. `:PaymentDAO` — control

**Message flow:**

1. Staff → ReturnCostumeFrm: `enterCustomerName("Tran Thi B")` + `clickSearch()`
2. ReturnCostumeFrm → CustomerDAO: `searchCustomerByName("Tran Thi B")`
3. CustomerDAO → ReturnCostumeFrm: return `List<Customer>`
4. Staff → ReturnCostumeFrm: `clickCustomer(B)`
5. ReturnCostumeFrm → RentalDAO: `getActiveRentals(customerId)`
6. RentalDAO → ReturnCostumeFrm: return `List<Rental>`
7. Staff → ReturnCostumeFrm: `selectReturn(Váy, condition="Bình thường", fine=0)` + `selectReturn(Áo, condition="Rách", fine=300000)`
8. Staff → ReturnCostumeFrm: `clickPayment()`
9. ReturnCostumeFrm: display invoice (950000 + 300000 - 1900000 = -650000 → trả lại KH)
10. Staff → ReturnCostumeFrm: `clickConfirm()`
11. ReturnCostumeFrm → ReturnDetailDAO: `addReturnDetails(details)`
12. ReturnCostumeFrm → PaymentDAO: `createPayment(payment)`
13. ReturnCostumeFrm → RentalDAO: `updateRentalStatus(rentalId, "returned")`
14. ReturnCostumeFrm → CostumeDAO: `updateCostumeQuantity(costumeId, +quantity)`

---

## Câu 5: Blackbox Testcase

### TC01: Trả trang phục thành công

**Database trước:**
- tblRental: 1 dòng (deposit=1900000, status="renting")
- tblRentalDetail: Váy × 2, Áo × 3
- tblPayment: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "Tran Thi B" → Search, click KH | Danh sách trang phục đang thuê |
| 2 | Tick Váy (Bình thường, 0đ), Áo (Rách, 300,000đ) | Hóa đơn: 950,000 + 300,000 = 1,250,000đ |
| 3 | Payment | Hóa đơn: tổng 1,250,000đ, cọc 1,900,000đ, trả lại KH 650,000đ |
| 4 | Confirm | "Tra trang phuc thanh cong" |

**Database sau:**
- tblRental: status = "returned"
- tblReturnDetail: thêm 2 dòng
- tblPayment: thêm 1 dòng (totalAmount=1250000, depositReturned=650000)
- tblCostume: Váy (+2), Áo (+3)
