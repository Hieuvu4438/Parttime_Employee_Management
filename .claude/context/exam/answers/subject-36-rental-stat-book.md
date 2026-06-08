# Subject No. 36 — Book Rental — Module "Statistics of book"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Thống kê sách thuê

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Book statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách sách: mã, tên, tác giả, NXB, năm xuất bản, tổng lần thuê, tổng doanh thu. Sắp xếp: tổng lần thuê giảm → tổng doanh thu giảm. |
| 5 | Staff nhấn vào sách "Lap trinh Java". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: mã, tên người mượn, ngày mượn, ngày trả, tổng tiền. |

---

## Câu 2-3: Entity + Design

**BookStat:** bookTitle, totalRentals, totalRevenue

DAO: `BookTitleDAO.getBookStat(startDate, endDate): List<BookStat>`

---

## Câu 4: Sequence Diagram

1. Staff → BookStatFrm: enter dates, click View
2. BookStatFrm → BookTitleDAO: getBookStat(dates)
3. Staff → BookStatFrm: click book
4. BookStatFrm → RentalDAO: getRentalsByBook(bookId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Lap trinh Java (50 lần, 2,500,000đ), Co so du lieu (40 lần, 1,600,000đ) |
| 2 | Nhấn Lap trinh Java → chi tiết hóa đơn |
