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
| Student | Entity | Doi tuong chinh, co ma SV, ten, khoa hoc |
| Subject | Entity | Mon hoc, co ma mon, ten mon, so tin chi, mon tien quyet |
| Class | Entity | Lop hoc cu the, co ma lop, giao vien, khung gio |
| Registration | Entity | Bang dang ky, lien ket sinh vien voi lop hoc |
| Grade | Entity | Bang diem, dung de kiem tra tien quyet |
| User | Entity | Tai khoan nguoi dung (student) |
| Classroom | Entity | Phong hoc |
| TimeSlot | Entity | Khung gio trong tuan |
| Prerequisite | Relationship | Quan he giua cac mon hoc (mon tien quyet) |
| Semester | Attribute | Thuoc tinh cua Registration |
| Credits | Attribute | Thuoc tinh cua Subject |
| Lecturer | Attribute | Thuoc tinh cua Class (teacherId) |

### Buoc 3: Xac dinh quan he

1. **Subject — Class**: Mot mon hoc co nhieu lop hoc (1-n). Moi Class thuoc ve mot Subject.
2. **Class — Registration**: Mot lop hoc co nhieu dang ky (1-n). Moi Registration lien ket mot Student voi mot Class.
3. **Student — Registration**: Mot sinh vien co nhieu dang ky (1-n). Moi Registration thuoc ve mot Student.
4. **Registration — Grade**: Mot dang ky co mot bang diem (1-1). Moi Grade lien ket voi mot Registration.
5. **Subject — Subject (Prerequisite)**: Quan he tu tham chieu (n-n). Mot mon hoc co the co nhieu mon tien quyet. VD: CS102 yeu cau CS101.
6. **Classroom — Class**: Mot phong hoc duoc su dung cho nhieu lop (1-n).
7. **TimeSlot — Class**: Mot khung gio co nhieu lop (1-n).
8. **User — Student**: Mot User lien ket voi mot Student (1-1).

### Buoc 4: Class Diagram (ASCII art)

```
+------------------+        +------------------+
|    <<entity>>    |        |    <<entity>>    |
|     Subject      |1      *|      Class       |
+------------------+--------+------------------+
| - subjectId: int |        | - classId: int   |
| - subjectCode:   |        | - subjectId: int |
|   String         |        | - classroomId:   |
| - subjectName:   |        |   int            |
|   String         |        | - timeSlotId:    |
| - credits: int   |        |   int            |
| - prerequisiteId:|        | - maxStudents:   |
|   int            |        |   int            |
+------------------+        | - section: String|
        |*                  | - teacherId: int |
        |                   +------------------+
        | 1                        |*
        v                          |1
+------------------+        +------------------+
|    <<entity>>    |        |    <<entity>>    |
|   Subject        |        |   Classroom      |
| (Prerequisite)   |        +------------------+
+------------------+        | - classroomId:   |
| - subjectId: int |        |   int            |
| - requiredSubject|        | - classroomName: |
|   Id: int        |        |   String         |
+------------------+        | - building:      |
                            |   String         |
                            | - capacity: int  |
                            +------------------+
                                    |1
+------------------+                 |
|    <<entity>>    |                 |
|   TimeSlot       |                 |
+------------------+                 |
| - timeSlotId: int|                 |
| - slotName:      |                 |
|   String         |                 |
| - startTime:     |                 |
|   String         |                 |
| - endTime: String|                 |
| - dayOfWeek:     |                 |
|   String         |                 |
+------------------+                 |
        |1                           |
        |                            |
        +----------------------------+
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
              +------------------+        +------------------+
              |    <<entity>>    |        |    <<entity>>    |
              |      Grade       |        |     Student      |
              +------------------+        +------------------+
              | - gradeId: int   |        | - studentId: int |
              | - regId: int     |        | - studentCode:   |
              | - component1:    |        |   String         |
              |   double         |        | - studentName:   |
              | - component2:    |        |   String         |
              |   double         |        | - email: String  |
              | - component3:    |        | - dob: Date      |
              |   double         |        | - major: String  |
              | - examScore:     |        | - userId: int    |
              |   double         |        +------------------+
              | - finalScore:    |                |1
              |   double         |                |
              +------------------+                |1
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
```

### Buoc 5: Bang quan he chi tiet

| Quan he | Kieu | Giai thich |
|---------|------|------------|
| Subject → Class | 1-n | Mot mon hoc co nhieu lop. VD: CS101 co CL001, CL002 |
| Class → Registration | 1-n | Mot lop co nhieu SV dang ky. VD: CL001 co 35 SV |
| Student → Registration | 1-n | Mot SV dang ky nhieu lop. VD: SV2026001 dang ky 3 lop |
| Registration → Grade | 1-1 | Moi dang ky co mot bang diem duy nhat |
| Subject → Subject (Prerequisite) | n-n | Quan he tu tham chieu. VD: CS102 yeu cau CS101, CS201 yeu cau CS101 |
| Classroom → Class | 1-n | Mot phong hoc duoc su dung cho nhieu lop |
| TimeSlot → Class | 1-n | Mot khung gio co nhieu lop |
| User → Student | 1-1 | Mot tai khoan User ung voi mot Student |

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

### Buoc 4: Entity Class Design

```java
// Student.java
public class Student {
    private int studentId;
    private String studentCode;    // "SV2026001"
    private String studentName;    // "Nguyen Van A"
    private String email;          // "nva@gmail.com"
    private Date dob;              // 2004-03-15
    private String major;          // "Computer Science"
    private String course;         // "K65"
    private int userId;            // FK -> tblUser
    // getters, setters
}

// Subject.java
public class Subject {
    private int subjectId;
    private String subjectCode;    // "CS101"
    private String subjectName;    // "Lap trinh Java"
    private int credits;           // 3
    private int prerequisiteId;    // FK -> tblSubject, null if none
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
    private int teacherId;         // FK -> tblUser (teacher)
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
    // getters, setters
}

// TimeSlot.java
public class TimeSlot {
    private int timeSlotId;
    private String slotName;       // "Slot1"
    private String startTime;      // "07:00"
    private String endTime;        // "09:00"
    private String dayOfWeek;      // "Monday"
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
| Major | VARCHAR(50) | |
| Course | VARCHAR(20) | | "K65"
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
| TeacherID | INT | FOREIGN KEY → tblUser(UserID), NOT NULL |
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
3. Tao 8 class: Student, Subject, Class, Registration, Grade, User, Classroom, TimeSlot
4. Voi moi class:
   - Nhap dup vao class → nhap ten class
   - Them attributes: ten: kieu (VD: studentId: int)
   - Chon visibility: private (-) cho tat ca attributes
5. Ve quan he:
   - Subject → Class: Association (1-n)
   - Class → Registration: Association (1-n)
   - Student → Registration: Association (1-n)
   - Registration → Grade: Association (1-1)
   - Classroom → Class: Association (1-n)
   - TimeSlot → Class: Association (1-n)
   - Subject → Subject: Self-association (Prerequisite, n-n)
   - User → Student: Association (1-1)
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
   - Control: RegisterController
   - Entity: Subject, Class, Student, Registration, Grade
4. Ve message flow tu tren xuong theo cac buoc trong bang
5. Them alt fragment cho cac dieu kien: tin chi ngoai pham vi, trung gio, thieu tien quyet
6. Them loop fragment cho viec lap chon mon va lop
7. Export: File → Export → PNG/PDF

### ASCII Sequence Diagram

```
Student    LoginFrm  HomeFrm  RegisterClassFrm  RegisterController  Subject  Class  Registration  Grade
  |           |         |           |                  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | login()   |         |           |                  |                |       |          |          |
  |           |-------->|           |                  |                |       |          |          |
  |           |validate()          |                  |                |       |          |          |
  |           |         |<----------|                  |                |       |          |          |
  |           |         |showHome() |                  |                |       |          |          |
  |<----------|         |           |                  |                |       |          |          |
  | showHome  |         |           |                  |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | clickRegister()     |           |                  |                |       |          |          |
  |           |         |---------->|                  |                |       |          |          |
  |           |         |openRegister()                |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  |           |         |           |----------------->|                |       |          |          |
  |           |         |           |getAllSubjects()   |                |       |          |          |
  |           |         |           |                  |--------------->|       |          |          |
  |           |         |           |                  |querySubjects() |       |          |          |
  |           |         |           |                  |<---------------|       |          |          |
  |           |         |           |<-----------------|                |       |          |          |
  |           |         |           |displaySubjects() |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  | [loop: for each subject to register]               |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectSubject("CS101")          |                  |                |       |          |          |
  |           |         |           |----------------->|                |       |          |          |
  |           |         |           |getClassesBySubject(subjectId)     |       |          |          |
  |           |         |           |                  |--------------->|       |          |          |
  |           |         |           |                  |queryClasses()  |       |          |          |
  |           |         |           |                  |<---------------|       |          |          |
  |           |         |           |<-----------------|                |       |          |          |
  |           |         |           |updateClassCbo()  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectClass("CL001")           |                  |                |       |          |          |
  |           |         |           |addToSelected()   |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectSubject("MA101")         |                  |                |       |          |          |
  |           |         |           |----------------->|                |       |          |          |
  |           |         |           |getClassesBySubject(2)             |       |          |          |
  |           |         |           |<-----------------|                |       |          |          |
  |           |         |           |updateClassCbo()  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectClass("CL003")           |                  |                |       |          |          |
  |           |         |           |addToSelected()   |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectSubject("EN101")         |                  |                |       |          |          |
  |           |         |           |----------------->|                |       |          |          |
  |           |         |           |getClassesBySubject(3)             |       |          |          |
  |           |         |           |<-----------------|                |       |          |          |
  |           |         |           |updateClassCbo()  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | selectClass("CL005")           |                  |                |       |          |          |
  |           |         |           |addToSelected()   |                |       |          |          |
  | [end loop]                     |                  |                |       |          |          |
  |           |         |           |                  |                |       |          |          |
  |---------->|         |           |                  |                |       |          |          |
  | clickRegister()     |           |                  |                |       |          |          |
  |           |         |           |----------------->|                |       |          |          |
  |           |         |           |validateAndRegister()              |       |          |          |
  |           |         |           |                  |--------------->|       |          |          |
  |           |         |           |                  |checkCredits()  |       |          |          |
  |           |         |           |                  | (10 <= 10 <= 15)       |          |          |
  |           |         |           |                  |checkTimeConflict()     |          |          |
  |           |         |           |                  | (no conflict)  |       |          |          |
  |           |         |           |                  |checkPrerequisites()    |          |          |
  |           |         |           |                  |------------->|          |          |
  |           |         |           |                  |queryGrades() |          |          |
  |           |         |           |                  |<-------------|          |          |
  |           |         |           |                  | (prereq met) |          |          |
  |           |         |           |                  |checkOneClassPerSubject()          |
  |           |         |           |                  | (valid)      |          |          |
  |           |         |           |                  |              |          |          |
  |           |         |           |                  |------------->|          |          |
  |           |         |           |                  |insertRegistration()    |          |
  |           |         |           |                  |<-------------|          |          |
  |           |         |           |                  | success      |          |          |
  |           |         |           |<-----------------|                |          |          |
  |           |         |           |showRegistrationForm()             |          |          |
  |<----------|         |           |                  |                |          |          |
  | showForm  |         |           |                  |                |          |          |
  |           |         |           |                  |                |          |          |
  |---------->|         |           |                  |                |          |          |
  | clickPrint()        |           |                  |                |          |          |
  |           |         |           |printForm()       |                |          |          |
  |<----------|         |           |                  |                |          |          |
  | showPDF   |         |           |                  |                |          |          |
```

### Bang giai thich Sequence Diagram

| Buoc | Message | Tu | Den | Mo ta |
|------|---------|-----|-----|--------|
| 1 | login() | Student | LoginFrm | SV nhap username/password va nhan Login |
| 2 | validate() | LoginFrm | HomeFrm | Xac thuc thong tin dang nhap |
| 3 | showHome() | HomeFrm | Student | Hien thi trang chu voi menu |
| 4 | clickRegister() | Student | HomeFrm | SV chon menu Register → Register for new semester |
| 5 | openRegister() | HomeFrm | RegisterClassFrm | Mo form dang ky lop hoc |
| 6 | getAllSubjects() | RegisterClassFrm | RegisterController | Lay tat ca mon hoc hoc ky |
| 7 | querySubjects() | RegisterController | Subject | Truy van database lay mon hoc |
| 8 | subjectList | Subject | RegisterController | Tra ve danh sach mon hoc |
| 9 | displaySubjects() | RegisterController | RegisterClassFrm | Hien thi bang mon hoc |
| 10 | selectSubject("CS101") | Student | RegisterClassFrm | SV chon mon CS101 |
| 11 | getClassesBySubject() | RegisterClassFrm | RegisterController | Lay lop theo mon CS101 |
| 12 | queryClasses() | RegisterController | Class | Truy van database lay lop cua CS101 |
| 13 | classList | Class | RegisterController | Tra ve: CL001, CL002 |
| 14 | updateClassCbo() | RegisterController | RegisterClassFrm | Cap nhat combobox Class |
| 15 | selectClass("CL001") | Student | RegisterClassFrm | SV chon lop CL001 (GV: Tran Thi Mai) |
| 16 | addToSelected() | RegisterClassFrm | RegisterClassFrm | Them CS101-CL001 vao danh sach da chon |
| 17 | selectSubject("MA101") | Student | RegisterClassFrm | SV chon mon MA101 |
| 18 | getClassesBySubject() | RegisterClassFrm | RegisterController | Lay lop theo mon MA101 |
| 19 | queryClasses() | RegisterController | Class | Truy van database |
| 20 | updateClassCbo() | RegisterController | RegisterClassFrm | Cap nhat combobox Class |
| 21 | selectClass("CL003") | Student | RegisterClassFrm | SV chon lop CL003 (GV: Pham Thi Lan) |
| 22 | addToSelected() | RegisterClassFrm | RegisterClassFrm | Them MA101-CL003 vao danh sach |
| 23 | selectSubject("EN101") | Student | RegisterClassFrm | SV chon mon EN101 |
| 24 | getClassesBySubject() | RegisterClassFrm | RegisterController | Lay lop theo mon EN101 |
| 25 | queryClasses() | RegisterController | Class | Truy van database |
| 26 | updateClassCbo() | RegisterController | RegisterClassFrm | Cap nhat combobox Class |
| 27 | selectClass("CL005") | Student | RegisterClassFrm | SV chon lop CL005 (GV: Nguyen Thi Hoa) |
| 28 | addToSelected() | RegisterClassFrm | RegisterClassFrm | Them EN101-CL005 vao danh sach |
| 29 | clickRegister() | Student | RegisterClassFrm | SV nhan nut Register |
| 30 | validateAndRegister() | RegisterClassFrm | RegisterController | Kiem tra va dang ky |
| 31 | checkCredits() | RegisterController | RegisterController | Kiem tra tong tin chi (10 <= 10 <= 15) |
| 32 | checkTimeConflict() | RegisterController | RegisterController | Kiem tra trung khung gio (khong trung) |
| 33 | checkPrerequisites() | RegisterController | Grade | Kiem tra tien quyet (da hoan thanh) |
| 34 | checkOneClassPerSubject() | RegisterController | RegisterController | Kiem tra moi mon 1 lop (hop le) |
| 35 | insertRegistration() | RegisterController | Registration | INSERT INTO tblRegistration (3 ban ghi) |
| 36 | success | Registration | RegisterController | Luu thanh cong |
| 37 | showRegistrationForm() | RegisterController | RegisterClassFrm | Hien thi phieu dang ky |
| 38 | showForm | RegisterClassFrm | Student | SV xem phieu dang ky |
| 39 | clickPrint() | Student | RegisterClassFrm | SV nhan nut Print |
| 40 | printForm() | RegisterClassFrm | RegisterClassFrm | Xuat phieu dang ky ra PDF |
| 41 | showPDF | RegisterClassFrm | Student | Hien thi PDF phieu dang ky |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Muc dich | Input | Expected Output |
|----|---------|-------|-----------------|
| TC01 | Dang ky thanh cong 3 mon (10 TC) | CS101-CL001, MA101-CL003, EN101-CL005 | Dang ky thanh cong, tong = 10 TC, in phieu |
| TC02 | Tong tin chi duoi 10 | Chi dang ky CS101 (3 TC) + EN101 (3 TC) = 6 TC | Loi: "Total credits (6) is below minimum (10)" |
| TC03 | Trung khung gio | CS101-CL001 (Thu 2 07:00) + CS201-CL006 (Thu 2 07:00) | Loi: "Time conflict detected" |
| TC04 | Chua hoan thanh tien quyet | Dang ky CS102 (yeu cau CS101) khi chua hoc CS101 | Loi: "Prerequisite not met" |
| TC05 | Dang ky 2 lop cung 1 mon | CS101-CL001 va CS101-CL002 | Loi: "Already registered for CS101" |

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
| 5 | SV2026001 | @Stu2026A | student |

tblStudent:
| StudentID | StudentCode | StudentName | Email | DOB | Major | Course | UserID |
|-----------|-------------|-------------|-------|-----|-------|--------|--------|
| 1 | SV2026001 | Nguyen Van A | nva@gmail.com | 2004-03-15 | Computer Science | K65 | 5 |

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

tblClass:
| ClassID | SubjectID | TeacherID | ClassroomID | TimeSlotID | MaxStudents | Section |
|---------|-----------|-----------|-------------|------------|-------------|---------|
| 1 | 1 | 2 | 1 | 1 | 40 | Section 1 |
| 2 | 1 | 3 | 2 | 3 | 35 | Section 2 |
| 3 | 3 | 4 | 3 | 2 | 50 | Section 1 |
| 4 | 3 | 4 | 1 | 4 | 40 | Section 2 |
| 5 | 4 | 2 | 2 | 5 | 35 | Section 1 |

tblRegistration: (rong)

tblGrade: (rong)

**Kich ban test:**

| Buoc | Hanh dong | Ket qua mong doi |
|------|-----------|------------------|
| 1 | Mo ung dung | Hien thi LoginFrm |
| 2 | Nhap username: "SV2026001", password: "@Stu2026A", nhan Login | Hien thi HomeFrm |
| 3 | Nhan menu "Register" → "Register for new semester" | Hien thi RegisterClassFrm, bang mon hoc hien thi 4 mon, tong TC = 0 |
| 4 | Chon mon "CS101 — Lap trinh Java" (3 TC) | Combobox Class hien thi: CL001 (GV: Tran Thi Mai, Thu 2 07:00–09:00), CL002 (GV: Le Van Hung, Thu 4 13:00–15:00) |
| 5 | Chon lop "CL001" | Mon CS101-CL001 them vao bang da chon. Tong TC = 3 |
| 6 | Chon mon "MA101 — Toan cao cap" (4 TC) | Combobox Class hien thi: CL003 (GV: Pham Thi Lan, Thu 3 09:00–11:00), CL004 (GV: Hoang Van Nam, Thu 5 07:00–09:00) |
| 7 | Chon lop "CL003" | Mon MA101-CL003 them vao bang da chon. Tong TC = 7 |
| 8 | Chon mon "EN101 — Tieng Anh" (3 TC) | Combobox Class hien thi: CL005 (GV: Nguyen Thi Hoa, Thu 6 07:00–09:00) |
| 9 | Chon lop "CL005" | Mon EN101-CL005 them vao bang da chon. Tong TC = 10 |
| 10 | Nhan nut "Register" | He thong kiem tra: (1) Tong TC = 10, 10 >= 10 va <= 15 ✓. (2) Khong trung gio: Thu 2 07:00, Thu 3 09:00, Thu 6 07:00 — khong trung ✓. (3) Tien quyet: CS101 khong co ✓, MA101 khong co ✓, EN101 khong co ✓. (4) Moi mon 1 lop ✓ |
| 11 | He thong hien thi phieu dang ky | Phieu: Ma SV: SV2026001, Ten SV: Nguyen Van A, Khoa: K65, Hoc ky: 2026-2. Bang: \|CS101\|Lap trinh Java\|3\|Thu 2 07:00–09:00\|Tran Thi Mai\|, \|MA101\|Toan cao cap\|4\|Thu 3 09:00–11:00\|Pham Thi Lan\|, \|EN101\|Tieng Anh\|3\|Thu 6 07:00–09:00\|Nguyen Thi Hoa\| |

**Database sau khi chay test:**

tblRegistration:
| RegID | StudentID | ClassID | Semester | RegDate | Status |
|-------|-----------|---------|----------|---------|--------|
| 1 | 1 | 1 | 2026-2 | 2026-06-08 | registered |
| 2 | 1 | 3 | 2026-2 | 2026-06-08 | registered |
| 3 | 1 | 5 | 2026-2 | 2026-06-08 | registered |

(Cac bang khac khong thay doi)
