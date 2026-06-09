# Subject No. 60 — Installment Loans — Module "Statistics of partners"

> **Domain:** Installment Loans Management

---

## Câu 1: Scenario — Thống kê đối tác

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Partner statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách đối tác: mã, tên, địa chỉ/chi nhánh, tổng hóa đơn, tổng tiền bán, tổng dư nợ. Sắp xếp: tổng doanh thu giảm dần. |
| 5 | Staff nhấn vào đối tác "FPT Shop". |
| 6 | Hệ thống hiển thị danh sách hợp đồng: mã HĐ, tên KH, ngày ký, tổng vay, số kỳ, tổng dư nợ, tổng nợ quá hạn. |
| 7 | Staff nhấn vào hợp đồng "HD001". |
| 8 | Hệ thống hiển thị chi tiết: thông tin KH, SĐT, danh sách sản phẩm, số lượng, giá, tổng tiền; lịch sử thanh toán, trạng thái. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Partner | id, code, name, address, email, phone |
| Customer | id, code, name, phone, address |
| Contract | id, customerId, partnerId, signingDate, totalAmount, status |
| ContractItem | id, contractId, productId, quantity, unitPrice, amount |
| PaymentSchedule | id, contractId, paymentDate, paymentAmount, outstandingBalance, status |
| User | id, username, password, role |

### Quan hệ

```
Partner 1 --- n Contract n --- 1 Customer
Contract 1 --- n ContractItem
Contract 1 --- n PaymentSchedule
```

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Statistics" → "Partner statistics" → subPartnerStat

Staff chọn Partner statistics → PartnerStatView xuất hiện:
  Ô nhập ngày bắt đầu → inStartDate
  Ô nhập ngày kết thúc → inEndDate
  Nút View → subView
  Bảng thống kê đối tác (click được) → outsubPartnerTable

Staff click đối tác → PartnerContractListView xuất hiện:
  Danh sách hợp đồng của đối tác (click được) → outsubContractTable

Staff click hợp đồng → ContractDetailView xuất hiện:
  Chi tiết hợp đồng → outContractDetail

Phân tích phương thức:

Lấy thống kê đối tác theo khoảng thời gian:
  Tên: getPartnerStat()
  Đầu vào: startDate (Date), endDate (Date)
  Đầu ra: List<PartnerStat>
  Gán cho entity class: Partner.

Lấy danh sách hợp đồng của đối tác:
  Tên: getPartnerContracts()
  Đầu vào: partnerId (int), startDate (Date), endDate (Date)
  Đầu ra: List<Contract>
  Gán cho entity class: Contract.

Lấy chi tiết sản phẩm trong hợp đồng:
  Tên: getContractItems()
  Đầu vào: contractId (int)
  Đầu ra: List<ContractItem>
  Gán cho entity class: ContractItem.

### Tóm tắt
View classes: HomeFrm, PartnerStatView, PartnerContractListView, ContractDetailView
Phương thức: getPartnerStat(), getPartnerContracts(), getContractItems()

---

## Câu 3: Thiết kế tĩnh

### View classes

**PartnerStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outPartnerTable`: bảng đối tác (click được)
- `outContractTable`: bảng hợp đồng (click được)
- `outContractDetail`: chi tiết hợp đồng

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PartnerDAO | `getPartnerStat(startDate, endDate): List<PartnerStat>` |
| ContractDAO | `getPartnerContracts(partnerId, startDate, endDate): List<ContractDebt>` |
| ContractItemDAO | `getContractItems(contractId): List<ContractItem>` |

**PartnerStat:** partner, totalInvoices, totalSales, totalOutstanding

**ContractDebt:** contract, customerName, signingDate, totalLoan, totalPayments, totalOutstanding, totalOverdue

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:PartnerStatFrm` — boundary
3. `:PartnerDAO` — control
4. `:ContractDAO` — control

**Message flow:**

1. Staff → PartnerStatFrm: `enterDates(01/01/2026, 31/12/2026)` + `clickView()`
2. PartnerStatFrm → PartnerDAO: `getPartnerStat(dates)`
3. PartnerDAO → PartnerStatFrm: return `List<PartnerStat>`
4. Staff → PartnerStatFrm: `clickPartner(FPT Shop)`
5. PartnerStatFrm → ContractDAO: `getPartnerContracts(partnerId, dates)`
6. ContractDAO → PartnerStatFrm: return `List<ContractDebt>`
7. Staff → PartnerStatFrm: `clickContract(HD001)`
8. PartnerStatFrm → ContractItemDAO: `getContractItems(contractId)`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê đối tác có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: FPT Shop (50 HĐ, 1,500,000,000đ, 500,000,000đ dư nợ), The Gioi Di Dong (30 HĐ, 800,000,000đ, 200,000,000đ) |
| 2 | Nhấn FPT Shop | HĐ: HD001 (Nguyen Van A, 01/06, 36,000,000đ, 28,800,000đ nợ, 0đ quá hạn) |
| 3 | Nhấn HD001 | Chi tiết: iPhone × 1, AirPods × 1; thanh toán 2/12 kỳ |

**Database sau:** Không thay đổi (chỉ đọc).
