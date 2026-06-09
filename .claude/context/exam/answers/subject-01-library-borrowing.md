# Subject No. 01 — Library Management — Module "Borrowing of books"

> **Domain:** Library Management
> **Duration:** 60 minutes
> **Total:** 7.5 points (1.5 points/question)

---

## Câu 1: Viết scenario chuẩn cho module (1.5 điểm)

### Scenario — Mượn sách

| Bước | Diễn biến |
|------|-----------|
| 1 | Thủ thư (Staff) đăng nhập vào hệ thống. Giao diện Login xuất hiện với ô nhập Username, Password và nút Login. |
| 2 | Staff nhập username `staff01`, password `******` và nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Borrowing, Returning, Statistics. |
| 4 | Staff chọn chức năng **Borrowing of books**. |
| 5 | Giao diện Borrowing xuất hiện với ô nhập mã thẻ bạn đọc (Reader barcode) và nút Scan. |
| 6 | Staff quét thẻ bạn đọc có mã `R001`. |
| 7 | Hệ thống hiển thị thông tin bạn đọc: Mã `R001`, Tên "Nguyen Van A", Ngày sinh "01/01/1995", Địa chỉ "Ha Noi", SĐT "0912345678", Barcode "R001". Đồng thời hiển thị danh sách sách chưa trả (nếu có) và danh sách sách đã trả. |
| 8 | Staff quét từng cuốn sách cần mượn: quét sách có barcode `B001` (Lap trinh Java, Tac gia: Nguyen B, NXB: 2020, Gia bia: 150000). |
| 9 | Sách `B001` được thêm vào danh sách mượn. Staff quét tiếp sách `B002` (Co so du lieu, Tac gia: Tran C, NXB: 2019, Gia bia: 120000). |
| 10 | Staff quét tiếp sách `B003` (He dieu hanh, Tac gia: Le D, NXB: 2021, Gia bia: 130000). |
| 11 | Staff nhấn **Submit** để hoàn tất phiếu mượn. |
| 12 | Hệ thống kiểm tra: tổng số sách đang mượn (chưa trả) + sách mới mượn <= 5. Nếu hợp lệ, hệ thống lưu vào database. |
| 13 | Hệ thống in phiếu mượn chứa: Mã bạn đọc `R001`, Tên "Nguyen Van A", Barcode phiếu mượn `LP001`, danh sách sách mượn (mỗi dòng: mã sách, tên sách, tác giả, barcode, ngày mượn, ngày hẹn trả). Dòng cuối ghi tổng số sách đang mượn. |
| 14 | Hệ thống thông báo "Muon sach thanh cong" và quay về giao diện Home. |

### Kịch bản ngoại lệ

- **EL1:** Thẻ bạn đọc không tồn tại → Hệ thống thông báo "The khong ton tai".
- **EL2:** Số sách đang mượn + sách mới > 5 → Hệ thống thông báo "So sach dang muon vuot qua 5 cuon".
- **EL3:** Sách không tồn tại trong hệ thống → Hệ thống thông báo "Sach khong ton tai".

---

## Câu 2: Trích xuất và xây dựng class diagram cho các entity class liên quan (1.5 điểm)

### Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý mượn sách thư viện. Mỗi cuốn sách (mã sách, tên sách, tác giả, năm xuất bản, giá bia, số lượng, barcode, mô tả) có thể được mượn nhiều lần bởi nhiều bạn đọc khác nhau. Mỗi bạn đọc có thẻ bạn đọc chứa mã bạn đọc, tên, ngày sinh, địa chỉ, số điện thoại, barcode. Mỗi lần mượn, một phiếu mượn được tạo chứa thông tin bạn đọc và danh sách sách mượn. Tối đa 5 cuốn sách được mượn cùng lúc. Thời hạn mượn tối đa 1 tháng, quá hạn bị phạt 20% giá bia.

### Bước 2: Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Sách (Book) | Entity class | Đối tượng chính được mượn |
| Bạn đọc (Reader) | Entity class | Đối tượng mượn sách |
| Phiếu mượn (Loan) | Entity class | Bản ghi mỗi lần mượn |
| Chi tiết mượn (LoanDetail) | Entity class | Liên kết n-n giữa Loan và Book |
| Mã sách, tên sách, tác giả... | Thuộc tính | Thuộc tính của Book |
| Mã bạn đọc, tên, ngày sinh... | Thuộc tính | Thuộc tính của Reader |
| Ngày mượn, ngày hẹn trả | Thuộc tính | Thuộc tính của LoanDetail |
| Barcode | Thuộc tính | Thuộc tính của Book và Reader |
| Thủ thư (Staff) | Entity class (User) | Người thực hiện mượn |
| Thư viện | Bị loại | Quá tổng quát |
| Số lượng | Thuộc tính | Thuộc tính của Book |
| Giá bia | Thuộc tính | Thuộc tính của Book |
| Tiền phạt | Thuộc tính | Tính toán từ giá bia × 20% |

### Bước 3: Xác định quan hệ

```
Book 1 --- n LoanDetail
```
- Một cuốn sách có thể xuất hiện trong nhiều chi tiết mượn.
- Mỗi chi tiết mượn liên kết đến một cuốn sách.

```
Loan 1 --- n LoanDetail
```
- Một phiếu mượn có nhiều chi tiết mượn (nhiều cuốn sách).
- Mỗi chi tiết mượn thuộc về một phiếu mượn.

```
Reader 1 --- n Loan
```
- Một bạn đọc có thể có nhiều phiếu mượn.
- Mỗi phiếu mượn thuộc về một bạn đọc.

```
User 1 --- n Loan
```
- Một thủ thư có thể tạo nhiều phiếu mượn.
- Mỗi phiếu mượn được tạo bởi một thủ thư.

```
Reader n --- n Book
```
- Quan hệ n-n, được tách qua Loan và LoanDetail.

### Bước 4: Class Diagram (Analysis)

```
+------------------+       +------------------+
|      Book        |       |     Reader       |
+------------------+       +------------------+
| -code: String    |       | -code: String    |
| -name: String    |       | -name: String    |
| -author: String  |       | -dob: Date       |
| -pubYear: int    |       | -address: String |
| -coverPrice: float|      | -phone: String   |
| -quantity: int   |       | -barcode: String |
| -barcode: String |       +------------------+
| -description: String|    | +searchReader()  |
+------------------+       +------------------+
| +searchBook()    |                |
| +getBookByBarcode()|              | 1
+------------------+                |
         | 1                        |
         |                          v
         | n                +------------------+
         +----------------->|      Loan        |
                            +------------------+
                            | -id: int         |
                            | -loanDate: Date  |
                            | -barcode: String |
                            +------------------+
                            | +addLoan()       |
                            | +printLoanSlip() |
                            +------------------+
                                    | 1
                                    |
                                    | n
                                    v
                            +------------------+
                            |   LoanDetail     |
                            +------------------+
                            | -id: int         |
                            | -dueDate: Date   |
                            | -returnDate: Date|
                            | -fine: float     |
                            +------------------+

+------------------+
|      User        |
+------------------+
| -username: String|
| -password: String|
| -role: String    |
+------------------+
| +checkLogin()    |
+------------------+
```

### Mô tả quan hệ chi tiết

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Book → LoanDetail | 1-n (Composition) | Một sách có trong nhiều chi tiết mượn |
| Loan → LoanDetail | 1-n (Composition) | Một phiếu mượn có nhiều chi tiết |
| Reader → Loan | 1-n (Association) | Một bạn đọc có nhiều phiếu mượn |
| User → Loan | 1-n (Association) | Một thủ thư tạo nhiều phiếu mượn |

### Classes diagram (analysis)

Phân tích module này:

Đăng nhập vào hệ thống → Giao diện Login xuất hiện → cần class: LoginFrm
  Ô nhập username → inUsername
  Ô nhập password → inPassword
  Nút Login → subLogin

Nhập username/password → Hệ thống phải kiểm tra đăng nhập → cần phương thức:
  Tên: checkLogin()
  Đầu vào: username, password (thuộc class User)
  Đầu ra: boolean
  Gán cho entity class: User.

Đăng nhập thành công → Giao diện Home xuất hiện → cần class: HomeFrm
  Nút chọn Borrowing → subBorrowing
  Nút chọn Returning → subReturning
  Nút chọn Statistics → subStatistics

Staff chọn Borrowing → Giao diện Borrowing xuất hiện → cần class: BorrowBookFrm
  Ô nhập mã thẻ bạn đọc → inReaderBarcode
  Nút Scan bạn đọc → subScanReader
  Vùng hiển thị thông tin bạn đọc → outReaderInfo
  Bảng sách chưa trả → outListUnreturnedBooks
  Bảng sách đã trả → outListReturnedBooks
  Ô nhập barcode sách → inBookBarcode
  Nút Scan sách → subScanBook
  Bảng sách đang mượn → outListBorrowedBooks
  Nút Submit → subSubmit

Staff quét thẻ bạn đọc → Hệ thống tìm thông tin bạn đọc → cần phương thức:
  Tên: getReaderByBarcode()
  Đầu vào: barcode (String)
  Đầu ra: Reader
  Gán cho entity class: Reader.

Hệ thống hiển thị sách chưa trả → cần phương thức:
  Tên: getUnreturnedBooks()
  Đầu vào: readerId (int)
  Đầu ra: List<Book>
  Gán cho entity class: Book.

Staff quét sách → Hệ thống tìm sách theo barcode → cần phương thức:
  Tên: getBookByBarcode()
  Đầu vào: barcode (String)
  Đầu ra: Book
  Gán cho entity class: Book.

Staff nhấn Submit → Hệ thống lưu phiếu mượn → cần phương thức:
  Tên: addLoan()
  Đầu vào: loan (Loan)
  Đầu ra: boolean
  Gán cho entity class: Loan.

Hệ thống lưu chi tiết mượn → cần phương thức:
  Tên: addLoanDetail()
  Đầu vào: loanDetail (LoanDetail)
  Đầu ra: boolean
  Gán cho entity class: LoanDetail.

### Tóm tắt
View classes: LoginFrm, HomeFrm, BorrowBookFrm
Phương thức: checkLogin(), getReaderByBarcode(), getUnreturnedBooks(), getBookByBarcode(), addLoan(), addLoanDetail()

---

## Câu 3: Thiết kế tĩnh — Giao diện và class diagram chi tiết (1.5 điểm)

### Bước 1: Xác định các View class (Form class)

| View class | Mô tả |
|------------|-------|
| LoginFrm | Giao diện đăng nhập |
| HomeFrm | Giao diện chính |
| BorrowBookFrm | Giao diện mượn sách (chính) |

### Bước 2: Xác định các phần tử giao diện

**LoginFrm:**
- `inUsername`: ô nhập username
- `inPassword`: ô nhập password
- `subLogin`: nút Login

**HomeFrm:**
- `subBorrowing`: nút chọn Borrowing
- `subReturning`: nút chọn Returning
- `subStatistics`: nút chọn Statistics

**BorrowBookFrm:**
- `inReaderBarcode`: ô nhập/scan mã thẻ bạn đọc
- `subScanReader`: nút scan bạn đọc
- `outReaderInfo`: vùng hiển thị thông tin bạn đọc
- `outListUnreturnedBooks`: bảng sách chưa trả
- `outListReturnedBooks`: bảng sách đã trả
- `inBookBarcode`: ô nhập/scan barcode sách
- `subScanBook`: nút scan sách
- `outListBorrowedBooks`: bảng sách đang mượn (hiện tại)
- `subSubmit`: nút Submit

### Bước 3: Xác định DAO class

| DAO class | Phương thức | Mô tả |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| ReaderDAO | `getReaderByBarcode(barcode): Reader` | Lấy thông tin bạn đọc theo barcode |
| BookDAO | `getBookByBarcode(barcode): Book` | Lấy thông tin sách theo barcode |
| BookDAO | `getUnreturnedBooks(readerId): List<Book>` | Lấy danh sách sách chưa trả |
| LoanDAO | `addLoan(loan): boolean` | Tạo phiếu mượn mới |
| LoanDetailDAO | `addLoanDetail(loanDetail): boolean` | Thêm chi tiết mượn |

### Bước 4: Xác định Entity class (Design phase)

**Book:**
- `id: int` (PK)
- `code: String`
- `name: String`
- `author: String`
- `pubYear: int`
- `coverPrice: float`
- `quantity: int`
- `barcode: String`
- `description: String`

**Reader:**
- `id: int` (PK)
- `code: String`
- `name: String`
- `dob: Date`
- `address: String`
- `phone: String`
- `barcode: String`

**Loan:**
- `id: int` (PK)
- `loanDate: Date`
- `barcode: String`
- `reader: Reader` (object attribute)
- `user: User` (object attribute)
- `loanDetails: List<LoanDetail>` (object attribute)

**LoanDetail:**
- `id: int` (PK)
- `dueDate: Date`
- `returnDate: Date`
- `fine: float`
- `book: Book` (object attribute)
- `loan: Loan` (object attribute)

**User:**
- `id: int` (PK)
- `username: String`
- `password: String`
- `role: String`

### Bước 5: Database Design

**tblBook:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| author | varchar | |
| pubYear | int | |
| coverPrice | float | |
| quantity | int | |
| barcode | varchar | |
| description | varchar | |

**tblReader:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| dob | date | |
| address | varchar | |
| phone | varchar | |
| barcode | varchar | |

**tblLoan:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| loanDate | date | |
| barcode | varchar | |
| readerID | int | FK → tblReader.ID |
| userID | int | FK → tblUser.ID |

**tblLoanDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| dueDate | date | |
| returnDate | date | |
| fine | float | |
| bookID | int | FK → tblBook.ID |
| loanID | int | FK → tblLoan.ID |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| username | varchar | |
| password | varchar | |
| role | varchar | |

### Bước 6: Mô tả cách vẽ Class Diagram (Design phase) bằng Visual Paradigm

**Các bước vẽ:**

1. Mở Visual Paradigm → chọn **Class Diagram** trong danh mục Diagrams.
2. Tạo 5 class: `LoginFrm`, `HomeFrm`, `BorrowBookFrm`, `UserDAO`, `ReaderDAO`, `BookDAO`, `LoanDAO`, `LoanDetailDAO`.
3. Tạo 5 entity class: `Book`, `Reader`, `Loan`, `LoanDetail`, `User`.

**Vẽ View classes (Form):**
- Vẽ hình chữ nhật 3 ngăn cho `LoginFrm`:
  - Ngăn 1 (tên): `<<boundary>> LoginFrm`
  - Ngăn 2 (thuộc tính): `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton`
  - Ngăn 3 (phương thức): +không có trong view class
- Tương tự cho `HomeFrm` và `BorrowBookFrm`.

**Vẽ DAO classes:**
- Vẽ hình chữ nhật 3 ngăn cho `UserDAO`:
  - Ngăn 1: `<<control>> UserDAO`
  - Ngăn 2: không có thuộc tính
  - Ngăn 3: `+checkLogin(username: String, password: String): boolean`
- Tương tự cho các DAO khác.

**Vẽ Entity classes:**
- Vẽ hình chữ nhật 3 ngăn cho `Book`:
  - Ngăn 1: `<<entity>> Book`
  - Ngăn 2: `-id: int`, `-code: String`, `-name: String`, `-author: String`, `-pubYear: int`, `-coverPrice: float`, `-quantity: int`, `-barcode: String`, `-description: String`
  - Ngăn 3: `+searchBook(key: String): List<Book>`, `+getBookByBarcode(barcode: String): Book`

**Vẽ các đường kết nối:**

| Từ | Đến | Loại đường | Kiểu quan hệ | Giải thích |
|----|-----|-----------|---------------|------------|
| LoginFrm | UserDAO | Đường liền nét, mũi tên tam giác rỗng (▷) | Dependency | LoginFrm sử dụng UserDAO |
| BorrowBookFrm | ReaderDAO | Đường liền nét, mũi tên tam giác rỗng | Dependency | BorrowBookFrm sử dụng ReaderDAO |
| BorrowBookFrm | BookDAO | Đường liền nét, mũi tên tam giác rỗng | Dependency | BorrowBookFrm sử dụng BookDAO |
| BorrowBookFrm | LoanDAO | Đường liền nét, mũi tên tam giác rỗng | Dependency | BorrowBookFrm sử dụng LoanDAO |
| Loan | LoanDetail | Đường liền nét, đầu kim cương rỗng (◇) | Aggregation 1-n | Loan chứa nhiều LoanDetail |
| Loan | Reader | Đường liền nét, mũi tên tam giác rỗng | Association | Loan tham chiếu đến Reader |
| Loan | User | Đường liền nét, mũi tên tam giác rỗng | Association | Loan tham chiếu đến User |
| LoanDetail | Book | Đường liền nét, mũi tên tam giác rỗng | Association | LoanDetail tham chiếu đến Book |
| Loan | LoanDetail | Đường liền nét, đầu kim cương filled (◆) | Composition | LoanDetail không tồn tại nếu không có Loan |

---

## Câu 4: Thiết kế động — Xây dựng sequence diagram (1.5 điểm)

### Mô tả cách vẽ Sequence Diagram bằng Visual Paradigm

**Các bước vẽ:**

1. Mở Visual Paradigm → chọn **Sequence Diagram**.
2. Tạo các lifeline (đối tượng):
   - Actor: `Staff` (loại Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `BorrowBookFrm`
   - Control: `UserDAO`, `ReaderDAO`, `BookDAO`, `LoanDAO`, `LoanDetailDAO`
   - Entity: `User`, `Reader`, `Book`, `Loan`, `LoanDetail`

3. Vẽ các message (mũi tên nét liền) theo thứ tự từ trên xuống dưới.

### Sequence Diagram — Mượn sách (Scenario version 3)

```
Staff          LoginFrm        UserDAO      HomeFrm       BorrowBookFrm    ReaderDAO      BookDAO        LoanDAO        LoanDetailDAO
  |               |               |            |               |               |              |              |               |
  |---login------>|               |            |               |               |              |              |               |
  |               |---checkLogin->|            |               |               |              |              |               |
  |               |               |---query DB |               |               |              |              |               |
  |               |               |<-return----|               |               |              |              |               |
  |               |<--true--------|            |               |               |              |              |               |
  |               |---open--------|----------->|               |               |              |              |               |
  |               |               |            |               |               |              |              |               |
  |---select------|---------------------------->|               |               |              |              |               |
  |               |               |            |---open--------|-------------->|              |              |               |
  |               |               |            |               |               |              |              |               |
  |---scanReader->|               |            |               |               |              |              |               |
  |               |               |            |               |---getReader-->|              |              |               |
  |               |               |            |               |               |---query DB   |              |               |
  |               |               |            |               |               |<-return------|              |               |
  |               |               |            |               |<--Reader------|              |              |               |
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |---getUnreturnedBooks---------->|              |               |
  |               |               |            |               |               |              |---query DB   |               |
  |               |               |            |               |               |              |<-return------|               |
  |               |               |            |               |<--List<Book>--|              |              |               |
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |--display info|              |              |               |
  |               |               |            |               |               |              |              |               |
  |---scanBook--->|               |            |               |               |              |              |               |
  |               |               |            |               |---getBookByBarcode----------->|              |               |
  |               |               |            |               |               |              |---query DB   |               |
  |               |               |            |               |               |              |<-return------|               |
  |               |               |            |               |<--Book--------|              |              |               |
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |--add to list |              |              |               |
  |               |               |            |               |               |              |              |               |
  |(lặp lại cho các sách khác)    |            |               |               |              |              |               |
  |               |               |            |               |               |              |              |               |
  |---submit----->|               |            |               |               |              |              |               |
  |               |               |            |               |---addLoan----|--------------|------------->|               |
  |               |               |            |               |               |              |              |---insert DB   |
  |               |               |            |               |               |              |              |<-return true--|
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |---addLoanDetail--------------|------------->|               |
  |               |               |            |               |               |              |              |---insert DB   |
  |               |               |            |               |               |              |              |<-return true--|
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |(lặp cho mỗi sách mượn)       |              |               |
  |               |               |            |               |               |              |              |               |
  |               |               |            |               |--print loan slip             |              |               |
  |               |               |            |               |--show success|              |              |               |
  |<--success-----|               |            |               |               |              |              |               |
```

### Giải thích từng bước

| # | Message | Từ | Đến | Mô tả |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhập username/password, nhấn Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Gọi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy vấn tblUser |
| 4 | return true | UserDAO | LoginFrm | Trả về true nếu đăng nhập hợp lệ |
| 5 | open | LoginFrm | HomeFrm | Mở giao diện Home |
| 6 | select Borrowing | Staff | HomeFrm | Staff chọn chức năng Borrowing |
| 7 | open | HomeFrm | BorrowBookFrm | Mở giao diện mượn sách |
| 8 | scanReader | Staff | BorrowBookFrm | Staff quét thẻ bạn đọc barcode "R001" |
| 9 | getReaderByBarcode() | BorrowBookFrm | ReaderDAO | Gọi ReaderDAO.getReaderByBarcode("R001") |
| 10 | query DB | ReaderDAO | Database | Truy vấn tblReader |
| 11 | return Reader | ReaderDAO | BorrowBookFrm | Trả về đối tượng Reader |
| 12 | getUnreturnedBooks() | BorrowBookFrm | BookDAO | Gọi BookDAO.getUnreturnedBooks(readerId) |
| 13 | query DB | BookDAO | Database | Truy vấn tblLoan JOIN tblLoanDetail JOIN tblBook |
| 14 | return List<Book> | BookDAO | BorrowBookFrm | Trả về danh sách sách chưa trả |
| 15 | display info | BorrowBookFrm | UI | Hiển thị thông tin bạn đọc + danh sách |
| 16 | scanBook | Staff | BorrowBookFrm | Staff quét sách barcode "B001" |
| 17 | getBookByBarcode() | BorrowBookFrm | BookDAO | Gọi BookDAO.getBookByBarcode("B001") |
| 18 | query DB | BookDAO | Database | Truy vấn tblBook |
| 19 | return Book | BookDAO | BorrowBookFrm | Trả về đối tượng Book |
| 20 | add to list | BorrowBookFrm | UI | Thêm sách vào danh sách mượn |
| 21 | submit | Staff | BorrowBookFrm | Staff nhấn Submit |
| 22 | addLoan() | BorrowBookFrm | LoanDAO | Tạo đối tượng Loan, gọi LoanDAO.addLoan(loan) |
| 23 | insert DB | LoanDAO | Database | INSERT INTO tblLoan |
| 24 | return true | LoanDAO | BorrowBookFrm | Trả về true |
| 25 | addLoanDetail() | BorrowBookFrm | LoanDetailDAO | Với mỗi sách mượn, tạo LoanDetail, gọi LoanDetailDAO.addLoanDetail() |
| 26 | insert DB | LoanDetailDAO | Database | INSERT INTO tblLoanDetail |
| 27 | return true | LoanDetailDAO | BorrowBookFrm | Trả về true |
| 28 | print loan slip | BorrowBookFrm | UI | In phiếu mượn |
| 29 | show success | BorrowBookFrm | UI | Hiển thị thông báo thành công |
| 30 | return | BorrowBookFrm | HomeFrm | Quay về giao diện Home |

---

## Câu 5: Viết testcase blackbox chuẩn (1.5 điểm)

### Test Plan — Danh sách testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Borrowing | Mượn sách thành công với bạn đọc đã tồn tại và sách hợp lệ |
| 2 | Borrowing | Thẻ bạn đọc không tồn tại |
| 3 | Borrowing | Sách không tồn tại trong hệ thống |
| 4 | Borrowing | Số sách đang mượn + sách mới > 5 |
| 5 | Borrowing | Bạn đọc đã mượn đúng 5 sách, không thể mượn thêm |

### Testcase chi tiết — TC01: Mượn sách thành công

**Mục đích:** Kiểm tra chức năng mượn sách hoạt động đúng khi bạn đọc tồn tại, sách hợp lệ, và tổng số sách <= 5.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblReader:**
| ID | code | name | dob | address | phone | barcode |
|----|------|------|-----|---------|-------|---------|
| 1 | R001 | Nguyen Van A | 01/01/1995 | Ha Noi | 0912345678 | R001 |

**tblBook:**
| ID | code | name | author | pubYear | coverPrice | quantity | barcode | description |
|----|------|------|--------|---------|------------|----------|---------|-------------|
| 1 | B001 | Lap trinh Java | Nguyen B | 2020 | 150000 | 10 | B001 | ... |
| 2 | B002 | Co so du lieu | Tran C | 2019 | 120000 | 8 | B002 | ... |
| 3 | B003 | He dieu hanh | Le D | 2021 | 130000 | 5 | B003 | ... |

**tblLoan:** (rỗng)

**tblLoanDetail:** (rỗng)

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login xuất hiện với ô Username, ô Password, nút Login |
| 2 | Nhập username `staff01`, password `123456`, nhấn Login | Giao diện Home xuất hiện với các chức năng: Borrowing, Returning, Statistics |
| 3 | Chọn chức năng Borrowing | Giao diện Borrowing xuất hiện với ô nhập barcode bạn đọc, nút Scan |
| 4 | Nhập barcode `R001`, nhấn Scan | Hiển thị thông tin bạn đọc: Mã R001, Tên Nguyen Van A, SĐT 0912345678. Hiển thị danh sách sách chưa trả (rỗng) và sách đã trả (rỗng) |
| 5 | Nhập barcode sách `B001`, nhấn Scan | Sách B001 (Lap trinh Java, Nguyen B, 150000) được thêm vào danh sách mượn |
| 6 | Nhập barcode sách `B002`, nhấn Scan | Sách B002 (Co so du lieu, Tran C, 120000) được thêm vào danh sách mượn |
| 7 | Nhập barcode sách `B003`, nhấn Scan | Sách B003 (He dieu hanh, Le D, 130000) được thêm vào danh sách mượn |
| 8 | Nhấn Submit | Hệ thống kiểm tra tổng số sách = 3 <= 5. Thông báo "Muon sach thanh cong". Phiếu mượn được in ra với thông tin: Mã bạn đọc R001, Tên Nguyen Van A, Barcode phiếu mượn LP001, danh sách 3 sách mượn (mỗi dòng: mã sách, tên sách, tác giả, barcode, ngày mượn, ngày hẹn trả). Dòng cuối: tổng số sách mượn = 3 |
| 9 | Nhấn OK | Quay về giao diện Home |

### Database sau khi test

**tblLoan:** (thêm 1 dòng)
| ID | loanDate | barcode | readerID | userID |
|----|----------|---------|----------|--------|
| 1 | 08/06/2026 | LP001 | 1 | 1 |

**tblLoanDetail:** (thêm 3 dòng)
| ID | dueDate | returnDate | fine | bookID | loanID |
|----|---------|------------|------|--------|--------|
| 1 | 08/07/2026 | null | 0 | 1 | 1 |
| 2 | 08/07/2026 | null | 0 | 2 | 1 |
| 3 | 08/07/2026 | null | 0 | 3 | 1 |

**tblBook:** (không thay đổi — chỉ giảm quantity khi hệ thống theo dõi tồn kho)

**tblUser:** (không thay đổi)

**tblReader:** (không thay đổi)

---

> **Ghi chú:** Đây là đáp án mẫu Subject 01. Các đề tiếp theo sẽ áp dụng cùng phương pháp: trích xuất entity → xây class diagram → thiết kế UI + class diagram → vẽ sequence diagram → viết testcase blackbox.
