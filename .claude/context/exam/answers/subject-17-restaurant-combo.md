# Subject No. 17 — Restaurant Order — Module "Make a combo menu"

> **Domain:** Restaurant Order Management
> **Entity classes:** Table, Customer, Dish, Combo, ComboDetail, Order, Invoice, User

---

## Câu 1: Scenario — Tạo combo

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Combo management** → **Add new combo**. |
| 2 | Giao diện thêm combo: ô nhập tên combo, nút Add dishes. |
| 3 | Manager nhập tên "Combo Phở + Coca". |
| 4 | Manager nhấn Add dishes → giao diện tìm món xuất hiện. |
| 5 | Manager nhập "Phở", nhấn Search → danh sách món: Phở bò (50,000), Phở gà (45,000). |
| 6 | Manager chọn "Phở bò" → quay về giao diện combo, Phở bò được thêm. |
| 7 | Manager lặp: tìm "Coca", chọn Coca (15,000). |
| 8 | Manager nhấn **Update**. Hệ thống lưu combo vào database. |

---

## Câu 2: Entity Class Diagram

```
Combo 1 --- n ComboDetail n --- 1 Dish
```

**Combo:** id, name, totalPrice
**ComboDetail:** id, comboId, dishId, quantity

---

## Câu 3: Thiết kế tĩnh

### View classes

**ComboFrm:**
- `inComboName`: ô nhập tên combo
- `subAddDishes`: nút Add dishes
- `outListComboDetail`: danh sách món trong combo
- `subUpdate`: nút Update

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| DishDAO | `searchDish(name): List<Dish>` |
| ComboDAO | `addCombo(combo): boolean` |
| ComboDetailDAO | `addComboDetail(detail): boolean` |

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Manager`, `ComboFrm`, `DishDAO`, `ComboDAO`.
2. Message flow:
   - Manager → ComboFrm: enter combo name
   - Manager → ComboFrm: click Add dishes
   - Manager → ComboFrm: search "Phở", select dish
   - ComboFrm → DishDAO: searchDish("Phở")
   - ComboFrm: add to combo list
   - (lặp)
   - Manager → ComboFrm: click Update
   - ComboFrm → ComboDAO: addCombo(combo)

---

## Câu 5: Blackbox Testcase

### TC01: Tạo combo thành công

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Nhập "Combo Phở + Coca", Add dishes | Giao diện tìm món |
| 2 | Tìm "Phở bò", chọn; tìm "Coca", chọn | Danh sách: Phở bò + Coca |
| 3 | Update | Thông báo "Tao combo thanh cong" |

**Database sau:** Thêm 1 tblCombo, 2 tblComboDetail.
