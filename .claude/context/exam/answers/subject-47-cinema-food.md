# Subject No. 47 — Cinema Chain — Module "Selling food"

> **Domain:** Cinema Chain Management

---

## Câu 1: Scenario — Bán đồ ăn

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Selling food**. |
| 2 | Giao diện: ô nhập tên món, nút Search, bảng hóa đơn. |
| 3 | Staff nhập "Popcorn", nhấn Search. Hệ thống hiển thị danh sách món chứa "Popcorn". |
| 4 | Staff click vào "Popcorn Caramel". Giao diện chọn size (S/M/L) + số lượng. |
| 5 | Staff chọn size L, số lượng 2, nhấn OK. Hệ thống thêm vào hóa đơn: Popcorn Caramel, L, 65,000đ × 2 = 130,000đ. |
| 6 | Staff nhập "Coca", Search, chọn "Coca Cola", size M, số lượng 3, OK. Hóa đơn thêm: Coca Cola, M, 25,000đ × 3 = 75,000đ. |
| 7 | Hệ thống hiển thị tổng tiền: 205,000đ. |
| 8 | Staff nhấn **Pay**. Hệ thống in hóa đơn, lưu database. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| FoodItem | id, code, name, price, size |
| FoodInvoice | id, invoiceDate, totalAmount |
| FoodInvoiceDetail | id, foodInvoiceId, foodItemId, quantity, amount |
| User | id, username, password, role |

### Quan hệ

```
FoodInvoice 1 --- n FoodInvoiceDetail n --- 1 FoodItem
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**SellFoodFrm:**
- `inFoodName`: ô nhập tên món
- `subSearch`: nút Search
- `outFoodList`: danh sách món tìm được (click được)
- `inSize`: combobox chọn size
- `inQuantity`: ô nhập số lượng
- `subOK`: nút OK
- `outInvoiceTable`: bảng hóa đơn (mã, tên, size, đơn giá, số lượng, thành tiền)
- `outTotal`: tổng tiền
- `subPay`: nút Pay

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| FoodItemDAO | `searchFoodByName(keyword): List<FoodItem>` |
| FoodInvoiceDAO | `createFoodInvoice(invoice): boolean` |
| FoodInvoiceDetailDAO | `addInvoiceDetail(detail): boolean` |

### Database

**tblFoodInvoice:** id (PK), invoiceDate, totalAmount

**tblFoodInvoiceDetail:** id (PK), foodInvoiceId (FK), foodItemId (FK), quantity, amount

---

## Câu 4: Sequence Diagram

### Mô tả bằng Visual Paradigm

**Lifelines:**
1. `:Staff` — actor
2. `:SellFoodFrm` — boundary
3. `:FoodItemDAO` — control
4. `:FoodInvoiceDAO` — control
5. `:FoodInvoiceDetailDAO` — control

**Message flow:**

1. Staff → SellFoodFrm: `enterFoodName("Popcorn")` + `clickSearch()`
2. SellFoodFrm → FoodItemDAO: `searchFoodByName("Popcorn")`
3. FoodItemDAO → SellFoodFrm: return `List<FoodItem>`
4. Staff → SellFoodFrm: `selectFood(Popcorn Caramel)` + `selectSize(L)` + `enterQuantity(2)` + `clickOK()`
5. SellFoodFrm: add to invoice table (Popcorn Caramel, L, 65000 × 2 = 130000)
6. Staff → SellFoodFrm: `clickPay()`
7. SellFoodFrm → FoodInvoiceDAO: `createFoodInvoice(invoice)`
8. FoodInvoiceDAO: INSERT INTO tblFoodInvoice
9. SellFoodFrm → FoodInvoiceDetailDAO: `addInvoiceDetail(details)` (loop each item)
10. FoodInvoiceDetailDAO: INSERT INTO tblFoodInvoiceDetail

---

## Câu 5: Blackbox Testcase

### TC01: Bán đồ ăn thành công

**Database trước:**
- tblFoodInvoice: 0 dòng
- tblFoodInvoiceDetail: 0 dòng
- tblFoodItem: Popcorn Caramel (L, 65000), Coca Cola (M, 25000)

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Nhập "Popcorn" → Search | Danh sách: Popcorn Caramel, Popcorn Cheese... |
| 2 | Chọn Popcorn Caramel, L, 2 → OK | Hóa đơn: Popcorn Caramel, L, 65,000đ × 2 = 130,000đ |
| 3 | Nhập "Coca" → Search, chọn Coca Cola, M, 3 → OK | Hóa đơn thêm: Coca Cola, M, 25,000đ × 3 = 75,000đ. Tổng: 205,000đ |
| 4 | Pay | "Ban do an thanh cong", in hóa đơn |

**Database sau:**
- tblFoodInvoice: thêm 1 dòng (totalAmount=205000)
- tblFoodInvoiceDetail: thêm 2 dòng (Popcorn L × 2 = 130000, Coca M × 3 = 75000)
