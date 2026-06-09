# Subject No. 06 — Student Results — Module "Enter grades by class"

> **Domain:** Student Results Management
> **Duration:** 90 minutes

---

## Cau 1: Scenario — Nhap diem theo lop (1.5 diem)

### Kich ban chinh — Enter grades by class

| Buoc | Nguoi dung | He thong | Ghi chu |
|------|-----------|----------|---------|
| 1 | Teacher Tran Thi Mai (GV001) mo ung dung | Man hinh Login xuat hien voi o Username, o Password, nut Login | Giao dien LoginFrm |
| 2 | Nhap username: `teacher01`, password: `@Teach2026`, nhan Login | He thong xac thuc thanh cong. Hien thi HomeFrm voi cac menu: Student, Subject, Class, Schedule, Grade, Statistics | Dang nhap thanh cong |
| 3 | Nhan menu "Grade" → chon "Enter grades by class" | Hien thi EnterGradeFrm: combobox Subject (rong), combobox Class (rong), bang danh sach SV trong (cot: Ma SV, Ten SV, Diem TP1, Diem TP2, Diem TP3, Diem thi), nut Confirm, nut Cancel | Giao dien nhap diem |
| 4 | Nhan vao combobox Subject, chon "CS101 — Lap trinh Java" | He thong tai danh sach mon hoc do teacher GV001 giang day. Combobox Subject hien thi: CS101 — Lap trinh Java, CS201 — Cau truc du lieu. Teacher chon CS101 | Danh sach mon hoc cua teacher |
| 5 | He thong tai danh sach Class cua mon CS101 do GV001 day | Combobox Class cap nhat: CL001 (Section 1, Thu 2, A101), CL002 (Section 2, Thu 4, A201). Bang chua hien thi SV | Combobox Class thay doi |
| 6 | Teacher chon "CL001 — Section 1, Thu 2, A101" tu combobox Class | He thong tai danh sach SV trong lop CL001. Bang cap nhat: \|SV2026001\|Nguyen Van A\| \| \| \| \|, \|SV2026002\|Tran Thi B\| \| \| \| \|, \|SV2026003\|Le Van C\| \| \| \| \|. Cot diem trong | Bang SV xuat hien |
| 7 | Teacher nhap diem tung SV: SV2026001 — TP1: 8.0, TP2: 7.5, TP3: 9.0, Diem thi: 8.0 | He thong hien thi diem da nhap trong cac o tuong ung. Bang cap nhat: \|SV2026001\|Nguyen Van A\|8.0\|7.5\|9.0\|8.0\| | Nhap diem SV thu nhat |
| 8 | Tiep tuc nhap: SV2026002 — TP1: 6.0, TP2: 7.0, TP3: 8.0, Diem thi: 7.5 | Bang cap nhat: \|SV2026002\|Tran Thi B\|6.0\|7.0\|8.0\|7.5\| | Nhap diem SV thu hai |
| 9 | Tiep tuc nhap: SV2026003 — TP1: 9.0, TP2: 8.5, TP3: 9.5, Diem thi: 9.0 | Bang cap nhat: \|SV2026003\|Le Van C\|9.0\|8.5\|9.5\|9.0\| | Nhap diem SV thu ba |
| 10 | Teacher nhan nut "Confirm" | He thong tinh FinalScore = 10%×TP1 + 10%×TP2 + 20%×TP3 + 60%×Diem thi cho tung SV. Hien thi cot FinalScore: SV01=8.15, SV02=7.40, SV03=9.05. Luu vao database | Tinh diem va luu |
| 11 | He thong hien thi thong bao: "Grades saved successfully for class CL001. 3 students graded." | Nhan OK, quay lai EnterGradeFrm | Hoan tat |

### Kich ban ngoai le

**Truong hop 1: Diem ngoai pham vi hop le**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 7b | Nhap diem TP1 cho SV2026001: 11.0 (vuot qua 10) | He thong hien thi loi: "Invalid score! Score must be between 0 and 10. Please re-enter." o dong SV2026001, cot TP1 |
| 8b | Nhap lai TP1: 8.0 | He thong chap nhan diem, khong con loi |

**Truong hop 2: Chua nhap du diem cho tat ca SV**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 10b | Nhan Confirm khi SV2026003 chua nhap diem thi (o trong) | He thong hien thi loi: "Incomplete grades! Student SV2026003 (Le Van C) is missing exam score. Please fill all fields before confirming." |
| 11b | Nhap diem thi cho SV2026003: 9.0, nhan lai Confirm | He thong tinh diem va luu thanh cong |

**Truong hop 3: Nhap chu vao o diem**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 7c | Nhap TP1 cho SV2026001: "abc" | He thong hien thi loi: "Invalid input! Please enter a numeric value between 0 and 10." o dong SV2026001, cot TP1 |
| 8c | Nhap lai TP1: 8.0 | He thong chap nhan diem |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly ket qua sinh vien (Student Results Management) cho phep giao vien (Teacher) nhap diem cho sinh vien trong tung lop hoc. Moi lop hoc (Class) thuoc ve mot mon hoc (Subject) va duoc giang day boi mot giao vien. Sinh vien (Student) dang ky lop hoc qua bang Registration. Khi nhap diem, giao vien nhap cac diem thanh phan (component1, component2, component3) va diem thi (examScore) cho tung sinh vien. He thong tu dong tinh diem tong ket (finalScore) theo cong thuc: finalScore = x%×component1 + y%×component2 + z%×component3 + w%×examScore. Moi sinh vien trong mot lop co mot ban ghi diem duy nhat, lien ket qua bang Registration.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Student | Entity | Doi tuong chinh, co ma SV, password, ten, ngay sinh, khoa hoc, que quan, dia chi |
| Subject | Entity | Mon hoc, co ma mon, ten mon, so tin chi |
| Class | Entity | Lop hoc cu the, co ma lop, ten lop, so SV toi da, phong hoc, gio co dinh trong tuan |
| Registration | Entity | Bang dang ky, lien ket sinh vien voi lop hoc |
| Grade | Entity | Bang diem, luu diem thanh phan, diem thi, diem tong ket |
| User | Entity | Tai khoan nguoi dung (teacher, student) |
| Teacher | Actor | Giao vien, nhap diem cho sinh vien trong lop |
| Component1 | Attribute | Thuoc tinh cua Grade, diem thanh phan 1 |
| Component2 | Attribute | Thuoc tinh cua Grade, diem thanh phan 2 |
| Component3 | Attribute | Thuoc tinh cua Grade, diem thanh phan 3 |
| ExamScore | Attribute | Thuoc tinh cua Grade, diem thi |
| FinalScore | Attribute | Thuoc tinh cua Grade, diem tong ket (tinh toan) |
| Semester | Attribute | Thuoc tinh cua Registration |
| Password | Attribute | Thuoc tinh cua Student |
| Hometown | Attribute | Thuoc tinh cua Student (que quan) |
| Address | Attribute | Thuoc tinh cua Student (dia chi) |
| Classroom | Attribute | Thuoc tinh cua Class (phong hoc) |
| TimeSlot | Attribute | Thuoc tinh cua Class (gio co dinh trong tuan) |
| Prerequisite | Entity | Quan he n-n giua Subject va Subject (mon tien quyet) |

### Buoc 3: Xac dinh quan he

1. **Subject — Class**: Mot mon hoc co nhieu lop hoc (1-n). Moi Class thuoc ve mot Subject duy nhat.
2. **Class — Registration**: Mot lop hoc co nhieu dang ky (1-n). Moi Registration lien ket mot Student voi mot Class.
3. **Student — Registration**: Mot sinh vien co nhieu dang ky (1-n). Moi Registration thuoc ve mot Student duy nhat.
4. **Registration — Grade**: Mot dang ky co mot bang diem (1-1). Moi Grade lien ket voi mot Registration duy nhat.
5. **User — Student**: Mot User lien ket voi mot Student (1-1). Student ke thua hoac tham chieu den User.
6. **User — Teacher (Class.teacherId)**: Mot User (teacher) co the day nhieu lop (1-n). Moi Class co mot teacherId tham chieu den User.
7. **Subject — Prerequisite — Subject**: Quan he n-n giua mon hoc va mon tien quyet. Mot mon co nhieu mon tien quyet, va mot mon co the la tien quyet cua nhieu mon. Duoc tach bang bang trung gian tblPrerequisite.
8. **Class — Classroom**: Moi lop hoc thuoc ve mot phong hoc (n-1). Moi phong hoc co the dung cho nhieu lop.

### Buoc 4: Class Diagram (ASCII art)

```
+------------------+        +------------------+
|    <<entity>>    |        |    <<entity>>    |
|     Subject      |1      *|      Class       |
+------------------+--------+------------------+
| - subjectId: int |        | - classId: int   |
| - subjectCode:   |        | - subjectId: int |
|   String         |        | - teacherId: int |
| - subjectName:   |        | - section: String|
|   String         |        | - maxStudents:   |
| - credits: int   |        |   int            |
+------------------+        | - classroom:     |
                            |   String         |
                            | - timeSlot:      |
                            |   String         |
                            +------------------+
                                     |*
                                     |1
                              +------------------+
                              |    <<entity>>    |
                              |   Registration   |
                              +------------------+
                              | - regId: int     |
                              | - studentId: int |
                              | - classId: int   |
                              | - semester:      |
                              |   String         |
                              | - regDate: Date  |
                              | - status: String |
                              +------------------+
                                     |1
                                     |1
                                     v
                              +------------------+
                              |    <<entity>>    |
                              |      Grade       |
                              +------------------+
                              | - gradeId: int   |
                              | - regId: int     |
                              | - component1:    |
                              |   double         |
                              | - component2:    |
                              |   double         |
                              | - component3:    |
                              |   double         |
                              | - examScore:     |
                              |   double         |
                              | - finalScore:    |
                              |   double         |
                              +------------------+

+------------------+        +------------------+
|    <<entity>>    |        |    <<entity>>    |
|     Student      |1      *|   Registration   |
+------------------+--------+------------------+
| - studentId: int |        |                  |
| - studentCode:   |        +------------------+
|   String         |
| - studentName:   |        +------------------+
|   String         |        |    <<entity>>    |
| - password:      |        |      User        |
|   String         |        +------------------+
| - email: String  |        | - userId: int    |
| - dob: Date      |        | - username:      |
| - major: String  |        |   String         |
| - hometown:      |        | - password:      |
|   String         |        |   String         |
| - address:       |        | - role: String   |
|   String         |        +------------------+
| - userId: int    |                |1
+------------------+                |
        |1                          v
        | 1                 +------------------+
        v                   |  Student / Staff |
+------------------+        |  (role-based)    |
|    <<entity>>    |        +------------------+
|      User        |
+------------------+
| - userId: int    |
| - username:      |
|   String         |
| - password:      |
|   String         |
| - role: String   |
+------------------+
```

### Buoc 5: Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|---------|------|------------|
| Subject → Class | 1-n | Mot mon hoc co nhieu lop. VD: CS101 co CL001, CL002, CL003 |
| Class → Registration | 1-n | Mot lop co nhieu SV dang ky. VD: CL001 co 35 SV dang ky |
| Student → Registration | 1-n | Mot SV dang ky nhieu lop. VD: SV2026001 dang ky 5 lop |
| Registration → Grade | 1-1 | Moi dang ky co mot bang diem duy nhat. VD: Reg001 co Grade001 |
| User → Student | 1-1 | Mot tai khoan User ung voi mot Student |
| User → Class (teacherId) | 1-n | Mot giao vien day nhieu lop. VD: GV001 day CL001, CL002 |
| Subject → Prerequisite → Subject | n-n | Mon tien quyet. VD: CS201 yeu cau hoan thanh CS101 truoc. Duoc tach bang bang trung gian tblPrerequisite(subjectId, prerequisiteId) |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_EnterGrades" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 6 class: Student, Subject, Class, Registration, Grade, User |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, EnterGradeFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Grade) |
|------|----------|----------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Grade` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-gradeId: int`, `-regId: int`, `-component1: float`, `-component2: float`, `-component3: float`, `-examScore: float`, `-finalScore: float` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+calculateFinalScore(): float`, `+saveGrades(): boolean` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| Student | `<<entity>>` | `-studentId: int`, `-studentCode: String`, `-studentName: String`, `-password: String`, `-email: String`, `-dob: Date`, `-major: String`, `-hometown: String`, `-address: String`, `-userId: int` | `+getStudentsByClass(classId: int): List<Student>` |
| Subject | `<<entity>>` | `-subjectId: int`, `-subjectCode: String`, `-subjectName: String`, `-credits: int` | `+getSubjectsByTeacher(teacherId: int): List<Subject>` |
| Class | `<<entity>>` | `-classId: int`, `-subjectId: int`, `-teacherId: int`, `-classroom: String`, `-timeSlot: String`, `-section: String`, `-maxStudents: int` | `+getClassesBySubjectTeacher(subjectId: int, teacherId: int): List<Class>` |
| Registration | `<<entity>>` | `-regId: int`, `-studentId: int`, `-classId: int`, `-semester: String`, `-regDate: Date`, `-status: String` | `+getRegistrationsByClass(classId: int): List<Registration>` |
| Grade | `<<entity>>` | `-gradeId: int`, `-regId: int`, `-component1: float`, `-component2: float`, `-component3: float`, `-examScore: float`, `-finalScore: float` | `+calculateFinalScore(): float`, `+saveGrades(): boolean` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | `+validateLogin(username: String, password: String): boolean` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuGrade: JMenuItem`, `-menuStudent: JMenuItem`, `-menuSubject: JMenuItem`, `-menuSchedule: JMenuItem`, `-subLogout: JButton` |
| EnterGradeFrm | `<<boundary>>` | `-inSubject: JComboBox`, `-inClass: JComboBox`, `-tblGrade: JTable`, `-subConfirm: JButton`, `-subCancel: JButton`, `-subRefresh: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (EnterGradeFrm → GradeDAO) |

**6. Cách ghi multiplicity:**

| Multiplicity | Cách ghi trong VP | Ví dụ |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" ở một đầu | Registration có đúng 1 Grade |
| 0..* hoặc 1..* | Ghi "*" hoặc "1..*" ở đầu kia | Student có nhiều Registration |
| 0..1 | Ghi "0..1" | (hiếm dùng) |

Ghi multiplicity ở cả 2 đầu của đường kết nối. Click vào đường → Properties → Source Multiplicity / Target Multiplicity.

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| Subject | Class | Association | 1 → * | Mỗi môn học có nhiều lớp |
| Class | Registration | Association | 1 → * | Mỗi lớp có nhiều sinh viên đăng ký |
| Student | Registration | Association | 1 → * | Mỗi sinh viên đăng ký nhiều lớp |
| Registration | Grade | Association | 1 → 1 | Mỗi đăng ký có một bảng điểm |
| User | Student | Association | 1 → 1 | Mỗi tài khoản User ứng với một Student |
| User | Class | Association | 1 → * | Mỗi giáo viên dạy nhiều lớp (qua teacherId) |
| Subject | Prerequisite | Association | 1 → * | Mỗi môn có nhiều môn tiên quyết |
| Prerequisite | Subject | Association | * → 1 | Mỗi prerequisite tham chiếu đến 1 môn tiên quyết |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Registration → Grade (1-1, Association)*

1. Click chuột phải vào class Registration → chọn **Association** → kéo đến class Grade.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `1`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `has_grade`.

*Ví dụ 2: Vẽ quan hệ User → Class (1-n, Association qua teacherId)*

1. Click chuột phải vào class User → chọn **Association** → kéo đến class Class.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `teaches`. Ghi chú: `teacherId` trong Class tham chiếu đến User.

### Classes diagram (analysis)

Phân tích module này:

Đăng nhập vào hệ thống → Giao diện Login xuất hiện → cần class: LoginFrm
  Ô nhập username → inUsername
  Ô nhập password → inPassword
  Nút Login → subLogin

Nhập username/password → Hệ thống phải kiểm tra đăng nhập → cần phương thức:
  Tên: validateLogin()
  Đầu vào: username, password (thuộc class User)
  Đầu ra: boolean
  Gán cho entity class: User.

Đăng nhập thành công → Giao diện Home xuất hiện → cần class: HomeFrm
  Menu Grade → menuGrade
  Menu Student → menuStudent
  Menu Subject → menuSubject
  Menu Schedule → menuSchedule
  Nút Logout → subLogout

Teacher chọn Enter grades by class → Giao diện nhập điểm xuất hiện → cần class: EnterGradeFrm
  Combobox chọn môn học → inSubject
  Combobox chọn lớp học → inClass
  Bảng nhập điểm sinh viên → tblGrade
  Nút Confirm → subConfirm
  Nút Cancel → subCancel
  Nút Refresh → subRefresh

Teacher chọn môn → Hệ thống tải danh sách môn của teacher → cần phương thức:
  Tên: getSubjectsByTeacher()
  Đầu vào: teacherId (int)
  Đầu ra: List<Subject>
  Gán cho entity class: Subject.

Teacher chọn lớp → Hệ thống tải danh sách sinh viên trong lớp → cần phương thức:
  Tên: getStudentsByClass()
  Đầu vào: classId (int)
  Đầu ra: List<Student>
  Gán cho entity class: Student.

Teacher nhập điểm và nhấn Confirm → Hệ thống tính điểm tổng kết → cần phương thức:
  Tên: calculateFinalScore()
  Đầu vào: component1 (double), component2 (double), component3 (double), examScore (double)
  Đầu ra: double
  Gán cho entity class: Grade.

Hệ thống lưu điểm vào database → cần phương thức:
  Tên: saveGrades()
  Đầu vào: classId (int), grades (List<Grade>)
  Đầu ra: boolean
  Gán cho entity class: Grade.

### Tóm tắt
View classes: LoginFrm, HomeFrm, EnterGradeFrm
Phương thức: validateLogin(), getSubjectsByTeacher(), getStudentsByClass(), calculateFinalScore(), saveGrades()

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|-----------|--------|
| LoginFrm | Form dang nhap, cho phep teacher nhap username/password de truy cap he thong |
| HomeFrm | Form trang chu, hien thi menu dieu huong cho teacher |
| EnterGradeFrm | Form nhap diem theo lop, cho phep teacher chon mon hoc, lop hoc, nhap diem tung SV |

### Buoc 2: UI Elements

**LoginFrm**
- `inUsername`: JTextField — O nhap ten dang nhap
- `inPassword`: JPasswordField — O nhap mat khau
- `subLogin`: JButton — Nut dang nhap
- `subCancel`: JButton — Nut huy

**HomeFrm**
- `menuGrade`: JMenuItem — Menu "Grade"
- `menuStudent`: JMenuItem — Menu "Student"
- `menuSubject`: JMenuItem — Menu "Subject"
- `menuSchedule`: JMenuItem — Menu "Schedule"
- `subLogout`: JButton — Nut dang xuat

**EnterGradeFrm**
- `inSubject`: JComboBox — Combobox chon mon hoc (chi hien thi mon teacher day)
- `inClass`: JComboBox — Combobox chon lop hoc (cap nhat theo mon da chon)
- `tblGrade`: JTable — Bang nhap diem SV, cac cot: Ma SV, Ten SV, TP1, TP2, TP3, Diem thi, Diem TK
- `subConfirm`: JButton — Nut xac nhan luu diem
- `subCancel`: JButton — Nut huy
- `subRefresh`: JButton — Nut lam moi danh sach

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| SubjectDAO | `List<Subject> getByTeacher(int teacherId)` | Lay danh sach mon hoc do teacher giang day |
| SubjectDAO | `Subject getById(int subjectId)` | Lay mon hoc theo ma |
| ClassDAO | `List<Class> getBySubjectAndTeacher(int subjectId, int teacherId)` | Lay lop theo mon va teacher |
| ClassDAO | `Class getById(int classId)` | Lay lop hoc theo ma |
| StudentDAO | `List<Student> getByClass(int classId)` | Lay danh sach SV trong lop (qua Registration) |
| StudentDAO | `Student getById(int studentId)` | Lay SV theo ma |
| RegistrationDAO | `Registration getByStudentAndClass(int studentId, int classId)` | Lay dang ky theo SV va lop |
| RegistrationDAO | `List<Registration> getByClass(int classId)` | Lay tat ca dang ky cua lop |
| GradeDAO | `Grade getByRegistration(int regId)` | Lay diem theo dang ky |
| GradeDAO | `boolean insert(Grade grade)` | Them diem moi |
| GradeDAO | `boolean update(Grade grade)` | Cap nhat diem |
| GradeDAO | `boolean batchInsert(List<Grade> grades)` | Them nhieu diem cung luc |
| UserDAO | `User getByUsername(String username)` | Lay nguoi dung theo ten dang nhap |
| UserDAO | `boolean validate(String username, String password)` | Xac thuc dang nhap |

### Buoc 4: Entity Class Design

```java
// Student.java
public class Student {
    private int studentId;
    private String studentCode;    // "SV2026001"
    private String studentName;    // "Nguyen Van A"
    private String password;       // "@Stu2026A"
    private String email;          // "nva@gmail.com"
    private Date dob;              // 2004-03-15
    private String major;          // "Computer Science"
    private String hometown;       // "Ha Noi"
    private String address;        // "123 Tran Hung Dao"
    private int userId;            // FK -> tblUser
    private User user;              // object attribute
    private List<Registration> registrations; // object attribute
    // getters, setters
}

// Subject.java
public class Subject {
    private int subjectId;
    private String subjectCode;    // "CS101"
    private String subjectName;    // "Lap trinh Java"
    private int credits;           // 3
    private List<Subject> prerequisites; // danh sach mon tien quyet (n-n qua tblPrerequisite)
    private List<Class> classes;   // object attribute
    // getters, setters
}

// Class.java
public class Class {
    private int classId;
    private int subjectId;         // FK -> tblSubject
    private int teacherId;         // FK -> tblUser (teacher)
    private String classroom;      // "A101"
    private String timeSlot;       // "Thu 2, 7:30-9:30"
    private int maxStudents;       // 40
    private String section;        // "Section 1"
    private Subject subject;       // object attribute
    private List<Registration> registrations; // object attribute
    // getters, setters
}

// Registration.java
public class Registration {
    private int regId;
    private int studentId;         // FK -> tblStudent
    private int classId;           // FK -> tblClass
    private String semester;       // "2026-1"
    private Date regDate;          // 2026-06-01
    private String status;         // "registered", "dropped", "completed"
    private Student student;       // object attribute
    private Class classObj;        // object attribute
    private Grade grade;           // object attribute
    // getters, setters
}

// Grade.java
public class Grade {
    private int gradeId;
    private int regId;             // FK -> tblRegistration
    private double component1;     // 8.5
    private double component2;     // 7.0
    private double component3;     // 9.0
    private double examScore;      // 8.0
    private double finalScore;     // tinh toan: 0.1*comp1 + 0.1*comp2 + 0.2*comp3 + 0.6*exam
    private Registration registration; // object attribute
    // getters, setters
    public double calculateFinalScore(double x, double y, double z, double w) {
        return x * component1 + y * component2 + z * component3 + w * examScore;
    }
}

// User.java
public class User {
    private int userId;
    private String username;       // "teacher01"
    private String password;       // "@Teach2026"
    private String role;           // "teacher"
    // getters, setters
}
```

### Buoc 5: Database Design

**tblUser**
| Column | Type | Constraint |
|--------|------|-----------|
| UserID | INT | PRIMARY KEY, AUTO_INCREMENT |
| Username | VARCHAR(50) | NOT NULL, UNIQUE |
| Password | VARCHAR(100) | NOT NULL |
| Role | VARCHAR(20) | NOT NULL, CHECK (Role IN ('staff','teacher','student')) |

**tblStudent**
| Column | Type | Constraint |
|--------|------|-----------|
| StudentID | INT | PRIMARY KEY, AUTO_INCREMENT |
| StudentCode | VARCHAR(20) | NOT NULL, UNIQUE |
| StudentName | VARCHAR(100) | NOT NULL |
| Password | VARCHAR(100) | NOT NULL |
| Email | VARCHAR(100) | UNIQUE |
| DOB | DATE | |
| Major | VARCHAR(50) | |
| Hometown | VARCHAR(100) | |
| Address | VARCHAR(200) | |
| UserID | INT | FOREIGN KEY → tblUser(UserID) |

**tblSubject**
| Column | Type | Constraint |
|--------|------|-----------|
| SubjectID | INT | PRIMARY KEY, AUTO_INCREMENT |
| SubjectCode | VARCHAR(20) | NOT NULL, UNIQUE |
| SubjectName | VARCHAR(100) | NOT NULL |
| Credits | INT | NOT NULL, CHECK (Credits > 0) |

**tblPrerequisite** (bang trung gian quan he n-n giua Subject va Subject)
| Column | Type | Constraint |
|--------|------|-----------|
| SubjectID | INT | FOREIGN KEY → tblSubject(SubjectID), NOT NULL |
| PrerequisiteID | INT | FOREIGN KEY → tblSubject(SubjectID), NOT NULL |
| PRIMARY KEY | (SubjectID, PrerequisiteID) | |

**tblClass**
| Column | Type | Constraint |
|--------|------|-----------|
| ClassID | INT | PRIMARY KEY, AUTO_INCREMENT |
| SubjectID | INT | FOREIGN KEY → tblSubject(SubjectID), NOT NULL |
| TeacherID | INT | FOREIGN KEY → tblUser(UserID), NOT NULL |
| Classroom | VARCHAR(50) | NOT NULL |
| TimeSlot | VARCHAR(100) | NOT NULL |
| MaxStudents | INT | NOT NULL, CHECK (MaxStudents > 0) |
| Section | VARCHAR(20) | NOT NULL |

**tblRegistration**
| Column | Type | Constraint |
|--------|------|-----------|
| RegID | INT | PRIMARY KEY, AUTO_INCREMENT |
| StudentID | INT | FOREIGN KEY → tblStudent(StudentID), NOT NULL |
| ClassID | INT | FOREIGN KEY → tblClass(ClassID), NOT NULL |
| Semester | VARCHAR(20) | NOT NULL |
| RegDate | DATE | NOT NULL |
| Status | VARCHAR(20) | DEFAULT 'registered' |

**tblGrade**
| Column | Type | Constraint |
|--------|------|-----------|
| GradeID | INT | PRIMARY KEY, AUTO_INCREMENT |
| RegID | INT | FOREIGN KEY → tblRegistration(RegID), NOT NULL, UNIQUE |
| Component1 | DOUBLE | DEFAULT 0, CHECK (>= 0 AND <= 10) |
| Component2 | DOUBLE | DEFAULT 0, CHECK (>= 0 AND <= 10) |
| Component3 | DOUBLE | DEFAULT 0, CHECK (>= 0 AND <= 10) |
| ExamScore | DOUBLE | DEFAULT 0, CHECK (>= 0 AND <= 10) |
| FinalScore | DOUBLE | DEFAULT 0 |

### Buoc 6: Huong dan ve Class Diagram bang Visual Paradigm

1. Mo Visual Paradigm → File → New Project → dat ten "StudentResults_GradeEntry"
2. Chon Diagram → Class Diagram
3. Tao 6 class: Student, Subject, Class, Registration, Grade, User
4. Voi moi class:
   - Nhap dup vao class → nhap ten class
   - Them attributes: ten: kieu (VD: studentId: int)
   - Chon visibility: private (-) cho tat ca attributes
5. Ve quan he:
   - Subject → Class: Association (1-n)
   - Class → Registration: Association (1-n)
   - Student → Registration: Association (1-n)
   - Registration → Grade: Association (1-1)
   - User → Student: Association (1-1)
   - User → Class (teacherId): Association (1-n)
6. Dat multiplicity: nhap vao Association → chon multiplicity o moi dau
7. Them `<<entity>>` stereotype cho moi class
8. Export: File → Export → PNG/PDF

---

## Cau 4: Sequence Diagram (1.5 diem)

### Huong dan ve bang Visual Paradigm

1. Mo Visual Paradigm → File → New → Sequence Diagram
2. Dat ten: "EnterGrades_SequenceDiagram"
3. Tao lifelines:
   - Actor: Teacher
   - Boundary: LoginFrm, HomeFrm, EnterGradeFrm
   - Control: UserDAO, SubjectDAO, ClassDAO, RegistrationDAO, GradeDAO
   - Entity: User, Subject, Class, Student, Registration, Grade
4. Ve message flow tu tren xuong theo cac buoc trong bang
5. Them alt fragment cho dieu kien (diem khong hop le, thieu diem)
6. Them loop fragment cho viec nhap diem tung SV
7. Export: File → Export → PNG/PDF

### ASCII Sequence Diagram

```
Teacher   LoginFrm   UserDAO   HomeFrm   EnterGradeFrm   SubjectDAO   ClassDAO   RegistrationDAO   GradeDAO   Grade
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | login()  |         |          |               |              |       |        |          |          |
  |          |-------->|          |               |              |       |        |          |          |
  |          |validate()         |               |              |       |        |          |          |
  |          |         |<---------|               |              |       |        |          |          |
  |          |         |showHome()|               |              |       |        |          |          |
  |<---------|         |          |               |              |       |        |          |          |
  | showHome |         |          |               |              |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | clickEnterGrade()  |          |               |              |       |        |          |          |
  |          |         |--------->|               |              |       |        |          |          |
  |          |         |openGrade()              |              |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |          |         |          |-------------->|              |       |        |          |          |
  |          |         |          |getSubjectsByTeacher(teacherId)       |        |          |          |
  |          |         |          |               |------------->|       |        |          |          |
  |          |         |          |               |querySubjects()       |        |          |          |
  |          |         |          |               |<-------------|       |        |          |          |
  |          |         |          |<--------------|              |       |        |          |          |
  |          |         |          |updateSubjectCbo()            |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | selectSubject("CS101")        |               |              |       |        |          |          |
  |          |         |          |-------------->|              |       |        |          |          |
  |          |         |          |getClassesBySubjectTeacher(subjectId, teacherId) |          |          |
  |          |         |          |               |------------->|       |        |          |          |
  |          |         |          |               |queryClasses()|------>|        |          |          |
  |          |         |          |               |<-------------|       |        |          |          |
  |          |         |          |<--------------|              |       |        |          |          |
  |          |         |          |updateClassCbo()              |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | selectClass("CL001")          |               |              |       |        |          |          |
  |          |         |          |-------------->|              |       |        |          |          |
  |          |         |          |getStudentsByClass(classId)  |       |        |          |          |
  |          |         |          |               |------------->|       |        |          |          |
  |          |         |          |               |queryStudents()------>|        |          |          |
  |          |         |          |               |<-------------|       |        |          |          |
  |          |         |          |<--------------|              |       |        |          |          |
  |          |         |          |displayStudentTable()         |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | enterScores()      |          |               |              |       |        |          |          |
  | (SV01: 8,7.5,9,8)  |          |               |              |       |        |          |          |
  | (SV02: 6,7,8,7.5)  |          |               |              |       |        |          |          |
  | (SV03: 9,8.5,9.5,9)|          |               |              |       |        |          |          |
  |          |         |          |               |              |       |        |          |          |
  |--------->|         |          |               |              |       |        |          |          |
  | clickConfirm()     |          |               |              |       |        |          |          |
  |          |         |          |-------------->|              |       |        |          |          |
  |          |         |          |saveGrades(classId, grades)  |       |        |          |          |
  |          |         |          |               |------------->|       |        |          |          |
  |          |         |          |               |calculateFinalScore()   |        |          |          |
  |          |         |          |               |insertGrade()|       |        |          |          |
  |          |         |          |               |<-------------|       |        |          |          |
  |          |         |          |               |success      |       |        |          |          |
  |          |         |          |<--------------|              |       |        |          |          |
  |          |         |          |showSuccess()  |              |       |        |          |          |
  |<---------|         |          |               |              |       |        |          |          |
  | showMsg  |         |          |               |              |       |        |          |          |
```

### Bang giai thich Sequence Diagram

| Buoc | Message | Tu | Den | Mo ta |
|------|---------|-----|-----|--------|
| 1 | login() | Teacher | LoginFrm | Teacher nhap username/password va nhan Login |
| 2 | validate() | LoginFrm | UserDAO | Goi UserDAO.validate(username, password) |
| 3 | query DB | UserDAO | Database | Truy van tblUser kiem tra dang nhap |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | openHome() | LoginFrm | HomeFrm | Mo giao dien trang chu |
| 6 | showHome | HomeFrm | Teacher | Hien thi menu |
| 7 | clickEnterGrade() | Teacher | HomeFrm | Teacher chon menu Grade → Enter grades by class |
| 8 | openGrade() | HomeFrm | EnterGradeFrm | Mo form nhap diem |
| 9 | getByTeacher(teacherId) | EnterGradeFrm | SubjectDAO | Lay danh sach mon hoc cua teacher |
| 10 | query DB | SubjectDAO | Database | Truy van tblSubject JOIN tblClass theo teacherId |
| 11 | return List<Subject> | SubjectDAO | EnterGradeFrm | Tra ve danh sach mon hoc |
| 12 | updateSubjectCbo() | EnterGradeFrm | UI | Cap nhat combobox Subject |
| 13 | selectSubject() | Teacher | EnterGradeFrm | Teacher chon mon "CS101" |
| 14 | getBySubjectAndTeacher(subjectId, teacherId) | EnterGradeFrm | ClassDAO | Lay lop theo mon va teacher |
| 15 | query DB | ClassDAO | Database | Truy van tblClass |
| 16 | return List<Class> | ClassDAO | EnterGradeFrm | Tra ve danh sach lop |
| 17 | updateClassCbo() | EnterGradeFrm | UI | Cap nhat combobox Class |
| 18 | selectClass() | Teacher | EnterGradeFrm | Teacher chon lop "CL001" |
| 19 | getByClass(classId) | EnterGradeFrm | RegistrationDAO | Lay danh sach dang ky cua lop |
| 20 | query DB | RegistrationDAO | Database | Truy van tblRegistration JOIN tblStudent |
| 21 | return List<Registration> | RegistrationDAO | EnterGradeFrm | Tra ve danh sach SV co dang ky |
| 22 | displayStudentTable() | EnterGradeFrm | UI | Hien thi bang SV voi cot diem trong |
| 23 | enterScores() | Teacher | EnterGradeFrm | Teacher nhap diem tung SV |
| 24 | clickConfirm() | Teacher | EnterGradeFrm | Teacher nhan nut Confirm |
| 25 | loop cho tung SV | EnterGradeFrm | Grade | Tinh finalScore = 10%×TP1 + 10%×TP2 + 20%×TP3 + 60%×Diem thi |
| 26 | insert(grade) | EnterGradeFrm | GradeDAO | INSERT INTO tblGrade |
| 27 | insert DB | GradeDAO | Database | Luu diem vao database |
| 28 | return true | GradeDAO | EnterGradeFrm | Luu thanh cong |
| 29 | showSuccess() | EnterGradeFrm | UI | Hien thi thong bao thanh cong |
| 30 | showMsg | EnterGradeFrm | Teacher | "Grades saved successfully for class CL001" |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Muc dich | Input | Expected Output |
|----|---------|-------|-----------------|
| TC01 | Nhap diem thanh cong cho 3 SV | TP1, TP2, TP3, Diem thi cho SV01, SV02, SV03 | Luu thanh cong, FinalScore duoc tinh |
| TC02 | Diem ngoai pham vi | TP1 = 11.0 cho SV01 | Loi: "Score must be between 0 and 10" |
| TC03 | Thieu diem thi | SV01 thieu Diem thi, nhan Confirm | Loi: "Incomplete grades for SV01" |
| TC04 | Nhap chu vao o diem | TP1 = "abc" cho SV01 | Loi: "Please enter a numeric value" |
| TC05 | Nhap diem cho lop da co diem | Lop CL001 da co diem, nhap lai | Cap nhat diem thanh cong (ghi de) |

### TC01: Nhap diem thanh cong — Chi tiet

**Muc dich:** Kiem tra teacher co the nhap diem thanh phan va diem thi cho tat ca SV trong lop CL001, he thong tinh diem tong ket va luu vao database.

**Database truoc khi chay test:**

tblUser:
| UserID | Username | Password | Role |
|--------|----------|----------|------|
| 1 | teacher01 | @Teach2026 | teacher |
| 2 | SV2026001 | @Stu2026A | student |
| 3 | SV2026002 | @Stu2026B | student |
| 4 | SV2026003 | @Stu2026C | student |

tblStudent:
| StudentID | StudentCode | StudentName | Password | Email | DOB | Major | Hometown | Address | UserID |
|-----------|-------------|-------------|----------|-------|-----|-------|----------|---------|--------|
| 1 | SV2026001 | Nguyen Van A | @Stu2026A | nva@gmail.com | 2004-03-15 | Computer Science | Ha Noi | 123 Tran Hung Dao | 2 |
| 2 | SV2026002 | Tran Thi B | @Stu2026B | ttb@gmail.com | 2004-07-22 | Computer Science | Hai Phong | 456 Le Loi | 3 |
| 3 | SV2026003 | Le Van C | @Stu2026C | lvc@gmail.com | 2004-01-10 | Computer Science | Da Nang | 789 Nguyen Hue | 4 |

tblSubject:
| SubjectID | SubjectCode | SubjectName | Credits | PrerequisiteID |
|-----------|-------------|-------------|---------|---------------|
| 1 | CS101 | Lap trinh Java | 3 | NULL |

tblClass:
| ClassID | SubjectID | TeacherID | Classroom | TimeSlot | MaxStudents | Section |
|---------|-----------|-----------|-----------|----------|-------------|---------|
| 1 | 1 | 1 | A101 | Thu 2, 7:30-9:30 | 40 | Section 1 |

tblRegistration:
| RegID | StudentID | ClassID | Semester | RegDate | Status |
|-------|-----------|---------|----------|---------|--------|
| 1 | 1 | 1 | 2026-1 | 2026-06-01 | registered |
| 2 | 2 | 1 | 2026-1 | 2026-06-01 | registered |
| 3 | 3 | 1 | 2026-1 | 2026-06-01 | registered |

tblPrerequisite: (0 dong — CS101 khong co mon tien quyet)

tblGrade:
| GradeID | RegID | Component1 | Component2 | Component3 | ExamScore | FinalScore |
|---------|-------|------------|------------|------------|-----------|------------|
| (0 dong — chua co diem) | | | | | | |

**Kich ban test:**

| Buoc | Hanh dong | Ket qua mong doi |
|------|-----------|------------------|
| 1 | Mo ung dung | Hien thi LoginFrm |
| 2 | Nhap username: "teacher01", password: "@Teach2026", nhan Login | Hien thi HomeFrm |
| 3 | Nhan menu "Grade" → "Enter grades by class" | Hien thi EnterGradeFrm, combobox Subject rong |
| 4 | Chon Subject "CS101 — Lap trinh Java" | Combobox Class cap nhat: CL001 (Section 1, Thu 2, A101) |
| 5 | Chon Class "CL001" | Bang SV hien thi 3 dong: SV2026001, SV2026002, SV2026003. Cot diem trong |
| 6 | **Kiem tra DB truoc khi nhap diem** | tblGrade van con 0 dong — chua co diem nao duoc luu |
| 7 | Nhap diem SV2026001: TP1=8.0, TP2=7.5, TP3=9.0, Diem thi=8.0 | Bang cap nhat dong SV2026001 |
| 8 | Nhap diem SV2026002: TP1=6.0, TP2=7.0, TP3=8.0, Diem thi=7.5 | Bang cap nhat dong SV2026002 |
| 9 | Nhap diem SV2026003: TP1=9.0, TP2=8.5, TP3=9.5, Diem thi=9.0 | Bang cap nhat dong SV2026003 |
| 10 | Nhan nut "Confirm" | He thong tinh FinalScore (10%×TP1 + 10%×TP2 + 20%×TP3 + 60%×Diem thi). SV01: 0.1×8 + 0.1×7.5 + 0.2×9 + 0.6×8 = 8.15. SV02: 0.1×6 + 0.1×7 + 0.2×8 + 0.6×7.5 = 7.40. SV03: 0.1×9 + 0.1×8.5 + 0.2×9.5 + 0.6×9 = 9.05. Hien thi cot FinalScore. Thong bao: "Grades saved successfully for class CL001. 3 students graded." |
| 11 | **Kiem tra DB sau khi luu** | tblGrade co 3 dong moi voi diem chinh xac cua SV01, SV02, SV03 |

**Database sau khi chay test:**

tblGrade:
| GradeID | RegID | Component1 | Component2 | Component3 | ExamScore | FinalScore |
|---------|-------|------------|------------|------------|-----------|------------|
| 1 | 1 | 8.0 | 7.5 | 9.0 | 8.0 | 8.15 |
| 2 | 2 | 6.0 | 7.0 | 8.0 | 7.5 | 7.40 |
| 3 | 3 | 9.0 | 8.5 | 9.5 | 9.0 | 9.05 |

(Cac bang khac khong thay doi)
