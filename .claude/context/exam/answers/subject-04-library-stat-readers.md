# Subject 04 — Library Management — Module "Statistics of Readers"

---

## Cau 1: Scenario (1.5 diem)

### Bang Scenario chinh

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1 | Staff dang nhap he thong | Username: `staff01`, Password: `123456` | LoginFrm: form nhap username, password, nut Login |
| 2 | Staff chon menu "Thong ke" | Click menu item "Statistics" | HomeFrm: thanh menu chinh voi cac muc: Borrow, Return, Statistics |
| 3 | Staff chon thong ke doc gia | Click "Statistics of Readers" | StatReaderFrm: form voi 2 o nhap ngay (StartDate, EndDate), nut Search, vung ket qua (an) |
| 4 | Staff nhap khoang thoi gian | StartDate: `01/01/2026`, EndDate: `08/06/2026` | StatReaderFrm: o StartDate va EndDate duoc dien, nut Search sang |
| 5 | Staff nhan nut tim kiem | Click "Search" | StatReaderFrm: bang ket qua hien thi danh sach doc gia xep theo so sach muon giam dan |
| 6 | He thong hien thi ket qua | — | StatReaderFrm: bang "Reader Statistics" voi cot: Code, Name, Date of Birth, Address, Total Books Borrowed. Dong dau: RDR-2024-0015 - Nguyen Minh Tuan - 15/03/1998 - 123 Le Loi, Q1, TP.HCM - 25 sach. Dong 2: RDR-2024-0010 - Tran Thi Binh - 10/05/1997 - 789 Cach Mang T8, Q3, TP.HCM - 18 sach. Dong 3: RDR-2024-0018 - Pham Thi Lan - 20/07/1995 - 456 Nguyen Hue, Q1, TP.HCM - 12 sach. Tong: 3 dong |
| 7 | Staff click vao dong doc gia dau tien | Click dong RDR-2024-0015 | StatReaderFrm: panel chi tiet phia duoi/phai hien thi "Detail: Loan slips of Nguyen Minh Tuan" |
| 8 | He thong hien thi chi tiet phieu muon cua doc gia | — | StatReaderFrm: bang "Loan Slip Details" voi cot: Loan Date, Total Books. Dong 1: 15/01/2026 - 3 sach. Dong 2: 01/03/2026 - 2 sach. Dong 3: 10/04/2026 - 4 sach. Dong 4: 10/05/2026 - 3 sach. Tong: 4 phieu muon |
| 9 | Staff click vao dong doc gia thu hai | Click dong RDR-2024-0010 | StatReaderFrm: panel chi tiet cap nhat, hien thi bang "Loan Slip Details" cho Tran Thi Binh |
| 10 | He thong hien thi chi tiet phieu muon cua doc gia thu hai | — | StatReaderFrm: bang voi cot: Loan Date, Total Books. Dong 1: 05/02/2026 - 2 sach. Dong 2: 20/03/2026 - 3 sach. Tong: 2 phieu muon, 18 sach tong cong |
| 11 | Staff thay doi khoang thoi gian | StartDate: `01/04/2026`, EndDate: `08/06/2026` | StatReaderFrm: o ngay duoc cap nhat |
| 12 | Staff nhan Search lai | Click "Search" | StatReaderFrm: bang ket qua cap nhat voi du lieu moi, xep theo so sach muon giam dan |
| 13 | Staff nhan Export | Click "Export to CSV" | StatReaderFrm: dialog luu file, file CSV duoc tao |
| 14 | Staff quay ve trang chu | Click "Back" | StatReaderFrm dong, quay ve HomeFrm |

### Kich ban ngoai le

**Ngoai le 1: Khoang thoi gian khong hop le (StartDate > EndDate)**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff nhap ngay sai | StartDate: `08/06/2026`, EndDate: `01/01/2026` | StatReaderFrm: o nhap duoc dien |
| 5 | Staff nhan Search | Click "Search" | StatReaderFrm: thong bao loi "Ngay bat dau phai nho hon hoac bang ngay ket thuc." Khong hien thi ket qua |

**Ngoai le 2: Khong co doc gia muon sach trong khoang thoi gian**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff nhap khoang thoi gian khong co du lieu | StartDate: `01/01/2025`, EndDate: `31/12/2025` | — |
| 5 | Staff nhan Search | Click "Search" | StatReaderFrm: bang ket qua trong. Thong bao "Khong co du lieu muon sach trong khoang thoi gian nay." |

**Ngoai le 3: Staff chua nhap du ngay**

| Buoc | Hanh dong | Du lieu | UI hien thi |
|------|-----------|----------|-------------|
| 1-3 | Tuong tu | — | — |
| 4 | Staff chi nhap StartDate | StartDate: `01/01/2026`, EndDate: (trong) | StatReaderFrm |
| 5 | Staff nhan Search | Click "Search" | StatReaderFrm: thong bao loi "Vui long nhap day du ngay bat dau va ngay ket thuc." |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong thu vien cho phep nhan vien thong ke so sach muon cua tung doc gia trong mot khoang thoi gian. Moi doc gia co thong tin ma doc gia, ho ten, ngay sinh, dia chi va ma vach the. Doc gia co the muon nhieu sach, moi lan muon tao ra mot phieu muon (Loan) chua thong tin ngay muon va danh sach cac cuon sach muon. Moi phieu muon co the chua nhieu chi tiet muon (LoanDetail), moi chi tiet tuong ung voi mot cuon sach cu the. Moi cuon sach co thong tin ma sach, ten sach, tac gia, ma vach, gia bia. Nhan vien (User) la nguoi thuc hien cac thao tac tren he thong. Chuc nang thong ke doc gia cho phep xem danh sach doc gia muon nhieu sach nhat va chi tiet cac phieu muon cua tung doc gia.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Nhan vien (User) | Entity | Doi tuong thuc hien thao tac, co thuoc tính rieng (ten, tai khoan) |
| Doc gia (Reader) | Entity | Doi tuong chinh cua thong ke, co nhieu thuoc tính (ma, ten, ngay sinh, dia chi, ma vach) |
| Sach (Book) | Entity | Doi tuong duoc muon, co thuoc tính (ma, ten, tac gia, ma vach, gia bia) |
| Phieu muon (Loan) | Entity | Dai dien cho mot lan muon, co ngay muon, lien ket voi doc gia va nhan vien |
| Chi tiet muon (LoanDetail) | Entity | Lien ket giua phieu muon va tung cuon sach, co ngay muon, ngay hen tra, ngay tra, tien phat |
| Ma doc gia (Reader Code) | Thuoc tính | Thuoc tính cua Reader |
| Ten doc gia (Reader Name) | Thuoc tính | Thuoc tính cua Reader |
| Ngay sinh (Date of Birth) | Thuoc tính | Thuoc tính cua Reader |
| Dia chi (Address) | Thuoc tính | Thuoc tính cua Reader |
| Tong so sach muon (Total Books Borrowed) | Thuoc tính tinh toan | Duoc tinh tu LoanDetail, khong luu trong database |
| Ngay muon (Loan Date) | Thuoc tính | Thuoc tính cua Loan |
| Tong so sach moi phieu (Total Books per Loan) | Thuoc tính tinh toan | Duoc dem tu LoanDetail theo loanId |
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

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|------------|-------|
| LoginFrm | Form dang nhap cho nhan vien, nhap username va password |
| HomeFrm | Trang chinh sau khi dang nhap, chua menu dieu huong: Borrow, Return, Statistics |
| StatReaderFrm | Form thong ke doc gia, cho phep nhap khoang thoi gian, hien thi bang doc gia muon nhieu sach nhat va chi tiet phieu muon khi click vao tung doc gia |

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

**StatReaderFrm**

| Element | Loai | Mo ta |
|---------|------|-------|
| inStartDate | Input (DatePicker) | O chon ngay bat dau khoang thoi gian thong ke |
| inEndDate | Input (DatePicker) | O chon ngay ket thuc khoang thoi gian thong ke |
| subSearch | Button (Submit) | Nut tim kiem / thong ke |
| outReaderTable | Output (Table) | Bang danh sach doc gia: Code, Name, Date of Birth, Address, Total Books Borrowed. Sap xep theo Total Books Borrowed giam dan |
| outDetailLabel | Output (Label) | Nhan hien thi "Detail: Loan slips of [reader name]" khi click vao mot dong doc gia |
| outDetailTable | Output (Table) | Bang chi tiet phieu muon cua doc gia duoc chon: Loan Date, Total Books |
| subExport | Button (Submit) | Nut xuat du lieu ra file CSV |
| subBack | Button (Navigate) | Nut quay ve HomeFrm |

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `findByUsername(String username): User` | Tim user theo ten dang nhap |
| UserDAO | `validateLogin(String username, String password): User` | Xac thuc dang nhap |
| ReaderDAO | `findAll(): List<Reader>` | Lay danh sach tat ca doc gia |
| ReaderDAO | `findById(int readerId): Reader` | Tim doc gia theo ID |
| LoanDAO | `findLoansByReaderId(int readerId, Date startDate, Date endDate): List<Loan>` | Lay danh sach phieu muon cua doc gia trong khoang thoi gian |
| LoanDAO | `countBooksByLoan(int loanId): int` | Dem so sach trong mot phieu muon |
| LoanDetailDAO | `countBooksByReader(Date startDate, Date endDate): List<Map>` | Dem tong so sach muon cua moi doc gia trong khoang thoi gian, tra ve danh sach {readerId, totalBooks} sap xep giam dan |
| LoanDetailDAO | `countByLoanId(int loanId): int` | Dem so chi tiet muon trong mot phieu muon |

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

1. Mo Visual Paradigm -> File -> New Project -> dat ten "LibraryManagement_StatReaders"
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
   - Keo "Boundary" vao, dat ten ":StatReaderFrm"
   - Keo "Control" vao, dat ten ":StatReaderControl"
   - Keo "Entity" vao, dat ten ":LoanDetailDAO", ":LoanDAO", ":ReaderDAO"
3. Ve message tu tren xuong theo thu tu trong bang duoi
4. Su dung alt/opt fragment cho phan kiem tra du lieu hop le
5. Su dung loop fragment cho phan hien thi tung dong doc gia
6. File -> Export -> PNG

### ASCII Sequence Diagram

```
Staff    :StatReaderFrm    :StatReaderControl    :LoanDetailDAO    :LoanDAO    :ReaderDAO
  |              |                   |                    |              |            |
  |---selectMenu()-->|               |                    |              |            |
  |              |---showForm()-->   |                    |              |            |
  |<--displayForm----|               |                    |              |            |
  |              |                   |                    |              |            |
  |---enterDates(start,end)-->       |                    |              |            |
  |              |                   |                    |              |            |
  |---clickSearch-->|                |                    |              |            |
  |              |---searchReaders(start,end)-->           |              |            |
  |              |                   |---validateDates(start,end)       |            |
  |              |                   |  [if invalid: return error]      |            |
  |              |                   |  [if valid: continue]            |            |
  |              |                   |                    |              |            |
  |              |                   |---countBooksByReader(start,end)->|            |
  |              |                   |<--List<Map>(readerId,totalBooks)--|            |
  |              |                   |                    |              |            |
  |              |                   |---getReaderById(readerId)--------------------->|
  |              |                   |<--Reader----------------------------------------|
  |              |                   |  [loop for each reader]            |            |
  |              |                   |                    |              |            |
  |              |<--ReaderList------|                    |              |            |
  |<--displayReaderTable--|          |                    |              |            |
  |              |                   |                    |              |            |
  |---clickReaderRow(readerId)-->    |                    |              |            |
  |              |---getReaderDetail(readerId,start,end)-->              |            |
  |              |                   |---findLoansByReader(readerId,start,end)------->|
  |              |                   |<--List<Loan>------------------------------------|
  |              |                   |                    |              |            |
  |              |                   |---countBooksByLoan(loanId)----->  |            |
  |              |                   |<--int(count)--------------------  |            |
  |              |                   |  [loop for each loan]            |            |
  |              |                   |                    |              |            |
  |              |<--DetailList------|                    |              |            |
  |<--displayDetailTable--|          |                    |              |            |
  |              |                   |                    |              |            |
  |---clickExport-->|                |                    |              |            |
  |              |---exportCSV()-->  |                    |              |            |
  |              |                   |---generateCSV(data)              |            |
  |              |<--filePath--------|                    |              |            |
  |<--showSaveDialog--|              |                    |              |            |
  |              |                   |                    |              |            |
  |---clickBack-->|                  |                    |              |            |
  |              |---close()-->      |                    |              |            |
  |<--returnToHome--|                |                    |              |            |
```

### Bang giai thich chi tiet

| # | Message | Tu | Den | Mo ta |
|---|---------|-----|-----|-------|
| 1 | selectMenu() | Staff | StatReaderFrm | Staff chon menu "Statistics of Readers" tu HomeFrm |
| 2 | showForm() | StatReaderFrm | StatReaderControl | Khoi tao controller, hien thi form thong ke |
| 3 | displayForm | StatReaderFrm | Staff | Hien thi form voi 2 o DatePicker (StartDate, EndDate) va nut Search |
| 4 | enterDates("01/01/2026", "08/06/2026") | Staff | StatReaderFrm | Staff nhap khoang thoi gian: StartDate = 01/01/2026, EndDate = 08/06/2026 |
| 5 | clickSearch | Staff | StatReaderFrm | Staff nhan nut Search |
| 6 | searchReaders(startDate, endDate) | StatReaderFrm | StatReaderControl | Gui yeu cau thong ke voi khoang thoi gian |
| 7 | validateDates(startDate, endDate) | StatReaderControl | StatReaderControl | Kiem tra: startDate <= endDate va ca hai khong null |
| 8 | countBooksByReader("2026-01-01", "2026-06-08") | StatReaderControl | LoanDetailDAO | Truy van SQL: SELECT l.readerId, COUNT(ld.detailId) as totalBooks FROM tblLoanDetail ld JOIN tblLoan l ON ld.loanId=l.loanId WHERE ld.borrowDate BETWEEN '2026-01-01' AND '2026-06-08' GROUP BY l.readerId ORDER BY totalBooks DESC |
| 9 | return List<Map> | LoanDetailDAO | StatReaderControl | Tra ve: [{readerId:15, totalBooks:25}, {readerId:10, totalBooks:18}, {readerId:18, totalBooks:12}] |
| 10 | getReaderById(15) | StatReaderControl | ReaderDAO | Lay thong tin doc gia readerId=15 |
| 11 | return Reader | ReaderDAO | StatReaderControl | Tra ve: Reader{readerId:15, fullName:"Nguyen Minh Tuan", dob:"1998-03-15", address:"123 Le Loi, Q1, TP.HCM", barcode:"RDR-2024-0015"} |
| 12 | getReaderById(10) | StatReaderControl | ReaderDAO | Lay thong tin doc gia readerId=10 |
| 13 | return Reader | ReaderDAO | StatReaderControl | Tra ve: Reader{readerId:10, fullName:"Tran Thi Binh", dob:"1997-05-10", address:"789 Cach Mang T8, Q3, TP.HCM"} |
| 14 | getReaderById(18) | StatReaderControl | ReaderDAO | Lay thong tin doc gia readerId=18 |
| 15 | return Reader | ReaderDAO | StatReaderControl | Tra ve: Reader{readerId:18, fullName:"Pham Thi Lan", dob:"1995-07-20", address:"456 Nguyen Hue, Q1, TP.HCM"} |
| 16 | ReaderList(readerList) | StatReaderControl | StatReaderFrm | Gui danh sach ket qua: [{code:"RDR-2024-0015", name:"Nguyen Minh Tuan", dob:"15/03/1998", address:"123 Le Loi", totalBooks:25}, ...] |
| 17 | displayReaderTable | StatReaderFrm | Staff | Hien thi bang voi 3 dong, sap xep theo totalBooks giam dan |
| 18 | clickReaderRow(readerId=15) | Staff | StatReaderFrm | Staff click vao dong doc gia Nguyen Minh Tuan |
| 19 | getReaderDetail(15, startDate, endDate) | StatReaderFrm | StatReaderControl | Yeu cau chi tiet phieu muon cua doc gia 15 |
| 20 | findLoansByReader(15, "2026-01-01", "2026-06-08") | StatReaderControl | LoanDAO | Truy van: SELECT * FROM tblLoan WHERE readerId=15 AND loanDate BETWEEN '2026-01-01' AND '2026-06-08' |
| 21 | return List<Loan> | LoanDAO | StatReaderControl | Tra ve 4 phieu muon: [{loanId:102, loanDate:"2026-01-15"}, {loanId:105, loanDate:"2026-04-01"}, ...] |
| 22 | countBooksByLoan(102) | StatReaderControl | LoanDetailDAO | Dem so sach trong phieu muon 102 |
| 23 | return 3 | LoanDetailDAO | StatReaderControl | Phieu muon 102 co 3 sach |
| 24 | countBooksByLoan(105) | StatReaderControl | LoanDetailDAO | Dem so sach trong phieu muon 105 |
| 25 | return 4 | LoanDetailDAO | StatReaderControl | Phieu muon 105 co 4 sach |
| 26 | DetailList(detailList) | StatReaderControl | StatReaderFrm | Gui danh sach chi tiet: [{loanDate:"15/01/2026", totalBooks:3}, {loanDate:"01/04/2026", totalBooks:4}, ...] |
| 27 | displayDetailTable | StatReaderFrm | Staff | Hien thi bang chi tiet: 4 phieu muon voi Loan Date va Total Books |
| 28 | clickExport | Staff | StatReaderFrm | Staff nhan nut Export to CSV |
| 29 | exportCSV() | StatReaderFrm | StatReaderControl | Yeu cau xuat du lieu |
| 30 | generateCSV(data) | StatReaderControl | StatReaderControl | Tao noi dung CSV tu du lieu bang |
| 31 | return filePath | StatReaderControl | StatReaderFrm | Tra ve duong dan file CSV |
| 32 | showSaveDialog | StatReaderFrm | Staff | Hien thi dialog luu file |
| 33 | clickBack | Staff | StatReaderFrm | Staff nhan nut Back |
| 34 | close() | StatReaderFrm | StatReaderControl | Dong form, giai phong tai nguyen |
| 35 | returnToHome | StatReaderFrm | Staff | Quay ve HomeFrm |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Test Case | Input | Expected Output |
|----|-----------|-------|-----------------|
| TC01 | Thong ke doc gia trong khoang thoi gian co du lieu | StartDate: 01/01/2026, EndDate: 08/06/2026 | Bang hien thi 3 doc gia, sap xep theo so sach muon giam dan |
| TC02 | Thong ke doc gia trong khoang thoi gian khong co du lieu | StartDate: 01/01/2025, EndDate: 31/12/2025 | Bang trong, thong bao "Khong co du lieu" |
| TC03 | Ngay bat dau lon hon ngay ket thuc | StartDate: 08/06/2026, EndDate: 01/01/2026 | Thong bao loi "Ngay bat dau phai nho hon hoac bang ngay ket thuc" |
| TC04 | Chua nhap ngay | StartDate: (trong), EndDate: (trong) | Thong bao loi "Vui long nhap day du ngay bat dau va ngay ket thuc" |
| TC05 | Click vao doc gia de xem chi tiet | Click dong RDR-2024-0015 | Bang chi tiet hien thi 4 phieu muon cua doc gia Nguyen Minh Tuan |

### TC01: Thong ke doc gia trong khoang thoi gian co du lieu - Chi tiet

**Muc dich:** Kiem tra chuc nang thong ke doc gia khi nhap khoang thoi gian hop le co du lieu. Bang ket qua phai hien thi dung danh sach doc gia, sap xep theo so sach muon giam dan, voi day du thong tin code, name, date of birth, address, total books borrowed. Khi click vao mot doc gia, bang chi tiet phieu muon phai hien thi dung.

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
| 5 | Cau truc du lieu | Pham Van D | 978-0-07-123456-7 | 350,000 |
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
| 107 | 2026-05-20 | 15 | 2 |

**tblLoanDetail**

| detailId | loanId | bookId | borrowDate | dueDate | returnDate | fine |
|----------|--------|--------|------------|---------|------------|------|
| 1 | 101 | 1 | 2026-01-10 | 2026-02-10 | 2026-02-05 | 0 |
| 2 | 101 | 3 | 2026-01-10 | 2026-02-10 | 2026-02-08 | 0 |
| 3 | 101 | 7 | 2026-01-10 | 2026-02-10 | 2026-03-15 | 150,000 |
| 4 | 102 | 1 | 2026-01-15 | 2026-02-15 | 2026-02-10 | 0 |
| 5 | 102 | 7 | 2026-01-15 | 2026-02-15 | 2026-02-12 | 0 |
| 6 | 102 | 5 | 2026-01-15 | 2026-02-15 | 2026-02-14 | 0 |
| 7 | 103 | 3 | 2026-02-01 | 2026-03-01 | 2026-02-28 | 0 |
| 8 | 103 | 7 | 2026-02-01 | 2026-03-01 | 2026-03-05 | 76,000 |
| 9 | 104 | 1 | 2026-03-05 | 2026-04-05 | 2026-04-01 | 0 |
| 10 | 104 | 5 | 2026-03-05 | 2026-04-05 | 2026-04-03 | 0 |
| 11 | 105 | 7 | 2026-04-01 | 2026-05-01 | 2026-04-28 | 0 |
| 12 | 105 | 1 | 2026-04-01 | 2026-05-01 | 2026-04-30 | 0 |
| 13 | 105 | 3 | 2026-04-01 | 2026-05-01 | 2026-05-10 | 38,000 |
| 14 | 105 | 10 | 2026-04-01 | 2026-05-01 | 2026-04-25 | 0 |
| 15 | 106 | 7 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 16 | 106 | 10 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 17 | 106 | 1 | 2026-05-10 | 2026-06-10 | 2026-06-05 | 0 |
| 18 | 106 | 5 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 19 | 107 | 3 | 2026-05-20 | 2026-06-20 | NULL | 0 |
| 20 | 107 | 7 | 2026-05-20 | 2026-06-20 | NULL | 0 |
| 21 | 107 | 10 | 2026-05-20 | 2026-06-20 | NULL | 0 |

**Kich ban test:**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|-----------------|
| 1 | Mo ung dung, dang nhap | Username: `staff01`, Password: `pass123` | Dang nhap thanh cong, hien thi HomeFrm |
| 2 | Chon menu "Statistics" | Click "Statistics" | Hien thi menu con thong ke |
| 3 | Chon "Statistics of Readers" | Click "Statistics of Readers" | Hien thi StatReaderFrm voi o StartDate, EndDate, nut Search |
| 4 | Nhap ngay bat dau | StartDate: `01/01/2026` | O StartDate hien thi 01/01/2026 |
| 5 | Nhap ngay ket thuc | EndDate: `08/06/2026` | O EndDate hien thi 08/06/2026 |
| 6 | Nhan Search | Click "Search" | Bang ket qua hien thi 4 dong |
| 7 | Kiem tra dong 1 | Xem dong dau tien | Code: RDR-2024-0015, Name: Nguyen Minh Tuan, DOB: 15/03/1998, Address: 123 Le Loi, Q1, TP.HCM, Total Books: 10 (detailId: 4,5,6,11,12,13,14,19,20,21) |
| 8 | Kiem tra dong 2 | Xem dong thu hai | Code: RDR-2024-0010, Name: Tran Thi Binh, DOB: 10/05/1997, Address: 789 Cach Mang T8, Q3, TP.HCM, Total Books: 7 (detailId: 1,2,3,15,16,17,18) |
| 9 | Kiem tra dong 3 | Xem dong thu ba | Code: RDR-2024-0018, Name: Pham Thi Lan, DOB: 20/07/1995, Address: 456 Nguyen Hue, Q1, TP.HCM, Total Books: 2 (detailId: 7,8) |
| 10 | Kiem tra dong 4 | Xem dong thu tu | Code: RDR-2024-0020, Name: Hoang Van Em, DOB: 05/11/2000, Address: 321 Vo Van Tan, Q3, TP.HCM, Total Books: 2 (detailId: 9,10) |
| 11 | Kiem tra thu tu | Xem thu tu cac dong | Sap xep giam dan: R0015(10) > R0010(7) > R0018(2) = R0020(2) |
| 12 | Click vao dong RDR-2024-0015 | Click dong dau tien | Panel chi tiet hien thi "Detail: Loan slips of Nguyen Minh Tuan" |
| 13 | Kiem tra bang chi tiet | Xem bang Loan Slip Details | 3 dong: (1) 15/01/2026 - 3 sach (loanId:102). (2) 01/04/2026 - 4 sach (loanId:105). (3) 20/05/2026 - 3 sach (loanId:107). Tong: 10 sach |
| 14 | Click vao dong RDR-2024-0010 | Click dong thu hai | Panel chi tiet cap nhat cho Tran Thi Binh |
| 15 | Kiem tra bang chi tiet | Xem bang Loan Slip Details | 2 dong: (1) 10/01/2026 - 3 sach (loanId:101). (2) 10/05/2026 - 4 sach (loanId:106). Tong: 7 sach |
| 16 | Click vao dong RDR-2024-0018 | Click dong thu ba | Panel chi tiet cap nhat cho Pham Thi Lan |
| 17 | Kiem tra bang chi tiet | Xem bang Loan Slip Details | 1 dong: (1) 01/02/2026 - 2 sach (loanId:103). Tong: 2 sach |
| 18 | Click vao dong RDR-2024-0020 | Click dong thu tu | Panel chi tiet cap nhat cho Hoang Van Em |
| 19 | Kiem tra bang chi tiet | Xem bang Loan Slip Details | 1 dong: (1) 05/03/2026 - 2 sach (loanId:104). Tong: 2 sach |

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
| 5 | Cau truc du lieu | Pham Van D | 978-0-07-123456-7 | 350,000 |
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
| 107 | 2026-05-20 | 15 | 2 |

**tblLoanDetail** (khong thay doi - day la chuc nang chi doc, khong sua database)

| detailId | loanId | bookId | borrowDate | dueDate | returnDate | fine |
|----------|--------|--------|------------|---------|------------|------|
| 1 | 101 | 1 | 2026-01-10 | 2026-02-10 | 2026-02-05 | 0 |
| 2 | 101 | 3 | 2026-01-10 | 2026-02-10 | 2026-02-08 | 0 |
| 3 | 101 | 7 | 2026-01-10 | 2026-02-10 | 2026-03-15 | 150,000 |
| 4 | 102 | 1 | 2026-01-15 | 2026-02-15 | 2026-02-10 | 0 |
| 5 | 102 | 7 | 2026-01-15 | 2026-02-15 | 2026-02-12 | 0 |
| 6 | 102 | 5 | 2026-01-15 | 2026-02-15 | 2026-02-14 | 0 |
| 7 | 103 | 3 | 2026-02-01 | 2026-03-01 | 2026-02-28 | 0 |
| 8 | 103 | 7 | 2026-02-01 | 2026-03-01 | 2026-03-05 | 76,000 |
| 9 | 104 | 1 | 2026-03-05 | 2026-04-05 | 2026-04-01 | 0 |
| 10 | 104 | 5 | 2026-03-05 | 2026-04-05 | 2026-04-03 | 0 |
| 11 | 105 | 7 | 2026-04-01 | 2026-05-01 | 2026-04-28 | 0 |
| 12 | 105 | 1 | 2026-04-01 | 2026-05-01 | 2026-04-30 | 0 |
| 13 | 105 | 3 | 2026-04-01 | 2026-05-01 | 2026-05-10 | 38,000 |
| 14 | 105 | 10 | 2026-04-01 | 2026-05-01 | 2026-04-25 | 0 |
| 15 | 106 | 7 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 16 | 106 | 10 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 17 | 106 | 1 | 2026-05-10 | 2026-06-10 | 2026-06-05 | 0 |
| 18 | 106 | 5 | 2026-05-10 | 2026-06-10 | NULL | 0 |
| 19 | 107 | 3 | 2026-05-20 | 2026-06-20 | NULL | 0 |
| 20 | 107 | 7 | 2026-05-20 | 2026-06-20 | NULL | 0 |
| 21 | 107 | 10 | 2026-05-20 | 2026-06-20 | NULL | 0 |
