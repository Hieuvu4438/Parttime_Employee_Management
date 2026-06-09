# Subject No. 05 — Student Results — Module "Schedule a class"

> **Domain:** Student Results Management
> **Duration:** 90 minutes

---

## Cau 1: Scenario — Xep lich lop hoc (1.5 diem)

### Kich ban chinh — Schedule a Class

| Buoc | Nguoi dung | He thong | Ghi chu |
|------|-----------|----------|---------|
| 1 | Staff Nguyen Van An (NV001) mo ung dung | Man hinh Login xuat hien voi o Username, o Password, nut Login | Giao dien LoginFrm |
| 2 | Nhap username: `staff01`, password: `@Staff2026`, nhan Login | He thong xac thuc thanh cong. Hien thi HomeFrm voi cac menu: Student, Subject, Class, Schedule, Grade, Statistics | Dang nhap thanh cong |
| 3 | Nhan menu "Schedule" → chon "Schedule a Class" | Hien thi ClassSchedulerFrm: combobox Subject (rong), combobox Section (rong), bang danh sach lop hoc rong, nut Add New Class, nut Confirm, nut Cancel | Giao dien lap lich lop hoc |
| 4 | Nhan vao combobox Subject, chon "CS101 — Lap trinh Java" (3 tin chi) | He thong tai danh sach Section theo mon CS101: Section 1 (Thu 2, 07:00–09:00), Section 2 (Thu 4, 13:00–15:00), Section 3 (Thu 7, 07:00–09:00). Combobox Section duoc cap nhat | Combobox Section thay doi |
| 5 | Chon "Section 1 — Thu 2, 07:00–09:00" tu combobox Section | He thong tai danh sach Class cua Section 1: chua co lop nao. Bang danh sach lop hien thi trong | Bang lop trong |
| 6 | Nhan nut "Add New Class" | He thong mo AddClassDialog: combobox Classroom (A101-Suc chua 40, A201-Suc chua 35, B301-Suc chua 50), combobox TimeSlot (Slot1: 07:00–09:00, Slot2: 09:00–11:00, Slot3: 13:00–15:00), o MaxStudents (mac dinh 40), nut OK, nut Cancel | Dialog them lop moi |
| 7 | Chon Classroom "A101 — Suc chua 40", TimeSlot "Slot1: 07:00–09:00", MaxStudents: 40, nhan OK | He thong kiem tra trung lich: A101 Slot1 Thu 2 chua co lop nao → them vao bang danh sach lop tam. Bang hien thi: \|CL001\|CS101\|Sec1\|A101\|Slot1\|40\| | Kiem tra trung lich thanh cong |
| 8 | Nhan nut "Confirm" | He thong xac nhan luu du lieu. Hien thi thong bao: "Schedule saved successfully! Class ID: CL001" | Luu vao database |
| 9 | Nhan OK tren thong bao | He thong quay lai ClassSchedulerFrm, bang danh sach lop hien thi lop CL001 vua tao | Hoan tat |

### Kich ban ngoai le

**Truong hop 1: Trung lich phong hoc**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 7b | Chon Classroom "A101", TimeSlot "Slot1: 07:00–09:00" (da co lop CL002 o Slot1 Thu 2) | He thong kiem tra: A101 Slot1 Thu 2 da co lop CL002 → Hien thi loi: "Conflict detected! Room A101 is already scheduled for Slot1 on Thu 2. Class CL002 occupies this slot." |
| 8b | Nhan OK, chon lai Classroom "A201", TimeSlot "Slot1: 07:00–09:00", nhan OK | He thong kiem tra: A201 Slot1 Thu 2 trong → Them vao bang thanh cong |

**Truong hop 2: Thong tin bat buoc thieu**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 7c | Khong chon Classroom, chi chon TimeSlot, nhan OK | He thong hien thi loi: "Please select a classroom before confirming." |
| 8c | Chon lai Classroom "B301", nhan OK | He thong them lop vao bang thanh cong |

**Truong hop 3: So luong SV vuot suc chua phong**
| Buoc | Nguoi dung | He thong |
|------|-----------|----------|
| 7d | Chon Classroom "A101" (suc chua toi da 40), nhap MaxStudents: 60, nhan OK | He thong hien thi loi: "Max students (60) exceeds room capacity (40). Please enter a number <= 40." |
| 8d | Nhap lai MaxStudents: 35, nhan OK | He thong them lop vao bang thanh cong |

---

## Cau 2: Entity Class Diagram (1.5 diem)

### Buoc 1: Mo ta he thong bang ngon ngu tu nhien

He thong quan ly ket qua sinh vien (Student Results Management) cho phep nhan vien phong dao tao (Staff) lap lich cac lop hoc (Class) cho tung mon hoc (Subject). Moi mon hoc duoc chia thanh nhieu tiet hoc (Section), moi tiet hoc co the co nhieu lop (Class). Moi lop hoc duoc to chuc tai mot phong hoc (Classroom) vao mot khung gio (TimeSlot) cu the trong tuan. Sinh vien (Student) dang ky cac lop hoc, va giao vien (Teacher) nhap diem cho sinh vien trong tung lop. He thong can dam bao khong trung lich phong hoc, so luong sinh vien khong vuot qua suc chua phong, va sinh vien phai hoc du tin chi tien quyet truoc khi dang ky mon hoc.

### Buoc 2: Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Student | Entity | Doi tuong chinh, co ma SV, ten, ngay sinh, khoa hoc, que quan, dia chi, email |
| Subject | Entity | Mon hoc, co ma mon, ten mon, so tin chi, mon tien quyet |
| Class | Entity | Lop hoc cu the, co ma lop, thuoc mon hoc, co si so toi da |
| Registration | Entity | Bang dang ky, lien ket sinh vien voi lop hoc |
| Grade | Entity | Bang diem, luu diem thanh phan va diem thi |
| User | Entity | Tai khoan nguoi dung (staff, teacher, student) |
| Classroom | Entity | Phong hoc, co ma phong, ten phong, suc chua |
| TimeSlot | Entity | Khung gio, co ma slot, gio bat dau, gio ket thuc, ngay trong tuan |
| Section | Attribute | Thuoc tinh cua Class, dai dien tiet hoc (Section 1, 2, 3) |
| Staff | Actor | Nhan vien phong dao tao, thuc hien lap lich |
| Teacher | Actor | Giao vien, nhap diem cho sinh vien |
| Semester | Attribute | Thuoc tinh cua Registration |
| Credits | Attribute | Thuoc tinh cua Subject |
| Prerequisite | Relationship | Quan he giua cac mon hoc (mon tien quyet) |
| Schedule | Relationship | Quan he giua Class va TimeSlot |

### Buoc 3: Xac dinh quan he

1. **Subject — Class**: Mot mon hoc co nhieu lop hoc (1-n). Moi Class thuoc ve mot Subject duy nhat.
2. **Class — Registration**: Mot lop hoc co nhieu dang ky (1-n). Moi Registration lien ket mot Student voi mot Class.
3. **Student — Registration**: Mot sinh vien co nhieu dang ky (1-n). Moi Registration thuoc ve mot Student duy nhat.
4. **Registration — Grade**: Mot dang ky co mot bang diem (1-1). Moi Grade lien ket voi mot Registration.
5. **Classroom — Class**: Mot phong hoc duoc su dung cho nhieu lop (1-n). Moi Class su dung mot Classroom.
6. **TimeSlot — Class**: Mot khung gio co nhieu lop (1-n). Moi Class co mot TimeSlot.
7. **Subject — Subject (Prerequisite)**: Quan he tu tham chieu, mot mon hoc co the co nhieu mon tien quyet (n-n). VD: CS201 yeu cau CS101.

### Buoc 4: Class Diagram (ASCII art)

```
+------------------+        +------------------+        +------------------+        +------------------+
|    <<entity>>    |  1   * |    <<entity>>    |  *   1 |    <<entity>>    |  *   1 |    <<entity>>    |
|     Student      |--------|   Registration   |--------|      Class       |--------|    Subject       |
+------------------+        +------------------+        +------------------+        +------------------+
| - studentId: int |        | - regId: int     |        | - classId: int   |        | - subjectId: int |
| - studentCode:   |        | - studentId: int |        | - subjectId: int |        | - subjectCode:   |
|   String         |        | - classId: int   |        | - classroomId:   |        |   String         |
| - studentName:   |        | - semester:      |        |   int            |        | - subjectName:   |
|   String         |        |   String         |        | - timeSlotId:    |        |   String         |
| - dob: Date      |        | - regDate: Date  |        |   int            |        | - credits: int   |
| - course: String |        | - status: String |        | - maxStudents:   |        | - prerequisiteId:|
| - hometown:      |        +------------------+        |   int            |        |   int            |
|   String         |                |1                  | - section: String|        +------------------+
| - address:       |                |1                  | - teacherId: int |               |*
|   String         |                v                   +------------------+               |1
| - email: String  |        +------------------+               |*                          v
| - userId: int    |        |    <<entity>>    |               |1                 +------------------+
+------------------+        |      Grade       |               v                 |    <<entity>>    |
                            +------------------+        +------------------+        |   Classroom      |
                            | - gradeId: int   |        |    <<entity>>    |        +------------------+
                            | - regId: int     |        |    TimeSlot       |        | - classroomId:   |
                            | - component1:    |        +------------------+        |   int            |
                            |   double         |        | - timeSlotId: int|        | - classroomName: |
                            | - component2:    |        | - slotName:      |        |   String         |
                            |   double         |        |   String         |        | - building:      |
                            | - component3:    |        | - startTime:     |        |   String         |
                            |   double         |        |   String         |        | - capacity: int  |
                            | - examScore:     |        | - endTime: String|        +------------------+
                            |   double         |        | - dayOfWeek:     |
                            | - finalScore:    |        |   String         |
                            |   double         |        +------------------+
                            +------------------+

+------------------+
|    <<entity>>    |
|      User        |
+------------------+
| - userId: int    |
| - username:      |
|   String         |
| - password:      |
|   String         |
| - role: String   |
+------------------+

Ghi chu: Subject co quan he tu tham chieu (self-reference) de the hien mon tien quyet.
Subject.prerequisiteId → Subject.subjectId (FK, NULL allowed).
```

### Buoc 5: Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|---------|------|------------|
| Subject → Class | 1-n | Mot mon hoc co nhieu lop hoc. VD: CS101 co 3 lop CL001, CL002, CL003 |
| Class → Registration | 1-n | Mot lop hoc co nhieu SV dang ky. VD: CL001 co 35 SV dang ky |
| Student → Registration | 1-n | Mot SV dang ky nhieu lop. VD: SV001 dang ky 5 lop trong hoc ky |
| Registration → Grade | 1-1 | Moi dang ky co mot bang diem duy nhat. VD: Reg001 co Grade001 |
| Classroom → Class | 1-n | Mot phong hoc duoc su dung cho nhieu lop. VD: A101 co 3 lop/tuan |
| TimeSlot → Class | 1-n | Mot khung gio co nhieu lop. VD: Slot1 (07:00–09:00) co 5 lop |
| Subject → Subject (Prerequisite) | n-n | Quan he tu tham chieu. VD: CS201 yeu cau CS101, CS202 yeu cau CS101 va CS102 |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

**1. Các bước vẽ tổng quan:**

| Bước | Thao tác | Mô tả |
|------|----------|-------|
| 1 | Mở Visual Paradigm → New → Class Diagram | Tạo diagram mới, đặt tên "Student_ScheduleClass" |
| 2 | Tạo entity class boxes | Kéo "Class" từ toolbar vào canvas, tạo 8 class: Student, Subject, Class, Registration, Grade, User, Classroom, TimeSlot |
| 3 | Tạo view class boxes | Kéo "Boundary" vào canvas, tạo: LoginFrm, HomeFrm, ClassSchedulerFrm, AddClassDialog |
| 4 | Vẽ relationships | Kéo đường kết nối giữa các class theo bảng quan hệ |
| 5 | Thêm multiplicities | Click vào đường kết nối → Properties → đặt Source/Target Multiplicity |

**2. Cấu trúc 1 class box (3 ngăn):**

Mỗi class trong Visual Paradigm là hình chữ nhật chia 3 ngăn:

| Ngăn | Nội dung | Ví dụ (class Class) |
|------|----------|----------------------|
| Ngăn 1 — Tên class | Stereotype + tên class | `<<entity>> Class` |
| Ngăn 2 — Thuộc tính | `-attributeName: Type` | `-classId: int`, `-subjectId: int`, `-classroomId: int`, `-timeSlotId: int`, `-maxStudents: int`, `-section: String`, `-teacherId: int` |
| Ngăn 3 — Phương thức | `+methodName(params): ReturnType` | `+getClassesBySubjectSection(subjectId: int, section: String): List<Class>` |

Stereotype sử dụng: `<<entity>>` cho entity class, `<<boundary>>` cho view class (Frm), `<<control>>` cho DAO class.

**3. Bảng chi tiết từng entity class:**

| Class | Stereotype | Thuộc tính (Ngăn 2) | Phương thức (Ngăn 3) |
|-------|-----------|----------------------|----------------------|
| Student | `<<entity>>` | `-studentId: int`, `-studentCode: String`, `-studentName: String`, `-dob: Date`, `-course: String`, `-hometown: String`, `-address: String`, `-email: String`, `-userId: int` | `+getStudentById(id: int): Student` |
| Subject | `<<entity>>` | `-subjectId: int`, `-subjectCode: String`, `-subjectName: String`, `-credits: int`, `-prerequisiteId: int` | `+getSectionsBySubject(subjectId: int): List<String>`, `+getAllSubjects(): List<Subject>` |
| Class | `<<entity>>` | `-classId: int`, `-subjectId: int`, `-classroomId: int`, `-timeSlotId: int`, `-maxStudents: int`, `-section: String`, `-teacherId: int` | `+getClassesBySubjectSection(subjectId: int, section: String): List<Class>` |
| Registration | `<<entity>>` | `-regId: int`, `-studentId: int`, `-classId: int`, `-semester: String`, `-regDate: Date`, `-status: String` | `+addRegistration(reg: Registration): boolean` |
| Grade | `<<entity>>` | `-gradeId: int`, `-regId: int`, `-component1: float`, `-component2: float`, `-component3: float`, `-examScore: float`, `-finalScore: float` | `+calculateFinalScore(): float` |
| User | `<<entity>>` | `-userId: int`, `-username: String`, `-password: String`, `-role: String` | `+validateLogin(username: String, password: String): boolean` |
| Classroom | `<<entity>>` | `-classroomId: int`, `-classroomName: String`, `-building: String`, `-capacity: int` | `+getAllClassrooms(): List<Classroom>` |
| TimeSlot | `<<entity>>` | `-timeSlotId: int`, `-slotName: String`, `-startTime: Time`, `-endTime: Time`, `-dayOfWeek: String` | `+getAllTimeSlots(): List<TimeSlot>` |

**4. Bảng chi tiết view classes (nếu có):**

| View Class | Stereotype | UI Elements |
|------------|-----------|-------------|
| LoginFrm | `<<boundary>>` | `-inUsername: JTextField`, `-inPassword: JPasswordField`, `-subLogin: JButton` |
| HomeFrm | `<<boundary>>` | `-menuSchedule: JMenuItem`, `-menuStudent: JMenuItem`, `-menuSubject: JMenuItem`, `-menuGrade: JMenuItem`, `-subLogout: JButton` |
| ClassSchedulerFrm | `<<boundary>>` | `-inSubject: JComboBox`, `-inSection: JComboBox`, `-tblSchedule: JTable`, `-subAddClass: JButton`, `-subConfirm: JButton`, `-subCancel: JButton`, `-subRefresh: JButton` |
| AddClassDialog | `<<boundary>>` | `-inClassroom: JComboBox`, `-inTimeSlot: JComboBox`, `-inMaxStudents: JTextField`, `-subOK: JButton`, `-subCancel: JButton` |

Quy ước đặt tên UI elements: `in` = nhập liệu, `out` = hiển thị, `sub` = nút bấm, `outsub` = bảng click được.

**5. Cách vẽ quan hệ:**

| Kiểu quan hệ | Ký hiệu Visual Paradigm | Khi nào dùng |
|---------------|--------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng (▷) | Quan hệ tham chiếu thông thường (Student → Registration) |
| **Aggregation** | Đường liền nét, đầu kim cương rỗng (◇) | "Contain" nhưng child có thể tồn tại độc lập |
| **Composition** | Đường liền nét, đầu kim cương filled (◆) | "Contain" nhưng child KHÔNG tồn tại nếu không có parent |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng (▷) | "Sử dụng" tạm thời (ClassSchedulerFrm → ClassDAO) |

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
| Classroom | Class | Association | 1 → * | Mỗi phòng học được dùng cho nhiều lớp |
| TimeSlot | Class | Association | 1 → * | Mỗi khung giờ có nhiều lớp |
| Subject | Subject | Association (self-ref) | * → * | Quan hệ môn tiên quyết |

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
  Tên: validateLogin()
  Đầu vào: username, password (thuộc class User)
  Đầu ra: boolean
  Gán cho entity class: User.

Đăng nhập thành công → Giao diện Home xuất hiện → cần class: HomeFrm
  Menu Schedule → menuSchedule
  Menu Student → menuStudent
  Menu Subject → menuSubject
  Menu Grade → menuGrade
  Nút Logout → subLogout

Staff chọn Schedule → Giao diện lập lịch lớp học xuất hiện → cần class: ClassSchedulerFrm
  Combobox chọn môn học → inSubject
  Combobox chọn tiet hoc (Section) → inSection
  Bảng danh sách lop hoc → tblSchedule
  Nút Add New Class → subAddClass
  Nút Confirm → subConfirm
  Nút Cancel → subCancel
  Nút Refresh → subRefresh

Staff chọn môn học → Hệ thống tải danh sách Section → cần phương thức:
  Tên: getSectionsBySubject()
  Đầu vào: subjectId (int)
  Đầu ra: List<String>
  Gán cho entity class: Subject.

Staff chọn Section → Hệ thống tải danh sách lớp → cần phương thức:
  Tên: getClassesBySubjectSection()
  Đầu vào: subjectId (int), section (String)
  Đầu ra: List<Class>
  Gán cho entity class: Class.

Staff nhấn Add New Class → Dialog thêm lớp xuất hiện → cần class: AddClassDialog
  Combobox chọn phòng học → inClassroom
  Combobox chọn khung giờ → inTimeSlot
  Ô nhập sĩ số tối đa → inMaxStudents
  Nút OK → subOK
  Nút Cancel → subCancel

Staff chọn phòng, khung giờ, nhấn OK → Hệ thống kiểm tra trùng lịch → cần phương thức:
  Tên: checkConflict()
  Đầu vào: classroomId (int), timeSlotId (int)
  Đầu ra: boolean
  Gán cho entity class: Class.

Staff nhấn Confirm → Hệ thống lưu lớp học → cần phương thức:
  Tên: insertClass()
  Đầu vào: cls (Class)
  Đầu ra: boolean
  Gán cho entity class: Class.

### Tóm tắt
View classes: LoginFrm, HomeFrm, ClassSchedulerFrm, AddClassDialog
Phương thức: validateLogin(), getSectionsBySubject(), getClassesBySubjectSection(), checkConflict(), insertClass()

---

## Cau 3: Thiet ke tinh (1.5 diem)

### Buoc 1: View Class

| View Class | Mo ta |
|-----------|--------|
| LoginFrm | Form dang nhap, cho phep nguoi dung nhap username/password de truy cap he thong |
| HomeFrm | Form trang chu, hien thi menu dieu huong cho staff, teacher, student |
| ClassSchedulerFrm | Form lap lich lop hoc, cho phep staff chon mon hoc, tiet hoc, them lop moi voi phong va khung gio |
| AddClassDialog | Dialog them lop moi, cho phep chon phong hoc, khung gio, nhap si so toi da |

### Buoc 2: UI Elements

**LoginFrm**
- `inUsername`: JTextField — O nhap ten dang nhap
- `inPassword`: JPasswordField — O nhap mat khau
- `subLogin`: JButton — Nut dang nhap
- `subCancel`: JButton — Nut huy

**HomeFrm**
- `menuSchedule`: JMenuItem — Menu "Schedule"
- `menuStudent`: JMenuItem — Menu "Student"
- `menuSubject`: JMenuItem — Menu "Subject"
- `menuGrade`: JMenuItem — Menu "Grade"
- `subLogout`: JButton — Nut dang xuat

**ClassSchedulerFrm**
- `inSubject`: JComboBox — Combobox chon mon hoc
- `inSection`: JComboBox — Combobox chon tiet hoc (Section)
- `tblSchedule`: JTable — Bang hien thi danh sach lop da lap lich
- `subAddClass`: JButton — Nut them lop moi
- `subConfirm`: JButton — Nut xac nhan luu
- `subCancel`: JButton — Nut huy
- `subRefresh`: JButton — Nut lam moi danh sach

**AddClassDialog**
- `inClassroom`: JComboBox — Combobox chon phong hoc
- `inTimeSlot`: JComboBox — Combobox chon khung gio
- `inMaxStudents`: JTextField — O nhap si so toi da
- `subOK`: JButton — Nut xac nhan
- `subCancel`: JButton — Nut huy

### Buoc 3: DAO Class

| DAO Class | Phuong thuc | Mo ta |
|-----------|-------------|-------|
| SubjectDAO | `List<Subject> getAll()` | Lay danh sach tat ca mon hoc |
| SubjectDAO | `Subject getById(int subjectId)` | Lay mon hoc theo ma |
| SubjectDAO | `List<Subject> getByKeyword(String keyword)` | Tim mon hoc theo tu khoa |
| ClassDAO | `List<Class> getBySubject(int subjectId)` | Lay danh sach lop theo mon hoc |
| ClassDAO | `Class getById(int classId)` | Lay lop hoc theo ma |
| ClassDAO | `boolean insert(Class cls)` | Them lop hoc moi |
| ClassDAO | `boolean update(Class cls)` | Cap nhat lop hoc |
| ClassDAO | `boolean delete(int classId)` | Xoa lop hoc |
| ClassroomDAO | `List<Classroom> getAll()` | Lay danh sach tat ca phong hoc |
| ClassroomDAO | `Classroom getById(int classroomId)` | Lay phong hoc theo ma |
| TimeSlotDAO | `List<TimeSlot> getAll()` | Lay danh sach tat ca khung gio |
| TimeSlotDAO | `TimeSlot getById(int timeSlotId)` | Lay khung gio theo ma |
| RegistrationDAO | `List<Registration> getByClass(int classId)` | Lay danh sach dang ky theo lop |
| RegistrationDAO | `boolean insert(Registration reg)` | Them dang ky moi |
| GradeDAO | `Grade getByRegistration(int regId)` | Lay diem theo dang ky |
| GradeDAO | `boolean insert(Grade grade)` | Them diem moi |
| UserDAO | `User getByUsername(String username)` | Lay nguoi dung theo ten dang nhap |
| UserDAO | `boolean validate(String username, String password)` | Xac thuc dang nhap |

### Buoc 4: Entity Class Design

```java
// Student.java
public class Student {
    private int studentId;
    private String studentCode;    // "SV2026001"
    private String studentName;    // "Nguyen Van A"
    private String email;          // "nva@gmail.com"
    private Date dob;              // 2004-03-15
    private String course;         // "Computer Science"
    private String hometown;       // "Ha Noi"
    private String address;        // "123 Main St"
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
    private int subjectId;         // FK -> tblSubject
    private int classroomId;       // FK -> tblClassroom
    private int timeSlotId;        // FK -> tblTimeSlot
    private int maxStudents;       // 40
    private String section;        // "Section 1"
    private int teacherId;         // FK -> tblTeacher
    private Subject subject;       // object attribute
    private Classroom classroom;   // object attribute
    private TimeSlot timeSlot;     // object attribute
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
    private double finalScore;     // calculated
    private Registration registration; // object attribute
    // getters, setters
}

// User.java
public class User {
    private int userId;
    private String username;       // "staff01"
    private String password;       // "@Staff2026"
    private String role;           // "staff", "teacher", "student"
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
| Email | VARCHAR(100) | UNIQUE |
| DOB | DATE | |
| Course | VARCHAR(50) | |
| Hometown | VARCHAR(100) | |
| Address | VARCHAR(200) | |
| Major | VARCHAR(50) | |
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

**tblClass**
| Column | Type | Constraint |
|--------|------|-----------|
| ClassID | INT | PRIMARY KEY, AUTO_INCREMENT |
| SubjectID | INT | FOREIGN KEY → tblSubject(SubjectID), NOT NULL |
| ClassroomID | INT | FOREIGN KEY → tblClassroom(ClassroomID), NOT NULL |
| TimeSlotID | INT | FOREIGN KEY → tblTimeSlot(TimeSlotID), NOT NULL |
| MaxStudents | INT | NOT NULL, CHECK (MaxStudents > 0) |
| Section | VARCHAR(20) | NOT NULL |
| TeacherID | INT | FOREIGN KEY → tblUser(UserID), NULL allowed |

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

1. Mo Visual Paradigm → File → New Project → dat ten "StudentResults_ClassDiagram"
2. Chon Diagram → Class Diagram
3. Tao 8 class: Student, Subject, Class, Registration, Grade, User, Classroom, TimeSlot
4. Voi moi class:
   - Nhap dup vao class → nhap ten class
   - Them attributes: ten: kieu (VD: studentId: int)
   - Chon visibility: private (-) cho tat ca attributes
5. Ve quan he:
   - Subject → Class: Association (1-n), keo line tu Subject sang Class
   - Class → Registration: Association (1-n)
   - Student → Registration: Association (1-n)
   - Registration → Grade: Association (1-1)
   - Classroom → Class: Association (1-n)
   - TimeSlot → Class: Association (1-n)
   - Subject → Subject: Self-association (Prerequisite)
6. Dat multiplicity: nhap vao Association → chon multiplicity o moi dau
7. Them `<<entity>>` stereotype cho moi class
8. Export: File → Export → PNG/PDF

---

## Cau 4: Sequence Diagram (1.5 diem)

### Huong dan ve bang Visual Paradigm

1. Mo Visual Paradigm → File → New → Sequence Diagram
2. Dat ten: "ScheduleClass_SequenceDiagram"
3. Tao lifelines:
   - Actor: Staff
   - Boundary: LoginFrm, HomeFrm, ClassSchedulerFrm, AddClassDialog
   - Control: UserDAO, ScheduleController
   - Entity: Subject, Class, Classroom, TimeSlot
4. Ve message flow tu tren xuong theo cac buoc trong bang
5. Them alt fragment cho dieu kien (kiem tra trung lich, thong tin thieu)
6. Them loop fragment cho viec lap chon mon/tiet
7. Export: File → Export → PNG/PDF

### ASCII Sequence Diagram

```
Staff     LoginFrm   UserDAO    HomeFrm  ClassSchedulerFrm  ScheduleController  Subject  Class  Classroom  TimeSlot
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | login()  |          |          |            |                |               |        |        |          |
  |          |--------->|          |            |                |               |        |        |          |
  |          |validate()|          |            |                |               |        |        |          |
  |          |          |---query DB            |                |               |        |        |          |
  |          |          |<-return--|            |                |               |        |        |          |
  |          |<--true---|          |            |                |               |        |        |          |
  |          |--------->|----------|----------->|                |               |        |        |          |
  |          |          |  openHome |           |                |               |        |        |          |
  |<---------|          |          |            |                |               |        |        |          |
  | showHome |          |          |            |                |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | clickSchedule()     |          |            |                |               |        |        |          |
  |          |          |          |----------->|                |               |        |        |          |
  |          |          |          |openScheduler               |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | selectSubject("CS101")        |            |                |               |        |        |          |
  |          |          |          |            |--------------->|               |        |        |          |
  |          |          |          |            |getSections()   |               |        |        |          |
  |          |          |          |            |                |-------------->|        |        |          |
  |          |          |          |            |                |querySections()|-------->|        |          |
  |          |          |          |            |                |<--------------|        |        |          |
  |          |          |          |            |<---------------|               |        |        |          |
  |          |          |          |            |updateSection() |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | selectSection("Sec1")         |            |                |               |        |        |          |
  |          |          |          |            |--------------->|               |        |        |          |
  |          |          |          |            |getClasses()    |               |        |        |          |
  |          |          |          |            |                |-------------->|        |        |          |
  |          |          |          |            |                |queryClasses() |-------->|        |          |
  |          |          |          |            |                |<--------------|        |        |          |
  |          |          |          |            |<---------------|               |        |        |          |
  |          |          |          |            |updateTable()   |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | clickAdd()          |          |            |                |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  | selectClassroom("A101")       |            |                |               |        |        |          |
  | selectTimeSlot("Slot1")       |            |                |               |        |        |          |
  | enterMaxStudents(40)          |            |                |               |        |        |          |
  | clickOK()           |          |            |                |               |        |        |          |
  |          |          |          |            |--------------->|               |        |        |          |
  |          |          |          |            |validateConflict               |        |        |          |
  |          |          |          |            |                |-------------->|        |        |          |
  |          |          |          |            |                |checkConflict()|-------->|        |          |
  |          |          |          |            |                |<--------------|        |        |          |
  |          |          |          |            |                | noConflict    |        |        |          |
  |          |          |          |            |<---------------|               |        |        |          |
  |          |          |          |            | valid          |               |        |        |          |
  |          |          |          |            |addToTable()    |               |        |        |          |
  |          |          |          |            |                |               |        |        |          |
  |--------->|          |          |            |                |               |        |        |          |
  | clickConfirm()      |          |            |                |               |        |        |          |
  |          |          |          |            |--------------->|               |        |        |          |
  |          |          |          |            |confirmSchedule()               |        |        |          |
  |          |          |          |            |                |-------------->|        |        |          |
  |          |          |          |            |                |saveSchedule() |-------->|        |          |
  |          |          |          |            |                |insertClass()  |        |        |          |
  |          |          |          |            |                |<--------------|        |        |          |
  |          |          |          |            |<---------------|               |        |        |          |
  |          |          |          |            |showSuccess()   |               |        |        |          |
  |<---------|          |          |            |                |               |        |        |          |
  | showMsg  |          |          |            |                |               |        |        |          |
```

### Bang giai thich Sequence Diagram

| Buoc | Message | Tu | Den | Mo ta |
|------|---------|-----|-----|--------|
| 1 | login() | Staff | LoginFrm | Staff nhap username/password va nhan Login |
| 2 | validateLogin() | LoginFrm | UserDAO | Goi UserDAO.validateLogin("staff01", "@Staff2026") |
| 3 | query DB | UserDAO | Database | Truy van tblUser kiem tra thong tin dang nhap |
| 4 | return true | UserDAO | LoginFrm | Tra ve true neu dang nhap hop le |
| 5 | openHome() | LoginFrm | HomeFrm | Mo giao dien trang chu |
| 6 | showHome() | HomeFrm | Staff | Hien thi menu: Student, Subject, Class, Schedule, Grade |
| 7 | clickSchedule() | Staff | HomeFrm | Staff chon menu Schedule → Schedule a Class |
| 8 | openScheduler() | HomeFrm | ClassSchedulerFrm | Mo form lap lich lop hoc |
| 9 | selectSubject() | Staff | ClassSchedulerFrm | Staff chon mon hoc "CS101" tu combobox Subject |
| 10 | getSections() | ClassSchedulerFrm | ScheduleController | Goi controller lay danh sach tiet hoc theo mon |
| 11 | querySections() | ScheduleController | Subject | Truy van database lay tiet hoc theo subjectId |
| 12 | return sectionList | Subject | ScheduleController | Tra ve danh sach tiet hoc |
| 13 | return sections | ScheduleController | ClassSchedulerFrm | Tra ve danh sach Section cho combobox |
| 14 | updateSection() | ClassSchedulerFrm | UI | Cap nhat combobox Section |
| 15 | selectSection() | Staff | ClassSchedulerFrm | Staff chon "Section 1" tu combobox Section |
| 16 | getClasses() | ClassSchedulerFrm | ScheduleController | Goi controller lay danh sach lop theo mon va tiet |
| 17 | queryClasses() | ScheduleController | Class | Truy van database lay lop theo subjectId + section |
| 18 | return classList | Class | ScheduleController | Tra ve danh sach lop |
| 19 | return classes | ScheduleController | ClassSchedulerFrm | Tra ve danh sach lop cho bang hien thi |
| 20 | updateTable() | ClassSchedulerFrm | UI | Cap nhat bang hien thi danh sach lop |
| 21 | clickAdd() | Staff | ClassSchedulerFrm | Staff nhan nut "Add New Class" |
| 22 | showDialog() | ClassSchedulerFrm | AddClassDialog | Mo dialog them lop moi |
| 23 | selectClassroom() | Staff | AddClassDialog | Staff chon phong hoc "A101" tu combobox |
| 24 | selectTimeSlot() | Staff | AddClassDialog | Staff chon khung gio "Slot1" tu combobox |
| 25 | enterMaxStudents() | Staff | AddClassDialog | Staff nhap si so toi da: 40 |
| 26 | clickOK() | Staff | AddClassDialog | Staff nhan OK de xac nhan |
| 27 | validateConflict() | AddClassDialog | ScheduleController | Gui thong tin phong + khung gio de kiem tra trung lich |
| 28 | checkConflict() | ScheduleController | Class | Truy van database kiem tra xung dot lich phong |
| 29 | return noConflict | Class | ScheduleController | Phong trong, khong trung lich |
| 30 | return valid | ScheduleController | AddClassDialog | Du lieu hop le |
| 31 | addToTable() | AddClassDialog | ClassSchedulerFrm | Them lop moi vao bang danh sach |
| 32 | clickConfirm() | Staff | ClassSchedulerFrm | Staff nhan nut "Confirm" de luu vao database |
| 33 | confirmSchedule() | ClassSchedulerFrm | ScheduleController | Gui yeu cau luu lich lop hoc |
| 34 | saveSchedule() | ScheduleController | Class | Goi Class.insertClass() de luu vao tblClass |
| 35 | insertClass() | ScheduleController | Database | INSERT INTO tblClass |
| 36 | return success | Class | ScheduleController | Luu thanh cong |
| 37 | return success | ScheduleController | ClassSchedulerFrm | Tra ve ket qua thanh cong |
| 38 | showSuccess() | ClassSchedulerFrm | Staff | Hien thi "Schedule saved successfully! Class ID: 1" |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| STT | Module | Test case |
|-----|--------|-----------|
| 1 | Schedule a Class | Lap lich lop hoc thanh cong voi mon CS101, phong A101, Slot1 |
| 2 | Schedule a Class | Trung lich phong hoc (phong A101 da co lop o Slot1) |
| 3 | Schedule a Class | Thieu thong tin bat buoc (khong chon Classroom) |
| 4 | Schedule a Class | Si so vuot suc chua phong (MaxStudents > Capacity) |
| 5 | Schedule a Class | Lap lich nhieu lop cho cung mon voi khung gio khac nhau |

### TC01: Lap lich lop hoc thanh cong — Chi tiet

**Muc dich:** Kiem tra staff co the lap lich lop hoc moi cho mon CS101 voi day du thong tin hop le.

**Database truoc khi chay test:**

tblUser:
| UserID | Username | Password | Role |
|--------|----------|----------|------|
| 1 | staff01 | @Staff2026 | staff |
| 2 | teacher01 | @Teach2026 | teacher |
| 3 | SV2026001 | @Stu2026 | student |

tblSubject:
| SubjectID | SubjectCode | SubjectName | Credits | PrerequisiteID |
|-----------|-------------|-------------|---------|---------------|
| 1 | CS101 | Lap trinh Java | 3 | NULL |
| 2 | CS102 | Cau truc du lieu | 3 | 1 |
| 3 | MA101 | Toan cao cap | 4 | NULL |

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
| 2 | Slot2 | 09:00 | 11:00 | Monday |
| 3 | Slot3 | 13:00 | 15:00 | Wednesday |

tblStudent:
| StudentID | StudentCode | StudentName | Email | DOB | Course | Hometown | Address | UserID |
|-----------|-------------|-------------|-------|-----|--------|----------|---------|--------|
| 1 | SV2026001 | Nguyen Van A | nva@gmail.com | 2004-03-15 | Computer Science | Ha Noi | 123 Main St | 3 |

tblClass: (rong)

tblRegistration: (rong)

tblGrade: (rong)

**Kich ban test:**

| Buoc | Hanh dong | Ket qua mong doi |
|------|-----------|------------------|
| 1 | Mo ung dung | Hien thi LoginFrm |
| 2 | Nhap username: "staff01", password: "@Staff2026", nhan Login | Hien thi HomeFrm |
| 3 | Nhan menu "Schedule" → "Schedule a Class" | Hien thi ClassSchedulerFrm, combobox Subject rong |
| 4 | Chon Subject "CS101 — Lap trinh Java" tu combobox | Combobox Section cap nhat: Section 1 (Slot1, Monday), Section 2 (Slot3, Wednesday) |
| 5 | Chon Section "Section 1 — Slot1, Monday" | Bang danh sach lop hien thi rong (chua co lop cho Section 1) |
| 6 | Nhan nut "Add New Class" | Hien thi AddClassDialog |
| 7 | Chon Classroom "A101", TimeSlot "Slot1 (07:00–09:00, Monday)", nhap MaxStudents: 40, nhan OK | Dialog dong, lop moi hien thi trong bang: ClassID=1, CS101, Sec1, A101, Slot1, 40 |
| 8 | Kiem tra tblClass trong database | tblClass van rong — chua luu vao database (chi them vao bang tam tren UI) |
| 9 | Nhan nut "Confirm" | He thong luu vao database. Hien thi thong bao: "Schedule saved successfully! Class ID: 1" |
| 10 | Nhan OK | Quay lai ClassSchedulerFrm, bang hien thi lop vua tao |

**Database sau khi chay test:**

tblClass:
| ClassID | SubjectID | ClassroomID | TimeSlotID | MaxStudents | Section | TeacherID |
|---------|-----------|-------------|------------|-------------|---------|-----------|
| 1 | 1 | 1 | 1 | 40 | Section 1 | NULL |

Cac bang khac khong thay doi:
- tblUser: khong thay doi
- tblStudent: khong thay doi
- tblSubject: khong thay doi
- tblClassroom: khong thay doi
- tblTimeSlot: khong thay doi
- tblRegistration: khong thay doi
- tblGrade: khong thay doi
