# Subject No. 57 — Installment Loan — Module "Customers paying"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Khách hàng thanh toán hợp đồng trả góp

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho vay trả góp cho phép nhân viên nhận thanh toán từ khách hàng. Nhân viên chọn chức năng nhận thanh toán, nhập mã hợp đồng để tìm kiếm. Hệ thống hiển thị chi tiết hợp đồng, lịch sử thanh toán, tổng dư nợ và số tiền cần thanh toán. Nhân viên thông báo số tiền cho khách hàng, hỏi khách muốn thanh toán bao nhiêu kỳ. Nhân viên chọn kỳ thanh toán, nhập số tiền. Hệ thống hiển thị hóa đơn thanh toán gồm thông tin khách hàng, đại diện công ty, danh sách sản phẩm, tổng tiền thanh toán, tổng dư nợ, số kỳ còn lại. Nhân viên xác nhận, hệ thống lưu thanh toán và in hóa đơn.

### Trích xuất danh từ

| Danh từ | Phân loại |
|---------|-----------|
| Nhân viên (Staff/User) | Actor |
| Khách hàng (Customer) | Entity |
| Đối tác (Partner) | Entity |
| Sản phẩm (Product) | Entity |
| Hợp đồng (Contract) | Entity |
| Chi tiết hợp đồng (ContractItem) | Entity |
| Lịch trả góp (PaymentSchedule) | Entity |
| Thanh toán (Payment) | Entity |
| Mã hợp đồng | Attribute |
| Tổng dư nợ, số tiền cần thanh toán | Attribute |
| Số kỳ còn lại | Attribute |

### Kịch bản chính (Main scenario)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống với username "staff01" và password "123456" |
| 2 | Hệ thống xác thực thành công, hiển thị trang chủ |
| 3 | Staff chọn menu **"Customer paying"** |
| 4 | Hệ thống hiển thị giao diện tìm kiếm hợp đồng với ô nhập mã hợp đồng (`inContractCode`) và nút **Search** (`subSearch`) |
| 5 | Staff nhập mã hợp đồng "HD001" vào ô `inContractCode` |
| 6 | Staff nhấn nút **Search** |
| 7 | Hệ thống truy vấn thông tin hợp đồng HD001, hiển thị chi tiết: thông tin khách hàng (tên, SĐT, địa chỉ), thông tin đại diện công ty, danh sách sản phẩm trong hợp đồng |
| 8 | Hệ thống hiển thị lịch sử thanh toán (`outPaymentHistory`), mỗi dòng: mã thanh toán, ngày thanh toán, số tiền, phương thức |
| 9 | Hệ thống hiển thị tổng dư nợ (`outOutstandingBalance`) và số tiền cần thanh toán (`outPayableAmount`) |
| 10 | Staff thông báo số tiền cần thanh toán cho khách hàng và hỏi khách muốn thanh toán bao nhiêu kỳ |
| 11 | Khách hàng muốn thanh toán 2 kỳ |
| 12 | Staff chọn kỳ thanh toán (kỳ 3 và kỳ 4) trong bảng lịch trả góp (`outPaymentSchedule`), nhập số tiền thanh toán `5,900,000` vào ô `inPaymentAmount` |
| 13 | Staff nhấn nút **Confirm** (`subConfirm`) |
| 14 | Hệ thống hiển thị hóa đơn thanh toán (`outInvoice`): thông tin khách hàng Nguyen Van A, đại diện công ty, danh sách sản phẩm (iPhone 15, AirPods Pro), tổng tiền thanh toán 5,900,000đ, tổng dư nợ còn lại 15,250,000đ, số kỳ còn lại 8 kỳ |
| 15 | Staff xác nhận lại thông tin, nhấn nút **Save** (`subSave`) |
| 16 | Hệ thống lưu thông tin thanh toán vào tblPayment, cập nhật trạng thái tblPaymentSchedule (kỳ 3, 4 chuyển thành "Paid"), cập nhật trạng thái tblContract nếu đã thanh toán hết, in hóa đơn cho khách hàng |

### Kịch bản ngoại lệ (Exception scenario)

| Bước | Diễn biến |
|------|-----------|
| 6a | Staff nhấn Search mà không nhập mã hợp đồng |
| 6a.1 | Hệ thống hiển thị thông báo lỗi "Vui lòng nhập mã hợp đồng" |
| 6b | Mã hợp đồng không tồn tại trong hệ thống |
| 6b.1 | Hệ thống hiển thị thông báo "Không tìm thấy hợp đồng với mã HDxxx" |
| 6c | Hợp đồng đã được thanh toán hết (status = "Completed") |
| 6c.1 | Hệ thống hiển thị thông báo "Hợp đồng đã được thanh toán đầy đủ" |
| 12a | Staff nhập số tiền thanh toán nhỏ hơn số tiền kỳ thanh toán |
| 12a.1 | Hệ thống hiển thị thông báo "Số tiền thanh toán phải lớn hơn hoặc bằng số tiền kỳ thanh toán" |
| 12b | Staff không chọn kỳ thanh toán nào |
| 12b.1 | Hệ thống hiển thị thông báo "Vui lòng chọn ít nhất 1 kỳ thanh toán" |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho vay trả góp. Công ty hợp tác với nhiều đối tác (nhà bán lẻ). Khách hàng mua sản phẩm tại đối tác dưới hình thức trả góp. Mỗi hợp đồng (Contract) liên kết với một khách hàng và một đối tác, ghi nhận ngày ký, tổng tiền vay, kỳ hạn, lãi suất, trạng thái. Hợp đồng chứa nhiều chi tiết sản phẩm (ContractItem), mỗi chi tiết liên kết với một sản phẩm, ghi nhận số lượng, đơn giá, thành tiền. Mỗi hợp đồng có lịch trả góp (PaymentSchedule) gồm nhiều kỳ, mỗi kỳ có ngày đến hạn, số tiền, số dư còn lại, trạng thái. Khách hàng thanh toán từng kỳ qua phiếu thanh toán (Payment). Nhân viên quản lý hệ thống với tài khoản User.

### Trích xuất danh từ và phân loại

| Danh từ | Phân loại | Ghi chú |
|---------|-----------|---------|
| Khách hàng (Customer) | Entity | Đối tượng chính |
| Đối tác (Partner) | Entity | Đối tượng chính |
| Sản phẩm (Product) | Entity | Đối tượng chính |
| Hợp đồng (Contract) | Entity | Đối tượng chính |
| Chi tiết hợp đồng (ContractItem) | Entity | Đối tượng liên kết |
| Lịch trả góp (PaymentSchedule) | Entity | Đối tượng chính |
| Thanh toán (Payment) | Entity | Đối tượng chính |
| Tài khoản (User) | Entity | Đối tượng phụ trợ |
| Mã KH, tên, SĐT, CMND, địa chỉ | Attribute | Thuộc tính Customer |
| Mã đối tác, tên, địa chỉ, SĐT, chi nhánh | Attribute | Thuộc tính Partner |
| Mã SP, tên SP, giá niêm yết, mô tả | Attribute | Thuộc tính Product |
| Mã HĐ, ngày ký, tổng vay, kỳ hạn, lãi suất, trạng thái | Attribute | Thuộc tính Contract |
| Số lượng, đơn giá, thành tiền | Attribute | Thuộc tính ContractItem |
| Số kỳ, ngày đến hạn, số tiền, dư nợ, trạng thái | Attribute | Thuộc tính PaymentSchedule |
| Mã TT, ngày TT, số tiền, phương thức | Attribute | Thuộc tính Payment |

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu quan hệ | Mô tả |
|-----------|----------|---------------|--------|
| Customer | Contract | 1 --- n | Một khách hàng có nhiều hợp đồng |
| Partner | Contract | 1 --- n | Một đối tác có nhiều hợp đồng |
| Contract | ContractItem | 1 --- n | Một hợp đồng có nhiều chi tiết sản phẩm |
| Product | ContractItem | 1 --- n | Một sản phẩm xuất hiện trong nhiều chi tiết hợp đồng |
| Contract | PaymentSchedule | 1 --- n | Một hợp đồng có lịch trả góp nhiều kỳ |
| PaymentSchedule | Payment | 1 --- n | Một kỳ thanh toán có thể có nhiều phiếu thanh toán |

### ASCII Class Diagram

```
+------------------+          +------------------+
|     Customer     |          |     Partner      |
+------------------+          +------------------+
| customerId (PK)  |          | partnerId (PK)   |
| customerName     |          | partnerName      |
| phoneNumber      |          | address          |
| address          |          | phoneNumber      |
| idCard           |          | branch           |
+--------+---------+          +--------+---------+
         |                             |
         | 1                           | 1
         |                             |
         | n                           | n
         +----------+    +-------------+
                    |    |
                    v    v
              +-----+----+-----+
              |   Contract     |
              +----------------+
              | contractId(PK) |
              | customerId(FK) |
              | partnerId(FK)  |
              | signingDate    |
              | totalLoanAmount|
              | loanTerm       |
              | interestRate   |
              | status         |
              +---+--------+---+
                  |        |
            1     |        |     1
                  |        |
            n     |        |     n
    +-------------+--+  +--+------------------+
    | ContractItem   |  | PaymentSchedule     |
    +----------------+  +---------------------+
    | contractItemId |  | scheduleId (PK)     |
    | contractId(FK) |  | contractId (FK)     |
    | productId (FK) |  | periodNumber        |
    | quantity       |  | dueDate             |
    | unitPrice      |  | amount              |
    | amount         |  | outstandingBalance  |
    +--------+-------+  | status              |
             |          +--------+------------+
             |                   |
             | 1                 | 1
             |                   |
             | n                 | n
    +--------+-------+  +--------+------------+
    |    Product     |  |     Payment         |
    +----------------+  +---------------------+
    | productId (PK) |  | paymentId (PK)      |
    | productName    |  | scheduleId (FK)     |
    | unitPrice      |  | paymentDate         |
    | description    |  | amountPaid          |
    +----------------+  | method              |
                        +---------------------+

+------------------+
|       User       |
+------------------+
| userId (PK)      |
| username         |
| password         |
| fullName         |
| role             |
+------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Customer, Partner, Product, Contract, ContractItem, PaymentSchedule, Payment, User
- Bước 3: Tạo các view class box từ giao diện: HomeFrm, SearchContractView, ContractDetailView, InvoiceView
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Payment`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-paymentId: int`, `-amountPaid: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+insertPayment(payment: Payment): boolean`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Customer | <<entity>> | -customerId: int, -customerName: String, -phoneNumber: String, -address: String, -idCard: String | +getCustomerById(customerId: int): Customer |
| Partner | <<entity>> | -partnerId: int, -partnerName: String, -address: String, -phoneNumber: String, -branch: String | |
| Product | <<entity>> | -productId: int, -productName: String, -unitPrice: double, -description: String | |
| Contract | <<entity>> | -contractId: int, -customerId: int, -partnerId: int, -signingDate: Date, -totalLoanAmount: double, -loanTerm: int, -interestRate: double, -status: String | +getContractByCode(code: String): Contract, +updateContractStatus(contractId: int, status: String): boolean |
| ContractItem | <<entity>> | -contractItemId: int, -contractId: int, -productId: int, -quantity: int, -unitPrice: double, -amount: double | +getItemsByContractId(contractId: int): List<ContractItem> |
| PaymentSchedule | <<entity>> | -scheduleId: int, -contractId: int, -periodNumber: int, -dueDate: Date, -amount: double, -outstandingBalance: double, -status: String | +getScheduleByContractId(contractId: int): List<PaymentSchedule>, +getOutstandingBalance(contractId: int): double, +updateScheduleStatus(scheduleId: int, status: String): boolean |
| Payment | <<entity>> | -paymentId: int, -contractId: int, -scheduleId: int, -paymentDate: Date, -amountPaid: double, -method: String, -note: String | +getPaymentsByContractId(contractId: int): List<Payment>, +insertPayment(payment: Payment): boolean |
| User | <<entity>> | -userId: int, -username: String, -password: String, -fullName: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeFrm | subCustomerPayment: JButton (nút chọn Customer paying) |
| SearchContractView | inContractCode: JTextField (ô nhập mã HĐ), subSearch: JButton (nút Search) |
| ContractDetailView | outCustomerInfo: JLabel (thông tin KH), outContractInfo: JLabel (thông tin HĐ), outContractItemList: JTable (SP trong HĐ), outPaymentHistory: JTable (lịch sử TT), outOutstandingBalance: JLabel (tổng dư nợ), outPayableAmount: JLabel (số tiền cần TT), inoutPaymentSchedule: JTable (lịch trả góp + checkbox), inPaymentAmount: JTextField (số tiền TT), subConfirm: JButton (nút Confirm) |
| InvoiceView | outInvoice: JPanel (hóa đơn), subSave: JButton (nút Save) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Contract
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Contract ◆→ ContractItem
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: ContractDetailView ---> ContractDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: PaymentSchedule (1) --- (n) Payment

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Customer | Contract | Association | 1 : n | Một khách hàng có nhiều hợp đồng |
| Partner | Contract | Association | 1 : n | Một đối tác có nhiều hợp đồng |
| Contract | ContractItem | Composition | 1 : n | Hợp đồng chứa nhiều chi tiết SP |
| Product | ContractItem | Association | 1 : n | Một sản phẩm xuất hiện trong nhiều chi tiết HĐ |
| Contract | PaymentSchedule | Composition | 1 : n | Hợp đồng chứa nhiều kỳ thanh toán |
| PaymentSchedule | Payment | Composition | 1 : n | Một kỳ có nhiều phiếu thanh toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition PaymentSchedule ◆→ Payment:
- Kéo class PaymentSchedule vào canvas, kéo class Payment vào bên phải
- Chọn công cụ **Composition**, click vào PaymentSchedule rồi kéo sang Payment
- Kim cương filled nằm ở phía PaymentSchedule (parent)
- Đặt Multiplicity: PaymentSchedule "1", Payment "n"

Ví dụ 2 — Vẽ dependency ContractDetailView ---> ContractDAO:
- Kéo class ContractDetailView (<<boundary>>) và ContractDAO (<<control>>) vào canvas
- Chọn công cụ **Dependency** (đường dashed mũi tên), click vào ContractDetailView kéo sang ContractDAO
- Mũi tên chỉ từ View sang DAO, thể hiện View sử dụng DAO để truy vấn dữ liệu

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Customer paying" → subCustomerPayment

Staff chọn Customer paying → SearchContractView xuất hiện:
  Ô nhập mã hợp đồng → inContractCode
  Nút Search → subSearch

Staff nhập mã HĐ và nhấn Search → ContractDetailView xuất hiện:
  Thông tin khách hàng (tên, SĐT, địa chỉ) → outCustomerInfo
  Thông tin hợp đồng → outContractInfo
  Danh sách sản phẩm trong hợp đồng → outContractItemList
  Lịch sử thanh toán → outPaymentHistory
  Tổng dư nợ → outOutstandingBalance
  Số tiền cần thanh toán → outPayableAmount
  Bảng lịch trả góp (có checkbox chọn kỳ) → inoutPaymentSchedule
  Ô nhập số tiền thanh toán → inPaymentAmount
  Nút Confirm → subConfirm

Staff chọn kỳ và nhấn Confirm → InvoiceView xuất hiện:
  Hóa đơn thanh toán → outInvoice
  Nút Save → subSave

Phân tích phương thức:

Tìm kiếm hợp đồng theo mã:
  Tên: getContractByCode()
  Đầu vào: code (String)
  Đầu ra: Contract
  Gán cho entity class: Contract.

Lấy thông tin khách hàng:
  Tên: getCustomerById()
  Đầu vào: customerId (int)
  Đầu ra: Customer
  Gán cho entity class: Customer.

Lấy danh sách sản phẩm trong hợp đồng:
  Tên: getItemsByContractId()
  Đầu vào: contractId (int)
  Đầu ra: List<ContractItem>
  Gán cho entity class: ContractItem.

Lấy lịch sử thanh toán:
  Tên: getPaymentsByContractId()
  Đầu vào: contractId (int)
  Đầu ra: List<Payment>
  Gán cho entity class: Payment.

Lấy lịch trả góp:
  Tên: getScheduleByContractId()
  Đầu vào: contractId (int)
  Đầu ra: List<PaymentSchedule>
  Gán cho entity class: PaymentSchedule.

Tính tổng dư nợ:
  Tên: getOutstandingBalance()
  Đầu vào: contractId (int)
  Đầu ra: double
  Gán cho entity class: PaymentSchedule.

Lưu phiếu thanh toán:
  Tên: insertPayment()
  Đầu vào: payment (Payment)
  Đầu ra: boolean
  Gán cho entity class: Payment.

Cập nhật trạng thái kỳ thanh toán:
  Tên: updateScheduleStatus()
  Đầu vào: scheduleId (int), status (String)
  Đầu ra: boolean
  Gán cho entity class: PaymentSchedule.

Cập nhật trạng thái hợp đồng:
  Tên: updateContractStatus()
  Đầu vào: contractId (int), status (String)
  Đầu ra: boolean
  Gán cho entity class: Contract.

### Tóm tắt
View classes: HomeFrm, SearchContractView, ContractDetailView, InvoiceView
Phương thức: getContractByCode(), getCustomerById(), getItemsByContractId(), getPaymentsByContractId(), getScheduleByContractId(), getOutstandingBalance(), insertPayment(), updateScheduleStatus(), updateContractStatus()

---

## Câu 3: Thiết kế tĩnh

### View classes

**CustomerPaymentFrm:**
- `inContractCode`: Ô nhập mã hợp đồng tìm kiếm (JTextField)
- `subSearch`: Nút "Search" tìm kiếm hợp đồng (JButton)
- `outCustomerName`: Label hiển thị tên khách hàng (JLabel)
- `outCustomerPhone`: Label hiển thị số điện thoại khách hàng (JLabel)
- `outCustomerAddress`: Label hiển thị địa chỉ khách hàng (JLabel)
- `outContractInfo`: Label hiển thị thông tin hợp đồng (ngày ký, tổng vay, kỳ hạn, lãi suất) (JLabel)
- `outContractItemList`: Bảng danh sách sản phẩm trong hợp đồng (JTable), mỗi dòng: tên SP, số lượng, đơn giá, thành tiền
- `outPaymentHistory`: Bảng lịch sử thanh toán (JTable), mỗi dòng: mã TT, ngày TT, số tiền, phương thức
- `outOutstandingBalance`: Label hiển thị tổng dư nợ (JLabel)
- `outPayableAmount`: Label hiển thị số tiền cần thanh toán (JLabel)
- `outPaymentSchedule`: Bảng lịch trả góp (JTable), mỗi dòng: kỳ, ngày đến hạn, số tiền, trạng thái, checkbox chọn
- `inPaymentAmount`: Ô nhập số tiền thanh toán (JTextField)
- `subConfirm`: Nút "Confirm" hiển thị hóa đơn (JButton)
- `outInvoice`: Panel hiển thị hóa đơn thanh toán (JPanel)
- `subSave`: Nút "Save" xác nhận và lưu thanh toán (JButton)
- `subPrintInvoice`: Nút "Print" in hóa đơn (JButton)

**HomeFrm:**
- `subCustomerPayment`: Nút/menu "Customer paying" để mở giao diện nhận thanh toán

### UI Elements

| UI Element | Loại | Vai trò |
|-----------|------|---------|
| inContractCode | JTextField | Nhập mã hợp đồng tìm kiếm |
| subSearch | JButton | Kích hoạt tìm kiếm hợp đồng |
| outCustomerName | JLabel | Hiển thị tên khách hàng |
| outCustomerPhone | JLabel | Hiển thị SĐT khách hàng |
| outCustomerAddress | JLabel | Hiển thị địa chỉ khách hàng |
| outContractInfo | JLabel | Hiển thị thông tin hợp đồng |
| outContractItemList | JTable + JScrollPane | Hiển thị danh sách SP trong hợp đồng |
| outPaymentHistory | JTable + JScrollPane | Hiển thị lịch sử thanh toán |
| outOutstandingBalance | JLabel | Hiển thị tổng dư nợ |
| outPayableAmount | JLabel | Hiển thị số tiền cần thanh toán |
| outPaymentSchedule | JTable + JScrollPane | Hiển thị lịch trả góp với checkbox chọn kỳ |
| inPaymentAmount | JTextField | Nhập số tiền thanh toán |
| subConfirm | JButton | Hiển thị hóa đơn thanh toán |
| outInvoice | JPanel | Hiển thị hóa đơn |
| subSave | JButton | Xác nhận và lưu thanh toán |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| ContractDAO | `getContractByCode(String code): Contract` | Tìm kiếm hợp đồng theo mã hợp đồng |
| CustomerDAO | `getCustomerById(int customerId): Customer` | Lấy thông tin khách hàng theo mã |
| PartnerDAO | `getPartnerById(int partnerId): Partner` | Lấy thông tin đối tác theo mã |
| ContractItemDAO | `getItemsByContractId(int contractId): List<ContractItem>` | Lấy danh sách sản phẩm trong hợp đồng |
| PaymentScheduleDAO | `getScheduleByContractId(int contractId): List<PaymentSchedule>` | Lấy lịch trả góp theo hợp đồng |
| PaymentScheduleDAO | `getOutstandingBalance(int contractId): double` | Tính tổng dư nợ còn lại của hợp đồng |
| PaymentScheduleDAO | `getPayableAmount(int contractId): double` | Tính số tiền cần thanh toán (các kỳ quá hạn + kỳ hiện tại) |
| PaymentDAO | `getPaymentsByContractId(int contractId): List<Payment>` | Lấy lịch sử thanh toán theo hợp đồng |
| PaymentDAO | `insertPayment(Payment p): boolean` | Lưu phiếu thanh toán mới |
| PaymentScheduleDAO | `updateStatus(int scheduleId, String status): boolean` | Cập nhật trạng thái kỳ thanh toán |
| ContractDAO | `updateStatus(int contractId, String status): boolean` | Cập nhật trạng thái hợp đồng |

### Entity types

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Customer | Entity | customerId (int), customerName (String), phoneNumber (String), address (String), idCard (String) |
| Partner | Entity | partnerId (int), partnerName (String), address (String), phoneNumber (String), branch (String) |
| Product | Entity | productId (int), productName (String), unitPrice (double), description (String) |
| Contract | Entity | contractId (int), customerId (int), partnerId (int), signingDate (Date), totalLoanAmount (double), loanTerm (int), interestRate (double), status (String) |
| ContractItem | Entity | contractItemId (int), contractId (int), productId (int), quantity (int), unitPrice (double), amount (double) |
| PaymentSchedule | Entity | scheduleId (int), contractId (int), periodNumber (int), dueDate (Date), amount (double), outstandingBalance (double), status (String) |
| Payment | Entity | paymentId (int), contractId (int), scheduleId (int), paymentDate (Date), amountPaid (double), method (String), note (String) |
| User | Entity | userId (int), username (String), password (String), fullName (String), role (String) |

### Database schema

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| customerId | INT | PK |
| customerName | VARCHAR(100) | NOT NULL |
| phoneNumber | VARCHAR(15) | |
| address | VARCHAR(200) | |
| idCard | VARCHAR(20) | UNIQUE |

**tblPartner:**
| Column | Type | Constraint |
|--------|------|------------|
| partnerId | INT | PK |
| partnerName | VARCHAR(100) | NOT NULL |
| address | VARCHAR(200) | |
| phoneNumber | VARCHAR(15) | |
| branch | VARCHAR(100) | |

**tblProduct:**
| Column | Type | Constraint |
|--------|------|------------|
| productId | INT | PK |
| productName | VARCHAR(100) | NOT NULL |
| unitPrice | DECIMAL(12,2) | NOT NULL |
| description | VARCHAR(500) | |

**tblContract:**
| Column | Type | Constraint |
|--------|------|------------|
| contractId | INT | PK |
| customerId | INT | FK → tblCustomer |
| partnerId | INT | FK → tblPartner |
| signingDate | DATE | NOT NULL |
| totalLoanAmount | DECIMAL(15,2) | NOT NULL |
| loanTerm | INT | NOT NULL |
| interestRate | DECIMAL(5,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblContractItem:**
| Column | Type | Constraint |
|--------|------|------------|
| contractItemId | INT | PK |
| contractId | INT | FK → tblContract |
| productId | INT | FK → tblProduct |
| quantity | INT | NOT NULL |
| unitPrice | DECIMAL(12,2) | NOT NULL |
| amount | DECIMAL(15,2) | NOT NULL |

**tblPaymentSchedule:**
| Column | Type | Constraint |
|--------|------|------------|
| scheduleId | INT | PK |
| contractId | INT | FK → tblContract |
| periodNumber | INT | NOT NULL |
| dueDate | DATE | NOT NULL |
| amount | DECIMAL(12,2) | NOT NULL |
| outstandingBalance | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblPayment:**
| Column | Type | Constraint |
|--------|------|------------|
| paymentId | INT | PK |
| contractId | INT | FK → tblContract |
| scheduleId | INT | FK → tblPaymentSchedule |
| paymentDate | DATE | NOT NULL |
| amountPaid | DECIMAL(12,2) | NOT NULL |
| method | VARCHAR(20) | |
| note | VARCHAR(200) | |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| userId | INT | PK |
| username | VARCHAR(50) | UNIQUE, NOT NULL |
| password | VARCHAR(100) | NOT NULL |
| fullName | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

### Visual Paradigm Guide — Static Design

Trong Visual Paradigm, tạo Class Diagram với:
1. Tạo 8 entity classes: Customer, Partner, Product, Contract, ContractItem, PaymentSchedule, Payment, User — mỗi class có attributes như trên
2. Tạo 1 view class: CustomerPaymentFrm với tất cả attributes input/output/submit
3. Tạo 5 DAO classes: ContractDAO, CustomerDAO, PartnerDAO, ContractItemDAO, PaymentScheduleDAO, PaymentDAO — mỗi class có phương thức tương ứng
4. Vẽ mối quan hệ: Customer -- Contract (1-n), Partner -- Contract (1-n), Contract -- ContractItem (1-n), Product -- ContractItem (1-n), Contract -- PaymentSchedule (1-n), PaymentSchedule -- Payment (1-n)
5. Thêm dependency mũi tên từ CustomerPaymentFrm → các DAO classes

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

**Tạo Sequence Diagram trong Visual Paradigm:**
1. Tạo 8 lifelines: `:Staff` (actor), `:CustomerPaymentFrm` (boundary), `:ContractDAO` (control), `:CustomerDAO` (control), `:ContractItemDAO` (control), `:PaymentScheduleDAO` (control), `:PaymentDAO` (control), `:Database` (database)
2. Thêm message arrows theo thứ tự bên dưới
3. Sử dụng return arrows (dashed) cho các message trả về
4. Thêm alt fragment cho trường hợp hợp đồng không tìm thấy
5. Thêm loop fragment cho bước cập nhật trạng thái PaymentSchedule

### ASCII Sequence Diagram

```
  Staff        CustomerPaymentFrm   ContractDAO  CustomerDAO  ContractItemDAO  PaymentScheduleDAO  PaymentDAO    Database
    |                  |                  |            |              |                |               |              |
    | selectMenu(      |                  |            |              |                |               |              |
    | "Customer        |                  |            |              |                |               |              |
    |  paying")        |                  |            |              |                |               |              |
    |----------------->|                  |            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    | show(inContractCode, subSearch)     |            |              |                |               |              |
    |<-----------------|                  |            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    | enter("HD001"), click(subSearch)    |            |              |                |               |              |
    |----------------->|                  |            |              |                |               |              |
    |                  | getContractByCode|            |              |                |               |              |
    |                  | ("HD001")        |            |              |                |               |              |
    |                  |----------------->|            |              |                |               |              |
    |                  |                  | SELECT * FROM tblContract WHERE contractId = ?           |              |
    |                  |                  |------------------------------------------------------------------------->|
    |                  |                  | ResultSet   |            |              |                |               |              |
    |                  |                  |<-------------------------------------------------------------------------|
    |                  | Contract         |            |              |                |               |              |
    |                  |<-----------------|            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    |                  | getCustomerById  |            |              |                |               |              |
    |                  | (customerId)     |            |              |                |               |              |
    |                  |-------------------------------->|            |                |               |              |
    |                  |                  |            | SELECT * FROM tblCustomer WHERE customerId = ? |              |
    |                  |                  |            |--------------------------------------------------------->|
    |                  |                  |            | ResultSet   |              |                |               |              |
    |                  |                  |            |<---------------------------------------------------------|
    |                  | Customer         |            |              |                |               |              |
    |                  |<--------------------------------|            |                |               |              |
    |                  |                  |            |              |                |               |              |
    |                  | getItemsByContractId           |              |                |               |              |
    |                  | (contractId)     |            |              |                |               |              |
    |                  |-------------------------------------------->|                |               |              |
    |                  |                  |            |              | SELECT * FROM tblContractItem WHERE contractId = ? |
    |                  |                  |            |              |--------------------------------------------------------->|
    |                  |                  |            |              | ResultSet       |               |              |
    |                  |                  |            |              |<---------------------------------------------------------|
    |                  | List<ContractItem>|           |              |                |               |              |
    |                  |<--------------------------------------------|                |               |              |
    |                  |                  |            |              |                |               |              |
    |                  | getPaymentsByContractId        |              |                |               |              |
    |                  | (contractId)     |            |              |                |               |              |
    |                  |----------------------------------------------------------------|-------------->|              |
    |                  |                  |            |              |                |               | SELECT * FROM tblPayment WHERE contractId = ? |
    |                  |                  |            |              |                |               |------------->|
    |                  |                  |            |              |                |               | ResultSet     |
    |                  |                  |            |              |                |               |<-------------|
    |                  | List<Payment>    |            |              |                |               |              |
    |                  |<----------------------------------------------------------------|-------------|              |
    |                  |                  |            |              |                |               |              |
    |                  | getScheduleByContractId        |              |                |               |              |
    |                  | (contractId)     |            |              |                |               |              |
    |                  |-----------------------------------------------------|------|------->|               |              |
    |                  |                  |            |              |                | SELECT * FROM tblPaymentSchedule WHERE contractId = ? |
    |                  |                  |            |              |                |---------------------------->|
    |                  |                  |            |              |                | ResultSet      |              |
    |                  |                  |            |              |                |<----------------------------|
    |                  | List<PaymentSchedule>          |              |                |               |              |
    |                  |<-----------------------------------------------------|------|--------|               |              |
    |                  |                  |            |              |                |               |              |
    |                  | getOutstandingBalance          |              |                |               |              |
    |                  | (contractId)     |            |              |                |               |              |
    |                  |-----------------------------------------------------|------|------->|               |              |
    |                  |                  |            |              |                | SELECT SUM(...) |              |
    |                  |                  |            |              |                |---------------------------->|
    |                  |                  |            |              |                | double          |              |
    |                  |                  |            |              |                |<----------------------------|
    |                  | double           |            |              |                |               |              |
    |                  |<-----------------------------------------------------|------|--------|               |              |
    |                  |                  |            |              |                |               |              |
    | show(customerName, phone, address, contractInfo, |              |                |               |              |
    |      outContractItemList, outPaymentHistory,     |              |                |               |              |
    |      outOutstandingBalance, outPayableAmount,    |              |                |               |              |
    |      outPaymentSchedule)                        |              |                |               |              |
    |<-----------------|                  |            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    | selectSchedules(PS003, PS004), enterAmount(5900000)             |                |               |              |
    | click(subConfirm)|                  |            |              |                |               |              |
    |----------------->|                  |            |              |                |               |              |
    |                  | calculateInvoice()           |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    | show(outInvoice) |                  |            |              |                |               |              |
    |<-----------------|                  |            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    | click(subSave)   |                  |            |              |                |               |              |
    |----------------->|                  |            |              |                |               |              |
    |                  |                  |            |              |                |               |              |
    |                  | insertPayment(payment)         |              |                |               |              |
    |                  |----------------------------------------------------------------|-------------->|              |
    |                  |                  |            |              |                |               | INSERT INTO tblPayment |
    |                  |                  |            |              |                |               |------------->|
    |                  |                  |            |              |                |               | true          |
    |                  |                  |            |              |                |               |<-------------|
    |                  | true             |            |              |                |               |              |
    |                  |<----------------------------------------------------------------|-------------|              |
    |                  |                  |            |              |                |               |              |
    |                  | [loop: update each selected PaymentSchedule]  |               |              |              |
    |                  | updateStatus(scheduleId, "Paid")|             |                |               |              |
    |                  |-----------------------------------------------------|------|------->|               |              |
    |                  |                  |            |              |                | UPDATE tblPaymentSchedule SET status='Paid' WHERE scheduleId=? |
    |                  |                  |            |              |                |---------------------------->|
    |                  |                  |            |              |                | true           |              |
    |                  |                  |            |              |                |<----------------------------|
    |                  | true             |            |              |                |               |              |
    |                  |<-----------------------------------------------------|------|--------|               |              |
    |                  |                  |            |              |                |               |              |
    | show("Thanh toán thành công"), printInvoice()    |              |                |               |              |
    |<-----------------|                  |            |              |                |               |              |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | CustomerPaymentFrm | `selectMenu("Customer paying")` | Chọn menu nhận thanh toán |
| 2 | CustomerPaymentFrm | Staff | `show(inContractCode, subSearch)` | Hiển thị form tìm hợp đồng |
| 3 | Staff | CustomerPaymentFrm | `enter(inContractCode="HD001")` | Nhập mã hợp đồng |
| 4 | Staff | CustomerPaymentFrm | `click(subSearch)` | Nhấn tìm kiếm |
| 5 | CustomerPaymentFrm | ContractDAO | `getContractByCode("HD001")` | Gọi DAO tìm hợp đồng |
| 6 | ContractDAO | Database | `SELECT * FROM tblContract WHERE contractId = 'HD001'` | Truy vấn SQL |
| 7 | Database | ContractDAO | `ResultSet` | Trả về dữ liệu |
| 8 | ContractDAO | CustomerPaymentFrm | `Contract` | Trả về đối tượng Contract |
| 9 | CustomerPaymentFrm | CustomerDAO | `getCustomerById(customerId)` | Lấy thông tin KH |
| 10 | CustomerDAO | Database | `SELECT * FROM tblCustomer WHERE customerId = ?` | Truy vấn SQL |
| 11 | Database | CustomerDAO | `ResultSet` | Trả về dữ liệu |
| 12 | CustomerDAO | CustomerPaymentFrm | `Customer` | Trả về đối tượng Customer |
| 13 | CustomerPaymentFrm | ContractItemDAO | `getItemsByContractId(contractId)` | Lấy danh sách SP trong HĐ |
| 14 | ContractItemDAO | Database | `SELECT ci.*, p.productName FROM tblContractItem ci JOIN tblProduct p ON ci.productId=p.productId WHERE ci.contractId = ?` | Truy vấn SQL |
| 15 | Database | ContractItemDAO | `ResultSet` | Trả về dữ liệu |
| 16 | ContractItemDAO | CustomerPaymentFrm | `List<ContractItem>` | Trả về danh sách SP |
| 17 | CustomerPaymentFrm | PaymentDAO | `getPaymentsByContractId(contractId)` | Lấy lịch sử thanh toán |
| 18 | PaymentDAO | Database | `SELECT * FROM tblPayment WHERE contractId = ?` | Truy vấn SQL |
| 19 | Database | PaymentDAO | `ResultSet` | Trả về dữ liệu |
| 20 | PaymentDAO | CustomerPaymentFrm | `List<Payment>` | Trả về lịch sử TT |
| 21 | CustomerPaymentFrm | PaymentScheduleDAO | `getScheduleByContractId(contractId)` | Lấy lịch trả góp |
| 22 | PaymentScheduleDAO | Database | `SELECT * FROM tblPaymentSchedule WHERE contractId = ? ORDER BY periodNumber` | Truy vấn SQL |
| 23 | Database | PaymentScheduleDAO | `ResultSet` | Trả về dữ liệu |
| 24 | PaymentScheduleDAO | CustomerPaymentFrm | `List<PaymentSchedule>` | Trả về lịch trả góp |
| 25 | CustomerPaymentFrm | PaymentScheduleDAO | `getOutstandingBalance(contractId)` | Tính tổng dư nợ |
| 26 | PaymentScheduleDAO | Database | `SELECT SUM(amount) FROM tblPaymentSchedule WHERE contractId = ? AND status = 'Unpaid'` | Truy vấn SQL |
| 27 | Database | PaymentScheduleDAO | `double` | Trả về tổng dư nợ |
| 28 | PaymentScheduleDAO | CustomerPaymentFrm | `double` | Trả về tổng dư nợ |
| 29 | CustomerPaymentFrm | Staff | `show(outCustomerName, outCustomerPhone, outCustomerAddress, outContractInfo, outContractItemList, outPaymentHistory, outOutstandingBalance, outPayableAmount, outPaymentSchedule)` | Hiển thị toàn bộ thông tin HĐ |
| 30 | Staff | CustomerPaymentFrm | `selectSchedules([PS003, PS004]), enter(inPaymentAmount=5900000)` | Chọn kỳ thanh toán, nhập tiền |
| 31 | Staff | CustomerPaymentFrm | `click(subConfirm)` | Xác nhận thanh toán |
| 32 | CustomerPaymentFrm | CustomerPaymentFrm | `calculateInvoice()` | Tính toán hóa đơn |
| 33 | CustomerPaymentFrm | Staff | `show(outInvoice)` | Hiển thị hóa đơn |
| 34 | Staff | CustomerPaymentFrm | `click(subSave)` | Lưu thanh toán |
| 35 | CustomerPaymentFrm | PaymentDAO | `insertPayment(payment)` | Lưu phiếu thanh toán |
| 36 | PaymentDAO | Database | `INSERT INTO tblPayment(contractId, scheduleId, paymentDate, amountPaid, method, note)` | Truy vấn SQL |
| 37 | Database | PaymentDAO | `true` | Thành công |
| 38 | PaymentDAO | CustomerPaymentFrm | `true` | Xác nhận lưu |
| 39 | CustomerPaymentFrm | PaymentScheduleDAO | `updateStatus(PS003, "Paid")` | Cập nhật kỳ 3 (loop) |
| 40 | PaymentScheduleDAO | Database | `UPDATE tblPaymentSchedule SET status='Paid' WHERE scheduleId=?` | Truy vấn SQL |
| 41 | Database | PaymentScheduleDAO | `true` | Thành công |
| 42 | PaymentScheduleDAO | CustomerPaymentFrm | `true` | Xác nhận |
| 43 | CustomerPaymentFrm | PaymentScheduleDAO | `updateStatus(PS004, "Paid")` | Cập nhật kỳ 4 (loop) |
| 44 | PaymentScheduleDAO | Database | `UPDATE tblPaymentSchedule SET status='Paid' WHERE scheduleId=?` | Truy vấn SQL |
| 45 | Database | PaymentScheduleDAO | `true` | Thành công |
| 46 | PaymentScheduleDAO | CustomerPaymentFrm | `true` | Xác nhận |
| 47 | CustomerPaymentFrm | Staff | `show("Thanh toán thành công"), printInvoice()` | Thông báo và in hóa đơn |

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán thành công 2 kỳ hạn

**Database trước:**

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi | 012345678901 |

tblPartner:
| partnerId | partnerName | address | phoneNumber | branch |
|-----------|-------------|---------|-------------|--------|
| P001 | FPT Shop | 123 Le Loi, HCM | 0901111111 | Chi nhanh 1 |

tblProduct:
| productId | productName | unitPrice | description |
|-----------|-------------|-----------|-------------|
| I001 | iPhone 15 | 25000000 | iPhone 15 128GB |
| I002 | AirPods Pro | 5000000 | AirPods Pro 2nd gen |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD001 | C001 | P001 | 2026-01-01 | 30000000 | 12 | 1.5 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS001 | HD001 | 1 | 2026-02-01 | 2950000 | 27050000 | Paid |
| PS002 | HD001 | 2 | 2026-03-01 | 2950000 | 24100000 | Paid |
| PS003 | HD001 | 3 | 2026-04-01 | 2950000 | 21150000 | Unpaid |
| PS004 | HD001 | 4 | 2026-05-01 | 2950000 | 18200000 | Unpaid |
| PS005 | HD001 | 5 | 2026-06-01 | 2950000 | 15250000 | Unpaid |
| PS006 | HD001 | 6 | 2026-07-01 | 2950000 | 12300000 | Unpaid |
| PS007 | HD001 | 7 | 2026-08-01 | 2950000 | 9350000 | Unpaid |
| PS008 | HD001 | 8 | 2026-09-01 | 2950000 | 6400000 | Unpaid |
| PS009 | HD001 | 9 | 2026-10-01 | 2950000 | 3450000 | Unpaid |
| PS010 | HD001 | 10 | 2026-11-01 | 2950000 | 500000 | Unpaid |
| PS011 | HD001 | 11 | 2026-12-01 | 500000 | 0 | Unpaid |
| PS012 | HD001 | 12 | 2027-01-01 | 0 | 0 | Unpaid |

tblPayment:
| paymentId | contractId | scheduleId | paymentDate | amountPaid | method | note |
|-----------|-----------|-----------|-------------|------------|--------|------|
| PAY001 | HD001 | PS001 | 2026-02-01 | 2950000 | Cash | Thanh toan ky 1 |
| PAY002 | HD001 | PS002 | 2026-03-01 | 2950000 | Cash | Thanh toan ky 2 |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, hiển thị trang chủ |
| 2 | Staff chọn "Customer paying" — Hiển thị giao diện tìm kiếm hợp đồng với ô nhập mã hợp đồng, nút Search |
| 3 | Staff nhập mã hợp đồng "HD001", nhấn Search |
| 4 | Hệ thống hiển thị chi tiết hợp đồng: khách hàng Nguyen Van A (0901234567, Ha Noi), đại diện FPT Shop, sản phẩm iPhone 15 (25,000,000đ), AirPods Pro (5,000,000đ), tổng vay 30,000,000đ |
| 5 | Hệ thống hiển thị lịch sử thanh toán: PAY001 (01/02/2026, 2,950,000đ, Cash), PAY002 (01/03/2026, 2,950,000đ, Cash) |
| 6 | Hệ thống hiển thị tổng dư nợ: 24,100,000đ (10 kỳ còn lại), số tiền cần thanh toán: 5,900,000đ (kỳ 3 + kỳ 4) |
| 7 | Staff thông báo cho khách hàng, khách hàng muốn thanh toán 2 kỳ. Staff chọn kỳ 3 và kỳ 4, nhập số tiền 5,900,000, nhấn Confirm |
| 8 | Hệ thống hiển thị hóa đơn: KH Nguyen Van A, đại diện FPT Shop, iPhone 15 + AirPods Pro, tổng thanh toán 5,900,000đ, dư nợ còn 15,250,000đ, còn 8 kỳ |
| 9 | Staff nhấn Save — Hệ thống lưu thanh toán, cập nhật trạng thái kỳ 3, 4 thành "Paid", in hóa đơn |

**Database sau:**

tblCustomer: Không thay đổi

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD001 | C001 | P001 | 2026-01-01 | 30000000 | 12 | 1.5 | Active |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS001 | HD001 | 1 | 2026-02-01 | 2950000 | 27050000 | Paid |
| PS002 | HD001 | 2 | 2026-03-01 | 2950000 | 24100000 | Paid |
| PS003 | HD001 | 3 | 2026-04-01 | 2950000 | 21150000 | **Paid** |
| PS004 | HD001 | 4 | 2026-05-01 | 2950000 | 18200000 | **Paid** |
| PS005 | HD001 | 5 | 2026-06-01 | 2950000 | 15250000 | Unpaid |
| PS006 | HD001 | 6 | 2026-07-01 | 2950000 | 12300000 | Unpaid |
| PS007 | HD001 | 7 | 2026-08-01 | 2950000 | 9350000 | Unpaid |
| PS008 | HD001 | 8 | 2026-09-01 | 2950000 | 6400000 | Unpaid |
| PS009 | HD001 | 9 | 2026-10-01 | 2950000 | 3450000 | Unpaid |
| PS010 | HD001 | 10 | 2026-11-01 | 2950000 | 500000 | Unpaid |
| PS011 | HD001 | 11 | 2026-12-01 | 500000 | 0 | Unpaid |
| PS012 | HD001 | 12 | 2027-01-01 | 0 | 0 | Unpaid |

tblPayment:
| paymentId | contractId | scheduleId | paymentDate | amountPaid | method | note |
|-----------|-----------|-----------|-------------|------------|--------|------|
| PAY001 | HD001 | PS001 | 2026-02-01 | 2950000 | Cash | Thanh toan ky 1 |
| PAY002 | HD001 | PS002 | 2026-03-01 | 2950000 | Cash | Thanh toan ky 2 |
| **PAY003** | **HD001** | **PS003** | **2026-06-08** | **2950000** | **Cash** | **Thanh toan ky 3** |
| **PAY004** | **HD001** | **PS004** | **2026-06-08** | **2950000** | **Cash** | **Thanh toan ky 4** |

---

### TC02: Thanh toán toàn bộ số dư còn lại (tất cả các kỳ Unpaid)

**Database trước:**

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C002 | Tran Thi B | 0912345678 | HCM | 098765432101 |

tblPartner:
| partnerId | partnerName | address | phoneNumber | branch |
|-----------|-------------|---------|-------------|--------|
| P001 | FPT Shop | 123 Le Loi, HCM | 0901111111 | Chi nhanh 1 |

tblProduct:
| productId | productName | unitPrice | description |
|-----------|-------------|-----------|-------------|
| I003 | Samsung Galaxy S24 | 20000000 | Samsung Galaxy S24 256GB |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD002 | C002 | P001 | 2026-01-15 | 20000000 | 6 | 2.0 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI003 | HD002 | I003 | 1 | 20000000 | 20000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS013 | HD002 | 1 | 2026-02-15 | 3733333 | 16266667 | Paid |
| PS014 | HD002 | 2 | 2026-03-15 | 3733333 | 12533334 | Paid |
| PS015 | HD002 | 3 | 2026-04-15 | 3733333 | 8800001 | Unpaid |
| PS016 | HD002 | 4 | 2026-05-15 | 3733333 | 5066668 | Unpaid |
| PS017 | HD002 | 5 | 2026-06-15 | 3733333 | 1333335 | Unpaid |
| PS018 | HD002 | 6 | 2026-07-15 | 1333335 | 0 | Unpaid |

tblPayment:
| paymentId | contractId | scheduleId | paymentDate | amountPaid | method | note |
|-----------|-----------|-----------|-------------|------------|--------|------|
| PAY005 | HD002 | PS013 | 2026-02-15 | 3733333 | Cash | Thanh toan ky 1 |
| PAY006 | HD002 | PS014 | 2026-03-15 | 3733333 | Cash | Thanh toan ky 2 |

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công |
| 2 | Staff chọn "Customer paying" — Hiển thị giao diện tìm kiếm hợp đồng |
| 3 | Staff nhập mã hợp đồng "HD002", nhấn Search |
| 4 | Hệ thống hiển thị chi tiết: KH Tran Thi B (0912345678, HCM), đại diện FPT Shop, SP Samsung Galaxy S24, tổng vay 20,000,000đ |
| 5 | Hệ thống hiển thị lịch sử thanh toán: PAY005 (15/02, 3,733,333đ), PAY006 (15/03, 3,733,333đ). Tổng dư nợ: 12,533,334đ (4 kỳ còn lại) |
| 6 | Staff chọn tất cả 4 kỳ Unpaid (PS015-PS018), nhập số tiền 12,533,334, nhấn Confirm |
| 7 | Hệ thống hiển thị hóa đơn: KH Tran Thi B, Samsung Galaxy S24, tổng thanh toán 12,533,334đ, dư nợ 0đ, kỳ còn lại 0 |
| 8 | Staff nhấn Save — Hệ thống lưu 4 phiếu thanh toán, cập nhật tất kỳ thành "Paid", cập nhật hợp đồng "Completed", in hóa đơn |

**Database sau:**

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD002 | C002 | P001 | 2026-01-15 | 20000000 | 6 | 2.0 | **Completed** |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS013 | HD002 | 1 | 2026-02-15 | 3733333 | 16266667 | Paid |
| PS014 | HD002 | 2 | 2026-03-15 | 3733333 | 12533334 | Paid |
| PS015 | HD002 | 3 | 2026-04-15 | 3733333 | 8800001 | **Paid** |
| PS016 | HD002 | 4 | 2026-05-15 | 3733333 | 5066668 | **Paid** |
| PS017 | HD002 | 5 | 2026-06-15 | 3733333 | 1333335 | **Paid** |
| PS018 | HD002 | 6 | 2026-07-15 | 1333335 | 0 | **Paid** |

tblPayment:
| paymentId | contractId | scheduleId | paymentDate | amountPaid | method | note |
|-----------|-----------|-----------|-------------|------------|--------|------|
| PAY005 | HD002 | PS013 | 2026-02-15 | 3733333 | Cash | Thanh toan ky 1 |
| PAY006 | HD002 | PS014 | 2026-03-15 | 3733333 | Cash | Thanh toan ky 2 |
| **PAY007** | **HD002** | **PS015** | **2026-06-08** | **3733333** | **Cash** | **Thanh toan ky 3** |
| **PAY008** | **HD002** | **PS016** | **2026-06-08** | **3733333** | **Cash** | **Thanh toan ky 4** |
| **PAY009** | **HD002** | **PS017** | **2026-06-08** | **3733333** | **Cash** | **Thanh toan ky 5** |
| **PAY010** | **HD002** | **PS018** | **2026-06-08** | **1333335** | **Cash** | **Thanh toan ky 6** |
