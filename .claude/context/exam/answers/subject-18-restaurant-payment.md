# Subject No. 18 — Restaurant Order — Module "Payment"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Thanh toán

| Bước | Diễn biến |
|------|-----------|
| 1 | KH yêu cầu thanh toán. Staff chọn **Payment**. |
| 2 | Giao diện chọn bàn: danh sách bàn có đơn hàng. Staff chọn bàn "B01". |
| 3 | Hóa đơn chi tiết xuất hiện: mã bàn, tên nhân viên, tên KH, danh sách món (tên, đơn giá, SL, thành tiền). Cuối: tổng tiền. |
| 4 | Staff hỏi KH có mã giảm giá không. KH có mã "GIAM10". |
| 5 | Staff nhấn **Add coupon**, nhập mã "GIAM10". |
| 6 | Hệ thống cập nhật: giảm 10%, tổng tiền mới. |
| 7 | Staff thông báo số tiền, KH thanh toán. |
| 8 | Staff nhấn **Confirm**. Hệ thống lưu và in hóa đơn. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Invoice:
- `id: int`, `orderId: int`, `couponId: int`, `totalAmount: float`, `paidAmount: float`, `invoiceDate: Date`

---

## Câu 3: Thiết kế tĩnh

### View classes

**PaymentFrm:**
- `inTable`: combobox chọn bàn
- `outInvoiceDetail`: hóa đơn chi tiết
- `inCouponCode`: ô nhập mã giảm giá
- `subAddCoupon`: nút Add coupon
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| OrderDAO | `getOrderByTable(tableId): Order` |
| CouponDAO | `getCouponByCode(code): Coupon` |
| InvoiceDAO | `addInvoice(invoice): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → PaymentFrm: select table "B01"
2. PaymentFrm → OrderDAO: getOrderByTable(1)
3. PaymentFrm: display invoice detail
4. Staff → PaymentFrm: enter "GIAM10", click Add coupon
5. PaymentFrm → CouponDAO: getCouponByCode("GIAM10")
6. PaymentFrm: update total
7. Staff → PaymentFrm: click Confirm
8. PaymentFrm → InvoiceDAO: addInvoice(invoice)

---

## Câu 5: Blackbox Testcase

### TC01: Thanh toán có mã giảm giá

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Chọn bàn B01 | Hóa đơn: tổng 145,000đ |
| 2 | Nhập "GIAM10" | Giảm 10% → tổng mới = 130,500đ |
| 3 | Confirm | In hóa đơn, thông báo "Thanh toan thanh cong" |

**Database sau:** Thêm tblInvoice.
