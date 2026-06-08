# Mon 57 - Installment Loan - Customers paying

## Câu 1: Scenario

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn Customers paying |
| 2 | Giao diện: ô nhập mã hợp đồng, nút Search |
| 3 | Staff nhập "HD001", Search |
| 4 | Hệ thống hiển thị chi tiết hợp đồng: Khách hàng Nguyen Van A / Sản phẩm iPhone 15 (25,000,000đ) AirPods Pro (5,000,000đ) / Tổng 30,000,000đ / Đã thanh toán 2,750,000đ (1 kỳ) / Dư nợ còn lại 27,250,000đ / Kỳ thanh toán tiếp theo 01/08/2026 số tiền 2,750,000đ |
| 5 | Staff thông báo KH số tiền cần trả: 2,750,000đ |
| 6 | KH muốn trả 2 kỳ: Staff chọn kỳ 01/08 + 01/09, nhập số tiền 5,500,000đ |
| 7 | Hệ thống hiển thị hóa đơn: thông tin KH, danh sách sản phẩm, tổng thanh toán 5,500,000đ, dư nợ còn lại 21,750,000đ, các kỳ còn lại |
| 8 | Staff xác nhận, nhấn Save. Hệ thống lưu Payment, cập nhật PaymentSchedule |

## Câu 2: Entity Class Diagram

| Entity | Thuộc tính chính |
|--------|-----------------|
| Customer | customerId, customerName, phone, address |
| Partner | partnerId, partnerName, address |
| Item | itemId, itemName, description |
| Contract | contractId, customerId, partnerId, totalAmount, loanTerm, interestRate, createdDate |
| ContractItem | contractItemId, contractId, itemId, quantity, unitPrice |
| PaymentSchedule | scheduleId, contractId, paymentTime, totalAmount, outstandingBalance, status |
| Payment | paymentId, contractId, scheduleId, amount, paymentDate, method |
| User | userId, username, password, role |

| Quan hệ | Mô tả |
|---------|-------|
| Customer 1 - N Contract | Một khách hàng có nhiều hợp đồng |
| Contract 1 - N ContractItem | Một hợp đồng có nhiều sản phẩm |
| Item 1 - N ContractItem | Một sản phẩm xuất hiện trong nhiều hợp đồng |
| Contract 1 - N PaymentSchedule | Một hợp đồng có nhiều kỳ thanh toán |
| Contract 1 - N Payment | Một hợp đồng có nhiều lần thanh toán |
| PaymentSchedule 1 - N Payment | Một kỳ thanh toán có thể có nhiều lần thanh toán |
| Partner 1 - N Contract | Một đối tác có nhiều hợp đồng |

## Câu 3: Thiết kế tĩnh

### View class

**CustomerPayLoanFrm**
- Fields: inContractCode, subSearch, outContractDetails, outPaymentHistory, outOutstandingBalance, outPayableAmount, inInstallmentCount, inPaymentAmount, outInvoicePreview, subConfirm

### DAO classes

**ContractDAO**
- getContractByCode(contractCode): Contract

**PaymentScheduleDAO**
- getScheduleByContract(contractId): List<PaymentSchedule>
- updateScheduleStatus(scheduleId, status): int

**PaymentDAO**
- createPayment(contractId, scheduleId, amount, paymentDate, method): int

## Câu 4: Sequence Diagram

### Lifelines
- Staff
- CustomerPayLoanFrm
- ContractDAO
- PaymentScheduleDAO
- PaymentDAO
- Database

### Message flow
1. Staff -> CustomerPayLoanFrm: select menu "Customers paying"
2. CustomerPayLoanFrm -> CustomerPayLoanFrm: showPaymentForm()
3. Staff -> CustomerPayLoanFrm: enter inContractCode="HD001", click subSearch
4. CustomerPayLoanFrm -> ContractDAO: getContractByCode("HD001")
5. ContractDAO -> Database: SELECT * FROM tblContract c JOIN tblCustomer cu ON c.customerId=cu.customerId WHERE c.contractId=?
6. Database -> ContractDAO: contract details
7. ContractDAO -> CustomerPayLoanFrm: Contract
8. CustomerPayLoanFrm -> PaymentScheduleDAO: getScheduleByContract("HD001")
9. PaymentScheduleDAO -> Database: SELECT * FROM tblPaymentSchedule WHERE contractId=? ORDER BY paymentTime
10. Database -> PaymentScheduleDAO: schedule list
11. PaymentScheduleDAO -> CustomerPayLoanFrm: List<PaymentSchedule>
12. CustomerPayLoanFrm -> Staff: show outContractDetails, outPaymentHistory, outOutstandingBalance, outPayableAmount
13. Staff -> CustomerPayLoanFrm: select scheduleId=PS002 (01/08), scheduleId=PS003 (01/09), enter inPaymentAmount=5500000
14. CustomerPayLoanFrm -> CustomerPayLoanFrm: calculateInvoice(selectedSchedules, paymentAmount=5500000)
15. CustomerPayLoanFrm -> Staff: show outInvoicePreview (customer info, items, total payment=5,500,000đ, outstanding=21,750,000đ, remaining schedules)
16. Staff -> CustomerPayLoanFrm: confirm with customer, click subConfirm
17. CustomerPayLoanFrm -> PaymentDAO: createPayment("HD001", "PS002", 2750000, "2026-06-08", "cash")
18. PaymentDAO -> Database: INSERT INTO tblPayment(contractId, scheduleId, amount, paymentDate, method) VALUES(...)
19. Database -> PaymentDAO: paymentId
20. CustomerPayLoanFrm -> PaymentScheduleDAO: updateScheduleStatus("PS002", "Paid")
21. PaymentScheduleDAO -> Database: UPDATE tblPaymentSchedule SET status='Paid' WHERE scheduleId=?
22. CustomerPayLoanFrm -> PaymentDAO: createPayment("HD001", "PS003", 2750000, "2026-06-08", "cash")
23. PaymentDAO -> Database: INSERT INTO tblPayment(contractId, scheduleId, amount, paymentDate, method) VALUES(...)
24. Database -> PaymentDAO: paymentId
25. CustomerPayLoanFrm -> PaymentScheduleDAO: updateScheduleStatus("PS003", "Paid")
26. PaymentScheduleDAO -> Database: UPDATE tblPaymentSchedule SET status='Paid' WHERE scheduleId=?
27. CustomerPayLoanFrm -> Staff: show success message, print invoice

## Câu 5: Blackbox Testcase

### TC01: Khách hàng trả 2 kỳ thanh toán

**Database TRƯỚC khi chạy TC:**

tblContract:
| contractId | customerId | partnerId | totalAmount | loanTerm | interestRate | createdDate |
|-----------|-----------|-----------|-------------|----------|-------------|-------------|
| HD001 | C001 | null | 30000000 | 12 | 2 | 2026-06-01 |

tblContractItem:
| contractItemId | contractId | itemId | quantity | unitPrice |
|----------------|-----------|--------|----------|-----------|
| CI001 | HD001 | I001 | 1 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 |

tblPaymentSchedule:
| scheduleId | contractId | paymentTime | totalAmount | outstandingBalance | status |
|-----------|-----------|-------------|-------------|-------------------|--------|
| PS001 | HD001 | 01/07/2026 | 2750000 | 27500000 | Paid |
| PS002 | HD001 | 01/08/2026 | 2750000 | 25000000 | Unpaid |
| PS003 | HD001 | 01/09/2026 | 2750000 | 22500000 | Unpaid |
| PS004 | HD001 | 01/10/2026 | 2750000 | 20000000 | Unpaid |

tblPayment:
| paymentId | contractId | scheduleId | amount | paymentDate | method |
|-----------|-----------|-----------|--------|-------------|--------|
| PM001 | HD001 | PS001 | 2750000 | 2026-07-01 | cash |

**Database SAU khi chạy TC:**

tblContract: Không thay đổi
tblContractItem: Không thay đổi

tblPaymentSchedule:
| scheduleId | contractId | paymentTime | totalAmount | outstandingBalance | status |
|-----------|-----------|-------------|-------------|-------------------|--------|
| PS001 | HD001 | 01/07/2026 | 2750000 | 27500000 | Paid |
| PS002 | HD001 | 01/08/2026 | 2750000 | 25000000 | **Paid** |
| PS003 | HD001 | 01/09/2026 | 2750000 | 22500000 | **Paid** |
| PS004 | HD001 | 01/10/2026 | 2750000 | 20000000 | Unpaid |

tblPayment:
| paymentId | contractId | scheduleId | amount | paymentDate | method |
|-----------|-----------|-----------|--------|-------------|--------|
| PM001 | HD001 | PS001 | 2750000 | 2026-07-01 | cash |
| **PM002** | **HD001** | **PS002** | **2750000** | **2026-06-08** | **cash** |
| **PM003** | **HD001** | **PS003** | **2750000** | **2026-06-08** | **cash** |

**Bước kiểm thử:**

| Bước | Hành động | Kết quả mong đợi |
|------|-----------|-----------------|
| 1 | Đăng nhập bằng tài khoản staff hợp lệ | Đăng nhập thành công, hiển thị trang chủ |
| 2 | Chọn menu "Customers paying" | Hiển thị giao diện tìm kiếm hợp đồng với ô nhập mã hợp đồng và nút Search |
| 3 | Nhập "HD001" vào ô mã hợp đồng, nhấn Search | Hiển thị chi tiết hợp đồng: khách hàng Nguyen Van A, sản phẩm iPhone 15 (25,000,000đ) và AirPods Pro (5,000,000đ), tổng 30,000,000đ, đã thanh toán 2,750,000đ (1 kỳ), dư nợ còn lại 27,250,000đ |
| 4 | Thông báo KH số tiền cần trả, KH muốn trả 2 kỳ | Staff chọn kỳ PS002 (01/08/2026) và PS003 (01/09/2026) |
| 5 | Nhập số tiền thanh toán = 5,500,000đ | Hiển thị hóa đơn: thông tin KH, danh sách sản phẩm, tổng thanh toán 5,500,000đ, dư nợ còn lại 21,750,000đ, các kỳ còn lại từ PS004 trở đi |
| 6 | Xác nhận với KH, nhấn Save | Hệ thống lưu 2 bản ghi Payment (PM002, PM003), cập nhật trạng thái PS002 và PS003 thành "Paid", hiển thị thông báo thành công và in hóa đơn |
