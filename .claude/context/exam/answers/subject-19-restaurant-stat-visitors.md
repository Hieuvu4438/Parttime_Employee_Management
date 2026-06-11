# Subject No. 19 — Restaurant Order — Module "Statistics of visitors by time slot"

> **Domain:** Restaurant Order Management
> **Entity classes:** Table, Customer, Order, OrderDetail, Invoice, TimeSlot, User

---

## Câu 1: Scenario — Thống kê khách theo khung giờ

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Manager nhập username `manager01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với menu Statistics. |
| 4 | Manager chọn menu **Statistics** → **Visitors by time slot**. |
| 5 | Giao diện thống kê xuất hiện: ô nhập ngày bắt đầu, ô nhập ngày kết thúc, nút View. |
| 6 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026, nhấn View. |
| 7 | Hệ thống hiển thị bảng kết quả: giờ bắt đầu, giờ kết thúc, số khách trung bình, doanh thu trung bình/khách, tổng doanh thu theo giờ. Dữ liệu ví dụ: 11:00-13:00 (52 khách TB, 85,000đ/khách, 4,420,000đ), 18:00-20:00 (78 khách TB, 110,000đ/khách, 8,580,000đ). |
| 8 | Manager nhấn vào dòng khung giờ "11:00-13:00" trong bảng. |
| 9 | Hệ thống hiển thị danh sách hóa đơn chi tiết: mã hóa đơn (HD001), tên khách hàng (Nguyễn Văn A), ngày (15/03/2026), tổng số món (5), tổng tiền (425,000đ). Tiếp tục: HD002, Trần Thị B, 15/03/2026, 3, 270,000đ; HD003, Lê Văn C, 16/03/2026, 4, 380,000đ. |
| 10 | Manager nhấn **Back** để quay về bảng thống kê khung giờ. |
| 11 | Manager nhấn **Home** để quay về giao diện chính. |

### Kịch bản ngoại lệ

- **EL1:** Ngày bắt đầu lớn hơn ngày kết thúc → hệ thống thông báo "Ngày bắt đầu phải nhỏ hơn ngày kết thúc".
- **EL2:** Không có dữ liệu trong khoảng thời gian đã chọn → bảng thống kê trống, hiển thị thông báo "Không có dữ liệu".
- **EL3:** Manager nhấn vào dòng khung giờ không có hóa đơn → bảng chi tiết trống.

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống nhà hàng quản lý việc đặt món và thanh toán cho khách hàng. Mỗi bàn ăn (Table) có thể chứa nhiều đơn hàng (Order). Mỗi khách hàng (Customer) có thể có nhiều đơn hàng. Mỗi đơn hàng chứa nhiều chi tiết món (OrderDetail), mỗi chi tiết liên kết đến một món ăn (Dish) hoặc combo. Mỗi đơn hàng tạo ra một hóa đơn (Invoice). Người dùng (User) là nhân viên hoặc quản lý thực hiện các thao tác trên hệ thống. Khung giờ (TimeSlot) dùng để phân loại thời gian phục vụ.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Bàn ăn (Table) | Entity class | Đối tượng chính: bàn ăn trong nhà hàng |
| Khách hàng (Customer) | Entity class | Người đặt món và thanh toán |
| Đơn hàng (Order) | Entity class | Bản ghi đặt món của khách tại bàn |
| Chi tiết đơn hàng (OrderDetail) | Entity class | Chi tiết từng món trong đơn |
| Hóa đơn (Invoice) | Entity class | Phiếu thanh toán cho đơn hàng |
| Món ăn (Dish) | Entity class | Món ăn riêng lẻ |
| Combo | Entity class | Combo nhiều món cho 1 người |
| Combo chi tiết (ComboDetail) | Entity class | Chi tiết các món trong combo |
| Khung giờ (TimeSlot) | Entity class | Khung giờ phục vụ để phân loại thống kê |
| Người dùng (User) | Entity class | Nhân viên/quản lý thao tác trên hệ thống |
| Mã bàn, tên bàn, số khách tối đa, mô tả | Attribute (Table) | Thuộc tính của Table |
| Mã KH, tên, SĐT, email, địa chỉ | Attribute (Customer) | Thuộc tính của Customer |
| Ngày đặt, tổng tiền, trạng thái | Attribute (Order) | Thuộc tính của Order |
| Số lượng, đơn giá, thành tiền | Attribute (OrderDetail) | Thuộc tính của OrderDetail |
| Ngày hóa đơn, tổng số món, tổng tiền, phương thức TT | Attribute (Invoice) | Thuộc tính của Invoice |
| Mã món, loại, tên, mô tả, giá | Attribute (Dish) | Thuộc tính của Dish |
| Tên combo, tổng giá | Attribute (Combo) | Thuộc tính của Combo |
| Giờ bắt đầu, giờ kết thúc | Attribute (TimeSlot) | Thuộc tính của TimeSlot |
| Username, password, họ tên, vai trò | Attribute (User) | Thuộc tính của User |
| Số khách trung bình, doanh thu TB/khách | Rejected (derived) | Giá trị tính toán, không lưu trong DB |

### Bảng thuộc tính

| Entity | Attributes |
|--------|------------|
| Table | id (PK), code, name, maxGuests, status |
| Customer | id (PK), code, name, phone, email, address |
| Order | id (PK), orderDate, totalAmount, status, table: Table (FK), customer: Customer (FK), user: User (FK) |
| OrderDetail | id (PK), quantity, unitPrice, amount, order: Order (FK), dish: Dish (FK), combo: Combo (FK) |
| Invoice | id (PK), invoiceDate, totalOrders, totalAmount, paymentMethod, order: Order (FK), customer: Customer (FK) |
| Dish | id (PK), code, type, name, description, price |
| Combo | id (PK), name, totalPrice, comboDetails: List<ComboDetail> |
| ComboDetail | id (PK), quantity, combo: Combo (FK), dish: Dish (FK) |
| TimeSlot | id (PK), startTime, endTime, description |
| User | id (PK), username, password, fullName, role |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  nút thống kê -> subStatistic

Chọn Visitors by time slot -> StatisticVisitorFrm xuất hiện:
  ô nhập ngày bắt đầu -> inStartDate
  ô nhập ngày kết thúc -> inEndDate
  nút xem -> subView
  bảng thống kê khung giờ (clickable) -> outsubTimeSlotStat
  bảng chi tiết hóa đơn -> outInvoiceDetail
  nút quay lại -> subBack

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
View classes: LoginFrm, HomeFrm, StatisticVisitorFrm
Methods: checkLogin(), getOrdersByDateRange(), getInvoicesByTimeSlot(), getCustomerById()

### Quan hệ

| Quan hệ | Kiểu | Multiplicity | Giải thích |
|----------|------|--------------|------------|
| Table → Order | Association | 1 - n | Một bàn có nhiều đơn hàng |
| Customer → Order | Association | 1 - n | Một khách hàng có nhiều đơn hàng |
| User → Order | Association | 1 - n | Một nhân viên tạo nhiều đơn hàng |
| Order → OrderDetail | Composition | 1 - n | Một đơn hàng chứa nhiều chi tiết; chi tiết không tồn tại nếu không có đơn hàng |
| Dish → OrderDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều chi tiết đơn |
| Combo → OrderDetail | Association | 1 - n | Một combo xuất hiện trong nhiều chi tiết đơn |
| Order → Invoice | Association | 1 - 1 | Mỗi đơn hàng tạo ra đúng 1 hóa đơn |
| Customer → Invoice | Association | 1 - n | Một khách hàng có nhiều hóa đơn |
| Combo → ComboDetail | Composition | 1 - n | Một combo chứa nhiều chi tiết món; chi tiết không tồn tại nếu không có combo |
| Dish → ComboDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều combo |

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
| Order | <<entity>> | -id: int, -orderDate: Date, -totalAmount: float, -status: String, -table: Table, -customer: Customer, -user: User | +getOrdersByDateRange(startDate: Date, endDate: Date): List<Order> |
| OrderDetail | <<entity>> | -id: int, -quantity: int, -unitPrice: float, -amount: float, -order: Order, -dish: Dish, -combo: Combo | (không có) |
| Invoice | <<entity>> | -id: int, -invoiceDate: Date, -totalOrders: int, -totalAmount: float, -paymentMethod: String, -order: Order, -customer: Customer | +getInvoicesByTimeSlot(startTime: String, endTime: String, startDate: Date, endDate: Date): List<Invoice> |
| Dish | <<entity>> | -id: int, -code: String, -type: String, -name: String, -description: String, -price: float | (không có) |
| Combo | <<entity>> | -id: int, -name: String, -totalPrice: float | (không có) |
| ComboDetail | <<entity>> | -id: int, -quantity: int | (không có) |
| TimeSlot | <<entity>> | -id: int, -startTime: String, -endTime: String, -description: String | (không có) |
| User | <<entity>> | -id: int, -username: String, -password: String, -fullName: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -subStatistic: JButton | Giao diện chính, chứa nút chọn Statistics |
| StatisticVisitorFrm | <<boundary>> | -inStartDate: JTextField, -inEndDate: JTextField, -subView: JButton, -outsubTimeSlotStat: JTable, -outInvoiceDetail: JTable, -subBack: JButton | Giao diện thống kê khách theo khung giờ |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inStartDate, inEndDate
- Tiền tố `out` → output (vùng hiển thị): outInvoiceDetail
- Tiền tố `outsub` → clickable output (bảng click được): outsubTimeSlotStat
- Tiền tố `sub` → submit (nút bấm): subView, subBack, subStatistic

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

**LoginFrm** — Giao diện đăng nhập:
- `inUsername`: JTextField — nhập tên đăng nhập
- `inPassword`: JPasswordField — nhập mật khẩu
- `subLogin`: JButton — nút Login

**HomeFrm** — Giao diện chính:
- `subStatistic`: JButton — nút chọn chức năng Statistics

**StatisticVisitorFrm** — Giao diện thống kê khách theo khung giờ:
- `inStartDate`: JTextField — nhập ngày bắt đầu thống kê
- `inEndDate`: JTextField — nhập ngày kết thúc thống kê
- `subView`: JButton — nút xem thống kê
- `outsubTimeSlotStat`: JTable — bảng danh sách khung giờ, click để chọn (giờ bắt đầu, giờ kết thúc, số khách TB, doanh thu TB/khách, tổng doanh thu)
- `outInvoiceDetail`: JTable — bảng chi tiết hóa đơn (mã hóa đơn, tên KH, ngày, tổng số món, tổng tiền)
- `subBack`: JButton — nút quay lại

### UI Elements

| UI Element | Kiểu | Mô tả |
|------------|------|-------|
| inStartDate | JTextField | Nhập ngày bắt đầu |
| inEndDate | JTextField | Nhập ngày kết thúc |
| subView | JButton | Nhấn để xem thống kê |
| outsubTimeSlotStat | JTable | Bảng kết quả thống kê khung giờ (clickable) |
| outInvoiceDetail | JTable | Bảng chi tiết hóa đơn |
| subBack | JButton | Quay lại bảng thống kê |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| OrderDAO | `getOrdersByDateRange(startDate, endDate): List<Order>` | Lấy danh sách đơn hàng theo khoảng thời gian |
| InvoiceDAO | `getInvoicesByTimeSlot(startTime, endTime, startDate, endDate): List<Invoice>` | Lấy hóa đơn theo khung giờ |
| CustomerDAO | `getCustomerById(customerId): Customer` | Lấy thông tin khách hàng theo mã |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, status: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Order | Model | id: int, orderDate: Date, totalAmount: float, status: String, table: Table (FK), customer: Customer (FK), user: User (FK) |
| OrderDetail | Model | id: int, quantity: int, unitPrice: float, amount: float, order: Order (FK), dish: Dish (FK), combo: Combo (FK) |
| Invoice | Model | id: int, invoiceDate: Date, totalOrders: int, totalAmount: float, paymentMethod: String, order: Order (FK), customer: Customer (FK) |
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Combo | Model | id: int, name: String, totalPrice: float, comboDetails: List<ComboDetail> |
| ComboDetail | Model | id: int, quantity: int, combo: Combo (FK), dish: Dish (FK) |
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
| tableID | int | FOREIGN KEY → tblTable(ID), NOT NULL |
| customerID | int | FOREIGN KEY → tblCustomer(ID), NULLABLE |
| userID | int | FOREIGN KEY → tblUser(ID), NOT NULL |
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
| orderID | int | FOREIGN KEY → tblOrder(ID), NOT NULL |
| customerID | int | FOREIGN KEY → tblCustomer(ID), NULLABLE |
| invoiceDate | datetime | NOT NULL |
| totalOrders | int | |
| totalAmount | float | NOT NULL |
| paymentMethod | varchar(20) | |

### Hướng dẫn vẽ Design Class Diagram trên Visual Paradigm

**Các bước vẽ:**

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo 3 package: `view.statistic`, `model`, `dao`.
3. Tạo View classes:
   - `<<boundary>> LoginFrm`: Ngăn 2 chứa (inUsername: JTextField, inPassword: JPasswordField, subLogin: JButton). Ngăn 3 trống.
   - `<<boundary>> HomeFrm`: Ngăn 2 chứa (subStatistic: JButton). Ngăn 3 trống.
   - `<<boundary>> StatisticVisitorFrm`: Ngăn 2 chứa UI elements (inStartDate: JTextField, inEndDate: JTextField, subView: JButton, outsubTimeSlotStat: JTable, outInvoiceDetail: JTable, subBack: JButton). Ngăn 3 trống.
4. Tạo DAO classes trong package `dao`:
   - `<<control>> OrderDAO`: Ngăn 2 trống. Ngăn 3: +getOrdersByDateRange(startDate: Date, endDate: Date): List<Order>
   - `<<control>> InvoiceDAO`: Ngăn 3: +getInvoicesByTimeSlot(startTime: String, endTime: String, startDate: Date, endDate: Date): List<Invoice>
   - `<<control>> CustomerDAO`: Ngăn 3: +getCustomerById(customerId: int): Customer
   - `<<control>> UserDAO`: Ngăn 3: +checkLogin(username: String, password: String): boolean
5. Tạo Entity classes trong package `model` với stereotype `<<entity>>`, mỗi class có Ngăn 2 chứa attributes với kiểu dữ liệu (int, String, float, Date).
6. Vẽ Dependency (đường dashed, mũi tên tam giác rỗng ▷):
   - LoginFrm → UserDAO (form sử dụng DAO để đăng nhập)
   - StatisticVisitorFrm → OrderDAO (form sử dụng DAO để truy vấn đơn hàng)
   - StatisticVisitorFrm → InvoiceDAO (form sử dụng DAO để truy vấn hóa đơn)
   - StatisticVisitorFrm → CustomerDAO (form sử dụng DAO để truy vấn khách hàng)
7. Vẽ Dependency từ DAO → Entity:
   - UserDAO → User (DAO truy vấn entity)
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

| # | Message | Từ | Đến | Mô tả |
|---|---------|----|-----|-------|
| 1 | selectMenu("Statistics") | Manager | HomeFrm | Manager chọn menu Statistics |
| 2 | show() | HomeFrm | StatisticVisitorFrm | Mở giao diện thống kê |
| 3 | displayForm() | StatisticVisitorFrm | Manager | Hiển thị ô nhập ngày, nút View |
| 4 | enterDateRange() | Manager | StatisticVisitorFrm | Nhập ngày 01/01/2026 - 31/12/2026 |
| 5 | clickView() | Manager | StatisticVisitorFrm | Nhấn nút View |
| 6 | getOrdersByDateRange() | StatisticVisitorFrm | OrderDAO | Gọi OrderDAO.getOrdersByDateRange(01/01/2026, 31/12/2026) |
| 7 | query DB | OrderDAO | Database | Truy vấn tblOrder |
| 8 | return List<Order> | OrderDAO | StatisticVisitorFrm | Trả về danh sách đơn hàng |
| 9 | groupOrdersByTimeSlot() | StatisticVisitorFrm | StatisticVisitorFrm | Nhóm đơn theo khung giờ (self-call) |
| 10 | calculateAvgVisitors() | StatisticVisitorFrm | StatisticVisitorFrm | Tính số khách TB/khung giờ (self-call) |
| 11 | calculateAvgRevenuePerGuest() | StatisticVisitorFrm | StatisticVisitorFrm | Tính doanh thu TB/khách (self-call) |
| 12 | calculateTotalHourlyRevenue() | StatisticVisitorFrm | StatisticVisitorFrm | Tính tổng doanh thu/khung giờ (self-call) |
| 13 | displayTimeSlotTable() | StatisticVisitorFrm | Manager | Hiển thị bảng thống kê khung giờ |
| 14 | clickRow("11:00-13:00") | Manager | StatisticVisitorFrm | Nhấn vào dòng khung giờ |
| 15 | getInvoicesByTimeSlot() | StatisticVisitorFrm | InvoiceDAO | Gọi InvoiceDAO.getInvoicesByTimeSlot("11:00", "13:00", 01/01/2026, 31/12/2026) |
| 16 | query DB | InvoiceDAO | Database | Truy vấn tblInvoice JOIN tblOrder |
| 17 | return List<Invoice> | InvoiceDAO | StatisticVisitorFrm | Trả về danh sách hóa đơn |
| 18 | getCustomerById() | StatisticVisitorFrm | CustomerDAO | Gọi CustomerDAO.getCustomerById(customerId) |
| 19 | query DB | CustomerDAO | Database | Truy vấn tblCustomer |
| 20 | return Customer | CustomerDAO | StatisticVisitorFrm | Trả về thông tin khách hàng |
| 21 | buildInvoiceDetailList() | StatisticVisitorFrm | StatisticVisitorFrm | Xây dựng danh sách chi tiết (self-call) |
| 22 | displayInvoiceDetail() | StatisticVisitorFrm | Manager | Hiển thị bảng chi tiết hóa đơn |
| 23 | clickBack() | Manager | StatisticVisitorFrm | Nhấn nút Back |
| 24 | displayTimeSlotTable() | StatisticVisitorFrm | Manager | Quay về bảng thống kê khung giờ |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Stat visitors | Thống kê thành công có dữ liệu và xem chi tiết hóa đơn |
| TC02 | Stat visitors | Khoảng thời gian không có dữ liệu |
| TC03 | Stat visitors | Ngày bắt đầu lớn hơn ngày kết thúc |
| TC04 | Stat visitors | Click vào khung giờ không có hóa đơn |
| TC05 | Stat visitors | Thống kê với khoảng thời gian 1 ngày |

### TC01: Thống kê khách theo khung giờ và xem chi tiết hóa đơn

**Purpose:** Kiểm tra chức năng thống kê khách theo khung giờ hiển thị đúng dữ liệu và xem chi tiết hóa đơn khi click vào một khung giờ.

**Database trước:**

**tblUser:**
| ID | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | manager01 | 123456 | Nguyen Van M | manager |
| 2 | staff01 | 123456 | Tran Van S | staff |

**tblTable:**
| ID | code | name | maxGuests | status |
|----|------|------|-----------|--------|
| 1 | B01 | Bàn VIP 1 | 6 | active |
| 2 | B02 | Bàn thường 1 | 4 | active |
| 3 | B03 | Bàn thường 2 | 4 | active |

**tblCustomer:**
| ID | code | name | phone | email | address |
|----|------|------|-------|-------|---------|
| 1 | C001 | Nguyễn Văn A | 0901234567 | nva@gmail.com | Ha Noi |
| 2 | C002 | Trần Thị B | 0912345678 | ttb@gmail.com | HCM |
| 3 | C003 | Lê Văn C | 0923456789 | lvc@gmail.com | Da Nang |
| 4 | C004 | Phạm Thị D | 0934567890 | ptd@gmail.com | Hue |
| 5 | C005 | Hoàng Văn E | 0945678901 | hve@gmail.com | Can Tho |

**tblOrder:**
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
| 1 | Manager nhập `manager01` / `123456`, nhấn Login | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Manager chọn menu Statistics → Visitors by time slot | Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút View |
| 3 | Manager nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026 | Các ô nhập hiển thị giá trị đã chọn |
| 4 | Manager nhấn nút View | Hệ thống hiển thị bảng thống kê: 11:00-13:00 (3 khách TB, 358,333đ/khách, 1,075,000đ), 18:00-20:00 (2 khách TB, 585,000đ/khách, 1,170,000đ) |
| 5 | Kiểm tra DB | tblOrder, tblInvoice không thay đổi (chức năng chỉ đọc) |
| 6 | Manager nhấn vào dòng "11:00-13:00" | Hệ thống hiển thị bảng chi tiết hóa đơn bên dưới |
| 7 | Bảng chi tiết hiển thị 3 dòng: HD001, Nguyễn Văn A, 15/03/2026, 5 món, 425,000đ; HD002, Trần Thị B, 15/03/2026, 3 món, 270,000đ; HD003, Lê Văn C, 16/03/2026, 4 món, 380,000đ | Dữ liệu hiển thị chính xác |
| 8 | Manager nhấn nút Back | Quay về bảng thống kê khung giờ, bảng chi tiết ẩn đi |

**Database sau:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).

---

### TC02: Thống kê với khoảng thời gian không có dữ liệu

**Purpose:** Kiểm tra hệ thống xử lý đúng khi không có dữ liệu trong khoảng thời gian đã chọn.

**Database trước:**

**tblUser:**
| ID | username | password | fullName | role |
|----|----------|----------|----------|------|
| 1 | manager01 | 123456 | Nguyen Van M | manager |

**tblTable:** (giống TC01)

**tblCustomer:** (giống TC01)

**tblOrder:** (giống TC01 — nhưng không có đơn nào trong khoảng 01/06/2025 - 30/06/2025)

**tblInvoice:** (giống TC01)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Manager nhập `manager01` / `123456`, nhấn Login | Đăng nhập thành công |
| 2 | Manager chọn Statistics → Visitors by time slot | Hiển thị giao diện thống kê |
| 3 | Manager nhập ngày: 01/06/2025 - 30/06/2025 | Các ô nhập hiển thị giá trị |
| 4 | Manager nhấn View | Bảng thống kê trống, hiển thị thông báo "Không có dữ liệu trong khoảng thời gian này" |

**Database sau:**
- Không có thay đổi dữ liệu (chức năng chỉ đọc).
