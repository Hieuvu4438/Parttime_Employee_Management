# Mon 56 - Installment Loan - Signing a contract

## Câu 1: Scenario

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn Signing a contract |
| 2 | Giao diện: ô nhập tên khách hàng, nút Search |
| 3 | Staff nhập "Nguyen Van A", Search. Hiển thị danh sách KH |
| 4 | Staff click "Nguyen Van A" (hoặc thêm mới nếu chưa có) |
| 5 | Giao diện nhập sản phẩm: Staff nhập "iPhone 15" Search chọn iPhone 15 giá 25,000,000đ số lượng 1 |
| 6 | Staff nhập "AirPods Pro" Search chọn AirPods Pro giá 5,000,000đ số lượng 1 |
| 7 | Staff nhấn Continue |
| 8 | Giao diện nhập kỳ hạn: 12 tháng, lãi suất 2%/tháng |
| 9 | Hệ thống tự động tính bảng trả góp: Tháng 1: 2,750,000đ dư nợ 27,500,000đ / Tháng 2: 2,750,000đ dư nợ 25,000,000đ / ... |
| 10 | Staff xác nhận với KH, nhấn Save. Hệ thống lưu hợp đồng, in hợp đồng |

## Câu 2: Entity Class Diagram

| Entity | Thuộc tính chính |
|--------|-----------------|
| Customer | customerId, customerName, phone, address |
| Partner | partnerId, partnerName, address |
| Item | itemId, itemName, description |
| Contract | contractId, customerId, partnerId, totalAmount, loanTerm, interestRate, createdDate |
| ContractItem | contractItemId, contractId, itemId, quantity, unitPrice |
| PaymentSchedule | scheduleId, contractId, paymentTime, totalAmount, outstandingBalance, status |
| Payment | paymentId, contractId, scheduleId, amount, paymentDate |
| User | userId, username, password, role |

| Quan hệ | Mô tả |
|---------|-------|
| Customer 1 - N Contract | Một khách hàng có nhiều hợp đồng |
| Contract 1 - N ContractItem | Một hợp đồng có nhiều sản phẩm |
| Item 1 - N ContractItem | Một sản phẩm xuất hiện trong nhiều hợp đồng |
| Contract 1 - N PaymentSchedule | Một hợp đồng có nhiều kỳ thanh toán |
| Contract 1 - N Payment | Một hợp đồng có nhiều lần thanh toán |
| Partner 1 - N Contract | Một đối tác có nhiều hợp đồng |

## Câu 3: Thiết kế tĩnh

### View class

**SigningContractFrm**
- Fields: inCustomerName, subSearchCustomer, outCustomerList, subAddNewCustomer, inItemName, subSearchItem, outItemList, inQuantity, inUnitPrice, subAddItem, outItemListTable, subContinue, inLoanTerm, inInterestRate, outPaymentSchedule, subSave

### DAO classes

**CustomerDAO**
- searchByName(keyword): List<Customer>
- addCustomer(customerName, phone, address): int

**ItemDAO**
- searchByName(keyword): List<Item>

**ContractDAO**
- createContract(customerId, partnerId, totalAmount, loanTerm, interestRate): int

**ContractItemDAO**
- addContractItem(contractId, itemId, quantity, unitPrice): int

**PaymentScheduleDAO**
- addSchedules(contractId, loanTerm, totalAmount, interestRate): int

## Câu 4: Sequence Diagram

### Lifelines
- Staff
- SigningContractFrm
- CustomerDAO
- ItemDAO
- ContractDAO
- ContractItemDAO
- PaymentScheduleDAO
- Database

### Message flow
1. Staff -> SigningContractFrm: select menu "Signing a contract"
2. SigningContractFrm -> SigningContractFrm: showSigningForm()
3. Staff -> SigningContractFrm: enter inCustomerName="Nguyen Van A", click subSearchCustomer
4. SigningContractFrm -> CustomerDAO: searchByName("Nguyen Van A")
5. CustomerDAO -> Database: SELECT * FROM tblCustomer WHERE customerName LIKE '%Nguyen Van A%'
6. Database -> CustomerDAO: result set
7. CustomerDAO -> SigningContractFrm: List<Customer>
8. SigningContractFrm -> Staff: show outCustomerList
9. Staff -> SigningContractFrm: select customer "Nguyen Van A"
10. SigningContractFrm -> SigningContractFrm: showItemSearchForm()
11. Staff -> SigningContractFrm: enter inItemName="iPhone 15", click subSearchItem
12. SigningContractFrm -> ItemDAO: searchByName("iPhone 15")
13. ItemDAO -> Database: SELECT * FROM tblItem WHERE itemName LIKE '%iPhone 15%'
14. Database -> ItemDAO: result set
15. ItemDAO -> SigningContractFrm: List<Item>
16. SigningContractFrm -> Staff: show outItemList
17. Staff -> SigningContractFrm: select item, enter inQuantity=1, inUnitPrice=25000000, click subAddItem
18. SigningContractFrm -> SigningContractFrm: addItemToTable(item, quantity, unitPrice)
19. Staff -> SigningContractFrm: enter inItemName="AirPods Pro", click subSearchItem
20. SigningContractFrm -> ItemDAO: searchByName("AirPods Pro")
21. ItemDAO -> Database: SELECT * FROM tblItem WHERE itemName LIKE '%AirPods Pro%'
22. Database -> ItemDAO: result set
23. ItemDAO -> SigningContractFrm: List<Item>
24. SigningContractFrm -> Staff: show outItemList
25. Staff -> SigningContractFrm: select item, enter inQuantity=1, inUnitPrice=5000000, click subAddItem
26. SigningContractFrm -> SigningContractFrm: addItemToTable(item, quantity, unitPrice)
27. Staff -> SigningContractFrm: click subContinue
28. SigningContractFrm -> SigningContractFrm: showLoanTermForm()
29. Staff -> SigningContractFrm: enter inLoanTerm=12, inInterestRate=2
30. SigningContractFrm -> SigningContractFrm: calculatePaymentSchedule(totalAmount=30000000, loanTerm=12, interestRate=2)
31. SigningContractFrm -> Staff: show outPaymentSchedule
32. Staff -> SigningContractFrm: confirm with customer, click subSave
33. SigningContractFrm -> ContractDAO: createContract(customerId, partnerId, 30000000, 12, 2)
34. ContractDAO -> Database: INSERT INTO tblContract(...)
35. Database -> ContractDAO: contractId
36. SigningContractFrm -> ContractItemDAO: addContractItem(contractId, itemId1, 1, 25000000)
37. ContractItemDAO -> Database: INSERT INTO tblContractItem(...)
38. SigningContractFrm -> ContractItemDAO: addContractItem(contractId, itemId2, 1, 5000000)
39. ContractItemDAO -> Database: INSERT INTO tblContractItem(...)
40. SigningContractFrm -> PaymentScheduleDAO: addSchedules(contractId, 12, 30000000, 2)
41. PaymentScheduleDAO -> Database: INSERT INTO tblPaymentSchedule(...) * 12 rows
42. SigningContractFrm -> Staff: show success message, print contract

## Câu 5: Blackbox Testcase

### TC01: Ký hợp đồng trả góp mới cho khách hàng

**Database TRƯỚC khi chạy TC:**

tblCustomer:
| customerId | customerName | phone | address |
|-----------|-------------|-------|---------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi |

tblItem:
| itemId | itemName | description |
|--------|----------|-------------|
| I001 | iPhone 15 | Smartphone |
| I002 | AirPods Pro | Earbuds |

tblContract: (trống)
tblContractItem: (trống)
tblPaymentSchedule: (trống)

**Database SAU khi chạy TC:**

tblCustomer: Không thay đổi

tblContract:
| contractId | customerId | partnerId | totalAmount | loanTerm | interestRate | createdDate |
|-----------|-----------|-----------|-------------|----------|-------------|-------------|
| CT001 | C001 | null | 30000000 | 12 | 2 | 2026-06-08 |

tblContractItem:
| contractItemId | contractId | itemId | quantity | unitPrice |
|----------------|-----------|--------|----------|-----------|
| CI001 | CT001 | I001 | 1 | 25000000 |
| CI002 | CT001 | I002 | 1 | 5000000 |

tblPaymentSchedule:
| scheduleId | contractId | paymentTime | totalAmount | outstandingBalance | status |
|-----------|-----------|-------------|-------------|-------------------|--------|
| PS001 | CT001 | 01/07/2026 | 2750000 | 27500000 | Unpaid |
| PS002 | CT001 | 01/08/2026 | 2750000 | 25000000 | Unpaid |
| PS003 | CT001 | 01/09/2026 | 2750000 | 22500000 | Unpaid |
| ... | ... | ... | ... | ... | ... |
| PS012 | CT001 | 01/06/2027 | 2750000 | 0 | Unpaid |

**Bước kiểm thử:**

| Bước | Hành động | Kết quả mong đợi |
|------|-----------|-----------------|
| 1 | Đăng nhập bằng tài khoản staff hợp lệ | Đăng nhập thành công, hiển thị trang chủ |
| 2 | Chọn menu "Signing a contract" | Hiển thị giao diện tìm kiếm khách hàng với ô nhập tên và nút Search |
| 3 | Nhập "Nguyen Van A" vào ô tên khách hàng, nhấn Search | Hiển thị danh sách khách hàng có tên chứa "Nguyen Van A", bao gồm Nguyễn Văn A |
| 4 | Chọn khách hàng "Nguyen Van A" | Hiển thị giao diện tìm kiếm sản phẩm |
| 5 | Nhập "iPhone 15" vào ô tên sản phẩm, nhấn Search | Hiển thị danh sách sản phẩm chứa "iPhone 15" |
| 6 | Chọn iPhone 15, nhập số lượng = 1, đơn giá = 25,000,000đ, nhấn Thêm | Sản phẩm iPhone 15 được thêm vào bảng danh sách sản phẩm |
| 7 | Nhập "AirPods Pro" vào ô tên sản phẩm, nhấn Search | Hiển thị danh sách sản phẩm chứa "AirPods Pro" |
| 8 | Chọn AirPods Pro, nhập số lượng = 1, đơn giá = 5,000,000đ, nhấn Thêm | Sản phẩm AirPods Pro được thêm vào bảng danh sách sản phẩm. Tổng cộng = 30,000,000đ |
| 9 | Nhấn Continue | Hiển thị giao diện nhập kỳ hạn và lãi suất |
| 10 | Nhập kỳ hạn = 12 tháng, lãi suất = 2%/tháng | Hệ thống hiển thị bảng lịch trả góp 12 kỳ, mỗi kỳ 2,750,000đ, dư nợ giảm dần từ 27,500,000đ xuống 0đ |
| 11 | Xác nhận với khách hàng, nhấn Save | Hệ thống lưu hợp đồng, hiển thị thông báo thành công và in hợp đồng |
