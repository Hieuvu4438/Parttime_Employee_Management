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

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Dish | Nguồn dữ liệu chính, hiển thị thông tin món ăn trong bảng thống kê |
| OrderDetail | Chi tiết từng món trong đơn, dùng để tính tổng số lượng và doanh thu |
| Order | Liên kết chi tiết món với đơn hàng và khách hàng |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết đặt món |
| Invoice | Hóa đơn thanh toán, liên kết với đơn hàng |
| User | Người tạo đơn hàng (nhân viên) |

### Database Schema

```sql
CREATE TABLE tblDish (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    type VARCHAR(50),
    name VARCHAR(100),
    description VARCHAR(200),
    price FLOAT
);

CREATE TABLE tblOrderDetail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    orderId INT,
    dishId INT,
    comboId INT,
    quantity INT,
    unitPrice FLOAT,
    amount FLOAT,
    FOREIGN KEY (orderId) REFERENCES tblOrder(id),
    FOREIGN KEY (dishId) REFERENCES tblDish(id),
    FOREIGN KEY (comboId) REFERENCES tblCombo(id)
);

CREATE TABLE tblOrder (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tableId INT,
    customerId INT,
    userId INT,
    orderDate DATETIME,
    totalAmount FLOAT,
    status VARCHAR(20),
    FOREIGN KEY (tableId) REFERENCES tblTable(id),
    FOREIGN KEY (customerId) REFERENCES tblCustomer(id),
    FOREIGN KEY (userId) REFERENCES tblUser(id)
);

CREATE TABLE tblInvoice (
    id INT PRIMARY KEY AUTO_INCREMENT,
    orderId INT,
    customerId INT,
    invoiceDate DATETIME,
    totalOrders INT,
    totalAmount FLOAT,
    paymentMethod VARCHAR(20),
    FOREIGN KEY (orderId) REFERENCES tblOrder(id),
    FOREIGN KEY (customerId) REFERENCES tblCustomer(id)
);

CREATE TABLE tblCustomer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(200)
);

CREATE TABLE tblCombo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    totalPrice FLOAT
);

CREATE TABLE tblComboDetail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comboId INT,
    dishId INT,
    quantity INT,
    FOREIGN KEY (comboId) REFERENCES tblCombo(id),
    FOREIGN KEY (dishId) REFERENCES tblDish(id)
);
```

### Hướng dẫn vẽ trên Visual Paradigm

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo các class: Dish, Order, OrderDetail, Customer, Invoice, Table, Combo, ComboDetail, User.
3. Thêm attributes cho mỗi class theo bảng ở trên.
4. Vẽ quan hệ:
   - Dish → OrderDetail (1-n, Association)
   - Order → OrderDetail (1-n, Composition)
   - Table → Order (1-n, Association)
   - Customer → Order (1-n, Association)
   - User → Order (1-n, Association)
   - Order → Invoice (1-1, Association)
   - Customer → Invoice (1-n, Association)
   - Combo → OrderDetail (1-n, Association)
   - Combo → ComboDetail (1-n, Composition)
   - Dish → ComboDetail (1-n, Association)
5. Đặt tên cho mỗi association (ví dụ: "contains", "generates", "belongs to").

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
