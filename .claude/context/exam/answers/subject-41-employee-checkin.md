# Subject No. 41 — Part-time Employee — Module "Checkin/Checkout"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Check-in / Check-out

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Checkin** (hoặc **Checkout**). |
| 2 | Giao diện: ô nhập mã nhân viên, nút Submit. |
| 3 | Staff nhập mã NV "B23DCCE060", nhấn Submit. |
| 4 | Hệ thống lưu thời gian checkin (hoặc checkout) vào database. |
| 5 | Hệ thống báo cáo: "Checkin thanh luc 08:05:00". |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**Attendance:** id, employeeId, shiftId, checkinTime, checkoutTime

Quan hệ:
```
Employee 1 --- n Attendance n --- 1 Shift
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**CheckinFrm:**
- `inEmployeeCode`: ô nhập mã NV
- `subSubmit`: nút Submit

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| AttendanceDAO | `checkin(employeeCode): boolean` |
| AttendanceDAO | `checkout(employeeCode): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → CheckinFrm: enter "B23DCCE060", click Submit
2. CheckinFrm → AttendanceDAO: checkin("B23DCCE060")
3. AttendanceDAO: INSERT INTO tblAttendance (checkinTime = now)
4. CheckinFrm: show "Checkin thanh luc 08:05:00"

---

## Câu 5: Blackbox Testcase

### TC01: Checkin thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "B23DCCE060", Submit | "Checkin thanh luc 08:05:00" |

**Database sau:** Thêm tblAttendance (checkinTime = 08:05:00, checkoutTime = null).
