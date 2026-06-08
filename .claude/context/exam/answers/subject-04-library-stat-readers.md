# Subject No. 04 — Library Management — Module "Statistics of readers"

> **Domain:** Library Management (cùng hệ thống với Subject 01-03)
> **Entity classes:** Book, Reader, Loan, LoanDetail, User

---

## Câu 1: Scenario — Thống kê bạn đọc

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn **Statistics** → **Statistics of readers**. |
| 3 | Giao diện thống kê xuất hiện với ô nhập ngày bắt đầu, ngày kết thúc, nút View. |
| 4 | Staff nhập khoảng thời gian: `01/01/2026` đến `31/12/2026`, nhấn View. |
| 5 | Hệ thống hiển thị danh sách bạn đọc mượn nhiều nhất, sắp xếp theo tổng số sách mượn giảm dần. Mỗi dòng: mã bạn đọc, tên, ngày sinh, địa chỉ, tổng số sách mượn. |
| 6 | Staff nhấn vào bạn đọc `R001` (Nguyen Van A). |
| 7 | Hệ thống hiển thị chi tiết phiếu mượn: ngày mượn, tổng số sách mỗi lần mượn. |
| 8 | Staff nhấn Back. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm class thống kê:

**ReaderStat:**
- `reader: Reader`
- `totalBooksBorrowed: int`

Quan hệ:
```
Reader 1 --- n Loan 1 --- n LoanDetail
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**StatReaderFrm:**
- `inStartDate`: ô nhập ngày bắt đầu
- `inEndDate`: ô nhập ngày kết thúc
- `subView`: nút View
- `outsubListReaderStat`: bảng thống kê bạn đọc (click được)
- `outListLoan`: bảng chi tiết phiếu mượn

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| ReaderDAO | `getReaderStat(startDate, endDate): List<ReaderStat>` | Thống kê bạn đọc |
| LoanDAO | `getLoanByReader(readerId, startDate, endDate): List<Loan>` | Chi tiết phiếu mượn |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `StatReaderFrm`, `ReaderDAO`, `LoanDAO`.
2. Message flow:
   - Staff → HomeFrm: select Statistics → Readers
   - HomeFrm → StatReaderFrm: open()
   - Staff → StatReaderFrm: enter dates, click View
   - StatReaderFrm → ReaderDAO: getReaderStat(01/01/2026, 31/12/2026)
   - ReaderDAO: query DB (COUNT books per reader, sorted DESC)
   - ReaderDAO → StatReaderFrm: return List<ReaderStat>
   - StatReaderFrm: display table
   - Staff → StatReaderFrm: click reader row "R001"
   - StatReaderFrm → LoanDAO: getLoanByReader(1, 01/01/2026, 31/12/2026)
   - LoanDAO → StatReaderFrm: return List<Loan>
   - StatReaderFrm: display loan details

---

## Câu 5: Blackbox Testcase

### TC01: Thống kê bạn đọc có dữ liệu

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập, chọn Statistics → Readers | Giao diện thống kê |
| 2 | Nhập 01/01/2026 - 31/12/2026, nhấn View | Bảng: R001 (5 sách), R002 (3 sách). Sắp xếp giảm dần |
| 3 | Nhấn R001 | Chi tiết: 01/06/2026, 3 sách; 15/06/2026, 2 sách |
| 4 | Nhấn Back | Quay về bảng thống kê |

**Database sau khi test:** Không thay đổi.
