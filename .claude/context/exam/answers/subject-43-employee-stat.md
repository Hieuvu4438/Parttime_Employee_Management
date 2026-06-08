# Subject No. 43 — Part-time Employee — Module "Statistics of employees"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Thống kê nhân viên

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Employee statistics**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 3 | Staff nhập 01/01/2026 - 31/12/2026. |
| 4 | Hệ thống hiển thị danh sách NV: mã, tên, SĐT, tổng giờ ca, tổng giờ tăng ca, tổng giờ trễ, tổng giờ thực tế, tổng tiền thực nhận. Sắp xếp: tổng giờ giảm dần. |
| 5 | Staff nhấn vào NV "B". |
| 6 | Hệ thống hiển thị chi tiết: mỗi dòng 1 ca: ngày, ca, giờ checkin, checkout, giờ ca, giờ tăng ca, giờ trễ, tổng giờ thực tế, tổng tiền. |

---

## Câu 2-3: Entity + Design

**EmployeeStat:** employee, totalShiftHours, totalOvertime, totalLateHours, totalActualHours, totalReceived

DAO: `EmployeeDAO.getEmployeeStat(startDate, endDate): List<EmployeeStat>`

---

## Câu 4: Sequence Diagram

1. Staff → EmployeeStatFrm: enter dates, click View
2. EmployeeStatFrm → EmployeeDAO: getEmployeeStat(dates)
3. Staff → EmployeeStatFrm: click employee
4. EmployeeStatFrm → AttendanceDAO: getAttendanceDetail(employeeId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: B (200h ca, 20h tăng ca, 5h trễ, 215h thực tế, 4,500,000đ) |
| 2 | Nhấn B → chi tiết từng ca |
