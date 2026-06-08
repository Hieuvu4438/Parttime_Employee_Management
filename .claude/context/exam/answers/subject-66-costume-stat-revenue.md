# Subject No. 66 -- Costume Rental -- Module "Revenue Statistics"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario -- Thống kê doanh thu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn menu **Revenue Statistics** |
| 2 | Hệ thống hiển thị combobox chọn kiểu thống kê: Tháng, Quý, Năm |
| 3 | Staff chọn **Tháng** trong combobox |
| 4 | Hệ thống hiển thị bảng thống kê doanh thu 12 tháng gần nhất: |
|    | -- 07/2026: 8,500,000đ |
|    | -- 06/2026: 7,200,000đ |
|    | -- 05/2026: 6,800,000đ |
|    | -- Sắp xếp theo thời gian từ gần nhất đến xa nhất |
| 5 | Staff click vào dòng **07/2026** |
| 6 | Hệ thống hiển thị chi tiết hóa đơn trong tháng 07/2026: |
|    | -- RS001, Nguyen Thi A, 01/07, 3 trang phục, 1,100,000đ |
|    | -- RS002, Tran Thi B, 05/07, 2 trang phục, 800,000đ |

---

## Câu 2: Entity Class Diagram

### Bảng thực thể

| Entity | Thuộc tính |
|--------|-----------|
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, returnDate, rentalDays, rentalAmount, status, fine |
| **Customer** | customerId (PK), customerName, phone, email, address |
| **Payment** | paymentId (PK), rentalSlipId (FK), paymentDate, totalAmount, deposit, amountPaid, note |
| **User** | userId (PK), username, password, fullName, role |

### Relationships

| Relationship | Mô tả |
|-------------|-------|
| RentalSlip -- Payment | 1 RentalSlip có 0 hoặc 1 Payment (0..1) |
| Customer -- RentalSlip | 1 Customer có nhiều RentalSlip (1..*) |
| RentalSlip -- RentalSlipDetail | 1 RentalSlip có nhiều RentalSlipDetail (1..*) |
| Costume -- RentalSlipDetail | 1 Costume xuất hiện trong nhiều RentalSlipDetail (1..*) |
| User -- RentalSlip | 1 User tạo nhiều RentalSlip (1..*) |

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `CostumeStatRevenueFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inStatType | JComboBox | Combobox chọn kiểu thống kê (Tháng / Quý / Năm) |
| outStatTable | JTable | Bảng hiển thị thống kê doanh thu theo kỳ (tên kỳ, tổng doanh thu) |
| outDetailTable | JTable | Bảng hiển thị chi tiết hóa đơn khi click vào 1 dòng trong bảng thống kê |

### DAO Classes

**PaymentDAO**

| Phương thức | Mô tả |
|------------|-------|
| getRevenueByPeriod(String periodType) : List\<RevenueStat\> | Lấy thống kê doanh thu theo kỳ (tháng/quý/năm): tên kỳ, tổng doanh thu, sắp xếp theo thời gian từ gần nhất đến xa nhất |

**RentalSlipDAO**

| Phương thức | Mô tả |
|------------|-------|
| getSlipsByMonth(int year, int month) : List\<RentalSlip\> | Lấy danh sách hóa đơn trong 1 tháng cụ thể: id, tên khách hàng, ngày mượn, tổng số trang phục, tổng tiền |

---

## Câu 4: Sequence Diagram

### Lifelines

- :Staff
- :CostumeStatRevenueFrm
- :PaymentDAO
- :RentalSlipDAO

### Message Flow

```
:Staff -> :CostumeStatRevenueFrm : Chọn menu Revenue Statistics
:CostumeStatRevenueFrm --> :Staff : Hiển thị combobox chọn kiểu thống kê

:Staff -> :CostumeStatRevenueFrm : Chọn "Tháng" trong combobox
:CostumeStatRevenueFrm -> :PaymentDAO : getRevenueByPeriod("month")
:PaymentDAO --> :CostumeStatRevenueFrm : List<RevenueStat>
:CostumeStatRevenueFrm --> :Staff : Hiển thị bảng thống kê doanh thu 12 tháng

:Staff -> :CostumeStatRevenueFrm : Click vào dòng 07/2026
:CostumeStatRevenueFrm -> :RentalSlipDAO : getSlipsByMonth(2026, 7)
:RentalSlipDAO --> :CostumeStatRevenueFrm : List<RentalSlip>
:CostumeStatRevenueFrm --> :Staff : Hiển thị chi tiết hóa đơn tháng 07/2026
```

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê doanh thu theo tháng và xem chi tiết hóa đơn

#### Database BEFORE

**tblPayment**

| paymentId | rentalSlipId | paymentDate | totalAmount |
|----------|-------------|------------|-----------|
| P001 | RS001 | 2026-07-04 | 1,100,000 |
| P002 | RS002 | 2026-07-08 | 800,000 |
| P003 | RS003 | 2026-06-15 | 7,200,000 |
| P004 | RS004 | 2026-05-20 | 6,800,000 |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | totalAmount |
|-------------|-----------|--------|-----------|-------------|
| RS001 | C001 | U001 | 2026-07-01 | 1,100,000 |
| RS002 | C002 | U001 | 2026-07-05 | 800,000 |
| RS003 | C003 | U002 | 2026-06-10 | 7,200,000 |
| RS004 | C004 | U002 | 2026-05-15 | 6,800,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity |
|---------|-------------|----------|---------|
| D001 | RS001 | CS001 | 2 |
| D002 | RS001 | CS002 | 1 |
| D003 | RS002 | CS003 | 2 |
| D004 | RS003 | CS001 | 3 |
| D005 | RS004 | CS002 | 2 |

**tblCustomer**

| customerId | customerName |
|-----------|-------------|
| C001 | Nguyen Thi A |
| C002 | Tran Thi B |
| C003 | Le Thi C |
| C004 | Pham Thi D |

#### Steps

| Bước | Hành động | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Staff chọn menu Revenue Statistics | Giao diện hiển thị combobox chọn kiểu thống kê |
| 2 | Staff chọn "Tháng" trong combobox | Bảng thống kê hiển thị 3 tháng: 07/2026 (1,900,000đ), 06/2026 (7,200,000đ), 05/2026 (6,800,000đ). Sắp xếp từ gần nhất đến xa nhất |
| 3 | Staff click vào dòng 07/2026 | Bảng chi tiết hiển thị 2 hóa đơn: RS001 - Nguyen Thi A - 01/07 - 3 trang phục - 1,100,000đ; RS002 - Tran Thi B - 05/07 - 2 trang phục - 800,000đ |

#### Database AFTER

Database không thay đổi vì chức năng này chỉ đọc dữ liệu (read-only), không có thao tác ghi.
