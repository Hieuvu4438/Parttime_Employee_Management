# Subject No. 15 — Restaurant Order — Module "Order"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Gọi món

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Order**. |
| 2 | Giao diện chọn bàn xuất hiện: dropdown danh sách bàn. |
| 3 | Staff chọn bàn "B01" (Bàn 1, tối đa 4 khách). |
| 4 | Giao diện gọi món xuất hiện: ô nhập tên món, nút Search, danh sách món đã chọn. |
| 5 | Staff nhập "Phở bò", nhấn Search. |
| 6 | Hệ thống hiển thị danh sách món: mã, loại, tên, giá. |
| 7 | Staff chọn "Phở bò" (50,000đ), nhập số lượng 2, nhấn OK. |
| 8 | Món được thêm vào danh sách: Phở bò × 2 = 100,000đ. |
| 9 | Staff lặp: chọn "Coca" (15,000đ) × 3 = 45,000đ. |
| 10 | Staff đọc lại xác nhận với khách, nhấn **Confirm**. |
| 11 | Hệ thống lưu vào database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Table | id, code, name, maxGuests, description |
| Customer | id, code, name, phone, email, address |
| Dish | id, code, type, name, description, price |
| Combo | id, name, totalPrice |
| ComboDetail | id, comboId, dishId, quantity |
| Order | id, tableId, customerId, userId, orderDate, totalAmount |
| OrderDetail | id, orderId, dishId/comboId, quantity, unitPrice, amount |
| Coupon | id, code, discount |
| Invoice | id, orderId, couponId, totalAmount, paidAmount |
| User | id, username, password, role |

### Quan hệ

```
Table 1 --- n Order
Customer 1 --- n Order
Order 1 --- n OrderDetail
Dish 1 --- n OrderDetail
Combo 1 --- n OrderDetail
Coupon 1 --- n Invoice
Order 1 --- 1 Invoice
User 1 --- n Order
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**OrderFrm:**
- `inTable`: combobox chọn bàn
- `inDishName`: ô nhập tên món
- `subSearch`: nút Search
- `outsubListDish`: bảng món (click được)
- `inQuantity`: ô nhập số lượng
- `subOK`: nút OK
- `outListOrderDetail`: danh sách món đã chọn
- `subConfirm`: nút Confirm

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| TableDAO | `getAllTables(): List<Table>` |
| DishDAO | `searchDish(name): List<Dish>` |
| OrderDAO | `addOrder(order): boolean` |
| OrderDetailDAO | `addOrderDetail(detail): boolean` |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `OrderFrm`, `TableDAO`, `DishDAO`, `OrderDAO`.
2. Message flow:
   - Staff → OrderFrm: select table "B01"
   - Staff → OrderFrm: enter "Phở bò", click Search
   - OrderFrm → DishDAO: searchDish("Phở bò")
   - DishDAO → OrderFrm: return List<Dish>
   - Staff → OrderFrm: select dish, enter quantity 2, click OK
   - OrderFrm: add to order list
   - (lặp cho các món khác)
   - Staff → OrderFrm: click Confirm
   - OrderFrm → OrderDAO: addOrder(order)
   - OrderDAO: INSERT INTO tblOrder, tblOrderDetail

---

## Câu 5: Blackbox Testcase

### TC01: Gọi món thành công

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Chọn bàn B01 | Giao diện gọi món |
| 2 | Tìm "Phở bò", chọn, SL 2 | Danh sách: Phở bò × 2 = 100,000đ |
| 3 | Tìm "Coca", chọn, SL 3 | Danh sách: + Coca × 3 = 45,000đ. Tổng = 145,000đ |
| 4 | Confirm | Thông báo "Goi mon thanh cong" |

**Database sau:** Thêm 1 dòng tblOrder, 2 dòng tblOrderDetail.
