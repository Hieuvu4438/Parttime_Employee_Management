# Subject No. 32 — F1 Championship — Module "View the racers' standings"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Xem bảng xếp hạng tay đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register to racing, Update results, Statistics. |
| 4 | Staff chọn **Statistics** → **Racer standings**. |
| 5 | Giao diện bảng xếp hạng tay đua xuất hiện: combobox chọn chặng đua (để lọc kết quả tính đến chặng đó). |
| 6 | Staff chọn "Monaco GP" (chặng 1) từ combobox. |
| 7 | Hệ thống hiển thị bảng xếp hạng tay đua: cột Tên tay đua, Quốc tịch, Tên đội, Tổng điểm, Tổng thời gian. Sắp xếp: tổng điểm giảm dần → tổng thời gian tăng dần. |
| 8 | Bảng hiển thị: Max Verstappen (Dutch, Red Bull, 25đ, 1:30:45), Sergio Perez (Mexican, Red Bull, 18đ, 1:31:20), Lewis Hamilton (British, Mercedes, 15đ, 1:31:55), George Russell (British, Mercedes, 12đ, 1:32:10), Lando Norris (British, McLaren, 0đ, --). |
| 9 | Staff nhấn vào dòng "Max Verstappen". |
| 10 | Hệ thống hiển thị chi tiết kết quả từng chặng của Max: Monaco GP (1st, 25đ, 1:30:45.123). |
| 11 | Staff nhấn nút **Back** để quay về bảng xếp hạng. |

### Kịch bản ngoại lệ

- **EL1:** Staff không chọn chặng đua → hệ thống cảnh báo "Vui long chon chang dua".
- **EL2:** Chưa có kết quả cho chặng đã chọn → hệ thống hiển thị bảng rỗng với thông báo "Chua co ket qua".

---

## Câu 2: Entity Class Diagram

### Mô tả nghiệp vụ

Hệ thống thống kê bảng xếp hạng tay đua dựa trên kết quả các chặng đã đấu. Tổng điểm là tổng score của tay đua qua các chặng. Tổng thời gian là tổng finishTime qua các chặng. Chỉ tính các tay đua đã hoàn thành (không DNF). Bảng xếp hạng sắp xếp theo tổng điểm giảm dần, nếu bằng điểm thì theo tổng thời gian tăng dần.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
| Race (Chặng đua) | Entity class | Đối tượng lưu thông tin chặng đua |
| Team (Đội đua) | Entity class | Đối tượng lưu thông tin đội |
| Driver (Tay đua) | Entity class | Đối tượng lưu thông tin tay đua |
| RaceRegistration | Entity class | Bản ghi đăng ký tay đua vào chặng |
| RaceResult | Entity class | Kết quả thi đấu của tay đua ở mỗi chặng |
| User | Entity class | Người dùng hệ thống (staff) |
| RacerStanding | Derived object | Không phải entity, là kết quả tính toán từ RaceResult |

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
|   RacerStanding (DTO) |
+-----------------------+
| -driver: Driver       |
| -nationality: String  |
| -teamName: String     |
| -totalScore: int      |
| -totalTime: Time      |
+-----------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: Race, Team, Driver, RaceRegistration, RaceResult, User |
| 3 | Tạo DTO class box cho: RacerStanding |
| 4 | Tạo các view class boxes: HomeFrm, RacerStandingFrm |
| 5 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ Driver |
|------|----------|--------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>`, `<<dto>>`, hoặc `<<boundary>>` + tên class | `<<entity>> Driver` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-id: int` `-code: String` `-name: String` `-dob: Date` `-nationality: String` `-teamId: int` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | `+getRacerStandings(raceId: int): List<RacerStanding>` |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Race | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-laps: int`, `-location: String`, `-time: DateTime`, `-description: String` | `+getAllRaces(): List<Race>` |
| Team | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-brand: String`, `-description: String` | — |
| Driver | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-dob: Date`, `-nationality: String`, `-bio: String`, `-teamId: int` | `+getRacerStandings(raceId: int): List<RacerStanding>` |
| RaceRegistration | `<<entity>>` | `-id: int`, `-raceId: int`, `-teamId: int`, `-driverId: int` | — |
| RaceResult | `<<entity>>` | `-id: int`, `-raceId: int`, `-driverId: int`, `-finishTime: String`, `-lapsCompleted: int`, `-position: int`, `-score: float` | `+getResultsByDriver(driverId: int): List<RaceResult>` |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |
| RacerStanding | `<<dto>>` | `-driver: Driver`, `-nationality: String`, `-teamName: String`, `-totalScore: int`, `-totalTime: Time` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subRacerStanding` (Button — chọn chức năng BXH tay đua) |
| RacerStandingFrm | `<<boundary>>` | `inRace` (ComboBox — chọn chặng đua), `outsubListStanding` (Table — bảng xếp hạng tay đua, click chọn), `outListDetail` (Table — chi tiết kết quả từng chặng), `subBack` (Button — quay về BXH) |

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
| RacerStandingFrm | RacerStanding | Association | 1 --- N | View hiển thị danh sách BXH |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ Driver (1) --- (N) RaceResult (Association)**

1. Tạo class `Driver` với attribute: `-id: int`, `-name: String`, `-nationality: String`.
2. Tạo class `RaceResult` với attribute: `-id: int`, `-finishTime: String`, `-position: int`, `-score: float`.
3. Kéo tool **Association** → click `Driver` → kéo sang `RaceResult`.
4. Đặt multiplicity: `1` (Driver), `N` (RaceResult).

**Ví dụ 2: Vẽ Dependency từ RacerStandingFrm đến DriverDAO**

1. Tạo class `RacerStandingFrm` với stereotype `<<boundary>>`.
2. Tạo class `DriverDAO` với stereotype `<<control>>`.
3. Kéo tool **Dependency** → click `RacerStandingFrm` → kéo sang `DriverDAO`.
4. Ghi chú: `getRacerStandings()`.

---

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-3: Staff đăng nhập → giao diện Home. View class: **HomeFrm**.
Bước 4: Staff chọn Statistics → Racer standings. View class: **RacerStandingFrm**.
Bước 5: Giao diện bảng xếp hạng: combobox chọn chặng đua. UI: `inRace` (ComboBox — chọn chặng đua).
Bước 6: Staff chọn "Monaco GP".
Bước 7-8: Hệ thống hiển thị bảng xếp hạng tay đua. UI: `outsubListStanding` (Table — bảng xếp hạng, click chọn dòng được).
Bước 9: Staff nhấn vào dòng "Max Verstappen".
Bước 10: Hệ thống hiển thị chi tiết kết quả từng chặng. UI: `outListDetail` (Table — chi tiết kết quả từng chặng).
Bước 11: Staff nhấn Back. UI: `subBack` (Button — quay về bảng xếp hạng).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Racer standings |
| RacerStandingFrm | Form | Giao diện xem bảng xếp hạng tay đua |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inRace` | ComboBox | RacerStandingFrm | Chọn chặng đua (lọc kết quả tính đến chặng đó) |
| `outsubListStanding` | Table (clickable) | RacerStandingFrm | Bảng xếp hạng: Tên, Quốc tịch, Đội, Tổng điểm, Tổng thời gian |
| `outListDetail` | Table | RacerStandingFrm | Chi tiết: Tên chặng, Thứ hạng, Điểm, Thời gian về đích |
| `subBack` | Button | RacerStandingFrm | Nút Back — quay về bảng xếp hạng |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getAllRaces()` | - | List\<Race\> | Race |
| `getRacerStandings()` | raceId | List\<RacerStanding\> | Driver, RaceResult |
| `getResultsByDriver()` | driverId | List\<RaceResult\> | RaceResult |

**Tong hop:**

- View classes: HomeFrm, RacerStandingFrm
- Methods: getAllRaces(), getRacerStandings(), getResultsByDriver()

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

**RacerStandingFrm:**
- `inRace`: combobox chọn chặng đua
- `outsubListStanding`: bảng xếp hạng tay đua (click được), hiển thị: tên, quốc tịch, đội, tổng điểm, tổng thời gian
- `outListDetail`: bảng chi tiết kết quả từng chặng của tay đua được chọn
- `subBack`: nút Back quay về

### UI Elements

| Thành phần | Loại | Mô tả |
|-----------|------|-------|
| inRace | ComboBox | Dropdown danh sách chặng đua |
| outsubListStanding | Table (clickable) | Bảng xếp hạng: Tên, Quốc tịch, Đội, Tổng điểm, Tổng thời gian |
| outListDetail | Table | Chi tiết: Tên chặng, Thứ hạng, Điểm, Thời gian về đích |
| subBack | Button | Nút quay về |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| RaceDAO | `getAllRaces(): List<Race>` | Lấy danh sách chặng đua |
| DriverDAO | `getRacerStandings(raceId): List<RacerStanding>` | Tính bảng xếp hạng tay đua đến chặng đã chọn |
| RaceResultDAO | `getResultsByDriver(driverId): List<RaceResult>` | Lấy chi tiết kết quả từng chặng của 1 tay đua |

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
| RacerStanding | Transient (DTO) | Kết quả tính toán tổng hợp, không lưu DB |

### Database Design

**tblRace:** ID (PK), code, name, laps, location, time, description
**tblTeam:** ID (PK), code, name, brand, description
**tblDriver:** ID (PK), code, name, dob, nationality, bio, teamID (FK → tblTeam)
**tblRaceRegistration:** ID (PK), raceID (FK → tblRace), teamID (FK → tblTeam), driverID (FK → tblDriver)
**tblRaceResult:** ID (PK), raceID (FK → tblRace), driverID (FK → tblDriver), finishTime, lapsCompleted, position, score
**tblUser:** ID (PK), username, password, role

### Visual Paradigm — Hướng dẫn vẽ Class Diagram

1. Tạo 6 persistent class: Race, Team, Driver, RaceRegistration, RaceResult, User.
2. Tạo 1 DTO class: RacerStanding.
3. Draw association giữa các persistent class như trên.
4. RacerStanding không có association trực tiếp, được populate từ query JOIN RaceResult + Driver + Team.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `RacerStandingFrm`, `RaceDAO`, `DriverDAO`, `RaceResultDAO`.
2. Vẽ message flow theo thứ tự bên dưới.
3. Sử dụng alt fragment cho trường hợp click vào tay đua để xem chi tiết.

### ASCII Sequence Diagram

```
Staff              RacerStandingFrm    RaceDAO    DriverDAO    RaceResultDAO
  |                      |                |            |              |
  |---login-------------->|               |            |              |
  |                      |--checkLogin--->|            |              |
  |                      |<--true---------|            |              |
  |                      |                |            |              |
  |---select Statistics-->|               |            |              |
  |---select Racer std--->|               |            |              |
  |                      |                |            |              |
  |                      |--getAllRaces-->|            |              |
  |                      |<--List<Race>---|            |              |
  |                      |                |            |              |
  |---select "Monaco GP"->|               |            |              |
  |                      |                |            |              |
  |                      |--getRacerStandings(1)------>|              |
  |                      |<--List<RacerStanding>--------|              |
  |                      |                |            |              |
  |                      |---display standings table---|              |
  |                      |  (sorted by totalScore desc, totalTime asc)|
  |                      |                |            |              |
  |---click "Max Verstappen"              |            |              |
  |                      |                |            |              |
  |                      |--getResultsByDriver(1)------------------->|
  |                      |<--List<RaceResult>-------------------------|
  |                      |                |            |              |
  |                      |---display detail table-----|              |
  |                      |  Monaco GP: 1st, 25đ, 1:30:45            |
  |                      |                |            |              |
  |---click Back--------->|               |            |              |
  |                      |---hide detail, show standings              |
  |<--standings----------|                |            |              |
```

### Bảng chi tiết các bước

| Bước | Sender | Receiver | Message | Loại |
|------|--------|----------|---------|------|
| 1 | Staff | RacerStandingFrm | login(username, password) | Sync |
| 2 | RacerStandingFrm | UserDAO | checkLogin("staff01", "******") | Sync |
| 3 | UserDAO | RacerStandingFrm | return true | Return |
| 4 | Staff | RacerStandingFrm | selectMenu("Statistics") | Sync |
| 5 | Staff | RacerStandingFrm | selectSubMenu("Racer standings") | Sync |
| 6 | RacerStandingFrm | RaceDAO | getAllRaces() | Sync |
| 7 | RaceDAO | RacerStandingFrm | return List\<Race\> | Return |
| 8 | RacerStandingFrm | (self) | populate race combo box | Self |
| 9 | Staff | RacerStandingFrm | selectRace("Monaco GP") | Sync |
| 10 | RacerStandingFrm | DriverDAO | getRacerStandings(raceId=1) | Sync |
| 11 | DriverDAO | (self) | SELECT driver, team, SUM(score), SUM(finishTime) FROM RaceResult JOIN Driver JOIN Team WHERE raceId <= 1 GROUP BY driver | Self |
| 12 | DriverDAO | RacerStandingFrm | return List\<RacerStanding\> | Return |
| 13 | RacerStandingFrm | (self) | sort by totalScore DESC, totalTime ASC | Self |
| 14 | RacerStandingFrm | (self) | display standings table (5 rows) | Self |
| 15 | Staff | RacerStandingFrm | clickRow("Max Verstappen") | Sync |
| 16 | RacerStandingFrm | RaceResultDAO | getResultsByDriver(driverId=1) | Sync |
| 17 | RaceResultDAO | (self) | SELECT * FROM RaceResult WHERE driverId = 1 | Self |
| 18 | RaceResultDAO | RacerStandingFrm | return List\<RaceResult\> (1 result) | Return |
| 19 | RacerStandingFrm | (self) | display detail table: Monaco GP, 1st, 25đ, 1:30:45.123 | Self |
| 20 | Staff | RacerStandingFrm | clickBack() | Sync |
| 21 | RacerStandingFrm | (self) | hide detail, show standings | Self |
| 22 | RacerStandingFrm | Staff | display standings table | Async |

---

## Câu 5: Blackbox Testcase

### TC01: Xem bảng xếp hạng tay đua thành công

**Database trước khi test:**

**tblRace:**
| ID | code | name | laps | location | time |
|----|------|------|------|----------|------|
| 1 | MON | Monaco GP | 78 | Monte Carlo | 2026-05-25 14:00 |
| 2 | SIL | Silverstone GP | 52 | Silverstone | 2026-07-06 15:00 |

**tblTeam:**
| ID | code | name | brand |
|----|------|------|-------|
| 1 | RBR | Red Bull Racing | Honda |
| 2 | MER | Mercedes AMG | Mercedes |
| 3 | MCL | McLaren | Mercedes |

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

**tblRaceResult:**
| ID | raceID | driverID | finishTime | lapsCompleted | position | score |
|----|--------|----------|------------|---------------|----------|-------|
| 1 | 1 | 1 | 1:30:45.123 | 78 | 1 | 25 |
| 2 | 1 | 2 | 1:31:20.456 | 78 | 2 | 18 |
| 3 | 1 | 3 | 1:31:55.789 | 78 | 3 | 15 |
| 4 | 1 | 4 | 1:32:10.012 | 78 | 4 | 12 |
| 5 | 1 | 5 | null | 45 | 5 | 0 |

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Đăng nhập thành công, hiển thị Home |
| 3 | Chọn **Statistics** → **Racer standings** | Giao diện: combobox chọn chặng đua |
| 4 | Chọn "Monaco GP" từ combobox | Bảng xếp hạng hiển thị 5 tay đua |
| 5 | Kiểm tra bảng xếp hạng | Dòng 1: Max Verstappen, Dutch, Red Bull Racing, 25đ, 1:30:45. Dòng 2: Sergio Perez, Mexican, Red Bull Racing, 18đ, 1:31:20. Dòng 3: Lewis Hamilton, British, Mercedes AMG, 15đ, 1:31:55. Dòng 4: George Russell, British, Mercedes AMG, 12đ, 1:32:10. Dòng 5: Lando Norris, British, McLaren, 0đ, -- |
| 6 | Nhấn vào dòng "Max Verstappen" | Bảng chi tiết hiện ra: Monaco GP, 1st, 25đ, 1:30:45.123 |
| 7 | Nhấn nút **Back** | Bảng chi tiết ẩn, quay về bảng xếp hạng |

### Database sau khi test

**Không có thay đổi** — chức năng xem bảng xếp hạng chỉ đọc dữ liệu, không ghi.
