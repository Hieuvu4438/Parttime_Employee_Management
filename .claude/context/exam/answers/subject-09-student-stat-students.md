# Subject No. 09 — Student Results — Module "Statistics of students"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, Grade, User

---

## Câu 1: Scenario — Thống kê sinh viên

### Kịch bản chính

| Bước | Diễn biến | Giao diện hiển thị |
|------|-----------|---------------------|
| 1 | Manage staff mở ứng dụng. Giao diện Login xuất hiện với ô nhập username, password và nút Login. | **LoginFrm** |
| 2 | Staff nhập username `staff01`, password `******`, nhấn nút Login. | **LoginFrm** |
| 3 | Hệ thống xác thực thành công. Giao diện Home xuất hiện với các menu: Schedule, Enter grades, Register, View schedule, Statistics. | **HomeFrm** |
| 4 | Staff chọn menu **Statistics**. | **HomeFrm** |
| 5 | Hệ thống hiển thị submenu: "Student statistics", "Subject statistics". Staff chọn **Student statistics**. | **HomeFrm** — submenu |
| 6 | Giao diện Stat Student xuất hiện. Hệ thống hiển thị danh sách sinh viên với các cột: Mã SV, Tên SV, Khóa, Học kỳ, Tổng tín chỉ đã học, Điểm TB học kỳ. Danh sách sắp xếp theo điểm TB từ cao đến thấp. | **StatStudentFrm** — bảng danh sách |
| 7 | Staff xem danh sách: dòng 1 = SV2021001 Nguyen Van A, K65, HK20251, 10 TC, GPA 3.5; dòng 2 = SV2021002 Tran Thi B, K65, HK20251, 13 TC, GPA 3.2; dòng 3 = SV2021003 Le Van C, K65, HK20251, 9 TC, GPA 2.8. | **StatStudentFrm** — bảng danh sách |
| 8 | Staff chọn combobox học kỳ "HK20251" (nếu có bộ lọc). | **StatStudentFrm** |
| 9 | Staff nhấn vào dòng SV2021001 (Nguyen Van A). | **StatStudentFrm** |
| 10 | Hệ thống hiển thị chi tiết điểm từng môn trong học kỳ của sinh viên: Mã môn, Tên môn, Số tín chỉ, Điểm thành phần 1, Điểm thành phần 2, Điểm thành phần 3, Điểm thi, Điểm cuối cùng. | **StatStudentFrm** — bảng chi tiết |
| 11 | Staff xem chi tiết: INT1340 Nhap mon CNPM 3TC (8.0, 7.0, 9.0, 8.0, 8.0); MAT1042 Toan cao cap 4TC (7.0, 6.5, 8.0, 7.5, 7.3); ENG1024 Tieng Anh 3TC (9.0, 8.0, 8.5, 9.0, 8.7). | **StatStudentFrm** — bảng chi tiết |
| 12 | Staff nhấn nút **Back** hoặc chọn dòng SV khác để xem. Quay về bảng danh sách sinh viên. | **StatStudentFrm** — bảng danh sách |

### Kịch bản ngoại lệ

| Mã | Tình huống | Diễn biến |
|----|-----------|-----------|
| EL1 | Không có sinh viên nào trong học kỳ được chọn | Hệ thống hiển thị bảng trống và thông báo "Khong co du lieu sinh vien trong hoc ky nay". |
| EL2 | Sinh viên chưa có điểm (chỉ đăng ký, chưa nhập điểm) | Dòng sinh viên hiển thị Tổng tín chỉ = 0, Điểm TB = N/A. Nhấn vào không hiển thị chi tiết. |
| EL3 | Staff chọn học kỳ không tồn tại | Hệ thống hiển thị bảng trống, không có lỗi crash. |

---

## Câu 2: Entity Class Diagram

### Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý kết quả học tập. Manage staff đăng nhập để xem thống kê kết quả học tập của sinh viên. Mỗi sinh viên đăng ký nhiều lớp học (Class) trong một học kỳ. Mỗi lớp thuộc về một môn học (Subject). Mỗi môn học có số tín chỉ riêng. Sau khi hoàn thành lớp học, sinh viên có điểm (Grade) bao gồm điểm thành phần và điểm thi. Hệ thống tính điểm trung bình học kỳ (GPA) dựa trên điểm và tín chỉ của các môn. Staff có thể nhấn vào từng sinh viên để xem chi tiết điểm từng môn.

### Bước 2: Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Student (Sinh viên) | Entity class | Đối tượng được thống kê, có mã, tên, khóa |
| Subject (Môn học) | Entity class | Đối tượng học, có mã, tên, tín chỉ |
| Class (Lớp học) | Entity class | Lớp cụ thể của môn trong học kỳ |
| Registration (Đăng ký) | Entity class | Liên kết sinh viên với lớp học |
| Grade (Điểm) | Entity class | Kết quả học tập, có nhiều cột điểm |
| User (Nhân viên quản lý) | Entity class | Người thao tác thống kê |
| GPA (Điểm TB) | Không phải class | Giá trị tính toán, không phải entity |
| Học kỳ | Thuộc tính | Thuộc tính của Registration |
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
                              | +getGPA(semester)  |
                              | +getTotalCredits() |
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
                                       | *                         | 1
                                       |                           |
                                       | 1                         | *
                              +-------------------+        +-------------------+
                              |      Class        |<-------|                   |
                              +-------------------+        +-------------------+
                              | -id: int          |               |
                              | -classCode: String|               |
                              | -className: String|               |
                              | -classroom: String|               |
                              | -maxStudents: int |               |
                              +-------------------+               |
                                       | 1                        | 1
                                       |                          |
                                       | *                        | *
                              +-------------------+               |
                              |      Grade        |<--------------+
                              +-------------------+
                              | -id: int          |
                              | -score1: float    |
                              | -score2: float    |
                              | -score3: float    |
                              | -testScore: float |
                              | -finalScore: float|
                              +-------------------+
                              | +calculateFinal() |
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
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_StatStudents" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 6 class: User, Student, Subject, Class, Registration, Grade |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, StatStudentFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Student) |
|------|----------|------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Student` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-address: String` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+getGPA(semester: String): float`, `+getTotalCredits(): int` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | `+login(username: String, password: String): User` |
| Student | `<<entity>>` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-address: String` | `+getGPA(semester: String): float`, `+getTotalCredits(): int` |
| Subject | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-credits: int` | `+getSubjectById(id: int): Subject` |
| Class | `<<entity>>` | `-id: int`, `-classCode: String`, `-className: String`, `-classroom: String`, `-maxStudents: int`, `-subjectID: int` | `+getClassById(id: int): Class` |
| Registration | `<<entity>>` | `-id: int`, `-semester: String`, `-regDate: Date`, `-status: String`, `-studentID: int`, `-classID: int` | `+getRegistrationsByStudent(studentId: int, semester: String): List<Registration>` |
| Grade | `<<entity>>` | `-id: int`, `-regId: int`, `-score1: float`, `-score2: float`, `-score3: float`, `-testScore: float`, `-finalScore: float` | `+calculateFinalScore(): float`, `+isPassed(): boolean` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuSchedule: JMenuItem`, `-menuEnterGrades: JMenuItem`, `-menuRegister: JMenuItem`, `-menuViewSchedule: JMenuItem`, `-menuStatistics: JMenuItem`, `-subMenuStudentStat: JMenuItem`, `-subMenuSubjectStat: JMenuItem` |
| StatStudentFrm | `<<boundary>>` | `-inSemester: JComboBox`, `-outStudentStatTable: JTable`, `-outGradeDetailTable: JTable`, `-subBack: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (StatStudentFrm → GradeDAO) |

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

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Student → Grade (1-n, Association)*

1. Click chuột phải vào class Student → chọn **Association** → kéo đến class Grade.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `has_grade`.

*Ví dụ 2: Vẽ quan hệ Class → Grade (1-n, Association)*

1. Click chuột phải vào class Class → chọn **Association** → kéo đến class Grade.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `contains_grades`.

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

Staff chọn Student statistics → Giao diện thống kê sinh viên xuất hiện → cần class: StatStudentFrm
  Combobox chọn học kỳ → inSemester
  Bảng thống kê sinh viên (click được) → outStudentStatTable
  Bảng chi tiết điểm (hiện khi click dòng SV) → outGradeDetailTable
  Nút Back → subBack

Hệ thống tải danh sách sinh viên kèm GPA → cần phương thức:
  Tên: getStudentStat()
  Đầu vào: semester (String)
  Đầu ra: List<StudentStat>
  Gán cho entity class: Student.

Staff click vào dòng sinh viên → Hệ thống hiển thị chi tiết điểm → cần phương thức:
  Tên: getGradeByStudentSemester()
  Đầu vào: studentId (int), semester (String)
  Đầu ra: List<Grade>
  Gán cho entity class: Grade.

Hệ thống lấy thông tin môn học cho mỗi dòng điểm → cần phương thức:
  Tên: getSubjectById()
  Đầu vào: subjectId (int)
  Đầu ra: Subject
  Gán cho entity class: Subject.

### Tóm tắt
View classes: LoginFrm, HomeFrm, StatStudentFrm
Phương thức: login(), getStudentStat(), getGradeByStudentSemester(), getSubjectById()

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

**StatStudentFrm:**
- `inSemester`: ComboBox chọn học kỳ (String) — giá trị: "20251", "20242", ...
- `outStudentStatTable`: Bảng thống kê sinh viên (JTable) — click được
- `outStudentCode`: Cột Mã SV (String)
- `outStudentName`: Cột Tên SV (String)
- `outCourse`: Cột Khóa (String)
- `outSemester`: Cột Học kỳ (String)
- `outTotalCredits`: Cột Tổng tín chỉ (int)
- `outGPA`: Cột Điểm TB học kỳ (float)
- `outGradeDetailTable`: Bảng chi tiết điểm (JTable) — hiển thị khi click dòng SV
- `outSubjectCode`: Cột Mã môn (String)
- `outSubjectName`: Cột Tên môn (String)
- `outCredits`: Cột Số tín chỉ (int)
- `outScore1`: Cột Điểm thành phần 1 (float)
- `outScore2`: Cột Điểm thành phần 2 (float)
- `outScore3`: Cột Điểm thành phần 3 (float)
- `outTestScore`: Cột Điểm thi (float)
- `outFinalScore`: Cột Điểm cuối cùng (float)
- `subBack`: Button "Back"

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| UserDAO | `login(username: String, password: String): User` | `User` hoặc `null` | Xác thực đăng nhập |
| StudentDAO | `getStudentStat(semester: String): List<StudentStat>` | `List<StudentStat>` | Lấy danh sách SV kèm GPA, tổng TC |
| StudentDAO | `getStudentById(studentId: int): Student` | `Student` | Lấy thông tin SV |
| GradeDAO | `getGradeByStudentSemester(studentId: int, semester: String): List<Grade>` | `List<Grade>` | Lấy chi tiết điểm SV theo học kỳ |
| RegistrationDAO | `getRegistrationByStudentSemester(studentId: int, semester: String): List<Registration>` | `List<Registration>` | Lấy đăng ký theo SV và học kỳ |
| SubjectDAO | `getSubjectById(subjectId: int): Subject` | `Subject` | Lấy thông tin môn học |

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
| course | String | Khóa học (K65, K66, ...) |
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
| finalScore | float | Điểm cuối cùng (tính toán) |
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

1. Mở Visual Paradigm -> File -> New Project -> đặt tên "StudentStatStudents".
2. Vào Diagram -> Class Diagram -> tạo class diagram mới.
3. Tạo 6 class: User, Student, Subject, Class, Registration, Grade.
4. Với mỗi class, click chuột phải -> Add Attribute -> nhập thuộc tính theo bảng Entity classes.
5. Thêm method cho Grade: click chuột phải -> Add Operation -> `calculateFinalScore(): float`.
6. Thêm method cho Student: `getGPA(semester: String): float`, `getTotalCredits(semester: String): int`.
7. Kéo connector "Association" từ Student đến Registration (1:N).
8. Kéo connector "Association" từ Class đến Registration (1:N).
9. Kéo connector "Association" từ Subject đến Class (1:N).
10. Kéo connector "Association" từ Student đến Grade (1:N).
11. Kéo connector "Association" từ Class đến Grade (1:N).
12. Đặt tên và đa thực thể cho mỗi mối quan hệ.

---

## Câu 4: Sequence Diagram

### Hướng dẫn Visual Paradigm — Các bước vẽ

1. Mở Visual Paradigm -> Diagram -> Sequence Diagram.
2. Tạo các lifeline: `:Staff`, `:LoginFrm`, `:HomeFrm`, `:StatStudentFrm`, `:StudentDAO`, `:GradeDAO`, `:SubjectDAO`.
3. Kéo message từ `:Staff` đến `:LoginFrm`: "login(username, password)".
4. Kéo return message: "return User".
5. Kéo message từ `:Staff` đến `:HomeFrm`: "selectStatistics()".
6. Kéo message từ `:HomeFrm` đến `:StatStudentFrm`: "open()".
7. Kéo message từ `:StatStudentFrm` đến `:StudentDAO`: "getStudentStat(semester)".
8. Kéo return message: "return List<StudentStat>".
9. Vẽ vòng lặp (loop fragment) với điều kiện "for each StudentStat".
10. Kéo message từ `:Staff` đến `:StatStudentFrm`: "clickStudent(studentId)".
11. Kéo message từ `:StatStudentFrm` đến `:GradeDAO`: "getGradeByStudentSemester(studentId, semester)".
12. Kéo return message: "return List<Grade>".
13. Vẽ vòng lặp gọi `:SubjectDAO.getSubjectById(subjectId)` cho mỗi Grade.
14. Kéo message self trên `:StatStudentFrm`: "displayGradeDetail(grades)".

### Sequence Diagram (ASCII)

```
Staff         LoginFrm      HomeFrm      StatStudentFrm    StudentDAO      GradeDAO      SubjectDAO
  |              |             |               |                |               |              |
  |---login------>|             |               |                |               |              |
  |<--return User-|             |               |                |               |              |
  |              |             |               |                |               |              |
  |---selectStatistics()------>|               |                |               |              |
  |              |             |---open()----->|                |               |              |
  |              |             |               |                |               |              |
  |              |             |               |---getStudentStat(semester)---->|              |
  |              |             |               |<--return List<StudentStat>-----|              |
  |              |             |               |                |               |              |
  |              |             |               | [display student table sorted by GPA DESC]   |
  |              |             |               |                |               |              |
  |---clickStudent(1)--------->|               |                |               |              |
  |              |             |               |---getGradeByStudentSemester(1,semester)----->|
  |              |             |               |<--return List<Grade>--------------------------|
  |              |             |               |                |               |              |
  |              |             |               | [loop: for each Grade]           |              |
  |              |             |               |---getSubjectById(subjectId)------|------------>|
  |              |             |               |<--return Subject-----------------|-------------|
  |              |             |               |                |               |              |
  |              |             |               |---displayGradeDetail(grades)-----|             |
  |<---show grade detail table-|               |                |               |              |
  |              |             |               |                |               |              |
  |---clickBack->|             |               |                |               |              |
  |              |             |               | [hide detail, show student table]|              |
```

### Bảng giải thích chi tiết

| Bước | Từ | Đến | Message | Giải thích |
|------|-----|------|---------|------------|
| 1 | Staff | LoginFrm | `login("staff01", "******")` | Staff nhập thông tin đăng nhập |
| 2 | LoginFrm | Staff | `return User` | Trả về đối tượng User sau xác thực |
| 3 | Staff | HomeFrm | `selectStatistics()` | Staff chọn menu Statistics |
| 4 | HomeFrm | Staff | `showSubMenu(["Student statistics", "Subject statistics"])` | Hiển thị submenu thống kê |
| 5 | Staff | HomeFrm | `selectSubMenu("Student statistics")` | Staff chọn "Student statistics" |
| 6 | HomeFrm | StatStudentFrm | `open()` | Mở giao diện thống kê sinh viên |
| 7 | StatStudentFrm | StudentDAO | `getStudentStat("20251")` | Truy vấn danh sách SV kèm GPA, tổng TC |
| 8 | StudentDAO | StatStudentFrm | `return List<StudentStat>` | Trả về danh sách thống kê SV |
| 9 | StatStudentFrm | StatStudentFrm | `sortbyGPADesc()` | Sắp xếp danh sách theo GPA giảm dần |
| 10 | StatStudentFrm | Staff | `displayStudentStatTable(studentStats)` | Hiển thị bảng thống kê SV |
| 11 | Staff | StatStudentFrm | `clickStudent(studentId=1)` | Staff nhấn vào dòng SV Nguyen Van A |
| 12 | StatStudentFrm | GradeDAO | `getGradeByStudentSemester(1, "20251")` | Truy vấn điểm SV theo học kỳ |
| 13 | GradeDAO | StatStudentFrm | `return List<Grade>` | Trả về danh sách điểm |
| 14 | StatStudentFrm | SubjectDAO | `getSubjectById(subjectId)` | Lấy thông tin môn cho mỗi dòng điểm |
| 15 | SubjectDAO | StatStudentFrm | `return Subject` | Trả về tên môn, mã môn, tín chỉ |
| 16 | StatStudentFrm | StatStudentFrm | `buildGradeDetailTable(grades, subjects)` | Tạo bảng chi tiết điểm |
| 17 | StatStudentFrm | Staff | `displayGradeDetail(gradeDetailTable)` | Hiển thị bảng chi tiết điểm |
| 18 | Staff | StatStudentFrm | `clickBack()` | Staff nhấn nút Back |
| 19 | StatStudentFrm | StatStudentFrm | `hideGradeDetail()` | Ẩn bảng chi tiết |
| 20 | StatStudentFrm | Staff | `showStudentStatTable()` | Hiển thị lại bảng thống kê SV |
| 21 | Staff | StatStudentFrm | `clickStudent(studentId=2)` | Staff nhấn vào dòng SV khác |
| 22 | StatStudentFrm | GradeDAO | `getGradeByStudentSemester(2, "20251")` | Truy vấn điểm SV thứ 2 |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên test case | Mô tả | Kết quả mong đợi |
|----|---------------|-------|------------------|
| TC01 | Thống kê SV có dữ liệu đầy đủ | Staff xem thống kê, click SV có điểm | Bảng SV hiển thị đúng GPA, chi tiết điểm đầy đủ |
| TC02 | Thống kê SV chưa có điểm | SV chỉ đăng ký chưa có điểm | GPA = N/A, tổng TC = 0 |
| TC03 | Thống kê SV theo học kỳ khác | Chọn học kỳ khác | Hiển thị dữ liệu học kỳ tương ứng |
| TC04 | Không có SV trong học kỳ | Học kỳ không có SV đăng ký | Bảng trống, thông báo |
| TC05 | Sắp xếp theo GPA | Kiểm tra thứ tự | SV GPA cao nhất đứng đầu |

### TC01: Thống kê sinh viên có dữ liệu đầy đủ

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | pass123 | staff |
| 2 | sv01 | pass123 | student |
| 3 | sv02 | pass123 | student |

**tblStudent:**
| ID | studentCode | name | dob | course | address | userID |
|----|-------------|------|-----|--------|---------|--------|
| 1 | SV2021001 | Nguyen Van A | 2003-05-15 | K65 | Ha Noi | 2 |
| 2 | SV2021002 | Tran Thi B | 2003-08-20 | K65 | Hai Phong | 3 |

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
| 2 | L03 | Lop 03 | B202 | 35 | 2 |
| 3 | L05 | Lop 05 | C301 | 30 | 3 |

**tblRegistration:**
| ID | semester | regDate | status | studentID | classID |
|----|----------|---------|--------|-----------|---------|
| 1 | 20251 | 2025-08-20 | active | 1 | 1 |
| 2 | 20251 | 2025-08-20 | active | 1 | 2 |
| 3 | 20251 | 2025-08-20 | active | 2 | 1 |
| 4 | 20251 | 2025-08-20 | active | 2 | 3 |

**tblGrade:**
| ID | score1 | score2 | score3 | testScore | finalScore | studentID | classID |
|----|--------|--------|--------|-----------|------------|-----------|---------|
| 1 | 8.0 | 7.0 | 9.0 | 8.0 | 8.0 | 1 | 1 |
| 2 | 7.0 | 6.5 | 8.0 | 7.5 | 7.3 | 1 | 2 |
| 3 | 6.0 | 7.0 | 7.5 | 7.0 | 6.9 | 2 | 1 |
| 4 | 8.0 | 8.5 | 9.0 | 8.0 | 8.3 | 2 | 3 |

### Kịch bản test TC01

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Mở ứng dụng. Giao diện Login xuất hiện. | Hiển thị ô username, password, nút Login |
| 2 | Nhập username `staff01`, password `pass123`, nhấn Login. | Chuyển sang giao diện Home |
| 3 | Chọn menu **Statistics** -> **Student statistics**. | Giao diện StatStudentFrm xuất hiện |
| 4 | Chọn học kỳ "20251" từ combobox. | Bảng thống kê sinh viên hiển thị |
| 5 | Kiểm tra bảng: dòng 1 = SV2021001 Nguyen Van A, K65, 20251, 7 TC, GPA ~3.5 (TB điểm TB các môn có trọng số TC). | Dữ liệu đúng, sắp xếp theo GPA giảm dần |
| 6 | Kiểm tra bảng: dòng 2 = SV2021002 Tran Thi B, K65, 20251, 6 TC, GPA ~3.2. | Dữ liệu đúng |
| 7 | Nhấn vào dòng SV2021001 (Nguyen Van A). | Bảng chi tiết điểm xuất hiện |
| 8 | Kiểm tra chi tiết: INT1340 Nhap mon CNPM 3TC (8.0, 7.0, 9.0, 8.0, 8.0); MAT1042 Toan cao cap 4TC (7.0, 6.5, 8.0, 7.5, 7.3). | Dữ liệu đúng |
| 9 | Nhấn nút **Back**. | Bảng chi tiết ẩn, quay về bảng thống kê SV |

### Database sau khi test

Không thay đổi. Đây là chức năng chỉ đọc (read-only), không có INSERT/UPDATE/DELETE.
