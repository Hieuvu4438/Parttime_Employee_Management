# Subject No. 60 — Installment Loan — Module "Statistics of partners"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Thống kê đối tác theo doanh số

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, chọn chức năng **Statistics of partners** |
| 2 | Hệ thống hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc và nút **Xem thống kê** |
| 3 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết thúc `31/12/2026`, nhấn nút **Xem thống kê** |
| 4 | Hệ thống hiển thị danh sách thống kê đối tác, mỗi đối tác trên 1 dòng, sắp xếp theo tổng doanh thu giảm dần. Mỗi dòng gồm: mã đối tác, tên, địa chỉ, tổng hóa đơn, tổng tiền bán hàng, tổng dư nợ |
| 5 | Staff click vào dòng đối tác **FPT Shop** |
| 6 | Hệ thống hiển thị danh sách hợp đồng của FPT Shop, mỗi dòng: mã hợp đồng, tên khách hàng, ngày ký, tổng vay, tổng đã thanh toán, tổng dư nợ, tổng quá hạn |
| 7 | Staff click vào dòng hợp đồng **HD001** |
| 8 | Hệ thống hiển thị chi tiết hợp đồng HD001: thông tin khách hàng, số điện thoại, danh sách sản phẩm (tên, số lượng, đơn giá, thành tiền), danh sách thanh toán theo kỳ và trạng thái từng kỳ |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Customer | customerId (PK), customerName, phoneNumber, address, idCard |
| Partner | partnerId (PK), partnerName, address, phoneNumber, branch |
| Contract | contractId (PK), customerId (FK), partnerId (FK), signingDate, totalLoanAmount, status |
| ContractItem | contractItemId (PK), contractId (FK), productId (FK), quantity, unitPrice, amount |
| Product | productId (PK), productName, unitPrice, description |
| PaymentSchedule | scheduleId (PK), contractId (FK), periodNumber, dueDate, amount, status |
| Payment | paymentId (PK), scheduleId (FK), paymentDate, amountPaid, method |
| User | userId (PK), username, password, fullName, role |

### Quan hệ

```
Customer 1 --- n Contract
Partner 1 --- n Contract
Contract 1 --- n ContractItem
Product 1 --- n ContractItem
Contract 1 --- n PaymentSchedule
PaymentSchedule 1 --- n Payment
User 1 --- n Payment
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatPartnerFrm:**
- `inStartDate`: Ô nhập ngày bắt đầu kỳ thống kê
- `inEndDate`: Ô nhập ngày kết thúc kỳ thống kê
- `subView`: Nút "Xem thống kê"
- `outPartnerStatTable`: Bảng thống kê đối tác (mã, tên, địa chỉ, tổng hóa đơn, tổng tiền bán hàng, tổng dư nợ), sắp xếp theo tổng doanh thu giảm dần
- `outContractList`: Danh sách hợp đồng của 1 đối tác được chọn (mã HĐ, tên KH, ngày ký, tổng vay, tổng đã thanh toán, tổng dư nợ, tổng quá hạn)
- `outContractDetails`: Thông tin chi tiết hợp đồng: thông tin KH, danh sách sản phẩm, danh sách thanh toán theo kỳ

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PartnerDAO | `getPartnerStatistics(Date startDate, Date endDate): List<Partner>` — Lấy danh sách thống kê đối tác kèm tổng hóa đơn, tổng tiền bán hàng, tổng dư nợ trong kỳ, sắp xếp theo doanh thu giảm dần |
| ContractDAO | `getContractsByPartner(int partnerId, Date startDate, Date endDate): List<Contract>` — Lấy danh sách hợp đồng của 1 đối tác trong kỳ thống kê |
| ContractItemDAO | `getItemsByContract(int contractId): List<ContractItem>` — Lấy danh sách sản phẩm trong 1 hợp đồng |
| PaymentScheduleDAO | `getScheduleByContract(int contractId): List<PaymentSchedule>` — Lấy lịch trình thanh toán theo kỳ của 1 hợp đồng |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — Actor, nhân viên xem thống kê đối tác theo doanh số
2. `:StatPartnerFrm` — boundary, giao diện thống kê đối tác
3. `:PartnerDAO` — control, truy vấn dữ liệu thống kê đối tác
4. `:ContractDAO` — control, truy vấn dữ liệu hợp đồng
5. `:ContractItemDAO` — control, truy vấn dữ liệu sản phẩm trong hợp đồng
6. `:PaymentScheduleDAO` — control, truy vấn lịch trình thanh toán
7. `:Database` — database

**Message flow:**
1. Staff → StatPartnerFrm: `selectMenu("Statistics of partners")`
2. StatPartnerFrm → Staff: `showForm(inStartDate, inEndDate, subView)`
3. Staff → StatPartnerFrm: `enter(inStartDate="01/01/2026", inEndDate="31/12/2026"), click(subView)`
4. StatPartnerFrm → PartnerDAO: `getPartnerStatistics(startDate, endDate)`
5. PartnerDAO → Database: `SELECT partnerId, partnerName, address, COUNT(contractId), SUM(totalLoanAmount), SUM(outstanding) FROM ... WHERE signingDate BETWEEN ? AND ? GROUP BY partnerId ORDER BY SUM(totalLoanAmount) DESC`
6. Database → PartnerDAO: `ResultSet`
7. PartnerDAO → StatPartnerFrm: `List<Partner>`
8. StatPartnerFrm → Staff: `show(outPartnerStatTable)`
9. Staff → StatPartnerFrm: `click("FPT Shop")`
10. StatPartnerFrm → ContractDAO: `getContractsByPartner(partnerId, startDate, endDate)`
11. ContractDAO → Database: `SELECT * FROM tblContract WHERE partnerId = ? AND signingDate BETWEEN ? AND ?`
12. Database → ContractDAO: `ResultSet`
13. ContractDAO → StatPartnerFrm: `List<Contract>`
14. StatPartnerFrm → Staff: `show(outContractList)`
15. Staff → StatPartnerFrm: `click("HD001")`
16. StatPartnerFrm → ContractItemDAO: `getItemsByContract(contractId)`
17. ContractItemDAO → Database: `SELECT * FROM tblContractItem WHERE contractId = ?`
18. Database → ContractItemDAO: `ResultSet`
19. ContractItemDAO → StatPartnerFrm: `List<ContractItem>`
20. StatPartnerFrm → PaymentScheduleDAO: `getScheduleByContract(contractId)`
21. PaymentScheduleDAO → Database: `SELECT * FROM tblPaymentSchedule WHERE contractId = ? ORDER BY periodNumber`
22. Database → PaymentScheduleDAO: `ResultSet`
23. PaymentScheduleDAO → StatPartnerFrm: `List<PaymentSchedule>`
24. StatPartnerFrm → Staff: `show(outContractDetails) — thông tin KH, danh sách SP, danh sách thanh toán theo kỳ`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê đối tác theo doanh số trong kỳ 01/01/2026 - 31/12/2026

**Database trước:**

tblPartner:
| partnerId | partnerName | address | phoneNumber |
|-----------|-------------|---------|-------------|
| P001 | FPT Shop | 123 Le Loi, HCM | 0901111111 |
| P002 | TGDD | 456 Tran Hung Dao, HN | 0902222222 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-03-15 | 30000000 | Active |
| HD002 | C002 | P002 | 2026-04-20 | 20000000 | Active |
| HD003 | C003 | P001 | 2026-05-10 | 15000000 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |
| CI003 | HD003 | I003 | 1 | 15000000 | 15000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | status |
|-----------|-----------|-------------|---------|--------|--------|
| PS001 | HD001 | 1 | 2026-04-15 | 7500000 | Paid |
| PS002 | HD001 | 2 | 2026-05-15 | 7500000 | Paid |
| PS003 | HD001 | 3 | 2026-06-15 | 7500000 | Unpaid |
| PS004 | HD001 | 4 | 2026-07-15 | 7500000 | Unpaid |
| PS005 | HD003 | 1 | 2026-06-10 | 3750000 | Paid |
| PS006 | HD003 | 2 | 2026-07-10 | 3750000 | Unpaid |
| PS007 | HD003 | 3 | 2026-08-10 | 3750000 | Unpaid |
| PS008 | HD003 | 4 | 2026-09-10 | 3750000 | Unpaid |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Đăng nhập thành công, hiển thị trang chủ |
| 2 | Chọn "Statistics of partners" — Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút Xem thống kê |
| 3 | Nhập ngày bắt đầu "01/01/2026", ngày kết thúc "31/12/2026", nhấn Xem thống kê — Hiển thị bảng thống kê: FPT Shop (123 Le Loi, 2 hóa đơn, tổng bán 45,000,000đ, dư nợ 22,500,000đ), TGDD (456 Tran Hung Dao, 1 hóa đơn, tổng bán 20,000,000đ, dư nợ 20,000,000đ) — sắp xếp giảm dần theo doanh thu |
| 4 | Click dòng "FPT Shop" — Hiển thị danh sách hợp đồng: HD001 (Nguyen Van A, 15/03/2026, vay 30,000,000đ, đã trả 15,000,000đ, dư nợ 15,000,000đ, quá hạn 0đ), HD003 (Le Thi C, 10/05/2026, vay 15,000,000đ, đã trả 3,750,000đ, dư nợ 11,250,000đ, quá hạn 0đ) |
| 5 | Click dòng "HD001" — Hiển thị chi tiết hợp đồng: thông tin KH Nguyen Van A, SĐT 0901234567; danh sách SP: iPhone 15 (1, 25,000,000đ), AirPods Pro (1, 5,000,000đ); thanh toán: kỳ 1 đã thanh toán, kỳ 2 đã thanh toán, kỳ 3 chưa thanh toán, kỳ 4 chưa thanh toán |

**Database sau:**

Không thay đổi (chức năng chỉ đọc dữ liệu thống kê, không có thao tác ghi).
