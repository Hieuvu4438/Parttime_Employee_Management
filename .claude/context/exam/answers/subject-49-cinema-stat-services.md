# Subject No. 49 — Cinema Chain — Module "Statistics of services"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Thống kê dịch vụ theo thời gian

### Diễn biến chính

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện với ô nhập username, password, nút Login. |
| 2 | Staff nhập `staff01`, password `******`, nhấn Login. Hệ thống xác thực, chuyển sang giao diện Home. |
| 3 | Giao diện Home hiển thị các menu: Sell Tickets, Schedule, Food & Drinks, Statistics. |
| 4 | Staff chọn **Statistics** → **Service statistics**. |
| 5 | Giao diện thống kê dịch vụ xuất hiện: ô nhập ngày bắt đầu (`inStartDate`), ô nhập ngày kết thúc (`inEndDate`), nút **View**. |
| 6 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết thức `30/06/2026`, nhấn **View**. |
| 7 | Hệ thống truy vấn database, hiển thị bảng danh sách dịch vụ đã bán trong khoảng thời gian. Các cột: mã dịch vụ, tên dịch vụ, tổng số lượng bán, tổng doanh thu. Bảng sắp xếp theo tổng doanh thu giảm dần. |
| 8 | Bảng hiển thị: Popcorn Caramel (mã FD01, 500 phần, 32,500,000đ), Coca Cola 330ml (mã FD02, 800 ly, 20,000,000đ), Nachos (mã FD03, 200 phần, 14,000,000đ). |
| 9 | Staff nhấn vào dòng "Popcorn Caramel" trên bảng. |
| 10 | Hệ thống hiển thị bảng chi tiết bán hàng của Popcorn Caramel. Các cột: ngày bán, đơn giá, số lượng, tổng tiền. Bảng sắp xếp theo ngày bán từ cũ đến mới. |
| 11 | Bảng chi tiết hiển thị: 15/01/2026 (65,000đ, 100 phần, 6,500,000đ), 02/03/2026 (65,000đ, 150 phần, 9,750,000đ), 20/05/2026 (65,000đ, 250 phần, 16,250,000đ). |
| 12 | Staff có thể nhấn vào dòng khác (ví dụ "Coca Cola 330ml") để xem chi tiết tương ứng. |

### Kịch bản ngoại lệ

- **EL1:** Staff nhập ngày bắt đầu > ngày kết thức → hệ thống cảnh báo "Ngay bat dau phai truoc ngay ket thuc".
- **EL2:** Không có dữ liệu trong khoảng thời gian → bảng danh sách trống, hiển thị thông báo "Khong co du lieu".
- **EL3:** Staff nhập ngày không hợp lệ (ví dụ 30/02/2026) → hệ thống cảnh báo "Dinh dang ngay khong hop le".

---

## Câu 2: Entity Class Diagram

### Mô tả tự nhiên

Hệ thống rạp chiếu phim quản lý các mặt hàng đồ ăn/thức uống (FoodItem). Khi khách hàng mua đồ, nhân viên lập hóa đơn đồ ăn (FoodInvoice) chứa nhiều chi tiết (FoodInvoiceDetail), mỗi chi tiết liên kết với một FoodItem. Nhân viên thao tác trên hệ thống là User. Các mặt hàng đổi bằng điểm tích lũy vẫn được quy đổi ra tiền để thống kê.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| FoodItem | Entity class | Mặt hàng đồ ăn/thức uống bán tại rạp |
| FoodInvoice | Entity class | Hóa đơn bán đồ ăn tại một thời điểm |
| FoodInvoiceDetail | Entity class | Chi tiết từng mặt hàng trong hóa đơn |
| User | Entity class | Nhân viên thao tác trên hệ thống |
| Service statistic | Derived data | Không phải entity, là kết quả truy vấn |

### Class Diagram (ASCII)

```
+------------------+       +---------------------+
|    FoodItem      |       |    FoodInvoice      |
+------------------+       +---------------------+
| -id: int         |       | -id: int            |
| -code: String    |       | -invoiceDate: Date  |
| -name: String    |       | -totalAmount: double|
| -price: double   |       | -userId: int        |
| -size: String    |       +---------------------+
| -description: String|    | +getInvoiceDetails()|
+------------------+       +---------------------+
| +getTotalSold()  |       |          | 1        |
+------------------+       |          |          |
         | 1               |          | n        |
         |                  |          v          |
         | n                | +---------------------+
         v                  | | FoodInvoiceDetail   |
+---------------------+     | +---------------------+
| FoodInvoiceDetail   |<----+ | -id: int            |
+---------------------+       | -foodInvoiceId: int |
| -id: int            |       | -foodItemId: int    |
| -foodInvoiceId: int |       | -quantity: int      |
| -foodItemId: int    |       | -unitPrice: double  |
| -quantity: int      |       | -amount: double     |
| -unitPrice: double  |       +---------------------+
| -amount: double     |       | +getFoodItem()      |
+---------------------+       +---------------------+

+------------------+
|      User        |
+------------------+
| -id: int         |
| -username: String|
| -password: String|
| -role: String    |
+------------------+
| +checkLogin()    |
+------------------+
```

### Bảng quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| FoodInvoice → FoodInvoiceDetail | 1-n | Một hóa đơn có nhiều chi tiết |
| FoodItem → FoodInvoiceDetail | 1-n | Một mặt hàng xuất hiện trong nhiều chi tiết |
| User → FoodInvoice | 1-n | Một nhân viên lập nhiều hóa đơn |

---

## Câu 3: Thiết kế tĩnh

### View classes

**ServiceStatFrm** (giao diện thống kê dịch vụ):

| Thành phần | Loại | Mô tả |
|------------|------|-------|
| `inStartDate` | Text field | Ô nhập ngày bắt đầu (dd/MM/yyyy) |
| `inEndDate` | Text field | Ô nhập ngày kết thức (dd/MM/yyyy) |
| `subView` | Button | Nút View — kích hoạt truy vấn thống kê |
| `outServiceTable` | Table (clickable) | Bảng danh sách dịch vụ: mã, tên, tổng SL, tổng DT. Mỗi dòng click được. |
| `outDetailTable` | Table | Bảng chi tiết bán hàng: ngày bán, đơn giá, số lượng, tổng tiền. Chỉ đọc. |

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| FoodItemDAO | `getServiceStat(startDate, endDate): List<ServiceStat>` | List<ServiceStat> | Truy vấn tổng hợp số lượng và doanh thu theo từng mặt hàng |
| FoodInvoiceDetailDAO | `getServiceDetail(foodItemId, startDate, endDate): List<ServiceDetail>` | List<ServiceDetail> | Truy vấn chi tiết bán hàng của một mặt hàng |

**DTO / View Model:**

| Class | Thuộc tính | Mô tả |
|-------|------------|-------|
| ServiceStat | foodItemCode, foodItemName, totalQuantity, totalRevenue | Dòng tổng hợp trong bảng dịch vụ |
| ServiceDetail | saleDate, unitPrice, quantity, totalAmount | Dòng chi tiết bán hàng |

### Entity types

| Entity | Bảng | Mô tả |
|--------|------|-------|
| FoodItem | tblFoodItem | Mặt hàng đồ ăn/thức uống |
| FoodInvoice | tblFoodInvoice | Hóa đơn bán đồ ăn |
| FoodInvoiceDetail | tblFoodInvoiceDetail | Chi tiết từng mục trong hóa đơn |
| User | tblUser | Người dùng hệ thống |

### Database schema

```
tblUser
├── ID (PK, int, identity)
├── username (varchar, unique)
├── password (varchar)
├── role (varchar)
└── description (varchar)

tblFoodItem
├── ID (PK, int, identity)
├── code (varchar, unique)
├── name (nvarchar)
├── price (float)
├── size (varchar)
└── description (nvarchar)

tblFoodInvoice
├── ID (PK, int, identity)
├── invoiceDate (datetime)
├── totalAmount (float)
└── userID (FK → tblUser.ID)

tblFoodInvoiceDetail
├── ID (PK, int, identity)
├── foodInvoiceID (FK → tblFoodInvoice.ID)
├── foodItemID (FK → tblFoodItem.ID)
├── quantity (int)
├── unitPrice (float)
└── amount (float)
```

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo package **View**: thêm boundary class `ServiceStatFrm` với các attribute `inStartDate`, `inEndDate`, `subView`, `outServiceTable`, `outDetailTable`.
2. Tạo package **Entity**: thêm entity classes `FoodItem`, `FoodInvoice`, `FoodInvoiceDetail`, `User` với các attribute như trên.
3. Tạo package **DAO**: thêm control classes `FoodItemDAO`, `FoodInvoiceDetailDAO` với các method.
4. Vẽ dependency: `ServiceStatFrm` → `FoodItemDAO`, `ServiceStatFrm` → `FoodInvoiceDetailDAO`.
5. Vẽ association: `FoodInvoice` ◆→ `FoodInvoiceDetail` (composition), `FoodInvoiceDetail` → `FoodItem` (association).
6. Thêm note mô tả DTO: `ServiceStat`, `ServiceDetail`.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Tạo 4 lifelines theo thứ tự trái sang phải: `:Staff` (actor), `:ServiceStatFrm` (boundary), `:FoodItemDAO` (control), `:FoodInvoiceDetailDAO` (control).
2. Vẽ message từ Staff đến ServiceStatFrm cho các thao tác nhập liệu và click.
3. Vẽ message từ ServiceStatFrm đến DAO cho các truy vấn.
4. Vẽ return message (dashed line) từ DAO về ServiceStatFrm.
5. Sử dụng `alt` fragment cho trường hợp có dữ liệu / không có dữ liệu.

### ASCII Sequence Diagram

```
Staff              ServiceStatFrm       FoodItemDAO      FoodInvoiceDetailDAO
  |                      |                    |                    |
  |--enterDates()------->|                    |                    |
  |  (01/01, 30/06)      |                    |                    |
  |--clickView()-------->|                    |                    |
  |                      |                    |                    |
  |                      |--getServiceStat()->|                    |
  |                      |  (dates)           |                    |
  |                      |                    |--SELECT SUM(qty),  |
  |                      |                    |  SUM(amount) FROM  |
  |                      |                    |  tblFoodInvoice    |
  |                      |                    |  Detail JOIN ...   |
  |                      |                    |<-return List<Stat>-|
  |                      |<--List<ServiceStat>|                    |
  |                      |                    |                    |
  |<--displayTable()-----|                    |                    |
  |  Popcorn(500,32.5M)  |                    |                    |
  |  Coca(800,20M)       |                    |                    |
  |                      |                    |                    |
  |--clickItem(Popcorn)->|                    |                    |
  |                      |--getServiceDetail()|------------------->|
  |                      |  (foodItemId,dates)|                    |
  |                      |                    |                    |--SELECT date,
  |                      |                    |                    |  price, qty,
  |                      |                    |                    |  amount FROM
  |                      |                    |                    |  tblFoodInvoice
  |                      |                    |                    |  Detail WHERE
  |                      |                    |                    |  foodItemId=...
  |                      |                    |                    |<-return List<Dtl>|
  |                      |<--List<ServiceDetail>-------------------|
  |                      |                    |                    |
  |<--displayDetail()----|                    |                    |
  |  15/01: 65k×100      |                    |                    |
  |  02/03: 65k×150      |                    |                    |
  |  20/05: 65k×250      |                    |                    |
```

### Bảng chi tiết message (>= 20 bước)

| # | Từ | Đến | Message | Ghi chú |
|---|-----|-----|---------|---------|
| 1 | Staff | ServiceStatFrm | `enterStartDate("01/01/2026")` | Nhập ngày bắt đầu |
| 2 | Staff | ServiceStatFrm | `enterEndDate("30/06/2026")` | Nhập ngày kết thúc |
| 3 | Staff | ServiceStatFrm | `clickView()` | Nhấn nút View |
| 4 | ServiceStatFrm | ServiceStatFrm | `validateDates()` | Kiểm tra ngày hợp lệ |
| 5 | ServiceStatFrm | FoodItemDAO | `getServiceStat(01/01/2026, 30/06/2026)` | Gọi truy vấn tổng hợp |
| 6 | FoodItemDAO | FoodItemDAO | `executeSQL()` | Thực thi SQL GROUP BY |
| 7 | FoodItemDAO | ServiceStatFrm | `return List<ServiceStat>` | Trả về danh sách tổng hợp |
| 8 | ServiceStatFrm | ServiceStatFrm | `displayServiceTable(list)` | Hiển thị bảng dịch vụ |
| 9 | Staff | ServiceStatFrm | `clickItem(Popcorn Caramel)` | Nhấn vào dòng Popcorn |
| 10 | ServiceStatFrm | ServiceStatFrm | `getSelectedFoodItemId()` | Lấy ID mặt hàng được chọn |
| 11 | ServiceStatFrm | FoodInvoiceDetailDAO | `getServiceDetail(1, dates)` | Gọi truy vấn chi tiết |
| 12 | FoodInvoiceDetailDAO | FoodInvoiceDetailDAO | `executeSQL()` | Thực thi SQL WHERE |
| 13 | FoodInvoiceDetailDAO | ServiceStatFrm | `return List<ServiceDetail>` | Trả về danh sách chi tiết |
| 14 | ServiceStatFrm | ServiceStatFrm | `displayDetailTable(list)` | Hiển thị bảng chi tiết |
| 15 | Staff | ServiceStatFrm | `clickItem(Coca Cola 330ml)` | Nhấn sang mặt hàng khác |
| 16 | ServiceStatFrm | ServiceStatFrm | `getSelectedFoodItemId()` | Lấy ID Coca Cola |
| 17 | ServiceStatFrm | FoodInvoiceDetailDAO | `getServiceDetail(2, dates)` | Gọi truy vấn chi tiết |
| 18 | FoodInvoiceDetailDAO | FoodInvoiceDetailDAO | `executeSQL()` | Thực thi SQL |
| 19 | FoodInvoiceDetailDAO | ServiceStatFrm | `return List<ServiceDetail>` | Trả về chi tiết Coca |
| 20 | ServiceStatFrm | ServiceStatFrm | `displayDetailTable(list)` | Hiển thị chi tiết Coca |
| 21 | Staff | ServiceStatFrm | `clickItem(Nachos)` | Nhấn sang Nachos |
| 22 | ServiceStatFrm | FoodInvoiceDetailDAO | `getServiceDetail(3, dates)` | Truy vấn chi tiết Nachos |
| 23 | FoodInvoiceDetailDAO | ServiceStatFrm | `return List<ServiceDetail>` | Trả về chi tiết Nachos |
| 24 | ServiceStatFrm | ServiceStatFrm | `displayDetailTable(list)` | Hiển thị chi tiết Nachos |

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê dịch vụ có dữ liệu trong khoảng thời gian

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

**tblFoodItem:**
| ID | code | name | price | size |
|----|------|------|-------|------|
| 1 | FD01 | Popcorn Caramel | 65000 | M |
| 2 | FD02 | Coca Cola 330ml | 25000 | S |
| 3 | FD03 | Nachos | 70000 | L |

**tblFoodInvoice:**
| ID | invoiceDate | totalAmount | userID |
|----|-------------|-------------|--------|
| 1 | 15/01/2026 14:30 | 13000000 | 1 |
| 2 | 02/03/2026 19:00 | 19500000 | 1 |
| 3 | 20/05/2026 21:00 | 32500000 | 1 |
| 4 | 10/07/2025 16:00 | 5000000 | 1 |

**tblFoodInvoiceDetail:**
| ID | foodInvoiceID | foodItemID | quantity | unitPrice | amount |
|----|---------------|------------|----------|-----------|--------|
| 1 | 1 | 1 | 100 | 65000 | 6500000 |
| 2 | 1 | 2 | 200 | 25000 | 5000000 |
| 3 | 1 | 3 | 50 | 70000 | 3500000 |
| 4 | 2 | 1 | 150 | 65000 | 9750000 |
| 5 | 2 | 2 | 300 | 25000 | 7500000 |
| 6 | 2 | 3 | 50 | 70000 | 3500000 |
| 7 | 3 | 1 | 250 | 65000 | 16250000 |
| 8 | 3 | 2 | 300 | 25000 | 7500000 |
| 9 | 3 | 3 | 100 | 70000 | 7000000 |
| 10 | 4 | 2 | 200 | 25000 | 5000000 |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Chuyển sang giao diện Home |
| 3 | Chọn **Statistics** → **Service statistics** | Giao diện thống kê dịch vụ: 2 ô nhập ngày, nút View |
| 4 | Nhập ngày bắt đầu `01/01/2026`, ngày kết thức `30/06/2026`, nhấn View | Bảng hiển thị 3 dòng: Popcorn Caramel (500 phần, 32,500,000đ), Coca Cola 330ml (800 ly, 20,000,000đ), Nachos (200 phần, 14,000,000đ). Sắp xếp giảm dần theo doanh thu. |
| 5 | Nhấn vào dòng "Popcorn Caramel" | Bảng chi tiết: 15/01/2026 (65,000đ, 100 phần, 6,500,000đ), 02/03/2026 (65,000đ, 150 phần, 9,750,000đ), 20/05/2026 (65,000đ, 250 phần, 16,250,000đ). Sắp xếp ngày cũ → mới. |
| 6 | Nhấn vào dòng "Coca Cola 330ml" | Bảng chi tiết thay đổi: 15/01/2026 (25,000đ, 200 ly, 5,000,000đ), 02/03/2026 (25,000đ, 300 ly, 7,500,000đ), 20/05/2026 (25,000đ, 300 ly, 7,500,000đ). |
| 7 | Nhấn vào dòng "Nachos" | Bảng chi tiết: 15/01/2026 (70,000đ, 50 phần, 3,500,000đ), 02/03/2026 (70,000đ, 50 phần, 3,500,000đ), 20/05/2026 (70,000đ, 100 phần, 7,000,000đ). |

### Database sau khi test

**Không thay đổi** — chức năng thống kê chỉ đọc dữ liệu, không ghi/ sửa/ xóa.

Ghi chú: Hóa đơn ID=4 (invoiceDate=10/07/2025) không nằm trong khoảng 01/01/2026 - 30/06/2026 nên không được tính vào thống kê.
