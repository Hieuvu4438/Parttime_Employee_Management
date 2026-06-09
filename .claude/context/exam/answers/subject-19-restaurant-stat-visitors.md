# Subject No. 19 — Restaurant Order — Module "Statistics of visitors by time slot"

> **Domain:** Restaurant Order Management
> **Entity classes:** Table, Customer, Order, OrderDetail, Invoice, TimeSlot, User

---

## Câu 1: Scenario — Thống kê khách theo khung giờ

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập hệ thống bằng tài khoản admin. |
| 2 | Manager chọn menu **Statistics** → **Visitors by time slot**. |
| 3 | Hệ thống hiển thị giao diện thống kê: ô nhập ngày bắt đầu (`txtStartDate`), ô nhập ngày kết thúc (`txtEndDate`), nút **View**. |
| 4 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thức: 31/12/2026. |
| 5 | Manager nhấn nút **View**. |
| 6 | Hệ thống truy vấn dữ liệu từ tblOrder, tblInvoice, tblOrderDetail theo khoảng thời gian đã chọn. |
| 7 | Hệ thống nhóm đơn hàng theo khung giờ (ví dụ: 07:00-09:00, 09:00-11:00, 11:00-13:00, 13:00-15:00, 15:00-17:00, 17:00-19:00, 19:00-21:00). |
| 8 | Hệ thống hiển thị bảng kết quả: giờ bắt đầu, giờ kết thúc, số khách trung bình, doanh thu trung bình/khách, tổng doanh thu theo giờ. Dữ liệu ví dụ: 11:00-13:00 (52 khách TB, 85,000đ/khách, 4,420,000đ), 18:00-20:00 (78 khách TB, 110,000đ/khách, 8,580,000đ). |
| 9 | Manager nhấn vào dòng khung giờ "11:00-13:00" trong bảng. |
| 10 | Hệ thống truy vấn chi tiết hóa đơn trong khung giờ đó từ tblInvoice, tblCustomer, tblOrder. |
| 11 | Hệ thống hiển thị danh sách hóa đơn chi tiết: mã hóa đơn (HD001), tên khách hàng (Nguyễn Văn A), ngày (15/03/2026), tổng số món (5), tổng tiền (425,000đ). Tiếp tục: HD002, Trần Thị B, 15/03/2026, 3, 270,000đ; HD003, Lê Văn C, 16/03/2026, 4, 380,000đ. |
| 12 | Manager nhấn **Back** để quay về bảng thống kê khung giờ. |

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống nhà hàng quản lý việc đặt món và thanh toán cho khách hàng. Mỗi bàn ăn (Table) có thể chứa nhiều đơn hàng (Order). Mỗi khách hàng (Customer) có thể có nhiều đơn hàng. Mỗi đơn hàng chứa nhiều chi tiết món (OrderDetail), mỗi chi tiết liên kết đến một món ăn (Dish) hoặc combo. Mỗi đơn hàng tạo ra một hóa đơn (Invoice). Người dùng (User) là nhân viên hoặc quản lý thực hiện các thao tác trên hệ thống. Khung giờ (TimeSlot) dùng để phân loại thời gian phục vụ.

### Trích xuất danh từ

| Danh từ | Entity class |
|---------|-------------|
| Bàn ăn | Table |
| Khách hàng | Customer |
| Đơn hàng | Order |
| Chi tiết đơn hàng | OrderDetail |
| Hóa đơn | Invoice |
| Món ăn | Dish |
| Combo | Combo |
| Combo chi tiết | ComboDetail |
| Khung giờ | TimeSlot |
| Người dùng | User |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Table | id (PK), code, name, maxGuests, status |
| Customer | id (PK), code, name, phone, email, address |
| Order | id (PK), tableId (FK), customerId (FK), userId (FK), orderDate, totalAmount, status |
| OrderDetail | id (PK), orderId (FK), dishId (FK), comboId (FK), quantity, unitPrice, amount |
| Invoice | id (PK), orderId (FK), customerId (FK), invoiceDate, totalOrders, totalAmount, paymentMethod |
| Dish | id (PK), code, type, name, description, price |
| Combo | id (PK), name, totalPrice |
| ComboDetail | id (PK), comboId (FK), dishId (FK), quantity |
| TimeSlot | id (PK), startTime, endTime, description |
| User | id (PK), username, password, fullName, role |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  menu thống kê -> mnuStatistic
  mục khách theo khung giờ -> mnuVisitorByTimeSlot

Chọn Visitors by time slot -> StatisticVisitorFrm xuất hiện:
  ô nhập ngày bắt đầu -> dtpStartDate
  ô nhập ngày kết thúc -> dtpEndDate
  nút xem -> btnView
  bảng thống kê khung giờ (clickable) -> dgvTimeSlotStat
  bảng chi tiết hóa đơn -> dgvInvoiceDetail

Nhập ngày và nhấn View -> hệ thống lấy đơn hàng theo khoảng thời gian -> cần phương thức:
  tên: getOrdersByDateRange()
  đầu vào: startDate (Date), endDate (Date)
  đầu ra: list of Order
  gán cho entity class: Order.

Click vào khung giờ -> hệ thống lấy hóa đơn theo khung giờ -> cần phương thức:
  tên: getInvoicesByTimeSlot()
  đầu vào: startTime (String), endTime (String), startDate (Date), endDate (Date)
  đầu ra: list of Invoice
  gán cho entity class: Invoice.

Click vào khung giờ -> hệ thống lấy thông tin khách hàng -> cần phương thức:
  tên: getCustomerById()
  đầu vào: customerId (int)
  đầu ra: Customer
  gán cho entity class: Customer.

### Summary
View classes: HomeFrm, StatisticVisitorFrm
Methods: getOrdersByDateRange(), getInvoicesByTimeSlot(), getCustomerById()

### Quan hệ

```
Table 1 --- n Order
Customer 1 --- n Order
User 1 --- n Order
Order 1 --- n OrderDetail
Dish 1 --- n OrderDetail
Combo 1 --- n OrderDetail
Order 1 --- 1 Invoice
Customer 1 --- n Invoice
Combo 1 --- n ComboDetail
Dish 1 --- n ComboDetail
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
        | n
        |
   +----+----+
   |         |
   | 1       | 1
+-------+ +-------+
| Dish  | | Combo |
+-------+ +-------+
|id     | |id     |
|code   | |name   |
|type   | |total  |
|name   | |Price  |
|price  | +-------+
+-------+
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

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Order`, `<<boundary>> StatisticVisitorFrm`, `<<control>> OrderDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-orderDate: Date`, `-totalAmount: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+getOrdersByDateRange(startDate: Date, endDate: Date): List<Order>`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -status: String | (không có) |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | +getCustomerById(customerId: int): Customer |
| Order | <<entity>> | -id: int, -orderDate: Date, -totalAmount: float, -status: String | +getOrdersByDateRange(startDate: Date, endDate: Date): List<Order> |
| OrderDetail | <<entity>> | -id: int, -quantity: int, -unitPrice: float, -amount: float | (không có) |
| Invoice | <<entity>> | -id: int, -invoiceDate: Date, -totalOrders: int, -totalAmount: float, -paymentMethod: String | +getInvoicesByTimeSlot(startTime: String, endTime: String, startDate: Date, endDate: Date): List<Invoice> |
| Dish | <<entity>> | -id: int, -code: String, -type: String, -name: String, -description: String, -price: float | (không có) |
| Combo | <<entity>> | -id: int, -name: String, -totalPrice: float | (không có) |
| ComboDetail | <<entity>> | -id: int, -quantity: int | (không có) |
| TimeSlot | <<entity>> | -id: int, -startTime: String, -endTime: String, -description: String | (không có) |
| User | <<entity>> | -id: int, -username: String, -password: String, -fullName: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -mnuStatistic: JMenu, -mnuVisitorByTimeSlot: JMenuItem | Giao diện chính, chứa menu Statistics |
| StatisticVisitorFrm | <<boundary>> | -dtpStartDate: DateTimePicker, -dtpEndDate: DateTimePicker, -btnView: JButton, -dgvTimeSlotStat: JTable, -dgvInvoiceDetail: JTable, -btnBack: JButton | Giao diện thống kê khách theo khung giờ |

Quy tắc đặt tên UI elements:
- Tiền tố `dtp` → DateTimePicker (chọn ngày): dtpStartDate, dtpEndDate
- Tiền tố `btn` → Button (nút bấm): btnView, btnBack
- Tiền tố `dgv` → DataGridView (bảng dữ liệu): dgvTimeSlotStat, dgvInvoiceDetail
- Tiền tố `mnu` → Menu: mnuStatistic, mnuVisitorByTimeSlot

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Order (khách hàng tham chiếu đến đơn hàng).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Order → OrderDetail (chi tiết đơn hàng không tồn tại nếu không có đơn hàng).
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: StatisticVisitorFrm → OrderDAO (form sử dụng DAO để truy vấn).

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
| Dish | OrderDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều chi tiết đơn |
| Combo | OrderDetail | Association | 1 - n | Một combo xuất hiện trong nhiều chi tiết đơn |
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

**StatisticVisitorFrm** — Giao diện thống kê khách theo khung giờ:
- `dtpStartDate`: DateTimePicker — chọn ngày bắt đầu thống kê
- `dtpEndDate`: DateTimePicker — chọn ngày kết thúc thống kê
- `btnView`: Button — thực hiện thống kê
- `dgvTimeSlotStat`: DataGridView — hiển thị danh sách khung giờ (giờ bắt đầu, giờ kết thúc, số khách TB, doanh thu TB/khách, tổng doanh thu)
- `dgvInvoiceDetail`: DataGridView — hiển thị chi tiết hóa đơn khi click vào 1 dòng khung giờ (mã hóa đơn, tên KH, ngày, tổng số món, tổng tiền)

**HomeFrm** — Giao diện chính:
- `mnuStatistic`: Menu — menu thống kê
- `mnuVisitorByTimeSlot`: MenuItem — mở giao diện thống kê khách theo khung giờ

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| dtpStartDate | DateTimePicker | Chọn ngày bắt đầu |
| dtpEndDate | DateTimePicker | Chọn ngày kết thúc |
| btnView | Button | Nhấn để xem thống kê |
| dgvTimeSlotStat | DataGridView | Bảng kết quả thống kê khung giờ |
| dgvInvoiceDetail | DataGridView | Bảng chi tiết hóa đơn |
| btnBack | Button | Quay lại bảng thống kê |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| OrderDAO | `getOrdersByDateRange(startDate, endDate): List<Order>` | Lấy danh sách đơn hàng theo khoảng thời gian |
| OrderDAO | `getOrdersByTimeSlot(startTime, endTime, startDate, endDate): List<Order>` | Lấy đơn hàng theo khung giờ và khoảng ngày |
| InvoiceDAO | `getInvoicesByTimeSlot(startTime, endTime, startDate, endDate): List<Invoice>` | Lấy hóa đơn theo khung giờ |
| CustomerDAO | `getCustomerById(customerId): Customer` | Lấy thông tin khách hàng theo mã |
| TableDAO | `getTableById(tableId): Table` | Lấy thông tin bàn theo mã |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, status: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Order | Model | id: int, tableId: int, customerId: int, userId: int, orderDate: Date, totalAmount: float, status: String |
| OrderDetail | Model | id: int, orderId: int, dishId: int, comboId: int, quantity: int, unitPrice: float, amount: float |
| Invoice | Model | id: int, orderId: int, customerId: int, invoiceDate: Date, totalOrders: int, totalAmount: float, paymentMethod: String |
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Combo | Model | id: int, name: String, totalPrice: float |
| ComboDetail | Model | id: int, comboId: int, dishId: int, quantity: int |
| User | Model | id: int, username: String, password: String, fullName: String, role: String |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Order | Nguồn dữ liệu chính, dùng để đếm số khách và tính doanh thu |
| OrderDetail | Chi tiết từng món trong đơn, dùng để tính tổng số món |
| Invoice | Hóa đơn thanh toán, hiển thị trong bảng chi tiết |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết |
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

**tblDish:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| type | varchar(50) | NOT NULL |
| name | varchar(100) | NOT NULL |
| description | nvarchar(255) | |
| price | float | NOT NULL |

**tblCombo:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| name | varchar(100) | NOT NULL |
| totalPrice | float | NOT NULL |

**tblComboDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| comboID | int | FOREIGN KEY → tblCombo(ID) |
| dishID | int | FOREIGN KEY → tblDish(ID) |
| quantity | int | NOT NULL |

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
   - `<<boundary>> StatisticVisitorFrm`: Ngăn 2 chứa UI elements (dtpStartDate, dtpEndDate, btnView, dgvTimeSlotStat, dgvInvoiceDetail, btnBack). Ngăn 3 trống.
   - `<<boundary>> HomeFrm`: Ngăn 2 chứa menu items (mnuStatistic, mnuVisitorByTimeSlot). Ngăn 3 trống.
4. Tạo DAO classes trong package `dao`:
   - `<<control>> OrderDAO`: Ngăn 2 trống. Ngăn 3: +getOrdersByDateRange(startDate: Date, endDate: Date): List<Order>, +getOrdersByTimeSlot(startTime: String, endTime: String, startDate: Date, endDate: Date): List<Order>
   - `<<control>> InvoiceDAO`: Ngăn 3: +getInvoicesByTimeSlot(startTime: String, endTime: String, startDate: Date, endDate: Date): List<Invoice>
   - `<<control>> CustomerDAO`: Ngăn 3: +getCustomerById(customerId: int): Customer
5. Tạo Entity classes trong package `model` với stereotype `<<entity>>`, mỗi class có Ngăn 2 chứa attributes với kiểu dữ liệu (int, String, float, Date).
6. Vẽ Dependency (đường dashed, mũi tên tam giác rỗng ▷):
   - StatisticVisitorFrm → OrderDAO (form sử dụng DAO)
   - StatisticVisitorFrm → InvoiceDAO (form sử dụng DAO)
   - StatisticVisitorFrm → CustomerDAO (form sử dụng DAO)
7. Vẽ Dependency từ DAO → Entity:
   - OrderDAO → Order (DAO truy vấn entity)
   - InvoiceDAO → Invoice (DAO truy vấn entity)
   - CustomerDAO → Customer (DAO truy vấn entity)
8. Vẽ Association giữa các Entity classes theo bảng quan hệ ở trên.

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Manager` — actor (người sử dụng)
2. `:HomeFrm` — boundary (giao diện chính)
3. `:StatisticVisitorFrm` — boundary (giao diện thống kê)
4. `:OrderDAO` — control (truy vấn đơn hàng)
5. `:InvoiceDAO` — control (truy vấn hóa đơn)
6. `:CustomerDAO` — control (truy vấn khách hàng)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 6 lifelines theo thứ tự: Manager, HomeFrm, StatisticVisitorFrm, OrderDAO, InvoiceDAO, CustomerDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng self-call cho các thao tác nội bộ của form.

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Manager | HomeFrm | selectMenu("Statistics") | synchronous |
| 2 | HomeFrm | StatisticVisitorFrm | show() | synchronous |
| 3 | StatisticVisitorFrm | Manager | displayForm(dtpStartDate, dtpEndDate, btnView) | return |
| 4 | Manager | StatisticVisitorFrm | enterDateRange(01/01/2026, 31/12/2026) | synchronous |
| 5 | Manager | StatisticVisitorFrm | clickView() | synchronous |
| 6 | StatisticVisitorFrm | OrderDAO | getOrdersByDateRange(01/01/2026, 31/12/2026) | synchronous |
| 7 | OrderDAO | StatisticVisitorFrm | return List<Order> | return |
| 8 | StatisticVisitorFrm | StatisticVisitorFrm | groupOrdersByTimeSlot(orders) | self |
| 9 | StatisticVisitorFrm | StatisticVisitorFrm | calculateAvgVisitors() | self |
| 10 | StatisticVisitorFrm | StatisticVisitorFrm | calculateAvgRevenuePerGuest() | self |
| 11 | StatisticVisitorFrm | StatisticVisitorFrm | calculateTotalHourlyRevenue() | self |
| 12 | StatisticVisitorFrm | Manager | displayTimeSlotTable(data) | return |
| 13 | Manager | StatisticVisitorFrm | clickRow("11:00-13:00") | synchronous |
| 14 | StatisticVisitorFrm | InvoiceDAO | getInvoicesByTimeSlot("11:00", "13:00", 01/01/2026, 31/12/2026) | synchronous |
| 15 | InvoiceDAO | StatisticVisitorFrm | return List<Invoice> | return |
| 16 | StatisticVisitorFrm | CustomerDAO | getCustomerById(customerId) | synchronous |
| 17 | CustomerDAO | StatisticVisitorFrm | return Customer | return |
| 18 | StatisticVisitorFrm | StatisticVisitorFrm | buildInvoiceDetailList(invoices, customers) | self |
| 19 | StatisticVisitorFrm | Manager | displayInvoiceDetail(data) | return |
| 20 | Manager | StatisticVisitorFrm | clickBack() | synchronous |
| 21 | StatisticVisitorFrm | Manager | displayTimeSlotTable(previousData) | return |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê khách theo khung giờ và xem chi tiết hóa đơn

**Database trước:**

tblOrder:
| id | tableId | customerId | userId | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | 1 | 1 | 2026-03-15 11:30:00 | 425000 | Completed |
| 2 | 2 | 2 | 1 | 2026-03-15 12:15:00 | 270000 | Completed |
| 3 | 3 | 3 | 2 | 2026-03-16 11:45:00 | 380000 | Completed |
| 4 | 1 | 4 | 1 | 2026-03-17 18:30:00 | 550000 | Completed |
| 5 | 2 | 5 | 2 | 2026-03-18 19:00:00 | 620000 | Completed |

tblInvoice:
| id | orderId | customerId | invoiceDate | totalOrders | totalAmount | paymentMethod |
|----|---------|------------|-------------|-------------|-------------|---------------|
| 1 | 1 | 1 | 2026-03-15 12:30:00 | 5 | 425000 | Cash |
| 2 | 2 | 2 | 2026-03-15 13:00:00 | 3 | 270000 | Card |
| 3 | 3 | 3 | 2026-03-16 12:45:00 | 4 | 380000 | Cash |
| 4 | 4 | 4 | 2026-03-17 19:30:00 | 6 | 550000 | Card |
| 5 | 5 | 5 | 2026-03-18 20:15:00 | 7 | 620000 | Cash |

tblCustomer:
| id | code | name | phone |
|----|------|------|-------|
| 1 | C001 | Nguyễn Văn A | 0901234567 |
| 2 | C002 | Trần Thị B | 0912345678 |
| 3 | C003 | Lê Văn C | 0923456789 |
| 4 | C004 | Phạm Thị D | 0934567890 |
| 5 | C005 | Hoàng Văn E | 0945678901 |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Manager chọn menu Statistics → Visitors by time slot | Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút View |
| 3 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026 | Các ô nhập hiển thị giá trị đã chọn |
| 4 | Manager nhấn nút View | Hệ thống hiển thị bảng thống kê: 11:00-13:00 (3 khách TB, 358,333đ/khách, 1,075,000đ), 18:00-20:00 (2 khách TB, 585,000đ/khách, 1,170,000đ) |
| 5 | Manager nhấn vào dòng "11:00-13:00" | Hệ thống hiển thị bảng chi tiết hóa đơn bên dưới |
| 6 | Bảng chi tiết hiển thị 3 dòng: HD001, Nguyễn Văn A, 15/03/2026, 5 món, 425,000đ; HD002, Trần Thị B, 15/03/2026, 3 món, 270,000đ; HD003, Lê Văn C, 16/03/2026, 4 món, 380,000đ | Dữ liệu hiển thị chính xác |
| 7 | Manager nhấn nút Back | Quay về bảng thống kê khung giờ, bảng chi tiết ẩn đi |

**Database sau:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).

---

### TC02: Thống kê với khoảng thời gian không có dữ liệu

**Database trước:**
- tblOrder: không có đơn hàng nào trong khoảng 01/06/2025 - 30/06/2025.

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công |
| 2 | Manager chọn Statistics → Visitors by time slot | Hiển thị giao diện thống kê |
| 3 | Manager nhập ngày: 01/06/2025 - 30/06/2025 | Các ô nhập hiển thị giá trị |
| 4 | Manager nhấn View | Bảng thống kê trống, hiển thị thông báo "Không có dữ liệu trong khoảng thời gian này" |

**Database sau:**
- Không có thay đổi dữ liệu.
