# Subject No. 15 — Restaurant — Module "Order"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Gọi món

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Order, Book a table, Payment, Combo management, Statistics. |
| 4 | Staff chọn chức năng **Order**. |
| 5 | Giao diện chọn bàn xuất hiện: combobox (dropdown) danh sách bàn. |
| 6 | Staff mở dropdown, chọn bàn "B01 — Bàn VIP 1, tối đa 6 khách". |
| 7 | Giao diện gọi món xuất hiện: ô nhập tên món (txtDishName), nút Search (btnSearch), bảng kết quả tìm kiếm (tblDishList), ô nhập số lượng (txtQuantity), nút OK (btnOK), danh sách món đã chọn (tblOrderDetail), nút Confirm (btnConfirm). |
| 8 | Staff hỏi khách, nhập "Phở bò" vào ô tên món, nhấn Search. |
| 9 | Hệ thống hiển thị danh sách món khớp từ khóa: bảng gồm cột Mã món, Loại, Tên, Giá. Ví dụ: SP01 — Món chính — Phở bò — 50,000đ. |
| 10 | Staff chọn dòng "Phở bò" (SP01, 50,000đ), nhập số lượng = 2 vào ô Quantity, nhấn OK. |
| 11 | Hệ thống thêm vào danh sách bên dưới: Phở bò — SL: 2 — Đơn giá: 50,000đ — Thành tiền: 100,000đ. |
| 12 | Staff lặp: nhập "Coca" → Search → chọn Coca (SP05, 15,000đ) → SL 3 → OK. Danh sách cập nhật: Coca — SL 3 — 45,000đ. Tổng tạm tính: 145,000đ. |
| 13 | Staff lặp: nhập "Gà rán" → Search → chọn Gà rán (SP03, 65,000đ) → SL 1 → OK. Danh sách cập nhật: Gà rán — SL 1 — 65,000đ. Tổng tạm tính: 210,000đ. |
| 14 | Staff đọc lại toàn bộ danh sách món đã chọn cho khách xác nhận. |
| 15 | Staff nhấn **Confirm**. Hệ thống lưu đơn hàng vào database (tblOrder + tblOrderDetail). |
| 16 | Hệ thống thông báo "Goi mon thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập tên món không tồn tại → danh sách kết quả trống, yêu cầu nhập lại.
- **EL2:** Staff nhập số lượng <= 0 → hệ thống cảnh báo "So luong phai lon hon 0".
- **EL3:** Staff chưa chọn bàn mà đã tìm món → hệ thống yêu cầu chọn bàn trước.
- **EL4:** Staff nhấn Confirm khi chưa thêm món nào → hệ thống cảnh báo "Chua co mon nao".

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Nhà hàng quản lý việc gọi món cho khách. Nhà hàng có nhiều bàn ăn. Mỗi bàn có mã, tên, số khách tối đa và mô tả. Bàn có thể được gộp lại khi có nhóm khách lớn. Khách hàng có thể đặt bàn trước. Nhà hàng có danh sách món ăn, mỗi món có mã, loại, tên, mô tả và giá hiện tại. Nhà hàng cũng tạo các combo kết hợp nhiều món cho một người ăn. Khi khách đến, nhân viên chọn bàn, gọi món (hoặc combo) với số lượng cụ thể. Mỗi đơn hàng gồm nhiều chi tiết (món hoặc combo). Khi thanh toán, hệ thống tạo hóa đơn có thể áp dụng mã giảm giá.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Restaurant | Entity class | | Hệ thống quản lý trong phạm vi nhà hàng |
| Table | Entity class | | Đối tượng bàn ăn, có thuộc tính riêng |
| code | Attribute (Table) | String | Mã bàn |
| name | Attribute (Table) | String | Tên bàn |
| max guests | Attribute (Table) | int | Số khách tối đa |
| description | Attribute (Table) | String | Mô tả bàn |
| Customer | Entity class | | Khách hàng đặt bàn và gọi món |
| code | Attribute (Customer) | String | Mã khách hàng |
| name | Attribute (Customer) | String | Tên khách hàng |
| phone | Attribute (Customer) | String | Số điện thoại |
| email | Attribute (Customer) | String | Email |
| address | Attribute (Customer) | String | Địa chỉ |
| Dish | Entity class | | Món ăn trong thực đơn |
| code | Attribute (Dish) | String | Mã món |
| type | Attribute (Dish) | String | Loại món (khai vị, chính, tráng miệng) |
| name | Attribute (Dish) | String | Tên món |
| description | Attribute (Dish) | String | Mô tả món |
| price | Attribute (Dish) | float | Giá hiện tại |
| Combo | Entity class | | Combo kết hợp nhiều món |
| ComboDetail | Entity class | | Chi tiết món trong combo |
| Order | Entity class | | Đơn hàng của khách tại bàn |
| OrderDetail | Entity class | | Chi tiết từng món/combo trong đơn |
| User | Entity class | | Nhân viên thao tác trên hệ thống |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|    Restaurant    |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -name: String    |       | -username: String|
| -address: String |       | -password: String|
| -description: String|    | -role: String    |
+------------------+       +------------------+
         | 1                        | 1
         |                          |
         | n                        | n
         v                          v
+------------------+       +------------------+
|     Table        |       |     Order        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -orderDate: Date |
| -name: String    |       | -totalAmount: float|
| -maxGuests: int  |       | -status: String  |
| -description: String|    +------------------+
+------------------+       | 1        | 1
                           |          |
                           | n        | n
                           v          v
+------------------+       +------------------+
|    Customer      |       |  OrderDetail     |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -quantity: int   |
| -name: String    |       | -unitPrice: float|
| -phone: String   |       | -amount: float   |
| -email: String   |       +------------------+
| -address: String |              | n
+------------------+              |
                                  | 1
+------------------+       +------+---------+
|      Dish        |       |     Combo      |
+------------------+       +----------------+
| -id: int         |       | -id: int       |
| -code: String    |       | -name: String  |
| -type: String    |       | -totalPrice: float|
| -name: String    |       +----------------+
| -description: String|    | 1
| -price: float    |       | n
+------------------+       v
         | 1        +------------------+
         |          |  ComboDetail     |
         | n        +------------------+
         +--------->| -id: int         |
                    | -quantity: int   |
                    +------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Restaurant → Table | 1-n | Một nhà hàng có nhiều bàn |
| Table → Order | 1-n | Một bàn có nhiều đơn hàng (khác thời điểm) |
| Customer → Order | 1-n | Một khách hàng có nhiều đơn hàng |
| User → Order | 1-n | Một nhân viên tạo nhiều đơn hàng |
| Order → OrderDetail | 1-n | Một đơn hàng có nhiều chi tiết |
| Dish → OrderDetail | 1-n | Một món được đặt trong nhiều chi tiết đơn |
| Combo → OrderDetail | 1-n | Một combo được đặt trong nhiều chi tiết đơn |
| Combo → ComboDetail | 1-n | Một combo chứa nhiều chi tiết món |
| Dish → ComboDetail | 1-n | Một món xuất hiện trong nhiều combo |

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subOrder`: nút chọn chức năng Order (JButton)
- `subBookTable`: nút chọn chức năng Book a table (JButton)
- `subPayment`: nút chọn chức năng Payment (JButton)
- `subCombo`: nút chọn Combo management (JButton)

**OrderFrm:**
- `inTable`: combobox chọn bàn (JComboBox<Table>)
- `inDishName`: ô nhập tên món (JTextField)
- `subSearch`: nút Search (JButton)
- `outsubListDish`: bảng danh sách món tìm được, click để chọn (JTable)
- `inQuantity`: ô nhập số lượng (JTextField)
- `subOK`: nút OK thêm món vào danh sách (JButton)
- `outListOrderDetail`: danh sách món đã chọn hiển thị bên dưới (JTable)
- `subConfirm`: nút Confirm lưu đơn hàng (JButton)

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, description: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Combo | Model | id: int, name: String, totalPrice: float |
| ComboDetail | Model | id: int, comboId: int, dishId: int, quantity: int |
| Order | Model | id: int, tableId: int, customerId: int, userId: int, orderDate: Date, totalAmount: float, status: String |
| OrderDetail | Model | id: int, orderId: int, dishId: int, comboId: int, quantity: int, unitPrice: float, amount: float |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| TableDAO | `getAllTables(): List<Table>` | Lấy danh sách bàn cho combobox |
| DishDAO | `searchDishByName(name): List<Dish>` | Tìm món theo tên |
| OrderDAO | `addOrder(order): int` | Lưu đơn hàng, trả về orderId |
| OrderDetailDAO | `addOrderDetail(detail): boolean` | Lưu chi tiết đơn hàng |

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

**tblComboDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| comboID | int | FOREIGN KEY → tblCombo(ID) |
| dishID | int | FOREIGN KEY → tblDish(ID) |
| quantity | int | NOT NULL |

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

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.order`: chứa LoginFrm, HomeFrm, OrderFrm.
2. Tạo package `model`: chứa Table, Customer, Dish, Combo, ComboDetail, Order, OrderDetail, User.
3. Tạo package `dao`: chứa UserDAO, TableDAO, DishDAO, OrderDAO, OrderDetailDAO.
4. Vẽ association từ OrderFrm → TableDAO, OrderFrm → DishDAO, OrderFrm → OrderDAO, OrderFrm → OrderDetailDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dùng mũi tên dashed).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `OrderFrm`, `UserDAO`, `TableDAO`, `DishDAO`, `OrderDAO`, `OrderDetailDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh ngoại lệ (món không tìm thấy).
4. Sử dụng `loop` fragment cho việc lặp chọn món.

### Sequence Diagram (ASCII)

```
Staff      LoginFrm    UserDAO   HomeFrm    OrderFrm    TableDAO   DishDAO   OrderDAO  OrderDetailDAO
  |            |           |         |           |           |          |          |           |
  |--login---->|           |         |           |           |          |          |           |
  |            |--checkLogin()----->|           |           |          |          |           |
  |            |<--true----|         |           |           |          |          |           |
  |            |--open HomeFrm----->|           |           |          |          |           |
  |            |           |         |           |           |          |          |           |
  |--select Order--------->|         |           |           |          |          |           |
  |            |           |         |--open OrderFrm------->|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--getAllTables()----->|          |           |
  |            |           |         |           |<--List<Table>|       |          |           |
  |            |           |         |           |--populate dropdown   |          |           |
  |            |           |         |           |           |          |          |           |
  |--select "B01"--------->|         |           |           |          |          |           |
  |            |           |         |           |           |          |          |           |
  |--enter "Phở bò", Search-------->|           |           |          |          |           |
  |            |           |         |           |--searchDishByName("Phở bò")-->|           |
  |            |           |         |           |<--List<Dish>|        |          |           |
  |            |           |         |           |--display list         |          |           |
  |            |           |         |           |           |          |          |           |
  |--select dish, qty 2, OK--------->          |           |          |          |           |
  |            |           |         |           |--add to tblOrderDetail          |           |
  |            |           |         |           |           |          |          |           |
  |  (loop: repeat for "Coca" SL3, "Gà rán" SL1)           |          |          |           |
  |            |           |         |           |           |          |          |           |
  |--click Confirm-------->|         |           |           |          |          |           |
  |            |           |         |           |--addOrder(order)-------------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrder
  |            |           |         |           |<--orderId--|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--addOrderDetail(detail)-------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrderDetail (x3)
  |            |           |         |           |<--true-----|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--show "Goi mon thanh cong"      |           |
  |<--success--|           |         |           |           |          |          |           |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | actionPerformed("Login") | Staff nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra thông tin đăng nhập |
| 3 | UserDAO | LoginFrm | return true | Đăng nhập thành công |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Staff | HomeFrm | actionPerformed("Order") | Chọn chức năng Order |
| 6 | HomeFrm | OrderFrm | new OrderFrm().setVisible(true) | Mở giao diện gọi món |
| 7 | OrderFrm | TableDAO | getAllTables() | Lấy danh sách bàn |
| 8 | TableDAO | OrderFrm | return List<Table> | Trả về danh sách bàn |
| 9 | OrderFrm | OrderFrm | populateComboBox(tables) | Đổ dữ liệu vào dropdown |
| 10 | Staff | OrderFrm | selectTable("B01") | Chọn bàn B01 |
| 11 | Staff | OrderFrm | setText("Phở bò") | Nhập tên món |
| 12 | Staff | OrderFrm | actionPerformed("Search") | Nhấn nút Search |
| 13 | OrderFrm | DishDAO | searchDishByName("Phở bò") | Tìm món theo tên |
| 14 | DishDAO | OrderFrm | return List<Dish> | Trả về danh sách món |
| 15 | OrderFrm | OrderFrm | displayTable(listDish) | Hiển thị bảng kết quả |
| 16 | Staff | OrderFrm | selectRow(0) + setText("2") + actionPerformed("OK") | Chọn món, nhập SL, nhấn OK |
| 17 | OrderFrm | OrderFrm | addToOrderDetail("Phở bò", 2, 50000) | Thêm vào danh sách đã chọn |
| 18 | Staff | OrderFrm | (lặp) search "Coca" → select → qty 3 → OK | Lặp cho Coca |
| 19 | Staff | OrderFrm | (lặp) search "Gà rán" → select → qty 1 → OK | Lặp cho Gà rán |
| 20 | Staff | OrderFrm | actionPerformed("Confirm") | Nhấn Confirm |
| 21 | OrderFrm | new Order() | Order(tableId=1, userId=1, orderDate=now, totalAmount=210000) | Tạo đối tượng Order |
| 22 | OrderFrm | OrderDAO | addOrder(order) | Lưu đơn hàng vào DB |
| 23 | OrderDAO | OrderFrm | return orderId = 1 | Trả về ID đơn hàng |
| 24 | OrderFrm | OrderDetailDAO | addOrderDetail(detail1) | Lưu chi tiết: Phở bò × 2 |
| 25 | OrderDetailDAO | OrderFrm | return true | Thành công |
| 26 | OrderFrm | OrderDetailDAO | addOrderDetail(detail2) | Lưu chi tiết: Coca × 3 |
| 27 | OrderDetailDAO | OrderFrm | return true | Thành công |
| 28 | OrderFrm | OrderDetailDAO | addOrderDetail(detail3) | Lưu chi tiết: Gà rán × 1 |
| 29 | OrderDetailDAO | OrderFrm | return true | Thành công |
| 30 | OrderFrm | Staff | showMessage("Goi mon thanh cong") | Thông báo thành công |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Order | Gọi món thành công (2 món) |
| TC02 | Order | Nhập tên món không tồn tại |
| TC03 | Order | Nhập số lượng <= 0 |
| TC04 | Order | Chưa chọn bàn đã tìm món |
| TC05 | Order | Confirm khi chưa có món nào |

### TC01: Gọi món thành công

**Purpose:** Kiểm tra quy trình gọi món hoàn chỉnh từ chọn bàn, tìm món, nhập số lượng đến xác nhận lưu đơn hàng.

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

**tblDish:**
| ID | code | type | name | description | price |
|----|------|------|------|-------------|-------|
| 1 | SP01 | Món chính | Phở bò | Phở bò tái chín | 50000 |
| 2 | SP02 | Món chính | Phở gà | Phở gà truyền thống | 45000 |
| 3 | SP03 | Món chính | Gà rán | Gà rán giòn | 65000 |
| 4 | SP04 | Tráng miệng | Kem vani | Kem vani Ý | 30000 |
| 5 | SP05 | Đồ uống | Coca | Coca Cola 330ml | 15000 |

**tblCombo:** (rỗng)

**tblCustomer:**
| ID | code | name | phone | email | address |
|----|------|------|-------|-------|---------|
| 1 | KH01 | Nguyễn Văn A | 0912345678 | nva@gmail.com | Hà Nội |

**tblOrder:** (rỗng)

**tblOrderDetail:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username = `staff01`, password = `123456`, nhấn Login | Giao diện Home hiển thị với các chức năng: Order, Book a table, Payment |
| 3 | Nhấn chọn **Order** | Giao diện Order hiển thị: combobox danh sách bàn (B01, B02, B03), ô nhập tên món, nút Search |
| 4 | Chọn bàn "B01 — Bàn VIP 1" từ dropdown | Bàn B01 được chọn. Ô tìm kiếm món sẵn sàng nhập |
| 5 | Nhập "Phở bò" vào ô tên món, nhấn Search | Bảng kết quả hiển thị 1 dòng: SP01 — Món chính — Phở bò — 50,000đ |
| 6 | Chọn dòng "Phở bò", nhập số lượng = 2, nhấn OK | Danh sách bên dưới cập nhật: Phở bò — SL: 2 — Đơn giá: 50,000đ — Thành tiền: 100,000đ |
| 7 | Nhập "Coca" vào ô tên món, nhấn Search | Bảng kết quả hiển thị: SP05 — Đồ uống — Coca — 15,000đ |
| 8 | Chọn dòng "Coca", nhập số lượng = 3, nhấn OK | Danh sách cập nhật thêm: Coca — SL: 3 — Đơn giá: 15,000đ — Thành tiền: 45,000đ. Tổng tạm tính: 145,000đ |
| 9 | Nhập "Gà rán" vào ô tên món, nhấn Search | Bảng kết quả hiển thị: SP03 — Món chính — Gà rán — 65,000đ |
| 10 | Chọn dòng "Gà rán", nhập số lượng = 1, nhấn OK | Danh sách cập nhật thêm: Gà rán — SL: 1 — Đơn giá: 65,000đ — Thành tiền: 65,000đ. Tổng tạm tính: 210,000đ |
| 11 | Nhấn **Confirm** | Hệ thống hiển thị thông báo "Goi mon thanh cong" |

### Database sau khi test

**tblOrder:** (thêm 1 dòng)
| ID | tableID | customerID | userID | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | NULL | 1 | 08/06/2026 10:30 | 210000 | active |

**tblOrderDetail:** (thêm 3 dòng)
| ID | orderID | dishID | comboID | quantity | unitPrice | amount |
|----|---------|--------|---------|----------|-----------|--------|
| 1 | 1 | 1 | NULL | 2 | 50000 | 100000 |
| 2 | 1 | 5 | NULL | 3 | 15000 | 45000 |
| 3 | 1 | 3 | NULL | 1 | 65000 | 65000 |
