# Subject No. 02 — Library Management — Module "Returning of books"

> **Domain:** Library Management (cùng hệ thống với Subject 01)
> **Entity classes:** Book, Reader, Loan, LoanDetail, User (giống Subject 01)

---

## Câu 1: Scenario — Trả sách

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn chức năng **Returning of books**. |
| 3 | Giao diện Returning xuất hiện với ô nhập mã thẻ bạn đọc và nút Scan. |
| 4 | Staff quét thẻ bạn đọc `R001`. |
| 5 | Hệ thống hiển thị thông tin bạn đọc + danh sách sách chưa trả + danh sách sách đã trả. |
| 6 | Staff quét từng cuốn sách trả: quét `B001`, `B002`. |
| 7 | Hệ thống cập nhật danh sách sách trả. Staff nhấn **Submit**. |
| 8 | Hệ thống kiểm tra: nếu sách trả sau ngày hẹn trả → tính phạt = 20% × giá bia. |
| 9 | Hệ thống in phiếu mượn (nếu còn sách chưa trả): mã bạn đọc, tên, barcode phiếu mượn, danh sách sách còn mượn. |
| 10 | Hệ thống in phiếu phạt (nếu có): mã bạn đọc, tên, barcode phiếu mượn, danh sách sách trả muộn (mã sách, tên sách, tác giả, barcode, ngày mượn, ngày hẹn trả, ngày trả, số tiền phạt). Dòng cuối: tổng tiền phạt. |
| 11 | Hệ thống lưu vào database và thông báo "Tra sach thanh cong". |

### Kịch bản ngoại lệ
- **EL1:** Thẻ bạn đọc không tồn tại.
- **EL2:** Bạn đọc không có sách nào đang mượn.

---

## Câu 2: Entity Class Diagram

Cùng entity classes với Subject 01: **Book, Reader, Loan, LoanDetail, User**.

Quan hệ:
```
Reader 1 --- n Loan 1 --- n LoanDetail n --- 1 Book
User 1 --- n Loan
```

**Điểm khác biệt:** LoanDetail cần thêm thuộc tính `returnDate: Date` và `fine: float` để ghi nhận ngày trả thực tế và tiền phạt.

---

## Câu 3: Thiết kế tĩnh — UI + Class Diagram

### View classes

**ReturningBookFrm:**
- `inReaderBarcode`: ô nhập barcode bạn đọc
- `subScanReader`: nút scan
- `outReaderInfo`: vùng thông tin bạn đọc
- `outListUnreturnedBooks`: bảng sách chưa trả (có checkbox chọn sách trả)
- `outListReturnedBooks`: bảng sách đã trả
- `subSubmit`: nút Submit

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| ReaderDAO | `getReaderByBarcode(barcode): Reader` | Lấy thông tin bạn đọc |
| BookDAO | `getUnreturnedBooks(readerId): List<Book>` | Lấy sách chưa trả |
| LoanDetailDAO | `updateReturn(loanDetailId, returnDate, fine): boolean` | Cập nhật ngày trả + phạt |
| LoanDAO | `getLoanByReader(readerId): List<Loan>` | Lấy phiếu mượn theo bạn đọc |

### Database
Cùng cấu trúc với Subject 01. `tblLoanDetail` cập nhật thêm `returnDate` và `fine`.

---

## Câu 4: Sequence Diagram — Trả sách

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `ReturningBookFrm`, `ReaderDAO`, `BookDAO`, `LoanDetailDAO`.
2. Vẽ message flow:
   - Staff → LoginFrm: login
   - LoginFrm → UserDAO: checkLogin()
   - Staff → HomeFrm: select Returning
   - HomeFrm → ReturningBookFrm: open()
   - Staff → ReturningBookFrm: scanReader("R001")
   - ReturningBookFrm → ReaderDAO: getReaderByBarcode("R001")
   - ReturningBookFrm → BookDAO: getUnreturnedBooks(readerId)
   - ReturningBookFrm: display reader info + unreturned books
   - Staff → ReturningBookFrm: scanBook("B001") [lặp cho mỗi sách trả]
   - ReturningBookFrm: add to return list
   - Staff → ReturningBookFrm: submit
   - ReturningBookFrm: calculate fine (nếu quá hạn)
   - ReturningBookFrm → LoanDetailDAO: updateReturn(loanDetailId, returnDate, fine)
   - ReturningBookFrm: print loan slip (nếu còn sách mượn)
   - ReturningBookFrm: print penalty slip (nếu có phạt)
   - ReturningBookFrm: show success

---

## Câu 5: Blackbox Testcase

### Testcase chi tiết — TC01: Trả sách thành công (không phạt)

**Database trước khi test:**

**tblLoan:**
| ID | loanDate | barcode | readerID | userID |
|----|----------|---------|----------|--------|
| 1 | 01/06/2026 | LP001 | 1 | 1 |

**tblLoanDetail:**
| ID | dueDate | returnDate | fine | bookID | loanID |
|----|---------|------------|------|--------|--------|
| 1 | 01/07/2026 | null | 0 | 1 | 1 |
| 2 | 01/07/2026 | null | 0 | 2 | 1 |

### Kịch bản test

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập thành công | Giao diện Home |
| 2 | Chọn Returning | Giao diện Returning xuất hiện |
| 3 | Scan thẻ bạn đọc `R001` | Hiển thị thông tin bạn đọc + 2 sách chưa trả |
| 4 | Scan sách `B001` | Sách B001 được thêm vào danh sách trả |
| 5 | Nhấn Submit | Hệ thống kiểm tra ngày trả (08/06/2026) < ngày hẹn trả (01/07/2026) → không phạt. In phiếu mượn (còn sách B002). Thông báo "Tra sach thanh cong" |

### Database sau khi test

**tblLoanDetail:**
| ID | dueDate | returnDate | fine | bookID | loanID |
|----|---------|------------|------|--------|--------|
| 1 | 01/07/2026 | **08/06/2026** | **0** | 1 | 1 |
| 2 | 01/07/2026 | null | 0 | 2 | 1 |
