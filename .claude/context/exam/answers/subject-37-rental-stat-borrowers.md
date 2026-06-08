# Subject No. 37 — Book Rental — Module "Statistics of borrowers"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Thống kê người mượn

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Borrower statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách KH: mã, tên, CMND, SĐT, địa chỉ, tổng lần mượn, tổng tiền đã trả. Sắp xếp: tổng lần mượn giảm → tổng tiền giảm. |
| 5 | Staff nhấn vào KH "Nguyen Van A". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: ngày mượn, tổng sách mượn, tổng tiền. |

---

## Câu 2-3: Entity + Design

**BorrowerStat:** customer, totalLoans, totalPaid

DAO: `CustomerDAO.getBorrowerStat(startDate, endDate): List<BorrowerStat>`

---

## Câu 4: Sequence Diagram

1. Staff → BorrowerStatFrm: enter dates, click View
2. BorrowerStatFrm → CustomerDAO: getBorrowerStat(dates)
3. Staff → BorrowerStatFrm: click customer
4. BorrowerStatFrm → RentalDAO: getRentalsByCustomer(customerId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Nguyen Van A (10 lần, 5,000,000đ), Tran Thi B (8 lần, 3,200,000đ) |
| 2 | Nhấn Nguyen Van A → chi tiết hóa đơn |
