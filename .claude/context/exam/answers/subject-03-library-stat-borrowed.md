# Subject 03 — Library Management — Module "Statistics of Borrowed Books"

---

## Cau 1: Scenario (1.5 diem)

### Kich ban chinh

1. Staff mo ung dung.
2. Giao dien dang nhap hien thi voi o nhap Username, Password va nut Login.
3. Staff nhap Username `staff01`, Password `123456` va nhan Login.
4. Giao dien chinh HomeFrm hien thi voi menu: Borrow, Return, Statistics.
5. Staff chon menu "Statistics".
6. Staff chon "Statistics of Borrowed Books".
7. Giao dien StatBorrowedFrm hien thi voi o nhap StartDate, EndDate va nut Search.
8. Staff nhap StartDate `01/01/2026`, EndDate `08/06/2026` va nhan Search.
9. Bang ket qua hien thi danh sach sach muon, xep theo so luot muon giam dan. Moi dong gom: Code, Book Title, Author, Barcode, Total Loans. Dong dau: B007 - Thiet ke phan mem - Le Van C - 978-1-491-95035-7 - 6 luot. Dong 2: B001 - Lap trinh Java - Nguyen Van A - 978-0-13-468599-1 - 5 luot. Dong 3: B003 - Co so du lieu - Tran Thi B - 978-0-596-52068-7 - 3 luot.
10. Staff click vao dong B007.
11. Bang chi tiet hien thi "Detail: Borrowings of Book B007 - Thiet ke phan mem" voi cot: Reader Name, Borrowed Day, Returned Day, Fine. Dong 1: Tran Thi Binh - 10/01/2026 - 15/03/2026 - 150,000. Dong 2: Nguyen Minh Tuan - 15/01/2026 - 12/02/2026 - 0. Dong 3: Pham Thi Lan - 01/02/2026 - 05/03/2026 - 76,000.

### Kich ban ngoai le

- **StartDate > EndDate**: He thong hien thi loi "Ngay bat dau phai nho hon hoac bang ngay ket thuc."
- **Khong co du lieu trong khoang thoi gian**: Bang ket qua trong, hien thi thong bao "Khong co du lieu muon sach trong khoang thoi gian nay."
- **Chua nhap du ngay**: He thong hien thi loi "Vui long nhap day du ngay bat dau va ngay ket thuc."

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thu vien cho phep nhan vien thong ke so luot muon sach trong mot khoang thoi gian. Moi cuon sach co thong tin ma sach, ten sach, tac gia, ma vach va gia bia. Moi lan muon tao ra mot phieu muon (Loan) lien ket voi mot doc gia va mot nhan vien. Moi phieu muon chua nhieu chi tiet muon (LoanDetail), moi chi tiet tuong ung voi mot cuon sach cu the voi ngay muon, ngay hen tra, ngay tra thuc te va tien phat neu tra muon. Doc gia co thong tin ma, ho ten, ngay sinh, dia chi va ma vach the. Nhan vien (User) thuc hien cac thao tac tren he thong. Chuc nang thong ke cho phep xem danh sach sach duoc muon nhieu nhat va chi tiet cac lan muon cua tung cuon sach.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Nhan vien (User) | Entity | Doi tuong thuc hien thao tac, co thuoc tính rieng (ten, tai khoan) |
| Doc gia (Reader) | Entity | Doi tuong trung tam, co nhieu thuoc tính (ma, ten, ngay sinh, dia chi, ma vach) |
| Sach (Book) | Entity | Doi tuong chinh cua thong ke, co thuoc tính (ma, ten, tac gia, nam xuat ban, gia bia, so luong, ma vach, mo ta) |
| Phieu muon (Loan) | Entity | Dai dien cho mot lan muon, co ngay muon, lien ket voi doc gia va nhan vien |
| Chi tiet muon (LoanDetail) | Entity | Lien ket giua phieu muon va tung cuon sach, co ngay muon, ngay hen tra, ngay tra, tien phat |
| Ma sach (Book Code) | Thuoc tính | Thuoc tính cua Book |
| Ten sach (Book Title) | Thuoc tính | Thuoc tính cua Book |
| Tac gia (Author) | Thuoc tính | Thuoc tính cua Book |
| Nam xuat ban (Publication Year) | Thuoc tính | Thuoc tính cua Book |
| So luong (Quantity) | Thuoc tính | Thuoc tính cua Book |
| Mo ta (Description) | Thuoc tính | Thuoc tính cua Book |
| Ma vach (Barcode) | Thuoc tính | Thuoc tính cua Book va Reader |
| So luot muon (Total Loans) | Thuoc tính tinh toan | Duoc dem tu LoanDetail, khong luu trong database |
| Ngay muon (BorrowDate) | Thuoc tính | Thuoc tính cua LoanDetail |
| Ngay tra (ReturnDate) | Thuoc tính | Thuoc tính cua LoanDetail |
| Tien phat (Fine) | Thuoc tính | Thuoc tính cua LoanDetail |
| Khoang thoi gian (Time Period) | Input | Gia tri dau vao cho thong ke, khong phai entity |

### Buoc 3: Xac dinh quan he

1. **Reader - Loan**: Mot doc gia co the co nhieu phieu muon (1-N). Moi phieu muon thuoc ve dung mot doc gia.
2. **Loan - LoanDetail**: Mot phieu muon chua nhieu chi tiet muon (1-N, composition). Moi chi tiet thuoc ve dung mot phieu muon.
3. **Book - LoanDetail**: Mot cuon sach co the xuat hien trong nhieu chi tiet muon (1-N). Moi chi tiet lien ket voi dung mot cuon sach.
4. **User - Loan**: Mot nhan vien co the xu ly nhieu phieu muon (1-N). Moi phieu muon duoc xu ly dung mot nhan vien.

### Buoc 4: Class Diagram (ASCII art)

```
+----------+ 1    N +--------+ 1    N +------------+ N    1 +-----------+
|  Reader  |--------|  Loan  |◆------| LoanDetail |--------|   Book    |
+----------+        +--------+        +------------+        +-----------+
| readerId |        | loanId |        | detailId   |        | bookId    |
| fullName |        | loanDate        | loanId     |        | title     |
| dob      |        | readerId|        | bookId     |        | author    |
| address  |        | userId |        | borrowDate |        | pubYear   |
| barcode  |        +--------+        | dueDate    |        | coverP    |
+----------+             |            | returnDate |        | quantity  |
                         1            | fine       |        | barcode   |
                         |            +------------+        | description|
                         N                                  +-----------+
                    +--------+
                    |  User  |
                    +--------+
                    | userId |
                    | fullNm |
                    | userNm |
                    | passw  |
                    +--------+
```

### Buoc 5: Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|---------|------|------------|
| Reader -> Loan | 1-N | Mot doc gia co the co nhieu phieu muon trong thoi gian su dung thu vien |
| Loan -> LoanDetail | 1-N (Composition) | Moi phieu muon chua nhieu chi tiet muon (toi da 5 sach). LoanDetail khong ton tai neu khong co Loan |
| Book -> LoanDetail | 1-N | Mot cuon sach co the duoc muon nhieu lan boi nhieu doc gia khac nhau |
| User -> Loan | 1-N | Moi nhan vien co the xu ly nhieu phieu muon/tra khac nhau |
| Reader -> LoanDetail | Gian tiep (qua Loan) | Doc gia lien ket voi chi tiet muon thong qua phieu muon |
| Book -> Loan | Gian tiep (qua LoanDetail) | Sach lien ket voi phieu muon thong qua chi tiet muon |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Library_StatBorrowed" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 5 class: Book, Reader, Loan, LoanDetail, User |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, StatBorrowedFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class LoanDetail) |
|------|----------|--------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> LoanDetail` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-detailId: int`, `-loanId: int`, `-bookId: int`, `-borrowDate: Date`, `-returnDate: Date`, `-fine: double` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+countLoansByBook(start: Date, end: Date): List<Map>` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| Book | `<<entity>>` | `-bookId: int`, `-code: String`, `-name: String`, `-author: String`, `-publicationYear: int`, `-coverPrice: double`, `-quantity: int`, `-barcode: String`, `-description: String` | `+getBookById(bookId: int): Book`, `+searchBook(key: String): List<Book>` |
| Reader | `<<entity>>` | `-readerId: int`, `-code: String`, `-name: String`, `-dob: Date`, `-address: String`, `-barcode: String` | `+getReaderById(readerId: int): Reader` |
| Loan | `<<entity>>` | `-loanId: int`, `-loanDate: Date`, `-readerId: int`, `-userId: int` | `+getLoanById(loanId: int): Loan` |
| LoanDetail | `<<entity>>` | `-detailId: int`, `-loanId: int`, `-bookId: int`, `-borrowDate: Date`, `-returnDate: Date`, `-fine: double` | `+countLoansByBook(start: Date, end: Date): List<Map>`, `+getDetailsByBookId(bookId: int): List<LoanDetail>` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | `+checkLogin(username: String, password: String): boolean` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-subStatistics: JButton`, `-subBorrow: JButton`, `-subReturn: JButton` |
| StatBorrowedFrm | `<<boundary>>` | `-inStartDate: JTextField`, `-inEndDate: JTextField`, `-subSearch: JButton`, `-outBookTable: JTable`, `-outDetailLabel: JLabel`, `-outDetailTable: JTable`, `-subExport: JButton`, `-subBack: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Reader → Loan) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent (Loan → LoanDetail) |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (StatBorrowedFrm → LoanDetailDAO) |

**6. Cách ghi multiplicity:**

| Multiplicity | Cách ghi trong VP | Ví dụ |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" ở một đầu | Loan có đúng 1 Reader |
| 0..* hoặc 1..* | Ghi "*" hoặc "1..*" ở đầu kia | Reader có nhiều Loan |
| 0..1 | Ghi "0..1" | LoanDetail có thể chưa có returnDate |

Ghi multiplicity ở cả 2 đầu của đường kết nối. Click vào đường → Properties → Source Multiplicity / Target Multiplicity.

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| Reader | Loan | Association | 1 → * | Mỗi bạn đọc có nhiều phiếu mượn |
| Loan | LoanDetail | Composition | 1 → * | Mỗi phiếu mượn có nhiều chi tiết. LoanDetail bị xóa khi Loan bị xóa |
| Book | LoanDetail | Association | * → 1 | Mỗi chi tiết mượn liên kết đến 1 sách |
| User | Loan | Association | 1 → * | Mỗi nhân viên xử lý nhiều phiếu mượn |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Loan → LoanDetail (1-n, Composition)*

1. Click chuột phải vào class Loan → chọn **Association** → kéo đến class LoanDetail.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Click vào đầu mũi tên ở phía LoanDetail → chọn **Composition** (filled diamond ◆).
5. Đặt tên association: `contains`.

*Ví dụ 2: Vẽ quan hệ Book → LoanDetail (1-n, Association)*

1. Click chuột phải vào class Book → chọn **Association** → kéo đến class LoanDetail.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `appears_in`.

### Classes diagram (analysis)

Analysis this module:

Enter the system -> The login interface is appeared -> need a class: LoginFrm
  input for username -> inUsername
  input for password -> inPassword
  a submit to login -> subLogin

Enter the username/password -> the system must check if the login is correct -> need a method:
  name: checkLogin()
  input: username, password (of the class User)
  output: boolean
  assign to the entity class: User.

Once login successful -> main interface appears -> need a class: HomeFrm
  an option to manage statistics -> subStatistics

Staff selects Statistics -> The statistics form appears -> need a class: StatBorrowedFrm
  input for start date -> inStartDate
  input for end date -> inEndDate
  a submit to search -> subSearch
  output for book statistics table -> outBookTable
  output for detail label -> outDetailLabel
  output for detail table -> outDetailTable
  a submit to export -> subExport
  a submit to go back -> subBack

Staff enters dates and clicks Search -> The system counts loans by book -> need a method:
  name: countLoansByBook()
  input: startDate (Date), endDate (Date)
  output: List<Map> (bookId, totalLoans)
  assign to the entity class: LoanDetail.

The system must get book info for each result -> need a method:
  name: getBookById()
  input: bookId (int)
  output: Book
  assign to the entity class: Book.

Staff clicks a book row -> The system shows borrowing details -> need a method:
  name: findBorrowingDetailsByBookId()
  input: bookId (int), startDate (Date), endDate (Date)
  output: List<Map> (readerName, borrowDate, returnDate, fine)
  assign to the entity class: LoanDetail.

The system must get reader info for each detail -> need a method:
  name: getReaderById()
  input: readerId (int)
  output: Reader
  assign to the entity class: Reader.

Staff clicks Export -> The system generates CSV -> need a method:
  name: exportCSV()
  input: data (List<Map>)
  output: String (filePath)
  assign to: no entity class (utility method).

### Summary
View classes: LoginFrm, HomeFrm, StatBorrowedFrm
Methods: checkLogin(), countLoansByBook(), getBookById(), findBorrowingDetailsByBookId(), getReaderById(), exportCSV()

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|------------|-------|
| LoginFrm | Form dang nhap cho nhan vien, nhap username va password |
| HomeFrm | Trang chinh sau khi dang nhap, chua menu dieu huong: Borrow, Return, Statistics |
| StatBorrowedFrm | Form thong ke sach muon, cho phep nhap khoang thoi gian, hien thi bang sach muon nhieu nhat va chi tiet khi click vao tung sach |

### Buoc 2: UI Elements

**LoginFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| inUsername | Input (TextField) | O nhap ten dang nhap |
| inPassword | Input (PasswordField) | O nhap mat khau |
| subLogin | Button (Submit) | Nut dang nhap |
| outError | Output (Label) | Thong bao loi khi dang nhap that bai |

**HomeFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| outWelcome | Output (Label) | Hien thi loi chao "Xin chao, [ten nhan vien]" |
| subBorrow | Button (Navigate) | Chuyen sang form muon sach |
| subReturn | Button (Navigate) | Chuyen sang form tra sach |
| subStatistics | Button (Navigate) | Chuyen sang menu thong ke |
| subLogout | Button (Navigate) | Dang xuat, quay ve LoginFrm |

**StatBorrowedFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| inStartDate | Input (DatePicker) | O chon ngay bat dau khoang thoi gian thong ke |
| inEndDate | Input (DatePicker) | O chon ngay ket thuc khoang thoi gian thong ke |
| subSearch | Button (Submit) | Nut tim kiem / thong ke |
| outBookTable | Output (Table) | Bang danh sach sach muon: Code, Book Title, Author, Barcode, Total Loans. Sap xep theo Total Loans giam dan |
| outDetailLabel | Output (Label) | Nhan hien thi "Detail: Borrowings of Book [code] - [title]" khi click vao mot dong sach |
| outDetailTable | Output (Table) | Bang chi tiet muon cua cuon sach duoc chon: Reader Name, Borrowed Day, Returned Day, Fine |
| subExport | Button (Submit) | Nut xuat du lieu ra file CSV |
| subBack | Button (Navigate) | Nut quay ve HomeFrm |

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `findByUsername(String username): User` | Tim user theo ten dang nhap |
| UserDAO | `validateLogin(String username, String password): User` | Xac thuc dang nhap |
| BookDAO | `findAll(): List<Book>` | Lay danh sach tat ca sach |
| BookDAO | `findById(int bookId): Book` | Tim sach theo ID |
| LoanDetailDAO | `countLoansByBook(Date startDate, Date endDate): List<Map>` | Dem so luot muon cua moi sach trong khoang thoi gian, tra ve danh sach {bookId, totalLoans} sap xep giam dan |
| LoanDetailDAO | `findBorrowingDetailsByBookId(int bookId, Date startDate, Date endDate): List<Map>` | Lay chi tiet cac lan muon cua mot cuon sach: readerName, borrowDate, returnDate, fine |
| LoanDetailDAO | `findByDateRange(Date startDate, Date endDate): List<LoanDetail>` | Lay tat ca chi tiet muon trong khoang thoi gian |
| ReaderDAO | `findById(int readerId): Reader` | Tim doc gia theo ID |

### Buoc 4: Entity Class Design

**User**
```
- userId: int
- fullName: String
- username: String
- password: String
```

**Reader**
```
- readerId: int
- fullName: String
- dateOfBirth: Date
- address: String
- barcode: String
```

**Book**
```
- bookId: int
- title: String
- author: String
- publicationYear: int
- coverPrice: double
- quantity: int
- barcode: String
- description: String
```

**Loan**
```
- loanId: int
- loanDate: Date
- readerId: int (FK)
- userId: int (FK)
- reader: Reader (object attribute)
- user: User (object attribute)
- loanDetails: List<LoanDetail> (object attribute)
```

**LoanDetail**
```
- detailId: int
- loanId: int (FK)
- bookId: int (FK)
- borrowDate: Date
- dueDate: Date
- returnDate: Date (nullable)
- fine: double (default 0)
- book: Book (object attribute)
- loan: Loan (object attribute)
```

### Buoc 5: Database Design

**tblUser**

| Column | Type | Constraint |
|--------|------|------------|
| userId | INT | PRIMARY KEY, AUTO_INCREMENT |
| fullName | VARCHAR(100) | NOT NULL |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(255) | NOT NULL |

**tblReader**

| Column | Type | Constraint |
|--------|------|------------|
| readerId | INT | PRIMARY KEY, AUTO_INCREMENT |
| fullName | VARCHAR(100) | NOT NULL |
| dateOfBirth | DATE | NOT NULL |
| address | VARCHAR(255) | |
| barcode | VARCHAR(50) | NOT NULL, UNIQUE |

**tblBook**

| Column | Type | Constraint |
|--------|------|------------|
| bookId | INT | PRIMARY KEY, AUTO_INCREMENT |
| title | VARCHAR(200) | NOT NULL |
| author | VARCHAR(100) | NOT NULL |
| publicationYear | INT | NOT NULL |
| coverPrice | DECIMAL(10,2) | NOT NULL |
| quantity | INT | NOT NULL, DEFAULT 0 |
| barcode | VARCHAR(50) | NOT NULL, UNIQUE |
| description | TEXT | NULLABLE |

**tblLoan**

| Column | Type | Constraint |
|--------|------|------------|
| loanId | INT | PRIMARY KEY, AUTO_INCREMENT |
| loanDate | DATE | NOT NULL |
| readerId | INT | FOREIGN KEY -> tblReader(readerId) |
| userId | INT | FOREIGN KEY -> tblUser(userId) |

**tblLoanDetail**

| Column | Type | Constraint |
|--------|------|------------|
| detailId | INT | PRIMARY KEY, AUTO_INCREMENT |
| loanId | INT | FOREIGN KEY -> tblLoan(loanId) |
| bookId | INT | FOREIGN KEY -> tblBook(bookId) |
| borrowDate | DATE | NOT NULL |
| dueDate | DATE | NOT NULL |
| returnDate | DATE | NULLABLE |
| fine | DECIMAL(10,2) | DEFAULT 0 |

### Buoc 6: Huong dan ve Class Diagram bang Visual Paradigm

1. Mo Visual Paradigm -> File -> New Project -> dat ten "LibraryManagement_StatBorrowed"
2. Vao menu Diagram -> Add Diagram -> Class Diagram
3. Tao 5 class:
   - Keo "Class" tu toolbar vao canvas, dat ten "User", them attributes: userId: int, fullName: String, username: String, password: String
   - Tao class "Reader" voi attributes: readerId: int, fullName: String, dateOfBirth: Date, address: String, barcode: String
   - Tao class "Book" voi attributes: bookId: int, title: String, author: String, publicationYear: int, coverPrice: double, quantity: int, barcode: String, description: String
   - Tao class "Loan" voi attributes: loanId: int, loanDate: Date, readerId: int, userId: int
   - Tao class "LoanDetail" voi attributes: detailId: int, loanId: int, bookId: int, borrowDate: Date, dueDate: Date, returnDate: Date, fine: double
4. Ve quan he:
   - Reader -> Loan: keo Association, multiplicity 1..* o Loan
   - Loan -> LoanDetail: keo Composition (filled diamond), multiplicity 1..* o LoanDetail
   - Book -> LoanDetail: keo Association, multiplicity 1..* o LoanDetail
   - User -> Loan: keo Association, multiplicity 1..* o Loan
5. Format: chon tat ca -> Format -> Auto Layout
6. Xuat: File -> Export -> PNG hoac PDF

---

## Cau 4: Sequence Diagram (1.5 diem)

### Huong dan ve bang Visual Paradigm

1. Mo Visual Paradigm -> Diagram -> Add Diagram -> Sequence Diagram
2. Tao cac lifelines:
   - Keo "Actor" vao, dat ten "Staff"
   - Keo "Boundary" vao, dat ten ":HomeFrm", ":StatBorrowedFrm"
   - Keo "Entity" vao, dat ten ":LoanDetailDAO", ":BookDAO"
3. Ve message tu tren xuong theo thu tu trong bang duoi
4. Su dung alt fragment cho phan kiem tra du lieu hop le
5. Su dung loop fragment cho phan hien thi tung dong sach
6. File -> Export -> PNG

### Sequence Diagram (Design Phase)

1. Staff chon "Statistics of Borrowed Books" tren HomeFrm.
2. HomeFrm tao `new StatBorrowedFrm()` va hien thi.
3. Staff nhap StartDate `01/01/2026`, EndDate `08/06/2026` va nhan nut Search.
4. `StatBorrowedFrm.actionPerformed()` duoc goi.
5. StatBorrowedFrm kiem tra du lieu nhap: startDate <= endDate va ca hai khong null. Neu khong hop le, hien thi loi va dung lai.
6. StatBorrowedFrm goi `LoanDetailDAO.countLoansByBook(startDate, endDate)`.
7. LoanDetailDAO thuc hien truy van SQL, dong goi ket qua vao `List<Map>` voi moi phan tu chua bookId va totalLoans, sap xep giam dan theo totalLoans.
8. StatBorrowedFrm voi moi ket qua, goi `BookDAO.getBookById(bookId)`.
9. BookDAO dong goi doi tuong Book voi cac thuoc tính title, author, barcode, coverPrice.
10. StatBorrowedFrm hien thi bang `outBookTable` voi cac dong: Code, Book Title, Author, Barcode, Total Loans.
11. Staff click vao mot dong sach (vi du B007). `StatBorrowedFrm` xu ly su kien mouse click.
12. StatBorrowedFrm goi `LoanDetailDAO.findBorrowingDetailsByBookId(bookId, startDate, endDate)`.
13. LoanDetailDAO thuc hien truy van JOIN tblLoanDetail, tblLoan, tblReader, dong goi ket qua vao `List<Map>` voi readerName, borrowDate, returnDate, fine.
14. StatBorrowedFrm cap nhat `outDetailLabel` voi noi dung "Detail: Borrowings of Book B007 - Thiet ke phan mem" va hien thi bang `outDetailTable` voi cot: Reader Name, Borrowed Day, Returned Day, Fine.

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| No. | Module | Test case |
|-----|--------|-----------|
| 1 | Statistics of Borrowed Books | Thong ke sach muon trong khoang thoi gian co du lieu |
| 2 | Statistics of Borrowed Books | Thong ke sach muon trong khoang thoi gian khong co du lieu |
| 3 | Statistics of Borrowed Books | Ngay bat dau lon hon ngay ket thuc |
| 4 | Statistics of Borrowed Books | Chua nhap du ngay |
| 5 | Statistics of Borrowed Books | Click vao sach de xem chi tiet muon |

### Test case No. 1 — Thong ke sach muon trong khoang thoi gian co du lieu

**Muc dich:** Kiem tra chuc nang thong ke sach muon khi nhap khoang thoi gian hop le co du lieu. Bang ket qua phai hien thi dung danh sach sach muon, sap xep theo so luot muon giam dan.

### Database truoc khi test

**tblUser**

| userId | fullName | username | password |
|--------|----------|----------|----------|
| 1 | Tran Van Admin | admin | admin123 |
| 2 | Le Thi Staff | staff01 | pass123 |

**tblReader**

| readerId | fullName | dateOfBirth | address | barcode |
|----------|----------|-------------|---------|---------|
| 10 | Tran Thi Binh | 1997-05-10 | 789 Cach Mang T8, Q3, TP.HCM | RDR-2024-0010 |
| 15 | Nguyen Minh Tuan | 1998-03-15 | 123 Le Loi, Q1, TP.HCM | RDR-2024-0015 |
| 18 | Pham Thi Lan | 1995-07-20 | 456 Nguyen Hue, Q1, TP.HCM | RDR-2024-0018 |
| 20 | Hoang Van Em | 2000-11-05 | 321 Vo Van Tan, Q3, TP.HCM | RDR-2024-0020 |

**tblBook**

| bookId | title | author | publicationYear | coverPrice | quantity | barcode | description |
|--------|-------|--------|-----------------|------------|----------|---------|-------------|
| 1 | Lap trinh Java | Nguyen Van A | 2020 | 450,000 | 10 | 978-0-13-468599-1 | Sach giao trinh lap trinh Java co ban |
| 3 | Co so du lieu | Tran Thi B | 2019 | 380,000 | 8 | 978-0-596-52068-7 | Sach giao trinh co so du lieu |
| 7 | Thiet ke phan mem | Le Van C | 2021 | 750,000 | 5 | 978-1-491-95035-7 | Sach thiet ke phan mem |
| 10 | Mang may tinh | Hoang Thi D | 2018 | 320,000 | 12 | 978-0-201-63361-0 | Sach giao trinh mang may tinh |

**tblLoan**

| loanId | loanDate | readerId | userId |
|--------|----------|----------|--------|
| 101 | 2026-01-10 | 10 | 2 |
| 102 | 2026-01-15 | 15 | 2 |
| 103 | 2026-02-01 | 18 | 2 |
| 104 | 2026-03-05 | 20 | 2 |
| 105 | 2026-04-01 | 15 | 2 |
| 106 | 2026-05-10 | 10 | 2 |

**tblLoanDetail**

| detailId | loanId | bookId | borrowDate | dueDate | returnDate | fine |
|----------|--------|--------|------------|---------|------------|------|
| 1 | 101 | 1 | 2026-01-10 | 2026-02-10 | 2026-02-05 | 0 |
| 2 | 101 | 3 | 2026-01-10 | 2026-02-10 | 2026-02-08 | 0 |
| 3 | 101 | 7 | 2026-01-10 | 2026-02-10 | 2026-03-15 | 150,000 |
| 4 | 102 | 1 | 2026-01-15 | 2026-02-15 | 2026-02-10 | 0 |
| 5 | 102 | 7 | 2026-01-15 | 2026-02-15 | 2026-02-12 | 0 |
| 6 | 103 | 3 | 2026-02-01 | 2026-03-01 | 2026-02-28 | 0 |
| 7 | 103 | 7 | 2026-02-01 | 2026-03-01 | 2026-03-05 | 76,000 |
| 8 | 104 | 1 | 2026-03-05 | 2026-04-05 | 2026-04-01 | 0 |
| 9 | 104 | 7 | 2026-03-05 | 2026-04-05 | 2026-04-10 | 150,000 |
| 10 | 105 | 7 | 2026-04-01 | 2026-05-01 | 2026-04-28 | 0 |
| 11 | 105 | 1 | 2026-04-01 | 2026-05-01 | 2026-04-30 | 0 |
| 12 | 105 | 3 | 2026-04-01 | 2026-05-01 | 2026-05-10 | 38,000 |
| 13 | 106 | 7 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 14 | 106 | 10 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 15 | 106 | 1 | 2026-05-10 | 2026-06-10 | 2026-06-05 | 0 |

### Kich ban test va ket qua mong doi

| Scenario | Expected result |
|----------|-----------------|
| 1. Mo ung dung | Giao dien dang nhap hien thi voi o Username, Password, nut Login |
| 2. Nhap Username `staff01`, Password `pass123`, nhan Login | Dang nhap thanh cong, hien thi HomeFrm |
| 3. Chon menu "Statistics", chon "Statistics of Borrowed Books" | StatBorrowedFrm hien thi voi o StartDate, EndDate, nut Search |
| 4. Nhap StartDate `01/01/2026`, EndDate `08/06/2026`, nhan Search | Bang ket qua hien thi 4 dong |
| 5. Kiem tra dong 1 | Code: B007, Title: Thiet ke phan mem, Author: Le Van C, Barcode: 978-1-491-95035-7, Total Loans: 6 |
| 6. Kiem tra dong 2 | Code: B001, Title: Lap trinh Java, Author: Nguyen Van A, Barcode: 978-0-13-468599-1, Total Loans: 5 |
| 7. Kiem tra dong 3 | Code: B003, Title: Co so du lieu, Author: Tran Thi B, Barcode: 978-0-596-52068-7, Total Loans: 3 |
| 8. Kiem tra dong 4 | Code: B010, Title: Mang may tinh, Author: Hoang Thi D, Barcode: 978-0-201-63361-0, Total Loans: 1 |
| 9. Kiem tra thu tu | Sap xep giam dan: B007(6) > B001(5) > B003(3) > B010(1) |
| 10. Click vao dong B007 | Panel chi tiet hien thi "Detail: Borrowings of Book B007 - Thiet ke phan mem" |
| 11. Kiem tra bang chi tiet | 6 dong hien thi: (1) Tran Thi Binh - 10/01/2026 - 15/03/2026 - 150,000. (2) Nguyen Minh Tuan - 15/01/2026 - 12/02/2026 - 0. (3) Pham Thi Lan - 01/02/2026 - 05/03/2026 - 76,000. (4) Hoang Van Em - 05/03/2026 - 10/04/2026 - 150,000. (5) Nguyen Minh Tuan - 01/04/2026 - 28/04/2026 - 0. (6) Tran Thi Binh - 10/05/2026 - NULL - 0 |

### Database sau khi test

Database khong thay doi. Day la chuc nang chi doc (read-only), khong sua doi du lieu.
