# Subject No. 07 — Student Results — Module "Register for classes"

> **Domain:** Student Results Management
> **Duration:** 90 minutes

---

## Cau 1: Scenario — Dang ky lop hoc (1.5 diem)

### Kich ban chinh — Register for classes

| Buoc | Nguoi dung | He thong | Ghi chu |
|------|-----------|----------|---------|
| 1 | Sinh vien Nguyen Van A (SV2026001) mo ung dung | Man hinh Login xuat hien voi o Username, o Password, nut Login | Giao dien LoginFrm |
| 2 | Nhap username: `SV2026001`, password: `@Stu2026A`, nhan Login | He thong xac thuc thanh cong. Hien thi HomeFrm voi cac menu: Register, View Schedule, Grade, Statistics | Dang nhap thanh cong |
| 3 | Nhan menu "Register" → chon "Register for new semester" | Hien thi RegisterClassFrm: bang danh sach mon hoc (cot: Ma mon, Ten mon, Tin chi, Tien quyet), combobox chon lop (rong), o Tong tin chi da dang ky: 0, nut Register, nut Cancel | Giao dien dang ky lop |
| 4 | He thong tai tat ca mon hoc hoc ky 2026-2 | Bang hien thi: \|CS101\|Lap trinh Java\|3\|Khong\|, \|CS102\|Cau truc du lieu\|3\|CS101\|, \|MA101\|Toan cao cap\|4\|Khong\|, \|EN101\|Tieng Anh\|3\|Khong\|, \|CS201\|Co so du lieu\|3\|CS101\| | Danh sach mon hoc |
| 5 | SV chon mon "CS101 — Lap trinh Java" (3 tin chi) | Combobox Class cap nhat: CL001 (GV: Tran Thi Mai, Thu 2 07:00–09:00), CL002 (GV: Le Van Hung, Thu 4 13:00–15:00). SV chon CL001 | Chon lop cho CS101 |
| 6 | SV chon mon "MA101 — Toan cao cap" (4 tin chi) | Combobox Class cap nhat: CL003 (GV: Pham Thi Lan, Thu 3 09:00–11:00), CL004 (GV: Hoang Van Nam, Thu 5 13:00–15:00). SV chon CL003 | Chon lop cho MA101 |
| 7 | SV chon mon "EN101 — Tieng Anh" (3 tin chi) | Combobox Class cap nhat: CL005 (GV: Nguyen Thi Hoa, Thu 6 07:00–09:00). SV chon CL005 | Chon lop cho EN101 |
| 8 | SV nhan nut "Register" | He thong kiem tra: (1) Tong tin chi = 3+4+3 = 10, 10 >= 10 va <= 15 ✓. (2) Khong trung khung gio: CL001 (Thu 2 07:00), CL003 (Thu 3 09:00), CL005 (Thu 6 07:00) — khong trung ✓. (3) Tien quyet: CS101 khong co tien quyet ✓, MA101 khong co tien quyet ✓, EN101 khong co tien quyet ✓. (4) Moi mon chi dang ky 1 lop ✓ | Kiem tra rang buoc |
| 9 | He thong thong bao: "Registration successful! Total credits: 10" va hien thi phieu dang ky | Phieu dang ky: Ma SV: SV2026001, Ten SV: Nguyen Van A, Khoa: K65, Hoc ky: 2026-2. Danh sach mon: \|CS101\|Lap trinh Java\|3\|Thu 2 07:00–09:00\|Tran Thi Mai\|, \|MA101\|Toan cao cap\|4\|Thu 3 09:00–11:00\|Pham Thi Lan\|, \|EN101\|Tieng Anh\|3\|Thu 6 07:00–09:00\|Nguyen Thi Hoa\| | Dang ky thanh cong |
| 10 | Nhan nut "Print" | He thong xuat phieu dang ky ra PDF/printer | In phieu |

### Kich ban ngoai le

**Truong hop 1: Tong tin chi ngoai pham vi cho phep**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 8b | SV chi dang ky 2 mon: CS101 (3 TC) + EN101 (3 TC) = 6 tin chi, nhan Register | He thong hien thi loi: "Total credits (6) is below minimum. You must register at least 10 credits per semester." |
| 9b | SV them mon MA101 (4 TC). Tong = 10 TC, nhan lai Register | He thong kiem tra thanh cong, dang ky thanh cong |

**Truong hop 2: Trung khung gio**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 8c | SV dang ky CS101-CL001 (Thu 2 07:00–09:00) va CS201-CL006 (Thu 2 07:00–09:00), nhan Register | He thong hien thi loi: "Time conflict detected! CS101-CL001 (Thu 2 07:00–09:00) conflicts with CS201-CL006 (Thu 2 07:00–09:00). Please choose a different class." |
| 9c | SV chon lai CS201-CL007 (Thu 4 09:00–11:00), nhan lai Register | He thong kiem tra thanh cong |

**Truong hop 3: Chua hoan thanh mon tien quyet**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 8d | SV dang ky CS102 (yeu cau tien quyet CS101). SV chua hoc CS101 (khong co trong bang Grade), nhan Register | He thong hien thi loi: "Prerequisite not met! CS102 requires CS101. You have not completed CS101 yet." |
| 9d | SV bo chon CS102, thay the bang EN101, nhan lai Register | He thong kiem tra thanh cong |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly ket qua sinh vien (Student Results Management) cho phep sinh vien (Student) dang ky cac lop hoc (Class) cho hoc ky moi. Moi mon hoc (Subject) co so tin chi va co the co mon tien quyet (prerequisite). Sinh vien chon mon hoc tu danh sach, sau do chon lop cu the (Class) ung voi mon do, moi lop co giao vien va khung gio rieng. He thong can kiem tra: tong tin chi nam trong khoang 10–15, khong trung khung gio giua cac lop da chon, da hoan thanh mon tien quyet, va moi mon chi dang ky mot lop. Khi dang ky thanh cong, he thong in phieu dang ky ghi ma SV, ten SV, khoa, hoc ky, va danh sach mon da dang ky voi ma mon, ten mon, so tin chi, khung gio, ten giao vien.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Student | Entity | Doi tuong chinh, co ma SV, password, ten, ngay sinh, khoa hoc, que quan, dia chi |
| Subject | Entity | Mon hoc, co ma mon, ten mon, so tin chi, mon tien quyet |
| Class | Entity | Lop hoc cu the, co ma lop, ten lop, giao vien, khung gio |
| Teacher | Entity | Giao vien, co ma GV, ten GV, bo mon — dai dien cho "associated lecturers" trong de bai |
| Registration | Entity | Bang dang ky, lien ket sinh vien voi lop hoc |
| Grade | Entity | Bang diem, dung de kiem tra tien quyet |
| User | Entity | Tai khoan nguoi dung (student, teacher) |
| Classroom | Attribute | Phong hoc — thuoc tinh cua Class (classroomName) |
| TimeSlot | Attribute | Khung gio trong tuan — thuoc tinh cua Class (dayOfWeek, startTime, endTime) |
| Prerequisite | Relationship | Quan he giua cac mon hoc (mon tien quyet) |
| Semester | Attribute | Thuoc tinh cua Registration |
| Credits | Attribute | Thuoc tinh cua Subject |
| Password | Attribute | Thuoc tinh cua Student (de dang nhap) |
| Hometown | Attribute | Thuoc tinh cua Student |
| Address | Attribute | Thuoc tinh cua Student |

### Buoc 3: Xac dinh quan he

1. **Subject — Class**: Mot mon hoc co nhieu lop hoc (1-n). Moi Class thuoc ve mot Subject.
2. **Teacher — Class**: Mot giao vien co day nhieu lop (1-n). Moi Class co mot giao vien.
3. **Class — Registration**: Mot lop hoc co nhieu dang ky (1-n). Moi Registration lien ket mot Student voi mot Class.
4. **Student — Registration**: Mot sinh vien co nhieu dang ky (1-n). Moi Registration thuoc ve mot Student.
5. **Registration — Grade**: Mot dang ky co mot bang diem (1-1). Moi Grade lien ket voi mot Registration.
6. **Subject — Subject (Prerequisite)**: Quan he tu tham chieu (n-n). Mot mon hoc co the co nhieu mon tien quyet. VD: CS102 yeu cau CS101.
7. **User — Student**: Mot User lien ket voi mot Student (1-1).
8. **User — Teacher**: Mot User lien ket voi mot Teacher (1-1).

### Buoc 4: Class Diagram (ASCII art)

```
+------------------+        +------------------+
|    <<entity>>    |        |    <<entity>>    |
|     Subject      |1      *|      Class       |
+------------------+--------+------------------+
| - subjectId: int |        | - classId: int   |
| - subjectCode:   |        | - className:     |
|   String         |        |   String         |
| - subjectName:   |        | - subjectId: int |
|   String         |        | - classroomId:   |
| - credits: int   |        |   int            |
| - prerequisiteId:|        | - timeSlotId:    |
|   int            |        |   int            |
+------------------+        | - maxStudents:   |
        |*                  |   int            |
        |                   | - section: String|
        | 1                 | - teacherId: int |
        v                   +------------------+
+------------------+              |*  |*
|    <<entity>>    |              |1  |1
|   Subject        |              v   v
| (Prerequisite)   |     +------------------+  +------------------+
+------------------+     |    <<entity>>    |  |    <<entity>>    |
| - subjectId: int |     |   Classroom      |  |    Teacher       |
| - requiredSubject|     +------------------+  +------------------+
|   Id: int        |     | - classroomId:   |  | - teacherId: int |
+------------------+     |   int            |  | - teacherCode:   |
                         | - classroomName: |  |   String         |
                         |   String         |  | - teacherName:   |
                         | - building:      |  |   String         |
                         |   String         |  | - department:    |
                         | - capacity: int  |  |   String         |
                         +------------------+  | - email: String  |
                                 |1            | - phone: String  |
+------------------+             |             | - userId: int    |
|    <<entity>>    |             |             +------------------+
|   TimeSlot       |             |                   |1
+------------------+             |                   |
| - timeSlotId: int|             |                   |1
| - slotName:      |             |             +------------------+
|   String         |             |             |    <<entity>>    |
| - startTime:     |             |             |      User        |
|   String         |             |             +------------------+
| - endTime: String|             |             | - userId: int    |
| - dayOfWeek:     |             |             | - username:      |
|   String         |             |             |   String         |
+------------------+             |             | - password:      |
        |1                       |             |   String         |
        |                        |             | - role: String   |
        +------------------------+             +------------------+
                     |*                               |1
                     |1                               |
              +------------------+                     |1
              |    <<entity>>    |             +------------------+
              |   Registration   |             |    <<entity>>    |
              +------------------+             |     Student      |
              | - regId: int     |             +------------------+
              | - studentId: int |             | - studentId: int |
              | - classId: int   |             | - studentCode:   |
              | - semester:      |             |   String         |
              |   String         |             | - studentName:   |
              | - regDate: Date  |             |   String         |
              | - status: String |             | - password:      |
              +------------------+             |   String         |
                     |1                        | - dob: Date      |
                     |1                        | - course: String |
                     v                         | - hometown:      |
              +------------------+             |   String         |
              |    <<entity>>    |             | - address:       |
              |      Grade       |             |   String         |
              +------------------+             | - userId: int    |
              | - gradeId: int   |             +------------------+
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
```

### Buoc 5: Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|---------|------|------------|
| Subject → Class | 1-n | Mot mon hoc co nhieu lop. VD: CS101 co CL001, CL002 |
| Teacher → Class | 1-n | Mot giao vien day nhieu lop. VD: Tran Thi Mai day CL001 |
| Class → Registration | 1-n | Mot lop co nhieu SV dang ky. VD: CL001 co 35 SV |
| Student → Registration | 1-n | Mot SV dang ky nhieu lop. VD: SV2026001 dang ky 3 lop |
| Registration → Grade | 1-1 | Moi dang ky co mot bang diem duy nhat |
| Subject → Subject (Prerequisite) | n-n | Quan he tu tham chieu. VD: CS102 yeu cau CS101, CS201 yeu cau CS101 |
| User → Student | 1-1 | Mot tai khoan User ung voi mot Student |
| User → Teacher | 1-1 | Mot tai khoan User ung voi mot Teacher |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_RegisterClass" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 9 class: Student, Subject, Class, Registration, Grade, User, Teacher |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, RegisterClassFrm, RegistrationFormFrm |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Registration) |
|------|----------|----------------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Registration` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-regId: int`, `-studentId: int`, `-classId: int`, `-semester: String`, `-regDate: Date`, `-status: String` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+addRegistration(reg: Registration): boolean`, `+checkPrerequisite(studentId: int, subjectId: int): boolean` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| Student | `<<entity>>` | `-studentId: int`, `-studentCode: String`, `-studentName: String`, `-password: String`, `-dob: Date`, `-course: String`, `-hometown: String`, `-address: String`, `-userId: int` | `+getStudentById(id: int): Student` |
| Subject | `<<entity>>` | `-subjectId: int`, `-subjectCode: String`, `-subjectName: String`, `-credits: int`, `-prerequisiteId: int` | `+getAllSubjects(): List<Subject>`, `+getPrerequisite(subjectId: int): Subject` |
| Class | `<<entity>>` | `-classId: int`, `-className: String`, `-subjectId: int`, `-classroomId: int`, `-timeSlotId: int`, `-maxStudents: int`, `-section: String`, `-teacherId: int` | `+getAvailableClasses(subjectId: int): List<Class>`, `+getCurrentEnrollment(classId: int): int` |
| Teacher | `<<entity>>` | `-teacherId: int`, `-teacherCode: String`, `-teacherName: String`, `-department: String`, `-email: String`, `-phone: String`, `-userId: int` | `+getTeacherById(id: int): Teacher` |
| Registration | `<<entity>>` | `-regId: int`, `-studentId: int`, `-classId: int`, `-semester: String`, `-regDate: Date`, `-status: String` | `+addRegistration(reg: Registration): boolean`, `+checkPrerequisite(studentId: int, subjectId: int): boolean` |
| Grade | `<<entity>>` | `-gradeId: int`, `-regId: int`, `-component1: float`, `-component2: float`, `-component3: float`, `-examScore: float`, `-finalScore: float` | `+calculateFinalScore(): float` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | `+validateLogin(username: String, password: String): boolean` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuSchedule: JMenuItem`, `-menuStudent: JMenuItem`, `-menuSubject: JMenuItem`, `-menuGrade: JMenuItem`, `-subLogout: JButton` |
| RegisterClassFrm | `<<boundary>>` | `-inSubject: JComboBox`, `-outClassTable: JTable`, `-outsubClassList: JTable`, `-subRegister: JButton`, `-subCancel: JButton`, `-subRefresh: JButton` |
| RegistrationFormFrm | `<<boundary>>` | `-outStudentInfo: JLabel`, `-outClassInfo: JLabel`, `-outSchedule: JTable`, `-subConfirm: JButton`, `-subBack: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (RegisterClassFrm → RegistrationDAO) |

**6. Cách ghi multiplicity:**

| Multiplicity | Cách ghi trong VP | Ví dụ |
|--------------|-------------------|-------|
| 1..1 | Ghi "1" ở một đầu | User có đúng 1 Student |
| 0..* hoặc 1..* | Ghi "*" hoặc "1..*" ở đầu kia | Student có nhiều Registration |
| 0..1 | Ghi "0..1" | (hiếm dùng) |

Ghi multiplicity ở cả 2 đầu của đường kết nối. Click vào đường → Properties → Source Multiplicity / Target Multiplicity.

**7. Bảng quan hệ chi tiết:**

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|---------------|--------------|------------|
| Subject | Class | Association | 1 → * | Mỗi môn học có nhiều lớp |
| Teacher | Class | Association | 1 → * | Mỗi giáo viên dạy nhiều lớp |
| Class | Registration | Association | 1 → * | Mỗi lớp có nhiều sinh viên đăng ký |
| Student | Registration | Association | 1 → * | Mỗi sinh viên đăng ký nhiều lớp |
| Registration | Grade | Association | 1 → 1 | Mỗi đăng ký có một bảng điểm |
| Subject | Subject | Association (self-ref) | * → * | Quan hệ môn tiên quyết |
| User | Student | Association | 1 → 1 | Mỗi tài khoản User ứng với một Student |
| User | Teacher | Association | 1 → 1 | Mỗi tài khoản User ứng với một Teacher |

**8. Ví dụ cụ thể trên Visual Paradigm:**

*Ví dụ 1: Vẽ quan hệ Student → Registration (1-n, Association)*

1. Click chuột phải vào class Student → chọn **Association** → kéo đến class Registration.
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `1`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `registers`.

*Ví dụ 2: Vẽ quan hệ Subject → Subject (n-n self-reference, Association)*

1. Click chuột phải vào class Subject → chọn **Association** → kéo đến **chính class Subject** (tự tham chiếu).
2. Click vào đường kết nối → chọn **Properties**.
3. Set Source Multiplicity = `*`, Target Multiplicity = `*`.
4. Giữ mặc định mũi tên tam giác rỗng (▷) — đây là Association.
5. Đặt tên association: `requires`. Ghi chú: thể hiện quan hệ môn tiên quyết.

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
  Menu Register → menuRegister
  Menu View Schedule → menuViewSchedule
  Menu Grade → menuGrade
  Menu Statistics → menuStatistics
  Nút Logout → subLogout

SV chọn Register → Giao diện đăng ký lớp xuất hiện → cần class: RegisterClassFrm
  Bảng danh sách môn học → tblSubject
  Combobox chọn lớp → inClass
  Hiển thị tổng tín chỉ → outTotalCredits
  Bảng môn đã đăng ký → outRegistered
  Nút Register → subRegister
  Nút Remove → subRemove
  Nút Cancel → subCancel

Hệ thống tải danh sách môn học → cần phương thức:
  Tên: getAllSubjects()
  Đầu vào: (không)
  Đầu ra: List<Subject>
  Gán cho entity class: Subject.

SV chọn môn → Hệ thống tải danh sách lớp theo môn → cần phương thức:
  Tên: getClassesBySubject()
  Đầu vào: subjectId (int)
  Đầu ra: List<Class>
  Gán cho entity class: Class.

SV nhấn Register → Hệ thống kiểm tra tổng tín chỉ → cần phương thức:
  Tên: checkCredits()
  Đầu vào: registrations (List<Registration>)
  Đầu ra: boolean
  Gán cho entity class: Registration.

Hệ thống kiểm tra trùng khung giờ → cần phương thức:
  Tên: checkTimeConflict()
  Đầu vào: registrations (List<Registration>)
  Đầu ra: boolean
  Gán cho entity class: Registration.

Hệ thống kiểm tra môn tiên quyết → cần phương thức:
  Tên: checkPrerequisites()
  Đầu vào: studentId (int), subjectIds (List<Integer>)
  Đầu ra: boolean
  Gán cho entity class: Grade.

Hệ thống lưu đăng ký → cần phương thức:
  Tên: insertRegistrations()
  Đầu vào: registrations (List<Registration>)
  Đầu ra: boolean
  Gán cho entity class: Registration.

Đăng ký thành công → Giao diện phiếu đăng ký xuất hiện → cần class: RegistrationFormFrm
  Hiển thị mã sinh viên → outStudentCode
  Hiển thị tên sinh viên → outStudentName
  Hiển thị khóa → outCourse
  Hiển thị học kỳ → outSemester
  Bảng môn đã đăng ký → tblRegistered
  Nút Print → subPrint
  Nút Close → subClose

### Tóm tắt
View classes: LoginFrm, HomeFrm, RegisterClassFrm, RegistrationFormFrm
Phương thức: validateLogin(), getAllSubjects(), getClassesBySubject(), checkCredits(), checkTimeConflict(), checkPrerequisites(), insertRegistrations()

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|-----------|--------|
| LoginFrm | Form dang nhap, cho phep SV nhap username/password de truy cap he thong |
| HomeFrm | Form trang chu, hien thi menu dieu huong cho SV |
| RegisterClassFrm | Form dang ky lop hoc, cho phep SV chon mon, chon lop, xem tong tin chi, dang ky |
| RegistrationFormFrm | Form phieu dang ky, hien thi thong tin dang ky va cho phep in |

### Buoc 2: UI Elements

**LoginFrm**
- `inUsername`: JTextField — O nhap ma sinh vien
- `inPassword`: JPasswordField — O nhap mat khau
- `subLogin`: JButton — Nut dang nhap
- `subCancel`: JButton — Nut huy

**HomeFrm**
- `menuRegister`: JMenuItem — Menu "Register"
- `menuViewSchedule`: JMenuItem — Menu "View Schedule"
- `menuGrade`: JMenuItem — Menu "Grade"
- `menuStatistics`: JMenuItem — Menu "Statistics"
- `subLogout`: JButton — Nut dang xuat

**RegisterClassFrm**
- `tblSubject`: JTable — Bang danh sach mon hoc (cot: Ma mon, Ten mon, Tin chi, Tien quyet, Chon lop)
- `inClass`: JComboBox — Combobox chon lop (hien thi khi chon mon: ma lop, ten GV, khung gio)
- `outTotalCredits`: JLabel — Hien thi tong tin chi da dang ky
- `outRegistered`: JTable — Bang cac mon da dang ky (cot: Ma mon, Ten mon, Tin chi, Lop, Khung gio, GV)
- `subRegister`: JButton — Nut dang ky
- `subRemove`: JButton — Nut xoa mon da chon
- `subCancel`: JButton — Nut huy

**RegistrationFormFrm**
- `outStudentCode`: JLabel — Ma sinh vien
- `outStudentName`: JLabel — Ten sinh vien
- `outCourse`: JLabel — Khoa hoc
- `outSemester`: JLabel — Hoc ky
- `tblRegistered`: JTable — Bang mon da dang ky (cot: Ma mon, Ten mon, Tin chi, Khung gio, GV)
- `subPrint`: JButton — Nut in phieu
- `subClose`: JButton — Nut dong

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| SubjectDAO | `List<Subject> getAll()` | Lay tat ca mon hoc cua hoc ky |
| SubjectDAO | `Subject getById(int subjectId)` | Lay mon hoc theo ma |
| SubjectDAO | `List<Subject> getPrerequisites(int subjectId)` | Lay danh sach mon tien quyet |
| ClassDAO | `List<Class> getBySubject(int subjectId)` | Lay danh sach lop theo mon |
| ClassDAO | `Class getById(int classId)` | Lay lop theo ma |
| StudentDAO | `Student getById(int studentId)` | Lay SV theo ma |
| RegistrationDAO | `List<Registration> getByStudentAndSemester(int studentId, String semester)` | Lay dang ky theo SV va hoc ky |
| RegistrationDAO | `boolean insert(Registration reg)` | Them dang ky moi |
| RegistrationDAO | `boolean batchInsert(List<Registration> regs)` | Them nhieu dang ky cung luc |
| GradeDAO | `List<Grade> getByStudent(int studentId)` | Lay diem cua SV (de kiem tra tien quyet) |
| GradeDAO | `Grade getByRegistration(int regId)` | Lay diem theo dang ky |
| UserDAO | `User getByUsername(String username)` | Lay nguoi dung theo ten dang nhap |
| UserDAO | `boolean validate(String username, String password)` | Xac thuc dang nhap |
| TeacherDAO | `Teacher getById(int teacherId)` | Lay giao vien theo ma |
| TeacherDAO | `List<Teacher> getAll()` | Lay tat ca giao vien |

### Buoc 4: Entity Class Design

```java
// Student.java
public class Student {
    private int studentId;
    private String studentCode;    // "SV2026001"
    private String studentName;    // "Nguyen Van A"
    private String password;       // "@Stu2026A"
    private Date dob;              // 2004-03-15
    private String course;         // "K65"
    private String hometown;       // "Ha Noi"
    private String address;        // "123 Nguyen Trai, Ha Noi"
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
    private int prerequisiteId;    // FK -> tblSubject, null if none
    private Subject prerequisite;  // object attribute
    private List<Class> classes;   // object attribute
    // getters, setters
}

// Class.java
public class Class {
    private int classId;
    private String className;      // "CL001"
    private int subjectId;         // FK -> tblSubject
    private int classroomId;       // FK -> tblClassroom
    private int timeSlotId;        // FK -> tblTimeSlot
    private int maxStudents;       // 40
    private String section;        // "Section 1"
    private int teacherId;         // FK -> tblTeacher
    private Subject subject;       // object attribute
    private Classroom classroom;   // object attribute
    private TimeSlot timeSlot;     // object attribute
    private Teacher teacher;       // object attribute
    private List<Registration> registrations; // object attribute
    // getters, setters
}

// Teacher.java
public class Teacher {
    private int teacherId;
    private String teacherCode;    // "GV001"
    private String teacherName;    // "Tran Thi Mai"
    private String department;     // "Computer Science"
    private String email;          // "ttm@gmail.com"
    private String phone;          // "0912345678"
    private int userId;            // FK -> tblUser
    private User user;              // object attribute
    private List<Class> classes;   // object attribute
    // getters, setters
}

// Registration.java
public class Registration {
    private int regId;
    private int studentId;         // FK -> tblStudent
    private int classId;           // FK -> tblClass
    private String semester;       // "2026-2"
    private Date regDate;          // 2026-06-01
    private String status;         // "registered"
    private Student student;       // object attribute
    private Class classObj;        // object attribute
    private Grade grade;           // object attribute
    // getters, setters
}

// Grade.java
public class Grade {
    private int gradeId;
    private int regId;             // FK -> tblRegistration
    private double component1;
    private double component2;
    private double component3;
    private double examScore;
    private double finalScore;
    private Registration registration; // object attribute
    // getters, setters
}

// User.java
public class User {
    private int userId;
    private String username;       // "SV2026001"
    private String password;       // "@Stu2026A"
    private String role;           // "student"
    // getters, setters
}

// Classroom.java
public class Classroom {
    private int classroomId;
    private String classroomName;  // "A101"
    private String building;       // "A"
    private int capacity;          // 40
    private List<Class> classes;   // object attribute
    // getters, setters
}

// TimeSlot.java
public class TimeSlot {
    private int timeSlotId;
    private String slotName;       // "Slot1"
    private String startTime;      // "07:00"
    private String endTime;        // "09:00"
    private String dayOfWeek;      // "Monday"
    private List<Class> classes;   // object attribute
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
| DOB | DATE | |
| Course | VARCHAR(20) | DEFAULT 'K65' |
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
| PrerequisiteID | INT | FOREIGN KEY → tblSubject(SubjectID), NULL allowed |

**tblClassroom**
| Column | Type | Constraint |
|--------|------|-----------|
| ClassroomID | INT | PRIMARY KEY, AUTO_INCREMENT |
| ClassroomName | VARCHAR(50) | NOT NULL |
| Building | VARCHAR(50) | |
| Capacity | INT | NOT NULL, CHECK (Capacity > 0) |

**tblTimeSlot**
| Column | Type | Constraint |
|--------|------|-----------|
| TimeSlotID | INT | PRIMARY KEY, AUTO_INCREMENT |
| SlotName | VARCHAR(50) | NOT NULL |
| StartTime | VARCHAR(10) | NOT NULL |
| EndTime | VARCHAR(10) | NOT NULL |
| DayOfWeek | VARCHAR(20) | NOT NULL |

**tblTeacher**
| Column | Type | Constraint |
|--------|------|-----------|
| TeacherID | INT | PRIMARY KEY, AUTO_INCREMENT |
| TeacherCode | VARCHAR(20) | NOT NULL, UNIQUE |
| TeacherName | VARCHAR(100) | NOT NULL |
| Department | VARCHAR(50) | |
| Email | VARCHAR(100) | |
| Phone | VARCHAR(20) | |
| UserID | INT | FOREIGN KEY → tblUser(UserID) |

**tblClass**
| Column | Type | Constraint |
|--------|------|-----------|
| ClassID | INT | PRIMARY KEY, AUTO_INCREMENT |
| ClassName | VARCHAR(20) | NOT NULL |
| SubjectID | INT | FOREIGN KEY → tblSubject(SubjectID), NOT NULL |
| TeacherID | INT | FOREIGN KEY → tblTeacher(TeacherID), NOT NULL |
| ClassroomID | INT | FOREIGN KEY → tblClassroom(ClassroomID) |
| TimeSlotID | INT | FOREIGN KEY → tblTimeSlot(TimeSlotID) |
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
| Component1 | DOUBLE | DEFAULT 0 |
| Component2 | DOUBLE | DEFAULT 0 |
| Component3 | DOUBLE | DEFAULT 0 |
| ExamScore | DOUBLE | DEFAULT 0 |
| FinalScore | DOUBLE | DEFAULT 0 |

### Buoc 6: Huong dan ve Class Diagram bang Visual Paradigm

1. Mo Visual Paradigm → File → New Project → dat ten "StudentResults_RegisterClass"
2. Chon Diagram → Class Diagram
3. Tao 9 class: Student, Subject, Class, Registration, Grade, User, Classroom, TimeSlot, Teacher
4. Voi moi class:
   - Nhap dup vao class → nhap ten class
   - Them attributes: ten: kieu (VD: studentId: int)
   - Chon visibility: private (-) cho tat ca attributes
5. Ve quan he:
   - Subject → Class: Association (1-n)
   - Teacher → Class: Association (1-n)
   - Class → Registration: Association (1-n)
   - Student → Registration: Association (1-n)
   - Registration → Grade: Association (1-1)
   - Classroom → Class: Association (1-n)
   - TimeSlot → Class: Association (1-n)
   - Subject → Subject: Self-association (Prerequisite, n-n)
   - User → Student: Association (1-1)
   - User → Teacher: Association (1-1)
6. Dat multiplicity: nhap vao Association → chon multiplicity o moi dau
7. Them `<<entity>>` stereotype cho moi class
8. Export: File → Export → PNG/PDF

---

## Cau 4: Sequence Diagram (1.5 diem)

### Huong dan ve bang Visual Paradigm

1. Mo Visual Paradigm → File → New → Sequence Diagram
2. Dat ten: "RegisterClass_SequenceDiagram"
3. Tao lifelines:
   - Actor: Student
   - Boundary: LoginFrm, HomeFrm, RegisterClassFrm, RegistrationFormFrm
   - Control: UserDAO, SubjectDAO, ClassDAO, RegistrationDAO, GradeDAO
   - Entity: User, Subject, Class, Student, Registration, Grade
4. Ve message flow tu tren xuong theo cac buoc trong bang
5. Them alt fragment cho cac dieu kien: tin chi ngoai pham vi, trung gio, thieu tien quyet
6. Them loop fragment cho viec lap chon mon va lop
7. Export: File → Export → PNG/PDF

### Sequence Diagram — Dang ky lop hoc (Design phase)

```
Student    LoginFrm    UserDAO    HomeFrm    RegisterClassFrm    SubjectDAO    ClassDAO    GradeDAO    RegistrationDAO
  |           |           |          |              |                 |            |            |             |
  |---login-->|           |          |              |                 |            |            |             |
  |           |--validate->|         |              |                 |            |            |             |
  |           |           |--query   |              |                 |            |            |             |
  |           |           | DB       |              |                 |            |            |             |
  |           |           |<-User----|              |                 |            |            |             |
  |           |<-true-----|          |              |                 |            |            |             |
  |           |---open----|--------->|              |                 |            |            |             |
  |<--showHome|           |          |              |                 |            |            |             |
  |           |           |          |              |                 |            |            |             |
  |---click-->|           |          |              |                 |            |            |             |
  | Register  |           |          |---open------>|                 |            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |---getAll-------->|            |            |             |
  |           |           |          |              |  Subjects()     |            |            |             |
  |           |           |          |              |                 |--query DB  |            |             |
  |           |           |          |              |                 |<-List<Sub>-|            |             |
  |           |           |          |              |<--List<Subject>-|            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |--display        |            |            |             |
  |           |           |          |              | subjects        |            |            |             |
  |           |           |          |              |                 |            |            |             |
  | [loop: for each subject to register]           |                 |            |            |             |
  |---select->|           |          |              |                 |            |            |             |
  | Subject   |           |          |              |                 |            |            |             |
  |           |           |          |              |---getClasses--->|            |            |             |
  |           |           |          |              |  BySubject()   |            |            |             |
  |           |           |          |              |                 |            |--query DB  |             |
  |           |           |          |              |                 |            |<-List<Cls>-|             |
  |           |           |          |              |<--List<Class>---|            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |--update class   |            |            |             |
  |           |           |          |              | combobox       |            |            |             |
  |---select->|           |          |              |                 |            |            |             |
  | Class     |           |          |              |                 |            |            |             |
  |           |           |          |              |--add to         |            |            |             |
  |           |           |          |              | selected list   |            |            |             |
  | [end loop]|           |          |              |                 |            |            |             |
  |           |           |          |              |                 |            |            |             |
  |---click-->|           |          |              |                 |            |            |             |
  | Register  |           |          |              |                 |            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |--validate       |            |            |             |
  |           |           |          |              | (checkCredits, checkTimeConflict)     |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |---check-------->|            |            |             |
  |           |           |          |              |  Prerequisites()|            |            |             |
  |           |           |          |              |                 |            |     |--query DB      |
  |           |           |          |              |                 |            |     |<-Grade---------|
  |           |           |          |              |<--true----------|            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |---batch-------->|            |            |             |
  |           |           |          |              |  Insert()       |            |            |             |
  |           |           |          |              |                 |            |            |--insert DB  |
  |           |           |          |              |                 |            |            |<-true-------|
  |           |           |          |              |<--true----------|            |            |             |
  |           |           |          |              |                 |            |            |             |
  |           |           |          |              |--show registration form      |            |             |
  |<--showForm|           |          |              |                 |            |            |             |
  |           |           |          |              |                 |            |            |             |
  |---click-->|           |          |              |                 |            |            |             |
  | Print     |           |          |              |                 |            |            |             |
  |           |           |          |              |--print form     |            |            |             |
  |<--PDF-----|           |          |              |                 |            |            |             |
```

### Bang giai thich Sequence Diagram

| Buoc | Message | Tu | Den | Mo ta |
|------|---------|-----|-----|--------|
| 1 | login() | Student | LoginFrm | SV nhap username "SV2026001", password "******", nhan Login |
| 2 | validate() | LoginFrm | UserDAO | Goi UserDAO.validate("SV2026001", "******") |
| 3 | query DB | UserDAO | Database | Truy van tblUser WHERE Username='SV2026001' |
| 4 | return User | UserDAO | LoginFrm | Tra ve doi tu User (role=student) |
| 5 | open HomeFrm | LoginFrm | HomeFrm | Mo giao dien Home voi menu Register, View Schedule, Grade |
| 6 | showHome | HomeFrm | Student | Hien thi trang chu |
| 7 | clickRegister | Student | HomeFrm | SV chon menu Register → Register for new semester |
| 8 | open RegisterClassFrm | HomeFrm | RegisterClassFrm | Mo form dang ky lop hoc |
| 9 | getAllSubjects() | RegisterClassFrm | SubjectDAO | Goi SubjectDAO.getAll() — lay tat ca mon hoc hoc ky |
| 10 | query DB | SubjectDAO | Database | Truy van tblSubject |
| 11 | return List | SubjectDAO | RegisterClassFrm | Tra ve danh sach: CS101, CS102, MA101, EN101 |
| 12 | display subjects | RegisterClassFrm | UI | Hien thi bang mon hoc (ma mon, ten mon, tin chi, tien quyet) |
| 13 | selectSubject("CS101") | Student | RegisterClassFrm | SV chon mon CS101 (3 tin chi) |
| 14 | getClassesBySubject(1) | RegisterClassFrm | ClassDAO | Goi ClassDAO.getBySubject(1) — lay lop cua CS101 |
| 15 | query DB | ClassDAO | Database | Truy van tblClass JOIN tblTimeSlot JOIN tblUser WHERE SubjectID=1 |
| 16 | return List | ClassDAO | RegisterClassFrm | Tra ve: CL001 (Tran Thi Mai, Mon 07:00-09:00), CL002 (Le Van Hung, Wed 13:00-15:00) |
| 17 | update class combobox | RegisterClassFrm | UI | Cap nhat combobox Class voi 2 lop |
| 18 | selectClass("CL001") | Student | RegisterClassFrm | SV chon lop CL001 |
| 19 | add to selected list | RegisterClassFrm | UI | Them CS101-CL001 vao bang da chon. Tong TC = 3 |
| 20-27 | (lap lai cho MA101-CL003, EN101-CL005) | | | Tuong tu buoc 13-19 |
| 28 | clickRegister | Student | RegisterClassFrm | SV nhan nut Register |
| 29 | checkCredits() | RegisterClassFrm | RegisterClassFrm | Kiem tra tong tin chi: 3+4+3 = 10, 10 >= 10 va <= 15 ✓ |
| 30 | checkTimeConflict() | RegisterClassFrm | RegisterClassFrm | Kiem tra trung gio: Mon 07:00, Tue 09:00, Fri 07:00 — khong trung ✓ |
| 31 | checkPrerequisites() | RegisterClassFrm | GradeDAO | Goi GradeDAO.getByStudent(1) — lay diem SV de kiem tra tien quyet |
| 32 | query DB | GradeDAO | Database | Truy van tblGrade JOIN tblRegistration WHERE StudentID=1 |
| 33 | return List | GradeDAO | RegisterClassFrm | CS101, MA101, EN101 khong co tien quyet → hop le ✓ |
| 34 | batchInsert() | RegisterClassFrm | RegistrationDAO | Goi RegistrationDAO.batchInsert(list) — luu 3 dang ky |
| 35 | insert DB | RegistrationDAO | Database | INSERT INTO tblRegistration (3 ban ghi: StudentID=1, ClassID=1,3,5, Semester='2026-2') |
| 36 | return true | RegistrationDAO | RegisterClassFrm | Luu thanh cong |
| 37 | show registration form | RegisterClassFrm | Student | Hien thi phieu dang ky: Ma SV, Ten SV, Khoa, Hoc ky, danh sach mon |
| 38 | clickPrint | Student | RegisterClassFrm | SV nhan nut Print |
| 39 | printForm() | RegisterClassFrm | UI | Xuat phieu dang ky ra PDF |
| 40 | showPDF | RegisterClassFrm | Student | Hien thi PDF phieu dang ky |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| No. | Module | Test case |
|-----|--------|-----------|
| 1 | Register for classes | Dang ky thanh cong 3 mon (10 TC), khong trung gio, tien quyet du |
| 2 | Register for classes | Tong tin chi duoi 10 (chi 6 TC) |
| 3 | Register for classes | Trung khung gio giua 2 lop |
| 4 | Register for classes | Chua hoan thanh mon tien quyet |
| 5 | Register for classes | Dang ky 2 lop cung 1 mon hoc |

### TC01: Dang ky thanh cong 3 mon — Chi tiet

**Muc dich:** Kiem tra SV co the dang ky 3 mon hoc voi tong tin chi = 10 (nam trong khoang 10–15), khong trung khung gio, khong co van de tien quyet, va he thong in phieu dang ky dung.

**Database truoc khi chay test:**

tblUser:
| UserID | Username | Password | Role |
|--------|----------|----------|------|
| 1 | staff01 | @Staff2026 | staff |
| 2 | teacher01 | @Teach2026 | teacher |
| 3 | teacher02 | @Teach2026B | teacher |
| 4 | teacher03 | @Teach2026C | teacher |
| 5 | teacher04 | @Teach2026D | teacher |
| 6 | teacher05 | @Teach2026E | teacher |
| 7 | SV2026001 | @Stu2026A | student |

tblStudent:
| StudentID | StudentCode | StudentName | Password | DOB | Course | Hometown | Address | UserID |
|-----------|-------------|-------------|----------|-----|--------|----------|---------|--------|
| 1 | SV2026001 | Nguyen Van A | @Stu2026A | 2004-03-15 | K65 | Ha Noi | 123 Nguyen Trai, Ha Noi | 7 |

tblSubject:
| SubjectID | SubjectCode | SubjectName | Credits | PrerequisiteID |
|-----------|-------------|-------------|---------|---------------|
| 1 | CS101 | Lap trinh Java | 3 | NULL |
| 2 | CS102 | Cau truc du lieu | 3 | 1 |
| 3 | MA101 | Toan cao cap | 4 | NULL |
| 4 | EN101 | Tieng Anh | 3 | NULL |

tblClassroom:
| ClassroomID | ClassroomName | Building | Capacity |
|-------------|---------------|----------|----------|
| 1 | A101 | A | 40 |
| 2 | A201 | A | 35 |
| 3 | B301 | B | 50 |

tblTimeSlot:
| TimeSlotID | SlotName | StartTime | EndTime | DayOfWeek |
|------------|----------|-----------|---------|-----------|
| 1 | Slot1 | 07:00 | 09:00 | Monday |
| 2 | Slot2 | 09:00 | 11:00 | Tuesday |
| 3 | Slot3 | 13:00 | 15:00 | Wednesday |
| 4 | Slot4 | 07:00 | 09:00 | Thursday |
| 5 | Slot5 | 07:00 | 09:00 | Friday |

tblTeacher:
| TeacherID | TeacherCode | TeacherName | Department | Email | Phone | UserID |
|-----------|-------------|-------------|------------|-------|-------|--------|
| 1 | GV001 | Tran Thi Mai | Computer Science | ttm@gmail.com | 0911111111 | 2 |
| 2 | GV002 | Le Van Hung | Computer Science | lvh@gmail.com | 0922222222 | 3 |
| 3 | GV003 | Pham Thi Lan | Mathematics | ptl@gmail.com | 0933333333 | 4 |
| 4 | GV004 | Hoang Van Nam | Computer Science | hvn@gmail.com | 0944444444 | 5 |
| 5 | GV005 | Nguyen Thi Hoa | English | nth@gmail.com | 0955555555 | 6 |

tblClass:
| ClassID | ClassName | SubjectID | TeacherID | ClassroomID | TimeSlotID | MaxStudents | Section |
|---------|-----------|-----------|-----------|-------------|------------|-------------|---------|
| 1 | CL001 | 1 | 1 | 1 | 1 | 40 | Section 1 |
| 2 | CL002 | 1 | 2 | 2 | 3 | 35 | Section 2 |
| 3 | CL003 | 3 | 3 | 3 | 2 | 50 | Section 1 |
| 4 | CL004 | 3 | 4 | 1 | 3 | 40 | Section 2 |
| 5 | CL005 | 4 | 5 | 2 | 5 | 35 | Section 1 |

tblRegistration: (rong)

tblGrade: (rong)

**Kich ban test:**

| Buoc | Hanh dong | Ket qua mong doi |
|------|-----------|------------------|
| 1 | Mo ung dung | Hien thi LoginFrm |
| 2 | Nhap username: "SV2026001", password: "@Stu2026A", nhan Login | Hien thi HomeFrm |
| 3 | Nhan menu "Register" → "Register for new semester" | Hien thi RegisterClassFrm, bang mon hoc hien thi 4 mon, tong TC = 0 |
| 4 | Chon mon "CS101 — Lap trinh Java" (3 TC) | Combobox Class hien thi: CL001 (GV: Tran Thi Mai, Mon 07:00–09:00), CL002 (GV: Le Van Hung, Wed 13:00–15:00) |
| 5 | Chon lop "CL001" | Mon CS101-CL001 them vao bang da chon. Tong TC = 3 |
| 6 | Chon mon "MA101 — Toan cao cap" (4 TC) | Combobox Class hien thi: CL003 (GV: Pham Thi Lan, Tue 09:00–11:00), CL004 (GV: Hoang Van Nam, Wed 13:00–15:00) |
| 7 | Chon lop "CL003" | Mon MA101-CL003 them vao bang da chon. Tong TC = 7 |
| 8 | Chon mon "EN101 — Tieng Anh" (3 TC) | Combobox Class hien thi: CL005 (GV: Nguyen Thi Hoa, Fri 07:00–09:00) |
| 9 | Chon lop "CL005" | Mon EN101-CL005 them vao bang da chon. Tong TC = 10 |
| 9b | **Verify DB truoc Submit:** Truy van tblRegistration WHERE StudentID=1 AND Semester='2026-2' | Ket qua: 0 ban ghi — chua co dang ky nao duoc luu |
| 10 | Nhan nut "Register" | He thong kiem tra: (1) Tong TC = 10, 10 >= 10 va <= 15 ✓. (2) Khong trung gio: Mon 07:00, Tue 09:00, Fri 07:00 — khong trung ✓. (3) Tien quyet: CS101 khong co ✓, MA101 khong co ✓, EN101 khong co ✓. (4) Moi mon 1 lop ✓ |
| 11 | He thong hien thi phieu dang ky | Phieu: Ma SV: SV2026001, Ten SV: Nguyen Van A, Khoa: K65, Hoc ky: 2026-2. Bang: \|CS101\|Lap trinh Java\|3\|Mon 07:00–09:00\|Tran Thi Mai\|, \|MA101\|Toan cao cap\|4\|Tue 09:00–11:00\|Pham Thi Lan\|, \|EN101\|Tieng Anh\|3\|Fri 07:00–09:00\|Nguyen Thi Hoa\| |
| 11b | **Verify DB sau Submit:** Truy van tblRegistration WHERE StudentID=1 AND Semester='2026-2' | Ket qua: 3 ban ghi (ClassID=1, 3, 5) — dang ky da duoc luu thanh cong |

**Database sau khi chay test:**

tblRegistration:
| RegID | StudentID | ClassID | Semester | RegDate | Status |
|-------|-----------|---------|----------|---------|--------|
| 1 | 1 | 1 | 2026-2 | 2026-06-08 | registered |
| 2 | 1 | 3 | 2026-2 | 2026-06-08 | registered |
| 3 | 1 | 5 | 2026-2 | 2026-06-08 | registered |

(Cac bang khac khong thay doi)
