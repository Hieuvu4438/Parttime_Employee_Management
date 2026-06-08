# Subject No. 44 — Part-time Employee — Module "Statistics of best employees"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Thống kê NV xuất sắc

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Best employees**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026. |
| 4 | Hệ thống hiển thị danh sách NV: mã, tên, SĐT, tổng giờ thực tế, tổng tiền, tổng giờ trễ, tổng phạt. Sắp xếp: tổng giờ trễ tăng dần (ít trễ nhất → xuất sắc nhất). |
| 5 | Staff nhấn vào NV "B". |
| 6 | Hệ thống hiển thị chi tiết: mỗi dòng 1 ca: ngày, ca, giờ checkin, checkout, giờ thực tế, tiền, giờ trễ, phạt. |

---

## Câu 2-3: Entity + Design

**BestEmployeeStat:** employee, totalActualHours, totalReceived, totalLateHours, totalFines

DAO: `EmployeeDAO.getBestEmployeeStat(startDate, endDate): List<BestEmployeeStat>`

---

## Câu 4: Sequence Diagram

1. Staff → BestEmployeeStatFrm: enter dates, click View
2. BestEmployeeStatFrm → EmployeeDAO: getBestEmployeeStat(dates)
3. Staff → BestEmployeeStatFrm: click employee
4. BestEmployeeStatFrm → AttendanceDAO: getAttendanceDetail(employeeId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: A (220h, 4,800,000đ, 0h trễ, 0đ phạt), B (200h, 4,200,000đ, 2h trễ, 60,000đ) |
| 2 | Nhấn A → chi tiết: 0 lần trễ |
