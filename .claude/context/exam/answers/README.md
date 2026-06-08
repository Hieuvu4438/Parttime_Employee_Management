# Đáp án Ngân hàng câu hỏi thi — INT1340_CLC

> **Học phần:** Nhập môn Công nghệ phần mềm
> **Phương pháp:** Áp dụng theo teacher-reference methodology

---

## Cấu trúc thư mục

Mỗi file `.md` chứa đáp án đầy đủ cho 1 đề thi (5 câu).

## Phân loại theo Domain

### 1. Library Management (Subject 01-04)

| Đề | Module | File |
|----|--------|------|
| 01 | Borrowing of books | `subject-01-library-borrowing.md` |
| 02 | Returning of books | `subject-02-library-returning.md` |
| 03 | Statistics of borrowed books | `subject-03-library-stat-borrowed.md` |
| 04 | Statistics of readers | `subject-04-library-stat-readers.md` |

**Entity classes chung:** Book, Reader, Loan, LoanDetail, User

### 2. Student Results Management (Subject 05-10)

| Đề | Module | File |
|----|--------|------|
| 05 | Schedule a class | `subject-05-student-schedule-class.md` |
| 06 | Enter grades by class | `subject-06-student-enter-grades.md` |
| 07 | Register for classes | `subject-07-student-register-class.md` |
| 08 | View Student's schedule | `subject-08-student-view-schedule.md` |
| 09 | Statistics of students | `subject-09-student-stat-students.md` |
| 10 | Statistics of subjects | `subject-10-student-stat-subjects.md` |

**Entity classes chung:** Student, Subject, Class, Registration, Grade, User

### 3. Tour Booking Management (Subject 11-14)

| Đề | Module | File |
|----|--------|------|
| 11 | Buy tickets | `subject-11-tour-buy-tickets.md` |
| 12 | Cancel the ticket | `subject-12-tour-cancel-ticket.md` |
| 13 | Tour statistics by revenue | `subject-13-tour-stat-revenue.md` |
| 14 | Revenue statistics by site | `subject-14-tour-stat-site.md` |

**Entity classes chung:** Tour, Customer, Ticket, TourSchedule, Site, TourSite, User

### 4. Restaurant Order Management (Subject 15-21)

| Đề | Module | File |
|----|--------|------|
| 15 | Order | `subject-15-restaurant-order.md` |
| 16 | Book a table | `subject-16-restaurant-book-table.md` |
| 17 | Make a combo menu | `subject-17-restaurant-combo.md` |
| 18 | Payment | `subject-18-restaurant-payment.md` |
| 19 | Statistics of visitors by time slot | `subject-19-restaurant-stat-visitors.md` |
| 20 | Statistical monthly revenue | `subject-20-restaurant-stat-monthly.md` |
| 21 | Statistics of dishes | `subject-21-restaurant-stat-dishes.md` |

**Entity classes chung:** Table, Customer, Dish, Combo, ComboDetail, Order, OrderDetail, Invoice, Coupon, User

### 5. Store Management (Subject 22-25)

| Đề | Module | File |
|----|--------|------|
| 22 | Exporting | `subject-22-store-exporting.md` |
| 23 | Importing | `subject-23-store-importing.md` |
| 24 | Statistics of items | `subject-24-store-stat-items.md` |
| 25 | Statistics of sub-agence | `subject-25-store-stat-agents.md` |

**Entity classes chung:** Item, Supplier, SubAgent, ImportBill, ImportDetail, ExportBill, ExportDetail, User

### 6. Chess Tournament Management (Subject 26-29)

| Đề | Module | File |
|----|--------|------|
| 26 | Update results | `subject-26-chess-update-results.md` |
| 27 | View leaderboard | `subject-27-chess-leaderboard.md` |
| 28 | Pair scheduling | `subject-28-chess-pair-scheduling.md` |
| 29 | Statistics of Elo change | `subject-29-chess-stat-elo.md` |

**Entity classes chung:** Tournament, Player, Match, Round, User

### 7. F1 Championship Management (Subject 30-33)

| Đề | Module | File |
|----|--------|------|
| 30 | Register to racing | `subject-30-f1-register-racing.md` |
| 31 | Update results | `subject-31-f1-update-results.md` |
| 32 | View the racers' standings | `subject-32-f1-racer-standings.md` |
| 33 | View the team rankings | `subject-33-f1-team-rankings.md` |

**Entity classes chung:** Race, Team, Driver, RaceRegistration, RaceResult, User

### 8. Book Rental Management (Subject 34-38)

| Đề | Module | File |
|----|--------|------|
| 34 | Borrowing | `subject-34-rental-borrowing.md` |
| 35 | Return and pay | `subject-35-rental-return-pay.md` |
| 36 | Statistics of book | `subject-36-rental-stat-book.md` |
| 37 | Statistics of borrowers | `subject-37-rental-stat-borrowers.md` |
| 38 | Revenue Statistics | `subject-38-rental-stat-revenue.md` |

**Entity classes chung:** BookTitle, Customer, Rental, RentalDetail, Payment, User

### 9. Part-time Employee Management (Subject 39-44)

| Đề | Module | File |
|----|--------|------|
| 39 | Register for next week | `subject-39-employee-register.md` |
| 40 | Schedule next week | `subject-40-employee-schedule.md` |
| 41 | Checkin/Checkout | `subject-41-employee-checkin.md` |
| 42 | Calculate this week's wages | `subject-42-employee-wages.md` |
| 43 | Statistics of employees | `subject-43-employee-stat.md` |
| 44 | Statistics of best employees | `subject-44-employee-stat-best.md` |

**Entity classes chung:** Restaurant, Employee, Shift, RegistrationShift, Schedule, Attendance, Wage, User

### 10. Cinema Chain Management (Subject 45-49)

| Đề | Module | File |
|----|--------|------|
| 45 | Selling movie tickets | `subject-45-cinema-sell-tickets.md` |
| 46 | Schedule showing | `subject-46-cinema-schedule.md` |
| 47 | Selling food | `subject-47-cinema-sell-food.md` |
| 48 | Revenue Statistics | `subject-48-cinema-stat-revenue.md` |
| 49 | Statistics of services | `subject-49-cinema-stat-services.md` |

**Entity classes chung:** Cinema, ScreenRoom, Movie, Showtime, Seat, Ticket, FoodItem, FoodInvoice, User

### 11. Mini Football Field Management (Subject 50-55)

| Đề | Module | File |
|----|--------|------|
| 50 | Booking | `subject-50-field-booking.md` |
| 51 | Goods importing | `subject-51-field-importing.md` |
| 52 | Update used items | `subject-52-field-update-items.md` |
| 53 | Customer paying | `subject-53-field-paying.md` |
| 54 | Revenue statistics | `subject-54-field-stat-revenue.md` |
| 55 | Statistics of time slot | `subject-55-field-stat-timeslot.md` |

**Entity classes chung:** Court, Customer, Booking, BookingDetail, Supplier, Item, ImportBill, ImportDetail, UsedItem, User

### 12. Installment Loans Management (Subject 56-61)

| Đề | Module | File |
|----|--------|------|
| 56 | Signing a contract | `subject-56-loan-signing.md` |
| 57 | Customers paying | `subject-57-loan-paying.md` |
| 58 | Payment to partners | `subject-58-loan-pay-partners.md` |
| 59 | Customer statistics by dept | `subject-59-loan-stat-dept.md` |
| 60 | Statistics of partners | `subject-60-loan-stat-partners.md` |
| 61 | Statistics of product | `subject-61-loan-stat-product.md` |

**Entity classes chung:** Partner, Customer, Contract, ContractItem, Item, PaymentSchedule, Payment, User

### 13. Costume Rental Management (Subject 62-66)

| Đề | Module | File |
|----|--------|------|
| 62 | Import costume | `subject-62-costume-import.md` |
| 63 | Costume renting | `subject-63-costume-renting.md` |
| 64 | Customer returns and pays | `subject-64-costume-return-pay.md` |
| 65 | Statistics of costumes | `subject-65-costume-stat-costumes.md` |
| 66 | Revenue Statistics | `subject-66-costume-stat-revenue.md` |

**Entity classes chung:** Costume, Customer, Rental, RentalDetail, Supplier, ImportBill, ImportDetail, Fine, User

---

## Phương pháp làm bài

Mỗi đề thi gồm 5 câu, áp dụng theo chu trình SE:

| Câu | Phase | Nội dung |
|-----|-------|----------|
| 1 | Analysis | Viết scenario chuẩn |
| 2 | Analysis | Trích xuất entity, xây class diagram |
| 3 | Design | Thiết kế UI (View class) + Class diagram chi tiết + Database |
| 4 | Design | Xây sequence diagram |
| 5 | Testing | Viết testcase blackbox |

### Nguyên tắc chung

1. **Scenario:** Viết theo dạng actor-interface-system flow, có bảng bước-by-bước.
2. **Entity extraction:** Trích xuất danh từ → phân loại → xác định quan hệ 1-1, 1-n, n-n.
3. **Class diagram:** View class (boundary) + DAO class (control) + Entity class (model). MVC pattern.
4. **Sequence diagram:** Scenario version 3 — có event handling, constructor, entity packing, DAO call.
5. **Testcase:** Database trước → kịch bản + kết quả mong đợi → database sau.
