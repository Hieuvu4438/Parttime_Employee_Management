# Subject No. 65 — Costume Rental — Module "Statistics of costumes"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Thống kê trang phục

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Costume statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách trang phục: mã, tên, mẫu, thể loại, tổng số lần thuê, tổng tiền thu. Sắp xếp: số lần thuê giảm dần, sau đó tổng tiền giảm dần. |
| 5 | Staff nhấn vào "Váy công chúa". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: mã HĐ, tên người thuê, ngày thuê, ngày trả, tổng tiền. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Costume | id, code, name, category, model, quantity, price |
| Customer | id, code, name, phone |
| Rental | id, customerId, rentalDate, deposit, status |
| RentalDetail | id, rentalId, costumeId, quantity, pricePerDay, status |
| ReturnDetail | id, rentalDetailId, returnDate, daysBorrowed, rentalAmount, fine |
| Payment | id, rentalId, paymentDate, totalAmount |
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
- Bước 3: Tạo các view class box từ giao diện: HomeView, CostumeStatFrm
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Costume`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-costumeId: int`, `-price: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+getCostumeStat(startDate: Date, endDate: Date): List<CostumeStat>`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Costume | <<entity>> | -costumeId: int, -code: String, -name: String, -category: String, -model: String, -quantity: int, -price: double | +getCostumeStat(startDate: Date, endDate: Date): List<CostumeStat> |
| Customer | <<entity>> | -customerId: int, -code: String, -name: String, -phone: String | |
| Rental | <<entity>> | -rentalId: int, -customer: Customer, -rentalDate: Date, -deposit: double, -status: String | +getCostumeInvoices(costumeId: int, startDate: Date, endDate: Date): List<CostumeInvoice> |
| RentalDetail | <<entity>> | -detailId: int, -rental: Rental, -costume: Costume, -quantity: int, -pricePerDay: double, -status: String | |
| ReturnDetail | <<entity>> | -returnDetailId: int, -rentalDetail: RentalDetail, -returnDate: Date, -daysBorrowed: int, -rentalAmount: double, -fine: double | |
| Payment | <<entity>> | -paymentId: int, -rental: Rental, -paymentDate: Date, -totalAmount: double | |
| User | <<entity>> | -userId: int, -username: String, -password: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| HomeView | subCostumeStat: JButton (nút chọn Costume statistics) |
| CostumeStatFrm | inStartDate: JTextField (ô nhập ngày bắt đầu), inEndDate: JTextField (ô nhập ngày kết thúc), subView: JButton (nút View), outCostumeTable: JTable (bảng thống kê trang phục click được: costumeId, name, model, category, totalLoans, totalRevenue), outInvoiceTable: JTable (bảng chi tiết hóa đơn: rentalSlipId, borrowerName, rentalDate, returnDate, totalAmount) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Rental
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Rental ◆→ RentalDetail
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: CostumeStatFrm ---> CostumeDAO

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
  tùy chọn thống kê trang phục → subCostumeStat

Chọn Statistics → Costume statistics → hiển thị CostumeStatFrm:
  ô nhập ngày bắt đầu → inStartDate
  ô nhập ngày kết thúc → inEndDate
  nút xem thống kê → subView

Nhập khoảng thời gian (01/01/2026 - 31/12/2026), nhấn View → hiển thị thống kê:
  bảng trang phục (click chọn) → outCostumeTable
  (cột: mã, tên, mẫu, thể loại, tổng số lần thuê, tổng tiền thu, sắp xếp theo số lần thuê giảm dần)

Click chọn trang phục "Váy công chúa" → hiển thị chi tiết:
  bảng hóa đơn chi tiết → outInvoiceTable
  (cột: mã HĐ, tên người thuê, ngày thuê, ngày trả, tổng tiền)

Click chọn trang phục khác → cập nhật bảng chi tiết hóa đơn

### Tóm tắt
View classes: HomeView, CostumeStatFrm
Methods: getCostumeStat(), getCostumeInvoices()

---

## Câu 3: Thiết kế tĩnh

### View classes

**CostumeStatFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outCostumeTable`: bảng trang phục (click được)
- `outInvoiceTable`: bảng hóa đơn chi tiết

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CostumeDAO | `getCostumeStat(startDate, endDate): List<CostumeStat>` |
| RentalDAO | `getCostumeInvoices(costumeId, startDate, endDate): List<CostumeInvoice>` |

**CostumeStat:** costume, totalLoans, totalRevenue

**CostumeInvoice:** id, borrowerName, rentalDate, returnDate, totalAmount

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Costume | Model | id: int, code: String, name: String, category: String, model: String, quantity: int, price: double |
| Customer | Model | id: int, code: String, name: String, phone: String |
| Rental | Model | id: int, customer: Customer, rentalDate: Date, deposit: double, status: String |
| RentalDetail | Model | id: int, rental: Rental, costume: Costume, quantity: int, pricePerDay: double, status: String |
| ReturnDetail | Model | id: int, rentalDetail: RentalDetail, returnDate: Date, daysBorrowed: int, rentalAmount: double, fine: double |
| Payment | Model | id: int, rental: Rental, paymentDate: Date, totalAmount: double |
| User | Model | id: int, username: String, password: String, role: String |

### Database Design

**tblCostume:**

| Column | Type | Constraint |
|--------|------|------------|
| costumeId | VARCHAR(10) | PRIMARY KEY |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | NVARCHAR(100) | NOT NULL |
| category | NVARCHAR(50) | |
| model | NVARCHAR(50) | |
| quantity | INT | DEFAULT 0 |
| price | DECIMAL(15,2) | NOT NULL |

**tblCustomer:**

| Column | Type | Constraint |
|--------|------|------------|
| customerId | VARCHAR(10) | PRIMARY KEY |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | NVARCHAR(100) | NOT NULL |
| phone | VARCHAR(15) | |

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

**tblPayment:**

| Column | Type | Constraint |
|--------|------|------------|
| paymentId | VARCHAR(10) | PRIMARY KEY |
| rentalId | VARCHAR(10) | FOREIGN KEY → tblRental(rentalId) |
| paymentDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |

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
2. `:CostumeStatFrm` — boundary
3. `:CostumeDAO` — control
4. `:RentalDAO` — control

**Message flow:**

1. Staff → CostumeStatFrm: `enterDates(01/01/2026, 31/12/2026)` + `clickView()`
2. CostumeStatFrm → CostumeDAO: `getCostumeStat(dates)`
3. CostumeDAO → CostumeStatFrm: return `List<CostumeStat>`
4. Staff → CostumeStatFrm: `clickCostume(Váy công chúa)`
5. CostumeStatFrm → RentalDAO: `getCostumeInvoices(costumeId, dates)`
6. RentalDAO → CostumeStatFrm: return `List<CostumeInvoice>`

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê trang phục có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: Váy công chúa (50 lần, 5,000,000đ), Áo Superman (40 lần, 3,600,000đ) |
| 2 | Nhấn Váy công chúa | Chi tiết: HD001 (Tran Thi B, 01/07, 06/07, 500,000đ), HD005... |

**Database sau:** Không thay đổi (chỉ đọc).
