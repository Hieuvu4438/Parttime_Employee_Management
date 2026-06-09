# Subject No. 26 — Chess Tournament — Module "Update results"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Cập nhật kết quả thi đấu

### Mô tả nghiệp vụ

Staff chọn menu cập nhật kết quả, trang cập nhật kết quả hiện ra. Staff chọn vòng đấu từ dropdown, chọn trận đấu từ danh sách, nhập điểm và hệ số Elo cho 2 đấu thủ, nhấn Update. Hệ thống thông báo thành công và quay về giao diện chọn vòng + trận.

### Bảng diễn biến chi tiết

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập vào hệ thống, giao diện chính hiển thị các menu chức năng. |
| 2 | Staff chọn menu **Update results** trên thanh menu. |
| 3 | Giao diện cập nhật kết quả hiện ra: combobox **Round** (chọn vòng đấu), vùng danh sách trận đấu trống. |
| 4 | Staff mở combobox Round, danh sách hiển thị: Round 1, Round 2, ..., Round 11. Staff chọn **Round 3**. |
| 5 | Hệ thống tải danh sách trận đấu của Round 3, hiển thị bảng: cột Match ID, Player 1, Player 2, Table, Score (chưa có). |
| 6 | Bảng hiển thị 6 trận: (M01) Nguyen Van A vs Tran Van B — Bàn 1, (M02) Le Van C vs Pham Thi D — Bàn 2, ... |
| 7 | Staff nhấp chuột chọn trận **M01: Nguyen Van A vs Tran Van B**. Dòng được highlight. |
| 8 | Vùng nhập kết quả phía dưới hiện ra: ô **Player 1 Score** (hiện tên Nguyen Van A), ô **Player 2 Score** (hiện tên Tran Van B), ô **Player 1 Elo Change**, ô **Player 2 Elo Change**. |
| 9 | Staff nhập: Player 1 Score = **1.0**, Player 2 Score = **0.0** (Nguyen Van A thắng). |
| 10 | Staff nhập: Player 1 Elo Change = **+12**, Player 2 Elo Change = **-12** (theo công thức FIDE). |
| 11 | Staff nhấn nút **Update**. |
| 12 | Hệ thống kiểm tra: điểm hợp lệ (0, 0.5, hoặc 1), tổng điểm 2 đấu thủ = 1.0 hoặc 1.0 (trường hợp hòa). Kiểm tra pass. |
| 13 | Hệ thống cập nhật tblMatch: player1Score = 1.0, player2Score = 0.0, player1EloChange = +12, player2EloChange = -12. |
| 14 | Hệ thống cập nhật tblPlayer: elo của Nguyen Van A += 12, elo của Tran Van B -= 12. |
| 15 | Hệ thống hiển thị thông báo: **"Cap nhat ket qua thanh cong"**. |
| 16 | Giao diện quay về trạng thái: combobox Round vẫn hiển thị Round 3, bảng trận đấu được reload với kết quả vừa cập nhật. Score cột M01 hiển thị "1.0 - 0.0". |

### Ngoại lệ

| Trường hợp | Xử lý |
|------------|--------|
| Staff nhập điểm không hợp lệ (ví dụ: 0.7, 2.0, -1) | Hệ thống cảnh báo: "Diem khong hop le. Chi chap nhan 0, 0.5, hoac 1". |
| Tổng điểm 2 đấu thủ != 1.0 (ví dụ: cả 2 đều 1.0) | Hệ thống cảnh报警: "Tong diem 2 dau thu phai bang 1.0". |
| Staff chưa chọn trận mà nhấn Update | Hệ thống cảnh báo: "Vui long chon tran dau". |
| Staff để trống ô Elo Change | Hệ thống cảnh báo: "Vui long nhap he so Elo thay doi". |

---

## Câu 2: Entity Class

### Mô tả ngôn ngữ tự nhiên

Hệ thống quản lý giải đấu cờ vua gồm nhiều **Tournament** (giải đấu). Mỗi giải đấu có nhiều **Round** (vòng đấu), tối đa 11 vòng theo luật Swiss. Mỗi vòng có nhiều **Match** (trận đấu), mỗi trận gồm 2 **Player** (đấu thủ) và kết quả điểm số, thay đổi Elo. Đấu thủ đăng ký tham gia giải đấu qua **TournamentRegistration**. Người dùng hệ thống là **User**.

### Trích xuất danh từ

| Danh từ | Entity class | Thuộc tính chính |
|---------|-------------|-----------------|
| Giải đấu | Tournament | id, code, name, year, time, location, description |
| Đấu thủ | Player | id, code, name, yob, nationality, elo, notes |
| Vòng đấu | Round | id, tournamentId, roundNumber |
| Trận đấu | Match | id, roundId, table, player1Id, player2Id, player1Score, player2Score, player1EloChange, player2EloChange |
| Đăng ký thi đấu | TournamentRegistration | id, tournamentId, playerId |
| Người dùng | User | id, username, password, role |

### Mối quan hệ

```
Tournament 1 --- * Round          (1 giải có nhiều vòng)
Round 1 --- * Match               (1 vòng có nhiều trận)
Player 1 --- * Match (as player1) (1 đấu thủ là player1 ở nhiều trận)
Player 1 --- * Match (as player2) (1 đấu thủ là player2 ở nhiều trận)
Tournament 1 --- * TournamentRegistration --- * Player  (N-N qua registration)
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
| - description: Str  |         +---------------------+
+---------------------+         |      Player         |
        | 1                     +---------------------+
        |                       | - id: int           |
        | *                     | - code: String      |
+---------------------+         | - name: String      |
|       Round         |         | - yob: int          |
+---------------------+         | - nationality: Str  |
| - id: int           |         | - elo: int          |
| - tournamentId: int |         | - notes: String     |
| - roundNumber: int  |         +---------------------+
+---------------------+              | 1        | 1
        | 1                          | *        | *
        | *                          |     +---------------------+
+---------------------+              |     |       Match         |
|       Match         |<-------------+-----+---------------------+
+---------------------+              | - id: int               |
| - id: int           |              | - roundId: int          |
| - roundId: int      |              | - table: String         |
| - table: String     |              | - player1Id: int        |
| - player1Id: int    |              | - player2Id: int        |
| - player2Id: int    |              | - player1Score: double  |
| - player1Score: dbl |              | - player2Score: double  |
| - player2Score: dbl |              | - player1EloChange: int |
| - player1EloChg: int|              | - player2EloChange: int |
| - player2EloChg: int|              +---------------------+
+---------------------+

+-----------------------------+
|   TournamentRegistration    |
+-----------------------------+
| - id: int                   |
| - tournamentId: int         |
| - playerId: int             |
+-----------------------------+
```

### Hướng dẫn vẽ Class Diagram trên Visual Paradigm

#### 1. Các bước vẽ tổng quan

| Bước | Thao tác trên Visual Paradigm |
|------|-------------------------------|
| 1 | Mở Visual Paradigm → File → New → chọn **Class Diagram** |
| 2 | Tạo các entity class boxes cho: Tournament, Round, Match, Player, User |
| 3 | Tạo các view class boxes: HomeFrm, UpdateResultFrm |
| 4 | Vẽ các đường kết nối theo bảng quan hệ, ghi multiplicity và role name |
| 5 | Ghi role name `player1` và `player2` trên 2 đường từ Player đến Match |

#### 2. Cấu trúc 1 class box (3 ngăn)

| Ngăn | Nội dung | Ví dụ Match |
|------|----------|-------------|
| Ngăn 1 — Tên class | Stereotype `<<entity>>` hoặc `<<boundary>>` + tên class | `<<entity>> Match` |
| Ngăn 2 — Thuộc tính | `-tenThuocTinh: KieuDuLieu` | `-id: int` `-table: String` `-player1Score: double` `-player2Score: double` `-player1EloChange: int` `-player2EloChange: int` |
| Ngăn 3 — Phương thức | `+tenPhuongThuc(thamSo): KieuTraVe` | `+updateMatchResult(match: Match): boolean` |

#### 3. Bảng chi tiết từng entity class

| Class | Stereotype | Attributes | Methods |
|-------|-----------|------------|---------|
| Tournament | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-year: int`, `-time: String`, `-location: String`, `-description: String` | — |
| Round | `<<entity>>` | `-id: int`, `-tournamentId: int`, `-roundNumber: int` | `+getRounds(tournamentId: int): List<Round>` |
| Match | `<<entity>>` | `-id: int`, `-roundId: int`, `-table: String`, `-player1Id: int`, `-player2Id: int`, `-player1Score: double`, `-player2Score: double`, `-player1EloChange: int`, `-player2EloChange: int` | `+getMatchesByRound(roundId: int): List<Match>`, `+updateMatchResult(match: Match): boolean` |
| Player | `<<entity>>` | `-id: int`, `-code: String`, `-name: String`, `-yob: int`, `-nationality: String`, `-elo: int`, `-notes: String` | `+updateElo(playerId: int, newElo: int): boolean` |
| User | `<<entity>>` | `-id: int`, `-username: String`, `-password: String`, `-role: String` | — |

#### 4. Bảng chi tiết view classes

| View class | Stereotype | UI elements (prefix convention) |
|------------|-----------|--------------------------------|
| HomeFrm | `<<boundary>>` | `subUpdateResult` (Button — chọn chức năng cập nhật kết quả) |
| UpdateResultFrm | `<<boundary>>` | `inRound` (ComboBox — chọn vòng đấu), `outsubListMatch` (Table — danh sách trận đấu, click chọn), `outPlayer1Name` (Label — tên đấu thủ 1), `outPlayer2Name` (Label — tên đấu thủ 2), `inPlayer1Score` (TextField — nhập điểm đấu thủ 1), `inPlayer2Score` (TextField — nhập điểm đấu thủ 2), `inPlayer1EloChange` (TextField — nhập Elo change đấu thủ 1), `inPlayer2EloChange` (TextField — nhập Elo change đấu thủ 2), `subUpdate` (Button — cập nhật kết quả) |

#### 5. Cách vẽ quan hệ

| Kiểu quan hệ | Ký hiệu trên Visual Paradigm | Khi nào dùng |
|---------------|------------------------------|---------------|
| **Association** | Đường liền nét, mũi tên tam giác rỗng ▷ | Quan hệ tham chiếu: Tournament → Round, Round → Match |
| **Composition** | Đường liền nét, đầu kim cương filled ◆ | Round chứa Match (Match không tồn tại nếu không có Round) |
| **Dependency** | Đường dashed, mũi tên tam giác rỗng ▷ | View gọi DAO |

#### 6. Cách ghi multiplicity

| Multiplicity | Cách ghi trên VP | Ý nghĩa |
|-------------|------------------|---------|
| 1..1 | `1` | Đúng 1 đối tượng |
| 1..* | `N` | Nhiều đối tượng |

Lưu ý đặc biệt cho Match: Match có 2 đường Association đến Player với role name khác nhau (`player1`, `player2`), mỗi đường có multiplicity `N` ở đầu Match và `1` ở đầu Player.

#### 7. Bảng quan hệ chi tiết

| Từ | Đến | Kiểu quan hệ | Multiplicity | Giải thích |
|----|-----|--------------|-------------|------------|
| Tournament | Round | Composition | 1 --- N | Một giải đấu có nhiều vòng đấu |
| Round | Match | Composition | 1 --- N | Một vòng đấu có nhiều trận đấu |
| Player | Match (vai player1) | Association | 1 --- N | Một đấu thủ là player1 ở nhiều trận |
| Player | Match (vai player2) | Association | 1 --- N | Một đấu thủ là player2 ở nhiều trận |
| Tournament | TournamentRegistration | Composition | 1 --- N | Giải đấu có nhiều đăng ký |
| TournamentRegistration | Player | Association | N --- 1 | Đăng ký liên kết đến đấu thủ |

#### 8. Ví dụ cụ thể trên Visual Paradigm

**Ví dụ 1: Vẽ Player (1) --- (N) Match với 2 role name**

1. Tạo class `Player` với attribute: `-id: int`, `-name: String`, `-elo: int`.
2. Tạo class `Match` với attribute: `-id: int`, `-table: String`, `-player1Score: double`, `-player2Score: double`.
3. Kéo tool **Association** → click `Player` → kéo sang `Match`. Đặt multiplicity: `1` (Player), `N` (Match). Thêm role name: `player1`.
4. Kéo thêm 1 đường **Association** nữa từ `Player` đến `Match`. Đặt multiplicity: `1` (Player), `N` (Match). Thêm role name: `player2`.

**Ví dụ 2: Vẽ Round (1) --- (N) Match (Composition)**

1. Tạo class `Round` với attribute: `-id: int`, `-roundNumber: int`.
2. Kéo tool **Composition** → click `Round` → kéo sang `Match`.
3. Đặt multiplicity: `1` ở đầu Round, `N` ở đầu Match.

---

### Classes diagram (analysis)

**Phân tích từ kịch bản (Câu 1):**

Bước 1-2: Staff đăng nhập → giao diện Home. View class: **HomeFrm**.
Bước 3: Staff chọn menu Update results → giao diện cập nhật kết quả. View class: **UpdateResultFrm**.
Bước 4: Staff mở combobox Round, chọn Round 3. UI: `inRound` (ComboBox — chọn vòng đấu).
Bước 5-6: Hệ thống tải danh sách trận đấu, hiển thị bảng. UI: `outsubListMatch` (Table — danh sách trận, click chọn dòng được).
Bước 7: Staff click chọn trận M01. Vùng nhập kết quả phía dưới hiện ra.
Bước 8: Hiển thị tên 2 đấu thủ. UI: `outPlayer1Name`, `outPlayer2Name` (Label — hiển thị tên).
Bước 9-10: Staff nhập điểm và Elo change. UI: `inPlayer1Score`, `inPlayer2Score`, `inPlayer1EloChange`, `inPlayer2EloChange` (TextField — nhập dữ liệu).
Bước 11: Staff nhấn Update. UI: `subUpdate` (Button — thực hiện cập nhật).
Bước 12-16: Hệ thống kiểm tra, cập nhật DB, thông báo, reload danh sách.

**Các view class:**

| View class | Loại | Mô tả |
|------------|------|-------|
| HomeFrm | Form | Giao diện chính, chứa menu Update results |
| UpdateResultFrm | Form | Giao diện cập nhật kết quả thi đấu |

**Các UI element:**

| UI Element | Kiểu | View class | Mô tả |
|------------|------|------------|-------|
| `inRound` | ComboBox | UpdateResultFrm | Chọn vòng đấu (Round 1 .. Round 11) |
| `outsubListMatch` | Table | UpdateResultFrm | Bảng danh sách trận đấu, click chọn dòng |
| `outPlayer1Name` | Label | UpdateResultFrm | Tên đấu thủ 1 (hiển thị khi chọn trận) |
| `outPlayer2Name` | Label | UpdateResultFrm | Tên đấu thủ 2 (hiển thị khi chọn trận) |
| `inPlayer1Score` | TextField | UpdateResultFrm | Ô nhập điểm đấu thủ 1 (0, 0.5, hoặc 1) |
| `inPlayer2Score` | TextField | UpdateResultFrm | Ô nhập điểm đấu thủ 2 (0, 0.5, hoặc 1) |
| `inPlayer1EloChange` | TextField | UpdateResultFrm | Ô nhập thay đổi Elo đấu thủ 1 |
| `inPlayer2EloChange` | TextField | UpdateResultFrm | Ô nhập thay đổi Elo đấu thủ 2 |
| `subUpdate` | Button | UpdateResultFrm | Nút Update — lưu kết quả vào DB |

**Các method:**

| Method | Input | Output | Entity |
|--------|-------|--------|--------|
| `getRounds()` | tournamentId | List\<Round\> | Round |
| `getMatchesByRound()` | roundId | List\<Match\> | Match |
| `updateMatchResult()` | match (id, player1Score, player2Score, player1EloChange, player2EloChange) | boolean | Match |
| `updateElo()` | playerId, newElo | boolean | Player |

**Tong hop:**

- View classes: HomeFrm, UpdateResultFrm
- Methods: getRounds(), getMatchesByRound(), updateMatchResult(), updateElo()

### Bảng quan hệ (Relationship Table)

| Entity 1 | Multiplicity | Entity 2 | Mô tả |
|----------|-------------|----------|-------|
| Tournament | 1 --- * | Round | 1 giải đấu có nhiều vòng đấu |
| Round | 1 --- * | Match | 1 vòng đấu có nhiều trận đấu |
| Player | 1 --- * | Match | 1 đấu thủ tham gia nhiều trận (vai player1) |
| Player | 1 --- * | Match | 1 đấu thủ tham gia nhiều trận (vai player2) |
| Tournament | * --- * | Player | Qua TournamentRegistration |

---

## Câu 3: Thiết kế tĩnh

### View classes

**UpdateResultFrm** (Giao diện cập nhật kết quả):

| Thành phần UI | Loại | Mô tả |
|---------------|------|-------|
| `inRound` | ComboBox | Dropdown chọn vòng đấu (Round 1 .. Round 11) |
| `outsubListMatch` | Table | Bảng danh sách trận đấu (Match ID, Player 1, Player 2, Table, Score). Có thể click chọn dòng. |
| `outPlayer1Name` | Label | Tên đấu thủ 1 (hiển thị khi chọn trận) |
| `outPlayer2Name` | Label | Tên đấu thủ 2 (hiển thị khi chọn trận) |
| `inPlayer1Score` | TextField | Ô nhập điểm đấu thủ 1 (0, 0.5, hoặc 1) |
| `inPlayer2Score` | TextField | Ô nhập điểm đấu thủ 2 (0, 0.5, hoặc 1) |
| `inPlayer1EloChange` | TextField | Ô nhập thay đổi Elo đấu thủ 1 (số nguyên, có thể âm) |
| `inPlayer2EloChange` | TextField | Ô nhập thay đổi Elo đấu thủ 2 (số nguyên, có thể âm) |
| `subUpdate` | Button | Nút "Update" — lưu kết quả vào database |

### DAO classes

| DAO | Phương thức | Mô tả |
|-----|-------------|--------|
| RoundDAO | `getRounds(tournamentId): List<Round>` | Lấy danh sách vòng đấu của giải |
| MatchDAO | `getMatchesByRound(roundId): List<Match>` | Lấy danh sách trận đấu theo vòng |
| MatchDAO | `updateMatchResult(match): boolean` | Cập nhật điểm và Elo change cho 1 trận |
| PlayerDAO | `getPlayerById(playerId): Player` | Lấy thông tin đấu thủ (để hiển thị tên) |
| PlayerDAO | `updateElo(playerId, newElo): boolean` | Cập nhật Elo mới cho đấu thủ |

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
| Round | Hiển thị trong combobox, xác định danh sách trận |
| Match | Đối tượng chính: hiển thị, cập nhật kết quả |
| Player | Hiển thị tên đấu thủ, cập nhật Elo |

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
2. Tạo 4 class: `UpdateResultFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. `UpdateResultFrm` có attribute: `inRound: ComboBox`, `outsubListMatch: Table`, `inPlayer1Score: TextField`, `inPlayer2Score: TextField`, `inPlayer1EloChange: TextField`, `inPlayer2EloChange: TextField`, `subUpdate: Button`.
4. Vẽ dependency (dashed arrow) từ `UpdateResultFrm` đến `RoundDAO`, `MatchDAO`, `PlayerDAO`.
5. Ghi chú method trên mỗi dependency arrow: `getRounds()`, `getMatchesByRound()`, `updateMatchResult()`, `updateElo()`.
6. Tạo 3 entity class: `Round`, `Match`, `Player` với attributes đầy đủ.
7. Vẽ association giữa `Round` (1) -- (*) `Match`, `Player` (1) -- (*) `Match`.

---

## Câu 4: Sequence Diagram

### Visual Paradigm — Hướng dẫn vẽ

1. Mở Visual Paradigm → New → Sequence Diagram.
2. Tạo 5 lifeline (Actor + Object): `Staff`, `UpdateResultFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
3. Staff → UpdateResultFrm: message "selectRound(roundId = 3)".
4. UpdateResultFrm → RoundDAO: message "getRounds(tournamentId)" — return `List<Round>`.
5. UpdateResultFrm → MatchDAO: message "getMatchesByRound(roundId = 3)" — return `List<Match>`.
6. UpdateResultFrm: self-call "displayMatches(list)".
7. Staff → UpdateResultFrm: message "selectMatch(matchId = 1)".
8. UpdateResultFrm: self-call "displayMatchDetail(match)".
9. Staff → UpdateResultFrm: message "enterScores(1.0, 0.0, +12, -12)".
10. Staff → UpdateResultFrm: message "clickUpdate()".
11. UpdateResultFrm: self-call "validateInput()" — return true.
12. UpdateResultFrm → MatchDAO: message "updateMatchResult(match)" — return true.
13. UpdateResultFrm → PlayerDAO: message "updateElo(player1Id, 2512)" — return true.
14. UpdateResultFrm → PlayerDAO: message "updateElo(player2Id, 2388)" — return true.
15. UpdateResultFrm: self-call "showSuccessMessage('Cap nhat ket qua thanh cong')".
16. UpdateResultFrm: self-call "reloadMatches(roundId = 3)".

### ASCII Sequence Diagram

```
Staff          UpdateResultFrm     RoundDAO       MatchDAO        PlayerDAO
  |                  |                 |               |               |
  |---selectRound--->|                 |               |               |
  |                  |---getRounds---->|               |               |
  |                  |<--List<Round>---|               |               |
  |                  |                 |               |               |
  |                  |---getMatchesByRound------------>|               |
  |                  |<--List<Match>-------------------|               |
  |                  |                 |               |               |
  |                  |=== displayMatches()             |               |
  |                  |                 |               |               |
  |---selectMatch--->|                 |               |               |
  |                  |=== displayMatchDetail()         |               |
  |                  |                 |               |               |
  |---enterScores--->|                 |               |               |
  |---clickUpdate--->|                 |               |               |
  |                  |=== validateInput()              |               |
  |                  |                 |               |               |
  |                  |---updateMatchResult------------>|               |
  |                  |<--true-------------------------|               |
  |                  |                 |               |               |
  |                  |---updateElo-------------------->|               |
  |                  |<--true------------------------------------------|
  |                  |---updateElo-------------------->|               |
  |                  |<--true------------------------------------------|
  |                  |                 |               |               |
  |                  |=== showSuccessMessage()         |               |
  |                  |=== reloadMatches()              |               |
  |<--reload---------|                 |               |               |
```

### Bảng chi tiết các bước trong Sequence Diagram

| Bước | From | To | Message | Loại | Ghi chú |
|------|------|-----|---------|------|---------|
| 1 | Staff | UpdateResultFrm | selectRound(roundId=3) | Synchrous | Staff chọn Round 3 từ combobox |
| 2 | UpdateResultFrm | RoundDAO | getRounds(tournamentId) | Synchrous | Lấy danh sách vòng đấu |
| 3 | RoundDAO | UpdateResultFrm | return List\<Round\> | Return | Trả về 11 vòng |
| 4 | UpdateResultFrm | MatchDAO | getMatchesByRound(roundId=3) | Synchrous | Lấy danh sách trận Round 3 |
| 5 | MatchDAO | UpdateResultFrm | return List\<Match\> | Return | Trả về 6 trận |
| 6 | UpdateResultFrm | UpdateResultFrm | displayMatches(list) | Self | Hiển thị bảng trận đấu |
| 7 | Staff | UpdateResultFrm | selectMatch(matchId=1) | Synchrous | Staff click chọn trận M01 |
| 8 | UpdateResultFrm | UpdateResultFrm | displayMatchDetail(match) | Self | Hiển thị ô nhập kết quả |
| 9 | Staff | UpdateResultFrm | enterScores(1.0, 0.0, +12, -12) | Synchrous | Staff nhập điểm + Elo |
| 10 | Staff | UpdateResultFrm | clickUpdate() | Synchrous | Staff nhấn nút Update |
| 11 | UpdateResultFrm | UpdateResultFrm | validateInput() | Self | Kiểm tra dữ liệu hợp lệ |
| 12 | UpdateResultFrm | MatchDAO | updateMatchResult(match) | Synchrous | Cập nhật điểm trận đấu |
| 13 | MatchDAO | UpdateResultFrm | return true | Return | Lưu thành công |
| 14 | UpdateResultFrm | PlayerDAO | updateElo(player1Id, 2512) | Synchrous | Cập nhật Elo player1 (+12) |
| 15 | PlayerDAO | UpdateResultFrm | return true | Return | Cập nhật thành công |
| 16 | UpdateResultFrm | PlayerDAO | updateElo(player2Id, 2388) | Synchrous | Cập nhật Elo player2 (-12) |
| 17 | PlayerDAO | UpdateResultFrm | return true | Return | Cập nhật thành công |
| 18 | UpdateResultFrm | UpdateResultFrm | showSuccessMessage(...) | Self | Hiển thị thông báo thành công |
| 19 | UpdateResultFrm | UpdateResultFrm | reloadMatches(roundId=3) | Self | Tải lại danh sách trận |
| 20 | UpdateResultFrm | Staff | display refreshed list | Return | Giao diện cập nhật |

---

## Câu 5: Blackbox Testcase

### Test Plan — Cập nhật kết quả thi đấu

| ID | Test case | Mô tả | Kết quả mong đợi |
|----|-----------|-------|-----------------|
| TC01 | Cập nhật kết quả thành công | Nhập điểm và Elo hợp lệ, nhấn Update | Thông báo thành công, DB cập nhật |
| TC02 | Điểm không hợp lệ | Nhập điểm = 0.7 | Cảnh báo "Diem khong hop le" |
| TC03 | Tổng điểm sai | Cả 2 đấu thủ đều 1.0 | Cảnh báo "Tong diem phai bang 1.0" |
| TC04 | Chưa chọn trận | Nhấn Update khi chưa chọn trận | Cảnh báo "Vui long chon tran dau" |
| TC05 | Để trống Elo | Không nhập Elo change | Cảnh báo "Vui long nhap he so Elo" |

### TC01: Cập nhật kết quả thành công

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

**tblPlayer:**
| ID | code | name | yob | nationality | elo | notes |
|----|------|------|-----|-------------|-----|-------|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | 2500 | |
| 2 | P002 | Tran Van B | 1998 | Vietnam | 2400 | |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2350 | |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2300 | |

**tblMatch (liên quan Round 3):**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 11 | 3 | Ban 1 | 1 | 2 | NULL | NULL | NULL | NULL |
| 12 | 3 | Ban 2 | 3 | 4 | NULL | NULL | NULL | NULL |

#### Kịch bản kiểm thử

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|-----------------|
| 1 | Đăng nhập với tài khoản staff hợp lệ | Vào giao diện chính |
| 2 | Nhấn menu **Update results** | Giao diện cập nhật kết quả hiện ra, combobox Round trống |
| 3 | Mở combobox Round, chọn **Round 3** | Bảng hiển thị 2 trận: M11 (Nguyen Van A vs Tran Van B, Ban 1), M12 (Le Van C vs Pham Thi D, Ban 2). Cột Score trống. |
| 4 | Click chọn dòng **M11: Nguyen Van A vs Tran Van B** | Vùng nhập kết quả hiện ra: Player 1 = Nguyen Van A, Player 2 = Tran Van B. Các ô nhập trống. |
| 5 | Nhập Player 1 Score = **1.0**, Player 2 Score = **0.0** | Giá trị hiển thị trong ô nhập. |
| 6 | Nhập Player 1 Elo Change = **+12**, Player 2 Elo Change = **-12** | Giá trị hiển thị trong ô nhập. |
| 7 | Nhấn nút **Update** | Hệ thống kiểm tra dữ liệu hợp lệ (pass). |
| 8 | Kiểm tra kết quả | Thông báo **"Cap nhat ket qua thanh cong"**. Bảng reload, cột Score M11 hiển thị "1.0 - 0.0". |

#### Database sau khi chạy test

**tblMatch:**
| ID | roundID | table | player1ID | player2ID | player1Score | player2Score | player1EloChange | player2EloChange |
|----|---------|-------|-----------|-----------|--------------|--------------|------------------|------------------|
| 11 | 3 | Ban 1 | 1 | 2 | **1.0** | **0.0** | **+12** | **-12** |
| 12 | 3 | Ban 2 | 3 | 4 | NULL | NULL | NULL | NULL |

**tblPlayer:**
| ID | code | name | yob | nationality | elo | notes |
|----|------|------|-----|-------------|-----|-------|
| 1 | P001 | Nguyen Van A | 1995 | Vietnam | **2512** | |
| 2 | P002 | Tran Van B | 1998 | Vietnam | **2388** | |
| 3 | P003 | Le Van C | 2000 | Vietnam | 2350 | |
| 4 | P004 | Pham Thi D | 1997 | Vietnam | 2300 | |

**So sánh thay đổi:**
- tblMatch ID=11: player1Score NULL -> 1.0, player2Score NULL -> 0.0, player1EloChange NULL -> +12, player2EloChange NULL -> -12.
- tblPlayer ID=1: elo 2500 -> 2512 (+12).
- tblPlayer ID=2: elo 2400 -> 2388 (-12).
- Các dòng khác không thay đổi.
