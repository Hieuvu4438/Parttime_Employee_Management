# Subject No. 32 — F1 Championship — Module "View the racers' standings"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Xem bảng xếp hạng tay đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Statistics** → **Racer standings**. |
| 2 | Giao diện: combobox chọn chặng đua. |
| 3 | Staff chọn "Monaco GP". |
| 4 | Hệ thống hiển thị BXH: tên tay đua, quốc tịch, đội, tổng điểm, tổng thời gian. Sắp xếp: tổng điểm giảm → tổng thời gian tăng. |
| 5 | Staff nhấn vào "Max Verstappen". |
| 6 | Hệ thống hiển thị chi tiết từng chặng: tên chặng, thứ hạng, điểm, thời gian về đích. |

---

## Câu 2-3: Entity + Design

**RacerStanding:** driver, nationality, teamName, totalScore, totalTime

DAO: `DriverDAO.getRacerStandings(raceId): List<RacerStanding>`

---

## Câu 4: Sequence Diagram

1. Staff → RacerStandingFrm: select race
2. RacerStandingFrm → DriverDAO: getRacerStandings(raceId)
3. Staff → RacerStandingFrm: click driver
4. RacerStandingFrm → RaceResultDAO: getResultsByDriver(driverId)

---

## Câu 5: Blackbox Testcase

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | BXH: Max (150đ, 10:30:45), Sergio (120đ, 10:32:10) |
| 2 | Nhấn Max → chi tiết: Monaco (1st, 25đ), Silverstone (2nd, 18đ) |
