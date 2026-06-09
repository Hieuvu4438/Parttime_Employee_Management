# Subject No. 27 — Chess Tournament — Module "View leaderboard"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Xem bảng xếp hạng

### Mô tả nghiệp vụ

Staff chọn menu thống kê, chọn "Xem bảng xếp hạng". Giao diện hiện ra, staff chọn vòng đấu từ dropdown. Hệ thống hiển thị danh sách đấu thủ xếp hạng: mã, tên, năm sinh, quốc tịch, tổng điểm, tổng điểm đối thủ, hệ số Elo hiện tại. Staff click vào một đấu thủ để xem chi tiết kết quả các trận: mã trận, tên đối thủ, kết quả (thắng/hòa/thua), thay đổi Elo.

### Bảng diễn biến chi tiết

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, giao diện chính hiển thị các menu chức năng. |
| 2 | Staff chọn menu **Statistics** → **View leaderboard**. |
| 3 | Giao diện bảng xếp hạng hiện ra: combobox **Round** (chọn vòng đấu), vùng bảng xếp hạng trống. |
| 4 | Staff mở combobox Round, danh sách hiển thị: Round 1, Round 2, ..., Round 11. Staff chọn **Round 5**. |
| 5 | Hệ thống tải dữ liệu xếp hạng sau Round 5. Bảng hiển thị: cột Rank, Code, Name, YOB, Nationality, Total Score, Opponent Total Score, Elo. |
| 6 | Bảng hiển thị 8 đấu thủ, sắp xếp theo: tổng điểm giảm dần → tổng điểm đối thủ giảm dần → Elo giảm dần. |
| 7 | Dòng đầu tiên: Rank 1, P001, Nguyen Van A, 1995, Vietnam, 4.5 điểm, tổng điểm đối thủ 18.5, Elo 2512. |
| 8 | Dòng thứ 2: Rank 2, P003, Le Van C, 2000, Vietnam, 4.0 điểm, tổng điểm đối thủ 17.0, Elo 2362. |
| 9 | Staff click vào dòng **Nguyen Van A** (Rank 1). Dòng được highlight. |
| 10 | Vùng chi tiết phía dưới (hoặc panel bên phải) hiển thị danh sách trận đã đấu của Nguyen Van A qua 5 vòng. |
| 11 | Chi tiết hiển thị: Match ID = M01, Opponent = Tran Van B, Result = Win (+1.0), Elo Change = +12. |
| 12 | Tiếp tục: M03 vs Le Van C — Draw (+0.5), Elo +0; M05 vs Pham Thi D — Win (+1.0), Elo +10; ... tổng 5 trận. |

### Ngoại lệ

| Trường hợp | Xử lý |
|------------|--------|
| Chưa có trận đấu nào trong vòng được chọn | Bảng xếp hạng trống, hiển thị thông báo "Chua co du lieu". |
| Staff click vào đấu thủ chưa đấu trận nào | Vùng chi tiết trống. |

---

## Câu 2: Entity Class

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý giải đấu cờ vua gồm nhiều **Tournament** (giải đấu). Mỗi giải đấu có nhiều **Round** (vòng đấu). Mỗi vòng có nhiều **Match** (trận đấu) giữa 2 **Player** (đấu thủ). Mỗi trận có kết quả điểm số và thay đổi Elo. Khi xem bảng xếp hạng, hệ thống tổng hợp điểm và Elo từ tất cả trận đã đấu của mỗi đấu thủ. Người dùng hệ thống là **User**.

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

### Derived class cho leaderboard

**PlayerStanding** (không lưu DB, tính toán từ Match):
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| player | Player | Thông tin đấu thủ |
| totalScore | double | Tổng điểm qua các vòng đã đấu |
| opponentTotalScore | double | Tổng điểm của tất cả đối thủ đã gặp |
| currentElo | int | Elo hiện tại (sau cập nhật) |

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: Tournament, Round, Match, Player, User |
| 3 | Tạo DTO class box cho: PlayerStanding |
| 4 | Tạo các view class boxes: HomeFrm, LeaderboardFrm |
| 5 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ Player |
|------|----------|--------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>`, `<<dto>>`, hoặc `<<boundary>>` + tên class | `<<entity>> Player` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-id: int` `-code: String` `-name: String` `-yob: int` `-nationality: String` `-elo: int` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | `+getAllPlayers(tournamentId: int): List<Player>` |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Tournament | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-year: int`, `-time: String`, `-location: String`, `-description: String` | — |
| Round | `<<entity>>` | `-id: int`, `-tournamentId: int`, `-roundNumber: int` | `+getRounds(tournamentId: int): List<Round>` |
| Match | `<<entity>>` | `-id: int`, `-roundId: int`, `-table: String`, `-player1Id: int`, `-player2Id: int`, `-player1Score: double`, `-player2Score: double`, `-player1EloChange: int`, `-player2EloChange: int` | `+getMatchesByPlayerUpToRound(playerId: int, roundId: int): List<Match>` |
| Player | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-yob: int`, `-nationality: String`, `-elo: int`, `-notes: String` | `+getAllPlayers(tournamentId: int): List<Player>` |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |
| PlayerStanding | `<<dto>>` | `-player: Player`, `-totalScore: double`, `-opponentTotalScore: double`, `-currentElo: int` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subViewLeaderboard` (Button — chọn chức năng xem BXH) |
| LeaderboardFrm | `<<boundary>>` | `inRound` (ComboBox — chọn vòng đấu), `outsubListStanding` (Table — bảng xếp hạng, click chọn), `outListMatchDetail` (Table — chi tiết trận đấu của đấu thủ) |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu giữa các entity |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | Round chứa Match |
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
| LeaderboardFrm | PlayerStanding | Association | 1 --- N | View hiển thị danh sách bảng xếp hạng |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ Tournament (1) --- (N) Round (Composition)**

1. Tạo class `Tournament` với attribute: `-id: int`, `-code: String`, `-name: String`.
2. Tạo class `Round` với attribute: `-id: int`, `-roundNumber: int`.
3. Kéo tool **Composition** → click `Tournament` → kéo sang `Round`.
4. Đặt multiplicity: `1` (Tournament), `N` (Round).

**Ví dụ 2: Vẽ Dependency từ LeaderboardFrm đến PlayerDAO**

1. Tạo class `LeaderboardFrm` với stereotype `<<boundary>>`, thêm UI elements.
2. Tạo class `PlayerDAO` với stereotype `<<control>>`.
3. Kéo tool **Dependency** (đường dashed) → click `LeaderboardFrm` → kéo sang `PlayerDAO`.
4. Ghi chú: `getAllPlayers()`.

---

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-2: Staff đăng nhập → giao diện Home → chọn menu Statistics → View leaderboard. View class: **HomeFrm**, **LeaderboardFrm**.
Bước 3: Giao diện bảng xếp hạng hiện ra: combobox Round. UI: `inRound` (ComboBox — chọn vòng đấu).
Bước 4: Staff chọn Round 5.
Bước 5-8: Hệ thống tải dữ liệu xếp hạng, hiển thị bảng. UI: `outsubListStanding` (Table — bảng xếp hạng, click chọn dòng được).
Bước 9: Staff click vào đấu thủ Nguyen Van A.
Bước 10-12: Vùng chi tiết hiển thị danh sách trận đã đấu. UI: `outListMatchDetail` (Table — chi tiết trận đấu).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → View leaderboard |
| LeaderboardFrm | Form | Giao diện xem bảng xếp hạng |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inRound` | ComboBox | LeaderboardFrm | Chọn vòng đấu (Round 1 .. Round 11) |
| `outsubListStanding` | Table | LeaderboardFrm | Bảng xếp hạng đấu thủ (Rank, Code, Name, Total Score, Opp Total, Elo), click chọn dòng |
| `outListMatchDetail` | Table | LeaderboardFrm | Chi tiết trận đấu của đấu thủ được chọn (Match ID, Opponent, Result, Elo Change) |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getRounds()` | tournamentId | List\<Round\> | Round |
| `getAllPlayers()` | tournamentId | List\<Player\> | Player |
| `getMatchesByPlayerUpToRound()` | playerId, roundId | List\<Match\> | Match |

**Tong hop:**

- View classes: HomeFrm, LeaderboardFrm
- Methods: getRounds(), getAllPlayers(), getMatchesByPlayerUpToRound()

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

**LeaderboardFrm** (Giao diện xem bảng xếp hạng):

| Thành phần UI | Loại | Mô tả |
|---------------|------|-------|
| `inRound` | ComboBox | Dropdown chọn vòng đấu (Round 1 .. Round 11) |
| `outsubListStanding` | Table | Bảng xếp hạng đấu thủ (Rank, Code, Name, YOB, Nationality, Total Score, Opponent Total Score, Elo). Có thể click chọn dòng. |
| `outListMatchDetail` | Table | Chi tiết trận đấu của đấu thủ được chọn (Match ID, Opponent Name, Result, Elo Change). |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|--------|
| RoundDAO | `getRounds(tournamentId): List<Round>` | Lấy danh sách vòng đấu của giải |
| MatchDAO | `getMatchesByPlayerUpToRound(playerId, roundId): List<Match>` | Lấy tất cả trận của đấu thủ từ Round 1 đến roundId |
| MatchDAO | `getMatchesByRound(roundId): List<Match>` | Lấy danh sách trận theo vòng |
| PlayerDAO | `getAllPlayers(tournamentId): List<Player>` | Lấy danh sách đấu thủ tham gia giải |

### Logic tính xếp hạng (trong service hoặc controller)

```
Với mỗi player trong danh sách:
    1. Lấy tất cả trận từ Round 1 đến roundId được chọn.
    2. totalScore = tổng điểm player đạt được qua các trận.
    3. opponentTotalScore = tổng điểm của tất cả đối thủ đã gặp.
    4. currentElo = elo hiện tại trong tblPlayer.
    5. Sắp xếp: totalScore DESC → opponentTotalScore DESC → currentElo DESC.
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
| Tournament | Xác định giải đấu hiện tại (context) |
| Round | Hiển thị trong combobox, giới hạn phạm vi xếp hạng |
| Match | Nguồn dữ liệu tính tổng điểm, tổng điểm đối thủ |
| Player | Hiển thị thông tin đấu thủ trong bảng xếp hạng |

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
| player2ID | INT | FK -> tblPlayer(ID) |
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
2. Tạo 4 class: `LeaderboardFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. `LeaderboardFrm` có attribute: `inRound: ComboBox`, `outsubListStanding: Table`, `outListMatchDetail: Table`.
4. Tạo class `PlayerStanding` với attributes: `player: Player`, `totalScore: double`, `opponentTotalScore: double`, `currentElo: int`.
5. Vẽ dependency từ `LeaderboardFrm` đến `RoundDAO`, `MatchDAO`, `PlayerDAO`.
6. Vẽ association giữa `LeaderboardFrm` (1) -- (*) `PlayerStanding`.
7. Vẽ association giữa `PlayerStanding` (1) -- (1) `Player`.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 4 lifeline: `Staff`, `LeaderboardFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. Staff → LeaderboardFrm: "selectRound(roundId = 5)".
4. LeaderboardFrm → PlayerDAO: "getAllPlayers(tournamentId)" — return `List<Player>`.
5. Loop qua mỗi player:
   - LeaderboardFrm → MatchDAO: "getMatchesByPlayerUpToRound(playerId, roundId=5)" — return `List<Match>`.
   - LeaderboardFrm: self-call "calculateStanding(player, matches)" — tính totalScore, opponentTotalScore.
6. LeaderboardFrm: self-call "sortStandings(totalScore DESC, opponentTotalScore DESC, elo DESC)".
7. LeaderboardFrm: self-call "displayStandings(list)".
8. Staff → LeaderboardFrm: "clickPlayer(playerId = 1)".
9. LeaderboardFrm → MatchDAO: "getMatchesByPlayerUpToRound(playerId=1, roundId=5)" — return `List<Match>`.
10. LeaderboardFrm: self-call "displayMatchDetails(list)".

### ASCII Sequence Diagram

```
Staff          LeaderboardFrm     PlayerDAO       MatchDAO
  |                  |                 |               |
  |---selectRound--->|                 |               |
  |                  |---getAllPlayers->|               |
  |                  |<-List<Player>---|               |
  |                  |                 |               |
  |                  |=== LOOP: each player            |
  |                  |                 |               |
  |                  |---getMatchesByPlayerUpToRound-->|
  |                  |<--List<Match>-------------------|
  |                  |                 |               |
  |                  |=== calculateStanding()          |
  |                  |=== END LOOP                     |
  |                  |                 |               |
  |                  |=== sortStandings()              |
  |                  |=== displayStandings()           |
  |                  |                 |               |
  |<--display--------|                 |               |
  |                  |                 |               |
  |---clickPlayer--->|                 |               |
  |                  |---getMatchesByPlayerUpToRound-->|
  |                  |<--List<Match>-------------------|
  |                  |                 |               |
  |                  |=== displayMatchDetails()        |
  |<--display--------|                 |               |
```

### Bảng chi tiết các bước trong Sequence Diagram

| Bước | From | To | Message | Loại | Ghi chú |
|------|------|-----|---------|------|---------|
| 1 | Staff | LeaderboardFrm | selectRound(roundId=5) | Synchrous | Staff chọn Round 5 từ combobox |
| 2 | LeaderboardFrm | PlayerDAO | getAllPlayers(tournamentId) | Synchrous | Lấy danh sách đấu thủ giải |
| 3 | PlayerDAO | LeaderboardFrm | return List\<Player\> | Return | Trả về 8 đấu thủ |
| 4 | LeaderboardFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=1, roundId=5) | Synchrous | Lấy trận của player 1 |
| 5 | MatchDAO | LeaderboardFrm | return List\<Match\> | Return | Trả về 5 trận |
| 6 | LeaderboardFrm | LeaderboardFrm | calculateStanding(player1, matches) | Self | Tính totalScore=4.5, oppTotal=18.5 |
| 7 | LeaderboardFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=2, roundId=5) | Synchrous | Lấy trận của player 2 |
| 8 | MatchDAO | LeaderboardFrm | return List\<Match\> | Return | Trả về 5 trận |
| 9 | LeaderboardFrm | LeaderboardFrm | calculateStanding(player2, matches) | Self | Tính totalScore=3.0, oppTotal=16.0 |
| 10 | LeaderboardFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=3, roundId=5) | Synchrous | Lấy trận của player 3 |
| 11 | MatchDAO | LeaderboardFrm | return List\<Match\> | Return | Trả về 5 trận |
| 12 | LeaderboardFrm | LeaderboardFrm | calculateStanding(player3, matches) | Self | Tính totalScore=4.0, oppTotal=17.0 |
| 13 | LeaderboardFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=4, roundId=5) | Synchrous | Lấy trận của player 4 |
| 14 | MatchDAO | LeaderboardFrm | return List\<Match\> | Return | Trả về 5 trận |
| 15 | LeaderboardFrm | LeaderboardFrm | calculateStanding(player4, matches) | Self | Tính totalScore=2.5, oppTotal=15.5 |
| 16 | LeaderboardFrm | LeaderboardFrm | sortStandings(...) | Self | Sắp xếp theo tổng điểm DESC |
| 17 | LeaderboardFrm | LeaderboardFrm | displayStandings(sortedList) | Self | Hiển thị bảng xếp hạng |
| 18 | LeaderboardFrm | Staff | return ranked table | Return | Bảng xếp hạng hiển thị |
| 19 | Staff | LeaderboardFrm | clickPlayer(playerId=1) | Synchrous | Staff click vào Nguyen Van A |
| 20 | LeaderboardFrm | MatchDAO | getMatchesByPlayerUpToRound(playerId=1, roundId=5) | Synchrous | Lấy chi tiết trận |
| 21 | MatchDAO | LeaderboardFrm | return List\<Match\> | Return | 5 trận của player 1 |
| 22 | LeaderboardFrm | LeaderboardFrm | displayMatchDetails(matches) | Self | Hiển thị chi tiết trận |
| 23 | LeaderboardFrm | Staff | return match details | Return | Chi tiết hiển thị |

---

## Câu 5: Blackbox Testcase

### Test Plan — Xem bảng xếp hạng

| ID | Test case | Mô tả | Kết quả mong đợi |
|----|-----------|-------|-----------------|
| TC01 | Xem BXH và chi tiết thành công | Chọn Round 5, click đấu thủ | BXH hiển thị đúng, chi tiết trận đúng |
| TC02 | BXH khi chưa có trận đấu | Chọn Round 1 nhưng chưa đấu | BXH trống hoặc thông báo |
| TC03 | Kiểm tra sắp xếp | Nhiều đấu thủ cùng điểm | Sắp xếp theo tổng điểm đối thủ → Elo |

### TC01: Xem bảng xếp hạng và chi tiết thành công

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
| 5 | 1 | 5 |

**tblPlayer:**
| ID | code | name | yob | nationality | elo | notes |
|----|------|------|-----|-------------|-----|-------|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2512 | |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2388 | |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2362 | |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2290 | |

**tblMatch (Round 1-5, chỉ liệt kê liên quan player 1):**
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
| 9 | 5 | Ban 1 | 1 | 3 | 1.0 | 0.0 | +10 | -10 |
| 10 | 5 | Ban 2 | 2 | 4 | 0.5 | 0.5 | +0 | +0 |

#### Kịch bản kiểm thử

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Đăng nhập với tài khoản staff hợp lệ | Vào giao diện chính |
| 2 | Nhấn **Statistics** → **View leaderboard** | Giao diện BXH hiện ra, combobox Round trống |
| 3 | Mở combobox Round, chọn **Round 5** | Bảng xếp hạng hiển thị 4 đấu thủ |
| 4 | Kiểm tra dòng Rank 1 | P001, Nguyen Van A, 1995, Vietnam, Total Score = **4.5**, Opponent Total Score = **18.5**, Elo = **2512** |
| 5 | Kiểm tra dòng Rank 2 | P003, Le Van C, 2000, Vietnam, Total Score = **3.0**, Opponent Total Score = **17.0**, Elo = **2362** |
| 6 | Kiểm tra dòng Rank 3 | P002, Tran Van B, 1998, Vietnam, Total Score = **1.5**, Opponent Total Score = **16.0**, Elo = **2388** |
| 7 | Kiểm tra dòng Rank 4 | P004, Pham Thi D, 1997, Vietnam, Total Score = **1.0**, Opponent Total Score = **15.5**, Elo = **2290** |
| 8 | Click vào dòng **Nguyen Van A** (Rank 1) | Vùng chi tiết hiển thị danh sách 5 trận |
| 9 | Kiểm tra chi tiết trận M01 | Opponent = Tran Van B, Result = **Win** (+1.0), Elo Change = **+12** |
| 10 | Kiểm tra chi tiết trận M03 | Opponent = Le Van C, Result = **Draw** (+0.5), Elo Change = **+0** |
| 11 | Kiểm tra chi tiết trận M05 | Opponent = Pham Thi D, Result = **Win** (+1.0), Elo Change = **+10** |
| 12 | Kiểm tra chi tiết trận M07 | Opponent = Tran Van B, Result = **Win** (+1.0), Elo Change = **+10** |
| 13 | Kiểm tra chi tiết trận M09 | Opponent = Le Van C, Result = **Win** (+1.0), Elo Change = **+10** |

#### Database sau khi chạy test

Module này chỉ đọc dữ liệu, không ghi. Database không thay đổi.

**tblMatch (không đổi):**
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
| 9 | 5 | Ban 1 | 1 | 3 | 1.0 | 0.0 | +10 | -10 |
| 10 | 5 | Ban 2 | 2 | 4 | 0.5 | 0.5 | +0 | +0 |

**tblPlayer (không đổi):**
| ID | code | name | yob | nationality | elo |
|----|------|------|-----|-------------|-----|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2512 |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2388 |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2362 |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2290 |

**So sánh:** Không có thay đổi nào trong database vì module chỉ đọc dữ liệu.
