# Subject No. 64 — Costume Rental — Module "Customer returns and pays"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Trả trang phục và thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Customer returns and pays**. |
| 2 | Giao diện: ô nhập tên khách hàng, nút Search. |
| 3 | Staff nhập "Tran Thi B", Search. Hệ thống hiển thị danh sách KH. |
| 4 | Staff click "Tran Thi B". Hệ thống hiển thị danh sách trang phục đang thuê. |
| 5 | Bảng: Váy công chúa × 2, ngày thuê 01/07, giá 50,000đ/ngày, số ngày thuê 5, tiền thuê 500,000đ. Áo Superman × 3, ngày thuê 01/07, giá 30,000đ/ngày, số ngày 5, tiền thuê 450,000đ. |
| 6 | Staff tick chọn Váy công chúa (trả), trạng thái "Bình thường", phạt 0đ. |
| 7 | Staff tick chọn Áo Superman (trả), trạng thái "Rách 1 áo", phạt 300,000đ. |
| 8 | Staff nhấn **Payment**. Hệ thống hiển thị hóa đơn: tổng thuê 950,000đ + phạt 300,000đ = 1,250,000đ. Cọc 1,900,000đ. KH nhận lại 650,000đ. |
| 9 | Staff nhấn **Confirm**. Hệ thống cập nhật database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Costume | id, code, name, category, quantity, price |
| Customer | id, code, name, phone, address |
| Rental | id, customerId, rentalDate, deposit, status |
| RentalDetail | id, rentalId, costumeId, quantity, pricePerDay, status |
| ReturnDetail | id, rentalDetailId, returnDate, daysBorrowed, rentalAmount, fine, condition |
| Payment | id, rentalId, paymentDate, totalAmount, depositReturned, customerPay |
| User | id, username, password, role |

### Quan hệ

```
Customer 1 --- n Rental
Rental 1 --- n RentalDetail n --- 1 Costume
RentalDetail 1 --- n ReturnDetail
Rental 1 --- n Payment
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Costume, Customer, Rental, RentalDetail, ReturnDetail, Payment, User
- Bước 3: Tạo các view class box từ giao diện: HomeView, ReturnCostumeFrm
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> ReturnDetail`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-returnDetailId: int`, `-fine: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+addReturnDetail(detail: ReturnDetail): boolean`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Costume | <<entity>> | -costumeId: int, -code: String, -name: String, -category: String, -quantity: int, -price: double | +updateCostumeQuantity(costumeId: int, qty: int): boolean |
| Customer | <<entity>> | -customerId: int, -code: String, -name: String, -phone: String, -address: String | +searchCustomerByName(keyword: String): List<Customer> |
| Rental | <<entity>> | -rentalId: int, -customer: Customer, -rentalDate: Date, -deposit: double, -status: String | +getActiveRentals(customerId: int): List<Rental>, +updateRentalStatus(rentalId: int, status: String): boolean |
| RentalDetail | <<entity>> | -detailId: int, -rental: Rental, -costume: Costume, -quantity: int, -pricePerDay: double, -status: String | +getRentalDetails(rentalId: int): List<RentalDetail> |
| ReturnDetail | <<entity>> | -returnDetailId: int, -rentalDetail: RentalDetail, -returnDate: Date, -daysBorrowed: int, -rentalAmount: double, -fine: double, -condition: String | +addReturnDetail(detail: ReturnDetail): boolean |
| Payment | <<entity>> | -paymentId: int, -rental: Rental, -paymentDate: Date, -totalAmount: double, -depositReturned: double, -customerPay: double | +createPayment(payment: Payment): boolean |
| User | <<entity>> | -userId: int, -username: String, -password: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeView | subReturnCostume: JButton (nút chọn Customer returns and pays) |
| ReturnCostumeFrm | inCustomerName: JTextField (ô nhập tên KH), subSearch: JButton (nút Search), outCustomerList: JTable (danh sách KH click được), outRentalTable: JTable (bảng trang phục đang thuê + checkbox), inCondition: JComboBox (combobox trạng thái: Bình thường/Hỏng/Rách), inFine: JTextField (ô nhập tiền phạt), outInvoice: JPanel (hóa đơn), outTotal: JLabel (tổng tiền thuê + phạt), outDeposit: JLabel (tiền cọc), outRefund: JLabel (tiền trả lại KH), subPayment: JButton (nút Payment), subConfirm: JButton (nút Confirm) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Rental
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Rental ◆→ RentalDetail
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: ReturnCostumeFrm ---> ReturnDetailDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Customer (1) --- (n) Rental

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Customer | Rental | Association | 1 : n | Một khách hàng có nhiều phiếu thuê |
| Rental | RentalDetail | Composition | 1 : n | Phiếu thuê chứa nhiều chi tiết trang phục |
| Costume | RentalDetail | Association | 1 : n | Một trang phục xuất hiện trong nhiều chi tiết thuê |
| RentalDetail | ReturnDetail | Composition | 1 : n | Chi tiết thuê có nhiều chi tiết trả |
| Rental | Payment | Association | 1 : n | Một phiếu thuê có nhiều phiếu thanh toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition Rental ◆→ RentalDetail:
- Kéo class Rental vào canvas, kéo class RentalDetail vào bên phải
- Chọn công cụ **Composition**, click vào Rental rồi kéo sang RentalDetail
- Kim cương filled nằm ở phía Rental (parent)
- Đặt Multiplicity: Rental "1", RentalDetail "n"

Ví dụ 2 — Vẽ quan hệ Association Customer → Rental (1:n):
- Kéo class Customer và Rental vào canvas
- Chọn công cụ **Association**, click vào Customer kéo sang Rental
- Đặt Multiplicity: Customer "1", Rental "n"

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeView:
  tùy chọn trả trang phục và thanh toán → subReturnCostume

Chọn Customer returns and pays → hiển thị ReturnCostumeFrm:
  ô nhập tên khách hàng → inCustomerName
  nút tìm kiếm → subSearch

Nhập "Tran Thi B", nhấn Search → hiển thị danh sách khách hàng:
  bảng khách hàng (click chọn) → outCustomerList

Click chọn khách hàng "Tran Thi B" → hiển thị trang phục đang thuê:
  bảng trang phục đang thuê (có checkbox) → outRentalTable

Tick chọn trang phục trả, nhập trạng thái và tiền phạt:
  combobox trạng thái (Bình thường/Hỏng/Rách) → inCondition
  ô nhập tiền phạt → inFine

Nhấn Payment → hiển thị hóa đơn:
  hóa đơn → outInvoice
  tổng tiền thuê + phạt → outTotal
  tiền cọc → outDeposit
  tiền trả lại khách hàng → outRefund

Nhấn Confirm → subConfirm
  hệ thống lưu chi tiết trả, tạo thanh toán, cập nhật trạng thái phiếu thuê, cập nhật tồn kho

### Tóm tắt
View classes: HomeView, ReturnCostumeFrm
Methods: searchCustomerByName(), getActiveRentals(), getRentalDetails(), addReturnDetail(), createPayment(), updateRentalStatus(), updateCostumeQuantity()

---

## Câu 3: Thiết kế tĩnh

### View classes

**ReturnCostumeFrm:**
- `inCustomerName`: ô nhập tên KH
- `subSearch`: nút Search
- `outCustomerList`: danh sách KH (click được)
- `outRentalTable`: bảng trang phục đang thuê (checkbox)
- `inCondition`: combobox trạng thái (Bình thường/Hỏng/Rách)
- `inFine`: ô nhập tiền phạt
- `outInvoice`: hóa đơn
- `outTotal`: tổng tiền thuê + phạt
- `outDeposit`: tiền cọc
- `outRefund`: tiền trả lại KH
- `subPayment`: nút Payment
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchCustomerByName(keyword): List<Customer>` |
| RentalDAO | `getActiveRentals(customerId): List<Rental>` |
| RentalDetailDAO | `getRentalDetails(rentalId): List<RentalDetail>` |
| ReturnDetailDAO | `addReturnDetail(returnDetail): boolean` |
| PaymentDAO | `createPayment(payment): boolean` |
| RentalDAO | `updateRentalStatus(rentalId, status): boolean` |
| CostumeDAO | `updateCostumeQuantity(costumeId, +quantity): boolean` |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Costume | Model | id: int, code: String, name: String, category: String, quantity: int, price: double |
| Customer | Model | id: int, code: String, name: String, phone: String, address: String |
| Rental | Model | id: int, customer: Customer, rentalDate: Date, deposit: double, status: String |
| RentalDetail | Model | id: int, rental: Rental, costume: Costume, quantity: int, pricePerDay: double, status: String |
| ReturnDetail | Model | id: int, rentalDetail: RentalDetail, returnDate: Date, daysBorrowed: int, rentalAmount: double, fine: double, condition: String |
| Payment | Model | id: int, rental: Rental, paymentDate: Date, totalAmount: double, depositReturned: double, customerPay: double |
| User | Model | id: int, username: String, password: String, role: String |

### Database Design

**tblCostume:**

| Column | Type | Constraint |
|--------|------|------------|
| costumeId | VARCHAR(10) | PRIMARY KEY |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | NVARCHAR(100) | NOT NULL |
| category | NVARCHAR(50) | |
| quantity | INT | DEFAULT 0 |
| price | DECIMAL(15,2) | NOT NULL |

**tblCustomer:**

| Column | Type | Constraint |
|--------|------|------------|
| customerId | VARCHAR(10) | PRIMARY KEY |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | NVARCHAR(100) | NOT NULL |
| phone | VARCHAR(15) | |
| address | NVARCHAR(200) | |

**tblRental:**

| Column | Type | Constraint |
|--------|------|------------|
| rentalId | VARCHAR(10) | PRIMARY KEY |
| customerId | VARCHAR(10) | FOREIGN KEY → tblCustomer(customerId) |
| rentalDate | DATE | NOT NULL |
| deposit | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblRentalDetail:**

| Column | Type | Constraint |
|--------|------|------------|
| detailId | VARCHAR(10) | PRIMARY KEY |
| rentalId | VARCHAR(10) | FOREIGN KEY → tblRental(rentalId) |
| costumeId | VARCHAR(10) | FOREIGN KEY → tblCostume(costumeId) |
| quantity | INT | NOT NULL |
| pricePerDay | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**tblReturnDetail:**

| Column | Type | Constraint |
|--------|------|------------|
| returnDetailId | VARCHAR(10) | PRIMARY KEY |
| rentalDetailId | VARCHAR(10) | FOREIGN KEY → tblRentalDetail(detailId) |
| returnDate | DATE | NOT NULL |
| daysBorrowed | INT | NOT NULL |
| rentalAmount | DECIMAL(15,2) | NOT NULL |
| fine | DECIMAL(15,2) | DEFAULT 0 |
| condition | NVARCHAR(50) | |

**tblPayment:**

| Column | Type | Constraint |
|--------|------|------------|
| paymentId | VARCHAR(10) | PRIMARY KEY |
| rentalId | VARCHAR(10) | FOREIGN KEY → tblRental(rentalId) |
| paymentDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |
| depositReturned | DECIMAL(15,2) | |
| customerPay | DECIMAL(15,2) | NOT NULL |

**tblUser:**

| Column | Type | Constraint |
|--------|------|------------|
| userId | VARCHAR(10) | PRIMARY KEY |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:ReturnCostumeFrm` — boundary
3. `:CustomerDAO` — control
4. `:RentalDAO` — control
5. `:ReturnDetailDAO` — control
6. `:PaymentDAO` — control

**Message flow:**

1. Staff → ReturnCostumeFrm: `enterCustomerName("Tran Thi B")` + `clickSearch()`
2. ReturnCostumeFrm → CustomerDAO: `searchCustomerByName("Tran Thi B")`
3. CustomerDAO → ReturnCostumeFrm: return `List<Customer>`
4. Staff → ReturnCostumeFrm: `clickCustomer(B)`
5. ReturnCostumeFrm → RentalDAO: `getActiveRentals(customerId)`
6. RentalDAO → ReturnCostumeFrm: return `List<Rental>`
7. Staff → ReturnCostumeFrm: `selectReturn(Váy, condition="Bình thường", fine=0)` + `selectReturn(Áo, condition="Rách", fine=300000)`
8. Staff → ReturnCostumeFrm: `clickPayment()`
9. ReturnCostumeFrm: display invoice (950000 + 300000 - 1900000 = -650000 → trả lại KH)
10. Staff → ReturnCostumeFrm: `clickConfirm()`
11. ReturnCostumeFrm → ReturnDetailDAO: `addReturnDetails(details)`
12. ReturnCostumeFrm → PaymentDAO: `createPayment(payment)`
13. ReturnCostumeFrm → RentalDAO: `updateRentalStatus(rentalId, "returned")`
14. ReturnCostumeFrm → CostumeDAO: `updateCostumeQuantity(costumeId, +quantity)`

---

## Câu 5: Blackbox Testcase

### TC01: Trả trang phục thành công

**Database trước:**
- tblRental: 1 dòng (deposit=1900000, status="renting")
- tblRentalDetail: Váy × 2, Áo × 3
- tblPayment: 0 dòng

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "Tran Thi B" → Search, click KH | Danh sách trang phục đang thuê |
| 2 | Tick Váy (Bình thường, 0đ), Áo (Rách, 300,000đ) | Hóa đơn: 950,000 + 300,000 = 1,250,000đ |
| 3 | Payment | Hóa đơn: tổng 1,250,000đ, cọc 1,900,000đ, trả lại KH 650,000đ |
| 4 | Confirm | "Tra trang phuc thanh cong" |

**Database sau:**
- tblRental: status = "returned"
- tblReturnDetail: thêm 2 dòng
- tblPayment: thêm 1 dòng (totalAmount=1250000, depositReturned=650000)
- tblCostume: Váy (+2), Áo (+3)
