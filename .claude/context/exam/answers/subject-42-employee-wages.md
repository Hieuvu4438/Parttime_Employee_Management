# Subject No. 42 — Part-time Employee — Module "Calculate this week's wages"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Tính lương tuần

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Calculate wages**. |
| 2 | Giao diện: ô nhập ngày bắt đầu, ngày kết thúc tuần. |
| 3 | Staff nhập 09/06/2026 - 15/06/2026. |
| 4 | Hệ thống hiển thị bảng lương: mã NV, tên, SĐT, tổng giờ ca, tổng tiền ca, tổng giờ tăng ca, tổng giờ đi trễ/về sớm, tổng phạt, tổng thực nhận. Sắp xếp theo tên. |
| 5 | Staff nhấn vào NV "B". |
| 6 | Hệ thống hiển thị chi tiết: mỗi dòng 1 ca: ngày, thứ, ca, giờ checkin, checkout, giờ ca, tiền ca, giờ tăng ca, tiền tăng ca, giờ trễ, phạt, tổng ca. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**Wage:** id, employeeId, weekStart, weekEnd, totalShiftHours, totalShiftMoney, totalOvertime, totalLateHours, totalFines, totalReceived

**WageDetail:** id, wageId, shiftId, checkinTime, checkoutTime, shiftHours, shiftMoney, overtimeHours, overtimeMoney, lateHours, fines, totalShift

---

## Câu 3: Thiết kế tĩnh

### View classes

**WageFrm:**
- `inStartDate`, `inEndDate`: ô nhập ngày
- `outsubListWage`: bảng lương (click được)
- `outListWageDetail`: chi tiết lương

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| WageDAO | `calculateWages(startDate, endDate): List<Wage>` |
| WageDetailDAO | `getWageDetail(employeeId, startDate, endDate): List<WageDetail>` |

---

## Câu 4: Sequence Diagram

1. Staff → WageFrm: enter dates
2. WageFrm → WageDAO: calculateWages(09/06/2026, 15/06/2026)
3. WageDAO: query tblAttendance, tblSchedule, calculate
4. WageDAO → WageFrm: return List<Wage>
5. Staff → WageFrm: click employee "B"
6. WageFrm → WageDetailDAO: getWageDetail(employeeId, dates)
7. WageDetailDAO → WageFrm: return List<WageDetail>

---

## Câu 5: Blackbox Testcase

### TC01: Tính lương có dữ liệu

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập ngày, View | Bảng: B (32h ca, 640,000đ, 4h tăng ca, 120,000đ, 1h trễ, 30,000đ phạt, 730,000đ thực nhận) |
| 2 | Nhấn B | Chi tiết: 4 ca (ngày, giờ, tiền) |

**Database sau:** Không thay đổi (chỉ đọc dữ liệu).
