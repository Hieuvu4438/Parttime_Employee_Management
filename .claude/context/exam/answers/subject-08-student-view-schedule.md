# Subject No. 08 — Student Results — Module "View Student's schedule"

> **Domain:** Student Results Management
> **Entity classes:** Student, Subject, Class, Registration, TimeSlot, User

---

## Câu 1: Scenario — Xem thời khóa biểu sinh viên

### Kịch bản chính

| Bước | Diễn biến | Giao diện hiển thị |
|------|-----------|---------------------|
| 1 | Student mở ứng dụng. Giao diện Login xuất hiện với ô nhập username, password và nút Login. | **LoginFrm** |
| 2 | Student nhập username `sv01`, password `******`, nhấn nút Login. | **LoginFrm** |
| 3 | Hệ thống xác thực thành công. Giao diện Home xuất hiện với các menu: Register, View schedule, Statistics. | **HomeFrm** |
| 4 | Student chọn menu **View schedule**. | **HomeFrm** |
| 5 | Giao diện View Schedule xuất hiện với hộp chọn (combobox) cách xem thời khóa biểu: "Theo tuần" (Weekly) và "Theo học kỳ" (Semester). Mặc định chưa chọn gì. | **ViewScheduleFrm** |
| 6 | Student chọn "Theo tuần" (Weekly) từ combobox. | **ViewScheduleFrm** |
| 7 | Hệ thống hiển thị thời khóa biểu tuần hiện tại. Bảng có 7 cột (Thứ 2, Thứ 3, Thứ 4, Thứ 5, Thứ 6, Thứ 7, Chủ nhật) và 6 dòng (Kíp 1 đến Kíp 6). Mỗi ô chứa tên môn, phòng học, tên lớp tương ứng. | **ViewScheduleFrm** — bảng 7x6 |
| 8 | Student xem bảng: Thứ 2 Kíp 1 = "Nhap mon CNPM / A101 / L01", Thứ 4 Kíp 3 = "Toan cao cap / B202 / L03", Thứ 6 Kíp 2 = "Tieng Anh / C301 / L05", các ô khác trống. | **ViewScheduleFrm** — bảng 7x6 |
| 9 | Student nhấn vào ô "Thứ 2, Kíp 1" (Nhap mon CNPM). | **ViewScheduleFrm** |
| 10 | Hệ thống hiển thị chi tiết kíp học: Mã môn INT1340, Tên môn Nhap mon CNPM, Mã lớp L01, Tên lớp Lop 01, Phòng A101, Giảng viên Nguyen Van A, Giờ bắt đầu 7:30, Giờ kết thúc 9:30, Thứ 2. | **ViewScheduleFrm** — panel chi tiết |
| 11 | Student nhấn nút **Back** để quay lại bảng thời khóa biểu. | **ViewScheduleFrm** — bảng 7x6 |
| 12 | Student nhấn nút **Back** để quay về giao diện Home. | **HomeFrm** |

### Kịch bản ngoại lệ

| Mã | Tình huống | Diễn biến |
|----|-----------|-----------|
| EL1 | Sinh viên chưa đăng ký môn nào trong học kỳ | Hệ thống hiển thị bảng thời khóa biểu 7x6 toàn bộ ô trống và thông báo "Ban chua dang ky mon hoc nao trong hoc ky nay". |
| EL2 | Tuần hiện tại nằm ngoài thời gian học kỳ (kỳ nghỉ) | Hệ thống thông báo "Tuan hien tai khong nam trong hoc ky" và không hiển thị bảng. |
| EL3 | Sinh viên nhấn vào ô trống trong bảng thời khóa biểu | Hệ thống không hiển thị chi tiết, không có phản ứng gì (ô trống không có sự kiện click). |

---

## Câu 2: Entity Class Diagram

### Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên

Hệ thống quản lý kết quả học tập của sinh viên. Sinh viên (mã sinh viên, mật khẩu, tên, ngày sinh, khóa học, quê quán, địa chỉ) đăng nhập để xem thời khóa biểu cá nhân. Mỗi sinh viên đăng ký nhiều lớp học (Class) trong một học kỳ, mỗi học kỳ từ 10 đến 15 tín chỉ. Mỗi lớp (mã lớp, tên lớp, sĩ số tối đa, phòng học, giảng viên, khung giờ cố định trong tuần) thuộc về một môn học (Subject: mã môn, tên môn, số tín chỉ). Mỗi môn có thể có nhiều môn tiên quyết. Thời khóa biểu của sinh viên được tạo từ các Registration (đăng ký) liên kết sinh viên với lớp học. Sinh viên không được đăng ký hai lớp có cùng khung giờ.

### Bước 2: Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Student (Sinh viên) | Entity class | Đối tượng chính, xem thời khóa biểu |
| Subject (Môn học) | Entity class | Đối tượng được học, có mã, tên, tín chỉ |
| Class (Lớp học) | Entity class | Lớp cụ thể của môn học, có phòng, khung giờ |
| Registration (Đăng ký) | Entity class | Liên kết sinh viên với lớp học trong học kỳ |
| TimeSlot (Khung giờ) | Entity class | Thời gian cụ thể (thứ, kíp) của mỗi lớp |
| User (Người dùng) | Entity class | Tài khoản đăng nhập của sinh viên và nhân viên |
| Thời khóa biểu | Không phải class | Là kết quả truy vấn, không phải entity độc lập |
| Phòng học | Thuộc tính | Thuộc tính của Class |
| Giảng viên | Thuộc tính | Thuộc tính của Class |
| Quê quán (hometown) | Thuộc tính | Thuộc tính của Student |
| Học kỳ | Thuộc tính | Thuộc tính của Registration |
| Tín chỉ | Thuộc tính | Thuộc tính của Subject |
| Môn tiên quyết | Bị loại (ngoài phạm vi module xem TKB) | Không cần cho chức năng xem thời khóa biểu |

### Bước 3: Xác định quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Student → Registration | 1..* | Một sinh viên có nhiều đăng ký |
| Class → Registration | 1..* | Một lớp có nhiều sinh viên đăng ký |
| Subject → Class | 1..* | Một môn học có nhiều lớp |
| TimeSlot → Class | 1..* | Một khung giờ có thể dùng cho nhiều lớp |
| Student → User | 1..1 | Mỗi sinh viên có một tài khoản User |

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
                              | +getSchedule()    |
                              +-------------------+
                                       | 1
                                       |
                                       | *
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
                              | -id: int          |
                              | -classCode: String|        +-------------------+
                              | -className: String|        |     TimeSlot      |
                              | -classroom: String|        +-------------------+
                              | -lecturer: String |   *  1 | -id: int          |
                              | -maxStudents: int |------->| -dayOfWeek: String|
                              | -day: String      |        | -startPeriod: int |
                              | -startPeriod: int |        | -endPeriod: int   |
                              | -endPeriod: int   |        | -startTime: String|
                              +-------------------+        | -endTime: String  |
                                                           +-------------------+
```

### Bước 5: Bảng quan hệ chi tiết

| Entity 1 | Entity 2 | Mối quan hệ | Đa thực thể | Khóa ngoại | Giải thích |
|-----------|----------|-------------|-------------|------------|------------|
| Student | User | kế thừa / liên kết 1-1 | 1:1 | studentID trong tblUser | Mỗi SV có 1 tài khoản User |
| Student | Registration | sở hữu | 1:N | studentID trong tblRegistration | 1 SV có nhiều đăng ký |
| Class | Registration | được đăng ký | 1:N | classID trong tblRegistration | 1 lớp có nhiều SV đăng ký |
| Subject | Class | chứa | 1:N | subjectID trong tblClass | 1 môn có nhiều lớp |
| TimeSlot | Class | gán cho | 1:N | timeSlotID trong tblClass | 1 khung giờ cho nhiều lớp |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_ViewSchedule" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 6 class: User, Student, Subject, Class, TimeSlot, Registration |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, ViewScheduleFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class TimeSlot) |
|------|----------|------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> TimeSlot` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-id: int`, `-dayOfWeek: String`, `-startPeriod: int`, `-endPeriod: int`, `-startTime: Time`, `-endTime: Time` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+getTimeSlotById(id: int): TimeSlot` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | `+login(username: String, password: String): User` |
| Student | `<<entity>>` | `-id: int`, `-studentCode: String`, `-name: String`, `-dob: Date`, `-course: String`, `-hometown: String`, `-address: String` | `+getSchedule(semester: String): List<Registration>` |
| Subject | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-credits: int` | `+getSubjectById(id: int): Subject` |
| Class | `<<entity>>` | `-id: int`, `-classCode: String`, `-className: String`, `-classroom: String`, `-lecturer: String`, `-maxStudents: int`, `-subjectID: int`, `-timeSlotID: int` | `+getClassById(id: int): Class` |
| TimeSlot | `<<entity>>` | `-id: int`, `-dayOfWeek: String`, `-startPeriod: int`, `-endPeriod: int`, `-startTime: Time`, `-endTime: Time` | `+getTimeSlotById(id: int): TimeSlot` |
| Registration | `<<entity>>` | `-id: int`, `-semester: String`, `-regDate: Date`, `-status: String`, `-studentID: int`, `-classID: int` | `+getRegistrationsByStudent(studentId: int, semester: String): List<Registration>` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuRegister: JMenuItem`, `-menuViewSchedule: JMenuItem`, `-menuStatistics: JMenuItem`, `-menuLogout: JMenuItem` |
| ViewScheduleFrm | `<<boundary>>` | `-inViewType: JComboBox`, `-outScheduleTable: JTable`, `-outDayHeaders: JLabel[]`, `-outPeriodLabels: JLabel[]`, `-outSlotDetail: JPanel`, `-outSubjectCode: JLabel`, `-outSubjectName: JLabel`, `-outClassCode: JLabel`, `-outClassName: JLabel`, `-outClassroom: JLabel`, `-outLecturer: JLabel`, `-outStartTime: JLabel`, `-outEndTime: JLabel`, `-subBack: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (ViewScheduleFrm → RegistrationDAO) |

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
| TimeSlot | Class | Association | 1 → * | Mỗi khung giờ cho nhiều lớp |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Student → Registration (1-n, Association)*

1. Click chuột phải vào class Student → chọn **Association** → kéo đến class Registration.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `registers`.

*Ví dụ 2: Vẽ quan hệ TimeSlot → Class (1-n, Association)*

1. Click chuột phải vào class TimeSlot → chọn **Association** → kéo đến class Class.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `assigned_to`.

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
  Menu Register → menuRegister
  Menu View Schedule → menuViewSchedule
  Menu Statistics → menuStatistics
  Menu Logout → menuLogout

SV chọn View schedule → Giao diện xem thời khóa biểu xuất hiện → cần class: ViewScheduleFrm
  Combobox chọn cách xem (Weekly/Semester) → inViewType
  Bảng thời khóa biểu 7x6 → outScheduleTable
  Tiêu đề 7 cột (Mon-Sun) → outDayHeaders
  Tiêu đề 6 dòng (Kip 1-6) → outPeriodLabels
  Panel chi tiết kíp học → outSlotDetail
  Hiển thị mã môn → outSubjectCode
  Hiển thị tên môn → outSubjectName
  Hiển thị mã lớp → outClassCode
  Hiển thị tên lớp → outClassName
  Hiển thị phòng học → outClassroom
  Hiển thị giảng viên → outLecturer
  Hiển thị giờ bắt đầu → outStartTime
  Hiển thị giờ kết thúc → outEndTime
  Nút Back → subBack

SV chọn "Theo tuần" → Hệ thống tải thời khóa biểu tuần → cần phương thức:
  Tên: getScheduleByWeek()
  Đầu vào: studentId (int), weekStart (Date), weekEnd (Date)
  Đầu ra: List<Registration>
  Gán cho entity class: Registration.

Hệ thống lấy chi tiết lớp cho mỗi đăng ký → cần phương thức:
  Tên: getClassDetail()
  Đầu vào: classId (int)
  Đầu ra: Class
  Gán cho entity class: Class.

Hệ thống lấy thông tin môn học → cần phương thức:
  Tên: getSubjectById()
  Đầu vào: subjectId (int)
  Đầu ra: Subject
  Gán cho entity class: Subject.

SV click vào ô có môn → Hệ thống hiển thị chi tiết kíp học → (sử dụng getClassDetail() và getSubjectById() ở trên).

### Tóm tắt
View classes: LoginFrm, HomeFrm, ViewScheduleFrm
Phương thức: login(), getScheduleByWeek(), getClassDetail(), getSubjectById()

---

## Câu 3: Thiết kế tĩnh

### View classes

**LoginFrm:**
- `inUsername`: TextField nhập tên đăng nhập (String)
- `inPassword`: PasswordField nhập mật khẩu (String)
- `subLogin`: Button "Login"

**HomeFrm:**
- `menuRegister`: MenuItem "Register"
- `menuViewSchedule`: MenuItem "View schedule"
- `menuStatistics`: MenuItem "Statistics"
- `menuLogout`: MenuItem "Logout"

**ViewScheduleFrm:**
- `inViewType`: ComboBox chọn cách xem (String) — giá trị: "Weekly", "Semester"
- `outScheduleTable`: Table hiển thị thời khóa biểu 7x6 (JTable)
- `outDayHeaders`: Label[] tiêu đề 7 cột: "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
- `outPeriodLabels`: Label[] tiêu đề 6 dòng: "Kip 1", "Kip 2", "Kip 3", "Kip 4", "Kip 5", "Kip 6"
- `outSlotDetail`: Panel chi tiết kíp học (ẩn mặc định, hiện khi click ô)
- `outSubjectCode`: Label hiển thị mã môn (String)
- `outSubjectName`: Label hiển thị tên môn (String)
- `outClassCode`: Label hiển thị mã lớp (String)
- `outClassName`: Label hiển thị tên lớp (String)
- `outClassroom`: Label hiển thị phòng học (String)
- `outLecturer`: Label hiển thị giảng viên (String)
- `outStartTime`: Label hiển thị giờ bắt đầu (String)
- `outEndTime`: Label hiển thị giờ kết thúc (String)
- `subBack`: Button "Back"

### DAO classes

| DAO | Phương thức | Kiểu trả về | Mô tả |
|-----|-------------|-------------|-------|
| UserDAO | `login(username: String, password: String): User` | `User` hoặc `null` | Xác thực đăng nhập, truy vấn tblUser |
| RegistrationDAO | `getScheduleByWeek(studentId: int, weekStart: Date, weekEnd: Date): List<Registration>` | `List<Registration>` | Lấy danh sách đăng ký trong tuần |
| RegistrationDAO | `getScheduleBySemester(studentId: int, semester: String): List<Registration>` | `List<Registration>` | Lấy danh sách đăng ký theo học kỳ |
| ClassDAO | `getClassDetail(classId: int): Class` | `Class` | Lấy chi tiết lớp (phòng, GV, giờ) |
| ClassDAO | `getClassesBySubject(subjectId: int): List<Class>` | `List<Class>` | Lấy danh sách lớp theo môn |
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

**Class:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| classCode | String | Mã lớp |
| className | String | Tên lớp |
| classroom | String | Phòng học |
| lecturer | String | Giảng viên |
| maxStudents | int | Sĩ số tối đa |
| subjectID | int | FK -> tblSubject.ID |
| timeSlotID | int | FK -> tblTimeSlot.ID |
| subject | Subject | Đối tượng môn học |
| timeSlot | TimeSlot | Đối tượng khung giờ |
| registrations | List<Registration> | Danh sách đăng ký |

**TimeSlot:**
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| id | int | Khóa chính |
| dayOfWeek | String | Thứ trong tuần |
| startPeriod | int | Kíp bắt đầu |
| endPeriod | int | Kíp kết thúc |
| startTime | String | Giờ bắt đầu |
| endTime | String | Giờ kết thúc |
| classes | List<Class> | Danh sách lớp học |

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

**tblTimeSlot:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| dayOfWeek | varchar(20) | NOT NULL |
| startPeriod | int | NOT NULL |
| endPeriod | int | NOT NULL |
| startTime | varchar(10) | |
| endTime | varchar(10) | |

**tblClass:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| classCode | varchar(20) | UNIQUE, NOT NULL |
| className | varchar(100) | NOT NULL |
| classroom | varchar(50) | |
| lecturer | varchar(100) | |
| maxStudents | int | |
| subjectID | int | FK -> tblSubject.ID, NOT NULL |
| timeSlotID | int | FK -> tblTimeSlot.ID, NOT NULL |

**tblRegistration:**
| Column | Type | Constraint |
|--------|------|------------|
| ID | int | PK, AUTO_INCREMENT |
| semester | varchar(20) | NOT NULL |
| regDate | date | |
| status | varchar(20) | DEFAULT 'active' |
| studentID | int | FK -> tblStudent.ID, NOT NULL |
| classID | int | FK -> tblClass.ID, NOT NULL |

### Hướng dẫn Visual Paradigm

1. Mở Visual Paradigm -> File -> New Project -> đặt tên "StudentViewSchedule".
2. Vào Diagram -> Class Diagram -> tạo class diagram mới.
3. Tạo 6 class: User, Student, Subject, Class, TimeSlot, Registration.
4. Với mỗi class, click chuột phải -> Add Attribute -> nhập thuộc tính theo bảng Entity classes ở trên.
5. Kéo connector "Association" từ Student đến Registration (1:N).
6. Kéo connector "Association" từ Class đến Registration (1:N).
7. Kéo connector "Association" từ Subject đến Class (1:N).
8. Kéo connector "Association" từ TimeSlot đến Class (1:N).
9. Kéo connector "Association" từ Student đến User (1:1).
10. Đặt tên và đa thực thể cho mỗi mối quan hệ.

---

## Câu 4: Sequence Diagram

### Hướng dẫn Visual Paradigm — Các bước vẽ

1. Mở Visual Paradigm -> Diagram -> Sequence Diagram.
2. Tạo các lifeline (đối tượng): `:Student`, `:LoginFrm`, `:UserDAO`, `:HomeFrm`, `:ViewScheduleFrm`, `:RegistrationDAO`, `:ClassDAO`, `:SubjectDAO`.
3. Kéo message từ `:Student` đến `:LoginFrm`: "login(username, password)".
4. Kéo message từ `:LoginFrm` đến `:UserDAO`: "login(username, password)".
5. Kéo return message từ `:UserDAO` đến `:LoginFrm`: "return User".
6. Kéo return message từ `:LoginFrm` đến `:Student`: "return User".
7. Kéo message từ `:Student` đến `:HomeFrm`: "selectViewSchedule()".
8. Kéo message từ `:HomeFrm` đến `:ViewScheduleFrm`: "open()".
9. Kéo message từ `:Student` đến `:ViewScheduleFrm`: "selectViewType('Weekly')".
10. Kéo message từ `:ViewScheduleFrm` đến `:RegistrationDAO`: "getScheduleByWeek(studentId, weekStart, weekEnd)".
11. Kéo return message: "return List<Registration>".
12. Với mỗi Registration, vẽ loop fragment gọi `:ClassDAO.getClassDetail(classId)` và `:SubjectDAO.getSubjectById(subjectId)`.
13. Kéo message từ `:Student` đến `:ViewScheduleFrm`: "clickCell(day='Mon', period=1)".
14. Kéo message từ `:ViewScheduleFrm` đến `:ClassDAO`: "getClassDetail(classId)".
15. Kéo return message: "return Class".
16. Kéo message self trên `:ViewScheduleFrm`: "displaySlotDetail(class)".

### Sequence Diagram (ASCII)

```
Student       LoginFrm       UserDAO       HomeFrm      ViewScheduleFrm    RegistrationDAO    ClassDAO     SubjectDAO
   |              |              |             |               |                  |                |              |
   |---login------>|              |             |               |                  |                |              |
   |              |---login()--->|             |               |                  |                |              |
   |              |              |---query DB  |               |                  |                |              |
   |              |              |<-return-----|               |                  |                |              |
   |              |<--return User|             |               |                  |                |              |
   |<--return User|              |             |               |                  |                |              |
   |              |              |             |               |                  |                |              |
   |---selectViewSchedule()-------------------->|               |                  |                |              |
   |              |              |             |---open()----->|                  |                |              |
   |              |              |             |               |                  |                |              |
   |---selectViewType('Weekly')-->|             |               |                  |                |              |
   |              |              |             |               |                  |                |              |
   |              |              |             |               |---getScheduleByWeek------------->|              |
   |              |              |             |               |<--return List<Reg>---------------|              |
   |              |              |             |               |                  |                |              |
   |              |              |             |               | [loop: each Registration]        |              |
   |              |              |             |               |---getClassDetail(classId)-------->|              |
   |              |              |             |               |<--return Class-------------------|              |
   |              |              |             |               |---getSubjectById(subjectId)------|------------->|
   |              |              |             |               |<--return Subject-----------------|--------------|
   |              |              |             |               |                  |                |              |
   |              |              |             |               | [display 7x6 table]              |              |
   |              |              |             |               |                  |                |              |
   |---clickCell('Mon',1)---------------------->|               |                  |                |              |
   |              |              |             |               |---getClassDetail(classId)-------->|              |
   |              |              |             |               |<--return Class-------------------|              |
   |              |              |             |               |                  |                |              |
   |              |              |             |               |---getSubjectById(subjectId)------|------------->|
   |              |              |             |               |<--return Subject-----------------|--------------|
   |              |              |             |               |                  |                |              |
   |              |              |             |               | [displaySlotDetail]              |              |
   |<---show detail panel----------------------|               |                  |                |              |
   |              |              |             |               |                  |                |              |
   |---clickBack->|              |             |               |                  |                |              |
   |              |              |             |               | [hide detail, show table]        |              |
```

### Bảng giải thích chi tiết

| Bước | Từ | Đến | Message | Giải thích |
|------|-----|------|---------|------------|
| 1 | Student | LoginFrm | `login("sv01", "******")` | Sinh viên nhập thông tin đăng nhập |
| 2 | LoginFrm | UserDAO | `login("sv01", "******")` | LoginFrm gọi UserDAO để xác thực |
| 3 | UserDAO | Database | `query tblUser` | Truy vấn kiểm tra username/password |
| 4 | UserDAO | LoginFrm | `return User` | Trả về đối tượng User nếu hợp lệ, null nếu sai |
| 5 | LoginFrm | Student | `return User` | Trả về User cho Student |
| 6 | Student | HomeFrm | `selectViewSchedule()` | SV chọn chức năng xem thời khóa biểu từ menu |
| 7 | HomeFrm | ViewScheduleFrm | `open()` | Mở giao diện xem thời khóa biểu |
| 8 | Student | ViewScheduleFrm | `selectViewType("Weekly")` | SV chọn kiểu xem "Theo tuần" |
| 9 | ViewScheduleFrm | RegistrationDAO | `getScheduleByWeek(studentId, weekStart, weekEnd)` | Truy vấn danh sách đăng ký trong tuần |
| 10 | RegistrationDAO | ViewScheduleFrm | `return List<Registration>` | Trả về danh sách đăng ký kèm Class, TimeSlot |
| 11 | ViewScheduleFrm | ClassDAO | `getClassDetail(classId)` | Lấy chi tiết lớp cho mỗi đăng ký |
| 12 | ClassDAO | ViewScheduleFrm | `return Class` | Trả về thông tin lớp (phòng, giờ, GV) |
| 13 | ViewScheduleFrm | SubjectDAO | `getSubjectById(subjectId)` | Lấy thông tin môn học |
| 14 | SubjectDAO | ViewScheduleFrm | `return Subject` | Trả về tên môn, mã môn |
| 15 | ViewScheduleFrm | ViewScheduleFrm | `displayScheduleTable(scheduleData)` | Hiển thị bảng 7x6 với dữ liệu |
| 16 | Student | ViewScheduleFrm | `clickCell(day="Mon", period=1)` | SV click vào ô Thứ 2 Kíp 1 |
| 17 | ViewScheduleFrm | ClassDAO | `getClassDetail(classId)` | Lấy chi tiết lớp cho ô được click |
| 18 | ClassDAO | ViewScheduleFrm | `return Class` | Trả về chi tiết lớp |
| 19 | ViewScheduleFrm | SubjectDAO | `getSubjectById(subjectId)` | Lấy thông tin môn học |
| 20 | SubjectDAO | ViewScheduleFrm | `return Subject` | Trả về thông tin môn |
| 21 | ViewScheduleFrm | ViewScheduleFrm | `displaySlotDetail(class, subject)` | Hiển thị panel chi tiết kíp học |
| 22 | ViewScheduleFrm | Student | `showDetailPanel()` | Hiển thị chi tiết cho SV xem |
| 23 | Student | ViewScheduleFrm | `clickBack()` | SV nhấn nút Back |
| 24 | ViewScheduleFrm | ViewScheduleFrm | `hideSlotDetail()` | Ẩn panel chi tiết |
| 25 | ViewScheduleFrm | Student | `showScheduleTable()` | Hiển thị lại bảng thời khóa biểu |

---

## Câu 5: Blackbox Testcase

### Test Plan

| TC | Tên test case | Mô tả | Kết quả mong đợi |
|----|---------------|-------|------------------|
| TC01 | Xem TKB tuần có dữ liệu | SV đăng ký 3 môn, xem TKB tuần | Bảng 7x6 hiển thị 3 môn tại đúng ô |
| TC02 | Xem TKB tuần khi chưa đăng ký | SV chưa đăng ký môn nào | Bảng trống, thông báo "Ban chua dang ky" |
| TC03 | Click ô có môn học | Nhấn vào ô có môn | Hiển thị chi tiết kíp học |
| TC04 | Click ô trống | Nhấn vào ô trống | Không có phản ứng |
| TC05 | Đăng nhập sai mật khẩu | Nhập sai password | Thông báo lỗi, không vào được Home |
| TC06 | Chọn kiểu xem Semester | Chọn "Theo học kỳ" | Hiển thị TKB cả học kỳ |

### TC01: Xem thời khóa biểu tuần có dữ liệu

**Database trước khi test:**

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | sv01 | pass123 | student |

**tblStudent:**
| ID | studentCode | name | dob | course | hometown | address | userID |
|----|-------------|------|-----|--------|----------|---------|--------|
| 1 | SV2021001 | Nguyen Van A | 2003-05-15 | K65 | Ha Noi | Ha Noi | 1 |

**tblSubject:**
| ID | code | name | credits |
|----|------|------|---------|
| 1 | INT1340 | Nhap mon CNPM | 3 |
| 2 | MAT1042 | Toan cao cap | 4 |
| 3 | ENG1024 | Tieng Anh | 3 |

**tblTimeSlot:**
| ID | dayOfWeek | startPeriod | endPeriod | startTime | endTime |
|----|-----------|-------------|-----------|-----------|---------|
| 1 | Mon | 1 | 2 | 7:30 | 9:30 |
| 2 | Wed | 3 | 4 | 13:00 | 15:00 |
| 3 | Fri | 2 | 3 | 9:30 | 11:30 |

**tblClass:**
| ID | classCode | className | classroom | lecturer | maxStudents | subjectID | timeSlotID |
|----|-----------|-----------|-----------|----------|-------------|-----------|------------|
| 1 | L01 | Lop 01 | A101 | Nguyen Van A | 40 | 1 | 1 |
| 2 | L03 | Lop 03 | B202 | Tran Van B | 35 | 2 | 2 |
| 3 | L05 | Lop 05 | C301 | Le Thi C | 30 | 3 | 3 |

**tblRegistration:**
| ID | semester | regDate | status | studentID | classID |
|----|----------|---------|--------|-----------|---------|
| 1 | 20251 | 2025-08-20 | active | 1 | 1 |
| 2 | 20251 | 2025-08-20 | active | 1 | 2 |
| 3 | 20251 | 2025-08-20 | active | 1 | 3 |

### Kịch bản test TC01

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Mở ứng dụng. Giao diện Login xuất hiện. | Hiển thị ô username, password, nút Login |
| 2 | Nhập username `sv01`, password `pass123`, nhấn Login. | Chuyển sang giao diện Home với menu: Register, View schedule, Statistics |
| 3 | Chọn menu **View schedule**. | Giao diện ViewScheduleFrm xuất hiện với combobox chọn cách xem |
| 4 | Chọn "Theo tuần" (Weekly) từ combobox. | Bảng 7x6 xuất hiện |
| 5 | Kiểm tra ô Thứ 2 (Mon), Kíp 1: hiển thị "Nhap mon CNPM / A101 / L01". | Đúng dữ liệu |
| 6 | Kiểm tra ô Thứ 4 (Wed), Kíp 3: hiển thị "Toan cao cap / B202 / L03". | Đúng dữ liệu |
| 7 | Kiểm tra ô Thứ 6 (Fri), Kíp 2: hiển thị "Tieng Anh / C301 / L05". | Đúng dữ liệu |
| 8 | Kiểm tra các ô còn lại: trống (không có dữ liệu). | Ô trống |
| 9 | Nhấn vào ô Thứ 2 (Mon), Kíp 1. | Panel chi tiết xuất hiện: Mã môn INT1340, Tên môn Nhap mon CNPM, Mã lớp L01, Tên lớp Lop 01, Phòng A101, Giảng viên Nguyen Van A, Giờ bắt đầu 7:30, Giờ kết thúc 9:30, Thứ 2 |
| 10 | Nhấn nút Back. | Panel chi tiết ẩn, quay về bảng TKB |

### Database sau khi test

Không thay đổi. Đây là chức năng chỉ đọc (read-only), không có INSERT/UPDATE/DELETE.
