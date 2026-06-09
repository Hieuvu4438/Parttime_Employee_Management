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

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Partner, Customer, Contract, ContractItem, PartnerPayment, PartnerPaymentDetail, User
- Bước 3: Tạo các view class box từ giao diện: HomeFrm, SearchPartnerView, PartnerContractListView
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> PartnerPayment`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-partnerPaymentId: int`, `-totalAmount: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+createPartnerPayment(payment: PartnerPayment): boolean`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Partner | <<entity>> | -partnerId: int, -partnerName: String, -address: String, -email: String, -phoneNumber: String | +searchPartnerByName(keyword: String): List<Partner> |
| Customer | <<entity>> | -customerId: int, -customerName: String, -phoneNumber: String, -address: String, -idCard: String | |
| Contract | <<entity>> | -contractId: int, -customerId: int, -partnerId: int, -signingDate: Date, -totalLoanAmount: double, -status: String | +getUnpaidContracts(partnerId: int): List<Contract> |
| ContractItem | <<entity>> | -contractItemId: int, -contractId: int, -productId: int, -quantity: int, -unitPrice: double, -amount: double | |
| PartnerPayment | <<entity>> | -partnerPaymentId: int, -partnerId: int, -userId: int, -paymentDate: Date, -totalAmount: double, -status: String | +createPartnerPayment(payment: PartnerPayment): boolean |
| PartnerPaymentDetail | <<entity>> | -detailId: int, -partnerPaymentId: int, -contractId: int, -amount: double | +addPaymentDetails(details: List<PartnerPaymentDetail>): boolean |
| User | <<entity>> | -userId: int, -username: String, -password: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeFrm | subPartnerPay: JButton (nút chọn Payment to partners) |
| SearchPartnerView | inPartnerName: JTextField (ô nhập tên đối tác), subSearch: JButton (nút Search), outsubListPartner: JTable (danh sách đối tác click được) |
| PartnerContractListView | outContractTable: JTable (bảng HĐ chưa thanh toán + checkbox), outInvoice: JPanel (hóa đơn thanh toán), outTotal: JLabel (tổng tiền), subSave: JButton (nút Save) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Partner → Contract
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: PartnerPayment ◆→ PartnerPaymentDetail
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: PartnerContractListView ---> ContractDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Partner (1) --- (n) PartnerPayment

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Partner | Contract | Association | 1 : n | Một đối tác có nhiều hợp đồng |
| Customer | Contract | Association | 1 : n | Một khách hàng có nhiều hợp đồng |
| Contract | ContractItem | Composition | 1 : n | Hợp đồng chứa nhiều chi tiết SP |
| Partner | PartnerPayment | Association | 1 : n | Một đối tác có nhiều phiếu thanh toán |
| PartnerPayment | PartnerPaymentDetail | Composition | 1 : n | Phiếu thanh toán chứa nhiều chi tiết theo HĐ |
| Contract | PartnerPaymentDetail | Association | 1 : n | Một HĐ có thể xuất hiện trong nhiều chi tiết TT |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition PartnerPayment ◆→ PartnerPaymentDetail:
- Kéo class PartnerPayment vào canvas, kéo class PartnerPaymentDetail vào bên phải
- Chọn công cụ **Composition**, click vào PartnerPayment rồi kéo sang PartnerPaymentDetail
- Kim cương filled nằm ở phía PartnerPayment (parent)
- Đặt Multiplicity: PartnerPayment "1", PartnerPaymentDetail "n"

Ví dụ 2 — Vẽ quan hệ Association Partner → Contract (1:n):
- Kéo class Partner và Contract vào canvas
- Chọn công cụ **Association**, click vào Partner kéo sang Contract
- Đặt Multiplicity: Partner "1", Contract "n"

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

### Entity classes

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Partner | Entity | id: int (PK), code: String, name: String, address: String, email: String, phone: String |
| Customer | Entity | id: int (PK), code: String, name: String, phone: String, address: String |
| Contract | Entity | id: int (PK), signingDate: Date, totalAmount: double, status: String, customer: Customer, partner: Partner |
| ContractItem | Entity | id: int (PK), quantity: int, unitPrice: double, amount: double, contract: Contract, product: Product |
| PartnerPayment | Entity | id: int (PK), paymentDate: Date, totalAmount: double, partner: Partner |
| PartnerPaymentDetail | Entity | id: int (PK), amount: double, partnerPayment: PartnerPayment, contract: Contract |
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

**tblPartnerPayment:**
| Column | Type | Constraint |
|--------|------|------------|
| partnerPaymentId | INT | PK |
| partnerId | INT | FK → tblPartner |
| paymentDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |

**tblPartnerPaymentDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| detailId | INT | PK |
| partnerPaymentId | INT | FK → tblPartnerPayment |
| contractId | INT | FK → tblContract |
| amount | DECIMAL(15,2) | NOT NULL |

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
