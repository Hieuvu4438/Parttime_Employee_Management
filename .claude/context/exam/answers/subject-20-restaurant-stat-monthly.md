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

### Entity types sử dụng

| Entity | Vai trò trong module |
|--------|---------------------|
| Invoice | Nguồn dữ liệu chính, dùng để tính tổng doanh thu và tổng số khách theo tháng |
| Customer | Thông tin khách hàng, hiển thị tên KH trong chi tiết hóa đơn |
| Order | Liên kết giữa hóa đơn và đơn hàng, dùng để đếm số món |
| Table | Thông tin bàn, liên kết với đơn hàng |
| User | Người tạo đơn hàng (nhân viên) |

### Database Schema

```sql
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

CREATE TABLE tblCustomer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(200)
);

CREATE TABLE tblTable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    name VARCHAR(50),
    maxGuests INT,
    status VARCHAR(20)
);

CREATE TABLE tblUser (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(100),
    fullName VARCHAR(100),
    role VARCHAR(20)
);
```

### Hướng dẫn vẽ trên Visual Paradigm

1. Mở Visual Pradesh → New → Class Diagram.
2. Tạo các class: Table, Customer, Order, OrderDetail, Invoice, User.
3. Thêm attributes cho mỗi class theo bảng ở trên.
4. Vẽ quan hệ:
   - Table → Order (1-n, Association)
   - Customer → Order (1-n, Association)
   - User → Order (1-n, Association)
   - Order → OrderDetail (1-n, Composition)
   - Order → Invoice (1-1, Association)
   - Customer → Invoice (1-n, Association)
5. Đặt tên cho mỗi association (ví dụ: "places", "generates", "pays").

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
