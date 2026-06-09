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

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Partner, Customer, Contract, ContractItem, PaymentSchedule, User
- Bước 3: Tạo các view class box từ giao diện: HomeFrm, PartnerStatView, PartnerContractListView, ContractDetailView
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Contract`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-contractId: int`, `-totalAmount: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+getPartnerContracts(partnerId: int, startDate: Date, endDate: Date): List<Contract>`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Partner | <<entity>> | -partnerId: int, -code: String, -name: String, -address: String, -email: String, -phone: String | +getPartnerStat(startDate: Date, endDate: Date): List<PartnerStat> |
| Customer | <<entity>> | -customerId: int, -code: String, -name: String, -phone: String, -address: String | |
| Contract | <<entity>> | -contractId: int, -customer: Customer, -partner: Partner, -signingDate: Date, -totalAmount: double, -status: String | +getPartnerContracts(partnerId: int, startDate: Date, endDate: Date): List<Contract> |
| ContractItem | <<entity>> | -contractItemId: int, -contract: Contract, -quantity: int, -unitPrice: double, -amount: double | +getContractItems(contractId: int): List<ContractItem> |
| PaymentSchedule | <<entity>> | -scheduleId: int, -contract: Contract, -paymentDate: Date, -paymentAmount: double, -outstandingBalance: double, -status: String | |
| User | <<entity>> | -userId: int, -username: String, -password: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeFrm | subPartnerStat: JButton (nút chọn Partner statistics) |
| PartnerStatView | inStartDate: JTextField (ô nhập ngày bắt đầu), inEndDate: JTextField (ô nhập ngày kết thúc), subView: JButton (nút View), outsubPartnerTable: JTable (bảng thống kê đối tác click được) |
| PartnerContractListView | outsubContractTable: JTable (danh sách hợp đồng click được) |
| ContractDetailView | outContractDetail: JPanel (chi tiết hợp đồng: thông tin KH, sản phẩm, lịch sử thanh toán) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Partner → Contract
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Contract ◆→ ContractItem
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: PartnerStatView ---> PartnerDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Partner (1) --- (n) Contract

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Partner | Contract | Association | 1 : n | Một đối tác có nhiều hợp đồng |
| Customer | Contract | Association | 1 : n | Một khách hàng có nhiều hợp đồng |
| Contract | ContractItem | Composition | 1 : n | Hợp đồng chứa nhiều chi tiết sản phẩm |
| Contract | PaymentSchedule | Composition | 1 : n | Hợp đồng có nhiều kỳ thanh toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition Contract ◆→ ContractItem:
- Kéo class Contract vào canvas, kéo class ContractItem vào bên phải
- Chọn công cụ **Composition**, click vào Contract rồi kéo sang ContractItem
- Kim cương filled nằm ở phía Contract (parent)
- Đặt Multiplicity: Contract "1", ContractItem "n"

Ví dụ 2 — Vẽ quan hệ Association Partner → Contract (1:n):
- Kéo class Partner và Contract vào canvas
- Chọn công cụ **Association**, click vào Partner kéo sang Contract
- Đặt Multiplicity: Partner "1", Contract "n"

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

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Partner | Entity | id: int (PK), code: String, name: String, address: String, email: String, phone: String |
| Customer | Entity | id: int (PK), code: String, name: String, phone: String, address: String |
| Contract | Entity | id: int (PK), signingDate: Date, totalAmount: double, status: String, customer: Customer, partner: Partner |
| ContractItem | Entity | id: int (PK), quantity: int, unitPrice: double, amount: double, contract: Contract |
| PaymentSchedule | Entity | id: int (PK), paymentDate: Date, paymentAmount: double, outstandingBalance: double, status: String, contract: Contract |
| User | Entity | id: int (PK), username: String, password: String, role: String |

### Database Design

**tblPartner:**
| Column | Type | Constraint |
|--------|------|------------|
| partnerId | INT | PK |
| code | VARCHAR(20) | UNIQUE, NOT NULL |
| partnerName | VARCHAR(100) | NOT NULL |
| address | VARCHAR(200) | |
| email | VARCHAR(100) | |
| phoneNumber | VARCHAR(15) | |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| customerId | INT | PK |
| code | VARCHAR(20) | UNIQUE, NOT NULL |
| customerName | VARCHAR(100) | NOT NULL |
| phoneNumber | VARCHAR(15) | |
| address | VARCHAR(200) | |

**tblContract:**
| Column | Type | Constraint |
|--------|------|------------|
| contractId | INT | PK |
| customerId | INT | FK → tblCustomer |
| partnerId | INT | FK → tblPartner |
| signingDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblContractItem:**
| Column | Type | Constraint |
|--------|------|------------|
| contractItemId | INT | PK |
| contractId | INT | FK → tblContract |
| productId | INT | NOT NULL |
| quantity | INT | NOT NULL |
| unitPrice | DECIMAL(12,2) | NOT NULL |
| amount | DECIMAL(15,2) | NOT NULL |

**tblPaymentSchedule:**
| Column | Type | Constraint |
|--------|------|------------|
| scheduleId | INT | PK |
| contractId | INT | FK → tblContract |
| paymentDate | DATE | NOT NULL |
| paymentAmount | DECIMAL(12,2) | NOT NULL |
| outstandingBalance | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| userId | INT | PK |
| username | VARCHAR(50) | UNIQUE, NOT NULL |
| password | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

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
