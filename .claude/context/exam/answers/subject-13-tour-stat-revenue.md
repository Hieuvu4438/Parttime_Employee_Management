# Subject No. 13 — Tour Booking — Module "Tour statistics by revenue"

> **Domain:** Tour Booking Management
> **Entity classes:** Tour, Customer, Ticket, TourSchedule, Site, TourSite, User

---

## Câu 1: Scenario — Thống kê tour theo doanh thu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Tour statistics by revenue**. |
| 2 | Giao diện thống kê xuất hiện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách tour: mã tour, tên tour, nơi khởi hành, điểm đến, số khách TB/tour, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 5 | Staff nhấn vào tour "T001". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn KH đã đặt tour: mã KH, tên KH, ngày khởi hành, tổng số khách, tổng tiền. |
| 7 | Staff nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**TourStat:**
- `tour: Tour`
- `avgGuests: float`
- `totalRevenue: float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**TourStatFrm:**
- `inStartDate`, `inEndDate`: ô nhập ngày
- `subView`: nút View
- `outsubListTourStat`: bảng thống kê (click được)
- `outListInvoice`: chi tiết hóa đơn

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| TourDAO | `getTourStat(startDate, endDate): List<TourStat>` | Thống kê tour |
| TicketDAO | `getTicketsByTour(tourId, startDate, endDate): List<Ticket>` | Vé theo tour |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `TourStatFrm`, `TourDAO`, `TicketDAO`.
2. Message flow:
   - Staff → TourStatFrm: enter dates, click View
   - TourStatFrm → TourDAO: getTourStat(01/01/2026, 31/12/2026)
   - TourDAO → TourStatFrm: return List<TourStat>
   - TourStatFrm: display sorted by revenue DESC
   - Staff → TourStatFrm: click tour "T001"
   - TourStatFrm → TicketDAO: getTicketsByTour(1, dates)
   - TicketDAO → TourStatFrm: return List<Ticket>
   - TourStatFrm: display invoice details

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Nhập ngày, View | Bảng: T001 (15,000,000đ), T002 (8,000,000đ) |
| 2 | Nhấn T001 | Chi tiết: C001, 15/07, 2 khách, 5,000,000đ; C002, 20/07, 4 khách, 10,000,000đ |
| 3 | Back | Quay về bảng |

**Database sau:** Không thay đổi.
