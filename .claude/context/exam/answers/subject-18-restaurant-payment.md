# Subject No. 18 — Restaurant — Module "Payment"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Thanh toán

### Kịch bản chính

| Bước | Diễn biến | Giao diện hiển thị |
|------|-----------|---------------------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. | **LoginFrm** |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. | **LoginFrm** |
| 3 | Giao diện Home xuất hiện với các chức năng: Order, Book a table, Payment, Combo management, Statistics. | **HomeFrm** |
| 4 | Khách hàng yêu cầu thanh toán. Staff chọn chức năng **Payment**. | **HomeFrm** |
| 5 | Giao diện chọn bàn **PaymentFrm** xuất hiện: danh sách bàn có đơn hàng chưa thanh toán, hiển thị dưới dạng bảng hoặc combobox. | **PaymentFrm** |
| 6 | Khách chọn bàn "B01". Staff nhấn chọn bàn B01. | **PaymentFrm** |
| 7 | Giao diện hóa đơn chi tiết **PaymentFrm** xuất hiện: thông tin bàn (B01 — Bàn VIP 1), tên nhân viên (staff01), tên khách hàng (nếu có), bảng danh sách món đã gọi: cột STT, Tên món, Đơn giá, Số lượng, Thành tiền. Dòng cuối: Tổng tiền. | **PaymentFrm** |
| 8 | Staff hỏi khách có mã giảm giá không. Khách có mã "GIAM10". | **PaymentFrm** |
| 9 | Staff nhấn **Add coupon** (btnAddCoupon). Ô nhập mã giảm giá (txtCouponCode) xuất hiện. | **PaymentFrm** |
| 10 | Staff nhập "GIAM10" vào ô mã, nhấn OK. | **PaymentFrm** |
| 11 | Hệ thống kiểm tra mã hợp lệ. Hóa đơn cập nhật: thêm dòng "Mã giảm giá: GIAM10 — Giảm 10%". Tổng tiền cập nhật: 210,000đ × 90% = 189,000đ. | **PaymentFrm** |
| 12 | Staff thông báo số tiền 189,000đ cho khách. Khách thanh toán. | **PaymentFrm** |
| 13 | Staff nhấn **Confirm** (btnConfirm). Hệ thống lưu hóa đơn vào database (tblInvoice). | **PaymentFrm** |
| 14 | Hệ thống thông báo "Thanh toan thanh cong" và in hóa đơn cho khách. Quay về giao diện Home. | **HomeFrm** |

### Kịch bản ngoại lệ

- **EL1:** Bàn không có đơn hàng nào → không hiển thị trong danh sách chọn bàn.
- **EL2:** Mã giảm giá không hợp lệ hoặc đã hết hạn → hệ thống cảnh báo "Ma giam gia khong hop le".
- **EL3:** Staff nhấn Confirm khi tổng tiền = 0 → hệ thống cảnh báo "Hoa don khong co mon nao".
- **EL4:** Staff muốn chỉnh sửa món trong hóa đơn → có thể thêm/sửa/xóa dòng món trước khi Confirm.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Nhà hàng quản lý việc thanh toán cho khách. Khi khách yêu cầu thanh toán, nhân viên chọn bàn có đơn hàng. Hệ thống hiển thị hóa đơn chi tiết gồm thông tin bàn, nhân viên, khách hàng và danh sách món đã gọi với đơn giá, số lượng và thành tiền. Nếu khách có mã giảm giá (coupon), nhân viên nhập mã để áp dụng. Mỗi coupon có mã và tỷ lệ giảm giá. Sau khi thanh toán, hệ thống tạo hóa đơn (invoice) lưu lại thông tin đơn hàng, coupon áp dụng, tổng tiền và số tiền thực nhận. Hóa đơn được in cho khách.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Table | Entity class | | Bàn ăn có đơn hàng |
| Customer | Entity class | | Khách hàng thanh toán |
| Order | Entity class | | Đơn hàng cần thanh toán |
| OrderDetail | Entity class | | Chi tiết món trong đơn |
| Dish | Entity class | | Món ăn đã gọi |
| Combo | Entity class | | Combo đã gọi |
| Coupon | Entity class | | Mã giảm giá |
| code | Attribute (Coupon) | String | Mã coupon |
| discount | Attribute (Coupon) | float | Tỷ lệ giảm giá |
| Invoice | Entity class | | Hóa đơn thanh toán |
| totalAmount | Attribute (Invoice) | float | Tổng tiền |
| paidAmount | Attribute (Invoice) | float | Số tiền thực nhận |
| invoiceDate | Attribute (Invoice) | Date | Ngày hóa đơn |
| User | Entity class | | Nhân viên thực hiện |

### Class Diagram (ASCII)

```
+------------------+       +------------------+       +------------------+
|     Table        |       |    Customer      |       |      User        |
+------------------+       +------------------+       +------------------+
| -id: int         |       | -id: int         |       | -id: int         |
| -code: String    |       | -code: String    |       | -username: String|
| -name: String    |       | -name: String    |       | -password: String|
| -maxGuests: int  |       | -phone: String   |       | -role: String    |
| -description: String|    | -email: String   |       +------------------+
+------------------+       | -address: String |               | 1
         | 1               +------------------+               |
         |                        | 1                         | n
         |                        |                           v
         | n                      | n                +------------------+
         v                        v                  |                  |
+------------------+                               |                  |
|     Order        |                               |                  |
+------------------+                               |                  |
| -id: int         |                               |                  |
| -orderDate: Date |                               |                  |
| -totalAmount: float|                             |                  |
| -status: String  |                               |                  |
+------------------+                               |                  |
         | 1                                       |                  |
         |                                         |                  |
         | n                                       |                  |
         v                                         |                  |
+------------------+                               |                  |
|  OrderDetail     |                               |                  |
+------------------+                               |                  |
| -id: int         |                               |                  |
| -quantity: int   |                               |                  |
| -unitPrice: float|                               |                  |
| -amount: float   |                               |                  |
+------------------+                               |                  |
         | n                                       |                  |
         |                                         |                  |
         +------+------+                            |                  |
                |      |                            |                  |
                v      v                            |                  |
+------------------+  +------------------+          |                  |
|      Dish        |  |     Combo        |          |                  |
+------------------+  +------------------+          |                  |
| -id: int         |  | -id: int         |          |                  |
| -code: String    |  | -name: String    |          |                  |
| -name: String    |  | -totalPrice: float|         |                  |
| -price: float    |  +------------------+          |                  |
+------------------+                                |                  |
                                                    |                  |
+------------------+       +------------------+     |                  |
|     Coupon       |       |    Invoice       |     |                  |
+------------------+       +------------------+     |                  |
| -id: int         |       | -id: int         |     |                  |
| -code: String    |       | -totalAmount: float|    |                  |
| -discount: float |       | -paidAmount: float|    |                  |
+------------------+       | -invoiceDate: Date|    |                  |
         | 1               +------------------+     |                  |
         |                        | 1               |                  |
         |                        |                 |                  |
         | n                      | n               |                  |
         +------------------------+                 |                  |
                                                    |                  |
                                                    +------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Table → Order | 1-n | Một bàn có nhiều đơn hàng |
| Customer → Order | 1-n | Một khách hàng có nhiều đơn hàng |
| User → Order | 1-n | Một nhân viên xử lý nhiều đơn |
| Order → OrderDetail | 1-n | Một đơn hàng có nhiều chi tiết |
| Dish → OrderDetail | 1-n | Một món trong nhiều chi tiết đơn |
| Combo → OrderDetail | 1-n | Một combo trong nhiều chi tiết đơn |
| Order → Invoice | 1-1 | Mỗi đơn hàng có 1 hóa đơn |
| Coupon → Invoice | 1-n | Một coupon áp dụng cho nhiều hóa đơn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity class.
3. Tạo view class boxes từ các interface (form) của module.
4. Vẽ mối quan hệ (relationship) giữa các class.
5. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Invoice`, `<<boundary>> PaymentFrm`, `<<control>> InvoiceDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-totalAmount: float`, `-paidAmount: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+addInvoice(invoice: Invoice): boolean`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -description: String | +getTablesWithActiveOrder(): List<Table> |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | (không có) |
| Dish | <<entity>> | -id: int, -code: String, -type: String, -name: String, -description: String, -price: float | (không có) |
| Combo | <<entity>> | -id: int, -name: String, -totalPrice: float | (không có) |
| Order | <<entity>> | -id: int, -orderDate: Date, -totalAmount: float, -status: String | +getActiveOrderByTable(tableId: int): Order, +updateOrderStatus(orderId: int, status: String): boolean |
| OrderDetail | <<entity>> | -id: int, -quantity: int, -unitPrice: float, -amount: float | +getOrderDetails(orderId: int): List<OrderDetail> |
| Coupon | <<entity>> | -id: int, -code: String, -discount: float | +getCouponByCode(code: String): Coupon |
| Invoice | <<entity>> | -id: int, -totalAmount: float, -paidAmount: float, -invoiceDate: Date | +addInvoice(invoice: Invoice): boolean |
| User | <<entity>> | -id: int, -username: String, -password: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -subPayment: JButton | Giao diện chính, chứa nút chọn Payment |
| PaymentFrm | <<boundary>> | -inTable: JComboBox, -outOrderInfo: JPanel, -outsubListOrderDetail: JTable, -outTotalAmount: JLabel, -subAddCoupon: JButton, -inCouponCode: JTextField, -subApplyCoupon: JButton, -outCouponInfo: JLabel, -subConfirm: JButton | Giao diện thanh toán |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inTable, inCouponCode
- Tiền tố `out` → output (vùng hiển thị): outOrderInfo, outTotalAmount, outCouponInfo
- Tiền tố `outsub` → clickable output (bảng click được): outsubListOrderDetail
- Tiền tố `sub` → submit (nút bấm): subAddCoupon, subApplyCoupon, subConfirm

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Coupon → Invoice (mã giảm giá tham chiếu đến hóa đơn).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent. Ví dụ: Order → OrderDetail (chi tiết đơn hàng không tồn tại nếu không có đơn hàng).
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: PaymentFrm → CouponDAO (form sử dụng DAO để tìm mã giảm giá).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Order (1) → (1) Invoice nghĩa là mỗi đơn hàng có đúng 1 hóa đơn.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Table | Order | Association | 1 - n | Một bàn có nhiều đơn hàng |
| Customer | Order | Association | 1 - n | Một khách hàng có nhiều đơn hàng |
| User | Order | Association | 1 - n | Một nhân viên xử lý nhiều đơn hàng |
| Order | OrderDetail | Composition | 1 - n | Một đơn hàng chứa nhiều chi tiết; chi tiết không tồn tại nếu không có đơn hàng |
| Dish | OrderDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều chi tiết đơn |
| Combo | OrderDetail | Association | 1 - n | Một combo xuất hiện trong nhiều chi tiết đơn |
| Order | Invoice | Association | 1 - 1 | Mỗi đơn hàng tạo ra đúng 1 hóa đơn thanh toán |
| Coupon | Invoice | Association | 1 - n | Một mã giảm giá áp dụng cho nhiều hóa đơn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Order → Invoice (1-1, Association)**
1. Tạo class `<<entity>> Order` và `<<entity>> Invoice` với các thuộc tính tương ứng.
2. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
3. Click vào class Order → kéo đến class Invoice.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = 1.
5. Đặt tên association: "generates" (tùy chọn).
6. Kết quả: Order (1) ▷----(1) Invoice.

**Ví dụ 2: Vẽ quan hệ Coupon → Invoice (1-n, Association)**
1. Tạo class `<<entity>> Coupon` với các thuộc tính: -id: int, -code: String, -discount: float.
2. Chọn công cụ **Association** từ palette Relationships.
3. Click vào class Coupon → kéo đến class Invoice.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Đặt tên association: "applied to" (tùy chọn).
6. Kết quả: Coupon (1) ▷----(*) Invoice.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  một tùy chọn thanh toán -> subPayment

Chọn Payment -> PaymentFrm xuất hiện:
  combobox chọn bàn có đơn chưa thanh toán -> inTable
  thông tin đơn hàng -> outOrderInfo
  bảng chi tiết hóa đơn -> outsubListOrderDetail
  tổng tiền -> outTotalAmount
  nút thêm mã giảm giá -> subAddCoupon
  ô nhập mã giảm giá -> inCouponCode
  nút áp dụng mã -> subApplyCoupon
  thông tin mã giảm giá -> outCouponInfo
  nút xác nhận -> subConfirm

Khi mở PaymentFrm -> hệ thống lấy danh sách bàn có đơn chưa thanh toán -> cần phương thức:
  tên: getTablesWithActiveOrder()
  đầu vào: (không có)
  đầu ra: list of Table
  gán cho entity class: Table.

Chọn bàn -> hệ thống lấy đơn hàng theo bàn -> cần phương thức:
  tên: getActiveOrderByTable()
  đầu vào: tableId (int)
  đầu ra: Order
  gán cho entity class: Order.

Chọn bàn -> hệ thống lấy chi tiết đơn hàng -> cần phương thức:
  tên: getOrderDetails()
  đầu vào: orderId (int)
  đầu ra: list of OrderDetail
  gán cho entity class: OrderDetail.

Nhập mã giảm giá -> hệ thống tìm coupon -> cần phương thức:
  tên: getCouponByCode()
  đầu vào: code (String)
  đầu ra: Coupon
  gán cho entity class: Coupon.

Nhấn Confirm -> hệ thống lưu hóa đơn -> cần phương thức:
  tên: addInvoice()
  đầu vào: invoice (Invoice)
  đầu ra: boolean
  gán cho entity class: Invoice.

Nhấn Confirm -> hệ thống cập nhật trạng thái đơn hàng -> cần phương thức:
  tên: updateOrderStatus()
  đầu vào: orderId (int), status (String)
  đầu ra: boolean
  gán cho entity class: Order.

### Summary
View classes: HomeFrm, PaymentFrm
Methods: getTablesWithActiveOrder(), getActiveOrderByTable(), getOrderDetails(), getCouponByCode(), addInvoice(), updateOrderStatus()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subOrder`: nút Order (JButton)
- `subBookTable`: nút Book a table (JButton)
- `subPayment`: nút Payment (JButton)

**PaymentFrm:**
- `inTable`: combobox chọn bàn có đơn hàng chưa thanh toán (JComboBox<Table>)
- `outOrderInfo`: thông tin đơn hàng: bàn, nhân viên, khách hàng (JPanel/JLabel)
- `outsubListOrderDetail`: bảng chi tiết hóa đơn: STT, Tên món, Đơn giá, SL, Thành tiền (JTable)
- `outTotalAmount`: tổng tiền hiển thị (JLabel)
- `subAddCoupon`: nút Add coupon (JButton)
- `inCouponCode`: ô nhập mã giảm giá (JTextField) — hiện khi nhấn Add coupon
- `subApplyCoupon`: nút áp dụng mã (JButton) — hiện khi nhấn Add coupon
- `outCouponInfo`: thông tin mã giảm giá đã áp dụng (JLabel)
- `subConfirm`: nút Confirm thanh toán (JButton)

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, description: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Combo | Model | id: int, name: String, totalPrice: float |
| Order | Model | id: int, tableId: int, customerId: int, userId: int, orderDate: Date, totalAmount: float, status: String |
| OrderDetail | Model | id: int, orderId: int, dishId: int, comboId: int, quantity: int, unitPrice: float, amount: float |
| Coupon | Model | id: int, code: String, discount: float |
| Invoice | Model | id: int, orderId: int, couponId: int, totalAmount: float, paidAmount: float, invoiceDate: Date |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| TableDAO | `getTablesWithActiveOrder(): List<Table>` | Lấy danh sách bàn có đơn chưa thanh toán |
| OrderDAO | `getActiveOrderByTable(tableId): Order` | Lấy đơn hàng chưa thanh toán theo bàn |
| OrderDetailDAO | `getOrderDetails(orderId): List<OrderDetail>` | Lấy chi tiết đơn hàng |
| CouponDAO | `getCouponByCode(code): Coupon` | Tìm mã giảm giá theo mã |
| InvoiceDAO | `addInvoice(invoice): boolean` | Lưu hóa đơn |
| OrderDAO | `updateOrderStatus(orderId, status): boolean` | Cập nhật trạng thái đơn hàng |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblTable:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | varchar(100) | NOT NULL |
| maxGuests | int | NOT NULL |
| description | nvarchar(255) | |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| name | varchar(100) | NOT NULL |
| phone | varchar(15) | |
| email | varchar(100) | |
| address | nvarchar(255) | |

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

**tblCoupon:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| code | varchar(20) | NOT NULL, UNIQUE |
| discount | float | NOT NULL |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| orderID | int | FOREIGN KEY → tblOrder(ID) |
| couponID | int | FOREIGN KEY → tblCoupon(ID), NULLABLE |
| totalAmount | float | NOT NULL |
| paidAmount | float | NOT NULL |
| invoiceDate | datetime | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.payment`: chứa LoginFrm, HomeFrm, PaymentFrm.
2. Tạo package `model`: chứa Table, Customer, Dish, Combo, Order, OrderDetail, Coupon, Invoice, User.
3. Tạo package `dao`: chứa UserDAO, TableDAO, OrderDAO, OrderDetailDAO, CouponDAO, InvoiceDAO.
4. Vẽ association từ PaymentFrm → TableDAO, PaymentFrm → OrderDAO, PaymentFrm → OrderDetailDAO, PaymentFrm → CouponDAO, PaymentFrm → InvoiceDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dashed arrow).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `PaymentFrm`, `UserDAO`, `TableDAO`, `OrderDAO`, `OrderDetailDAO`, `CouponDAO`, `InvoiceDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh: có mã giảm giá / không có mã giảm giá.

### Sequence Diagram (ASCII)

```
Staff    LoginFrm  UserDAO  HomeFrm  PaymentFrm  TableDAO  OrderDAO  OrderDetailDAO  CouponDAO  InvoiceDAO
  |         |         |       |          |           |          |            |             |           |
  |--login->|         |       |          |           |          |            |             |           |
  |         |--checkLogin()-->|          |           |          |            |             |           |
  |         |<--User--|       |          |           |          |            |             |           |
  |         |--open HomeFrm-->|          |           |          |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |--select Payment-->|       |          |           |          |            |             |           |
  |         |         |       |--open PaymentFrm--->|          |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |         |         |       |          |--getTablesWithActiveOrder()----->|             |           |
  |         |         |       |          |           |--query DB|            |             |           |
  |         |         |       |          |<--List<Table>|       |            |             |           |
  |         |         |       |          |--populate combobox   |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |--select "B01"---->|       |          |           |          |            |             |           |
  |         |         |       |          |--getActiveOrderByTable(1)------->|             |           |
  |         |         |       |          |           |          |--query DB |             |           |
  |         |         |       |          |<--Order---|          |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |         |         |       |          |--getOrderDetails(orderId)------->|             |           |
  |         |         |       |          |           |          |    |--query DB          |           |
  |         |         |       |          |<--List<OrderDetail>--|            |             |           |
  |         |         |       |          |--display invoice     |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |--click Add coupon>|       |          |           |          |            |             |           |
  |--enter "GIAM10"-->|       |          |           |          |            |             |           |
  |         |         |       |          |--getCouponByCode("GIAM10")------>|             |           |
  |         |         |       |          |           |          |            |    |--query DB|
  |         |         |       |          |<--Coupon--|          |            |             |           |
  |         |         |       |          |--update total (210000 * 0.9 = 189000)          |           |
  |         |         |       |          |           |          |            |             |           |
  |--click Confirm--->|       |          |           |          |            |             |           |
  |         |         |       |          |--addInvoice(invoice)------------>|             |           |
  |         |         |       |          |           |          |            |       |--INSERT tblInvoice
  |         |         |       |          |<--true----|          |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |         |         |       |          |--updateOrderStatus(orderId, "paid")----------->|           |
  |         |         |       |          |           |          |--UPDATE DB|             |           |
  |         |         |       |          |<--true----|          |            |             |           |
  |         |         |       |          |           |          |            |             |           |
  |         |         |       |          |--print invoice + show "Thanh toan thanh cong"  |           |
  |<--success---------|       |          |           |          |            |             |           |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | actionPerformed("Login") | Nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra đăng nhập |
| 3 | UserDAO | LoginFrm | return User | Trả về đối tượng User |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Staff | HomeFrm | actionPerformed("Payment") | Chọn thanh toán |
| 6 | HomeFrm | PaymentFrm | new PaymentFrm().setVisible(true) | Mở form thanh toán |
| 7 | PaymentFrm | TableDAO | getTablesWithActiveOrder() | Lấy bàn có đơn chưa thanh toán |
| 8 | TableDAO | PaymentFrm | return List<Table> [B01, B03] | Trả về danh sách bàn |
| 9 | PaymentFrm | PaymentFrm | populateComboBox(tables) | Đổ dữ liệu vào combobox |
| 10 | Staff | PaymentFrm | selectTable("B01") | Chọn bàn B01 |
| 11 | PaymentFrm | OrderDAO | getActiveOrderByTable(1) | Lấy đơn hàng đang hoạt động |
| 12 | OrderDAO | PaymentFrm | return Order (orderId=1, total=210000) | Trả về đơn hàng |
| 13 | PaymentFrm | OrderDetailDAO | getOrderDetails(1) | Lấy chi tiết đơn hàng |
| 14 | OrderDetailDAO | PaymentFrm | return List<OrderDetail> (3 dòng) | Trả về chi tiết |
| 15 | PaymentFrm | PaymentFrm | displayInvoice(order, details) | Hiển thị hóa đơn |
| 16 | Staff | PaymentFrm | actionPerformed("AddCoupon") | Nhấn thêm mã giảm giá |
| 17 | Staff | PaymentFrm | setText(txtCouponCode, "GIAM10") | Nhập mã coupon |
| 18 | Staff | PaymentFrm | actionPerformed("ApplyCoupon") | Nhấn áp dụng |
| 19 | PaymentFrm | CouponDAO | getCouponByCode("GIAM10") | Tìm mã giảm giá |
| 20 | CouponDAO | PaymentFrm | return Coupon(discount=0.1) | Trả về coupon giảm 10% |
| 21 | PaymentFrm | PaymentFrm | updateTotal(210000 * 0.9 = 189000) | Cập nhật tổng tiền |
| 22 | PaymentFrm | PaymentFrm | displayCouponInfo("GIAM10", "10%") | Hiển thị thông tin coupon |
| 23 | Staff | PaymentFrm | actionPerformed("Confirm") | Nhấn Confirm |
| 24 | PaymentFrm | new Invoice() | Invoice(orderId=1, couponId=1, total=189000, paid=189000) | Tạo đối tượng Invoice |
| 25 | PaymentFrm | InvoiceDAO | addInvoice(invoice) | Lưu hóa đơn |
| 26 | InvoiceDAO | PaymentFrm | return true | Thành công |
| 27 | PaymentFrm | OrderDAO | updateOrderStatus(1, "paid") | Cập nhật trạng thái đơn |
| 28 | OrderDAO | PaymentFrm | return true | Thành công |
| 29 | PaymentFrm | PaymentFrm | printInvoice() | In hóa đơn |
| 30 | PaymentFrm | Staff | showMessage("Thanh toan thanh cong") | Thông báo |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Payment | Thanh toán có mã giảm giá thành công |
| TC02 | Payment | Thanh toán không có mã giảm giá |
| TC03 | Payment | Mã giảm giá không hợp lệ |
| TC04 | Payment | Bàn không có đơn hàng |
| TC05 | Payment | Thanh toán không sử dụng mã giảm giá |

### TC01: Thanh toán có mã giảm giá thành công

**Purpose:** Kiểm tra quy trình thanh toán hoàn chỉnh từ chọn bàn, xem hóa đơn, áp dụng mã giảm giá đến xác nhận thanh toán.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |
| 2 | manager01 | admin123 | manager |

**tblTable:**
| ID | code | name | maxGuests | description |
|----|------|------|-----------|-------------|
| 1 | B01 | Bàn VIP 1 | 6 | Tầng 1, gần cửa sổ |
| 2 | B02 | Bàn VIP 2 | 4 | Tầng 1, góc yên tĩnh |
| 3 | B03 | Bàn thường 1 | 4 | Tầng 2 |

**tblCustomer:**
| ID | code | name | phone | email | address |
|----|------|------|-------|-------|---------|
| 1 | KH01 | Nguyen Van A | 0912345678 | nva@gmail.com | Ha Noi |

**tblDish:**
| ID | code | type | name | description | price |
|----|------|------|------|-------------|-------|
| 1 | SP01 | Món chính | Phở bò | Phở bò tái chín | 50000 |
| 2 | SP03 | Món chính | Gà rán | Gà rán giòn | 65000 |
| 3 | SP05 | Đồ uống | Coca | Coca Cola 330ml | 15000 |

**tblCombo:**
| ID | name | totalPrice |
|----|------|------------|
| (0 dòng) | | |

**tblOrder:**
| ID | tableID | customerID | userID | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | 1 | 1 | 08/06/2026 10:30 | 210000 | active |
| 2 | 3 | NULL | 1 | 08/06/2026 11:00 | 80000 | active |

**tblOrderDetail:**
| ID | orderID | dishID | comboID | quantity | unitPrice | amount |
|----|---------|--------|---------|----------|-----------|--------|
| 1 | 1 | 1 | NULL | 2 | 50000 | 100000 |
| 2 | 1 | 3 | NULL | 3 | 15000 | 45000 |
| 3 | 1 | 2 | NULL | 1 | 65000 | 65000 |
| 4 | 3 | 1 | NULL | 1 | 50000 | 50000 |
| 5 | 3 | 3 | NULL | 2 | 15000 | 30000 |

**tblCoupon:**
| ID | code | discount |
|----|------|----------|
| 1 | GIAM10 | 0.1 |
| 2 | GIAM20 | 0.2 |

**tblInvoice:**
| ID | orderID | couponID | totalAmount | paidAmount | invoiceDate |
|----|---------|----------|-------------|------------|-------------|
| (0 dòng) | | | | | |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Giao diện Home hiển thị |
| 3 | Nhấn chọn **Payment** | Giao diện chọn bàn: combobox hiển thị B01 và B03 (có đơn hàng active) |
| 4 | Chọn bàn "B01" từ combobox | Hóa đơn chi tiết hiển thị: Bàn B01, NV staff01, KH Nguyen Van A. Bảng: Phở bò × 2 = 100,000đ; Coca × 3 = 45,000đ; Gà rán × 1 = 65,000đ. Tổng: 210,000đ |
| 5 | Nhấn **Add coupon** | Ô nhập mã giảm giá xuất hiện |
| 6 | Nhập "GIAM10", nhấn OK | Hệ thống kiểm tra mã hợp lệ. Hóa đơn cập nhật: thêm dòng "GIAM10 — Giảm 10%". Tổng mới: 210,000 × 0.9 = 189,000đ |
| 7 | **Kiểm tra DB trước:** tblInvoice có 0 dòng. tblOrder ID=1 có status = "active". | Dữ liệu đúng như Database trước khi test |
| 8 | Nhấn **Confirm** | Hệ thống lưu hóa đơn. Thông báo "Thanh toan thanh cong". Hóa đơn được in |
| 9 | **Kiểm tra DB sau:** tblInvoice có 1 dòng mới. tblOrder ID=1 có status = "paid". | Dữ liệu đúng như Database sau khi test |

### Database sau khi test

**tblOrder:** (cập nhật 1 dòng)
| ID | tableID | customerID | userID | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | 1 | 1 | 08/06/2026 10:30 | 210000 | paid |
| 2 | 3 | NULL | 1 | 08/06/2026 11:00 | 80000 | active |

**tblInvoice:** (thêm 1 dòng)
| ID | orderID | couponID | totalAmount | paidAmount | invoiceDate |
|----|---------|----------|-------------|------------|-------------|
| 1 | 1 | 1 | 189000 | 189000 | 08/06/2026 12:00 |
