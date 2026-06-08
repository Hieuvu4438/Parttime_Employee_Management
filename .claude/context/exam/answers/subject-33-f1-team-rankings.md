# Subject No. 33 — F1 Championship — Module "View the team rankings"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Xem bảng xếp hạng đội đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Team rankings**. |
| 2 | Giao diện: combobox chọn chặng đua. |
| 3 | Staff chọn "Monaco GP". |
| 4 | Hệ thống hiển thị BXH đội: tên đội, chủ sở hữu, tổng điểm 2 tay đua, tổng thời gian. Sắp xếp: tổng điểm giảm → tổng thời gian tăng. |
| 5 | Staff nhấn vào "Red Bull Racing". |
| 6 | Hệ thống hiển thị chi tiết từng chặng: tên chặng, tổng điểm, tổng thời gian 2 tay đua. |

---

## Câu 2-3: Entity + Design

**TeamStanding:** team, owner, totalScore, totalTime

DAO: `TeamDAO.getTeamStandings(raceId): List<TeamStanding>`

---

## Câu 4: Sequence Diagram

1. Staff → TeamStandingFrm: select race
2. TeamStandingFrm → TeamDAO: getTeamStandings(raceId)
3. Staff → TeamStandingFrm: click team
4. TeamStandingFrm → RaceResultDAO: getResultsByTeam(teamId, raceId)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | BXH: Red Bull (270đ), Mercedes (200đ), Ferrari (180đ) |
| 2 | Nhấn Red Bull → chi tiết: Monaco (43đ), Silverstone (38đ) |
