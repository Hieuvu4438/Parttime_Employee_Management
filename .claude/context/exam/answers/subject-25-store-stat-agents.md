# Subject 25 -- Store Management -- Module "Statistics of sub-agence"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Manager (Quan ly):** Nguoi xem thong ke dai ly, phan tich doanh thu theo dai ly.
- **System (He thong):** He thong quan ly kho, xu ly truy van thong ke, hien thi bao cao.

### Preconditions
- Manager da dang nhap thanh cong vao he thong voi vai tro "Manager".
- He thong co du lieu hoa don xuat (tblExportInvoice, tblExportInvoiceDetail) trong co so du lieu.
- Du lieu hoa don da duoc nhap day du tu cac giao dich xuat hang truoc do.
- He thong co du lieu dai ly (tblSubAgent).

### Main Scenario: Thong ke dai ly theo doanh thu

| Buoc | Actor | Hanh dong | He thong phan hoi |
|------|-------|-----------|-------------------|
| 1 | Manager | Nhan chon menu "Thong ke dai ly" (Sub-Agent Statistics) tren giao dien chinh. | Hien thi trang Thong ke dai ly voi o nhap khoang thoi gian (dtpFromDate, dtpToDate), nut "Thong ke" (btnStat), va vung bang ket qua (grdAgentStats) trong. |
| 2 | Manager | Chon ngay bat dau "01/01/2026" vao dtpFromDate, chon ngay ket thuc "31/03/2026" vao dtpToDate. Nhan nut "Thong ke" (btnStat). | He thong truy van: SELECT s.agentId, s.agentName, SUM(e.totalAmount) as totalRevenue FROM tblExportInvoice e JOIN tblSubAgent s ON e.agentId = s.agentId WHERE e.exportDate BETWEEN '2026-01-01' AND '2026-03-31' GROUP BY s.agentId, s.agentName ORDER BY totalRevenue DESC. Hien thi bang ket qua grdAgentStats gom cot: STT, Ma dai ly, Ten dai ly, Tong doanh thu. |
| 3 | Manager | Xem bang ket qua thong ke. | He thong hien thi danh sach dai ly theo doanh thu giam dan. Vi du: (1) AG005 - Dai ly Minh Khang - 200,000,000; (2) AG006 - Dai ly Thanh Binh - 80,000,000; (3) AG008 - Dai ly Phuong Nam - 45,000,000. Tong doanh thu ky: lblTotalRevenue = "325,000,000 VND". |
| 4 | Manager | Nhan chon dong "AG005 - Dai ly Minh Khang" trong grdAgentStats. | He thong hien thi chi tiet cac hoa don xuat cho dai ly AG005: Bang grdInvoiceDetail gom cot: Ma phieu xuat, Ngay xuat, Tong so mat hang, Tong tien. Cac dong sap xep theo ngay xuat giam dan. |
| 5 | Manager | Xem bang chi tiet. | He thong hien thi danh sach hoa don cua dai ly AG005 trong Q1/2026. Vi du: (1) PX2026015 - 28/03/2026 - 3 mat hang - 90,000,000; (2) PX2026012 - 05/03/2026 - 2 mat hang - 50,000,000; (3) PX2026003 - 10/01/2026 - 3 mat hang - 75,000,000. Tong: lblInvoiceTotal = "215,000,000 VND", So hoa don: "3". |
| 6 | Manager | Nhan chon dong khac "AG006 - Dai ly Thanh Binh" trong grdAgentStats. | He thong cap nhat bang grdInvoiceDetail hien thi chi tiet cho dai ly AG006. Vi du: (1) PX2026008 - 15/02/2026 - 2 mat hang - 125,000,000. Tong: "125,000,000 VND", So hoa don: "1". |
| 7 | Manager | Thay doi khoang thoi gian: dtpFromDate = "01/04/2026", dtpToDate = "30/06/2026". Nhan btnStat. | He thong truy van lai voi khoang thoi gian moi (Q2/2026). Hien thi bang grdAgentStats cap nhat voi du lieu Q2/2026. grdInvoiceDetail duoc xoa trang vi chua chon dai ly cu the. |
| 8 | Manager | Nhan nut "Xuat bao cao" (btnExport). | He thong xuat bao cao thong ke dai ly ra file Excel/PDF voi day du thong tin: header (ky thong ke), bang tong hop, bang chi tiet cho tung dai ly. Hien thi thong bao "Xuat bao cao thanh cong!". |

### Alternative Scenarios

**2a. Khong co du lieu trong ky thong ke:**
- Buoc 2 (ngoai le): Manager chon khoang thoi gian khong co du lieu xuat (vi du: nam 2020). He thong hien thi bang grdAgentStats trong va thong bao: "Khong co du lieu xuat hang trong khoang thoi gian da chon." lblTotalRevenue = "0 VND".

**2b. Ngay bat dau > ngay ket thuc:**
- Buoc 2 (ngoai le): Manager chon dtpFromDate = "31/03/2026", dtpToDate = "01/01/2026". He thong hien thi loi: "Ngay bat dau phai nho hon hoac bang ngay ket thuc."

**4a. Nhan vao dai ly nhung khong co hoa don:**
- Buoc 4 (ngoai le): Dai ly co trong danh sach nhung khong co hoa don nao trong ky (co the la du lieu tong hop tu ky khac). He thong hien thi bang grdInvoiceDetail trong va thong bao: "Khong co hoa don nao cho dai ly nay trong ky thong ke."

**8a. Loi khi xuat bao cao:**
- Buoc 8 (ngoai le): He thong gap loi khi ghi file. Hien thi loi: "Khong the xuat bao cao. Vui long thu lai sau."

### Postconditions
- Bang thong ke dai ly hien thi dung du lieu theo khoang thoi gian da chon.
- Khi Manager chon mot dai ly, bang chi tiet hien thi dung cac hoa don cua dai ly do.
- Bao cao xuat ra file chua day du thong tin thong ke.
- Du lieu trong he thong khong bi thay doi (chuc nang chi doc).

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management - Module Thong ke dai ly bao gom cac thuc the sau:

1. **SubAgent (Dai ly):** Moi dai ly co ma (agentId), ten (agentName), dia chi (address), so dien thoai (phone). Dai ly la doi tuong chinh duoc thong ke. Mot dai ly co the co nhieu phieu xuat khac nhau trong he thong. Du lieu thong ke bao gom tong doanh thu va so luong hoa don theo tung ky.

2. **ExportInvoice (Phieu xuat):** Moi phieu xuat co ma phieu (invoiceId), ngay xuat (exportDate), tong tien (totalAmount), ma dai ly (agentId), ma nhan vien (userId). Phieu xuat la nguon du lieu de tinh toan thong ke cho dai ly. Mot phieu xuat thuoc ve mot dai ly va chua nhieu mat hang.

3. **ExportInvoiceDetail (Chi tiet phieu xuat):** Moi chi tiet co ma chi tiet (detailId), ma phieu xuat (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Du lieu chi tiet duoc su dung de dem so luong mat hang trong moi hoa don khi hien thi chi tiet cho dai ly.

4. **Item (Hang hoa):** Moi hang hoa co ma hang (itemId), ten hang (itemName), mo ta (description). Hang hoa xuat hien trong chi tiet hoa don xuat. Du lieu hang hoa duoc su dung khi tinh tong so mat hang trong bang chi tiet dai ly.

5. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Manager la nguoi dung co vai tro "Manager" thuc hien chuc nang thong ke.

### Trich xuat danh tu tu mo ta module

| STT | Danh tu trong mo ta | Entity tuong ung | Thuoc tinh |
|-----|---------------------|------------------|------------|
| 1 | Dai ly, dai ly con, sub-agent, dai ly phan phoi | SubAgent | agentId, agentName, address, phone |
| 2 | Phieu xuat, hoa don xuat, invoice, export bill | ExportInvoice | invoiceId, exportDate, totalAmount, agentId, userId |
| 3 | Chi tiet phieu xuat, chi tiet hoa don | ExportInvoiceDetail | detailId, invoiceId, itemId, quantity, unitPrice, amount |
| 4 | Hang hoa, mat hang, san pham | Item | itemId, itemName, description |
| 5 | Quan ly, nguoi dung, nhan vien, manager | User | userId, username, password, fullName, role |
| 6 | Doanh thu, tong tien, revenue, tong doanh thu | (thuoc tinh tinh toan) | totalRevenue = SUM(totalAmount) |
| 7 | Tong so mat hang, so luong mat hang | (thuoc tinh tinh toan) | totalItems = COUNT(detail) |
| 8 | Ky thong ke, khoang thoi gian, period | (tham so dau vao) | fromDate, toDate |

### Moi quan he giua cac Entity

| Moi quan he | Kieu | Mo ta |
|-------------|------|-------|
| SubAgent -- ExportInvoice | 1 : N | Mot dai ly co the co nhieu phieu xuat |
| ExportInvoice -- ExportInvoiceDetail | 1 : N | Mot phieu xuat co nhieu chi tiet |
| Item -- ExportInvoiceDetail | 1 : N | Mot hang hoa co the co trong nhieu chi tiet phieu xuat |
| User -- ExportInvoice | 1 : N | Mot nhan vien co the tao nhieu phieu xuat |

### ASCII Class Diagram

```
+------------------+          +---------------------+          +------------------+
|      User        |          |    ExportInvoice     |          |    SubAgent      |
+------------------+          +---------------------+          +------------------+
| - userId: String | 1      N | - invoiceId: String | N      1 | - agentId: String|
| - username       |----------| - exportDate: Date  |----------| - agentName      |
| - password       |          | - totalAmount: float|          | - address        |
| - fullName       |          | - agentId: String   |          | - phone          |
| - role: String   |          | - userId: String    |          +------------------+
+------------------+          +---------------------+
                                       |
                                       | 1
                                       |
                                       N
                             +--------------------------+          +------------------+
                             |  ExportInvoiceDetail     |          |      Item        |
                             +--------------------------+          +------------------+
                             | - detailId: String       | N      1 | - itemId: String |
                             | - invoiceId: String      |----------| - itemName       |
                             | - itemId: String         |          | - description    |
                             | - quantity: int          |          +------------------+
                             | - unitPrice: float       |
                             | - amount: float          |
                             +--------------------------+

                             <<derived>>
                             +--------------------------+
                             |   AgentStatistics        |
                             +--------------------------+
                             | - agentId: String        |
                             | - agentName: String      |
                             | - totalRevenue: float    |
                             +--------------------------+

                             <<derived>>
                             +--------------------------+
                             |   AgentInvoiceSummary    |
                             +--------------------------+
                             | - invoiceId: String      |
                             | - exportDate: Date       |
                             | - totalItems: int        |
                             | - totalAmount: float     |
                             +--------------------------+
```

### Bang quan he (Relation Table)

| Entity | Thuoc tinh chinh | Khoa ngoai | Entity lien ket |
|--------|-----------------|------------|-----------------|
| User | userId | -- | -- |
| SubAgent | agentId | -- | -- |
| Item | itemId | -- | -- |
| ExportInvoice | invoiceId | agentId -> SubAgent(agentId), userId -> User(userId) | SubAgent, User |
| ExportInvoiceDetail | detailId | invoiceId -> ExportInvoice(invoiceId), itemId -> Item(itemId) | ExportInvoice, Item |

---

## Câu 3: Thiet ke tinh (Static Design)

### View Classes

| View Class | Mo ta | Form lien quan |
|------------|-------|----------------|
| AgentStatFrm | Giao dien thong ke dai ly. Chua o nhap khoang thoi gian, nut thong ke, bang tong hop dai ly theo doanh thu, bang chi tiet hoa don khi chon mot dai ly, nut xuat bao cao. | MainForm (mo tu menu "Thong ke dai ly") |

### UI Elements trong AgentStatFrm

| UI Element | Loai | Thuoc tinh | Mo ta |
|------------|------|------------|-------|
| dtpFromDate | DateTimePicker | Format = Short | O chon ngay bat dau ky thong ke |
| dtpToDate | DateTimePicker | Format = Short | O chon ngay ket thuc ky thong ke |
| btnStat | Button | Text = "Thong ke" | Nut thuc hien truy van thong ke |
| grdAgentStats | DataGridView | ReadOnly = true, SelectionMode = FullRowSelect | Bang tong hop dai ly: STT, Ma dai ly, Ten dai ly, Tong doanh thu |
| lblTotalRevenue | Label | Text | Hien thi tong doanh thu toan ky |
| lblSelectedTitle | Label | Text | Tieu de "Chi tiet hoa don cho: [Ten dai ly]" |
| grdInvoiceDetail | DataGridView | ReadOnly = true | Bang chi tiet hoa don: Ma phieu, Ngay xuat, Tong so mat hang, Tong tien |
| lblInvoiceCount | Label | Text | Hien thi tong so hoa don cua dai ly duoc chon |
| lblInvoiceTotal | Label | Text | Hien thi tong tien cac hoa don cua dai ly duoc chon |
| btnExport | Button | Text = "Xuat bao cao" | Nut xuat bao cao ra file |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| AgentStatDAO | tblExportInvoice, tblSubAgent, tblExportInvoiceDetail | getAgentStats(Date fromDate, Date toDate): List<AgentStatistics>, getAgentInvoices(String agentId, Date fromDate, Date toDate): List<AgentInvoiceSummary> |
| SubAgentDAO | tblSubAgent | getById(String agentId): SubAgent |
| ExportInvoiceDAO | tblExportInvoice | getByAgentAndDateRange(String agentId, Date fromDate, Date toDate): List<ExportInvoice> |

### Entity Types

| Entity | Kieu du lieu | Thuoc tinh |
|--------|--------------|------------|
| SubAgent | Entity Class | agentId: String, agentName: String, address: String, phone: String |
| ExportInvoice | Entity Class | invoiceId: String, exportDate: Date, totalAmount: float, agentId: String, userId: String |
| ExportInvoiceDetail | Entity Class | detailId: String, invoiceId: String, itemId: String, quantity: int, unitPrice: float, amount: float |
| Item | Entity Class | itemId: String, itemName: String, description: String |
| User | Entity Class | userId: String, username: String, password: String, fullName: String, role: String |
| AgentStatistics | DTO Class | agentId: String, agentName: String, totalRevenue: float |
| AgentInvoiceSummary | DTO Class | invoiceId: String, exportDate: Date, totalItems: int, totalAmount: float |

### Database Schema

```sql
CREATE TABLE tblUser (
    userId      VARCHAR(10) PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    fullName    NVARCHAR(100) NOT NULL,
    role        VARCHAR(20) NOT NULL
);

CREATE TABLE tblItem (
    itemId      VARCHAR(10) PRIMARY KEY,
    itemName    NVARCHAR(100) NOT NULL,
    description NVARCHAR(255)
);

CREATE TABLE tblSubAgent (
    agentId     VARCHAR(10) PRIMARY KEY,
    agentName   NVARCHAR(100) NOT NULL,
    address     NVARCHAR(200),
    phone       VARCHAR(15)
);

CREATE TABLE tblExportInvoice (
    invoiceId   VARCHAR(10) PRIMARY KEY,
    exportDate  DATE NOT NULL,
    totalAmount FLOAT NOT NULL,
    agentId     VARCHAR(10) NOT NULL,
    userId      VARCHAR(10) NOT NULL,
    FOREIGN KEY (agentId) REFERENCES tblSubAgent(agentId),
    FOREIGN KEY (userId) REFERENCES tblUser(userId)
);

CREATE TABLE tblExportInvoiceDetail (
    detailId    VARCHAR(10) PRIMARY KEY,
    invoiceId   VARCHAR(10) NOT NULL,
    itemId      VARCHAR(10) NOT NULL,
    quantity    INT NOT NULL,
    unitPrice   FLOAT NOT NULL,
    amount      FLOAT NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES tblExportInvoice(invoiceId),
    FOREIGN KEY (itemId) REFERENCES tblItem(itemId)
);
```

### Visual Paradigm (VP) Guide

**Tao Class Diagram trong VP:**
1. Mo VP -> New -> Class Diagram.
2. Tao 5 entity class: User, SubAgent, Item, ExportInvoice, ExportInvoiceDetail.
3. Tao 2 DTO class: AgentStatistics, AgentInvoiceSummary.
4. Them attributes cho moi class.
5. Ve association:
   - User 1---N ExportInvoice.
   - SubAgent 1---N ExportInvoice.
   - ExportInvoice 1---N ExportInvoiceDetail.
   - Item 1---N ExportInvoiceDetail.
6. Ve dependency: AgentStatDAO ..> AgentStatistics, AgentStatDAO ..> AgentInvoiceSummary.
7. Export diagram duoi dang PNG.

---

## Câu 4: Sequence Diagram (Chuc nang thong ke dai ly)

### Visual Paradigm (VP) Guide

1. Mo VP -> New -> Sequence Diagram.
2. Tao cac lifeline: Manager, AgentStatFrm, AgentStatDAO, SubAgentDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho truong hop khong co du lieu.
5. Su dung loop fragment cho hien thi danh sach va chi tiet.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Manager        AgentStatFrm     AgentStatDAO    SubAgentDAO     Database
  |                |                |                |              |
  |--selectMenu---->|                |                |              |
  |                |--initForm()    |                |              |
  |                |                |                |              |
  |--setFromDate-->|                |                |              |
  |  "01/01/2026"  |                |                |              |
  |--setToDate---->|                |                |              |
  |  "31/03/2026"  |                |                |              |
  |--clickStat---->|                |                |              |
  |                |--validateDates()               |              |
  |                |  [from <= to]  |                |              |
  |                |                |                |              |
  |                |--getAgentStats>|                |              |
  |                |  (from, to)    |                |              |
  |                |                |--SELECT--------|------------->|
  |                |                |  s.agentId,    |              |
  |                |                |  s.agentName,  |              |
  |                |                |  SUM(e.total)  |              |
  |                |                |  FROM invoice e|              |
  |                |                |  JOIN agent s   |              |
  |                |                |  WHERE e.date   |              |
  |                |                |  BETWEEN from   |              |
  |                |                |  AND to         |              |
  |                |                |  GROUP BY       |              |
  |                |                |  s.agentId,     |              |
  |                |                |  s.agentName    |              |
  |                |                |  ORDER BY       |              |
  |                |                |  SUM(e.total)   |              |
  |                |                |  DESC           |              |
  |                |                |<--ResultSet-----|              |
  |                |<--List<Stats>--|                |              |
  |                |                |                |              |
  |                |--displayStats()|                |              |
  |<--showGrdStats-|                |                |              |
  |                |--calcTotalRevenue()            |              |
  |<--showTotal----|                |                |              |
  |                |                |                |              |
  |--clickAgent--->|                |                |              |
  |  "AG005"       |                |                |              |
  |                |--getAgentInvoices              |              |
  |                |  "AG005", from, to             |              |
  |                |                |--SELECT--------|------------->|
  |                |                |  e.invoiceId,  |              |
  |                |                |  e.exportDate, |              |
  |                |                |  COUNT(d.detail)|             |
  |                |                |  e.totalAmount |              |
  |                |                |  FROM invoice e|              |
  |                |                |  JOIN detail d  |              |
  |                |                |  WHERE e.agentId|              |
  |                |                |  = 'AG005'      |              |
  |                |                |  AND e.date     |              |
  |                |                |  BETWEEN from   |              |
  |                |                |  AND to         |              |
  |                |                |  GROUP BY       |              |
  |                |                |  e.invoiceId,   |              |
  |                |                |  e.exportDate,  |              |
  |                |                |  e.totalAmount  |              |
  |                |                |  ORDER BY       |              |
  |                |                |  e.exportDate   |              |
  |                |                |  DESC           |              |
  |                |                |<--ResultSet-----|              |
  |                |<--List<Invoices                |              |
  |                |                |                |              |
  |                |--displayInvoices()             |              |
  |<--showGrdDetail|                |                |              |
  |                |--calcInvoiceTotals()           |              |
  |<--showInvTotal-|                |                |              |
  |                |                |                |              |
  |--clickAgent--->|                |                |              |
  |  "AG006"       |                |                |              |
  |                |  (tuong tu, truy van AG006)    |              |
  |                |                |                |              |
  |--clickExport-->|                |                |              |
  |                |--exportReport()                |              |
  |                |--generateFile()                |              |
  |<--showSuccess--|                |                |              |
```

### Bang chi tiet Sequence Diagram

| Buoc | From | To | Message | Loai | Ghi chu |
|------|------|-----|---------|------|---------|
| 1 | Manager | AgentStatFrm | selectAgentStatMenu() | Sync | Manager nhan menu "Thong ke dai ly" |
| 2 | AgentStatFrm | AgentStatFrm | initForm() | Self | Khoi tao form, dat ngay mac dinh |
| 3 | Manager | AgentStatFrm | setFromDate("01/01/2026") | Sync | Manager chon ngay bat dau |
| 4 | Manager | AgentStatFrm | setToDate("31/03/2026") | Sync | Manager chon ngay ket thuc |
| 5 | Manager | AgentStatFrm | clickStat() | Sync | Manager nhan nut "Thong ke" |
| 6 | AgentStatFrm | AgentStatFrm | validateDates() | Self | Kiem tra fromDate <= toDate -> hop le |
| 7 | AgentStatFrm | AgentStatDAO | getAgentStats(fromDate, toDate) | Sync | Goi DAO lay thong ke dai ly |
| 8 | AgentStatDAO | Database | SELECT s.agentId, s.agentName, SUM(e.totalAmount) AS totalRevenue FROM tblExportInvoice e JOIN tblSubAgent s ON e.agentId = s.agentId WHERE e.exportDate BETWEEN '2026-01-01' AND '2026-03-31' GROUP BY s.agentId, s.agentName ORDER BY totalRevenue DESC | Sync | Truy van thong ke |
| 9 | Database | AgentStatDAO | ResultSet: [(AG005, Dai ly Minh Khang, 200000000), (AG006, Dai ly Thanh Binh, 80000000), (AG008, Dai ly Phuong Nam, 45000000)] | Return | Tra ve ket qua thong ke |
| 10 | AgentStatDAO | AgentStatFrm | return List<AgentStatistics> | Return | Tra ve danh sach thong ke |
| 11 | AgentStatFrm | AgentStatFrm | displayStats(list) | Self | Hien thi danh sach len grdAgentStats |
| 12 | AgentStatFrm | AgentStatFrm | calculateTotalRevenue(list) | Self | Tinh tong doanh thu = 325,000,000 |
| 13 | AgentStatFrm | Manager | showTotalRevenue("325,000,000 VND") | Return | Hien thi tong doanh thu ky |
| 14 | Manager | AgentStatFrm | selectAgent("AG005") | Sync | Manager nhan chon dai ly AG005 |
| 15 | AgentStatFrm | AgentStatDAO | getAgentInvoices("AG005", fromDate, toDate) | Sync | Goi DAO lay chi tiet hoa don cho AG005 |
| 16 | AgentStatDAO | Database | SELECT e.invoiceId, e.exportDate, COUNT(d.detailId) AS totalItems, e.totalAmount FROM tblExportInvoice e JOIN tblExportInvoiceDetail d ON e.invoiceId = d.invoiceId WHERE e.agentId = 'AG005' AND e.exportDate BETWEEN '2026-01-01' AND '2026-03-31' GROUP BY e.invoiceId, e.exportDate, e.totalAmount ORDER BY e.exportDate DESC | Sync | Truy van chi tiet |
| 17 | Database | AgentStatDAO | ResultSet: [(PX2026015, 28/03/2026, 3, 90000000), (PX2026012, 05/03/2026, 2, 50000000), (PX2026003, 10/01/2026, 3, 75000000)] | Return | Tra ve chi tiet |
| 18 | AgentStatDAO | AgentStatFrm | return List<AgentInvoiceSummary> | Return | Tra ve danh sach chi tiet |
| 19 | AgentStatFrm | AgentStatFrm | displayInvoices(list) | Self | Hien thi chi tiet len grdInvoiceDetail |
| 20 | AgentStatFrm | AgentStatFrm | calculateInvoiceTotals(list) | Self | Tinh tong so hoa don, tong tien cho AG005 |
| 21 | AgentStatFrm | Manager | showInvoiceTotals(count=3, total=215000000) | Return | Hien thi tong cho AG005 |
| 22 | Manager | AgentStatFrm | selectAgent("AG006") | Sync | Manager nhan chon dai ly AG006 |
| 23 | AgentStatFrm | AgentStatDAO | getAgentInvoices("AG006", fromDate, toDate) | Sync | Goi DAO lay chi tiet cho AG006 |
| 24 | AgentStatDAO | Database | (tuong tu query cho AG006) | Sync | Truy van chi tiet |
| 25 | Database | AgentStatDAO | ResultSet cho AG006 | Return | Tra ve chi tiet |
| 26 | AgentStatDAO | AgentStatFrm | return List<AgentInvoiceSummary> | Return | Tra ve danh sach chi tiet |
| 27 | AgentStatFrm | AgentStatFrm | displayInvoices(list) | Self | Cap nhat grdInvoiceDetail cho AG006 |
| 28 | Manager | AgentStatFrm | clickExport() | Sync | Manager nhan "Xuat bao cao" |
| 29 | AgentStatFrm | AgentStatFrm | exportReport() | Self | Tao file bao cao Excel/PDF |
| 30 | AgentStatFrm | Manager | showSuccess("Xuat bao cao thanh cong!") | Return | Thong bao thanh cong |

---

## Câu 5: Blackbox Testing (Test Plan)

### Test Plan cho chuc nang Thong ke dai ly

| Test Case | Mo ta | Input | Expected Output |
|-----------|-------|-------|-----------------|
| TC01 | Thong ke dai ly thanh cong trong ky co du lieu | fromDate=01/01/2026, toDate=31/03/2026 | Bang hien thi danh sach dai ly theo doanh thu giam dan, tong doanh thu dung |
| TC02 | Thong ke trong ky khong co du lieu | fromDate=01/01/2020, toDate=31/03/2020 | Bang trong, thong bao "Khong co du lieu" |
| TC03 | Ngay bat dau > ngay ket thuc | fromDate=31/03/2026, toDate=01/01/2026 | Hien thi loi "Ngay bat dau phai <= ngay ket thuc" |
| TC04 | Xem chi tiet dai ly khi nhan vao dong | Chon AG005 trong bang thong ke | Bang chi tiet hien thi cac hoa don cua AG005 |
| TC05 | Chuyen doi giua cac dai ly | Chon AG005 roi chon AG006 | Bang chi tiet cap nhat cho AG006 |
| TC06 | Thay doi khoang thoi gian va thong ke lai | Thay toDate tu 31/03 sang 30/06 | Bang cap nhat du lieu Q2/2026 |
| TC07 | Xuat bao cao | Nhan btnExport | File bao cao duoc tao, thong bao thanh cong |
| TC08 | Ky thong ke chi co 1 ngay | fromDate=toDate=08/06/2026 | Thong ke cho dung ngay do |

### TC01: Thong ke dai ly thanh cong trong ky co du lieu

**Mo ta:** Kiem tra chuc nang thong ke dai ly khi chon khoang thoi gian co du lieu xuat hang, dam bao bang hien thi dung va chi tiet khi chon mot dai ly.

**Preconditions:**
- Manager da dang nhap (userId = "NV001", role = "Manager").
- He thong co du lieu hoa don xuat trong Q1/2026.

**Database Before (Du lieu truoc khi chay test):**

Bang `tblUser`:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV001 | admin | admin123 | Tran Van A | Manager |
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |

Bang `tblSubAgent`:
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |
| AG008 | Dai ly Phuong Nam | 789 Cach Mang Thang 8, Q.10, TP.HCM | 0903456789 |

Bang `tblItem`:
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |

Bang `tblExportInvoice`:
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| PX2026003 | 2026-01-10 | 75,000,000 | AG005 | NV003 |
| PX2026008 | 2026-02-15 | 125,000,000 | AG006 | NV003 |
| PX2026012 | 2026-03-05 | 50,000,000 | AG005 | NV003 |
| PX2026015 | 2026-03-28 | 90,000,000 | AG005 | NV003 |
| PX2026018 | 2026-03-30 | 45,000,000 | AG008 | NV003 |
| PX2026020 | 2026-04-10 | 60,000,000 | AG006 | NV003 |

Bang `tblExportInvoiceDetail`:
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ED001 | PX2026003 | IT012 | 200 | 250,000 | 50,000,000 |
| ED002 | PX2026003 | IT023 | 100 | 180,000 | 18,000,000 |
| ED003 | PX2026003 | IT034 | 50 | 120,000 | 6,000,000 |
| ED004 | PX2026008 | IT012 | 300 | 250,000 | 75,000,000 |
| ED005 | PX2026008 | IT034 | 200 | 120,000 | 24,000,000 |
| ED006 | PX2026012 | IT023 | 150 | 180,000 | 27,000,000 |
| ED007 | PX2026012 | IT034 | 100 | 120,000 | 12,000,000 |
| ED008 | PX2026015 | IT012 | 200 | 250,000 | 50,000,000 |
| ED009 | PX2026015 | IT034 | 150 | 120,000 | 18,000,000 |
| ED010 | PX2026018 | IT023 | 200 | 180,000 | 36,000,000 |
| ED011 | PX2026018 | IT034 | 50 | 120,000 | 6,000,000 |
| ED012 | PX2026020 | IT012 | 100 | 250,000 | 25,000,000 |
| ED013 | PX2026020 | IT034 | 200 | 120,000 | 24,000,000 |

**Test Steps (Kich ban >= 7 buoc):**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|------------------|
| 1 | Manager nhan menu "Thong ke dai ly" | -- | Giao dien AgentStatFrm hien thi voi dtpFromDate, dtpToDate, btnStat, grdAgentStats trong |
| 2 | Manager chon fromDate = 01/01/2026, toDate = 31/03/2026, nhan btnStat | dtpFromDate="01/01/2026"; dtpToDate="31/03/2026" | grdAgentStats hien thi 3 dong (chi tinh PX2026003, PX2026008, PX2026012, PX2026015, PX2026018 -- khong tinh PX2026020 vi ngay 10/04): |
| 3 | Manager xem bang grdAgentStats | -- | Dong 1: AG005, Dai ly Minh Khang, Doanh thu=215,000,000 (PX2026003: 75M + PX2026012: 50M + PX2026015: 90M). Dong 2: AG006, Dai ly Thanh Binh, Doanh thu=125,000,000 (PX2026008: 125M). Dong 3: AG008, Dai ly Phuong Nam, Doanh thu=45,000,000 (PX2026018: 45M). lblTotalRevenue = "385,000,000 VND". |
| 4 | Manager nhan chon dong AG005 trong grdAgentStats | Chon AG005 | grdInvoiceDetail hien thi 3 dong chi tiet cho AG005 trong Q1/2026: (1) PX2026015 - 28/03/2026 - 3 mat hang - 90,000,000; (2) PX2026012 - 05/03/2026 - 2 mat hang - 50,000,000; (3) PX2026003 - 10/01/2026 - 3 mat hang - 75,000,000. lblInvoiceCount = "3 hoa don". lblInvoiceTotal = "215,000,000 VND". |
| 5 | Manager nhan chon dong AG006 trong grdAgentStats | Chon AG006 | grdInvoiceDetail cap nhat hien thi 1 dong cho AG006: (1) PX2026008 - 15/02/2026 - 2 mat hang - 125,000,000. lblInvoiceCount = "1 hoa don". lblInvoiceTotal = "125,000,000 VND". |
| 6 | Manager nhan chon dong AG008 trong grdAgentStats | Chon AG008 | grdInvoiceDetail cap nhat hien thi 1 dong cho AG008: (1) PX2026018 - 30/03/2026 - 2 mat hang - 45,000,000. lblInvoiceCount = "1 hoa don". lblInvoiceTotal = "45,000,000 VND". |
| 7 | Manager thay doi toDate = 30/06/2026, nhan btnStat | dtpToDate="30/06/2026" | grdAgentStats cap nhat bao gom ca PX2026020 (10/04). AG006 tang len 185M (125M + 60M). grdInvoiceDetail duoc xoa trang. |
| 8 | Manager nhan btnExport | -- | File bao cao duoc tao. Thong bao "Xuat bao cao thanh cong!". |

**Database After (Du lieu sau khi chay test):**

Bang `tblUser` (khong thay doi):
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV001 | admin | admin123 | Tran Van A | Manager |
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |

Bang `tblSubAgent` (khong thay doi):
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |
| AG008 | Dai ly Phuong Nam | 789 Cach Mang Thang 8, Q.10, TP.HCM | 0903456789 |

Bang `tblItem` (khong thay doi):
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |

Bang `tblExportInvoice` (khong thay doi):
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| PX2026003 | 2026-01-10 | 75,000,000 | AG005 | NV003 |
| PX2026008 | 2026-02-15 | 125,000,000 | AG006 | NV003 |
| PX2026012 | 2026-03-05 | 50,000,000 | AG005 | NV003 |
| PX2026015 | 2026-03-28 | 90,000,000 | AG005 | NV003 |
| PX2026018 | 2026-03-30 | 45,000,000 | AG008 | NV003 |
| PX2026020 | 2026-04-10 | 60,000,000 | AG006 | NV003 |

Bang `tblExportInvoiceDetail` (khong thay doi):
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ED001-ED013 | (giu nguyen nhu Database Before) | | | | |

Ghi chu: Chuc nang thong ke la READ-ONLY, khong thay doi bat ky du lieu nao trong database.

**Pass Criteria:**
- grdAgentStats hien thi dung 3 dai ly cho Q1/2026, sap xep theo doanh thu giam dan.
- Thu tu dung: AG005 (215,000,000) > AG006 (125,000,000) > AG008 (45,000,000).
- Tong doanh thu Q1/2026 = 215M + 125M + 45M = 385,000,000 VND.
- Khi chon AG005, grdInvoiceDetail hien thi dung 3 hoa don (PX2026003, PX2026012, PX2026015).
- Khi chon AG006, grdInvoiceDetail hien thi dung 1 hoa don (PX2026008).
- Khi chon AG008, grdInvoiceDetail hien thi dung 1 hoa don (PX2026018).
- So mat hang trong moi hoa don duoc dem dung tu bang tblExportInvoiceDetail.
- Thay doi toDate sang 30/06, bang cap nhat bao gom PX2026020 (AG006 tang len 185M).
- Khong co du lieu nao trong database bi thay doi sau khi chay test.
