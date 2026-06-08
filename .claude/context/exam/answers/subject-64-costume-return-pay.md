# Subject No. 64 — Costume Rental — Module "Customer returns and pays"

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario — Khách hàng trả trang phục và thanh toán

### Mô tả ngôn ngữ tự nhiên

Module "Customer returns and pays" cho phép nhân viên (staff) xử lý việc khách hàng mang trang phục đã thuê đến trả. Staff chọn menu tìm kiếm phiếu thuê theo tên khách hàng, nhập tên và tìm kiếm, hệ thống hiển thị danh sách khách hàng phù hợp. Staff chọn đúng khách hàng, hệ thống hiển thị danh sách trang phục đang mượn với đầy đủ thông tin: tên trang phục, ngày thuê, giá thuê/ngày, số ngày đã thuê, tổng tiền thuê, và checkbox để chọn trả. Staff có thể chọn trả từng phần hoặc toàn bộ. Với mỗi trang phục trả, staff nhập trạng thái (bình thường/hư hỏng/bẩn) và tiền phạt nếu có. Staff nhấn Payment, hệ thống hiển thị hóa đơn với thông tin khách hàng, danh sách trang phục trả, tổng tiền, tiền cọc, số tiền khách phải trả hoặc được hoàn. Staff nhấn Confirm để lưu vào database.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Customer mang trang phục đã thuê đến quầy trả |
| 2 | Staff đăng nhập hệ thống (username: `staff01`, password: `******`) |
| 3 | Giao diện Home hiển thị. Staff chọn menu **Customer returns and pays** |
| 4 | Hệ thống hiển thị giao diện tìm kiếm: ô nhập tên khách hàng (`txtCustomerName`) + nút **Search** |
| 5 | Staff nhập "Nguyen Thi A" vào ô Customer Name, nhấn **Search** |
| 6 | Hệ thống tìm kiếm khách hàng theo tên (LIKE), hiển thị bảng danh sách khách hàng chứa keyword: mã KH, tên, SĐT, email, địa chỉ |
| 7 | Staff click chọn khách hàng **Nguyen Thi A** (customerId = C001) trong danh sách |
| 8 | Hệ thống hiển thị danh sách trang phục đang mượn của khách hàng C001 trong bảng: |
|    | -- D001: Vest nam đen, thuê 01/07, giá 100,000đ/ngày, SL 2, đã thuê 3 ngày, tổng 600,000đ [checkbox] |
|    | -- D002: Váy dạ hội đỏ, thuê 01/07, giá 150,000đ/ngày, SL 1, đã thuê 3 ngày, tổng 450,000đ [checkbox] |
|    | -- D003: Áo dài trắng, thuê 01/07, giá 80,000đ/ngày, SL 1, đã thuê 3 ngày, tổng 240,000đ [checkbox] |
| 9 | Staff tick chọn checkbox **Vest nam đen** (D001) và **Váy dạ hội đỏ** (D002), không chọn Áo dài trắng (trả một phần) |
| 10 | Staff nhập trạng thái Vest nam đen: "Binh thuong", phạt: 0đ |
| 11 | Staff nhập trạng thái Váy dạ hội đỏ: "Ban" (bẩn), phạt: 50,000đ |
| 12 | Staff nhấn nút **Payment** |
| 13 | Hệ thống hiển thị hóa đơn: thông tin khách hàng Nguyen Thi A + danh sách trang phục trả: Vest nam đen x2 = 600,000đ (phạt 0đ), Váy dạ hội đỏ x1 = 450,000đ (phạt 50,000đ). Tổng tiền thuê: 1,050,000đ. Tổng phạt: 50,000đ. Tổng thanh toán: 1,100,000đ. Tiền cọc: 350,000đ. Khách phải trả: 750,000đ |
| 14 | Staff nhấn nút **Confirm** |
| 15 | Hệ thống cập nhật database: cập nhật trạng thái chi tiết phiếu thuê D001, D002 thành "Da tra" + ghi nhận phạt, tạo bản ghi Payment mới. Do chưa trả hết (còn D003), tiền cọc không được hoàn |
| 16 | Hệ thống thông báo "Thanh toan thanh cong" và hiển thị lại danh sách trang phục còn mượn (chỉ còn D003) |

### Kịch bản ngoại lệ

| Ngoại lệ | Mô tả | Xử lý |
|----------|-------|-------|
| EL1 | Tên khách hàng không tồn tại | Hiển thị danh sách trống, thông báo "Khong tim thay khach hang" |
| EL2 | Khách hàng không có trang phục đang mượn | Hiển thị danh sách trống, thông báo "Khach hang khong co trang phuc dang muon" |
| EL3 | Staff không tick chọn trang phục nào | Nhấn Payment bị vô hiệu hóa hoặc cảnh báo "Vui long chon trang phuc de tra" |
| EL4 | Staff nhập tiền phạt < 0 | Hiển thị lỗi "Tien phat khong hop le" |

---

## Câu 2: Entity Class Diagram

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý cho thuê trang phục. Cửa hàng có nhiều loại trang phục khác nhau, mỗi loại có thể có nhiều số lượng. Nhà cung cấp cung cấp nhiều loại trang phục, mỗi lần nhập có thể nhập nhiều loại từ cùng nhà cung cấp. Khách hàng có thể thuê nhiều lần, mỗi lần thuê nhiều loại trang phục khác nhau, mỗi loại có số lượng khác nhau. Khi thuê lần đầu, tiền cọc bằng tổng giá trị gốc của các trang phục thuê. Khi trả, khách hàng có thể trả từng phần hoặc toàn bộ. Mỗi lần trả có phiếu thanh toán tương ứng. Tiền cọc chỉ được hoàn khi tất cả trang phục đã được trả. Nếu trang phục bị hư hỏng hoặc bẩn, khách hàng phải nộp phạt.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Costume (trang phục) | Entity class | Đối tượng chính được quản lý, cho thuê |
| Customer (khách hàng) | Entity class | Người thuê trang phục |
| RentalSlip (phiếu thuê) | Entity class | Chứng từ ghi nhận mỗi lần thuê |
| RentalSlipDetail (chi tiết phiếu thuê) | Entity class | Chi tiết từng loại trang phục trong phiếu thuê |
| Payment (phiếu thanh toán) | Entity class | Chứng từ ghi nhận mỗi lần trả/thanh toán |
| User (nhân viên) | Entity class | Người thao tác trên hệ thống |
| costumeName, model, genre | Thuộc tính của Costume | Mô tả đặc điểm trang phục |
| rentalPricePerDay, status | Thuộc tính của Costume | Giá thuê và trạng thái |
| customerName, phone, email, address | Thuộc tính của Customer | Thông tin liên hệ |
| rentalDate, deposit, totalAmount | Thuộc tính của RentalSlip | Thông tin phiếu thuê |
| quantity, rentalDays, rentalAmount, fine | Thuộc tính của RentalSlipDetail | Chi tiết thuê và phạt |
| paymentDate, amountPaid, note | Thuộc tính của Payment | Thông tin thanh toán |

### Bảng thuộc tính entity

| Entity | Thuộc tính |
|--------|-----------|
| **Costume** | costumeId (PK), costumeName, model, genre, rentalPricePerDay, status |
| **Customer** | customerId (PK), customerName, phone, email, address |
| **RentalSlip** | rentalSlipId (PK), customerId (FK), userId (FK), rentalDate, deposit, totalAmount |
| **RentalSlipDetail** | detailId (PK), rentalSlipId (FK), costumeId (FK), quantity, rentalDate, rentalDays, rentalAmount, status, fine |
| **Payment** | paymentId (PK), rentalSlipId (FK), paymentDate, totalAmount, deposit, amountPaid, note |
| **User** | userId (PK), username, password, fullName, role |

### Class Diagram (ASCII)

```
+------------------+       +-------------------+       +------------------+
|    Customer      |       |   RentalSlip      |       |      User        |
+------------------+       +-------------------+       +------------------+
| -customerId: int |       | -rentalSlipId: int|       | -userId: int     |
| -customerName: Str|  1  | -customerId: FK   |  1   | -username: Str   |
| -phone: Str      |------| -userId: FK       |------| -password: Str   |
| -email: Str      |   *  | -rentalDate: Date |   *  | -fullName: Str   |
| -address: Str    |       | -deposit: double  |       | -role: Str       |
+------------------+       | -totalAmount: dbl |       +------------------+
                           +-------------------+
                                   | 1
                                   |
                                   | *
                           +-----------------------+
                           |  RentalSlipDetail     |
                           +-----------------------+
                           | -detailId: int        |
                           | -rentalSlipId: FK     |
                           | -costumeId: FK        |
                           | -quantity: int        |
                           | -rentalDate: Date     |
                           | -rentalDays: int      |
                           | -rentalAmount: double |
                           | -status: Str          |
                           | -fine: double         |
                           +-----------------------+
                                   | *
                                   |
                                   | 1
                           +------------------+       +-------------------+
                           |    Costume       |       |    Payment        |
                           +------------------+       +-------------------+
                           | -costumeId: int  |       | -paymentId: int   |
                           | -costumeName: Str|       | -rentalSlipId: FK |
                           | -model: Str      |       | -paymentDate: Date|
                           | -genre: Str      |       | -totalAmount: dbl |
                           | -rentalPricePD: dbl      | -deposit: double  |
                           | -status: Str     |       | -amountPaid: dbl  |
                           +------------------+       | -note: Str        |
                                                      +-------------------+
```

### Bảng quan hệ

| Entity 1 | Entity 2 | Kiểu | Giải thích |
|-----------|----------|------|------------|
| Customer | RentalSlip | 1 : N | Một khách hàng có nhiều phiếu thuê |
| RentalSlip | RentalSlipDetail | 1 : N | Một phiếu thuê có nhiều chi tiết trang phục |
| Costume | RentalSlipDetail | 1 : N | Một trang phục xuất hiện trong nhiều chi tiết phiếu thuê |
| RentalSlip | Payment | 1 : 0..1 | Một phiếu thuê có tối đa 1 phiếu thanh toán |
| User | RentalSlip | 1 : N | Một nhân viên tạo nhiều phiếu thuê |

---

## Câu 3: Thiết kế tĩnh (Static Design)

### View Class: `CostumeReturnPayFrm`

| Thành viên | Kiểu | Mô tả |
|-----------|------|-------|
| inCustomerName | JTextField | Ô nhập tên khách hàng cần tìm |
| subSearchCustomer | JButton | Nút "Search" để tìm khách hàng theo tên |
| outCustomerList | JTable | Bảng danh sách khách hàng tìm được (click được). Cột: customerId, customerName, phone, email, address |
| outRentalDetailList | JTable | Bảng danh sách trang phục đang mượn. Cột: checkbox, costumeName, rentalDate, pricePerDay, quantity, rentalDays, rentalAmount, status (nhập), fine (nhập) |
| inStatus | JTextField | Ô nhập trạng thái trang phục khi trả (cho mỗi dòng được chọn) |
| inFine | JTextField | Ô nhập tiền phạt (cho mỗi dòng được chọn) |
| subPayment | JButton | Nút "Payment" để xem hóa đơn trước khi xác nhận |
| outInvoice | JTextArea | Hiển thị hóa đơn thanh toán (thông tin KH, danh sách trả, tổng tiền, cọc, phải trả) |
| subConfirm | JButton | Nút "Confirm" xác nhận thanh toán và lưu database |

### DAO Classes

**CustomerDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| searchByName(String keyword) | List\<Customer\> | Tìm khách hàng theo tên (LIKE '%keyword%') |

**RentalSlipDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getSlipsByCustomer(int customerId) | List\<RentalSlip\> | Lấy danh sách phiếu thuê chưa trả hết của khách hàng |

**RentalSlipDetailDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| getUnreturnedDetails(int rentalSlipId) | List\<RentalSlipDetail\> | Lấy danh sách chi tiết phiếu thuê chưa trả (status != 'Da tra') |
| updateDetailReturn(int detailId, String status, double fine) | boolean | Cập nhật trạng thái trả và tiền phạt cho 1 chi tiết |

**PaymentDAO**

| Phương thức | Kiểu trả về | Mô tả |
|------------|-------------|-------|
| createPayment(Payment payment) | boolean | Tạo bản ghi thanh toán mới |

### Database Schema

**tblCustomer:** customerId (PK), customerName, phone, email, address

**tblCostume:** costumeId (PK), costumeName, model, genre, rentalPricePerDay, status

**tblRentalSlip:** rentalSlipId (PK), customerId (FK→tblCustomer), userId (FK→tblUser), rentalDate, deposit, totalAmount

**tblRentalSlipDetail:** detailId (PK), rentalSlipId (FK→tblRentalSlip), costumeId (FK→tblCostume), quantity, rentalDate, rentalDays, rentalAmount, status, fine

**tblPayment:** paymentId (PK), rentalSlipId (FK→tblRentalSlip), paymentDate, totalAmount, deposit, amountPaid, note

**tblUser:** userId (PK), username, password, fullName, role

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package `view.costumereturnpay` chứa `CostumeReturnPayFrm`
2. Tạo package `model` chứa các entity: `Customer`, `Costume`, `RentalSlip`, `RentalSlipDetail`, `Payment`, `User`
3. Tạo package `dao` chứa: `CustomerDAO`, `RentalSlipDAO`, `RentalSlipDetailDAO`, `PaymentDAO`
4. Vẽ association: CostumeReturnPayFrm → CustomerDAO, RentalSlipDAO, RentalSlipDetailDAO, PaymentDAO
5. Vẽ dependency: DAO classes → Entity classes

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo các lifeline: `:Staff`, `:CostumeReturnPayFrm`, `:CustomerDAO`, `:RentalSlipDAO`, `:RentalSlipDetailDAO`, `:PaymentDAO`
2. Vẽ message flow theo bảng bên dưới
3. Sử dụng `return` message cho các kết quả trả về
4. Sử dụng `alt` fragment cho trường hợp tìm thấy / không tìm thấy khách hàng
5. Sử dụng `loop` fragment cho việc cập nhật từng chi tiết phiếu thuê

### Bảng chi tiết message flow

| STT | Từ | Đến | Message | Loại | Ghi chú |
|-----|-----|------|---------|------|---------|
| 1 | Staff | CostumeReturnPayFrm | actionPerformed(e) | sync | Staff chọn menu Customer returns and pays |
| 2 | CostumeReturnPayFrm | Staff | show() | return | Hiển thị giao diện: ô Customer Name + nút Search |
| 3 | Staff | CostumeReturnPayFrm | txtCustomerName.setText("Nguyen Thi A") | sync | Nhập tên KH |
| 4 | Staff | CostumeReturnPayFrm | btnSearch.doClick() | sync | Nhấn nút Search |
| 5 | CostumeReturnPayFrm | CustomerDAO | searchByName("Nguyen Thi A") | sync | Tìm KH theo tên |
| 6 | CustomerDAO | CostumeReturnPayFrm | return List\<Customer\> | return | Danh sách KH tìm được |
| 7 | CostumeReturnPayFrm | Staff | tblCustomerList.setData(list) | return | Hiển thị bảng danh sách KH |
| 8 | Staff | CostumeReturnPayFrm | tblCustomerList.selectRow(0) | sync | Chọn KH Nguyen Thi A |
| 9 | CostumeReturnPayFrm | RentalSlipDAO | getSlipsByCustomer(C001) | sync | Lấy phiếu thuê theo KH |
| 10 | RentalSlipDAO | CostumeReturnPayFrm | return List\<RentalSlip\> | return | Danh sách phiếu thuê |
| 11 | CostumeReturnPayFrm | RentalSlipDetailDAO | getUnreturnedDetails(RS001) | sync | Lấy chi tiết chưa trả |
| 12 | RentalSlipDetailDAO | CostumeReturnPayFrm | return List\<RentalSlipDetail\> | return | 3 chi tiết: D001, D002, D003 |
| 13 | CostumeReturnPayFrm | Staff | tblRentalDetail.setData(list) | return | Hiển thị bảng trang phục đang mượn |
| 14 | Staff | CostumeReturnPayFrm | tblRentalDetail.checkBox[0].setSelected(true) | sync | Tick Vest nam đen (D001) |
| 15 | Staff | CostumeReturnPayFrm | tblRentalDetail.checkBox[1].setSelected(true) | sync | Tick Váy dạ hội đỏ (D002) |
| 16 | Staff | CostumeReturnPayFrm | tblRentalDetail.setStatus(0, "Binh thuong") | sync | Nhập trạng thái Vest |
| 17 | Staff | CostumeReturnPayFrm | tblRentalDetail.setFine(0, 0) | sync | Nhập phạt Vest = 0 |
| 18 | Staff | CostumeReturnPayFrm | tblRentalDetail.setStatus(1, "Ban") | sync | Nhập trạng thái Váy |
| 19 | Staff | CostumeReturnPayFrm | tblRentalDetail.setFine(1, 50000) | sync | Nhập phạt Váy = 50,000 |
| 20 | Staff | CostumeReturnPayFrm | btnPayment.doClick() | sync | Nhấn nút Payment |
| 21 | CostumeReturnPayFrm | CostumeReturnPayFrm | calculateInvoice() | self | Tính tổng: 600,000 + 450,000 + 50,000 = 1,100,000. Cọc: 350,000. Trả: 750,000 |
| 22 | CostumeReturnPayFrm | Staff | showInvoice(invoice) | return | Hiển thị hóa đơn |
| 23 | Staff | CostumeReturnPayFrm | btnConfirm.doClick() | sync | Nhấn Confirm |
| 24 | CostumeReturnPayFrm | RentalSlipDetailDAO | updateDetailReturn(D001, "Binh thuong", 0) | sync | Cập nhật D001 |
| 25 | RentalSlipDetailDAO | CostumeReturnPayFrm | return true | return | Thành công |
| 26 | CostumeReturnPayFrm | RentalSlipDetailDAO | updateDetailReturn(D002, "Ban", 50000) | sync | Cập nhật D002 |
| 27 | RentalSlipDetailDAO | CostumeReturnPayFrm | return true | return | Thành công |
| 28 | CostumeReturnPayFrm | PaymentDAO | createPayment(payment) | sync | Tạo Payment (total=1,100,000, deposit=350,000, paid=750,000) |
| 29 | PaymentDAO | CostumeReturnPayFrm | return true | return | Thành công |
| 30 | CostumeReturnPayFrm | Staff | showMessage("Thanh toan thanh cong") | return | Thông báo thành công |

### ASCII Sequence Diagram

```
Staff    CostumeReturnPayFrm    CustomerDAO    RentalSlipDAO    RentalSlipDetailDAO    PaymentDAO
  |              |                    |               |                    |                  |
  |--selectMenu->|                    |               |                    |                  |
  |<--showFrm----|                    |               |                    |                  |
  |              |                    |               |                    |                  |
  |--enterName-->|                    |               |                    |                  |
  |--clickSearch>|                    |               |                    |                  |
  |              |--searchByName----->|               |                    |                  |
  |              |<--List<Customer>---|               |                    |                  |
  |<--showList---|                    |               |                    |                  |
  |              |                    |               |                    |                  |
  |--selectKH--->|                    |               |                    |                  |
  |              |--getSlipsByCustomer--------------->|                    |                  |
  |              |<--List<RentalSlip>-----------------|                    |                  |
  |              |--getUnreturnedDetails--------------------------------->|                  |
  |              |<--List<RentalSlipDetail>--------------------------------------------|      |
  |<--showDetail-|                    |               |                    |                  |
  |              |                    |               |                    |                  |
  |--tickVest--->|                    |               |                    |                  |
  |--tickVay---->|                    |               |                    |                  |
  |--enterStatus>|                    |               |                    |                  |
  |--clickPay--->|                    |               |                    |                  |
  |              |--calculateInvoice  |               |                    |                  |
  |<--showInvoice|                    |               |                    |                  |
  |              |                    |               |                    |                  |
  |--confirm---->|                    |               |                    |                  |
  |              |--updateReturn(D001)------------------------------------>|                  |
  |              |<--true------------------------------------------------------------|        |
  |              |--updateReturn(D002)------------------------------------>|                  |
  |              |<--true------------------------------------------------------------|        |
  |              |--createPayment-----------------------------------------------------------> |
  |              |<--true----------------------------------------------------------------------|
  |<--success----|                    |               |                    |                  |
```

---

## Câu 5: Blackbox Testcase

### Bảng testcase

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Customer returns and pays | TC01: Trả trang phục một phần và thanh toán thành công |
| 2 | Customer returns and pays | TC02: Khách hàng không tồn tại |
| 3 | Customer returns and pays | TC03: Khách hàng không có trang phục đang mượn |
| 4 | Customer returns and pays | TC04: Trả toàn bộ trang phục, cọc được hoàn |

---

### TC01: Trả trang phục một phần và thanh toán thành công

**Purpose:** Kiểm tra quy trình staff trả một phần trang phục cho khách hàng, nhập trạng thái và phạt, thanh toán và cập nhật database.

#### Database BEFORE

**tblCustomer**

| customerId | customerName | phone | email | address |
|-----------|-------------|-------|-------|---------|
| C001 | Nguyen Thi A | 0901234567 | nta@gmail.com | 123 Le Loi, HCM |
| C002 | Tran Thi B | 0912345678 | ttb@gmail.com | 456 Nguyen Hue, HCM |

**tblCostume**

| costumeId | costumeName | model | genre | rentalPricePerDay | status |
|----------|------------|-------|-------|------------------|--------|
| CS001 | Vest nam đen | Slim fit | Nam | 100,000 | Available |
| CS002 | Váy dạ hội đỏ | Size M | Nữ | 150,000 | Available |
| CS003 | Áo dài trắng | Size S | Nữ | 80,000 | Available |

**tblUser**

| userId | username | password | fullName | role |
|--------|---------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | staff |

**tblRentalSlip**

| rentalSlipId | customerId | userId | rentalDate | deposit | totalAmount |
|-------------|-----------|--------|-----------|---------|-------------|
| RS001 | C001 | U001 | 2026-07-01 | 350,000 | 1,290,000 |

**tblRentalSlipDetail**

| detailId | rentalSlipId | costumeId | quantity | rentalDate | rentalDays | rentalAmount | status | fine |
|---------|-------------|----------|---------|-----------|-----------|-------------|--------|-----|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 3 | 600,000 | Dang thue | 0 |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 3 | 450,000 | Dang thue | 0 |
| D003 | RS001 | CS003 | 1 | 2026-07-01 | 3 | 240,000 | Dang thue | 0 |

**tblPayment** (không có bản ghi nào liên quan đến RS001)

#### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Staff đăng nhập thành công (staff01/123456) | Giao diện Home hiển thị |
| 2 | Staff chọn menu "Customer returns and pays" | Giao diện tìm kiếm khách hàng hiển thị: ô Customer Name + nút Search |
| 3 | Staff nhập "Nguyen Thi A" vào ô Customer Name, nhấn Search | Bảng danh sách KH hiển thị 1 dòng: C001, Nguyen Thi A, 0901234567, nta@gmail.com |
| 4 | Staff click chọn dòng C001 (Nguyen Thi A) | Bảng trang phục đang mượn hiển thị 3 dòng: D001 (Vest nam đen x2, 600,000đ), D002 (Váy dạ hội đỏ x1, 450,000đ), D003 (Áo dài trắng x1, 240,000đ) |
| 5 | Staff tick checkbox D001 (Vest nam đen) và D002 (Váy dạ hội đỏ), không tick D003 | 2 checkbox được chọn |
| 6 | Staff nhập trạng thái D001: "Binh thuong", phạt: 0đ. Trạng thái D002: "Ban", phạt: 50,000đ | Các trường hiển thị giá trị đã nhập |
| 7 | Staff nhấn nút Payment | Hóa đơn hiển thị: KH Nguyen Thi A. Vest nam đen x2 = 600,000đ (phạt 0đ). Váy dạ hội đỏ x1 = 450,000đ (phạt 50,000đ). Tổng thuê: 1,050,000đ. Tổng phạt: 50,000đ. Tổng thanh toán: 1,100,000đ. Tiền cọc: 350,000đ. Khách phải trả: 750,000đ |
| 8 | Staff nhấn nút Confirm | Thông báo "Thanh toan thanh cong". Bảng trang phục còn mượn chỉ hiển thị 1 dòng D003 (Áo dài trắng) |

#### Database AFTER

**tblRentalSlipDetail** (thay đổi)

| detailId | rentalSlipId | costumeId | quantity | rentalDate | rentalDays | rentalAmount | status | fine |
|---------|-------------|----------|---------|-----------|-----------|-------------|--------|-----|
| D001 | RS001 | CS001 | 2 | 2026-07-01 | 3 | 600,000 | **Binh thuong** | **0** |
| D002 | RS001 | CS002 | 1 | 2026-07-01 | 3 | 450,000 | **Ban** | **50,000** |
| D003 | RS001 | CS003 | 1 | 2026-07-01 | 3 | 240,000 | Dang thue | 0 |

**tblPayment** (thêm 1 bản ghi mới)

| paymentId | rentalSlipId | paymentDate | totalAmount | deposit | amountPaid | note |
|----------|-------------|------------|-----------|---------|-----------|------|
| P001 | RS001 | 2026-07-04 | 1,100,000 | 350,000 | 750,000 | Tra 1 phan: Vest nam ban, Vay da hoi |

**tblCustomer, tblCostume, tblUser, tblRentalSlip** — không thay đổi

---
