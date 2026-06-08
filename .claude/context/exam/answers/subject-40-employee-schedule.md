# Subject No. 40 — Part-time Employee — Module "Schedule next week"

> **Domain:** Part-time Employee Management

---

## Câu 1: Scenario — Xếp lịch tuần sau

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Schedule next week**. |
| 2 | Giao diện: bảng 7 dòng (Thứ 2-CN), mỗi dòng 2 cột (Ca 1, Ca 2). Mỗi cột hiển thị tên NV đã đăng ký. |
| 3 | Staff nhấn vào "Thứ 2, Ca 1". |
| 4 | Giao diện hiển thị danh sách NV đã đăng ký ca này nhưng chưa được xếp: tên NV, SĐT, tổng giờ đã xếp tuần sau. Sắp xếp: tổng giờ tăng dần. |
| 5 | Staff chọn NV "B" (tổng giờ = 0), nhấn **Select**. |
| 6 | NV "B" được thêm vào cột "Thứ 2, Ca 1". |
| 7 | Staff lặp cho các ca khác. Nhấn **Save**. |
| 8 | Hệ thống lưu lịch vào database. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**Schedule:** id, employeeId, shiftId, weekStartDate

Quan hệ:
```
Employee 1 --- n Schedule n --- 1 Shift
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**ScheduleFrm:**
- `outScheduleTable`: bảng 7×2 với tên NV
- `outsubListRegistered`: danh sách NV đăng ký (khi click ca)
- `subSelect`: nút Select
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| RegistrationShiftDAO | `getRegisteredEmployees(shiftId): List<Employee>` |
| ScheduleDAO | `getSchedule(week): List<Schedule>` |
| ScheduleDAO | `addSchedule(schedule): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → ScheduleFrm: open
2. ScheduleFrm → ScheduleDAO: getSchedule(week)
3. ScheduleFrm: display 7×2 table
4. Staff → ScheduleFrm: click "Mon, Shift 1"
5. ScheduleFrm → RegistrationShiftDAO: getRegisteredEmployees(shiftId)
6. Staff → ScheduleFrm: select employee, click Select
7. ScheduleFrm: add to table
8. (lặp)
9. Staff → ScheduleFrm: click Save
10. ScheduleFrm → ScheduleDAO: addSchedule(schedules)

---

## Câu 5: Blackbox Testcase

### TC01: Xếp lịch thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng 7×2: Thứ 2 Ca 1 = B, C; Thứ 4 Ca 2 = B, D |
| 2 | Nhấn Thứ 2 Ca 1 → DS đăng ký: B (0h), C (8h), E (16h) |
| 3 | Chọn B, Select → Thứ 2 Ca 1 = B |
| 4 | Save | "Xep lich thanh cong" |

**Database sau:** Thêm tblSchedule.
