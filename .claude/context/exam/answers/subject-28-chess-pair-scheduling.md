# Subject No. 28 — Chess Tournament — Module "Pair scheduling"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Xếp cặp thi đấu

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Scheduling**. |
| 2 | Giao diện: combobox chọn vòng trước đó. |
| 3 | Staff chọn "Round 4". Hệ thống hiển thị BXH hiện tại + nút Schedule. |
| 4 | Staff nhấn **Schedule**. |
| 5 | Hệ thống tự động xếp cặp theo luật Swiss: từ BXH, ghép cặp chưa gặp nhau. Hiển thị danh sách bàn: tên bàn, tên 2 đấu thủ. |
| 6 | Staff nhấn **Save**. Hệ thống lưu lịch vòng 5 vào database. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Match có thêm:
- `table: String` (tên bàn)

---

## Câu 3: Thiết kế tĩnh

### View classes

**PairSchedulingFrm:**
- `inPreviousRound`: combobox chọn vòng trước
- `outStanding`: bảng BXH hiện tại
- `subSchedule`: nút Schedule
- `outListPair`: danh sách cặp đấu
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PlayerDAO | `getStandings(roundId): List<PlayerStanding>` |
| MatchDAO | `createRound(round): boolean` |
| MatchDAO | `addMatch(match): boolean` |

---

## Câu 4: Sequence Diagram

1. Staff → PairSchedulingFrm: select "Round 4"
2. PairSchedulingFrm → PlayerDAO: getStandings(4)
3. Staff → PairSchedulingFrm: click Schedule
4. PairSchedulingFrm: auto-pair by Swiss rules
5. PairSchedulingFrm: display pairs
6. Staff → PairSchedulingFrm: click Save
7. PairSchedulingFrm → MatchDAO: createRound(round), addMatch(matches)

---

## Câu 5: Blackbox Testcase

### TC01: Xếp cặp thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Round 4 → BXH: A(4đ), B(3.5đ), C(3đ), D(2.5đ) |
| 2 | Schedule → Cặp: A vs C (Bàn 1), B vs D (Bàn 2) |
| 3 | Save | "Xep cap thanh cong" |

**Database sau:** Thêm tblRound, 2 dòng tblMatch.
