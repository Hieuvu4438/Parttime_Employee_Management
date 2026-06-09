# Subject No. 56 — Installment Loan — Module "Signing a contract"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Ký hợp đồng mới

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho vay trả góp cho phép nhân viên ký hợp đồng mới cho khách hàng mua hàng trả góp. Công ty hợp tác với nhiều đối tác (nhà bán lẻ). Khách hàng mua sản phẩm tại đối tác, công ty đứng ra cho vay trả góp. Hợp đồng chứa thông tin đại diện công ty, thông tin khách hàng, ngày ký, danh sách sản phẩm. Mỗi sản phẩm có giá niêm yết, công ty được chiết khấu từ đối tác, và tính lãi suất cho khách hàng. Khách hàng trả góp hàng tháng. Nếu trả trễ, số dư nợ được tính vào gốc. Công ty thanh toán cho đối tác theo từng hợp đồng.

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
| Kỳ hạn vay, lãi suất | Attribute |
| Số lượng, đơn giá, thành tiền | Attribute |
| Thời gian thanh toán, tổng số tiền, số dư còn lại | Attribute |

### Kịch bản chính (Main scenario)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống với username "staff01" và password "123456" |
| 2 | Hệ thống xác thực thành công, hiển thị trang chủ |
| 3 | Staff chọn menu **"Sign new contract"** |
| 4 | Hệ thống hiển thị giao diện tìm kiếm khách hàng với ô nhập tên (`inCustomerName`) và nút **Search** (`subSearchCustomer`) |
| 5 | Staff nhập tên khách hàng "Nguyen Van A" vào ô `inCustomerName` |
| 6 | Staff nhấn nút **Search** |
| 7 | Hệ thống tìm kiếm khách hàng theo tên, hiển thị danh sách khách hàng phù hợp (`outCustomerList`), mỗi dòng: mã KH, tên, SĐT, CMND |
| 8 | Staff click chọn khách hàng **Nguyen Van A** (mã C001) trong danh sách |
| 9 | Hệ thống chuyển sang giao diện tìm kiếm sản phẩm với ô nhập tên sản phẩm (`inProductName`) và nút **Search** (`subSearchProduct`) |
| 10 | Staff nhập tên sản phẩm "iPhone 15" vào ô `inProductName`, nhấn **Search** |
| 11 | Hệ thống hiển thị danh sách sản phẩm phù hợp (`outProductList`), mỗi dòng: mã SP, tên, giá niêm yết |
| 12 | Staff chọn sản phẩm "iPhone 15", nhập số lượng `1` vào ô `inQuantity` và đơn giá `25,000,000` vào ô `inUnitPrice` |
| 13 | Staff nhấn nút **Add** (`subAdd`), sản phẩm được thêm vào danh sách chi tiết hợp đồng (`outContractItemList`) |
| 14 | Staff lặp lại bước 10-13 cho sản phẩm "AirPods Pro", số lượng 1, đơn giá 5,000,000 |
| 15 | Staff nhấn nút **Continue** (`subContinue`) |
| 16 | Hệ thống chuyển sang giao diện nhập kỳ hạn và lãi suất với ô nhập số tháng (`inLoanTerm`) và lãi suất (`inInterestRate`) |
| 17 | Staff nhập kỳ hạn `12` tháng, lãi suất `1.5%`/tháng |
| 18 | Hệ thống tự động tính toán lịch trả góp, hiển thị bảng (`outPaymentSchedule`), mỗi dòng: kỳ hạn, thời gian thanh toán, số tiền phải trả, số dư còn lại |
| 19 | Staff kiểm tra thông tin, nhấn nút **Confirm** (`subConfirm`) |
| 20 | Hệ thống lưu hợp đồng vào tblContract, tblContractItem, tblPaymentSchedule, hiển thị thông báo "Ký hợp đồng thành công" và in hợp đồng |

### Kịch bản ngoại lệ (Exception scenario)

| Bước | Diễn biến |
|------|-----------|
| 6a | Staff nhấn Search mà không nhập tên khách hàng |
| 6a.1 | Hệ thống hiển thị danh sách tất cả khách hàng |
| 7a | Không tìm thấy khách hàng nào |
| 7a.1 | Hệ thống hiển thị thông báo "Không tìm thấy khách hàng". Staff nhấn nút "Add new customer" để nhập thông tin khách hàng mới |
| 10a | Không tìm thấy sản phẩm nào |
| 10a.1 | Hệ thống hiển thị thông báo "Không tìm thấy sản phẩm" |
| 15a | Danh sách sản phẩm trống (chưa thêm sản phẩm nào) |
| 15a.1 | Hệ thống hiển thị thông báo "Vui lòng thêm ít nhất 1 sản phẩm" |
| 17a | Staff nhập kỳ hạn hoặc lãi suất không hợp lệ (nhỏ hơn hoặc bằng 0) |
| 17a.1 | Hệ thống hiển thị thông báo lỗi "Kỳ hạn và lãi suất phải lớn hơn 0" |

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

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Sign new contract" → subSignContract

Staff chọn Sign new contract → SearchCustomerView xuất hiện:
  Ô nhập tên khách hàng → inCustomerName
  Nút Search → subSearchCustomer
  Danh sách khách hàng (click được) → outsubListCustomer

Staff chọn khách hàng → SearchProductView xuất hiện:
  Ô nhập tên sản phẩm → inProductName
  Nút Search → subSearchProduct
  Danh sách sản phẩm (click được) → outsubListProduct
  Ô nhập số lượng → inQuantity
  Ô nhập đơn giá → inUnitPrice
  Nút Add → subAdd
  Danh sách sản phẩm đã thêm vào hợp đồng → outContractItemList
  Nút Continue → subContinue

Staff nhấn Continue → LoanTermView xuất hiện:
  Ô nhập kỳ hạn (tháng) → inLoanTerm
  Ô nhập lãi suất (%/tháng) → inInterestRate
  Bảng lịch trả góp tự động tính → outPaymentSchedule
  Nút Confirm → subConfirm

Phân tích phương thức:

Tìm kiếm khách hàng theo tên:
  Tên: searchCustomer()
  Đầu vào: keyword (String)
  Đầu ra: List<Customer>
  Gán cho entity class: Customer.

Tìm kiếm sản phẩm theo tên:
  Tên: searchProduct()
  Đầu vào: keyword (String)
  Đầu ra: List<Product>
  Gán cho entity class: Product.

Lưu hợp đồng mới:
  Tên: insertContract()
  Đầu vào: contract (Contract)
  Đầu ra: int (contractId)
  Gán cho entity class: Contract.

Lưu chi tiết hợp đồng:
  Tên: insertContractItem()
  Đầu vào: contractItem (ContractItem)
  Đầu ra: boolean
  Gán cho entity class: ContractItem.

Lưu lịch trả góp:
  Tên: insertPaymentSchedule()
  Đầu vào: paymentSchedule (PaymentSchedule)
  Đầu ra: boolean
  Gán cho entity class: PaymentSchedule.

### Tóm tắt
View classes: HomeFrm, SearchCustomerView, SearchProductView, LoanTermView
Phương thức: searchCustomer(), searchProduct(), insertContract(), insertContractItem(), insertPaymentSchedule()

---

## Câu 3: Thiết kế tĩnh

### View classes

**SignContractFrm:**
- `inCustomerName`: Ô nhập tên khách hàng tìm kiếm (JTextField)
- `subSearchCustomer`: Nút "Search" tìm kiếm khách hàng (JButton)
- `outCustomerList`: Danh sách khách hàng kết quả tìm kiếm (JTable), click được, mỗi dòng: mã KH, tên, SĐT, CMND
- `inProductName`: Ô nhập tên sản phẩm tìm kiếm (JTextField)
- `subSearchProduct`: Nút "Search" tìm kiếm sản phẩm (JButton)
- `outProductList`: Danh sách sản phẩm kết quả tìm kiếm (JTable), click được, mỗi dòng: mã SP, tên, giá niêm yết
- `inQuantity`: Ô nhập số lượng sản phẩm (JSpinner)
- `inUnitPrice`: Ô nhập đơn giá (JTextField)
- `subAdd`: Nút "Add" thêm sản phẩm vào danh sách hợp đồng (JButton)
- `outContractItemList`: Danh sách sản phẩm đã chọn trong hợp đồng (JTable), mỗi dòng: mã SP, tên, số lượng, đơn giá, thành tiền
- `subContinue`: Nút "Continue" chuyển sang bước nhập kỳ hạn (JButton)
- `inLoanTerm`: Ô nhập kỳ hạn vay theo tháng (JSpinner)
- `inInterestRate`: Ô nhập lãi suất %/tháng (JTextField)
- `outPaymentSchedule`: Bảng lịch trả góp (JTable), mỗi dòng: kỳ, thời gian thanh toán, số tiền, số dư còn lại
- `subConfirm`: Nút "Confirm" xác nhận và lưu hợp đồng (JButton)
- `subPrintContract`: Nút "Print" in hợp đồng (JButton)

**HomeFrm:**
- `subSignContract`: Nút/menu "Sign new contract" để mở giao diện ký hợp đồng

### UI Elements

| UI Element | Loại | Vai trò |
|-----------|------|---------|
| inCustomerName | JTextField | Nhập tên khách hàng tìm kiếm |
| subSearchCustomer | JButton | Kích hoạt tìm kiếm khách hàng |
| outCustomerList | JTable + JScrollPane | Hiển thị danh sách KH tìm được (click chọn) |
| inProductName | JTextField | Nhập tên sản phẩm tìm kiếm |
| subSearchProduct | JButton | Kích hoạt tìm kiếm sản phẩm |
| outProductList | JTable + JScrollPane | Hiển thị danh sách SP tìm được (click chọn) |
| inQuantity | JSpinner | Nhập số lượng sản phẩm |
| inUnitPrice | JTextField | Nhập đơn giá sản phẩm |
| subAdd | JButton | Thêm sản phẩm vào danh sách hợp đồng |
| outContractItemList | JTable + JScrollPane | Hiển thị danh sách SP trong hợp đồng |
| subContinue | JButton | Chuyển sang bước nhập kỳ hạn lãi suất |
| inLoanTerm | JSpinner | Nhập kỳ hạn vay (tháng) |
| inInterestRate | JTextField | Nhập lãi suất (%/tháng) |
| outPaymentSchedule | JTable + JScrollPane | Hiển thị lịch trả góp tự động tính |
| subConfirm | JButton | Xác nhận và lưu hợp đồng |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| CustomerDAO | `searchByName(String keyword): List<Customer>` | Tìm kiếm khách hàng theo tên, trả về danh sách KH phù hợp |
| CustomerDAO | `insertCustomer(Customer c): boolean` | Thêm khách hàng mới vào hệ thống |
| ProductDAO | `searchByName(String keyword): List<Product>` | Tìm kiếm sản phẩm theo tên, trả về danh sách SP phù hợp |
| ContractDAO | `insertContract(Contract c): int` | Lưu hợp đồng mới, trả về contractId vừa tạo |
| ContractItemDAO | `insertContractItem(ContractItem ci): boolean` | Lưu chi tiết hợp đồng (sản phẩm trong hợp đồng) |
| PaymentScheduleDAO | `insertPaymentSchedule(PaymentSchedule ps): boolean` | Lưu lịch trả góp cho hợp đồng |

### Entity types

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Customer | Entity | customerId (int), customerName (String), phoneNumber (String), address (String), idCard (String) |
| Partner | Entity | partnerId (int), partnerName (String), address (String), phoneNumber (String), branch (String) |
| Product | Entity | productId (int), productName (String), unitPrice (double), description (String) |
| Contract | Entity | contractId (int), customerId (int), partnerId (int), signingDate (Date), totalLoanAmount (double), loanTerm (int), interestRate (double), status (String) |
| ContractItem | Entity | contractItemId (int), contractId (int), productId (int), quantity (int), unitPrice (double), amount (double) |
| PaymentSchedule | Entity | scheduleId (int), contractId (int), periodNumber (int), dueDate (Date), amount (double), outstandingBalance (double), status (String) |
| Payment | Entity | paymentId (int), scheduleId (int), paymentDate (Date), amountPaid (double), method (String) |
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
| scheduleId | INT | FK → tblPaymentSchedule |
| paymentDate | DATE | NOT NULL |
| amountPaid | DECIMAL(12,2) | NOT NULL |
| method | VARCHAR(20) | |

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
2. Tạo 1 view class: SignContractFrm với tất cả attributes input/output/submit
3. Tạo 6 DAO classes: CustomerDAO, ProductDAO, ContractDAO, ContractItemDAO, PaymentScheduleDAO — mỗi class có phương thức tương ứng
4. Vẽ mối quan hệ: Customer -- Contract (1-n), Partner -- Contract (1-n), Contract -- ContractItem (1-n), Product -- ContractItem (1-n), Contract -- PaymentSchedule (1-n), PaymentSchedule -- Payment (1-n)
5. Thêm dependency mũi tên từ SignContractFrm → các DAO classes

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

**Tạo Sequence Diagram trong Visual Paradigm:**
1. Tạo 8 lifelines: `:Staff` (actor), `:SignContractFrm` (boundary), `:CustomerDAO` (control), `:ProductDAO` (control), `:ContractDAO` (control), `:ContractItemDAO` (control), `:PaymentScheduleDAO` (control), `:Database` (database)
2. Thêm message arrows theo thứ tự bên dưới
3. Sử dụng return arrows (dashed) cho các message trả về
4. Thêm loop fragment cho bước tìm kiếm và thêm sản phẩm (lặp lại nhiều lần)
5. Thêm alt fragment cho trường hợp khách hàng mới

### ASCII Sequence Diagram

```
  Staff          SignContractFrm     CustomerDAO    ProductDAO    ContractDAO  ContractItemDAO  PaymentScheduleDAO  Database
    |                  |                  |              |              |              |                |               |
    | selectMenu(      |                  |              |              |              |                |               |
    | "Sign new        |                  |              |              |              |                |               |
    |  contract")      |                  |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(inCustomerName, subSearch)     |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | enter("Nguyen Van A"), click(Search)|              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  | searchByName(    |              |              |              |                |               |
    |                  | "Nguyen Van A")  |              |              |              |                |               |
    |                  |----------------->|              |              |              |                |               |
    |                  |                  | SELECT * FROM tblCustomer WHERE customerName LIKE '%?%'      |               |
    |                  |                  |----------------------------------------------------------------------->|
    |                  |                  | ResultSet    |              |              |                |               |
    |                  |                  |<-----------------------------------------------------------------------|
    |                  | List<Customer>   |              |              |              |                |               |
    |                  |<-----------------|              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(outCustomerList)               |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | click("Nguyen Van A")               |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(inProductName, subSearch)      |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | enter("iPhone 15"), click(Search)   |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  | searchByName(    |              |              |              |                |               |
    |                  | "iPhone 15")     |              |              |              |                |               |
    |                  |------------------------------>|              |              |                |               |
    |                  |                  |              | SELECT * FROM tblProduct WHERE productName LIKE '%?%'  |
    |                  |                  |              |----------------------------------------------------------------------->|
    |                  |                  |              | ResultSet    |              |                |               |
    |                  |                  |              |<-----------------------------------------------------------------------|
    |                  | List<Product>    |              |              |              |                |               |
    |                  |<------------------------------|              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(outProductList)                |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | selectProduct, enterQty(1), enterPrice(25000000)  |              |              |                |               |
    | click(subAdd)    |                  |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  | addItemToList()  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(outContractItemList updated)   |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | [loop: repeat for each product]     |              |              |              |                |               |
    | enter("AirPods Pro"), search, select, qty=1, price=5000000, click(Add)        |                |               |
    |                  |                  |              |              |              |                |               |
    | click(subContinue)                  |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(inLoanTerm, inInterestRate)    |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | enter(loanTerm=12, interestRate=1.5)|              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  | calculateSchedule()             |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | show(outPaymentSchedule: 12 kỳ)     |              |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
    |                  |                  |              |              |              |                |               |
    | click(subConfirm)|                  |              |              |              |                |               |
    |----------------->|                  |              |              |              |                |               |
    |                  | insertContract(contract)        |              |              |                |               |
    |                  |-------------------------------|------------->|              |                |               |
    |                  |                  |              |              | INSERT INTO tblContract |        |               |
    |                  |                  |              |              |------------------------------------------->|
    |                  |                  |              |              | contractId |              |                |               |
    |                  |                  |              |              |<-------------------------------------------|
    |                  | contractId       |              |              |              |                |               |
    |                  |<-------------------------------|------------|              |                |               |
    |                  |                  |              |              |              |                |               |
    |                  | [loop: for each ContractItem]   |              |              |                |               |
    |                  | insertContractItem(ci)          |              |              |                |               |
    |                  |----------------------------------------------------------->|                |               |
    |                  |                  |              |              |              | INSERT INTO tblContractItem |
    |                  |                  |              |              |              |--------------------------->|
    |                  |                  |              |              |              | true           |               |
    |                  |                  |              |              |              |<---------------------------|
    |                  | true             |              |              |              |                |               |
    |                  |<----------------------------------------------------------|                |               |
    |                  |                  |              |              |              |                |               |
    |                  | [loop: for each PaymentSchedule]|              |              |                |               |
    |                  | insertPaymentSchedule(ps)       |              |              |                |               |
    |                  |-----------------------------------------------------|------|--------------->|               |
    |                  |                  |              |              |              |                | INSERT INTO tblPaymentSchedule |
    |                  |                  |              |              |              |                |----------->|
    |                  |                  |              |              |              |                | true        |
    |                  |                  |              |              |              |                |<-----------|
    |                  | true             |              |              |              |                |               |
    |                  |<-----------------------------------------------------|------|---------------|               |
    |                  |                  |              |              |              |                |               |
    | show("Ký hợp đồng thành công"), printContract()   |              |              |                |               |
    |<-----------------|                  |              |              |              |                |               |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | SignContractFrm | `selectMenu("Sign new contract")` | Chọn menu ký hợp đồng |
| 2 | SignContractFrm | Staff | `show(inCustomerName, subSearchCustomer)` | Hiển thị form tìm KH |
| 3 | Staff | SignContractFrm | `enter(inCustomerName="Nguyen Van A")` | Nhập tên KH |
| 4 | Staff | SignContractFrm | `click(subSearchCustomer)` | Nhấn tìm kiếm |
| 5 | SignContractFrm | CustomerDAO | `searchByName("Nguyen Van A")` | Gọi DAO tìm KH |
| 6 | CustomerDAO | Database | `SELECT * FROM tblCustomer WHERE customerName LIKE '%Nguyen Van A%'` | Truy vấn SQL |
| 7 | Database | CustomerDAO | `ResultSet` | Trả về dữ liệu |
| 8 | CustomerDAO | SignContractFrm | `List<Customer>` | Trả về danh sách KH |
| 9 | SignContractFrm | Staff | `show(outCustomerList)` | Hiển thị danh sách KH |
| 10 | Staff | SignContractFrm | `click(customerId="C001")` | Chọn khách hàng |
| 11 | SignContractFrm | Staff | `show(inProductName, subSearchProduct)` | Hiển thị form tìm SP |
| 12 | Staff | SignContractFrm | `enter(inProductName="iPhone 15")` | Nhập tên SP |
| 13 | Staff | SignContractFrm | `click(subSearchProduct)` | Nhấn tìm kiếm |
| 14 | SignContractFrm | ProductDAO | `searchByName("iPhone 15")` | Gọi DAO tìm SP |
| 15 | ProductDAO | Database | `SELECT * FROM tblProduct WHERE productName LIKE '%iPhone 15%'` | Truy vấn SQL |
| 16 | Database | ProductDAO | `ResultSet` | Trả về dữ liệu |
| 17 | ProductDAO | SignContractFrm | `List<Product>` | Trả về danh sách SP |
| 18 | SignContractFrm | Staff | `show(outProductList)` | Hiển thị danh sách SP |
| 19 | Staff | SignContractFrm | `selectProduct(productId="I001"), enter(inQuantity=1), enter(inUnitPrice=25000000)` | Chọn SP, nhập SL, giá |
| 20 | Staff | SignContractFrm | `click(subAdd)` | Thêm vào danh sách |
| 21 | SignContractFrm | SignContractFrm | `addItemToList(product, quantity, unitPrice)` | Cập nhật danh sách HĐ |
| 22 | SignContractFrm | Staff | `show(outContractItemList)` | Hiển thị danh sách SP trong HĐ |
| 23 | Staff | SignContractFrm | `click(subContinue)` | Chuyển sang bước kỳ hạn |
| 24 | SignContractFrm | Staff | `show(inLoanTerm, inInterestRate, outPaymentSchedule)` | Hiển thị form nhập kỳ hạn |
| 25 | Staff | SignContractFrm | `enter(inLoanTerm=12, inInterestRate=1.5)` | Nhập kỳ hạn, lãi suất |
| 26 | SignContractFrm | SignContractFrm | `calculatePaymentSchedule()` | Tự động tính lịch trả góp |
| 27 | SignContractFrm | Staff | `show(outPaymentSchedule)` | Hiển thị lịch trả góp 12 kỳ |
| 28 | Staff | SignContractFrm | `click(subConfirm)` | Xác nhận lưu hợp đồng |
| 29 | SignContractFrm | ContractDAO | `insertContract(contract)` | Lưu hợp đồng mới |
| 30 | ContractDAO | Database | `INSERT INTO tblContract(customerId, partnerId, signingDate, totalLoanAmount, loanTerm, interestRate, status)` | Truy vấn SQL |
| 31 | Database | ContractDAO | `contractId=HD001` | Trả về ID hợp đồng |
| 32 | ContractDAO | SignContractFrm | `contractId` | Trả về contractId |
| 33 | SignContractFrm | ContractItemDAO | `insertContractItem(ci)` | Lưu chi tiết HĐ (loop) |
| 34 | ContractItemDAO | Database | `INSERT INTO tblContractItem(contractId, productId, quantity, unitPrice, amount)` | Truy vấn SQL |
| 35 | Database | ContractItemDAO | `true` | Thành công |
| 36 | ContractItemDAO | SignContractFrm | `true` | Xác nhận lưu |
| 37 | SignContractFrm | PaymentScheduleDAO | `insertPaymentSchedule(ps)` | Lưu lịch trả góp (loop) |
| 38 | PaymentScheduleDAO | Database | `INSERT INTO tblPaymentSchedule(contractId, periodNumber, dueDate, amount, outstandingBalance, status)` | Truy vấn SQL |
| 39 | Database | PaymentScheduleDAO | `true` | Thành công |
| 40 | PaymentScheduleDAO | SignContractFrm | `true` | Xác nhận lưu |
| 41 | SignContractFrm | Staff | `show("Ký hợp đồng thành công"), printContract()` | Thông báo và in HĐ |

---

## Câu 5: Blackbox Testcase

### TC01: Ký hợp đồng thành công với 2 sản phẩm

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

tblContract: (rỗng)
tblContractItem: (rỗng)
tblPaymentSchedule: (rỗng)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công, hiển thị trang chủ |
| 2 | Staff chọn "Sign new contract" — Hiển thị giao diện tìm kiếm khách hàng |
| 3 | Staff nhập "Nguyen Van A", nhấn Search — Hiển thị danh sách: C001 (Nguyen Van A, 0901234567, 012345678901) |
| 4 | Staff click chọn C001 — Chuyển sang giao diện tìm kiếm sản phẩm |
| 5 | Staff nhập "iPhone 15", nhấn Search — Hiển thị: I001 (iPhone 15, 25,000,000đ) |
| 6 | Staff chọn I001, nhập số lượng 1, đơn giá 25,000,000, nhấn Add — Sản phẩm thêm vào danh sách HĐ |
| 7 | Staff nhập "AirPods Pro", nhấn Search — Hiển thị: I002 (AirPods Pro, 5,000,000đ) |
| 8 | Staff chọn I002, nhập số lượng 1, đơn giá 5,000,000, nhấn Add — Sản phẩm thêm vào danh sách HĐ |
| 9 | Staff nhấn Continue — Chuyển sang giao diện nhập kỳ hạn và lãi suất |
| 10 | Staff nhập kỳ hạn 12 tháng, lãi suất 1.5%/tháng — Hệ thống tự động tính lịch trả góp 12 kỳ |
| 11 | Staff kiểm tra lịch trả góp, nhấn Confirm — Hệ thống lưu hợp đồng, hiển thị "Ký hợp đồng thành công", in hợp đồng |

**Database sau:**

tblCustomer: Không thay đổi

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD001 | C001 | P001 | 2026-06-08 | 30000000 | 12 | 1.5 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS001 | HD001 | 1 | 2026-07-08 | 2950000 | 27050000 | Unpaid |
| PS002 | HD001 | 2 | 2026-08-08 | 2950000 | 24100000 | Unpaid |
| PS003 | HD001 | 3 | 2026-09-08 | 2950000 | 21150000 | Unpaid |
| PS004 | HD001 | 4 | 2026-10-08 | 2950000 | 18200000 | Unpaid |
| PS005 | HD001 | 5 | 2026-11-08 | 2950000 | 15250000 | Unpaid |
| PS006 | HD001 | 6 | 2026-12-08 | 2950000 | 12300000 | Unpaid |
| PS007 | HD001 | 7 | 2027-01-08 | 2950000 | 9350000 | Unpaid |
| PS008 | HD001 | 8 | 2027-02-08 | 2950000 | 6400000 | Unpaid |
| PS009 | HD001 | 9 | 2027-03-08 | 2950000 | 3450000 | Unpaid |
| PS010 | HD001 | 10 | 2027-04-08 | 2950000 | 500000 | Unpaid |
| PS011 | HD001 | 11 | 2027-05-08 | 500000 | 0 | Unpaid |
| PS012 | HD001 | 12 | 2027-06-08 | 0 | 0 | Unpaid |

---

### TC02: Khách hàng chưa tồn tại — thêm mới rồi ký hợp đồng

**Database trước:**

tblCustomer: (rỗng)

tblPartner:
| partnerId | partnerName | address | phoneNumber | branch |
|-----------|-------------|---------|-------------|--------|
| P001 | TGDD | 456 Tran Hung Dao, HN | 0902222222 | Chi nhanh 1 |

tblProduct:
| productId | productName | unitPrice | description |
|-----------|-------------|-----------|-------------|
| I003 | Samsung Galaxy S24 | 20000000 | Samsung Galaxy S24 256GB |

tblContract: (rỗng)
tblContractItem: (rỗng)
tblPaymentSchedule: (rỗng)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Staff đăng nhập thành công |
| 2 | Staff chọn "Sign new contract" |
| 3 | Staff nhập "Tran Thi B", nhấn Search — Hiển thị "Không tìm thấy khách hàng" |
| 4 | Staff nhấn nút "Add new customer" — Hiển thị form nhập thông tin KH mới |
| 5 | Staff nhập: tên "Tran Thi B", SĐT "0922222222", địa chỉ "TP.HCM", CMND "098765432101", nhấn Save |
| 6 | Hệ thống lưu khách hàng mới, quay về giao diện tìm SP, khách hàng Tran Thi B đã được chọn |
| 7 | Staff tìm SP "Samsung Galaxy S24", chọn, nhập SL 1, giá 20,000,000, nhấn Add |
| 8 | Staff nhấn Continue, nhập kỳ hạn 6 tháng, lãi suất 2%/tháng |
| 9 | Staff nhấn Confirm — Hệ thống lưu hợp đồng, in hợp đồng |

**Database sau:**

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C001 | Tran Thi B | 0922222222 | TP.HCM | 098765432101 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | loanTerm | interestRate | status |
|-----------|-----------|-----------|-------------|-----------------|----------|--------------|--------|
| HD001 | C001 | P001 | 2026-06-08 | 20000000 | 6 | 2.0 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I003 | 1 | 20000000 | 20000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | outstandingBalance | status |
|-----------|-----------|-------------|---------|--------|--------------------|--------|
| PS001 | HD001 | 1 | 2026-07-08 | 3733333 | 16266667 | Unpaid |
| PS002 | HD001 | 2 | 2026-08-08 | 3733333 | 12533334 | Unpaid |
| PS003 | HD001 | 3 | 2026-09-08 | 3733333 | 8800001 | Unpaid |
| PS004 | HD001 | 4 | 2026-10-08 | 3733333 | 5066668 | Unpaid |
| PS005 | HD001 | 5 | 2026-11-08 | 3733333 | 1333335 | Unpaid |
| PS006 | HD001 | 6 | 2026-12-08 | 1333335 | 0 | Unpaid |
