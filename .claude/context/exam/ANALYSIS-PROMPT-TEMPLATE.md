# Prompt Template — Phân tích và sửa lỗi bài thi CNPM

> Copy toàn bộ prompt bên dưới và thay `[SUBJECT_FILE]`, `[ANSWER_FILE]` bằng đường dẫn thực tế.

---

## Prompt

```
Giúp tôi phân tích và sửa lỗi bài thi CNPM. Yêu cầu cụ thể:

**Bước 1: Nạp kiến thức (BẮT BUỘC đọc trước khi phân tích)**

Đọc TẤT CẢ các file trong folder `.claude/context/teacher-reference/` theo thứ tự:

1. `01_business-model-natural-language.md` — cách mô tả hệ thống bằng ngôn ngữ tự nhiên
2. `02_use-case-uml.md` — cách trích xuất use case, actor
3. `03_analysis-phase.md` — quy trình viết scenario, entity extraction, analysis class diagram, analysis sequence diagram
4. `04_design-phase.md` — quy trình thiết kế entity class, database, design class diagram, design sequence diagram
5. `05_testing-phase.md` — quy trình viết blackbox testcase (test plan + test case chi tiết)
6. `06_coding-java-swing-dao.md` — pattern code Java Swing/DAO (để hiểu cách đặt tên method, class)

Sau đó đọc thêm:
7. `.claude/context/exam/KNOWLEDGE-BASE.md` — tổng hợp kiến thức domain + common patterns

**LƯU QUAN TRỌNG:** Các file teacher-reference là phương pháp mẫu từ hệ thống hotel. Chỉ tham khảo CÁCH LÀM, KHÔNG copy nội dung hotel (entity, actor, table, test data) vào bài làm.

**Bước 2: Đọc đề bài và bài làm**

1. Đọc file đề thi: `[SUBJECT_FILE]`
2. Đọc file bài làm: `[ANSWER_FILE]`

**Bước 3: Phân tích theo checklist**

Đối chiếu bài làm với phương pháp teacher-reference và kiểm tra từng câu:

### Câu 1 — Scenario (1.5 điểm)
(tham khảo `03_analysis-phase.md` — mục Scenario Writing)

- [ ] Flow chính viết theo format bước cụ thể, có actor, UI, dữ liệu mẫu
- [ ] Có ít nhất 2 kịch bản ngoại lệ (exceptional scenarios)
- [ ] Không có bước mâu thuẫn logic (ví dụ: mô tả in phiếu rồi nói không in)
- [ ] Có tên form cụ thể (LoginFrm, HomeFrm, XxxFrm)
- [ ] Dữ liệu mẫu nhất quán giữa các bước (barcode, tên, mã không thay đổi)
- [ ] Bước cuối quay về Home hoặc kết thúc hợp lý

### Câu 2 — Entity Class Diagram (1.5 điểm)
(tham khảo `03_analysis-phase.md` — mục Entity Extraction + Analysis Class Diagram)

- [ ] Bước 1: Mô tả hệ thống bằng ngôn ngữ tự nhiên, đầy đủ chức năng
- [ ] Bước 2: Trích xuất TẤT CẢ danh từ từ đề bài (kể cả publication year, quantity, phone, description, cover price...)
- [ ] Bước 2: Phân loại đúng entity / attribute / rejected
- [ ] Bước 3: Xác định đúng loại quan hệ (1-1, 1-N, N-N)
- [ ] Bước 3: Composition chỉ dùng khi child KHÔNG tồn tại nếu parent bị xóa
- [ ] Bước 4: Class diagram có đủ thuộc tính từ đề bài
- [ ] Bước 5: Bảng quan hệ chi tiết, multiplicity đúng cả 2 đầu
- [ ] Hướng dẫn Visual Paradigm chi tiết (3 ngăn, stereotype, relationships, multiplicities)
- [ ] Analysis: xác định view classes, UI elements (in/out/sub), methods, assignment

### Câu 3 — Static Design (1.5 điểm)
(tham khảo `04_design-phase.md` — mục Design Entity + Database + Class Diagram)

- [ ] View class đầy đủ (LoginFrm, HomeFrm, module form, slip forms nếu có)
- [ ] UI elements có prefix đúng: in=input, out=output, sub=button, outsub=clickable table
- [ ] DAO class có phương thức phù hợp (CRUD + business logic)
- [ ] Entity class design có TẤT CẢ thuộc tính từ đề bài (kể cả code, phone, pubYear, quantity, description)
- [ ] Entity có object attributes (ví dụ: `reader: Reader`, `loanDetails: List<LoanDetail>`)
- [ ] Database design có đầy đủ cột, đúng kiểu dữ liệu, PK/FK/UNIQUE/NOT NULL
- [ ] Nullable/default đúng (ví dụ: returnDate nullable, fine default 0)
- [ ] Hướng dẫn Visual Paradigm cho design phase (view class + DAO class + entity class + relationships)

### Câu 4 — Sequence Diagram (1.5 điểm)
(tham khảo `04_design-phase.md` — mục Design Sequence Diagram)

- [ ] Có đầy đủ lifelines: actor, boundary (Frm), control (DAO/Control), entity
- [ ] Flow đúng thứ tự: scan → validate → update UI → Submit → update DB
- [ ] **DB chỉ cập nhật SAU khi Submit, KHÔNG cập nhật khi đang scan sách**
- [ ] Có bước validate (kiểm tra sách thuộc phiếu mượn / hợp lệ)
- [ ] Có bước tính fine (checkLate / calculateFine)
- [ ] Return messages (dashed line) đầy đủ
- [ ] Bảng giải thích từng bước: #, message, from, to, mô tả
- [ ] Hướng dẫn Visual Paradigm cho sequence diagram

### Câu 5 — Blackbox Testcase (1.5 điểm)
(tham khảo `05_testing-phase.md` — mục Black-Box Test Case + Detailed Test Case)

- [ ] Test plan có 5+ test cases (standard + exceptional)
- [ ] TC chi tiết là STANDARD case (không phải exceptional)
- [ ] Purpose: 1 câu mô tả mục đích
- [ ] Database before có dữ liệu cụ thể cho TẤT CẢ bảng liên quan (không được "rỗng")
- [ ] Dữ liệu test NHẤT QUÁN (borrowDate, loanDate, dueDate phải logic với nhau)
- [ ] Scenario có bước kiểm tra DB TRƯỚC Submit (verify chưa cập nhật)
- [ ] Scenario có bước kiểm tra DB SAU Submit (verify đã cập nhật)
- [ ] Database after chỉ liệt kê bảng THAY ĐỔI
- [ ] Fine calculation đúng theo business rule đề bài
- [ ] Không copy test data từ hotel domain

**Bước 4: Xuất kết quả**

1. Liệt kê TẤT CẢ lỗi tìm thấy, phân loại mức độ: **Lớn** / Trung bình / Nhỏ
2. Với mỗi lỗi:
   - Mô tả vấn đề
   - Giải thích tại sao sai (dẫn chiếu teacher-reference nếu phù hợp)
   - Nêu cách sửa cụ thể
3. Sau đó hỏi tôi có muốn sửa file trực tiếp không

**Lưu ý cuối:**
- Không copy nội dung domain hotel (entity, actor, table, data) vào bài làm
- Đảm bảo thuật ngữ phù hợp với domain của đề thi
- Giữ nguyên format markdown của file answer
```

---

## Cách sử dụng

1. Mở Claude Code
2. Copy toàn bộ prompt trong khối code ở trên
3. Thay `[SUBJECT_FILE]` bằng đường dẫn file đề thi
4. Thay `[ANSWER_FILE]` bằng đường dẫn file bài làm
5. Paste vào Claude Code và chạy

## Ví dụ

```
Subject file: .claude/context/exam/subjects/subject-02.md
Answer file:  .claude/context/exam/answers/subject-02-library-returning.md
```

## Danh sách file teacher-reference

| File | Nội dung | Dùng cho câu nào |
|------|----------|-------------------|
| `01_business-model-natural-language.md` | Mô tả hệ thống, business process | Câu 1, 2 |
| `02_use-case-uml.md` | Use case diagram, actor extraction | Câu 2 |
| `03_analysis-phase.md` | Scenario, entity extraction, analysis class diagram, analysis sequence | Câu 1, 2, 4 |
| `04_design-phase.md` | Design entity, database, design class diagram, design sequence | Câu 3, 4 |
| `05_testing-phase.md` | Blackbox test plan, detailed test case | Câu 5 |
| `06_coding-java-swing-dao.md` | Java Swing/DAO patterns | Câu 3 (DAO methods) |
