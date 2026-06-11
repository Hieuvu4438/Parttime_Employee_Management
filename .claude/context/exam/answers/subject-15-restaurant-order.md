# Subject No. 15 — Restaurant — Module "Order"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Gọi món

### Kịch bản chính

| Bước | Diễn biến | Giao diện hiển thị |
|------|-----------|---------------------|
| 1 | Staff mở ứng dụng. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. | **LoginFrm** |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. | **LoginFrm** |
| 3 | Hệ thống xác thực thành công. Giao diện Home xuất hiện với các chức năng: Order, Book a table, Payment, Combo management, Statistics. | **HomeFrm** |
| 4 | Staff chọn chức năng **Order**. | **HomeFrm** |
| 5 | Giao diện chọn bàn **OrderFrm** xuất hiện: combobox (dropdown) danh sách bàn, ô nhập tên món, nút Search, bảng kết quả, ô số lượng, nút OK, danh sách món đã chọn, nút Confirm. | **OrderFrm** |
| 6 | Staff mở dropdown, chọn bàn "B01 — Bàn VIP 1, tối đa 6 khách". | **OrderFrm** |
| 7 | Staff hỏi khách, nhập "Phở bò" vào ô tên món, nhấn Search. | **OrderFrm** |
| 8 | Hệ thống hiển thị danh sách món khớp từ khóa: bảng gồm cột Mã món, Loại, Tên, Giá. Ví dụ: SP01 — Món chính — Phở bò — 50,000đ. | **OrderFrm** — bảng kết quả |
| 9 | Staff chọn dòng "Phở bò" (SP01, 50,000đ), nhập số lượng = 2 vào ô Quantity, nhấn OK. | **OrderFrm** |
| 10 | Hệ thống thêm vào danh sách bên dưới: Phở bò — SL: 2 — Đơn giá: 50,000đ — Thành tiền: 100,000đ. | **OrderFrm** — danh sách đã chọn |
| 11 | Staff lặp: nhập "Coca" → Search → chọn Coca (SP05, 15,000đ) → SL 3 → OK. Danh sách cập nhật: Coca — SL 3 — 45,000đ. Tổng tạm tính: 145,000đ. | **OrderFrm** |
| 12 | Staff lặp: nhập "Gà rán" → Search → chọn Gà rán (SP03, 65,000đ) → SL 1 → OK. Danh sách cập nhật: Gà rán — SL 1 — 65,000đ. Tổng tạm tính: 210,000đ. | **OrderFrm** |
| 13 | Staff đọc lại toàn bộ danh sách món đã chọn cho khách xác nhận. | **OrderFrm** |
| 14 | Staff nhấn **Confirm**. Hệ thống lưu đơn hàng vào database (tblOrder + tblOrderDetail). | **OrderFrm** |
| 15 | Hệ thống thông báo "Goi mon thanh cong". Quay về giao diện Home. | **HomeFrm** |

### Kịch bản ngoại lệ

| Mã | Tình huống | Diễn biến |
|----|-----------|-----------|
| EL1 | Staff nhập tên món không tồn tại | Danh sách kết quả trống, yêu cầu nhập lại. |
| EL2 | Staff nhập số lượng <= 0 | Hệ thống cảnh báo "So luong phai lon hon 0". |
| EL3 | Staff chưa chọn bàn mà đã tìm món | Hệ thống yêu cầu chọn bàn trước. |
| EL4 | Staff nhấn Confirm khi chưa thêm món nào | Hệ thống cảnh báo "Chua co mon nao". |

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Nhà hàng quản lý việc gọi món cho khách. Nhà hàng có nhiều bàn ăn, mỗi bàn có mã, tên, số khách tối đa và mô tả. Nhiều bàn nhỏ có thể gộp thành bàn lớn khi có nhóm khách lớn. Khách hàng có thể đặt bàn trước. Nhà hàng có danh sách món ăn (mã, loại, tên, mô tả, giá hiện tại) và các combo kết hợp nhiều món cho một người ăn. Khi khách đến, nhân viên chọn bàn, gọi món hoặc combo với số lượng cụ thể. Mỗi đơn hàng gồm nhiều chi tiết (món hoặc combo). Khi thanh toán, hệ thống tạo hóa đơn chứa thông tin bàn, nhân viên, khách hàng, danh sách món đã gọi (tên, đơn giá, số lượng, thành tiền) và tổng tiền. Khách hàng có thể áp dụng mã giảm giá khi thanh toán.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Table (Bàn ăn) | Entity class | Đối tượng bàn ăn, có mã, tên, số khách tối đa, mô tả |
| Customer (Khách hàng) | Entity class | Khách hàng đặt bàn và gọi món, có mã, tên, SDT, email, địa chỉ |
| Dish (Món ăn) | Entity class | Món ăn trong thực đơn, có mã, loại, tên, mô tả, giá |
| Combo (Combo) | Entity class | Combo kết hợp nhiều món, có tên, tổng giá |
| ComboDetail (Chi tiết combo) | Entity class | Chi tiết món trong combo, có số lượng |
| Order (Đơn hàng) | Entity class | Đơn hàng của khách tại bàn, có ngày, tổng tiền, trạng thái |
| OrderDetail (Chi tiết đơn) | Entity class | Chi tiết từng món/combo trong đơn, có số lượng, đơn giá, thành tiền |
| Invoice (Hóa đơn) | Entity class | Hóa đơn thanh toán, chứa thông tin bàn, nhân viên, khách, tổng tiền |
| Coupon (Mã giảm giá) | Entity class | Mã giảm giá áp dụng khi thanh toán |
| User (Nhân viên) | Entity class | Nhân viên thao tác trên hệ thống |
| code, name, max guests, description | Thuộc tính | Thuộc tính của Table |
| code, name, phone, email, address | Thuộc tính | Thuộc tính của Customer |
| code, type, name, description, price | Thuộc tính | Thuộc tính của Dish |
| name, totalPrice | Thuộc tính | Thuộc tính của Combo |
| quantity | Thuộc tính | Thuộc tính của ComboDetail và OrderDetail |
| orderDate, totalAmount, status | Thuộc tính | Thuộc tính của Order |
| unitPrice, amount | Thuộc tính | Thuộc tính của OrderDetail |
| invoiceDate, totalAmount | Thuộc tính | Thuộc tính của Invoice |
| code, discountPercent, validUntil | Thuộc tính | Thuộc tính của Coupon |
| merge (gộp bàn) | rejected | Hành động, không phải entity |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|      User        |       |    Customer      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -username: String|       | -code: String    |
| -password: String|       | -name: String    |
| -role: String    |       | -phone: String   |
+------------------+       | -email: String   |
         | 1               | -address: String |
         |                 +------------------+
         | n                       | 1
         v                         |
+------------------+               | n
|     Order        |<--------------+
+------------------+
| -id: int         |       +------------------+
| -orderDate: Date |       |     Table        |
| -totalAmount: float|     +------------------+
| -status: String  |  n  1 | -id: int         |
+------------------+<------| -code: String    |
         | 1               | -name: String    |
         |                 | -maxGuests: int  |
         | n               | -description: String|
         v                 +------------------+
+------------------+
|  OrderDetail     |       +------------------+
+------------------+       |      Dish        |
| -id: int         |       +------------------+
| -quantity: int   |  n  1 | -id: int         |
| -unitPrice: float|<------| -code: String    |
| -amount: float   |       | -type: String    |
+------------------+       | -name: String    |
                           | -description: String|
                           | -price: float    |
                           +------------------+
                                    | 1
                                    |
                                    | n
+------------------+       +--------+---------+
|     Combo        |       |   ComboDetail    |
+------------------+       +------------------+
| -id: int         |  1  n | -id: int         |
| -name: String    |-------| -quantity: int   |
| -totalPrice: float|      +------------------+
+------------------+

+------------------+       +------------------+
|     Invoice      |       |     Coupon       |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -invoiceDate: Date|      | -code: String    |
| -totalAmount: float|     | -discountPercent: float|
| -orderId: int    |       | -validUntil: Date|
| -customerId: int |       +------------------+
| -userId: int     |
| -couponId: int   |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Table → Order | 1-n (Association) | Một bàn có nhiều đơn hàng (khác thời điểm) |
| Customer → Order | 1-n (Association) | Một khách hàng có nhiều đơn hàng |
| User → Order | 1-n (Association) | Một nhân viên tạo nhiều đơn hàng |
| Order → OrderDetail | 1-n (Composition) | Một đơn hàng có nhiều chi tiết; chi tiết không tồn tại nếu không có đơn hàng |
| Dish → OrderDetail | 1-n (Association) | Một món được đặt trong nhiều chi tiết đơn |
| Combo → OrderDetail | 1-n (Association) | Một combo được đặt trong nhiều chi tiết đơn |
| Combo → ComboDetail | 1-n (Composition) | Một combo chứa nhiều chi tiết món; chi tiết không tồn tại nếu không có combo |
| Dish → ComboDetail | 1-n (Association) | Một món xuất hiện trong nhiều combo |
| Order → Invoice | 1-1 (Association) | Mỗi đơn hàng có một hóa đơn thanh toán |
| Customer → Invoice | 1-n (Association) | Một khách hàng có nhiều hóa đơn |
| User → Invoice | 1-n (Association) | Một nhân viên tạo nhiều hóa đơn |
| Coupon → Invoice | 1-n (Association) | Một mã giảm giá được áp dụng trong nhiều hóa đơn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Restaurant_Order" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 10 class: Table, Customer, Dish, Combo, ComboDetail, Order, OrderDetail, Invoice, Coupon, User |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, OrderFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Order) |
|------|----------|----------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Order` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-id: int`, `-orderDate: Date`, `-totalAmount: float`, `-status: String` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+addOrder(): int` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| Table | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-maxGuests: int`, `-description: String` | — |
| Customer | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-phone: String`, `-email: String`, `-address: String` | — |
| Dish | `<<entity>>` | `-id: int`, `-code: String`, `-type: String`, `-name: String`, `-description: String`, `-price: float` | — |
| Combo | `<<entity>>` | `-id: int`, `-name: String`, `-totalPrice: float` | — |
| ComboDetail | `<<entity>>` | `-id: int`, `-quantity: int`, `-dishId: int`, `-comboId: int` | — |
| Order | `<<entity>>` | `-id: int`, `-orderDate: Date`, `-totalAmount: float`, `-status: String`, `-table: Table`, `-customer: Customer`, `-user: User`, `-orderDetails: List<OrderDetail>` | — |
| OrderDetail | `<<entity>>` | `-id: int`, `-quantity: int`, `-unitPrice: float`, `-amount: float`, `-dish: Dish`, `-combo: Combo` | — |
| Invoice | `<<entity>>` | `-id: int`, `-invoiceDate: Date`, `-totalAmount: float`, `-order: Order`, `-customer: Customer`, `-user: User`, `-coupon: Coupon` | — |
| Coupon | `<<entity>>` | `-id: int`, `-code: String`, `-discountPercent: float`, `-validUntil: Date` | — |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-subOrder: JButton`, `-subBookTable: JButton`, `-subPayment: JButton`, `-subCombo: JButton`, `-subStatistics: JButton` |
| OrderFrm | `<<boundary>>` | `-inTable: JComboBox`, `-inDishName: JTextField`, `-subSearch: JButton`, `-outsubListDish: JTable`, `-inQuantity: JTextField`, `-subOK: JButton`, `-outListOrderDetail: JTable`, `-subConfirm: JButton` |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inTable, inDishName, inQuantity
- Tiền tố `out` → output (vùng hiển thị): outListOrderDetail
- Tiền tố `outsub` → clickable output (bảng click được): outsubListDish
- Tiền tố `sub` → submit (nút bấm): subSearch, subOK, subConfirm

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Customer → Order) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent (Order → OrderDetail) |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (OrderFrm → DishDAO) |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trong VP | Ví dụ |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" ở một đầu | Order có đúng 1 Invoice |
| 0..* hoặc 1..* | Ghi "*" hoặc "1..*" ở đầu kia | Customer có nhiều Order |
| 0..1 | Ghi "0..1" | customerID trong Order (nullable) |

Ghi multiplicity ở cả 2 đầu của đường kết nối. Click vào đường → Properties → Source Multiplicity / Target Multiplicity.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| Table | Order | Association | 1 → * | Mỗi bàn có nhiều đơn hàng |
| Customer | Order | Association | 1 → * | Mỗi khách hàng có nhiều đơn hàng |
| User | Order | Association | 1 → * | Mỗi nhân viên tạo nhiều đơn hàng |
| Order | OrderDetail | Composition | 1 → * | Mỗi đơn hàng chứa nhiều chi tiết |
| Dish | OrderDetail | Association | 1 → * | Mỗi món ăn xuất hiện trong nhiều chi tiết đơn |
| Combo | OrderDetail | Association | 1 → * | Mỗi combo xuất hiện trong nhiều chi tiết đơn |
| Combo | ComboDetail | Composition | 1 → * | Mỗi combo chứa nhiều chi tiết món |
| Dish | ComboDetail | Association | 1 → * | Mỗi món ăn xuất hiện trong nhiều combo |
| Order | Invoice | Association | 1 → 1 | Mỗi đơn hàng có một hóa đơn |
| Customer | Invoice | Association | 1 → * | Mỗi khách hàng có nhiều hóa đơn |
| Coupon | Invoice | Association | 1 → * | Mỗi mã giảm giá áp dụng trong nhiều hóa đơn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

*Ví dụ 1: Vẽ quan hệ Order → OrderDetail (1-n, Composition)*

1. Tạo class `<<entity>> Order` và `<<entity>> OrderDetail` với các thuộc tính tương ứng.
2. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
3. Click vào class Order → kéo đến class OrderDetail.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Kết quả: Order (1) ◆----(*) OrderDetail.

*Ví dụ 2: Vẽ quan hệ Dish → OrderDetail (1-n, Association)*

1. Tạo class `<<entity>> Dish` và `<<entity>> OrderDetail`.
2. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
3. Click vào class Dish → kéo đến class OrderDetail.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Đặt tên association: "ordered as" (tùy chọn).
6. Kết quả: Dish (1) ▷----(*) OrderDetail.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  một tùy chọn gọi món -> subOrder

Chọn Order -> OrderFrm xuất hiện:
  combobox chọn bàn -> inTable
  ô nhập tên món -> inDishName
  nút tìm kiếm -> subSearch
  bảng kết quả tìm kiếm (clickable) -> outsubListDish
  ô nhập số lượng -> inQuantity
  nút OK thêm món -> subOK
  danh sách món đã chọn -> outListOrderDetail
  nút xác nhận -> subConfirm

Khi mở form Order -> hệ thống lấy danh sách bàn -> cần phương thức:
  tên: getAllTables()
  đầu vào: (không có)
  đầu ra: list of Table
  gán cho DAO class: TableDAO.

Chọn bàn và nhập tên món, nhấn Search -> hệ thống tìm món theo tên -> cần phương thức:
  tên: searchDishByName()
  đầu vào: name (String)
  đầu ra: list of Dish
  gán cho DAO class: DishDAO.

Chọn món, nhập số lượng, nhấn OK -> thêm vào danh sách đã chọn (không cần gọi DAO).

Nhấn Confirm -> hệ thống lưu đơn hàng -> cần phương thức:
  tên: addOrder()
  đầu vào: order (Order)
  đầu ra: int (orderId)
  gán cho DAO class: OrderDAO.

Nhấn Confirm -> hệ thống lưu chi tiết đơn hàng -> cần phương thức:
  tên: addOrderDetail()
  đầu vào: detail (OrderDetail)
  đầu ra: boolean
  gán cho DAO class: OrderDetailDAO.

### Summary
View classes: HomeFrm, OrderFrm
DAO methods: getAllTables(), searchDishByName(), addOrder(), addOrderDetail()

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
- `subStatistics`: nút chọn Statistics (JButton)

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

**Table:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã bàn |
| name | String | Tên bàn |
| maxGuests | int | Số khách tối đa |
| description | String | Mô tả bàn |

**Customer:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã khách hàng |
| name | String | Tên khách hàng |
| phone | String | Số điện thoại |
| email | String | Email |
| address | String | Địa chỉ |

**Dish:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã món |
| type | String | Loại món (khai vị, chính, tráng miệng) |
| name | String | Tên món |
| description | String | Mô tả món |
| price | float | Giá hiện tại |

**Combo:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| name | String | Tên combo |
| totalPrice | float | Tổng giá combo |
| comboDetails | List<ComboDetail> | Danh sách món trong combo |

**ComboDetail:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| quantity | int | Số lượng món |
| dish | Dish | Đối tượng món ăn (FK) |
| combo | Combo | Đối tượng combo (FK) |

**Order:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| orderDate | Date | Ngày đặt hàng |
| totalAmount | float | Tổng tiền |
| status | String | Trạng thái |
| table | Table | Đối tượng bàn (FK) |
| customer | Customer | Đối tượng khách hàng (FK, nullable) |
| user | User | Đối tượng nhân viên (FK) |
| orderDetails | List<OrderDetail> | Danh sách chi tiết đơn |

**OrderDetail:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| quantity | int | Số lượng |
| unitPrice | float | Đơn giá |
| amount | float | Thành tiền |
| dish | Dish | Đối tượng món ăn (FK, nullable nếu là combo) |
| combo | Combo | Đối tượng combo (FK, nullable nếu là món) |

**Invoice:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| invoiceDate | Date | Ngày thanh toán |
| totalAmount | float | Tổng tiền thanh toán |
| order | Order | Đối tượng đơn hàng (FK) |
| customer | Customer | Đối tượng khách hàng (FK, nullable) |
| user | User | Đối tượng nhân viên (FK) |
| coupon | Coupon | Đối tượng mã giảm giá (FK, nullable) |

**Coupon:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã giảm giá |
| discountPercent | float | Phần trăm giảm giá |
| validUntil | Date | Ngày hết hạn |

**User:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| username | String | Tên đăng nhập |
| password | String | Mật khẩu |
| role | String | Vai trò |

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| UserDAO | `checkLogin(username: String, password: String): User` | `User` hoặc `null` | Kiểm tra đăng nhập |
| TableDAO | `getAllTables(): List<Table>` | `List<Table>` | Lấy danh sách bàn cho combobox |
| DishDAO | `searchDishByName(name: String): List<Dish>` | `List<Dish>` | Tìm món theo tên |
| OrderDAO | `addOrder(order: Order): int` | `int` (orderId) | Lưu đơn hàng, trả về orderId |
| OrderDetailDAO | `addOrderDetail(detail: OrderDetail): boolean` | `boolean` | Lưu chi tiết đơn hàng |
| InvoiceDAO | `createInvoice(invoice: Invoice): boolean` | `boolean` | Tạo hóa đơn thanh toán |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| username | varchar(50) | UNIQUE, NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblTable:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| maxGuests | int | NOT NULL |
| description | nvarchar(255) | |

**tblCustomer:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| phone | varchar(15) | |
| email | varchar(100) | |
| address | nvarchar(255) | |

**tblDish:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| type | varchar(50) | NOT NULL |
| name | varchar(100) | NOT NULL |
| description | nvarchar(255) | |
| price | float | NOT NULL |

**tblCombo:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| name | varchar(100) | NOT NULL |
| totalPrice | float | NOT NULL |

**tblComboDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| comboID | int | FK → tblCombo(ID), NOT NULL |
| dishID | int | FK → tblDish(ID), NOT NULL |
| quantity | int | NOT NULL |

**tblOrder:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| tableID | int | FK → tblTable(ID), NOT NULL |
| customerID | int | FK → tblCustomer(ID), NULLABLE |
| userID | int | FK → tblUser(ID), NOT NULL |
| orderDate | datetime | NOT NULL |
| totalAmount | float | NOT NULL |
| status | varchar(20) | NOT NULL |

**tblOrderDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| orderID | int | FK → tblOrder(ID), NOT NULL |
| dishID | int | FK → tblDish(ID), NULLABLE |
| comboID | int | FK → tblCombo(ID), NULLABLE |
| quantity | int | NOT NULL |
| unitPrice | float | NOT NULL |
| amount | float | NOT NULL |

**tblCoupon:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| discountPercent | float | NOT NULL |
| validUntil | date | NOT NULL |

**tblInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| invoiceDate | datetime | NOT NULL |
| totalAmount | float | NOT NULL |
| orderID | int | FK → tblOrder(ID), NOT NULL |
| customerID | int | FK → tblCustomer(ID), NULLABLE |
| userID | int | FK → tblUser(ID), NOT NULL |
| couponID | int | FK → tblCoupon(ID), NULLABLE |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.order`: chứa LoginFrm, HomeFrm, OrderFrm.
2. Tạo package `model`: chứa Table, Customer, Dish, Combo, ComboDetail, Order, OrderDetail, Invoice, Coupon, User.
3. Tạo package `dao`: chứa UserDAO, TableDAO, DishDAO, OrderDAO, OrderDetailDAO, InvoiceDAO.
4. Vẽ association từ OrderFrm → TableDAO, OrderFrm → DishDAO, OrderFrm → OrderDAO, OrderFrm → OrderDetailDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dùng mũi tên dashed).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `UserDAO`, `HomeFrm`, `OrderFrm`, `TableDAO`, `DishDAO`, `OrderDAO`, `OrderDetailDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh ngoại lệ (món không tìm thấy).
4. Sử dụng `loop` fragment cho việc lặp chọn món.

### Sequence Diagram (ASCII)

```
Staff      LoginFrm    UserDAO   HomeFrm    OrderFrm    TableDAO   DishDAO   OrderDAO  OrderDetailDAO
  |            |           |         |           |           |          |          |           |
  |--login---->|           |         |           |           |          |          |           |
  |            |--checkLogin()----->|           |           |          |          |           |
  |            |<--User-----|         |           |           |          |          |           |
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
  |            |           |         |           |--addToOrderDetail(in-memory)    |           |
  |            |           |         |           |           |          |          |           |
  |  (loop: repeat for "Coca" SL3, "Gà rán" SL1)           |          |          |           |
  |            |           |         |           |           |          |          |           |
  |--click Confirm-------->|         |           |           |          |          |           |
  |            |           |         |           |--addOrder(order)-------------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrder
  |            |           |         |           |<--orderId--|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--addOrderDetail(detail1)------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrderDetail
  |            |           |         |           |<--true-----|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--addOrderDetail(detail2)------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrderDetail
  |            |           |         |           |<--true-----|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--addOrderDetail(detail3)------->|           |
  |            |           |         |           |           |          |    |--INSERT tblOrderDetail
  |            |           |         |           |<--true-----|          |          |           |
  |            |           |         |           |           |          |          |           |
  |            |           |         |           |--show "Goi mon thanh cong"      |           |
  |<--success--|           |         |           |           |          |          |           |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | `actionPerformed("Login")` | Staff nhập username/password |
| 2 | LoginFrm | UserDAO | `checkLogin("staff01", "******")` | Kiểm tra thông tin đăng nhập |
| 3 | UserDAO | LoginFrm | `return User` | Trả về đối tượng User |
| 4 | LoginFrm | HomeFrm | `new HomeFrm(user).setVisible(true)` | Mở giao diện chính |
| 5 | Staff | HomeFrm | `actionPerformed("Order")` | Chọn chức năng Order |
| 6 | HomeFrm | OrderFrm | `new OrderFrm(user).setVisible(true)` | Mở giao diện gọi món |
| 7 | OrderFrm | TableDAO | `getAllTables()` | Lấy danh sách bàn |
| 8 | TableDAO | OrderFrm | `return List<Table>` | Trả về danh sách bàn |
| 9 | OrderFrm | OrderFrm | `populateComboBox(tables)` | Đổ dữ liệu vào dropdown |
| 10 | Staff | OrderFrm | `selectTable("B01")` | Chọn bàn B01 |
| 11 | Staff | OrderFrm | `setText("Phở bò")` | Nhập tên món |
| 12 | Staff | OrderFrm | `actionPerformed("Search")` | Nhấn nút Search |
| 13 | OrderFrm | DishDAO | `searchDishByName("Phở bò")` | Tìm món theo tên |
| 14 | DishDAO | OrderFrm | `return List<Dish>` | Trả về danh sách món |
| 15 | OrderFrm | OrderFrm | `displayTable(listDish)` | Hiển thị bảng kết quả |
| 16 | Staff | OrderFrm | `selectRow(0) + setText("2") + actionPerformed("OK")` | Chọn món, nhập SL, nhấn OK |
| 17 | OrderFrm | OrderFrm | `addToOrderDetail("Phở bò", 2, 50000)` | Thêm vào danh sách đã chọn (in-memory) |
| 18 | Staff | OrderFrm | (lặp) search "Coca" → select → qty 3 → OK | Lặp cho Coca |
| 19 | Staff | OrderFrm | (lặp) search "Gà rán" → select → qty 1 → OK | Lặp cho Gà rán |
| 20 | Staff | OrderFrm | `actionPerformed("Confirm")` | Nhấn Confirm |
| 21 | OrderFrm | OrderDAO | `addOrder(order)` | Lưu đơn hàng vào DB |
| 22 | OrderDAO | OrderFrm | `return orderId = 1` | Trả về ID đơn hàng |
| 23 | OrderFrm | OrderDetailDAO | `addOrderDetail(detail1)` | Lưu chi tiết: Phở bò × 2 |
| 24 | OrderDetailDAO | OrderFrm | `return true` | Thành công |
| 25 | OrderFrm | OrderDetailDAO | `addOrderDetail(detail2)` | Lưu chi tiết: Coca × 3 |
| 26 | OrderDetailDAO | OrderFrm | `return true` | Thành công |
| 27 | OrderFrm | OrderDetailDAO | `addOrderDetail(detail3)` | Lưu chi tiết: Gà rán × 1 |
| 28 | OrderDetailDAO | OrderFrm | `return true` | Thành công |
| 29 | OrderFrm | Staff | `showMessage("Goi mon thanh cong")` | Thông báo thành công |
| 30 | OrderFrm | HomeFrm | Quay về giao diện Home | Đóng OrderFrm, hiển thị HomeFrm |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Order | Gọi món thành công (3 món) |
| TC02 | Order | Nhập tên món không tồn tại |
| TC03 | Order | Nhập số lượng <= 0 |
| TC04 | Order | Chưa chọn bàn đã tìm món |
| TC05 | Order | Confirm khi chưa có món nào |
| TC06 | Order | Gọi món với combo |

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

**tblCombo:**
| ID | name | totalPrice |
|----|------|------------|
| (0 dòng) | | |

**tblComboDetail:**
| ID | comboID | dishID | quantity |
|----|---------|--------|----------|
| (0 dòng) | | | |

**tblCustomer:**
| ID | code | name | phone | email | address |
|----|------|------|-------|-------|---------|
| 1 | KH01 | Nguyễn Văn A | 0912345678 | nva@gmail.com | Hà Nội |

**tblOrder:**
| ID | tableID | customerID | userID | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| (0 dòng) | | | | | | |

**tblOrderDetail:**
| ID | orderID | dishID | comboID | quantity | unitPrice | amount |
|----|---------|--------|---------|----------|-----------|--------|
| (0 dòng) | | | | | | |

**tblCoupon:**
| ID | code | discountPercent | validUntil |
|----|------|-----------------|------------|
| (0 dòng) | | | |

**tblInvoice:**
| ID | invoiceDate | totalAmount | orderID | customerID | userID | couponID |
|----|-------------|-------------|---------|------------|--------|----------|
| (0 dòng) | | | | | | |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị: ô username, ô password, nút Login |
| 2 | Nhập username = `staff01`, password = `123456`, nhấn Login | Giao diện Home hiển thị với các chức năng: Order, Book a table, Payment |
| 3 | Nhấn chọn **Order** | Giao diện Order hiển thị: combobox danh sách bàn (B01, B02, B03), ô nhập tên món, nút Search |
| 4 | **Kiểm tra DB trước:** tblOrder có 0 dòng, tblOrderDetail có 0 dòng. | Dữ liệu đúng như Database trước khi test |
| 5 | Chọn bàn "B01 — Bàn VIP 1" từ dropdown | Bàn B01 được chọn. Ô tìm kiếm món sẵn sàng nhập |
| 6 | Nhập "Phở bò" vào ô tên món, nhấn Search | Bảng kết quả hiển thị 1 dòng: SP01 — Món chính — Phở bò — 50,000đ |
| 7 | Chọn dòng "Phở bò", nhập số lượng = 2, nhấn OK | Danh sách bên dưới cập nhật: Phở bò — SL: 2 — Đơn giá: 50,000đ — Thành tiền: 100,000đ |
| 8 | Nhập "Coca" vào ô tên món, nhấn Search | Bảng kết quả hiển thị: SP05 — Đồ uống — Coca — 15,000đ |
| 9 | Chọn dòng "Coca", nhập số lượng = 3, nhấn OK | Danh sách cập nhật thêm: Coca — SL: 3 — Đơn giá: 15,000đ — Thành tiền: 45,000đ. Tổng tạm tính: 145,000đ |
| 10 | Nhập "Gà rán" vào ô tên món, nhấn Search | Bảng kết quả hiển thị: SP03 — Món chính — Gà rán — 65,000đ |
| 11 | Chọn dòng "Gà rán", nhập số lượng = 1, nhấn OK | Danh sách cập nhật thêm: Gà rán — SL: 1 — Đơn giá: 65,000đ — Thành tiền: 65,000đ. Tổng tạm tính: 210,000đ |
| 12 | Nhấn **Confirm** | Hệ thống hiển thị thông báo "Goi mon thanh cong" |
| 13 | **Kiểm tra DB sau:** tblOrder có 1 dòng mới, tblOrderDetail có 3 dòng mới | Dữ liệu đúng như Database sau khi test |

### Database sau khi test

**tblOrder:** (thêm 1 dòng)
| ID | tableID | customerID | userID | orderDate | totalAmount | status |
|----|---------|------------|--------|-----------|-------------|--------|
| 1 | 1 | NULL | 1 | 2026-06-08 10:30 | 210000 | active |

**tblOrderDetail:** (thêm 3 dòng)
| ID | orderID | dishID | comboID | quantity | unitPrice | amount |
|----|---------|--------|---------|----------|-----------|--------|
| 1 | 1 | 1 | NULL | 2 | 50000 | 100000 |
| 2 | 1 | 5 | NULL | 3 | 15000 | 45000 |
| 3 | 1 | 3 | NULL | 1 | 65000 | 65000 |

**Các bảng khác:** Không thay đổi (tblUser, tblTable, tblDish, tblCombo, tblCustomer, tblCoupon, tblInvoice).
