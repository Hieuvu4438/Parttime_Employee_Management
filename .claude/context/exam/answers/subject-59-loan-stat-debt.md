# Subject No. 59 — Installment Loan — Module "Customer statistics by debt"

> **Domain:** Installment Loan Management

---

## Cau 1: Scenario — Thong ke khach hang theo no

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop cho phep nhan vien xem thong ke tinh trang no cua tat ca khach hang. Nhan vien chon chuc nang "Customer statistics by debt", he thong hien thi bang tong hop khach hang sap xep theo tong no giam dan. Nhan vien co the click vao 1 khach hang de xem danh sach hop dong, sau do click vao 1 hop dong de xem chi tiet san pham va lich su thanh toan.

### Chuong trinh chinh (Main scenario)

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien dang nhap he thong voi tai khoan `staff01` / `matkhau123` |
| 2 | He thong hien thi trang chu, nhan vien click chuc nang **Customer statistics by debt** |
| 3 | He thong hien thi bang thong ke khach hang theo no (`outCustomerStatTable`). Moi dong gom: ma KH, ten KH, so dien thoai, tong du no (outstanding balance), tong no con lai (debt remaining). Bang sap xep theo tong no con lai giam dan |
| 4 | He thong hien thi du lieu: |
| | — C001 | Nguyen Van A | 0901234567 | du no 27,250,000d | no con lai 21,750,000d |
| | — C002 | Tran Van B | 0912345678 | du no 18,000,000d | no con lai 12,000,000d |
| | — C003 | Le Thi C | 0923456789 | du no 11,250,000d | no con lai 7,500,000d |
| 5 | Nhan vien click vao dong khach hang **Nguyen Van A** |
| 6 | He thong hien thi danh sach hop dong cua Nguyen Van A (`outContractList`). Moi dong gom: ma hop dong, ngay ky, tong so tien vay, tong so ky da tra, tong du no, tong no con lai |
| 7 | He thong hien thi du lieu: |
| | — HD001 | 01/06/2026 | vay 30,000,000d | da tra 3 ky | du no 21,750,000d | no con 21,750,000d |
| | — HD005 | 15/07/2026 | vay 10,000,000d | da tra 1 ky | du no 8,000,000d | no con 8,000,000d |
| 8 | Nhan vien click vao dong hop dong **HD001** |
| 9 | He thong hien thi chi tiet hop dong HD001 (`outContractDetails`): thong tin khach hang (ten, SĐT), danh sach san pham (ten SP, so luong, don gia, thanh tien), danh sach thanh toan theo ky (ky, ngay den han, so tien, trang thai) |
| 10 | He thong hien thi chi tiet: khach hang Nguyen Van A, SĐT 0901234567; san pham: iPhone 15 (1, 25,000,000d, 25,000,000d), AirPods Pro (1, 5,000,000d, 5,000,000d); thanh toan: ky 1 da thanh toan, ky 2 da thanh toan, ky 3 da thanh toan, ky 4 chua thanh toan |
| 11 | Nhan vien xem xong, click nut **Back** (`subBack`) de tro ve bang thong ke khach hang |

### Ngoai le

| Ngoai le | Xu ly |
|----------|-------|
| E1: Khach hang khong co hop dong nao | He thong hien thi `"Khach hang nay chua co hop dong nao"`, nhan vien click Back de tro ve |
| E2: Hop dong chua co ban ghi thanh toan | He thong hien thi chi tiet hop dong voi danh sach thanh toan rong, hien thi `"Chua co thanh toan nao"` |
| E3: Du lieu thong ke rong (chua co khach hang) | He thong hien thi bang rong voi thong bao `"Chua co du lieu thong ke"` |

---

## Cau 2: Entity Class Diagram

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop ghi nhan khach hang (Customer) ky hop dong (Contract) mua san pham (Product). Moi hop dong co nhieu chi tiet san pham (ContractItem). Khach hang tra tien theo ky han (PaymentSchedule), moi ky co ban ghi thanh toan (Payment). Nhan vien (User) su dung he thong de xem thong ke.

### Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Customer | Entity | Doi tuong trung tam, co thuoc tinh rieng |
| Contract | Entity | Hop dong mua ban tra gop |
| ContractItem | Entity | Chi tiet san pham trong hop dong |
| Product | Entity | San pham duoc ban tra gop |
| PaymentSchedule | Entity | Lich thanh toan theo ky han |
| Payment | Entity | Ban ghi thanh toan tung ky |
| User | Entity | Nguoi dung he thong |
| customerId, contractId, productId | Attribute | Thuoc tinh dinh danh |
| customerName, phoneNumber, address | Attribute | Thuoc tinh cua Customer |
| signingDate, totalLoanAmount, status | Attribute | Thuoc tinh cua Contract |
| quantity, unitPrice, amount | Attribute | Thuoc tinh cua ContractItem |
| periodNumber, dueDate | Attribute | Thuoc tinh cua PaymentSchedule |
| paymentDate, amountPaid, method | Attribute | Thuoc tinh cua Payment |
| tong no, du no, no con lai | Attribute | Thuoc tinh tinh toan (computed) |
| he thong, giao diem, bang thong ke | Rejected | Khong phai doi tuong nghiep vu |

### Quan he so luong

```
Customer 1 --- n Contract        (1 KH co nhieu hop dong)
Contract 1 --- n ContractItem    (1 hop dong co nhieu chi tiet SP)
Product 1 --- n ContractItem     (1 san pham xuat hien nhieu chi tiet)
Contract 1 --- n PaymentSchedule (1 hop dong co nhieu ky thanh toan)
PaymentSchedule 1 --- n Payment  (1 ky co nhieu ban ghi thanh toan)
User 1 --- n Payment             (1 nhan vien xu ly nhieu thanh toan)
```

### Quan he doi tuong

```
Customer 1 --- n Contract        (association)
Contract 1 --- * ContractItem    (composition — ContractItem ton tai khi Contract ton tai)
Product 1 --- n ContractItem     (association)
Contract 1 --- * PaymentSchedule (composition)
PaymentSchedule 1 --- * Payment  (composition)
User 1 --- n Payment             (association)
```

### Bang quan he (Database Schema)

| Bang | Cot PK | Cot FK | Cac cot khac |
|------|--------|--------|--------------|
| tblCustomer | customerId | — | customerName, phoneNumber, address, idCard |
| tblContract | contractId | customerId → tblCustomer | signingDate, totalLoanAmount, status |
| tblContractItem | contractItemId | contractId → tblContract, productId → tblProduct | quantity, unitPrice, amount |
| tblProduct | productId | — | productName, unitPrice, description |
| tblPaymentSchedule | scheduleId | contractId → tblContract | periodNumber, dueDate, amount, status |
| tblPayment | paymentId | scheduleId → tblPaymentSchedule | paymentDate, amountPaid, method |
| tblUser | userId | — | username, password, fullName, role |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Customer, Contract, ContractItem, Product, PaymentSchedule, Payment, User
- Bước 3: Tạo các view class box từ giao diện: HomeFrm, StatDebtView, ContractListView, ContractDetailView
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Customer`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-customerId: int`, `-customerName: String`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+getDebtStatistics(): List<Customer>`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Customer | <<entity>> | -customerId: int, -customerName: String, -phoneNumber: String, -address: String, -idCard: String | +getDebtStatistics(): List<Customer> |
| Contract | <<entity>> | -contractId: int, -customerId: int, -partnerId: int, -signingDate: Date, -totalLoanAmount: double, -status: String | +getContractsByCustomer(customerId: int): List<Contract> |
| ContractItem | <<entity>> | -contractItemId: int, -contractId: int, -productId: int, -quantity: int, -unitPrice: double, -amount: double | +getItemsByContract(contractId: int): List<ContractItem> |
| Product | <<entity>> | -productId: int, -productName: String, -unitPrice: double, -description: String | |
| PaymentSchedule | <<entity>> | -scheduleId: int, -contractId: int, -periodNumber: int, -dueDate: Date, -amount: double, -status: String | +getScheduleByContract(contractId: int): List<PaymentSchedule> |
| Payment | <<entity>> | -paymentId: int, -scheduleId: int, -paymentDate: Date, -amountPaid: double, -method: String | +getPaymentsBySchedule(scheduleId: int): List<Payment> |
| User | <<entity>> | -userId: int, -username: String, -password: String, -fullName: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeFrm | subStatDebt: JButton (nút chọn Customer statistics by debt) |
| StatDebtView | outsubCustomerStatTable: JTable (bảng thống kê KH theo nợ, click được) |
| ContractListView | outsubContractList: JTable (danh sách HĐ của KH, click được), subBack: JButton (nút Back) |
| ContractDetailView | outCustomerInfo: JLabel (thông tin KH), outItemList: JTable (danh sách SP), outPaymentList: JTable (danh sách thanh toán theo kỳ), subBack: JButton (nút Back) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Contract
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Contract ◆→ ContractItem
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: StatDebtView ---> CustomerDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Customer (1) --- (n) Contract

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Customer | Contract | Association | 1 : n | Một khách hàng có nhiều hợp đồng |
| Contract | ContractItem | Composition | 1 : n | Hợp đồng chứa nhiều chi tiết SP, chi tiết không tồn tại nếu không có HĐ |
| Product | ContractItem | Association | 1 : n | Một sản phẩm xuất hiện trong nhiều chi tiết HĐ |
| Contract | PaymentSchedule | Composition | 1 : n | Hợp đồng chứa nhiều kỳ thanh toán |
| PaymentSchedule | Payment | Composition | 1 : n | Một kỳ có nhiều phiếu thanh toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition Contract ◆→ PaymentSchedule:
- Kéo class Contract vào canvas, kéo class PaymentSchedule vào bên phải
- Chọn công cụ **Composition**, click vào Contract rồi kéo sang PaymentSchedule
- Kim cương filled nằm ở phía Contract (parent)
- Đặt Multiplicity: Contract "1", PaymentSchedule "n"

Ví dụ 2 — Vẽ quan hệ Customer → Contract (1:n) Association:
- Kéo class Customer và Contract vào canvas
- Chọn công cụ **Association**, click vào Customer kéo sang Contract
- Đặt Multiplicity: Customer "1", Contract "n"

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Customer statistics by debt" → subStatDebt

Staff chọn Customer statistics by debt → StatDebtView xuất hiện:
  Bảng thống kê khách hàng theo nợ (click được) → outsubCustomerStatTable

Staff click khách hàng → ContractListView xuất hiện:
  Danh sách hợp đồng của khách hàng (click được) → outsubContractList
  Nút Back → subBack

Staff click hợp đồng → ContractDetailView xuất hiện:
  Thông tin khách hàng (tên, SĐT) → outCustomerInfo
  Danh sách sản phẩm trong hợp đồng → outItemList
  Danh sách thanh toán theo kỳ → outPaymentList
  Nút Back → subBack

Phân tích phương thức:

Lấy thống kê khách hàng theo nợ:
  Tên: getDebtStatistics()
  Đầu vào: (không có)
  Đầu ra: List<Customer> (kèm tổng dư nợ, tổng nợ còn lại)
  Gán cho entity class: Customer.

Lấy danh sách hợp đồng của khách hàng:
  Tên: getContractsByCustomer()
  Đầu vào: customerId (int)
  Đầu ra: List<Contract>
  Gán cho entity class: Contract.

Lấy danh sách sản phẩm trong hợp đồng:
  Tên: getItemsByContract()
  Đầu vào: contractId (int)
  Đầu ra: List<ContractItem>
  Gán cho entity class: ContractItem.

Lấy lịch trả góp theo hợp đồng:
  Tên: getScheduleByContract()
  Đầu vào: contractId (int)
  Đầu ra: List<PaymentSchedule>
  Gán cho entity class: PaymentSchedule.

Lấy danh sách thanh toán theo kỳ:
  Tên: getPaymentsBySchedule()
  Đầu vào: scheduleId (int)
  Đầu ra: List<Payment>
  Gán cho entity class: Payment.

### Tóm tắt
View classes: HomeFrm, StatDebtView, ContractListView, ContractDetailView
Phương thức: getDebtStatistics(), getContractsByCustomer(), getItemsByContract(), getScheduleByContract(), getPaymentsBySchedule()

---

## Cau 3: Thiet ke tinh

### View classes

**StatDebtFrm** — Giao diem thong ke khach hang theo no:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| outCustomerStatTable | `dgvCustomerStat` | DataGridView | Bang thong ke khach hang: ma KH, ten, SĐT, tong du no, tong no con lai. Sap xep theo no giam dan |
| outContractList | `dgvContractList` | DataGridView | Danh sach hop dong cua 1 KH duoc chon: ma HĐ, ngay ky, tong vay, so ky da tra, du no, no con lai |
| outContractDetails | `pnlContractDetails` | Panel | Chi tiet hop dong: thong tin KH, SĐT, trang thai thanh toan |
| outItemList | `dgvItemList` | DataGridView | Danh sach san pham trong hop dong: ten SP, so luong, don gia, thanh tien |
| outPaymentList | `dgvPaymentList` | DataGridView | Danh sach thanh toan theo ky: ky, ngay den han, so tien, trang thai da/chua thanh toan |
| subBack | `btnBack` | Button | Nut tro ve bang thong ke |

**HomeFrm** — Giao diem trang chu:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| subStatDebt | `btnStatDebt` | Button | Nut mo giao diem thong ke khach hang theo no |

### Entity types

| Entity | Attributes | Kieu du lieu |
|--------|------------|--------------|
| Customer | customerId | int (PK) |
| | customerName | String |
| | phoneNumber | String |
| | address | String |
| | idCard | String |
| Contract | contractId | int (PK) |
| | customerId | int (FK) |
| | partnerId | int (FK) |
| | signingDate | Date |
| | totalLoanAmount | double |
| | status | String |
| ContractItem | contractItemId | int (PK) |
| | contractId | int (FK) |
| | productId | int (FK) |
| | quantity | int |
| | unitPrice | double |
| | amount | double |
| PaymentSchedule | scheduleId | int (PK) |
| | contractId | int (FK) |
| | periodNumber | int |
| | dueDate | Date |
| | amount | double |
| | status | String |
| Payment | paymentId | int (PK) |
| | scheduleId | int (FK) |
| | paymentDate | Date |
| | amountPaid | double |
| | method | String |

### DAO classes

| DAO | Phuong thuc | Mo ta |
|-----|-------------|-------|
| CustomerDAO | `getDebtStatistics(): List<Customer>` | Lay danh sach tat ca KH kem tong du no va tong no con lai, sap xep theo no giam dan |
| ContractDAO | `getContractsByCustomer(int customerId): List<Contract>` | Lay danh sach hop dong cua 1 khach hang |
| ContractItemDAO | `getItemsByContract(int contractId): List<ContractItem>` | Lay danh sach san pham trong 1 hop dong |
| PaymentScheduleDAO | `getScheduleByContract(int contractId): List<PaymentSchedule>` | Lay lich trinh thanh toan theo ky cua 1 hop dong |
| PaymentDAO | `getPaymentsBySchedule(int scheduleId): List<Payment>` | Lay danh sach thanh toan cua 1 ky |

### Database Design

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

### Huong dan Visual Paradigm

1. Tao package `view.statistics` chua `StatDebtFrm`, `HomeFrm`
2. Tao package `dao` chua `CustomerDAO`, `ContractDAO`, `ContractItemDAO`, `PaymentScheduleDAO`, `PaymentDAO`
3. Tao package `model` chua cac entity classes
4. Ve dependency tu `StatDebtFrm` den cac DAO classes
5. Ve dependency tu DAO classes den cac Entity classes

---

## Cau 4: Sequence Diagram

### Huong dan Visual Paradigm

1. Tao Sequence Diagram trong VP
2. Them lifelines: `:Staff` (actor), `:StatDebtFrm` (boundary), `:CustomerDAO` (control), `:ContractDAO` (control), `:ContractItemDAO` (control), `:PaymentScheduleDAO` (control), `:Database` (entity)
3. Ve message flow theo bang duoi, dung `synchronous message` (mui ten dac) cho request va `return message` (mui ten dut) cho response
4. Them `alt` fragment cho truong hop khach hang khong co hop dong (E1)
5. Them `ref` fragment cho viec hien thi chi tiet hop dong

### Bang buoc sequence

| Buoc | Nguoi gui | Nguoi nhan | Message | Ghi chu |
|------|-----------|------------|---------|---------|
| 1 | Staff | StatDebtFrm | `selectMenu("Customer statistics by debt")` | Nhan vien chon chuc nang |
| 2 | StatDebtFrm | CustomerDAO | `getDebtStatistics()` | Goi DAO lay thong ke |
| 3 | CustomerDAO | Database | `SELECT c.customerId, c.customerName, c.phoneNumber, SUM(ps.amount) AS totalOutstanding, SUM(CASE WHEN ps.status='Unpaid' THEN ps.amount ELSE 0 END) AS totalDebt FROM tblCustomer c JOIN tblContract ct ON c.customerId = ct.customerId JOIN tblPaymentSchedule ps ON ct.contractId = ps.contractId GROUP BY c.customerId ORDER BY totalDebt DESC` | Truy van thong ke |
| 4 | Database | CustomerDAO | `ResultSet` | Tra ket qua |
| 5 | CustomerDAO | StatDebtFrm | `List<Customer>` | Tra ve danh sach KH |
| 6 | StatDebtFrm | Staff | `show(outCustomerStatTable)` | Hien thi bang thong ke: C001 (21,750,000d), C002 (12,000,000d), C003 (7,500,000d) |
| 7 | Staff | StatDebtFrm | `click("Nguyen Van A")` | Nhan vien click vao KH |
| 8 | StatDebtFrm | ContractDAO | `getContractsByCustomer(C001)` | Lay hop dong cua KH |
| 9 | ContractDAO | Database | `SELECT * FROM tblContract WHERE customerId = C001` | Truy van hop dong |
| 10 | Database | ContractDAO | `ResultSet` | Tra ket qua |
| 11 | ContractDAO | StatDebtFrm | `List<Contract>` | Tra ve danh sach HĐ |
| 12 | StatDebtFrm | Staff | `show(outContractList)` | Hien thi: HD001 (30,000,000d, 3 ky), HD005 (10,000,000d, 1 ky) |
| 13 | Staff | StatDebtFrm | `click("HD001")` | Nhan vien click vao HĐ |
| 14 | StatDebtFrm | ContractItemDAO | `getItemsByContract(HD001)` | Lay san pham trong HĐ |
| 15 | ContractItemDAO | Database | `SELECT ci.*, p.productName FROM tblContractItem ci JOIN tblProduct p ON ci.productId = p.productId WHERE ci.contractId = 'HD001'` | Truy van san pham |
| 16 | Database | ContractItemDAO | `ResultSet` | Tra ket qua |
| 17 | ContractItemDAO | StatDebtFrm | `List<ContractItem>` | Tra ve danh sach SP |
| 18 | StatDebtFrm | PaymentScheduleDAO | `getScheduleByContract(HD001)` | Lay lich thanh toan |
| 19 | PaymentScheduleDAO | Database | `SELECT * FROM tblPaymentSchedule WHERE contractId = 'HD001' ORDER BY periodNumber` | Truy van lich thanh toan |
| 20 | Database | PaymentScheduleDAO | `ResultSet` | Tra ket qua |
| 21 | PaymentScheduleDAO | StatDebtFrm | `List<PaymentSchedule>` | Tra ve lich thanh toan |
| 22 | StatDebtFrm | Staff | `show(outContractDetails, outItemList, outPaymentList)` | Hien thi chi tiet: KH Nguyen Van A, SĐT 0901234567; SP: iPhone 15 (1, 25,000,000d), AirPods Pro (1, 5,000,000d); thanh toan: ky 1-3 da thanh toan, ky 4 chua thanh toan |

---

## Cau 5: Blackbox Testcase

### Test Plan

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Customer statistics by debt | Xem thong ke KH theo no va xem chi tiet hop dong thanh cong |
| TC02 | Customer statistics by debt | Khach hang khong co hop dong nao |
| TC03 | Customer statistics by debt | Hop dong chua co ban ghi thanh toan |

### TC01: Xem thong ke khach hang theo no va xem chi tiet hop dong

**Muc dich:** Kiem tra luong chinh — xem bang thong ke KH, drill-down vao hop dong, xem chi tiet san pham va thanh toan.

**Database truoc:**

tblUser:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | matkhau123 | Nguyen Van Staff | Staff |

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi | 012345678901 |
| C002 | Tran Van B | 0912345678 | HCM | 023456789012 |
| C003 | Le Thi C | 0923456789 | Da Nang | 034567890123 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-06-01 | 30000000 | Active |
| HD005 | C001 | P002 | 2026-07-15 | 10000000 | Active |
| HD008 | C002 | P001 | 2026-05-20 | 18000000 | Active |
| HD010 | C003 | P003 | 2026-04-10 | 15000000 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |
| CI005 | HD005 | I003 | 1 | 10000000 | 10000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | status |
|-----------|-----------|-------------|---------|--------|--------|
| PS001 | HD001 | 1 | 2026-07-01 | 7500000 | Paid |
| PS002 | HD001 | 2 | 2026-08-01 | 7500000 | Paid |
| PS003 | HD001 | 3 | 2026-09-01 | 7500000 | Paid |
| PS004 | HD001 | 4 | 2026-10-01 | 7500000 | Unpaid |
| PS005 | HD005 | 1 | 2026-08-15 | 2500000 | Paid |
| PS006 | HD005 | 2 | 2026-09-15 | 2500000 | Unpaid |
| PS007 | HD005 | 3 | 2026-10-15 | 2500000 | Unpaid |
| PS008 | HD005 | 4 | 2026-11-15 | 2500000 | Unpaid |

tblPayment:
| paymentId | scheduleId | paymentDate | amountPaid | method |
|-----------|-----------|-------------|-----------|--------|
| PAY001 | PS001 | 2026-07-01 | 7500000 | Cash |
| PAY002 | PS002 | 2026-08-01 | 7500000 | Cash |
| PAY003 | PS003 | 2026-09-01 | 7500000 | Cash |
| PAY004 | PS005 | 2026-08-15 | 2500000 | Transfer |

**Kich ban va ket qua mong doi:**

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Nhan vien dang nhap voi `staff01` / `matkhau123` | Dang nhap thanh cong, hien thi trang chu |
| 2 | Nhan vien click **Customer statistics by debt** | Hien thi bang thong ke KH theo no giam dan: C001 (Nguyen Van A, 0901234567, du no 27,250,000d, no con lai 21,750,000d), C002 (Tran Van B, 0912345678, du no 18,000,000d, no con lai 12,000,000d), C003 (Le Thi C, 0923456789, du no 11,250,000d, no con lai 7,500,000d) |
| 3 | Nhan vien click dong **Nguyen Van A** | Hien thi danh sach HĐ: HD001 (01/06/2026, vay 30,000,000d, da tra 3 ky, du no 21,750,000d, no con 7,500,000d), HD005 (15/07/2026, vay 10,000,000d, da tra 1 ky, du no 7,500,000d, no con 7,500,000d) |
| 4 | Nhan vien click dong **HD001** | Hien thi chi tiet HĐ: KH Nguyen Van A, SĐT 0901234567; SP: iPhone 15 (1, 25,000,000d, 25,000,000d), AirPods Pro (1, 5,000,000d, 5,000,000d); thanh toan: ky 1 da thanh toan (7,500,000d), ky 2 da thanh toan (7,500,000d), ky 3 da thanh toan (7,500,000d), ky 4 chua thanh toan (7,500,000d) |
| 5 | Nhan vien xem chi tiet, nut **Back** | Tro ve bang thong ke KH |

**Database sau:**

Khong thay doi (chuc nang chi doc du lieu thong ke, khong co thao tac ghi).
