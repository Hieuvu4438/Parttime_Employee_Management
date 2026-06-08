# Subject No. 59 — Installment Loan — Module "Customer statistics by debt"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Thống kê khách hàng theo nợ

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, chọn chức năng **Customer statistics by debt** |
| 2 | Hệ thống hiển thị danh sách thống kê tất cả khách hàng, mỗi khách hàng trên 1 dòng, sắp xếp theo tổng nợ giảm dần. Mỗi dòng gồm: mã khách hàng, tên, số điện thoại, tổng dư nợ, tổng nợ còn lại |
| 3 | Staff click vào dòng khách hàng **Nguyen Van A** |
| 4 | Hệ thống hiển thị danh sách hợp đồng của khách hàng Nguyen Van A, mỗi dòng: mã hợp đồng, ngày ký, tổng số tiền vay, tổng số kỳ đã trả, tổng dư nợ, tổng nợ còn lại |
| 5 | Staff click vào dòng hợp đồng **HD001** |
| 6 | Hệ thống hiển thị chi tiết hợp đồng HD001: thông tin khách hàng, số điện thoại, danh sách sản phẩm (tên, số lượng, đơn giá, thành tiền), danh sách thanh toán theo kỳ (kỳ 1: đã thanh toán, kỳ 2: đã thanh toán, kỳ 3: đã thanh toán, kỳ 4: chưa thanh toán...), trạng thái hoàn thành hay chưa |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | customerId (PK), customerName, phoneNumber, address, idCard |
| Contract | contractId (PK), customerId (FK), partnerId (FK), signingDate, totalLoanAmount, status |
| ContractItem | contractItemId (PK), contractId (FK), productId (FK), quantity, unitPrice, amount |
| Product | productId (PK), productName, unitPrice, description |
| PaymentSchedule | scheduleId (PK), contractId (FK), periodNumber, dueDate, amount, status |
| Payment | paymentId (PK), scheduleId (FK), paymentDate, amountPaid, method |
| User | userId (PK), username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Contract
Contract 1 --- n ContractItem
Product 1 --- n ContractItem
Contract 1 --- n PaymentSchedule
PaymentSchedule 1 --- n Payment
User 1 --- n Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatDebtFrm:**
- `outCustomerStatTable`: Bảng thống kê khách hàng theo nợ (mã KH, tên, SĐT, tổng dư nợ, tổng nợ còn lại), sắp xếp theo tổng nợ giảm dần
- `outContractList`: Danh sách hợp đồng của 1 khách hàng được chọn (mã HĐ, ngày ký, tổng vay, số kỳ đã trả, dư nợ, nợ còn lại)
- `outContractDetails`: Thông tin chi tiết hợp đồng: thông tin KH, SĐT, trạng thái thanh toán
- `outItemList`: Danh sách sản phẩm trong hợp đồng (tên SP, số lượng, đơn giá, thành tiền)
- `outPaymentList`: Danh sách thanh toán theo kỳ (kỳ, ngày đến hạn, số tiền, trạng thái đã/chưa thanh toán)

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `getDebtStatistics(): List<Customer>` — Lấy danh sách tất cả khách hàng kèm tổng dư nợ và tổng nợ còn, sắp xếp theo tổng nợ giảm dần |
| ContractDAO | `getContractsByCustomer(int customerId): List<Contract>` — Lấy danh sách hợp đồng của 1 khách hàng |
| ContractItemDAO | `getItemsByContract(int contractId): List<ContractItem>` — Lấy danh sách sản phẩm trong 1 hợp đồng |
| PaymentScheduleDAO | `getScheduleByContract(int contractId): List<PaymentSchedule>` — Lấy lịch trình thanh toán theo kỳ của 1 hợp đồng |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — Actor, nhân viên xem thống kê khách hàng theo nợ
2. `:StatDebtFrm` — boundary, giao diện thống kê khách hàng theo nợ
3. `:CustomerDAO` — control, truy vấn dữ liệu thống kê khách hàng
4. `:ContractDAO` — control, truy vấn dữ liệu hợp đồng
5. `:ContractItemDAO` — control, truy vấn dữ liệu sản phẩm trong hợp đồng
6. `:PaymentScheduleDAO` — control, truy vấn lịch trình thanh toán
7. `:Database` — database

**Message flow:**
1. Staff → StatDebtFrm: `selectMenu("Customer statistics by debt")`
2. StatDebtFrm → CustomerDAO: `getDebtStatistics()`
3. CustomerDAO → Database: `SELECT customerId, customerName, phone, SUM(outstanding) AS totalOutstanding, SUM(debtRemaining) AS totalDebt FROM ... GROUP BY customerId ORDER BY totalDebt DESC`
4. Database → CustomerDAO: `ResultSet`
5. CustomerDAO → StatDebtFrm: `List<Customer>`
6. StatDebtFrm → Staff: `show(outCustomerStatTable)`
7. Staff → StatDebtFrm: `click("Nguyen Van A")`
8. StatDebtFrm → ContractDAO: `getContractsByCustomer(customerId)`
9. ContractDAO → Database: `SELECT * FROM tblContract WHERE customerId = ?`
10. Database → ContractDAO: `ResultSet`
11. ContractDAO → StatDebtFrm: `List<Contract>`
12. StatDebtFrm → Staff: `show(outContractList)`
13. Staff → StatDebtFrm: `click("HD001")`
14. StatDebtFrm → ContractItemDAO: `getItemsByContract(contractId)`
15. ContractItemDAO → Database: `SELECT * FROM tblContractItem WHERE contractId = ?`
16. Database → ContractItemDAO: `ResultSet`
17. ContractItemDAO → StatDebtFrm: `List<ContractItem>`
18. StatDebtFrm → PaymentScheduleDAO: `getScheduleByContract(contractId)`
19. PaymentScheduleDAO → Database: `SELECT * FROM tblPaymentSchedule WHERE contractId = ? ORDER BY periodNumber`
20. Database → PaymentScheduleDAO: `ResultSet`
21. PaymentScheduleDAO → StatDebtFrm: `List<PaymentSchedule>`
22. StatDebtFrm → Staff: `show(outContractDetails, outItemList, outPaymentList)`

---

## Câu 5: Blackbox Testcase

### TC01: Xem thống kê khách hàng theo nợ và xem chi tiết hợp đồng

**Database trước:**

tblCustomer:
| customerId | customerName | phoneNumber | address |
|-----------|-------------|-------------|---------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi |
| C002 | Tran Van B | 0912345678 | HCM |
| C003 | Le Thi C | 0923456789 | Da Nang |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-06-01 | 30000000 | Active |
| HD005 | C001 | P002 | 2026-07-15 | 10000000 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | status |
|-----------|-----------|-------------|---------|--------|--------|
| PS001 | HD001 | 1 | 2026-07-01 | 7500000 | Paid |
| PS002 | HD001 | 2 | 2026-08-01 | 7500000 | Paid |
| PS003 | HD001 | 3 | 2026-09-01 | 7500000 | Paid |
| PS004 | HD001 | 4 | 2026-10-01 | 7500000 | Unpaid |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Đăng nhập thành công, hiển thị trang chủ |
| 2 | Chọn "Customer statistics by debt" — Hiển thị bảng thống kê khách hàng theo nợ giảm dần: C001 (Nguyen Van A, 0901234567, dư nợ 27,250,000đ, nợ còn lại 21,750,000đ), C002, C003... |
| 3 | Click dòng "Nguyen Van A" — Hiển thị danh sách hợp đồng: HD001 (01/06/2026, vay 30,000,000đ, đã trả 3 kỳ, dư nợ 21,750,000đ, nợ còn 21,750,000đ), HD005 (15/07/2026, vay 10,000,000đ, ...) |
| 4 | Click dòng "HD001" — Hiển thị chi tiết hợp đồng: thông tin KH Nguyen Van A, SĐT 0901234567; danh sách SP: iPhone 15 (1, 25,000,000đ), AirPods Pro (1, 5,000,000đ); thanh toán: kỳ 1 đã thanh toán, kỳ 2 đã thanh toán, kỳ 3 đã thanh toán, kỳ 4 chưa thanh toán |

**Database sau:**

Không thay đổi (chức năng chỉ đọc dữ liệu thống kê, không có thao tác ghi).
