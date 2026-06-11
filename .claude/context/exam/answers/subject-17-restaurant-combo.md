# Subject No. 17 — Restaurant — Module "Make a combo menu"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Tạo combo

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Manager nhập username `manager01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Order, Book a table, Payment, Combo management, Statistics. |
| 4 | Manager chọn **Combo management**. Giao diện quản lý combo xuất hiện: danh sách combo hiện có, nút Add new combo (btnAddCombo), nút Edit, nút Delete. |
| 5 | Manager nhấn **Add new combo**. Giao diện thêm combo xuất hiện: ô nhập tên combo (txtComboName), danh sách món trong combo (tblComboDetail — trống), nút Add dishes (btnAddDishes), nút Update (btnUpdate). |
| 6 | Manager nhập tên combo "Combo Phở + Coca" vào ô Combo name. |
| 7 | Manager nhấn **Add dishes**. Giao diện tìm món xuất hiện: ô nhập tên món (txtDishName), nút Search (btnSearchDish), bảng danh sách món (tblDishList). |
| 8 | Manager nhập "Phở" vào ô tên món, nhấn Search. |
| 9 | Hệ thống hiển thị danh sách món chứa từ khóa "Phở": SP01 — Món chính — Phở bò — Phở bò tái chín — 50,000đ; SP02 — Món chính — Phở gà — Phở gà truyền thống — 45,000đ. |
| 10 | Manager chọn "Phở bò" (SP01, 50,000đ). Hệ thống quay về giao diện thêm combo, Phở bò được thêm vào danh sách combo. |
| 11 | Manager nhấn **Add dishes** lần nữa. Giao diện tìm món xuất hiện. |
| 12 | Manager nhập "Coca", nhấn Search. Hệ thống hiển thị: SP05 — Đồ uống — Coca — Coca Cola 330ml — 15,000đ. |
| 13 | Manager chọn "Coca" (SP05, 15,000đ). Quay về giao diện combo, Coca được thêm. Danh sách combo: Phở bò (50,000đ) + Coca (15,000đ). Tổng: 65,000đ. |
| 14 | Manager nhấn **Update**. Hệ thống lưu combo vào database (tblCombo + tblComboDetail). |
| 15 | Hệ thống thông báo "Tao combo thanh cong". Quay về giao diện quản lý combo. |

### Kịch bản ngoại lệ

- **EL1:** Tên combo để trống → hệ thống cảnh báo "Vui long nhap ten combo".
- **EL2:** Không có món nào trong combo khi nhấn Update → hệ thống cảnh báo "Combo phai co it nhat 1 mon".
- **EL3:** Nhập tên món không tồn tại → danh sách kết quả trống, yêu cầu nhập lại.
- **EL4:** Chọn trùng món đã có trong combo → hệ thống thông báo "Mon da ton tai trong combo".

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Nhà hàng cho phép quản lý tạo các combo kết hợp nhiều món ăn thành một bữa cho một người. Mỗi combo có tên và tổng giá. Một combo chứa nhiều món (combo detail), mỗi món có số lượng cụ thể. Mỗi món ăn có mã, loại, tên, mô tả và giá. Khi tạo combo, quản lý nhập tên combo, sau đó tìm và chọn từng món ăn để thêm vào combo. Hệ thống tự động tính tổng giá dựa trên giá và số lượng các món trong combo.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Combo | Entity class | | Đối tượng combo cần quản lý |
| name | Attribute (Combo) | String | Tên combo |
| totalPrice | Attribute (Combo) | float | Tổng giá combo |
| Dish | Entity class | | Món ăn trong thực đơn |
| code | Attribute (Dish) | String | Mã món |
| type | Attribute (Dish) | String | Loại món |
| name | Attribute (Dish) | String | Tên món |
| description | Attribute (Dish) | String | Mô tả món |
| price | Attribute (Dish) | float | Giá hiện tại |
| ComboDetail | Entity class | | Chi tiết món trong combo |
| quantity | Attribute (ComboDetail) | int | Số lượng món |
| User | Entity class | | Quản lý thao tác |
| menu | Rejected (context) | | Không phải entity, chỉ là tên chức năng |

### Class Diagram (ASCII)

```
+------------------+       +------------------+       +------------------+
|      User        |       |     Combo        |       |      Dish        |
+------------------+       +------------------+       +------------------+
| -id: int         |       | -id: int         |       | -id: int         |
| -username: String|       | -name: String    |       | -code: String    |
| -password: String|       | -totalPrice: float|      | -type: String    |
| -role: String    |       +------------------+       | -name: String    |
+------------------+       | 1        | 1             | -description: String|
         | 1               |          |               | -price: float    |
         |                 | n        |               +------------------+
         | n               v          |                        | 1
         v         +------------------+                        |
+------------------+       | ComboDetail      |                |
|                  |       +------------------+                |
|                  |       | -id: int         |                |
|                  |       | -quantity: int   |                |
|                  |       +------------------|                |
|                  |               | n                         |
|                  |               |                           |
|                  |               +---------------------------+
|                  |
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Combo → ComboDetail | 1-n | Một combo chứa nhiều chi tiết món |
| Dish → ComboDetail | 1-n | Một món xuất hiện trong nhiều combo |
| User → Combo | 1-n | Một quản lý tạo nhiều combo |
| Combo ↔ Dish | n-n (qua ComboDetail) | Combo chứa nhiều món, món thuộc nhiều combo |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity class.
3. Tạo view class boxes từ các interface (form) của module.
4. Vẽ mối quan hệ (relationship) giữa các class.
5. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Combo`, `<<boundary>> AddComboFrm`, `<<control>> ComboDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-name: String`, `-totalPrice: float`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+addCombo(combo: Combo): int`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Combo | <<entity>> | -id: int, -name: String, -totalPrice: float | +getAllCombos(): List<Combo>, +addCombo(combo: Combo): int |
| Dish | <<entity>> | -id: int, -code: String, -type: String, -name: String, -description: String, -price: float | +searchDishByName(name: String): List<Dish> |
| ComboDetail | <<entity>> | -id: int, -quantity: int | +addComboDetail(detail: ComboDetail): boolean |
| User | <<entity>> | -id: int, -username: String, -password: String, -role: String | (không có) |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -subCombo: JButton | Giao diện chính, chứa nút chọn Combo management |
| ComboManageFrm | <<boundary>> | -outsubListCombo: JTable, -subAddCombo: JButton, -subEdit: JButton, -subDelete: JButton | Giao diện quản lý danh sách combo |
| AddComboFrm | <<boundary>> | -inComboName: JTextField, -outsubListComboDetail: JTable, -subAddDishes: JButton, -subUpdate: JButton | Giao diện thêm/sửa combo |
| SearchDishFrm | <<boundary>> | -inDishName: JTextField, -subSearch: JButton, -outsubListDish: JTable | Giao diện tìm kiếm món ăn |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inComboName, inDishName
- Tiền tố `out` → output (vùng hiển thị): (không có)
- Tiền tố `outsub` → clickable output (bảng click được): outsubListCombo, outsubListComboDetail, outsubListDish
- Tiền tố `sub` → submit (nút bấm): subAddCombo, subEdit, subDelete, subAddDishes, subUpdate, subSearch

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: User → Combo (quản lý tham chiếu đến combo).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập. Ví dụ: Combo → ComboDetail (chi tiết combo tham chiếu combo nhưng món ăn vẫn tồn tại độc lập).
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: AddComboFrm → DishDAO (form sử dụng DAO để tìm món).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Combo (1) → (n) ComboDetail nghĩa là một combo có nhiều chi tiết món.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Combo | ComboDetail | Composition | 1 - n | Một combo chứa nhiều chi tiết món; chi tiết không tồn tại nếu không có combo |
| Dish | ComboDetail | Association | 1 - n | Một món ăn xuất hiện trong nhiều combo khác nhau |
| User | Combo | Association | 1 - n | Một quản lý tạo nhiều combo |

Quan hệ n-n giữa Combo và Dish được tách thành hai quan hệ 1-n qua bảng trung gian ComboDetail.

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Combo → ComboDetail (1-n, Composition)**
1. Tạo class `<<entity>> Combo` với các thuộc tính: -id: int, -name: String, -totalPrice: float.
2. Tạo class `<<entity>> ComboDetail` với các thuộc tính: -id: int, -quantity: int.
3. Chọn công cụ **Composition** từ palette Relationships (đầu kim cương filled ◆).
4. Click vào class Combo → kéo đến class ComboDetail.
5. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
6. Kết quả: Combo (1) ◆----(*) ComboDetail.

**Ví dụ 2: Vẽ quan hệ Dish → ComboDetail (1-n, Association)**
1. Tạo class `<<entity>> Dish` với các thuộc tính: -id: int, -code: String, -type: String, -name: String, -description: String, -price: float.
2. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
3. Click vào class Dish → kéo đến class ComboDetail.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Đặt tên association: "appears in" (tùy chọn).
6. Kết quả: Dish (1) ▷----(*) ComboDetail.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  một tùy chọn quản lý combo -> subCombo

Chọn Combo management -> ComboManageFrm xuất hiện:
  danh sách combo hiện có (clickable) -> outsubListCombo
  nút thêm combo mới -> subAddCombo
  nút sửa -> subEdit
  nút xóa -> subDelete

Nhấn Add new combo -> AddComboFrm xuất hiện:
  ô nhập tên combo -> inComboName
  danh sách món trong combo -> outsubListComboDetail
  nút thêm món -> subAddDishes
  nút cập nhật -> subUpdate

Nhấn Add dishes -> SearchDishFrm xuất hiện:
  ô nhập tên món -> inDishName
  nút tìm kiếm -> subSearch
  bảng danh sách món (clickable) -> outsubListDish

Nhập tên món và nhấn Search -> hệ thống tìm món theo tên -> cần phương thức:
  tên: searchDishByName()
  đầu vào: name (String)
  đầu ra: list of Dish
  gán cho entity class: Dish.

Khi mở ComboManageFrm -> hệ thống lấy danh sách combo -> cần phương thức:
  tên: getAllCombos()
  đầu vào: (không có)
  đầu ra: list of Combo
  gán cho entity class: Combo.

Nhấn Update -> hệ thống lưu combo -> cần phương thức:
  tên: addCombo()
  đầu vào: combo (Combo)
  đầu ra: int (comboId)
  gán cho entity class: Combo.

Nhấn Update -> hệ thống lưu chi tiết combo -> cần phương thức:
  tên: addComboDetail()
  đầu vào: detail (ComboDetail)
  đầu ra: boolean
  gán cho entity class: ComboDetail.

### Summary
View classes: HomeFrm, ComboManageFrm, AddComboFrm, SearchDishFrm
Methods: getAllCombos(), searchDishByName(), addCombo(), addComboDetail()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: ô nhập tên đăng nhập (JTextField)
- `inPassword`: ô nhập mật khẩu (JPasswordField)
- `subLogin`: nút Login (JButton)

**HomeFrm:**
- `subOrder`: nút Order (JButton)
- `subCombo`: nút Combo management (JButton)
- `subPayment`: nút Payment (JButton)

**ComboManageFrm:**
- `outsubListCombo`: bảng danh sách combo hiện có (JTable)
- `subAddCombo`: nút Add new combo (JButton)
- `subEdit`: nút Edit combo (JButton)
- `subDelete`: nút Delete combo (JButton)

**AddComboFrm:**
- `inComboName`: ô nhập tên combo (JTextField)
- `outsubListComboDetail`: bảng danh sách món trong combo đang tạo (JTable)
- `subAddDishes`: nút Add dishes (JButton)
- `subUpdate`: nút Update lưu combo (JButton)

**SearchDishFrm:**
- `inDishName`: ô nhập tên món cần tìm (JTextField)
- `subSearch`: nút Search (JButton)
- `outsubListDish`: bảng danh sách món tìm được, click để chọn (JTable)

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Dish | Model | id: int, code: String, type: String, name: String, description: String, price: float |
| Combo | Model | id: int, name: String, totalPrice: float |
| ComboDetail | Model | id: int, comboId: int, dishId: int, quantity: int |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| DishDAO | `searchDishByName(name): List<Dish>` | Tìm món theo tên |
| ComboDAO | `addCombo(combo): int` | Lưu combo mới, trả về comboId |
| ComboDAO | `getAllCombos(): List<Combo>` | Lấy danh sách combo |
| ComboDetailDAO | `addComboDetail(detail): boolean` | Lưu chi tiết combo |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| username | varchar(50) | NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

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

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.combo`: chứa LoginFrm, HomeFrm, ComboManageFrm, AddComboFrm, SearchDishFrm.
2. Tạo package `model`: chứa Dish, Combo, ComboDetail, User.
3. Tạo package `dao`: chứa UserDAO, DishDAO, ComboDAO, ComboDetailDAO.
4. Vẽ association từ AddComboFrm → DishDAO (khi tìm món), AddComboFrm → ComboDAO (khi lưu combo), AddComboFrm → ComboDetailDAO (khi lưu chi tiết).
5. Vẽ dependency từ DAO classes → Entity classes (dashed arrow).
6. Thêm kiểu trả về cho các phương thức DAO.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Manager`, `LoginFrm`, `HomeFrm`, `ComboManageFrm`, `AddComboFrm`, `SearchDishFrm`, `UserDAO`, `DishDAO`, `ComboDAO`, `ComboDetailDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `loop` fragment cho việc lặp tìm và chọn món.

### Sequence Diagram (ASCII)

```
Manager   LoginFrm  UserDAO  HomeFrm  ComboManageFrm  AddComboFrm  SearchDishFrm  DishDAO  ComboDAO  ComboDetailDAO
  |          |         |       |            |              |              |            |         |           |
  |--login-->|         |       |            |              |              |            |         |           |
  |          |--checkLogin()-->|            |              |              |            |         |           |
  |          |<--User--|       |            |              |              |            |         |           |
  |          |--open HomeFrm-->|            |              |              |            |         |           |
  |          |         |       |            |              |              |            |         |           |
  |--select Combo Mgmt-------->|            |              |              |            |         |           |
  |          |         |       |--open ComboManageFrm----->|              |            |         |           |
  |          |         |       |            |              |              |            |         |           |
  |--click Add new combo------>|            |              |              |            |         |           |
  |          |         |       |            |--open AddComboFrm--------->|            |         |           |
  |          |         |       |            |              |              |            |         |           |
  |--enter "Combo Phở + Coca"->|            |              |              |            |         |           |
  |--click Add dishes--------->|            |              |              |            |         |           |
  |          |         |       |            |              |--open SearchDishFrm----->|         |           |
  |          |         |       |            |              |              |            |         |           |
  |--enter "Phở", Search------>|            |              |              |            |         |           |
  |          |         |       |            |              |              |--searchDishByName("Phở")---->|
  |          |         |       |            |              |              |<--List<Dish>|         |           |
  |          |         |       |            |              |              |--display list          |           |
  |          |         |       |            |              |              |            |         |           |
  |--select "Phở bò"--------->|            |              |              |            |         |           |
  |          |         |       |            |              |<--return dish|            |         |           |
  |          |         |       |            |              |--add to combo list       |         |           |
  |          |         |       |            |              |              |            |         |           |
  |  (loop: click Add dishes, search "Coca", select "Coca")             |            |         |           |
  |          |         |       |            |              |              |            |         |           |
  |--click Update------------->|            |              |              |            |         |           |
  |          |         |       |            |              |--addCombo(combo)--------->|         |           |
  |          |         |       |            |              |              |     |--INSERT tblCombo       |
  |          |         |       |            |              |<--comboId----|         |           |
  |          |         |       |            |              |              |            |         |           |
  |          |         |       |            |              |--addComboDetail(detail1)->|         |           |
  |          |         |       |            |              |              |     |--INSERT tblComboDetail  |
  |          |         |       |            |              |<--true------|         |           |
  |          |         |       |            |              |              |            |         |           |
  |          |         |       |            |              |--addComboDetail(detail2)->|         |           |
  |          |         |       |            |              |              |     |--INSERT tblComboDetail  |
  |          |         |       |            |              |<--true------|         |           |
  |          |         |       |            |              |              |            |         |           |
  |          |         |       |            |              |--show "Tao combo thanh cong"     |           |
  |<--success|         |       |            |              |              |            |         |           |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Manager | LoginFrm | actionPerformed("Login") | Nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("manager01", "******") | Kiểm tra đăng nhập |
| 3 | UserDAO | LoginFrm | return User | Trả về đối tượng User |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Manager | HomeFrm | actionPerformed("Combo management") | Chọn quản lý combo |
| 6 | HomeFrm | ComboManageFrm | new ComboManageFrm().setVisible(true) | Mở form quản lý combo |
| 7 | Manager | ComboManageFrm | actionPerformed("AddNewCombo") | Nhấn thêm combo mới |
| 8 | ComboManageFrm | AddComboFrm | new AddComboFrm().setVisible(true) | Mở form thêm combo |
| 9 | Manager | AddComboFrm | setText(txtComboName, "Combo Phở + Coca") | Nhập tên combo |
| 10 | Manager | AddComboFrm | actionPerformed("AddDishes") | Nhấn thêm món |
| 11 | AddComboFrm | SearchDishFrm | new SearchDishFrm().setVisible(true) | Mở form tìm món |
| 12 | Manager | SearchDishFrm | setText(txtDishName, "Phở") | Nhập tên món |
| 13 | Manager | SearchDishFrm | actionPerformed("Search") | Nhấn Search |
| 14 | SearchDishFrm | DishDAO | searchDishByName("Phở") | Tìm món |
| 15 | DishDAO | SearchDishFrm | return List<Dish> [Phở bò, Phở gà] | Trả về 2 món |
| 16 | SearchDishFrm | SearchDishFrm | displayTable(listDish) | Hiển thị danh sách |
| 17 | Manager | SearchDishFrm | selectRow("Phở bò") | Chọn Phở bò |
| 18 | SearchDishFrm | AddComboFrm | return dish (Phở bò) | Trả về form combo |
| 19 | AddComboFrm | AddComboFrm | addToComboDetailList(dish) | Thêm vào danh sách |
| 20 | Manager | AddComboFrm | (lặp) AddDishes → search "Coca" → select "Coca" | Lặp cho Coca |
| 21 | AddComboFrm | AddComboFrm | addToComboDetailList(dish) | Thêm Coca vào danh sách |
| 22 | Manager | AddComboFrm | actionPerformed("Update") | Nhấn Update |
| 23 | AddComboFrm | new Combo() | Combo("Combo Phở + Coca", 65000) | Tạo đối tượng Combo |
| 24 | AddComboFrm | ComboDAO | addCombo(combo) | Lưu combo |
| 25 | ComboDAO | AddComboFrm | return comboId = 1 | Trả về ID |
| 26 | AddComboFrm | ComboDetailDAO | addComboDetail(comboId=1, dishId=1, qty=1) | Lưu Phở bò |
| 27 | ComboDetailDAO | AddComboFrm | return true | Thành công |
| 28 | AddComboFrm | ComboDetailDAO | addComboDetail(comboId=1, dishId=5, qty=1) | Lưu Coca |
| 29 | ComboDetailDAO | AddComboFrm | return true | Thành công |
| 30 | AddComboFrm | Manager | showMessage("Tao combo thanh cong") | Thông báo |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Combo | Tạo combo thành công (2 món) |
| TC02 | Combo | Tên combo để trống |
| TC03 | Combo | Không có món nào trong combo |
| TC04 | Combo | Nhập tên món không tồn tại |
| TC05 | Combo | Sửa combo hiện có (thêm/xóa món) |

### TC01: Tạo combo thành công

**Purpose:** Kiểm tra quy trình tạo combo hoàn chỉnh từ nhập tên combo, tìm và chọn món đến lưu combo vào database.

**Purpose:** Kiểm tra quy trình tạo combo hoàn chỉnh từ nhập tên combo, tìm và chọn món đến lưu combo vào database.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | manager01 | admin123 | manager |
| 2 | staff01 | 123456 | staff |

**tblDish:**
| ID | code | type | name | description | price |
|----|------|------|------|-------------|-------|
| 1 | SP01 | Món chính | Phở bò | Phở bò tái chín | 50000 |
| 2 | SP02 | Món chính | Phở gà | Phở gà truyền thống | 45000 |
| 3 | SP03 | Món chính | Gà rán | Gà rán giòn | 65000 |
| 4 | SP04 | Tráng miệng | Kem vani | Kem vani Ý | 30000 |
| 5 | SP05 | Đồ uống | Coca | Coca Cola 330ml | 15000 |
| 6 | SP06 | Đồ uống | Pepsi | Pepsi 330ml | 15000 |

**tblCombo:**
| ID | name | totalPrice |
|----|------|------------|
| 1 | Combo Gà rán + Kem | 95000 |

**tblComboDetail:**
| ID | comboID | dishID | quantity |
|----|---------|--------|----------|
| 1 | 1 | 3 | 1 |
| 2 | 1 | 4 | 1 |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `manager01` / `admin123`, nhấn Login | Giao diện Home hiển thị |
| 3 | Nhấn **Combo management** | Giao diện quản lý combo: bảng danh sách combo (hiện 1 combo: Combo Gà rán + Kem), nút Add new combo |
| 4 | Nhấn **Add new combo** | Giao diện thêm combo: ô nhập tên combo (trống), bảng danh sách món (trống), nút Add dishes, nút Update |
| 5 | Nhập "Combo Phở + Coca" vào ô tên combo | Tên combo hiển thị: "Combo Phở + Coca" |
| 6 | Nhấn **Add dishes** | Giao diện tìm món xuất hiện: ô nhập tên món, nút Search |
| 7 | Nhập "Phở", nhấn Search | Bảng hiển thị 2 dòng: SP01 — Phở bò — 50,000đ; SP02 — Phở gà — 45,000đ |
| 8 | Chọn dòng "Phở bò" (SP01) | Quay về form combo. Danh sách món: Phở bò — SL 1 — 50,000đ |
| 9 | Nhấn **Add dishes** lần nữa | Giao diện tìm món xuất hiện |
| 10 | Nhập "Coca", nhấn Search | Bảng hiển thị: SP05 — Coca — 15,000đ |
| 11 | Chọn dòng "Coca" (SP05) | Quay về form combo. Danh sách: Phở bò (50,000đ) + Coca (15,000đ). Tổng: 65,000đ |
| 12 | **Kiểm tra DB trước:** tblCombo có 1 dòng (ID=1). tblComboDetail có 2 dòng (comboID=1). | Dữ liệu đúng như Database trước khi test |
| 13 | Nhấn **Update** | Thông báo "Tao combo thanh cong" |
| 14 | **Kiểm tra DB sau:** tblCombo có 2 dòng (thêm ID=2). tblComboDetail có 4 dòng (thêm 2 dòng comboID=2). | Dữ liệu đúng như Database sau khi test |

### Database sau khi test

**tblCombo:** (thêm 1 dòng)
| ID | name | totalPrice |
|----|------|------------|
| 1 | Combo Gà rán + Kem | 95000 |
| 2 | Combo Phở + Coca | 65000 |

**tblComboDetail:** (thêm 2 dòng)
| ID | comboID | dishID | quantity |
|----|---------|--------|----------|
| 1 | 1 | 3 | 1 |
| 2 | 1 | 4 | 1 |
| 3 | 2 | 1 | 1 |
| 4 | 2 | 5 | 1 |
