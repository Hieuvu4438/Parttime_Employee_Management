# Subject No. 21 — Restaurant Order — Module "Statistics of dishes"

> **Domain:** Restaurant Order Management

---

## Câu 1: Scenario — Thống kê món ăn

| Bước | Diễn biến |
|------|-----------|
| 1 | Manager đăng nhập, chọn **Statistics** → **Dishes statistics**. |
| 2 | Giao diện thống kê: ô nhập ngày bắt đầu, ngày kết thúc, nút Statistics. |
| 3 | Manager nhập 01/01/2026 - 31/12/2026, nhấn Statistics. |
| 4 | Hệ thống hiển thị danh sách món: mã, loại, tên, tổng số lượng bán, tổng doanh thu, sắp xếp theo doanh thu giảm dần. |
| 5 | Manager nhấn vào món "Phở bò". |
| 6 | Hệ thống hiển thị chi tiết lần đặt món: mã hóa đơn, tên KH, ngày giờ, số lượng, thành tiền. |

---

## Câu 2-3: Entity + Design

**DishStat:** dish, totalQuantity, totalRevenue

DAO: `DishDAO.getDishStat(startDate, endDate): List<DishStat>`

---

## Câu 4: Sequence Diagram

1. Manager → DishStatFrm: enter dates, click Statistics
2. DishStatFrm → DishDAO: getDishStat(dates)
3. Manager → DishStatFrm: click "Phở bò"
4. DishStatFrm → OrderDetailDAO: getOrderDetailByDish(dishId, dates)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Phở bò (200 bán, 10,000,000đ), Coca (300 bán, 4,500,000đ)... |
| 2 | Nhấn Phở bò → chi tiết: ngày, KH, SL, tiền |
