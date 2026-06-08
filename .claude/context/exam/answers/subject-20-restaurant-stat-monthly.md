# Subject No. 20 — Restaurant Order — Module "Statistical monthly revenue"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Thống kê doanh thu theo tháng

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Statistics** → **Monthly revenue**. |
| 2 | Hệ thống hiển thị danh sách 12 tháng gần nhất: tên tháng, tổng số khách, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 3 | Manager nhấn vào tháng "05/2026". |
| 4 | Hệ thống hiển thị chi tiết hóa đơn trong tháng: mã hóa đơn, tên KH, ngày giờ, tổng số món, tổng tiền. |

---

## Câu 2-3: Entity + Design

**MonthlyStat:** month, year, totalGuests, totalRevenue

DAO: `InvoiceDAO.getMonthlyStat(): List<MonthlyStat>`

---

## Câu 4: Sequence Diagram

1. Manager → MonthlyStatFrm: open
2. MonthlyStatFrm → InvoiceDAO: getMonthlyStat()
3. Manager → MonthlyStatFrm: click "05/2026"
4. MonthlyStatFrm → InvoiceDAO: getInvoicesByMonth(5, 2026)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng 12 tháng: 05/2026 (500 khách, 50,000,000đ), 04/2026 (400 khách, 40,000,000đ)... |
| 2 | Nhấn 05/2026 → chi tiết hóa đơn |
