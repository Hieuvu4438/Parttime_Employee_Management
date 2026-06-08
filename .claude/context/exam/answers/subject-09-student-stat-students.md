# Subject No. 09 — Student Results — Module "Statistics of students"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Thống kê sinh viên

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn **Statistics** → **Student statistics**. |
| 3 | Hệ thống hiển thị danh sách sinh viên: mã SV, tên SV, khóa, học kỳ, tổng tín chỉ đã học, điểm TB học kỳ, sắp xếp theo điểm TB giảm dần. |
| 4 | Staff nhấn vào SV `SV01` (Nguyen Van A). |
| 5 | Hệ thống hiển thị chi tiết điểm từng môn trong học kỳ: mã môn, tên môn, tín chỉ, điểm thành phần, điểm thi, điểm cuối cùng. |
| 6 | Staff nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm class thống kê:

**StudentStat:**
- `student: Student`
- `semester: String`
- `totalCredits: int`
- `gpa: float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatStudentFrm:**
- `outsubListStudentStat`: bảng thống kê SV (click được)
- `outListGradeDetail`: chi tiết điểm

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| StudentDAO | `getStudentStat(semester): List<StudentStat>` | Thống kê SV |
| GradeDAO | `getGradeByStudent(studentId, semester): List<Grade>` | Chi tiết điểm |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `StatStudentFrm`, `StudentDAO`, `GradeDAO`.
2. Message flow:
   - Staff → StatStudentFrm: open
   - StatStudentFrm → StudentDAO: getStudentStat(semester)
   - StudentDAO → StatStudentFrm: return List<StudentStat>
   - StatStudentFrm: display table sorted by GPA DESC
   - Staff → StatStudentFrm: click student "SV01"
   - StatStudentFrm → GradeDAO: getGradeByStudent(1, semester)
   - GradeDAO → StatStudentFrm: return List<Grade>
   - StatStudentFrm: display grade details

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê SV có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập, chọn Statistics → Students | Bảng SV: SV01 (GPA 3.5), SV02 (GPA 3.0) |
| 2 | Nhấn SV01 | Chi tiết: CNPM (3TC, 8.0), Toan (4TC, 7.5) |
| 3 | Back | Quay về bảng |

**Database sau:** Không thay đổi.
