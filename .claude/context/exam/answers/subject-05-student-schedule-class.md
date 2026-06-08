# Subject No. 05 — Student Results — Module "Schedule a class"

> **Domain:** Student Results Management
> **Duration:** 60 minutes

---

## Câu 1: Scenario — Xếp lịch lớp học

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Schedule, Enter grades, Register, View schedule, Statistics. |
| 4 | Staff chọn chức năng **Schedule a class**. |
| 5 | Giao diện Schedule xuất hiện với combobox chọn môn học (Subject), combobox chọn lớp (Class), combobox chọn khung giờ (Time slot), nút Confirm. |
| 6 | Staff chọn môn học "Nhap mon CNPM" từ dropdown → danh sách lớp của môn học được cập nhật. |
| 7 | Staff chọn lớp "L01" từ dropdown. |
| 8 | Staff nhấn nút **Add new class** để thêm lớp mới cho môn học. |
| 9 | Giao diện thêm lớp mới xuất hiện: Staff chọn phòng học từ dropdown (Phong A101), chọn khung giờ từ dropdown (Thu 2, 7:30-9:30), nhấn Confirm. |
| 10 | Hệ thống kiểm tra: phòng học và khung giờ không bị trùng với lớp khác. Nếu hợp lệ, lưu vào database. |
| 11 | Hệ thống thông báo "Xep lich thanh cong" và quay về giao diện Schedule. |

### Kịch bản ngoại lệ
- **EL1:** Phòng học đã được sử dụng vào khung giờ đó → thông báo "Phong hoc da duoc su dung".
- **EL2:** Lớp đã có lịch vào khung giờ đó → thông báo "Lop da co lich vao khung gio nay".

---

## Câu 2: Entity Class Diagram

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Student (Sinh viên) | Entity class | Đối tượng đăng ký học |
| Subject (Môn học) | Entity class | Đối tượng được xếp lịch |
| Class (Lớp) | Entity class | Lớp cụ thể của môn học |
| Registration (Đăng ký) | Entity class | Liên kết Student-Subject-Class |
| Grade (Điểm) | Entity class | Kết quả học tập |
| User (Nhân viên) | Entity class | Người thao tác |
| Khung giờ | Thuộc tính | Thuộc tính của Class |
| Phòng học | Thuộc tính | Thuộc tính của Class |
| Số tín chỉ | Thuộc tính | Thuộc tính của Subject |

### Class Diagram

```
+------------------+       +------------------+
|    Student       |       |     Subject      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -studentId: String|      | -code: String    |
| -password: String|       | -name: String    |
| -name: String    |       | -credits: int    |
| -dob: Date       |       +------------------+
| -course: String  |       | +getClasses()    |
| -hometown: String|       +------------------+
| -address: String |                |
+------------------+                | 1
| +register()      |                |
+------------------+                | n
         | 1                        v
         |                  +------------------+
         | n                |      Class       |
         v                  +------------------+
+------------------+        | -id: int         |
|   Registration   |        | -code: String    |
+------------------+        | -name: String    |
| -id: int         |        | -maxStudents: int|
| -semester: String|        | -classroom: String|
| -regDate: Date   |        | -timeSlot: String|
+------------------+        +------------------+
                            | +getStudents()   |
                            +------------------+

+------------------+
|      Grade       |
+------------------+
| -id: int         |
| -score1: float   |
| -score2: float   |
| -score3: float   |
| -testScore: float|
| -finalScore: float|
+------------------+

+------------------+
|      User        |
+------------------+
| -username: String|
| -password: String|
| -role: String    |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Student → Registration | 1-n | Một SV có nhiều đăng ký |
| Subject → Class | 1-n | Một môn có nhiều lớp |
| Class → Registration | 1-n | Một lớp có nhiều SV đăng ký |
| Subject → Subject | n-n (prerequisite) | Môn tiên quyết |
| Student → Grade | 1-n | Một SV có nhiều điểm |
| Class → Grade | 1-n | Một lớp có nhiều điểm SV |

---

## Câu 3: Thiết kế tĩnh

### View classes

**ScheduleClassFrm:**
- `inSubject`: combobox chọn môn học
- `inClass`: combobox chọn lớp
- `inClassroom`: combobox chọn phòng học
- `inTimeSlot`: combobox chọn khung giờ
- `subAddClass`: nút thêm lớp mới
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| SubjectDAO | `getAllSubjects(): List<Subject>` | Lấy danh sách môn học |
| ClassDAO | `getClassesBySubject(subjectId): List<Class>` | Lấy lớp theo môn |
| ClassDAO | `addClass(class): boolean` | Thêm lớp mới |
| ClassDAO | `checkConflict(classroom, timeSlot): boolean` | Kiểm tra trùng lịch |

### Database Design

**tblStudent:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| studentId | varchar | |
| password | varchar | |
| name | varchar | |
| dob | date | |
| course | varchar | |
| hometown | varchar | |
| address | varchar | |

**tblSubject:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| credits | int | |

**tblSubjectPrerequisite:**
| Column | Type | Constraint |
|--------|------|------------|
| subjectID | int | FK → tblSubject.ID |
| prerequisiteID | int | FK → tblSubject.ID |

**tblClass:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| code | varchar | |
| name | varchar | |
| maxStudents | int | |
| classroom | varchar | |
| timeSlot | varchar | |
| subjectID | int | FK → tblSubject.ID |

**tblRegistration:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| semester | varchar | |
| regDate | date | |
| studentID | int | FK → tblStudent.ID |
| classID | int | FK → tblClass.ID |

**tblGrade:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK |
| score1 | float | |
| score2 | float | |
| score3 | float | |
| testScore | float | |
| finalScore | float | |
| studentID | int | FK → tblStudent.ID |
| classID | int | FK → tblClass.ID |

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `ScheduleClassFrm`, `SubjectDAO`, `ClassDAO`.
2. Message flow:
   - Staff → LoginFrm: login
   - Staff → HomeFrm: select Schedule a class
   - HomeFrm → ScheduleClassFrm: open()
   - ScheduleClassFrm → SubjectDAO: getAllSubjects()
   - SubjectDAO → ScheduleClassFrm: return List<Subject>
   - ScheduleClassFrm: populate subject combobox
   - Staff → ScheduleClassFrm: select subject "Nhap mon CNPM"
   - ScheduleClassFrm → ClassDAO: getClassesBySubject(subjectId)
   - ClassDAO → ScheduleClassFrm: return List<Class>
   - ScheduleClassFrm: populate class combobox
   - Staff → ScheduleClassFrm: click Add new class
   - ScheduleClassFrm: show new class form
   - Staff → ScheduleClassFrm: select classroom "A101", timeSlot "Thu2 7:30-9:30"
   - Staff → ScheduleClassFrm: click Confirm
   - ScheduleClassFrm → ClassDAO: checkConflict("A101", "Thu2 7:30-9:30")
   - ClassDAO → ScheduleClassFrm: return false (no conflict)
   - ScheduleClassFrm → ClassDAO: addClass(newClass)
   - ClassDAO: INSERT INTO tblClass
   - ClassDAO → ScheduleClassFrm: return true
   - ScheduleClassFrm: show success, return to schedule

---

## Câu 5: Blackbox Testcase

### TC01: Xếp lịch lớp thành công

**Database trước khi test:**

**tblSubject:**
| ID | code | name | credits |
|----|------|------|---------|
| 1 | INT1340 | Nhap mon CNPM | 3 |

**tblClass:** (rỗng)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập | Giao diện Home |
| 2 | Chọn Schedule a class | Giao diện Schedule với combobox môn học |
| 3 | Chọn "Nhap mon CNPM" | Combobox lớp cập nhật (rỗng) |
| 4 | Nhấn Add new class | Form thêm lớp mới xuất hiện |
| 5 | Chọn phòng A101, khung giờ Thu2 7:30-9:30, nhấn Confirm | Hệ thống kiểm tra không trùng. Thông báo "Xep lich thanh cong" |

### Database sau khi test

**tblClass:**
| ID | code | name | maxStudents | classroom | timeSlot | subjectID |
|----|------|------|-------------|-----------|----------|-----------|
| 1 | L01 | Lop 01 | 40 | A101 | Thu2 7:30-9:30 | 1 |
