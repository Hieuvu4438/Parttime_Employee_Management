# Subject 22 -- Store Management -- Module "Exporting"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Staff (Nhân vien kho):** Nguoi thuc hien thao tac xuat hang cho dai ly.
- **Sub-Agent (Dai ly):** Dai ly nhan hang tu kho.
- **System (He thong):** He thong quan ly kho, xu ly tim kiem, luu tru va in phieu xuat.

### Preconditions
- Staff da dang nhap thanh cong vao he thong.
- He thong co du lieu hang hoa trong kho (bang tblItem co san).
- He thong co du lieu dai ly (bang tblSubAgent co san).
- Staff co quyen thuc hien chuc nang xuat hang.

### Main Scenario: Xuat hang cho dai ly

| Buoc | Actor | Hanh dong | He thong phan hoi |
|------|-------|-----------|-------------------|
| 1 | Staff | Nhan chon menu "Xuat hang" (Export) tren giao dien chinh. | Hien thi trang Xuat hang voi o tim kiem dai ly (txtSearchAgent), danh sach dai ly rong, nut "Them moi dai ly" (btnAddAgent), va vung danh sach hang xuat (tblExportList). |
| 2 | Staff | Nhap ten dai ly "Dai ly Minh Khang" vao o txtSearchAgent, nhan nut "Tim kiem" (btnSearchAgent). | He thong truy van bang tblSubAgent voi dieu kien agentName LIKE '%Minh Khang%'. Hien thi danh sach dai ly phu hop trong bang ket qua (grdAgents) gom cot: Ma dai ly, Ten dai ly, Dia chi, So dien thoai. |
| 3 | Staff | Nhan chon dong "Dai ly Minh Khang" (Ma: AG005) trong grdAgents. | He thong xac nhan dai ly da chon, hien thi thong tin dai ly (lblAgentName = "Dai ly Minh Khang", lblAgentAddress = "123 Le Loi, Q.1, TP.HCM", lblAgentPhone = "0901234567"). Kich hoat vung tim kiem hang hoa. |
| 4 | Staff | Nhap ten hang hoa "Sua Vinamilk" vao o txtSearchItem, nhan nut "Tim kiem" (btnSearchItem). | He thong truy van bang tblItem voi dieu kien itemName LIKE '%Vinamilk%'. Hien thi danh sach hang hoa phu hop trong grdItems gom cot: Ma hang, Ten hang, Mo ta, Ton kho. |
| 5 | Staff | Nhan chon dong "Sua Vinamilk" (Ma: IT012, Ton kho: 500 thung) trong grdItems. | He thong hien thi thong tin hang hoa da chon. Kich hoat o nhap so luong (txtQuantity) va don gia (txtUnitPrice). |
| 6 | Staff | Nhap so luong = "50" vao txtQuantity, nhap don gia = "250,000" vao txtUnitPrice, nhan nut "Them vao danh sach" (btnAddToList). | He thong kiem tra: so luong (50) <= ton kho (500) -> hop le. Tao dong moi trong tblExportList: Ma hang IT012, Ten "Sua Vinamilk", SL: 50, Don gia: 250,000, Thanh tien: 12,500,000. Cap nhat tong tien (lblTotal). Xoa noi dung o tim kiem hang hoa. |
| 7 | Staff | Lap lai buoc 4-6 cho hang hoa "Banh Oreo" (Ma: IT023, SL: 30, Don gia: 180,000). | He thong them dong vao tblExportList: IT023, Banh Oreo, SL: 30, Don gia: 180,000, Thanh tien: 5,400,000. Cap nhat tong tien = 17,900,000. |
| 8 | Staff | Lap lai buoc 4-6 cho hang hoa "Nuoc ngot Coca" (Ma: IT034, SL: 100, Don gia: 120,000). | He thong them dong vao tblExportList: IT034, Nuoc ngot Coca, SL: 100, Don gia: 120,000, Thanh tien: 12,000,000. Cap nhat tong tien = 29,900,000. |
| 9 | Staff | Kiem tra lai toan bo danh sach hang xuat trong tblExportList, xac nhan dung. Nhan nut "Xuat hoa don" (btnSubmit). | He thong hien thi hop thoai xac nhan: "Xuat hoa don cho Dai ly Minh Khang voi tong tien 29,900,000 VND?" |
| 10 | Staff | Nhan "Xac nhan" trong hop thoai. | He thong thuc hien: (1) Tao ban ghi moi trong tblExportInvoice: Ma phieu xuat PX2026001, Ngay xuat: 08/06/2026, Ma dai ly: AG005, Tong tien: 29,900,000, Ma nhan vien: NV003. (2) Tao 3 ban ghi trong tblExportInvoiceDetail cho tung mat hang. (3) Cap nhat ton kho: IT012: 500-50=450, IT023: 200-30=170, IT034: 300-100=200. (4) Hien thi thong bao "Xuat hoa don thanh cong!" va in phieu xuat. |
| 11 | Staff | Nhan "OK" tren thong bao thanh cong. | He thong hien thi phieu xuat hoa don (report) voi day du thong tin: header (ma phieu, ngay, nhan vien), thong tin dai ly, bang chi tiet hang hoa, tong tien. Quay ve trang xuat hang trong. |

### Alternative Scenarios

**3a. Dai ly chua ton tai:**
- Buoc 3 (thay the): Staff khong tim thay dai ly trong danh sach ket qua. Staff nhan nut "Them moi dai ly". He thong hien thi form them dai ly moi (txtNewAgentName, txtNewAgentAddress, txtNewAgentPhone). Staff nhap thong tin va nhan "Luu". He thong them vao tblSubAgent va tu dong chon dai ly moi tao. Tiep tuc tu buoc 4.

**6a. So luong xuat vuot ton kho:**
- Buoc 6 (ngoai le): Staff nhap so luong = 600 cho Sua Vinamilk (ton kho = 500). He thong hien thi loi: "So luong xuat (600) vuot qua ton kho hien tai (500). Vui long nhap lai." Khong them vao danh sach. Staff sua so luong va thuc hien lai.

**6b. So luong hoac don gia khong hop le:**
- Buoc 6 (ngoai le): Staff nhap so luong = 0 hoac don gia = -100. He thong hien thi loi: "So luong va don gia phai la so duong." Khong them vao danh sach.

**6c. Hang hoa da ton tai trong danh sach:**
- Buoc 6 (ngoai le): Staff them lai hang IT012 da co trong danh sach. He thong hien thi loi: "Hang hoa IT012 da ton tai trong danh sach. Vui long xoa dong truoc khi them lai." Hoac he thong cong don so luong (tuy thiet ke).

**9a. Danh sach hang xuat trong:**
- Buoc 9 (ngoai le): Staff nhan "Xuat hoa don" khi chua them hang hoa nao. He thong hien thi loi: "Danh sach hang xuat trong. Vui long them it nhat mot mat hang."

**10a. Loi he thong khi luu:**
- Buoc 10 (ngoai le): He thong gap loi co so du lieu khi luu. He thong hien thi loi: "Khong the luu hoa don. Vui long thu lai sau." Khong thay doi du lieu (rollback transaction).

### Postconditions
- Hoa don xuat moi duoc luu trong tblExportInvoice va tblExportInvoiceDetail.
- Ton kho cac mat hang da xuat duoc cap nhat giam dung so luong.
- Phieu xuat hoa duoc in ra cho Staff va dai ly.
- Giao dien quay ve trang thai ban dau, san sang cho lan xuat tiep theo.

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management bao gom cac thuc the sau:

1. **Item (Hang hoa):** Moi hang hoa co ma hang (itemId), ten hang (itemName), mo ta (description). Mot hang hoa co the duoc nhap nhieu lan va xuat nhieu lan cho nhieu dai ly khac nhau. Hang hoa ton kho duoc quan ly qua so luong hien tai.

2. **Supplier (Nha cung cap):** Moi nha cung cap co ma (supplierId), ten (supplierName), dia chi (address), so dien thoai (phone). Mot nha cung cap co the cung cap nhieu hang hoa qua nhieu phieu nhap.

3. **SubAgent (Dai ly):** Moi dai ly co ma (agentId), ten (agentName), dia chi (address), so dien thoai (phone). Mot dai ly co the nhan nhieu hang hoa qua nhieu phieu xuat.

4. **ImportInvoice (Phieu nhap):** Moi phieu nhap co ma phieu (invoiceId), ngay nhap (importDate), tong tien (totalAmount), ma nha cung cap (supplierId), ma nhan vien nhap (userId). Mot phieu nhap thuoc ve mot nha cung cap va do mot nhan vien thuc hien.

5. **ImportInvoiceDetail (Chi tiet phieu nhap):** Moi chi tiet co ma chi tiet (detailId), ma phieu nhap (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Moi chi tiet thuoc ve mot phieu nhap va mot hang hoa.

6. **ExportInvoice (Phieu xuat):** Moi phieu xuat co ma phieu (invoiceId), ngay xuat (exportDate), tong tien (totalAmount), ma dai ly (agentId), ma nhan vien xuat (userId). Mot phieu xuat thuoc ve mot dai ly va do mot nhan vien thuc hien.

7. **ExportInvoiceDetail (Chi tiet phieu xuat):** Moi chi tiet co ma chi tiet (detailId), ma phieu xuat (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Moi chi tiet thuoc ve mot phieu xuat va mot hang hoa.

8. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Mot nguoi dung co the tao nhieu phieu nhap va nhieu phieu xuat.

### Trich xuat danh tu tu mo ta module

| STT | Danh tu trong mo ta | Entity tuong ung | Thuoc tinh |
|-----|---------------------|------------------|------------|
| 1 | Hang hoa, mat hang, san pham | Item | itemId, itemName, description |
| 2 | Nha cung cap, nha phan phoi | Supplier | supplierId, supplierName, address, phone |
| 3 | Dai ly, dai ly con | SubAgent | agentId, agentName, address, phone |
| 4 | Phieu nhap, hoa don nhap | ImportInvoice | invoiceId, importDate, totalAmount, supplierId, userId |
| 5 | Chi tiet phieu nhap | ImportInvoiceDetail | detailId, invoiceId, itemId, quantity, unitPrice, amount |
| 6 | Phieu xuat, hoa don xuat | ExportInvoice | invoiceId, exportDate, totalAmount, agentId, userId |
| 7 | Chi tiet phieu xuat | ExportInvoiceDetail | detailId, invoiceId, itemId, quantity, unitPrice, amount |
| 8 | Nhan vien, nguoi dung, tai khoan | User | userId, username, password, fullName, role |

### Moi quan he giua cac Entity

| Moi quan he | Kieu | Mo ta |
|-------------|------|-------|
| Item -- ImportInvoiceDetail | 1 : N | Mot hang hoa co the co trong nhieu chi tiet phieu nhap |
| Item -- ExportInvoiceDetail | 1 : N | Mot hang hoa co the co trong nhieu chi tiet phieu xuat |
| Supplier -- ImportInvoice | 1 : N | Mot nha cung cap co the co nhieu phieu nhap |
| SubAgent -- ExportInvoice | 1 : N | Mot dai ly co the co nhieu phieu xuat |
| ImportInvoice -- ImportInvoiceDetail | 1 : N | Mot phieu nhap co nhieu chi tiet |
| ExportInvoice -- ExportInvoiceDetail | 1 : N | Mot phieu xuat co nhieu chi tiet |
| User -- ImportInvoice | 1 : N | Mot nhan vien co the tao nhieu phieu nhap |
| User -- ExportInvoice | 1 : N | Mot nhan vien co the tao nhieu phieu xuat |

### ASCII Class Diagram

```
+------------------+          +---------------------+          +------------------+
|      User        |          |    ImportInvoice     |          |    Supplier      |
+------------------+          +---------------------+          +------------------+
| - userId: String | 1      N | - invoiceId: String | N      1 | - supplierId     |
| - username       |----------| - importDate: Date  |----------| - supplierName   |
| - password       |          | - totalAmount: float|          | - address        |
| - fullName       |          | - supplierId: String|          | - phone          |
| - role: String   |          | - userId: String    |          +------------------+
+------------------+          +---------------------+
        |                              |
        | 1                          1 |
        |                              |
        | N                            N
+------------------+          +--------------------------+
|  ExportInvoice   |          |  ImportInvoiceDetail     |
+------------------+          +--------------------------+
| - invoiceId      | 1      N | - detailId: String       |
| - exportDate:Date|----------| - invoiceId: String      |
| - totalAmount    |          | - itemId: String         |
| - agentId: String|          | - quantity: int          |
| - userId: String |          | - unitPrice: float       |
+------------------+          | - amount: float          |
        |                     +--------------------------+
        | N                             |
        |                              | N
        | 1                             | 1
+------------------+          +------------------+
|  SubAgent        |          |      Item        |
+------------------+          +------------------+
| - agentId: String| N      N | - itemId: String |
| - agentName      |----------| - itemName       |
| - address        |          | - description    |
| - phone          |          +------------------+
+------------------+                  |
        |                              | 1
        | 1                            | N
        |                    +--------------------------+
        +--------------------|  ExportInvoiceDetail     |
                             +--------------------------+
                             | - detailId: String       |
                             | - invoiceId: String      |
                             | - itemId: String         |
                             | - quantity: int          |
                             | - unitPrice: float       |
                             | - amount: float          |
                             +--------------------------+
```

### Classes diagram (analysis)

Phan tich module nay (bo qua buoc dang nhap):

Sau khi dang nhap thanh cong -> HomeFrm:
  mot lua chon de xuat hang -> subExport

Chon xuat hang -> ExportFrm hien thi:
  o tim kiem dai ly -> inSearchAgent
  nut tim kiem dai ly -> subSearchAgent
  nut them moi dai ly -> subAddAgent
  bang danh sach dai ly tim duoc -> outsubListAgent
  ten dai ly da chon -> outAgentName
  dia chi dai ly da chon -> outAgentAddress
  so dien thoai dai ly da chon -> outAgentPhone
  o tim kiem hang hoa -> inSearchItem
  nut tim kiem hang hoa -> subSearchItem
  bang danh sach hang hoa tim duoc -> outsubListItem
  o nhap so luong -> inQuantity
  o nhap don gia -> inUnitPrice
  nut them vao danh sach -> subAddToList
  bang danh sach hang xuat -> outListExport
  tong tien -> outTotal
  nut xuat hoa don -> subSubmit

Staff nhap ten dai ly va nhan tim kiem -> he thong truy van dai ly -> can mot phuong thuc:
  ten: searchAgentByName()
  dau vao: agentName (String)
  dau ra: List<SubAgent>
  gan cho entity class: SubAgent

Staff chon dai ly -> hien thi thong tin dai ly.

Staff nhap ten hang hoa va nhan tim kiem -> he thong truy van hang hoa -> can mot phuong thuc:
  ten: searchItemByName()
  dau vao: itemName (String)
  dau ra: List<Item>
  gan cho entity class: Item

Staff chon hang hoa, nhap so luong va don gia, nhan them vao danh sach -> he thong kiem tra ton kho -> can mot phuong thuc:
  ten: getStock()
  dau vao: itemId (String)
  dau ra: int (so luong ton kho)
  gan cho entity class: Item

Staff nhan xuat hoa don -> he thong tao phieu xuat va chi tiet -> can cac phuong thuc:
  ten: insertExportInvoice()
  dau vao: ExportInvoice (exportDate, totalAmount, agentId, userId)
  dau ra: String (invoiceId moi)
  gan cho entity class: ExportInvoice

  ten: insertExportInvoiceDetails()
  dau vao: List<ExportInvoiceDetail> (invoiceId, itemId, quantity, unitPrice, amount)
  dau ra: boolean
  gan cho entity class: ExportInvoiceDetail

  ten: updateStock()
  dau vao: itemId (String), qtyChange (int)
  dau ra: boolean
  gan cho entity class: Item

### Tong hop
View classes: HomeFrm, ExportFrm
Methods: searchAgentByName(), searchItemByName(), getStock(), insertExportInvoice(), insertExportInvoiceDetails(), updateStock()

### Bang quan he (Relation Table)

| Entity | Thuoc tinh chinh | Khoa ngoai | Entity lien ket |
|--------|-----------------|------------|-----------------|
| User | userId | -- | -- |
| Supplier | supplierId | -- | -- |
| SubAgent | agentId | -- | -- |
| Item | itemId | -- | -- |
| ImportInvoice | invoiceId | supplierId -> Supplier(supplierId), userId -> User(userId) | Supplier, User |
| ImportInvoiceDetail | detailId | invoiceId -> ImportInvoice(invoiceId), itemId -> Item(itemId) | ImportInvoice, Item |
| ExportInvoice | invoiceId | agentId -> SubAgent(agentId), userId -> User(userId) | SubAgent, User |
| ExportInvoiceDetail | detailId | invoiceId -> ExportInvoice(invoiceId), itemId -> Item(itemId) | ExportInvoice, Item |

---

## Câu 3: Thiet ke tinh (Static Design)

### View Classes

| View Class | Mo ta | Form lien quan |
|------------|-------|----------------|
| ExportFrm | Giao dien chinh cua chuc nang xuat hang. Chua o tim kiem dai ly, danh sach dai ly, o tim kiem hang hoa, danh sach hang hoa, bang danh sach hang xuat, nut them/xoa/thanh toan. | MainForm (mo tu menu "Xuat hang") |

### UI Elements trong ExportFrm

| UI Element | Loai | Thuoc tinh | Mo ta |
|------------|------|------------|-------|
| txtSearchAgent | TextBox | Text | O nhap ten dai ly can tim |
| btnSearchAgent | Button | Text = "Tim dai ly" | Nut tim kiem dai ly |
| btnAddAgent | Button | Text = "Them moi dai ly" | Nut them dai ly moi |
| grdAgents | DataGridView | ReadOnly = true | Bang hien thi danh sach dai ly tim duoc |
| lblAgentName | Label | Text | Hien thi ten dai ly da chon |
| lblAgentAddress | Label | Text | Hien thi dia chi dai ly da chon |
| lblAgentPhone | Label | Text | Hien thi so dien thoai dai ly da chon |
| txtSearchItem | TextBox | Text | O nhap ten hang hoa can tim |
| btnSearchItem | Button | Text = "Tim hang hoa" | Nut tim kiem hang hoa |
| grdItems | DataGridView | ReadOnly = true | Bang hien thi danh sach hang hoa tim duoc |
| txtQuantity | TextBox | Text | O nhap so luong xuat |
| txtUnitPrice | TextBox | Text | O nhap don gia xuat |
| btnAddToList | Button | Text = "Them vao danh sach" | Nut them hang vao danh sach xuat |
| dgvExportList | DataGridView | ReadOnly = true (tru cot xoa) | Bang danh sach hang hoa se xuat |
| lblTotal | Label | Text | Hien thi tong tien hoa don |
| btnRemoveItem | Button | Text = "Xoa khoi danh sach" | Nut xoa hang khoi danh sach xuat |
| btnSubmit | Button | Text = "Xuat hoa don" | Nut xac nhan xuat hoa don |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| SubAgentDAO | tblSubAgent | searchByName(String name): List<SubAgent>, addAgent(SubAgent agent): boolean |
| ItemDAO | tblItem | searchByName(String name): List<Item>, updateStock(String itemId, int qtyChange): boolean, getStock(String itemId): int |
| ExportInvoiceDAO | tblExportInvoice | insert(ExportInvoice invoice): String, getById(String invoiceId): ExportInvoice |
| ExportInvoiceDetailDAO | tblExportInvoiceDetail | insertBatch(List<ExportInvoiceDetail> details): boolean |
| UserDAO | tblUser | getById(String userId): User |

### Entity Types

| Entity | Kieu du lieu | Thuoc tinh |
|--------|--------------|------------|
| Item | Entity Class | itemId: String, itemName: String, description: String |
| SubAgent | Entity Class | agentId: String, agentName: String, address: String, phone: String |
| ExportInvoice | Entity Class | invoiceId: String, exportDate: Date, totalAmount: float, agentId: String, userId: String |
| ExportInvoiceDetail | Entity Class | detailId: String, invoiceId: String, itemId: String, quantity: int, unitPrice: float, amount: float |
| User | Entity Class | userId: String, username: String, password: String, fullName: String, role: String |

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
2. Tao 8 class: User, Supplier, SubAgent, Item, ImportInvoice, ImportInvoiceDetail, ExportInvoice, ExportInvoiceDetail.
3. Them attributes cho moi class theo bang Entity Types.
4. Ve association (moi quan he):
   - User 1---N ImportInvoice (add navigability tu ImportInvoice -> User).
   - User 1---N ExportInvoice.
   - Supplier 1---N ImportInvoice.
   - SubAgent 1---N ExportInvoice.
   - ImportInvoice 1---N ImportInvoiceDetail.
   - ExportInvoice 1---N ExportInvoiceDetail.
   - Item 1---N ImportInvoiceDetail.
   - Item 1---N ExportInvoiceDetail.
5. Dat multiplicity dung theo bang Moi quan he.
6. Export diagram duoi dang PNG de dua vao bao cao.

---

## Câu 4: Sequence Diagram (Chuc nang xuat hang)

### Visual Paradigm (VP) Guide

1. Mo VP -> New -> Sequence Diagram.
2. Tao cac lifeline: Staff, ExportFrm, SubAgentDAO, ItemDAO, ExportInvoiceDAO, ExportInvoiceDetailDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho cac truong hop ngoai le (so luong vuot ton kho, danh sach trong).
5. Su dung loop fragment cho viec them nhieu mat hang vao danh sach.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Staff          ExportFrm        SubAgentDAO    ItemDAO       ExportInvoiceDAO  DetailDAO    Database
  |                |                |              |                |              |             |
  |--selectMenu---->|                |              |                |              |             |
  |                |--initForm()---->|              |                |              |             |
  |                |                |              |                |              |             |
  |--inputAgent--->|                |              |                |              |             |
  |    "Minh Khang"|                |              |                |              |             |
  |--clickSearch-->|                |              |                |              |             |
  |                |--searchByName->|              |                |              |             |
  |                |    "Minh Khang"|              |                |              |             |
  |                |                |--SELECT------|------------------------------------->|
  |                |                |<--ResultSet---|-------------------------------------|
  |                |<--List<Agent>--|              |                |              |             |
  |                |--displayAgents()              |                |              |             |
  |<--showGrdAgents|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--selectAgent-->|                |              |                |              |             |
  |    "AG005"     |--displayAgentInfo()           |                |              |             |
  |<--showInfo-----|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--inputItem---->|                |              |                |              |             |
  |  "Vinamilk"   |                |              |                |              |             |
  |--clickSearch-->|                |              |                |              |             |
  |                |--searchByName--------------->|                |              |             |
  |                |    "Vinamilk" |              |                |              |             |
  |                |                |              |--SELECT--------|----------------------->|
  |                |                |              |<--ResultSet-----|-----------------------|
  |                |<--List<Item>-----------------|                |              |             |
  |                |--displayItems()|              |                |              |             |
  |<--showGrdItems-|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--selectItem--->|                |              |                |              |             |
  |   "IT012"      |                |              |                |              |             |
  |--inputQty------|                |              |                |              |             |
  |   "50"         |                |              |                |              |             |
  |--inputPrice--->|                |              |                |              |             |
  |  "250000"      |                |              |                |              |             |
  |--clickAdd----->|                |              |                |              |             |
  |                |--getStock----------------->|                |              |             |
  |                |    "IT012"    |              |                |              |             |
  |                |                |              |--SELECT--------|----------------------->|
  |                |                |              |<--500-----------|-----------------------|
  |                |<--500---------|              |                |              |             |
  |                |                |              |                |              |             |
  |                |  [50 <= 500]  |              |                |              |             |
  |                |--addToExportList()           |                |              |             |
  |<--updateGrd----|                |              |                |              |             |
  |                |                |              |                |              |             |
  |  (loop: them IT023, IT034...) |              |                |              |             |
  |                |                |              |                |              |             |
  |--clickSubmit-->|                |              |                |              |             |
  |                |--confirmDialog()             |                |              |             |
  |<--showConfirm--|                |              |                |              |             |
  |--clickConfirm->|                |              |                |              |             |
  |                |                |              |                |              |             |
  |                |--beginTransaction()          |                |              |             |
  |                |                |              |                |              |             |
  |                |--generateInvoiceId()---------|------------------------------------->|
  |                |<--"PX2026001"-|----------------------------------------|-------------|
  |                |                |              |                |              |             |
  |                |-------------------------------------------->insert()      |             |
  |                |                |              |                |--INSERT-----|----------->|
  |                |                |              |                |<--OK--------|-----------|
  |                |<-------------------------------------------true            |             |
  |                |                |              |                |              |             |
  |                |------------------------------------------------------>insertBatch()     |
  |                |                |              |                |              |--INSERT-->|
  |                |                |              |                |              |    x3     |
  |                |                |              |                |              |<--OK------|
  |                |<---------------------------------------------------------true           |
  |                |                |              |                |              |             |
  |                |--updateStock----------------->|                |              |             |
  |                |  "IT012", -50  |              |                |              |             |
  |                |                |              |--UPDATE--------|----------------------->|
  |                |                |              |<--OK-----------|-----------------------|
  |                |<--true--------|              |                |              |             |
  |                |                |              |                |              |             |
  |                |  (loop: update stock IT023-30, IT034-100)    |              |             |
  |                |                |              |                |              |             |
  |                |--commitTransaction()         |                |              |             |
  |                |                |              |                |              |             |
  |                |--printInvoice()|              |                |              |             |
  |<--showSuccess--|                |              |                |              |             |
  |<--printReport--|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--clickOK------|                |              |                |              |             |
  |                |--resetForm()   |              |                |              |             |
  |<--formCleared--|                |              |                |              |             |
```

### Bang chi tiet Sequence Diagram

| Buoc | From | To | Message | Loai | Ghi chu |
|------|------|-----|---------|------|---------|
| 1 | Staff | ExportFrm | selectExportMenu() | Sync | Staff nhan menu "Xuat hang" |
| 2 | ExportFrm | ExportFrm | initForm() | Self | Khoi tao form, hien thi giao dien trong |
| 3 | Staff | ExportFrm | inputAgentName("Minh Khang") | Sync | Staff nhap ten dai ly vao txtSearchAgent |
| 4 | Staff | ExportFrm | clickSearchAgent() | Sync | Staff nhan nut "Tim dai ly" |
| 5 | ExportFrm | SubAgentDAO | searchByName("Minh Khang") | Sync | Goi DAO tim dai ly theo ten |
| 6 | SubAgentDAO | Database | SELECT * FROM tblSubAgent WHERE agentName LIKE '%Minh Khang%' | Sync | Truy van CSDL |
| 7 | Database | SubAgentDAO | ResultSet (AG005, Minh Khang, ...) | Return | Tra ve ket qua |
| 8 | SubAgentDAO | ExportFrm | return List<SubAgent> | Return | Tra ve danh sach dai ly |
| 9 | ExportFrm | ExportFrm | displayAgents(list) | Self | Hien thi danh sach dai ly len grdAgents |
| 10 | Staff | ExportFrm | selectAgent("AG005") | Sync | Staff chon dai ly "Minh Khang" |
| 11 | ExportFrm | ExportFrm | displayAgentInfo(agent) | Self | Hien thi thong tin dai ly da chon |
| 12 | Staff | ExportFrm | inputItemName("Vinamilk") | Sync | Staff nhap ten hang hoa vao txtSearchItem |
| 13 | Staff | ExportFrm | clickSearchItem() | Sync | Staff nhan nut "Tim hang hoa" |
| 14 | ExportFrm | ItemDAO | searchByName("Vinamilk") | Sync | Goi DAO tim hang hoa theo ten |
| 15 | ItemDAO | Database | SELECT * FROM tblItem WHERE itemName LIKE '%Vinamilk%' | Sync | Truy van CSDL |
| 16 | Database | ItemDAO | ResultSet (IT012, Sua Vinamilk, ..., 500) | Return | Tra ve ket qua |
| 17 | ItemDAO | ExportFrm | return List<Item> | Return | Tra ve danh sach hang hoa |
| 18 | ExportFrm | ExportFrm | displayItems(list) | Self | Hien thi danh sach hang hoa len grdItems |
| 19 | Staff | ExportFrm | selectItem("IT012") | Sync | Staff chon hang hoa "Sua Vinamilk" |
| 20 | Staff | ExportFrm | inputQuantity(50) | Sync | Staff nhap so luong = 50 |
| 21 | Staff | ExportFrm | inputUnitPrice(250000) | Sync | Staff nhap don gia = 250,000 |
| 22 | Staff | ExportFrm | clickAddToList() | Sync | Staff nhan nut "Them vao danh sach" |
| 23 | ExportFrm | ItemDAO | getStock("IT012") | Sync | Kiem tra ton kho hien tai |
| 24 | ItemDAO | Database | SELECT stock FROM tblItem WHERE itemId='IT012' | Sync | Truy van ton kho |
| 25 | Database | ItemDAO | 500 | Return | Ton kho = 500 |
| 26 | ItemDAO | ExportFrm | return 500 | Return | Tra ve ton kho |
| 27 | ExportFrm | ExportFrm | validateQuantity(50, 500) | Self | Kiem tra 50 <= 500 -> hop le |
| 28 | ExportFrm | ExportFrm | addToExportList(item, 50, 250000) | Self | Them dong vao bang dgvExportList |
| 29 | ExportFrm | ExportFrm | updateTotal(12500000) | Self | Cap nhat tong tien |
| 30 | Staff | ExportFrm | clickSubmit() | Sync | Staff nhan nut "Xuat hoa don" |
| 31 | ExportFrm | ExportFrm | showConfirmDialog() | Self | Hien thi hop thoai xac nhan |
| 32 | Staff | ExportFrm | clickConfirm() | Sync | Staff nhan "Xac nhan" |
| 33 | ExportFrm | Database | BEGIN TRANSACTION | Sync | Bat dau giao dich |
| 34 | ExportFrm | ExportInvoiceDAO | insert(invoice) | Sync | Tao phieu xuat moi |
| 35 | ExportInvoiceDAO | Database | INSERT INTO tblExportInvoice ... | Sync | Luu phieu xuat |
| 36 | ExportInvoiceDAO | ExportFrm | return true | Return | Thanh cong |
| 37 | ExportFrm | ExportInvoiceDetailDAO | insertBatch(details) | Sync | Luu danh sach chi tiet |
| 38 | ExportInvoiceDetailDAO | Database | INSERT INTO tblExportInvoiceDetail ... (x3) | Sync | Luu 3 chi tiet |
| 39 | ExportInvoiceDetailDAO | ExportFrm | return true | Return | Thanh cong |
| 40 | ExportFrm | ItemDAO | updateStock("IT012", -50) | Sync | Cap nhat ton kho IT012 |
| 41 | ItemDAO | Database | UPDATE tblItem SET stock=stock-50 WHERE itemId='IT012' | Sync | Tru ton kho |
| 42 | ItemDAO | ExportFrm | return true | Return | Thanh cong |
| 43 | ExportFrm | Database | COMMIT TRANSACTION | Sync | Cam ket giao dich |
| 44 | ExportFrm | ExportFrm | printInvoice() | Self | In phieu xuat |
| 45 | ExportFrm | Staff | showSuccess("Xuat hoa don thanh cong!") | Return | Thong bao thanh cong |
| 46 | Staff | ExportFrm | clickOK() | Sync | Staff nhan OK |
| 47 | ExportFrm | ExportFrm | resetForm() | Self | Reset giao dien ve trang thai ban dau |

---

## Câu 5: Blackbox Testing (Test Plan)

### Test Plan cho chuc nang Xuat hang

| Test Case | Mo ta | Input | Expected Output |
|-----------|-------|-------|-----------------|
| TC01 | Xuat hang thanh cong cho dai ly | Ten dai ly hop le, hang hoa hop le, SL <= ton kho | Hoa don duoc tao, ton kho giam, thong bao thanh cong |
| TC02 | Tim dai ly khong ton tai | Ten dai ly "XYZ123" khong co trong DB | Hien thi danh sach trong, cho phep them moi |
| TC03 | So luong xuat vuot ton kho | SL xuat = 600, ton kho = 500 | Hien thi loi "So luong vuot ton kho" |
| TC04 | So luong = 0 | SL = 0 | Hien thi loi "So luong phai > 0" |
| TC05 | Don gia am | Don gia = -100 | Hien thi loi "Don gia phai > 0" |
| TC06 | Danh sach xuat trong khi nhan Submit | Khong them hang nao | Hien thi loi "Danh sach trong" |
| TC07 | Tim hang hoa khong ton tai | Ten hang "XYZ999" | Hien thi danh sach trong |
| TC08 | Them trung hang hoa da co trong danh sach | Them lai IT012 khi da co | Hien thi loi hoac cong don SL |

### TC01: Xuat hang thanh cong cho dai ly

**Mo ta:** Kiem tra quy trinh xuat hang hoa thanh cong tu kho cho mot dai ly voi day du thong tin hop le.

**Preconditions:**
- Staff da dang nhap (userId = "NV003").
- Dai ly "Dai ly Minh Khang" da ton tai trong he thong.

**Database Before (Du lieu truoc khi chay test):**

Bang `tblUser`:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |
| NV001 | admin | admin123 | Tran Van A | Manager |

Bang `tblSubAgent`:
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |

Bang `tblItem`:
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |
| IT045 | Mi goi Hao Hao | Thung 30 goi |

Bang `tblExportInvoice`:
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| (rong -- chua co phieu xuat nao) | | | | |

Bang `tblExportInvoiceDetail`:
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| (rong) | | | | | |

Bang `tblStock` (ton kho):
| itemId | stockQuantity |
|--------|---------------|
| IT012 | 500 |
| IT023 | 200 |
| IT034 | 300 |
| IT045 | 150 |

**Test Steps (Kich ban >= 7 buoc):**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|------------------|
| 1 | Staff nhan menu "Xuat hang" | -- | Giao dien ExportFrm hien thi voi o tim kiem dai ly, bang dai ly trong, o tim kiem hang hoa disabled |
| 2 | Staff nhap "Minh Khang" vao txtSearchAgent, nhan btnSearchAgent | txtSearchAgent = "Minh Khang" | Bang grdAgents hien thi 1 dong: AG005, Dai ly Minh Khang, 123 Le Loi, Q.1, TP.HCM, 0901234567 |
| 3 | Staff nhan chon dong dai ly AG005 trong grdAgents | Chon AG005 | Thong tin dai ly hien thi: Ten = "Dai ly Minh Khang", DC = "123 Le Loi, Q.1, TP.HCM", SDT = "0901234567". O tim kiem hang hoa duoc kich hoat. |
| 4 | Staff nhap "Vinamilk" vao txtSearchItem, nhan btnSearchItem | txtSearchItem = "Vinamilk" | Bang grdItems hien thi 1 dong: IT012, Sua Vinamilk, Sua tuoi 1 lit, Ton kho: 500 |
| 5 | Staff chon IT012, nhap SL=50, Don gia=250000, nhan btnAddToList | Chon IT012; txtQuantity="50"; txtUnitPrice="250000" | dgvExportList co 1 dong: IT012, Sua Vinamilk, 50, 250,000, 12,500,000. lblTotal = "12,500,000 VND". txtSearchItem, txtQuantity, txtUnitPrice duoc xoa trang. |
| 6 | Staff tim "Oreo", chon IT023, nhap SL=30, Don gia=180000, nhan btnAddToList | txtSearchItem="Oreo"; Chon IT023; txtQuantity="30"; txtUnitPrice="180000" | dgvExportList co 2 dong. Dong 2: IT023, Banh Oreo, 30, 180,000, 5,400,000. lblTotal = "17,900,000 VND". |
| 7 | Staff tim "Coca", chon IT034, nhap SL=100, Don gia=120000, nhan btnAddToList | txtSearchItem="Coca"; Chon IT034; txtQuantity="100"; txtUnitPrice="120000" | dgvExportList co 3 dong. Dong 3: IT034, Nuoc ngot Coca, 100, 120,000, 12,000,000. lblTotal = "29,900,000 VND". |
| 8 | Staff nhan btnSubmit | -- | Hop thoai xac nhan: "Xuat hoa don cho Dai ly Minh Khang voi tong tien 29,900,000 VND?" |
| 9 | Staff nhan "Xac nhan" | -- | Thong bao "Xuat hoa don thanh cong!". Phieu xuat hoa don duoc in ra. |
| 10 | Staff nhan OK | -- | Form reset ve trang thai ban dau. Bang dgvExportList trong. lblTotal = "0 VND". |

**Database After (Du lieu sau khi chay test):**

Bang `tblUser` (khong thay doi):
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |
| NV001 | admin | admin123 | Tran Van A | Manager |

Bang `tblSubAgent` (khong thay doi):
| agentId | agentName | address | phone |
|---------|-----------|---------|-------|
| AG005 | Dai ly Minh Khang | 123 Le Loi, Q.1, TP.HCM | 0901234567 |
| AG006 | Dai ly Thanh Binh | 456 Nguyen Trai, Q.5, TP.HCM | 0912345678 |

Bang `tblItem` (khong thay doi):
| itemId | itemName | description |
|--------|----------|-------------|
| IT012 | Sua Vinamilk | Sua tuoi 1 lit |
| IT023 | Banh Oreo | Banh quy 300g |
| IT034 | Nuoc ngot Coca | Chai 1.5 lit |
| IT045 | Mi goi Hao Hao | Thung 30 goi |

Bang `tblExportInvoice` (them moi 1 dong):
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| PX2026001 | 2026-06-08 | 29,900,000 | AG005 | NV003 |

Bang `tblExportInvoiceDetail` (them moi 3 dong):
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ED001 | PX2026001 | IT012 | 50 | 250,000 | 12,500,000 |
| ED002 | PX2026001 | IT023 | 30 | 180,000 | 5,400,000 |
| ED003 | PX2026001 | IT034 | 100 | 120,000 | 12,000,000 |

Bang `tblStock` (cap nhat ton kho):
| itemId | stockQuantity |
|--------|---------------|
| IT012 | 450 (was 500, minus 50) |
| IT023 | 170 (was 200, minus 30) |
| IT034 | 200 (was 300, minus 100) |
| IT045 | 150 (unchanged) |

**Pass Criteria:**
- tblExportInvoice co dung 1 ban ghi moi voi ma PX2026001.
- tblExportInvoiceDetail co dung 3 ban ghi moi lien ket voi PX2026001.
- Tong tien trong tblExportInvoice = 29,900,000 = tong cac amount trong detail.
- Ton kho IT012 giam tu 500 xuong 450.
- Ton kho IT023 giam tu 200 xuong 170.
- Ton kho IT034 giam tu 300 xuong 200.
- Ton kho IT045 khong doi (150).
- Hien thi thong bao "Xuat hoa don thanh cong!".
- Form reset ve trang thai ban dau.
