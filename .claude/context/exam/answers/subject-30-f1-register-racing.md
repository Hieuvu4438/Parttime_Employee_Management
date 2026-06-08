# Subject No. 30 — F1 Championship — Module "Register to racing"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Đăng ký tay đua vào chặng đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register to racing, Update results, Statistics. |
| 4 | Staff chọn chức năng **Register to racing**. |
| 5 | Giao diện đăng ký xuất hiện: combobox chọn chặng đua (Race), combobox chọn đội đua (Team). |
| 6 | Staff chọn chặng "Monaco GP" từ combobox Race. |
| 7 | Staff chọn đội "Red Bull Racing" từ combobox Team. |
| 8 | Hệ thống hiển thị danh sách tay đua của đội Red Bull Racing, sắp xếp theo tên alphabet: Daniel Ricciardo, Max Verstappen, Sergio Perez. |
| 9 | Staff tick chọn checkbox của 2 tay đua: "Max Verstappen" và "Sergio Perez". |
| 10 | Staff nhấn nút **Save**. |
| 11 | Hệ thống kiểm tra: đúng 2 tay đua được chọn (<= 2). Hợp lệ, hệ thống lưu 2 bản ghi RaceRegistration vào database. |
| 12 | Hệ thống thông báo "Dang ky thanh cong" và quay về giao diện đăng ký trống. |

### Kịch bản ngoại lệ

- **EL1:** Staff không chọn chặng đua hoặc đội đua → hệ thống cảnh báo "Vui long chon chang dua va doi dua".
- **EL2:** Staff tick quá 2 tay đua → hệ thống cảnh báo "Chi duoc phep toi da 2 tay dau cho moi doi".
- **EL3:** 2 tay đua đã được đăng ký trước đó cho chặng + đội này → hệ thống thông báo "Tay dau da duoc dang ky".

---

## Câu 2: Entity Class Diagram

### Mô tả nghiệp vụ

Hệ thống quản lý giải đua F1 Championship. Mỗi năm có nhiều chặng đua (Race). Mỗi chặng có nhiều đội đua (Team) tham gia. Mỗi đội có nhiều tay đua (Driver). Mỗi chặng, mỗi đội được đăng ký tối đa 2 tay đua. Kết quả mỗi chặng được lưu trong RaceResult. Người dùng (User) là staff thao tác trên hệ thống.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Championship | Không cần entity | Là context tổng thể, không lưu trữ riêng |
| Race (Chặng đua) | Entity class | Đối tượng lưu thông tin chặng đua |
| Team (Đội đua) | Entity class | Đối tượng lưu thông tin đội |
| Driver (Tay đua) | Entity class | Đối tượng lưu thông tin tay đua |
| RaceRegistration | Entity class | Bản ghi đăng ký tay đua vào chặng |
| RaceResult | Entity class | Kết quả thi đấu của tay đua ở mỗi chặng |
| User | Entity class | Người dùng hệ thống (staff) |

### Attribute chi tiết

| Entity | Attributes |
|--------|------------|
| Race | id, code, name, laps, location, time, description |
| Team | id, code, name, brand, description |
| Driver | id, code, name, dob, nationality, bio, teamId(FK) |
| RaceRegistration | id, raceId(FK), teamId(FK), driverId(FK) |
| RaceResult | id, raceId(FK), driverId(FK), finishTime, lapsCompleted, position, score |
| User | id, username, password, role |

### Class Diagram (ASCII)

```
+------------------+       +------------------+
|      Race        |       |      Team        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -code: String    |       | -code: String    |
| -name: String    |       | -name: String    |
| -laps: int       |       | -brand: String   |
| -location: String|       | -description: String|
| -time: DateTime  |       +------------------+
| -description: String|    | 1                |
+------------------+       |                  |
| 1                |       | n                |
|                  |       v                  |
| n                |  +-------------------+  |
v                  |  | RaceRegistration  |  |
+------------------+  +-------------------+  |
|                  |--| -id: int           |<-+
|                  |  | -raceId: int(FK)   |
|                  |  | -teamId: int(FK)   |
|                  |  | -driverId: int(FK) |
|                  |  +-------------------+
|                  |         | n
|                  |         |
|                  |         v
|                  |  +------------------+
|                  |  |     Driver       |
|                  |  +------------------+
|                  |  | -id: int         |
|                  |  | -code: String    |
|                  |  | -name: String    |
|                  |  | -dob: Date       |
|                  |  | -nationality: String|
|                  |  | -bio: String     |
|                  |  | -teamId: int(FK) |
|                  |  +------------------+
| n                |         | 1
v                  |         |
+------------------+         |
|   RaceResult     |<--------+
+------------------+
| -id: int         |
| -raceId: int(FK) |
| -driverId: int(FK)|
| -finishTime: Time|
| -lapsCompleted: int|
| -position: int   |
| -score: int      |
+------------------+

+------------------+
|      User        |
+------------------+
| -id: int         |
| -username: String|
| -password: String|
| -role: String    |
+------------------+
```

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Race → RaceRegistration | 1-n | Một chặng có nhiều đăng ký |
| Team → RaceRegistration | 1-n | Một đội có nhiều đăng ký |
| Driver → RaceRegistration | 1-n | Một tay đua có nhiều đăng ký |
| Race → RaceResult | 1-n | Một chặng có nhiều kết quả |
| Driver → RaceResult | 1-n | Một tay đua có nhiều kết quả |
| Team → Driver | 1-n | Một đội có nhiều tay đua |
| Race ↔ Team ↔ Driver | n-n (qua RaceRegistration) | Đăng ký tay đua vào chặng theo đội |

---

## Câu 3: Thiết kế tĩnh

### View classes

**RegisterRacingFrm:**
- `inRace`: combobox chọn chặng đua (danh sách Race)
- `inTeam`: combobox chọn đội đua (danh sách Team)
- `outsubListDriver`: bảng danh sách tay đua (có checkbox), hiển thị: mã, tên, quốc tịch, ngày sinh
- `subSave`: nút Save

### UI Elements

| Thành phần | Loại | Mô tả |
|-----------|------|-------|
| inRace | ComboBox | Dropdown danh sách chặng đua |
| inTeam | ComboBox | Dropdown danh sách đội đua |
| outsubListDriver | Table với CheckBox | Bảng tay đua, mỗi dòng có checkbox để chọn |
| subSave | Button | Nút lưu đăng ký |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| RaceDAO | `getAllRaces(): List<Race>` | Lấy danh sách tất cả chặng đua |
| TeamDAO | `getAllTeams(): List<Team>` | Lấy danh sách tất cả đội đua |
| DriverDAO | `getDriversByTeam(teamId): List<Driver>` | Lấy tay đua theo đội, sắp xếp theo tên |
| RaceRegistrationDAO | `getRegistrations(raceId, teamId): List<RaceRegistration>` | Kiểm tra đăng ký đã tồn tại |
| RaceRegistrationDAO | `addRegistration(reg): boolean` | Lưu đăng ký mới |

### Entity types

| Entity | Loại | Mô tả |
|--------|------|-------|
| Race | Persistent | Lưu thông tin chặng đua |
| Team | Persistent | Lưu thông tin đội đua |
| Driver | Persistent | Lưu thông tin tay đua |
| RaceRegistration | Persistent | Lưu đăng ký tay đua vào chặng |
| RaceResult | Persistent | Lưu kết quả thi đấu |
| User | Persistent | Lưu thông tin người dùng |

### Database Design

**tblRace:** ID (PK), code, name, laps, location, time, description
**tblTeam:** ID (PK), code, name, brand, description
**tblDriver:** ID (PK), code, name, dob, nationality, bio, teamID (FK → tblTeam)
**tblRaceRegistration:** ID (PK), raceID (FK → tblRace), teamID (FK → tblTeam), driverID (FK → tblDriver)
**tblRaceResult:** ID (PK), raceID (FK → tblRace), driverID (FK → tblDriver), finishTime, lapsCompleted, position, score
**tblUser:** ID (PK), username, password, role

### Visual Paradigm — Hướng dẫn vẽ Class Diagram

1. Tạo 6 class: Race, Team, Driver, RaceRegistration, RaceResult, User.
2. Thêm attributes cho mỗi class như bảng trên.
3. Draw association: Race → RaceRegistration (1-n), Team → RaceRegistration (1-n), Driver → RaceRegistration (1-n).
4. Draw association: Race → RaceResult (1-n), Driver → RaceResult (1-n).
5. Draw association: Team → Driver (1-n).
6. Đặt multiplicity đúng (1 ở phía cha, n ở phía con).

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `RegisterRacingFrm`, `RaceDAO`, `TeamDAO`, `DriverDAO`, `RaceRegistrationDAO`.
2. Vẽ message flow theo thứ tự bên dưới.
3. Sử dụng return message (dashed line) cho các giá trị trả về.
4. Thêm note chú ý cho bước kiểm tra business rule.

### ASCII Sequence Diagram

```
Staff              RegisterRacingFrm    RaceDAO    TeamDAO    DriverDAO    RaceRegistrationDAO
  |                       |                |          |            |                |
  |---login--------------->|               |          |            |                |
  |                       |--checkLogin--->|          |            |                |
  |                       |<--true---------|          |            |                |
  |                       |                |          |            |                |
  |---select Register----->|               |          |            |                |
  |                       |                |          |            |                |
  |                       |--getAllRaces-->|          |            |                |
  |                       |<--List<Race>---|          |            |                |
  |                       |                |          |            |                |
  |                       |--getAllTeams------------>|            |                |
  |                       |<--List<Team>--------------|            |                |
  |                       |                |          |            |                |
  |---select "Monaco GP"-->|               |          |            |                |
  |---select "Red Bull"--->|               |          |            |                |
  |                       |                |          |            |                |
  |                       |--getDriversByTeam--------------------------------->|
  |                       |<--List<Driver>------------------------------------|
  |                       |                |          |            |                |
  |                       |---display drivers (sorted alphabetically)-----------|
  |                       |                |          |            |                |
  |---tick 2 drivers----->|                |          |            |                |
  |---click Save--------->|                |          |            |                |
  |                       |                |          |            |                |
  |                       |---validate: count <= 2---|            |                |
  |                       |                |          |            |                |
  |                       |--addRegistration--------------------------------->|
  |                       |                                       |--INSERT DB   |
  |                       |<--true--------------------------------------------|
  |                       |                |          |            |                |
  |                       |--show "Dang ky thanh cong"------------|                |
  |<--success-------------|                |          |            |                |
```

### Bảng chi tiết các bước

| Bước | Sender | Receiver | Message | Loại |
|------|--------|----------|---------|------|
| 1 | Staff | RegisterRacingFrm | login(username, password) | Sync |
| 2 | RegisterRacingFrm | UserDAO | checkLogin("staff01", "******") | Sync |
| 3 | UserDAO | RegisterRacingFrm | return true | Return |
| 4 | Staff | RegisterRacingFrm | selectMenu("Register to racing") | Sync |
| 5 | RegisterRacingFrm | RaceDAO | getAllRaces() | Sync |
| 6 | RaceDAO | RegisterRacingFrm | return List\<Race\> | Return |
| 7 | RegisterRacingFrm | TeamDAO | getAllTeams() | Sync |
| 8 | TeamDAO | RegisterRacingFrm | return List\<Team\> | Return |
| 9 | RegisterRacingFrm | (self) | populate combo boxes | Self |
| 10 | Staff | RegisterRacingFrm | selectRace("Monaco GP") | Sync |
| 11 | Staff | RegisterRacingFrm | selectTeam("Red Bull Racing") | Sync |
| 12 | RegisterRacingFrm | DriverDAO | getDriversByTeam(teamId=1) | Sync |
| 13 | DriverDAO | RegisterRacingFrm | return List\<Driver\> [Daniel, Max, Sergio] | Return |
| 14 | RegisterRacingFrm | (self) | display driver list with checkboxes | Self |
| 15 | Staff | RegisterRacingFrm | tickCheckbox("Max Verstappen") | Sync |
| 16 | Staff | RegisterRacingFrm | tickCheckbox("Sergio Perez") | Sync |
| 17 | Staff | RegisterRacingFrm | clickSave() | Sync |
| 18 | RegisterRacingFrm | (self) | validate selectedCount == 2 | Self |
| 19 | RegisterRacingFrm | RaceRegistrationDAO | addRegistration(reg1: raceId=1, teamId=1, driverId=2) | Sync |
| 20 | RaceRegistrationDAO | (self) | INSERT INTO tblRaceRegistration | Self |
| 21 | RaceRegistrationDAO | RegisterRacingFrm | return true | Return |
| 22 | RegisterRacingFrm | RaceRegistrationDAO | addRegistration(reg2: raceId=1, teamId=1, driverId=3) | Sync |
| 23 | RaceRegistrationDAO | (self) | INSERT INTO tblRaceRegistration | Self |
| 24 | RaceRegistrationDAO | RegisterRacingFrm | return true | Return |
| 25 | RegisterRacingFrm | (self) | showMessage("Dang ky thanh cong") | Self |
| 26 | RegisterRacingFrm | Staff | show success notification | Async |

---

## Câu 5: Blackbox Testcase

### TC01: Đăng ký tay đua vào chặng thành công

**Database trước khi test:**

**tblRace:**
| ID | code | name | laps | location | time |
|----|------|------|------|----------|------|
| 1 | MON | Monaco GP | 78 | Monte Carlo | 2026-05-25 14:00 |
| 2 | SIL | Silverstone GP | 52 | Silverstone | 2026-07-06 15:00 |

**tblTeam:**
| ID | code | name | brand | description |
|----|------|------|-------|-------------|
| 1 | RBR | Red Bull Racing | Honda | Austrian racing team |
| 2 | MER | Mercedes AMG | Mercedes | German racing team |

**tblDriver:**
| ID | code | name | dob | nationality | teamID |
|----|------|------|-----|-------------|--------|
| 1 | RIC | Daniel Ricciardo | 1989-07-01 | Australian | 1 |
| 2 | VER | Max Verstappen | 1997-09-30 | Dutch | 1 |
| 3 | PER | Sergio Perez | 1990-01-26 | Mexican | 1 |
| 4 | HAM | Lewis Hamilton | 1985-01-07 | British | 2 |
| 5 | RUS | George Russell | 1998-02-15 | British | 2 |

**tblRaceRegistration:** (rỗng)
| ID | raceID | teamID | driverID |
|----|--------|--------|----------|
| - | - | - | - |

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập username `staff01`, password `123456`, nhấn Login | Đăng nhập thành công, hiển thị Home |
| 3 | Chọn **Register to racing** | Giao diện đăng ký: combobox Race + combobox Team |
| 4 | Mở combobox Race, chọn "Monaco GP" | Combobox hiển thị "Monaco GP" |
| 5 | Mở combobox Team, chọn "Red Bull Racing" | Bảng danh sách tay đua hiện ra: Daniel Ricciardo, Max Verstappen, Sergio Perez (sắp xếp theo tên) |
| 6 | Tick checkbox "Max Verstappen" | Checkbox Max được tick |
| 7 | Tick checkbox "Sergio Perez" | Checkbox Sergio được tick. Tổng 2 tay đua được chọn |
| 8 | Nhấn nút **Save** | Hệ thống kiểm tra: 2 <= 2 ✓. Thông báo "Dang ky thanh cong" |
| 9 | Kiểm tra giao diện | Quay về giao diện đăng ký trống, combobox reset |

### Database sau khi test

**tblRaceRegistration:** (thêm 2 dòng)
| ID | raceID | teamID | driverID |
|----|--------|--------|----------|
| 1 | 1 | 1 | 2 |
| 2 | 1 | 1 | 3 |

**tblRace:** (không thay đổi)
**tblTeam:** (không thay đổi)
**tblDriver:** (không thay đổi)
