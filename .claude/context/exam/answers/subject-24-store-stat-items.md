# Subject No. 24 — Store Management — Module "Statistics of items"

> **Domain:** Store Management

---

## Câu 1: Scenario — Thống kê mặt hàng

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Statistics** → **Item statistics**. |
| 2 | Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Manager nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách mặt hàng: mã, tên, số lượng bán, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 5 | Manager nhấn vào "Banh Oreo". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn đại lý đã mua sản phẩm. |

---

## Câu 2-3: Entity + Design

**ItemStat:** item, totalQuantitySold, totalRevenue

DAO: `ItemDAO.getItemStat(startDate, endDate): List<ItemStat>`

---

## Câu 4: Sequence Diagram

1. Manager → ItemStatFrm: enter dates, click View
2. ItemStatFrm → ItemDAO: getItemStat(dates)
3. Manager → ItemStatFrm: click "Banh Oreo"
4. ItemStatFrm → ExportDetailDAO: getExportByItem(itemId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Banh Oreo (500 bán, 7,500,000đ), Coca (800 bán, 6,400,000đ) |
| 2 | Nhấn Banh Oreo → chi tiết hóa đơn đại lý |
