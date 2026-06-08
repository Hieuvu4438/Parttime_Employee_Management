# Subject No. 29 — Chess Tournament — Module "Statistics of Elo change"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Thống kê thay đổi Elo

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Elo change statistics**. |
| 2 | Hệ thống hiển thị danh sách đấu thủ: mã, tên, năm sinh, quốc tịch, Elo cũ, Elo mới, thay đổi Elo. Sắp xếp: Elo thay đổi giảm dần → Elo mới giảm dần. |
| 3 | Staff nhấn vào "Player A". |
| 4 | Hệ thống hiển thị chi tiết trận: mã trận, tên đối thủ, kết quả, thay đổi Elo. |

---

## Câu 2-3: Entity + Design

**EloStat:** player, oldElo, newElo, eloChange

DAO: `PlayerDAO.getEloStat(tournamentId): List<EloStat>`

---

## Câu 4: Sequence Diagram

1. Staff → EloStatFrm: open
2. EloStatFrm → PlayerDAO: getEloStat(tournamentId)
3. Staff → EloStatFrm: click "Player A"
4. EloStatFrm → MatchDAO: getMatchesByPlayer(playerId, tournamentId)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Bảng: Player A (2500→2510, +10), Player B (2400→2390, -10) |
| 2 | Nhấn Player A → chi tiết: 11 trận, kết quả, Elo change |
