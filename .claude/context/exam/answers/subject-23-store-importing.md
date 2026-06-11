# Subject 23 -- Store Management -- Module "Importing"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Staff (Nhân viên kho):** Người thực hiện thao tác nhập hàng từ nhà cung cấp.

### Preconditions
- Hệ thống có dữ liệu nhà cung cấp (bảng tblSupplier có sẵn).
- Hệ thống có dữ liệu hàng hóa (bảng tblItem có sẵn).

### Main Scenario: Nhập hàng từ nhà cung cấp

| Bước | Actor | Hành động | Hệ thống phản hồi |
|------|-------|-----------|-------------------|
| 1 | Staff | Khởi động ứng dụng. | Hiển thị giao diện LoginFrm với ô nhập username (txtUsername), ô nhập password (txtPassword), và nút "Đăng nhập" (btnLogin). |
| 2 | Staff | Nhập username `staff01`, password `******`, nhấn nút "Đăng nhập". | Hệ thống kiểm tra thông tin đăng nhập. Hiển thị HomeFrm với các chức năng: Quản lý nhập hàng, Quản lý xuất hàng, Báo cáo. |
| 3 | Staff | Nhấn chọn menu "Nhập hàng" (Import). | Hiển thị ImportFrm với ô tìm kiếm nhà cung cấp (txtSearchSupplier), danh sách nhà cung cấp rỗng, nút "Thêm mới nhà cung cấp" (btnAddSupplier), và vùng danh sách hàng nhập (tblImportList). |
| 4 | Staff | Nhập tên nhà cung cấp "Công ty ABC" vào ô txtSearchSupplier, nhấn nút "Tìm kiếm" (btnSearchSupplier). | Hệ thống truy vấn bảng tblSupplier với điều kiện supplierName LIKE '%ABC%'. Hiển thị danh sách nhà cung cấp phù hợp trong grdSuppliers gồm cột: Mã NCC, Tên NCC, Địa chỉ, Số điện thoại. |
| 5 | Staff | Nhấn chọn dòng "Công ty ABC" (Mã: SP001) trong grdSuppliers. | Hệ thống xác nhận nhà cung cấp đã chọn, hiển thị thông tin: lblSupplierName = "Công ty ABC", lblSupplierAddress = "789 Võ Văn Tần, Q.3, TP.HCM", lblSupplierPhone = "0908765432". Kích hoạt vùng tìm kiếm hàng hóa. |
| 6 | Staff | Nhập tên hàng hóa "Sữa TH True Milk" vào ô txtSearchItem, nhấn nút "Tìm kiếm" (btnSearchItem). | Hệ thống truy vấn bảng tblItem với điều kiện itemName LIKE '%TH True Milk%'. Hiển thị danh sách hàng hóa phù hợp trong grdItems gồm cột: Mã hàng, Tên hàng, Mô tả, Tồn kho. |
| 7 | Staff | Nhấn chọn dòng "Sữa TH True Milk" (Mã: IT015, Tồn kho: 300 thùng) trong grdItems. | Hệ thống hiển thị thông tin hàng hóa đã chọn. Kích hoạt ô nhập số lượng (txtQuantity) và đơn giá (txtUnitPrice). |
| 8 | Staff | Nhập số lượng = "100" vào txtQuantity, nhập đơn giá = "200,000" vào txtUnitPrice, nhấn nút "Thêm vào danh sách" (btnAddToList). | Hệ thống kiểm tra: số lượng > 0, đơn giá > 0 -> hợp lệ. Tạo dòng mới trong tblImportList: Mã hàng IT015, Tên "Sữa TH True Milk", SL: 100, Đơn giá: 200,000, Thành tiền: 20,000,000. Cập nhật tổng tiền (lblTotal). Xóa nội dung ô tìm kiếm hàng hóa. |
| 9 | Staff | Lặp lại bước 6-8 cho hàng hóa "Bánh Chocopie" (Mã: IT028, SL: 200, Đơn giá: 150,000). | Hệ thống thêm dòng vào tblImportList: IT028, Bánh Chocopie, SL: 200, Đơn giá: 150,000, Thành tiền: 30,000,000. Cập nhật tổng tiền = 50,000,000. |
| 10 | Staff | Lặp lại bước 6-8 cho hàng hóa "Nước suối Lavie" (Mã: IT039, SL: 500, Đơn giá: 80,000). | Hệ thống thêm dòng vào tblImportList: IT039, Nước suối Lavie, SL: 500, Đơn giá: 80,000, Thành tiền: 40,000,000. Cập nhật tổng tiền = 90,000,000. |
| 11 | Staff | Kiểm tra lại toàn bộ danh sách hàng nhập trong tblImportList, xác nhận đúng. Nhấn nút "Nhập hàng" (btnSubmit). | Hệ thống hiển thị hộp thoại xác nhận: "Nhập hàng từ Công ty ABC với tổng tiền 90,000,000 VND?" |
| 12 | Staff | Nhấn "Xác nhận" trong hộp thoại. | Hệ thống thực hiện: (1) Tạo bản ghi mới trong tblImportInvoice: Mã phiếu nhập PN2026001, Ngày nhập: 08/06/2026, Mã NCC: SP001, Tổng tiền: 90,000,000, Mã nhân viên: NV003. (2) Tạo 3 bản ghi trong tblImportInvoiceDetail cho từng mặt hàng. (3) Cập nhật tồn kho: IT015: 300+100=400, IT028: 100+200=300, IT039: 200+500=700. (4) Hiển thị thông báo "Nhập hàng thành công!" và in phiếu nhập. |
| 13 | Staff | Nhấn "OK" trên thông báo thành công. | Hệ thống hiển thị phiếu nhập hóa đơn (report) với đầy đủ thông tin: header (mã phiếu, ngày, nhân viên), thông tin nhà cung cấp, bảng chi tiết hàng hóa, tổng tiền. Quay về ImportFrm trống. |

### Exceptional Scenarios

**5a. Nhà cung cấp chưa tồn tại:**
- Bước 5 (thay thế): Staff không tìm thấy nhà cung cấp trong danh sách kết quả. Staff nhấn nút "Thêm mới nhà cung cấp". Hệ thống hiển thị form thêm nhà cung cấp mới (txtNewSupplierName, txtNewSupplierAddress, txtNewSupplierPhone). Staff nhập thông tin và nhấn "Lưu". Hệ thống thêm vào tblSupplier và tự động chọn nhà cung cấp mới tạo. Tiếp tục từ bước 6.

**6a. Hàng hóa chưa tồn tại:**
- Bước 6 (thay thế): Staff không tìm thấy hàng hóa trong danh sách kết quả. Staff nhấn nút "Thêm mới hàng hóa" (btnAddItem). Hệ thống hiển thị form thêm hàng hóa mới (txtNewItemName, txtNewItemDesc). Staff nhập thông tin và nhấn "Lưu". Hệ thống thêm vào tblItem với tồn kho = 0, tự động chọn hàng hóa mới. Tiếp tục từ bước 7.

**8a. Số lượng hoặc đơn giá không hợp lệ:**
- Bước 8 (ngoại lệ): Staff nhập số lượng = 0 hoặc đơn giá = -100. Hệ thống hiển thị lỗi: "Số lượng và đơn giá phải là số dương." Không thêm vào danh sách.

**8b. Hàng hóa đã tồn tại trong danh sách:**
- Bước 8 (ngoại lệ): Staff thêm lại hàng IT015 đã có trong danh sách. Hệ thống hiển thị lỗi: "Hàng hóa IT015 đã tồn tại trong danh sách. Vui lòng xóa dòng trước khi thêm lại."

**11a. Danh sách hàng nhập trống:**
- Bước 11 (ngoại lệ): Staff nhấn "Nhập hàng" khi chưa thêm hàng hóa nào. Hệ thống hiển thị lỗi: "Danh sách hàng nhập trống. Vui lòng thêm ít nhất một mặt hàng."

**12a. Lỗi hệ thống khi lưu:**
- Bước 12 (ngoại lệ): Hệ thống gặp lỗi cơ sở dữ liệu khi lưu. Hệ thống hiển thị lỗi: "Không thể lưu phiếu nhập. Vui lòng thử lại sau." Không thay đổi dữ liệu (rollback transaction).

### Postconditions
- Phieu nhap moi duoc luu trong tblImportInvoice va tblImportInvoiceDetail.
- Ton kho cac mat hang da nhap duoc tang dung so luong.
- Phieu nhap hoa don duoc in ra cho Staff va nha cung cap.
- Giao dien quay ve trang thai ban dau.

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management - Module Nhap hang bao gom cac thuc the sau:

1. **Item (Hàng hóa):** Mỗi hàng hóa có mã hàng (itemId), mã code (code), tên hàng (itemName), mô tả (description). Một hàng hóa có thể được nhập nhiều lần từ nhiều nhà cung cấp khác nhau và xuất nhiều lần cho nhiều đại lý. Hàng hóa tồn kho được quản lý qua số lượng hiện tại (stockQuantity).

2. **Supplier (Nha cung cap):** Moi nha cung cap co ma (supplierId), ten (supplierName), dia chi (address), so dien thoai (phone). Mot nha cung cap co the cung cap nhieu hang hoa qua nhieu phieu nhap. Khi nha cung cap moi chua co trong he thong, Staff co the them truc tiep.

3. **ImportInvoice (Phieu nhap):** Moi phieu nhap co ma phieu (invoiceId), ngay nhap (importDate), tong tien (totalAmount), ma nha cung cap (supplierId), ma nhan vien nhap (userId). Mot phieu nhap thuoc ve mot nha cung cap va do mot nhan vien thuc hien. Moi phieu nhap co the chua nhieu mat hang khac nhau.

4. **ImportInvoiceDetail (Chi tiet phieu nhap):** Moi chi tiet co ma chi tiet (detailId), ma phieu nhap (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount = quantity * unitPrice). Moi chi tiet thuoc ve mot phieu nhap va mot hang hoa.

5. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Mot nguoi dung co the tao nhieu phieu nhap.

### Trich xuat danh tu tu mo ta module

| STT | Danh tu trong mo ta | Entity tuong ung | Thuoc tinh |
|-----|---------------------|------------------|------------|
| 1 | Hang hoa, mat hang, san pham, vat pham | Item | itemId, code, itemName, description, stockQuantity |
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
                             | - invoiceId: String      |----------| - code: String   |
                             | - itemId: String         |          | - itemName       |
                             | - quantity: int          |          | - description    |
                             | - unitPrice: float       |          | - stockQuantity  |
                             | - amount: float          |          +------------------+
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
| Item | `<<entity>>` | `-itemId: String`, `-code: String`, `-itemName: String`, `-description: String`, `-stockQuantity: int` | — |
| Supplier | `<<entity>>` | `-supplierId: String`, `-supplierName: String`, `-address: String`, `-phone: String` | — |
| ImportInvoice | `<<entity>>` | `-invoiceId: String`, `-importDate: Date`, `-totalAmount: float`, `-supplier: Supplier`, `-user: User` | — |
| ImportInvoiceDetail | `<<entity>>` | `-detailId: String`, `-importInvoice: ImportInvoice`, `-item: Item`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | — |

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
3. Tạo class `Item` với attribute: `-itemId: String`, `-code: String`, `-itemName: String`, `-description: String`, `-stockQuantity: int`.
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

## Câu 3: Thiết kế tĩnh (Static Design)

### View Classes

| View Class | Mô tả | UI Elements |
|------------|-------|-------------|
| LoginFrm | Giao diện đăng nhập. | `inUsername` (TextField), `inPassword` (PasswordField), `subLogin` (Button) |
| HomeFrm | Giao diện chính sau khi đăng nhập. | `subImport` (Button — chọn chức năng nhập hàng) |
| ImportFrm | Giao diện chính của chức năng nhập hàng. Chứa ô tìm kiếm nhà cung cấp, danh sách NCC, ô tìm kiếm hàng hóa, danh sách hàng hóa, bảng danh sách hàng nhập, nút thêm/xóa/thanh toán. | Xem bảng UI Elements bên dưới |

### UI Elements trong ImportFrm

| UI Element | Loai | Mô tả |
|------------|------|-------|
| inSearchSupplier | JTextField | Ô nhập tên nhà cung cấp cần tìm |
| subSearchSupplier | JButton | Nút tìm kiếm nhà cung cấp |
| subAddSupplier | JButton | Nút thêm nhà cung cấp mới |
| outsubListSupplier | JTable | Bảng hiển thị danh sách nhà cung cấp tìm được (clickable) |
| outSupplierName | JLabel | Hiển thị tên nhà cung cấp đã chọn |
| outSupplierAddress | JLabel | Hiển thị địa chỉ nhà cung cấp đã chọn |
| outSupplierPhone | JLabel | Hiển thị số điện thoại nhà cung cấp đã chọn |
| inSearchItem | JTextField | Ô nhập tên hàng hóa cần tìm |
| subSearchItem | JButton | Nút tìm kiếm hàng hóa |
| subAddItem | JButton | Nút thêm hàng hóa mới |
| outsubListItem | JTable | Bảng hiển thị danh sách hàng hóa tìm được (clickable) |
| inQuantity | JTextField | Ô nhập số lượng nhập |
| inUnitPrice | JTextField | Ô nhập đơn giá nhập |
| subAddToList | JButton | Nút thêm vào danh sách nhập |
| outListImport | JTable | Bảng danh sách hàng hóa sẽ nhập |
| outTotal | JLabel | Hiển thị tổng tiền phiếu nhập |
| subRemoveItem | JButton | Nút xóa hàng khỏi danh sách nhập |
| subSubmit | JButton | Nút xác nhận nhập hàng |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| SupplierDAO | tblSupplier | searchByName(String name): List<Supplier>, addSupplier(Supplier s): boolean |
| ItemDAO | tblItem | searchByName(String name): List<Item>, addItem(Item i): String, updateStock(String itemId, int qtyChange): boolean, getStock(String itemId): int |
| ImportInvoiceDAO | tblImportInvoice, tblImportInvoiceDetail, tblItem | createImport(ImportInvoice invoice, List<ImportInvoiceDetail> details): boolean (bao gồm transaction: insert invoice, insert details, update stock), getById(String invoiceId): ImportInvoice |
| UserDAO | tblUser | checkLogin(String username, String password): User, getById(String userId): User |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Item | Entity Class | itemId: String (PK), code: String, itemName: String, description: String, stockQuantity: int |
| Supplier | Entity Class | supplierId: String (PK), supplierName: String, address: String, phone: String |
| ImportInvoice | Entity Class | invoiceId: String (PK), importDate: Date, totalAmount: float, supplier: Supplier (FK), user: User (FK) |
| ImportInvoiceDetail | Entity Class | detailId: String (PK), importInvoice: ImportInvoice (FK), item: Item (FK), quantity: int, unitPrice: float, amount: float |
| User | Entity Class | userId: String (PK), username: String, password: String, fullName: String, role: String |

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
    code        VARCHAR(20) NOT NULL,
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
    totalAmount DECIMAL(15,2) NOT NULL,
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
    unitPrice   DECIMAL(15,2) NOT NULL,
    amount      DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES tblImportInvoice(invoiceId),
    FOREIGN KEY (itemId) REFERENCES tblItem(itemId)
);

-- Indexes for frequently queried foreign keys
CREATE INDEX idx_importinvoice_supplierId ON tblImportInvoice(supplierId);
CREATE INDEX idx_importinvoice_userId ON tblImportInvoice(userId);
CREATE INDEX idx_importinvoicedetail_invoiceId ON tblImportInvoiceDetail(invoiceId);
CREATE INDEX idx_importinvoicedetail_itemId ON tblImportInvoiceDetail(itemId);
```

### Visual Paradigm (VP) Guide

**Tao Class Diagram trong VP:**
1. Mo VP -> New -> Class Diagram.
2. Tao 5 class: User, Supplier, Item, ImportInvoice, ImportInvoiceDetail.
3. Them attributes cho moi class theo bang Entity classes (Design phase).
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
2. Tao cac lifeline: Staff, ImportFrm, SupplierDAO, ItemDAO, ImportInvoiceDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho cac truong hop ngoai le (NCC/hang chua ton tai, du lieu khong hop le).
5. Su dung loop fragment cho viec them nhieu mat hang vao danh sach.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Staff          ImportFrm        SupplierDAO    ItemDAO       ImportInvoiceDAO    Database
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
  |                |-------------------------------------------->createImport()|             |
  |                |                |              |                |              |             |
  |                |                |              |                |--BEGIN TXN---|----------->|
  |                |                |              |                |              |             |
  |                |                |              |                |--generateId--|----------->|
  |                |                |              |                |<-"PN2026001"-|-----------|
  |                |                |              |                |              |             |
  |                |                |              |                |--INSERT------|----------->|
  |                |                |              |                |<--OK---------|-----------|
  |                |                |              |                |              |             |
  |                |                |              |                |              |--INSERT-->|
  |                |                |              |                |              |    x3     |
  |                |                |              |                |              |<--OK------|
  |                |                |              |                |              |             |
  |                |                |              |                |--UPDATE------|----------->|
  |                |                |              |                |  stock x3    |           |
  |                |                |              |                |<--OK---------|-----------|
  |                |                |              |                |              |             |
  |                |                |              |                |--COMMIT------|----------->|
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
| 30 | ImportFrm | ImportInvoiceDAO | createImport(invoice, details) | Sync | Gọi DAO tạo phiếu nhập (bao gồm transaction) |
| 31 | ImportInvoiceDAO | Database | BEGIN TRANSACTION | Sync | Bắt đầu giao dịch |
| 32 | ImportInvoiceDAO | Database | INSERT INTO tblImportInvoice ... | Sync | Lưu phiếu nhập |
| 33 | ImportInvoiceDAO | Database | INSERT INTO tblImportInvoiceDetail ... (x3) | Sync | Lưu 3 chi tiết |
| 34 | ImportInvoiceDAO | Database | UPDATE tblItem SET stockQuantity=stockQuantity+100 WHERE itemId='IT015' | Sync | Cộng tồn kho IT015 |
| 35 | ImportInvoiceDAO | Database | UPDATE tblItem SET stockQuantity=stockQuantity+200 WHERE itemId='IT028' | Sync | Cộng tồn kho IT028 |
| 36 | ImportInvoiceDAO | Database | UPDATE tblItem SET stockQuantity=stockQuantity+500 WHERE itemId='IT039' | Sync | Cộng tồn kho IT039 |
| 37 | ImportInvoiceDAO | Database | COMMIT TRANSACTION | Sync | Cam kết giao dịch |
| 38 | ImportInvoiceDAO | ImportFrm | return true | Return | Thành công |
| 39 | ImportFrm | ImportFrm | printInvoice() | Self | In phiếu nhập |
| 40 | ImportFrm | Staff | showSuccess("Nhập hàng thành công!") | Return | Thông báo thành công |
| 41 | Staff | ImportFrm | clickOK() | Sync | Staff nhấn OK |
| 42 | ImportFrm | ImportFrm | resetForm() | Self | Reset giao diện về trạng thái ban đầu |

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

### TC01: Nhập hàng thành công từ nhà cung cấp

**Purpose:** Kiểm tra quy trình nhập hàng hóa thành công từ nhà cung cấp với đầy đủ thông tin hợp lệ, xác nhận phiếu nhập được tạo và tồn kho được cập nhật đúng.

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
| itemId | code | itemName | description | stockQuantity |
|--------|------|----------|-------------|---------------|
| IT015 | SP015 | Sua TH True Milk | Sua tuoi 1 lit | 300 |
| IT028 | SP028 | Banh Chocopie | Hop 12 cai | 100 |
| IT039 | SP039 | Nuoc suoi Lavie | Chai 500ml | 200 |
| IT045 | SP045 | Mi goi Hao Hao | Thung 30 goi | 150 |

Bang `tblImportInvoice`:
| invoiceId | importDate | totalAmount | supplierId | userId |
|-----------|------------|-------------|------------|--------|
| (rong -- chua co phieu nhap nao) | | | | |

Bang `tblImportInvoiceDetail`:
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| (rong) | | | | | |

**Test Steps (Kịch bản >= 7 bước):**

| Bước | Hành động | Dữ liệu nhập | Kết quả mong đợi |
|------|-----------|---------------|------------------|
| 1 | Staff khởi động ứng dụng, nhập username `staff01`, password `abc123`, nhấn Đăng nhập | username: staff01, password: abc123 | Giao diện HomeFrm hiển thị với các chức năng: Quản lý nhập hàng, Quản lý xuất hàng, Báo cáo |
| 2 | Staff nhấn menu "Nhập hàng" | -- | Giao diện ImportFrm hiển thị với ô tìm kiếm NCC, bảng NCC rỗng, ô tìm kiếm hàng hóa disabled |
| 3 | Staff nhập "ABC" vào ô tìm kiếm NCC, nhấn nút Tìm kiếm | "ABC" | Bảng danh sách NCC hiển thị 1 dòng: SP001, Công ty ABC, 789 Võ Văn Tần, Q.3, TP.HCM, 0908765432 |
| 4 | Staff nhấn chọn dòng NCC SP001 | Chọn SP001 | Thông tin NCC hiển thị: Tên = "Công ty ABC", DC = "789 Võ Văn Tần, Q.3, TP.HCM", SĐT = "0908765432". Ô tìm kiếm hàng hóa được kích hoạt. |
| 5 | Staff nhập "TH True Milk" vào ô tìm kiếm hàng hóa, nhấn nút Tìm kiếm | "TH True Milk" | Bảng danh sách hàng hóa hiển thị 1 dòng: IT015, Sữa TH True Milk, Sữa tươi 1 lit, Tồn kho: 300 |
| 6 | Staff chọn IT015, nhập SL=100, Đơn giá=200000, nhấn nút Thêm vào danh sách | SL=100, Đơn giá=200000 | Bảng nhập có 1 dòng: IT015, Sữa TH True Milk, 100, 200,000, 20,000,000. Tổng tiền = "20,000,000 VND". |
| 7 | Staff tìm "Chocopie", chọn IT028, nhập SL=200, Đơn giá=150000, nhấn nút Thêm | SL=200, Đơn giá=150000 | Bảng nhập có 2 dòng. Dòng 2: IT028, Bánh Chocopie, 200, 150,000, 30,000,000. Tổng tiền = "50,000,000 VND". |
| 8 | Staff tìm "Lavie", chọn IT039, nhập SL=500, Đơn giá=80000, nhấn nút Thêm | SL=500, Đơn giá=80000 | Bảng nhập có 3 dòng. Dòng 3: IT039, Nước suối Lavie, 500, 80,000, 40,000,000. Tổng tiền = "90,000,000 VND". |
| 9 | Kiểm tra DB trước khi Submit | -- | tblImportInvoice vẫn rỗng (chưa có phiếu nhập mới). tblImportInvoiceDetail vẫn rỗng. Tồn kho IT015=300, IT028=100, IT039=200 (chưa thay đổi). |
| 10 | Staff nhấn nút Nhập hàng | -- | Hộp thoại xác nhận: "Nhập hàng từ Công ty ABC với tổng tiền 90,000,000 VND?" |
| 11 | Staff nhấn "Xác nhận" | -- | Thông báo "Nhập hàng thành công!". Phiếu nhập hóa đơn được in ra. |
| 12 | Staff nhấn OK | -- | Form reset về trạng thái ban đầu. Bảng danh sách nhập trống. Tổng tiền = "0 VND". |

**Database After (Dữ liệu sau khi chạy test):**

Bang `tblImportInvoice` (thêm mới 1 dòng):
| invoiceId | importDate | totalAmount | supplierId | userId |
|-----------|------------|-------------|------------|--------|
| PN2026001 | 2026-06-08 | 90,000,000 | SP001 | NV003 |

Bang `tblImportInvoiceDetail` (thêm mới 3 dòng):
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| ID001 | PN2026001 | IT015 | 100 | 200,000 | 20,000,000 |
| ID002 | PN2026001 | IT028 | 200 | 150,000 | 30,000,000 |
| ID003 | PN2026001 | IT039 | 500 | 80,000 | 40,000,000 |

Bang `tblItem` (cập nhật tồn kho — chỉ update cột stockQuantity):
| itemId | stockQuantity |
|--------|---------------|
| IT015 | 400 (was 300, plus 100) |
| IT028 | 300 (was 100, plus 200) |
| IT039 | 700 (was 200, plus 500) |

**Bảng không thay đổi:** tblUser, tblSupplier

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
