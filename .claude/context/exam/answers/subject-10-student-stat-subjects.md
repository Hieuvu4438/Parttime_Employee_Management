# Subject No. 10 — Student Results — Module "Statistics of subjects"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

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
| 7 | Staff xem danh sách: dòng 1 = ENG1024 Tieng Anh 3TC, TB 8.5, 95%; dòng 2 = INT1340 Nhap mon CNPM 3TC, TB 7.8, 85%; dòng 3 = MAT1042 Toan cao cap 4TC, TB 6.5, 70%. | **StatSubjectFrm** — bảng danh sách |
| 8 | Staff nhấn vào dòng INT1340 (Nhap mon CNPM). | **StatSubjectFrm** |
| 9 | Hệ thống hiển thị chi tiết điểm tất cả sinh viên đã học môn đó: Mã SV, Tên SV, Tên lớp, Điểm thành phần 1, Điểm thành phần 2, Điểm thành phần 3, Điểm thi, Điểm cuối cùng, Kết quả (Đậu/Rớt). | **StatSubjectFrm** — bảng chi tiết |
| 10 | Staff xem chi tiết: SV2021001 Nguyen Van A, L01 (8.0, 7.0, 9.0, 8.0, 8.0, Dau); SV2021002 Tran Thi B, L01 (6.0, 7.0, 7.5, 7.0, 6.9, Dau); SV2021003 Le Van C, L02 (4.0, 5.0, 5.5, 4.0, 4.6, Rot). | **StatSubjectFrm** — bảng chi tiết |
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

Hệ thống quản lý kết quả học tập. Manage staff đăng nhập để xem thống kê kết quả theo môn học. Mỗi môn học (Subject) có nhiều lớp (Class) trong các học kỳ khác nhau. Sinh viên (Student) đăng ký (Registration) các lớp học. Sau khi hoàn thành, sinh viên có điểm (Grade) bao gồm điểm thành phần và điểm thi. Hệ thống tính điểm trung bình của tất cả sinh viên trong môn học và tỷ lệ sinh viên đậu (finalScore >= 4.0). Staff có thể nhấn vào từng môn để xem chi tiết điểm của tất cả sinh viên đã học môn đó.

### Bước 2: Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Student (Sinh viên) | Entity class | Đối tượng học, có điểm trong môn |
| Subject (Môn học) | Entity class | Đối tượng chính được thống kê |
| Class (Lớp học) | Entity class | Lớp cụ thể của môn học |
| Registration (Đăng ký) | Entity class | Liên kết sinh viên với lớp học |
| Grade (Điểm) | Entity class | Kết quả học tập của sinh viên |
| User (Nhân viên quản lý) | Entity class | Người thao tác thống kê |
| Tỷ lệ đậu | Không phải class | Giá trị tính toán (% SV có finalScore >= 4.0) |
| Điểm TB môn | Không phải class | Giá trị tính toán (TB finalScore) |
| Tín chỉ | Thuộc tính | Thuộc tính của Subject |

### Bước 3: Xác định quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Student → Registration | 1..* | Một sinh viên có nhiều đăng ký |
| Class → Registration | 1..* | Một lớp có nhiều sinh viên đăng ký |
| Subject → Class | 1..* | Một môn học có nhiều lớp |
| Student → Grade | 1..* | Một sinh viên có nhiều điểm |
| Class → Grade | 1..* | Một lớp có nhiều điểm sinh viên |

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
                              | -address: String  |
                              +-------------------+
                              | +getGrade(subjectId)|
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
                                       | *                    | 1       | 1
                                       |                      |         |
                                       | 1                    | *       | *
                              +-------------------+   +-------+   +-----+-----+
                              |      Class        |<--+            |           |
                              +-------------------+                |           |
                              | -id: int          |                |           |
                              | -classCode: String|                |           |
                              | -className: String|                |           |
                              | -classroom: String|                |           |
                              | -maxStudents: int |                |           |
                              +-------------------+                |           |
                                       | 1                        |           |
                                       |                          |           |
                                       | *                        |           |
                              +-------------------+               |           |
                              |      Grade        |<--------------+           |
                              +-------------------+                           |
                              | -id: int          |                           |
                              | -score1: float    |                           |
                              | -score2: float    |                           |
                              | -score3: float    |                           |
                              | -testScore: float |                           |
                              | -finalScore: float|                           |
                              +-------------------+                           |
                              | +isPassed(): bool |                           |
                              | +calculateFinal() |                           |
                              +-------------------+                           |
                                                                              |
                              +-------------------+                           |
                              |   SubjectStat     |<-------------------------+
                              +-------------------+
                              | -subject: Subject |
                              | -avgScore: float  |
                              | -passRate: float  |
                              +-------------------+
```

### Bước 5: Bảng quan hệ chi tiết

| Entity 1 | Entity 2 | Mối quan hệ | Đa thực thể | Khóa ngoại | Giải thích |
|-----------|----------|-------------|-------------|------------|------------|
| Student | User | kế thừa / liên kết 1-1 | 1:1 | studentID trong tblUser | Mỗi SV có 1 tài khoản |
| Student | Registration | sở hữu | 1:N | studentID trong tblRegistration | 1 SV có nhiều đăng ký |
| Class | Registration | được đăng ký | 1:N | classID trong tblRegistration | 1 lớp có nhiều SV |
| Subject | Class | chứa | 1:N | subjectID trong tblClass | 1 môn có nhiều lớp |
| Student | Grade | có điểm | 1:N | studentID trong tblGrade | 1 SV có nhiều điểm |
| Class | Grade | chứa điểm | 1:N | classID trong tblGrade | 1 lớp có nhiều điểm SV |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_StatSubjects" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 7 class: User, Student, Subject, Class, Registration, Grade, SubjectStat |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, StatSubjectFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class SubjectStat) |
|------|----------|----------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> SubjectStat` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-subject: Subject`, `-avgScore: float`, `-passRate: float` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+calculateAvgScore(): float`, `+calculatePassRate(): float` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | `+login(username: String, password: String): User` |
| Student | `<<entity>>` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-address: String` | `+getGrade(subjectId: int): Grade` |
| Subject | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-credits: int` | `+getSubjectById(id: int): Subject`, `+getAllSubjects(): List<Subject>` |
| Class | `<<entity>>` | `-id: int`, `-classCode: String`, `-className: String`, `-classroom: String`, `-maxStudents: int`, `-subjectID: int` | `+getClassById(id: int): Class` |
| Registration | `<<entity>>` | `-id: int`, `-semester: String`, `-regDate: Date`, `-status: String`, `-studentID: int`, `-classID: int` | `+getRegistrationsByClass(classId: int): List<Registration>` |
| Grade | `<<entity>>` | `-id: int`, `-regId: int`, `-score1: float`, `-score2: float`, `-score3: float`, `-testScore: float`, `-finalScore: float` | `+calculateFinalScore(): float`, `+isPassed(): boolean` |
| SubjectStat | `<<entity>>` | `-subject: Subject`, `-avgScore: float`, `-passRate: float` | `+calculateAvgScore(): float`, `+calculatePassRate(): float` |

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
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (StatSubjectFrm → SubjectDAO) |

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
| Student | Grade | Association | 1 → * | Mỗi sinh viên có nhiều điểm |
| Class | Grade | Association | 1 → * | Mỗi lớp có nhiều điểm sinh viên |
| SubjectStat | Subject | Dependency | 1 → 1 | SubjectStat phụ thuộc vào Subject để tính toán |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Subject → Class (1-n, Association)*

1. Click chuột phải vào class Subject → chọn **Association** → kéo đến class Class.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `offers`.

*Ví dụ 2: Vẽ quan hệ SubjectStat → Subject (Dependency)*

1. Click chuột phải vào class SubjectStat → chọn **Dependency** (đường dashed) → kéo đến class Subject.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `1`.
4. Đường dashed mũi tên tam giác rỗng (▷) thể hiện Dependency — SubjectStat sử dụng Subject để tính toán thống kê.
5. Đặt tên dependency: `depends_on`.

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
  Gán cho entity class: User.

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
  Đầu ra: List<SubjectStat>
  Gán cho entity class: Subject.

Staff click vào dòng môn học → Hệ thống hiển thị chi tiết điểm → cần phương thức:
  Tên: getGradeBySubject()
  Đầu vào: subjectId (int)
  Đầu ra: List<Grade>
  Gán cho entity class: Grade.

Hệ thống lấy thông tin sinh viên cho mỗi dòng điểm → cần phương thức:
  Tên: getStudentById()
  Đầu vào: studentId (int)
  Đầu ra: Student
  Gán cho entity class: Student.

Hệ thống lấy thông tin lớp cho mỗi dòng điểm → cần phương thức:
  Tên: getClassById()
  Đầu vào: classId (int)
  Đầu ra: Class
  Gán cho entity class: Class.

### Tóm tắt
View classes: LoginFrm, HomeFrm, StatSubjectFrm
Phương thức: login(), getSubjectStat(), getGradeBySubject(), getStudentById(), getClassById()

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
| SubjectDAO | `getAllSubjects(): List<Subject>` | `List<Subject>` | Lấy tất cả môn học |
| SubjectDAO | `getSubjectStat(): List<SubjectStat>` | `List<SubjectStat>` | Thống kê: điểm TB, tỷ lệ đậu mỗi môn |
| GradeDAO | `getGradeBySubject(subjectId: int): List<Grade>` | `List<Grade>` | Lấy chi tiết điểm tất cả SV trong môn |
| GradeDAO | `getAvgScoreBySubject(subjectId: int): float` | `float` | Tính điểm TB môn |
| GradeDAO | `getPassRateBySubject(subjectId: int): float` | `float` | Tính tỷ lệ đậu (%) |
| ClassDAO | `getClassById(classId: int): Class` | `Class` | Lấy thông tin lớp |
| StudentDAO | `getStudentById(studentId: int): Student` | `Student` | Lấy thông tin SV |

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
| address | String | Địa chỉ |
| user | User | Đối tượng tài khoản |
| registrations | List<Registration> | Danh sách đăng ký |
| grades | List<Grade> | Danh sách điểm |

**Subject:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| code | String | Mã môn |
| name | String | Tên môn |
| credits | int | Số tín chỉ |
| classes | List<Class> | Danh sách lớp học |

**Class:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| classCode | String | Mã lớp |
| className | String | Tên lớp |
| classroom | String | Phòng học |
| maxStudents | int | Sĩ số tối đa |
| subjectID | int | FK -> tblSubject.ID |
| subject | Subject | Đối tượng môn học |
| registrations | List<Registration> | Danh sách đăng ký |
| grades | List<Grade> | Danh sách điểm |

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

**Grade:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| score1 | float | Điểm thành phần 1 |
| score2 | float | Điểm thành phần 2 |
| score3 | float | Điểm thành phần 3 |
| testScore | float | Điểm thi |
| finalScore | float | Điểm cuối cùng |
| studentID | int | FK -> tblStudent.ID |
| classID | int | FK -> tblClass.ID |
| student | Student | Đối tượng sinh viên |
| classObj | Class | Đối tượng lớp học |

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
| address | varchar(200) | |
| userID | int | FK -> tblUser.ID |

**tblSubject:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| code | varchar(20) | UNIQUE, NOT NULL |
| name | varchar(100) | NOT NULL |
| credits | int | NOT NULL |

**tblClass:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| classCode | varchar(20) | UNIQUE, NOT NULL |
| className | varchar(100) | NOT NULL |
| classroom | varchar(50) | |
| maxStudents | int | |
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
| score1 | float | |
| score2 | float | |
| score3 | float | |
| testScore | float | |
| finalScore | float | |
| studentID | int | FK -> tblStudent.ID, NOT NULL |
| classID | int | FK -> tblClass.ID, NOT NULL |

### Hướng dẫn Visual Paradigm

1. Mở Visual Paradigm -> File -> New Project -> đặt tên "StudentStatSubjects".
2. Vào Diagram -> Class Diagram -> tạo class diagram mới.
3. Tạo 7 class: User, Student, Subject, Class, Registration, Grade, SubjectStat.
4. Với mỗi class, click chuột phải -> Add Attribute -> nhập thuộc tính theo bảng Entity classes.
5. Thêm method cho Grade: click chuột phải -> Add Operation -> `isPassed(): boolean` (finalScore >= 4.0), `calculateFinal(): float`.
6. Thêm method cho SubjectStat: `calcAvgScore(): float`, `calcPassRate(): float`.
7. Kéo connector "Association" từ Student đến Registration (1:N).
8. Kéo connector "Association" từ Class đến Registration (1:N).
9. Kéo connector "Association" từ Subject đến Class (1:N).
10. Kéo connector "Association" từ Student đến Grade (1:N).
11. Kéo connector "Association" từ Class đến Grade (1:N).
12. Kéo connector "Dependency" từ SubjectStat đến Subject (dùng nét đứt).
13. Đặt tên và đa thực thể cho mỗi mối quan hệ.

---

## Câu 4: Sequence Diagram

### Hướng dẫn Visual Paradigm — Các bước vẽ

1. Mở Visual Paradigm -> Diagram -> Sequence Diagram.
2. Tạo các lifeline: `:Staff`, `:LoginFrm`, `:HomeFrm`, `:StatSubjectFrm`, `:SubjectDAO`, `:GradeDAO`, `:ClassDAO`, `:StudentDAO`.
3. Kéo message từ `:Staff` đến `:LoginFrm`: "login(username, password)".
4. Kéo return message: "return User".
5. Kéo message từ `:Staff` đến `:HomeFrm`: "selectStatistics()".
6. Kéo message từ `:HomeFrm` đến `:StatSubjectFrm`: "open()".
7. Kéo message từ `:StatSubjectFrm` đến `:SubjectDAO`: "getSubjectStat()".
8. Kéo return message: "return List<SubjectStat>".
9. Kéo message self trên `:StatSubjectFrm`: "sortbyPassRateDesc()".
10. Kéo message từ `:Staff` đến `:StatSubjectFrm`: "clickSubject(subjectId)".
11. Kéo message từ `:StatSubjectFrm` đến `:GradeDAO`: "getGradeBySubject(subjectId)".
12. Kéo return message: "return List<Grade>".
13. Vẽ vòng lặp (loop fragment) gọi `:StudentDAO.getStudentById(studentId)` và `:ClassDAO.getClassById(classId)` cho mỗi Grade.
14. Kéo message self trên `:StatSubjectFrm`: "displayGradeDetail(grades)".

### Sequence Diagram (ASCII)

```
Staff         LoginFrm      HomeFrm      StatSubjectFrm    SubjectDAO     GradeDAO     ClassDAO    StudentDAO
  |              |             |               |                |              |             |             |
  |---login------>|             |               |                |              |             |             |
  |<--return User-|             |               |                |              |             |             |
  |              |             |               |                |              |             |             |
  |---selectStatistics()------>|               |                |              |             |             |
  |              |             |---open()----->|                |              |             |             |
  |              |             |               |                |              |             |             |
  |              |             |               |---getSubjectStat()---------->|             |             |
  |              |             |               |<--return List<SubjectStat>---|             |             |
  |              |             |               |                |              |             |             |
  |              |             |               |---sortbyPassRateDesc()------>|             |             |
  |              |             |               |                |              |             |             |
  |              |             |               | [display subject table sorted by passRate DESC]        |
  |              |             |               |                |              |             |             |
  |---clickSubject(1)--------->|               |                |              |             |             |
  |              |             |               |---getGradeBySubject(1)------>|             |             |
  |              |             |               |<--return List<Grade>---------|             |             |
  |              |             |               |                |              |             |             |
  |              |             |               | [loop: for each Grade]       |             |             |
  |              |             |               |---getStudentById(studentId)--|------------>|             |
  |              |             |               |<--return Student-------------|-------------|             |
  |              |             |               |---getClassById(classId)------|-------------|------------>|
  |              |             |               |<--return Class---------------|-------------|-------------|
  |              |             |               |                |              |             |             |
  |              |             |               |---displayGradeDetail(grades) |             |             |
  |<---show grade detail table-|               |                |              |             |             |
  |              |             |               |                |              |             |             |
  |---clickBack->|             |               |                |              |             |             |
  |              |             |               | [hide detail, show subject table]         |             |
```

### Bảng giải thích chi tiết

| Bước | Từ | Đến | Message | Giải thích |
|------|-----|------|---------|------------|
| 1 | Staff | LoginFrm | `login("staff01", "******")` | Staff nhập thông tin đăng nhập |
| 2 | LoginFrm | Staff | `return User` | Trả về đối tượng User sau xác thực |
| 3 | Staff | HomeFrm | `selectStatistics()` | Staff chọn menu Statistics |
| 4 | HomeFrm | Staff | `showSubMenu(["Student statistics", "Subject statistics"])` | Hiển thị submenu thống kê |
| 5 | Staff | HomeFrm | `selectSubMenu("Subject statistics")` | Staff chọn "Subject statistics" |
| 6 | HomeFrm | StatSubjectFrm | `open()` | Mở giao diện thống kê môn học |
| 7 | StatSubjectFrm | SubjectDAO | `getSubjectStat()` | Truy vấn thống kê tất cả môn: điểm TB, tỷ lệ đậu |
| 8 | SubjectDAO | StatSubjectFrm | `return List<SubjectStat>` | Trả về danh sách thống kê môn |
| 9 | StatSubjectFrm | StatSubjectFrm | `sortbyPassRateDesc()` | Sắp xếp theo tỷ lệ đậu giảm dần |
| 10 | StatSubjectFrm | Staff | `displaySubjectStatTable(subjectStats)` | Hiển thị bảng thống kê môn |
| 11 | Staff | StatSubjectFrm | `clickSubject(subjectId=1)` | Staff nhấn vào dòng Nhap mon CNPM |
| 12 | StatSubjectFrm | GradeDAO | `getGradeBySubject(1)` | Truy vấn điểm tất cả SV trong môn |
| 13 | GradeDAO | StatSubjectFrm | `return List<Grade>` | Trả về danh sách điểm |
| 14 | StatSubjectFrm | StudentDAO | `getStudentById(studentId)` | Lấy thông tin SV cho mỗi dòng điểm |
| 15 | StudentDAO | StatSubjectFrm | `return Student` | Trả về mã SV, tên SV |
| 16 | StatSubjectFrm | ClassDAO | `getClassById(classId)` | Lấy thông tin lớp cho mỗi dòng điểm |
| 17 | ClassDAO | StatSubjectFrm | `return Class` | Trả về tên lớp |
| 18 | StatSubjectFrm | StatSubjectFrm | `buildGradeDetailTable(grades, students, classes)` | Tạo bảng chi tiết điểm |
| 19 | StatSubjectFrm | Staff | `displayGradeDetail(gradeDetailTable)` | Hiển thị bảng chi tiết điểm SV |
| 20 | Staff | StatSubjectFrm | `clickBack()` | Staff nhấn nút Back |
| 21 | StatSubjectFrm | StatSubjectFrm | `hideGradeDetail()` | Ẩn bảng chi tiết |
| 22 | StatSubjectFrm | Staff | `showSubjectStatTable()` | Hiển thị lại bảng thống kê môn |
| 23 | Staff | StatSubjectFrm | `clickSubject(subjectId=2)` | Staff nhấn vào dòng môn khác |
| 24 | StatSubjectFrm | GradeDAO | `getGradeBySubject(2)` | Truy vấn điểm môn thứ 2 |

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

### TC01: Thống kê môn học có dữ liệu đầy đủ

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | pass123 | staff |
| 2 | sv01 | pass123 | student |
| 3 | sv02 | pass123 | student |
| 4 | sv03 | pass123 | student |

**tblStudent:**
| ID | studentCode | name | dob | course | address | userID |
|----|-------------|------|-----|--------|---------|--------|
| 1 | SV2021001 | Nguyen Van A | 2003-05-15 | K65 | Ha Noi | 2 |
| 2 | SV2021002 | Tran Thi B | 2003-08-20 | K65 | Hai Phong | 3 |
| 3 | SV2021003 | Le Van C | 2003-03-10 | K65 | Da Nang | 4 |

**tblSubject:**
| ID | code | name | credits |
|----|------|------|---------|
| 1 | INT1340 | Nhap mon CNPM | 3 |
| 2 | MAT1042 | Toan cao cap | 4 |
| 3 | ENG1024 | Tieng Anh | 3 |

**tblClass:**
| ID | classCode | className | classroom | maxStudents | subjectID |
|----|-----------|-----------|-----------|-------------|-----------|
| 1 | L01 | Lop 01 | A101 | 40 | 1 |
| 2 | L02 | Lop 02 | A102 | 40 | 1 |
| 3 | L03 | Lop 03 | B202 | 35 | 2 |
| 4 | L04 | Lop 04 | C301 | 30 | 3 |

**tblRegistration:**
| ID | semester | regDate | status | studentID | classID |
|----|----------|---------|--------|-----------|---------|
| 1 | 20251 | 2025-08-20 | active | 1 | 1 |
| 2 | 20251 | 2025-08-20 | active | 2 | 1 |
| 3 | 20251 | 2025-08-20 | active | 3 | 2 |
| 4 | 20251 | 2025-08-20 | active | 1 | 3 |
| 5 | 20251 | 2025-08-20 | active | 2 | 4 |

**tblGrade:**
| ID | score1 | score2 | score3 | testScore | finalScore | studentID | classID |
|----|--------|--------|--------|-----------|------------|-----------|---------|
| 1 | 8.0 | 7.0 | 9.0 | 8.0 | 8.0 | 1 | 1 |
| 2 | 6.0 | 7.0 | 7.5 | 7.0 | 6.9 | 2 | 1 |
| 3 | 4.0 | 5.0 | 5.5 | 4.0 | 4.6 | 3 | 2 |
| 4 | 7.0 | 6.5 | 8.0 | 7.5 | 7.3 | 1 | 3 |
| 5 | 8.0 | 8.5 | 9.0 | 8.0 | 8.3 | 2 | 4 |

### Kịch bản test TC01

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Mở ứng dụng. Giao diện Login xuất hiện. | Hiển thị ô username, password, nút Login |
| 2 | Nhập username `staff01`, password `pass123`, nhấn Login. | Chuyển sang giao diện Home |
| 3 | Chọn menu **Statistics** -> **Subject statistics**. | Giao diện StatSubjectFrm xuất hiện |
| 4 | Kiểm tra bảng thống kê: dòng 1 = ENG1024 Tieng Anh 3TC, TB 8.3, tỷ lệ đậu 100% (1/1 SV >= 4.0). | Đúng dữ liệu, sắp xếp theo tỷ lệ đậu giảm dần |
| 5 | Kiểm tra bảng thống kê: dòng 2 = INT1340 Nhap mon CNPM 3TC, TB ~6.5 (TB của 8.0, 6.9, 4.6), tỷ lệ đậu 100% (3/3 SV >= 4.0). | Đúng dữ liệu |
| 6 | Kiểm tra bảng thống kê: dòng 3 = MAT1042 Toan cao cap 4TC, TB 7.3, tỷ lệ đậu 100% (1/1 SV >= 4.0). | Đúng dữ liệu |
| 7 | Nhấn vào dòng INT1340 (Nhap mon CNPM). | Bảng chi tiết điểm xuất hiện |
| 8 | Kiểm tra chi tiết: SV2021001 Nguyen Van A, L01 (8.0, 7.0, 9.0, 8.0, 8.0, Dau); SV2021002 Tran Thi B, L01 (6.0, 7.0, 7.5, 7.0, 6.9, Dau); SV2021003 Le Van C, L02 (4.0, 5.0, 5.5, 4.0, 4.6, Dau). | Dữ liệu đúng, kết quả Đậu/Rớt hiển thị |
| 9 | Nhấn nút **Back**. | Bảng chi tiết ẩn, quay về bảng thống kê môn |

### Database sau khi test

Không thay đổi. Đây là chức năng chỉ đọc (read-only), không có INSERT/UPDATE/DELETE.
