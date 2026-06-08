# Subject No. 25 — Store Management — Module "Statistics of sub-agence"

> **Domain:** Store Management

---

## Câu 1: Scenario — Thống kê đại lý

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Statistics** → **Sub-agent statistics**. |
| 2 | Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Manager nhập 01/01/2026 - 31/12/2026, nhấn View. |
| 4 | Hệ thống hiển thị danh sách đại lý: mã, tên, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 5 | Manager nhấn vào "Dai ly A". |
| 6 | Hệ thống hiển thị chi tiết hóa đơn: ngày, tổng số mặt hàng, tổng tiền. |

---

## Câu 2-3: Entity + Design

**AgentStat:** agent, totalRevenue

DAO: `SubAgentDAO.getAgentStat(startDate, endDate): List<AgentStat>`

---

## Câu 4: Sequence Diagram

1. Manager → AgentStatFrm: enter dates, click View
2. AgentStatFrm → SubAgentDAO: getAgentStat(dates)
3. Manager → AgentStatFrm: click "Dai ly A"
4. AgentStatFrm → ExportBillDAO: getExportByAgent(agentId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Dai ly A (20,000,000đ), Dai ly B (15,000,000đ) |
| 2 | Nhấn Dai ly A → chi tiết hóa đơn |
