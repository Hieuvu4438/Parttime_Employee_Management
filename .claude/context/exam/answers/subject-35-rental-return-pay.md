# Subject No. 35 — Book Rental — Module "Return and pay"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Trả sách và thanh toán

### Mô tả ngôn ngữ tự nhiên

Khi khách hàng trả sách, nhân viên chọn menu tìm danh sách sách đang mượn theo tên khách hàng. Nhân viên nhập tên khách hàng và tìm kiếm, danh sách khách hàng hiển thị, nhân viên chọn đúng khách hàng. Hệ thống hiển thị danh sách đầu sách đang mượn của khách hàng đó, mỗi dòng gồm: mã sách, tên sách, ngày mượn, giá thuê, số tiền thuê tính đến ngày trả, và checkbox để chọn trả. Nhân viên tick chọn sách trả, nhập trạng thái sách và tiền phạt (nếu có), nhấn nút Payment. Hệ thống hiển thị hóa đơn gồm thông tin khách hàng, danh sách sách trả, và tổng tiền thanh toán. Nhân viên nhấn Confirm để hệ thống cập nhật database.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Nhân viên (Staff) đăng nhập hệ thống, chọn chức năng **Return and pay**. |
| 2 | Hệ thống hiển thị giao diện ReturnBookFrm: ô nhập tên khách hàng (`inCustomerName`), nút Search (`subSearchCustomer`). |
| 3 | Staff nhập "Nguyen Van A" vào ô `inCustomerName`, nhấn nút **Search**. |
| 4 | Hệ thống tìm kiếm trong bảng `tblCustomer`, hiển thị danh sách khách hàng phù hợp trong bảng `outsubListCustomer` (cột: mã KH, tên KH, CMND, điện thoại, địa chỉ). |
| 5 | Staff click chọn đúng khách hàng "Nguyen Van A" (customerId = 1). |
| 6 | Hệ thống truy vấn danh sách sách đang mượn của khách hàng từ `tblRentalSlipDetail` (chưa trả), hiển thị bảng `outListBorrowedBooks` gồm các cột: mã sách, tên sách, ngày mượn, giá thuê/ngày, số tiền thuê tính đến ngày trả, checkbox chọn trả. |
| 7 | Staff tick checkbox chọn sách "Lap trinh Java" (mượn ngày 01/06/2026, giá 5,000đ/ngày, số ngày = 7, tiền thuê = 35,000đ). Nhập trạng thái "binh thuong", tiền phạt = 0. |
| 8 | Staff tick checkbox chọn sách "Co so du lieu" (mượn ngày 01/06/2026, giá 4,000đ/ngày, số ngày = 7, tiền thuê = 28,000đ). Nhập trạng thái "binh thuong", tiền phạt = 0. |
| 9 | Staff nhấn nút **Payment** (`subPayment`). |
| 10 | Hệ thống hiển thị hóa đơn (`outInvoice`): thông tin KH "Nguyen Van A", ngày trả "08/06/2026", danh sách sách trả (tên, tác giả, NXB, năm, ngày mượn, ngày trả, tiền thuê, tiền phạt), tổng tiền thanh toán = 35,000 + 28,000 = 63,000đ. |
| 11 | Staff nhấn nút **Confirm** (`subConfirm`). Hệ thống cập nhật `tblRentalSlipDetail` (thêm returnDate, status, fine) và lưu thông tin thanh toán vào `tblPayment`. Hiển thị thông báo "Tra sach va thanh toan thanh cong". |

### Ngoại lệ

| Bước | Ngoại lệ | Xử lý |
|------|----------|-------|
| 4 | Không tìm thấy khách hàng nào | Hệ thống hiển thị "Khong tim thay khach hang". Staff nhập lại tên khác. |
| 6 | Khách hàng không có sách đang mượn | Hệ thống hiển thị "Khach hang khong co sach dang muon". Nút Payment bị disable. |
| 7 | Staff không tick chọn sách nào | Nút Payment bị disable, Staff phải chọn ít nhất 1 sách. |
| 9 | Staff chọn trả một phần (không trả hết) | Hệ thống chỉ cập nhật sách được chọn, các sách khác vẫn giữ trạng thái đang mượn. |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê sách. **Đầu sách** (BookTitle) có mã, tên, tác giả, nhà xuất bản, năm xuất bản, giá thuê/ngày, số lượng. **Khách hàng** (Customer) có mã, tên, CMND, điện thoại, địa chỉ. Mỗi lần thuê tạo **phiếu mượn** (RentalSlip) với ngày mượn và tổng số sách. Mỗi phiếu mượn có nhiều **chi tiết phiếu mượn** (RentalSlipDetail), mỗi chi tiết liên kết đến một đầu sách, lưu giá thuê, và khi trả cập nhật ngày trả, trạng thái, tiền phạt. Mỗi lần trả tạo một **phiếu thanh toán** (Payment) ghi nhận tổng tiền. **Nhân viên** (User) là người xử lý.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| BookTitle | Entity | Đối tượng chính, có thuộc tính riêng |
| Customer | Entity | Đối tượng chính, có thuộc tính riêng |
| RentalSlip | Entity | Đối tượng phiếu mượn |
| RentalSlipDetail | Entity | Đối tượng chi tiết phiếu mượn (bao gồm thông tin trả) |
| Payment | Entity | Đối tượng phiếu thanh toán |
| User | Entity | Đối tượng nhân viên |

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1: Staff đăng nhập, chọn Return and pay. View class: **HomeFrm**, **ReturnBookFrm**.
Bước 2: Giao diện ReturnBookFrm: ô nhập tên KH, nút Search. UI: `inCustomerName` (JTextField — nhập tên KH), `subSearchCustomer` (JButton — tìm KH).
Bước 3-5: Staff nhập tên, nhấn Search, danh sách KH hiển thị, click chọn KH. UI: `outsubListCustomer` (JTable — danh sách KH, click chọn được).
Bước 6: Hệ thống hiển thị danh sách sách đang mượn. UI: `outListBorrowedBooks` (JTable — danh sách sách đang mượn, có checkbox).
Bước 7-8: Staff tick chọn sách, nhập trạng thái và tiền phạt. UI: `inStatus` (JTextField — nhập trạng thái sách), `inFine` (JTextField — nhập tiền phạt).
Bước 9: Staff nhấn Payment. UI: `subPayment` (JButton — hiển thị hóa đơn).
Bước 10: Hệ thống hiển thị hóa đơn. UI: `outInvoice` (JTextArea — hóa đơn thanh toán).
Bước 11: Staff nhấn Confirm. UI: `subConfirm` (JButton — xác nhận thanh toán).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Return and pay |
| ReturnBookFrm | Form | Giao diện trả sách và thanh toán |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inCustomerName` | JTextField | ReturnBookFrm | Ô nhập tên khách hàng để tìm kiếm |
| `subSearchCustomer` | JButton | ReturnBookFrm | Nút Search tìm kiếm khách hàng |
| `outsubListCustomer` | JTable | ReturnBookFrm | Bảng danh sách khách hàng, click để chọn |
| `outListBorrowedBooks` | JTable | ReturnBookFrm | Bảng sách đang mượn (mã, tên, ngày mượn, giá thuê, tiền thuê, checkbox) |
| `inStatus` | JTextField | ReturnBookFrm | Ô nhập trạng thái sách (binh thuong / hu hong) |
| `inFine` | JTextField | ReturnBookFrm | Ô nhập tiền phạt |
| `subPayment` | JButton | ReturnBookFrm | Nút Payment — hiển thị hóa đơn |
| `outInvoice` | JTextArea | ReturnBookFrm | Khu vực hiển thị hóa đơn thanh toán |
| `subConfirm` | JButton | ReturnBookFrm | Nút Confirm — xác nhận thanh toán |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `searchCustomerByName()` | name | List\<Customer\> | Customer |
| `getUnreturnedDetails()` | customerId | List\<RentalSlipDetail\> | RentalSlipDetail |
| `updateReturn()` | detailId, returnDate, status, fine | boolean | RentalSlipDetail |
| `insertPayment()` | payment (rentalSlipId, userId, paymentDate, totalAmount) | boolean | Payment |

**Tong hop:**

- View classes: HomeFrm, ReturnBookFrm
- Methods: searchCustomerByName(), getUnreturnedDetails(), updateReturn(), insertPayment()

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu | Mô tả |
|-----------|----------|------|-------|
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu mượn có nhiều chi tiết |
| BookTitle | RentalSlipDetail | 1 : N | Một đầu sách xuất hiện trong nhiều chi tiết |
| RentalSlip | Payment | 1 : N | Một phiếu mượn có thể có nhiều phiếu thanh toán (trả từng phần) |
| User | RentalSlip | 1 : N | Một nhân viên xử lý nhiều phiếu mượn |
| User | Payment | 1 : N | Một nhân viên lập nhiều phiếu thanh toán |

### Bảng thuộc tính entity

| Entity | Thuộc tính |
|--------|-----------|
| BookTitle | bookTitleId (PK), code, name, author, publisher, pubYear, rentalPrice (double), quantity (int) |
| Customer | customerId (PK), code, name, idCard, phone, address |
| RentalSlip | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate (Date), totalBooks (int) |
| RentalSlipDetail | rentalSlipDetailId (PK), rentalSlipId (FK), bookTitleId (FK), rentalPrice (double), returnDate (Date), status (String), fine (double) |
| Payment | paymentId (PK), rentalSlipId (FK), userId (FK), paymentDate (Date), totalAmount (double) |
| User | userId (PK), username, password, role |

### ASCII Class Diagram

```
+------------------+         +------------------+
|    BookTitle     |         |     Customer     |
+------------------+         +------------------+
| bookTitleId (PK) |         | customerId (PK)  |
| code             |         | code             |
| name             |         | name             |
| author           |         | idCard           |
| publisher        |         | phone            |
| pubYear          |         | address          |
| rentalPrice      |         +------------------+
| quantity         |                  |
+------------------+                  | 1
        |                             |
        | 1                           | N
        |                    +------------------+
        | N                  |    RentalSlip    |
+-------------------------+  +------------------+
|   RentalSlipDetail      |  | rentalSlipId (PK)|
+-------------------------+  | customerId (FK)  |
| rentalSlipDetailId (PK) |  | userId (FK)      |
| rentalSlipId (FK)       |  | rentalDate       |
| bookTitleId (FK)        |  | totalBooks       |
| rentalPrice             |  +------------------+
| returnDate              |          | 1
| status                  |          |
| fine                    |          | N
+-------------------------+  +------------------+
                             |     Payment      |
                             +------------------+
                             | paymentId (PK)   |
                             | rentalSlipId (FK)|
                             | userId (FK)      |
                             | paymentDate      |
                             | totalAmount      |
                             +------------------+
                                     |
                                     | N
                                     |
                              +------------------+
                              |      User        |
                              +------------------+
                              | userId (PK)      |
                              | username         |
                              | password         |
                              | role             |
                              +------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác |
|------|----------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class box (hình chữ nhật 3 ngăn): BookTitle, Customer, RentalSlip, RentalSlipDetail, Payment, User |
| 3 | Tạo các view class box từ giao diện: HomeFrm, ReturnBookFrm |
| 4 | Vẽ các đường quan hệ (association) giữa các class |
| 5 | Ghi multiplicity và role name cho mỗi quan hệ |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

- **Ngăn 1 (tên class):** Ghi stereotype `<<entity>>` hoặc `<<boundary>>` rồi đến tên class. Ví dụ: `<<entity>> Payment`, `<<boundary>> ReturnBookFrm`
- **Ngăn 2 (thuộc tính):** Ghi từng thuộc tính theo format `-tenThuocTinh: KieuDuLieu`. Dùng `-` cho private. Ví dụ: `-paymentId: int`, `-totalAmount: double`
- **Ngăn 3 (phương thức):** Ghi từng phương thức theo format `+tenPhuongThuc(thamSo): KieuTraVe`. Dùng `+` cho public. Ví dụ: `+getUnreturnedDetails(customerId: int): List<RentalSlipDetail>`

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| BookTitle | `<<entity>>` | `-bookTitleId: int`, `-code: String`, `-name: String`, `-author: String`, `-publisher: String`, `-pubYear: int`, `-rentalPrice: double`, `-quantity: int` | — |
| Customer | `<<entity>>` | `-customerId: int`, `-code: String`, `-name: String`, `-idCard: String`, `-phone: String`, `-address: String` | `+searchCustomerByName(name: String): List<Customer>` |
| RentalSlip | `<<entity>>` | `-rentalSlipId: int`, `-customerId: int`, `-userId: int`, `-rentalDate: Date`, `-totalBooks: int` | — |
| RentalSlipDetail | `<<entity>>` | `-rentalSlipDetailId: int`, `-rentalSlipId: int`, `-bookTitleId: int`, `-rentalPrice: double`, `-returnDate: Date`, `-status: String`, `-fine: double` | `+getUnreturnedDetails(customerId: int): List<RentalSlipDetail>`, `+updateReturn(detailId: int, returnDate: Date, status: String, fine: double): boolean` |
| Payment | `<<entity>>` | `-paymentId: int`, `-rentalSlipId: int`, `-userId: int`, `-paymentDate: Date`, `-totalAmount: double` | `+insertPayment(p: Payment): boolean` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

**HomeFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `subReturnPay` | sub | JButton | Nút mở chức năng Return and pay |

**ReturnBookFrm (`<<boundary>>`):**

| UI Element | Prefix | Kiểu | Mô tả |
|------------|--------|------|-------|
| `inCustomerName` | in | JTextField | Ô nhập tên khách hàng |
| `subSearchCustomer` | sub | JButton | Nút Search tìm khách hàng |
| `outsubListCustomer` | outsub | JTable | Bảng danh sách KH (click chọn được) |
| `outListBorrowedBooks` | out | JTable | Bảng sách đang mượn (có checkbox) |
| `inStatus` | in | JTextField | Ô nhập trạng thái sách |
| `inFine` | in | JTextField | Ô nhập tiền phạt |
| `subPayment` | sub | JButton | Nút Payment — hiển thị hóa đơn |
| `outInvoice` | out | JTextArea | Khu vực hiển thị hóa đơn thanh toán |
| `subConfirm` | sub | JButton | Nút Confirm — xác nhận thanh toán |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường giữa 2 class |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) ở đầu "chứa" | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) ở đầu "chứa" | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed (nét đứt), mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (view class gọi DAO class) |

#### 6. Cách ghi multiplicity

- **1..1** → ghi `1` ở một đầu của đường kết nối
- **0..\*** hoặc **1..\*** → ghi `n` hoặc `*` ở đầu kia
- Ghi multiplicity ở **cả 2 đầu** của đường kết nối

Ví dụ: RentalSlip(1) ←——→ (n) Payment nghĩa là 1 phiếu mượn có nhiều lần thanh toán.

#### 7. Bảng quan hệ chi tiết (Visual Paradigm)

| Từ | Đến | Kiểu quan hệ | Multiplicity | Role name | Giải thích |
|----|-----|---------------|-------------|-----------|------------|
| Customer | RentalSlip | Association | 1 — n | borrows | Một khách hàng có nhiều phiếu mượn |
| RentalSlip | RentalSlipDetail | Composition | 1 — n | contains | Chi tiết không tồn tại nếu không có phiếu mượn |
| BookTitle | RentalSlipDetail | Association | 1 — n | references | Một đầu sách xuất hiện trong nhiều chi tiết |
| RentalSlip | Payment | Composition | 1 — n | paidBy | Một phiếu mượn có nhiều lần thanh toán |
| User | RentalSlip | Association | 1 — n | processes | Một nhân viên xử lý nhiều phiếu mượn |
| User | Payment | Association | 1 — n | creates | Một nhân viên lập nhiều phiếu thanh toán |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ RentalSlip → Payment (Composition 1-n)**

1. Chọn công cụ **Composition** (đường liền nét đầu kim cương filled ◆) từ toolbox.
2. Click vào `RentalSlip` (phía "chứa"), kéo sang `Payment` (phía "bị chứa").
3. Click chuột phải vào đường kết nối → **Open Specification**.
4. Tại mục **From** (RentalSlip): multiplicity = `1`.
5. Tại mục **To** (Payment): multiplicity = `*`, role name = `paidBy`.
6. Nhấn OK. Đầu kim cương filled (◆) nằm phía `RentalSlip`.

**Ví dụ 2: Vẽ quan hệ BookTitle → RentalSlipDetail (Association 1-n)**

1. Chọn công cụ **Association** (đường liền nét mũi tên rỗng) từ toolbox.
2. Click vào `BookTitle`, kéo sang `RentalSlipDetail`.
3. Click chuột phải → **Open Specification**.
4. Tại mục **From** (BookTitle): multiplicity = `1`, role name = `references`.
5. Tại mục **To** (RentalSlipDetail): multiplicity = `*`.
6. Nhấn OK.

---

## Câu 3: Thiết kế tĩnh

### View Classes

**ReturnBookFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `inCustomerName` | JTextField | Ô nhập tên khách hàng để tìm kiếm |
| `subSearchCustomer` | JButton | Nút "Search" tìm kiếm khách hàng |
| `outsubListCustomer` | JTable | Bảng danh sách khách hàng tìm được (click để chọn) |
| `outListBorrowedBooks` | JTable | Bảng danh sách sách đang mượn (cột: mã, tên, ngày mượn, giá thuê, tiền thuê, checkbox) |
| `inStatus` | JTextField | Ô nhập trạng thái sách (binh thuong / hu hong) cho sách được chọn |
| `inFine` | JTextField | Ô nhập tiền phạt cho sách được chọn |
| `subPayment` | JButton | Nút "Payment" hiển thị hóa đơn |
| `outInvoice` | JTextArea | Khu vực hiển thị hóa đơn thanh toán |
| `subConfirm` | JButton | Nút "Confirm" xác nhận thanh toán |

**HomeFrm:**

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `subReturnPay` | JButton | Nút mở chức năng Return and pay |

### DAO Classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| CustomerDAO | `searchCustomerByName(name)` | `List<Customer>` | Tìm khách hàng theo tên |
| CustomerDAO | `getCustomerById(customerId)` | `Customer` | Lấy thông tin KH theo mã |
| RentalSlipDAO | `getRentalSlipsByCustomerId(customerId)` | `List<RentalSlip>` | Lấy danh sách phiếu mượn của KH |
| RentalSlipDetailDAO | `getUnreturnedDetails(customerId)` | `List<RentalSlipDetail>` | Lấy danh sách sách đang mượn (returnDate IS NULL) |
| RentalSlipDetailDAO | `updateReturn(detailId, returnDate, status, fine)` | `boolean` | Cập nhật thông tin trả sách |
| PaymentDAO | `insertPayment(payment)` | `boolean` | Lưu phiếu thanh toán mới |
| BookTitleDAO | `getBookTitleById(bookTitleId)` | `BookTitle` | Lấy thông tin đầu sách theo mã |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| BookTitle | Entity | id: int (PK), code: String, name: String, author: String, publisher: String, pubYear: int, rentalPrice: double, quantity: int |
| Customer | Entity | id: int (PK), code: String, name: String, idCard: String, phone: String, address: String |
| RentalSlip | Entity | id: int (PK), customer: Customer (object), user: User (object), rentalDate: Date, totalBooks: int |
| RentalSlipDetail | Entity | id: int (PK), rentalSlip: RentalSlip (object), bookTitle: BookTitle (object), rentalPrice: double, returnDate: Date, status: String, fine: double |
| Payment | Entity | id: int (PK), rentalSlip: RentalSlip (object), user: User (object), paymentDate: Date, totalAmount: double |
| User | Entity | id: int (PK), username: String, password: String, role: String |

### Entity Types (Design)

| Entity | Kiểu thuộc tính |
|--------|----------------|
| BookTitle | bookTitleId: int, code: String, name: String, author: String, publisher: String, pubYear: int, rentalPrice: double, quantity: int |
| Customer | customerId: int, code: String, name: String, idCard: String, phone: String, address: String |
| RentalSlip | rentalSlipId: int, customerId: int, userId: int, rentalDate: Date, totalBooks: int |
| RentalSlipDetail | rentalSlipDetailId: int, rentalSlipId: int, bookTitleId: int, rentalPrice: double, returnDate: Date, status: String, fine: double |
| Payment | paymentId: int, rentalSlipId: int, userId: int, paymentDate: Date, totalAmount: double |
| User | userId: int, username: String, password: String, role: String |

### Database Schema

**tblCustomer**

| Column | Type | Constraint |
|--------|------|------------|
| customerId | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | VARCHAR(100) | NOT NULL |
| idCard | VARCHAR(20) | |
| phone | VARCHAR(15) | |
| address | VARCHAR(200) | |

**tblBookTitle**

| Column | Type | Constraint |
|--------|------|------------|
| bookTitleId | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | NOT NULL, UNIQUE |
| name | VARCHAR(200) | NOT NULL |
| author | VARCHAR(100) | |
| publisher | VARCHAR(100) | |
| pubYear | INT | |
| rentalPrice | DOUBLE | NOT NULL |
| quantity | INT | NOT NULL, DEFAULT 0 |

**tblRentalSlip**

| Column | Type | Constraint |
|--------|------|------------|
| rentalSlipId | INT | PK, AUTO_INCREMENT |
| customerId | INT | FK → tblCustomer(customerId) |
| userId | INT | FK → tblUser(userId) |
| rentalDate | DATE | NOT NULL |
| totalBooks | INT | NOT NULL |

**tblRentalSlipDetail**

| Column | Type | Constraint |
|--------|------|------------|
| rentalSlipDetailId | INT | PK, AUTO_INCREMENT |
| rentalSlipId | INT | FK → tblRentalSlip(rentalSlipId) |
| bookTitleId | INT | FK → tblBookTitle(bookTitleId) |
| rentalPrice | DOUBLE | NOT NULL |
| returnDate | DATE | NULL (null = chưa trả) |
| status | VARCHAR(50) | NULL |
| fine | DOUBLE | DEFAULT 0 |

**tblPayment**

| Column | Type | Constraint |
|--------|------|------------|
| paymentId | INT | PK, AUTO_INCREMENT |
| rentalSlipId | INT | FK → tblRentalSlip(rentalSlipId) |
| userId | INT | FK → tblUser(userId) |
| paymentDate | DATE | NOT NULL |
| totalAmount | DOUBLE | NOT NULL |

**tblUser**

| Column | Type | Constraint |
|--------|------|------------|
| userId | INT | PK, AUTO_INCREMENT |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |

### Hướng dẫn vẽ trên Visual Paradigm

1. Tạo Class Diagram mới.
2. Tạo 6 class: `BookTitle`, `Customer`, `RentalSlip`, `RentalSlipDetail`, `Payment`, `User`.
3. Thêm attributes cho từng class (dùng visibility `-` cho private).
4. Vẽ Association từ `Customer` → `RentalSlip` (1..*).
5. Vẽ Association từ `RentalSlip` → `RentalSlipDetail` (1..*).
6. Vẽ Association từ `BookTitle` → `RentalSlipDetail` (1..*).
7. Vẽ Association từ `RentalSlip` → `Payment` (1..*).
8. Vẽ Association từ `User` → `RentalSlip` (1..*) và `User` → `Payment` (1..*).
9. Đặt tên association: "borrows", "contains", "references", "pays", "processes".

---

## Câu 4: Sequence Diagram

### Hướng dẫn vẽ trên Visual Paradigm

1. Tạo Sequence Diagram mới.
2. Tạo lifelines: `:Staff` (Actor), `:ReturnBookFrm` (Boundary), `:CustomerDAO` (Control), `:RentalSlipDetailDAO` (Control), `:BookTitleDAO` (Control), `:PaymentDAO` (Control).
3. Vẽ message flow theo bảng bên dưới.
4. Sử dụng `alt` fragment cho nhánh ngoại lệ (KH không tồn tại, không có sách đang mượn).
5. Sử dụng `loop` fragment cho bước lặp tick nhiều sách.
6. Sử dụng synchronous message cho request, return message cho response.

### ASCII Sequence Diagram

```
Staff            ReturnBookFrm      CustomerDAO    RentalSlipDetailDAO  BookTitleDAO   PaymentDAO
  |                    |                  |                |                  |              |
  |--- select -------->|                  |                |                  |              |
  |    ReturnPay       |                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- enterName ----->|                  |                |                  |              |
  |    "Nguyen Van A"  |                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- clickSearch --->|                  |                |                  |              |
  |                    |--- search ------>|                |                  |              |
  |                    |    Customer()    |                |                  |              |
  |                    |<-- List<Customer>|                |                  |              |
  |                    |                  |                |                  |              |
  |<-- showList -------|                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- selectCustomer->|                  |                |                  |              |
  |                    |                  |                |                  |              |
  |                    |--- getUnreturned |                |                  |              |
  |                    |    Details() ----|--------------->|                  |              |
  |                    |<-- List<Detail> -|----------------|                  |              |
  |                    |                  |                |                  |              |
  |                    |--- getBookTitle()|                |                  |              |
  |                    |                  |----------------|----------------->|              |
  |                    |<-- BookTitle ----|----------------|------------------|              |
  |                    |                  |                |                  |              |
  |<-- showBorrowedList|                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- tickBook1 ------>|                  |                |                  |              |
  |--- enterStatus --->|                  |                |                  |              |
  |    "binh thuong"   |                  |                |                  |              |
  |--- enterFine ----->|                  |                |                  |              |
  |    0               |                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- tickBook2 ------>|                  |                |                  |              |
  |--- enterStatus --->|                  |                |                  |              |
  |    "binh thuong"   |                  |                |                  |              |
  |--- enterFine ----->|                  |                |                  |              |
  |    0               |                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- clickPayment -->|                  |                |                  |              |
  |<-- showInvoice ----|                  |                |                  |              |
  |                    |                  |                |                  |              |
  |--- clickConfirm -->|                  |                |                  |              |
  |                    |--- updateReturn()|                |                  |              |
  |                    |                  |----------------|----------------->|              |
  |                    |<-- true ---------|----------------|------------------|              |
  |                    |                  |                |                  |              |
  |                    |--- updateReturn()|                |                  |              |
  |                    |                  |----------------|----------------->|              |
  |                    |<-- true ---------|----------------|------------------|              |
  |                    |                  |                |                  |              |
  |                    |--- insertPayment()                |                  |              |
  |                    |                  |----------------|------------------|------------>|
  |                    |<-- true ---------|----------------|------------------|-------------|
  |                    |                  |                |                  |              |
  |<-- successMsg -----|                  |                |                  |              |
```

### Bảng message flow

| # | Từ | Đến | Message | Ghi chú |
|---|-----|------|---------|---------|
| 1 | Staff | ReturnBookFrm | `selectReturnPay()` | Chọn chức năng Return and pay từ HomeFrm |
| 2 | ReturnBookFrm | ReturnBookFrm | `showReturnForm()` | Hiển thị giao diện trả sách |
| 3 | Staff | ReturnBookFrm | `enterCustomerName("Nguyen Van A")` | Nhập tên KH vào ô inCustomerName |
| 4 | Staff | ReturnBookFrm | `clickSearchCustomer()` | Nhấn nút Search |
| 5 | ReturnBookFrm | CustomerDAO | `searchCustomerByName("Nguyen Van A")` | Gọi DAO tìm KH theo tên |
| 6 | CustomerDAO | ReturnBookFrm | `return List<Customer>` | Trả về danh sách KH |
| 7 | ReturnBookFrm | ReturnBookFrm | `displayCustomerList(list)` | Hiển thị danh sách KH ra outsubListCustomer |
| 8 | Staff | ReturnBookFrm | `selectCustomer(customerId=1)` | Click chọn KH "Nguyen Van A" |
| 9 | ReturnBookFrm | RentalSlipDetailDAO | `getUnreturnedDetails(customerId=1)` | Lấy sách đang mượn (returnDate IS NULL) |
| 10 | RentalSlipDetailDAO | ReturnBookFrm | `return List<RentalSlipDetail>` | Trả về danh sách sách đang mượn |
| 11 | ReturnBookFrm | BookTitleDAO | `getBookTitleById(bookTitleId)` | Lấy thông tin đầu sách cho mỗi chi tiết |
| 12 | BookTitleDAO | ReturnBookFrm | `return BookTitle` | Trả về thông tin đầu sách |
| 13 | ReturnBookFrm | ReturnBookFrm | `displayBorrowedBooks(list)` | Hiển thị danh sách sách đang mượn ra bảng |
| 14 | Staff | ReturnBookFrm | `tickBook(detailId=1, status="binh thuong", fine=0)` | Tick chọn sách 1, nhập trạng thái và phạt |
| 15 | Staff | ReturnBookFrm | `tickBook(detailId=2, status="binh thuong", fine=0)` | Tick chọn sách 2, nhập trạng thái và phạt |
| 16 | Staff | ReturnBookFrm | `clickPayment()` | Nhấn nút Payment |
| 17 | ReturnBookFrm | ReturnBookFrm | `calculateAndDisplayInvoice()` | Tính toán và hiển thị hóa đơn |
| 18 | ReturnBookFrm | ReturnBookFrm | `showInvoice(invoice)` | Hiển thị hóa đơn: KH, sách trả, tổng tiền |
| 19 | Staff | ReturnBookFrm | `clickConfirm()` | Nhấn nút Confirm |
| 20 | ReturnBookFrm | RentalSlipDetailDAO | `updateReturn(detailId=1, returnDate, status, fine)` | Cập nhật chi tiết trả sách 1 |
| 21 | RentalSlipDetailDAO | ReturnBookFrm | `return true` | Xác nhận cập nhật thành công |
| 22 | ReturnBookFrm | RentalSlipDetailDAO | `updateReturn(detailId=2, returnDate, status, fine)` | Cập nhật chi tiết trả sách 2 |
| 23 | RentalSlipDetailDAO | ReturnBookFrm | `return true` | Xác nhận cập nhật thành công |
| 24 | ReturnBookFrm | PaymentDAO | `insertPayment(payment)` | Lưu phiếu thanh toán |
| 25 | PaymentDAO | ReturnBookFrm | `return true` | Xác nhận lưu thành công |
| 26 | ReturnBookFrm | Staff | `displayMessage("Tra sach va thanh toan thanh cong")` | Hiển thị thông báo thành công |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên testcase | Mô tả |
|----|-------------|-------|
| TC01 | Trả sách và thanh toán thành công | Staff tìm KH, chọn, tick 2 sách trả, thanh toán |
| TC02 | Trả sách một phần | Staff chỉ tick 1 trong 2 sách đang mượn |
| TC03 | Trả sách có phạt | Staff nhập trạng thái "hu hong" và tiền phạt |

### TC01: Trả sách và thanh toán thành công

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

**tblCustomer**

| customerId | code | name | idCard | phone | address |
|------------|------|------|--------|-------|---------|
| 1 | KH001 | Nguyen Van A | 012345678901 | 0901234567 | Ha Noi |

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |
| 2 | BT002 | Co so du lieu | Tran Van C | DHQG | 2022 | 4000 | 8 |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalBooks |
|--------------|------------|--------|------------|------------|
| 1 | 1 | 1 | 2026-06-01 | 2 |

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice | returnDate | status | fine |
|--------------------|-------------|-------------|-------------|------------|--------|------|
| 1 | 1 | 1 | 5000 | NULL | NULL | 0 |
| 2 | 1 | 2 | 4000 | NULL | NULL | 0 |

**tblPayment**: rỗng (0 dòng)

#### Kịch bản test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công, hiển thị HomeFrm |
| 2 | Staff chọn Return and pay | — | Hiển thị ReturnBookFrm với ô nhập tên KH, nút Search |
| 3 | Staff nhập tên KH | "Nguyen Van A" vào ô inCustomerName | Ô inCustomerName hiển thị "Nguyen Van A" |
| 4 | Staff nhấn Search | Nhấn nút SearchCustomer | Hiển thị danh sách KH: 1 dòng — KH001, Nguyen Van A |
| 5 | Staff chọn khách hàng | Click dòng KH001 | Hiển thị danh sách sách đang mượn: 2 dòng — BT001 Lap trinh Java (ngày mượn 01/06, giá 5000, tiền thuê 35000), BT002 Co so du lieu (ngày mượn 01/06, giá 4000, tiền thuê 28000) |
| 6 | Staff tick sách 1 | Tick checkbox BT001, nhập trạng thái "binh thuong", tiền phạt 0 | Checkbox BT001 được chọn, trạng thái = "binh thuong", fine = 0 |
| 7 | Staff tick sách 2 | Tick checkbox BT002, nhập trạng thái "binh thuong", tiền phạt 0 | Checkbox BT002 được chọn, trạng thái = "binh thuong", fine = 0 |
| 8 | Staff nhấn Payment | Nhấn nút Payment | Hiển thị hóa đơn: KH Nguyen Van A, ngày trả 08/06/2026, sách 1 = Lap trinh Java (35,000đ), sách 2 = Co so du lieu (28,000đ), tổng = 63,000đ |
| 9 | Staff nhấn Confirm | Nhấn nút Confirm | Hệ thống hiển thị "Tra sach va thanh toan thanh cong" |

#### Database sau khi test

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice | returnDate | status | fine |
|--------------------|-------------|-------------|-------------|------------|--------|------|
| 1 | 1 | 1 | 5000 | 2026-06-08 | binh thuong | 0 |
| 2 | 1 | 2 | 4000 | 2026-06-08 | binh thuong | 0 |

**tblPayment**

| paymentId | rentalSlipId | userId | paymentDate | totalAmount |
|-----------|-------------|--------|------------|-------------|
| 1 | 1 | 1 | 2026-06-08 | 63000 |

**tblCustomer**: không thay đổi

**tblBookTitle**: không thay đổi

**tblRentalSlip**: không thay đổi

---

### TC02: Trả sách một phần (chỉ trả 1 trong 2 sách)

#### Database trước khi test

**tblUser**

| userId | username | password | role |
|--------|----------|----------|------|
| 1 | staff01 | 123456 | Staff |

**tblCustomer**

| customerId | code | name | idCard | phone | address |
|------------|------|------|--------|-------|---------|
| 1 | KH001 | Nguyen Van A | 012345678901 | 0901234567 | Ha Noi |

**tblBookTitle**

| bookTitleId | code | name | author | publisher | pubYear | rentalPrice | quantity |
|-------------|------|------|--------|-----------|---------|-------------|----------|
| 1 | BT001 | Lap trinh Java | Nguyen Van B | DHQG | 2023 | 5000 | 10 |
| 2 | BT002 | Co so du lieu | Tran Van C | DHQG | 2022 | 4000 | 8 |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalBooks |
|--------------|------------|--------|------------|------------|
| 1 | 1 | 1 | 2026-06-01 | 2 |

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice | returnDate | status | fine |
|--------------------|-------------|-------------|-------------|------------|--------|------|
| 1 | 1 | 1 | 5000 | NULL | NULL | 0 |
| 2 | 1 | 2 | 4000 | NULL | NULL | 0 |

**tblPayment**: rỗng

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | username: "staff01", password: "123456" | Đăng nhập thành công |
| 2 | Staff chọn Return and pay | — | Hiển thị ReturnBookFrm |
| 3 | Staff tìm KH | "Nguyen Van A", nhấn Search | Hiển thị 1 KH: Nguyen Van A |
| 4 | Staff chọn KH | Click KH001 | Hiển thị 2 sách đang mượn |
| 5 | Staff chỉ tick sách 1 | Tick BT001, trạng thái "binh thuong", phạt 0 | Chỉ BT001 được chọn |
| 6 | Staff nhấn Payment | Nhấn nút Payment | Hóa đơn: chỉ Lap trinh Java, tổng = 35,000đ |
| 7 | Staff nhấn Confirm | Nhấn nút Confirm | Thông báo thanh toán thành công |

#### Database sau khi test

**tblRentalSlipDetail**

| rentalSlipDetailId | rentalSlipId | bookTitleId | rentalPrice | returnDate | status | fine |
|--------------------|-------------|-------------|-------------|------------|--------|------|
| 1 | 1 | 1 | 5000 | 2026-06-08 | binh thuong | 0 |
| 2 | 1 | 2 | 4000 | NULL | NULL | 0 |

**tblPayment**

| paymentId | rentalSlipId | userId | paymentDate | totalAmount |
|-----------|-------------|--------|------------|-------------|
| 1 | 1 | 1 | 2026-06-08 | 35000 |

**tblCustomer**: không thay đổi

**tblBookTitle**: không thay đổi
