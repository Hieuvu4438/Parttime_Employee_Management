# Subject No. 66 — Costume Rental — Module "Revenue Statistics"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Thống kê doanh thu

### Mô tả ngôn ngữ tự nhiên

Module "Revenue Statistics" cho phép nhân viên (staff) xem thống kê doanh thu theo các kỳ thời gian khác nhau (tháng, quý, năm). Staff chọn menu thống kê doanh thu, hệ thống hiển thị hộp chọn loại thống kê (tháng/quý/năm). Staff chọn "Theo tháng", hệ thống hiển thị bảng thống kê doanh thu theo tháng, mỗi dòng là một tháng với tên tháng và tổng doanh thu, sắp xếp theo thứ tự thời gian từ gần nhất đến xa nhất. Staff click vào một dòng tháng, hệ thống hiển thị chi tiết các hóa đơn trong tháng đó: id hóa đơn, tên khách hàng, ngày mượn, tổng số trang phục đã mượn, tổng tiền hóa đơn.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống (username: `staff01`, password: `******`) |
| 2 | Giao diện Home hiển thị. Staff chọn menu **Revenue Statistics** |
| 3 | Hệ thống hiển thị giao diện thống kê: combobox chọn loại thống kê (`cboStatType`: Tháng / Quý / Năm) |
| 4 | Staff chọn **Tháng** trong combobox |
| 5 | Hệ thống truy vấn database, tổng hợp doanh thu theo tháng từ bảng tblPayment |
| 6 | Hệ thống hiển thị bảng thống kê doanh thu theo tháng: |
|    | -- 07/2026: 8,500,000đ |
|    | -- 06/2026: 7,200,000đ |
|    | -- 05/2026: 6,800,000đ |
|    | -- 04/2026: 5,500,000đ |
|    | -- 03/2026: 4,200,000đ |
|    | -- Sắp xếp theo thời gian từ gần nhất đến xa nhất |
| 7 | Staff click vào dòng **07/2026** |
| 8 | Hệ thống hiển thị bảng chi tiết hóa đơn trong tháng 07/2026: |
|    | -- RS001, Nguyen Thi A, 01/07, 3 trang phục, 1,100,000đ |
|    | -- RS002, Tran Thi B, 05/07, 2 trang phục, 800,000đ |
|    | -- RS008, Le Thi C, 15/07, 5 trang phục, 2,500,000đ |
|    | -- RS012, Pham Thi D, 22/07, 4 trang phục, 1,800,000đ |
|    | -- RS015, Nguyen Thi A, 28/07, 2 trang phục, 900,000đ |
| 9 | Staff click vào dòng khác (ví dụ 06/2026) để xem chi tiết tháng đó |
| 10 | Staff có thể thay đổi combobox sang **Quý** hoặc **Năm** để xem thống kê theo kỳ khác |

### Kịch bản ngoại lệ

| Ngoại lệ | Mô tả | Xử lý |
|----------|-------|-------|
| EL1 | Không có dữ liệu thanh toán trong hệ thống | Bảng thống kê hiển thị trống, thông báo "Khong co du lieu thong ke" |
| EL2 | Tháng được chọn không có hóa đơn nào | Bảng chi tiết hiển thị trống |
| EL3 | Combobox chưa chọn loại thống kê | Nút xem thống kê bị vô hiệu hóa |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê trang phục. Cửa hàng có nhiều loại trang phục. Khách hàng thuê trang phục, mỗi lần thuê tạo phiếu thuê chứa nhiều chi tiết trang phục. Khi trả trang phục, hệ thống tạo phiếu thanh toán (Payment) ghi nhận tổng tiền, tiền cọc, số tiền đã trả. Nhân viên thao tác trên hệ thống. Chức năng thống kê doanh thu sử dụng dữ liệu từ bảng Payment (ngày thanh toán, tổng tiền) kết hợp với RentalSlip (khách hàng, ngày thuê) và RentalSlipDetail (số lượng trang phục).

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Costume (trang phục) | Entity class | Đối tượng cho thuê |
| RentalSlip (phiếu thuê) | Entity class | Chứng từ mỗi lần thuê |
| RentalSlipDetail (chi tiết phiếu thuê) | Entity class | Chi tiết từng loại trang phục |
| Customer (khách hàng) | Entity class | Người thuê trang phục |
| Payment (phiếu thanh toán) | Entity class | Chứng từ thanh toán khi trả |
| User (nhân viên) | Entity class | Người thao tác hệ thống |
| paymentDate, totalAmount, deposit | Thuộc tính của Payment | Thông tin thanh toán |
| customerName, phone | Thuộc tính của Customer | Thông tin khách hàng |
| rentalDate, totalAmount | Thuộc tính của RentalSlip | Thông tin phiếu thuê |
| quantity, rentalAmount | Thuộc tính của RentalSlipDetail | Chi tiết thuê |

### Bảng thuộc tính entity

| Entity | Thuộc tính |
|--------|-----------|
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, returnDate, rentalDays, rentalAmount, status, fine |
| **Customer** | customerId (PK), customerName, phone, email, address |
| **Payment** | paymentId (PK), rentalSlipId (FK), paymentDate, totalAmount, deposit, amountPaid, note |
| **User** | userId (PK), username, password, fullName, role |

### Class Diagram (ASCII)

```
+------------------+       +-------------------+       +------------------+
|    Customer      |       |   RentalSlip      |       |      User        |
+------------------+       +-------------------+       +------------------+
| -customerId: int |       | -rentalSlipId: int|       | -userId: int     |
| -customerName: Str|  1  | -customerId: FK   |  1   | -username: Str   |
| -phone: Str      |------| -userId: FK       |------| -password: Str   |
| -email: Str      |   *  | -rentalDate: Date |   *  | -fullName: Str   |
| -address: Str    |       | -deposit: double  |       | -role: Str       |
+------------------+       | -totalAmount: dbl |       +------------------+
                           +-------------------+
                                   | 1                    | 1
                                   |                      |
                                   | *                    | 0..1
                           +-----------------------+  +-------------------+
                           |  RentalSlipDetail     |  |    Payment        |
                           +-----------------------+  +-------------------+
                           | -detailId: int        |  | -paymentId: int   |
                           | -rentalSlipId: FK     |  | -rentalSlipId: FK |
                           | -costumeId: FK        |  | -paymentDate: Date|
                           | -quantity: int        |  | -totalAmount: dbl |
                           | -rentalDate: Date     |  | -deposit: double  |
                           | -returnDate: Date     |  | -amountPaid: dbl  |
                           | -rentalDays: int      |  | -note: Str        |
                           | -rentalAmount: double |  +-------------------+
                           | -status: Str          |
                           | -fine: double         |
                           +-----------------------+
                                   | *
                                   |
                                   | 1
                           +------------------+
                           |    Costume       |
                           +------------------+
                           | -costumeId: int  |
                           | -costumeName: Str|
                           | -model: Str      |
                           | -genre: Str      |
                           | -rentalPricePD: dbl
                           | -status: Str     |
                           +------------------+
```

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu | Giải thích |
|-----------|----------|------|------------|
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu thuê có nhiều chi tiết trang phục |
| Costume | RentalSlipDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết |
| RentalSlip | Payment | 1 : 0..1 | Một phiếu thuê có tối đa 1 phiếu thanh toán |
| User | RentalSlip | 1 : N | Một nhân viên tạo nhiều phiếu thuê |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**
- Bước 1: Mở Visual Paradigm → File → New → chọn **Class Diagram**
- Bước 2: Tạo các entity class box (hình chữ nhật 3 ngăn) cho: Costume, RentalSlip, RentalSlipDetail, Customer, Payment, User
- Bước 3: Tạo các view class box từ giao diện: CostumeStatRevenueFrm
- Bước 4: Vẽ các đường quan hệ (association, composition) giữa các class
- Bước 5: Ghi multiplicity (1, n) và role name ở hai đầu đường kết nối

**2. Cấu trúc 1 class box (3 ngăn):**
- Ngăn 1 (tên class): ghi stereotype `<<entity>>` hoặc `<<boundary>>` + tên class. Ví dụ: `<<entity>> Payment`
- Ngăn 2 (thuộc tính): ghi theo định dạng `-attributeName: Type`. Ví dụ: `-paymentId: int`, `-totalAmount: double`
- Ngăn 3 (phương thức): ghi theo định dạng `+methodName(params): ReturnType`. Ví dụ: `+getRevenueByMonth(): List<RevenueStatDTO>`

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Costume | <<entity>> | -costumeId: int, -costumeName: String, -model: String, -genre: String, -rentalPricePerDay: double, -status: String | |
| RentalSlip | <<entity>> | -rentalSlipId: int, -customer: Customer, -staff: User, -rentalDate: Date, -deposit: double, -totalAmount: double | +getSlipsByMonth(year: int, month: int): List<SlipDetailDTO> |
| RentalSlipDetail | <<entity>> | -detailId: int, -rentalSlip: RentalSlip, -costume: Costume, -quantity: int, -rentalDate: Date, -returnDate: Date, -rentalDays: int, -rentalAmount: double, -status: String, -fine: double | |
| Customer | <<entity>> | -customerId: int, -customerName: String, -phone: String, -email: String, -address: String | |
| Payment | <<entity>> | -paymentId: int, -rentalSlip: RentalSlip, -paymentDate: Date, -totalAmount: double, -deposit: double, -amountPaid: double, -note: String | +getRevenueByMonth(): List<RevenueStatDTO>, +getRevenueByQuarter(): List<RevenueStatDTO>, +getRevenueByYear(): List<RevenueStatDTO> |
| User | <<entity>> | -userId: int, -username: String, -password: String, -fullName: String, -role: String | |

**4. Bảng chi tiết view classes:**

| View Class | UI Elements |
|------------|-------------|
| CostumeStatRevenueFrm | inStatType: JComboBox (combobox chọn Tháng/Quý/Năm), subView: JButton (nút xem thống kê), outStatTable: JTable (bảng doanh thu theo kỳ: periodName, totalRevenue), outDetailTable: JTable (bảng chi tiết hóa đơn: rentalSlipId, customerName, borrowedDate, totalCostumes, totalAmount) |

**5. Cách vẽ quan hệ:**
- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → RentalSlip
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: RentalSlip ◆→ RentalSlipDetail
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: CostumeStatRevenueFrm ---> PaymentDAO

**6. Cách ghi multiplicity:**
- 1..1 → ghi "1" ở một đầu
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia
- Ghi ở cả 2 đầu của đường kết nối. Ví dụ: Customer (1) --- (n) RentalSlip

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|-------------|------------|
| Customer | RentalSlip | Association | 1 : n | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | Composition | 1 : n | Phiếu thuê chứa nhiều chi tiết trang phục |
| Costume | RentalSlipDetail | Association | 1 : n | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| RentalSlip | Payment | Association | 1 : 0..1 | Một phiếu thuê có tối đa 1 phiếu thanh toán |
| User | RentalSlip | Association | 1 : n | Một nhân viên tạo nhiều phiếu thuê |

**8. Ví dụ cụ thể trên Visual Paradigm:**

Ví dụ 1 — Vẽ quan hệ Composition RentalSlip ◆→ RentalSlipDetail:
- Kéo class RentalSlip vào canvas, kéo class RentalSlipDetail vào bên phải
- Chọn công cụ **Composition**, click vào RentalSlip rồi kéo sang RentalSlipDetail
- Kim cương filled nằm ở phía RentalSlip (parent)
- Đặt Multiplicity: RentalSlip "1", RentalSlipDetail "n"

Ví dụ 2 — Vẽ quan hệ Association RentalSlip → Payment (1:0..1):
- Kéo class RentalSlip và Payment vào canvas
- Chọn công cụ **Association**, click vào RentalSlip kéo sang Payment
- Đặt Multiplicity: RentalSlip "1", Payment "0..1"

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeView:
  tùy chọn thống kê doanh thu → subRevenueStat

Chọn Revenue Statistics → hiển thị CostumeStatRevenueFrm:
  combobox chọn loại thống kê (Tháng/Quý/Năm) → inStatType
  nút xem thống kê → subView

Chọn "Tháng" trong combobox → hiển thị thống kê:
  bảng doanh thu theo tháng (click chọn) → outStatTable
  (cột: tên tháng, tổng doanh thu, sắp xếp từ gần nhất đến xa nhất)

Click chọn tháng "07/2026" → hiển thị chi tiết:
  bảng chi tiết hóa đơn → outDetailTable
  (cột: mã HĐ, tên khách hàng, ngày mượn, tổng số trang phục, tổng tiền)

Click chọn tháng khác → cập nhật bảng chi tiết hóa đơn

Thay đổi combobox sang "Quý" hoặc "Năm" → cập nhật bảng thống kê tương ứng

### Tóm tắt
View classes: HomeView, CostumeStatRevenueFrm
Methods: getRevenueByMonth(), getRevenueByQuarter(), getRevenueByYear(), getSlipsByMonth(), getSlipsByQuarter(), getSlipsByYear()

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `CostumeStatRevenueFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inStatType | JComboBox | Combobox chọn kiểu thống kê: "Thang" / "Quy" / "Nam" |
| subView | JButton | Nút xem thống kê (tự kích hoạt khi chọn combobox) |
| outStatTable | JTable | Bảng thống kê doanh thu theo kỳ. Cột: periodName, totalRevenue. Sắp xếp từ gần nhất đến xa nhất |
| outDetailTable | JTable | Bảng chi tiết hóa đơn khi click vào 1 dòng. Cột: rentalSlipId, customerName, borrowedDate, totalCostumes, totalAmount |

### DAO Classes

**PaymentDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getRevenueByMonth() | List\<RevenueStatDTO\> | Thống kê doanh thu theo tháng: SELECT MONTH(paymentDate), YEAR(paymentDate), SUM(totalAmount) FROM tblPayment GROUP BY YEAR, MONTH ORDER BY YEAR DESC, MONTH DESC |
| getRevenueByQuarter() | List\<RevenueStatDTO\> | Thống kê doanh thu theo quý: GROUP BY YEAR, QUARTER |
| getRevenueByYear() | List\<RevenueStatDTO\> | Thống kê doanh thu theo năm: GROUP BY YEAR |

**RentalSlipDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getSlipsByMonth(int year, int month) | List\<SlipDetailDTO\> | Lấy chi tiết hóa đơn trong 1 tháng: rentalSlipId, customerName (JOIN tblCustomer), rentalDate, COUNT(detailId) AS totalCostumes, SUM(totalAmount). JOIN tblRentalSlipDetail để đếm số trang phục |
| getSlipsByQuarter(int year, int quarter) | List\<SlipDetailDTO\> | Lấy chi tiết hóa đơn trong 1 quý |
| getSlipsByYear(int year) | List\<SlipDetailDTO\> | Lấy chi tiết hóa đơn trong 1 năm |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Costume | Model | id: int, costumeName: String, model: String, genre: String, rentalPricePerDay: double, status: String |
| Customer | Model | id: int, customerName: String, phone: String, email: String, address: String |
| RentalSlip | Model | id: int, customer: Customer, user: User, rentalDate: Date, deposit: double, totalAmount: double |
| RentalSlipDetail | Model | id: int, rentalSlip: RentalSlip, costume: Costume, quantity: int, rentalDate: Date, returnDate: Date, rentalDays: int, rentalAmount: double, status: String, fine: double |
| Payment | Model | id: int, rentalSlip: RentalSlip, paymentDate: Date, totalAmount: double, deposit: double, amountPaid: double, note: String |
| User | Model | id: int, username: String, password: String, fullName: String, role: String |

### Database Design

**tblPayment:**

| Column | Type | Constraint |
|--------|------|------------|
| paymentId | VARCHAR(10) | PRIMARY KEY |
| rentalSlipId | VARCHAR(10) | FOREIGN KEY → tblRentalSlip(rentalSlipId) |
| paymentDate | DATE | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |
| deposit | DECIMAL(15,2) | |
| amountPaid | DECIMAL(15,2) | NOT NULL |
| note | NVARCHAR(255) | |

**tblRentalSlip:**

| Column | Type | Constraint |
|--------|------|------------|
| rentalSlipId | VARCHAR(10) | PRIMARY KEY |
| customerId | VARCHAR(10) | FOREIGN KEY → tblCustomer(customerId) |
| userId | VARCHAR(10) | FOREIGN KEY → tblUser(userId) |
| rentalDate | DATE | NOT NULL |
| deposit | DECIMAL(15,2) | NOT NULL |
| totalAmount | DECIMAL(15,2) | NOT NULL |

**tblRentalSlipDetail:**

| Column | Type | Constraint |
|--------|------|------------|
| detailId | VARCHAR(10) | PRIMARY KEY |
| rentalSlipId | VARCHAR(10) | FOREIGN KEY → tblRentalSlip(rentalSlipId) |
| costumeId | VARCHAR(10) | FOREIGN KEY → tblCostume(costumeId) |
| quantity | INT | NOT NULL |
| rentalDate | DATE | NOT NULL |
| returnDate | DATE | |
| rentalDays | INT | NOT NULL |
| rentalAmount | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |
| fine | DECIMAL(15,2) | DEFAULT 0 |

**tblCustomer:**

| Column | Type | Constraint |
|--------|------|------------|
| customerId | VARCHAR(10) | PRIMARY KEY |
| customerName | NVARCHAR(100) | NOT NULL |
| phone | VARCHAR(15) | |
| email | VARCHAR(100) | |
| address | NVARCHAR(200) | |

**tblCostume:**

| Column | Type | Constraint |
|--------|------|------------|
| costumeId | VARCHAR(10) | PRIMARY KEY |
| costumeName | NVARCHAR(100) | NOT NULL |
| model | NVARCHAR(50) | |
| genre | NVARCHAR(50) | |
| rentalPricePerDay | DECIMAL(15,2) | NOT NULL |
| status | VARCHAR(20) | |

**tblUser:**

| Column | Type | Constraint |
|--------|------|------------|
| userId | VARCHAR(10) | PRIMARY KEY |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| fullName | NVARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package `view.statrevenue` chứa `CostumeStatRevenueFrm`
2. Tạo package `model` chứa: `Costume`, `RentalSlip`, `RentalSlipDetail`, `Customer`, `Payment`, `User`
3. Tạo package `dao` chứa: `PaymentDAO`, `RentalSlipDAO`
4. Vẽ association: CostumeStatRevenueFrm → PaymentDAO, RentalSlipDAO
5. PaymentDAO có 3 overloaded methods cho month/quarter/year
6. RentalSlipDAO có 3 overloaded methods cho month/quarter/year

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo các lifeline: `:Staff`, `:CostumeStatRevenueFrm`, `:PaymentDAO`, `:RentalSlipDAO`
2. Vẽ message flow theo bảng bên dưới
3. Sử dụng `return` message cho kết quả trả về
4. Sử dụng `alt` fragment cho 3 loại thống kê (tháng/quý/năm)

### Bảng chi tiết message flow

| STT | Từ | Đến | Message | Loại | Ghi chú |
|-----|-----|------|---------|------|---------|
| 1 | Staff | CostumeStatRevenueFrm | actionPerformed(e) | sync | Staff chọn menu Revenue Statistics |
| 2 | CostumeStatRevenueFrm | Staff | show() | return | Hiển thị giao diện: combobox chọn loại thống kê |
| 3 | Staff | CostumeStatRevenueFrm | cboStatType.setSelectedItem("Thang") | sync | Chọn "Tháng" trong combobox |
| 4 | CostumeStatRevenueFrm | CostumeStatRevenueFrm | onStatTypeChanged() | self | Xử lý sự kiện thay đổi combobox |
| 5 | CostumeStatRevenueFrm | PaymentDAO | getRevenueByMonth() | sync | Truy vấn doanh thu theo tháng |
| 6 | PaymentDAO | PaymentDAO | executeSQL(...) | self | SELECT YEAR(paymentDate) AS year, MONTH(paymentDate) AS month, SUM(totalAmount) AS totalRevenue FROM tblPayment GROUP BY YEAR(paymentDate), MONTH(paymentDate) ORDER BY year DESC, month DESC |
| 7 | PaymentDAO | CostumeStatRevenueFrm | return List\<RevenueStatDTO\> | return | Danh sách: 07/2026 (8.5M), 06/2026 (7.2M), 05/2026 (6.8M), ... |
| 8 | CostumeStatRevenueFrm | Staff | tblStat.setData(list) | return | Hiển thị bảng thống kê doanh thu theo tháng |
| 9 | Staff | CostumeStatRevenueFrm | tblStat.selectRow(0) | sync | Click vào dòng 07/2026 |
| 10 | CostumeStatRevenueFrm | CostumeStatRevenueFrm | parseSelectedPeriod() | self | Tách year=2026, month=7 từ dòng được chọn |
| 11 | CostumeStatRevenueFrm | RentalSlipDAO | getSlipsByMonth(2026, 7) | sync | Truy vấn chi tiết hóa đơn tháng 07/2026 |
| 12 | RentalSlipDAO | RentalSlipDAO | executeSQL(...) | self | SELECT r.rentalSlipId, c.customerName, r.rentalDate, COUNT(d.detailId) AS totalCostumes, p.totalAmount FROM tblRentalSlip r JOIN tblCustomer c ON r.customerId = c.customerId JOIN tblRentalSlipDetail d ON r.rentalSlipId = d.rentalSlipId JOIN tblPayment p ON r.rentalSlipId = p.rentalSlipId WHERE MONTH(p.paymentDate) = 7 AND YEAR(p.paymentDate) = 2026 GROUP BY r.rentalSlipId |
| 13 | RentalSlipDAO | CostumeStatRevenueFrm | return List\<SlipDetailDTO\> | return | Danh sách hóa đơn tháng 07 |
| 14 | CostumeStatRevenueFrm | Staff | tblDetail.setData(list) | return | Hiển thị bảng chi tiết hóa đơn |
| 15 | Staff | CostumeStatRevenueFrm | tblStat.selectRow(1) | sync | Click vào dòng 06/2026 |
| 16 | CostumeStatRevenueFrm | RentalSlipDAO | getSlipsByMonth(2026, 6) | sync | Truy vấn chi tiết tháng 06/2026 |
| 17 | RentalSlipDAO | CostumeStatRevenueFrm | return List\<SlipDetailDTO\> | return | Danh sách hóa đơn tháng 06 |
| 18 | CostumeStatRevenueFrm | Staff | tblDetail.setData(list) | return | Hiển thị bảng chi tiết hóa đơn tháng 06 |
| 19 | Staff | CostumeStatRevenueFrm | cboStatType.setSelectedItem("Quy") | sync | Thay đổi sang "Quý" |
| 20 | CostumeStatRevenueFrm | PaymentDAO | getRevenueByQuarter() | sync | Truy vấn doanh thu theo quý |
| 21 | PaymentDAO | CostumeStatRevenueFrm | return List\<RevenueStatDTO\> | return | Danh sách: Quy 3/2026 (8.5M), Quy 2/2026 (14M), ... |
| 22 | CostumeStatRevenueFrm | Staff | tblStat.setData(list) | return | Hiển thị bảng thống kê theo quý |

### ASCII Sequence Diagram

```
Staff    CostumeStatRevenueFrm    PaymentDAO    RentalSlipDAO
  |              |                      |              |
  |--selectMenu->|                      |              |
  |<--showFrm----|                      |              |
  |              |                      |              |
  |--selectMonth>|                      |              |
  |              |--getRevenueByMonth-->|              |
  |              |                      |--executeSQL  |
  |              |                      |--pack result |
  |              |<--List<RevenueStat>--|              |
  |<--showTable--|                      |              |
  |              |                      |              |
  |--click07/26->|                      |              |
  |              |--getSlipsByMonth(2026,7)----------->|
  |              |                      |     |--executeSQL
  |              |                      |     |--pack result
  |              |<--List<SlipDetail>------------------|
  |<--showDetail-|                      |              |
```

---

## Câu 5: Blackbox Testcase

### Bảng testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Revenue Statistics | TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn |
| 2 | Revenue Statistics | TC02: Thống kê doanh thu theo quý |
| 3 | Revenue Statistics | TC03: Tháng không có doanh thu |

---

### TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn

**Purpose:** Kiểm tra chức năng thống kê doanh thu theo tháng, hiển thị đúng thứ tự sắp xếp (gần nhất đến xa nhất), và xem chi tiết hóa đơn khi click vào một dòng tháng.

#### Database BEFORE

**tblPayment**

| paymentId | rentalSlipId | paymentDate | totalAmount | deposit | amountPaid | note |
|----------|-------------|------------|-----------|---------|-----------|------|
| P001 | RS001 | 2026-07-04 | 1,100,000 | 350,000 | 750,000 | Tra trang phuc |
| P002 | RS002 | 2026-07-08 | 800,000 | 400,000 | 400,000 | Tra trang phuc |
| P003 | RS003 | 2026-06-15 | 7,200,000 | 2,000,000 | 5,200,000 | Tra toan bo |
| P004 | RS004 | 2026-05-20 | 6,800,000 | 1,500,000 | 5,300,000 | Tra toan bo |
| P005 | RS005 | 2026-07-22 | 2,500,000 | 800,000 | 1,700,000 | Tra trang phuc |
| P006 | RS006 | 2026-06-28 | 1,800,000 | 600,000 | 1,200,000 | Tra toan bo |
| P007 | RS007 | 2026-05-10 | 3,200,000 | 1,000,000 | 2,200,000 | Tra toan bo |
| P008 | RS008 | 2026-04-05 | 5,500,000 | 1,200,000 | 4,300,000 | Tra toan bo |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | deposit | totalAmount |
|-------------|-----------|--------|-----------|---------|-------------|
| RS001 | C001 | U001 | 2026-07-01 | 350,000 | 1,050,000 |
| RS002 | C002 | U001 | 2026-07-05 | 400,000 | 800,000 |
| RS003 | C003 | U002 | 2026-06-10 | 2,000,000 | 7,200,000 |
| RS004 | C004 | U002 | 2026-05-15 | 1,500,000 | 6,800,000 |
| RS005 | C001 | U001 | 2026-07-18 | 800,000 | 2,500,000 |
| RS006 | C002 | U001 | 2026-06-25 | 600,000 | 1,800,000 |
| RS007 | C003 | U002 | 2026-05-05 | 1,000,000 | 3,200,000 |
| RS008 | C004 | U001 | 2026-04-01 | 1,200,000 | 5,500,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | returnDate | rentalDays | rentalAmount |
|---------|-------------|----------|---------|-----------|-----------|-----------|-------------|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 2026-07-04 | 3 | 600,000 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 2026-07-04 | 3 | 450,000 |
| D003 | RS002 | CS003 | 2 | 2026-07-05 | 2026-07-08 | 3 | 480,000 |
| D004 | RS002 | CS001 | 1 | 2026-07-05 | 2026-07-08 | 3 | 300,000 |
| D005 | RS003 | CS001 | 3 | 2026-06-10 | 2026-06-15 | 5 | 1,500,000 |
| D006 | RS003 | CS002 | 2 | 2026-06-10 | 2026-06-15 | 5 | 1,500,000 |
| D007 | RS004 | CS002 | 2 | 2026-05-15 | 2026-05-20 | 5 | 1,500,000 |
| D008 | RS005 | CS001 | 2 | 2026-07-18 | 2026-07-22 | 4 | 800,000 |
| D009 | RS005 | CS003 | 1 | 2026-07-18 | 2026-07-22 | 4 | 320,000 |
| D010 | RS006 | CS003 | 2 | 2026-06-25 | 2026-06-28 | 3 | 480,000 |

**tblCustomer**

| customerId | customerName | phone | email | address |
|-----------|-------------|-------|-------|---------|
| C001 | Nguyen Thi A | 0901234567 | nta@gmail.com | 123 Le Loi, HCM |
| C002 | Tran Thi B | 0912345678 | ttb@gmail.com | 456 Nguyen Hue, HCM |
| C003 | Le Thi C | 0923456789 | ltc@gmail.com | 789 Vo Van Tan, HCM |
| C004 | Pham Thi D | 0934567890 | ptd@gmail.com | 321 Cach Mang, HCM |

**tblUser**

| userId | username | password | fullName | role |
|--------|---------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | staff |
| U002 | staff02 | 654321 | Tran Van Staff | staff |

**tblCostume**

| costumeId | costumeName | model | genre | rentalPricePerDay | status |
|----------|------------|-------|-------|------------------|--------|
| CS001 | Vest nam đen | Slim fit | Nam | 100,000 | Available |
| CS002 | Váy dạ hội đỏ | Size M | Nữ | 150,000 | Available |
| CS003 | Áo dài trắng | Size S | Nữ | 80,000 | Available |

#### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công (staff01/123456) | Giao diện Home hiển thị |
| 2 | Staff chọn menu "Revenue Statistics" | Giao diện thống kê hiển thị: combobox chọn loại thống kê (Thang/Quy/Nam) |
| 3 | Staff chọn "Thang" trong combobox | Bảng thống kê hiển thị 5 tháng (sắp xếp gần nhất đến xa nhất): 07/2026 (4,400,000đ = P001 1,100,000 + P002 800,000 + P005 2,500,000), 06/2026 (9,000,000đ = P003 7,200,000 + P006 1,800,000), 05/2026 (10,000,000đ = P004 6,800,000 + P007 3,200,000), 04/2026 (5,500,000đ = P008) |
| 4 | Staff click vào dòng 07/2026 | Bảng chi tiết hiển thị 3 hóa đơn: RS001 - Nguyen Thi A - 01/07 - 2 trang phục - 1,100,000đ; RS002 - Tran Thi B - 05/07 - 2 trang phục - 800,000đ; RS005 - Nguyen Thi A - 18/07 - 2 trang phục - 2,500,000đ |
| 5 | Staff click vào dòng 06/2026 | Bảng chi tiết cập nhật hiển thị 2 hóa đơn: RS003 - Le Thi C - 10/06 - 2 trang phục - 7,200,000đ; RS006 - Tran Thi B - 25/06 - 2 trang phục - 1,800,000đ |
| 6 | Staff click vào dòng 05/2026 | Bảng chi tiết cập nhật hiển thị 2 hóa đơn: RS004 - Pham Thi D - 15/05 - 2 trang phục - 6,800,000đ; RS007 - Le Thi C - 05/05 - 2 trang phục - 3,200,000đ |
| 7 | Staff click vào dòng 04/2026 | Bảng chi tiết cập nhật hiển thị 1 hóa đơn: RS008 - Pham Thi D - 01/04 - 2 trang phục - 5,500,000đ |

#### Database AFTER

Database không thay đổi vì chức năng này chỉ đọc dữ liệu (read-only), không có thao tác ghi.

---
