# Subject No. 47 — Cinema Chain — Module "Selling food"

> **Domain:** Cinema Chain Management

---

## Cau 1: Scenario — Ban do an

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly chuoi rap chieu phim ban them dich vu do an nhanh (bap rang bo, nuoc uong). Moi mon do an co ma mon, ten mon, gia tien theo tung size (S/M/L). Nhan vien tim kiem mon theo ten, chon size va so luong, them vao hoa don. Hoa don ghi ma, ten, size, don gia, so luong, thanh tien. Dong cuoi la tong tien. Nhan vien nhan Pay de in hoa don va luu database.

### Scenario chinh

| Buoc | Dien bien |
|------|-----------|
| 1 | Staff dang nhap vao he thong. Giao dien Login xuat hien voi o nhap Username, Password va nut Login. |
| 2 | Staff nhap username `staff01`, password `******` va nhan Login. |
| 3 | Giao dien Home xuat hien voi cac chuc nang: Sell tickets, Schedule showing, Selling food, Revenue Statistics. |
| 4 | Staff chon chuc nang **Selling food**. |
| 5 | Giao dien ban do an xuat hien voi: o nhap ten mon, nut Search, bang hoa don (trong), tong tien = 0. |
| 6 | Staff nhap "Popcorn" vao o tim kiem va nhan nut **Search**. |
| 7 | He thong hien thi danh sach mon chua tu khoa "Popcorn": Popcorn Caramel (S: 45,000d, M: 55,000d, L: 65,000d), Popcorn Cheese (S: 45,000d, M: 55,000d, L: 65,000d), Popcorn Salted (S: 40,000d, M: 50,000d, L: 60,000d). |
| 8 | Staff click vao mon "Popcorn Caramel". Giao dien chon size va so luong xuat hien: combobox size (S/M/L), o nhap so luong, nut OK. |
| 9 | Staff chon size "L", nhap so luong `2`, nhan nut **OK**. |
| 10 | Mon duoc them vao hoa don: Popcorn Caramel, L, 65,000d x 2 = 130,000d. Tong tien cap nhat: 130,000d. |
| 11 | Staff nhap "Coca" vao o tim kiem, nhan Search. He thong hien thi: Coca Cola, Pepsi, 7Up. |
| 12 | Staff click "Coca Cola". Chon size "M", so luong `3`, nhan OK. |
| 13 | Hoa don them dong: Coca Cola, M, 25,000d x 3 = 75,000d. Tong tien cap nhat: 205,000d. |
| 14 | Staff nhap "Hotdog", Search, chon "Hotdog Classic", size "L", so luong `1`, OK. |
| 15 | Hoa don them dong: Hotdog Classic, L, 50,000d x 1 = 50,000d. Tong tien cap nhat: 255,000d. |
| 16 | Staff xem lai hoa don, nhan nut **Pay**. |
| 17 | He thong in hoa don chua: ma hoa don, ngay ban, danh sach mon (ma, ten, size, don gia, so luong, thanh tien), tong tien. He thong luu vao database. |
| 18 | He thong thong bao "Ban do an thanh cong". Hoa don duoc reset, san sang cho khach tiep theo. |

### Kich ban ngoai le

- **EL1:** Tim kiem khong ra mon nao → He thong hien thi danh sach trong, thong bao "Khong tim thay mon an".
- **EL2:** So luong nhap <= 0 → He thong thong bao "So luong khong hop le".
- **EL3:** Khach huy don truoc khi Pay → Staff co the xoa tung dong trong hoa don.

---

## Cau 2: Entity Class Diagram

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong ban do an nhanh trong rap chieu phim. Moi mon do an (ma mon, ten mon) co nhieu size khac nhau (S, M, L) voi gia rieng biet cho tung size. Khi nhan vien ban hang, mot hoa don do an duoc tao chua thong tin ngay ban va tong tien. Hoa don co nhieu chi tiet, moi chi tiet lien ket den mot mon do an voi size cu the, ghi so luong, don gia va thanh tien. Nguoi dung (staff) dang nhap he thong de ban do an.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Mon do an (FoodItem) | Entity class | Doi tuong ban chinh — mon an/uong |
| Size (FoodSize) | Entity class | Size cua mon an (S/M/L) voi gia rieng |
| Hoa don do an (FoodInvoice) | Entity class | Hoa don moi lan ban |
| Chi tiet hoa don (FoodInvoiceDetail) | Entity class | Chi tiet tung mon trong hoa don |
| Nguoi dung (User) | Entity class | Nhan vien thao tac he thong |
| Ma mon, ten mon | Thuoc tinh | Thuoc tinh cua FoodItem |
| Size, gia | Thuoc tinh | Thuoc tinh cua FoodSize |
| Ngay ban, tong tien | Thuoc tinh | Thuoc tinh cua FoodInvoice |
| So luong, don gia, thanh tien | Thuoc tinh | Thuoc tinh cua FoodInvoiceDetail |

### Buoc 3: Xac dinh quan he

```
FoodItem 1 --- n FoodSize
```
- Mot mon do an co nhieu size voi gia khac nhau.
- Moi size thuoc mot mon do an.

```
FoodInvoice 1 --- n FoodInvoiceDetail
```
- Mot hoa don co nhieu chi tiet.
- Moi chi tiet thuoc mot hoa don.

```
FoodSize 1 --- n FoodInvoiceDetail
```
- Mot mon-size co the xuat hien trong nhieu chi tiet hoa don.
- Moi chi tiet lien ket den mot mon-size cu the.

```
User 1 --- n FoodInvoice
```
- Mot nhan vien tao nhieu hoa don.
- Moi hoa don duoc tao boi mot nhan vien.

### Buoc 4: Class Diagram (Analysis)

```
+------------------+       +------------------+
|    FoodItem      |       |    FoodSize      |
+------------------+       +------------------+
| -code: String    |       | -size: String    |
| -name: String    |       | -price: float    |
+------------------+       +------------------+
| +searchFood()    |               ^
         | 1                       | 1
         |                         |
         | n                       | n
         v                         |
+------------------+       +------------------+
|                  |       |FoodInvoiceDetail |
|                  |       +------------------+
|                  |       | -quantity: int   |
|                  |       | -unitPrice: float|
|                  |       | -amount: float   |
|                  |       +------------------+
|                  |               ^
|                  |               | n
|                  |               |
+------------------+       +------------------+
                           |  FoodInvoice     |
                           +------------------+
                           | -invoiceDate: Date|
                           | -totalAmount: float|
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

### Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|----------|------|------------|
| FoodItem → FoodSize | 1-n (Composition) | Mot mon co nhieu size, size khong ton tai neu khong co mon |
| FoodInvoice → FoodInvoiceDetail | 1-n (Composition) | Hoa don co nhieu chi tiet, chi tiet khong ton tai neu khong co hoa don |
| FoodSize → FoodInvoiceDetail | 1-n (Association) | Mot mon-size xuat hien trong nhieu chi tiet |
| User → FoodInvoice | 1-n (Association) | Mot nhan vien tao nhieu hoa don |

---

## Cau 3: Thiet ke tinh — Giao dien va class diagram chi tiet

### Buoc 1: Xac dinh cac View class (Form class)

| View class | Mo ta |
|------------|-------|
| LoginFrm | Giao dien dang nhap |
| HomeFrm | Giao dien chinh |
| SellFoodFrm | Giao dien ban do an (chinh) |

### Buoc 2: Xac dinh cac phan tu giao dien

**LoginFrm:**
- `inUsername`: o nhap username
- `inPassword`: o nhap password
- `subLogin`: nut Login

**HomeFrm:**
- `subSellTickets`: nut chon Sell tickets
- `subScheduleShowing`: nut chon Schedule showing
- `subSellingFood`: nut chon Selling food
- `subRevenueStatistics`: nut chon Revenue Statistics

**SellFoodFrm:**
- `inFoodName`: o nhap ten mon can tim
- `subSearch`: nut Search
- `outsubFoodList`: bang danh sach mon tim duoc (click duoc)
- `inSize`: combobox chon size (S/M/L)
- `inQuantity`: o nhap so luong
- `subOK`: nut OK
- `outInvoiceTable`: bang hoa don (cot: ma, ten, size, don gia, so luong, thanh tien)
- `outTotal`: hien thi tong tien
- `subPay`: nut Pay

### Buoc 3: Xac dinh DAO class

| DAO class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiem tra dang nhap |
| FoodItemDAO | `searchFoodByName(keyword): List<FoodItem>` | Tim mon theo ten |
| FoodSizeDAO | `getSizesByFoodItem(foodItemId): List<FoodSize>` | Lay danh sach size cua mon |
| FoodInvoiceDAO | `addFoodInvoice(invoice): int` | Tao hoa don moi, tra ve ID |
| FoodInvoiceDetailDAO | `addInvoiceDetail(detail): boolean` | Them chi tiet hoa don |

### Buoc 4: Xac dinh Entity class (Design phase)

**FoodItem:**
- `id: int` (PK)
- `code: String`
- `name: String`

**FoodSize:**
- `id: int` (PK)
- `size: String` (S/M/L)
- `price: float`
- `foodItemId: int` (FK → FoodItem)

**FoodInvoice:**
- `id: int` (PK)
- `invoiceDate: Date`
- `totalAmount: float`
- `userId: int` (FK → User)

**FoodInvoiceDetail:**
- `id: int` (PK)
- `quantity: int`
- `unitPrice: float`
- `amount: float`
- `foodInvoiceId: int` (FK → FoodInvoice)
- `foodSizeId: int` (FK → FoodSize)

**User:**
- `id: int` (PK)
- `username: String`
- `password: String`
- `role: String`

### Buoc 5: Database Design

**tblFoodItem:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |

**tblFoodSize:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| size | varchar | |
| price | float | |
| foodItemID | int | FK → tblFoodItem.ID |

**tblFoodInvoice:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| invoiceDate | date | |
| totalAmount | float | |
| userID | int | FK → tblUser.ID |

**tblFoodInvoiceDetail:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| quantity | int | |
| unitPrice | float | |
| amount | float | |
| foodInvoiceID | int | FK → tblFoodInvoice.ID |
| foodSizeID | int | FK → tblFoodSize.ID |

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| username | varchar | |
| password | varchar | |
| role | varchar | |

### Buoc 6: Mo ta cach ve Class Diagram (Design phase) bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Class Diagram** trong danh muc Diagrams.
2. Tao cac class:
   - View classes: `LoginFrm`, `HomeFrm`, `SellFoodFrm`
   - DAO classes: `UserDAO`, `FoodItemDAO`, `FoodSizeDAO`, `FoodInvoiceDAO`, `FoodInvoiceDetailDAO`
   - Entity classes: `FoodItem`, `FoodSize`, `FoodInvoice`, `FoodInvoiceDetail`, `User`

3. Ve View classes (Form):
   - Ve hinh chu nhat 3 ngan cho `SellFoodFrm`:
     - Ngan 1 (ten): `<<boundary>> SellFoodFrm`
     - Ngan 2 (thuoc tinh): `-inFoodName: JTextField`, `-subSearch: JButton`, `-outsubFoodList: JTable`, `-inSize: JComboBox`, `-inQuantity: JTextField`, `-subOK: JButton`, `-outInvoiceTable: JTable`, `-outTotal: JLabel`, `-subPay: JButton`
     - Ngan 3 (phuong thuc): khong co

4. Ve DAO classes:
   - Ve hinh chu nhat 3 ngan cho `FoodItemDAO`:
     - Ngan 1: `<<control>> FoodItemDAO`
     - Ngan 2: khong co thuoc tinh
     - Ngan 3: `+searchFoodByName(keyword: String): List<FoodItem>`
   - Tuong tu cho `FoodInvoiceDAO`: `+addFoodInvoice(invoice: FoodInvoice): int`
   - Tuong tu cho `FoodInvoiceDetailDAO`: `+addInvoiceDetail(detail: FoodInvoiceDetail): boolean`

5. Ve Entity classes:
   - Ve hinh chu nhat 3 ngan cho `FoodItem`:
     - Ngan 1: `<<entity>> FoodItem`
     - Ngan 2: `-id: int`, `-code: String`, `-name: String`
     - Ngan 3: getter/setter
   - Tuong tu cho `FoodSize`, `FoodInvoice`, `FoodInvoiceDetail`, `User`

6. Ve cac duong ket noi:

| Tu | Den | Loai duong | Kieu quan he | Giai thich |
|----|-----|-----------|---------------|------------|
| SellFoodFrm | FoodItemDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung FoodItemDAO |
| SellFoodFrm | FoodInvoiceDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung FoodInvoiceDAO |
| SellFoodFrm | FoodInvoiceDetailDAO | Duong lien net, mui tam giac rong | Dependency | Frm su dung FoodInvoiceDetailDAO |
| FoodItem → FoodSize | Duong lien net, dau kim cuong filled | Composition 1-n | FoodSize khong ton tai neu khong co FoodItem |
| FoodInvoice → FoodInvoiceDetail | Duong lien net, dau kim cuong filled | Composition 1-n | FoodInvoiceDetail khong ton tai neu khong co FoodInvoice |
| FoodSize → FoodInvoiceDetail | Duong lien net, mui tam giac rong | Association 1-n | FoodInvoiceDetail tham chieu den FoodSize |

---

## Cau 4: Thiet ke dong — Xay dung sequence diagram

### Mo ta cach ve Sequence Diagram bang Visual Paradigm

**Cac buoc ve:**

1. Mo Visual Paradigm → chon **Sequence Diagram**.
2. Tao cac lifeline (doi tuong):
   - Actor: `Staff` (loai Actor)
   - Boundary: `LoginFrm`, `HomeFrm`, `SellFoodFrm`
   - Control: `UserDAO`, `FoodItemDAO`, `FoodSizeDAO`, `FoodInvoiceDAO`, `FoodInvoiceDetailDAO`

3. Ve cac message (mui ten net lien) theo thu tu tu tren xuong duoi.

### Sequence Diagram — Ban do an (Scenario version 3)

```
Staff      LoginFrm   UserDAO   HomeFrm   SellFoodFrm   FoodItemDAO   FoodSizeDAO   FoodInvoiceDAO   FoodInvoiceDetailDAO
  |            |          |         |           |              |             |               |                  |
  |--login---->|          |         |           |              |             |               |                  |
  |            |--checkL->|         |           |              |             |               |                  |
  |            |<-true----|         |           |              |             |               |                  |
  |            |--open----|-------->|           |              |             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |--select----|--------------------->          |              |             |               |                  |
  |            |          |         |--open---->|              |             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |--enter "Popcorn"----->|         |           |              |             |               |                  |
  |--click Search-------->|         |           |              |             |               |                  |
  |            |          |         |           |--searchFood->|             |               |                  |
  |            |          |         |           |              |--query DB   |               |                  |
  |            |          |         |           |              |<-return-----|               |                  |
  |            |          |         |           |<-List<Food>--|             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |--display list|             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |--click "Popcorn Caramel"------->|           |              |             |               |                  |
  |            |          |         |           |--getSizes()--|------------>|               |                  |
  |            |          |         |           |              |             |--query DB     |                  |
  |            |          |         |           |              |             |<-return-------|                  |
  |            |          |         |           |<-List<Size>--|-------------|               |                  |
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |--display size|             |               |                  |
  |            |          |         |           | combo        |             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |--select L, qty 2, click OK---->|           |              |             |               |                  |
  |            |          |         |           |--add to table|             |               |                  |
  |            |          |         |           | (Popcorn Caramel, L, 65000x2=130000)       |                  |
  |            |          |         |           |              |             |               |                  |
  |--(lap lai cho mon khac)-------->|           |              |             |               |                  |
  |--enter "Coca", Search---------->|           |              |             |               |                  |
  |            |          |         |           |--searchFood->|             |               |                  |
  |            |          |         |           |<-List<Food>--|             |               |                  |
  |--click "Coca Cola", M, 3, OK-->|           |              |             |               |                  |
  |            |          |         |           |--add to table|             |               |                  |
  |            |          |         |           | (Coca Cola, M, 25000x3=75000)             |                  |
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |--update total|             |               |                  |
  |            |          |         |           | total=205000 |             |               |                  |
  |            |          |         |           |              |             |               |                  |
  |--click Pay|          |         |           |              |             |               |                  |
  |            |          |         |           |--addInvoice()|-------------|-------------->|                  |
  |            |          |         |           |              |             |               |--INSERT DB       |
  |            |          |         |           |              |             |               |<-return id--------|
  |            |          |         |           |<-invoiceId----|-------------|---------------|                  |
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |--addDetail()--|-------------|---------------|----------------->|
  |            |          |         |           |              |             |               |                  |--INSERT DB
  |            |          |         |           |              |             |               |                  |<-return true
  |            |          |         |           |<-true---------|-------------|---------------|------------------|
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |(lap cho moi chi tiet)      |               |                  |
  |            |          |         |           |              |             |               |                  |
  |            |          |         |           |--print invoice|             |               |                  |
  |            |          |         |           |--show success |             |               |                  |
  |<--success--|          |         |           |              |             |               |                  |
```

### Giai thich tung buoc

| # | Message | Tu | Den | Mo ta |
|---|---------|----|-----|-------|
| 1 | login | Staff | LoginFrm | Staff nhap username/password, nhan Login |
| 2 | checkLogin() | LoginFrm | UserDAO | Goi UserDAO.checkLogin("staff01", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | open | LoginFrm | HomeFrm | Mo giao dien Home |
| 6 | select Selling food | Staff | HomeFrm | Staff chon chuc nang Selling food |
| 7 | open | HomeFrm | SellFoodFrm | Mo giao dien ban do an |
| 8 | enterFoodName("Popcorn") | Staff | SellFoodFrm | Staff nhap tu khoa tim kiem |
| 9 | clickSearch() | Staff | SellFoodFrm | Staff nhan nut Search |
| 10 | searchFoodByName("Popcorn") | SellFoodFrm | FoodItemDAO | Goi FoodItemDAO.searchFoodByName("Popcorn") |
| 11 | query DB | FoodItemDAO | Database | Truy van tblFoodItem WHERE name LIKE '%Popcorn%' |
| 12 | return List<FoodItem> | FoodItemDAO | SellFoodFrm | Tra ve danh sach mon phu hop |
| 13 | display food list | SellFoodFrm | UI | Hien thi danh sach mon tim duoc |
| 14 | selectFood("Popcorn Caramel") | Staff | SellFoodFrm | Staff click chon mon |
| 15 | getSizesByFoodItem(id) | SellFoodFrm | FoodSizeDAO | Lay danh sach size cua mon |
| 16 | query DB | FoodSizeDAO | Database | Truy van tblFoodSize |
| 17 | return List<FoodSize> | FoodSizeDAO | SellFoodFrm | Tra ve danh sach size voi gia |
| 18 | display size combo | SellFoodFrm | UI | Hien thi combobox size (S/M/L) |
| 19 | selectSize("L"), enterQuantity(2), clickOK() | Staff | SellFoodFrm | Staff chon size, so luong, nhan OK |
| 20 | add to invoice table | SellFoodFrm | UI | Them dong vao bang hoa don |
| 21 | (lap lai cho mon khac) | Staff | SellFoodFrm | Tim "Coca", chon Coca Cola, M, 3 |
| 22 | update total | SellFoodFrm | UI | Cap nhat tong tien = 205,000d |
| 23 | clickPay() | Staff | SellFoodFrm | Staff nhan nut Pay |
| 24 | addFoodInvoice(invoice) | SellFoodFrm | FoodInvoiceDAO | Tao hoa don moi |
| 25 | INSERT DB | FoodInvoiceDAO | Database | INSERT INTO tblFoodInvoice |
| 26 | return invoiceId | FoodInvoiceDAO | SellFoodFrm | Tra ve ID hoa don |
| 27 | addInvoiceDetail(detail) | SellFoodFrm | FoodInvoiceDetailDAO | Them chi tiet (loop moi mon) |
| 28 | INSERT DB | FoodInvoiceDetailDAO | Database | INSERT INTO tblFoodInvoiceDetail |
| 29 | return true | FoodInvoiceDetailDAO | SellFoodFrm | Tra ve true |
| 30 | print invoice | SellFoodFrm | UI | In hoa don cho khach |
| 31 | show success | SellFoodFrm | UI | Hien thi "Ban do an thanh cong" |

---

## Cau 5: Viet testcase blackbox chuan

### Test Plan — Danh sach testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Selling food | Ban do an thanh cong voi nhieu mon, nhieu size |
| 2 | Selling food | Tim kiem khong ra mon nao |
| 3 | Selling food | So luong khong hop le (<= 0) |
| 4 | Selling food | Ban 1 mon duy nhat |

### Testcase chi tiet — TC01: Ban do an thanh cong

**Muc dich:** Kiem tra chuc nang ban do an hoat dong dung khi tim kiem mon, chon size, nhap so luong, va thanh toan.

**Database truoc khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblFoodItem:**
| ID | code | name |
|----|------|------|
| 1 | F01 | Popcorn Caramel |
| 2 | F02 | Popcorn Cheese |
| 3 | F03 | Popcorn Salted |
| 4 | F04 | Coca Cola |
| 5 | F05 | Pepsi |
| 6 | F06 | Hotdog Classic |

**tblFoodSize:**
| ID | size | price | foodItemID |
|----|------|-------|------------|
| 1 | S | 45000 | 1 |
| 2 | M | 55000 | 1 |
| 3 | L | 65000 | 1 |
| 4 | S | 45000 | 2 |
| 5 | M | 55000 | 2 |
| 6 | L | 65000 | 2 |
| 7 | S | 20000 | 4 |
| 8 | M | 25000 | 4 |
| 9 | L | 30000 | 4 |
| 10 | S | 40000 | 6 |
| 11 | M | 45000 | 6 |
| 12 | L | 50000 | 6 |

**tblFoodInvoice:** (rong)

**tblFoodInvoiceDetail:** (rong)

### Kich ban test va ket qua mong doi

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Khoi dong ung dung | Giao dien Login xuat hien voi o Username, o Password, nut Login |
| 2 | Nhap username `staff01`, password `123456`, nhan Login | Giao dien Home xuat hien voi cac chuc nang |
| 3 | Chon chuc nang Selling food | Giao dien ban do an xuat hien voi o tim kiem, nut Search, bang hoa don (trong) |
| 4 | Nhap "Popcorn" vao o tim kiem, nhan Search | Danh sach hien thi: Popcorn Caramel, Popcorn Cheese, Popcorn Salted |
| 5 | Click vao "Popcorn Caramel" | Giao dien chon size xuat hien: combobox S/M/L, o so luong, nut OK |
| 6 | Chon size "L", nhap so luong `2`, nhan OK | Bang hoa don them dong: Popcorn Caramel, L, 65,000d x 2 = 130,000d. Tong tien = 130,000d |
| 7 | Nhap "Coca", nhan Search, click "Coca Cola", chon size "M", so luong `3`, nhan OK | Bang hoa don them dong: Coca Cola, M, 25,000d x 3 = 75,000d. Tong tien = 205,000d |
| 8 | Nhap "Hotdog", nhan Search, click "Hotdog Classic", chon size "L", so luong `1`, nhan OK | Bang hoa don them dong: Hotdog Classic, L, 50,000d x 1 = 50,000d. Tong tien = 255,000d |
| 9 | Nhan nut Pay | He thong in hoa don voi noi dung: ma hoa don, ngay ban, 3 dong mon (ma, ten, size, don gia, so luong, thanh tien), tong tien 255,000d. Thong bao "Ban do an thanh cong" |

### Database sau khi test

**tblFoodInvoice:** (them 1 dong)
| ID | invoiceDate | totalAmount | userID |
|----|-------------|-------------|--------|
| 1 | 08/06/2026 | 255000 | 1 |

**tblFoodInvoiceDetail:** (them 3 dong)
| ID | quantity | unitPrice | amount | foodInvoiceID | foodSizeID |
|----|----------|-----------|--------|---------------|------------|
| 1 | 2 | 65000 | 130000 | 1 | 3 |
| 2 | 3 | 25000 | 75000 | 1 | 8 |
| 3 | 1 | 50000 | 50000 | 1 | 12 |

**tblUser:** (khong thay doi)

**tblFoodItem:** (khong thay doi)

**tblFoodSize:** (khong thay doi)
