# Subject No. 16 — Restaurant — Module "Book a table"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Đặt bàn

### Kịch bản chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện: ô nhập username, ô nhập password, nút Login. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Order, Book a table, Payment, Combo management, Statistics. |
| 4 | Staff chọn chức năng **Book a table**. |
| 5 | Giao diện tìm bàn trống xuất hiện: ô nhập ngày đặt, ô nhập giờ đặt, ô nhập số khách, nút Search. |
| 6 | Staff nhập ngày 15/07/2026, giờ 18:00, số khách 4, nhấn Search. |
| 7 | Hệ thống hiển thị danh sách bàn trống tại thời điểm đó: bảng gồm cột Mã bàn, Tên bàn, Số khách tối đa, Mô tả. Ví dụ: B01 — Bàn VIP 1 — 6 khách — Tầng 1; B03 — Bàn thường 1 — 4 khách — Tầng 2; B05 — Bàn thường 3 — 4 khách — Tầng 2. |
| 8 | Staff hỏi khách và chọn bàn "B03 — Bàn thường 1, tối đa 4 khách". |
| 9 | Giao diện nhập thông tin khách hàng xuất hiện: ô nhập mã KH, ô nhập tên KH, ô nhập SĐT, ô nhập email, ô nhập địa chỉ, nút Search, nút Add New. |
| 10 | Staff hỏi khách, nhập "Nguyen Van A" vào ô tên, nhấn Search. |
| 11 | Hệ thống hiển thị danh sách khách hàng có tên chứa từ khóa: bảng gồm cột Mã KH, Tên, SĐT, Email, Địa chỉ. Ví dụ: KH01 — Nguyen Van A — 0912345678 — nva@gmail.com — Ha Noi. |
| 12 | Staff nhấn chọn dòng đúng với khách hàng hiện tại "KH01 — Nguyen Van A". |
| 13 | Hệ thống hiển thị thông tin đầy đủ: Bàn B03 + Khách hàng Nguyen Van A + Ngày 15/07/2026 + Giờ 18:00 + Số khách 4. |
| 14 | Staff xác nhận với khách, nhấn **Confirm** (btnConfirm). |
| 15 | Hệ thống lưu thông tin đặt bàn vào database (tblBooking). |
| 16 | Hệ thống thông báo "Đặt bàn thành công" và quay về giao diện Home. |

### Kịch bản ngoại lệ

- **EL1:** Không có bàn trống cho ngày/giờ/số khách yêu cầu → danh sách trống, yêu cầu thay đổi thông tin.
- **EL2:** Khách hàng không tìm thấy trong hệ thống → staff nhấn "Add New" để thêm khách hàng mới.
- **EL3:** Staff chưa chọn bàn mà đã nhấn Confirm → hệ thống yêu cầu chọn bàn trước.
- **EL4:** Staff chưa chọn khách hàng mà đã nhấn Confirm → hệ thống yêu cầu chọn hoặc thêm khách hàng.

---

## Câu 2: Entity Class Diagram

### Mô tả hệ thống bằng ngôn ngữ tự nhiên

Nhà hàng quản lý việc đặt bàn cho khách. Nhà hàng có nhiều bàn ăn, mỗi bàn có mã, tên, số khách tối đa và mô tả. Nhiều bàn nhỏ có thể gộp thành bàn lớn khi có nhóm khách đông. Mỗi bàn có thể được đặt nhiều lần trong ngày hoặc nhiều ngày khác nhau. Khách hàng có mã, tên, số điện thoại, email và địa chỉ. Một khách hàng có thể đặt bàn nhiều lần, mỗi lần có thể đặt nhiều bàn (gộp lại). Khi đặt bàn, nhân viên tìm bàn trống theo ngày, giờ và số khách. Sau đó nhập hoặc tìm thông tin khách hàng. Hệ thống lưu lại thông tin đặt bàn bao gồm bàn, khách hàng, ngày giờ và nhân viên thực hiện.

### Trích xuất danh từ

| Danh từ | Phân loại | Loại | Lý do |
|---------|-----------|------|-------|
| Restaurant | Entity class | | Hệ thống quản lý trong phạm vi nhà hàng |
| Table | Entity class | | Đối tượng bàn ăn |
| code | Attribute (Table) | String | Mã bàn |
| name | Attribute (Table) | String | Tên bàn |
| max guests | Attribute (Table) | int | Số khách tối đa |
| description | Attribute (Table) | String | Mô tả bàn |
| Customer | Entity class | | Khách hàng đặt bàn |
| code | Attribute (Customer) | String | Mã khách hàng |
| name | Attribute (Customer) | String | Tên khách hàng |
| phone | Attribute (Customer) | String | Số điện thoại |
| email | Attribute (Customer) | String | Email |
| address | Attribute (Customer) | String | Địa chỉ |
| Booking | Entity class | | Bản ghi đặt bàn |
| date | Attribute (Booking) | Date | Ngày đặt |
| time | Attribute (Booking) | String | Giờ đặt |
| numGuests | Attribute (Booking) | int | Số khách |
| User | Entity class | | Nhân viên thao tác |
| booking | Rejected (verb) | | Không phải danh từ |

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
|     Table        |       |    Booking       |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -bookingDate: Date|
| -name: String    |       | -bookingTime: String|
| -maxGuests: int  |       | -numGuests: int  |
| -description: String|    | -status: String  |
+------------------+       | -table: Table    |
                           | -customer: Customer|
                           | -user: User      |
                           +------------------+
         | n                        | n
         |                          |
         |                          |
         +----------+---------------+
                    |
                    v
           +------------------+
           |    Customer      |
           +------------------+
           | -id: int         |
           | -code: String    |
           | -name: String    |
           | -phone: String   |
           | -email: String   |
           | -address: String |
           +------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Multiplicity | Giải thích |
|----------|------|--------------|------------|
| Restaurant → Table | Composition | 1 - n | Một nhà hàng có nhiều bàn; bàn không tồn tại nếu không có nhà hàng |
| Table → Booking | Association | 1 - n | Một bàn có nhiều lần đặt bàn (khác ngày/giờ) |
| Customer → Booking | Association | 1 - n | Một khách hàng đặt bàn nhiều lần |
| User → Booking | Association | 1 - n | Một nhân viên xử lý nhiều đặt bàn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo entity class boxes (hình chữ nhật 3 ngăn) cho từng entity class.
3. Tạo view class boxes từ các interface (form) của module.
4. Vẽ mối quan hệ (relationship) giữa các class.
5. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>`, `<<boundary>>`, hoặc `<<control>>` phía trên tên class. Ví dụ: `<<entity>> Booking`, `<<boundary>> BookTableFrm`, `<<control>> BookingDAO`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-id: int`, `-code: String`, `-bookingDate: Date`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+addBooking(booking: Booking): boolean`.

Trong Visual Paradigm, click đúp vào class box để chỉnh sửa tên, tab Attributes để thêm thuộc tính, tab Operations để thêm phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Restaurant | <<entity>> | -id: int, -name: String, -address: String, -description: String | (không có) |
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -description: String | +getAvailableTables(date: Date, time: String, numGuests: int): List<Table> |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | +searchCustomerByName(name: String): List<Customer> |
| Booking | <<entity>> | -id: int, -bookingDate: Date, -bookingTime: String, -numGuests: int, -status: String, -table: Table, -customer: Customer, -user: User | +addBooking(booking: Booking): boolean |
| User | <<entity>> | -id: int, -username: String, -password: String, -role: String | +checkLogin(username: String, password: String): boolean |

#### 4. Bảng chi tiết view classes

| View Class | Stereotype | UI Elements | Mô tả |
|-----------|-----------|-------------|-------|
| HomeFrm | <<boundary>> | -subBookTable: JButton | Giao diện chính, chứa nút chọn Book a table |
| BookTableFrm | <<boundary>> | -inDate: JTextField, -inTime: JTextField, -inNumGuests: JTextField, -subSearchTable: JButton, -outsubListTable: JTable, -inCustomerName: JTextField, -subSearchCustomer: JButton, -outsubListCustomer: JTable, -subAddCustomer: JButton, -outBookingInfo: JLabel, -subConfirm: JButton | Giao diện đặt bàn |

Quy tắc đặt tên UI elements:
- Tiền tố `in` → input (ô nhập liệu): inDate, inTime, inNumGuests, inCustomerName
- Tiền tố `out` → output (vùng hiển thị): outBookingInfo
- Tiền tố `outsub` → clickable output (bảng click được): outsubListTable, outsubListCustomer
- Tiền tố `sub` → submit (nút bấm): subSearchTable, subSearchCustomer, subAddCustomer, subConfirm

#### 5. Cách vẽ quan hệ

Trong Visual Paradigm, sử dụng palette Relationships ở bên phải để chọn kiểu quan hệ:

- **Association** (đường liền nét, mũi tên tam giác rỗng ▷): dùng cho quan hệ tham chiếu thông thường. Ví dụ: Customer → Booking (khách hàng tham chiếu đến đặt bàn).
- **Aggregation** (đường liền nét, đầu kim cương rỗng ◇): dùng cho "contain" nhưng child có thể tồn tại độc lập.
- **Composition** (đường liền nét, đầu kim cương filled ◆): dùng cho "contain" nhưng child KHÔNG tồn tại nếu không có parent.
- **Dependency** (đường dashed, mũi tên tam giác rỗng ▷): dùng cho "sử dụng" tạm thời. Ví dụ: BookTableFrm → TableDAO (form sử dụng DAO để tìm bàn trống).

#### 6. Cách ghi multiplicity

Trong Visual Paradigm, click vào đường kết nối → tab Properties → chỉnh Source Multiplicity và Target Multiplicity:

- 1..1 → ghi "1" ở một đầu.
- 0..* hoặc 1..* → ghi "n" hoặc "*" ở đầu kia.
- Ghi multiplicity ở cả 2 đầu của đường kết nối.

Ví dụ: Table (1) → (n) Booking nghĩa là một bàn có nhiều lần đặt.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|---|---|---|---|---|
| Restaurant | Table | Association | 1 - n | Một nhà hàng có nhiều bàn ăn |
| Table | Booking | Association | 1 - n | Một bàn có nhiều lần đặt bàn (khác ngày/giờ) |
| Customer | Booking | Association | 1 - n | Một khách hàng đặt bàn nhiều lần |
| User | Booking | Association | 1 - n | Một nhân viên xử lý nhiều đặt bàn |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ Table → Booking (1-n, Association)**
1. Tạo class `<<entity>> Table` với các thuộc tính: -id: int, -code: String, -name: String, -maxGuests: int, -description: String.
2. Tạo class `<<entity>> Booking` với các thuộc tính: -id: int, -bookingDate: Date, -bookingTime: String, -numGuests: int, -status: String.
3. Chọn công cụ **Association** từ palette Relationships (mũi tên tam giác rỗng ▷).
4. Click vào class Table → kéo đến class Booking.
5. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
6. Đặt tên association: "has bookings" (tùy chọn).
7. Kết quả: Table (1) ▷----(*) Booking.

**Ví dụ 2: Vẽ quan hệ Customer → Booking (1-n, Association)**
1. Tạo class `<<entity>> Customer` và `<<entity>> Booking`.
2. Chọn công cụ **Association** từ palette Relationships.
3. Click vào class Customer → kéo đến class Booking.
4. Click vào đường kết nối → Properties → set Source Multiplicity = 1, Target Multiplicity = *.
5. Đặt tên association: "makes" (tùy chọn).
6. Kết quả: Customer (1) ▷----(*) Booking.

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công -> HomeFrm xuất hiện:
  một tùy chọn đặt bàn -> subBookTable

Chọn Book a table -> BookTableFrm xuất hiện:
  ô nhập ngày đặt -> inDate
  ô nhập giờ đặt -> inTime
  ô nhập số khách -> inNumGuests
  nút tìm bàn trống -> subSearchTable
  bảng danh sách bàn trống (clickable) -> outsubListTable
  ô nhập tên khách hàng -> inCustomerName
  nút tìm khách hàng -> subSearchCustomer
  bảng danh sách khách hàng (clickable) -> outsubListCustomer
  nút thêm khách mới -> subAddCustomer
  vùng hiển thị thông tin đặt bàn -> outBookingInfo
  nút xác nhận -> subConfirm

Nhập ngày, giờ, số khách và nhấn Search -> hệ thống tìm bàn trống -> cần phương thức:
  tên: getAvailableTables()
  đầu vào: date (Date), time (String), numGuests (int)
  đầu ra: list of Table
  gán cho entity class: Table.

Nhập tên khách hàng và nhấn Search -> hệ thống tìm khách hàng -> cần phương thức:
  tên: searchCustomerByName()
  đầu vào: name (String)
  đầu ra: list of Customer
  gán cho entity class: Customer.

Nhấn Confirm -> hệ thống lưu đặt bàn -> cần phương thức:
  tên: addBooking()
  đầu vào: booking (Booking)
  đầu ra: boolean
  gán cho entity class: Booking.

### Summary
View classes: HomeFrm, BookTableFrm
Methods: getAvailableTables(), searchCustomerByName(), addBooking()

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

**BookTableFrm:**
- `inDate`: ô nhập ngày đặt (JTextField, format dd/MM/yyyy)
- `inTime`: ô nhập giờ đặt (JTextField, format HH:mm)
- `inNumGuests`: ô nhập số khách (JTextField)
- `subSearchTable`: nút Search bàn trống (JButton)
- `outsubListTable`: bảng danh sách bàn trống, click để chọn (JTable)
- `inCustomerName`: ô nhập tên khách hàng (JTextField)
- `subSearchCustomer`: nút Search khách hàng (JButton)
- `outsubListCustomer`: bảng danh sách khách hàng, click để chọn (JTable)
- `subAddCustomer`: nút thêm khách hàng mới (JButton)
- `outBookingInfo`: vùng hiển thị thông tin đặt bàn (JLabel/JPanel)
- `subConfirm`: nút Confirm (JButton)

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Table | Model | id: int, code: String, name: String, maxGuests: int, description: String |
| Customer | Model | id: int, code: String, name: String, phone: String, email: String, address: String |
| Booking | Model | id: int, bookingDate: Date, bookingTime: String, numGuests: int, status: String, table: Table (object attribute, FK), customer: Customer (object attribute, FK), user: User (object attribute, FK) |
| User | Model | id: int, username: String, password: String, role: String |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| TableDAO | `getAvailableTables(date, time, numGuests): List<Table>` | Tìm bàn trống theo ngày, giờ, số khách |
| CustomerDAO | `searchCustomerByName(name): List<Customer>` | Tìm khách hàng theo tên |
| CustomerDAO | `addCustomer(customer): boolean` | Thêm khách hàng mới |
| BookingDAO | `addBooking(booking): boolean` | Lưu thông tin đặt bàn |

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

**tblBooking:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PRIMARY KEY |
| tableID | int | FOREIGN KEY → tblTable(ID), NOT NULL |
| customerID | int | FOREIGN KEY → tblCustomer(ID), NOT NULL |
| userID | int | FOREIGN KEY → tblUser(ID), NOT NULL |
| bookingDate | date | NOT NULL |
| bookingTime | varchar(10) | NOT NULL |
| numGuests | int | NOT NULL |
| status | varchar(20) | NOT NULL, DEFAULT 'confirmed' |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

#### Các bước vẽ tổng quan

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo View class boxes (hình chữ nhật 3 ngăn) cho LoginFrm, HomeFrm, BookTableFrm.
3. Tạo DAO class boxes cho UserDAO, TableDAO, CustomerDAO, BookingDAO.
4. Tạo Entity class boxes cho Table, Customer, Booking, User.
5. Vẽ mối quan hệ (relationship) giữa các class.
6. Ghi multiplicity và role name cho mỗi mối quan hệ.

#### Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được vẽ dưới dạng hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<boundary>>`, `<<control>>`, hoặc `<<entity>>` phía trên tên class. Ví dụ: `<<boundary>> BookTableFrm`, `<<control>> BookingDAO`, `<<entity>> Booking`.
- **Ngăn 2 (thuộc tính):** Liệt kê các thuộc tính theo format `-attributeName: Type`. Ví dụ: `-inDate: JTextField`, `-bookingDate: Date`.
- **Ngăn 3 (phương thức):** Liệt kê các phương thức theo format `+methodName(params): ReturnType`. Ví dụ: `+addBooking(booking: Booking): boolean`.

#### Bảng chi tiết View classes

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| LoginFrm | <<boundary>> | -inUsername: JTextField, -inPassword: JPasswordField, -subLogin: JButton | |
| HomeFrm | <<boundary>> | -subOrder: JButton, -subBookTable: JButton, -subPayment: JButton | |
| BookTableFrm | <<boundary>> | -inDate: JTextField, -inTime: JTextField, -inNumGuests: JTextField, -subSearchTable: JButton, -outsubListTable: JTable, -inCustomerName: JTextField, -subSearchCustomer: JButton, -outsubListCustomer: JTable, -subAddCustomer: JButton, -outBookingInfo: JLabel, -subConfirm: JButton | |

#### Bảng chi tiết DAO classes

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| UserDAO | <<control>> | | +checkLogin(username: String, password: String): boolean |
| TableDAO | <<control>> | | +getAvailableTables(date: Date, time: String, numGuests: int): List<Table> |
| CustomerDAO | <<control>> | | +searchCustomerByName(name: String): List<Customer>, +addCustomer(customer: Customer): boolean |
| BookingDAO | <<control>> | | +addBooking(booking: Booking): boolean |

#### Bảng chi tiết Entity classes

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Table | <<entity>> | -id: int, -code: String, -name: String, -maxGuests: int, -description: String | |
| Customer | <<entity>> | -id: int, -code: String, -name: String, -phone: String, -email: String, -address: String | |
| Booking | <<entity>> | -id: int, -bookingDate: Date, -bookingTime: String, -numGuests: int, -status: String, -table: Table, -customer: Customer, -user: User | |
| User | <<entity>> | -id: int, -username: String, -password: String, -role: String | |

#### Cách vẽ quan hệ

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| BookTableFrm | TableDAO | Dependency (dashed, ▷) | | Form sử dụng DAO để tìm bàn trống |
| BookTableFrm | CustomerDAO | Dependency (dashed, ▷) | | Form sử dụng DAO để tìm khách hàng |
| BookTableFrm | BookingDAO | Dependency (dashed, ▷) | | Form sử dụng DAO để lưu đặt bàn |
| Table | Booking | Association (▷) | 1 - n | Một bàn có nhiều lần đặt |
| Customer | Booking | Association (▷) | 1 - n | Một khách hàng đặt bàn nhiều lần |
| User | Booking | Association (▷) | 1 - n | Một nhân viên xử lý nhiều đặt bàn |

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo các lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `BookTableFrm`, `UserDAO`, `TableDAO`, `CustomerDAO`, `BookingDAO`.
2. Vẽ message flow theo thứ tự trong bảng bên dưới.
3. Sử dụng `alt` fragment cho nhánh: khách hàng tồn tại / thêm mới.

### Sequence Diagram (ASCII)

```
Staff       LoginFrm   UserDAO  HomeFrm  BookTableFrm  TableDAO  CustomerDAO  BookingDAO
  |             |          |       |           |           |           |            |
  |--login----->|          |       |           |           |           |            |
  |             |--checkLogin()-->|           |           |           |            |
  |             |          |--query DB        |           |           |            |
  |             |          |<-return true     |           |           |            |
  |             |<--true---|       |           |           |           |            |
  |             |--open HomeFrm-->|           |           |           |            |
  |             |          |       |           |           |           |            |
  |--select Book a table-->|       |           |           |           |            |
  |             |          |       |--open BookTableFrm--->|           |            |
  |             |          |       |           |           |           |            |
  |--enter date, time, guests----->|           |           |           |            |
  |--click Search Table--->        |           |           |           |            |
  |             |          |       |           |--getAvailableTables()->|            |
  |             |          |       |           |           |--query DB |            |
  |             |          |       |           |<--List<Table>|        |            |
  |             |          |       |           |--display table list   |            |
  |             |          |       |           |   ^       |           |            |
  |             |          |       |           |   |       |           |            |
  |--select table "B03"-->|        |           |           |           |            |
  |             |          |       |           |           |           |            |
  |--enter "Nguyen Van A"-------> |           |           |           |            |
  |--click Search Customer------->|           |           |           |            |
  |             |          |       |           |--searchCustomerByName("Nguyen Van A")
  |             |          |       |           |           |           |--query DB   |
  |             |          |       |           |           |           |<-return----|
  |             |          |       |           |<--List<Customer>|     |            |
  |             |          |       |           |--display customer list |            |
  |             |          |       |           |   ^       |           |            |
  |             |          |       |           |           |           |            |
  |--select customer------>|       |           |           |           |            |
  |             |          |       |           |--display booking info  |            |
  |             |          |       |           |   ^       |           |            |
  |             |          |       |           |           |           |            |
  |--click Confirm-------->|       |           |           |           |            |
  |             |          |       |           |--new Booking(table, customer, user, date, time, numGuests)
  |             |          |       |           |           |           |            |
  |             |          |       |           |--addBooking(booking)-------------->|
  |             |          |       |           |           |           |     |--INSERT DB
  |             |          |       |           |           |           |     |<-return
  |             |          |       |           |<--true-----|           |            |
  |             |          |       |           |           |           |            |
  |             |          |       |           |--show "Dat ban thanh cong"         |
  |<--success---|          |       |           |           |           |            |
```

### Bảng chi tiết các bước

| # | Message | Từ | Đến | Mô tả |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhập username/password, nhấn Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Gọi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy vấn tblUser |
| 4 | return true | UserDAO | LoginFrm | Trả về true nếu đăng nhập hợp lệ |
| 5 | open | LoginFrm | HomeFrm | Mở giao diện Home |
| 6 | select Book a table | Staff | HomeFrm | Staff chọn chức năng đặt bàn |
| 7 | open | HomeFrm | BookTableFrm | Mở form đặt bàn |
| 8 | enter date/time/guests | Staff | BookTableFrm | Staff nhập ngày 15/07/2026, giờ 18:00, số khách 4 |
| 9 | click SearchTable | Staff | BookTableFrm | Staff nhấn nút tìm bàn trống |
| 10 | getAvailableTables() | BookTableFrm | TableDAO | Gọi TableDAO.getAvailableTables("15/07/2026", "18:00", 4) |
| 11 | query DB | TableDAO | Database | Truy vấn tblTable JOIN tblBooking |
| 12 | return List<Table> | TableDAO | BookTableFrm | Trả về danh sách bàn trống [B01, B03, B05] |
| 13 | displayTable() | BookTableFrm | BookTableFrm | Hiển thị danh sách bàn trống (self-call) |
| 14 | selectRow("B03") | Staff | BookTableFrm | Staff chọn bàn B03 |
| 15 | enter "Nguyen Van A" | Staff | BookTableFrm | Staff nhập tên khách hàng |
| 16 | click SearchCustomer | Staff | BookTableFrm | Staff nhấn nút tìm khách hàng |
| 17 | searchCustomerByName() | BookTableFrm | CustomerDAO | Gọi CustomerDAO.searchCustomerByName("Nguyen Van A") |
| 18 | query DB | CustomerDAO | Database | Truy vấn tblCustomer |
| 19 | return List<Customer> | CustomerDAO | BookTableFrm | Trả về danh sách khách hàng [KH01] |
| 20 | displayCustomerList() | BookTableFrm | BookTableFrm | Hiển thị danh sách khách hàng (self-call) |
| 21 | selectRow("KH01") | Staff | BookTableFrm | Staff chọn khách hàng KH01 |
| 22 | displayBookingInfo() | BookTableFrm | BookTableFrm | Hiển thị thông tin đặt bàn đầy đủ (self-call) |
| 23 | click Confirm | Staff | BookTableFrm | Staff nhấn nút Confirm |
| 24 | new Booking() | BookTableFrm | Booking | Tạo đối tượng Booking(table=B03, customer=KH01, user=staff01, date=15/07/2026, time=18:00, numGuests=4) |
| 25 | addBooking() | BookTableFrm | BookingDAO | Gọi BookingDAO.addBooking(booking) |
| 26 | INSERT DB | BookingDAO | Database | INSERT INTO tblBooking |
| 27 | return true | BookingDAO | BookTableFrm | Trả về true |
| 28 | show success | BookTableFrm | Staff | Hiển thị "Đặt bàn thành công", quay về Home |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Book a table | Đặt bàn thành công |
| TC02 | Book a table | Không có bàn trống |
| TC03 | Book a table | Khách hàng chưa có trong hệ thống (thêm mới) |
| TC04 | Book a table | Chưa chọn bàn đã nhấn Confirm |
| TC05 | Book a table | Đặt bàn trùng bàn, trùng ngày giờ |

### TC01: Đặt bàn thành công

**Purpose:** Kiểm tra quy trình đặt bàn hoàn chỉnh từ tìm bàn trống, chọn bàn, tìm khách hàng đến xác nhận lưu đặt bàn.

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
| 4 | B04 | Bàn thường 2 | 4 | Tầng 2 |
| 5 | B05 | Bàn thường 3 | 4 | Tầng 2 |
| 6 | B06 | Bàn ngoài trời 1 | 8 | Sân vườn |

**tblCustomer:**
| ID | code | name | phone | email | address |
|----|------|------|-------|-------|---------|
| 1 | KH01 | Nguyen Van A | 0912345678 | nva@gmail.com | Ha Noi |
| 2 | KH02 | Tran Thi B | 0987654321 | ttb@gmail.com | HCM |
| 3 | KH03 | Le Van C | 0901234567 | lvc@gmail.com | Da Nang |

**tblBooking:**
| ID | tableID | customerID | userID | bookingDate | bookingTime | numGuests | status |
|----|---------|------------|--------|-------------|-------------|-----------|--------|
| 1 | 1 | 2 | 1 | 15/07/2026 | 12:00 | 3 | confirmed |
| 2 | 2 | 3 | 1 | 15/07/2026 | 18:00 | 4 | confirmed |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Giao diện Home hiển thị |
| 3 | Nhấn chọn **Book a table** | Giao diện tìm bàn trống: ô ngày, giờ, số khách, nút Search |
| 4 | Nhập ngày = 15/07/2026, giờ = 18:00, số khách = 4, nhấn Search | Bảng hiển thị bàn trống: B01 (6 khách), B03 (4 khách), B04 (4 khách), B05 (4 khách), B06 (8 khách). Không hiển thị B02 (đã đặt lúc 18:00 cùng ngày) |
| 5 | Chọn bàn "B03 — Bàn thường 1" | Bàn B03 được chọn. Giao diện nhập thông tin KH xuất hiện |
| 6 | Nhập "Nguyen Van A" vào ô tên KH, nhấn Search | Bảng hiển thị: KH01 — Nguyen Van A — 0912345678 — nva@gmail.com — Ha Noi |
| 7 | Nhấn chọn dòng "KH01 — Nguyen Van A" | Hiển thị thông tin đầy đủ: Bàn B03 + KH Nguyen Van A + 15/07/2026 18:00 + 4 khách |
| 8 | Kiểm tra DB trước khi Confirm | tblBooking: vẫn chỉ có 2 dòng (ID=1, ID=2), chưa có dòng mới |
| 9 | Nhấn **Confirm** | Thông báo "Đặt bàn thành công", quay về Home |

### Database sau khi test

**tblBooking:** (thêm 1 dòng mới, các dòng cũ không thay đổi)
| ID | tableID | customerID | userID | bookingDate | bookingTime | numGuests | status |
|----|---------|------------|--------|-------------|-------------|-----------|--------|
| 3 | 3 | 1 | 1 | 15/07/2026 | 18:00 | 4 | confirmed |

**tblUser, tblTable, tblCustomer:** (không thay đổi)
