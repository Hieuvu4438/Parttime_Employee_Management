# Subject No. 06 — Student Results — Module "Enter grades by class"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Nhập điểm theo lớp

| Bước | Diễn biến |
|------|-----------|
| 1 | Teacher (Giảng viên) đăng nhập vào hệ thống. |
| 2 | Teacher chọn chức năng **Enter grades**. |
| 3 | Hệ thống hiển thị danh sách môn học do teacher giảng dạy. |
| 4 | Teacher chọn môn "Nhap mon CNPM". |
| 5 | Hệ thống hiển thị danh sách lớp của môn do teacher dạy: L01, L02. |
| 6 | Teacher chọn lớp "L01". |
| 7 | Hệ thống hiển thị danh sách sinh viên trong lớp, mỗi dòng: mã SV, tên SV, cột điểm thành phần (score1, score2, score3), cột điểm thi (testScore). |
| 8 | Teacher nhập điểm cho từng sinh viên: SV01 (8, 7, 9, 8), SV02 (6, 7, 8, 7). |
| 9 | Teacher nhấn **Confirm**. |
| 10 | Hệ thống tính finalScore = x%×score1 + y%×score2 + z%×score3 + w%×testScore. Lưu vào database. |
| 11 | Hệ thống thông báo "Nhap diem thanh cong". |

---

## Câu 2: Entity Class Diagram

Cùng entity classes với Subject 05. Grade có thêm phương thức:
- `Grade.calculateFinalScore(x, y, z, w): float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**EnterGradeFrm:**
- `inSubject`: combobox chọn môn học
- `inClass`: combobox chọn lớp
- `outListStudent`: bảng sinh viên với ô nhập điểm
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| SubjectDAO | `getSubjectsByTeacher(teacherId): List<Subject>` | Môn học theo GV |
| ClassDAO | `getClassesBySubjectTeacher(subjectId, teacherId): List<Class>` | Lớp theo môn+GV |
| StudentDAO | `getStudentsByClass(classId): List<Student>` | SV trong lớp |
| GradeDAO | `saveGrades(classId, grades): boolean` | Lưu điểm |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Teacher`, `LoginFrm`, `HomeFrm`, `EnterGradeFrm`, `SubjectDAO`, `ClassDAO`, `StudentDAO`, `GradeDAO`.
2. Message flow:
   - Teacher → LoginFrm: login
   - Teacher → HomeFrm: select Enter grades
   - HomeFrm → EnterGradeFrm: open()
   - EnterGradeFrm → SubjectDAO: getSubjectsByTeacher(teacherId)
   - Teacher → EnterGradeFrm: select subject
   - EnterGradeFrm → ClassDAO: getClassesBySubjectTeacher(subjectId, teacherId)
   - Teacher → EnterGradeFrm: select class "L01"
   - EnterGradeFrm → StudentDAO: getStudentsByClass(classId)
   - StudentDAO → EnterGradeFrm: return List<Student>
   - EnterGradeFrm: display student table with empty score columns
   - Teacher → EnterGradeFrm: enter scores for each student
   - Teacher → EnterGradeFrm: click Confirm
   - EnterGradeFrm → GradeDAO: saveGrades(classId, grades)
   - GradeDAO: calculate finalScore, INSERT/UPDATE tblGrade
   - GradeDAO → EnterGradeFrm: return true
   - EnterGradeFrm: show success

---

## Câu 5: Blackbox Testcase

### TC01: Nhập điểm thành công

**Database trước khi test:**

**tblStudent:**
| ID | studentId | name | course |
|----|-----------|------|--------|
| 1 | SV01 | Nguyen Van A | K65 |
| 2 | SV02 | Tran Thi B | K65 |

**tblClass:**
| ID | code | name | subjectID |
|----|------|------|-----------|
| 1 | L01 | Lop 01 | 1 |

**tblRegistration:**
| ID | studentID | classID |
|----|-----------|---------|
| 1 | 1 | 1 |
| 2 | 2 | 1 |

**tblGrade:** (rỗng)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Teacher đăng nhập | Giao diện Home |
| 2 | Chọn Enter grades | Danh sách môn học của teacher |
| 3 | Chọn "Nhap mon CNPM" → Lớp "L01" | Bảng SV: SV01, SV02 với ô nhập điểm trống |
| 4 | Nhập điểm SV01: 8, 7, 9, 8; SV02: 6, 7, 8, 7 | Điểm hiển thị trên bảng |
| 5 | Nhấn Confirm | Thông báo "Nhap diem thanh cong" |

### Database sau khi test

**tblGrade:**
| ID | score1 | score2 | score3 | testScore | finalScore | studentID | classID |
|----|--------|--------|--------|-----------|------------|-----------|---------|
| 1 | 8.0 | 7.0 | 9.0 | 8.0 | 8.0* | 1 | 1 |
| 2 | 6.0 | 7.0 | 8.0 | 7.0 | 7.0* | 2 | 1 |

*finalScore = x%×score1 + y%×score2 + z%×score3 + w%×testScore (giá trị phụ thuộc hệ số)
