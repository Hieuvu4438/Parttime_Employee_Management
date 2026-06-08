# Subject No. 34 — Book Rental — Module "Borrowing"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Mượn sách (thuê sách)

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Borrowing**. |
| 2 | Giao diện: ô nhập tên khách hàng, nút Search. |
| 3 | Staff nhập "Nguyen Van A", nhấn Search. |
| 4 | Hệ thống hiển thị danh sách KH. Staff chọn "Nguyen Van A" (hoặc thêm mới). |
| 5 | Giao diện thêm sách mượn: Staff tìm sách theo tên "Lap trinh Java". |
| 6 | Hệ thống hiển thị danh sách sách: mã, tên, tác giả, NXB, năm xuất bản, giá thuê. Staff chọn sách. |
| 7 | Sách được thêm vào danh sách mượn. Staff lặp cho các sách khác. |
| 8 | Staff nhấn **Create loan slip**. |
| 9 | Hệ thống lưu và in phiếu mượn: tên KH, ngày mượn, danh sách sách (tên, tác giả, NXB, năm, giá thuê). Dòng cuối: tổng số sách mượn. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| BookTitle | id, code, name, author, publisher, pubYear, rentalPrice, quantity |
| Customer | id, code, name, idCard, phone, address |
| Rental | id, customerId, rentalDate, totalBooks |
| RentalDetail | id, rentalId, bookTitleId, rentalPrice |
| User | id, username, password, role |

### Quan hệ

```
Customer 1 --- n Rental 1 --- n RentalDetail n --- 1 BookTitle
User 1 --- n Rental
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**BorrowBookFrm:**
- `inCustomerName`: ô nhập tên KH
- `subSearchCustomer`: nút Search
- `outsubListCustomer`: bảng KH
- `subAddCustomer`: nút thêm KH mới
- `inBookName`: ô nhập tên sách
- `subSearchBook`: nút Search
- `outsubListBook`: bảng sách
- `outListRentalDetail`: danh sách mượn
- `subCreateSlip`: nút Create loan slip

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| CustomerDAO | `searchCustomer(name): List<Customer>` |
| BookTitleDAO | `searchBook(name): List<BookTitle>` |
| RentalDAO | `addRental(rental): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → BorrowBookFrm: search "Nguyen Van A"
2. BorrowBookFrm → CustomerDAO: searchCustomer("Nguyen Van A")
3. Staff → BorrowBookFrm: select customer
4. Staff → BorrowBookFrm: search "Lap trinh Java"
5. BorrowBookFrm → BookTitleDAO: searchBook("Lap trinh Java")
6. Staff → BorrowBookFrm: select book
7. (lặp)
8. Staff → BorrowBookFrm: click Create loan slip
9. BorrowBookFrm → RentalDAO: addRental(rental)

---

## Câu 5: Blackbox Testcase

### TC01: Mượn sách thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Tìm "Nguyen Van A", chọn |
| 2 | Tìm "Lap trinh Java", chọn; tìm "Co so du lieu", chọn |
| 3 | Create slip | In phiếu: KH Nguyen Van A, ngày mượn, 2 sách, tổng = giá thuê × 2 |

**Database sau:** Thêm tblRental, 2 dòng tblRentalDetail.
