# Subject 03 — Library Management — Module "Statistics of Borrowed Books"

---

## Cau 1: Scenario (1.5 diem)

### Bang Scenario chinh

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1 | Staff dang nhap he thong | Username: `staff01`, Password: `123456` | LoginFrm: form nhap username, password, nut Login |
| 2 | Staff chon menu "Thong ke" | Click menu item "Statistics" | HomeFrm: thanh menu chinh voi cac muc: Borrow, Return, Statistics |
| 3 | Staff chon thong ke sach muon | Click "Statistics of Borrowed Books" | StatBorrowedFrm: form voi 2 o nhap ngay (StartDate, EndDate), nut Search, vung ket qua (an) |
| 4 | Staff nhap khoang thoi gian | StartDate: `01/01/2026`, EndDate: `08/06/2026` | StatBorrowedFrm: o StartDate va EndDate duoc dien, nut Search sang |
| 5 | Staff nhan nut tim kiem | Click "Search" | StatBorrowedFrm: bang ket qua hien thi danh sach sach muon xep theo so luot muon giam dan |
| 6 | He thong hien thi ket qua | — | StatBorrowedFrm: bang "Borrowed Books Statistics" voi cot: Code, Book Title, Author, Barcode, Total Loans. Dong dau: B007 - Thiet ke phan mem - Le Van C - 978-1-491-95035-7 - 15 luot. Dong 2: B001 - Lap trinh Java - Nguyen Van A - 978-0-13-468599-1 - 12 luot. Dong 3: B003 - Co so du lieu - Tran Thi B - 978-0-596-52068-7 - 8 luot. Tong: 3 dong |
| 7 | Staff click vao dong sach dau tien | Click dong B007 | StatBorrowedFrm: panel chi tiet phia duoi/phai hien thi "Detail: Borrowings of Book B007 - Thiet ke phan mem" |
| 8 | He thong hien thi chi tiet muon cua cuon sach | — | StatBorrowedFrm: bang "Borrowing Details" voi cot: Reader Name, Borrowed Day, Returned Day, Fine. Dong 1: Nguyen Minh Tuan - 15/01/2026 - 10/02/2026 - 0. Dong 2: Pham Thi Lan - 01/03/2026 - 05/04/2026 - 0. Dong 3: Hoang Van Em - 20/04/2026 - NULL (dang muon) - 0. Dong 4: Nguyen Minh Tuan - 10/05/2026 - 15/06/2026 - 150,000 (qua han). Tong: 4 dong |
| 9 | Staff click vao dong sach thu hai | Click dong B001 | StatBorrowedFrm: panel chi tiet cap nhat, hien thi bang "Borrowing Details" cho B001 - Lap trinh Java |
| 10 | He thong hien thi chi tiet muon cua B001 | — | StatBorrowedFrm: bang voi cot: Reader Name, Borrowed Day, Returned Day, Fine. Dong 1: Tran Thi Binh - 05/02/2026 - 01/03/2026 - 0. Dong 2: Le Van Cuong - 10/03/2026 - 08/04/2026 - 0. Tong: 12 dong |
| 11 | Staff thay doi khoang thoi gian | StartDate: `01/04/2026`, EndDate: `08/06/2026` | StatBorrowedFrm: o ngay duoc cap nhat |
| 12 | Staff nhan Search lai | Click "Search" | StatBorrowedFrm: bang ket qua cap nhat voi du lieu moi, xep theo luot muon giam dan |
| 13 | Staff nhan Export | Click "Export to CSV" | StatBorrowedFrm: dialog luu file, file CSV duoc tao |
| 14 | Staff quay ve trang chu | Click "Back" | StatBorrowedFrm dong, quay ve HomeFrm |

### Kich ban ngoai le

**Ngoai le 1: Khoang thoi gian khong hop le (StartDate > EndDate)**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff nhap ngay sai | StartDate: `08/06/2026`, EndDate: `01/01/2026` | StatBorrowedFrm: o nhap duoc dien |
| 5 | Staff nhan Search | Click "Search" | StatBorrowedFrm: thong bao loi "Ngay bat dau phai nho hon hoac bang ngay ket thuc." Khong hien thi ket qua |

**Ngoai le 2: Khong co sach muon trong khoang thoi gian**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff nhap khoang thoi gian khong co du lieu | StartDate: `01/01/2025`, EndDate: `31/12/2025` | — |
| 5 | Staff nhan Search | Click "Search" | StatBorrowedFrm: bang ket qua trong. Thong bao "Khong co du lieu muon sach trong khoang thoi gian nay." |

**Ngoai le 3: Staff chua nhap du ngay**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff chi nhap StartDate | StartDate: `01/01/2026`, EndDate: (trong) | StatBorrowedFrm |
| 5 | Staff nhan Search | Click "Search" | StatBorrowedFrm: thong bao loi "Vui long nhap day du ngay bat dau va ngay ket thuc." |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thu vien cho phep nhan vien thong ke so luot muon sach trong mot khoang thoi gian. Moi cuon sach co thong tin ma sach, ten sach, tac gia, ma vach va gia bia. Moi lan muon tao ra mot phieu muon (Loan) lien ket voi mot doc gia va mot nhan vien. Moi phieu muon chua nhieu chi tiet muon (LoanDetail), moi chi tiet tuong ung voi mot cuon sach cu the voi ngay muon, ngay hen tra, ngay tra thuc te va tien phat neu tra muon. Doc gia co thong tin ma, ho ten, ngay sinh, dia chi va ma vach the. Nhan vien (User) thuc hien cac thao tac tren he thong. Chuc nang thong ke cho phep xem danh sach sach duoc muon nhieu nhat va chi tiet cac lan muon cua tung cuon sach.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Nhan vien (User) | Entity | Doi tuong thuc hien thao tac, co thuoc tính rieng (ten, tai khoan) |
| Doc gia (Reader) | Entity | Doi tuong trung tam, co nhieu thuoc tính (ma, ten, ngay sinh, dia chi, ma vach) |
| Sach (Book) | Entity | Doi tuong chinh cua thong ke, co thuoc tính (ma, ten, tac gia, ma vach, gia bia) |
| Phieu muon (Loan) | Entity | Dai dien cho mot lan muon, co ngay muon, lien ket voi doc gia va nhan vien |
| Chi tiet muon (LoanDetail) | Entity | Lien ket giua phieu muon va tung cuon sach, co ngay muon, ngay hen tra, ngay tra, tien phat |
| Ma sach (Book Code) | Thuoc tính | Thuoc tính cua Book |
| Ten sach (Book Title) | Thuoc tính | Thuoc tính cua Book |
| Tac gia (Author) | Thuoc tính | Thuoc tính cua Book |
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
+----------+ 1    N +--------+ 1    N +------------+ N    1 +--------+
|  Reader  |--------|  Loan  |--------| LoanDetail |--------|  Book  |
+----------+        +--------+        +------------+        +--------+
| readerId |        | loanId |        | detailId   |        | bookId |
| fullName |        | loanDate        | loanId     |        | title  |
| dob      |        | readerId|        | bookId     |        | author |
| address  |        | userId |        | borrowDate |        | barcode|
| barcode  |        +--------+        | dueDate    |        | coverP |
+----------+             |            | returnDate |        +--------+
                         1            | fine       |
                         |            +------------+
                         N
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
- barcode: String
- coverPrice: double
```

**Loan**
```
- loanId: int
- loanDate: Date
- readerId: int (FK)
- userId: int (FK)
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
| barcode | VARCHAR(50) | NOT NULL, UNIQUE |
| coverPrice | DECIMAL(10,2) | NOT NULL |

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
   - Tao class "Book" voi attributes: bookId: int, title: String, author: String, barcode: String, coverPrice: double
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
   - Keo "Boundary" vao, dat ten ":StatBorrowedFrm"
   - Keo "Control" vao, dat ten ":StatBorrowedControl"
   - Keo "Entity" vao, dat ten ":LoanDetailDAO", ":BookDAO", ":ReaderDAO"
3. Ve message tu tren xuong theo thu tu trong bang duoi
4. Su dung alt/opt fragment cho phan kiem tra du lieu hop le
5. Su dung loop fragment cho phan hien thi tung dong sach
6. File -> Export -> PNG

### ASCII Sequence Diagram

```
Staff    :StatBorrowedFrm    :StatBorrowedControl    :LoanDetailDAO    :BookDAO    :ReaderDAO
  |              |                     |                    |              |            |
  |---selectMenu()-->|                 |                    |              |            |
  |              |---showForm()-->     |                    |              |            |
  |<--displayForm----|                 |                    |              |            |
  |              |                     |                    |              |            |
  |---enterDates(start,end)-->         |                    |              |            |
  |              |                     |                    |              |            |
  |---clickSearch-->|                  |                    |              |            |
  |              |---searchBooks(start,end)-->               |              |            |
  |              |                     |---validateDates(start,end)       |            |
  |              |                     |  [if invalid: return error]      |            |
  |              |                     |  [if valid: continue]            |            |
  |              |                     |                    |              |            |
  |              |                     |---countLoansByBook(start,end)--> |            |
  |              |                     |<--List<Map>(bookId,totalLoans)---|            |
  |              |                     |                    |              |            |
  |              |                     |---getBookById(bookId)----------->|            |
  |              |                     |<--Book----------------------------|            |
  |              |                     |  [loop for each book]            |            |
  |              |                     |                    |              |            |
  |              |<--BookList----------|                    |              |            |
  |<--displayBookTable--|              |                    |              |            |
  |              |                     |                    |              |            |
  |---clickBookRow(bookId)-->          |                    |              |            |
  |              |---getBookDetail(bookId,start,end)-->      |              |            |
  |              |                     |---findBorrowingDetails(bookId,start,end)-->   |
  |              |                     |<--List<Map>(readerName,borrowDate,returnDate,fine)|
  |              |                     |                    |              |            |
  |              |                     |---getReaderById(readerId)--------------->      |
  |              |                     |<--Reader--------------------------------------|
  |              |                     |  [loop for each detail]            |            |
  |              |                     |                    |              |            |
  |              |<--DetailList--------|                    |              |            |
  |<--displayDetailTable--|            |                    |              |            |
  |              |                     |                    |              |            |
  |---clickExport-->|                  |                    |              |            |
  |              |---exportCSV()-->    |                    |              |            |
  |              |                     |---generateCSV(data)              |            |
  |              |<--filePath----------|                    |              |            |
  |<--showSaveDialog--|                |                    |              |            |
  |              |                     |                    |              |            |
  |---clickBack-->|                    |                    |              |            |
  |              |---close()-->        |                    |              |            |
  |<--returnToHome--|                  |                    |              |            |
```

### Bang giai thich chi tiet

| # | Message | Tu | Den | Mo ta |
|---|---------|-----|-----|-------|
| 1 | selectMenu() | Staff | StatBorrowedFrm | Staff chon menu "Statistics of Borrowed Books" tu HomeFrm |
| 2 | showForm() | StatBorrowedFrm | StatBorrowedControl | Khoi tao controller, hien thi form thong ke |
| 3 | displayForm | StatBorrowedFrm | Staff | Hien thi form voi 2 o DatePicker (StartDate, EndDate) va nut Search |
| 4 | enterDates("01/01/2026", "08/06/2026") | Staff | StatBorrowedFrm | Staff nhap khoang thoi gian: StartDate = 01/01/2026, EndDate = 08/06/2026 |
| 5 | clickSearch | Staff | StatBorrowedFrm | Staff nhan nut Search |
| 6 | searchBooks(startDate, endDate) | StatBorrowedFrm | StatBorrowedControl | Gui yeu cau thong ke voi khoang thoi gian |
| 7 | validateDates(startDate, endDate) | StatBorrowedControl | StatBorrowedControl | Kiem tra: startDate <= endDate va ca hai khong null |
| 8 | countLoansByBook("2026-01-01", "2026-06-08") | StatBorrowedControl | LoanDetailDAO | Truy van SQL: SELECT bookId, COUNT(*) as totalLoans FROM tblLoanDetail WHERE borrowDate BETWEEN '2026-01-01' AND '2026-06-08' GROUP BY bookId ORDER BY totalLoans DESC |
| 9 | return List<Map> | LoanDetailDAO | StatBorrowedControl | Tra ve: [{bookId:7, totalLoans:15}, {bookId:1, totalLoans:12}, {bookId:3, totalLoans:8}] |
| 10 | getBookById(7) | StatBorrowedControl | BookDAO | Lay thong tin sach B007 |
| 11 | return Book | BookDAO | StatBorrowedControl | Tra ve: Book{bookId:7, title:"Thiet ke phan mem", author:"Le Van C", barcode:"978-1-491-95035-7"} |
| 12 | getBookById(1) | StatBorrowedControl | BookDAO | Lay thong tin sach B001 |
| 13 | return Book | BookDAO | StatBorrowedControl | Tra ve: Book{bookId:1, title:"Lap trinh Java", author:"Nguyen Van A"} |
| 14 | getBookById(3) | StatBorrowedControl | BookDAO | Lay thong tin sach B003 |
| 15 | return Book | BookDAO | StatBorrowedControl | Tra ve: Book{bookId:3, title:"Co so du lieu", author:"Tran Thi B"} |
| 16 | BookList(bookList) | StatBorrowedControl | StatBorrowedFrm | Gui danh sach ket qua: [{code:"B007", title:"Thiet ke phan mem", author:"Le Van C", barcode:"978-1-491-95035-7", totalLoans:15}, ...] |
| 17 | displayBookTable | StatBorrowedFrm | Staff | Hien thi bang voi 3 dong, sap xep theo totalLoans giam dan |
| 18 | clickBookRow(bookId=7) | Staff | StatBorrowedFrm | Staff click vao dong sach B007 - Thiet ke phan mem |
| 19 | getBookDetail(7, startDate, endDate) | StatBorrowedFrm | StatBorrowedControl | Yeu cau chi tiet muon cua sach B007 |
| 20 | findBorrowingDetails(7, "2026-01-01", "2026-06-08") | StatBorrowedControl | LoanDetailDAO | Truy van: SELECT r.fullName, ld.borrowDate, ld.returnDate, ld.fine FROM tblLoanDetail ld JOIN tblLoan l ON ld.loanId=l.loanId JOIN tblReader r ON l.readerId=r.readerId WHERE ld.bookId=7 AND ld.borrowDate BETWEEN '2026-01-01' AND '2026-06-08' |
| 21 | return List<Map> | LoanDetailDAO | StatBorrowedControl | Tra ve 4 ket qua: [{readerName:"Nguyen Minh Tuan", borrowDate:"2026-01-15", returnDate:"2026-02-10", fine:0}, {readerName:"Pham Thi Lan", borrowDate:"2026-03-01", returnDate:"2026-04-05", fine:0}, {readerName:"Hoang Van Em", borrowDate:"2026-04-20", returnDate:null, fine:0}, {readerName:"Nguyen Minh Tuan", borrowDate:"2026-05-10", returnDate:"2026-06-15", fine:150000}] |
| 22 | getReaderById(readerId) | StatBorrowedControl | ReaderDAO | Lay thong tin doc gia (neu can bo sung) |
| 23 | return Reader | ReaderDAO | StatBorrowedControl | Tra ve thong tin doc gia |
| 24 | DetailList(detailList) | StatBorrowedControl | StatBorrowedFrm | Gui danh sach chi tiet muon |
| 25 | displayDetailTable | StatBorrowedFrm | Staff | Hien thi bang chi tiet: 4 dong voi Reader Name, Borrowed Day, Returned Day, Fine |
| 26 | clickExport | Staff | StatBorrowedFrm | Staff nhan nut Export to CSV |
| 27 | exportCSV() | StatBorrowedFrm | StatBorrowedControl | Yeu cau xuat du lieu |
| 28 | generateCSV(data) | StatBorrowedControl | StatBorrowedControl | Tao noi dung CSV tu du lieu bang |
| 29 | return filePath | StatBorrowedControl | StatBorrowedFrm | Tra ve duong dan file CSV |
| 30 | showSaveDialog | StatBorrowedFrm | Staff | Hien thi dialog luu file |
| 31 | clickBack | Staff | StatBorrowedFrm | Staff nhan nut Back |
| 32 | close() | StatBorrowedFrm | StatBorrowedControl | Dong form, giai phong tai nguyen |
| 33 | returnToHome | StatBorrowedFrm | Staff | Quay ve HomeFrm |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Test Case | Input | Expected Output |
|----|-----------|-------|-----------------|
| TC01 | Thong ke sach muon trong khoang thoi gian co du lieu | StartDate: 01/01/2026, EndDate: 08/06/2026 | Bang hien thi 3 sach, sap xep theo luot muon giam dan |
| TC02 | Thong ke sach muon trong khoang thoi gian khong co du lieu | StartDate: 01/01/2025, EndDate: 31/12/2025 | Bang trong, thong bao "Khong co du lieu" |
| TC03 | Ngay bat dau lon hon ngay ket thuc | StartDate: 08/06/2026, EndDate: 01/01/2026 | Thong bao loi "Ngay bat dau phai nho hon hoac bang ngay ket thuc" |
| TC04 | Chua nhap ngay | StartDate: (trong), EndDate: (trong) | Thong bao loi "Vui long nhap day du ngay bat dau va ngay ket thuc" |
| TC05 | Click vao sach de xem chi tiet | Click dong B007 | Bang chi tiet hien thi 4 lan muon cua B007 |

### TC01: Thong ke sach muon trong khoang thoi gian co du lieu - Chi tiet

**Muc dich:** Kiem tra chuc nang thong ke sach muon khi nhap khoang thoi gian hop le co du lieu. Bang ket qua phai hien thi dung danh sach sach muon, sap xep theo so luot muon giam dan, voi day du thong tin code, title, author, barcode, total loans.

**Database truoc khi test:**

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

| bookId | title | author | barcode | coverPrice |
|--------|-------|--------|---------|------------|
| 1 | Lap trinh Java | Nguyen Van A | 978-0-13-468599-1 | 450,000 |
| 3 | Co so du lieu | Tran Thi B | 978-0-596-52068-7 | 380,000 |
| 7 | Thiet ke phan mem | Le Van C | 978-1-491-95035-7 | 750,000 |
| 10 | Mang may tinh | Hoang Thi D | 978-0-201-63361-0 | 320,000 |

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

**Kich ban test:**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|-----------------|
| 1 | Mo ung dung, dang nhap | Username: `staff01`, Password: `pass123` | Dang nhap thanh cong, hien thi HomeFrm |
| 2 | Chon menu "Statistics" | Click "Statistics" | Hien thi menu con thong ke |
| 3 | Chon "Statistics of Borrowed Books" | Click "Statistics of Borrowed Books" | Hien thi StatBorrowedFrm voi o StartDate, EndDate, nut Search |
| 4 | Nhap ngay bat dau | StartDate: `01/01/2026` | O StartDate hien thi 01/01/2026 |
| 5 | Nhap ngay ket thuc | EndDate: `08/06/2026` | O EndDate hien thi 08/06/2026 |
| 6 | Nhan Search | Click "Search" | Bang ket qua hien thi 4 dong |
| 7 | Kiem tra dong 1 | Xem dong dau tien | Code: B007, Title: Thiet ke phan mem, Author: Le Van C, Barcode: 978-1-491-95035-7, Total Loans: 6 (cac detailId: 3,5,7,9,10,13) |
| 8 | Kiem tra dong 2 | Xem dong thu hai | Code: B001, Title: Lap trinh Java, Author: Nguyen Van A, Barcode: 978-0-13-468599-1, Total Loans: 5 (cac detailId: 1,4,8,11,15) |
| 9 | Kiem tra dong 3 | Xem dong thu ba | Code: B003, Title: Co so du lieu, Author: Tran Thi B, Barcode: 978-0-596-52068-7, Total Loans: 3 (cac detailId: 2,6,12) |
| 10 | Kiem tra dong 4 | Xem dong thu tu | Code: B010, Title: Mang may tinh, Author: Hoang Thi D, Barcode: 978-0-201-63361-0, Total Loans: 1 (detailId: 14) |
| 11 | Kiem tra thu tu | Xem thu tu cac dong | Sap xep giam dan: B007(6) > B001(5) > B003(3) > B010(1) |
| 12 | Click vao dong B007 | Click dong dau tien | Panel chi tiet hien thi "Detail: Borrowings of Book B007 - Thiet ke phan mem" |
| 13 | Kiem tra bang chi tiet | Xem bang Borrowing Details | 6 dong: (1) Tran Thi Binh - 10/01/2026 - 15/03/2026 - 150,000. (2) Nguyen Minh Tuan - 15/01/2026 - 12/02/2026 - 0. (3) Pham Thi Lan - 01/02/2026 - 05/03/2026 - 76,000. (4) Hoang Van Em - 05/03/2026 - 10/04/2026 - 150,000. (5) Nguyen Minh Tuan - 01/04/2026 - 28/04/2026 - 0. (6) Tran Thi Binh - 10/05/2026 - NULL - 0 |
| 14 | Click dong B001 | Click dong thu hai | Panel chi tiet cap nhat cho B001 - Lap trinh Java |
| 15 | Kiem tra bang chi tiet B001 | Xem bang | 5 dong hien thi thong tin muon cua B001 |

**Database sau khi test:**

**tblUser** (khong thay doi)

| userId | fullName | username | password |
|--------|----------|----------|----------|
| 1 | Tran Van Admin | admin | admin123 |
| 2 | Le Thi Staff | staff01 | pass123 |

**tblReader** (khong thay doi)

| readerId | fullName | dateOfBirth | address | barcode |
|----------|----------|-------------|---------|---------|
| 10 | Tran Thi Binh | 1997-05-10 | 789 Cach Mang T8, Q3, TP.HCM | RDR-2024-0010 |
| 15 | Nguyen Minh Tuan | 1998-03-15 | 123 Le Loi, Q1, TP.HCM | RDR-2024-0015 |
| 18 | Pham Thi Lan | 1995-07-20 | 456 Nguyen Hue, Q1, TP.HCM | RDR-2024-0018 |
| 20 | Hoang Van Em | 2000-11-05 | 321 Vo Van Tan, Q3, TP.HCM | RDR-2024-0020 |

**tblBook** (khong thay doi)

| bookId | title | author | barcode | coverPrice |
|--------|-------|--------|---------|------------|
| 1 | Lap trinh Java | Nguyen Van A | 978-0-13-468599-1 | 450,000 |
| 3 | Co so du lieu | Tran Thi B | 978-0-596-52068-7 | 380,000 |
| 7 | Thiet ke phan mem | Le Van C | 978-1-491-95035-7 | 750,000 |
| 10 | Mang may tinh | Hoang Thi D | 978-0-201-63361-0 | 320,000 |

**tblLoan** (khong thay doi)

| loanId | loanDate | readerId | userId |
|--------|----------|----------|--------|
| 101 | 2026-01-10 | 10 | 2 |
| 102 | 2026-01-15 | 15 | 2 |
| 103 | 2026-02-01 | 18 | 2 |
| 104 | 2026-03-05 | 20 | 2 |
| 105 | 2026-04-01 | 15 | 2 |
| 106 | 2026-05-10 | 10 | 2 |

**tblLoanDetail** (khong thay doi - day la chuc nang chi doc, khong sua database)

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
