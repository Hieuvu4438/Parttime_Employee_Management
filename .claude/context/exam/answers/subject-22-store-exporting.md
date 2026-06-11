# Subject 22 -- Store Management -- Module "Exporting"

## Câu 1: Scenario (Functional Requirement)

### Actors
- **Staff (Nhân viên kho):** Người thực hiện thao tác xuất hàng cho đại lý.

### Preconditions
- Hệ thống có dữ liệu hàng hóa trong kho (bảng tblItem có sẵn).
- Hệ thống có dữ liệu đại lý (bảng tblSubAgent có sẵn).

### Main Scenario: Xuất hàng cho đại lý

| Bước | Actor | Hành động | Hệ thống phản hồi |
|------|-------|-----------|-------------------|
| 1 | Staff | Khởi động ứng dụng. | Hiển thị giao diện LoginFrm với ô nhập username (txtUsername), ô nhập password (txtPassword), và nút "Đăng nhập" (btnLogin). |
| 2 | Staff | Nhập username `staff01`, password `******`, nhấn nút "Đăng nhập". | Hệ thống kiểm tra thông tin đăng nhập. Hiển thị HomeFrm với các chức năng: Quản lý nhập hàng, Quản lý xuất hàng, Báo cáo. |
| 3 | Staff | Nhấn chọn menu "Xuất hàng" (Export). | Hiển thị ExportFrm với ô tìm kiếm đại lý (txtSearchAgent), danh sách đại lý rống, nút "Thêm mới đại lý" (btnAddAgent), và vùng danh sách hàng xuất (tblExportList). |
| 4 | Staff | Nhập tên đại lý "Minh Khang" vào ô txtSearchAgent, nhấn nút "Tìm kiếm" (btnSearchAgent). | Hệ thống truy vấn bảng tblSubAgent với điều kiện agentName LIKE '%Minh Khang%'. Hiển thị danh sách đại lý phù hợp trong bảng kết quả (grdAgents) gồm cột: Mã đại lý, Tên đại lý, Địa chỉ, Số điện thoại. |
| 5 | Staff | Nhấn chọn dòng "Đại lý Minh Khang" (Mã: AG005) trong grdAgents. | Hệ thống xác nhận đại lý đã chọn, hiển thị thông tin đại lý (lblAgentName = "Đại lý Minh Khang", lblAgentAddress = "123 Lê Lợi, Q.1, TP.HCM", lblAgentPhone = "0901234567"). Kích hoạt vùng tìm kiếm hàng hóa. |
| 6 | Staff | Nhập tên hàng hóa "Sữa Vinamilk" vào ô txtSearchItem, nhấn nút "Tìm kiếm" (btnSearchItem). | Hệ thống truy vấn bảng tblItem với điều kiện itemName LIKE '%Vinamilk%'. Hiển thị danh sách hàng hóa phù hợp trong grdItems gồm cột: Mã hàng, Tên hàng, Mô tả, Tồn kho. |
| 7 | Staff | Nhấn chọn dòng "Sữa Vinamilk" (Mã: IT012, Tồn kho: 500 thùng) trong grdItems. | Hệ thống hiển thị thông tin hàng hóa đã chọn. Kích hoạt ô nhập số lượng (txtQuantity) và đơn giá (txtUnitPrice). |
| 8 | Staff | Nhập số lượng = "50" vào txtQuantity, nhập đơn giá = "250,000" vào txtUnitPrice, nhấn nút "Thêm vào danh sách" (btnAddToList). | Hệ thống kiểm tra: số lượng (50) <= tồn kho (500) -> hợp lệ. Tạo dòng mới trong tblExportList: Mã hàng IT012, Tên "Sữa Vinamilk", SL: 50, Đơn giá: 250,000, Thành tiền: 12,500,000. Cập nhật tổng tiền (lblTotal). Xóa nội dung ô tìm kiếm hàng hóa. |
| 9 | Staff | Lặp lại bước 6-8 cho hàng hóa "Bánh Oreo" (Mã: IT023, SL: 30, Đơn giá: 180,000). | Hệ thống thêm dòng vào tblExportList: IT023, Bánh Oreo, SL: 30, Đơn giá: 180,000, Thành tiền: 5,400,000. Cập nhật tổng tiền = 17,900,000. |
| 10 | Staff | Lặp lại bước 6-8 cho hàng hóa "Nước ngọt Coca" (Mã: IT034, SL: 100, Đơn giá: 120,000). | Hệ thống thêm dòng vào tblExportList: IT034, Nước ngọt Coca, SL: 100, Đơn giá: 120,000, Thành tiền: 12,000,000. Cập nhật tổng tiền = 29,900,000. |
| 11 | Staff | Kiểm tra lại toàn bộ danh sách hàng xuất trong tblExportList, xác nhận đúng. Nhấn nút "Xuất hóa đơn" (btnSubmit). | Hệ thống hiển thị hộp thoại xác nhận: "Xuất hóa đơn cho Đại lý Minh Khang với tổng tiền 29,900,000 VND?" |
| 12 | Staff | Nhấn "Xác nhận" trong hộp thoại. | Hệ thống thực hiện: (1) Tạo bản ghi mới trong tblExportInvoice: Mã phiếu xuất PX2026001, Ngày xuất: 08/06/2026, Mã đại lý: AG005, Tổng tiền: 29,900,000, Mã nhân viên: NV003. (2) Tạo 3 bản ghi trong tblExportInvoiceDetail cho từng mặt hàng. (3) Cập nhật tồn kho: IT012: 500-50=450, IT023: 200-30=170, IT034: 300-100=200. (4) Hiển thị thông báo "Xuất hóa đơn thành công!" và in phiếu xuất. |
| 13 | Staff | Nhấn "OK" trên thông báo thành công. | Hệ thống hiển thị phiếu xuất hóa đơn (report) với đầy đủ thông tin: header (mã phiếu, ngày, nhân viên), thông tin đại lý, bảng chi tiết hàng hóa, tổng tiền. Quay về ExportFrm trống. |

### Exceptional Scenarios

**5a. Đại lý chưa tồn tại:**
- Bước 5 (thay thế): Staff không tìm thấy đại lý trong danh sách kết quả. Staff nhấn nút "Thêm mới đại lý". Hệ thống hiển thị form thêm đại lý mới (txtNewAgentName, txtNewAgentAddress, txtNewAgentPhone). Staff nhập thông tin và nhấn "Lưu". Hệ thống thêm vào tblSubAgent và tự động chọn đại lý mới tạo. Tiếp tục từ bước 6.

**8a. Số lượng xuất vượt tồn kho:**
- Bước 8 (ngoại lệ): Staff nhập số lượng = 600 cho Sữa Vinamilk (tồn kho = 500). Hệ thống hiển thị lỗi: "Số lượng xuất (600) vượt qua tồn kho hiện tại (500). Vui lòng nhập lại." Không thêm vào danh sách. Staff sửa số lượng và thực hiện lại.

**8b. Số lượng hoặc đơn giá không hợp lệ:**
- Bước 8 (ngoại lệ): Staff nhập số lượng = 0 hoặc đơn giá = -100. Hệ thống hiển thị lỗi: "Số lượng và đơn giá phải là số dương." Không thêm vào danh sách.

**8c. Hàng hóa đã tồn tại trong danh sách:**
- Bước 8 (ngoại lệ): Staff thêm lại hàng IT012 đã có trong danh sách. Hệ thống hiển thị lỗi: "Hàng hóa IT012 đã tồn tại trong danh sách. Vui lòng xóa dòng trước khi thêm lại." Hoặc hệ thống cộng dồn số lượng (tùy thiết kế).

**11a. Danh sách hàng xuất trống:**
- Bước 11 (ngoại lệ): Staff nhấn "Xuất hóa đơn" khi chưa thêm hàng hóa nào. Hệ thống hiển thị lỗi: "Danh sách hàng xuất trống. Vui lòng thêm ít nhất một mặt hàng."

**12a. Lỗi hệ thống khi lưu:**
- Bước 12 (ngoại lệ): Hệ thống gặp lỗi cơ sở dữ liệu khi lưu. Hệ thống hiển thị lỗi: "Không thể lưu hóa đơn. Vui lòng thử lại sau." Không thay đổi dữ liệu (rollback transaction).

### Postconditions
- Hoa don xuat moi duoc luu trong tblExportInvoice va tblExportInvoiceDetail.
- Ton kho cac mat hang da xuat duoc cap nhat giam dung so luong.
- Phieu xuat hoa duoc in ra cho Staff va dai ly.
- Giao dien quay ve trang thai ban dau, san sang cho lan xuat tiep theo.

---

## Câu 2: Entity Class Description

### Mo ta tu nhien (Natural Language)

He thong quan ly kho Store Management bao gom cac thuc the sau:

1. **Item (Hàng hóa):** Mỗi hàng hóa có mã hàng (itemId), mã code (code), tên hàng (itemName), mô tả (description). Một hàng hóa có thể được nhập nhiều lần và xuất nhiều lần cho nhiều đại lý khác nhau. Hàng hóa tồn kho được quản lý qua số lượng hiện tại (stockQuantity).

2. **Supplier (Nha cung cap):** Moi nha cung cap co ma (supplierId), ten (supplierName), dia chi (address), so dien thoai (phone). Mot nha cung cap co the cung cap nhieu hang hoa qua nhieu phieu nhap.

3. **SubAgent (Dai ly):** Moi dai ly co ma (agentId), ten (agentName), dia chi (address), so dien thoai (phone). Mot dai ly co the nhan nhieu hang hoa qua nhieu phieu xuat.

4. **ImportInvoice (Phieu nhap):** Moi phieu nhap co ma phieu (invoiceId), ngay nhap (importDate), tong tien (totalAmount), ma nha cung cap (supplierId), ma nhan vien nhap (userId). Mot phieu nhap thuoc ve mot nha cung cap va do mot nhan vien thuc hien.

5. **ImportInvoiceDetail (Chi tiet phieu nhap):** Moi chi tiet co ma chi tiet (detailId), ma phieu nhap (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Moi chi tiet thuoc ve mot phieu nhap va mot hang hoa.

6. **ExportInvoice (Phieu xuat):** Moi phieu xuat co ma phieu (invoiceId), ngay xuat (exportDate), tong tien (totalAmount), ma dai ly (agentId), ma nhan vien xuat (userId). Mot phieu xuat thuoc ve mot dai ly va do mot nhan vien thuc hien.

7. **ExportInvoiceDetail (Chi tiet phieu xuat):** Moi chi tiet co ma chi tiet (detailId), ma phieu xuat (invoiceId), ma hang hoa (itemId), so luong (quantity), don gia (unitPrice), thanh tien (amount). Moi chi tiet thuoc ve mot phieu xuat va mot hang hoa.

8. **User (Nguoi dung):** Moi nguoi dung co ma (userId), ten dang nhap (username), mat khau (password), ho ten (fullName), vai tro (role). Mot nguoi dung co the tao nhieu phieu nhap va nhieu phieu xuat.

### Trich xuat danh tu tu mo ta module

| Danh tu | Phân loại | Lý do |
|---------|-----------|-------|
| Hang hoa, mat hang (Item) | Entity class | Đối tượng chính: hàng hóa trong kho |
| Nha cung cap (Supplier) | Entity class | Nhà cung cấp hàng nhập |
| Dai ly (SubAgent) | Entity class | Đại lý nhận hàng xuất |
| Phieu nhap (ImportInvoice) | Entity class | Phiếu ghi nhận nhập hàng |
| Chi tiet phieu nhap (ImportInvoiceDetail) | Entity class | Chi tiết từng mặt hàng trong phiếu nhập |
| Phieu xuat (ExportInvoice) | Entity class | Phiếu ghi nhận xuất hàng |
| Chi tiet phieu xuat (ExportInvoiceDetail) | Entity class | Chi tiết từng mặt hàng trong phiếu xuất |
| Nhan vien, nguoi dung (User) | Entity class | Người thao tác trên hệ thống |
| Ma hang, code, ten hang, mo ta, ton kho | Attribute (Item) | Thuộc tính của Item |
| Ten nha cung cap, dia chi, SDT | Attribute (Supplier) | Thuộc tính của Supplier |
| Ten dai ly, dia chi, SDT | Attribute (SubAgent) | Thuộc tính của SubAgent |
| Ngay nhap, tong tien | Attribute (ImportInvoice) | Thuộc tính của ImportInvoice |
| Ngay xuat, tong tien | Attribute (ExportInvoice) | Thuộc tính của ExportInvoice |
| So luong, don gia, thanh tien | Attribute (Detail) | Thuộc tính của ImportInvoiceDetail / ExportInvoiceDetail |
| Username, password, ho ten, vai tro | Attribute (User) | Thuộc tính của User |
| So luong ton kho | Attribute (Item) | Số lượng hiện có trong kho |

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
| - agentName      |----------| - code: String   |
| - address        |          | - itemName       |
| - phone          |          | - description    |
+------------------+          | - stockQuantity  |
                              +------------------+
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

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes (hình chữ nhật 3 ngăn) cho: User, Item, Supplier, SubAgent, ImportInvoice, ImportInvoiceDetail, ExportInvoice, ExportInvoiceDetail |
| 3 | Tạo các view class boxes từ giao diện: HomeFrm, ExportFrm |
| 4 | Vẽ các đường kết nối (relationships) giữa các class theo bảng quan hệ bên dưới |
| 5 | Ghi multiplicity (1, N) và role name trên từng đầu đường kết nối |

#### 2. Cấu trúc 1 class box (3 ngăn)

Mỗi class trong Visual Paradigm được thể hiện bằng hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ ExportInvoice |
|------|----------|---------------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>` hoặc `<<boundary>>` + tên class | `<<entity>> ExportInvoice` |
| Ngăn 2 — Thuộc tính | Định dạng `-tenThuocTinh: KieuDuLieu` | `-invoiceId: String` `-exportDate: Date` `-totalAmount: float` |
| Ngăn 3 — Phương thức | Định dạng `+tenPhuongThuc(thamSo): KieuTraVe` | `+insertExportInvoice(inv: ExportInvoice): String` |

Cách thao tác: Kéo biểu tượng Class từ panel bên trái vào canvas → double-click để chỉnh sửa tên → click phải chọn "Add Attribute" hoặc "Add Operation" để thêm thuộc tính/phương thức.

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| User | `<<entity>>` | `-userId: String`, `-username: String`, `-password: String`, `-fullName: String`, `-role: String` | — |
| Item | `<<entity>>` | `-itemId: String`, `-code: String`, `-itemName: String`, `-description: String`, `-stockQuantity: int` | — |
| Supplier | `<<entity>>` | `-supplierId: String`, `-supplierName: String`, `-address: String`, `-phone: String` | — |
| SubAgent | `<<entity>>` | `-agentId: String`, `-agentName: String`, `-address: String`, `-phone: String` | — |
| ImportInvoice | `<<entity>>` | `-invoiceId: String`, `-importDate: Date`, `-totalAmount: float`, `-supplier: Supplier`, `-user: User` | — |
| ImportInvoiceDetail | `<<entity>>` | `-detailId: String`, `-importInvoice: ImportInvoice`, `-item: Item`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | — |
| ExportInvoice | `<<entity>>` | `-invoiceId: String`, `-exportDate: Date`, `-totalAmount: float`, `-agent: SubAgent`, `-user: User` | — |
| ExportInvoiceDetail | `<<entity>>` | `-detailId: String`, `-exportInvoice: ExportInvoice`, `-item: Item`, `-quantity: int`, `-unitPrice: float`, `-amount: float` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subExport` (Button — chọn chức năng xuất hàng) |
| ExportFrm | `<<boundary>>` | `inSearchAgent` (TextField — nhập tên đại lý), `subSearchAgent` (Button — tìm đại lý), `subAddAgent` (Button — thêm đại lý mới), `outsubListAgent` (Table — danh sách đại lý, click chọn), `outAgentName` (Label — tên đại lý đã chọn), `outAgentAddress` (Label — địa chỉ), `outAgentPhone` (Label — SĐT), `inSearchItem` (TextField — nhập tên hàng hóa), `subSearchItem` (Button — tìm hàng), `outsubListItem` (Table — danh sách hàng hóa, click chọn), `inQuantity` (TextField — nhập số lượng), `inUnitPrice` (TextField — nhập đơn giá), `subAddToList` (Button — thêm vào danh sách), `outListExport` (Table — danh sách hàng xuất), `outTotal` (Label — tổng tiền), `subSubmit` (Button — xuất hóa đơn) |

Quy tắc prefix:
- `in`: thành phần nhập liệu (TextField, ComboBox, DatePicker)
- `out`: thành phần hiển thị (Label, Table chỉ đọc)
- `sub`: thành phần gửi/nhấn (Button)
- `outsub`: thành phần hiển thị đồng thời cho phép click chọn (Table có thể click dòng)

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu thông thường (1 class biết đến class khác) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng ◇ | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | "Sử dụng" tạm thời (view gọi DAO) |

Cách thao tác:
- Association: Kéo tool "Association" từ panel → click class nguồn → click class đích.
- Aggregation/Composition: Chọn đường kết nối → trong Properties panel, chọn Aggregation hoặc Composition.
- Dependency: Kéo tool "Dependency" từ panel → click class nguồn → click class đích.

#### 6. Cách ghi multiplicity

Đặt multiplicity ở cả 2 đầu của đường kết nối:

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 0..* hoặc 1..* | `N` hoặc `*` | Nhiều đối tượng (0 hoặc nhiều, 1 hoặc nhiều) |

Thao tác: Click vào đường kết nối → trong Properties panel, chỉnh "Multiplicity" ở đầu Source và Target.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| User | ImportInvoice | Association | 1 --- N | Một nhân viên tạo nhiều phiếu nhập |
| User | ExportInvoice | Association | 1 --- N | Một nhân viên tạo nhiều phiếu xuất |
| Supplier | ImportInvoice | Association | 1 --- N | Một nhà cung cấp có nhiều phiếu nhập |
| SubAgent | ExportInvoice | Association | 1 --- N | Một đại lý có nhiều phiếu xuất |
| ImportInvoice | ImportInvoiceDetail | Composition | 1 --- N | Chi tiết phiếu nhập không tồn tại nếu không có phiếu nhập cha |
| ExportInvoice | ExportInvoiceDetail | Composition | 1 --- N | Chi tiết phiếu xuất không tồn tại nếu không có phiếu xuất cha |
| Item | ImportInvoiceDetail | Association | 1 --- N | Một hàng hóa xuất hiện trong nhiều chi tiết phiếu nhập |
| Item | ExportInvoiceDetail | Association | 1 --- N | Một hàng hóa xuất hiện trong nhiều chi tiết phiếu xuất |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ quan hệ ExportInvoice (1) --- (N) ExportInvoiceDetail (Composition)**

1. Tạo class `ExportInvoice` với stereotype `<<entity>>`, thêm các attribute: `-invoiceId: String`, `-exportDate: Date`, `-totalAmount: float`.
2. Tạo class `ExportInvoiceDetail` với stereotype `<<entity>>`, thêm attribute: `-detailId: String`, `-quantity: int`, `-unitPrice: float`, `-amount: float`.
3. Kéo tool **Composition** từ panel → click vào `ExportInvoice` → kéo sang `ExportInvoiceDetail`.
4. Click vào đường kết nối → đặt multiplicity: đầu ExportInvoice = `1`, đầu ExportInvoiceDetail = `N`.
5. Click vào đường kết nối → thêm role name ở đầu ExportInvoiceDetail: `details`.

**Ví dụ 2: Vẽ quan hệ Item (1) --- (N) ExportInvoiceDetail (Association)**

1. Tạo class `Item` với attribute: `-itemId: String`, `-code: String`, `-itemName: String`.
2. Kéo tool **Association** từ panel → click vào `Item` → kéo sang `ExportInvoiceDetail`.
3. Đặt multiplicity: đầu Item = `1`, đầu ExportInvoiceDetail = `N`.

---

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

## Câu 3: Thiết kế tĩnh (Static Design)

### View Classes

| View Class | Mô tả | UI Elements |
|------------|-------|-------------|
| LoginFrm | Giao diện đăng nhập. | `inUsername` (TextField), `inPassword` (PasswordField), `subLogin` (Button) |
| HomeFrm | Giao diện chính sau khi đăng nhập. | `subExport` (Button — chọn chức năng xuất hàng) |
| ExportFrm | Giao diện chính của chức năng xuất hàng. Chứa ô tìm kiếm đại lý, danh sách đại lý, ô tìm kiếm hàng hóa, danh sách hàng hóa, bảng danh sách hàng xuất, nút thêm/xóa/thanh toán. | Xem bảng UI Elements bên dưới |

### UI Elements trong ExportFrm

| UI Element | Loai | Mô tả |
|------------|------|-------|
| inSearchAgent | JTextField | Ô nhập tên đại lý cần tìm |
| subSearchAgent | JButton | Nút tìm kiếm đại lý |
| subAddAgent | JButton | Nút thêm đại lý mới |
| outsubListAgent | JTable | Bảng hiển thị danh sách đại lý tìm được (clickable) |
| outAgentName | JLabel | Hiển thị tên đại lý đã chọn |
| outAgentAddress | JLabel | Hiển thị địa chỉ đại lý đã chọn |
| outAgentPhone | JLabel | Hiển thị số điện thoại đại lý đã chọn |
| inSearchItem | JTextField | Ô nhập tên hàng hóa cần tìm |
| subSearchItem | JButton | Nút tìm kiếm hàng hóa |
| outsubListItem | JTable | Bảng hiển thị danh sách hàng hóa tìm được (clickable) |
| inQuantity | JTextField | Ô nhập số lượng xuất |
| inUnitPrice | JTextField | Ô nhập đơn giá xuất |
| subAddToList | JButton | Nút thêm vào danh sách xuất |
| outListExport | JTable | Bảng danh sách hàng hóa sẽ xuất |
| outTotal | JLabel | Hiển thị tổng tiền hóa đơn |
| subRemoveItem | JButton | Nút xóa hàng khỏi danh sách xuất |
| subSubmit | JButton | Nút xác nhận xuất hóa đơn |

### DAO Classes

| DAO Class | Bang | Phuong thuc chinh |
|-----------|------|------------------|
| SubAgentDAO | tblSubAgent | searchByName(String name): List<SubAgent>, addAgent(SubAgent agent): boolean |
| ItemDAO | tblItem | searchByName(String name): List<Item>, updateStock(String itemId, int qtyChange): boolean, getStock(String itemId): int |
| ExportInvoiceDAO | tblExportInvoice, tblExportInvoiceDetail, tblItem | createExport(ExportInvoice invoice, List<ExportInvoiceDetail> details): boolean (bao gồm transaction: insert invoice, insert details, update stock), getById(String invoiceId): ExportInvoice |
| UserDAO | tblUser | checkLogin(String username, String password): User, getById(String userId): User |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Item | Entity Class | itemId: String (PK), code: String, itemName: String, description: String, stockQuantity: int |
| SubAgent | Entity Class | agentId: String (PK), agentName: String, address: String, phone: String |
| ExportInvoice | Entity Class | invoiceId: String (PK), exportDate: Date, totalAmount: float, agent: SubAgent (FK), user: User (FK) |
| ExportInvoiceDetail | Entity Class | detailId: String (PK), exportInvoice: ExportInvoice (FK), item: Item (FK), quantity: int, unitPrice: float, amount: float |
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
    stockQuantity INT NOT NULL DEFAULT 0
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
    totalAmount DECIMAL(15,2) NOT NULL,
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
    unitPrice   DECIMAL(15,2) NOT NULL,
    amount      DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES tblExportInvoice(invoiceId),
    FOREIGN KEY (itemId) REFERENCES tblItem(itemId)
);

-- Indexes for frequently queried foreign keys
CREATE INDEX idx_exportinvoice_agentId ON tblExportInvoice(agentId);
CREATE INDEX idx_exportinvoice_userId ON tblExportInvoice(userId);
CREATE INDEX idx_exportinvoicedetail_invoiceId ON tblExportInvoiceDetail(invoiceId);
CREATE INDEX idx_exportinvoicedetail_itemId ON tblExportInvoiceDetail(itemId);
```

### Visual Paradigm (VP) Guide

**Tao Class Diagram trong VP:**
1. Mo VP -> New -> Class Diagram.
2. Tao 8 class: User, Supplier, SubAgent, Item, ImportInvoice, ImportInvoiceDetail, ExportInvoice, ExportInvoiceDetail.
3. Them attributes cho moi class theo bang Entity classes (Design phase).
4. Ve association (moi quan he):
   - User 1---N ImportInvoice (ImportInvoice co object attribute `user: User`).
   - User 1---N ExportInvoice (ExportInvoice co object attribute `user: User`).
   - Supplier 1---N ImportInvoice (ImportInvoice co object attribute `supplier: Supplier`).
   - SubAgent 1---N ExportInvoice (ExportInvoice co object attribute `agent: SubAgent`).
   - ImportInvoice 1---N ImportInvoiceDetail (ImportInvoiceDetail co object attribute `importInvoice: ImportInvoice`).
   - ExportInvoice 1---N ExportInvoiceDetail (ExportInvoiceDetail co object attribute `exportInvoice: ExportInvoice`).
   - Item 1---N ImportInvoiceDetail (ImportInvoiceDetail co object attribute `item: Item`).
   - Item 1---N ExportInvoiceDetail (ExportInvoiceDetail co object attribute `item: Item`).
5. Dat multiplicity dung theo bang Moi quan he.
6. Export diagram duoi dang PNG de dua vao bao cao.

---

## Câu 4: Sequence Diagram (Chuc nang xuat hang)

### Visual Paradigm (VP) Guide

1. Mo VP -> New -> Sequence Diagram.
2. Tao cac lifeline: Staff, ExportFrm, SubAgentDAO, ItemDAO, ExportInvoiceDAO, Database.
3. Ve message theo bang buoc ben duoi.
4. Su dung alt fragment cho cac truong hop ngoai le (so luong vuot ton kho, danh sach trong).
5. Su dung loop fragment cho viec them nhieu mat hang vao danh sach.
6. Export diagram duoi dang PNG.

### ASCII Sequence Diagram

```
Staff          ExportFrm        SubAgentDAO    ItemDAO       ExportInvoiceDAO    Database
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
  |                |-------------------------------------------->createExport() |             |
  |                |                |              |                |              |             |
  |                |                |              |                |--BEGIN TXN---|----------->|
  |                |                |              |                |              |             |
  |                |                |              |                |--generateId--|----------->|
  |                |                |              |                |<-"PX2026001"-|-----------|
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
  |                |<-------------------------------------------true            |             |
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
| 33 | ExportFrm | ExportInvoiceDAO | createExport(invoice, details, stockChanges) | Sync | Gọi DAO tạo phiếu xuất (bao gồm transaction) |
| 34 | ExportInvoiceDAO | Database | BEGIN TRANSACTION | Sync | Bắt đầu giao dịch |
| 35 | ExportInvoiceDAO | Database | INSERT INTO tblExportInvoice ... | Sync | Lưu phiếu xuất |
| 36 | ExportInvoiceDAO | Database | INSERT INTO tblExportInvoiceDetail ... (x3) | Sync | Lưu 3 chi tiết |
| 37 | ExportInvoiceDAO | Database | UPDATE tblItem SET stock=stock-50 WHERE itemId='IT012' | Sync | Trừ tồn kho IT012 |
| 38 | ExportInvoiceDAO | Database | UPDATE tblItem SET stock=stock-30 WHERE itemId='IT023' | Sync | Trừ tồn kho IT023 |
| 39 | ExportInvoiceDAO | Database | UPDATE tblItem SET stock=stock-100 WHERE itemId='IT034' | Sync | Trừ tồn kho IT034 |
| 40 | ExportInvoiceDAO | Database | COMMIT TRANSACTION | Sync | Cam kết giao dịch |
| 41 | ExportInvoiceDAO | ExportFrm | return true | Return | Thành công |
| 42 | ExportFrm | ExportFrm | printInvoice() | Self | In phiếu xuất |
| 43 | ExportFrm | Staff | showSuccess("Xuất hóa đơn thành công!") | Return | Thông báo thành công |
| 44 | Staff | ExportFrm | clickOK() | Sync | Staff nhấn OK |
| 45 | ExportFrm | ExportFrm | resetForm() | Self | Reset giao diện về trạng thái ban dau |

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

**Purpose:** Kiểm tra quy trình xuất hàng hóa thành công từ kho cho một đại lý với đầy đủ thông tin hợp lệ, xác nhận hóa đơn được tạo và tồn kho được cập nhật đúng.

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
| itemId | code | itemName | description | stockQuantity |
|--------|------|----------|-------------|---------------|
| IT012 | SP012 | Sua Vinamilk | Sua tuoi 1 lit | 500 |
| IT023 | SP023 | Banh Oreo | Banh quy 300g | 200 |
| IT034 | SP034 | Nuoc ngot Coca | Chai 1.5 lit | 300 |
| IT045 | SP045 | Mi goi Hao Hao | Thung 30 goi | 150 |

Bang `tblExportInvoice`:
| invoiceId | exportDate | totalAmount | agentId | userId |
|-----------|------------|-------------|---------|--------|
| (rong -- chua co phieu xuat nao) | | | | |

Bang `tblExportInvoiceDetail`:
| detailId | invoiceId | itemId | quantity | unitPrice | amount |
|----------|-----------|--------|----------|-----------|--------|
| (rong) | | | | | |

**Test Steps (Kịch bản >= 7 bước):**

| Bước | Hành động | Dữ liệu nhập | Kết quả mong đợi |
|------|-----------|---------------|------------------|
| 1 | Staff khởi động ứng dụng, nhập username `staff01`, password `abc123`, nhấn Đăng nhập | username: staff01, password: abc123 | Giao diện HomeFrm hiển thị với các chức năng: Quản lý nhập hàng, Quản lý xuất hàng, Báo cáo |
| 2 | Staff nhấn menu "Xuất hàng" | -- | Giao diện ExportFrm hiển thị với ô tìm kiếm đại lý, bảng đại lý rỗng, ô tìm kiếm hàng hóa disabled |
| 3 | Staff nhập "Minh Khang" vào ô tìm kiếm đại lý, nhấn nút Tìm kiếm | "Minh Khang" | Bảng danh sách đại lý hiển thị 1 dòng: AG005, Đại lý Minh Khang, 123 Lê Lợi, Q.1, TP.HCM, 0901234567 |
| 4 | Staff nhấn chọn dòng đại lý AG005 | Chọn AG005 | Thông tin đại lý hiển thị: Tên = "Đại lý Minh Khang", DC = "123 Lê Lợi, Q.1, TP.HCM", SĐT = "0901234567". Ô tìm kiếm hàng hóa được kích hoạt. |
| 5 | Staff nhập "Vinamilk" vào ô tìm kiếm hàng hóa, nhấn nút Tìm kiếm | "Vinamilk" | Bảng danh sách hàng hóa hiển thị 1 dòng: IT012, Sữa Vinamilk, Sữa tươi 1 lit, Tồn kho: 500 |
| 6 | Staff chọn IT012, nhập SL=50, Đơn giá=250000, nhấn nút Thêm vào danh sách | SL=50, Đơn giá=250000 | Bảng xuất có 1 dòng: IT012, Sữa Vinamilk, 50, 250,000, 12,500,000. Tổng tiền = "12,500,000 VND". Các ô nhập được xóa trắng. |
| 7 | Staff tìm "Oreo", chọn IT023, nhập SL=30, Đơn giá=180000, nhấn nút Thêm | SL=30, Đơn giá=180000 | Bảng xuất có 2 dòng. Dòng 2: IT023, Bánh Oreo, 30, 180,000, 5,400,000. Tổng tiền = "17,900,000 VND". |
| 8 | Staff tìm "Coca", chọn IT034, nhập SL=100, Đơn giá=120000, nhấn nút Thêm | SL=100, Đơn giá=120000 | Bảng xuất có 3 dòng. Dòng 3: IT034, Nước ngọt Coca, 100, 120,000, 12,000,000. Tổng tiền = "29,900,000 VND". |
| 9 | Kiểm tra DB trước khi Submit | -- | tblExportInvoice vẫn rỗng (chưa có hóa đơn mới). tblExportInvoiceDetail vẫn rỗng. Tồn kho IT012=500, IT023=200, IT034=300 (chưa thay đổi). |
| 10 | Staff nhấn nút Xuất hóa đơn | -- | Hộp thoại xác nhận: "Xuất hóa đơn cho Đại lý Minh Khang với tổng tiền 29,900,000 VND?" |
| 11 | Staff nhấn "Xác nhận" | -- | Thông báo "Xuất hóa đơn thành công!". Phiếu xuất hóa đơn được in ra. |
| 12 | Staff nhấn OK | -- | Form reset về trạng thái ban đầu. Bảng danh sách xuất trống. Tổng tiền = "0 VND". |

**Database After (Du lieu sau khi chay test):**

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

Bang `tblItem` (cập nhật tồn kho — chỉ update cột stockQuantity):
| itemId | stockQuantity |
|--------|---------------|
| IT012 | 450 (was 500, minus 50) |
| IT023 | 170 (was 200, minus 30) |
| IT034 | 200 (was 300, minus 100) |

**Bang khong thay doi:** tblUser, tblSubAgent

**Pass Criteria:**
- tblExportInvoice co dung 1 ban ghi moi voi ma PX2026001.
- tblExportInvoiceDetail co dung 3 ban ghi moi lien ket voi PX2026001.
- Tong tien trong tblExportInvoice = 29,900,000 = tong cac amount trong detail.
- tblItem: stockQuantity IT012 giam tu 500 xuong 450.
- tblItem: stockQuantity IT023 giam tu 200 xuong 170.
- tblItem: stockQuantity IT034 giam tu 300 xuong 200.
- tblItem: stockQuantity IT045 khong doi (150).
- Hien thi thong bao "Xuat hoa don thanh cong!".
- Form reset ve trang thai ban dau.
