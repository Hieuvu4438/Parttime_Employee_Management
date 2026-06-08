# Subject No. 63 — Costume Rental — Module “Costume renting”

> **Domain:** Costume Rental Management

---

## Câu 1: Scenario (Bảng diễn biến)

| Bước | Diễn biến |
|------|-----------|
| 1 | Customer mang trang phục đến quầy thuê |
| 2 | Staff đăng nhập hệ thống, chọn chức năng **Costume renting** |
| 3 | Hệ thống hiển thị giao diện: õ nhập tên khách hàng + nút **Search** |
| 4 | Staff nhập “Tran Thi B” vào õ tìm kiếm, nhấn **Search**. Hệ thống hiển thị danh sách khách hàng phù hợp |
| 5 | Staff click chọn khách hàng **Tran Thi B** trong danh sách (nếu chưa có thì chuyển sang giao diện thêm mới khách hàng, nhập thông tin và tiếp tục) |
| 6 | Hệ thống hiển thị giao diện thêm trang phục vào phiếu thuê. Staff nhập “Vest nam” → nhấn **Search** → danh sách hiển thị → chọn **Vest nam đen**, hệ thống hiển thị giá thuê |
| 7 | Staff nhập số lượng 2, hệ thống tự động tính tiền cọc cho Vest nam đen: 2 × 500,000đ = 1,000,000đ |
| 8 | Staff nhập “Váy đã hội” → nhấn **Search** → chọn **Váy đã hội đỏ**, nhập số lượng 1, tiền cọc: 1 × 800,000đ = 800,000đ |
| 9 | Hệ thống hiển thị phiếu thuê: Vest nam đen × 2, Váy đã hội đỏ × 1. Tổng tiền cọc = 1,800,000đ (tự động tính) |
| 10 | Staff nhấn **Create rental slip**. Hệ thống lưu phiếu thuê vào database, in phiếu thuê, staff nhận tiền cọc từ khách hàng |
