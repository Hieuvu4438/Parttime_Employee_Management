# Subject No. 58 — Installment Loan — Module "Payment to partners"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Thanh toán cho đối tác

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, chọn chức năng **Payment to partners** |
| 2 | Hệ thống hiển thị giao diện tìm kiếm đối tác với ô nhập tên đối tác và nút **Search** |
| 3 | Staff nhập tên đối tác "FPT Shop" vào ô tìm kiếm, nhấn nút **Search** |
| 4 | Hệ thống hiển thị danh sách đối tác có tên chứa từ khóa "FPT Shop" |
| 5 | Staff click chọn đối tác **FPT Shop** |
| 6 | Hệ thống hiển thị danh sách hợp đồng của khách hàng mua từ FPT Shop mà công ty chưa thanh toán cho đối tác. Mỗi hợp đồng trên 1 dòng: mã hợp đồng, tên khách hàng, tổng sản phẩm, tổng tiền khách hàng, số tiền cần trả cho đối tác |
| 7 | Staff tick chọn hợp đồng **HD001** (Nguyen Van A, 2 sản phẩm, 30,000,000đ, cần trả FPT: 28,000,000đ) và **HD003** (Le Thi C, 1 sản phẩm, 15,000,000đ, cần trả FPT: 14,000,000đ), nhấn **Next** |
| 8 | Hệ thống hiển thị hóa đơn thanh toán cho đối tác với đầy đủ thông tin: đối tác FPT Shop, danh sách hợp đồng đã chọn, tổng thanh toán 42,000,000đ |
| 9 | Staff xác nhận thông tin, nhấn **Save** |
| 10 | Hệ thống lưu phiếu thanh toán đối tác vào cơ sở dữ liệu, in hóa đơn cho đối tác ký, staff lưu hóa đơn |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | customerId (PK), customerName, phoneNumber, address, idCard |
| Partner | partnerId (PK), partnerName, address, phoneNumber, branch |
| Product | productId (PK), productName, unitPrice, description |
| Contract | contractId (PK), customerId (FK), partnerId (FK), signingDate, totalLoanAmount, status |
| ContractItem | contractItemId (PK), contractId (FK), productId (FK), quantity, unitPrice, amount |
| PaymentSchedule | scheduleId (PK), contractId (FK), periodNumber, dueDate, amount, status |
| Payment | paymentId (PK), scheduleId (FK), paymentDate, amountPaid, method |
| PartnerPayment | partnerPaymentId (PK), partnerId (FK), userId (FK), paymentDate, totalAmount, status |
| User | userId (PK), username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Contract
Partner 1 --- n Contract
Contract 1 --- n ContractItem
Product 1 --- n ContractItem
Contract 1 --- n PaymentSchedule
PaymentSchedule 1 --- n Payment
Partner 1 --- n PartnerPayment
User 1 --- n PartnerPayment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**PayPartnerFrm:**
- `inPartnerName`: Ô nhập tên đối tác cần tìm kiếm
- `subSearchPartner`: Nút tìm kiếm đối tác
- `outPartnerList`: Danh sách đối tác kết quả tìm kiếm (tên, địa chỉ, chi nhánh)
- `outContractList`: Danh sách hợp đồng chưa thanh toán cho đối tác (checkbox, mã HĐ, tên KH, tổng sản phẩm, tổng tiền, tiền cần trả)
- `subNext`: Nút chuyển sang xem hóa đơn thanh toán
- `outPaymentInvoice`: Hóa đơn thanh toán đối tác (đối tác, danh sách HĐ, tổng tiền)
- `subConfirm`: Nút xác nhận và lưu thanh toán

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PartnerDAO | `searchByName(String keyword): List<Partner>` — Tìm kiếm đối tác theo tên |
| ContractDAO | `getUnpaidContractsByPartner(int partnerId): List<Contract>` — Lấy danh sách hợp đồng chưa thanh toán cho đối tác |
| PartnerPaymentDAO | `createPayment(PartnerPayment payment): boolean` — Tạo phiếu thanh toán đối tác mới |
| PartnerPaymentDetailDAO | `addDetails(int partnerPaymentId, List<PartnerPaymentDetail> details): boolean` — Thêm chi tiết thanh toán theo hợp đồng |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — Actor, nhân viên thực hiện thanh toán cho đối tác
2. `:PayPartnerFrm` — boundary, giao diện thanh toán cho đối tác
3. `:PartnerDAO` — control, truy vấn dữ liệu đối tác
4. `:ContractDAO` — control, truy vấn dữ liệu hợp đồng
5. `:PartnerPaymentDAO` — control, truy vấn và lưu phiếu thanh toán đối tác
6. `:PartnerPaymentDetailDAO` — control, truy vấn và lưu chi tiết thanh toán
7. `:Database` — database

**Message flow:**
1. Staff → PayPartnerFrm: `selectMenu("Payment to partners")`
2. PayPartnerFrm → Staff: `showSearchForm()`
3. Staff → PayPartnerFrm: `enter(inPartnerName="FPT Shop"), click(subSearchPartner)`
4. PayPartnerFrm → PartnerDAO: `searchByName("FPT Shop")`
5. PartnerDAO → Database: `SELECT * FROM tblPartner WHERE partnerName LIKE '%FPT Shop%'`
6. Database → PartnerDAO: `ResultSet`
7. PartnerDAO → PayPartnerFrm: `List<Partner>`
8. PayPartnerFrm → Staff: `show(outPartnerList)`
9. Staff → PayPartnerFrm: `click("FPT Shop")`
10. PayPartnerFrm → ContractDAO: `getUnpaidContractsByPartner(partnerId)`
11. ContractDAO → Database: `SELECT hợp đồng chưa thanh toán WHERE partnerId = ?`
12. Database → ContractDAO: `ResultSet`
13. ContractDAO → PayPartnerFrm: `List<Contract>`
14. PayPartnerFrm → Staff: `show(outContractList)`
15. Staff → PayPartnerFrm: `tick(HD001, HD003), click(subNext)`
16. PayPartnerFrm → PayPartnerFrm: `calculateInvoice(selectedContracts)`
17. PayPartnerFrm → Staff: `show(outPaymentInvoice) — tổng 42,000,000đ`
18. Staff → PayPartnerFrm: `click(subConfirm)`
19. PayPartnerFrm → PartnerPaymentDAO: `createPayment(payment)`
20. PartnerPaymentDAO → Database: `INSERT INTO tblPartnerPayment(...)`
21. Database → PartnerPaymentDAO: `partnerPaymentId`
22. PayPartnerFrm → PartnerPaymentDetailDAO: `addDetails(partnerPaymentId, details)`
23. PartnerPaymentDetailDAO → Database: `INSERT INTO tblPartnerPaymentDetail(...) × 2 dòng`
24. Database → PartnerPaymentDetailDAO: `true`
25. PayPartnerFrm → Staff: `printInvoice(), showSuccessMessage()`

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán thành công cho đối tác FPT Shop

**Database trước:**

tblPartner:
| partnerId | partnerName | address | phoneNumber |
|-----------|-------------|---------|-------------|
| P001 | FPT Shop | 123 Le Loi, HCM | 0901111111 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-01-15 | 30000000 | Active |
| HD003 | C003 | P001 | 2026-03-10 | 15000000 | Active |

tblPartnerPayment: (rỗng)
tblPartnerPaymentDetail: (rỗng)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Đăng nhập thành công, hiển thị trang chủ |
| 2 | Chọn "Payment to partners" — Hiển thị giao diện tìm kiếm đối tác với ô nhập tên và nút Search |
| 3 | Nhập "FPT Shop", nhấn Search — Hiển thị danh sách đối tác chứa "FPT Shop" |
| 4 | Click chọn "FPT Shop" — Hiển thị danh sách hợp đồng chưa thanh toán: HD001 (Nguyen Van A, 2 SP, 30,000,000đ, cần trả 28,000,000đ), HD003 (Le Thi C, 1 SP, 15,000,000đ, cần trả 14,000,000đ) |
| 5 | Tick chọn HD001 và HD003, nhấn Next — Hiển thị hóa đơn thanh toán: FPT Shop, tổng 42,000,000đ |
| 6 | Xác nhận, nhấn Save — Hệ thống lưu phiếu thanh toán, in hóa đơn, hiển thị thông báo thành công |

**Database sau:**

tblPartner: Không thay đổi

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-01-15 | 30000000 | **Paid** |
| HD003 | C003 | P001 | 2026-03-10 | 15000000 | **Paid** |

tblPartnerPayment:
| partnerPaymentId | partnerId | userId | paymentDate | totalAmount | status |
|-----------------|-----------|--------|-------------|-------------|--------|
| PP001 | P001 | U001 | 2026-06-08 | 42000000 | Completed |

tblPartnerPaymentDetail:
| detailId | partnerPaymentId | contractId | amount |
|----------|-----------------|-----------|--------|
| D001 | PP001 | HD001 | 28000000 |
| D002 | PP001 | HD003 | 14000000 |
