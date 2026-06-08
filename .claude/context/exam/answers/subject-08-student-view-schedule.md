# Subject No. 08 — Student Results — Module "View Student's schedule"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Xem thời khóa biểu

| Bước | Diễn biến |
|------|-----------|
| 1 | Student đăng nhập vào hệ thống. |
| 2 | Student chọn chức năng **View schedule**. |
| 3 | Hệ thống hiển thị hộp chọn cách xem: theo tuần (week) hoặc theo học kỳ (semester). |
| 4 | Student chọn **Weekly view**. |
| 5 | Hệ thống hiển thị thời khóa biểu tuần hiện tại: bảng 7 cột (Thứ 2-CN), 6 dòng (6 kíp). Mỗi ô hiển thị tên môn, phòng học, tên lớp. |
| 6 | Student nhấn vào ô "Thứ 2, Kíp 1" (Nhap mon CNPM). |
| 7 | Hệ thống hiển thị chi tiết: mã môn INT1340, tên môn Nhap mon CNPM, mã lớp L01, tên lớp Lop 01, phòng A101, GV Nguyen A, giờ bắt đầu 7:30, giờ kết thúc 9:30. |
| 8 | Student nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm class hiển thị:

**ScheduleSlot:**
- `day: String`
- `slot: int`
- `subject: Subject`
- `classRoom: String`
- `className: String`

---

## Câu 3: Thiết kế tĩnh

### View classes

**ViewScheduleFrm:**
- `inViewType`: combobox chọn cách xem (week/semester)
- `outScheduleTable`: bảng thời khóa biểu 7×6
- `outSlotDetail`: chi tiết kíp học (ẩn, hiện khi click)

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| RegistrationDAO | `getScheduleByWeek(studentId, week): List<ScheduleSlot>` | TKB theo tuần |
| ClassDAO | `getClassDetail(classId): Class` | Chi tiết lớp |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Student`, `LoginFrm`, `ViewScheduleFrm`, `RegistrationDAO`, `ClassDAO`.
2. Message flow:
   - Student → ViewScheduleFrm: select View schedule
   - Student → ViewScheduleFrm: select "Weekly view"
   - ViewScheduleFrm → RegistrationDAO: getScheduleByWeek(studentId, currentWeek)
   - RegistrationDAO → ViewScheduleFrm: return List<ScheduleSlot>
   - ViewScheduleFrm: display 7×6 table
   - Student → ViewScheduleFrm: click cell "Mon, Slot 1"
   - ViewScheduleFrm → ClassDAO: getClassDetail(classId)
   - ClassDAO → ViewScheduleFrm: return Class detail
   - ViewScheduleFrm: display slot detail

---

## Câu 5: Blackbox Testcase

### TC01: Xem thời khóa biểu có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | SV đăng nhập, chọn View schedule | Hộp chọn cách xem |
| 2 | Chọn Weekly view | Bảng TKB: Thứ 2 Kíp 1 = CNPM (A101), Thứ 4 Kíp 2 = Toan (B201) |
| 3 | Nhấn Thứ 2 Kíp 1 | Chi tiết: INT1340, Nhap mon CNPM, L01, A101, GV Nguyen A, 7:30-9:30 |
| 4 | Nhấn Back | Quay về bảng TKB |

**Database sau:** Không thay đổi.
