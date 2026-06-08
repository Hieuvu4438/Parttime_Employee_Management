# Subject 02 — Library Management — Module "Returning of Books"

---

## Cau 1: Scenario (1.5 diem)

### Bang Scenario chinh

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1 | Staff dang nhap he thong | Username: `staff01`, Password: `123456` | LoginFrm: form nhap username, password, nut Login |
| 2 | Staff chon menu "Tra sach" | Click menu item "Returning Books" | HomeFrm: thanh menu chinh voi cac muc: Borrow, Return, Statistics |
| 3 | He thong hien thi form tra sach | — | ReturnBookFrm: o nhap ma the doc gia (ReaderBarcode), nut Scan, vung thong tin doc gia (an), bang sach muon (an) |
| 4 | Staff quet ma the doc gia | Reader Barcode: `RDR-2024-0015` | ReturnBookFrm: o nhap duoc dien tu dong sau khi quet |
| 5 | He thong hien thi thong tin doc gia + danh sach sach dang muon | Reader: Nguyen Minh Tuan, DOB: 15/03/1998, Address: 123 Le Loi, Q1, TP.HCM. Sach dang muon: 3 cuon (B001, B003, B007) | ReturnBookFrm: vung thong tin hien ten, ngay sinh, dia chi. Bang "Borrowed Books" hien thi: Code, Title, Author, Barcode, BorrowDate, DueDate. Bang "Returned Books" trong |
| 6 | Staff quet ma vach cuon sach tra dau tien | Book Barcode: `978-0-13-468599-1` (B001 - Lap trinh Java, Tac gia: Nguyen Van A) | ReturnBookFrm: sach B001 chuyen tu bang "Borrowed" sang bang "Returned", ngay tra dien tu dong la hom nay (08/06/2026), cot Fine hien thi 0 VND |
| 7 | Staff quet cuon sach tra thu hai | Book Barcode: `978-0-596-52068-7` (B003 - Co so du lieu, Tac gia: Tran Thi B) | ReturnBookFrm: bang "Borrowed" con 1 cuon (B007). Bang "Returned" co 2 cuon. Cuon B003 tra dung han, fine = 0 |
| 8 | Staff quet cuon sach tra thu ba | Book Barcode: `978-1-491-95035-7` (B007 - Thiet ke phan mem, Tac gia: Le Van C) | ReturnBookFrm: bang "Borrowed" trong (het sach muon). Bang "Returned" co 3 cuon. Cuon B007 qua han 5 ngay, fine = 150,000 VND (gia bia 750,000 x 20%) |
| 9 | He thong tu dong hien thi tong hop | Tong phat: 150,000 VND | ReturnBookFrm: nut Submit bat sang. Label "Total Fine: 150,000 VND" hien thi mau do. Nut "Print Loan Slip" va "Print Penalty Slip" xuat hien |
| 10 | Staff nhan nut Submit | Click "Submit Return" | ReturnBookFrm: dialog xac nhan "Xac nhan tra 3 cuon sach cho doc gia Nguyen Minh Tuan?" |
| 11 | Staff xac nhan tra | Click "Yes" | ReturnBookFrm: thong bao "Tra sach thanh cong!" He thong cap nhat database |
| 12 | Staff in phieu phat | Click "Print Penalty Slip" | PenaltySlipFrm: phieu in chua ma phieu: PN-20260608-001, ten doc gia, ma the, danh sach sach qua han, tong tien phat |
| 13 | Staff in phieu muon (con sach tren muon) | Click "Print Loan Slip" | LoanSlipFrm: phieu in chua ma phieu muon, danh sach sach con dang muon (neu co). O truong hop nay khong co sach con muon nen khong in |
| 14 | Staff nhan Done / Quay ve | Click "Done" | ReturnBookFrm dong, quay ve HomeFrm |

### Kich ban ngoai le

**Ngoai le 1: The doc gia khong hop le**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu buoc 1-3 o kich ban chinh | — | — |
| 4 | Staff quet ma the khong ton tai | Reader Barcode: `RDR-9999-XXXX` | ReturnBookFrm: thong bao loi mau do "Ma the doc gia khong ton tai. Vui long kiem tra lai." |
| 5 | Staff quet lai ma the dung | Reader Barcode: `RDR-2024-0015` | Tiep tuc tu buoc 5 kich ban chinh |

**Ngoai le 2: Doc gia khong co sach dang muon**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff quet ma the doc gia da tra het sach | Reader Barcode: `RDR-2024-0022` | ReturnBookFrm: hien thi thong tin doc gia. Bang "Borrowed Books" trong. Thong bao "Doc gia nay khong co sach dang muon." Nut Submit bi disable |

**Ngoai le 3: Sach quet khong thuoc danh sach muon cua doc gia**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-5 | Tuong tu buoc 1-5 kich ban chinh | — | — |
| 6 | Staff quet sach khong thuoc phieu muon | Book Barcode: `978-0-201-63361-0` (B010) | ReturnBookFrm: thong bao loi "Sach nay khong thuoc danh sach muon cua doc gia Nguyen Minh Tuan." Sach khong duoc them vao bang "Returned" |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thu vien cho phep nhan vien quan ly viec muon va tra sach. Moi doc gia co thong tin ca nhan gom ma doc gia, ho ten, ngay sinh, dia chi va ma vach the. Doc gia co the muon nhieu sach, moi lan muon tao ra mot phieu muon (Loan) chua thong tin ngay muon va danh sach cac cuon sach muon. Moi cuon sach co thong tin ma sach, ten sach, tac gia, ma vach, gia bia. Moi phieu muon co the chua nhieu chi tiet muon (LoanDetail), moi chi tiet tuong ung voi mot cuon sach cu the voi ngay muon, ngay hen tra, ngay tra thuc te va tien phat neu tra muon. He thong gioi han moi nguoi muon toi da 5 cuon sach trong thoi han 1 thang. Neu tra muon, tien phat bang 20% gia bia cuon sach. Nhan vien (User) la nguoi thuc hien cac thao tac muon tra tren he thong.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Nhan vien (Staff/User) | Entity | Doi tuong thuc hien thao tac, co thuoc tính rieng (ten, tai khoan) |
| Doc gia (Reader) | Entity | Doi tuong trung tam, co nhieu thuoc tính (ma, ten, ngay sinh, dia chi, ma vach) |
| Sach (Book) | Entity | Doi tuong chinh, co thuoc tính (ma, ten, tac gia, ma vach, gia bia) |
| Phieu muon (Loan) | Entity | Dai dien cho mot lan muon, co ngay muon, lien ket voi doc gia va nhan vien |
| Chi tiet muon (LoanDetail) | Entity | Lien ket giua phieu muon va tung cuon sach, co ngay muon, ngay hen tra, ngay tra, tien phat |
| Ma doc gia (Reader Barcode) | Thuoc tính | Thuoc tính cua Reader |
| Ma vach sach (Book Barcode) | Thuoc tính | Thuoc tính cua Book |
| Ngay muon (BorrowDate) | Thuoc tính | Thuoc tính cua LoanDetail |
| Ngay hen tra (DueDate) | Thuoc tính | Thuoc tính cua LoanDetail |
| Ngay tra thuc te (ReturnDate) | Thuoc tính | Thuoc tính cua LoanDetail |
| Tien phat (Fine) | Thuoc tính | Thuoc tính cua LoanDetail |
| Gia bia (CoverPrice) | Thuoc tính | Thuoc tính cua Book |
| He thong (System) | Actor | Khong phai entity, la moi truong |
| Phieu phat (Penalty Slip) | Output | Khong phai entity, la ket qua in ra tu LoanDetail co fine > 0 |

### Buoc 3: Xac dinh quan he

1. **Reader - Loan**: Mot doc gia co the co nhieu phieu muon (1-N). Moi phieu muon thuoc ve dung mot doc gia.
2. **Loan - LoanDetail**: Mot phieu muon chua nhieu chi tiet muon (1-N). Moi chi tiet thuoc ve dung mot phieu muon. Quan he composition vi LoanDetail khong ton tai neu khong co Loan.
3. **Book - LoanDetail**: Mot cuon sach co the xuat hien trong nhieu chi tiet muon qua cac lan khac nhau (1-N). Moi chi tiet muon lien ket voi dung mot cuon sach.
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
| Loan -> LoanDetail | 1-N (Composition) | Moi phieu muon chua 1 den 5 chi tiet muon (toi da 5 sach/nguoi). LoanDetail bi xoa khi Loan bi xoa |
| Book -> LoanDetail | 1-N | Mot cuon sach co the duoc muon nhieu lan boi nhieu doc gia khac nhau qua cac phieu muon khac nhau |
| User -> Loan | 1-N | Moi nhan vien co the xu ly nhieu phieu muon/tra khac nhau |
| Reader -> LoanDetail | Gian tiep (qua Loan) | Doc gia lien ket voi chi tiet muon thong qua phieu muon |
| User -> LoanDetail | Gian tiep (qua Loan) | Nhan vien lien ket voi chi tiet muon thong qua phieu muon |

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|------------|-------|
| LoginFrm | Form dang nhap cho nhan vien, nhap username va password |
| HomeFrm | Trang chinh sau khi dang nhap, chua menu dieu huong: Borrow, Return, Statistics |
| ReturnBookFrm | Form tra sach chinh, cho phep quet ma the doc gia, hien thi thong tin doc gia, danh sach sach muon/tra, nut submit va in phieu |
| PenaltySlipFrm | Form hien thi va in phieu phat cho sach tra muon |
| LoanSlipFrm | Form hien thi va in phieu muon (khi con sach dang muon) |

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

**ReturnBookFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| inReaderBarcode | Input (TextField) | O nhap/quet ma vach the doc gia |
| subScanReader | Button (Submit) | Nut quet/tra cuu thong tin doc gia |
| outReaderName | Output (Label) | Hien thi ten doc gia |
| outReaderDOB | Output (Label) | Hien thi ngay sinh doc gia |
| outReaderAddress | Output (Label) | Hien thi dia chi doc gia |
| outReaderBarcode | Output (Label) | Hien thi ma vach doc gia |
| inBookBarcode | Input (TextField) | O nhap/quet ma vach sach tra |
| subScanBook | Button (Submit) | Nut quet sach tra |
| outBorrowedTable | Output (Table) | Bang hien thi danh sach sach dang muon: Code, Title, Author, Barcode, BorrowDate, DueDate |
| outReturnedTable | Output (Table) | Bang hien thi danh sach sach da quet tra: Code, Title, Author, Barcode, BorrowDate, DueDate, ReturnDate, Fine |
| outTotalFine | Output (Label) | Hien thi tong tien phat |
| subSubmit | Button (Submit) | Nut xac nhan tra sach |
| subPrintPenalty | Button (Navigate) | Nut in phieu phat |
| subPrintLoan | Button (Navigate) | Nut in phieu muon (khi con sach muon) |
| subDone | Button (Navigate) | Nut hoan tat, quay ve HomeFrm |

**PenaltySlipFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| outSlipCode | Output (Label) | Ma phieu phat |
| outReaderName | Output (Label) | Ten doc gia |
| outReaderBarcode | Output (Label) | Ma vach doc gia |
| outLoanBarcode | Output (Label) | Ma phieu muon |
| outFineTable | Output (Table) | Bang sach qua han: Code, Title, Author, Barcode, BorrowDate, DueDate, ReturnDate, Fine |
| outTotalFine | Output (Label) | Tong tien phat |
| subPrint | Button (Submit) | Nut in phieu |

**LoanSlipFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| outSlipCode | Output (Label) | Ma phieu muon |
| outReaderName | Output (Label) | Ten doc gia |
| outReaderBarcode | Output (Label) | Ma vach doc gia |
| outLoanTable | Output (Table) | Bang sach con muon: Code, Title, Author, Barcode, BorrowDate, DueDate |
| subPrint | Button (Submit) | Nut in phieu |

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `findByUsername(String username): User` | Tim user theo ten dang nhap |
| UserDAO | `validateLogin(String username, String password): User` | Xac thuc dang nhap, tra ve User hoac null |
| ReaderDAO | `findByBarcode(String barcode): Reader` | Tim doc gia theo ma vach the |
| BookDAO | `findByBarcode(String barcode): Book` | Tim sach theo ma vach |
| BookDAO | `findById(int bookId): Book` | Tim sach theo ID |
| LoanDAO | `findActiveLoanByReaderId(int readerId): Loan` | Tim phieu muon dang hoat dong (chua tra het) cua doc gia |
| LoanDAO | `findById(int loanId): Loan` | Tim phieu muon theo ID |
| LoanDAO | `insert(Loan loan): int` | Tao phieu muon moi, tra ve loanId |
| LoanDetailDAO | `findUnreturnedByLoanId(int loanId): List<LoanDetail>` | Tim danh sach sach chua tra trong mot phieu muon |
| LoanDetailDAO | `findByLoanId(int loanId): List<LoanDetail>` | Tim tat ca chi tiet muon theo phieu muon |
| LoanDetailDAO | `returnBook(int detailId, Date returnDate, double fine): boolean` | Cap nhat ngay tra va tien phat cho mot chi tiet muon |
| LoanDetailDAO | `insert(LoanDetail detail): int` | Tao chi tiet muon moi |

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

1. Mo Visual Paradigm -> File -> New Project -> dat ten "LibraryManagement_Returning"
2. Vao menu Diagram -> Add Diagram -> Class Diagram
3. Tao 5 class:
   - Keo "Class" tu toolbar vao canvas, dat ten "User", them attributes: userId: int, fullName: String, username: String, password: String
   - Tao class "Reader" voi attributes: readerId: int, fullName: String, dateOfBirth: Date, address: String, barcode: String
   - Tao class "Book" voi attributes: bookId: int, title: String, author: String, barcode: String, coverPrice: double
   - Tao class "Loan" voi attributes: loanId: int, loanDate: Date, readerId: int, userId: int
   - Tao class "LoanDetail" voi attributes: detailId: int, loanId: int, bookId: int, borrowDate: Date, dueDate: Date, returnDate: Date, fine: double
4. Ve quan he:
   - Reader -> Loan: keo Association tu Reader sang Loan, dat multiplicity 1 o Reader, * o Loan
   - Loan -> LoanDetail: keo Composition (filled diamond) tu Loan sang LoanDetail, multiplicity 1 o Loan, 1..* o LoanDetail
   - Book -> LoanDetail: keo Association tu LoanDetail sang Book, multiplicity * o LoanDetail, 1 o Book
   - User -> Loan: keo Association tu User sang Loan, multiplicity 1 o User, * o Loan
5. Format: chon tat ca -> Format -> Auto Layout
6. Xuat: File -> Export -> PNG hoac PDF

---

## Cau 4: Sequence Diagram (1.5 diem)

### Huong dan ve bang Visual Paradigm

1. Mo Visual Paradigm -> Diagram -> Add Diagram -> Sequence Diagram
2. Tao cac lifelines:
   - Keo "Actor" vao, dat ten "Staff"
   - Keo "Boundary" (tu toolbar) vao, dat ten ":ReturnBookFrm"
   - Keo "Boundary" vao, dat ten ":PenaltySlipFrm"
   - Keo "Control" vao, dat ten ":ReturnBookControl"
   - Keo "Entity" vao, dat ten ":ReaderDAO", ":BookDAO", ":LoanDAO", ":LoanDetailDAO"
3. Ve message tu tren xuong theo thu tu trong bang duoi
4. Su dung self-message cho cac thao tac noi bo
5. Su dung return message (dashed line) cho gia tri tra ve
6. Them alt fragment cho phan kiem tra qua han
7. File -> Export -> PNG

### ASCII Sequence Diagram

```
Staff    :ReturnBookFrm    :ReturnBookControl    :ReaderDAO    :LoanDAO    :LoanDetailDAO    :BookDAO    :PenaltySlipFrm
  |              |                  |                 |             |              |              |              |
  |---scanReaderBarcode-->|         |                 |             |              |              |              |
  |              |---getReader()-->|                 |             |              |              |              |
  |              |                  |---findByBarcode->|            |              |              |              |
  |              |                  |<--Reader--------|             |              |              |              |
  |              |                  |---getActiveLoan()->           |              |              |              |
  |              |                  |<--Loan-----------|             |              |              |              |
  |              |                  |---getUnreturned()|            |              |              |              |
  |              |                  |<--List<LoanDetail>------------|              |              |              |
  |              |                  |---getBookById()  |            |              |              |              |
  |              |                  |<--Book------------------------------------------|              |              |
  |              |<--showReaderInfo(Reader, List<LoanDetail>)       |              |              |              |
  |<--displayReader+Books--|        |                 |             |              |              |              |
  |              |                  |                 |             |              |              |              |
  |---scanBookBarcode---->|         |                 |             |              |              |              |
  |              |---processReturn()-->               |             |              |              |              |
  |              |                  |---returnBook()  |             |              |              |              |
  |              |                  |---update(detailId, returnDate, fine)----->    |              |              |
  |              |                  |<--true------------------------------------------|              |              |
  |              |<--updateUI(detail, fine)           |             |              |              |              |
  |<--moveToReturnedTable-|         |                 |             |              |              |              |
  |              |                  |                 |             |              |              |              |
  |---clickSubmit------->|         |                 |             |              |              |              |
  |              |---confirmDialog()->                 |             |              |              |              |
  |<--confirmDialog------|         |                 |             |              |              |              |
  |---clickYes---------->|         |                 |             |              |              |              |
  |              |---finalizeReturn()-->              |             |              |              |              |
  |              |                  |---updateAll()   |             |              |              |              |
  |              |<--success--------|                 |             |              |              |              |
  |<--showSuccessMsg-----|         |                 |             |              |              |              |
  |              |                  |                 |             |              |              |              |
  |---clickPrintPenalty->|         |                 |             |              |              |              |
  |              |---openPenaltySlip()-->              |             |              |              |              |
  |              |                  |---getFineDetails()------------>|              |              |              |
  |              |                  |<--List<LoanDetail>(fined)------|              |              |              |
  |              |                  |---generateSlipCode()           |              |              |              |
  |              |<--PenaltyData----|                 |             |              |              |              |
  |              |            open(:PenaltySlipFrm)--|-------------|--------------|------------->|              |
  |<--displaySlip--------|          |                 |             |              |              |              |
```

### Bang giai thich chi tiet

| # | Message | Tu | Den | Mo ta |
|---|---------|-----|-----|-------|
| 1 | scanReaderBarcode(barcode) | Staff | ReturnBookFrm | Staff quet ma vach the doc gia `RDR-2024-0015` vao o input |
| 2 | getReader(barcode) | ReturnBookFrm | ReturnBookControl | Gui yeu cau tim doc gia theo ma vach |
| 3 | findByBarcode("RDR-2024-0015") | ReturnBookControl | ReaderDAO | Truy van database tim Reader co barcode = "RDR-2024-0015" |
| 4 | return Reader | ReaderDAO | ReturnBookControl | Tra ve doi tuong Reader: Nguyen Minh Tuan, 15/03/1998 |
| 5 | getActiveLoan(readerId) | ReturnBookControl | LoanDAO | Tim phieu muon dang hoat dong (chua tra het) cua doc gia co readerId = 15 |
| 6 | return Loan | LoanDAO | ReturnBookControl | Tra ve Loan: loanId=101, loanDate=15/05/2026 |
| 7 | getUnreturnedByLoanId(101) | ReturnBookControl | LoanDetailDAO | Lay danh sach sach chua tra trong phieu muon 101 |
| 8 | return List<LoanDetail> | LoanDetailDAO | ReturnBookControl | Tra ve 3 LoanDetail: B001 (due 15/06), B003 (due 15/06), B007 (due 03/06 - qua han) |
| 9 | getBookById(bookId) | ReturnBookControl | BookDAO | Lay thong tin sach cho moi LoanDetail |
| 10 | return Book | BookDAO | ReturnBookControl | Tra ve thong tin sach: B001-Lap trinh Java, B003-Co so du lieu, B007-Thiet ke phan mem |
| 11 | showReaderInfo(reader, details) | ReturnBookControl | ReturnBookFrm | Hien thi thong tin doc gia + bang sach muon |
| 12 | displayReader+Books | ReturnBookFrm | Staff | Giao dien cap nhat: hien ten, ngay sinh, dia chi + bang Borrowed Books |
| 13 | scanBookBarcode("978-0-13-468599-1") | Staff | ReturnBookFrm | Staff quet ma vach sach B001 |
| 14 | processReturn(bookBarcode) | ReturnBookFrm | ReturnBookControl | Gui yeu cau xu ly tra sach |
| 15 | returnBook(detailId=1, returnDate=08/06/2026, fine=0) | ReturnBookControl | LoanDetailDAO | Cap nhat ngay tra = hom nay, fine = 0 (tra dung han) |
| 16 | return true | LoanDetailDAO | ReturnBookControl | Cap nhat thanh cong |
| 17 | updateUI(detail, fine=0) | ReturnBookControl | ReturnBookFrm | Chuyen sach tu bang Borrowed sang Returned, hien thi fine = 0 |
| 18 | moveToReturnedTable | ReturnBookFrm | Staff | Giao dien cap nhat bang |
| 19 | scanBookBarcode("978-1-491-95035-7") | Staff | ReturnBookFrm | Staff quet ma vach sach B007 |
| 20 | processReturn(bookBarcode) | ReturnBookFrm | ReturnBookControl | Gui yeu cau xu ly tra sach |
| 21 | checkLate(detailId=3) | ReturnBookControl | ReturnBookControl | Kiem tra: dueDate=03/06/2026, returnDate=08/06/2026 -> qua han 5 ngay |
| 22 | returnBook(detailId=3, returnDate=08/06/2026, fine=150000) | ReturnBookControl | LoanDetailDAO | Cap nhat: fine = 750000 x 0.2 = 150,000 VND |
| 23 | return true | LoanDetailDAO | ReturnBookControl | Cap nhat thanh cong |
| 24 | updateUI(detail, fine=150000) | ReturnBookControl | ReturnBookFrm | Chuyen sach sang bang Returned, hien thi fine = 150,000 |
| 25 | showTotalFine(150000) | ReturnBookControl | ReturnBookFrm | Hien thi tong tien phat = 150,000 VND |
| 26 | clickSubmit | Staff | ReturnBookFrm | Staff nhan nut Submit |
| 27 | confirmDialog() | ReturnBookFrm | ReturnBookFrm | Hien thi dialog xac nhan |
| 28 | confirmDialog | ReturnBookFrm | Staff | Staff thay dialog "Xac nhan tra 3 cuon sach?" |
| 29 | clickYes | Staff | ReturnBookFrm | Staff nhan Yes |
| 30 | finalizeReturn() | ReturnBookFrm | ReturnBookControl | Gui yeu cau hoan tat tra sach |
| 31 | updateAll() | ReturnBookControl | LoanDetailDAO | Dam bao tat ca chi tiet da duoc luu vao database |
| 32 | return success | LoanDetailDAO | ReturnBookControl | Hoan tat |
| 33 | showSuccessMsg | ReturnBookControl | ReturnBookFrm | Hien thi "Tra sach thanh cong!" |
| 34 | clickPrintPenalty | Staff | ReturnBookFrm | Staff nhan nut in phieu phat |
| 35 | openPenaltySlip() | ReturnBookFrm | ReturnBookControl | Yeu cau tao phieu phat |
| 36 | getFineDetails(loanId) | ReturnBookControl | LoanDetailDAO | Lay cac chi tiet co fine > 0 |
| 37 | return List<LoanDetail>(fined) | LoanDetailDAO | ReturnBookControl | Tra ve 1 LoanDetail: B007, fine=150,000 |
| 38 | generateSlipCode() | ReturnBookControl | ReturnBookControl | Tao ma phieu: PN-20260608-001 |
| 39 | return PenaltyData | ReturnBookControl | ReturnBookFrm | Chuan bi du lieu phieu phat |
| 40 | open(PenaltySlipFrm) | ReturnBookFrm | PenaltySlipFrm | Mo form phieu phat voi du lieu |
| 41 | displaySlip | PenaltySlipFrm | Staff | Hien thi phieu phat de in |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Test Case | Input | Expected Output |
|----|-----------|-------|-----------------|
| TC01 | Tra sach dung han | Quet the RDR-2024-0015, quet sach B001 (due 15/06/2026, tra 08/06/2026) | Tra thanh cong, fine = 0, sach chuyen sang trang thai da tra |
| TC02 | Tra sach qua han | Quet the RDR-2024-0015, quet sach B007 (due 03/06/2026, tra 08/06/2026) | Tra thanh cong, fine = 150,000 VND (750,000 x 20%) |
| TC03 | The doc gia khong ton tai | Quet ma the "RDR-9999-XXXX" | Thong bao loi "Ma the doc gia khong ton tai" |
| TC04 | Doc gia khong co sach muon | Quet the RDR-2024-0022 (da tra het) | Bang Borrowed Books trong, nut Submit disable |
| TC05 | Sach khong thuoc phieu muon | Quet the RDR-2024-0015, quet sach B010 (khong thuoc phieu muon) | Thong bao loi "Sach khong thuoc danh sach muon cua doc gia" |

### TC01: Tra sach dung han - Chi tiet

**Muc dich:** Kiem tra chuc nang tra sach khi sach duoc tra dung han (truoc hoac dung ngay hen tra). Tien phat phai bang 0, sach phai duoc cap nhat ngay tra trong database.

**Database truoc khi test:**

**tblUser**

| userId | fullName | username | password |
|--------|----------|----------|----------|
| 1 | Tran Van Admin | admin | admin123 |
| 2 | Le Thi Staff | staff01 | pass123 |

**tblReader**

| readerId | fullName | dateOfBirth | address | barcode |
|----------|----------|-------------|---------|---------|
| 15 | Nguyen Minh Tuan | 1998-03-15 | 123 Le Loi, Q1, TP.HCM | RDR-2024-0015 |
| 22 | Pham Thi Lan | 1995-07-20 | 456 Nguyen Hue, Q1, TP.HCM | RDR-2024-0022 |

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
| 101 | 2026-05-15 | 15 | 2 |
| 105 | 2026-05-20 | 22 | 2 |

**tblLoanDetail**

| detailId | loanId | bookId | borrowDate | dueDate | returnDate | fine |
|----------|--------|--------|------------|---------|------------|------|
| 1 | 101 | 1 | 2026-05-15 | 2026-06-15 | NULL | 0 |
| 2 | 101 | 3 | 2026-05-15 | 2026-06-15 | NULL | 0 |
| 3 | 101 | 7 | 2026-05-03 | 2026-06-03 | NULL | 0 |
| 4 | 105 | 10 | 2026-05-20 | 2026-06-20 | NULL | 0 |

**Kich ban test:**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|-----------------|
| 1 | Mo ung dung, dang nhap | Username: `staff01`, Password: `pass123` | Dang nhap thanh cong, hien thi HomeFrm |
| 2 | Chon menu "Return" | Click "Returning Books" | Hien thi ReturnBookFrm, o ReaderBarcode trong |
| 3 | Quet ma the doc gia | Reader Barcode: `RDR-2024-0015` | Hien thi: Nguyen Minh Tuan, DOB: 15/03/1998, Address: 123 Le Loi. Bang Borrowed Books hien 3 cuon (B001, B003, B007) |
| 4 | Quet sach B001 | Book Barcode: `978-0-13-468599-1` | B001 chuyen sang bang Returned, ReturnDate = 08/06/2026, Fine = 0. Bang Borrowed con 2 cuon (B003, B007) |
| 5 | Kiem tra database | Query tblLoanDetail WHERE detailId=1 | returnDate = 2026-06-08, fine = 0 |
| 6 | Quet sach B003 | Book Barcode: `978-0-596-52068-7` | B003 chuyen sang bang Returned, ReturnDate = 08/06/2026, Fine = 0. Bang Borrowed con 1 cuon (B007) |
| 7 | Quet sach B007 | Book Barcode: `978-1-491-95035-7` | B007 chuyen sang bang Returned, ReturnDate = 08/06/2026, Fine = 150,000 (qua han). Bang Borrowed trong |
| 8 | Kiem tra tong phat | Xem label Total Fine | Hien thi "150,000 VND" |
| 9 | Nhan Submit | Click "Submit Return" | Dialog xac nhan xuat hien |
| 10 | Xac nhan | Click "Yes" | Thong bao "Tra sach thanh cong!" |
| 11 | Nhan in phieu phat | Click "Print Penalty Slip" | Phieu phat hien thi voi ma PN-20260608-001, sach B007, fine = 150,000 |
| 12 | Kiem tra database toan bo | Query tblLoanDetail WHERE loanId=101 | Tat ca 3 dong co returnDate = 2026-06-08 |

**Database sau khi test:**

**tblUser** (khong thay doi)

| userId | fullName | username | password |
|--------|----------|----------|----------|
| 1 | Tran Van Admin | admin | admin123 |
| 2 | Le Thi Staff | staff01 | pass123 |

**tblReader** (khong thay doi)

| readerId | fullName | dateOfBirth | address | barcode |
|----------|----------|-------------|---------|---------|
| 15 | Nguyen Minh Tuan | 1998-03-15 | 123 Le Loi, Q1, TP.HCM | RDR-2024-0015 |
| 22 | Pham Thi Lan | 1995-07-20 | 456 Nguyen Hue, Q1, TP.HCM | RDR-2024-0022 |

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
| 101 | 2026-05-15 | 15 | 2 |
| 105 | 2026-05-20 | 22 | 2 |

**tblLoanDetail** (THAY DOI - 3 dong duoc cap nhat)

| detailId | loanId | bookId | borrowDate | dueDate | returnDate | fine |
|----------|--------|--------|------------|---------|------------|------|
| 1 | 101 | 1 | 2026-05-15 | 2026-06-15 | **2026-06-08** | **0** |
| 2 | 101 | 3 | 2026-05-15 | 2026-06-15 | **2026-06-08** | **0** |
| 3 | 101 | 7 | 2026-05-03 | 2026-06-03 | **2026-06-08** | **150,000** |
| 4 | 105 | 10 | 2026-05-20 | 2026-06-20 | NULL | 0 |
