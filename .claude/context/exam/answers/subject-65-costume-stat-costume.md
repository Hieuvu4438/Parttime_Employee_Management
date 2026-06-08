# Subject No. 65 — Costume Rental — Module "Statistics of costumes"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Thống kê trang phục

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn menu **Statistics of costumes** |
| 2 | Hệ thống hiển thị giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút **Xem thống kê** |
| 3 | Staff nhập ngày bắt đầu: 01/01/2026, ngày kết thúc: 31/12/2026 |
| 4 | Staff nhấn nút **Xem thống kê** |
| 5 | Hệ thống hiển thị bảng thống kê danh sách trang phục được thuê trong khoảng thời gian: |
|    | -- CS001, Vest nam đen, Slim fit, Nam: 25 lần thuê, 5,000,000đ |
|    | -- CS002, Váy dạ hội đỏ, Size M, Nữ: 20 lần thuê, 6,000,000đ |
|    | -- CS003, Áo dài trắng, Size S, Nữ: 15 lần thuê, 3,000,000đ |
|    | -- Sắp xếp theo tổng số lần thuê giảm dần, sau đó theo tổng tiền thu giảm dần |
| 6 | Staff click vào dòng **Vest nam đen** (CS001) |
| 7 | Hệ thống hiển thị chi tiết hóa đơn của trang phục CS001: |
|    | -- RS001, Nguyen Thi A, 01/07 10:00, 04/07 10:00, 600,000đ |
|    | -- RS005, Tran Thi B, 10/07 14:00, 13/07 14:00, 600,000đ |

---

## Câu 2: Entity Class Diagram

### Bảng thực thể

| Entity | Thuộc tính |
|--------|-----------|
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, returnDate, rentalDays, rentalAmount, status, fine |
| **Customer** | customerId (PK), customerName, phone, email, address |
| **User** | userId (PK), username, password, fullName, role |

### Relationships

| Relationship | Mô tả |
|-------------|-------|
| Costume -- RentalSlipDetail | 1 Costume xuất hiện trong nhiều RentalSlipDetail (1..*) |
| RentalSlip -- RentalSlipDetail | 1 RentalSlip có nhiều RentalSlipDetail (1..*) |
| Customer -- RentalSlip | 1 Customer có nhiều RentalSlip (1..*) |
| User -- RentalSlip | 1 User tạo nhiều RentalSlip (1..*) |

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `StatCostumesFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inStartDate | JTextField | Ô nhập ngày bắt đầu thống kê |
| inEndDate | JTextField | Ô nhập ngày kết thúc thống kê |
| subView | JButton | Nút Xem thống kê |
| outCostumeStatTable | JTable | Bảng hiển thị thống kê trang phục (mã, tên, model, thể loại, số lần thuê, tổng tiền) |
| outDetailInvoiceTable | JTable | Bảng hiển thị chi tiết hóa đơn khi click vào 1 dòng trang phục |

### DAO Classes

**CostumeDAO**

| Phương thức | Mô tả |
|------------|-------|
| getCostumeStatistics(Date startDate, Date endDate) : List\<CostumeStat\> | Lấy thống kê trang phục theo khoảng thời gian (mã, tên, model, thể loại, tổng số lần thuê, tổng tiền thu), sắp xếp theo tổng lần thuê giảm dần, sau đó tổng tiền giảm dần |

**RentalSlipDAO**

| Phương thức | Mô tả |
|------------|-------|
| getSlipsByCostume(int costumeId, Date startDate, Date endDate) : List\<RentalSlipDetail\> | Lấy chi tiết hóa đơn của 1 trang phục trong khoảng thời gian (id phiếu, tên người mượn, ngày mượn, ngày trả, tổng tiền) |

---

## Câu 4: Sequence Diagram

### Lifelines

- :Staff
- :StatCostumesFrm
- :CostumeDAO
- :RentalSlipDAO

### Message Flow

```
:Staff -> :StatCostumesFrm : Chọn menu Statistics of costumes
:StatCostumesFrm --> :Staff : Hiển thị giao diện (ô nhập ngày bắt đầu, ngày kết thúc)

:Staff -> :StatCostumesFrm : Nhập ngày 01/01/2026 - 31/12/2026, nhấn Xem thống kê
:StatCostumesFrm -> :CostumeDAO : getCostumeStatistics(01/01/2026, 31/12/2026)
:CostumeDAO --> :StatCostumesFrm : List<CostumeStat>
:StatCostumesFrm --> :Staff : Hiển thị bảng thống kê trang phục

:Staff -> :StatCostumesFrm : Click vào dòng "Vest nam đen" (CS001)
:StatCostumesFrm -> :RentalSlipDAO : getSlipsByCostume(CS001, 01/01/2026, 31/12/2026)
:RentalSlipDAO --> :StatCostumesFrm : List<RentalSlipDetail>
:StatCostumesFrm --> :Staff : Hiển thị chi tiết hóa đơn của Vest nam đen
```

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê trang phục theo khoảng thời gian và xem chi tiết

#### Database BEFORE

**tblCostume**

| costumeId | costumeName | model | genre |
|----------|------------|-------|-------|
| CS001 | Vest nam đen | Slim fit | Nam |
| CS002 | Váy dạ hội đỏ | Size M | Nữ |
| CS003 | Áo dài trắng | Size S | Nữ |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalAmount |
|-------------|-----------|--------|-----------|-------------|
| RS001 | C001 | U001 | 2026-07-01 | 1,100,000 |
| RS005 | C002 | U001 | 2026-07-10 | 800,000 |
| RS010 | C003 | U002 | 2026-08-05 | 600,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | returnDate | rentalDays | rentalAmount |
|---------|-------------|----------|---------|-----------|-----------|-----------|-------------|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 2026-07-04 | 3 | 600,000 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 2026-07-04 | 3 | 450,000 |
| D003 | RS005 | CS001 | 2 | 2026-07-10 | 2026-07-13 | 3 | 600,000 |
| D004 | RS010 | CS003 | 1 | 2026-08-05 | 2026-08-08 | 3 | 300,000 |

**tblCustomer**

| customerId | customerName |
|-----------|-------------|
| C001 | Nguyen Thi A |
| C002 | Tran Thi B |
| C003 | Le Thi C |

#### Steps

| Bước | Hành động | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Staff nhập ngày bắt đầu 01/01/2026, ngày kết thúc 31/12/2026 | Các ô nhập hiển thị giá trị |
| 2 | Staff nhấn nút Xem thống kê | Bảng hiển thị 3 trang phục: CS001 (2 lần, 1,200,000đ), CS002 (1 lần, 450,000đ), CS003 (1 lần, 300,000đ). Sắp xếp: CS001 > CS002 = CS003 (ưu tiên số lần thuê giảm dần) |
| 3 | Staff click vào dòng CS001 (Vest nam đen) | Bảng chi tiết hiển thị 2 hóa đơn: RS001 - Nguyen Thi A - 01/07 10:00 - 04/07 10:00 - 600,000đ; RS005 - Tran Thi B - 10/07 14:00 - 13/07 14:00 - 600,000đ |

#### Database AFTER

Database không thay đổi vì chức năng này chỉ đọc dữ liệu (read-only), không có thao tác ghi.
