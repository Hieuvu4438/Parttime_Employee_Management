# Subject No. 30 — F1 Championship — Module "Register to racing"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Đăng ký tay đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Register to racing**. |
| 2 | Giao diện: combobox chọn chặng đua, combobox chọn đội đua. |
| 3 | Staff chọn "Monaco GP" + "Red Bull Racing". |
| 4 | Hệ thống hiển thị danh sách tay đua của đội, sắp xếp theo tên. |
| 5 | Staff tick chọn 2 tay đua: "Max Verstappen", "Sergio Perez". |
| 6 | Staff nhấn **Save**. Hệ thống lưu đăng ký. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Race | id, code, name, laps, location, time, description |
| Team | id, code, name, brand, description |
| Driver | id, code, name, dob, nationality, bio |
| RaceRegistration | id, raceId, teamId, driverId |
| RaceResult | id, raceId, driverId, finishTime, lapsCompleted, position, score |
| User | id, username, password, role |

### Quan hệ

```
Race 1 --- n RaceRegistration n --- 1 Team
Race 1 --- n RaceRegistration n --- 1 Driver
Team 1 --- n Driver
Race 1 --- n RaceResult n --- 1 Driver
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**RegisterRacingFrm:**
- `inRace`: combobox chọn chặng
- `inTeam`: combobox chọn đội
- `outListDriver`: danh sách tay đua (có checkbox)
- `subSave`: nút Save

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| RaceDAO | `getAllRaces(): List<Race>` |
| TeamDAO | `getAllTeams(): List<Team>` |
| DriverDAO | `getDriversByTeam(teamId): List<Driver>` |
| RaceRegistrationDAO | `addRegistration(reg): boolean` |

### Database

**tblRace:** ID, code, name, laps, location, time, description
**tblTeam:** ID, code, name, brand, description
**tblDriver:** ID, code, name, dob, nationality, bio, teamID
**tblRaceRegistration:** ID, raceID, teamID, driverID
**tblRaceResult:** ID, raceID, driverID, finishTime, lapsCompleted, position, score

---

## Câu 4: Sequence Diagram

1. Staff → RegisterRacingFrm: select race + team
2. RegisterRacingFrm → DriverDAO: getDriversByTeam(teamId)
3. Staff → RegisterRacingFrm: tick 2 drivers
4. Staff → RegisterRacingFrm: click Save
5. RegisterRacingFrm → RaceRegistrationDAO: addRegistration(regs)

---

## Câu 5: Blackbox Testcase

### TC01: Đăng ký thành công

| Bước | Kết quả mong đợi |
|------|------------------|
| 1 | Chọn Monaco GP + Red Bull → Danh sách: Max, Sergio, Daniel |
| 2 | Tick Max + Sergio, Save | "Dang ky thanh cong" |

**Database sau:** Thêm 2 dòng tblRaceRegistration.
