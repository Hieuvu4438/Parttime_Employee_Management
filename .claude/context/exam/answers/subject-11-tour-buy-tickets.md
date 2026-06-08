# Subject No. 11 — Tour Booking — Module "Buy tickets"

> **Domain:** Tour Booking Management
> **Duration:** 60 minutes

---

## Câu 1: Scenario — Mua vé tour

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. |
| 2 | Staff chọn chức năng **Buy tickets**. |
| 3 | Giao diện tìm kiếm tour xuất hiện với ô nhập tên điểm đến (destination), nút Search. |
| 4 | Staff nhập "Ha Long" và nhấn Search. |
| 5 | Hệ thống hiển thị danh sách tour có sẵn: mã tour, tên tour, nơi khởi hành, điểm đến, mô tả, ngày khởi hành, giá vé tại thời điểm tìm kiếm. |
| 6 | Staff chọn tour "Tour Ha Long 3 ngay". |
| 7 | Giao diện hóa đơn xuất hiện: tên tour, nơi khởi hành, điểm đến, ngày khởi hành, tên đại diện đoàn, CMND, loại CMND, địa chỉ, SĐT, email, số khách, giá vé. |
| 8 | Staff chọn thanh toán. Khách hàng thanh toán. |
| 9 | Hệ thống lưu vào database và in vé cho khách. |
| 10 | Hệ thống thông báo "Mua ve thanh cong". |

---

## Câu 2: Entity Class Diagram

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Tour | Entity class | Đối tượng chính |
| Customer | Entity class | Người mua vé |
| Ticket (Invoice) | Entity class | Phiếu mua vé |
| TourSchedule | Entity class | Ngày khởi hành cụ thể |
| Site | Entity class | Điểm tham quan |
| TourSite | Entity class | Liên kết Tour-Site |
| User | Entity class | Nhân viên |

### Class Diagram

```
+------------------+       +------------------+
|      Tour        |       |    Customer      |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -code: String    |
| -name: String    |       | -name: String    |
| -departure: String|      | -idNumber: String|
| -destination: String|    | -idType: String  |
| -description: String|    | -phone: String   |
+------------------+       | -email: String   |
         | 1               | -address: String |
         |                  +------------------+
         | n                        | 1
         v                          | n
+------------------+                v
|  TourSchedule    |        +------------------+
+------------------+        |     Ticket       |
| -id: int         |        +------------------+
| -departureDate: Date|     | -id: int         |
| -price: float    |        | -representative: String|
| -maxGuests: int  |        | -idNumber: String|
+------------------+        | -numGuests: int  |
                            | -totalAmount: float|
                            | -ticketDate: Date|
                            +------------------+

+------------------+       +------------------+
|      Site        |       |    TourSite      |
+------------------+       +------------------+
| -id: int         |       | -tourId: int     |
| -name: String    |       | -siteId: int     |
| -description: String|    +------------------+
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Tour → TourSchedule | 1-n | Một tour có nhiều ngày khởi hành |
| Tour → Site | n-n (qua TourSite) | Tour đi qua nhiều điểm tham quan |
| Customer → Ticket | 1-n | Một KH mua nhiều vé |
| TourSchedule → Ticket | 1-n | Một ngày khởi hành có nhiều vé |

---

## Câu 3: Thiết kế tĩnh

### View classes

**SearchTourFrm:**
- `inDestination`: ô nhập điểm đến
- `subSearch`: nút Search
- `outsubListTour`: bảng tour (click được)

**BuyTicketFrm:**
- `outTourInfo`: thông tin tour
- `outScheduleInfo`: thông tin ngày khởi hành, giá
- `inRepresentative`: ô nhập tên đại diện
- `inIdNumber`: ô nhập CMND
- `inNumGuests`: ô nhập số khách
- `subPay`: nút thanh toán
- `outTicket`: phiếu vé

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| TourDAO | `searchTour(destination): List<Tour>` | Tìm tour theo điểm đến |
| TourScheduleDAO | `getSchedules(tourId): List<TourSchedule>` | Lịch khởi hành |
| TicketDAO | `addTicket(ticket): boolean` | Tạo vé |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `LoginFrm`, `HomeFrm`, `SearchTourFrm`, `BuyTicketFrm`, `TourDAO`, `TicketDAO`.
2. Message flow:
   - Staff → SearchTourFrm: enter "Ha Long", click Search
   - SearchTourFrm → TourDAO: searchTour("Ha Long")
   - TourDAO → SearchTourFrm: return List<Tour>
   - Staff → SearchTourFrm: select tour
   - SearchTourFrm → BuyTicketFrm: open(tour)
   - Staff → BuyTicketFrm: enter customer info, click Pay
   - BuyTicketFrm → TicketDAO: addTicket(ticket)
   - TicketDAO: INSERT INTO tblTicket
   - BuyTicketFrm: print ticket, show success

---

## Câu 5: Blackbox Testcase

### TC01: Mua vé thành công

**Database trước:**

**tblTour:**
| ID | code | name | departure | destination |
|----|------|------|-----------|-------------|
| 1 | T001 | Tour Ha Long 3 ngay | Ha Noi | Ha Long |

**tblTourSchedule:**
| ID | tourID | departureDate | price | maxGuests |
|----|--------|---------------|-------|-----------|
| 1 | 1 | 15/07/2026 | 2500000 | 30 |

**tblCustomer:**
| ID | code | name | idNumber | phone | email | address |
|----|------|------|----------|-------|-------|---------|
| 1 | C001 | Nguyen Van A | 012345678901 | 0912345678 | a@gmail.com | Ha Noi |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Đăng nhập, chọn Buy tickets | Giao diện tìm kiếm |
| 2 | Nhập "Ha Long", Search | Danh sách tour: T001 |
| 3 | Chọn T001, ngày 15/07 | Hóa đơn: giá 2,500,000/khách |
| 4 | Nhập đại diện "Nguyen Van A", CMND, 2 khách, thanh toán | Thông báo "Mua ve thanh cong". In vé |

### Database sau

**tblTicket:**
| ID | representative | idNumber | numGuests | totalAmount | ticketDate | scheduleID | customerID |
|----|---------------|----------|-----------|-------------|------------|------------|------------|
| 1 | Nguyen Van A | 012345678901 | 2 | 5000000 | 08/06/2026 | 1 | 1 |
