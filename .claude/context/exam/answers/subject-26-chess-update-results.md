# Subject No. 26 — Chess Tournament — Module "Update results"

> **Domain:** Chess Tournament Management

---

## Câu 1: Scenario — Cập nhật kết quả

| Bước | Diễn biến |
|------|-----------|
| 1 | Staff đăng nhập, chọn **Update results**. |
| 2 | Giao diện cập nhật: combobox chọn vòng đấu (Round). |
| 3 | Staff chọn "Round 1". Hệ thống hiển thị danh sách trận đấu theo vòng. |
| 4 | Staff chọn trận "Player A vs Player B". |
| 5 | Giao diện nhập kết quả: ô nhập điểm 2 đấu thủ, ô nhập hệ số Elo thay đổi. |
| 6 | Staff nhập: Player A thắng (1 điểm), Player B thua (0 điểm), Elo A +10, Elo B -10. |
| 7 | Staff nhấn **Update**. |
| 8 | Hệ thống lưu kết quả vào database, cập nhật hệ số Elo. |
| 9 | Hệ thống thông báo "Cap nhat ket qua thanh cong", quay về giao diện chọn vòng + trận. |

---

## Câu 2: Entity Class Diagram

| Entity | Attributes |
|--------|------------|
| Tournament | id, code, name, year, time, location, description |
| Player | id, code, name, yob, nationality, elo, notes |
| Round | id, tournamentId, roundNumber |
| Match | id, roundId, player1Id, player2Id, player1Score, player2Score, player1EloChange, player2EloChange |
| User | id, username, password, role |

### Quan hệ

```
Tournament 1 --- n Round 1 --- n Match
Player 1 --- n Match (as player1)
Player 1 --- n Match (as player2)
```

---

## Câu 3: Thiết kế tĩnh

### View classes

**UpdateResultFrm:**
- `inRound`: combobox chọn vòng
- `outsubListMatch`: bảng trận đấu (click được)
- `inPlayer1Score`, `inPlayer2Score`: ô nhập điểm
- `inPlayer1Elo`, `inPlayer2Elo`: ô nhập Elo
- `subUpdate`: nút Update

### DAO classes

| DAO | Phương thức |
|-----|-------------|
| RoundDAO | `getRounds(tournamentId): List<Round>` |
| MatchDAO | `getMatchesByRound(roundId): List<Match>` |
| MatchDAO | `updateResult(match): boolean` |
| PlayerDAO | `updateElo(playerId, eloChange): boolean` |

### Database

**tblTournament:** ID, code, name, year, time, location, description
**tblPlayer:** ID, code, name, yob, nationality, elo, notes
**tblRound:** ID, tournamentID, roundNumber
**tblMatch:** ID, roundID, player1ID, player2ID, player1Score, player2Score, player1EloChange, player2EloChange

---

## Câu 4: Sequence Diagram

**Visual Paradigm — Các bước vẽ:**

1. Tạo lifeline: `Staff`, `UpdateResultFrm`, `RoundDAO`, `MatchDAO`, `PlayerDAO`.
2. Message flow:
   - Staff → UpdateResultFrm: select "Round 1"
   - UpdateResultFrm → MatchDAO: getMatchesByRound(roundId)
   - Staff → UpdateResultFrm: select match "A vs B"
   - Staff → UpdateResultFrm: enter scores and Elo changes
   - Staff → UpdateResultFrm: click Update
   - UpdateResultFrm → MatchDAO: updateResult(match)
   - UpdateResultFrm → PlayerDAO: updateElo(player1Id, +10)
   - UpdateResultFrm → PlayerDAO: updateElo(player2Id, -10)
   - UpdateResultFrm: show success

---

## Câu 5: Blackbox Testcase

### TC01: Cập nhật kết quả thành công

**Database trước:**

**tblMatch:**
| ID | roundID | player1ID | player2ID | player1Score | player2Score |
|----|---------|-----------|-----------|--------------|--------------|
| 1 | 1 | 1 | 2 | null | null |

**tblPlayer:**
| ID | name | elo |
|----|------|-----|
| 1 | Player A | 2500 |
| 2 | Player B | 2400 |

| Bước | Kịch bản | Kết quả mong đợi |
|------|----------|------------------|
| 1 | Chọn Round 1 | Danh sách trận: A vs B |
| 2 | Chọn trận A vs B, nhập điểm (1, 0), Elo (+10, -10) | |
| 3 | Update | Thông báo "Cap nhat thanh cong" |

### Database sau

**tblMatch:**
| ID | ... | player1Score | player2Score | player1EloChange | player2EloChange |
|----|-----|--------------|--------------|------------------|------------------|
| 1 | ... | **1.0** | **0.0** | **+10** | **-10** |

**tblPlayer:**
| ID | name | elo |
|----|------|-----|
| 1 | Player A | **2510** |
| 2 | Player B | **2390** |
