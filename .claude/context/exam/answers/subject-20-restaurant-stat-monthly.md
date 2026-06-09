# Subject No. 20 — Restaurant Order — Module "Statistical monthly revenue"

> **Domain:** Restaurant Order Management
> **Entity classes:** Table, Customer, Order, Invoice, User

---

## Câu 1: Scenario — Thống kê doanh thu theo tháng

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập hệ thống bằng tài khoản admin. |
| 2 | Manager chọn menu **Statistics** → **Monthly revenue**. |
| 3 | Hệ thống tự động truy vấn dữ liệu từ tblInvoice, tblOrder, tblCustomer trong 12 tháng gần nhất. |
| 4 | Hệ thống nhóm hóa đơn theo tháng, tính tổng số khách và tổng doanh thu cho mỗi tháng. |
| 5 | Hệ thống hiển thị bảng kết quả: tên tháng, tổng số khách, tổng doanh thu, sắp xếp giảm dần theo doanh thu. Dữ liệu ví dụ: 05/2026 (520 khách, 52,000,000đ), 03/2026 (480 khách, 48,500,000đ), 01/2026 (450 khách, 45,000,000đ). |
| 6 | Manager nhấn vào dòng tháng "05/2026" trong bảng. |
| 7 | Hệ thống truy vấn chi tiết hóa đơn trong tháng 05/2026 từ tblInvoice, tblCustomer, tblOrder. |
| 8 | Hệ thống hiển thị danh sách hóa đơn chi tiết: mã hóa đơn (HD001), tên khách hàng (Nguyễn Văn A), ngày giờ (05/05/2026 19:30), tổng số món (5), tổng tiền (485,000đ). Tiếp tục: HD002, Trần Thị B, 08/05/2026 12:15, 3, 320,000đ; HD003, Lê Văn C, 12/05/2026 18:45, 4, 410,000đ. |
| 9 | Manager nhấn vào một dòng hóa đơn để xem thêm chi tiết (nếu cần). |
| 10 | Manager nhấn **Back** để quay về bảng thống kê 12 tháng. |

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống nhà hàng quản lý quá trình đặt món và thanh toán. Khách hàng (Customer) đến nhà hàng, ngồi tại bàn (Table), đặt đơn hàng (Order) gồm nhiều món. Sau khi ăn xong, hệ thống tạo hóa đơn (Invoice) để thanh toán. Nhân viên hoặc quản lý (User) thực hiện các thao tác trên hệ thống. Chức năng thống kê doanh thu theo tháng tổng hợp dữ liệu từ hóa đơn để hiển thị doanh thu 12 tháng gần nhất.

### Trích xuất danh từ

| Danh từ | Entity class |
|---------|-------------|
| Bàn ăn | Table |
| Khách hàng | Customer |
| Đơn hàng | Order |
| Hóa đơn | Invoice |
| Người dùng | User |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Table | id (PK), code, name, maxGuests, status |
| Customer | id (PK), code, name, phone, email, address |
| Order | id (PK), tableId (FK), customerId (FK), userId (FK), orderDate, totalAmount, status |
| OrderDetail | id (PK), orderId (FK), dishId (FK), comboId (FK), quantity, unitPrice, amount |
| Invoice | id (PK), orderId (FK), customerId (FK), invoiceDate, totalOrders, totalAmount, paymentMethod |
| User | id (PK), username, password, fullName, role |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  menu thống kê -> mnuStatistic
  mục doanh thu theo tháng -> mnuMonthlyRevenue

Chọn Monthly revenue -> MonthlyRevenueFrm xuất hiện:
  bảng thống kê 12 tháng (clickable) -> dgvMonthlyStat
  bảng chi tiết hóa đơn -> dgvInvoiceDetail
  nút quay lại -> btnBack

Khi mở form -> hệ thống tự động lấy doanh thu theo tháng -> cần phương thức:
  tên: getMonthlyRevenue()
  đầu vào: (không có)
  đầu ra: list of Object[] {monthYear, totalGuests, totalRevenue}
  gán cho entity class: Invoice (dữ liệu từ Invoice).

Click vào tháng -> hệ thống lấy hóa đơn theo tháng -> cần phương thức:
  tên: getInvoicesByMonth()
  đầu vào: year (int), month (int)
  đầu ra: list of Invoice
  gán cho entity class: Invoice.

Click vào tháng -> hệ thống lấy thông tin khách hàng -> cần phương thức:
  tên: getCustomerById()
  đầu vào: customerId (int)
  đầu ra: Customer
  gán cho entity class: Customer.

### Summary
View classes: HomeFrm, MonthlyRevenueFrm
Methods: getMonthlyRevenue(), getInvoicesByMonth(), getCustomerById()

### Quan hệ

```
Table 1 --- n Order
Customer 1 --- n Order
User 1 --- n Order
Order 1 --- n OrderDetail
Order 1 --- 1 Invoice
Customer 1 --- n Invoice
```

### ASCII Class Diagram

```
+------------------+          +------------------+
|      Table       |          |     Customer     |
+------------------+          +------------------+
| - id: int        |          | - id: int        |
| - code: String   |          | - code: String   |
| - name: String   |          | - name: String   |
| - maxGuests: int |          | - phone: String  |
| - status: String |          | - email: String  |
+------------------+          | - address: String|
        |                     +------------------+
        | 1                          |
        |                            | 1
        | n                          | n
+------------------+          +------------------+
|      Order       |          |      Invoice     |
+------------------+          +------------------+
| - id: int        | 1      1 | - id: int        |
| - tableId: int   |----------| - orderId: int   |
| - customerId: int|          | - customerId: int|
| - userId: int    |          | - invoiceDate    |
| - orderDate      |          | - totalOrders:int|
| - totalAmount    |          | - totalAmount    |
| - status: String |          | - paymentMethod  |
+------------------+          +------------------+
        |
        | 1
        | n
+------------------+
|   OrderDetail    |
+------------------+
| - id: int        |
| - orderId: int   |
| - dishId: int    |
| - comboId: int   |
| - quantity: int  |
| - unitPrice: float|
| - amount: float  |
+------------------+

+------------------+
|      User        |
+------------------+
| - id: int        |
| - username: String|
| - password: String|
| - fullName: String|
| - role: String   |
+------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity class.
3. Tạo view class boxes từ các interface (form) của module.
4. Vẽ mối quan hệ (relationship) giữa các class.
5. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Invoice`, `<<boundary>> MonthlyRevenueFrm`, `<<control>> InvoiceDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-invoiceDate: Date`, `-totalAmount: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+getMonthlyRevenue(): List<Object[]>`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -status: String | (không có) |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | +getCustomerById(customerId: int): Customer |
| Order | <<entity>> | -id: int, -orderDate: Date, -totalAmount: float, -status: String | +getOrdersByMonth(year: int, month: int): List<Order> |
| OrderDetail | <<entity>> | -id: int, -quantity: int, -unitPrice: float, -amount: float | (không có) |
| Invoice | <<entity>> | -id: int, -invoiceDate: Date, -totalOrders: int, -totalAmount: float, -paymentMethod: String | +getMonthlyRevenue(): List<Object[]>, +getInvoicesByMonth(year: int, month: int): List<Invoice> |
| User | <<entity>> | -id: int, -username: String, -password: String, -fullName: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -mnuStatistic: JMenu, -mnuMonthlyRevenue: JMenuItem | Giao diện chính, chứa menu Statistics |
| MonthlyRevenueFrm | <<boundary>> | -dgvMonthlyStat: JTable, -dgvInvoiceDetail: JTable, -btnBack: JButton, -lblTotalRevenue: JLabel, -lblMonthTitle: JLabel | Giao diện thống kê doanh thu theo tháng |

Quy tắc đặt tên UI elements:
- Tiền tố `dgv` → DataGridView (bảng dữ liệu): dgvMonthlyStat, dgvInvoiceDetail
- Tiền tố `btn` → Button (nút bấm): btnBack
- Tiền tố `lbl` → Label (nhãn): lblTotalRevenue, lblMonthTitle
- Tiền tố `mnu` → Menu: mnuStatistic, mnuMonthlyRevenue

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Order (khách hàng tham chiếu đến đơn hàng).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Order → OrderDetail (chi tiết đơn hàng không tồn tại nếu không có đơn hàng).
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: MonthlyRevenueFrm → InvoiceDAO (form sử dụng DAO để truy vấn).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Table (1) → (n) Order nghĩa là một bàn có nhiều đơn hàng.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Table | Order | Association | 1 - n | Một bàn có nhiều đơn hàng |
| Customer | Order | Association | 1 - n | Một khách hàng có nhiều đơn hàng |
| User | Order | Association | 1 - n | Một nhân viên tạo nhiều đơn hàng |
| Order | OrderDetail | Composition | 1 - n | Một đơn hàng chứa nhiều chi tiết; chi tiết không tồn tại nếu không có đơn hàng |
| Order | Invoice | Association | 1 - 1 | Mỗi đơn hàng tạo ra đúng 1 hóa đơn |
| Customer | Invoice | Association | 1 - n | Một khách hàng có nhiều hóa đơn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Order → OrderDetail (1-n, Composition)**
1. Tạo class `<<entity>> Order` và `<<entity>> OrderDetail` với các thuộc tính tương ứng.
2. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
3. Click vào class Order → kéo đến class OrderDetail.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Kết quả: Order (1) ◆----(*) OrderDetail.

**Ví dụ 2: Vẽ quan hệ Order → Invoice (1-1, Association)**
1. Tạo class `<<entity>> Invoice` với các thuộc tính: -id: int, -orderId: int, -customerId: int, -invoiceDate: Date, -totalOrders: int, -totalAmount: float, -paymentMethod: String.
2. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
3. Click vào class Order → kéo đến class Invoice.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = 1.
5. Đặt tên association: "generates" (tùy chọn).
6. Kết quả: Order (1) ▷----(1) Invoice.

---

## Câu 3: Thiết kế tĩnh

### View classes

**MonthlyRevenueFrm** — Giao diện thống kê doanh thu theo tháng:
- `dgvMonthlyStat`: DataGridView — hiển thị bảng thống kê 12 tháng (tên tháng, tổng số khách, tổng doanh thu), sắp xếp giảm dần theo doanh thu
- `dgvInvoiceDetail`: DataGridView — hiển thị chi tiết hóa đơn khi click vào 1 dòng tháng (mã hóa đơn, tên KH, ngày giờ, tổng số món, tổng tiền)
- `btnBack`: Button — quay lại bảng thống kê

**HomeFrm** — Giao diện chính:
- `mnuStatistic`: Menu — menu thống kê
- `mnuMonthlyRevenue`: MenuItem — mở giao diện thống kê doanh thu theo tháng

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| dgvMonthlyStat | DataGridView | Bảng thống kê 12 tháng gần nhất |
| dgvInvoiceDetail | DataGridView | Bảng chi tiết hóa đơn theo tháng |
| btnBack | Button | Quay lại bảng thống kê |
| lblTotalRevenue | JLabel | Hiển thị tổng doanh thu 12 tháng |
| lblMonthTitle | JLabel | Tiêu đề "Chi tiết hóa đơn tháng XX/YYYY" |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| InvoiceDAO | `getMonthlyRevenue(): List<Object[]>` | Thống kê doanh thu 12 tháng gần nhất, mỗi dòng gồm: monthYear, totalGuests, totalRevenue, sắp xếp giảm dần theo totalRevenue |
| InvoiceDAO | `getInvoicesByMonth(int year, int month): List<Invoice>` | Lấy danh sách hóa đơn trong 1 tháng cụ thể, bao gồm: id, customerName, invoiceDate, totalOrders, totalAmount |
| CustomerDAO | `getCustomerById(customerId): Customer` | Lấy thông tin khách hàng theo mã |
| OrderDAO | `getOrdersByMonth(int year, int month): List<Order>` | Lấy danh sách đơn hàng trong 1 tháng |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, status: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Order | Model | id: int, tableId: int, customerId: int, userId: int, orderDate: Date, totalAmount: float, status: String |
| OrderDetail | Model | id: int, orderId: int, dishId: int, comboId: int, quantity: int, unitPrice: float, amount: float |
| Invoice | Model | id: int, orderId: int, customerId: int, invoiceDate: Date, totalOrders: int, totalAmount: float, paymentMethod: String |
| User | Model | id: int, username: String, password: String, fullName: String, role: String |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Invoice | Nguồn dữ liệu chính, dùng để tính tổng doanh thu và tổng số khách theo tháng |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết hóa đơn |
| Order | Liên kết giữa hóa đơn và đơn hàng, dùng để đếm số món |
| Table | Thông tin bàn, liên kết với đơn hàng |
| User | Người tạo đơn hàng (nhân viên) |

### Database Design

**tblTable:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL |
| name | varchar(50) | NOT NULL |
| maxGuests | int | NOT NULL |
| status | varchar(20) | |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL |
| name | varchar(100) | NOT NULL |
| phone | varchar(20) | |
| email | varchar(100) | |
| address | varchar(200) | |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL |
| password | varchar(100) | NOT NULL |
| fullName | varchar(100) | |
| role | varchar(20) | NOT NULL |

**tblOrder:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| tableID | int | FOREIGN KEY → tblTable(ID) |
| customerID | int | FOREIGN KEY → tblCustomer(ID), NULLABLE |
| userID | int | FOREIGN KEY → tblUser(ID) |
| orderDate | datetime | NOT NULL |
| totalAmount | float | NOT NULL |
| status | varchar(20) | NOT NULL |

**tblOrderDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| orderID | int | FOREIGN KEY → tblOrder(ID) |
| dishID | int | FOREIGN KEY → tblDish(ID), NULLABLE |
| comboID | int | FOREIGN KEY → tblCombo(ID), NULLABLE |
| quantity | int | NOT NULL |
| unitPrice | float | NOT NULL |
| amount | float | NOT NULL |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| orderID | int | FOREIGN KEY → tblOrder(ID) |
| customerID | int | FOREIGN KEY → tblCustomer(ID) |
| invoiceDate | datetime | NOT NULL |
| totalOrders | int | |
| totalAmount | float | NOT NULL |
| paymentMethod | varchar(20) | |

### Hướng dẫn vẽ Design Class Diagram trên Visual Paradigm

**Các bước vẽ:**

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo 3 package: `view.statistic`, `model`, `dao`.
3. Tạo View classes trong package `view.statistic`:
   - `<<boundary>> MonthlyRevenueFrm`: Ngăn 2 chứa UI elements (dgvMonthlyStat, dgvInvoiceDetail, btnBack, lblTotalRevenue, lblMonthTitle). Ngăn 3 trống.
   - `<<boundary>> HomeFrm`: Ngăn 2 chứa menu items (mnuStatistic, mnuMonthlyRevenue). Ngăn 3 trống.
4. Tạo DAO classes trong package `dao`:
   - `<<control>> InvoiceDAO`: Ngăn 2 trống. Ngăn 3: +getMonthlyRevenue(): List<Object[]>, +getInvoicesByMonth(year: int, month: int): List<Invoice>
   - `<<control>> CustomerDAO`: Ngăn 3: +getCustomerById(customerId: int): Customer
   - `<<control>> OrderDAO`: Ngăn 3: +getOrdersByMonth(year: int, month: int): List<Order>
5. Tạo Entity classes trong package `model` với stereotype `<<entity>>`, mỗi class có Ngăn 2 chứa attributes với kiểu dữ liệu (int, String, float, Date).
6. Vẽ Dependency (đường dashed, mũi tên tam giác rỗng ▷):
   - MonthlyRevenueFrm → InvoiceDAO (form sử dụng DAO)
   - MonthlyRevenueFrm → CustomerDAO (form sử dụng DAO)
7. Vẽ Dependency từ DAO → Entity:
   - InvoiceDAO → Invoice (DAO truy vấn entity)
   - CustomerDAO → Customer (DAO truy vấn entity)
   - OrderDAO → Order (DAO truy vấn entity)
8. Vẽ Association giữa các Entity classes theo bảng quan hệ ở trên.

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Manager` — actor (người sử dụng)
2. `:HomeFrm` — boundary (giao diện chính)
3. `:MonthlyRevenueFrm` — boundary (giao diện thống kê)
4. `:InvoiceDAO` — control (truy vấn hóa đơn)
5. `:CustomerDAO` — control (truy vấn khách hàng)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 5 lifelines theo thứ tự: Manager, HomeFrm, MonthlyRevenueFrm, InvoiceDAO, CustomerDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng self-call cho các thao tác nội bộ của form.

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Manager | HomeFrm | selectMenu("Statistics") | synchronous |
| 2 | HomeFrm | MonthlyRevenueFrm | show() | synchronous |
| 3 | MonthlyRevenueFrm | InvoiceDAO | getMonthlyRevenue() | synchronous |
| 4 | InvoiceDAO | InvoiceDAO | query and aggregate data (12 months) | self |
| 5 | InvoiceDAO | MonthlyRevenueFrm | return List<Object[]> {monthYear, totalGuests, totalRevenue} | return |
| 6 | MonthlyRevenueFrm | MonthlyRevenueFrm | sortByRevenueDesc(data) | self |
| 7 | MonthlyRevenueFrm | Manager | displayMonthlyTable(sortedData) | return |
| 8 | Manager | MonthlyRevenueFrm | clickRow("05/2026") | synchronous |
| 9 | MonthlyRevenueFrm | MonthlyRevenueFrm | extractMonth("05/2026") → year=2026, month=5 | self |
| 10 | MonthlyRevenueFrm | InvoiceDAO | getInvoicesByMonth(2026, 5) | synchronous |
| 11 | InvoiceDAO | InvoiceDAO | query tblInvoice JOIN tblCustomer | self |
| 12 | InvoiceDAO | MonthlyRevenueFrm | return List<Invoice> | return |
| 13 | MonthlyRevenueFrm | CustomerDAO | getCustomerById(customerId) | synchronous |
| 14 | CustomerDAO | MonthlyRevenueFrm | return Customer | return |
| 15 | MonthlyRevenueFrm | MonthlyRevenueFrm | buildInvoiceDetailList(invoices, customers) | self |
| 16 | MonthlyRevenueFrm | Manager | displayInvoiceDetail(detailList) | return |
| 17 | Manager | MonthlyRevenueFrm | clickRow(invoiceId) | synchronous |
| 18 | MonthlyRevenueFrm | MonthlyRevenueFrm | showInvoiceFullDetail(invoice) | self |
| 19 | Manager | MonthlyRevenueFrm | clickBack() | synchronous |
| 20 | MonthlyRevenueFrm | Manager | displayMonthlyTable(previousData) | return |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn

**Database trước:**

tblInvoice:
| id | orderId | customerId | invoiceDate | totalOrders | totalAmount | paymentMethod |
|----|---------|------------|-------------|-------------|-------------|---------------|
| 1 | 1 | 1 | 2026-05-05 19:30:00 | 5 | 485000 | Cash |
| 2 | 2 | 2 | 2026-05-08 12:15:00 | 3 | 320000 | Card |
| 3 | 3 | 3 | 2026-05-12 18:45:00 | 4 | 410000 | Cash |
| 4 | 4 | 1 | 2026-04-20 19:00:00 | 6 | 550000 | Card |
| 5 | 5 | 4 | 2026-04-25 20:30:00 | 4 | 380000 | Cash |
| 6 | 6 | 2 | 2026-03-10 12:00:00 | 3 | 290000 | Card |
| 7 | 7 | 5 | 2026-03-15 18:30:00 | 5 | 470000 | Cash |

tblCustomer:
| id | code | name | phone | email |
|----|------|------|-------|-------|
| 1 | C001 | Nguyễn Văn A | 0901234567 | nva@gmail.com |
| 2 | C002 | Trần Thị B | 0912345678 | ttb@gmail.com |
| 3 | C003 | Lê Văn C | 0923456789 | lvc@gmail.com |
| 4 | C004 | Phạm Thị D | 0934567890 | ptd@gmail.com |
| 5 | C005 | Hoàng Văn E | 0945678901 | hve@gmail.com |

tblOrder:
| id | tableId | customerId | userId | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | 1 | 1 | 2026-05-05 18:00:00 | 485000 | Completed |
| 2 | 2 | 2 | 1 | 2026-05-08 11:30:00 | 320000 | Completed |
| 3 | 3 | 3 | 2 | 2026-05-12 18:00:00 | 410000 | Completed |
| 4 | 1 | 1 | 1 | 2026-04-20 18:30:00 | 550000 | Completed |
| 5 | 2 | 4 | 2 | 2026-04-25 19:45:00 | 380000 | Completed |
| 6 | 3 | 2 | 1 | 2026-03-10 11:30:00 | 290000 | Completed |
| 7 | 1 | 5 | 2 | 2026-03-15 18:00:00 | 470000 | Completed |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Manager chọn menu Statistics → Monthly revenue | Hệ thống tự động truy vấn dữ liệu 12 tháng gần nhất |
| 3 | Hệ thống hiển thị bảng thống kê | Bảng gồm 12 tháng, sắp xếp giảm dần theo doanh thu: 05/2026 (3 khách, 1,215,000đ), 04/2026 (2 khách, 930,000đ), 03/2026 (2 khách, 760,000đ), các tháng còn lại hiển thị 0đ |
| 4 | Manager nhấn vào dòng "05/2026" | Hệ thống hiển thị bảng chi tiết hóa đơn bên dưới |
| 5 | Bảng chi tiết hiển thị 3 dòng | HD001, Nguyễn Văn A, 05/05/2026 19:30, 5 món, 485,000đ; HD002, Trần Thị B, 08/05/2026 12:15, 3 món, 320,000đ; HD003, Lê Văn C, 12/05/2026 18:45, 4 món, 410,000đ |
| 6 | Manager nhấn vào dòng HD001 | Hiển thị chi tiết đầy đủ hóa đơn HD001 |
| 7 | Manager nhấn nút Back | Quay về bảng thống kê 12 tháng, bảng chi tiết ẩn đi |

**Database sau:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).

---

### TC02: Tháng không có doanh thu

**Database trước:**
- tblInvoice: chỉ có hóa đơn trong tháng 05/2026. Không có hóa đơn nào trong tháng 06/2026.

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công |
| 2 | Manager chọn Statistics → Monthly revenue | Hiển thị bảng thống kê 12 tháng |
| 3 | Bảng hiển thị | 05/2026 có doanh thu, 06/2026 hiển thị 0 khách, 0đ |
| 4 | Manager nhấn vào dòng "06/2026" | Bảng chi tiết trống, hiển thị thông báo "Không có hóa đơn trong tháng này" |

**Database sau:**
- Không có thay đổi dữ liệu.
