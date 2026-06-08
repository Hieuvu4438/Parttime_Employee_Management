# Subject No. 10 — Student Results — Module "Statistics of subjects"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Thống kê môn học

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn **Statistics** → **Subject statistics**. |
| 3 | Hệ thống hiển thị danh sách môn học: mã môn, tên môn, số tín chỉ, điểm TB SV, tỷ lệ SV đậu (%), sắp xếp theo tỷ lệ đậu giảm dần. |
| 4 | Staff nhấn vào môn "Nhap mon CNPM". |
| 5 | Hệ thống hiển thị chi tiết điểm tất cả SV đã học môn đó. |
| 6 | Staff nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm class thống kê:

**SubjectStat:**
- `subject: Subject`
- `avgScore: float`
- `passRate: float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatSubjectFrm:**
- `outsubListSubjectStat`: bảng thống kê môn (click được)
- `outListGradeDetail`: chi tiết điểm SV

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| SubjectDAO | `getSubjectStat(): List<SubjectStat>` | Thống kê môn |
| GradeDAO | `getGradeBySubject(subjectId): List<Grade>` | Chi tiết điểm |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `StatSubjectFrm`, `SubjectDAO`, `GradeDAO`.
2. Message flow:
   - Staff → StatSubjectFrm: open
   - StatSubjectFrm → SubjectDAO: getSubjectStat()
   - SubjectDAO → StatSubjectFrm: return List<SubjectStat>
   - StatSubjectFrm: display table sorted by passRate DESC
   - Staff → StatSubjectFrm: click subject "CNPM"
   - StatSubjectFrm → GradeDAO: getGradeBySubject(1)
   - GradeDAO → StatSubjectFrm: return List<Grade>
   - StatSubjectFrm: display all student grades

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê môn có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập, chọn Statistics → Subjects | Bảng: CNPM (80%), Toan (70%), Tieng Anh (90%) |
| 2 | Nhấn CNPM | Chi tiết: SV01 (8.0), SV02 (7.0), SV03 (6.5) |
| 3 | Back | Quay về bảng |

**Database sau:** Không thay đổi.
