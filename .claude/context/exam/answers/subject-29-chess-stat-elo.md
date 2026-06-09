# Subject No. 29 — Chess Tournament — Module "Statistics of Elo change"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Thống kê thay đổi Elo

### Mô tả nghiệp vụ

Staff chọn menu thống kê, chọn "Thống kê thay đổi Elo". Hệ thống hiển thị danh sách đấu thủ: mã, tên, năm sinh, quốc tịch, Elo cũ, Elo mới, thay đổi Elo. Sắp xếp theo Elo thay đổi giảm dần, nếu bằng thì theo Elo mới giảm dần. Staff click vào một đấu thủ để xem chi tiết trận đấu: mã trận, tên đối thủ, kết quả (thắng/hòa/thua), thay đổi Elo.

### Bảng diễn biến chi tiết

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, giao diện chính hiển thị các menu chức năng. |
| 2 | Staff chọn menu **Statistics** → **Elo change statistics**. |
| 3 | Giao diện thống kê Elo hiện ra: bảng danh sách đấu thủ. Hệ thống tự động tải dữ liệu từ tournament hiện tại. |
| 4 | Bảng hiển thị: cột Code, Name, YOB, Nationality, Old Elo, New Elo, Elo Change. Dữ liệu sắp xếp theo Elo Change giảm dần → New Elo giảm dần. |
| 5 | Dòng đầu tiên: P001, Nguyen Van A, 1995, Vietnam, Old Elo = 2500, New Elo = 2512, Elo Change = **+12**. |
| 6 | Dòng thứ 2: P003, Le Van C, 2000, Vietnam, Old Elo = 2350, New Elo = 2362, Elo Change = **+12**. (Cùng +12, nhưng New Elo 2362 < 2512 nên xếp sau). |
| 7 | Dòng thứ 3: P004, Pham Thi D, 1997, Vietnam, Old Elo = 2300, New Elo = 2290, Elo Change = **-10**. |
| 8 | Dòng thứ 4: P002, Tran Van B, 1998, Vietnam, Old Elo = 2400, New Elo = 2388, Elo Change = **-12**. |
| 9 | Staff click vào dòng **Nguyen Van A**. Dòng được highlight. |
| 10 | Vùng chi tiết phía dưới hiển thị danh sách trận đã đấu của Nguyen Van A trong giải. |
| 11 | Chi tiết: Match M01 vs Tran Van B — Win (+1.0), Elo Change = +12. |
| 12 | Tiếp tục: M03 vs Le Van C — Draw (+0.5), Elo Change = +0. M05 vs Pham Thi D — Win (+1.0), Elo Change = +10. M07 vs Tran Van B — Win (+1.0), Elo Change = +10. M09 vs Le Van C — Win (+1.0), Elo Change = +10. Tổng Elo change = +42 (nhưng New Elo = Old Elo + tổng change thực tế). |

### Ngoại lệ

| Trường hợp | Xử lý |
|------------|--------|
| Tournament chưa có trận đấu nào | Bảng trống, hiển thị thông báo "Chua co du lieu". |
| Đấu thủ chưa đấu trận nào | Old Elo = New Elo, Elo Change = 0. |

---

## Câu 2: Entity Class

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý giải đấu cờ vua gồm nhiều **Tournament** (giải đấu). Mỗi giải đấu có nhiều **Round** (vòng đấu). Mỗi vòng có nhiều **Match** (trận đấu) giữa 2 **Player** (đấu thủ). Mỗi trận có kết quả điểm số và thay đổi Elo cho cả 2 đấu thủ. Khi thống kê Elo, hệ thống tổng hợp thay đổi Elo từ tất cả trận đã đấu trong giải. Người dùng là **User**.

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

### Derived class cho thống kê

**EloStat** (không lưu DB, tính toán từ Match):
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| player | Player | Thông tin đấu thủ |
| oldElo | int | Elo trước giải đấu |
| newElo | int | Elo hiện tại (sau tất cả trận) |
| eloChange | int | newElo - oldElo |

**MatchDetail** (không lưu DB, chi tiết trận cho 1 đấu thủ):
| Thuộc tính | Kiểu | Mô tả |
|------------|------|-------|
| matchId | int | Mã trận |
| opponentName | String | Tên đối thủ |
| result | String | Win / Draw / Loss |
| score | double | Điểm đạt được (1.0, 0.5, 0.0) |
| eloChange | int | Thay đổi Elo trận này |

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-2: Staff đăng nhập → giao diện Home → chọn menu Statistics → Elo change statistics. View class: **HomeFrm**, **EloStatFrm**.
Bước 3: Giao diện thống kê Elo hiện ra. Hệ thống tự động tải dữ liệu (không cần nút View).
Bước 4-8: Bảng hiển thị danh sách đấu thủ: Code, Name, YOB, Nationality, Old Elo, New Elo, Elo Change. Sắp xếp theo Elo Change DESC → New Elo DESC. UI: `outsubListEloStat` (Table — bảng thống kê Elo, click chọn dòng được).
Bước 9: Staff click vào dòng Nguyen Van A.
Bước 10-12: Vùng chi tiết hiển thị danh sách trận đã đấu. UI: `outListMatchDetail` (Table — chi tiết trận đấu).

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Statistics → Elo change statistics |
| EloStatFrm | Form | Giao diện thống kê thay đổi Elo |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `outsubListEloStat` | Table | EloStatFrm | Bảng thống kê Elo (Code, Name, YOB, Nationality, Old Elo, New Elo, Elo Change), click chọn dòng |
| `outListMatchDetail` | Table | EloStatFrm | Chi tiết trận đấu của đấu thủ được chọn (Match ID, Opponent, Result, Score, Elo Change) |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getAllPlayers()` | tournamentId | List\<Player\> | Player |
| `getMatchesByPlayerTournament()` | playerId, tournamentId | List\<Match\> | Match |

**Tong hop:**

- View classes: HomeFrm, EloStatFrm
- Methods: getAllPlayers(), getMatchesByPlayerTournament()

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

**EloStatFrm** (Giao diện thống kê thay đổi Elo):

| Thành phần UI | Loại | Mô tả |
|---------------|------|-------|
| `outsubListEloStat` | Table | Bảng thống kê Elo (Code, Name, YOB, Nationality, Old Elo, New Elo, Elo Change). Sắp xếp: Elo Change DESC → New Elo DESC. Có thể click chọn dòng. |
| `outListMatchDetail` | Table | Chi tiết trận đấu của đấu thủ được chọn (Match ID, Opponent Name, Result, Score, Elo Change). |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|--------|
| PlayerDAO | `getAllPlayers(tournamentId): List<Player>` | Lấy danh sách đấu thủ tham gia giải |
| PlayerDAO | `getPlayerById(playerId): Player` | Lấy thông tin 1 đấu thủ |
| MatchDAO | `getMatchesByPlayerTournament(playerId, tournamentId): List<Match>` | Lấy tất cả trận của đấu thủ trong giải (qua join Round) |
| RoundDAO | `getRounds(tournamentId): List<Round>` | Lấy danh sách vòng đấu |

### Logic tính Elo stat (trong service)

```
Với mỗi player:
    1. oldElo = elo ban đầu (cần lưu riêng hoặc tính ngược: currentElo - tổng change).
    2. Lấy tất cả trận trong giải.
    3. Tính tổng eloChange = tổng player1EloChange (nếu player là player1) + tổng player2EloChange (nếu player là player2).
    4. newElo = oldElo + eloChange.
    5. Sắp xếp: eloChange DESC → newElo DESC.
```

Ghi chú: Trong thực tế, oldElo cần được lưu khi đăng ký giải hoặc tính từ elo hiện tại trừ tổng change.

### Entity types liên quan

| Entity | Vai trò trong module |
|--------|---------------------|
| Tournament | Xác định giải đấu hiện tại |
| Round | Liên kết trận đấu với giải |
| Match | Nguồn dữ liệu: eloChange cho mỗi trận |
| Player | Hiển thị thông tin đấu thủ |

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
2. Tạo 3 class: `EloStatFrm`, `PlayerDAO`, `MatchDAO`.
3. `EloStatFrm` có attribute: `outsubListEloStat: Table`, `outListMatchDetail: Table`.
4. Tạo class `EloStat` với attributes: `player: Player`, `oldElo: int`, `newElo: int`, `eloChange: int`.
5. Tạo class `MatchDetail` với attributes: `matchId: int`, `opponentName: String`, `result: String`, `score: double`, `eloChange: int`.
6. Vẽ dependency từ `EloStatFrm` đến `PlayerDAO`, `MatchDAO`.
7. Vẽ association giữa `EloStatFrm` (1) -- (*) `EloStat` và `EloStatFrm` (1) -- (*) `MatchDetail`.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 3 lifeline: `Staff`, `EloStatFrm`, `PlayerDAO`, `MatchDAO`.
3. Staff → EloStatFrm: "open()" (mở giao diện).
4. EloStatFrm → PlayerDAO: "getAllPlayers(tournamentId)" — return `List<Player>`.
5. Loop qua mỗi player:
   - EloStatFrm → MatchDAO: "getMatchesByPlayerTournament(playerId, tournamentId)" — return `List<Match>`.
   - EloStatFrm: self-call "calculateEloStat(player, matches)" — tính oldElo, newElo, eloChange.
6. EloStatFrm: self-call "sortEloStat(eloChange DESC, newElo DESC)".
7. EloStatFrm: self-call "displayEloStat(list)".
8. Staff → EloStatFrm: "clickPlayer(playerId = 1)".
9. EloStatFrm → MatchDAO: "getMatchesByPlayerTournament(playerId=1, tournamentId)" — return `List<Match>`.
10. EloStatFrm: self-call "displayMatchDetails(list)".

### ASCII Sequence Diagram

```
Staff          EloStatFrm          PlayerDAO       MatchDAO
  |                  |                 |               |
  |---open---------->|                 |               |
  |                  |---getAllPlayers->|               |
  |                  |<-List<Player>---|               |
  |                  |                 |               |
  |                  |=== LOOP: each player            |
  |                  |                 |               |
  |                  |---getMatchesByPlayerTournament->|
  |                  |<--List<Match>-------------------|
  |                  |                 |               |
  |                  |=== calculateEloStat()           |
  |                  |=== END LOOP                     |
  |                  |                 |               |
  |                  |=== sortEloStat()                |
  |                  |=== displayEloStat()             |
  |                  |                 |               |
  |<--display--------|                 |               |
  |                  |                 |               |
  |---clickPlayer--->|                 |               |
  |                  |---getMatchesByPlayerTournament->|
  |                  |<--List<Match>-------------------|
  |                  |                 |               |
  |                  |=== displayMatchDetails()        |
  |<--display--------|                 |               |
```

### Bảng chi tiết các bước trong Sequence Diagram

| Bước | From | To | Message | Loại | Ghi chú |
|------|------|-----|---------|------|---------|
| 1 | Staff | EloStatFrm | open() | Synchrous | Mở giao diện thống kê Elo |
| 2 | EloStatFrm | PlayerDAO | getAllPlayers(tournamentId) | Synchrous | Lấy danh sách 4 đấu thủ |
| 3 | PlayerDAO | EloStatFrm | return List\<Player\> | Return | 4 đấu thủ |
| 4 | EloStatFrm | MatchDAO | getMatchesByPlayerTournament(playerId=1, tournamentId=1) | Synchrous | Trận của player 1 trong giải |
| 5 | MatchDAO | EloStatFrm | return List\<Match\> | Return | 11 trận |
| 6 | EloStatFrm | EloStatFrm | calculateEloStat(player1, matches) | Self | oldElo=2500, newElo=2542, change=+42 |
| 7 | EloStatFrm | MatchDAO | getMatchesByPlayerTournament(playerId=2, tournamentId=1) | Synchrous | Trận của player 2 |
| 8 | MatchDAO | EloStatFrm | return List\<Match\> | Return | 11 trận |
| 9 | EloStatFrm | EloStatFrm | calculateEloStat(player2, matches) | Self | oldElo=2400, newElo=2360, change=-40 |
| 10 | EloStatFrm | MatchDAO | getMatchesByPlayerTournament(playerId=3, tournamentId=1) | Synchrous | Trận của player 3 |
| 11 | MatchDAO | EloStatFrm | return List\<Match\> | Return | 11 trận |
| 12 | EloStatFrm | EloStatFrm | calculateEloStat(player3, matches) | Self | oldElo=2350, newElo=2375, change=+25 |
| 13 | EloStatFrm | MatchDAO | getMatchesByPlayerTournament(playerId=4, tournamentId=1) | Synchrous | Trận của player 4 |
| 14 | MatchDAO | EloStatFrm | return List\<Match\> | Return | 11 trận |
| 15 | EloStatFrm | EloStatFrm | calculateEloStat(player4, matches) | Self | oldElo=2300, newElo=2223, change=-77 |
| 16 | EloStatFrm | EloStatFrm | sortEloStat(eloChange DESC, newElo DESC) | Self | Sắp xếp kết quả |
| 17 | EloStatFrm | EloStatFrm | displayEloStat(sortedList) | Self | Hiển thị bảng thống kê |
| 18 | EloStatFrm | Staff | return table | Return | Bảng thống kê hiển thị |
| 19 | Staff | EloStatFrm | clickPlayer(playerId=1) | Synchrous | Staff click vào Nguyen Van A |
| 20 | EloStatFrm | MatchDAO | getMatchesByPlayerTournament(playerId=1, tournamentId=1) | Synchrous | Lấy chi tiết trận |
| 21 | MatchDAO | EloStatFrm | return List\<Match\> | Return | 11 trận |
| 22 | EloStatFrm | EloStatFrm | displayMatchDetails(matches) | Self | Hiển thị chi tiết trận |
| 23 | EloStatFrm | Staff | return match details | Return | Chi tiết hiển thị |

---

## Câu 5: Blackbox Testcase

### Test Plan — Thống kê thay đổi Elo

| ID | Test case | Mô tả | Kết quả mong đợi |
|----|-----------|-------|-----------------|
| TC01 | Xem thống kê Elo và chi tiết thành công | Mở giao diện, click đấu thủ | Bảng Elo đúng, chi tiết trận đúng |
| TC02 | Tournament chưa có trận | Giải đấu mới tạo, chưa đấu | Bảng trống, thông báo |
| TC03 | Kiểm tra sắp xếp | Nhiều đấu thủ cùng eloChange | Sắp xếp theo newElo giảm dần |

### TC01: Xem thống kê thay đổi Elo thành công

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
| 6 | 1 | 6 |
| 7 | 1 | 7 |
| 8 | 1 | 8 |
| 9 | 1 | 9 |
| 10 | 1 | 10 |
| 11 | 1 | 11 |

**tblPlayer:**
| ID | code | name | yob | nationality | elo | notes |
|----|------|------|-----|-------------|-----|-------|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2542 | |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2360 | |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2375 | |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2223 | |

**tblMatch (liên quan player 1 — Round 1-5):**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 1 | 1 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +12 | -12 |
| 3 | 2 | Ban 1 | 1 | 3 | 0.5 | 0.5 | +0 | +0 |
| 5 | 3 | Ban 1 | 1 | 4 | 1.0 | 0.0 | +10 | -10 |
| 7 | 4 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +10 | -10 |
| 9 | 5 | Ban 1 | 1 | 3 | 1.0 | 0.0 | +10 | -10 |

#### Kịch bản kiểm thử

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Đăng nhập với tài khoản staff hợp lệ | Vào giao diện chính |
| 2 | Nhấn **Statistics** → **Elo change statistics** | Giao diện thống kê Elo hiện ra, bảng hiển thị 4 đấu thủ |
| 3 | Kiểm tra dòng 1 (sắp xếp theo Elo Change DESC) | P001, Nguyen Van A, 1995, Vietnam, Old Elo = **2500**, New Elo = **2542**, Elo Change = **+42** |
| 4 | Kiểm tra dòng 2 | P003, Le Van C, 2000, Vietnam, Old Elo = **2350**, New Elo = **2375**, Elo Change = **+25** |
| 5 | Kiểm tra dòng 3 | P002, Tran Van B, 1998, Vietnam, Old Elo = **2400**, New Elo = **2360**, Elo Change = **-40** |
| 6 | Kiểm tra dòng 4 | P004, Pham Thi D, 1997, Vietnam, Old Elo = **2300**, New Elo = **2223**, Elo Change = **-77** |
| 7 | Click vào dòng **Nguyen Van A** (dòng 1) | Vùng chi tiết hiển thị danh sách 11 trận |
| 8 | Kiểm tra chi tiết trận M01 | Opponent = Tran Van B, Result = **Win** (+1.0), Elo Change = **+12** |
| 9 | Kiểm tra chi tiết trận M03 | Opponent = Le Van C, Result = **Draw** (+0.5), Elo Change = **+0** |
| 10 | Kiểm tra chi tiết trận M05 | Opponent = Pham Thi D, Result = **Win** (+1.0), Elo Change = **+10** |

#### Database sau khi chạy test

Module này chỉ đọc dữ liệu, không ghi. Database không thay đổi.

**tblMatch (liên quan player 1, không đổi):**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 1 | 1 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +12 | -12 |
| 3 | 2 | Ban 1 | 1 | 3 | 0.5 | 0.5 | +0 | +0 |
| 5 | 3 | Ban 1 | 1 | 4 | 1.0 | 0.0 | +10 | -10 |
| 7 | 4 | Ban 1 | 1 | 2 | 1.0 | 0.0 | +10 | -10 |
| 9 | 5 | Ban 1 | 1 | 3 | 1.0 | 0.0 | +10 | -10 |

**tblPlayer (không đổi):**
| ID | code | name | yob | nationality | elo |
|----|------|------|-----|-------------|-----|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2542 |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2360 |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2375 |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2223 |

**So sánh:** Không có thay đổi nào trong database vì module chỉ đọc dữ liệu.
