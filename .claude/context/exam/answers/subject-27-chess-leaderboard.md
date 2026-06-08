# Subject No. 27 — Chess Tournament — Module "View leaderboard"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Xem bảng xếp hạng

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **View leaderboard**. |
| 2 | Giao diện: combobox chọn vòng đấu. |
| 3 | Staff chọn "Round 5". |
| 4 | Hệ thống hiển thị bảng xếp hạng: mã, tên, năm sinh, quốc tịch, tổng điểm, tổng điểm đối thủ, hệ số Elo hiện tại. Sắp xếp: tổng điểm giảm → tổng điểm đối thủ giảm → Elo giảm. |
| 5 | Staff nhấn vào "Player A". |
| 6 | Hệ thống hiển thị chi tiết các trận đã đấu: mã trận, tên đối thủ, kết quả (thắng/hòa/thua), thay đổi Elo. |

---

## Câu 2: Entity Class Diagram

Cùng entity classes. Thêm:

**PlayerStanding:** player, totalScore, totalOpponentScore, currentElo

---

## Câu 3: Thiết kế tĩnh

### View classes

**LeaderboardFrm:**
- `inRound`: combobox chọn vòng
- `outsubListStanding`: bảng xếp hạng (click được)
- `outListMatchDetail`: chi tiết trận đấu

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| PlayerDAO | `getStandings(roundId): List<PlayerStanding>` |
| MatchDAO | `getMatchesByPlayer(playerId, roundId): List<Match>` |

---

## Câu 4: Sequence Diagram

1. Staff → LeaderboardFrm: select "Round 5"
2. LeaderboardFrm → PlayerDAO: getStandings(roundId)
3. Staff → LeaderboardFrm: click "Player A"
4. LeaderboardFrm → MatchDAO: getMatchesByPlayer(playerId, roundId)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Round 5 → BXH: Player A (4.5đ, Elo 2510), Player B (4.0đ, Elo 2390) |
| 2 | Nhấn Player A → chi tiết: 5 trận (thắng 4, hòa 1) |
