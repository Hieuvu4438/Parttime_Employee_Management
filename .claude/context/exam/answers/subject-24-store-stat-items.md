# Subject 24 -- Store Management -- Module "Statistics of Items"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Manager (Quan ly):** Nguoi xem thong ke hang hoa, phan tich doanh thu theo mat hang.
- **System (He thong):** He thong quan ly kho, xu ly truy van thong ke, hien thi bao cao.

### Preconditions
- Manager da dang nhap thanh cong vao he thong voi vai tro "Manager".
- He thong co du lieu hoa don xuat (tblExportInvoice, tblExportInvoiceDetail) trong co so du lieu.
- Du lieu hoa don da duoc nhap day du tu cac giao dich xuat hang truoc do.

### Main Scenario: Thong ke hang hoa theo doanh thu

| Buoc | Actor | Hanh dong | He thong phan hoi |
|------|-------|-----------|-------------------|
| 1 | Manager | Nhan chon menu "Thong ke hang hoa" (Item Statistics) tren giao dien chinh. | Hien thi trang Thong ke hang hoa voi o nhap khoang thoi gian (dtpFromDate, dtpToDate), nut "Thong ke" (btnStat), va vung bang ket qua (grdItemStats) trong. |
| 2 | Manager | Chon ngay bat dau "01/01/2026" vao dtpFromDate, chon ngay ket thuc "31/03/2026" vao dtpToDate. Nhan nut "Thong ke" (btnStat). | He thong truy van: SELECT itemId, itemName, SUM(quantity) as totalQty, SUM(amount) as totalRevenue FROM tblExportInvoiceDetail JOIN tblExportInvoice ON ... WHERE exportDate BETWEEN '2026-01-01' AND '2026-03-31' GROUP BY itemId, itemName ORDER BY totalRevenue DESC. Hien thi bang ket qua grdItemStats gom cot: STT, Ma hang, Ten hang, So luong ban, Doanh thu. |
| 3 | Manager | Xem bang ket qua thong ke. | He thong hien thi danh sach hang hoa da ban trong Q1/2026, sap xep theo doanh thu giam dan. Vi du: (1) IT012 - Sua Vinamilk - 1,500 thung - 375,000,000; (2) IT034 - Nuoc ngot Coca - 2,000 chai - 240,000,000; (3) IT023 - Banh Oreo - 800 hop - 144,000,000; ... Tong doanh thu ky: lblTotalRevenue = "759,000,000 VND". |
| 4 | Manager | Nhan chon dong "IT012 - Sua Vinamilk" trong grdItemStats. | He thong hien thi chi tiet thong ke cho hang hoa IT012: Bang grdItemDetail gom cot: Ma phieu xuat, Ngay xuat, Ma dai ly, Ten dai ly, So luong, Don gia, Thanh tien. Cac dong sap xep theo ngay xuat giam dan. |
| 5 | Manager | Xem bang chi tiet. | He thong hien thi danh sach cac hoa don xuat chua hang hoa IT012 trong ky Q1/2026. Vi du: (1) PX2026015 - 28/03/2026 - AG005 - Dai ly Minh Khang - 200 thung - 250,000 - 50,000,000; (2) PX2026008 - 15/02/2026 - AG006 - Dai ly Thanh Binh - 300 thung - 250,000 - 75,000,000; ... |
| 6 | Manager | Nhan chon dong khac "IT034 - Nuoc ngot Coca" trong grdItemStats. | He thong cap nhat bang grdItemDetail hien thi chi tiet cho hang hoa IT034. |
| 7 | Manager | Thay doi khoang thoi gian: dtpFromDate = "01/04/2026", dtpToDate = "30/06/2026". Nhan btnStat. | He thong truy van lai voi khoang thoi gian moi (Q2/2026). Hien thi bang grdItemStats cap nhat voi du lieu Q2/2026. grdItemDetail duoc xoa trang vi chua chon hang hoa cu the. |
| 8 | Manager | Nhan nut "Xuat bao cao" (btnExport). | He thong xuat bao cao thong ke hang hoa ra file Excel/PDF voi day du thong tin: header (ky thong ke), bang tong hop, bang chi tiet cho tung mat hang. Hien thi thong bao "Xuat bao cao thanh cong!". |

### Alternative Scenarios

**2a. Khong co du lieu trong ky thong ke:**
- Buoc 2 (ngoai le): Manager chon khoang thoi gian khong co du lieu xuat (vi du: nam 2020). He thong hien thi bang grdItemStats trong va thong bao: "Khong co du lieu xuat hang trong khoang thoi gian da chon." lblTotalRevenue = "0 VND".

**2b. Ngay bat dau > ngay ket thuc:**
- Buoc 2 (ngoai le): Manager chon dtpFromDate = "31/03/2026", dtpToDate = "01/01/2026". He thong hien thi loi: "Ngay bat dau phai nho hon hoac bang ngay ket thuc."

**4a. Nhan vao hang hoa nhung khong co chi tiet:**
- Buoc 4 (ngoai le): Du lieu tong hop co nhung chi tiet phieu xuat bi thieu (loi du lieu). He thong hien thi bang grdItemDetail trong va thong bao: "Khong tim thay chi tiet phieu xuat cho hang hoa nay."

**8a. Loi khi xuat bao cao:**
- Buoc 8 (ngoai le): He thong gap loi khi ghi file. Hien thi loi: "Khong the xuat bao cao. Vui long thu lai sau."

### Postconditions
- Bang thong ke hang hoa hien thi dung du lieu theo khoang thoi gian da chon.
- Khi Manager chon mot hang hoa, bang chi tiet hien thi dung cac phieu xuat chua hang hoa do.
- Bao cao xuat ra file chua day du thong tin thong ke.
- Du lieu trong he thong khong bi thay doi (chuc nang chi doc).

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management - Module Thong ke hang hoa bao gom cac thuc the sau:

1. **Item (Hang hoa):** Moi hang hoa co ma hang (itemId), ten hang (itemName), mo ta (description). Hang hoa la doi tuong chinh duoc thong ke. Mot hang hoa co the xuat hien trong nhieu chi tiet hoa don xuat khac nhau. Du lieu thong ke bao gom so luong ban va doanh thu theo tung ky.

2. **ExportInvoice (Phieu xuat):** Moi phieu xuat co ma phieu (invoiceId), ngay xuat (exportDate), tong tien (totalAmount), ma dai ly (agentId), ma nhan vien (userId). Phieu xuat la nguon du lieu de tinh toan thong ke. Mot phieu xuat co the chua nhieu mat hang khac nhau.

3. **ExportInvoiceDetail (Chi tiet phieu xuat):** Moi chi tiet co ma chi tiet (detailId), ma phieu xuat (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Day la bang du lieu chinh de truy van thong ke, vi moi dong chua thong tin ve mot mat hang trong mot lan xuat.

4. **SubAgent (Dai ly):** Moi dai ly co ma (agentId), ten (agentName), dia chi (address), so dien thoai (phone). Dai ly xuat hien trong chi tiet thong ke khi Manager nhan vao mot hang hoa cu the de xem chi tiet cac phieu xuat.

5. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Manager la nguoi dung co vai tro "Manager" thuc hien chuc nang thong ke.

### Trich xuat danh tu tu mo ta module

| STT | Danh tu trong mo ta | Entity tuong ung | Thuoc tinh |
|-----|---------------------|------------------|------------|
| 1 | Hang hoa, mat hang, san pham, item | Item | itemId, itemName, description |
| 2 | Phieu xuat, hoa don xuat, export invoice | ExportInvoice | invoiceId, exportDate, totalAmount, agentId, userId |
| 3 | Chi tiet phieu xuat, chi tiet hoa don, dong hang | ExportInvoiceDetail | detailId, invoiceId, itemId, quantity, unitPrice, amount |
| 4 | Dai ly, dai ly con, sub-agent | SubAgent | agentId, agentName, address, phone |
| 5 | Quan ly, nguoi dung, nhan vien, manager | User | userId, username, password, fullName, role |
| 6 | Doanh thu, tong tien, revenue | (thuoc tinh tinh toan) | totalRevenue = SUM(amount) |
| 7 | So luong ban, quantity sold | (thuoc tinh tinh toan) | totalQty = SUM(quantity) |
| 8 | Ky thong ke, khoang thoi gian, period | (tham so dau vao) | fromDate, toDate |

### Moi quan he giua cac Entity

| Moi quan he | Kieu | Mo ta |
|-------------|------|-------|
| Item -- ExportInvoiceDetail | 1 : N | Mot hang hoa co the co trong nhieu chi tiet phieu xuat |
| ExportInvoice -- ExportInvoiceDetail | 1 : N | Mot phieu xuat co nhieu chi tiet |
| SubAgent -- ExportInvoice | 1 : N | Mot dai ly co the co nhieu phieu xuat |
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
                             |   ItemStatistics         |
                             +--------------------------+
                             | - itemId: String         |
                             | - itemName: String       |
                             | - totalQty: int          |
                             | - totalRevenue: float    |
                             +--------------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: User, SubAgent, Item, ExportInvoice, ExportInvoiceDetail |
| 3 | Tạo các DTO class boxes cho: ItemStatistics, ExportDetailInfo |
| 4 | Tạo các view class boxes: HomeFrm, ItemStatFrm |
| 5 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ ExportInvoiceDetail |
|------|----------|--------------------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>`, `<<dto>>`, hoặc `<<boundary>>` + tên class | `<<entity>> ExportInvoiceDetail` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-detailId: String` `-quantity: int` `-unitPrice: float` `-amount: float` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | — |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| User | `<<entity>>` | `-userId: String`, `-username: String`, `-password: String`, `-fullName: String`, `-role: String` | — |
| SubAgent | `<<entity>>` | `-agentId: String`, `-agentName: String`, `-address: String`, `-phone: String` | — |
| Item | `<<entity>>` | `-itemId: String`, `-itemName: String`, `-description: String` | `+getItemStats(from: Date, to: Date): List<ItemStatistics>` |
| ExportInvoice | `<<entity>>` | `-invoiceId: String`, `-exportDate: Date`, `-totalAmount: float`, `-agentId: String`, `-userId: String` | — |
| ExportInvoiceDetail | `<<entity>>` | `-detailId: String`, `-invoiceId: String`, `-itemId: String`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | `+getItemDetail(itemId: String, from: Date, to: Date): List<ExportDetailInfo>` |
| ItemStatistics | `<<dto>>` | `-itemId: String`, `-itemName: String`, `-totalQty: int`, `-totalRevenue: float` | — |
| ExportDetailInfo | `<<dto>>` | `-invoiceId: String`, `-exportDate: Date`, `-agentId: String`, `-agentName: String`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subItemStat` (Button — chọn chức năng thống kê hàng hóa) |
| ItemStatFrm | `<<boundary>>` | `inStartDate` (DatePicker — chọn ngày bắt đầu), `inEndDate` (DatePicker — chọn ngày kết thúc), `subStat` (Button — thực hiện thống kê), `outsubListItemStat` (Table — bảng tổng hợp hàng hóa theo doanh thu, click chọn), `outTotalRevenue` (Label — tổng doanh thu kỳ), `outSelectedTitle` (Label — tiêu đề chi tiết), `outListItemDetail` (Table — chi tiết phiếu xuất), `outItemTotalQty` (Label — tổng SL bán của hàng hóa), `outItemTotalRevenue` (Label — tổng doanh thu của hàng hóa), `subExport` (Button — xuất báo cáo) |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu thông thường |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | Child không tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | View gọi DAO, DAO tạo DTO |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 1..* | `N` | Nhiều đối tượng |

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| User | ExportInvoice | Association | 1 --- N | Một nhân viên tạo nhiều phiếu xuất |
| SubAgent | ExportInvoice | Association | 1 --- N | Một đại lý có nhiều phiếu xuất |
| ExportInvoice | ExportInvoiceDetail | Composition | 1 --- N | Chi tiết không tồn tại nếu không có phiếu xuất cha |
| Item | ExportInvoiceDetail | Association | 1 --- N | Một hàng hóa xuất hiện trong nhiều chi tiết |
| ItemStatDAO | ItemStatistics | Dependency | — | DAO tạo đối tượng DTO khi truy vấn thống kê |
| ItemStatDAO | ExportDetailInfo | Dependency | — | DAO tạo đối tượng DTO khi truy vấn chi tiết |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ ExportInvoice (1) --- (N) ExportInvoiceDetail (Composition)**

1. Tạo class `ExportInvoice` với stereotype `<<entity>>`, thêm attribute: `-invoiceId: String`, `-exportDate: Date`, `-totalAmount: float`.
2. Tạo class `ExportInvoiceDetail` với stereotype `<<entity>>`, thêm attribute: `-detailId: String`, `-quantity: int`, `-amount: float`.
3. Kéo tool **Composition** → click `ExportInvoice` → kéo sang `ExportInvoiceDetail`.
4. Đặt multiplicity: `1` ở đầu ExportInvoice, `N` ở đầu ExportInvoiceDetail.

**Ví dụ 2: Vẽ Dependency từ ItemStatDAO đến ItemStatistics**

1. Tạo class `ItemStatDAO` với stereotype `<<control>>`.
2. Tạo class `ItemStatistics` với stereotype `<<dto>>`.
3. Kéo tool **Dependency** (đường dashed) → click `ItemStatDAO` → kéo sang `ItemStatistics`.
4. Ghi chú method trên đường dependency: `getItemStats()`.

---

### Classes diagram (analysis)

Phan tich module nay (bo qua buoc dang nhap):

Sau khi dang nhap thanh cong -> HomeFrm:
  mot lua chon de thong ke hang hoa -> subItemStat

Chon thong ke hang hoa -> ItemStatFrm hien thi:
  o chon ngay bat dau -> inStartDate
  o chon ngay ket thuc -> inEndDate
  nut thong ke -> subStat
  bang tong hop hang hoa theo doanh thu -> outsubListItemStat
  tong doanh thu ky -> outTotalRevenue
  tieu de chi tiet -> outSelectedTitle
  bang chi tiet phieu xuat -> outListItemDetail
  tong so luong ban cua hang hoa -> outItemTotalQty
  tong doanh thu cua hang hoa -> outItemTotalRevenue
  nut xuat bao cao -> subExport

Staff chon khoang thoi gian va nhan thong ke -> he thong truy van thong ke hang hoa -> can mot phuong thuc:
  ten: getItemStats()
  dau vao: fromDate (Date), toDate (Date)
  dau ra: List<ItemStatistics>
  gan cho entity class: Item (thong ke tu ExportInvoiceDetail)

Staff nhan chon mot hang hoa -> he thong truy van chi tiet phieu xuat -> can mot phuong thuc:
  ten: getItemDetail()
  dau vao: itemId (String), fromDate (Date), toDate (Date)
  dau ra: List<ExportDetailInfo>
  gan cho entity class: ExportInvoice, ExportInvoiceDetail, SubAgent

### Tong hop
View classes: HomeFrm, ItemStatFrm
Methods: getItemStats(), getItemDetail()

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
| ItemStatFrm | Giao dien thong ke hang hoa. Chua o nhap khoang thoi gian, nut thong ke, bang tong hop hang hoa theo doanh thu, bang chi tiet phieu xuat khi chon mot hang hoa, nut xuat bao cao. | MainForm (mo tu menu "Thong ke hang hoa") |

### UI Elements trong ItemStatFrm

| UI Element | Loai | Thuoc tinh | Mo ta |
|------------|------|------------|-------|
| dtpFromDate | DateTimePicker | Format = Short | O chon ngay bat dau ky thong ke |
| dtpToDate | DateTimePicker | Format = Short | O chon ngay ket thuc ky thong ke |
| btnStat | Button | Text = "Thong ke" | Nut thuc hien truy van thong ke |
| grdItemStats | DataGridView | ReadOnly = true, SelectionMode = FullRowSelect | Bang tong hop hang hoa: STT, Ma hang, Ten hang, So luong ban, Doanh thu |
| lblTotalRevenue | Label | Text | Hien thi tong doanh thu toan ky |
| lblSelectedTitle | Label | Text | Tieu de "Chi tiet phieu xuat cho: [Ten hang]" |
| grdItemDetail | DataGridView | ReadOnly = true | Bang chi tiet phieu xuat: Ma phieu, Ngay, Ma dai ly, Ten dai ly, SL, Don gia, Thanh tien |
| lblItemTotalQty | Label | Text | Hien thi tong so luong ban cua hang hoa duoc chon |
| lblItemTotalRevenue | Label | Text | Hien thi tong doanh thu cua hang hoa duoc chon |
| btnExport | Button | Text = "Xuat bao cao" | Nut xuat bao cao ra file |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| ItemStatDAO | tblExportInvoice, tblExportInvoiceDetail, tblItem | getItemStats(Date fromDate, Date toDate): List<ItemStatistics>, getItemDetail(String itemId, Date fromDate, Date toDate): List<ExportDetailInfo> |
| SubAgentDAO | tblSubAgent | getById(String agentId): SubAgent |
| ExportInvoiceDAO | tblExportInvoice | getByDateRange(Date fromDate, Date toDate): List<ExportInvoice> |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Item | Entity | id: int (PK), itemId: String, itemName: String, description: String |
| ExportInvoice | Entity | id: int (PK), invoiceId: String, exportDate: Date, totalAmount: float, agent: SubAgent (object), user: User (object) |
| ExportInvoiceDetail | Entity | id: int (PK), exportInvoice: ExportInvoice (object), item: Item (object), quantity: int, unitPrice: float, amount: float |
| SubAgent | Entity | id: int (PK), agentId: String, agentName: String, address: String, phone: String |
| User | Entity | id: int (PK), userId: String, username: String, password: String, fullName: String, role: String |

### Entity Types

| Entity | Kieu du lieu | Thuoc tinh |
|--------|--------------|------------|
| Item | Entity Class | itemId: String, itemName: String, description: String |
| ExportInvoice | Entity Class | invoiceId: String, exportDate: Date, totalAmount: float, agentId: String, userId: String |
| ExportInvoiceDetail | Entity Class | detailId: String, invoiceId: String, itemId: String, quantity: int, unitPrice: float, amount: float |
| SubAgent | Entity Class | agentId: String, agentName: String, address: String, phone: String |
| User | Entity Class | userId: String, username: String, password: String, fullName: String, role: String |
| ItemStatistics | DTO Class | itemId: String, itemName: String, totalQty: int, totalRevenue: float |
| ExportDetailInfo | DTO Class | invoiceId: String, exportDate: Date, agentId: String, agentName: String, quantity: int, unitPrice: float, amount: float |

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
3. Tao 2 DTO class: ItemStatistics, ExportDetailInfo.
4. Them attributes cho moi class.
5. Ve association:
   - User 1---N ExportInvoice.
   - SubAgent 1---N ExportInvoice.
   - ExportInvoice 1---N ExportInvoiceDetail.
   - Item 1---N ExportInvoiceDetail.
6. Ve dependency: ItemStatDAO ..> ItemStatistics, ItemStatDAO ..> ExportDetailInfo.
7. Export diagram duoi dang PNG.

---

## Câu 4: Sequence Diagram (Chuc nang thong ke hang hoa)

### Visual Paradigm (VP) Guide

1. Mo VP -> New -> Sequence Diagram.
2. Tao cac lifeline: Manager, ItemStatFrm, ItemStatDAO, SubAgentDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho truong hop khong co du lieu.
5. Su dung loop fragment cho hien thi danh sach va chi tiet.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Manager        ItemStatFrm      ItemStatDAO     SubAgentDAO     Database
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
  |                |--getItemStats->|                |              |
  |                |  (from, to)    |                |              |
  |                |                |--SELECT--------|------------->|
  |                |                |  d.itemId,     |              |
  |                |                |  i.itemName,   |              |
  |                |                |  SUM(d.qty),   |              |
  |                |                |  SUM(d.amount) |              |
  |                |                |  FROM detail d  |              |
  |                |                |  JOIN invoice e |              |
  |                |                |  JOIN item i    |              |
  |                |                |  WHERE e.date   |              |
  |                |                |  BETWEEN from   |              |
  |                |                |  AND to         |              |
  |                |                |  GROUP BY       |              |
  |                |                |  d.itemId,      |              |
  |                |                |  i.itemName     |              |
  |                |                |  ORDER BY       |              |
  |                |                |  SUM(amount)    |              |
  |                |                |  DESC           |              |
  |                |                |<--ResultSet-----|              |
  |                |<--List<Stats>--|                |              |
  |                |                |                |              |
  |                |--displayStats()|                |              |
  |<--showGrdStats-|                |                |              |
  |                |--calcTotalRevenue()            |              |
  |<--showTotal----|                |                |              |
  |                |                |                |              |
  |--clickItem---->|                |                |              |
  |  "IT012"       |                |                |              |
  |                |--getItemDetail>|                |              |
  |                |  "IT012", from, to             |              |
  |                |                |--SELECT--------|------------->|
  |                |                |  e.invoiceId,  |              |
  |                |                |  e.exportDate, |              |
  |                |                |  e.agentId,    |              |
  |                |                |  s.agentName,  |              |
  |                |                |  d.quantity,   |              |
  |                |                |  d.unitPrice,  |              |
  |                |                |  d.amount      |              |
  |                |                |  FROM detail d  |              |
  |                |                |  JOIN invoice e |              |
  |                |                |  JOIN agent s   |              |
  |                |                |  WHERE d.itemId |              |
  |                |                |  = 'IT012'      |              |
  |                |                |  AND e.date     |              |
  |                |                |  BETWEEN from   |              |
  |                |                |  AND to         |              |
  |                |                |  ORDER BY       |              |
  |                |                |  e.exportDate   |              |
  |                |                |  DESC           |              |
  |                |                |<--ResultSet-----|              |
  |                |<--List<Detail>-|                |              |
  |                |                |                |              |
  |                |--displayDetail()               |              |
  |<--showGrdDetail|                |                |              |
  |                |--calcItemTotals()              |              |
  |<--showItemTotal|                |                |              |
  |                |                |                |              |
  |--clickItem---->|                |                |              |
  |  "IT034"       |                |                |              |
  |                |  (tuong tu, truy van IT034)    |              |
  |                |                |                |              |
  |--clickExport-->|                |                |              |
  |                |--exportReport()                |              |
  |                |--generateFile()                |              |
  |<--showSuccess--|                |                |              |
```

### Bang chi tiet Sequence Diagram

| Buoc | From | To | Message | Loai | Ghi chu |
|------|------|-----|---------|------|---------|
| 1 | Manager | ItemStatFrm | selectItemStatMenu() | Sync | Manager nhan menu "Thong ke hang hoa" |
| 2 | ItemStatFrm | ItemStatFrm | initForm() | Self | Khoi tao form, dat ngay mac dinh |
| 3 | Manager | ItemStatFrm | setFromDate("01/01/2026") | Sync | Manager chon ngay bat dau |
| 4 | Manager | ItemStatFrm | setToDate("31/03/2026") | Sync | Manager chon ngay ket thuc |
| 5 | Manager | ItemStatFrm | clickStat() | Sync | Manager nhan nut "Thong ke" |
| 6 | ItemStatFrm | ItemStatFrm | validateDates() | Self | Kiem tra fromDate <= toDate -> hop le |
| 7 | ItemStatFrm | ItemStatDAO | getItemStats(fromDate, toDate) | Sync | Goi DAO lay thong ke hang hoa |
| 8 | ItemStatDAO | Database | SELECT d.itemId, i.itemName, SUM(d.quantity) AS totalQty, SUM(d.amount) AS totalRevenue FROM tblExportInvoiceDetail d JOIN tblExportInvoice e ON d.invoiceId = e.invoiceId JOIN tblItem i ON d.itemId = i.itemId WHERE e.exportDate BETWEEN '2026-01-01' AND '2026-03-31' GROUP BY d.itemId, i.itemName ORDER BY totalRevenue DESC | Sync | Truy van thong ke |
| 9 | Database | ItemStatDAO | ResultSet: [(IT012, Sua Vinamilk, 1500, 375000000), (IT034, Nuoc ngot Coca, 2000, 240000000), (IT023, Banh Oreo, 800, 144000000), ...] | Return | Tra ve ket qua thong ke |
| 10 | ItemStatDAO | ItemStatFrm | return List<ItemStatistics> | Return | Tra ve danh sach thong ke |
| 11 | ItemStatFrm | ItemStatFrm | displayStats(list) | Self | Hien thi danh sach len grdItemStats |
| 12 | ItemStatFrm | ItemStatFrm | calculateTotalRevenue(list) | Self | Tinh tong doanh thu = 759,000,000 |
| 13 | ItemStatFrm | Manager | showTotalRevenue("759,000,000 VND") | Return | Hien thi tong doanh thu ky |
| 14 | Manager | ItemStatFrm | selectItem("IT012") | Sync | Manager nhan chon hang hoa IT012 |
| 15 | ItemStatFrm | ItemStatDAO | getItemDetail("IT012", fromDate, toDate) | Sync | Goi DAO lay chi tiet phieu xuat cho IT012 |
| 16 | ItemStatDAO | Database | SELECT e.invoiceId, e.exportDate, e.agentId, s.agentName, d.quantity, d.unitPrice, d.amount FROM tblExportInvoiceDetail d JOIN tblExportInvoice e ON d.invoiceId = e.invoiceId JOIN tblSubAgent s ON e.agentId = s.agentId WHERE d.itemId = 'IT012' AND e.exportDate BETWEEN '2026-01-01' AND '2026-03-31' ORDER BY e.exportDate DESC | Sync | Truy van chi tiet |
| 17 | Database | ItemStatDAO | ResultSet: [(PX2026015, 28/03/2026, AG005, Minh Khang, 200, 250000, 50000000), (PX2026008, 15/02/2026, AG006, Thanh Binh, 300, 250000, 75000000), ...] | Return | Tra ve chi tiet |
| 18 | ItemStatDAO | ItemStatFrm | return List<ExportDetailInfo> | Return | Tra ve danh sach chi tiet |
| 19 | ItemStatFrm | ItemStatFrm | displayDetail(list) | Self | Hien thi chi tiet len grdItemDetail |
| 20 | ItemStatFrm | ItemStatFrm | calculateItemTotals(list) | Self | Tinh tong SL, doanh thu cho IT012 |
| 21 | ItemStatFrm | Manager | showItemTotals(totalQty, totalRevenue) | Return | Hien thi tong cho IT012 |
| 22 | Manager | ItemStatFrm | selectItem("IT034") | Sync | Manager nhan chon hang hoa IT034 |
| 23 | ItemStatFrm | ItemStatDAO | getItemDetail("IT034", fromDate, toDate) | Sync | Goi DAO lay chi tiet cho IT034 |
| 24 | ItemStatDAO | Database | (tuong tu query cho IT034) | Sync | Truy van chi tiet |
| 25 | Database | ItemStatDAO | ResultSet cho IT034 | Return | Tra ve chi tiet |
| 26 | ItemStatDAO | ItemStatFrm | return List<ExportDetailInfo> | Return | Tra ve danh sach chi tiet |
| 27 | ItemStatFrm | ItemStatFrm | displayDetail(list) | Self | Cap nhat grdItemDetail cho IT034 |
| 28 | Manager | ItemStatFrm | clickExport() | Sync | Manager nhan "Xuat bao cao" |
| 29 | ItemStatFrm | ItemStatFrm | exportReport() | Self | Tao file bao cao Excel/PDF |
| 30 | ItemStatFrm | Manager | showSuccess("Xuat bao cao thanh cong!") | Return | Thong bao thanh cong |

---

## Câu 5: Blackbox Testing (Test Plan)

### Test Plan cho chuc nang Thong ke hang hoa

| Test Case | Mo ta | Input | Expected Output |
|-----------|-------|-------|-----------------|
| TC01 | Thong ke hang hoa thanh cong trong ky co du lieu | fromDate=01/01/2026, toDate=31/03/2026 | Bang hien thi danh sach hang hoa theo doanh thu giam dan, tong doanh thu dung |
| TC02 | Thong ke trong ky khong co du lieu | fromDate=01/01/2020, toDate=31/03/2020 | Bang trong, thong bao "Khong co du lieu" |
| TC03 | Ngay bat dau > ngay ket thuc | fromDate=31/03/2026, toDate=01/01/2026 | Hien thi loi "Ngay bat dau phai <= ngay ket thuc" |
| TC04 | Xem chi tiet hang hoa khi nhan vao dong | Chon IT012 trong bang thong ke | Bang chi tiet hien thi cac phieu xuat chua IT012 |
| TC05 | Chuyen doi giua cac hang hoa | Chon IT012 roi chon IT034 | Bang chi tiet cap nhat cho IT034 |
| TC06 | Thay doi khoang thoi gian va thong ke lai | Thay toDate tu 31/03 sang 30/06 | Bang cap nhat du lieu Q2/2026 |
| TC07 | Xuat bao cao | Nhan btnExport | File bao cao duoc tao, thong bao thanh cong |
| TC08 | Ky thong ke chi co 1 ngay | fromDate=toDate=08/06/2026 | Thong ke cho dung ngay do |

### TC01: Thong ke hang hoa thanh cong trong ky co du lieu

**Mo ta:** Kiem tra chuc nang thong ke hang hoa khi chon khoang thoi gian co du lieu xuat hang, dam bao bang hien thi dung va chi tiet khi chon mot hang hoa.

**Preconditions:**
- Manager da dang nhap (userId = "NV001", role = "Manager").
- He thong co du lieu hoa don xuat trong Q1/2026.

**Database Before (Du lieu truoc khi chay test):**

Bang `tblUser`:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV001 | admin | admin123 | Tran Van A | Manager |
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |

Bang `tblItem`:
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |

Bang `tblSubAgent`:
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |

Bang `tblExportInvoice`:
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| PX2026003 | 2026-01-10 | 75,000,000 | AG005 | NV003 |
| PX2026008 | 2026-02-15 | 125,000,000 | AG006 | NV003 |
| PX2026012 | 2026-03-05 | 50,000,000 | AG005 | NV003 |
| PX2026015 | 2026-03-28 | 90,000,000 | AG005 | NV003 |
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
| ED010 | PX2026020 | IT012 | 100 | 250,000 | 25,000,000 |
| ED011 | PX2026020 | IT034 | 200 | 120,000 | 24,000,000 |

**Test Steps (Kich ban >= 7 buoc):**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|------------------|
| 1 | Manager nhan menu "Thong ke hang hoa" | -- | Giao dien ItemStatFrm hien thi voi dtpFromDate, dtpToDate, btnStat, grdItemStats trong |
| 2 | Manager chon fromDate = 01/01/2026, toDate = 31/03/2026, nhan btnStat | dtpFromDate="01/01/2026"; dtpToDate="31/03/2026" | grdItemStats hien thi 3 dong (chi tinh PX2026003, PX2026008, PX2026012, PX2026015 -- khong tinh PX2026020 vi ngay 10/04): |
| 3 | Manager xem bang grdItemStats | -- | Dong 1: IT012, Sua Vinamilk, SL=700, Doanh thu=175,000,000. Dong 2: IT034, Nuoc ngot Coca, SL=500, Doanh thu=60,000,000. Dong 3: IT023, Banh Oreo, SL=250, Doanh thu=45,000,000. lblTotalRevenue = "280,000,000 VND". |
| 4 | Manager nhan chon dong IT012 trong grdItemStats | Chon IT012 | grdItemDetail hien thi 3 dong chi tiet cho IT012 trong Q1/2026: (1) PX2026015 - 28/03/2026 - AG005 - Minh Khang - 200 - 250,000 - 50,000,000; (2) PX2026008 - 15/02/2026 - AG006 - Thanh Binh - 300 - 250,000 - 75,000,000; (3) PX2026003 - 10/01/2026 - AG005 - Minh Khang - 200 - 250,000 - 50,000,000. lblItemTotalQty = "700". lblItemTotalRevenue = "175,000,000 VND". |
| 5 | Manager nhan chon dong IT034 trong grdItemStats | Chon IT034 | grdItemDetail cap nhat hien thi 4 dong cho IT034: (1) PX2026015 - 28/03/2026 - 150 - 120,000 - 18,000,000; (2) PX2026012 - 05/03/2026 - 100 - 120,000 - 12,000,000; (3) PX2026008 - 15/02/2026 - 200 - 120,000 - 24,000,000; (4) PX2026003 - 10/01/2026 - 50 - 120,000 - 6,000,000. lblItemTotalQty = "500". lblItemTotalRevenue = "60,000,000 VND". |
| 6 | Manager nhan chon dong IT023 trong grdItemStats | Chon IT023 | grdItemDetail cap nhat hien thi 2 dong cho IT023: (1) PX2026012 - 05/03/2026 - 150 - 180,000 - 27,000,000; (2) PX2026003 - 10/01/2026 - 100 - 180,000 - 18,000,000. lblItemTotalQty = "250". lblItemTotalRevenue = "45,000,000 VND". |
| 7 | Manager thay doi toDate = 30/06/2026, nhan btnStat | dtpToDate="30/06/2026" | grdItemStats cap nhat bao gom ca PX2026020 (10/04). Du lieu thay doi: IT012 tang len 800 SL / 200M doanh thu. IT034 tang len 700 SL / 84M doanh thu. grdItemDetail duoc xoa trang. |
| 8 | Manager nhan btnExport | -- | File bao cao duoc tao. Thong bao "Xuat bao cao thanh cong!". |

**Database After (Du lieu sau khi chay test):**

Bang `tblUser` (khong thay doi):
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV001 | admin | admin123 | Tran Van A | Manager |
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |

Bang `tblItem` (khong thay doi):
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |

Bang `tblSubAgent` (khong thay doi):
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |

Bang `tblExportInvoice` (khong thay doi):
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| PX2026003 | 2026-01-10 | 75,000,000 | AG005 | NV003 |
| PX2026008 | 2026-02-15 | 125,000,000 | AG006 | NV003 |
| PX2026012 | 2026-03-05 | 50,000,000 | AG005 | NV003 |
| PX2026015 | 2026-03-28 | 90,000,000 | AG005 | NV003 |
| PX2026020 | 2026-04-10 | 60,000,000 | AG006 | NV003 |

Bang `tblExportInvoiceDetail` (khong thay doi):
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ED001-ED011 | (giu nguyen nhu Database Before) | | | | |

Ghi chu: Chuc nang thong ke la READ-ONLY, khong thay doi bat ky du lieu nao trong database.

**Pass Criteria:**
- grdItemStats hien thi dung 3 hang hoa cho Q1/2026, sap xep theo doanh thu giam dan.
- Thu tu dung: IT012 (175,000,000) > IT034 (60,000,000) > IT023 (45,000,000).
- Tong doanh thu Q1/2026 = 175M + 60M + 45M = 280,000,000 VND.
- Khi chon IT012, grdItemDetail hien thi dung 3 phieu xuat (PX2026003, PX2026008, PX2026015).
- Khi chon IT034, grdItemDetail hien thi dung 4 phieu xuat.
- Khi chon IT023, grdItemDetail hien thi dung 2 phieu xuat.
- Thay doi toDate sang 30/06, bang cap nhat bao gom PX2026020.
- Khong co du lieu nao trong database bi thay doi sau khi chay test.
