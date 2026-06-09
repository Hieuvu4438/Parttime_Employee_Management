# Subject No. 61 — Installment Loan — Module "Statistics of product"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario — Thống kê sản phẩm theo doanh thu

**Mô tả:** Staff chọn chức năng thống kê sản phẩm theo doanh thu, nhập kỳ thống kê, hệ thống hiển thị danh sách danh mục sản phẩm sắp xếp theo tổng lãi giảm dần. Staff click vào 1 danh mục để xem chi tiết sản phẩm trong danh mục đó sắp xếp theo tổng lợi nhuận giảm dần. Staff click vào 1 sản phẩm để xem danh sách hợp đồng chứa sản phẩm đó sắp xếp theo thời gian tăng dần.

### Bảng diễn biến

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống với tài khoản `staff01` / `123456` |
| 2 | Staff chọn menu **Statistics** → **Statistics of product** |
| 3 | Hệ thống hiển thị giao diện thống kê sản phẩm: ô nhập ngày bắt đầu (`txtStartDate`), ô nhập ngày kết thúc (`txtEndDate`), nút **View** |
| 4 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết thúc `31/12/2026` |
| 5 | Staff nhấn nút **View** |
| 6 | Hệ thống hiển thị bảng thống kê theo danh mục sản phẩm gồm cột: tên danh mục, tổng số hóa đơn, tổng lãi. Dữ liệu sắp xếp giảm dần theo tổng lãi: Điện thoại (25 hóa đơn, lãi 45,000,000đ), Laptop (18 hóa đơn, lãi 32,000,000đ), Điện tử gia dụng (10 hóa đơn, lãi 15,000,000đ), Phụ kiện (30 hóa đơn, lãi 8,000,000đ) |
| 7 | Staff click vào dòng **Điện thoại** |
| 8 | Hệ thống hiển thị bảng chi tiết sản phẩm thuộc danh mục Điện thoại gồm cột: mã sản phẩm, tên sản phẩm, tổng số hóa đơn, tổng lợi nhuận. Dữ liệu sắp xếp giảm dần theo tổng lợi nhuận: SP001 - iPhone 15 (12 hóa đơn, lợi nhuận 22,000,000đ), SP002 - Samsung Galaxy S24 (8 hóa đơn, lợi nhuận 15,000,000đ), SP003 - OPPO Reno 11 (5 hóa đơn, lợi nhuận 8,000,000đ) |
| 9 | Staff click vào dòng **iPhone 15** |
| 10 | Hệ thống hiển thị danh sách hợp đồng chứa sản phẩm iPhone 15 trong kỳ thống kê, sắp xếp theo thời gian tăng dần, gồm cột: mã hợp đồng, tên khách hàng, tổng giá trị vay, tổng lãi đã thu. Ví dụ: HD001 - Nguyen Van A - vay 25,000,000đ - lãi 3,500,000đ (15/03/2026), HD005 - Pham Van D - vay 25,000,000đ - lãi 3,000,000đ (20/06/2026), HD012 - Le Thi C - vay 22,000,000đ - lãi 2,800,000đ (10/09/2026) |

### Ngoại lệ

- **EX01:** Staff nhập ngày bắt đầu > ngày kết thúc → hệ thống thông báo lỗi "Ngày bắt đầu phải nhỏ hơn ngày kết thúc"
- **EX02:** Staff để trống ngày bắt đầu hoặc ngày kết thúc → hệ thống thông báo "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc"
- **EX03:** Không có dữ liệu hợp đồng trong kỳ thống kê → hệ thống hiển thị bảng trống với thông báo "Không có dữ liệu trong kỳ thống kê"

---

## Câu 2: Entity Class Diagram

### Mô tả bằng ngôn ngữ tự nhiên

Hệ thống quản lý cho vay trả góp bao gồm các thực thể chính: **Product** (sản phẩm cho vay), **Contract** (hợp đồng vay), **ContractItem** (chi tiết sản phẩm trong hợp đồng), **Customer** (khách hàng vay), **Partner** (đối tác cung cấp sản phẩm), **User** (nhân viên hệ thống).

- Một khách hàng có thể có nhiều hợp đồng vay.
- Một hợp đồng vay chứa nhiều sản phẩm (thông qua ContractItem).
- Một sản phẩm có thể xuất hiện trong nhiều hợp đồng khác nhau.
- Một nhân viên có thể tạo nhiều hợp đồng.
- Một đối tác có thể liên kết với nhiều sản phẩm.

### Trích xuất danh từ → Entity

| Danh từ trong mô tả | Entity | Thuộc tính |
|----------------------|--------|------------|
| Sản phẩm, mặt hàng | **Product** | productId (PK), name, category, unitPrice, description, partnerId (FK) |
| Hợp đồng vay | **Contract** | contractId (PK), contractCode, contractDate, totalLoanValue, totalInterest, status, customerId (FK), staffId (FK) |
| Chi tiết hợp đồng (sản phẩm trong hợp đồng) | **ContractItem** | contractItemId (PK), contractId (FK), productId (FK), quantity, unitPrice, interest, subtotal |
| Khách hàng | **Customer** | customerId (PK), name, phone, address, idCard, dateOfBirth |
| Đối tác cung cấp | **Partner** | partnerId (PK), name, phone, address, representativeName |
| Nhân viên / người dùng | **User** | userId (PK), username, password, fullName, role |

### Mối quan hệ

| Entity 1 | Entity 2 | Cardinality | Mô tả |
|-----------|----------|-------------|-------|
| Customer | Contract | 1 : N | Một khách hàng có nhiều hợp đồng vay |
| Contract | ContractItem | 1 : N | Một hợp đồng chứa nhiều chi tiết sản phẩm |
| Product | ContractItem | 1 : N | Một sản phẩm xuất hiện trong nhiều chi tiết hợp đồng |
| User | Contract | 1 : N | Một nhân viên tạo nhiều hợp đồng |
| Partner | Product | 1 : N | Một đối tác cung cấp nhiều sản phẩm |

### ASCII Class Diagram

```
+---------------------+         +---------------------+
|      Customer       |         |       Partner       |
+---------------------+         +---------------------+
| - customerId (PK)   |         | - partnerId (PK)    |
| - name              |         | - name              |
| - phone             |         | - phone             |
| - address           |         | - address           |
| - idCard            |         | - representativeName|
| - dateOfBirth       |         +---------------------+
+---------------------+                  | 1
           | 1                           |
           |                             | N
           | N                  +---------------------+
+---------------------+        |      Product        |
|      Contract       |        +---------------------+
+---------------------+        | - productId (PK)    |
| - contractId (PK)   |        | - name              |
| - contractCode      |        | - category          |
| - contractDate      |        | - unitPrice         |
| - totalLoanValue    |        | - description       |
| - totalInterest     |        | - partnerId (FK)    |
| - status            |        +---------------------+
| - customerId (FK)   |                  | 1
| - staffId (FK)      |                  |
+---------------------+                  | N
           | 1                  +---------------------+
           |                    |    ContractItem     |
           | N                  +---------------------+
+---------------------+        | - contractItemId(PK)|
|    ContractItem     |--------| - contractId (FK)   |
+---------------------+   1:N  | - productId (FK)    |
                               | - quantity          |
+---------------------+        | - unitPrice         |
|       User          |        | - interest          |
+---------------------+        | - subtotal          |
| - userId (PK)       |        +---------------------+
| - username          |
| - password          |
| - fullName          |
| - role              |
+---------------------+
           | 1
           |
           | N
+---------------------+
|      Contract       |
+---------------------+
```

### Bảng quan hệ (Relation Schema)

| Bảng | Khóa chính | Khóa ngoại | Tham chiếu đến |
|------|-----------|------------|----------------|
| tblProduct | productId | partnerId | tblPartner(partnerId) |
| tblCustomer | customerId | — | — |
| tblPartner | partnerId | — | — |
| tblUser | userId | — | — |
| tblContract | contractId | customerId, staffId | tblCustomer(customerId), tblUser(userId) |
| tblContractItem | contractItemId | contractId, productId | tblContract(contractId), tblProduct(productId) |

### Classes diagram (analysis)

Phân tích module này (bỏ qua bước đăng nhập):

Đăng nhập thành công → HomeFrm:
  Nút chọn "Statistics" → "Statistics of product" → subStatProduct

Staff chọn Statistics of product → StatProductView xuất hiện:
  Ô nhập ngày bắt đầu → inStartDate
  Ô nhập ngày kết thúc → inEndDate
  Nút View → subView
  Bảng thống kê theo danh mục sản phẩm (click được) → outsubCategoryTable

Staff click danh mục → CategoryDetailView xuất hiện:
  Bảng chi tiết sản phẩm trong danh mục (click được) → outsubItemTable

Staff click sản phẩm → ProductContractListView xuất hiện:
  Danh sách hợp đồng chứa sản phẩm → outContractList

Phân tích phương thức:

Lấy thống kê danh mục sản phẩm theo doanh thu:
  Tên: getCategoryStatByRevenue()
  Đầu vào: startDate (Date), endDate (Date)
  Đầu ra: List<CategoryStatDTO>
  Gán cho entity class: Product.

Lấy chi tiết sản phẩm theo danh mục:
  Tên: getItemDetailByCategory()
  Đầu vào: category (String), startDate (Date), endDate (Date)
  Đầu ra: List<ItemDetailDTO>
  Gán cho entity class: Product.

Lấy danh sách hợp đồng chứa sản phẩm:
  Tên: getContractsByProduct()
  Đầu vào: productId (int), startDate (Date), endDate (Date)
  Đầu ra: List<Contract>
  Gán cho entity class: Contract.

### Tóm tắt
View classes: HomeFrm, StatProductView, CategoryDetailView, ProductContractListView
Phương thức: getCategoryStatByRevenue(), getItemDetailByCategory(), getContractsByProduct()

---

## Câu 3: Thiết kế tĩnh

### View Class: `StatProductFrm`

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `txtStartDate` | JTextField | Ô nhập ngày bắt đầu kỳ thống kê (định dạng dd/MM/yyyy) |
| `txtEndDate` | JTextField | Ô nhập ngày kết thúc kỳ thống kê (định dạng dd/MM/yyyy) |
| `btnView` | JButton | Nút "Xem thống kê" — thực hiện truy vấn thống kê |
| `tblCategoryStat` | JTable | Bảng hiển thị thống kê theo danh mục: cột tên danh mục, tổng hóa đơn, tổng lãi. Sắp xếp giảm dần theo tổng lãi |
| `tblItemStat` | JTable | Bảng hiển thị chi tiết sản phẩm trong danh mục: cột mã sản phẩm, tên sản phẩm, tổng hóa đơn, tổng lợi nhuận. Sắp xếp giảm dần theo tổng lợi nhuận |
| `tblContractList` | JTable | Bảng hiển thị danh sách hợp đồng của 1 sản phẩm: cột mã hợp đồng, tên khách hàng, tổng giá trị vay, tổng lãi đã thu. Sắp xếp theo thời gian tăng dần |
| `lblStatus` | JLabel | Hiển thị thông báo trạng thái (lỗi nhập liệu, không có dữ liệu, v.v.) |

### HomeFrm (chức năng điều hướng)

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `btnStatProduct` | JButton | Nút mở giao diện thống kê sản phẩm theo doanh thu |

### Entity Types

| Entity | Mô tả |
|--------|-------|
| `Product` | Sản phẩm cho vay: productId, name, category, unitPrice, description, partnerId |
| `Contract` | Hợp đồng vay: contractId, contractCode, contractDate, totalLoanValue, totalInterest, status, customerId, staffId |
| `ContractItem` | Chi tiết hợp đồng: contractItemId, contractId, productId, quantity, unitPrice, interest, subtotal |
| `Customer` | Khách hàng: customerId, name, phone, address, idCard, dateOfBirth |
| `Partner` | Đối tác: partnerId, name, phone, address, representativeName |
| `User` | Nhân viên: userId, username, password, fullName, role |

### DAO Classes

#### `ProductDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `getCategoryStatByRevenue(startDate, endDate)` | `List<CategoryStatDTO>` | Truy vấn danh sách danh mục sản phẩm kèm tổng hóa đơn, tổng lãi trong kỳ thống kê. GROUP BY category, ORDER BY SUM(interest) DESC |
| `getItemDetailByCategory(category, startDate, endDate)` | `List<ItemDetailDTO>` | Truy vấn chi tiết sản phẩm thuộc 1 danh mục kèm tổng hóa đơn, tổng lợi nhuận. GROUP BY productId, ORDER BY SUM(subtotal - quantity * unitPrice) DESC |
| `getProductById(productId)` | `Product` | Lấy thông tin sản phẩm theo mã |

#### `ContractDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `getContractsByProduct(productId, startDate, endDate)` | `List<ContractDTO>` | Truy vấn danh sách hợp đồng chứa 1 sản phẩm trong kỳ thống kê, JOIN tblContractItem, tblCustomer. ORDER BY contractDate ASC |
| `getContractById(contractId)` | `Contract` | Lấy thông tin hợp đồng theo mã |

#### `CustomerDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `getCustomerById(customerId)` | `Customer` | Lấy thông tin khách hàng theo mã |

### Database Schema

```sql
CREATE TABLE tblPartner (
    partnerId   VARCHAR(10) PRIMARY KEY,
    name        NVARCHAR(100),
    phone       VARCHAR(15),
    address     NVARCHAR(200),
    representativeName NVARCHAR(100)
);

CREATE TABLE tblProduct (
    productId   VARCHAR(10) PRIMARY KEY,
    name        NVARCHAR(100),
    category    NVARCHAR(50),
    unitPrice   DECIMAL(15,2),
    description NVARCHAR(500),
    partnerId   VARCHAR(10) REFERENCES tblPartner(partnerId)
);

CREATE TABLE tblCustomer (
    customerId  VARCHAR(10) PRIMARY KEY,
    name        NVARCHAR(100),
    phone       VARCHAR(15),
    address     NVARCHAR(200),
    idCard      VARCHAR(20),
    dateOfBirth DATE
);

CREATE TABLE tblUser (
    userId      VARCHAR(10) PRIMARY KEY,
    username    VARCHAR(50),
    password    VARCHAR(100),
    fullName    NVARCHAR(100),
    role        VARCHAR(20)
);

CREATE TABLE tblContract (
    contractId      VARCHAR(10) PRIMARY KEY,
    contractCode    VARCHAR(20),
    contractDate    DATE,
    totalLoanValue  DECIMAL(15,2),
    totalInterest   DECIMAL(15,2),
    status          VARCHAR(20),
    customerId      VARCHAR(10) REFERENCES tblCustomer(customerId),
    staffId         VARCHAR(10) REFERENCES tblUser(userId)
);

CREATE TABLE tblContractItem (
    contractItemId  VARCHAR(10) PRIMARY KEY,
    contractId      VARCHAR(10) REFERENCES tblContract(contractId),
    productId       VARCHAR(10) REFERENCES tblProduct(productId),
    quantity        INT,
    unitPrice       DECIMAL(15,2),
    interest        DECIMAL(15,2),
    subtotal        DECIMAL(15,2)
);
```

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Class Diagram**
2. Tạo 6 class: Product, Contract, ContractItem, Customer, Partner, User
3. Thêm attributes cho từng class (xem bảng Entity Types ở trên)
4. Vẽ mối quan hệ:
   - Customer → Contract (1:N) với navigable arrow từ Contract sang Customer
   - Contract → ContractItem (1:N) với composition (filled diamond)
   - Product → ContractItem (1:N) với navigable arrow từ ContractItem sang Product
   - User → Contract (1:N) với navigable arrow từ Contract sang User
   - Partner → Product (1:N) với navigable arrow từ Product sang Partner
5. Ghi chú cardinality ở mỗi đầu mối quan hệ

---

## Câu 4: Sequence Diagram

### Visual Paradigm Guide

1. Mở Visual Paradigm → **Diagram** → **Sequence Diagram**
2. Tạo 4 lifelines theo thứ tự từ trái sang phải:
   - `:Staff` (actor stereotype)
   - `:StatProductFrm` (boundary stereotype)
   - `:ProductDAO` (control stereotype)
   - `:ContractDAO` (control stereotype)
3. Vẽ message flow theo bảng bên dưới
4. Sử dụng `alt` fragment cho ngoại lệ (ngày không hợp lệ)
5. Sử dụng `loop` fragment cho bước lặp (staff click nhiều danh mục / sản phẩm)

### Lifelines

- **:Staff** — actor, người thực hiện thao tác
- **:StatProductFrm** — boundary, giao diện thống kê sản phẩm
- **:ProductDAO** — control, truy vấn dữ liệu sản phẩm
- **:ContractDAO** — control, truy vấn dữ liệu hợp đồng

### ASCII Sequence Diagram

```
Staff                StatProductFrm           ProductDAO                ContractDAO
  |                        |                       |                         |
  |--- selectFunction ---> |                       |                         |
  |                        |--- new StatProductFrm |                         |
  |                        |--- show() -----------> |                         |
  | <--- showForm ---------|                       |                         |
  |                        |                       |                         |
  |--- enterStartDate ---->|                       |                         |
  |    (01/01/2026)        |                       |                         |
  |--- enterEndDate ------>|                       |                         |
  |    (31/12/2026)        |                       |                         |
  |--- clickView --------->|                       |                         |
  |                        |                       |                         |
  |                        |--- getCategoryStat    |                         |
  |                        |    ByRevenue(         |                         |
  |                        |      startDate,       |                         |
  |                        |      endDate) ------->|                         |
  |                        |                       |                         |
  |                        |<-- List<CategoryStat> |                         |
  |                        |                       |                         |
  | <--- showCategoryTable |                       |                         |
  |                        |                       |                         |
  |--- clickCategory ----->|                       |                         |
  |    (e.g. "Dien thoai") |                       |                         |
  |                        |                       |                         |
  |                        |--- getItemDetail      |                         |
  |                        |    ByCategory(        |                         |
  |                        |      category,        |                         |
  |                        |      startDate,       |                         |
  |                        |      endDate) ------->|                         |
  |                        |                       |                         |
  |                        |<-- List<ItemDetailDTO>|                         |
  |                        |                       |                         |
  | <--- showItemTable ----|                       |                         |
  |                        |                       |                         |
  |--- clickItem --------->|                       |                         |
  |    (e.g. "iPhone 15")  |                       |                         |
  |                        |                       |                         |
  |                        |--- getContracts       |                         |
  |                        |    ByProduct(         |                         |
  |                        |      productId,       |                         |
  |                        |      startDate,       |                         |
  |                        |      endDate) --------|------------------------>|
  |                        |                       |                         |
  |                        |<-- List<ContractDTO> -|-------------------------|
  |                        |                       |                         |
  | <--- showContractList -|                       |                         |
  |                        |                       |                         |
```

### Bảng message flow chi tiết

| Bước | Từ | Đến | Message | Ghi chú |
|------|-----|-----|---------|---------|
| 1 | Staff | StatProductFrm | `selectFunction()` | Staff chọn menu Statistics → Statistics of product |
| 2 | StatProductFrm | StatProductFrm | `new StatProductFrm()` | Khởi tạo form thống kê |
| 3 | StatProductFrm | Staff | `showForm()` | Hiển thị giao diện với ô nhập ngày, nút View |
| 4 | Staff | StatProductFrm | `enterStartDate("01/01/2026")` | Staff nhập ngày bắt đầu |
| 5 | Staff | StatProductFrm | `enterEndDate("31/12/2026")` | Staff nhập ngày kết thúc |
| 6 | Staff | StatProductFrm | `clickView()` | Staff nhấn nút View |
| 7 | StatProductFrm | StatProductFrm | `validateDates(startDate, endDate)` | Kiểm tra ngày hợp lệ |
| 8 | StatProductFrm | ProductDAO | `getCategoryStatByRevenue(startDate, endDate)` | Truy vấn danh mục sản phẩm theo doanh thu |
| 9 | ProductDAO | ProductDAO | `SELECT category, COUNT(contractId), SUM(interest) FROM tblContractItem JOIN tblContract ... GROUP BY category ORDER BY SUM(interest) DESC` | SQL thực thi |
| 10 | ProductDAO | StatProductFrm | `return List<CategoryStatDTO>` | Trả về danh sách thống kê danh mục |
| 11 | StatProductFrm | Staff | `showCategoryTable(list)` | Hiển thị bảng danh mục: Điện thoại, Laptop, Điện tử, Phụ kiện |
| 12 | Staff | StatProductFrm | `clickCategory("Dien thoai")` | Staff click vào 1 dòng danh mục |
| 13 | StatProductFrm | ProductDAO | `getItemDetailByCategory("Dien thoai", startDate, endDate)` | Truy vấn chi tiết sản phẩm trong danh mục |
| 14 | ProductDAO | ProductDAO | `SELECT productId, name, COUNT(contractId), SUM(subtotal - quantity*unitPrice) FROM ... WHERE category = ? GROUP BY productId ORDER BY SUM(profit) DESC` | SQL thực thi |
| 15 | ProductDAO | StatProductFrm | `return List<ItemDetailDTO>` | Trả về danh sách chi tiết sản phẩm |
| 16 | StatProductFrm | Staff | `showItemTable(list)` | Hiển thị bảng sản phẩm: iPhone 15, Samsung S24, OPPO Reno 11 |
| 17 | Staff | StatProductFrm | `clickItem("iPhone 15")` | Staff click vào 1 dòng sản phẩm |
| 18 | StatProductFrm | ProductDAO | `getProductById(productId)` | Lấy productId từ sản phẩm được chọn |
| 19 | ProductDAO | StatProductFrm | `return Product` | Trả về đối tượng Product |
| 20 | StatProductFrm | ContractDAO | `getContractsByProduct(productId, startDate, endDate)` | Truy vấn hợp đồng chứa sản phẩm |
| 21 | ContractDAO | ContractDAO | `SELECT c.contractCode, cu.name, c.totalLoanValue, ci.interest FROM tblContract c JOIN tblContractItem ci ... JOIN tblCustomer cu ... ORDER BY c.contractDate ASC` | SQL thực thi |
| 22 | ContractDAO | StatProductFrm | `return List<ContractDTO>` | Trả về danh sách hợp đồng |
| 23 | StatProductFrm | Staff | `showContractList(list)` | Hiển thị danh sách hợp đồng: HD001, HD005, HD012 |

---

## Câu 5: Blackbox Testcase — TC01

### Testcase: Thống kê sản phẩm theo doanh thu — Kỳ 01/01/2026 đến 31/12/2026

#### Database trước khi test

**tblUser**

| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | 123456 | Nguyen Van Staff | Staff |

**tblPartner**

| partnerId | name | phone | address | representativeName |
|-----------|------|-------|---------|-------------------|
| P001 | Samsung Vietnam | 0901000000 | HCM City | Tran Van B |
| P002 | Apple Vietnam | 0902000000 | HCM City | Le Van C |

**tblCustomer**

| customerId | name | phone | address | idCard | dateOfBirth |
|------------|------|-------|---------|--------|-------------|
| KH001 | Nguyen Van A | 0901111111 | Ha Noi | 012345678901 | 1990-01-15 |
| KH002 | Pham Van D | 0902222222 | Da Nang | 098765432101 | 1985-06-20 |
| KH003 | Le Thi C | 0903333333 | HCM City | 011122233344 | 1992-03-10 |

**tblProduct**

| productId | name | category | unitPrice | description | partnerId |
|-----------|------|----------|-----------|-------------|-----------|
| SP001 | iPhone 15 | Dien thoai | 25,000,000 | iPhone 15 128GB | P002 |
| SP002 | Samsung Galaxy S24 | Dien thoai | 22,000,000 | Galaxy S24 256GB | P001 |
| SP003 | OPPO Reno 11 | Dien thoai | 12,000,000 | OPPO Reno 11 5G | P001 |
| SP004 | MacBook Air M3 | Laptop | 30,000,000 | MacBook Air 13 inch | P002 |

**tblContract**

| contractId | contractCode | contractDate | totalLoanValue | totalInterest | status | customerId | staffId |
|------------|-------------|--------------|----------------|---------------|--------|------------|---------|
| C001 | HD001 | 2026-03-15 | 25,000,000 | 3,500,000 | Active | KH001 | U001 |
| C002 | HD005 | 2026-06-20 | 47,000,000 | 6,500,000 | Active | KH002 | U001 |
| C003 | HD012 | 2026-09-10 | 22,000,000 | 2,800,000 | Active | KH003 | U001 |
| C004 | HD015 | 2025-05-10 | 30,000,000 | 4,000,000 | Active | KH001 | U001 |

**tblContractItem**

| contractItemId | contractId | productId | quantity | unitPrice | interest | subtotal |
|---------------|------------|-----------|----------|-----------|----------|----------|
| CI001 | C001 | SP001 | 1 | 25,000,000 | 3,500,000 | 25,000,000 |
| CI002 | C002 | SP001 | 1 | 25,000,000 | 3,000,000 | 25,000,000 |
| CI003 | C002 | SP002 | 1 | 22,000,000 | 3,500,000 | 22,000,000 |
| CI004 | C003 | SP002 | 1 | 22,000,000 | 2,800,000 | 22,000,000 |
| CI005 | C004 | SP004 | 1 | 30,000,000 | 4,000,000 | 30,000,000 |

**Lưu ý dữ liệu test:** Hợp đồng C004 có ngày `2025-05-10` nằm ngoài kỳ thống kê (01/01/2026 – 31/12/2026) để kiểm tra tính chính xác của bộ lọc theo ngày.

#### Database sau khi test

- Không thay đổi. Chức năng thống kê là read-only, không thêm/sửa/xóa dữ liệu.

#### Bảng các bước test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập hệ thống | Username: `staff01`, Password: `123456` | Đăng nhập thành công, hiển thị giao diện chính (HomeFrm) |
| 2 | Staff chọn Statistics → Statistics of product | — | Hiển thị giao diện StatProductFrm với ô nhập ngày bắt đầu, ngày kết thúc, nút View |
| 3 | Staff nhập ngày bắt đầu | `01/01/2026` | Ô txtStartDate hiển thị "01/01/2026" |
| 4 | Staff nhập ngày kết thúc | `31/12/2026` | Ô txtEndDate hiển thị "31/12/2026" |
| 5 | Staff nhấn nút **View** | — | Bảng tblCategoryStat hiển thị 1 danh mục: Dien thoai (3 hóa đơn: C001, C002, C003; tổng lãi: 12,800,000đ). Không hiển thị Laptop vì hợp đồng C004 nằm ngoài kỳ |
| 6 | Staff click vào dòng **Dien thoai** | — | Bảng tblItemStat hiển thị 2 sản phẩm: SP001 - iPhone 15 (2 hóa đơn, lợi nhuận 6,500,000đ), SP002 - Samsung Galaxy S24 (2 hóa đơn, lợi nhuận 6,300,000đ). SP003 không xuất hiện vì không có hợp đồng nào trong kỳ. Sắp xếp giảm dần theo lợi nhuận |
| 7 | Staff click vào dòng **iPhone 15** | — | Bảng tblContractList hiển thị 2 hợp đồng: HD001 - Nguyen Van A - vay 25,000,000đ - lãi 3,500,000đ, HD005 - Pham Van D - vay 47,000,000đ - lãi 3,000,000đ. Sắp xếp theo thời gian tăng dần (15/03/2026 trước 20/06/2026) |

#### Giải thích dữ liệu kỳ vọng

- **Dien thoai tổng lãi:** C001 lãi 3,500,000 (SP001) + C002 lãi 3,000,000 (SP001) + 3,500,000 (SP002) + C003 lãi 2,800,000 (SP002) = 12,800,000đ
- **iPhone 15 lợi nhuận:** C001 (3,500,000) + C002 (3,000,000) = 6,500,000đ
- **Samsung S24 lợi nhuận:** C002 (3,500,000) + C003 (2,800,000) = 6,300,000đ
- **Laptop không hiển thị:** C004 ngày 2025-05-10 nằm ngoài kỳ 2026
