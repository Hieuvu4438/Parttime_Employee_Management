# Subject No. 58 — Installment Loans — Module "Payment to partners"

> **Domain:** Installment Loans Management

---

## Câu 1: Scenario — Thanh toán cho đối tác

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Payment to partners**. |
| 2 | Giao diện: ô nhập tên đối tác, nút Search. |
| 3 | Staff nhập "FPT Shop", Search. Hệ thống hiển thị danh sách đối tác. |
| 4 | Staff click "FPT Shop". |
| 5 | Hệ thống hiển thị danh sách hợp đồng mà công ty chưa thanh toán cho đối tác: mã HĐ, tên KH, tổng sản phẩm, tổng tiền KH, số tiền cần trả đối tác. |
| 6 | Staff chọn hợp đồng HD001, HD002. |
| 7 | Hệ thống hiển thị hóa đơn thanh toán đối tác: thông tin đối tác, danh sách HĐ đã chọn, tổng tiền. |
| 8 | Staff xác nhận, nhấn **Save**. Hệ thống lưu và in hóa đơn. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Partner | id, code, name, address, email, phone |
| Customer | id, code, name, phone, address |
| Contract | id, customerId, partnerId, signingDate, totalAmount, status |
| ContractItem | id, contractId, productId, quantity, unitPrice, amount |
| PartnerPayment | id, partnerId, paymentDate, totalAmount |
| PartnerPaymentDetail | id, partnerPaymentId, contractId, amount |
| User | id, username, password, role |

### Quan hệ

```
Partner 1 --- n Contract n --- 1 Customer
Contract 1 --- n ContractItem
Partner 1 --- n PartnerPayment
PartnerPayment 1 --- n PartnerPaymentDetail n --- 1 Contract
```

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Payment to partners" → subPartnerPay

Staff chọn Payment to partners → SearchPartnerView xuất hiện:
  Ô nhập tên đối tác → inPartnerName
  Nút Search → subSearch
  Danh sách đối tác (click được) → outsubListPartner

Staff chọn đối tác → PartnerContractListView xuất hiện:
  Bảng hợp đồng chưa thanh toán cho đối tác (checkbox) → outContractTable
  Hóa đơn thanh toán đối tác → outInvoice
  Tổng tiền → outTotal
  Nút Save → subSave

Phân tích phương thức:

Tìm kiếm đối tác theo tên:
  Tên: searchPartnerByName()
  Đầu vào: keyword (String)
  Đầu ra: List<Partner>
  Gán cho entity class: Partner.

Lấy danh sách hợp đồng chưa thanh toán cho đối tác:
  Tên: getUnpaidContracts()
  Đầu vào: partnerId (int)
  Đầu ra: List<Contract>
  Gán cho entity class: Contract.

Tạo phiếu thanh toán đối tác:
  Tên: createPartnerPayment()
  Đầu vào: partnerPayment (PartnerPayment)
  Đầu ra: boolean
  Gán cho entity class: PartnerPayment.

Lưu chi tiết thanh toán đối tác:
  Tên: addPaymentDetails()
  Đầu vào: details (List<PartnerPaymentDetail>)
  Đầu ra: boolean
  Gán cho entity class: PartnerPaymentDetail.

### Tóm tắt
View classes: HomeFrm, SearchPartnerView, PartnerContractListView
Phương thức: searchPartnerByName(), getUnpaidContracts(), createPartnerPayment(), addPaymentDetails()

---

## Câu 3: Thiết kế tĩnh

### View classes

**PartnerPayFrm:**
- `inPartnerName`: ô nhập tên đối tác
- `subSearch`: nút Search
- `outPartnerList`: danh sách đối tác (click được)
- `outContractTable`: bảng hợp đồng chưa thanh toán (checkbox)
- `outInvoice`: hóa đơn thanh toán
- `outTotal`: tổng tiền
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PartnerDAO | `searchPartnerByName(keyword): List<Partner>` |
| ContractDAO | `getUnpaidContracts(partnerId): List<Contract>` |
| PartnerPaymentDAO | `createPartnerPayment(payment): boolean` |
| PartnerPaymentDetailDAO | `addPaymentDetails(details): boolean` |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:PartnerPayFrm` — boundary
3. `:PartnerDAO` — control
4. `:ContractDAO` — control
5. `:PartnerPaymentDAO` — control

**Message flow:**

1. Staff → PartnerPayFrm: `enterPartnerName("FPT Shop")` + `clickSearch()`
2. PartnerPayFrm → PartnerDAO: `searchPartnerByName("FPT Shop")`
3. PartnerDAO → PartnerPayFrm: return `List<Partner>`
4. Staff → PartnerPayFrm: `clickPartner(FPT Shop)`
5. PartnerPayFrm → ContractDAO: `getUnpaidContracts(partnerId)`
6. ContractDAO → PartnerPayFrm: return `List<Contract>`
7. Staff → PartnerPayFrm: `selectContracts(HD001, HD002)`
8. PartnerPayFrm: display invoice
9. Staff → PartnerPayFrm: `clickSave()`
10. PartnerPayFrm → PartnerPaymentDAO: `createPartnerPayment(payment)`
11. PartnerPaymentDAO: INSERT INTO tblPartnerPayment + tblPartnerPaymentDetail

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán đối tác thành công

**Database trước:**
- tblPartnerPayment: 0 dòng
- tblContract: HD001 (partnerId=FPT, amountToPartner=28,000,000), HD002 (amountToPartner=5,000,000)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "FPT Shop" → Search, click đối tác | Danh sách HĐ chưa thanh toán |
| 2 | Chọn HD001, HD002 | Hóa đơn: tổng 33,000,000đ |
| 3 | Save | "Thanh toan doi tac thanh cong" |

**Database sau:**
- tblPartnerPayment: thêm 1 dòng (totalAmount=33000000)
- tblPartnerPaymentDetail: thêm 2 dòng
