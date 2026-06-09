# Subject No. 21 — Restaurant Order — Module "Statistics of dishes"

> **Domain:** Restaurant Order Management
> **Entity classes:** Dish, Order, OrderDetail, Customer, Invoice, User

---

## Câu 1: Scenario — Thống kê món ăn

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập hệ thống bằng tài khoản admin. |
| 2 | Manager chọn menu **Statistics** → **Dishes statistics**. |
| 3 | Hệ thống hiển thị giao diện thống kê: ô nhập ngày bắt đầu (`dtpStartDate`), ô nhập ngày kết thúc (`dtpEndDate`), nút **Statistics**. |
| 4 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026. |
| 5 | Manager nhấn nút **Statistics**. |
| 6 | Hệ thống truy vấn dữ liệu từ tblOrderDetail, tblDish, tblOrder, tblInvoice theo khoảng thời gian đã chọn. |
| 7 | Hệ thống nhóm chi tiết đơn hàng theo món ăn, tính tổng số lượng bán và tổng doanh thu cho mỗi món. |
| 8 | Hệ thống hiển thị bảng kết quả: mã món, loại món, tên món, tổng số lượng bán, tổng doanh thu, sắp xếp giảm dần theo doanh thu. Dữ liệu ví dụ: D001, Món chính, Phở bò (215 bán, 10,750,000đ), D003, Thức uống, Coca Cola (320 bán, 4,800,000đ), D002, Món chính, Bún bò (180 bán, 9,000,000đ). |
| 9 | Manager nhấn vào dòng "Phở bò" trong bảng. |
| 10 | Hệ thống truy vấn chi tiết các lần đặt món Phở bò từ tblOrderDetail, tblOrder, tblCustomer, tblInvoice. |
| 11 | Hệ thống hiển thị danh sách chi tiết: mã hóa đơn (HD001), tên khách hàng (Nguyễn Văn A), ngày giờ (15/03/2026 11:30), số lượng (2), thành tiền (100,000đ). Tiếp tục: HD005, Trần Thị B, 18/03/2026 12:15, 1, 50,000đ; HD012, Lê Văn C, 22/03/2026 18:30, 3, 150,000đ. |
| 12 | Manager nhấn **Back** để quay về bảng thống kê món ăn. |

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống nhà hàng quản lý việc đặt món và thanh toán. Khách hàng (Customer) đặt đơn hàng (Order) tại bàn ăn (Table). Mỗi đơn hàng chứa nhiều chi tiết món (OrderDetail), mỗi chi tiết liên kết đến một món ăn (Dish) với số lượng và đơn giá. Khi thanh toán, hệ thống tạo hóa đơn (Invoice). Nhân viên hoặc quản lý (User) thực hiện các thao tác. Chức năng thống kê món ăn tổng hợp dữ liệu từ chi tiết đơn hàng để hiển thị số lượng bán và doanh thu của từng món trong khoảng thời gian.

### Trích xuất danh từ

| Danh từ | Entity class |
|---------|-------------|
| Món ăn | Dish |
| Đơn hàng | Order |
| Chi tiết đơn hàng | OrderDetail |
| Khách hàng | Customer |
| Hóa đơn | Invoice |
| Bàn ăn | Table |
| Combo | Combo |
| Combo chi tiết | ComboDetail |
| Người dùng | User |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Dish | id (PK), code, type, name, description, price |
| Order | id (PK), tableId (FK), customerId (FK), userId (FK), orderDate, totalAmount, status |
| OrderDetail | id (PK), orderId (FK), dishId (FK), comboId (FK), quantity, unitPrice, amount |
| Customer | id (PK), code, name, phone, email, address |
| Invoice | id (PK), orderId (FK), customerId (FK), invoiceDate, totalOrders, totalAmount, paymentMethod |
| Table | id (PK), code, name, maxGuests, status |
| Combo | id (PK), name, totalPrice |
| ComboDetail | id (PK), comboId (FK), dishId (FK), quantity |
| User | id (PK), username, password, fullName, role |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  menu thống kê -> mnuStatistic
  mục thống kê món ăn -> mnuDishStatistic

Chọn Dishes statistics -> DishStatFrm xuất hiện:
  ô nhập ngày bắt đầu -> dtpStartDate
  ô nhập ngày kết thúc -> dtpEndDate
  nút thống kê -> btnStatistic
  bảng thống kê món ăn (clickable) -> dgvDishStat
  bảng chi tiết lần đặt món -> dgvOrderDetail
  nút quay lại -> btnBack

Nhập ngày và nhấn Statistics -> hệ thống thống kê món ăn -> cần phương thức:
  tên: getDishStatistic()
  đầu vào: startDate (Date), endDate (Date)
  đầu ra: list of Object[] {dishId, code, type, name, totalQty, totalRevenue}
  gán cho entity class: Dish (dữ liệu từ OrderDetail liên kết Dish).

Click vào món -> hệ thống lấy chi tiết lần đặt món -> cần phương thức:
  tên: getOrderDetailByDish()
  đầu vào: dishId (int), startDate (Date), endDate (Date)
  đầu ra: list of Object[] {invoiceId, customerName, orderDate, quantity, amount}
  gán cho entity class: OrderDetail.

Click vào món -> hệ thống lấy thông tin khách hàng -> cần phương thức:
  tên: getCustomerById()
  đầu vào: customerId (int)
  đầu ra: Customer
  gán cho entity class: Customer.

### Summary
View classes: HomeFrm, DishStatFrm
Methods: getDishStatistic(), getOrderDetailByDish(), getCustomerById()

### Quan hệ

```
Dish 1 --- n OrderDetail
Order 1 --- n OrderDetail
Table 1 --- n Order
Customer 1 --- n Order
User 1 --- n Order
Order 1 --- 1 Invoice
Customer 1 --- n Invoice
Combo 1 --- n OrderDetail
Combo 1 --- n ComboDetail
Dish 1 --- n ComboDetail
```

### ASCII Class Diagram

```
+------------------+          +------------------+
|      Dish        |          |      Table       |
+------------------+          +------------------+
| - id: int        |          | - id: int        |
| - code: String   |          | - code: String   |
| - type: String   |          | - name: String   |
| - name: String   |          | - maxGuests: int |
| - description    |          | - status: String |
| - price: float   |          +------------------+
+------------------+                  |
        |                             | 1
        | 1                           | n
        | n                     +------------------+
+------------------+            |      Order       |
|   OrderDetail    |            +------------------+
+------------------+            | - id: int        |
| - id: int        |            | - tableId: int   |
| - orderId: int   |     1    1 | - customerId: int|
| - dishId: int    |------------| - userId: int    |
| - comboId: int   |            | - orderDate      |
| - quantity: int  |            | - totalAmount    |
| - unitPrice: float|           | - status: String |
| - amount: float  |            +------------------+
+------------------+                    |
                                        | 1
+------------------+                    | 1
|     Combo        |            +------------------+
+------------------+            |     Invoice      |
| - id: int        |            +------------------+
| - name: String   |            | - id: int        |
| - totalPrice     |            | - orderId: int   |
+------------------+            | - customerId: int|
        |                       | - invoiceDate    |
        | 1                     | - totalOrders:int|
        | n                     | - totalAmount    |
+------------------+            | - paymentMethod  |
|  ComboDetail     |            +------------------+
+------------------+
| - id: int        |
| - comboId: int   |
| - dishId: int    |
| - quantity: int  |
+------------------+

+------------------+          +------------------+
|     Customer     |          |      User        |
+------------------+          +------------------+
| - id: int        |          | - id: int        |
| - code: String   |          | - username: String|
| - name: String   |          | - password: String|
| - phone: String  |          | - fullName: String|
| - email: String  |          | - role: String   |
| - address: String|          +------------------+
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

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Dish`, `<<boundary>> DishStatFrm`, `<<control>> OrderDetailDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-code: String`, `-price: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+getDishStatistic(startDate: Date, endDate: Date): List<Object[]>`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Dish | <<entity>> | -id: int, -code: String, -type: String, -name: String, -description: String, -price: float | +getDishStatistic(startDate: Date, endDate: Date): List<Object[]>, +getDishById(dishId: int): Dish |
| Order | <<entity>> | -id: int, -orderDate: Date, -totalAmount: float, -status: String | +getOrderById(orderId: int): Order |
| OrderDetail | <<entity>> | -id: int, -quantity: int, -unitPrice: float, -amount: float | +getOrderDetailByDish(dishId: int, startDate: Date, endDate: Date): List<Object[]> |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | +getCustomerById(customerId: int): Customer |
| Invoice | <<entity>> | -id: int, -invoiceDate: Date, -totalOrders: int, -totalAmount: float, -paymentMethod: String | +getInvoiceByOrderId(orderId: int): Invoice |
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -status: String | (không có) |
| Combo | <<entity>> | -id: int, -name: String, -totalPrice: float | (không có) |
| ComboDetail | <<entity>> | -id: int, -quantity: int | (không có) |
| User | <<entity>> | -id: int, -username: String, -password: String, -fullName: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -mnuStatistic: JMenu, -mnuDishStatistic: JMenuItem | Giao diện chính, chứa menu Statistics |
| DishStatFrm | <<boundary>> | -dtpStartDate: DateTimePicker, -dtpEndDate: DateTimePicker, -btnStatistic: JButton, -dgvDishStat: JTable, -dgvOrderDetail: JTable, -btnBack: JButton, -lblTotalRevenue: JLabel, -lblTotalDishes: JLabel | Giao diện thống kê món ăn |

Quy tắc đặt tên UI elements:
- Tiền tố `dtp` → DateTimePicker (chọn ngày): dtpStartDate, dtpEndDate
- Tiền tố `btn` → Button (nút bấm): btnStatistic, btnBack
- Tiền tố `dgv` → DataGridView (bảng dữ liệu): dgvDishStat, dgvOrderDetail
- Tiền tố `lbl` → Label (nhãn): lblTotalRevenue, lblTotalDishes
- Tiền tố `mnu` → Menu: mnuStatistic, mnuDishStatistic

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Dish → OrderDetail (món ăn tham chiếu đến chi tiết đơn).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập. Ví dụ: Combo → ComboDetail (chi tiết combo tham chiếu combo nhưng món ăn vẫn tồn tại độc lập).
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Order → OrderDetail (chi tiết đơn hàng không tồn tại nếu không có đơn hàng).
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: DishStatFrm → OrderDetailDAO (form sử dụng DAO để truy vấn).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Dish (1) → (n) OrderDetail nghĩa là một món ăn xuất hiện trong nhiều chi tiết đơn.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Dish | OrderDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều chi tiết đơn hàng |
| Order | OrderDetail | Composition | 1 - n | Một đơn hàng chứa nhiều chi tiết; chi tiết không tồn tại nếu không có đơn hàng |
| Table | Order | Association | 1 - n | Một bàn có nhiều đơn hàng |
| Customer | Order | Association | 1 - n | Một khách hàng có nhiều đơn hàng |
| User | Order | Association | 1 - n | Một nhân viên tạo nhiều đơn hàng |
| Order | Invoice | Association | 1 - 1 | Mỗi đơn hàng tạo ra đúng 1 hóa đơn |
| Customer | Invoice | Association | 1 - n | Một khách hàng có nhiều hóa đơn |
| Combo | OrderDetail | Association | 1 - n | Một combo xuất hiện trong nhiều chi tiết đơn |
| Combo | ComboDetail | Composition | 1 - n | Một combo chứa nhiều chi tiết món; chi tiết không tồn tại nếu không có combo |
| Dish | ComboDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều combo |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Dish → OrderDetail (1-n, Association)**
1. Tạo class `<<entity>> Dish` với các thuộc tính: -id: int, -code: String, -type: String, -name: String, -description: String, -price: float.
2. Tạo class `<<entity>> OrderDetail` với các thuộc tính: -id: int, -quantity: int, -unitPrice: float, -amount: float.
3. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
4. Click vào class Dish → kéo đến class OrderDetail.
5. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
6. Đặt tên association: "sold as" (tùy chọn).
7. Kết quả: Dish (1) ▷----(*) OrderDetail.

**Ví dụ 2: Vẽ quan hệ Combo → ComboDetail (1-n, Composition)**
1. Tạo class `<<entity>> Combo` với các thuộc tính: -id: int, -name: String, -totalPrice: float.
2. Tạo class `<<entity>> ComboDetail` với các thuộc tính: -id: int, -quantity: int.
3. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
4. Click vào class Combo → kéo đến class ComboDetail.
5. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
6. Kết quả: Combo (1) ◆----(*) ComboDetail.

---

## Câu 3: Thiết kế tĩnh

### View classes

**DishStatFrm** — Giao diện thống kê món ăn:
- `dtpStartDate`: DateTimePicker — chọn ngày bắt đầu thống kê
- `dtpEndDate`: DateTimePicker — chọn ngày kết thúc thống kê
- `btnStatistic`: Button — thực hiện thống kê
- `dgvDishStat`: DataGridView — hiển thị danh sách món ăn (mã, loại, tên, tổng số lượng bán, tổng doanh thu), sắp xếp giảm dần theo doanh thu
- `dgvOrderDetail`: DataGridView — hiển thị chi tiết lần đặt món khi click vào 1 dòng (mã hóa đơn, tên KH, ngày giờ, số lượng, thành tiền)
- `btnBack`: Button — quay lại bảng thống kê

**HomeFrm** — Giao diện chính:
- `mnuStatistic`: Menu — menu thống kê
- `mnuDishStatistic`: MenuItem — mở giao diện thống kê món ăn

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| dtpStartDate | DateTimePicker | Chọn ngày bắt đầu |
| dtpEndDate | DateTimePicker | Chọn ngày kết thúc |
| btnStatistic | Button | Nhấn để thống kê |
| dgvDishStat | DataGridView | Bảng thống kê món ăn |
| dgvOrderDetail | DataGridView | Bảng chi tiết lần đặt món |
| btnBack | Button | Quay lại bảng thống kê |
| lblTotalRevenue | JLabel | Hiển thị tổng doanh thu tất cả món |
| lblTotalDishes | JLabel | Hiển thị tổng số món bán được |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| OrderDetailDAO | `getDishStatistic(startDate, endDate): List<Object[]>` | Thống kê món ăn theo khoảng thời gian, mỗi dòng gồm: dishId, dishCode, dishType, dishName, totalQuantity, totalRevenue, sắp xếp giảm dần theo totalRevenue |
| OrderDetailDAO | `getOrderDetailByDish(dishId, startDate, endDate): List<Object[]>` | Lấy chi tiết các lần đặt 1 món cụ thể, mỗi dòng gồm: invoiceId, customerName, orderDate, quantity, amount |
| DishDAO | `getDishById(dishId): Dish` | Lấy thông tin món ăn theo mã |
| CustomerDAO | `getCustomerById(customerId): Customer` | Lấy thông tin khách hàng theo mã |
| OrderDAO | `getOrderById(orderId): Order` | Lấy thông tin đơn hàng theo mã |
| InvoiceDAO | `getInvoiceByOrderId(orderId): Invoice` | Lấy hóa đơn theo mã đơn hàng |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Table | Model | id: int, code: String, name: String, maxGuests: int, status: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Order | Model | id: int, tableId: int, customerId: int, userId: int, orderDate: Date, totalAmount: float, status: String |
| OrderDetail | Model | id: int, orderId: int, dishId: int, comboId: int, quantity: int, unitPrice: float, amount: float |
| Invoice | Model | id: int, orderId: int, customerId: int, invoiceDate: Date, totalOrders: int, totalAmount: float, paymentMethod: String |
| Combo | Model | id: int, name: String, totalPrice: float |
| ComboDetail | Model | id: int, comboId: int, dishId: int, quantity: int |
| User | Model | id: int, username: String, password: String, fullName: String, role: String |

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Dish | Nguồn dữ liệu chính, hiển thị thông tin món ăn trong bảng thống kê |
| OrderDetail | Chi tiết từng món trong đơn, dùng để tính tổng số lượng và doanh thu |
| Order | Liên kết chi tiết món với đơn hàng và khách hàng |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết đặt món |
| Invoice | Hóa đơn thanh toán, liên kết với đơn hàng |
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
   - `<<boundary>> DishStatFrm`: Ngăn 2 chứa UI elements (dtpStartDate, dtpEndDate, btnStatistic, dgvDishStat, dgvOrderDetail, btnBack, lblTotalRevenue, lblTotalDishes). Ngăn 3 trống.
   - `<<boundary>> HomeFrm`: Ngăn 2 chứa menu items (mnuStatistic, mnuDishStatistic). Ngăn 3 trống.
4. Tạo DAO classes trong package `dao`:
   - `<<control>> OrderDetailDAO`: Ngăn 2 trống. Ngăn 3: +getDishStatistic(startDate: Date, endDate: Date): List<Object[]>, +getOrderDetailByDish(dishId: int, startDate: Date, endDate: Date): List<Object[]>
   - `<<control>> DishDAO`: Ngăn 3: +getDishById(dishId: int): Dish
   - `<<control>> CustomerDAO`: Ngăn 3: +getCustomerById(customerId: int): Customer
   - `<<control>> OrderDAO`: Ngăn 3: +getOrderById(orderId: int): Order
   - `<<control>> InvoiceDAO`: Ngăn 3: +getInvoiceByOrderId(orderId: int): Invoice
5. Tạo Entity classes trong package `model` với stereotype `<<entity>>`, mỗi class có Ngăn 2 chứa attributes với kiểu dữ liệu (int, String, float, Date).
6. Vẽ Dependency (đường dashed, mũi tên tam giác rỗng ▷):
   - DishStatFrm → OrderDetailDAO (form sử dụng DAO)
   - DishStatFrm → CustomerDAO (form sử dụng DAO)
7. Vẽ Dependency từ DAO → Entity:
   - OrderDetailDAO → OrderDetail (DAO truy vấn entity)
   - DishDAO → Dish (DAO truy vấn entity)
   - CustomerDAO → Customer (DAO truy vấn entity)
   - OrderDAO → Order (DAO truy vấn entity)
   - InvoiceDAO → Invoice (DAO truy vấn entity)
8. Vẽ Association giữa các Entity classes theo bảng quan hệ ở trên.

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Manager` — actor (người sử dụng)
2. `:HomeFrm` — boundary (giao diện chính)
3. `:DishStatFrm` — boundary (giao diện thống kê)
4. `:OrderDetailDAO` — control (truy vấn chi tiết đơn hàng)
5. `:CustomerDAO` — control (truy vấn khách hàng)
6. `:InvoiceDAO` — control (truy vấn hóa đơn)

**Các bước vẽ:**
1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 6 lifelines theo thứ tự: Manager, HomeFrm, DishStatFrm, OrderDetailDAO, CustomerDAO, InvoiceDAO.
3. Vẽ message flow từ trên xuống theo bảng bên dưới.
4. Sử dụng reply message (dashed arrow) cho các giá trị trả về.
5. Sử dụng self-call cho các thao tác nội bộ của form.

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Loại |
|------|-----|-----|---------|------|
| 1 | Manager | HomeFrm | selectMenu("Statistics") | synchronous |
| 2 | HomeFrm | DishStatFrm | show() | synchronous |
| 3 | DishStatFrm | Manager | displayForm(dtpStartDate, dtpEndDate, btnStatistic) | return |
| 4 | Manager | DishStatFrm | enterDateRange(01/01/2026, 31/12/2026) | synchronous |
| 5 | Manager | DishStatFrm | clickStatistic() | synchronous |
| 6 | DishStatFrm | OrderDetailDAO | getDishStatistic(01/01/2026, 31/12/2026) | synchronous |
| 7 | OrderDetailDAO | OrderDetailDAO | query tblOrderDetail JOIN tblDish JOIN tblOrder | self |
| 8 | OrderDetailDAO | OrderDetailDAO | group by dishId, SUM(quantity), SUM(amount) | self |
| 9 | OrderDetailDAO | DishStatFrm | return List<Object[]> {dishId, code, type, name, totalQty, totalRevenue} | return |
| 10 | DishStatFrm | DishStatFrm | sortByRevenueDesc(data) | self |
| 11 | DishStatFrm | Manager | displayDishStatTable(sortedData) | return |
| 12 | Manager | DishStatFrm | clickRow("Phở bò", dishId=1) | synchronous |
| 13 | DishStatFrm | OrderDetailDAO | getOrderDetailByDish(1, 01/01/2026, 31/12/2026) | synchronous |
| 14 | OrderDetailDAO | OrderDetailDAO | query tblOrderDetail JOIN tblOrder JOIN tblInvoice JOIN tblCustomer | self |
| 15 | OrderDetailDAO | DishStatFrm | return List<Object[]> {invoiceId, customerName, orderDate, quantity, amount} | return |
| 16 | DishStatFrm | CustomerDAO | getCustomerById(customerId) | synchronous |
| 17 | CustomerDAO | DishStatFrm | return Customer | return |
| 18 | DishStatFrm | DishStatFrm | buildDetailList(details, customers) | self |
| 19 | DishStatFrm | Manager | displayOrderDetail(detailList) | return |
| 20 | Manager | DishStatFrm | clickBack() | synchronous |
| 21 | DishStatFrm | Manager | displayDishStatTable(previousData) | return |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê món ăn và xem chi tiết lần đặt

**Database trước:**

tblDish:
| id | code | type | name | description | price |
|----|------|------|------|-------------|-------|
| 1 | D001 | Món chính | Phở bò | Phở bò tái chín | 50000 |
| 2 | D002 | Món chính | Bún bò | Bún bò Huế | 55000 |
| 3 | D003 | Thức uống | Coca Cola | Lon 330ml | 15000 |
| 4 | D004 | Tráng miệng | Chè đậu | Chè đậu xanh | 20000 |

tblOrder:
| id | tableId | customerId | userId | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | 1 | 1 | 2026-03-15 11:30:00 | 145000 | Completed |
| 2 | 2 | 2 | 1 | 2026-03-18 12:15:00 | 205000 | Completed |
| 3 | 3 | 3 | 2 | 2026-03-22 18:30:00 | 165000 | Completed |
| 4 | 1 | 4 | 1 | 2026-04-05 19:00:00 | 120000 | Completed |

tblOrderDetail:
| id | orderId | dishId | comboId | quantity | unitPrice | amount |
|----|---------|--------|---------|----------|-----------|--------|
| 1 | 1 | 1 | NULL | 2 | 50000 | 100000 |
| 2 | 1 | 3 | NULL | 3 | 15000 | 45000 |
| 3 | 2 | 1 | NULL | 1 | 50000 | 50000 |
| 4 | 2 | 2 | NULL | 2 | 55000 | 110000 |
| 5 | 2 | 3 | NULL | 3 | 15000 | 45000 |
| 6 | 3 | 2 | NULL | 1 | 55000 | 55000 |
| 7 | 3 | 4 | NULL | 2 | 20000 | 40000 |
| 8 | 3 | 3 | NULL | 2 | 15000 | 30000 |
| 9 | 4 | 1 | NULL | 1 | 50000 | 50000 |
| 10 | 4 | 3 | NULL | 2 | 15000 | 30000 |
| 11 | 4 | 4 | NULL | 2 | 20000 | 40000 |

tblInvoice:
| id | orderId | customerId | invoiceDate | totalOrders | totalAmount | paymentMethod |
|----|---------|------------|-------------|-------------|-------------|---------------|
| 1 | 1 | 1 | 2026-03-15 12:30:00 | 2 | 145000 | Cash |
| 2 | 2 | 2 | 2026-03-18 13:00:00 | 3 | 205000 | Card |
| 3 | 3 | 3 | 2026-03-22 19:30:00 | 3 | 165000 | Cash |
| 4 | 4 | 4 | 2026-04-05 20:00:00 | 3 | 120000 | Card |

tblCustomer:
| id | code | name | phone | email |
|----|------|------|-------|-------|
| 1 | C001 | Nguyễn Văn A | 0901234567 | nva@gmail.com |
| 2 | C002 | Trần Thị B | 0912345678 | ttb@gmail.com |
| 3 | C003 | Lê Văn C | 0923456789 | lvc@gmail.com |
| 4 | C004 | Phạm Thị D | 0934567890 | ptd@gmail.com |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Manager chọn menu Statistics → Dishes statistics | Hiển thị giao diện thống kê với ô nhập ngày, nút Statistics |
| 3 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thức: 31/12/2026 | Các ô nhập hiển thị giá trị đã chọn |
| 4 | Manager nhấn nút Statistics | Hệ thống hiển thị bảng thống kê món ăn |
| 5 | Bảng hiển thị 4 dòng, sắp xếp giảm dần theo doanh thu | D001, Món chính, Phở bò, 4 bán, 200,000đ; D002, Món chính, Bún bò, 3 bán, 165,000đ; D003, Thức uống, Coca Cola, 10 bán, 150,000đ; D004, Tráng miệng, Chè đậu, 4 bán, 80,000đ |
| 6 | Manager nhấn vào dòng "Phở bò" (D001) | Hệ thống hiển thị bảng chi tiết lần đặt món bên dưới |
| 7 | Bảng chi tiết hiển thị 4 dòng | HD001, Nguyễn Văn A, 15/03/2026 11:30, 2, 100,000đ; HD002, Trần Thị B, 18/03/2026 12:15, 1, 50,000đ; HD004, Phạm Thị D, 05/04/2026 19:00, 1, 50,000đ |
| 8 | Manager nhấn nút Back | Quay về bảng thống kê món ăn, bảng chi tiết ẩn đi |

**Database sau:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).

---

### TC02: Thống kê với khoảng thời gian không có dữ liệu

**Database trước:**
- tblOrderDetail: không có chi tiết đơn hàng nào trong khoảng 01/06/2025 - 30/06/2025.

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager đăng nhập hệ thống | Đăng nhập thành công |
| 2 | Manager chọn Statistics → Dishes statistics | Hiển thị giao diện thống kê |
| 3 | Manager nhập ngày: 01/06/2025 - 30/06/2025 | Các ô nhập hiển thị giá trị |
| 4 | Manager nhấn Statistics | Bảng thống kê trống, hiển thị thông báo "Không có dữ liệu trong khoảng thời gian này" |

**Database sau:**
- Không có thay đổi dữ liệu.
