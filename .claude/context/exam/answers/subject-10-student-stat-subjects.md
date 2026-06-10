# Subject No. 10 — Student Results — Module "Statistics of subjects"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User, SubjectPrerequisite

---

## Câu 1: Scenario — Thống kê môn học

### Kịch bản chính

| Bước | Diễn biến | Giao diện hiển thị |
|------|-----------|---------------------|
| 1 | Manage staff mở ứng dụng. Giao diện Login xuất hiện với ô nhập username, password và nút Login. | **LoginFrm** |
| 2 | Staff nhập username `staff01`, password `******`, nhấn nút Login. | **LoginFrm** |
| 3 | Hệ thống xác thực thành công. Giao diện Home xuất hiện với các menu: Schedule, Enter grades, Register, View schedule, Statistics. | **HomeFrm** |
| 4 | Staff chọn menu **Statistics**. | **HomeFrm** |
| 5 | Hệ thống hiển thị submenu: "Student statistics", "Subject statistics". Staff chọn **Subject statistics**. | **HomeFrm** — submenu |
| 6 | Giao diện Stat Subject xuất hiện. Hệ thống hiển thị danh sách môn học với các cột: Mã môn, Tên môn, Số tín chỉ, Điểm TB sinh viên, Tỷ lệ SV đậu (%). Danh sách sắp xếp theo tỷ lệ đậu từ cao đến thấp. | **StatSubjectFrm** — bảng danh sách |
| 7 | Staff xem danh sách: dòng 1 = ENG1024 Tieng Anh 3TC, TB 8.3, 100%; dòng 2 = MAT1042 Toan cao cap 4TC, TB 7.3, 100%; dòng 3 = INT1340 Nhap mon CNPM 3TC, TB 6.5, 100%. | **StatSubjectFrm** — bảng danh sách |
| 8 | Staff nhấn vào dòng INT1340 (Nhap mon CNPM). | **StatSubjectFrm** |
| 9 | Hệ thống hiển thị chi tiết điểm tất cả sinh viên đã học môn đó: Mã SV, Tên SV, Tên lớp, Điểm thành phần 1, Điểm thành phần 2, Điểm thành phần 3, Điểm thi, Điểm cuối cùng, Kết quả (Đậu/Rớt). | **StatSubjectFrm** — bảng chi tiết |
| 10 | Staff xem chi tiết: SV2021001 Nguyen Van A, L01 (8.0, 7.0, 9.0, 8.0, 8.0, Dau); SV2021002 Tran Thi B, L01 (6.0, 7.0, 7.5, 7.0, 6.9, Dau); SV2021003 Le Van C, L02 (4.0, 5.0, 5.5, 4.0, 4.6, Dau). | **StatSubjectFrm** — bảng chi tiết |
| 11 | Staff nhấn nút **Back**. Quay về bảng danh sách môn học. | **StatSubjectFrm** — bảng danh sách |
| 12 | Staff nhấn vào dòng khác để xem chi tiết môn khác, hoặc nhấn Logout để thoát. | **StatSubjectFrm** hoặc **LoginFrm** |

### Kịch bản ngoại lệ

| Mã | Tình huống | Diễn biến |
|----|-----------|-----------|
| EL1 | Môn học chưa có sinh viên nào học (chưa có điểm) | Dòng môn học hiển thị Điểm TB = N/A, Tỷ lệ đậu = 0%. Nhấn vào hiển thị bảng chi tiết trống. |
| EL2 | Môn học có sinh viên đăng ký nhưng chưa nhập điểm | Tỷ lệ đậu = 0% (chưa có finalScore). Chi tiết hiển thị điểm trống. |
| EL3 | Staff chọn xem chi tiết môn không có lớp nào | Hệ thống hiển thị bảng chi tiết trống, thông báo "Khong co du lieu diem cho mon hoc nay". |

---

## Câu 2: Entity Class Diagram

### Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý kết quả học tập. Manage staff đăng nhập để xem thống kê kết quả theo môn học. Mỗi sinh viên (Student) có mã SV, tên, ngày sinh, khóa, quê quán, địa chỉ. Mỗi sinh viên đăng ký nhiều lớp học (Class) trong một học kỳ, tối thiểu 10 tín chỉ và tối đa 15 tín chỉ. Mỗi môn học (Subject) có nhiều lớp và có thể có nhiều môn tiên quyết. Mỗi lớp có mã lớp, tên lớp, phòng học, sĩ số tối đa, giờ học cố định. Sinh viên không được phép đăng ký hai lớp có cùng giờ học. Sau khi hoàn thành, sinh viên có điểm (Grade) bao gồm điểm thành phần 1, 2, 3 và điểm thi. Điểm cuối cùng = x% × score1 + y% × score2 + z% × score3 + w% × testScore. Hệ thống tính điểm trung bình của tất cả sinh viên trong môn học và tỷ lệ sinh viên đậu (finalScore >= 4.0). Staff có thể nhấn vào từng môn để xem chi tiết điểm của tất cả sinh viên đã học môn đó.

### Bước 2: Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Student (Sinh viên) | Entity class | Đối tượng học, có điểm trong môn, có mã SV, tên, ngày sinh, khóa, quê quán, địa chỉ |
| Subject (Môn học) | Entity class | Đối tượng chính được thống kê, có mã môn, tên môn, tín chỉ |
| Class (Lớp học) | Entity class | Lớp cụ thể của môn học, có mã lớp, tên lớp, phòng học, sĩ số, giờ học cố định |
| Registration (Đăng ký) | Entity class | Liên kết sinh viên với lớp học trong một học kỳ |
| Grade (Điểm) | Entity class | Kết quả học tập, có điểm thành phần 1, 2, 3, điểm thi, điểm cuối cùng |
| User (Nhân viên quản lý) | Entity class | Người thao tác thống kê, có username, password |
| SubjectPrerequisite (Điều kiện tiên quyết) | Entity class | Quan hệ N-N giữa Subject và chính nó |
| Student code (Mã SV) | Thuộc tính | Thuộc tính của Student |
| Password | Thuộc tính | Thuộc tính của User và Student |
| Name (Tên) | Thuộc tính | Thuộc tính của Student, Subject, Class |
| Date of birth (Ngày sinh) | Thuộc tính | Thuộc tính của Student |
| Course (Khóa) | Thuộc tính | Thuộc tính của Student |
| Hometown (Quê quán) | Thuộc tính | Thuộc tính của Student |
| Address (Địa chỉ) | Thuộc tính | Thuộc tính của Student |
| Subject code (Mã môn) | Thuộc tính | Thuộc tính của Subject |
| Number of credits (Số tín chỉ) | Thuộc tính | Thuộc tính của Subject |
| Class code (Mã lớp) | Thuộc tính | Thuộc tính của Class |
| Class name (Tên lớp) | Thuộc tính | Thuộc tính của Class |
| Maximum number of students (Sĩ số tối đa) | Thuộc tính | Thuộc tính của Class |
| Classrooms (Phòng học) | Thuộc tính | Thuộc tính của Class |
| Fixed hours of the week (Giờ học cố định) | Thuộc tính | Thuộc tính của Class |
| Semester (Học kỳ) | Thuộc tính | Thuộc tính của Registration |
| Score 1, 2, 3 (Điểm thành phần) | Thuộc tính | Thuộc tính của Grade |
| Test score (Điểm thi) | Thuộc tính | Thuộc tính của Grade |
| Final score (Điểm cuối cùng) | Thuộc tính | Thuộc tính của Grade — giá trị tính toán |
| Tỷ lệ đậu | Không phải class | Giá trị tính toán (% SV có finalScore >= 4.0) |
| Điểm TB môn | Không phải class | Giá trị tính toán (TB finalScore) |

### Bước 3: Xác định quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Student → Registration | 1..* | Một sinh viên có nhiều đăng ký |
| Class → Registration | 1..* | Một lớp có nhiều sinh viên đăng ký |
| Subject → Class | 1..* | Một môn học có nhiều lớp |
| Registration → Grade | 1..1 | Mỗi đăng ký có một điểm |
| Subject → SubjectPrerequisite | 1..* | Một môn có nhiều điều kiện tiên quyết |
| SubjectPrerequisite → Subject | *..1 | Mỗi điều kiện tiên quyết tham chiếu đến 1 môn bắt buộc |

### Bước 4: Class Diagram

```
+------------------+          +-------------------+
|      User        |          |     Student       |
+------------------+          +-------------------+
| -username: String|    1  1  | -id: int          |
| -password: String|<-------->| -studentCode: String|
| -role: String    |          | -name: String     |
+------------------+          | -dob: Date        |
                              | -course: String   |
                              | -hometown: String |
                              | -address: String  |
                              +-------------------+
                                 | 1         | 1
                                 |           |
                                 | *         | *
                              +-------------------+        +-------------------+
                              |   Registration    |        |     Subject       |
                              +-------------------+        +-------------------+
                              | -id: int          |   *  1 | -id: int          |
                              | -semester: String |------->| -code: String     |
                              | -regDate: Date    |        | -name: String     |
                              | -status: String   |        | -credits: int     |
                              +-------------------+        +-------------------+
                                       | 1                         | 1
                                       |                           |
                                       | 1                         | *
                              +-------------------+        +-------------------+
                              |      Grade        |        | SubjectPrerequisite|
                              +-------------------+        +-------------------+
                              | -id: int          |        | -id: int          |
                              | -registrationID: int|      | -subjectID: int   |
                              | -score1: float    |        | -prereqSubjectID: int|
                              | -score2: float    |        +-------------------+
                              | -score3: float    |
                              | -testScore: float |
                              | -finalScore: float|
                              +-------------------+
                              | +isPassed(): bool |
                              | +calculateFinal() |
                              +-------------------+

                              +-------------------+
                              |      Class        |
                              +-------------------+
                              | -id: int          |
                              | -classCode: String|
                              | -className: String|
                              | -classroom: String|
                              | -maxStudents: int |
                              | -fixedHours: String|
                              | -subjectID: int   |
                              +-------------------+
```

### Bước 5: Bảng quan hệ chi tiết

| Entity 1 | Entity 2 | Mối quan hệ | Đa thực thể | Khóa ngoại | Giải thích |
|-----------|----------|-------------|-------------|------------|------------|
| Student | User | liên kết 1-1 | 1:1 | userID trong tblStudent | Mỗi SV có 1 tài khoản |
| Student | Registration | sở hữu | 1:N | studentID trong tblRegistration | 1 SV có nhiều đăng ký |
| Class | Registration | được đăng ký | 1:N | classID trong tblRegistration | 1 lớp có nhiều SV |
| Subject | Class | chứa | 1:N | subjectID trong tblClass | 1 môn có nhiều lớp |
| Registration | Grade | có điểm | 1:1 | registrationID trong tblGrade | Mỗi đăng ký có 1 điểm |
| Subject | SubjectPrerequisite | có tiên quyết | 1:N | subjectID trong tblPrerequisite | 1 môn có nhiều môn tiên quyết |
| SubjectPrerequisite | Subject | tham chiếu môn bắt buộc | N:1 | prereqSubjectID trong tblPrerequisite | Mỗi tiên quyết tham chiếu 1 môn |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_StatSubjects" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 7 class: User, Student, Subject, Class, Registration, Grade, SubjectPrerequisite |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, StatSubjectFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Student) |
|------|----------|------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Student` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-hometown: String`, `-address: String` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | — |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |
| Student | `<<entity>>` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-hometown: String`, `-address: String` | — |
| Subject | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-credits: int` | — |
| Class | `<<entity>>` | `-id: int`, `-classCode: String`, `-className: String`, `-classroom: String`, `-maxStudents: int`, `-fixedHours: String`, `-subjectID: int` | — |
| Registration | `<<entity>>` | `-id: int`, `-semester: String`, `-regDate: Date`, `-status: String`, `-studentID: int`, `-classID: int` | — |
| Grade | `<<entity>>` | `-id: int`, `-registrationID: int`, `-score1: float`, `-score2: float`, `-score3: float`, `-testScore: float`, `-finalScore: float` | `+calculateFinalScore(): float`, `+isPassed(): boolean` |
| SubjectPrerequisite | `<<entity>>` | `-id: int`, `-subjectID: int`, `-prereqSubjectID: int` | — |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuSchedule: JMenuItem`, `-menuEnterGrades: JMenuItem`, `-menuRegister: JMenuItem`, `-menuViewSchedule: JMenuItem`, `-menuStatistics: JMenuItem`, `-subMenuStudentStat: JMenuItem`, `-subMenuSubjectStat: JMenuItem` |
| StatSubjectFrm | `<<boundary>>` | `-outSubjectStatTable: JTable`, `-outGradeDetailTable: JTable`, `-subBack: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời |

**6. Cách ghi multiplicity:**

| Multiplicity | Cách ghi trong VP | Ví dụ |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" ở một đầu | Student có đúng 1 User |
| 0..* hoặc 1..* | Ghi "*" hoặc "1..*" ở đầu kia | Student có nhiều Registration |
| 0..1 | Ghi "0..1" | (hiếm dùng) |

Ghi multiplicity ở cả 2 đầu của đường kết nối. Click vào đường → Properties → Source Multiplicity / Target Multiplicity.

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| Student | User | Association | 1 → 1 | Mỗi sinh viên có một tài khoản User |
| Student | Registration | Association | 1 → * | Mỗi sinh viên có nhiều đăng ký |
| Class | Registration | Association | 1 → * | Mỗi lớp có nhiều sinh viên đăng ký |
| Subject | Class | Association | 1 → * | Mỗi môn học có nhiều lớp |
| Registration | Grade | Association | 1 → 1 | Mỗi đăng ký có một điểm |
| Subject | SubjectPrerequisite | Association | 1 → * | Mỗi môn có nhiều môn tiên quyết |
| SubjectPrerequisite | Subject | Association | * → 1 | Mỗi tiên quyết tham chiếu đến 1 môn |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Subject → Class (1-n, Association)*

1. Click chuột phải vào class Subject → chọn **Association** → kéo đến class Class.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `offers`.

*Ví dụ 2: Vẽ quan hệ Registration → Grade (1-1, Association)*

1. Click chuột phải vào class Registration → chọn **Association** → kéo đến class Grade.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `1`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `has_grade`.

### Classes diagram (analysis)

Phân tích module này:

Đăng nhập vào hệ thống → Giao diện Login xuất hiện → cần class: LoginFrm
  Ô nhập username → inUsername
  Ô nhập password → inPassword
  Nút Login → subLogin

Nhập username/password → Hệ thống phải kiểm tra đăng nhập → cần phương thức:
  Tên: login()
  Đầu vào: username, password (String)
  Đầu ra: User
  Gán cho DAO class: UserDAO.

Đăng nhập thành công → Giao diện Home xuất hiện → cần class: HomeFrm
  Menu Schedule → menuSchedule
  Menu Enter Grades → menuEnterGrades
  Menu Register → menuRegister
  Menu View Schedule → menuViewSchedule
  Menu Statistics → menuStatistics
  Submenu Student statistics → subMenuStudentStat
  Submenu Subject statistics → subMenuSubjectStat

Staff chọn Subject statistics → Giao diện thống kê môn học xuất hiện → cần class: StatSubjectFrm
  Bảng thống kê môn học (click được) → outSubjectStatTable
  Bảng chi tiết điểm SV (hiện khi click dòng môn) → outGradeDetailTable
  Nút Back → subBack

Hệ thống tải thống kê tất cả môn học → cần phương thức:
  Tên: getSubjectStat()
  Đầu vào: (không)
  Đầu ra: List<SubjectStat> (computed: code, name, credits, avgScore, passRate)
  Gán cho DAO class: SubjectDAO.

Staff click vào dòng môn học → Hệ thống hiển thị chi tiết điểm → cần phương thức:
  Tên: getGradeBySubject()
  Đầu vào: subjectId (int)
  Đầu ra: List<Grade> (kèm studentCode, studentName, className)
  Gán cho DAO class: GradeDAO.

### Tóm tắt
View classes: LoginFrm, HomeFrm, StatSubjectFrm
DAO methods: login(), getSubjectStat(), getGradeBySubject()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: TextField nhập tên đăng nhập (String)
- `inPassword`: PasswordField nhập mật khẩu (String)
- `subLogin`: Button "Login"

**HomeFrm:**
- `menuSchedule`: MenuItem "Schedule"
- `menuEnterGrades`: MenuItem "Enter grades"
- `menuRegister`: MenuItem "Register"
- `menuViewSchedule`: MenuItem "View schedule"
- `menuStatistics`: MenuItem "Statistics"
- `subMenuStudentStat`: MenuItem "Student statistics"
- `subMenuSubjectStat`: MenuItem "Subject statistics"

**StatSubjectFrm:**
- `outSubjectStatTable`: Bảng thống kê môn học (JTable) — click được
- `outSubjectCode`: Cột Mã môn (String)
- `outSubjectName`: Cột Tên môn (String)
- `outCredits`: Cột Số tín chỉ (int)
- `outAvgScore`: Cột Điểm TB sinh viên (float)
- `outPassRate`: Cột Tỷ lệ SV đậu — % (float)
- `outGradeDetailTable`: Bảng chi tiết điểm SV (JTable) — hiển thị khi click dòng môn
- `outStudentCode`: Cột Mã SV (String)
- `outStudentName`: Cột Tên SV (String)
- `outClassName`: Cột Tên lớp (String)
- `outScore1`: Cột Điểm thành phần 1 (float)
- `outScore2`: Cột Điểm thành phần 2 (float)
- `outScore3`: Cột Điểm thành phần 3 (float)
- `outTestScore`: Cột Điểm thi (float)
- `outFinalScore`: Cột Điểm cuối cùng (float)
- `outResult`: Cột Kết quả Đậu/Rớt (String)
- `subBack`: Button "Back"

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| UserDAO | `login(username: String, password: String): User` | `User` hoặc `null` | Xác thực đăng nhập |
| SubjectDAO | `getSubjectStat(): List<SubjectStat>` | `List<SubjectStat>` | Thống kê: điểm TB, tỷ lệ đậu mỗi môn (computed query) |
| GradeDAO | `getGradeBySubject(subjectId: int): List<Grade>` | `List<Grade>` | Lấy chi tiết điểm tất cả SV trong môn (JOIN tblRegistration, tblStudent, tblClass) |

### Entity classes

**User:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| username | String | Tên đăng nhập |
| password | String | Mật khẩu |
| role | String | Vai trò: "student", "staff" |

**Student:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| studentCode | String | Mã sinh viên |
| name | String | Họ tên |
| dob | Date | Ngày sinh |
| course | String | Khóa học |
| hometown | String | Quê quán |
| address | String | Địa chỉ |
| user | User | Đối tượng tài khoản |
| registrations | List<Registration> | Danh sách đăng ký |

**Subject:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã môn |
| name | String | Tên môn |
| credits | int | Số tín chỉ |
| classes | List<Class> | Danh sách lớp học |

**SubjectPrerequisite:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| subjectID | int | FK -> tblSubject.ID (môn cần đăng ký) |
| prereqSubjectID | int | FK -> tblSubject.ID (môn tiên quyết phải hoàn thành) |
| subject | Subject | Đối tượng môn học |
| prereqSubject | Subject | Đối tượng môn tiên quyết |

**Class:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| classCode | String | Mã lớp |
| className | String | Tên lớp |
| classroom | String | Phòng học |
| maxStudents | int | Sĩ số tối đa |
| fixedHours | String | Giờ học cố định trong tuần |
| subjectID | int | FK -> tblSubject.ID |
| subject | Subject | Đối tượng môn học |
| registrations | List<Registration> | Danh sách đăng ký |

**Registration:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| semester | String | Học kỳ |
| regDate | Date | Ngày đăng ký |
| status | String | Trạng thái |
| studentID | int | FK -> tblStudent.ID |
| classID | int | FK -> tblClass.ID |
| student | Student | Đối tượng sinh viên |
| classObj | Class | Đối tượng lớp học |
| grade | Grade | Đối tượng điểm (1-1) |

**Grade:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| registrationID | int | FK -> tblRegistration.ID |
| score1 | float | Điểm thành phần 1 |
| score2 | float | Điểm thành phần 2 |
| score3 | float | Điểm thành phần 3 |
| testScore | float | Điểm thi |
| finalScore | float | Điểm cuối cùng |
| registration | Registration | Đối tượng đăng ký |

**SubjectStat (computed — không lưu DB, tính từ query):**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| subject | Subject | Đối tượng môn học |
| avgScore | float | Điểm TB của tất cả SV trong môn |
| passRate | float | Tỷ lệ đậu (% SV có finalScore >= 4.0) |

### Database Design

**tblUser:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| username | varchar(50) | UNIQUE, NOT NULL |
| password | varchar(100) | NOT NULL |
| role | varchar(20) | NOT NULL |

**tblStudent:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| studentCode | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| dob | date | |
| course | varchar(20) | |
| hometown | varchar(100) | |
| address | varchar(200) | |
| userID | int | FK -> tblUser.ID |

**tblSubject:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| credits | int | NOT NULL |

**tblPrerequisite:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| subjectID | int | FK -> tblSubject.ID, NOT NULL |
| prereqSubjectID | int | FK -> tblSubject.ID, NOT NULL |

**tblClass:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| classCode | varchar(20) | UNIQUE, NOT NULL |
| className | varchar(100) | NOT NULL |
| classroom | varchar(50) | |
| maxStudents | int | |
| fixedHours | varchar(100) | |
| subjectID | int | FK -> tblSubject.ID, NOT NULL |

**tblRegistration:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| semester | varchar(20) | NOT NULL |
| regDate | date | |
| status | varchar(20) | DEFAULT 'active' |
| studentID | int | FK -> tblStudent.ID, NOT NULL |
| classID | int | FK -> tblClass.ID, NOT NULL |

**tblGrade:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| registrationID | int | FK -> tblRegistration.ID, UNIQUE, NOT NULL |
| score1 | float | |
| score2 | float | |
| score3 | float | |
| testScore | float | |
| finalScore | float | |

### Hướng dẫn Visual Paradigm

1. Mở Visual Paradigm -> File -> New Project -> đặt tên "StudentStatSubjects".
2. Vào Diagram -> Class Diagram -> tạo class diagram mới.
3. Tạo 7 class: User, Student, Subject, Class, Registration, Grade, SubjectPrerequisite.
4. Với mỗi class, click chuột phải -> Add Attribute -> nhập thuộc tính theo bảng Entity classes.
5. Thêm method cho Grade: click chuột phải -> Add Operation -> `isPassed(): boolean` (finalScore >= 4.0), `calculateFinal(): float`.
6. Kéo connector "Association" từ Student đến Registration (1:N).
7. Kéo connector "Association" từ Class đến Registration (1:N).
8. Kéo connector "Association" từ Subject đến Class (1:N).
9. Kéo connector "Association" từ Registration đến Grade (1:1).
10. Kéo connector "Association" từ Subject đến SubjectPrerequisite (1:N).
11. Kéo connector "Association" từ SubjectPrerequisite đến Subject (N:1).
12. Đặt tên và đa thực thể cho mỗi mối quan hệ.

---

## Câu 4: Sequence Diagram

### Hướng dẫn Visual Paradigm — Các bước vẽ

1. Mở Visual Paradigm -> Diagram -> Sequence Diagram.
2. Tạo các lifeline: `:Staff`, `:LoginFrm`, `:UserDAO`, `:HomeFrm`, `:StatSubjectFrm`, `:SubjectDAO`, `:GradeDAO`.
3. Kéo message từ `:Staff` đến `:LoginFrm`: "login(username, password)".
4. Kéo message từ `:LoginFrm` đến `:UserDAO`: "login(username, password)".
5. Kéo return message: "return User" từ UserDAO đến LoginFrm.
6. Kéo return message: "return User" từ LoginFrm đến Staff.
7. Kéo message từ `:Staff` đến `:HomeFrm`: "selectStatistics()".
8. Kéo message từ `:HomeFrm` đến `:StatSubjectFrm`: "open()".
9. Kéo message từ `:StatSubjectFrm` đến `:SubjectDAO`: "getSubjectStat()".
10. Kéo return message: "return List<SubjectStat>".
11. Kéo message self trên `:StatSubjectFrm`: "sortbyPassRateDesc()".
12. Kéo message từ `:Staff` đến `:StatSubjectFrm`: "clickSubject(subjectId)".
13. Kéo message từ `:StatSubjectFrm` đến `:GradeDAO`: "getGradeBySubject(subjectId)".
14. Kéo return message: "return List<Grade>".
15. Kéo message self trên `:StatSubjectFrm`: "displayGradeDetail(grades)".

### Sequence Diagram (ASCII)

```
Staff      LoginFrm     UserDAO      HomeFrm      StatSubjectFrm    SubjectDAO     GradeDAO
  |            |            |            |               |                |              |
  |---login--->|            |            |               |                |              |
  |            |---login--->|            |               |                |              |
  |            |            |            |               |                |              |
  |            |<--return User----------|               |                |              |
  |<--return User-----------|            |               |                |              |
  |            |            |            |               |                |              |
  |---selectStatistics()--->|            |               |                |              |
  |            |            |            |---open()----->|                |              |
  |            |            |            |               |                |              |
  |            |            |            |               |---getSubjectStat()---------->|
  |            |            |            |               |<--return List<SubjectStat>---|
  |            |            |            |               |                |              |
  |            |            |            |               |---sortbyPassRateDesc()       |
  |            |            |            |               |                |              |
  |            |            |            |               | [display subject table sorted by passRate DESC] |
  |            |            |            |               |                |              |
  |---clickSubject(1)------>|            |               |                |              |
  |            |            |            |               |---getGradeBySubject(1)------>|
  |            |            |            |               |<--return List<Grade>---------|
  |            |            |            |               |                |              |
  |            |            |            |               |---displayGradeDetail(grades) |
  |<---show grade detail----|            |               |                |              |
  |            |            |            |               |                |              |
  |---clickBack|            |            |               |                |              |
  |            |            |            |               | [hide detail, show subject table] |
```

### Bảng giải thích chi tiết

| Bước | Từ | Đến | Message | Giải thích |
|------|-----|------|---------|------------|
| 1 | Staff | LoginFrm | `login("staff01", "******")` | Staff nhập thông tin đăng nhập |
| 2 | LoginFrm | UserDAO | `login("staff01", "******")` | LoginFrm gọi UserDAO để xác thực |
| 3 | UserDAO | LoginFrm | `return User` | UserDAO trả về đối tượng User sau xác thực |
| 4 | LoginFrm | Staff | `return User` | LoginFrm trả User cho Staff, mở HomeFrm |
| 5 | Staff | HomeFrm | `selectStatistics()` | Staff chọn menu Statistics |
| 6 | HomeFrm | HomeFrm | `showSubMenu(["Student statistics", "Subject statistics"])` | Hiển thị submenu thống kê |
| 7 | Staff | HomeFrm | `selectSubMenu("Subject statistics")` | Staff chọn "Subject statistics" |
| 8 | HomeFrm | StatSubjectFrm | `new StatSubjectFrm(user).open()` | Mở giao diện thống kê môn học |
| 9 | StatSubjectFrm | SubjectDAO | `getSubjectStat()` | Truy vấn thống kê tất cả môn: điểm TB, tỷ lệ đậu |
| 10 | SubjectDAO | StatSubjectFrm | `return List<SubjectStat>` | Trả về danh sách thống kê môn |
| 11 | StatSubjectFrm | StatSubjectFrm | `sortbyPassRateDesc()` | Sắp xếp theo tỷ lệ đậu giảm dần |
| 12 | StatSubjectFrm | Staff | `displaySubjectStatTable(subjectStats)` | Hiển thị bảng thống kê môn |
| 13 | Staff | StatSubjectFrm | `clickSubject(subjectId=1)` | Staff nhấn vào dòng Nhap mon CNPM |
| 14 | StatSubjectFrm | GradeDAO | `getGradeBySubject(1)` | Truy vấn điểm tất cả SV trong môn (JOIN tblRegistration, tblStudent, tblClass) |
| 15 | GradeDAO | StatSubjectFrm | `return List<Grade>` | Trả về danh sách điểm kèm mã SV, tên SV, tên lớp |
| 16 | StatSubjectFrm | StatSubjectFrm | `buildGradeDetailTable(grades)` | Tạo bảng chi tiết điểm |
| 17 | StatSubjectFrm | Staff | `displayGradeDetail(gradeDetailTable)` | Hiển thị bảng chi tiết điểm SV |
| 18 | Staff | StatSubjectFrm | `clickBack()` | Staff nhấn nút Back |
| 19 | StatSubjectFrm | StatSubjectFrm | `hideGradeDetail()` | Ẩn bảng chi tiết |
| 20 | StatSubjectFrm | Staff | `showSubjectStatTable()` | Hiển thị lại bảng thống kê môn |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên test case | Mô tả | Kết quả mong đợi |
|----|---------------|-------|------------------|
| TC01 | Thống kê môn có dữ liệu đầy đủ | Staff xem thống kê, click môn có điểm | Bảng môn hiển thị đúng TB, tỷ lệ đậu; chi tiết điểm đầy đủ |
| TC02 | Thống kê môn chưa có điểm | Môn mới tạo, chưa có SV học | TB = N/A, tỷ lệ đậu = 0% |
| TC03 | Môn có tỷ lệ đậu 100% | Tất cả SV đều đậu | Tỷ lệ = 100% |
| TC04 | Môn có tỷ lệ đậu 0% | Tất cả SV đều rớt | Tỷ lệ = 0% |
| TC05 | Sắp xếp theo tỷ lệ đậu | Kiểm tra thứ tự | Môn tỷ lệ cao nhất đứng đầu |
| TC06 | Xem thống kê 2 lần liên tiếp | Staff xem, Back, xem lại | Dữ liệu hiển thị đúng cả 2 lần |

### TC01: Thống kê môn học có dữ liệu đầy đủ

**Purpose:** Verify that the subject statistics page displays correct average score and pass rate for each subject, and shows complete grade details when clicking on a subject.

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | pass123 | staff |
| 2 | sv01 | pass123 | student |
| 3 | sv02 | pass123 | student |
| 4 | sv03 | pass123 | student |

**tblStudent:**
| ID | studentCode | name | dob | course | hometown | address | userID |
|----|-------------|------|-----|--------|----------|---------|--------|
| 1 | SV2021001 | Nguyen Van A | 2003-05-15 | K65 | Ha Noi | Ha Noi | 2 |
| 2 | SV2021002 | Tran Thi B | 2003-08-20 | K65 | Hai Phong | Hai Phong | 3 |
| 3 | SV2021003 | Le Van C | 2003-03-10 | K65 | Da Nang | Da Nang | 4 |

**tblSubject:**
| ID | code | name | credits |
|----|------|------|---------|
| 1 | INT1340 | Nhap mon CNPM | 3 |
| 2 | MAT1042 | Toan cao cap | 4 |
| 3 | ENG1024 | Tieng Anh | 3 |

**tblClass:**
| ID | classCode | className | classroom | maxStudents | fixedHours | subjectID |
|----|-----------|-----------|-----------|-------------|------------|-----------|
| 1 | L01 | Lop 01 | A101 | 40 | T2(7-9) | 1 |
| 2 | L02 | Lop 02 | A102 | 40 | T3(10-12) | 1 |
| 3 | L03 | Lop 03 | B202 | 35 | T4(13-15) | 2 |
| 4 | L04 | Lop 04 | C301 | 30 | T5(7-9) | 3 |

**tblRegistration:**
| ID | semester | regDate | status | studentID | classID |
|----|----------|---------|--------|-----------|---------|
| 1 | 20251 | 2025-08-20 | active | 1 | 1 |
| 2 | 20251 | 2025-08-20 | active | 2 | 1 |
| 3 | 20251 | 2025-08-20 | active | 3 | 2 |
| 4 | 20251 | 2025-08-20 | active | 1 | 3 |
| 5 | 20251 | 2025-08-20 | active | 2 | 4 |

**tblGrade:**
| ID | registrationID | score1 | score2 | score3 | testScore | finalScore |
|----|---------------|--------|--------|--------|-----------|------------|
| 1 | 1 | 8.0 | 7.0 | 9.0 | 8.0 | 8.0 |
| 2 | 2 | 6.0 | 7.0 | 7.5 | 7.0 | 6.9 |
| 3 | 3 | 4.0 | 5.0 | 5.5 | 4.0 | 4.6 |
| 4 | 4 | 7.0 | 6.5 | 8.0 | 7.5 | 7.3 |
| 5 | 5 | 8.0 | 8.5 | 9.0 | 8.0 | 8.3 |

### Kịch bản test TC01

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Mở ứng dụng. Giao diện Login xuất hiện. | Hiển thị ô username, password, nút Login |
| 2 | Nhập username `staff01`, password `pass123`, nhấn Login. | Chuyển sang giao diện Home |
| 3 | Chọn menu **Statistics** -> **Subject statistics**. | Giao diện StatSubjectFrm xuất hiện |
| 4 | **Kiểm tra DB trước:** tblRegistration có 5 dòng, tblGrade có 5 dòng. | Dữ liệu đúng như Database trước khi test |
| 5 | Kiểm tra bảng thống kê: dòng 1 = ENG1024 Tieng Anh 3TC, TB 8.3 (1 SV), tỷ lệ đậu 100% (1/1 >= 4.0). | Đúng dữ liệu, sắp xếp theo tỷ lệ đậu giảm dần |
| 6 | Kiểm tra bảng thống kê: dòng 2 = INT1340 Nhap mon CNPM 3TC, TB 6.5 ((8.0+6.9+4.6)/3), tỷ lệ đậu 100% (3/3 >= 4.0). | Đúng dữ liệu |
| 7 | Kiểm tra bảng thống kê: dòng 3 = MAT1042 Toan cao cap 4TC, TB 7.3 (1 SV), tỷ lệ đậu 100% (1/1 >= 4.0). | Đúng dữ liệu |
| 8 | Nhấn vào dòng INT1340 (Nhap mon CNPM). | Bảng chi tiết điểm xuất hiện |
| 9 | Kiểm tra chi tiết: SV2021001 Nguyen Van A, L01 (8.0, 7.0, 9.0, 8.0, 8.0, Dau); SV2021002 Tran Thi B, L01 (6.0, 7.0, 7.5, 7.0, 6.9, Dau); SV2021003 Le Van C, L02 (4.0, 5.0, 5.5, 4.0, 4.6, Dau). | Dữ liệu đúng, kết quả Đậu/Rớt hiển thị |
| 10 | Nhấn nút **Back**. | Bảng chi tiết ẩn, quay về bảng thống kê môn |
| 11 | **Kiểm tra DB sau:** Không thay đổi. Đây là chức năng chỉ đọc (read-only). | Không có INSERT/UPDATE/DELETE |

### Database sau khi test

Không thay đổi. Đây là chức năng chỉ đọc (read-only), không có INSERT/UPDATE/DELETE.
