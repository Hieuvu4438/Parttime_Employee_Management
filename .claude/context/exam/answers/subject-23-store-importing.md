# Subject 23 -- Store Management -- Module "Importing"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Staff (Nhan vien kho):** Nguoi thuc hien thao tac nhap hang tu nha cung cap.
- **Supplier (Nha cung cap):** Nha cung cap hang hoa cho kho.
- **System (He thong):** He thong quan ly kho, xu ly tim kiem, luu tru va in phieu nhap.

### Preconditions
- Staff da dang nhap thanh cong vao he thong.
- He thong co du lieu nha cung cap (bang tblSupplier co san).
- He thong co du lieu hang hoa (bang tblItem co san).
- Staff co quyen thuc hien chuc nang nhap hang.

### Main Scenario: Nhap hang tu nha cung cap

| Buoc | Actor | Hanh dong | He thong phan hoi |
|------|-------|-----------|-------------------|
| 1 | Staff | Nhan chon menu "Nhap hang" (Import) tren giao dien chinh. | Hien thi trang Nhap hang voi o tim kiem nha cung cap (txtSearchSupplier), danh sach nha cung cap, nut "Them moi nha cung cap" (btnAddSupplier), va vung danh sach hang nhap (tblImportList). |
| 2 | Staff | Nhap ten nha cung cap "Cong ty ABC" vao o txtSearchSupplier, nhan nut "Tim kiem" (btnSearchSupplier). | He thong truy van bang tblSupplier voi dieu kien supplierName LIKE '%ABC%'. Hien thi danh sach nha cung cap phu hop trong grdSuppliers gom cot: Ma NCC, Ten NCC, Dia chi, So dien thoai. |
| 3 | Staff | Nhan chon dong "Cong ty ABC" (Ma: SP001) trong grdSuppliers. | He thong xac nhan nha cung cap da chon, hien thi thong tin: lblSupplierName = "Cong ty ABC", lblSupplierAddress = "789 Vo Van Tan, Q.3, TP.HCM", lblSupplierPhone = "0908765432". Kich hoat vung tim kiem hang hoa. |
| 4 | Staff | Nhap ten hang hoa "Sua TH True Milk" vao o txtSearchItem, nhan nut "Tim kiem" (btnSearchItem). | He thong truy van bang tblItem voi dieu kien itemName LIKE '%TH True Milk%'. Hien thi danh sach hang hoa phu hop trong grdItems gom cot: Ma hang, Ten hang, Mo ta, Ton kho. |
| 5 | Staff | Nhan chon dong "Sua TH True Milk" (Ma: IT015, Ton kho: 300 thung) trong grdItems. | He thong hien thi thong tin hang hoa da chon. Kich hoat o nhap so luong (txtQuantity) va don gia (txtUnitPrice). |
| 6 | Staff | Nhap so luong = "100" vao txtQuantity, nhap don gia = "200,000" vao txtUnitPrice, nhan nut "Them vao danh sach" (btnAddToList). | He thong kiem tra: so luong > 0, don gia > 0 -> hop le. Tao dong moi trong tblImportList: Ma hang IT015, Ten "Sua TH True Milk", SL: 100, Don gia: 200,000, Thanh tien: 20,000,000. Cap nhat tong tien (lblTotal). Xoa noi dung o tim kiem hang hoa. |
| 7 | Staff | Lap lai buoc 4-6 cho hang hoa "Banh Chocopie" (Ma: IT028, SL: 200, Don gia: 150,000). | He thong them dong vao tblImportList: IT028, Banh Chocopie, SL: 200, Don gia: 150,000, Thanh tien: 30,000,000. Cap nhat tong tien = 50,000,000. |
| 8 | Staff | Lap lai buoc 4-6 cho hang hoa "Nuoc suoi Lavie" (Ma: IT039, SL: 500, Don gia: 80,000). | He thong them dong vao tblImportList: IT039, Nuoc suoi Lavie, SL: 500, Don gia: 80,000, Thanh tien: 40,000,000. Cap nhat tong tien = 90,000,000. |
| 9 | Staff | Kiem tra lai toan bo danh sach hang nhap trong tblImportList, xac nhan dung. Nhan nut "Nhap hang" (btnSubmit). | He thong hien thi hop thoai xac nhan: "Nhap hang tu Cong ty ABC voi tong tien 90,000,000 VND?" |
| 10 | Staff | Nhan "Xac nhan" trong hop thoai. | He thong thuc hien: (1) Tao ban ghi moi trong tblImportInvoice: Ma phieu nhap PN2026001, Ngay nhap: 08/06/2026, Ma NCC: SP001, Tong tien: 90,000,000, Ma nhan vien: NV003. (2) Tao 3 ban ghi trong tblImportInvoiceDetail cho tung mat hang. (3) Cap nhat ton kho: IT015: 300+100=400, IT028: 100+200=300, IT039: 200+500=700. (4) Hien thi thong bao "Nhap hang thanh cong!" va in phieu nhap. |
| 11 | Staff | Nhan "OK" tren thong bao thanh cong. | He thong hien thi phieu nhap hoa don (report) voi day du thong tin: header (ma phieu, ngay, nhan vien), thong tin nha cung cap, bang chi tiet hang hoa, tong tien. Quay ve trang nhap hang trong. |

### Alternative Scenarios

**3a. Nha cung cap chua ton tai:**
- Buoc 3 (thay the): Staff khong tim thay nha cung cap trong danh sach ket qua. Staff nhan nut "Them moi nha cung cap". He thong hien thi form them nha cung cap moi (txtNewSupplierName, txtNewSupplierAddress, txtNewSupplierPhone). Staff nhap thong tin va nhan "Luu". He thong them vao tblSupplier va tu dong chon nha cung cap moi tao. Tiep tuc tu buoc 4.

**4a. Hang hoa chua ton tai:**
- Buoc 4 (thay the): Staff khong tim thay hang hoa trong danh sach ket qua. Staff nhan nut "Them moi hang hoa" (btnAddItem). He thong hien thi form them hang hoa moi (txtNewItemName, txtNewItemDesc). Staff nhap thong tin va nhan "Luu". He thong them vao tblItem voi ton kho = 0, tu dong chon hang hoa moi. Tiep tuc tu buoc 5.

**6a. So luong hoac don gia khong hop le:**
- Buoc 6 (ngoai le): Staff nhap so luong = 0 hoac don gia = -100. He thong hien thi loi: "So luong va don gia phai la so duong." Khong them vao danh sach.

**6b. Hang hoa da ton tai trong danh sach:**
- Buoc 6 (ngoai le): Staff them lai hang IT015 da co trong danh sach. He thong hien thi loi: "Hang hoa IT015 da ton tai trong danh sach. Vui long xoa dong truoc khi them lai."

**9a. Danh sach hang nhap trong:**
- Buoc 9 (ngoai le): Staff nhan "Nhap hang" khi chua them hang hoa nao. He thong hien thi loi: "Danh sach hang nhap trong. Vui long them it nhat mot mat hang."

**10a. Loi he thong khi luu:**
- Buoc 10 (ngoai le): He thong gap loi co so du lieu khi luu. He thong hien thi loi: "Khong the luu phieu nhap. Vui long thu lai sau." Khong thay doi du lieu (rollback transaction).

### Postconditions
- Phieu nhap moi duoc luu trong tblImportInvoice va tblImportInvoiceDetail.
- Ton kho cac mat hang da nhap duoc tang dung so luong.
- Phieu nhap hoa don duoc in ra cho Staff va nha cung cap.
- Giao dien quay ve trang thai ban dau.

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management - Module Nhap hang bao gom cac thuc the sau:

1. **Item (Hang hoa):** Moi hang hoa co ma hang (itemId), ten hang (itemName), mo ta (description). Mot hang hoa co the duoc nhap nhieu lan tu nhieu nha cung cap khac nhau va xuat nhieu lan cho nhieu dai ly. Hang hoa ton kho duoc quan ly qua so luong hien tai.

2. **Supplier (Nha cung cap):** Moi nha cung cap co ma (supplierId), ten (supplierName), dia chi (address), so dien thoai (phone). Mot nha cung cap co the cung cap nhieu hang hoa qua nhieu phieu nhap. Khi nha cung cap moi chua co trong he thong, Staff co the them truc tiep.

3. **ImportInvoice (Phieu nhap):** Moi phieu nhap co ma phieu (invoiceId), ngay nhap (importDate), tong tien (totalAmount), ma nha cung cap (supplierId), ma nhan vien nhap (userId). Mot phieu nhap thuoc ve mot nha cung cap va do mot nhan vien thuc hien. Moi phieu nhap co the chua nhieu mat hang khac nhau.

4. **ImportInvoiceDetail (Chi tiet phieu nhap):** Moi chi tiet co ma chi tiet (detailId), ma phieu nhap (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount = quantity * unitPrice). Moi chi tiet thuoc ve mot phieu nhap va mot hang hoa.

5. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Mot nguoi dung co the tao nhieu phieu nhap.

### Trich xuat danh tu tu mo ta module

| STT | Danh tu trong mo ta | Entity tuong ung | Thuoc tinh |
|-----|---------------------|------------------|------------|
| 1 | Hang hoa, mat hang, san pham, vat pham | Item | itemId, itemName, description |
| 2 | Nha cung cap, nha phan phoi, nha san xuat, provider | Supplier | supplierId, supplierName, address, phone |
| 3 | Phieu nhap, hoa don nhap, import bill, import invoice | ImportInvoice | invoiceId, importDate, totalAmount, supplierId, userId |
| 4 | Chi tiet phieu nhap, danh sach hang nhap | ImportInvoiceDetail | detailId, invoiceId, itemId, quantity, unitPrice, amount |
| 5 | Nhan vien, nguoi dung, tai khoan, staff | User | userId, username, password, fullName, role |

### Moi quan he giua cac Entity

| Moi quan he | Kieu | Mo ta |
|-------------|------|-------|
| Item -- ImportInvoiceDetail | 1 : N | Mot hang hoa co the co trong nhieu chi tiet phieu nhap khac nhau |
| Supplier -- ImportInvoice | 1 : N | Mot nha cung cap co the co nhieu phieu nhap |
| ImportInvoice -- ImportInvoiceDetail | 1 : N | Mot phieu nhap co nhieu chi tiet (nhieu mat hang) |
| User -- ImportInvoice | 1 : N | Mot nhan vien co the tao nhieu phieu nhap |

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
                                       |
                                       | 1
                                       |
                                       N
                             +--------------------------+          +------------------+
                             |  ImportInvoiceDetail     |          |      Item        |
                             +--------------------------+          +------------------+
                             | - detailId: String       | N      1 | - itemId: String |
                             | - invoiceId: String      |----------| - itemName       |
                             | - itemId: String         |          | - description    |
                             | - quantity: int          |          +------------------+
                             | - unitPrice: float       |
                             | - amount: float          |
                             +--------------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes (hình chữ nhật 3 ngăn) cho: User, Item, Supplier, ImportInvoice, ImportInvoiceDetail |
| 3 | Tạo các view class boxes từ giao diện: HomeFrm, ImportFrm |
| 4 | Vẽ các đường kết nối (relationships) giữa các class theo bảng quan hệ bên dưới |
| 5 | Ghi multiplicity (1, N) và role name trên từng đầu đường kết nối |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được thể hiện bằng hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ ImportInvoice |
|------|----------|---------------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>` hoặc `<<boundary>>` + tên class | `<<entity>> ImportInvoice` |
| Ngăn 2 — Thuộc tính | Định dạng `-tenThuocTinh: KieuDuLieu` | `-invoiceId: String` `-importDate: Date` `-totalAmount: float` |
| Ngăn 3 — Phương thức | Định dạng `+tenPhuongThuc(thamSo): KieuTraVe` | `+insertImportInvoice(inv: ImportInvoice): String` |

Cách thao tác: Kéo biểu tượng Class từ panel bên trái vào canvas → double-click để chỉnh sửa tên → click phải chọn "Add Attribute" hoặc "Add Operation".

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| User | `<<entity>>` | `-userId: String`, `-username: String`, `-password: String`, `-fullName: String`, `-role: String` | — |
| Item | `<<entity>>` | `-itemId: String`, `-itemName: String`, `-description: String`, `-stockQuantity: int` | `+searchItemByName(name: String): List<Item>`, `+addItem(item: Item): String`, `+updateStock(itemId: String, qty: int): boolean` |
| Supplier | `<<entity>>` | `-supplierId: String`, `-supplierName: String`, `-address: String`, `-phone: String` | `+searchSupplierByName(name: String): List<Supplier>`, `+addSupplier(s: Supplier): boolean` |
| ImportInvoice | `<<entity>>` | `-invoiceId: String`, `-importDate: Date`, `-totalAmount: float`, `-supplierId: String`, `-userId: String` | `+insertImportInvoice(inv: ImportInvoice): String` |
| ImportInvoiceDetail | `<<entity>>` | `-detailId: String`, `-invoiceId: String`, `-itemId: String`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | `+insertImportInvoiceDetails(details: List): boolean` |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subImport` (Button — chọn chức năng nhập hàng) |
| ImportFrm | `<<boundary>>` | `inSearchSupplier` (TextField — nhập tên NCC), `subSearchSupplier` (Button — tìm NCC), `subAddSupplier` (Button — thêm NCC mới), `outsubListSupplier` (Table — danh sách NCC, click chọn), `outSupplierName` (Label — tên NCC đã chọn), `outSupplierAddress` (Label — địa chỉ), `outSupplierPhone` (Label — SĐT), `inSearchItem` (TextField — nhập tên hàng hóa), `subSearchItem` (Button — tìm hàng), `subAddItem` (Button — thêm hàng mới), `outsubListItem` (Table — danh sách hàng hóa, click chọn), `inQuantity` (TextField — nhập số lượng), `inUnitPrice` (TextField — nhập đơn giá), `subAddToList` (Button — thêm vào danh sách), `outListImport` (Table — danh sách hàng nhập), `outTotal` (Label — tổng tiền), `subSubmit` (Button — nhập hàng) |

Quy tắc prefix: `in` = nhập liệu, `out` = hiển thị, `sub` = gửi/nhấn, `outsub` = hiển thị + click chọn.

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu thông thường |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng ◇ | "Contain" nhưng child tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | "Sử dụng" tạm thời (view gọi DAO) |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 0..* hoặc 1..* | `N` hoặc `*` | Nhiều đối tượng |

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| User | ImportInvoice | Association | 1 --- N | Một nhân viên tạo nhiều phiếu nhập |
| Supplier | ImportInvoice | Association | 1 --- N | Một nhà cung cấp có nhiều phiếu nhập |
| ImportInvoice | ImportInvoiceDetail | Composition | 1 --- N | Chi tiết phiếu nhập không tồn tại nếu không có phiếu nhập cha |
| Item | ImportInvoiceDetail | Association | 1 --- N | Một hàng hóa xuất hiện trong nhiều chi tiết phiếu nhập |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ ImportInvoice (1) --- (N) ImportInvoiceDetail (Composition)**

1. Tạo class `ImportInvoice` với stereotype `<<entity>>`, thêm attribute: `-invoiceId: String`, `-importDate: Date`, `-totalAmount: float`.
2. Tạo class `ImportInvoiceDetail` với stereotype `<<entity>>`, thêm attribute: `-detailId: String`, `-quantity: int`, `-unitPrice: float`, `-amount: float`.
3. Kéo tool **Composition** từ panel → click vào `ImportInvoice` → kéo sang `ImportInvoiceDetail`.
4. Đặt multiplicity: đầu ImportInvoice = `1`, đầu ImportInvoiceDetail = `N`.
5. Thêm role name ở đầu ImportInvoiceDetail: `details`.

**Ví dụ 2: Vẽ quan hệ Supplier (1) --- (N) ImportInvoice (Association)**

1. Tạo class `Supplier` với attribute: `-supplierId: String`, `-supplierName: String`, `-address: String`, `-phone: String`.
2. Kéo tool **Association** từ panel → click vào `Supplier` → kéo sang `ImportInvoice`.
3. Đặt multiplicity: đầu Supplier = `1`, đầu ImportInvoice = `N`.

---

### Classes diagram (analysis)

Phan tich module nay (bo qua buoc dang nhap):

Sau khi dang nhap thanh cong -> HomeFrm:
  mot lua chon de nhap hang -> subImport

Chon nhap hang -> ImportFrm hien thi:
  o tim kiem nha cung cap -> inSearchSupplier
  nut tim kiem nha cung cap -> subSearchSupplier
  nut them moi nha cung cap -> subAddSupplier
  bang danh sach nha cung cap tim duoc -> outsubListSupplier
  ten nha cung cap da chon -> outSupplierName
  dia chi nha cung cap da chon -> outSupplierAddress
  so dien thoai nha cung cap da chon -> outSupplierPhone
  o tim kiem hang hoa -> inSearchItem
  nut tim kiem hang hoa -> subSearchItem
  nut them moi hang hoa -> subAddItem
  bang danh sach hang hoa tim duoc -> outsubListItem
  o nhap so luong -> inQuantity
  o nhap don gia -> inUnitPrice
  nut them vao danh sach -> subAddToList
  bang danh sach hang nhap -> outListImport
  tong tien -> outTotal
  nut nhap hang -> subSubmit

Staff nhap ten nha cung cap va nhan tim kiem -> he thong truy van nha cung cap -> can mot phuong thuc:
  ten: searchSupplierByName()
  dau vao: supplierName (String)
  dau ra: List<Supplier>
  gan cho entity class: Supplier

Staff nhap ten hang hoa va nhan tim kiem -> he thong truy van hang hoa -> can mot phuong thuc:
  ten: searchItemByName()
  dau vao: itemName (String)
  dau ra: List<Item>
  gan cho entity class: Item

Staff nhan nhap hang -> he thong tao phieu nhap va chi tiet -> can cac phuong thuc:
  ten: insertImportInvoice()
  dau vao: ImportInvoice (importDate, totalAmount, supplierId, userId)
  dau ra: String (invoiceId moi)
  gan cho entity class: ImportInvoice

  ten: insertImportInvoiceDetails()
  dau vao: List<ImportInvoiceDetail> (invoiceId, itemId, quantity, unitPrice, amount)
  dau ra: boolean
  gan cho entity class: ImportInvoiceDetail

  ten: updateStock()
  dau vao: itemId (String), qtyChange (int)
  dau ra: boolean
  gan cho entity class: Item

### Tong hop
View classes: HomeFrm, ImportFrm
Methods: searchSupplierByName(), searchItemByName(), insertImportInvoice(), insertImportInvoiceDetails(), updateStock()

### Bang quan he (Relation Table)

| Entity | Thuoc tinh chinh | Khoa ngoai | Entity lien ket |
|--------|-----------------|------------|-----------------|
| User | userId | -- | -- |
| Supplier | supplierId | -- | -- |
| Item | itemId | -- | -- |
| ImportInvoice | invoiceId | supplierId -> Supplier(supplierId), userId -> User(userId) | Supplier, User |
| ImportInvoiceDetail | detailId | invoiceId -> ImportInvoice(invoiceId), itemId -> Item(itemId) | ImportInvoice, Item |

---

## Câu 3: Thiet ke tinh (Static Design)

### View Classes

| View Class | Mo ta | Form lien quan |
|------------|-------|----------------|
| ImportFrm | Giao dien chinh cua chuc nang nhap hang. Chua o tim kiem nha cung cap, danh sach NCC, o tim kiem hang hoa, danh sach hang hoa, bang danh sach hang nhap, nut them/xoa/thanh toan. | MainForm (mo tu menu "Nhap hang") |

### UI Elements trong ImportFrm

| UI Element | Loai | Thuoc tinh | Mo ta |
|------------|------|------------|-------|
| txtSearchSupplier | TextBox | Text | O nhap ten nha cung cap can tim |
| btnSearchSupplier | Button | Text = "Tim nha cung cap" | Nut tim kiem nha cung cap |
| btnAddSupplier | Button | Text = "Them moi NCC" | Nut them nha cung cap moi |
| grdSuppliers | DataGridView | ReadOnly = true | Bang hien thi danh sach nha cung cap tim duoc |
| lblSupplierName | Label | Text | Hien thi ten nha cung cap da chon |
| lblSupplierAddress | Label | Text | Hien thi dia chi nha cung cap da chon |
| lblSupplierPhone | Label | Text | Hien thi so dien thoai nha cung cap da chon |
| txtSearchItem | TextBox | Text | O nhap ten hang hoa can tim |
| btnSearchItem | Button | Text = "Tim hang hoa" | Nut tim kiem hang hoa |
| btnAddItem | Button | Text = "Them moi hang hoa" | Nut them hang hoa moi |
| grdItems | DataGridView | ReadOnly = true | Bang hien thi danh sach hang hoa tim duoc |
| txtQuantity | TextBox | Text | O nhap so luong nhap |
| txtUnitPrice | TextBox | Text | O nhap don gia nhap |
| btnAddToList | Button | Text = "Them vao danh sach" | Nut them hang vao danh sach nhap |
| dgvImportList | DataGridView | ReadOnly = true (tru cot xoa) | Bang danh sach hang hoa se nhap |
| lblTotal | Label | Text | Hien thi tong tien phieu nhap |
| btnRemoveItem | Button | Text = "Xoa khoi danh sach" | Nut xoa hang khoi danh sach nhap |
| btnSubmit | Button | Text = "Nhap hang" | Nut xac nhan nhap hang |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| SupplierDAO | tblSupplier | searchByName(String name): List<Supplier>, addSupplier(Supplier s): boolean |
| ItemDAO | tblItem | searchByName(String name): List<Item>, addItem(Item i): String, updateStock(String itemId, int qtyChange): boolean, getStock(String itemId): int |
| ImportInvoiceDAO | tblImportInvoice | insert(ImportInvoice invoice): String, getById(String invoiceId): ImportInvoice |
| ImportInvoiceDetailDAO | tblImportInvoiceDetail | insertBatch(List<ImportInvoiceDetail> details): boolean |
| UserDAO | tblUser | getById(String userId): User |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Item | Entity | id: int (PK), itemId: String, itemName: String, description: String |
| Supplier | Entity | id: int (PK), supplierId: String, supplierName: String, address: String, phone: String |
| ImportInvoice | Entity | id: int (PK), invoiceId: String, importDate: Date, totalAmount: float, supplier: Supplier (object), user: User (object) |
| ImportInvoiceDetail | Entity | id: int (PK), importInvoice: ImportInvoice (object), item: Item (object), quantity: int, unitPrice: float, amount: float |
| User | Entity | id: int (PK), userId: String, username: String, password: String, fullName: String, role: String |

### Entity Types

| Entity | Kieu du lieu | Thuoc tinh |
|--------|--------------|------------|
| Item | Entity Class | itemId: String, itemName: String, description: String |
| Supplier | Entity Class | supplierId: String, supplierName: String, address: String, phone: String |
| ImportInvoice | Entity Class | invoiceId: String, importDate: Date, totalAmount: float, supplierId: String, userId: String |
| ImportInvoiceDetail | Entity Class | detailId: String, invoiceId: String, itemId: String, quantity: int, unitPrice: float, amount: float |
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
    description NVARCHAR(255),
    stockQuantity INT DEFAULT 0
);

CREATE TABLE tblSupplier (
    supplierId  VARCHAR(10) PRIMARY KEY,
    supplierName NVARCHAR(100) NOT NULL,
    address     NVARCHAR(200),
    phone       VARCHAR(15)
);

CREATE TABLE tblImportInvoice (
    invoiceId   VARCHAR(10) PRIMARY KEY,
    importDate  DATE NOT NULL,
    totalAmount FLOAT NOT NULL,
    supplierId  VARCHAR(10) NOT NULL,
    userId      VARCHAR(10) NOT NULL,
    FOREIGN KEY (supplierId) REFERENCES tblSupplier(supplierId),
    FOREIGN KEY (userId) REFERENCES tblUser(userId)
);

CREATE TABLE tblImportInvoiceDetail (
    detailId    VARCHAR(10) PRIMARY KEY,
    invoiceId   VARCHAR(10) NOT NULL,
    itemId      VARCHAR(10) NOT NULL,
    quantity    INT NOT NULL,
    unitPrice   FLOAT NOT NULL,
    amount      FLOAT NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES tblImportInvoice(invoiceId),
    FOREIGN KEY (itemId) REFERENCES tblItem(itemId)
);
```

### Visual Paradigm (VP) Guide

**Tao Class Diagram trong VP:**
1. Mo VP -> New -> Class Diagram.
2. Tao 5 class: User, Supplier, Item, ImportInvoice, ImportInvoiceDetail.
3. Them attributes cho moi class theo bang Entity Types.
4. Ve association:
   - User 1---N ImportInvoice.
   - Supplier 1---N ImportInvoice.
   - ImportInvoice 1---N ImportInvoiceDetail.
   - Item 1---N ImportInvoiceDetail.
5. Dat multiplicity dung theo bang Moi quan he.
6. Export diagram duoi dang PNG.

---

## Câu 4: Sequence Diagram (Chuc nang nhap hang)

### Visual Paradigm (VP) Guide

1. Mo VP -> New -> Sequence Diagram.
2. Tao cac lifeline: Staff, ImportFrm, SupplierDAO, ItemDAO, ImportInvoiceDAO, ImportInvoiceDetailDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho cac truong hop ngoai le (NCC/hang chua ton tai, du lieu khong hop le).
5. Su dung loop fragment cho viec them nhieu mat hang vao danh sach.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Staff          ImportFrm        SupplierDAO    ItemDAO       ImportInvoiceDAO  DetailDAO    Database
  |                |                |              |                |              |             |
  |--selectMenu---->|                |              |                |              |             |
  |                |--initForm()---->|              |                |              |             |
  |                |                |              |                |              |             |
  |--inputSupplier>|                |              |                |              |             |
  |    "ABC"       |                |              |                |              |             |
  |--clickSearch-->|                |              |                |              |             |
  |                |--searchByName->|              |                |              |             |
  |                |    "ABC"       |              |                |              |             |
  |                |                |--SELECT------|------------------------------------->|
  |                |                |<--ResultSet---|-------------------------------------|
  |                |<--List<Supp>--|              |                |              |             |
  |                |--displaySuppliers()          |                |              |             |
  |<--showGrdSupp--|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--selectSupp--->|                |              |                |              |             |
  |    "SP001"     |--displaySupplierInfo()       |                |              |             |
  |<--showInfo-----|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--inputItem---->|                |              |                |              |             |
  |  "TH True Milk"|                |              |                |              |             |
  |--clickSearch-->|                |              |                |              |             |
  |                |--searchByName--------------->|                |              |             |
  |                |  "TH True Milk"|              |                |              |             |
  |                |                |              |--SELECT--------|----------------------->|
  |                |                |              |<--ResultSet-----|-----------------------|
  |                |<--List<Item>-----------------|                |              |             |
  |                |--displayItems()|              |                |              |             |
  |<--showGrdItems-|                |              |                |              |             |
  |                |                |              |                |              |             |
  |--selectItem--->|                |              |                |              |             |
  |   "IT015"      |                |              |                |              |             |
  |--inputQty------|                |              |                |              |             |
  |   "100"        |                |              |                |              |             |
  |--inputPrice--->|                |              |                |              |             |
  |  "200000"      |                |              |                |              |             |
  |--clickAdd----->|                |              |                |              |             |
  |                |--validate(100, 200000)        |                |              |             |
  |                |  [> 0 -> OK]   |              |                |              |             |
  |                |  [not in list]  |              |                |              |             |
  |                |--addToImportList()           |                |              |             |
  |<--updateGrd----|                |              |                |              |             |
  |                |                |              |                |              |             |
  |  (loop: them IT028, IT039...) |              |                |              |             |
  |                |                |              |                |              |             |
  |--clickSubmit-->|                |              |                |              |             |
  |                |--confirmDialog()             |                |              |             |
  |<--showConfirm--|                |              |                |              |             |
  |--clickConfirm->|                |              |                |              |             |
  |                |                |              |                |              |             |
  |                |--beginTransaction()          |                |              |             |
  |                |                |              |                |              |             |
  |                |--generateInvoiceId()---------|------------------------------------->|
  |                |<--"PN2026001"-|----------------------------------------|-------------|
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
  |                |  "IT015", +100 |              |                |              |             |
  |                |                |              |--UPDATE--------|----------------------->|
  |                |                |              |<--OK-----------|-----------------------|
  |                |<--true--------|              |                |              |             |
  |                |                |              |                |              |             |
  |                |  (loop: update stock IT028+200, IT039+500)   |              |             |
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
| 1 | Staff | ImportFrm | selectImportMenu() | Sync | Staff nhan menu "Nhap hang" |
| 2 | ImportFrm | ImportFrm | initForm() | Self | Khoi tao form, hien thi giao dien trong |
| 3 | Staff | ImportFrm | inputSupplierName("ABC") | Sync | Staff nhap ten nha cung cap vao txtSearchSupplier |
| 4 | Staff | ImportFrm | clickSearchSupplier() | Sync | Staff nhan nut "Tim nha cung cap" |
| 5 | ImportFrm | SupplierDAO | searchByName("ABC") | Sync | Goi DAO tim nha cung cap theo ten |
| 6 | SupplierDAO | Database | SELECT * FROM tblSupplier WHERE supplierName LIKE '%ABC%' | Sync | Truy van CSDL |
| 7 | Database | SupplierDAO | ResultSet (SP001, Cong ty ABC, ...) | Return | Tra ve ket qua |
| 8 | SupplierDAO | ImportFrm | return List<Supplier> | Return | Tra ve danh sach nha cung cap |
| 9 | ImportFrm | ImportFrm | displaySuppliers(list) | Self | Hien thi danh sach NCC len grdSuppliers |
| 10 | Staff | ImportFrm | selectSupplier("SP001") | Sync | Staff chon nha cung cap "Cong ty ABC" |
| 11 | ImportFrm | ImportFrm | displaySupplierInfo(supplier) | Self | Hien thi thong tin NCC da chon |
| 12 | Staff | ImportFrm | inputItemName("TH True Milk") | Sync | Staff nhap ten hang hoa vao txtSearchItem |
| 13 | Staff | ImportFrm | clickSearchItem() | Sync | Staff nhan nut "Tim hang hoa" |
| 14 | ImportFrm | ItemDAO | searchByName("TH True Milk") | Sync | Goi DAO tim hang hoa theo ten |
| 15 | ItemDAO | Database | SELECT * FROM tblItem WHERE itemName LIKE '%TH True Milk%' | Sync | Truy van CSDL |
| 16 | Database | ItemDAO | ResultSet (IT015, Sua TH True Milk, ..., 300) | Return | Tra ve ket qua |
| 17 | ItemDAO | ImportFrm | return List<Item> | Return | Tra ve danh sach hang hoa |
| 18 | ImportFrm | ImportFrm | displayItems(list) | Self | Hien thi danh sach hang hoa len grdItems |
| 19 | Staff | ImportFrm | selectItem("IT015") | Sync | Staff chon hang hoa "Sua TH True Milk" |
| 20 | Staff | ImportFrm | inputQuantity(100) | Sync | Staff nhap so luong = 100 |
| 21 | Staff | ImportFrm | inputUnitPrice(200000) | Sync | Staff nhap don gia = 200,000 |
| 22 | Staff | ImportFrm | clickAddToList() | Sync | Staff nhan nut "Them vao danh sach" |
| 23 | ImportFrm | ImportFrm | validateInput(100, 200000) | Self | Kiem tra SL > 0, DG > 0 -> hop le |
| 24 | ImportFrm | ImportFrm | checkDuplicate("IT015") | Self | Kiem tra hang chua co trong danh sach -> OK |
| 25 | ImportFrm | ImportFrm | addToImportList(item, 100, 200000) | Self | Them dong vao bang dgvImportList |
| 26 | ImportFrm | ImportFrm | updateTotal(20000000) | Self | Cap nhat tong tien |
| 27 | Staff | ImportFrm | clickSubmit() | Sync | Staff nhan nut "Nhap hang" |
| 28 | ImportFrm | ImportFrm | showConfirmDialog() | Self | Hien thi hop thoai xac nhan |
| 29 | Staff | ImportFrm | clickConfirm() | Sync | Staff nhan "Xac nhan" |
| 30 | ImportFrm | Database | BEGIN TRANSACTION | Sync | Bat dau giao dich |
| 31 | ImportFrm | ImportInvoiceDAO | insert(invoice) | Sync | Tao phieu nhap moi |
| 32 | ImportInvoiceDAO | Database | INSERT INTO tblImportInvoice ... | Sync | Luu phieu nhap |
| 33 | ImportInvoiceDAO | ImportFrm | return true | Return | Thanh cong |
| 34 | ImportFrm | ImportInvoiceDetailDAO | insertBatch(details) | Sync | Luu danh sach chi tiet |
| 35 | ImportInvoiceDetailDAO | Database | INSERT INTO tblImportInvoiceDetail ... (x3) | Sync | Luu 3 chi tiet |
| 36 | ImportInvoiceDetailDAO | ImportFrm | return true | Return | Thanh cong |
| 37 | ImportFrm | ItemDAO | updateStock("IT015", +100) | Sync | Cap nhat ton kho IT015 |
| 38 | ItemDAO | Database | UPDATE tblItem SET stockQuantity=stockQuantity+100 WHERE itemId='IT015' | Sync | Cong ton kho |
| 39 | ItemDAO | ImportFrm | return true | Return | Thanh cong |
| 40 | ImportFrm | Database | COMMIT TRANSACTION | Sync | Cam ket giao dich |
| 41 | ImportFrm | ImportFrm | printInvoice() | Self | In phieu nhap |
| 42 | ImportFrm | Staff | showSuccess("Nhap hang thanh cong!") | Return | Thong bao thanh cong |
| 43 | Staff | ImportFrm | clickOK() | Sync | Staff nhan OK |
| 44 | ImportFrm | ImportFrm | resetForm() | Self | Reset giao dien ve trang thai ban dau |

---

## Câu 5: Blackbox Testing (Test Plan)

### Test Plan cho chuc nang Nhap hang

| Test Case | Mo ta | Input | Expected Output |
|-----------|-------|-------|-----------------|
| TC01 | Nhap hang thanh cong tu nha cung cap | Ten NCC hop le, hang hoa hop le, SL > 0, DG > 0 | Phieu nhap duoc tao, ton kho tang, thong bao thanh cong |
| TC02 | Tim nha cung cap khong ton tai | Ten NCC "XYZ123" khong co trong DB | Hien thi danh sach trong, cho phep them moi |
| TC03 | Them moi nha cung cap trong qua trinh nhap | Nhap thong tin NCC moi | NCC duoc them vao DB, tu dong chon NCC moi |
| TC04 | So luong = 0 | SL = 0 | Hien thi loi "So luong phai > 0" |
| TC05 | Don gia am | Don gia = -100 | Hien thi loi "Don gia phai > 0" |
| TC06 | Danh sach nhap trong khi nhan Submit | Khong them hang nao | Hien thi loi "Danh sach trong" |
| TC07 | Tim hang hoa khong ton tai, them moi | Ten hang "XYZ999", nhan them moi | Hang hoa duoc them vao DB, tu dong chon |
| TC08 | Them trung hang hoa da co trong danh sach | Them lai IT015 khi da co | Hien thi loi "Da ton tai trong danh sach" |

### TC01: Nhap hang thanh cong tu nha cung cap

**Mo ta:** Kiem tra quy trinh nhap hang hoa thanh cong tu nha cung cap voi day du thong tin hop le.

**Preconditions:**
- Staff da dang nhap (userId = "NV003").
- Nha cung cap "Cong ty ABC" da ton tai trong he thong.

**Database Before (Du lieu truoc khi chay test):**

Bang `tblUser`:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |
| NV001 | admin | admin123 | Tran Van A | Manager |

Bang `tblSupplier`:
| supplierId | supplierName | address | phone |
|------------|--------------|---------|-------|
| SP001 | Cong ty ABC | 789 Vo Van Tan, Q.3, TP.HCM | 0908765432 |
| SP002 | Cong ty XYZ | 321 Le Van Sy, Q.3, TP.HCM | 0909876543 |

Bang `tblItem`:
| itemId | itemName | description | stockQuantity |
|--------|----------|-------------|---------------|
| IT015 | Sua TH True Milk | Sua tuoi 1 lit | 300 |
| IT028 | Banh Chocopie | Hop 12 cai | 100 |
| IT039 | Nuoc suoi Lavie | Chai 500ml | 200 |
| IT045 | Mi goi Hao Hao | Thung 30 goi | 150 |

Bang `tblImportInvoice`:
| invoiceId | importDate | totalAmount | supplierId | userId |
|-----------|------------|-------------|------------|--------|
| (rong -- chua co phieu nhap nao) | | | | |

Bang `tblImportInvoiceDetail`:
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| (rong) | | | | | |

**Test Steps (Kich ban >= 7 buoc):**

| Buoc | Hanh dong | Du lieu nhap | Ket qua mong doi |
|------|-----------|---------------|------------------|
| 1 | Staff nhan menu "Nhap hang" | -- | Giao dien ImportFrm hien thi voi o tim kiem NCC, bang NCC trong, o tim kiem hang hoa disabled |
| 2 | Staff nhap "ABC" vao txtSearchSupplier, nhan btnSearchSupplier | txtSearchSupplier = "ABC" | Bang grdSuppliers hien thi 1 dong: SP001, Cong ty ABC, 789 Vo Van Tan, Q.3, TP.HCM, 0908765432 |
| 3 | Staff nhan chon dong NCC SP001 trong grdSuppliers | Chon SP001 | Thong tin NCC hien thi: Ten = "Cong ty ABC", DC = "789 Vo Van Tan, Q.3, TP.HCM", SDT = "0908765432". O tim kiem hang hoa duoc kich hoat. |
| 4 | Staff nhap "TH True Milk" vao txtSearchItem, nhan btnSearchItem | txtSearchItem = "TH True Milk" | Bang grdItems hien thi 1 dong: IT015, Sua TH True Milk, Sua tuoi 1 lit, Ton kho: 300 |
| 5 | Staff chon IT015, nhap SL=100, Don gia=200000, nhan btnAddToList | Chon IT015; txtQuantity="100"; txtUnitPrice="200000" | dgvImportList co 1 dong: IT015, Sua TH True Milk, 100, 200,000, 20,000,000. lblTotal = "20,000,000 VND". |
| 6 | Staff tim "Chocopie", chon IT028, nhap SL=200, Don gia=150000, nhan btnAddToList | txtSearchItem="Chocopie"; Chon IT028; txtQuantity="200"; txtUnitPrice="150000" | dgvImportList co 2 dong. Dong 2: IT028, Banh Chocopie, 200, 150,000, 30,000,000. lblTotal = "50,000,000 VND". |
| 7 | Staff tim "Lavie", chon IT039, nhap SL=500, Don gia=80000, nhan btnAddToList | txtSearchItem="Lavie"; Chon IT039; txtQuantity="500"; txtUnitPrice="80000" | dgvImportList co 3 dong. Dong 3: IT039, Nuoc suoi Lavie, 500, 80,000, 40,000,000. lblTotal = "90,000,000 VND". |
| 8 | Staff nhan btnSubmit | -- | Hop thoai xac nhan: "Nhap hang tu Cong ty ABC voi tong tien 90,000,000 VND?" |
| 9 | Staff nhan "Xac nhan" | -- | Thong bao "Nhap hang thanh cong!". Phieu nhap hoa don duoc in ra. |
| 10 | Staff nhan OK | -- | Form reset ve trang thai ban dau. Bang dgvImportList trong. lblTotal = "0 VND". |

**Database After (Du lieu sau khi chay test):**

Bang `tblUser` (khong thay doi):
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| NV003 | staff01 | abc123 | Nguyen Van C | Staff |
| NV001 | admin | admin123 | Tran Van A | Manager |

Bang `tblSupplier` (khong thay doi):
| supplierId | supplierName | address | phone |
|------------|--------------|---------|-------|
| SP001 | Cong ty ABC | 789 Vo Van Tan, Q.3, TP.HCM | 0908765432 |
| SP002 | Cong ty XYZ | 321 Le Van Sy, Q.3, TP.HCM | 0909876543 |

Bang `tblItem` (thay doi stockQuantity):
| itemId | itemName | description | stockQuantity |
|--------|----------|-------------|---------------|
| IT015 | Sua TH True Milk | Sua tuoi 1 lit | 400 (was 300, plus 100) |
| IT028 | Banh Chocopie | Hop 12 cai | 300 (was 100, plus 200) |
| IT039 | Nuoc suoi Lavie | Chai 500ml | 700 (was 200, plus 500) |
| IT045 | Mi goi Hao Hao | Thung 30 goi | 150 (unchanged) |

Bang `tblImportInvoice` (them moi 1 dong):
| invoiceId | importDate | totalAmount | supplierId | userId |
|-----------|------------|-------------|------------|--------|
| PN2026001 | 2026-06-08 | 90,000,000 | SP001 | NV003 |

Bang `tblImportInvoiceDetail` (them moi 3 dong):
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ID001 | PN2026001 | IT015 | 100 | 200,000 | 20,000,000 |
| ID002 | PN2026001 | IT028 | 200 | 150,000 | 30,000,000 |
| ID003 | PN2026001 | IT039 | 500 | 80,000 | 40,000,000 |

**Pass Criteria:**
- tblImportInvoice co dung 1 ban ghi moi voi ma PN2026001.
- tblImportInvoiceDetail co dung 3 ban ghi moi lien ket voi PN2026001.
- Tong tien trong tblImportInvoice = 90,000,000 = tong cac amount trong detail (20M + 30M + 40M).
- Ton kho IT015 tang tu 300 len 400.
- Ton kho IT028 tang tu 100 len 300.
- Ton kho IT039 tang tu 200 len 700.
- Ton kho IT045 khong doi (150).
- Hien thi thong bao "Nhap hang thanh cong!".
- Form reset ve trang thai ban dau.
