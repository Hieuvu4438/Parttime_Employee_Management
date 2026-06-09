# Subject No. 31 — F1 Championship — Module "Update results"

> **Domain:** F1 Championship Management

---

## Câu 1: Scenario — Cập nhật kết quả chặng đua

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống. Giao diện Login xuất hiện. |
| 2 | Staff nhập username `staff01`, password `******`, nhấn Login. |
| 3 | Giao diện Home xuất hiện với các chức năng: Register to racing, Update results, Statistics. |
| 4 | Staff chọn chức năng **Update results**. |
| 5 | Giao diện cập nhật kết quả xuất hiện: combobox chọn chặng đua (Race). |
| 6 | Staff chọn chặng "Monaco GP" từ combobox. |
| 7 | Hệ thống hiển thị danh sách các tay đua đã đăng ký cho chặng Monaco GP dưới dạng bảng: cột Tên tay đua, Tên đội, ô nhập Finish Time, ô nhập Laps Completed. |
| 8 | Staff nhập kết quả cho từng tay đua: Max Verstappen (1:30:45.123, 78 laps), Sergio Perez (1:31:20.456, 78 laps), Lewis Hamilton (1:31:55.789, 78 laps), George Russell (1:32:10.012, 78 laps), Lando Norris (DNF, 45 laps). |
| 9 | Staff nhấn nút **Save**. |
| 10 | Hệ thống tự động xếp hạng theo thời gian hoàn thành: Max (1st), Sergio (2nd), Hamilton (3rd), Russell (4th). Lando Norris xếp cuối (DNF). |
| 11 | Hệ thống tính điểm theo thứ tự: 25, 18, 15, 12, 10, 8, 6, 4, 2, 1. Max = 25đ, Sergio = 18đ, Hamilton = 15đ, Russell = 12đ, Norris = 0đ (DNF). |
| 12 | Hệ thống lưu kết quả vào database (tblRaceResult) và thông báo "Cap nhat ket qua thanh cong". |

### Kịch bản ngoại lệ

- **EL1:** Staff không chọn chặng đua → hệ thống cảnh báo "Vui long chon chang dua".
- **EL2:** Staff bỏ trống ô Finish Time hoặc Laps Completed → hệ thống cảnh báo "Vui long nhap day du ket qua".
- **EL3:** Staff nhập Laps Completed > số vòng của chặng đua → hệ thống cảnh báo "So vuong vuot qua gioi han".

---

## Câu 2: Entity Class Diagram

### Mô tả nghiệp vụ

Hệ thống quản lý giải đua F1 Championship. Staff nhập kết quả thi đấu cho từng tay đua đã đăng ký ở mỗi chặng. Hệ thống tự động xếp hạng theo thời gian và tính điểm theo quy tắc: top 10 được 25,18,15,12,10,8,6,4,2,1 điểm. Tay đua bỏ cuộc (DNF) được 0 điểm.

### Trích xuất danh từ

| Danh từ | Phân loại | Lý do |
|---------|-----------|-------|
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

+------------------+
|   RaceResult     |
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

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-3: Staff đăng nhập → giao diện Home. View class: **HomeFrm**.
Bước 4: Staff chọn Update results. View class: **UpdateResultFrm**.
Bước 5: Giao diện cập nhật kết quả: combobox chọn chặng đua. UI: `inRace` (ComboBox — chọn chặng đua).
Bước 6: Staff chọn "Monaco GP".
Bước 7: Hệ thống hiển thị danh sách tay đua đã đăng ký với ô nhập kết quả. UI: `outsubListDriverResult` (Table — danh sách tay đua với ô nhập).
Bước 8: Staff nhập kết quả cho từng tay đua. UI: `inFinishTime` (TextField per row — nhập thời gian về đích), `inLapsCompleted` (TextField per row — nhập số vòng hoàn thành).
Bước 9: Staff nhấn Save. UI: `subSave` (Button — lưu kết quả).
Bước 10-12: Hệ thống xếp hạng theo thời gian, tính điểm (25,18,15,12,...), lưu RaceResult, thông báo thành công.

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Update results |
| UpdateResultFrm | Form | Giao diện cập nhật kết quả chặng đua |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inRace` | ComboBox | UpdateResultFrm | Chọn chặng đua |
| `outsubListDriverResult` | Table | UpdateResultFrm | Bảng: Tên tay đua, Tên đội, Finish Time (input), Laps (input) |
| `inFinishTime` | TextField (per row) | UpdateResultFrm | Ô nhập thời gian về đích cho từng tay đua |
| `inLapsCompleted` | TextField (per row) | UpdateResultFrm | Ô nhập số vòng hoàn thành cho từng tay đua |
| `subSave` | Button | UpdateResultFrm | Nút Save — lưu kết quả |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getAllRaces()` | - | List\<Race\> | Race |
| `getRegistrationsByRace()` | raceId | List\<RaceRegistration\> | RaceRegistration |
| `addResults()` | List\<RaceResult\> | boolean | RaceResult |

**Tong hop:**

- View classes: HomeFrm, UpdateResultFrm
- Methods: getAllRaces(), getRegistrationsByRace(), addResults()

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

**UpdateResultFrm:**
- `inRace`: combobox chọn chặng đua
- `outsubListDriverResult`: bảng danh sách tay đua đã đăng ký với ô nhập kết quả
- `inFinishTime[]`: mảng ô nhập thời gian về đích (mỗi tay đua 1 ô)
- `inLapsCompleted[]`: mảng ô nhập số vòng hoàn thành (mỗi tay đua 1 ô)
- `subSave`: nút Save

### UI Elements

| Thành phần | Loại | Mô tả |
|-----------|------|-------|
| inRace | ComboBox | Dropdown danh sách chặng đua |
| outsubListDriverResult | Table | Bảng: Tên tay đua, Tên đội, Finish Time (input), Laps (input) |
| inFinishTime | TextField (per row) | Ô nhập thời gian về đích |
| inLapsCompleted | TextField (per row) | Ô nhập số vòng hoàn thành |
| subSave | Button | Nút lưu kết quả |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|-------|
| UserDAO | `checkLogin(username, password): boolean` | Kiểm tra đăng nhập |
| RaceDAO | `getAllRaces(): List<Race>` | Lấy danh sách chặng đua |
| RaceRegistrationDAO | `getRegistrationsByRace(raceId): List<RaceRegistration>` | Lấy danh sách tay đua đã đăng ký theo chặng |
| RaceResultDAO | `addResult(result): boolean` | Lưu kết quả cho 1 tay đua |
| RaceResultDAO | `addResults(results): boolean` | Lưu danh sách kết quả (batch) |

### Entity types

| Entity | Loại | Mô tả |
|--------|------|-------|
| Race | Persistent | Lưu thông tin chặng đua |
| Team | Persistent | Lưu thông tin đội đua |
| Driver | Persistent | Lưu thông tin tay đua |
| RaceRegistration | Persistent | Lưu đăng ký tay đua vào chặng |
| RaceResult | Persistent | Lưu kết quả thi đấu (position, score, finishTime) |
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
2. Thêm attributes cho mỗi class.
3. Draw association: Race → RaceRegistration (1-n), Team → RaceRegistration (1-n), Driver → RaceRegistration (1-n).
4. Draw association: Race → RaceResult (1-n), Driver → RaceResult (1-n).
5. Draw association: Team → Driver (1-n).

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Các bước vẽ

1. Tạo lifeline: `Staff`, `UpdateResultFrm`, `RaceDAO`, `RaceRegistrationDAO`, `RaceResultDAO`.
2. Vẽ message flow theo thứ tự bên dưới.
3. Sử dụng loop fragment cho việc nhập kết quả nhiều tay đua.
4. Thêm alt fragment cho DNF (không hoàn thành).

### ASCII Sequence Diagram

```
Staff              UpdateResultFrm    RaceDAO    RaceRegistrationDAO    RaceResultDAO
  |                      |               |               |                    |
  |---login-------------->|              |               |                    |
  |                      |--checkLogin-->|               |                    |
  |                      |<--true--------|               |                    |
  |                      |               |               |                    |
  |---select Update------>|              |               |                    |
  |                      |               |               |                    |
  |                      |--getAllRaces->|               |                    |
  |                      |<--List<Race>--|               |                    |
  |                      |               |               |                    |
  |---select "Monaco GP"->|              |               |                    |
  |                      |               |               |                    |
  |                      |--getRegistrationsByRace----->|                    |
  |                      |<--List<Reg>-------------------|                    |
  |                      |               |               |                    |
  |                      |---display table with input fields                  |
  |                      |               |               |                    |
  |---enter results------>|              |               |                    |
  |  [Max: 1:30:45, 78]  |              |               |                    |
  |  [Sergio: 1:31:20, 78]|             |               |                    |
  |  [Hamilton: 1:31:55, 78]|           |               |                    |
  |  [Russell: 1:32:10, 78]|            |               |                    |
  |  [Norris: DNF, 45]   |              |               |                    |
  |                      |               |               |                    |
  |---click Save--------->|              |               |                    |
  |                      |               |               |                    |
  |                      |---calculate positions by finishTime                |
  |                      |---calculate scores: 25,18,15,12,0                  |
  |                      |               |               |                    |
  |                      |--addResults------------------------------------->|
  |                      |                                              |--INSERT x5|
  |                      |<--true--------------------------------------------|
  |                      |               |               |                    |
  |                      |--show "Cap nhat thanh cong"----|                    |
  |<--success------------|               |               |                    |
```

### Bảng chi tiết các bước

| Bước | Sender | Receiver | Message | Loại |
|------|--------|----------|---------|------|
| 1 | Staff | UpdateResultFrm | login(username, password) | Sync |
| 2 | UpdateResultFrm | UserDAO | checkLogin("staff01", "******") | Sync |
| 3 | UserDAO | UpdateResultFrm | return true | Return |
| 4 | Staff | UpdateResultFrm | selectMenu("Update results") | Sync |
| 5 | UpdateResultFrm | RaceDAO | getAllRaces() | Sync |
| 6 | RaceDAO | UpdateResultFrm | return List\<Race\> | Return |
| 7 | UpdateResultFrm | (self) | populate race combo box | Self |
| 8 | Staff | UpdateResultFrm | selectRace("Monaco GP") | Sync |
| 9 | UpdateResultFrm | RaceRegistrationDAO | getRegistrationsByRace(raceId=1) | Sync |
| 10 | RaceRegistrationDAO | UpdateResultFrm | return List\<RaceRegistration\> (5 registrations) | Return |
| 11 | UpdateResultFrm | (self) | display table with 5 rows + input fields | Self |
| 12 | Staff | UpdateResultFrm | enterFinishTime("Max", "1:30:45.123") | Sync |
| 13 | Staff | UpdateResultFrm | enterLapsCompleted("Max", 78) | Sync |
| 14 | Staff | UpdateResultFrm | enterFinishTime("Sergio", "1:31:20.456") | Sync |
| 15 | Staff | UpdateResultFrm | enterLapsCompleted("Sergio", 78) | Sync |
| 16 | Staff | UpdateResultFrm | enterFinishTime("Hamilton", "1:31:55.789") | Sync |
| 17 | Staff | UpdateResultFrm | enterLapsCompleted("Hamilton", 78) | Sync |
| 18 | Staff | UpdateResultFrm | enterFinishTime("Russell", "1:32:10.012") | Sync |
| 19 | Staff | UpdateResultFrm | enterLapsCompleted("Russell", 78) | Sync |
| 20 | Staff | UpdateResultFrm | enterFinishTime("Norris", "DNF") | Sync |
| 21 | Staff | UpdateResultFrm | enterLapsCompleted("Norris", 45) | Sync |
| 22 | Staff | UpdateResultFrm | clickSave() | Sync |
| 23 | UpdateResultFrm | (self) | validate all fields not empty | Self |
| 24 | UpdateResultFrm | (self) | sort by finishTime asc → calculate position [1,2,3,4,5] | Self |
| 25 | UpdateResultFrm | (self) | assign scores [25,18,15,12,0] (Norris=0, DNF) | Self |
| 26 | UpdateResultFrm | RaceResultDAO | addResults(List\<RaceResult\>) | Sync |
| 27 | RaceResultDAO | (self) | INSERT INTO tblRaceResult x5 rows | Self |
| 28 | RaceResultDAO | UpdateResultFrm | return true | Return |
| 29 | UpdateResultFrm | (self) | showMessage("Cap nhat ket qua thanh cong") | Self |
| 30 | UpdateResultFrm | Staff | show success notification | Async |

---

## Câu 5: Blackbox Testcase

### TC01: Cập nhật kết quả chặng đua thành công

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
| 4 | FER | Ferrari | Ferrari |

**tblDriver:**
| ID | code | name | nationality | teamID |
|----|------|------|-------------|--------|
| 1 | VER | Max Verstappen | Dutch | 1 |
| 2 | PER | Sergio Perez | Mexican | 1 |
| 3 | HAM | Lewis Hamilton | British | 2 |
| 4 | RUS | George Russell | British | 2 |
| 5 | NOR | Lando Norris | British | 3 |

**tblRaceRegistration:**
| ID | raceID | teamID | driverID |
|----|--------|--------|----------|
| 1 | 1 | 1 | 1 |
| 2 | 1 | 1 | 2 |
| 3 | 1 | 2 | 3 |
| 4 | 1 | 2 | 4 |
| 5 | 1 | 3 | 5 |

**tblRaceResult:** (rỗng)

**tblUser:**
| ID | username | password | role |
|----|----------|----------|------|
| 1 | staff01 | 123456 | staff |

### Kịch bản test và kết quả mong đợi

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Khởi động ứng dụng | Giao diện Login hiển thị |
| 2 | Nhập `staff01` / `123456`, nhấn Login | Đăng nhập thành công, hiển thị Home |
| 3 | Chọn **Update results** | Giao diện: combobox chọn chặng đua |
| 4 | Chọn "Monaco GP" từ combobox | Bảng 5 dòng hiện ra: VER, PER, HAM, RUS, NOR. Mỗi dòng có ô Finish Time + Laps |
| 5 | Nhập kết quả Max Verstappen: Finish Time = 1:30:45.123, Laps = 78 | Ô nhập hiển thị giá trị |
| 6 | Nhập Sergio Perez: 1:31:20.456, 78 laps | Ô nhập hiển thị giá trị |
| 7 | Nhập Lewis Hamilton: 1:31:55.789, 78 laps | Ô nhập hiển thị giá trị |
| 8 | Nhập George Russell: 1:32:10.012, 78 laps | Ô nhập hiển thị giá trị |
| 9 | Nhập Lando Norris: DNF, 45 laps | Ô nhập hiển thị DNF |
| 10 | Nhấn **Save** | Hệ thống xếp hạng: VER(1st), PER(2nd), HAM(3rd), RUS(4th), NOR(5th/DNF). Tính điểm: 25,18,15,12,0. Thông báo "Cap nhat ket qua thanh cong" |

### Database sau khi test

**tblRaceResult:** (thêm 5 dòng)
| ID | raceID | driverID | finishTime | lapsCompleted | position | score |
|----|--------|----------|------------|---------------|----------|-------|
| 1 | 1 | 1 | 1:30:45.123 | 78 | 1 | 25 |
| 2 | 1 | 2 | 1:31:20.456 | 78 | 2 | 18 |
| 3 | 1 | 3 | 1:31:55.789 | 78 | 3 | 15 |
| 4 | 1 | 4 | 1:32:10.012 | 78 | 4 | 12 |
| 5 | 1 | 5 | null | 45 | 5 | 0 |

**tblRace:** (không thay đổi)
**tblTeam:** (không thay đổi)
**tblDriver:** (không thay đổi)
**tblRaceRegistration:** (không thay đổi)
