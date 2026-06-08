# Subject No. 65 — Costume Rental — Module "Statistics of costumes"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Thống kê trang phục

### Mô tả ngôn ngữ tự nhiên

Module "Statistics of costumes" cho phép nhân viên (staff) xem thống kê về tần suất cho thuê và doanh thu của từng loại trang phục trong một khoảng thời gian. Staff chọn menu thống kê trang phục, nhập ngày bắt đầu và ngày kết thúc, nhấn xem thống kê. Hệ thống hiển thị bảng danh sách các trang phục đã được cho thuê trong khoảng thời gian đó, bao gồm: mã trang phục, tên, model, thể loại, tổng số lần thuê, tổng tiền thu. Bảng được sắp xếp giảm dần theo tổng số lần thuê, nếu bằng nhau thì sắp xếp giảm dần theo tổng tiền thu. Staff click vào một dòng trang phục, hệ thống hiển thị chi tiết các hóa đơn liên quan đến trang phục đó: id hóa đơn, tên người mượn, ngày/giờ mượn, ngày/giờ trả, tổng tiền.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống (username: `staff01`, password: `******`) |
| 2 | Giao diện Home hiển thị. Staff chọn menu **Statistics of costumes** |
| 3 | Hệ thống hiển thị giao diện thống kê: ô nhập ngày bắt đầu (`txtStartDate`), ô nhập ngày kết thúc (`txtEndDate`), nút **Xem thống kê** |
| 4 | Staff nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026 |
| 5 | Staff nhấn nút **Xem thống kê** |
| 6 | Hệ thống truy vấn database, tổng hợp dữ liệu cho thuê trong khoảng thời gian 01/01/2026 - 31/12/2026 |
| 7 | Hệ thống hiển thị bảng thống kê danh sách trang phục được thuê: |
|    | -- CS001, Vest nam đen, Slim fit, Nam: 25 lần thuê, 7,500,000đ |
|    | -- CS002, Váy dạ hội đỏ, Size M, Nữ: 20 lần thuê, 9,000,000đ |
|    | -- CS003, Áo dài trắng, Size S, Nữ: 15 lần thuê, 3,600,000đ |
|    | -- CS004, Vest nam xám, Regular, Nam: 8 lần thuê, 2,400,000đ |
|    | -- Sắp xếp giảm dần theo tổng số lần thuê (25 > 20 > 15 > 8) |
| 8 | Staff click vào dòng **CS001 - Vest nam đen** |
| 9 | Hệ thống hiển thị bảng chi tiết hóa đơn của trang phục CS001 trong khoảng thời gian: |
|    | -- RS001, Nguyen Thi A, 01/07 10:00, 04/07 10:00, 600,000đ |
|    | -- RS005, Tran Thi B, 10/07 14:00, 13/07 14:00, 600,000đ |
|    | -- RS012, Le Thi C, 20/08 09:00, 23/08 09:00, 600,000đ |
|    | -- ... (25 dòng hóa đơn) |
| 10 | Staff click vào dòng trang phục khác (ví dụ CS002) để xem chi tiết tương ứng |

### Kịch bản ngoại lệ

| Ngoại lệ | Mô tả | Xử lý |
|----------|-------|-------|
| EL1 | Ngày bắt đầu > ngày kết thúc | Hiển thị lỗi "Ngay bat dau phai truoc ngay ket thuc" |
| EL2 | Khoảng thời gian không có trang phục nào được thuê | Hiển thị bảng thống kê trống, thông báo "Khong co du lieu thong ke" |
| EL3 | Staff nhập ngày không hợp lệ (ví dụ 30/02) | Hiển thị lỗi "Dinh dang ngay khong hop le" |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê trang phục. Cửa hàng có nhiều loại trang phục, mỗi loại có thể có nhiều số lượng. Khách hàng có thể thuê nhiều lần, mỗi lần thuê nhiều loại trang phục khác nhau. Mỗi lần thuê tạo một phiếu thuê (RentalSlip) chứa nhiều chi tiết (RentalSlipDetail), mỗi chi tiết tương ứng một loại trang phục. Nhân viên thao tác trên hệ thống được lưu trong bảng User.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Costume (trang phục) | Entity class | Đối tượng chính cần thống kê |
| RentalSlip (phiếu thuê) | Entity class | Chứng từ mỗi lần thuê |
| RentalSlipDetail (chi tiết phiếu thuê) | Entity class | Chi tiết từng loại trang phục trong phiếu |
| Customer (khách hàng) | Entity class | Người thuê trang phục |
| User (nhân viên) | Entity class | Người thao tác hệ thống |
| costumeName, model, genre | Thuộc tính của Costume | Mô tả đặc điểm trang phục |
| rentalPricePerDay, status | Thuộc tính của Costume | Giá thuê và trạng thái |
| rentalDate, deposit, totalAmount | Thuộc tính của RentalSlip | Thông tin phiếu thuê |
| quantity, rentalDate, returnDate, rentalDays | Thuộc tính của RentalSlipDetail | Chi tiết thuê |
| rentalAmount, status, fine | Thuộc tính của RentalSlipDetail | Tiền thuê và trạng thái trả |

### Bảng thuộc tính entity

| Entity | Thuộc tính |
|--------|-----------|
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, returnDate, rentalDays, rentalAmount, status, fine |
| **Customer** | customerId (PK), customerName, phone, email, address |
| **User** | userId (PK), username, password, fullName, role |

### Class Diagram (ASCII)

```
+------------------+       +-------------------+       +------------------+
|    Costume       |       |   RentalSlip      |       |      User        |
+------------------+       +-------------------+       +------------------+
| -costumeId: int  |       | -rentalSlipId: int|       | -userId: int     |
| -costumeName: Str|  1   | -customerId: FK   |  1   | -username: Str   |
| -model: Str      |------| -userId: FK       |------| -password: Str   |
| -genre: Str      |   *  | -rentalDate: Date |   *  | -fullName: Str   |
| -rentalPricePD: dbl      | -deposit: double  |       | -role: Str       |
| -status: Str     |       | -totalAmount: dbl |       +------------------+
+------------------+       +-------------------+
        | 1                         | 1
        |                           |
        | *                         | *
        v                           v
+-----------------------+   +-------------------+
|  RentalSlipDetail     |   |    Customer       |
+-----------------------+   +-------------------+
| -detailId: int        |   | -customerId: int  |
| -rentalSlipId: FK     |   | -customerName: Str|
| -costumeId: FK        |   | -phone: Str       |
| -quantity: int        |   | -email: Str       |
| -rentalDate: Date     |   | -address: Str     |
| -returnDate: Date     |   +-------------------+
| -rentalDays: int      |
| -rentalAmount: double |
| -status: Str          |
| -fine: double         |
+-----------------------+
```

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu | Giải thích |
|-----------|----------|------|------------|
| Costume | RentalSlipDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu thuê có nhiều chi tiết trang phục |
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu thuê |
| User | RentalSlip | 1 : N | Một nhân viên tạo nhiều phiếu thuê |

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `StatCostumesFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inStartDate | JTextField | Ô nhập ngày bắt đầu thống kê (dd/MM/yyyy) |
| inEndDate | JTextField | Ô nhập ngày kết thúc thống kê (dd/MM/yyyy) |
| subView | JButton | Nút "Xem thống kê" để thực hiện truy vấn |
| outCostumeStatTable | JTable | Bảng thống kê trang phục. Cột: costumeId, costumeName, model, genre, totalLoans, totalRevenue. Sắp xếp giảm dần theo totalLoans, sau đó totalRevenue |
| outDetailInvoiceTable | JTable | Bảng chi tiết hóa đơn khi click vào 1 dòng trang phục. Cột: rentalSlipId, borrowerName, borrowedDateTime, paymentDateTime, totalAmount |

### DAO Classes

**CostumeDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getCostumeStatistics(Date startDate, Date endDate) | List\<CostumeStatDTO\> | Truy vấn tổng hợp: đếm số lần thuê (COUNT detailId), tổng tiền thu (SUM rentalAmount) cho mỗi costumeId trong khoảng thời gian. GROUP BY costumeId, ORDER BY totalLoans DESC, totalRevenue DESC. Join tblCostume để lấy tên, model, genre |

**RentalSlipDetailDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getInvoiceDetailsByCostume(int costumeId, Date startDate, Date endDate) | List\<InvoiceDetailDTO\> | Truy vấn chi tiết hóa đơn của 1 trang phục: rentalSlipId, customerName (JOIN tblCustomer), rentalDate (ngày mượn), returnDate (ngày trả), rentalAmount. Filter theo costumeId và khoảng thời gian |

### Database Schema

**tblCostume:** costumeId (PK), costumeName, model, genre, rentalPricePerDay, status

**tblRentalSlip:** rentalSlipId (PK), customerId (FK→tblCustomer), userId (FK→tblUser), rentalDate, deposit, totalAmount

**tblRentalSlipDetail:** detailId (PK), rentalSlipId (FK→tblRentalSlip), costumeId (FK→tblCostume), quantity, rentalDate, returnDate, rentalDays, rentalAmount, status, fine

**tblCustomer:** customerId (PK), customerName, phone, email, address

**tblUser:** userId (PK), username, password, fullName, role

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package `view.statcostume` chứa `StatCostumesFrm`
2. Tạo package `model` chứa: `Costume`, `RentalSlip`, `RentalSlipDetail`, `Customer`, `User`
3. Tạo package `dao` chứa: `CostumeDAO`, `RentalSlipDetailDAO`
4. Vẽ association: StatCostumesFrm → CostumeDAO, RentalSlipDetailDAO
5. CostumeDAO có method `getCostumeStatistics()` trả về `List<CostumeStatDTO>` (DTO nội bộ cho thống kê)
6. RentalSlipDetailDAO có method `getInvoiceDetailsByCostume()` trả về `List<InvoiceDetailDTO>`

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo các lifeline: `:Staff`, `:StatCostumesFrm`, `:CostumeDAO`, `:RentalSlipDetailDAO`
2. Vẽ message flow theo bảng bên dưới
3. Sử dụng `return` message cho kết quả trả về
4. Sử dụng `alt` fragment cho trường hợp có dữ liệu / không có dữ liệu

### Bảng chi tiết message flow

| STT | Từ | Đến | Message | Loại | Ghi chú |
|-----|-----|------|---------|------|---------|
| 1 | Staff | StatCostumesFrm | actionPerformed(e) | sync | Staff chọn menu Statistics of costumes |
| 2 | StatCostumesFrm | Staff | show() | return | Hiển thị giao diện thống kê: ô ngày bắt đầu, ngày kết thúc, nút Xem |
| 3 | Staff | StatCostumesFrm | txtStartDate.setText("01/01/2026") | sync | Nhập ngày bắt đầu |
| 4 | Staff | StatCostumesFrm | txtEndDate.setText("31/12/2026") | sync | Nhập ngày kết thúc |
| 5 | Staff | StatCostumesFrm | btnView.doClick() | sync | Nhấn nút Xem thống kê |
| 6 | StatCostumesFrm | CostumeDAO | getCostumeStatistics(01/01/2026, 31/12/2026) | sync | Truy vấn thống kê |
| 7 | CostumeDAO | CostumeDAO | executeSQL(...) | self | SELECT c.costumeId, c.costumeName, c.model, c.genre, COUNT(d.detailId) AS totalLoans, SUM(d.rentalAmount) AS totalRevenue FROM tblCostume c JOIN tblRentalSlipDetail d ON c.costumeId = d.costumeId JOIN tblRentalSlip r ON d.rentalSlipId = r.rentalSlipId WHERE r.rentalDate BETWEEN startDate AND endDate GROUP BY c.costumeId ORDER BY totalLoans DESC, totalRevenue DESC |
| 8 | CostumeDAO | StatCostumesFrm | return List\<CostumeStatDTO\> | return | 4 dòng: CS001 (25 lần, 7.5M), CS002 (20 lần, 9M), CS003 (15 lần, 3.6M), CS004 (8 lần, 2.4M) |
| 9 | StatCostumesFrm | Staff | tblCostumeStat.setData(list) | return | Hiển thị bảng thống kê trang phục |
| 10 | Staff | StatCostumesFrm | tblCostumeStat.selectRow(0) | sync | Click vào dòng CS001 (Vest nam đen) |
| 11 | StatCostumesFrm | RentalSlipDetailDAO | getInvoiceDetailsByCostume(CS001, 01/01/2026, 31/12/2026) | sync | Truy vấn chi tiết hóa đơn |
| 12 | RentalSlipDetailDAO | RentalSlipDetailDAO | executeSQL(...) | self | SELECT r.rentalSlipId, c.customerName, d.rentalDate, d.returnDate, d.rentalAmount FROM tblRentalSlipDetail d JOIN tblRentalSlip r ON d.rentalSlipId = r.rentalSlipId JOIN tblCustomer c ON r.customerId = c.customerId WHERE d.costumeId = CS001 AND r.rentalDate BETWEEN startDate AND endDate |
| 13 | RentalSlipDetailDAO | StatCostumesFrm | return List\<InvoiceDetailDTO\> | return | Danh sách 25 hóa đơn |
| 14 | StatCostumesFrm | Staff | tblDetailInvoice.setData(list) | return | Hiển thị bảng chi tiết hóa đơn |
| 15 | Staff | StatCostumesFrm | tblCostumeStat.selectRow(1) | sync | Click vào dòng CS002 (Váy dạ hội đỏ) |
| 16 | StatCostumesFrm | RentalSlipDetailDAO | getInvoiceDetailsByCostume(CS002, 01/01/2026, 31/12/2026) | sync | Truy vấn chi tiết hóa đơn CS002 |
| 17 | RentalSlipDetailDAO | StatCostumesFrm | return List\<InvoiceDetailDTO\> | return | Danh sách 20 hóa đơn |
| 18 | StatCostumesFrm | Staff | tblDetailInvoice.setData(list) | return | Hiển thị bảng chi tiết hóa đơn CS002 |

### ASCII Sequence Diagram

```
Staff    StatCostumesFrm    CostumeDAO    RentalSlipDetailDAO
  |              |                |                |
  |--selectMenu->|                |                |
  |<--showFrm----|                |                |
  |              |                |                |
  |--enterDates->|                |                |
  |--clickView-->|                |                |
  |              |--getCostumeStatistics-->|       |
  |              |                |--executeSQL    |
  |              |                |--pack result   |
  |              |<--List<CostumeStatDTO>--|       |
  |<--showTable--|                |                |
  |              |                |                |
  |--clickCS001->|                |                |
  |              |--getInvoiceDetailsByCostume---->|
  |              |                |       |--executeSQL
  |              |                |       |--pack result
  |              |<--List<InvoiceDetailDTO>--------|
  |<--showDetail-|                |                |
```

---

## Câu 5: Blackbox Testcase

### Bảng testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Statistics of costumes | TC01: Thống kê trang phục theo khoảng thời gian và xem chi tiết |
| 2 | Statistics of costumes | TC02: Khoảng thời gian không có dữ liệu thuê |
| 3 | Statistics of costumes | TC03: Ngày bắt đầu > ngày kết thúc (lỗi nhập liệu) |

---

### TC01: Thống kê trang phục theo khoảng thời gian và xem chi tiết

**Purpose:** Kiểm tra chức năng thống kê trang phục cho thuê trong khoảng thời gian, hiển thị đúng thứ tự sắp xếp, và xem chi tiết hóa đơn khi click vào một dòng trang phục.

#### Database BEFORE

**tblCostume**

| costumeId | costumeName | model | genre | rentalPricePerDay | status |
|----------|------------|-------|-------|------------------|--------|
| CS001 | Vest nam đen | Slim fit | Nam | 100,000 | Available |
| CS002 | Váy dạ hội đỏ | Size M | Nữ | 150,000 | Available |
| CS003 | Áo dài trắng | Size S | Nữ | 80,000 | Available |
| CS004 | Vest nam xám | Regular | Nam | 100,000 | Available |

**tblCustomer**

| customerId | customerName | phone | email | address |
|-----------|-------------|-------|-------|---------|
| C001 | Nguyen Thi A | 0901234567 | nta@gmail.com | 123 Le Loi, HCM |
| C002 | Tran Thi B | 0912345678 | ttb@gmail.com | 456 Nguyen Hue, HCM |
| C003 | Le Thi C | 0923456789 | ltc@gmail.com | 789 Vo Van Tan, HCM |

**tblUser**

| userId | username | password | fullName | role |
|--------|---------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | staff |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | deposit | totalAmount |
|-------------|-----------|--------|-----------|---------|-------------|
| RS001 | C001 | U001 | 2026-07-01 | 350,000 | 1,050,000 |
| RS002 | C002 | U001 | 2026-07-10 | 400,000 | 900,000 |
| RS003 | C003 | U001 | 2026-08-05 | 200,000 | 480,000 |
| RS004 | C001 | U001 | 2026-08-20 | 300,000 | 720,000 |
| RS005 | C002 | U001 | 2026-06-15 | 250,000 | 600,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | returnDate | rentalDays | rentalAmount | status | fine |
|---------|-------------|----------|---------|-----------|-----------|-----------|-------------|--------|-----|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 2026-07-04 | 3 | 600,000 | Da tra | 0 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 2026-07-04 | 3 | 450,000 | Da tra | 0 |
| D003 | RS002 | CS001 | 2 | 2026-07-10 | 2026-07-13 | 3 | 600,000 | Da tra | 0 |
| D004 | RS002 | CS003 | 1 | 2026-07-10 | 2026-07-13 | 3 | 240,000 | Da tra | 0 |
| D005 | RS003 | CS003 | 2 | 2026-08-05 | 2026-08-08 | 3 | 480,000 | Da tra | 0 |
| D006 | RS004 | CS001 | 2 | 2026-08-20 | 2026-08-23 | 3 | 600,000 | Da tra | 0 |
| D007 | RS004 | CS002 | 1 | 2026-08-20 | 2026-08-23 | 3 | 450,000 | Dang thue | 0 |
| D008 | RS005 | CS001 | 3 | 2026-06-15 | 2026-06-18 | 3 | 900,000 | Da tra | 0 |
| D009 | RS005 | CS002 | 1 | 2026-06-15 | 2026-06-18 | 3 | 450,000 | Da tra | 0 |
| D010 | RS005 | CS004 | 2 | 2026-06-15 | 2026-06-18 | 3 | 600,000 | Da tra | 0 |

#### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công (staff01/123456) | Giao diện Home hiển thị |
| 2 | Staff chọn menu "Statistics of costumes" | Giao diện thống kê hiển thị: ô ngày bắt đầu, ô ngày kết thúc, nút Xem thống kê |
| 3 | Staff nhập ngày bắt đầu: 01/01/2026, ngày kết thức: 31/12/2026 | Các ô hiển thị giá trị đã nhập |
| 4 | Staff nhấn nút "Xem thống kê" | Bảng thống kê hiển thị 4 dòng (sắp xếp giảm dần theo tổng lần thuê): CS001 (4 lần, 2,700,000đ), CS002 (3 lần, 1,350,000đ), CS003 (2 lần, 720,000đ), CS004 (1 lần, 600,000đ) |
| 5 | Staff click vào dòng CS001 (Vest nam đen) | Bảng chi tiết hóa đơn hiển thị 4 dòng: RS001 - Nguyen Thi A - 01/07 10:00 - 04/07 10:00 - 600,000đ; RS002 - Tran Thi B - 10/07 14:00 - 13/07 14:00 - 600,000đ; RS004 - Nguyen Thi A - 20/08 09:00 - 23/08 09:00 - 600,000đ; RS005 - Tran Thi B - 15/06 14:00 - 18/06 14:00 - 900,000đ |
| 6 | Staff click vào dòng CS002 (Váy dạ hội đỏ) | Bảng chi tiết hóa đơn cập nhật hiển thị 3 dòng: RS001 - Nguyen Thi A - 01/07 - 04/07 - 450,000đ; RS004 - Nguyen Thi A - 20/08 - 23/08 - 450,000đ; RS005 - Tran Thi B - 15/06 - 18/06 - 450,000đ |

#### Database AFTER

Database không thay đổi vì chức năng này chỉ đọc dữ liệu (read-only), không có thao tác ghi.

---
