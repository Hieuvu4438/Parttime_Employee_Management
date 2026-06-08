# Subject No. 14 — Tour Booking — Module "Revenue statistics by site"

> **Domain:** Tour Booking Management
> **Entity classes:** Tour, Customer, Ticket, TourSchedule, Site, TourSite, User

---

## Câu 1: Scenario — Thống kê doanh thu theo điểm tham quan

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Revenue by site**. |
| 2 | Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách điểm tham quan: tên, số tour đi qua, tổng số khách, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 5 | Staff nhấn vào "Vinh Ha Long". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn KH đã đặt tour qua điểm đó: mã KH, tên KH, ngày khởi hành, tên tour, tổng số khách, tổng tiền. |
| 7 | Staff nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**SiteStat:**
- `site: Site`
- `totalTours: int`
- `totalVisitors: int`
- `totalRevenue: float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**SiteStatFrm:**
- `inStartDate`, `inEndDate`: ô nhập ngày
- `subView`: nút View
- `outsubListSiteStat`: bảng thống kê (click được)
- `outListInvoice`: chi tiết hóa đơn

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| SiteDAO | `getSiteStat(startDate, endDate): List<SiteStat>` | Thống kê điểm tham quan |
| TicketDAO | `getTicketsBySite(siteId, startDate, endDate): List<Ticket>` | Vé theo điểm |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `SiteStatFrm`, `SiteDAO`, `TicketDAO`.
2. Message flow:
   - Staff → SiteStatFrm: enter dates, click View
   - SiteStatFrm → SiteDAO: getSiteStat(dates)
   - SiteDAO → SiteStatFrm: return List<SiteStat>
   - Staff → SiteStatFrm: click "Vinh Ha Long"
   - SiteStatFrm → TicketDAO: getTicketsBySite(siteId, dates)
   - TicketDAO → SiteStatFrm: return List<Ticket>

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Nhập ngày, View | Bảng: Vinh Ha Long (5 tour, 50 khách, 25,000,000đ) |
| 2 | Nhấn Vinh Ha Long | Chi tiết hóa đơn theo tour |
| 3 | Back | Quay về bảng |

**Database sau:** Không thay đổi.
