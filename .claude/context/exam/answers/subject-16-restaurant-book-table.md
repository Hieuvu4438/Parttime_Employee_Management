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
| 5 | Giao diện tìm bàn trống xuất hiện: ô nhập ngày đặt (txtDate), ô nhập giờ đặt (txtTime), ô nhập số khách (txtNumGuests), nút Search (btnSearchTable). |
| 6 | Staff nhập ngày 15/07/2026, giờ 18:00, số khách 4, nhấn Search. |
| 7 | Hệ thống hiển thị danh sách bàn trống tại thời điểm đó: bảng gồm cột Mã bàn, Tên bàn, Số khách tối đa, Mô tả. Ví dụ: B01 — Bàn VIP 1 — 6 khách — Tầng 1; B03 — Bàn thường 1 — 4 khách — Tầng 2; B05 — Bàn thường 3 — 4 khách — Tầng 2. |
| 8 | Staff hỏi khách và chọn bàn "B03 — Bàn thường 1, tối đa 4 khách". |
| 9 | Giao diện nhập thông tin khách hàng xuất hiện: ô nhập mã KH (txtCustomerCode), ô nhập tên KH (txtCustomerName), ô nhập SĐT (txtPhone), ô nhập email (txtEmail), ô nhập địa chỉ (txtAddress), nút Search (btnSearchCustomer), nút Add New (btnAddCustomer). |
| 10 | Staff hỏi khách, nhập "Nguyen Van A" vào ô tên, nhấn Search. |
| 11 | Hệ thống hiển thị danh sách khách hàng có tên chứa từ khóa: bảng gồm cột Mã KH, Tên, SĐT, Email, Địa chỉ. Ví dụ: KH01 — Nguyen Van A — 0912345678 — nva@gmail.com — Ha Noi. |
| 12 | Staff nhấn chọn dòng đúng với khách hàng hiện tại "KH01 — Nguyen Van A". |
| 13 | Hệ thống hiển thị thông tin đầy đủ: Bàn B03 + Khách hàng Nguyen Van A + Ngày 15/07/2026 + Giờ 18:00 + Số khách 4. |
| 14 | Staff xác nhận với khách, nhấn **Confirm** (btnConfirm). |
| 15 | Hệ thống lưu thông tin đặt bàn vào database (tblBooking). |
| 16 | Hệ thống thông báo "Dat ban thanh cong". |

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
+------------------+       +------------------+
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

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Restaurant → Table | 1-n | Một nhà hàng có nhiều bàn |
| Table → Booking | 1-n | Một bàn có nhiều lần đặt |
| Customer → Booking | 1-n | Một khách hàng đặt bàn nhiều lần |
| User → Booking | 1-n | Một nhân viên xử lý nhiều đặt bàn |

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
| Booking | Model | id: int, tableId: int, customerId: int, userId: int, bookingDate: Date, bookingTime: String, numGuests: int, status: String |
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
| tableID | int | FOREIGN KEY → tblTable(ID) |
| customerID | int | FOREIGN KEY → tblCustomer(ID) |
| userID | int | FOREIGN KEY → tblUser(ID) |
| bookingDate | date | NOT NULL |
| bookingTime | varchar(10) | NOT NULL |
| numGuests | int | NOT NULL |
| status | varchar(20) | NOT NULL |

### Visual Paradigm — Hướng dẫn vẽ Static Design Class Diagram

1. Tạo package `view.booking`: chứa LoginFrm, HomeFrm, BookTableFrm.
2. Tạo package `model`: chứa Table, Customer, Booking, User.
3. Tạo package `dao`: chứa UserDAO, TableDAO, CustomerDAO, BookingDAO.
4. Vẽ association từ BookTableFrm → TableDAO, BookTableFrm → CustomerDAO, BookTableFrm → BookingDAO.
5. Vẽ dependency từ DAO classes → Entity classes (dashed arrow).
6. Thêm kiểu trả về cho các phương thức DAO.

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
  |             |          |       |           |           |           |            |
  |--select table "B03"-->|        |           |           |           |            |
  |             |          |       |           |           |           |            |
  |--enter "Nguyen Van A"-------> |           |           |           |            |
  |--click Search Customer------->|           |           |           |            |
  |             |          |       |           |--searchCustomerByName("Nguyen Van A")
  |             |          |       |           |           |           |--query DB   |
  |             |          |       |           |           |           |<-return----|
  |             |          |       |           |<--List<Customer>|     |            |
  |             |          |       |           |--display customer list |            |
  |             |          |       |           |           |           |            |
  |--select customer------>|       |           |           |           |            |
  |             |          |       |           |--display booking info  |            |
  |             |          |       |           |           |           |            |
  |--click Confirm-------->|       |           |           |           |            |
  |             |          |       |           |--addBooking(booking)-------------->|
  |             |          |       |           |           |           |     |--INSERT DB
  |             |          |       |           |<--true-----|           |            |
  |             |          |       |           |           |           |            |
  |             |          |       |           |--show "Dat ban thanh cong"         |
  |<--success---|          |       |           |           |           |            |
```

### Bảng chi tiết các bước

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|------|---------|---------|
| 1 | Staff | LoginFrm | actionPerformed("Login") | Nhập username/password |
| 2 | LoginFrm | UserDAO | checkLogin("staff01", "******") | Kiểm tra đăng nhập |
| 3 | UserDAO | LoginFrm | return true | Thành công |
| 4 | LoginFrm | HomeFrm | new HomeFrm().setVisible(true) | Mở giao diện chính |
| 5 | Staff | HomeFrm | actionPerformed("Book a table") | Chọn đặt bàn |
| 6 | HomeFrm | BookTableFrm | new BookTableFrm().setVisible(true) | Mở form đặt bàn |
| 7 | Staff | BookTableFrm | setText(txtDate, "15/07/2026") | Nhập ngày |
| 8 | Staff | BookTableFrm | setText(txtTime, "18:00") | Nhập giờ |
| 9 | Staff | BookTableFrm | setText(txtNumGuests, "4") | Nhập số khách |
| 10 | Staff | BookTableFrm | actionPerformed("SearchTable") | Nhấn tìm bàn |
| 11 | BookTableFrm | TableDAO | getAvailableTables("15/07/2026", "18:00", 4) | Tìm bàn trống |
| 12 | TableDAO | BookTableFrm | return List<Table> [B01, B03, B05] | Trả về 3 bàn trống |
| 13 | BookTableFrm | BookTableFrm | displayTable(listTable) | Hiển thị danh sách bàn |
| 14 | Staff | BookTableFrm | selectRow("B03") | Chọn bàn B03 |
| 15 | Staff | BookTableFrm | setText(txtCustomerName, "Nguyen Van A") | Nhập tên KH |
| 16 | Staff | BookTableFrm | actionPerformed("SearchCustomer") | Nhấn tìm KH |
| 17 | BookTableFrm | CustomerDAO | searchCustomerByName("Nguyen Van A") | Tìm khách hàng |
| 18 | CustomerDAO | BookTableFrm | return List<Customer> [KH01] | Trả về danh sách KH |
| 19 | BookTableFrm | BookTableFrm | displayCustomerList(list) | Hiển thị danh sách KH |
| 20 | Staff | BookTableFrm | selectRow("KH01") | Chọn khách hàng |
| 21 | BookTableFrm | BookTableFrm | displayBookingInfo(table, customer, date, time) | Hiển thị thông tin đầy đủ |
| 22 | Staff | BookTableFrm | actionPerformed("Confirm") | Nhấn Confirm |
| 23 | BookTableFrm | new Booking() | Booking(tableId=3, customerId=1, userId=1, date, time, 4) | Tạo đối tượng Booking |
| 24 | BookTableFrm | BookingDAO | addBooking(booking) | Lưu đặt bàn |
| 25 | BookingDAO | BookTableFrm | return true | Thành công |
| 26 | BookTableFrm | Staff | showMessage("Dat ban thanh cong") | Thông báo |

---

## Câu 5: Blackbox Testcase

### Bảng tổng hợp test case

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Book a table | Đặt bàn thành công |
| TC02 | Book a table | Không có bàn trống |
| TC03 | Book a table | Khách hàng chưa có trong hệ thống (thêm mới) |
| TC04 | Book a table | Chưa chọn bàn đã nhấn Confirm |

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
| 4 | Nhập ngày = 15/07/2026, giờ = 18:00, số khách = 4, nhấn Search | Bảng hiển thị bàn trống: B03 (4 khách), B04 (4 khách), B05 (4 khách), B06 (8 khách). Không hiển thị B01 (đã đặt 12:00), B02 (đã đặt 18:00) |
| 5 | Chọn bàn "B03 — Bàn thường 1" | Bàn B03 được chọn. Giao diện nhập thông tin KH xuất hiện |
| 6 | Nhập "Nguyen Van A" vào ô tên KH, nhấn Search | Bảng hiển thị: KH01 — Nguyen Van A — 0912345678 — nva@gmail.com — Ha Noi |
| 7 | Nhấn chọn dòng "KH01 — Nguyen Van A" | Hiển thị thông tin đầy đủ: Bàn B03 + KH Nguyen Van A + 15/07/2026 18:00 + 4 khách |
| 8 | Nhấn **Confirm** | Thông báo "Dat ban thanh cong" |

### Database sau khi test

**tblBooking:** (thêm 1 dòng)
| ID | tableID | customerID | userID | bookingDate | bookingTime | numGuests | status |
|----|---------|------------|--------|-------------|-------------|-----------|--------|
| 1 | 1 | 2 | 1 | 15/07/2026 | 12:00 | 3 | confirmed |
| 2 | 2 | 3 | 1 | 15/07/2026 | 18:00 | 4 | confirmed |
| 3 | 3 | 1 | 1 | 15/07/2026 | 18:00 | 4 | confirmed |
