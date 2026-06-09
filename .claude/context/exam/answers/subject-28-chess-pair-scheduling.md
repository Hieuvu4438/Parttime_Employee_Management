# Subject No. 28 — Chess Tournament — Module "Pair scheduling"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Xếp cặp thi đấu

### Mô tả nghiệp vụ

Staff chọn menu xếp cặp, trang xếp cặp hiện ra. Staff chọn vòng trước đó từ dropdown, hệ thống hiển thị bảng xếp hạng hiện tại cùng nút Schedule. Staff nhấn Schedule, hệ thống tự động xếp cặp theo luật Swiss và hiển thị danh sách trận đấu (tên bàn, 2 đấu thủ). Staff nhấn Save, hệ thống lưu vòng đấu mới.

### Bảng diễn biến chi tiết

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, giao diện chính hiển thị các menu chức năng. |
| 2 | Staff chọn menu **Scheduling**. |
| 3 | Giao diện xếp cặp hiện ra: combobox **Previous Round** (chọn vòng trước), vùng BXH trống, nút Schedule bị disable. |
| 4 | Staff mở combobox Previous Round, danh sách hiển thị: Round 1, Round 2, ..., Round 10 (tối đa Round 10 vì Round 11 là vòng cuối). Staff chọn **Round 4**. |
| 5 | Hệ thống tải bảng xếp hạng sau Round 4. Bảng hiển thị: Rank, Code, Name, Total Score, Opponent Total Score, Elo. |
| 6 | BXH hiển thị: Rank 1 — Nguyen Van A (4.0đ, Elo 2512), Rank 2 — Le Van C (3.0đ, Elo 2362), Rank 3 — Tran Van B (2.5đ, Elo 2388), Rank 4 — Pham Thi D (1.5đ, Elo 2290). |
| 7 | Nút **Schedule** được enable. Staff nhấn **Schedule**. |
| 8 | Hệ thống thực hiện xếp cặp theo luật Swiss: từ BXH, lấy đấu thủ chưa ghép xếp trên, tìm đấu thủ chưa gặp nhau xếp gần nhất. |
| 9 | Kết quả ghép cặp hiển thị: Bảng cột Table, Player 1, Player 2. Dòng 1: Ban 1, Nguyen Van A, Le Van C. Dòng 2: Ban 2, Tran Van B, Pham Thi D. |
| 10 | Staff kiểm tra danh sách, nhấn **Save**. |
| 11 | Hệ thống tạo Round 5 trong tblRound, tạo 2 bản ghi trong tblMatch với kết quả NULL. |
| 12 | Hệ thống thông báo: **"Xep cap thanh cong — Round 5 da duoc tao"**. |

### Ngoại lệ

| Trường hợp | Xử lý |
|------------|--------|
| Số đấu thủ lẻ (ví dụ: 5 người) | Đấu thủ cuối cùng được "bye" (nghỉ vòng này, mặc định được 1 điểm). |
| Tất cả cặp đã gặp nhau | Hệ thống cho phép ghép lại cặp đã gặp (ưu tiên ít gặp nhất). |
| Staff nhấn Save khi chưa Schedule | Nút Save bị disable cho đến khi có kết quả ghép cặp. |
| Round 11 đã tồn tại | Không thể tạo thêm vòng. Combobox chỉ hiện tối đa Round 10. |

---

## Câu 2: Entity Class

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý giải đấu cờ vua gồm nhiều **Tournament** (giải đấu). Mỗi giải đấu có nhiều **Round** (vòng đấu). Mỗi vòng có nhiều **Match** (trận đấu) giữa 2 **Player** (đấu thủ). Khi xếp cặp, hệ thống tính toán dựa trên bảng xếp hạng hiện tại (từ Match đã đấu) để ghép đấu thủ chưa gặp nhau. Người dùng là **User**.

### Trích xuất danh từ

| Danh từ | Entity class | Thuộc tính chính |
|---------|-------------|-----------------|
| Giải đấu | Tournament | id, code, name, year, time, location, description |
| Đấu thủ | Player | id, code, name, yob, nationality, elo, notes |
| Vòng đấu | Round | id, tournamentId, roundNumber |
| Trận đấu | Match | id, roundId, table, player1Id, player2Id, player1Score, player2Score, player1EloChange, player2EloChange |
| Người dùng | User | id, username, password, role |

### Mối quan hệ

```
Tournament 1 --- * Round          (1 giải có nhiều vòng)
Round 1 --- * Match               (1 vòng có nhiều trận)
Player 1 --- * Match (as player1) (1 đấu thủ là player1 ở nhiều trận)
Player 1 --- * Match (as player2) (1 đấu thủ là player2 ở nhiều trận)
```

### ASCII Class Diagram

```
+---------------------+         +---------------------+
|    Tournament       |         |       User          |
+---------------------+         +---------------------+
| - id: int           |         | - id: int           |
| - code: String      |         | - username: String  |
| - name: String      |         | - password: String  |
| - year: int         |         | - role: String      |
| - time: String      |         +---------------------+
| - location: String  |
| - description: Str  |
+---------------------+
        | 1
        | *
+---------------------+         +---------------------+
|       Round         |         |      Player         |
+---------------------+         +---------------------+
| - id: int           |         | - id: int           |
| - tournamentId: int |         | - code: String      |
| - roundNumber: int  |         | - name: String      |
+---------------------+         | - yob: int          |
        | 1                     | - nationality: Str  |
        | *                     | - elo: int          |
+---------------------+         | - notes: String     |
|       Match         |         +---------------------+
+---------------------+              | 1        | 1
| - id: int           |              | *        | *
| - roundId: int      |              |     +----+-------+
| - table: String     |              |     |            |
| - player1Id: int    |<-------------+     |            |
| - player2Id: int    |<-------------------+            |
| - player1Score: dbl |                                 |
| - player2Score: dbl |                                 |
| - player1EloChg: int|                                 |
| - player2EloChg: int|                                 |
+---------------------+
```

### Derived class cho pairing

**PlayerStanding** (không lưu DB, tính toán từ Match):
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| player | Player | Thông tin đấu thủ |
| totalScore | double | Tổng điểm qua các vòng |
| opponentTotalScore | double | Tổng điểm đối thủ |
| currentElo | int | Elo hiện tại |

**PairResult** (không lưu DB, kết quả ghép cặp):
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| table | String | Tên bàn |
| player1 | Player | Đấu thủ 1 |
| player2 | Player | Đấu thủ 2 (null nếu bye) |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: Tournament, Round, Match, Player, User |
| 3 | Tạo các DTO class boxes cho: PlayerStanding, PairResult |
| 4 | Tạo các view class boxes: HomeFrm, PairSchedulingFrm |
| 5 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ Tournament |
|------|----------|------------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>`, `<<dto>>`, hoặc `<<boundary>>` + tên class | `<<entity>> Tournament` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-id: int` `-code: String` `-name: String` `-year: int` `-location: String` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | — |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Tournament | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-year: int`, `-time: String`, `-location: String`, `-description: String` | — |
| Round | `<<entity>>` | `-id: int`, `-tournamentId: int`, `-roundNumber: int` | `+getRounds(tournamentId: int): List<Round>`, `+addRound(round: Round): boolean` |
| Match | `<<entity>>` | `-id: int`, `-roundId: int`, `-table: String`, `-player1Id: int`, `-player2Id: int`, `-player1Score: double`, `-player2Score: double`, `-player1EloChange: int`, `-player2EloChange: int` | `+getAllMatchesUpToRound(roundId: int): List<Match>`, `+addMatch(match: Match): boolean` |
| Player | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-yob: int`, `-nationality: String`, `-elo: int`, `-notes: String` | `+getAllPlayers(tournamentId: int): List<Player>` |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |
| PlayerStanding | `<<dto>>` | `-player: Player`, `-totalScore: double`, `-opponentTotalScore: double`, `-currentElo: int` | — |
| PairResult | `<<dto>>` | `-table: String`, `-player1: Player`, `-player2: Player` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subScheduling` (Button — chọn chức năng xếp cặp) |
| PairSchedulingFrm | `<<boundary>>` | `inPreviousRound` (ComboBox — chọn vòng trước), `outStanding` (Table — bảng xếp hạng hiện tại), `subSchedule` (Button — ghép cặp tự động), `outListPair` (Table — danh sách cặp đấu sau ghép), `subSave` (Button — lưu vòng đấu mới) |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu giữa các entity |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | Tournament chứa Round, Round chứa Match |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | View gọi DAO |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 1..* | `N` | Nhiều đối tượng |

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| Tournament | Round | Composition | 1 --- N | Một giải đấu có nhiều vòng đấu |
| Round | Match | Composition | 1 --- N | Một vòng đấu có nhiều trận đấu |
| Player | Match (vai player1) | Association | 1 --- N | Một đấu thủ là player1 ở nhiều trận |
| Player | Match (vai player2) | Association | 1 --- N | Một đấu thủ là player2 ở nhiều trận |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ Round (1) --- (N) Match (Composition)**

1. Tạo class `Round` với attribute: `-id: int`, `-roundNumber: int`.
2. Tạo class `Match` với attribute: `-id: int`, `-table: String`, `-player1Score: double`.
3. Kéo tool **Composition** → click `Round` → kéo sang `Match`.
4. Đặt multiplicity: `1` (Round), `N` (Match).

**Ví dụ 2: Vẽ PairSchedulingFrm gọi RoundDAO qua Dependency**

1. Tạo class `PairSchedulingFrm` với stereotype `<<boundary>>`.
2. Tạo class `RoundDAO` với stereotype `<<control>>`.
3. Kéo tool **Dependency** → click `PairSchedulingFrm` → kéo sang `RoundDAO`.
4. Ghi chú: `addRound()`, `getRounds()`.

---

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-2: Staff đăng nhập → giao diện Home → chọn menu Scheduling. View class: **HomeFrm**, **PairSchedulingFrm**.
Bước 3: Giao diện xếp cặp hiện ra: combobox Previous Round, BXH trống, nút Schedule bị disable. UI: `inPreviousRound` (ComboBox — chọn vòng trước).
Bước 4: Staff chọn Round 4.
Bước 5-6: Hệ thống tải bảng xếp hạng sau Round 4. UI: `outStanding` (Table — bảng xếp hạng hiện tại).
Bước 7: Nút Schedule enable. Staff nhấn Schedule. UI: `subSchedule` (Button — thực hiện ghép cặp tự động).
Bước 8-9: Hệ thống ghép cặp theo luật Swiss, hiển thị danh sách cặp đấu. UI: `outListPair` (Table — danh sách cặp đấu sau ghép).
Bước 10: Staff kiểm tra, nhấn Save. UI: `subSave` (Button — lưu vòng đấu mới vào DB).
Bước 11-12: Hệ thống tạo Round 5, tạo trận đấu mới, thông báo thành công.

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Scheduling |
| PairSchedulingFrm | Form | Giao diện xếp cặp thi đấu |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inPreviousRound` | ComboBox | PairSchedulingFrm | Chọn vòng trước (Round 1 .. Round 10) |
| `outStanding` | Table | PairSchedulingFrm | Bảng xếp hạng hiện tại (Rank, Code, Name, Total Score, Opp Total, Elo) |
| `subSchedule` | Button | PairSchedulingFrm | Nút Schedule — ghép cặp tự động theo luật Swiss |
| `outListPair` | Table | PairSchedulingFrm | Danh sách cặp đấu sau ghép (Table, Player 1, Player 2) |
| `subSave` | Button | PairSchedulingFrm | Nút Save — lưu vòng đấu mới vào DB |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getRounds()` | tournamentId | List\<Round\> | Round |
| `getAllPlayers()` | tournamentId | List\<Player\> | Player |
| `getMatchesByPlayerUpToRound()` | playerId, roundId | List\<Match\> | Match |
| `getAllMatchesUpToRound()` | roundId | List\<Match\> | Match |
| `addRound()` | round (tournamentId, roundNumber) | boolean | Round |
| `addMatch()` | match (roundId, table, player1Id, player2Id) | boolean | Match |

**Tong hop:**

- View classes: HomeFrm, PairSchedulingFrm
- Methods: getRounds(), getAllPlayers(), getMatchesByPlayerUpToRound(), getAllMatchesUpToRound(), addRound(), addMatch()

### Bảng quan hệ (Relationship Table)

| Entity 1 | Multiplicity | Entity 2 | Mô tả |
|----------|-------------|----------|-------|
| Tournament | 1 --- * | Round | 1 giải đấu có nhiều vòng đấu |
| Round | 1 --- * | Match | 1 vòng đấu có nhiều trận đấu |
| Player | 1 --- * | Match | 1 đấu thủ tham gia nhiều trận (vai player1) |
| Player | 1 --- * | Match | 1 đấu thủ tham gia nhiều trận (vai player2) |

---

## Câu 3: Thiết kế tĩnh

### View classes

**PairSchedulingFrm** (Giao diện xếp cặp thi đấu):

| Thành phần UI | Loại | Mô tả |
|---------------|------|-------|
| `inPreviousRound` | ComboBox | Dropdown chọn vòng trước (Round 1 .. Round 10) |
| `outStanding` | Table | Bảng xếp hạng hiện tại (Rank, Code, Name, Total Score, Opponent Total Score, Elo) |
| `subSchedule` | Button | Nút "Schedule" — thực hiện ghép cặp tự động (disable khi chưa chọn vòng) |
| `outListPair` | Table | Danh sách cặp đấu sau ghép (Table, Player 1, Player 2). Hiển thị sau khi nhấn Schedule. |
| `subSave` | Button | Nút "Save" — lưu vòng đấu mới vào DB (disable khi chưa có kết quả ghép) |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|--------|
| RoundDAO | `getRounds(tournamentId): List<Round>` | Lấy danh sách vòng đấu |
| RoundDAO | `addRound(round): boolean` | Tạo vòng đấu mới |
| MatchDAO | `getAllMatchesUpToRound(roundId): List<Match>` | Lấy tất cả trận từ Round 1 đến roundId (để kiểm tra đã gặp nhau chưa) |
| MatchDAO | `addMatch(match): boolean` | Tạo trận đấu mới |
| PlayerDAO | `getAllPlayers(tournamentId): List<Player>` | Lấy danh sách đấu thủ |

### Logic xếp cặp Swiss (trong service)

```
1. Lấy BXH sau round trước: sort totalScore DESC → oppTotalScore DESC → elo DESC.
2. Nếu số đấu thủ lẻ: đấu thủ cuối cùng được bye (1 điểm), loại khỏi danh sách ghép.
3. Với đấu thủ đầu tiên trong danh sách chưa ghép:
   a. Duyệt từ trên xuống, tìm đấu thủ chưa gặp nhau.
   b. Nếu tìm thấy: ghép cặp, đánh dấu cả 2 đã ghép.
   c. Nếu tất cả đã gặp: ghép với đấu thủ có ít lần gặp nhất.
4. Gán tên bàn: Ban 1, Ban 2, ...
```

### Entity classes (Design phase)

| Entity | Kiểu | Attributes |
|--------|------|------------|
| Tournament | Entity | id: int (PK), code: String, name: String, year: int, time: String, location: String, description: String |
| Round | Entity | id: int (PK), tournament: Tournament (object), roundNumber: int |
| Match | Entity | id: int (PK), round: Round (object), table: String, player1: Player (object), player2: Player (object), player1Score: double, player2Score: double, player1EloChange: int, player2EloChange: int |
| Player | Entity | id: int (PK), code: String, name: String, yob: int, nationality: String, elo: int, notes: String |

### Entity types liên quan

| Entity | Vai trò trong module |
|--------|---------------------|
| Tournament | Xác định giải đấu hiện tại |
| Round | Tạo mới (Round N+1), hiển thị trong combobox |
| Match | Tạo mới cho vòng đấu mới, đọc lịch sử để kiểm tra đã gặp |
| Player | Hiển thị trong BXH, ghép cặp |

### Database Schema

**tblTournament:**
| Column | Type | Constraint |
|--------|------|-----------|
| ID | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | UNIQUE, NOT NULL |
| name | VARCHAR(100) | NOT NULL |
| year | INT | NOT NULL |
| time | VARCHAR(50) | |
| location | VARCHAR(100) | |
| description | TEXT | |

**tblRound:**
| Column | Type | Constraint |
|--------|------|-----------|
| ID | INT | PK, AUTO_INCREMENT |
| tournamentID | INT | FK -> tblTournament(ID) |
| roundNumber | INT | NOT NULL |

**tblMatch:**
| Column | Type | Constraint |
|--------|------|-----------|
| ID | INT | PK, AUTO_INCREMENT |
| roundID | INT | FK -> tblRound(ID) |
| table | VARCHAR(20) | |
| player1ID | INT | FK -> tblPlayer(ID) |
| player2ID | INT | FK -> tblPlayer(ID), NULLABLE (bye) |
| player1Score | DOUBLE | NULLABLE |
| player2Score | DOUBLE | NULLABLE |
| player1EloChange | INT | NULLABLE |
| player2EloChange | INT | NULLABLE |

**tblPlayer:**
| Column | Type | Constraint |
|--------|------|-----------|
| ID | INT | PK, AUTO_INCREMENT |
| code | VARCHAR(20) | UNIQUE, NOT NULL |
| name | VARCHAR(100) | NOT NULL |
| yob | INT | NOT NULL |
| nationality | VARCHAR(50) | |
| elo | INT | DEFAULT 1500 |
| notes | TEXT | |

### Visual Paradigm — Hướng dẫn vẽ class diagram

1. Mở Visual Paradigm → New → Class Diagram.
2. Tạo 4 class: `PairSchedulingFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. `PairSchedulingFrm` có attribute: `inPreviousRound: ComboBox`, `outStanding: Table`, `subSchedule: Button`, `outListPair: Table`, `subSave: Button`.
4. Tạo class `PlayerStanding` và `PairResult` với attributes đầy đủ.
5. Vẽ dependency từ `PairSchedulingFrm` đến `RoundDAO`, `MatchDAO`, `PlayerDAO`.
6. Ghi chú method trên mỗi dependency: `getRounds()`, `getAllMatchesUpToRound()`, `addRound()`, `addMatch()`, `getAllPlayers()`.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 4 lifeline: `Staff`, `PairSchedulingFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. Staff → PairSchedulingFrm: "selectPreviousRound(roundId = 4)".
4. PairSchedulingFrm → PlayerDAO: "getAllPlayers(tournamentId)" — return `List<Player>`.
5. Loop: PairSchedulingFrm → MatchDAO: "getMatchesByPlayerUpToRound()" — tính standings.
6. PairSchedulingFrm: self-call "displayStandings()".
7. Staff → PairSchedulingFrm: "clickSchedule()".
8. PairSchedulingFrm → MatchDAO: "getAllMatchesUpToRound(roundId=4)" — lịch sử gặp nhau.
9. PairSchedulingFrm: self-call "autoPairBySwiss(standings, history)".
10. PairSchedulingFrm: self-call "displayPairs(pairResults)".
11. Staff → PairSchedulingFrm: "clickSave()".
12. PairSchedulingFrm → RoundDAO: "addRound(round5)" — return true.
13. Loop qua mỗi cặp: PairSchedulingFrm → MatchDAO: "addMatch(match)".
14. PairSchedulingFrm: self-call "showSuccessMessage()".

### ASCII Sequence Diagram

```
Staff          PairSchedulingFrm   PlayerDAO       MatchDAO        RoundDAO
  |                  |                 |               |               |
  |---selectPrev---->|                 |               |               |
  |                  |---getAllPlayers->|               |               |
  |                  |<-List<Player>---|               |               |
  |                  |                 |               |               |
  |                  |=== LOOP: calculate standings     |               |
  |                  |---getMatchesByPlayerUpToRound--->|               |
  |                  |<-List<Match>--------------------|               |
  |                  |=== END LOOP                     |               |
  |                  |                 |               |               |
  |                  |=== displayStandings()           |               |
  |<--display--------|                 |               |               |
  |                  |                 |               |               |
  |---clickSchedule->|                 |               |               |
  |                  |---getAllMatchesUpToRound-------->|               |
  |                  |<-List<Match>--------------------|               |
  |                  |                 |               |               |
  |                  |=== autoPairBySwiss()            |               |
  |                  |=== displayPairs()               |               |
  |<--display--------|                 |               |               |
  |                  |                 |               |               |
  |---clickSave----->|                 |               |               |
  |                  |--------------------------->addRound------------>|
  |                  |<--true----------------------------------------->|
  |                  |                 |               |               |
  |                  |=== LOOP: each pair              |               |
  |                  |---addMatch--------------------->|               |
  |                  |<-true--------------------------|               |
  |                  |=== END LOOP                     |               |
  |                  |                 |               |               |
  |                  |=== showSuccessMessage()         |               |
  |<--notify---------|                 |               |               |
```

### Bảng chi tiết các bước trong Sequence Diagram

| Bước | From | To | Message | Loại | Ghi chú |
|------|------|-----|---------|------|---------|
| 1 | Staff | PairSchedulingFrm | selectPreviousRound(roundId=4) | Synchrous | Staff chọn Round 4 |
| 2 | PairSchedulingFrm | PlayerDAO | getAllPlayers(tournamentId) | Synchrous | Lấy danh sách 4 đấu thủ |
| 3 | PlayerDAO | PairSchedulingFrm | return List\<Player\> | Return | 4 đấu thủ |
| 4 | PairSchedulingFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=1, roundId=4) | Synchrous | Trận của player 1 |
| 5 | MatchDAO | PairSchedulingFrm | return List\<Match\> | Return | 4 trận |
| 6 | PairSchedulingFrm | PairSchedulingFrm | calculateStanding(player1, matches) | Self | totalScore=4.0 |
| 7 | PairSchedulingFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=2, roundId=4) | Synchrous | Trận của player 2 |
| 8 | MatchDAO | PairSchedulingFrm | return List\<Match\> | Return | 4 trận |
| 9 | PairSchedulingFrm | PairSchedulingFrm | calculateStanding(player2, matches) | Self | totalScore=2.5 |
| 10 | PairSchedulingFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=3, roundId=4) | Synchrous | Trận của player 3 |
| 11 | MatchDAO | PairSchedulingFrm | return List\<Match\> | Return | 4 trận |
| 12 | PairSchedulingFrm | PairSchedulingFrm | calculateStanding(player3, matches) | Self | totalScore=3.0 |
| 13 | PairSchedulingFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=4, roundId=4) | Synchrous | Trận của player 4 |
| 14 | MatchDAO | PairSchedulingFrm | return List\<Match\> | Return | 4 trận |
| 15 | PairSchedulingFrm | PairSchedulingFrm | calculateStanding(player4, matches) | Self | totalScore=1.5 |
| 16 | PairSchedulingFrm | PairSchedulingFrm | displayStandings(sortedList) | Self | Hiển thị BXH |
| 17 | Staff | PairSchedulingFrm | clickSchedule() | Synchrous | Nhấn Schedule |
| 18 | PairSchedulingFrm | MatchDAO | getAllMatchesUpToRound(roundId=4) | Synchrous | Lịch sử gặp nhau |
| 19 | MatchDAO | PairSchedulingFrm | return List\<Match\> | Return | 8 trận |
| 20 | PairSchedulingFrm | PairSchedulingFrm | autoPairBySwiss(standings, history) | Self | Ghép: A-C, B-D |
| 21 | PairSchedulingFrm | PairSchedulingFrm | displayPairs(pairResults) | Self | Hiển thị 2 cặp |
| 22 | Staff | PairSchedulingFrm | clickSave() | Synchrous | Nhấn Save |
| 23 | PairSchedulingFrm | RoundDAO | addRound(round5) | Synchrous | Tạo Round 5 |
| 24 | RoundDAO | PairSchedulingFrm | return true | Return | Thành công |
| 25 | PairSchedulingFrm | MatchDAO | addMatch(match1: A vs C, Ban 1) | Synchrous | Tạo trận 1 |
| 26 | MatchDAO | PairSchedulingFrm | return true | Return | Thành công |
| 27 | PairSchedulingFrm | MatchDAO | addMatch(match2: B vs D, Ban 2) | Synchrous | Tạo trận 2 |
| 28 | MatchDAO | PairSchedulingFrm | return true | Return | Thành công |
| 29 | PairSchedulingFrm | PairSchedulingFrm | showSuccessMessage(...) | Self | Thông báo thành công |
| 30 | PairSchedulingFrm | Staff | display notification | Return | "Xep cap thanh cong" |

---

## Câu 5: Blackbox Testcase

### Test Plan — Xếp cặp thi đấu

| ID | Test case | Mô tả | Kết quả mong đợi |
|----|-----------|-------|-----------------|
| TC01 | Xếp cặp thành công | Chọn Round 4, Schedule, Save | Round 5 tạo, 2 trận mới |
| TC02 | Số đấu thủ lẻ | 5 đấu thủ, 1 người bye | Bye được 1 điểm, 2 cặp còn lại |
| TC03 | Đã gặp nhau hết | Tất cả cặp đều đã gặp | Ghép lại cặp ít gặp nhất |

### TC01: Xếp cặp thi đấu thành công

#### Database trước khi chạy test

**tblTournament:**
| ID | code | name | year | time | location | description |
|----|------|------|------|------|----------|-------------|
| 1 | T001 | Chess Championship 2026 | 2026 | 01/06-15/06 | Ha Noi | Giai vo dich co vua |

**tblRound:**
| ID | tournamentID | roundNumber |
|----|-------------|-------------|
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 1 | 3 |
| 4 | 1 | 4 |

**tblPlayer:**
| ID | code | name | yob | nationality | elo | notes |
|----|------|------|-----|-------------|-----|-------|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2512 | |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2388 | |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2362 | |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2290 | |

**tblMatch (Round 1-4):**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 1 | 1 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +12 | -12 |
| 2 | 1 | Ban 2 | 3 | 4 | 0.5 | 0.5 | +0 | +0 |
| 3 | 2 | Ban 1 | 1 | 3 | 0.5 | 0.5 | +0 | +0 |
| 4 | 2 | Ban 2 | 2 | 4 | 1.0 | 0.0 | +10 | -10 |
| 5 | 3 | Ban 1 | 1 | 4 | 1.0 | 0.0 | +10 | -10 |
| 6 | 3 | Ban 2 | 2 | 3 | 0.0 | 1.0 | -8 | +8 |
| 7 | 4 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +10 | -10 |
| 8 | 4 | Ban 2 | 3 | 4 | 1.0 | 0.0 | +8 | -8 |

**BXH sau Round 4:**
| Rank | Player | Total Score | Opp Total | Elo |
|------|--------|-------------|-----------|-----|
| 1 | Nguyen Van A | 4.0 | 15.5 | 2512 |
| 2 | Le Van C | 3.0 | 16.0 | 2362 |
| 3 | Tran Van B | 2.5 | 15.0 | 2388 |
| 4 | Pham Thi D | 1.5 | 15.5 | 2290 |

**Lịch sử gặp nhau:**
- A vs B: Round 1, Round 4 (2 lần)
- A vs C: Round 2 (1 lần)
- A vs D: Round 3 (1 lần)
- B vs C: Round 3 (1 lần)
- B vs D: Round 2 (1 lần)
- C vs D: Round 1, Round 4 (2 lần)

#### Kịch bản kiểm thử

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Đăng nhập với tài khoản staff hợp lệ | Vào giao diện chính |
| 2 | Nhấn menu **Scheduling** | Giao diện xếp cặp hiện ra |
| 3 | Mở combobox Previous Round, chọn **Round 4** | BXH hiển thị: A(4.0), C(3.0), B(2.5), D(1.5). Nút Schedule enable. |
| 4 | Nhấn **Schedule** | Hệ thống ghép cặp: A chưa gặp B(gặp 2 lần), C(gặp 1 lần), D(gặp 1 lần). Ưu tiên C (ít gặp). Ghép: **A vs C** (Ban 1). Còn B và D (gặp 1 lần). Ghép: **B vs D** (Ban 2). |
| 5 | Kiểm tra danh sách cặp | Ban 1: Nguyen Van A vs Le Van C. Ban 2: Tran Van B vs Pham Thi D. |
| 6 | Nhấn **Save** | Hệ thống tạo Round 5 và 2 trận đấu. |
| 7 | Kiểm tra kết quả | Thông báo **"Xep cap thanh cong — Round 5 da duoc tao"**. |

#### Database sau khi chạy test

**tblRound:**
| ID | tournamentID | roundNumber |
|----|-------------|-------------|
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 1 | 3 |
| 4 | 1 | 4 |
| **5** | **1** | **5** | ← MỚI

**tblMatch (thêm 2 dòng mới):**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 1 | 1 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +12 | -12 |
| 2 | 1 | Ban 2 | 3 | 4 | 0.5 | 0.5 | +0 | +0 |
| 3 | 2 | Ban 1 | 1 | 3 | 0.5 | 0.5 | +0 | +0 |
| 4 | 2 | Ban 2 | 2 | 4 | 1.0 | 0.0 | +10 | -10 |
| 5 | 3 | Ban 1 | 1 | 4 | 1.0 | 0.0 | +10 | -10 |
| 6 | 3 | Ban 2 | 2 | 3 | 0.0 | 1.0 | -8 | +8 |
| 7 | 4 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +10 | -10 |
| 8 | 4 | Ban 2 | 3 | 4 | 1.0 | 0.0 | +8 | -8 |
| **9** | **5** | **Ban 1** | **1** | **3** | **NULL** | **NULL** | **NULL** | **NULL** | ← MỚI
| **10** | **5** | **Ban 2** | **2** | **4** | **NULL** | **NULL** | **NULL** | **NULL** | ← MỚI

**tblPlayer (không đổi):**
| ID | code | name | yob | nationality | elo |
|----|------|------|-----|-------------|-----|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2512 |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2388 |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2362 |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2290 |

**So sánh thay đổi:**
- tblRound: thêm 1 dòng (ID=5, roundNumber=5).
- tblMatch: thêm 2 dòng (ID=9, ID=10) với score và Elo change = NULL (chưa đấu).
- tblPlayer: không thay đổi.
