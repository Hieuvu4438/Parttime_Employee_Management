# Subject No. 07 — Student Results — Module "Register for classes"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Đăng ký lớp học

| Bước | Diễn biến |
|------|-----------|
| 1 | Student đăng nhập vào hệ thống. |
| 2 | Student chọn chức năng **Register for new semester**. |
| 3 | Giao diện đăng ký xuất hiện với danh sách môn học và combobox chọn lớp. |
| 4 | Student chọn môn "Nhap mon CNPM" (3 tín chỉ). |
| 5 | Hệ thống hiển thị danh sách lớp của môn: L01 (GV: Nguyen A, Thứ 2 7:30-9:30), L02 (GV: Tran B, Thứ 4 7:30-9:30). |
| 6 | Student chọn lớp L01. |
| 7 | Student lặp lại cho các môn khác: Toan cao cap (L03), Tieng Anh (L05). |
| 8 | Hệ thống kiểm tra: tổng tín chỉ >= 10 và <= 15; không trùng khung giờ; đã hoàn thành môn tiên quyết. |
| 9 | Nếu hợp lệ, hệ thống thông báo "Dang ky thanh cong". |
| 10 | Hệ thống in phiếu đăng ký: mã SV, tên SV, khóa, học kỳ, danh sách môn đăng ký (mã môn, tên môn, số tín chỉ, khung giờ, tên GV). |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm quan hệ prerequisite:
```
Subject n --- n Subject (prerequisite)
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**RegisterClassFrm:**
- `outListSubject`: danh sách môn học
- `inClass`: combobox chọn lớp (cho mỗi môn)
- `outTotalCredits`: tổng tín chỉ đã đăng ký
- `subRegister`: nút Register
- `outRegistrationForm`: phiếu đăng ký

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| SubjectDAO | `getAllSubjects(): List<Subject>` | Tất cả môn học |
| ClassDAO | `getClassesBySubject(subjectId): List<Class>` | Lớp theo môn |
| RegistrationDAO | `checkConflict(studentId, timeSlots): boolean` | Kiểm tra trùng giờ |
| RegistrationDAO | `checkPrerequisites(studentId, subjectId): boolean` | Kiểm tra tiên quyết |
| RegistrationDAO | `addRegistration(registration): boolean` | Tạo đăng ký |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Student`, `LoginFrm`, `RegisterClassFrm`, `SubjectDAO`, `ClassDAO`, `RegistrationDAO`.
2. Message flow:
   - Student → LoginFrm: login
   - Student → RegisterClassFrm: select Register for new semester
   - RegisterClassFrm → SubjectDAO: getAllSubjects()
   - RegisterClassFrm: display subjects
   - Student → RegisterClassFrm: select subject, select class
   - RegisterClassFrm → ClassDAO: getClassesBySubject(subjectId)
   - RegisterClassFrm: update class combobox
   - (lặp cho mỗi môn)
   - Student → RegisterClassFrm: click Register
   - RegisterClassFrm → RegistrationDAO: checkConflict(studentId, timeSlots)
   - RegisterClassFrm → RegistrationDAO: checkPrerequisites(studentId, subjectIds)
   - RegisterClassFrm → RegistrationDAO: addRegistration(registrations)
   - RegisterClassFrm: print registration form, show success

---

## Câu 5: Blackbox Testcase

### TC01: Đăng ký thành công

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | SV đăng nhập | Giao diện đăng ký |
| 2 | Chọn môn CNPM → L01, Toan → L03, Tieng Anh → L05 | Tổng tín chỉ = 10 |
| 3 | Nhấn Register | Kiểm tra: 10 <= 10 <= 15 ✓, không trùng giờ ✓, tiên quyết ✓ |
| 4 | Kết quả | Thông báo "Dang ky thanh cong". In phiếu đăng ký |

**Database sau:** Thêm 3 dòng vào tblRegistration.
