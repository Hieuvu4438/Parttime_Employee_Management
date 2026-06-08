# Subject No. 19 — Restaurant Order — Module "Statistics of visitors by time slot"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Thống kê khách theo khung giờ

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Statistics** → **Visitors by time slot**. |
| 2 | Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Manager nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách khung giờ: giờ bắt đầu, giờ kết thúc, số khách TB, doanh thu TB/khách, tổng doanh thu theo giờ. |
| 5 | Manager nhấn vào khung giờ "11:00-13:00". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn trong khung giờ: mã hóa đơn, tên KH, ngày, tổng số món, tổng tiền. |

---

## Câu 2-3: Entity + Design

**TimeSlotStat:** id, startTime, endTime, avgVisitors, avgRevenuePerGuest, totalRevenue

DAO: `TimeSlotStatDAO.getTimeSlotStat(startDate, endDate): List<TimeSlotStat>`

---

## Câu 4: Sequence Diagram

1. Manager → TimeSlotStatFrm: enter dates, click View
2. TimeSlotStatFrm → TimeSlotStatDAO: getTimeSlotStat(dates)
3. Manager → TimeSlotStatFrm: click slot "11:00-13:00"
4. TimeSlotStatFrm → InvoiceDAO: getInvoicesBySlot(slot, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: 11:00-13:00 (50 khách, 80,000đ/khách, 4,000,000đ), 18:00-20:00 (80 khách, 100,000đ/khách, 8,000,000đ) |
| 2 | Nhấn 11:00-13:00 → chi tiết hóa đơn |
