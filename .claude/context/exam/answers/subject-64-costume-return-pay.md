# Subject No. 64 — Costume Rental — Module "Customer returns and pays"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Khách hàng trả trang phục và thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | Customer mang trang phục đến trả |
| 2 | Staff đăng nhập hệ thống, chọn menu **Customer returns and pays** |
| 3 | Hệ thống hiển thị giao diện: ô nhập tên khách hàng + nút **Search** |
| 4 | Staff nhập "Nguyen Thi A" vào ô tên khách hàng, nhấn **Search** |
| 5 | Hệ thống hiển thị danh sách khách hàng có tên chứa "Nguyen Thi A" |
| 6 | Staff click chọn khách hàng **Nguyen Thi A** |
| 7 | Hệ thống hiển thị danh sách trang phục đang mượn của khách hàng: |
|    | -- Vest nam đen x2, thuê ngày 01/07, giá 100,000đ/ngày, đã thuê 3 ngày, tổng 600,000đ [checkbox] |
|    | -- Váy dạ hội đỏ x1, thuê ngày 01/07, giá 150,000đ/ngày, đã thuê 3 ngày, tổng 450,000đ [checkbox] |
| 8 | Staff tick chọn **Vest nam đen** và **Váy dạ hội đỏ** |
| 9 | Staff nhập trạng thái Vest: "Bình thường", phạt: 0đ |
| 10 | Staff nhập trạng thái Váy: "Bẩn", phạt: 50,000đ |
| 11 | Staff nhấn nút **Payment** |
| 12 | Hệ thống hiển thị hóa đơn: Vest nam đen x2: 600,000đ (phạt 0đ), Váy dạ hội đỏ x1: 450,000đ (phạt 50,000đ), Tổng: 1,100,000đ, Tiền cọc: 350,000đ, Khách phải trả: 750,000đ |
| 13 | Staff nhấn **Confirm** |
| 14 | Hệ thống cập nhật database: trạng thái chi tiết phiếu thuê = đã trả, tạo bản ghi Payment mới, cập nhật tiền cọc (nếu có) |

---

## Câu 2: Entity Class Diagram

### Bảng thực thể

| Entity | Thuộc tính |
|--------|-----------|
| **Customer** | customerId (PK), customerName, phone, email, address |
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, rentalDays, rentalAmount, status, fine |
| **Payment** | paymentId (PK), rentalSlipId (FK), paymentDate, totalAmount, deposit, amountPaid, note |
| **User** | userId (PK), username, password, fullName, role |

### Relationships

| Relationship | Mô tả |
|-------------|-------|
| Customer -- RentalSlip | 1 Customer có nhiều RentalSlip (1..*) |
| RentalSlip -- RentalSlipDetail | 1 RentalSlip có nhiều RentalSlipDetail (1..*) |
| Costume -- RentalSlipDetail | 1 Costume xuất hiện trong nhiều RentalSlipDetail (1..*) |
| RentalSlip -- Payment | 1 RentalSlip có 0 hoặc 1 Payment (0..1) |
| User -- RentalSlip | 1 User tạo nhiều RentalSlip (1..*) |

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `CostumeReturnPayFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inCustomerName | JTextField | Ô nhập tên khách hàng |
| subSearchCustomer | JButton | Nút Search tìm khách hàng |
| outCustomerList | JTable | Bảng hiển thị danh sách khách hàng tìm được |
| outRentalList | JTable (checkbox) | Bảng danh sách trang phục đang mượn với cột checkbox để chọn trả |
| inStatus | JTextField | Ô nhập trạng thái trang phục khi trả |
| inFine | JTextField | Ô nhập tiền phạt (nếu có) |
| subPayment | JButton | Nút Payment để xem hóa đơn |
| outInvoice | JTextArea | Hiển thị hóa đơn thanh toán |
| outDeposit | JLabel | Hiển thị tiền cọc |
| outAmountToPay | JLabel | Hiển thị số tiền khách phải trả |
| subConfirm | JButton | Nút Confirm xác nhận thanh toán |

### DAO Classes

**CustomerDAO**

| Phương thức | Mô tả |
|------------|-------|
| searchByName(String keyword) : List\<Customer\> | Tìm khách hàng theo tên (LIKE) |

**RentalSlipDAO**

| Phương thức | Mô tả |
|------------|-------|
| getSlipsByCustomer(int customerId) : List\<RentalSlip\> | Lấy danh sách phiếu thuê theo khách hàng |

**RentalSlipDetailDAO**

| Phương thức | Mô tả |
|------------|-------|
| getDetailsBySlip(int rentalSlipId) : List\<RentalSlipDetail\> | Lấy chi tiết phiếu thuê |
| updateDetailStatus(int detailId, String status, double fine) : boolean | Cập nhật trạng thái và phạt khi trả |

**PaymentDAO**

| Phương thức | Mô tả |
|------------|-------|
| createPayment(Payment payment) : boolean | Tạo bản ghi thanh toán mới |

---

## Câu 4: Sequence Diagram

### Lifelines

- :Staff
- :CostumeReturnPayFrm
- :CustomerDAO
- :RentalSlipDAO
- :RentalSlipDetailDAO
- :PaymentDAO

### Message Flow

```
:Staff -> :CostumeReturnPayFrm : Nhập tên "Nguyen Thi A", nhấn Search
:CostumeReturnPayFrm -> :CustomerDAO : searchByName("Nguyen Thi A")
:CustomerDAO --> :CostumeReturnPayFrm : List<Customer>
:CostumeReturnPayFrm --> :Staff : Hiển thị danh sách khách hàng

:Staff -> :CostumeReturnPayFrm : Click chọn khách hàng "Nguyen Thi A"
:CostumeReturnPayFrm -> :RentalSlipDAO : getSlipsByCustomer(customerId)
:RentalSlipDAO --> :CostumeReturnPayFrm : List<RentalSlip>
:CostumeReturnPayFrm -> :RentalSlipDetailDAO : getDetailsBySlip(rentalSlipId)
:RentalSlipDetailDAO --> :CostumeReturnPayFrm : List<RentalSlipDetail>
:CostumeReturnPayFrm --> :Staff : Hiển thị danh sách trang phục đang mượn

:Staff -> :CostumeReturnPayFrm : Tick chọn Vest + Váy, nhập trạng thái + phạt, nhấn Payment
:CostumeReturnPayFrm --> :Staff : Hiển thị hóa đơn (tổng, cọc, phải trả)

:Staff -> :CostumeReturnPayFrm : Nhấn Confirm
:CostumeReturnPayFrm -> :RentalSlipDetailDAO : updateDetailStatus(detailId, status, fine)
:RentalSlipDetailDAO --> :CostumeReturnPayFrm : true
:CostumeReturnPayFrm -> :PaymentDAO : createPayment(payment)
:PaymentDAO --> :CostumeReturnPayFrm : true
:CostumeReturnPayFrm --> :Staff : Thông báo thanh toán thành công
```

---

## Câu 5: Blackbox Testcase

### TC01: Trả trang phục và thanh toán thành công

#### Database BEFORE

**tblCustomer**

| customerId | customerName | phone |
|-----------|-------------|-------|
| C001 | Nguyen Thi A | 0901234567 |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | deposit |
|-------------|-----------|--------|-----------|---------|
| RS001 | C001 | U001 | 2026-07-01 | 350,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | rentalDays | rentalAmount | status | fine |
|---------|-------------|----------|---------|-----------|-----------|-------------|--------|-----|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 3 | 600,000 | Dang thue | 0 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 3 | 450,000 | Dang thue | 0 |

**tblPayment** (chua co ban ghi)

#### Steps

| Bước | Hành động | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Staff nhập "Nguyen Thi A", nhấn Search | Hiển thị danh sách KH có "Nguyen Thi A" |
| 2 | Staff click chọn "Nguyen Thi A" | Hiển thị 2 trang phục đang mượn: Vest nam đen x2, Váy dạ hội đỏ x1 |
| 3 | Staff tick checkbox Vest nam đen + Váy dạ hội đỏ | 2 dòng được chọn |
| 4 | Staff nhập trạng thái Vest: "Bình thường", phạt: 0 | Trường nhập hiển thị |
| 5 | Staff nhập trạng thái Váy: "Bẩn", phạt: 50,000đ | Trường nhập hiển thị |
| 6 | Staff nhấn Payment | Hóa đơn hiển thị: Vest 600,000đ + Váy 450,000đ + phạt 50,000đ = Tổng 1,100,000đ, Cọc 350,000đ, Khách trả 750,000đ |
| 7 | Staff nhấn Confirm | Thông báo thanh toán thành công |

#### Database AFTER

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | rentalDays | rentalAmount | status | fine |
|---------|-------------|----------|---------|-----------|-----------|-------------|--------|-----|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 3 | 600,000 | Binh thuong | 0 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 3 | 450,000 | Ban | 50,000 |

**tblPayment** (them 1 ban ghi moi)

| paymentId | rentalSlipId | paymentDate | totalAmount | deposit | amountPaid | note |
|----------|-------------|------------|-----------|---------|-----------|------|
| P001 | RS001 | 2026-07-04 | 1,100,000 | 350,000 | 750,000 | Tra trang phuc |
