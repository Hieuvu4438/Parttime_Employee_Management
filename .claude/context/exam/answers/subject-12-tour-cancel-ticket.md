# Subject No. 12 — Tour Booking — Module "Cancel the ticket"

> **Domain:** Tour Booking Management
> **Entity classes:** Tour, Customer, Ticket, TourSchedule, Site, TourSite, User

---

## Câu 1: Scenario — Hủy vé

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Cancel the ticket**. |
| 2 | Giao diện tìm kiếm vé xuất hiện: ô nhập mã vé, nút Search. |
| 3 | Staff nhập mã vé `TK001`, nhấn Search. |
| 4 | Hệ thống hiển thị chi tiết vé: tên tour, nơi khởi hành, điểm đến, ngày khởi hành, tên đại diện, CMND, loại CMND, địa chỉ, SĐT, email, số khách, giá vé. |
| 5 | Staff chọn **Cancel ticket**. |
| 6 | Hệ thống hiển thị hóa đơn phạt: thông tin vé + tiền phạt theo thời điểm hủy. |
| 7 | Staff nhấn OK. |
| 8 | Hệ thống lưu vào database, staff trả tiền cho khách. |
| 9 | Hệ thống thông báo "Huy ve thanh cong". |

---

## Câu 2: Entity Class Diagram

Cùng entity classes với Subject 11. Ticket có thêm:
- `status: String` (active/cancelled)
- `cancelDate: Date`
- `fine: float`

---

## Câu 3: Thiết kế tĩnh

### View classes

**CancelTicketFrm:**
- `inTicketCode`: ô nhập mã vé
- `subSearch`: nút Search
- `outTicketInfo`: thông tin vé
- `subCancel`: nút Cancel
- `outFineInvoice`: hóa đơn phạt

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| TicketDAO | `getTicketByCode(code): Ticket` | Tìm vé theo mã |
| TicketDAO | `cancelTicket(ticketId, fine): boolean` | Hủy vé + phạt |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `CancelTicketFrm`, `TicketDAO`.
2. Message flow:
   - Staff → CancelTicketFrm: enter "TK001", click Search
   - CancelTicketFrm → TicketDAO: getTicketByCode("TK001")
   - TicketDAO → CancelTicketFrm: return Ticket
   - CancelTicketFrm: display ticket info
   - Staff → CancelTicketFrm: click Cancel
   - CancelTicketFrm: calculate fine based on cancel date
   - CancelTicketFrm: display fine invoice
   - Staff → CancelTicketFrm: click OK
   - CancelTicketFrm → TicketDAO: cancelTicket(ticketId, fine)
   - TicketDAO: UPDATE tblTicket SET status='cancelled', fine=fine
   - CancelTicketFrm: show success

---

## Câu 5: Blackbox Testcase

### TC01: Hủy vé thành công

**Database trước:**

**tblTicket:**
| ID | code | representative | numGuests | totalAmount | status | scheduleID | customerID |
|----|------|---------------|-----------|-------------|--------|------------|------------|
| 1 | TK001 | Nguyen Van A | 2 | 5000000 | active | 1 | 1 |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Nhập "TK001", Search | Hiển thị vé: Tour Ha Long, 2 khách, 5,000,000đ |
| 2 | Nhấn Cancel | Hóa đơn phạt: 500,000đ (10% giá vé) |
| 3 | Nhấn OK | Thông báo "Huy ve thanh cong" |

### Database sau

**tblTicket:**
| ID | code | ... | status | fine |
|----|------|-----|--------|------|
| 1 | TK001 | ... | **cancelled** | **500000** |
