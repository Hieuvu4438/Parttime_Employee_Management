# Subject No. 38 — Book Rental — Module "Revenue Statistics"

> **Domain:** Book Rental Management

---

## Câu 1: Scenario — Thống kê doanh thu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Revenue statistics**. |
| 2 | Giao diện: combobox chọn thống kê theo tháng/quý/năm. |
| 3 | Staff chọn "Theo tháng". |
| 4 | Hệ thống hiển thị doanh thu theo tháng: tên tháng, tổng doanh thu. Sắp xếp: tháng gần nhất → cũ nhất. |
| 5 | Staff nhấn vào tháng "05/2026". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: mã, tên KH, ngày mượn, tổng sách, tổng tiền. |

---

## Câu 2-3: Entity + Design

**RevenueStat:** periodName, totalRevenue

DAO: `RentalDAO.getRevenueStat(type): List<RevenueStat>` (type = month/quarter/year)

---

## Câu 4: Sequence Diagram

1. Staff → RevenueStatFrm: select "month"
2. RevenueStatFrm → RentalDAO: getRevenueStat("month")
3. Staff → RevenueStatFrm: click "05/2026"
4. RevenueStatFrm → RentalDAO: getRentalsByMonth(5, 2026)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn "tháng" → Bảng: 05/2026 (5,000,000đ), 04/2026 (4,200,000đ) |
| 2 | Nhấn 05/2026 → chi tiết hóa đơn |
