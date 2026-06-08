# Subject No. 35 — Book Rental — Module "Return and pay"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Trả sách và thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Return and pay**. |
| 2 | Giao diện: ô nhập tên KH, nút Search. |
| 3 | Staff nhập "Nguyen Van A", nhấn Search → chọn KH. |
| 4 | Hệ thống hiển thị danh sách sách đang mượn: mã, tên, ngày mượn, giá thuê, số tiền thuê đến ngày trả. Cuối: checkbox chọn trả. |
| 5 | Staff tick chọn sách trả, nhập trạng thái sách (bình thường/hư hỏng), nhập tiền phạt (nếu có). |
| 6 | Staff nhấn **Payment**. |
| 7 | Hệ thống hiển thị hóa đơn: thông tin KH + danh sách sách trả + tổng tiền. |
| 8 | Staff nhấn **Confirm**. Hệ thống lưu vào database. |

---

## Câu 2-3: Entity + Design

Cùng entity classes. RentalDetail thêm: `returnDate`, `status`, `fine`

**ReturnBookFrm:**
- `inCustomerName`, `subSearch`, `outsubListCustomer`
- `outListRentalDetail` (có checkbox + ô nhập trạng thái + phạt)
- `subPayment`, `subConfirm`

DAO: `RentalDetailDAO.updateReturn(detailId, returnDate, status, fine): boolean`

---

## Câu 4: Sequence Diagram

1. Staff → ReturnBookFrm: search customer
2. ReturnBookFrm → CustomerDAO: searchCustomer(name)
3. ReturnBookFrm → RentalDetailDAO: getUnreturnedBooks(customerId)
4. Staff → ReturnBookFrm: tick books, enter status/fine
5. Staff → ReturnBookFrm: click Payment
6. ReturnBookFrm: display invoice
7. Staff → ReturnBookFrm: click Confirm
8. ReturnBookFrm → RentalDetailDAO: updateReturn(details)

---

## Câu 5: Blackbox Testcase

### TC01: Trả sách thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Tìm KH, chọn → danh sách 2 sách đang mượn |
| 2 | Tick cả 2, trạng thái "binh thuong", không phạt |
| 3 | Payment → Confirm | Hóa đơn: tổng = giá thuê × số ngày |

**Database sau:** Cập nhật tblRentalDetail (returnDate, status, fine).
