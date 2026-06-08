# Knowledge Base — Ngân hàng câu hỏi thi CNPM

> **Bắt buộc đọc file này trước khi tạo hoặc sửa bất kỳ file answer nào.**

---

## 1. Quy trình làm bài chuẩn

Mỗi subject có 5 câu hỏi, mỗi câu 1.5 điểm. Quy trình làm bài:

### Bước 1: Đọc đề bài
- Đọc kĩ module description trong `exam-bank-2022.md`
- Trích xuất: actors, entities, attributes, constraints, flow chính

### Bước 2: Nạp knowledge
- Đọc `KNOWLEDGE-BASE.md` (file này)
- Đọc teacher reference: `03_analysis-phase.md` (scenario + entity extraction + class diagram + sequence diagram)
- Đọc teacher reference: `05_testing-phase.md` (blackbox testcase format)
- Đọc bài mẫu: `subject-01-library-borrowing.md` hoặc `subject-39-employee-register.md`

### Bước 3: Viết câu trả lời
- Viết đầy đủ theo chuẩn chất lượng trong `exam-answer-quality-standard.md`
- Mỗi câu phải đạt checklist trước khi chuyển câu tiếp

### Bước 4: Kiểm tra
- Đối chiếu với checklist trong `exam-answer-quality-standard.md`
- Đảm bảo database before/after có dữ liệu cụ thể (không được ghi "0 dòng")

---

## 2. Teacher Reference Knowledge Map

### File 01: Business Model (`01_business-model-natural-language.md`)
**Khi nào cần:** Không cần cho exam (exam chỉ hỏi 5 câu chính)
**Nội dung chính:**
- Cách mô tả objective/scope
- Cách liệt kê users và functions
- Cách viết business process step-by-step
- Cách identify objects và relationships

### File 02: Use Case UML (`02_use-case-uml.md`)
**Khi nào cần:** Không cần cho exam trực tiếp, nhưng hiểu cách extract sub-usecases
**Nội dung chính:**
- Cách propose actors (direct, abstract, indirect)
- Cách propose use cases từ functions
- Cách refine: generalization, include, extend
- Detailed use case descriptions

### File 03: Analysis Phase (`03_analysis-phase.md`) ⭐ BẮT BUỘC ĐỌC
**Khi nào cần:** Khi viết Câu 1, 2, 4
**Nội dung chính:**

#### Scenario Writing (cho Câu 1):
- Viết flow cụ thể với actor, UI, input values, result tables
- Phải có exceptional scenarios
- Mẫu: edit room (14 bước), book room (21 bước), view report (10 bước)

#### Entity Extraction (cho Câu 2):
- Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên
- Bước 2: Trích xuất danh nouns → phân loại (entity/attribute/rejected)
- Bước 3: Xác định quantitative relationships (1-1, 1-n, n-n)
- Bước 4: Xác định object relationships (generalization, aggregation, composition, association)

#### Analysis Class Diagram (cho Câu 2):
- Tạo view classes từ interfaces
- Prefix elements: `in` (input), `out` (output), `sub` (submit), `outsub` (clickable output)
- Xác định methods: tên, input params, output, class owner
- Assignment principle: output là entity type → assign method vào entity đó

#### Analysis Sequence Diagram (cho Câu 4):
- Scenario version 2: actor, view classes, entity classes
- Pattern: Actor → ViewFrm → Entity.method() → return → display

### File 04: Design Phase (`04_design-phase.md`) ⭐ BẮT BUỘC ĐỌC
**Khi nào cần:** Khi viết Câu 3, 4
**Nội dung chính:**

#### Design Entity Classes:
- Step 1: Add ID attributes
- Step 2: Add attribute types (String, int, float, Date)
- Step 3: Convert associations → aggregation/composition
- Step 4: Add object attributes (Room room, List<Room> listRoom)

#### Database Design (cho Câu 3):
- Step 1: Create tables với prefix `tbl`
- Step 2: Transfer non-object attributes → columns
- Step 3: Convert quantity relationships → table relationships
- Step 4: Configure PK/FK columns
- Step 5: Remove redundant attributes

#### Static Design Class Diagram (cho Câu 3):
- MVC: View → Frm, Control → DAO, Entity → model
- View classes: LoginFrm, XxxHomeFrm, SearchXxxFrm, EditXxxFrm
- DAO classes: XxxDAO với methods
- Entity classes: với attributes + types

#### Design Sequence Diagram (cho Câu 4):
- Scenario version 3: chi tiết hơn analysis
- Bao gồm: actionPerformed(), constructors, entity packing, DAO calls, UI display
- Pattern: Actor → Frm.actionPerformed() → new Entity() → DAO.method() → pack result → display

### File 05: Testing Phase (`05_testing-phase.md`) ⭐ BẮT BUỘC ĐỌC
**Khi nào cần:** Khi viết Câu 5
**Nội dung chính:**

#### Black-Box Test Case List:
- Bảng: No. | Module | Test case
- Liệt kê standard case + exceptional cases

#### Detailed Test Case Format:
1. **Test case title** — "Test case No. X — Mô tả"
2. **Purpose** — 1 câu mô tả mục đích
3. **Database before testing** — LIỆT KÊ TẤT CẢ bảng liên quan VỚI dữ liệu mẫu cụ thể
4. **Scenario and expected results** — bảng: Bước | Kịch bản | Kết quả mong đợi
5. **Database after testing** — bảng thay đổi + dữ liệu mới

#### Rules:
- Test case chi tiết = standard case (không phải exceptional)
- Database before phải có dữ liệu cụ thể (không được "rỗng" nếu test cần data)
- Database after chỉ liệt kê bảng thay đổi
- Nếu là read-only function → "No database change"

### File 06: Coding Java Swing/DAO (`06_coding-java-swing-dao.md`)
**Khi nào cần:** Khi cần hiểu pattern code cho Câu 3 (DAO methods)
**Nội dung chính:**
- Package: model, dao, view.<module>, view.user, test.unit
- Entity: JavaBeans, private fields, getters/setters
- DAO: extends DAO base, PreparedStatement, pack rows → objects
- Swing: JFrame, ActionListener, actionPerformed(), constructor-based passing

---

## 3. Domain Knowledge — Các domain trong đề thi

### Domain 1: Library Management (Subject 01-04)
- Actors: Staff (thủ thư)
- Entities: Book, Reader, Loan, LoanDetail, User
- Constraints: max 5 books/person, 1 month limit, 20% fine

### Domain 2: Student Results (Subject 05-10)
- Actors: Staff, Teacher, Student
- Entities: Student, Subject, Class, Registration, Grade
- Constraints: 10-15 credits/semester, no time conflict, prerequisite subjects

### Domain 3: Tour Booking (Subject 11-14)
- Actors: Staff, Customer
- Entities: Tour, TourSchedule, TourSite, Site, Service, Provider, Customer, Invoice, InvoiceDetail
- Constraints: different prices per departure date, cancellation fine

### Domain 4: Restaurant Order (Subject 15-21)
- Actors: Staff, Manager, Customer
- Entities: Table, Customer, Dish, Combo, ComboDetail, Order, OrderDetail, Invoice, Coupon
- Constraints: table merging, combo = multiple dishes

### Domain 5: Store Management (Subject 22-25)
- Actors: Staff, Manager
- Entities: Item, Supplier, SubAgent, ImportInvoice, ImportInvoiceDetail, ExportInvoice, ExportInvoiceDetail
- Constraints: export qty <= stock

### Domain 6: Chess Tournament (Subject 26-29)
- Actors: Staff
- Entities: Tournament, Player, Match, Round, Registration
- Constraints: Swiss rule, 11 rounds, Elo calculation, pairing rules

### Domain 7: F1 Championship (Subject 30-33)
- Actors: Staff
- Entities: Race, Team, Driver, Registration, Result
- Constraints: max 2 drivers/team/race, top 10 scoring, dropout = 0 points

### Domain 8: Book Rental (Subject 34-38)
- Actors: Staff, Customer
- Entities: BookTitle, Customer, RentalSlip, RentalSlipDetail, Payment
- Constraints: rental per day, deposit, late fine

### Domain 9: Part-time Employee (Subject 39-44)
- Actors: Staff, Manager, Employee
- Entities: Restaurant, Employee, Shift, RegistrationShift, Schedule, Attendance, Wage
- Constraints: min shifts/week, N employees/shift, priority by hours worked, overtime, late/early penalty

### Domain 10: Cinema Chain (Subject 45-49)
- Actors: Staff, Customer
- Entities: Cinema, ScreenRoom, Movie, Showtime, Seat, Ticket, FoodItem, FoodInvoice
- Constraints: different prices per showtime/seat, empty seats only

### Domain 11: Football Field (Subject 50-55)
- Actors: Staff, Customer
- Entities: Court, Customer, Booking, BookingSession, Product, SessionProduct, Supplier, ImportInvoice, Payment
- Constraints: court merging (2 or 4), deposit, food/drink per session

### Domain 12: Installment Loan (Subject 56-61)
- Actors: Staff, Customer, Partner
- Entities: Customer, Partner, Product, Contract, ContractItem, PaymentSchedule, Payment, PartnerPayment
- Constraints: monthly payment, interest rate, late penalty, partner payment

### Domain 13: Costume Rental (Subject 62-66)
- Actors: Staff, Customer
- Entities: Provider, Costume, Customer, RentalSlip, RentalSlipDetail, Payment
- Constraints: deposit = total costume value, fine for damage/dirt, partial return

---

## 4. Common Patterns

### Pattern: Search + Select + Process
1. Staff enters keyword → Search
2. System shows list → Staff clicks item
3. System shows detail/form → Staff enters data
4. Staff clicks Save/Submit → System saves to DB

### Pattern: Statistics with Drill-down
1. Staff selects statistics function
2. Staff enters period (start-end date)
3. System shows summary table (sorted)
4. Staff clicks row → System shows detail table

### Pattern: Invoice Generation
1. Staff selects items/services
2. System calculates totals
3. System generates invoice with:
   - Header: customer/staff info, date
   - Body: line items (code, name, qty, price, amount)
   - Footer: total amount
4. System saves + prints
