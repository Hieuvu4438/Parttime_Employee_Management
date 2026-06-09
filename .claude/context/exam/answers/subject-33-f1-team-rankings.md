# Subject No. 33 — F1 Championship — Module "View the team rankings"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Xem bảng xếp hạng đội đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register to racing, Update results, Statistics. |
| 4 | Staff chọn **Statistics** → **Team rankings**. |
| 5 | Giao diện bảng xếp hạng đội đua xuất hiện: combobox chọn chặng đua (để lọc kết quả tính đến chặng đó). |
| 6 | Staff chọn "Silverstone GP" (chặng 2) từ combobox. |
| 7 | Hệ thống hiển thị bảng xếp hạng đội: cột Tên đội, Chủ sở hữu (brand), Tổng điểm, Tổng thời gian. Sắp xếp: tổng điểm giảm dần → tổng thời gian tăng dần. |
| 8 | Bảng hiển thị: Red Bull Racing (Honda, 83đ, 5:53:45), Mercedes AMG (Mercedes, 57đ, 5:56:25), McLaren (Mercedes, 0đ, --). |
| 9 | Staff nhấn vào dòng "Red Bull Racing". |
| 10 | Hệ thống hiển thị chi tiết kết quả từng chặng của Red Bull: Monaco GP (tổng 43đ, tổng thời gian 3:02:05), Silverstone GP (tổng 40đ, tổng thời gian 2:51:40). |
| 11 | Staff nhấn nút **Back** để quay về bảng xếp hạng. |

### Kịch bản ngoại lệ

- **EL1:** Staff không chọn chặng đua → hệ thống cảnh báo "Vui long chon chang dua".
- **EL2:** Chưa có kết quả cho chặng đã chọn → hệ thống hiển thị bảng rỗng với thông báo "Chua co ket qua".

---

## Câu 2: Entity Class Diagram

### Mô tả nghiệp vụ

Hệ thống thống kê bảng xếp hạng đội đua dựa trên kết quả các chặng đã đấu. Tổng điểm của mỗi đội là tổng score của tất cả tay đua thuộc đội đó qua các chặng. Tổng thời gian là tổng finishTime của tất cả tay đua thuộc đội. Bảng xếp hạng sắp xếp theo tổng điểm giảm dần, nếu bằng điểm thì theo tổng thời gian tăng dần. Nhấn vào đội để xem chi tiết từng chặng.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Race (Chặng đua) | Entity class | Đối tượng lưu thông tin chặng đua |
| Team (Đội đua) | Entity class | Đối tượng lưu thông tin đội |
| Driver (Tay đua) | Entity class | Đối tượng lưu thông tin tay đua |
| RaceRegistration | Entity class | Bản ghi đăng ký tay đua vào chặng |
| RaceResult | Entity class | Kết quả thi đấu của tay đua ở mỗi chặng |
| User | Entity class | Người dùng hệ thống (staff) |
| TeamStanding | Derived object | Không phải entity, là kết quả tính toán từ RaceResult |

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
| -description: String|            | 1
+------------------+               | n
| 1                |               v
|                  |       +------------------+
| n                |       |     Driver       |
v                  |       +------------------+
+------------------+       | -id: int         |
| RaceRegistration |       | -code: String    |
+------------------+       | -name: String    |
| -id: int         |<------| -dob: Date       |
| -raceId: int(FK) |  n    | -nationality: String|
| -teamId: int(FK) |       | -bio: String     |
| -driverId: int(FK)|      | -teamId: int(FK) |
+------------------+       +------------------+

+------------------+       +------------------+
|   RaceResult     |       |      User        |
+------------------+       +------------------+
| -id: int         |       | -id: int         |
| -raceId: int(FK) |       | -username: String|
| -driverId: int(FK)|      | -password: String|
| -finishTime: Time|       | -role: String    |
| -lapsCompleted: int|     +------------------+
| -position: int   |
| -score: int      |
+------------------+

+-----------------------+
|  TeamStanding (DTO)   |
+-----------------------+
| -team: Team           |
| -teamName: String     |
| -teamOwner: String    |
| -totalScore: int      |
| -totalTime: Time      |
+-----------------------+

+-----------------------------+
| TeamRaceDetail (DTO)        |
+-----------------------------+
| -raceName: String           |
| -totalScore: int            |
| -totalTime: Time (2 drivers)|
+-----------------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: Race, Team, Driver, RaceRegistration, RaceResult, User |
| 3 | Tạo các DTO class boxes cho: TeamStanding, TeamRaceDetail |
| 4 | Tạo các view class boxes: HomeFrm, TeamRankingFrm |
| 5 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ Team |
|------|----------|------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>`, `<<dto>>`, hoặc `<<boundary>>` + tên class | `<<entity>> Team` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-id: int` `-code: String` `-name: String` `-brand: String` `-description: String` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | `+getTeamStandings(raceId: int): List<TeamStanding>` |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Race | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-laps: int`, `-location: String`, `-time: DateTime`, `-description: String` | `+getAllRaces(): List<Race>` |
| Team | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-brand: String`, `-description: String` | `+getTeamStandings(raceId: int): List<TeamStanding>`, `+getTeamRaceDetails(teamId: int, maxRaceId: int): List<TeamRaceDetail>` |
| Driver | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-dob: Date`, `-nationality: String`, `-bio: String`, `-teamId: int` | — |
| RaceRegistration | `<<entity>>` | `-id: int`, `-raceId: int`, `-teamId: int`, `-driverId: int` | — |
| RaceResult | `<<entity>>` | `-id: int`, `-raceId: int`, `-driverId: int`, `-finishTime: String`, `-lapsCompleted: int`, `-position: int`, `-score: float` | — |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |
| TeamStanding | `<<dto>>` | `-team: Team`, `-teamName: String`, `-teamOwner: String`, `-totalScore: int`, `-totalTime: Time` | — |
| TeamRaceDetail | `<<dto>>` | `-raceName: String`, `-totalScore: int`, `-totalTime: Time` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subTeamRanking` (Button — chọn chức năng BXH đội đua) |
| TeamRankingFrm | `<<boundary>>` | `inRace` (ComboBox — chọn chặng đua), `outsubListTeamStanding` (Table — bảng xếp hạng đội, click chọn), `outListRaceDetail` (Table — chi tiết kết quả từng chặng), `subBack` (Button — quay về BXH) |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu giữa các entity |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | Team chứa Driver |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | View gọi DAO |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 1..* | `N` | Nhiều đối tượng |

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| Team | Driver | Composition | 1 --- N | Một đội có nhiều tay đua |
| Race | RaceRegistration | Association | 1 --- N | Một chặng có nhiều đăng ký |
| Team | RaceRegistration | Association | 1 --- N | Một đội có nhiều đăng ký |
| Driver | RaceRegistration | Association | 1 --- N | Một tay đua có nhiều đăng ký |
| Race | RaceResult | Association | 1 --- N | Một chặng có nhiều kết quả |
| Driver | RaceResult | Association | 1 --- N | Một tay đua có nhiều kết quả |
| TeamRankingFrm | TeamStanding | Association | 1 --- N | View hiển thị BXH đội |
| TeamRankingFrm | TeamRaceDetail | Association | 1 --- N | View hiển thị chi tiết từng chặng |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ Team (1) --- (N) Driver (Composition)**

1. Tạo class `Team` với attribute: `-id: int`, `-name: String`, `-brand: String`.
2. Tạo class `Driver` với attribute: `-id: int`, `-name: String`, `-nationality: String`.
3. Kéo tool **Composition** → click `Team` → kéo sang `Driver`.
4. Đặt multiplicity: `1` (Team), `N` (Driver).

**Ví dụ 2: Vẽ Dependency từ TeamRankingFrm đến TeamDAO**

1. Tạo class `TeamRankingFrm` với stereotype `<<boundary>>`.
2. Tạo class `TeamDAO` với stereotype `<<control>>`.
3. Kéo tool **Dependency** → click `TeamRankingFrm` → kéo sang `TeamDAO`.
4. Ghi chú: `getTeamStandings()`, `getTeamRaceDetails()`.

---

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-3: Staff đăng nhập → giao diện Home. View class: **HomeFrm**.
Bước 4: Staff chọn Statistics → Team rankings. View class: **TeamRankingFrm**.
Bước 5: Giao diện bảng xếp hạng đội: combobox chọn chặng đua. UI: `inRace` (ComboBox — chọn chặng đua).
Bước 6: Staff chọn "Silverstone GP".
Bước 7-8: Hệ thống hiển thị bảng xếp hạng đội. UI: `outsubListTeamStanding` (Table — bảng xếp hạng đội, click chọn dòng được).
Bước 9: Staff nhấn vào dòng "Red Bull Racing".
Bước 10: Hệ thống hiển thị chi tiết kết quả từng chặng của đội. UI: `outListRaceDetail` (Table — chi tiết kết quả từng chặng).
Bước 11: Staff nhấn Back. UI: `subBack` (Button — quay về bảng xếp hạng).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Team rankings |
| TeamRankingFrm | Form | Giao diện xem bảng xếp hạng đội đua |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inRace` | ComboBox | TeamRankingFrm | Chọn chặng đua (lọc kết quả tính đến chặng đó) |
| `outsubListTeamStanding` | Table (clickable) | TeamRankingFrm | Bảng xếp hạng: Tên đội, Chủ sở hữu, Tổng điểm, Tổng thời gian |
| `outListRaceDetail` | Table | TeamRankingFrm | Chi tiết: Tên chặng, Tổng điểm (2 tay đua), Tổng thời gian |
| `subBack` | Button | TeamRankingFrm | Nút Back — quay về bảng xếp hạng |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getAllRaces()` | - | List\<Race\> | Race |
| `getTeamStandings()` | raceId | List\<TeamStanding\> | Team, RaceResult |
| `getTeamRaceDetails()` | teamId, maxRaceId | List\<TeamRaceDetail\> | RaceResult, Race |

**Tong hop:**

- View classes: HomeFrm, TeamRankingFrm
- Methods: getAllRaces(), getTeamStandings(), getTeamRaceDetails()

### Quan hệ

| Quan hệ | Kiểu | Giải thích |
|----------|------|------------|
| Race → RaceRegistration | 1-n | Một chặng có nhiều đăng ký |
| Team → RaceRegistration | 1-n | Một đội có nhiều đăng ký |
| Driver → RaceRegistration | 1-n | Một tay đua có nhiều đăng ký |
| Race → RaceResult | 1-n | Một chặng có nhiều kết quả |
| Driver → RaceResult | 1-n | Một tay đua có nhiều kết quả |
| Team → Driver | 1-n | Một đội có nhiều tay đua |

---

## Câu 3: Thiết kế tĩnh

### View classes

**TeamRankingFrm:**
- `inRace`: combobox chọn chặng đua
- `outsubListTeamStanding`: bảng xếp hạng đội (click được), hiển thị: tên đội, chủ sở hữu, tổng điểm, tổng thời gian
- `outListRaceDetail`: bảng chi tiết kết quả từng chặng của đội được chọn
- `subBack`: nút Back quay về

### UI Elements

| Thành phần | Loại | Mô tả |
|-----------|------|-------|
| inRace | ComboBox | Dropdown danh sách chặng đua |
| outsubListTeamStanding | Table (clickable) | Bảng xếp hạng: Tên đội, Chủ sở hữu, Tổng điểm, Tổng thời gian |
| outListRaceDetail | Table | Chi tiết: Tên chặng, Tổng điểm (2 tay đua), Tổng thời gian (2 tay đua) |
| subBack | Button | Nút quay về |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| RaceDAO | `getAllRaces(): List<Race>` | Lấy danh sách chặng đua |
| TeamDAO | `getTeamStandings(raceId): List<TeamStanding>` | Tính bảng xếp hạng đội đến chặng đã chọn |
| TeamDAO | `getTeamRaceDetails(teamId, raceId): List<TeamRaceDetail>` | Lấy chi tiết kết quả từng chặng của 1 đội |

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Race | Entity | id: int (PK), code: String, name: String, laps: int, location: String, time: String, description: String |
| Team | Entity | id: int (PK), code: String, name: String, brand: String, description: String |
| Driver | Entity | id: int (PK), code: String, name: String, dob: Date, nationality: String, bio: String, team: Team (object) |
| RaceRegistration | Entity | id: int (PK), race: Race (object), team: Team (object), driver: Driver (object) |
| RaceResult | Entity | id: int (PK), race: Race (object), driver: Driver (object), finishTime: String, lapsCompleted: int, position: int, score: float |
| User | Entity | id: int (PK), username: String, password: String, role: String |

### Entity types

| Entity | Loại | Mô tả |
|--------|------|-------|
| Race | Persistent | Lưu thông tin chặng đua |
| Team | Persistent | Lưu thông tin đội đua |
| Driver | Persistent | Lưu thông tin tay đua |
| RaceRegistration | Persistent | Lưu đăng ký tay đua vào chặng |
| RaceResult | Persistent | Lưu kết quả thi đấu |
| User | Persistent | Lưu thông tin người dùng |
| TeamStanding | Transient (DTO) | Kết quả tính toán tổng hợp, không lưu DB |
| TeamRaceDetail | Transient (DTO) | Chi tiết kết quả từng chặng của đội, không lưu DB |

### Database Design

**tblRace:** ID (PK), code, name, laps, location, time, description
**tblTeam:** ID (PK), code, name, brand, description
**tblDriver:** ID (PK), code, name, dob, nationality, bio, teamID (FK → tblTeam)
**tblRaceRegistration:** ID (PK), raceID (FK → tblRace), teamID (FK → tblTeam), driverID (FK → tblDriver)
**tblRaceResult:** ID (PK), raceID (FK → tblRace), driverID (FK → tblDriver), finishTime, lapsCompleted, position, score
**tblUser:** ID (PK), username, password, role

### Visual Paradigm — Hướng dẫn vẽ Class Diagram

1. Tạo 6 persistent class: Race, Team, Driver, RaceRegistration, RaceResult, User.
2. Tạo 2 DTO class: TeamStanding, TeamRaceDetail.
3. Draw association giữa các persistent class.
4. TeamStanding và TeamRaceDetail không có association trực tiếp, được populate từ query JOIN.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `TeamRankingFrm`, `RaceDAO`, `TeamDAO`.
2. Vẽ message flow theo thứ tự bên dưới.
3. Sử dụng alt fragment cho trường hợp click vào đội để xem chi tiết.

### ASCII Sequence Diagram

```
Staff              TeamRankingFrm    RaceDAO    TeamDAO
  |                      |               |          |
  |---login-------------->|              |          |
  |                      |--checkLogin-->|          |
  |                      |<--true--------|          |
  |                      |               |          |
  |---select Statistics-->|              |          |
  |---select Team rank--->|              |          |
  |                      |               |          |
  |                      |--getAllRaces->|          |
  |                      |<--List<Race>--|          |
  |                      |               |          |
  |---select "Silverstone"              |          |
  |                      |               |          |
  |                      |--getTeamStandings(2)---->|
  |                      |<--List<TeamStanding>------|
  |                      |               |          |
  |                      |---display team standings table
  |                      |  (sorted by totalScore desc, totalTime asc)
  |                      |               |          |
  |---click "Red Bull Racing"            |          |
  |                      |               |          |
  |                      |--getTeamRaceDetails(1,2)->|
  |                      |<--List<TeamRaceDetail>----|
  |                      |               |          |
  |                      |---display race detail table
  |                      |  Monaco: 43đ, 3:02:05
  |                      |  Silverstone: 40đ, 2:51:40
  |                      |               |          |
  |---click Back--------->|              |          |
  |                      |---hide detail, show standings
  |<--standings----------|               |          |
```

### Bảng chi tiết các bước

| Bước | Sender | Receiver | Message | Loại |
|------|--------|----------|---------|------|
| 1 | Staff | TeamRankingFrm | login(username, password) | Sync |
| 2 | TeamRankingFrm | UserDAO | checkLogin("staff01", "******") | Sync |
| 3 | UserDAO | TeamRankingFrm | return true | Return |
| 4 | Staff | TeamRankingFrm | selectMenu("Statistics") | Sync |
| 5 | Staff | TeamRankingFrm | selectSubMenu("Team rankings") | Sync |
| 6 | TeamRankingFrm | RaceDAO | getAllRaces() | Sync |
| 7 | RaceDAO | TeamRankingFrm | return List\<Race\> | Return |
| 8 | TeamRankingFrm | (self) | populate race combo box | Self |
| 9 | Staff | TeamRankingFrm | selectRace("Silverstone GP") | Sync |
| 10 | TeamRankingFrm | TeamDAO | getTeamStandings(raceId=2) | Sync |
| 11 | TeamDAO | (self) | SELECT team, SUM(score), SUM(finishTime) FROM RaceResult JOIN Driver JOIN Team WHERE raceId <= 2 GROUP BY team | Self |
| 12 | TeamDAO | TeamRankingFrm | return List\<TeamStanding\> | Return |
| 13 | TeamRankingFrm | (self) | sort by totalScore DESC, totalTime ASC | Self |
| 14 | TeamRankingFrm | (self) | display team standings table (3 rows) | Self |
| 15 | Staff | TeamRankingFrm | clickRow("Red Bull Racing") | Sync |
| 16 | TeamRankingFrm | TeamDAO | getTeamRaceDetails(teamId=1, maxRaceId=2) | Sync |
| 17 | TeamDAO | (self) | SELECT race, SUM(score), SUM(finishTime) FROM RaceResult JOIN Driver JOIN Race WHERE teamId = 1 AND raceId <= 2 GROUP BY race | Self |
| 18 | TeamDAO | TeamRankingFrm | return List\<TeamRaceDetail\> (2 races) | Return |
| 19 | TeamRankingFrm | (self) | display detail table: Monaco (43đ, 3:02:05), Silverstone (40đ, 2:51:40) | Self |
| 20 | Staff | TeamRankingFrm | clickBack() | Sync |
| 21 | TeamRankingFrm | (self) | hide detail, show standings | Self |
| 22 | TeamRankingFrm | Staff | display standings table | Async |

---

## Câu 5: Blackbox Testcase

### TC01: Xem bảng xếp hạng đội đua thành công

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
| 3 | MCL | McLaren | Mercedes | British racing team |

**tblDriver:**
| ID | code | name | dob | nationality | teamID |
|----|------|------|-----|-------------|--------|
| 1 | VER | Max Verstappen | 1997-09-30 | Dutch | 1 |
| 2 | PER | Sergio Perez | 1990-01-26 | Mexican | 1 |
| 3 | HAM | Lewis Hamilton | 1985-01-07 | British | 2 |
| 4 | RUS | George Russell | 1998-02-15 | British | 2 |
| 5 | NOR | Lando Norris | 1999-11-13 | British | 3 |

**tblRaceRegistration:**
| ID | raceID | teamID | driverID |
|----|--------|--------|----------|
| 1 | 1 | 1 | 1 |
| 2 | 1 | 1 | 2 |
| 3 | 1 | 2 | 3 |
| 4 | 1 | 2 | 4 |
| 5 | 1 | 3 | 5 |
| 6 | 2 | 1 | 1 |
| 7 | 2 | 1 | 2 |
| 8 | 2 | 2 | 3 |
| 9 | 2 | 2 | 4 |
| 10 | 2 | 3 | 5 |

**tblRaceResult:**
| ID | raceID | driverID | finishTime | lapsCompleted | position | score |
|----|--------|----------|------------|---------------|----------|-------|
| 1 | 1 | 1 | 1:30:45.123 | 78 | 1 | 25 |
| 2 | 1 | 2 | 1:31:20.456 | 78 | 2 | 18 |
| 3 | 1 | 3 | 1:31:55.789 | 78 | 3 | 15 |
| 4 | 1 | 4 | 1:32:10.012 | 78 | 4 | 12 |
| 5 | 1 | 5 | null | 45 | 5 | 0 |
| 6 | 2 | 1 | 1:25:30.000 | 52 | 1 | 25 |
| 7 | 2 | 2 | 1:26:10.000 | 52 | 3 | 15 |
| 8 | 2 | 3 | 1:25:50.000 | 52 | 2 | 18 |
| 9 | 2 | 4 | 1:26:30.000 | 52 | 4 | 12 |
| 10 | 2 | 5 | null | 30 | 5 | 0 |

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Đăng nhập thành công, hiển thị Home |
| 3 | Chọn **Statistics** → **Team rankings** | Giao diện: combobox chọn chặng đua |
| 4 | Chọn "Silverstone GP" từ combobox | Bảng xếp hạng hiển thị 3 đội |
| 5 | Kiểm tra bảng xếp hạng | Dòng 1: Red Bull Racing, Honda, 83đ, 5:53:45. Dòng 2: Mercedes AMG, Mercedes, 57đ, 5:56:25. Dòng 3: McLaren, Mercedes, 0đ, -- |
| 6 | Nhấn vào dòng "Red Bull Racing" | Bảng chi tiết hiện ra: Monaco GP (43đ, 3:02:05), Silverstone GP (40đ, 2:51:40) |
| 7 | Nhấn nút **Back** | Bảng chi tiết ẩn, quay về bảng xếp hạng |

### Database sau khi test

**Không có thay đổi** — chức năng xem bảng xếp hạng chỉ đọc dữ liệu, không ghi.
