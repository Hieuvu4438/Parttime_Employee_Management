# Prompt Template — Phân tích và sửa lỗi bài thi CNPM

> Copy toàn bộ prompt bên dưới và thay `[SUBJECT_FILE]`, `[ANSWER_FILE]` bằng đường dẫn thực tế.

---

## Prompt

```
Giúp tôi phân tích và sửa lỗi bài thi CNPM. Yêu cầu cụ thể:

**Bước 1: Nạp kiến thức (BẮT BUỘC đọc trước khi phân tích)**

Đọc tất cả các file sau theo đúng thứ tự:

1. `.claude/context/exam/KNOWLEDGE-BASE.md` — knowledge base chung
2. `.claude/context/teacher-reference/03_analysis-phase.md` — quy trình viết scenario, entity extraction, class diagram, sequence diagram
3. `.claude/context/teacher-reference/05_testing-phase.md` — quy trình viết blackbox testcase
4. `.claude/context/exam/answers/subject-01-library-borrowing.md` — bài mẫu tham khảo chất lượng

**Bước 2: Đọc đề bài và bài làm**

1. Đọc file đề thi: `[SUBJECT_FILE]`
2. Đọc file bài làm: `[ANSWER_FILE]`

**Bước 3: Phân tích theo checklist**

Kiểm tra từng câu theo các tiêu chí sau:

### Câu 1 — Scenario (1.5 điểm)
- [ ] Có flow chính đầy đủ (từng bước cụ thể, có dữ liệu mẫu)
- [ ] Có ít nhất 2 kịch bản ngoại lệ
- [ ] Bước cuối không mâu thuẫn logic
- [ ] Có tên form, tên trường input/output cụ thể
- [ ] Dữ liệu mẫu nhất quán giữa các bước

### Câu 2 — Entity Class Diagram (1.5 điểm)
- [ ] Bước 1: Mô tả hệ thống đầy đủ bằng ngôn ngữ tự nhiên
- [ ] Bước 2: Trích xuất TẤT CẢ danh từ từ đề bài (kể cả publication year, quantity, phone, description...)
- [ ] Bước 2: Phân loại đúng entity / attribute / rejected
- [ ] Bước 3: Xác định đúng loại quan hệ (1-1, 1-N, N-N)
- [ ] Bước 3: Composition chỉ dùng khi child KHÔNG tồn tại nếu parent bị xóa
- [ ] Bước 4: Class diagram có đủ thuộc tính từ đề bài
- [ ] Bước 5: Bảng quan hệ chi tiết, multiplicity đúng
- [ ] Hướng dẫn Visual Paradigm chi tiết (stereotype, attributes, methods, relationships)

### Câu 3 — Static Design (1.5 điểm)
- [ ] View class đầy đủ (LoginFrm, HomeFrm, module form, slip forms)
- [ ] UI elements có prefix đúng (in/out/sub/outsub)
- [ ] DAO class có phương thức phù hợp
- [ ] Entity class design có TẤT CẢ thuộc tính (kể cả code, phone, pubYear, quantity, description)
- [ ] Database design có đầy đủ cột, đúng kiểu dữ liệu, PK/FK
- [ ] Nullable/default đúng (returnDate nullable, fine default 0)

### Câu 4 — Sequence Diagram (1.5 điểm)
- [ ] Có đầy đủ lifelines (actor, boundary, control, entity)
- [ ] Flow đúng thứ tự: scan → validate → update UI → Submit → update DB
- [ ] **DB chỉ cập nhật SAU khi Submit, KHÔNG cập nhật khi đang scan**
- [ ] Có bước validate (kiểm tra sách thuộc phiếu mượn)
- [ ] Có bước tính fine (checkLate / calculateFine)
- [ ] Return messages đầy đủ
- [ ] Bảng giải thích từng bước chi tiết

### Câu 5 — Blackbox Testcase (1.5 điểm)
- [ ] Test plan có 5+ test cases (standard + exceptional)
- [ ] TC chi tiết là STANDARD case (không phải exceptional)
- [ ] Database before có dữ liệu cụ thể cho TẤT CẢ bảng liên quan
- [ ] Dữ liệu test NHẤT QUÁN (borrowDate, loanDate, dueDate logic)
- [ ] Scenario có bước kiểm tra DB TRƯỚC Submit (verify chưa cập nhật)
- [ ] Scenario có bước kiểm tra DB SAU Submit (verify đã cập nhật)
- [ ] Database after chỉ liệt kê bảng thay đổi
- [ ] Fine calculation đúng: coverPrice × 20% (làm tròn nếu cần)

**Bước 4: Xuất kết quả**

1. Liệt kê TẤT CẢ lỗi tìm thấy, phân loại mức độ: Lớn / Trung bình / Nhỏ
2. Với mỗi lỗi: mô tả vấn đề, giải thích tại sao sai, nêu cách sửa
3. Sau đó hỏi tôi có muốn sửa file trực tiếp không

**Lưu ý quan trọng:**
- Không copy nội dung từ teacher-reference (hotel domain) vào bài làm
- Đảm bảo thuật ngữ phù hợp với domain của đề thi
- Giữ nguyên format markdown của file answer
```

---

## Cách sử dụng

1. Mở Claude Code
2. Copy prompt ở trên
3. Thay `[SUBJECT_FILE]` bằng đường dẫn file đề thi, ví dụ: `.claude/context/exam/subjects/subject-02.md`
4. Thay `[ANSWER_FILE]` bằng đường dẫn file bài làm, ví dụ: `.claude/context/exam/answers/subject-02-library-returning.md`
5. Paste vào Claude Code và chạy

## Ví dụ prompt hoàn chỉnh

```
Giúp tôi phân tích và sửa lỗi bài thi CNPM. Yêu cầu cụ thể:

**Bước 1: Nạp kiến thức (BẮT BUỘC đọc trước khi phân tích)**

Đọc tất cả các file sau theo đúng thứ tự:

1. `.claude/context/exam/KNOWLEDGE-BASE.md` — knowledge base chung
2. `.claude/context/teacher-reference/03_analysis-phase.md` — quy trình viết scenario, entity extraction, class diagram, sequence diagram
3. `.claude/context/teacher-reference/05_testing-phase.md` — quy trình viết blackbox testcase
4. `.claude/context/exam/answers/subject-01-library-borrowing.md` — bài mẫu tham khảo chất lượng

**Bước 2: Đọc đề bài và bài làm**

1. Đọc file đề thi: `.claude/context/exam/subjects/subject-02.md`
2. Đọc file bài làm: `.claude/context/exam/answers/subject-02-library-returning.md`

**Bước 3: Phân tích theo checklist**

[... giữ nguyên checklist từ trên ...]

**Bước 4: Xuất kết quả**

[... giữ nguyên yêu cầu xuất từ trên ...]
```
