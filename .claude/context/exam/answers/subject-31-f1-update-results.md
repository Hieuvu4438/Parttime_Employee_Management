# Subject No. 31 — F1 Championship — Module "Update results"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Cập nhật kết quả chặng đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Update results**. |
| 2 | Giao diện: combobox chọn chặng đua. |
| 3 | Staff chọn "Monaco GP". |
| 4 | Hệ thống hiển thị danh sách tay đua đăng ký, mỗi dòng: tên tay đua, ô nhập thời gian về đích, số vòng hoàn thành. |
| 5 | Staff nhập kết quả: Max (1:30:45, 78 vòng), Sergio (1:31:20, 78 vòng)... |
| 6 | Staff nhấn **Save**. |
| 7 | Hệ thống tính điểm theo thứ tự: 25, 18, 15, 12, 10, 8, 6, 4, 2, 1. Nếu top 10 nhưng không hoàn thành → 0 điểm. Lưu vào database. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes với Subject 30. RaceResult:
- `id, raceId, driverId, finishTime, lapsCompleted, position, score`

---

## Câu 3: Thiết kế tĩnh

### View classes

**UpdateResultFrm:**
- `inRace`: combobox chọn chặng
- `outListDriver`: bảng tay đua với ô nhập kết quả
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| RaceRegistrationDAO | `getRegistrations(raceId): List<RaceRegistration>` |
| RaceResultDAO | `addResult(result): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → UpdateResultFrm: select "Monaco GP"
2. UpdateResultFrm → RaceRegistrationDAO: getRegistrations(raceId)
3. Staff → UpdateResultFrm: enter results for each driver
4. Staff → UpdateResultFrm: click Save
5. UpdateResultFrm: calculate positions and scores
6. UpdateResultFrm → RaceResultDAO: addResult(results)

---

## Câu 5: Blackbox Testcase

### TC01: Cập nhật kết quả thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Monaco GP → danh sách tay đua |
| 2 | Nhập kết quả, Save | Điểm: Max 25đ, Sergio 18đ, Leclerc 15đ... |

**Database sau:** Thêm N dòng tblRaceResult.
