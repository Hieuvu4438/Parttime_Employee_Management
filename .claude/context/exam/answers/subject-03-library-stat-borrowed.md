# Subject No. 03 — Library Management — Module "Statistics of borrowed books"

> **Domain:** Library Management (cùng hệ thống với Subject 01-02)
> **Entity classes:** Book, Reader, Loan, LoanDetail, User

---

## Câu 1: Scenario — Thống kê sách mượn

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn chức năng **Statistics** → **Statistics of borrowed books**. |
| 3 | Giao diện thống kê xuất hiện với ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 4 | Staff nhập khoảng thời gian: `01/01/2026` đến `31/12/2026`, nhấn View. |
| 5 | Hệ thống hiển thị danh sách sách mượn, sắp xếp theo số lần mượn giảm dần. Mỗi dòng: mã sách, tên sách, tác giả, barcode, tổng số lần mượn. |
| 6 | Staff nhấn vào sách `B001` (Lap trinh Java). |
| 7 | Hệ thống hiển thị chi tiết các lần mượn sách đó: tên bạn đọc, ngày mượn, ngày trả, tiền phạt (nếu có). |
| 8 | Staff nhấn Back để quay về. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes với Subject 01. Thêm class thống kê:

**BookStat** (kế thừa hoặc liên kết với Book):
- `book: Book`
- `totalLoans: int`

**LoanStat** (chi tiết thống kê):
- `readerName: String`
- `borrowedDate: Date`
- `returnedDate: Date`
- `fine: float`

Quan hệ:
```
Book 1 --- n LoanDetail n --- 1 Loan n --- 1 Reader
```

---

## Câu 3: Thiết kế tĩnh — UI + Class Diagram

### View classes

**StatBorrowedBookFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outsubListBookStat`: bảng thống kê sách (click được)
- `outListLoanDetail`: bảng chi tiết mượn

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| BookDAO | `getBookStat(startDate, endDate): List<BookStat>` | Thống kê sách mượn theo khoảng thời gian |
| LoanDetailDAO | `getLoanDetailByBook(bookId, startDate, endDate): List<LoanDetail>` | Chi tiết mượn theo sách |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `StatBorrowedBookFrm`, `BookDAO`, `LoanDetailDAO`.
2. Message flow:
   - Staff → LoginFrm: login
   - Staff → HomeFrm: select Statistics → Borrowed books
   - HomeFrm → StatBorrowedBookFrm: open()
   - Staff → StatBorrowedBookFrm: enter dates, click View
   - StatBorrowedBookFrm → BookDAO: getBookStat(01/01/2026, 31/12/2026)
   - BookDAO: query DB (JOIN tblBook, tblLoanDetail, tblLoan WHERE loanDate BETWEEN dates)
   - BookDAO → StatBorrowedBookFrm: return List<BookStat>
   - StatBorrowedBookFrm: display table sorted by totalLoans DESC
   - Staff → StatBorrowedBookFrm: click book row "B001"
   - StatBorrowedBookFrm → LoanDetailDAO: getLoanDetailByBook(1, 01/01/2026, 31/12/2026)
   - LoanDetailDAO: query DB (JOIN tblLoanDetail, tblLoan, tblReader)
   - LoanDetailDAO → StatBorrowedBookFrm: return List<LoanDetail>
   - StatBorrowedBookFrm: display detail table

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê sách mượn có dữ liệu

**Database trước khi test:**

**tblLoan + tblLoanDetail + tblBook:** (giống Subject 01-02, đã có dữ liệu mượn)

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập, chọn Statistics → Borrowed books | Giao diện thống kê xuất hiện |
| 2 | Nhập 01/01/2026 - 31/12/2026, nhấn View | Bảng sách mượn: B001 (3 lần), B002 (2 lần), B003 (1 lần). Sắp xếp: B001, B002, B003 |
| 3 | Nhấn vào dòng B001 | Chi tiết: Nguyen Van A, 01/06/2026, 08/06/2026, 0đ |
| 4 | Nhấn Back | Quay về bảng thống kê |

**Database sau khi test:** Không thay đổi.
