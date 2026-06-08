# Subject No. 58 — Installment Loan — Module "Payment to partners"

> **Domain:** Installment Loan Management

---

## Cau 1: Scenario — Thanh toan cho doi tac

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop hoat dong voi cac doi tac (Partner) la cac cua hang ban le. Khi khach hang mua hang tra gop, cong ty tru tien tu doi tac va thanh toan cho doi tac sau. Nhan vien (Staff) su dung chuc nang "Payment to partners" de tao phieu thanh toan cho doi tac, ghi nhan cac hop dong ma cong ty chua tra tien cho doi tac.

### Chuong trinh chinh (Main scenario)

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien dang nhap he thong voi tai khoan `staff01` / `matkhau123` |
| 2 | He thong hien thi trang chu, nhan vien click chuc nang **Payment to partners** |
| 3 | He thong hien thi giao diem tim kiem doi tac gom: o nhap ten doi tac (`inPartnerName`), nut **Search** (`subSearch`), vung danh sach ket qua (`outPartnerList`) |
| 4 | Nhan vien nhap ten doi tac `"FPT Shop"` vao o `inPartnerName`, nhan nut **Search** |
| 5 | He thong truy van CSDL, hien thi danh sach doi tac co ten chua tu khoa "FPT Shop": 1 dong — `P001 | FPT Shop | 123 Le Loi, Q.1, HCM | 0901111111` |
| 6 | Nhan vien click chon dong doi tac **FPT Shop** |
| 7 | He thong hien thi danh sach hop dong chua thanh toan cho doi tac FPT Shop trong bang `outContractList`. Moi hop dong tren 1 dong: o checkbox, ma hop dong, ten khach hang, tong san pham, tong tien khach hang, so tien can tra cho doi tac. Du lieu cu the: |
| | — HD001 | Nguyen Van A | 2 SP | 30,000,000d | can tra 28,000,000d |
| | — HD003 | Le Thi C | 1 SP | 15,000,000d | can tra 14,000,000d |
| 8 | Nhan vien tick checkbox chon hop dong **HD001** va **HD003**, nhan nut **Next** (`subNext`) |
| 9 | He thong tinh toan va hien thi hoa don thanh toan doi tac (`outPaymentInvoice`): doi tac FPT Shop, danh sach hop dong da chon, tong thanh toan **42,000,000d** |
| 10 | Nhan vien xac nhan thong tin chinh xac, nhan nut **Save** (`subSave`) |
| 11 | He thong luu phieu thanh toan doi tac vao bang `tblPartnerPayment` va chi tiet vao bang `tblPartnerPaymentDetail`, cap nhat trang thai cac hop dong thanh "Paid to Partner" |
| 12 | He thong in hoa don cho doi tac ky, hien thi thong bao "Thanh toan thanh cong cho doi tac FPT Shop, tong: 42,000,000d" |

### Ngoai le

| Ngoai le | Xu ly |
|----------|-------|
| E1: Khong tim thay doi tac | He thong hien thi thong bao `"Khong tim thay doi tac phu hop"`, nhan vien nhap lai tu khoa |
| E2: Doi tac khong co hop dong nao chua thanh toan | He thong hien thi `"Doi tac nay khong co hop dong nao can thanh toan"` |
| E3: Nhan vien khong tick hop dong nao ma nhan Next | He thong hien thi `"Vui long chon it nhat 1 hop dong de thanh toan"` |
| E4: Luu that bai do loi ket noi CSDL | He thong hien thi loi `"Khong the luu phieu thanh toan, vui long thu lai"`, khong thay doi du lieu |

---

## Cau 2: Entity Class Diagram

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop ghi nhan khach hang (Customer) mua san pham (Product) tu doi tac (Partner) theo hop dong (Contract). Moi hop dong co nhieu chi tiet san pham (ContractItem). Khach hang tra tien theo ky han (PaymentSchedule), moi ky co ban ghi thanh toan (Payment). Cong ty thanh toan cho doi tac qua phieu thanh toan doi tac (PartnerPayment). Nguoi dung he thong (User) la nhan vien thuc hien cac giao dich.

### Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Customer | Entity | Doi tuong trung tam, co thuoc tinh rieng |
| Partner | Entity | Doi tuong doi tac cua cong ty |
| Product | Entity | San pham duoc ban tra gop |
| Contract | Entity | Hop dong giua khach hang va cong ty |
| ContractItem | Entity | Chi tiet san pham trong hop dong |
| PaymentSchedule | Entity | Lich thanh toan theo ky han |
| Payment | Entity | Ban ghi thanh toan tung ky |
| PartnerPayment | Entity | Phieu thanh toan cho doi tac |
| User | Entity | Nguoi dung he thong |
| customerId, partnerId, productId | Attribute | Thuoc tinh dinh danh |
| customerName, phoneNumber, address | Attribute | Thuoc tinh cua Customer |
| signingDate, totalLoanAmount, status | Attribute | Thuoc tinh cua Contract |
| quantity, unitPrice, amount | Attribute | Thuoc tinh cua ContractItem |
| periodNumber, dueDate | Attribute | Thuoc tinh cua PaymentSchedule |
| paymentDate, amountPaid | Attribute | Thuoc tinh cua Payment |
| he thong, giao diem, nut bam | Rejected | Khong phai doi tuong nghiep vu |

### Quan he so luong

```
Customer 1 --- n Contract        (1 KH co nhieu hop dong)
Partner 1 --- n Contract         (1 doi tac co nhieu hop dong)
Contract 1 --- n ContractItem    (1 hop dong co nhieu chi tiet SP)
Product 1 --- n ContractItem     (1 san pham xuat hien nhieu chi tiet)
Contract 1 --- n PaymentSchedule (1 hop dong co nhieu ky thanh toan)
PaymentSchedule 1 --- n Payment  (1 ky co nhieu ban ghi thanh toan)
Partner 1 --- n PartnerPayment   (1 doi tac co nhieu phieu thanh toan)
User 1 --- n PartnerPayment      (1 nhan vien tao nhieu phieu thanh toan)
```

### Quan he doi tuong

```
Customer 1 --- n Contract        (association)
Partner 1 --- n Contract         (association)
Contract 1 --- * ContractItem    (composition — ContractItem ton tai khi Contract ton tai)
Product 1 --- n ContractItem     (association)
Contract 1 --- * PaymentSchedule (composition)
PaymentSchedule 1 --- * Payment  (composition)
Partner 1 --- n PartnerPayment   (association)
User 1 --- n PartnerPayment      (association)
```

### Bang quan he (Database Schema)

| Bang | Cot PK | Cot FK | Cac cot khac |
|------|--------|--------|--------------|
| tblCustomer | customerId | — | customerName, phoneNumber, address, idCard |
| tblPartner | partnerId | — | partnerName, address, phoneNumber, branch |
| tblProduct | productId | — | productName, unitPrice, description |
| tblContract | contractId | customerId → tblCustomer, partnerId → tblPartner | signingDate, totalLoanAmount, status |
| tblContractItem | contractItemId | contractId → tblContract, productId → tblProduct | quantity, unitPrice, amount |
| tblPaymentSchedule | scheduleId | contractId → tblContract | periodNumber, dueDate, amount, status |
| tblPayment | paymentId | scheduleId → tblPaymentSchedule | paymentDate, amountPaid, method |
| tblPartnerPayment | partnerPaymentId | partnerId → tblPartner, userId → tblUser | paymentDate, totalAmount, status |
| tblPartnerPaymentDetail | detailId | partnerPaymentId → tblPartnerPayment, contractId → tblContract | amount |
| tblUser | userId | — | username, password, fullName, role |

---

## Cau 3: Thiet ke tinh

### View classes

**PayPartnerFrm** — Giao diem thanh toan cho doi tac:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| inPartnerName | `txtPartnerName` | TextBox | O nhap ten doi tac can tim kiem |
| subSearch | `btnSearch` | Button | Nut tim kiem doi tac theo ten |
| outPartnerList | `dgvPartnerList` | DataGridView | Bang danh sach doi tac ket qua tim kiem: ma, ten, dia chi, so dien thoai |
| outContractList | `dgvContractList` | DataGridView | Bang hop dong chua thanh toan: checkbox, ma HĐ, ten KH, tong SP, tong tien, tien can tra |
| subNext | `btnNext` | Button | Nut chuyen sang xem hoa don thanh toan |
| outPaymentInvoice | `pnlInvoice` | Panel | Hoa don thanh toan doi tac: thong tin doi tac, danh sach HĐ, tong tien |
| subSave | `btnSave` | Button | Nut xac nhan va luu phieu thanh toan |

**HomeFrm** — Giao diem trang chu:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| subPayPartner | `btnPayPartner` | Button | Nut mo giao diem thanh toan cho doi tac |

### Entity types

| Entity | Attributes | Kieu du lieu |
|--------|------------|--------------|
| Customer | customerId | int (PK) |
| | customerName | String |
| | phoneNumber | String |
| | address | String |
| | idCard | String |
| Partner | partnerId | int (PK) |
| | partnerName | String |
| | address | String |
| | phoneNumber | String |
| | branch | String |
| Contract | contractId | int (PK) |
| | customerId | int (FK) |
| | partnerId | int (FK) |
| | signingDate | Date |
| | totalLoanAmount | double |
| | status | String |
| PartnerPayment | partnerPaymentId | int (PK) |
| | partnerId | int (FK) |
| | userId | int (FK) |
| | paymentDate | Date |
| | totalAmount | double |
| | status | String |

### DAO classes

| DAO | Phuong thuc | Mo ta |
|-----|-------------|-------|
| PartnerDAO | `searchByName(String keyword): List<Partner>` | Tim kiem doi tac theo ten |
| PartnerDAO | `getById(int partnerId): Partner` | Lay thong tin doi tac theo ma |
| ContractDAO | `getUnpaidContractsByPartner(int partnerId): List<Contract>` | Lay danh sach hop dong chua thanh toan cho doi tac |
| PartnerPaymentDAO | `create(PartnerPayment payment): int` | Tao phieu thanh toan doi tac, tra ve ID |
| PartnerPaymentDetailDAO | `batchInsert(int partnerPaymentId, List<PartnerPaymentDetail> details): boolean` | Them chi tiet thanh toan theo hop dong |

### Huong dan Visual Paradigm

1. Tao package `view.payment` chua `PayPartnerFrm`, `HomeFrm`
2. Tao package `dao` chua `PartnerDAO`, `ContractDAO`, `PartnerPaymentDAO`, `PartnerPaymentDetailDAO`
3. Tao package `model` chua cac entity classes
4. Ve dependency tu `PayPartnerFrm` den cac DAO classes
5. Ve dependency tu DAO classes den cac Entity classes

---

## Cau 4: Sequence Diagram

### Huong dan Visual Paradigm

1. Tao Sequence Diagram trong VP
2. Them lifelines: `:Staff` (actor), `:PayPartnerFrm` (boundary), `:PartnerDAO` (control), `:ContractDAO` (control), `:PartnerPaymentDAO` (control), `:PartnerPaymentDetailDAO` (control), `:Database` (entity)
3. Ve message flow theo bang duoi, dung `synchronous message` (mui ten dac) cho request va `return message` (mui ten dut) cho response
4. Them `alt` fragment cho truong hop khong tim thay doi tac (E1) va khong co hop dong (E2)
5. Them `loop` fragment cho viec luu chi tiet thanh toan nhieu dong

### Bang buoc sequence

| Buoc | Nguoi gui | Nguoi nhan | Message | Ghi chu |
|------|-----------|------------|---------|---------|
| 1 | Staff | PayPartnerFrm | `selectMenu("Payment to partners")` | Nhan vien chon chuc nang |
| 2 | PayPartnerFrm | Staff | `showSearchForm()` | Hien thi giao diem tim kiem |
| 3 | Staff | PayPartnerFrm | `enter(inPartnerName="FPT Shop")` | Nhap ten doi tac |
| 4 | Staff | PayPartnerFrm | `click(subSearch)` | Nhan nut tim kiem |
| 5 | PayPartnerFrm | PartnerDAO | `searchByName("FPT Shop")` | Goi DAO tim kiem |
| 6 | PartnerDAO | Database | `SELECT * FROM tblPartner WHERE partnerName LIKE '%FPT Shop%'` | Truy van CSDL |
| 7 | Database | PartnerDAO | `ResultSet` | Tra ket qua |
| 8 | PartnerDAO | PayPartnerFrm | `List<Partner>` | Tra ve danh sach doi tac |
| 9 | PayPartnerFrm | Staff | `show(outPartnerList)` | Hien thi ket qua tim kiem |
| 10 | Staff | PayPartnerFrm | `click("FPT Shop")` | Chon doi tac |
| 11 | PayPartnerFrm | ContractDAO | `getUnpaidContractsByPartner(partnerId)` | Lay hop dong chua thanh toan |
| 12 | ContractDAO | Database | `SELECT c.*, ci.totalAmount FROM tblContract c ... WHERE c.partnerId = ? AND c.status != 'Paid to Partner'` | Truy van hop dong |
| 13 | Database | ContractDAO | `ResultSet` | Tra ket qua |
| 14 | ContractDAO | PayPartnerFrm | `List<Contract>` | Tra ve danh sach hop dong |
| 15 | PayPartnerFrm | Staff | `show(outContractList)` | Hien thi danh sach hop dong |
| 16 | Staff | PayPartnerFrm | `tick(HD001, HD003), click(subNext)` | Chon hop dong va bam Next |
| 17 | PayPartnerFrm | PayPartnerFrm | `calculateInvoice(selectedContracts)` | Tinh toan tong tien |
| 18 | PayPartnerFrm | Staff | `show(outPaymentInvoice)` | Hien thi hoa don: 42,000,000d |
| 19 | Staff | PayPartnerFrm | `click(subSave)` | Xac nhan luu |
| 20 | PayPartnerFrm | PartnerPaymentDAO | `create(payment)` | Tao phieu thanh toan |
| 21 | PartnerPaymentDAO | Database | `INSERT INTO tblPartnerPayment(partnerId, userId, paymentDate, totalAmount, status) VALUES (?, ?, ?, 42000000, 'Completed')` | Luu phieu thanh toan |
| 22 | Database | PartnerPaymentDAO | `partnerPaymentId = PP001` | Tra ve ID vua tao |
| 23 | PartnerPaymentDAO | PayPartnerFrm | `partnerPaymentId` | Tra ve ID phieu |
| 24 | PayPartnerFrm | PartnerPaymentDetailDAO | `batchInsert(PP001, details)` | Luu chi tiet thanh toan |
| 25 | PartnerPaymentDetailDAO | Database | `INSERT INTO tblPartnerPaymentDetail VALUES (D001, PP001, HD001, 28000000), (D002, PP001, HD003, 14000000)` | Luu 2 dong chi tiet |
| 26 | Database | PartnerPaymentDetailDAO | `true` | Xac nhan thanh cong |
| 27 | PartnerPaymentDetailDAO | PayPartnerFrm | `true` | Tra ve ket qua |
| 28 | PayPartnerFrm | Staff | `printInvoice(), showSuccessMessage("Thanh toan thanh cong")` | In hoa don va thong bao |

---

## Cau 5: Blackbox Testcase

### Test Plan

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Payment to partners | Thanh toan thanh cong cho doi tac FPT Shop voi 2 hop dong |
| TC02 | Payment to partners | Tim kiem doi tac khong ton tai |
| TC03 | Payment to partners | Doi tac khong co hop dong chua thanh toan |
| TC04 | Payment to partners | Khong chon hop dong nao ma nhan Next |

### TC01: Thanh toan thanh cong cho doi tac FPT Shop voi 2 hop dong

**Muc dich:** Kiem tra luong chinh — tim doi tac, chon hop dong, xac nhan thanh toan thanh cong.

**Database truoc:**

tblUser:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | matkhau123 | Nguyen Van Staff | Staff |

tblPartner:
| partnerId | partnerName | address | phoneNumber | branch |
|-----------|-------------|---------|-------------|--------|
| P001 | FPT Shop | 123 Le Loi, Q.1, HCM | 0901111111 | Chi nhanh 1 |

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi | 012345678901 |
| C003 | Le Thi C | 0923456789 | Da Nang | 034567890123 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-01-15 | 30000000 | Active |
| HD002 | C001 | P001 | 2026-02-20 | 20000000 | Paid to Partner |
| HD003 | C003 | P001 | 2026-03-10 | 15000000 | Active |

tblPartnerPayment: (rong)
tblPartnerPaymentDetail: (rong)

**Kich ban va ket qua mong doi:**

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Nhan vien dang nhap voi `staff01` / `matkhau123` | Dang nhap thanh cong, hien thi trang chu |
| 2 | Nhan vien click nut **Payment to partners** | Hien thi giao diem tim kiem doi tac voi o nhap ten va nut Search |
| 3 | Nhan vien nhap `"FPT Shop"` vao o ten doi tac, nhan **Search** | Hien thi danh sach doi tac: 1 dong — P001, FPT Shop, 123 Le Loi, Q.1, HCM, 0901111111 |
| 4 | Nhan vien click chon dong **FPT Shop** | Hien thi bang hop dong chua thanh toan: HD001 (Nguyen Van A, 2 SP, 30,000,000d, can tra 28,000,000d), HD003 (Le Thi C, 1 SP, 15,000,000d, can tra 14,000,000d). HD002 khong hien vi da thanh toan |
| 5 | Nhan vien tick checkbox **HD001** va **HD003**, nhan nut **Next** | Hien thi hoa don thanh toan doi tac: FPT Shop, tong thanh toan 42,000,000d |
| 6 | Nhan vien xac nhan, nhan nut **Save** | He thong luu phieu thanh toan, in hoa don, hien thi thong bao "Thanh toan thanh cong cho doi tac FPT Shop, tong: 42,000,000d" |
| 7 | Nhan vien kiem tra giao diem | Hoa don da in xong, doi tac co the ky xac nhan |

**Database sau:**

tblPartner: Khong thay doi

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-01-15 | 30000000 | **Paid to Partner** |
| HD002 | C001 | P001 | 2026-02-20 | 20000000 | Paid to Partner |
| HD003 | C003 | P001 | 2026-03-10 | 15000000 | **Paid to Partner** |

tblPartnerPayment:
| partnerPaymentId | partnerId | userId | paymentDate | totalAmount | status |
|-----------------|-----------|--------|-------------|-------------|--------|
| PP001 | P001 | U001 | 2026-06-08 | 42000000 | Completed |

tblPartnerPaymentDetail:
| detailId | partnerPaymentId | contractId | amount |
|----------|-----------------|-----------|--------|
| D001 | PP001 | HD001 | 28000000 |
| D002 | PP001 | HD003 | 14000000 |
