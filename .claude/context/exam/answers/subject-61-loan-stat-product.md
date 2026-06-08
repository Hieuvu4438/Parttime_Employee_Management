# Subject No. 61 — Installment Loan — Module "Statistics of product"

> **Domain:** Installment Loan Management

---

## Câu 1: Scenario (Bảng diễn biến)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập hệ thống, chọn chức năng **Statistics of product** |
| 2 | Hệ thống hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc và nút **Xem thống kê** |
| 3 | Staff nhập ngày bắt đầu `01/01/2026`, ngày kết thúc `31/12/2026`, nhấn nút **Xem thống kê** |
| 4 | Hệ thống hiển thị danh sách thống kê theo dòng/sản phẩm: Điện thoại (20 hóa đơn, lãi 15,000,000đ), Laptop (12 hóa đơn, lãi 10,000,000đ), Điện tử (8 hóa đơn, lãi 5,000,000đ), ... — sắp xếp theo tổng lãi giảm dần |
| 5 | Staff click vào dòng **Điện thoại** |
| 6 | Hệ thống hiển thị chi tiết sản phẩm thuộc danh mục Điện thoại: iPhone 15 (8 hóa đơn, lãi 8,000,000đ), Samsung S24 (6 hóa đơn, lãi 4,000,000đ), ... — sắp xếp theo lãi giảm dần |
| 7 | Staff click vào dòng **iPhone 15** |
| 8 | Hệ thống hiển thị danh sách hợp đồng của iPhone 15 trong kỳ thống kê, sắp xếp theo thời gian: HD001 - Nguyen Van A - vay 25,000,000đ - lãi 3,000,000đ, HD005 - Pham Van D - vay 25,000,000đ - lãi 2,500,000đ |

---

## Câu 2: Entity Class Diagram

### Bảng thuộc tính

| Entity | Thuộc tính |
|--------|-----------|
| **Item** | itemId (PK), name, category, unitPrice, description |
| **Contract** | contractId (PK), contractCode, contractDate, totalLoanValue, totalInterest, status, customerId (FK), staffId (FK) |
| **ContractItem** | contractItemId (PK), contractId (FK), itemId (FK), quantity, unitPrice, interest |
| **Customer** | customerId (PK), name, phone, address, idCard |
| **Partner** | partnerId (PK), name, phone, address |
| **User** | userId (PK), username, password, fullName, role |

### Mối quan hệ

| Entity 1 | Entity 2 | Mối quan hệ | Mô tả |
|-----------|----------|-------------|-------|
| Contract | Customer | N : 1 | Một khách hàng có nhiều hợp đồng |
| Contract | User | N : 1 | Một nhân viên lập nhiều hợp đồng |
| Contract | ContractItem | 1 : N | Một hợp đồng có nhiều chi tiết sản phẩm |
| Item | ContractItem | 1 : N | Một sản phẩm xuất hiện trong nhiều chi tiết hợp đồng |
| Partner | Contract | 1 : N | Một đối tác có nhiều hợp đồng (nếu áp dụng) |

---

## Câu 3: Thiết kế tĩnh

### View Class: `StatProductFrm`

| Thành phần | Kiểu | Mô tả |
|------------|------|-------|
| `inStartDate` | JTextField | Ô nhập ngày bắt đầu kỳ thống kê |
| `inEndDate` | JTextField | Ô nhập ngày kết thúc kỳ thống kê |
| `subView` | JButton | Nút "Xem thống kê" |
| `outCategoryStatTable` | JTable | Bảng hiển thị thống kê theo danh mục (tên danh mục, tổng hóa đơn, tổng lãi) |
| `outItemStatTable` | JTable | Bảng hiển thị chi tiết sản phẩm theo danh mục (mã, tên, tổng hóa đơn, tổng lãi) |
| `outContractList` | JTable | Bảng hiển thị danh sách hợp đồng của 1 sản phẩm (mã hợp đồng, tên KH, tổng vay, tổng lãi) |

### DAO Classes

#### `ItemDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `getItemStatisticsByRevenue(startDate, endDate)` | `List<ItemStatDTO>` | Lấy danh sách danh mục sản phẩm kèm tổng hóa đơn, tổng lãi trong kỳ, sắp xếp giảm dần theo lãi |
| `getItemDetailsByCategory(category, startDate, endDate)` | `List<ItemDetailDTO>` | Lấy chi tiết sản phẩm trong 1 danh mục kèm tổng hóa đơn, tổng lãi, sắp xếp giảm dần theo lãi |

#### `ContractDAO`

| Phương thức | Kiểu trả về | Mô tả |
|-------------|-------------|-------|
| `getContractsByItem(itemId, startDate, endDate)` | `List<ContractDTO>` | Lấy danh sách hợp đồng của 1 sản phẩm trong kỳ thống kê, sắp xếp theo thời gian tăng dần |

---

## Câu 4: Sequence Diagram

### Lifelines

- **Staff** (Actor)
- **StatProductFrm** (Boundary)
- **ItemDAO** (Data Access)
- **ContractDAO** (Data Access)

### Message Flow

```
Staff                StatProductFrm              ItemDAO                  ContractDAO
  |                       |                         |                         |
  |--- selectFunction --> |                         |                         |
  |                       |                         |                         |
  | <-- showForm -------- |                         |                         |
  |                       |                         |                         |
  |--- enterDates ------> |                         |                         |
  |    (startDate,        |                         |                         |
  |     endDate)          |                         |                         |
  |                       |                         |                         |
  |--- clickView -------->|                         |                         |
  |                       |--- getItemStatistics    |                         |
  |                       |    ByRevenue() -------->|                         |
  |                       |                         |                         |
  |                       | <-- List<ItemStatDTO> --|                         |
  |                       |                         |                         |
  | <-- showCategoryTable |                         |                         |
  |                       |                         |                         |
  |--- clickCategory ---->|                         |                         |
  |                       |--- getItemDetails       |                         |
  |                       |    ByCategory() ------->|                         |
  |                       |                         |                         |
  |                       | <-- List<ItemDetailDTO>-|                         |
  |                       |                         |                         |
  | <-- showItemTable ----|                         |                         |
  |                       |                         |                         |
  |--- clickItem -------->|                         |                         |
  |                       |--- getContracts         |                         |
  |                       |    ByItem() ------------|------------------------>|
  |                       |                         |                         |
  |                       | <-- List<ContractDTO> --|-------------------------|
  |                       |                         |                         |
  | <-- showContractList -|                         |                         |
```

---

## Câu 5: Blackbox Testcase — TC01

### Testcase: Thống kê sản phẩm theo doanh thu trong kỳ 01/01/2026 – 31/12/2026

#### Database trước khi test

**tblItem**

| itemId | name | category | unitPrice |
|--------|------|----------|-----------|
| I001 | iPhone 15 | Điện thoại | 25,000,000 |
| I002 | Samsung S24 | Điện thoại | 22,000,000 |
| I003 | MacBook Air | Laptop | 30,000,000 |

**tblContract**

| contractId | contractCode | contractDate | totalLoanValue | status | customerId | staffId |
|------------|-------------|--------------|----------------|--------|------------|---------|
| C001 | HD001 | 2026-03-15 | 25,000,000 | Active | KH001 | U001 |
| C002 | HD005 | 2026-06-20 | 25,000,000 | Active | KH002 | U001 |

**tblContractDetail**

| detailId | contractId | itemId | quantity | unitPrice | interest |
|----------|-----------|--------|----------|-----------|----------|
| D001 | C001 | I001 | 1 | 25,000,000 | 3,000,000 |
| D002 | C002 | I001 | 1 | 25,000,000 | 2,500,000 |
| D003 | C001 | I003 | 1 | 30,000,000 | 4,000,000 |

**tblCustomer**

| customerId | name | phone |
|------------|------|-------|
| KH001 | Nguyen Van A | 0901111111 |
| KH002 | Pham Van D | 0902222222 |

#### Database sau khi test

- Không thay đổi (chức năng chỉ đọc dữ liệu thống kê)

#### Bảng các bước test

| Bước | Hành động | Dữ liệu đầu vào | Kết quả mong đợi |
|------|-----------|-----------------|-----------------|
| 1 | Staff đăng nhập | Username/password hợp lệ | Đăng nhập thành công, hiển thị giao diện chính |
| 2 | Staff chọn Statistics of product | — | Hiển thị giao diện thống kê với ô nhập ngày bắt đầu, ngày kết thúc, nút Xem thống kê |
| 3 | Staff nhập ngày bắt đầu | `01/01/2026` | Ô startDate hiển thị "01/01/2026" |
| 4 | Staff nhập ngày kết thúc | `31/12/2026` | Ô endDate hiển thị "31/12/2026" |
| 5 | Staff nhấn "Xem thống kê" | — | Hiển thị danh sách danh mục: Điện thoại (2 hóa đơn, lãi 5,500,000đ), Laptop (1 hóa đơn, lãi 4,000,000đ) — sắp xếp giảm dần theo lãi |
| 6 | Staff click dòng "Điện thoại" | — | Hiển thị chi tiết: iPhone 15 (2 hóa đơn, lãi 5,500,000đ) — sắp xếp giảm dần theo lãi |
| 7 | Staff click dòng "iPhone 15" | — | Hiển thị danh sách hợp đồng: HD001 - Nguyen Van A - vay 25,000,000đ - lãi 3,000,000đ; HD005 - Pham Van D - vay 25,000,000đ - lãi 2,500,000đ — sắp xếp theo thời gian tăng dần |
