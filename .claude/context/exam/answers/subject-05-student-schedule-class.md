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
| 7b | Chon Classroom "A101", TimeSlot "Slot1: 07:00–09:00" (da co lop CL002 o Slot1 Thu 2) | He thong kiem tra: A101 Slot1 Thu 2 da co lop CL002 → Hien thi loi: "Conflict detected! Room A101 is already scheduled for Slot1 on Monday. Class CL002 occupies this slot." |
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
| Student | Entity | Doi tuong chinh, co ma SV, ten, ngay sinh, email, khoa hoc |
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
| Subject → Class | 1-n | Mot mon hoc co nhieu lop hoc. VD: CS101 co 3 lop CL001, CL002, CL003 |
| Class → Registration | 1-n | Mot lop hoc co nhieu SV dang ky. VD: CL001 co 35 SV dang ky |
| Student → Registration | 1-n | Mot SV dang ky nhieu lop. VD: SV001 dang ky 5 lop trong hoc ky |
| Registration → Grade | 1-1 | Moi dang ky co mot bang diem duy nhat. VD: Reg001 co Grade001 |
| Classroom → Class | 1-n | Mot phong hoc duoc su dung cho nhieu lop. VD: A101 co 3 lop/tuan |
| TimeSlot → Class | 1-n | Mot khung gio co nhieu lop. VD: Slot1 (07:00–09:00) co 5 lop |
| Subject → Subject (Prerequisite) | n-n | Quan he tu tham chieu. VD: CS201 yeu cau CS101, CS202 yeu cau CS101 va CS102 |

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
    private String major;          // "Computer Science"
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
    private int teacherId;         // FK -> tblTeacher
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
| TeacherID | INT | FOREIGN KEY → tblTeacher(TeacherID) |

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
   - Control: ScheduleController
   - Entity: Subject, Class, Classroom, TimeSlot
4. Ve message flow tu tren xuong theo cac buoc trong bang
5. Them alt fragment cho dieu kien (kiem tra trung lich, thong tin thieu)
6. Them loop fragment cho viec lap chon mon/tiet
7. Export: File → Export → PNG/PDF

### ASCII Sequence Diagram

```
Staff     LoginFrm   HomeFrm  ClassSchedulerFrm  AddClassDialog  ScheduleController  Subject  Class  Classroom  TimeSlot
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | login()  |          |            |                |                |               |        |        |          |
  |          |--------->|            |                |                |               |        |        |          |
  |          |validate()|            |                |                |               |        |        |          |
  |          |          |<-----------|                |                |               |        |        |          |
  |          |          | showHome() |                |                |               |        |        |          |
  |<---------|          |            |                |                |               |        |        |          |
  | showHome |          |            |                |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | clickSchedule()     |            |                |                |               |        |        |          |
  |          |          |----------->|                |                |               |        |        |          |
  |          |          |openScheduler               |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | selectSubject("CS101")          |                |                |               |        |        |          |
  |          |          |            |--------------->|                |               |        |        |          |
  |          |          |            |getSections()   |                |               |        |        |          |
  |          |          |            |                |--------------->|               |        |        |          |
  |          |          |            |                |querySections() |-------------->|        |        |          |
  |          |          |            |                |                |<--------------|        |        |          |
  |          |          |            |                |<---------------|               |        |        |          |
  |          |          |            |<---------------|                |               |        |        |          |
  |          |          |            |updateSection() |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | selectSection("Sec1")           |                |                |               |        |        |          |
  |          |          |            |--------------->|                |               |        |        |          |
  |          |          |            |getClasses()    |                |               |        |        |          |
  |          |          |            |                |--------------->|               |        |        |          |
  |          |          |            |                |queryClasses()  |-------------->|        |        |          |
  |          |          |            |                |                |<--------------|        |        |          |
  |          |          |            |                |<---------------|               |        |        |          |
  |          |          |            |<---------------|                |               |        |        |          |
  |          |          |            |updateTable()   |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | clickAdd()          |            |                |                |               |        |        |          |
  |          |          |            |--------------->|                |               |        |        |          |
  |          |          |            |showDialog()    |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | selectClassroom("A101")         |                |                |               |        |        |          |
  | selectTimeSlot("Slot1")         |                |                |               |        |        |          |
  | enterMaxStudents(40)            |                |                |               |        |        |          |
  | clickOK()           |            |                |                |               |        |        |          |
  |          |          |            |                |--------------->|               |        |        |          |
  |          |          |            |                |validateConflict               |        |        |          |
  |          |          |            |                |                |-------------->|        |        |          |
  |          |          |            |                |                |checkConflict()|-------->|        |          |
  |          |          |            |                |                |<--------------|        |        |          |
  |          |          |            |                |                | noConflict    |        |        |          |
  |          |          |            |                |<---------------|               |        |        |          |
  |          |          |            |                | valid          |               |        |        |          |
  |          |          |            |<---------------|                |               |        |        |          |
  |          |          |            |addToTable()    |                |               |        |        |          |
  |          |          |            |                |                |               |        |        |          |
  |--------->|          |            |                |                |               |        |        |          |
  | clickConfirm()      |            |                |                |               |        |        |          |
  |          |          |            |--------------->|                |               |        |        |          |
  |          |          |            |confirmSchedule()                |               |        |        |          |
  |          |          |            |                |--------------->|               |        |        |          |
  |          |          |            |                |saveSchedule()  |               |        |        |          |
  |          |          |            |                |                |-------------->|        |        |          |
  |          |          |            |                |                |insertClass()  |-------->|        |          |
  |          |          |            |                |                |<--------------|        |        |          |
  |          |          |            |                |                | success       |        |        |          |
  |          |          |            |                |<---------------|               |        |        |          |
  |          |          |            |<---------------|                |               |        |        |          |
  |          |          |            |showSuccess()   |                |               |        |        |          |
  |<---------|          |            |                |                |               |        |        |          |
  | showMsg  |          |            |                |                |               |        |        |          |
```

### Bang giai thich Sequence Diagram

| Buoc | Message | Tu | Den | Mo ta |
|------|---------|-----|-----|--------|
| 1 | login() | Staff | LoginFrm | Staff nhap username/password va nhan Login |
| 2 | validate() | LoginFrm | HomeFrm | LoginFrm xac thuc thong tin dang nhap |
| 3 | showHome() | HomeFrm | Staff | Hien thi giao dien trang chu voi menu |
| 4 | clickSchedule() | Staff | HomeFrm | Staff chon menu Schedule → Schedule a Class |
| 5 | openScheduler() | HomeFrm | ClassSchedulerFrm | Mo form lap lich lop hoc |
| 6 | selectSubject() | Staff | ClassSchedulerFrm | Staff chon mon hoc tu combobox Subject |
| 7 | getSections() | ClassSchedulerFrm | AddClassDialog | Goi controller lay danh sach tiet hoc |
| 8 | querySections() | AddClassDialog | Subject | Truy van database lay tiet hoc theo mon |
| 9 | sectionList | Subject | AddClassDialog | Tra ve danh sach tiet hoc |
| 10 | updateSection() | AddClassDialog | ClassSchedulerFrm | Cap nhat combobox Section |
| 11 | selectSection() | Staff | ClassSchedulerFrm | Staff chon tiet hoc tu combobox Section |
| 12 | getClasses() | ClassSchedulerFrm | AddClassDialog | Goi controller lay danh sach lop |
| 13 | queryClasses() | AddClassDialog | Class | Truy van database lay lop theo tiet |
| 14 | classList | Class | AddClassDialog | Tra ve danh sach lop |
| 15 | updateTable() | AddClassDialog | ClassSchedulerFrm | Cap nhat bang hien thi lop |
| 16 | clickAdd() | Staff | ClassSchedulerFrm | Staff nhan nut Add de them lop moi |
| 17 | showDialog() | ClassSchedulerFrm | AddClassDialog | Hien thi dialog them lop |
| 18 | selectClassroom() | Staff | AddClassDialog | Staff chon phong hoc tu combobox |
| 19 | selectTimeSlot() | Staff | AddClassDialog | Staff chon khung gio tu combobox |
| 20 | enterMaxStudents() | Staff | AddClassDialog | Staff nhap si so toi da |
| 21 | clickOK() | Staff | AddClassDialog | Staff nhan OK de xac nhan |
| 22 | validateConflict() | AddClassDialog | ScheduleController | Kiem tra trung lich phong hoc |
| 23 | checkConflict() | ScheduleController | Class | Truy van database kiem tra xung dot |
| 24 | noConflict | Class | ScheduleController | Phong hoc trong, khong trung lich |
| 25 | valid | ScheduleController | AddClassDialog | Du lieu hop le |
| 26 | addToTable() | AddClassDialog | ClassSchedulerFrm | Them lop vao bang danh sach tam |
| 27 | clickConfirm() | Staff | ClassSchedulerFrm | Staff nhan nut Confirm de luu |
| 28 | confirmSchedule() | ClassSchedulerFrm | AddClassDialog | Goi controller luu lich |
| 29 | saveSchedule() | AddClassDialog | ScheduleController | Luu du lieu vao database |
| 30 | insertClass() | ScheduleController | Class | INSERT INTO tblClass |
| 31 | success | Class | ScheduleController | Luu thanh cong |
| 32 | showSuccess() | AddClassDialog | ClassSchedulerFrm | Hien thi thong bao thanh cong |
| 33 | showMsg | ClassSchedulerFrm | Staff | Thong bao "Schedule saved successfully!" |

---

## Cau 5: Blackbox Testcase (1.5 diem)

### Test Plan

| TC | Muc dich | Input | Expected Output |
|----|---------|-------|-----------------|
| TC01 | Lap lich lop hoc thanh cong | Chon CS101, Section 1, Classroom A101, Slot1, MaxStudents 40 | Luu thanh cong, ClassID = 1 |
| TC02 | Trung lich phong hoc | Chon A101, Slot1 (da co lop ClassID=2) | Loi: "Room A101 is already scheduled for Slot1" |
| TC03 | Thieu thong tin bat buoc | Khong chon Classroom, nhan OK | Loi: "Please select a classroom" |
| TC04 | Si so vuot suc chua phong | MaxStudents: 60, Room capacity: 40 | Loi: "Max students exceeds room capacity" |
| TC05 | Lap lich nhieu lop cho cung mon | Tao 3 lop cho CS101 voi 3 khung gio khac nhau | 3 lop duoc tao thanh cong |

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
| 8 | Nhan nut "Confirm" | Hien thi thong bao: "Schedule saved successfully! Class ID: 1" |
| 9 | Nhan OK | Quay lai ClassSchedulerFrm, bang hien thi lop vua tao |

**Database sau khi chay test:**

tblClass:
| ClassID | SubjectID | ClassroomID | TimeSlotID | MaxStudents | Section | TeacherID |
|---------|-----------|-------------|------------|-------------|---------|-----------|
| 1 | 1 | 1 | 1 | 40 | Section 1 | NULL |

(Cac bang khac khong thay doi)
