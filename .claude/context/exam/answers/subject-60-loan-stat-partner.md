# Subject No. 60 — Installment Loan — Module "Statistics of partners"

> **Domain:** Installment Loan Management

---

## Cau 1: Scenario — Thong ke doi tac theo doanh so

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop cho phep nhan vien xem thong ke doanh so cua cac doi tac (Partner) trong mot ky thoi gian. Nhan vien nhap ngay bat dau va ngay ket thuc, he thong hien thi bang tong hop doi tác sap xep theo tong doanh thu giam dan. Nhan vien co the drill-down vao doi tac de xem danh sach hop dong, sau do xem chi tiet tung hop dong.

### Chuong trinh chinh (Main scenario)

| Buoc | Dien bien |
|------|-----------|
| 1 | Nhan vien dang nhap he thong voi tai khoan `staff01` / `matkhau123` |
| 2 | He thong hien thi trang chu, nhan vien click chuc nang **Statistics of partners** |
| 3 | He thong hien thi giao diem thong ke voi o nhap ngay bat dau (`inStartDate`), ngay ket thuc (`inEndDate`), va nut **Xem thong ke** (`subView`) |
| 4 | Nhan vien nhap ngay bat dau `"01/01/2026"`, ngay ket thuc `"31/12/2026"`, nhan nut **Xem thong ke** |
| 5 | He thong hien thi bang thong ke doi tac (`outPartnerStatTable`). Moi dong gom: ma doi tac, ten, dia chi, tong hoa don, tong tien ban hang, tong du no. Bang sap xep theo tong doanh thu giam dan |
| 6 | He thong hien thi du lieu: |
| | — P001 | FPT Shop | 123 Le Loi, HCM | 2 hoa don | 45,000,000d | du no 22,500,000d |
| | — P002 | TGDD | 456 Tran Hung Dao, HN | 1 hoa don | 20,000,000d | du no 20,000,000d |
| 7 | Nhan vien click vao dong doi tac **FPT Shop** |
| 8 | He thong hien thi danh sach hop dong cua FPT Shop trong ky (`outContractList`). Moi dong gom: ma hop dong, ten khach hang, ngay ky, tong vay, tong da thanh toan, tong du no, tong qua han |
| 9 | He thong hien thi du lieu: |
| | — HD001 | Nguyen Van A | 15/03/2026 | vay 30,000,000d | da tra 15,000,000d | du no 15,000,000d | qua han 0d |
| | — HD003 | Le Thi C | 10/05/2026 | vay 15,000,000d | da tra 3,750,000d | du no 11,250,000d | qua han 0d |
| 10 | Nhan vien click vao dong hop dong **HD001** |
| 11 | He thong hien thi chi tiet hop dong HD001 (`outContractDetails`): thong tin khach hang (ten, SĐT), danh sach san pham (ten SP, so luong, don gia, thanh tien), danh sach thanh toan theo ky (ky, ngay den han, so tien, trang thai) |
| 12 | He thong hien thi chi tiet: khach hang Nguyen Van A, SĐT 0901234567; san pham: iPhone 15 (1, 25,000,000d, 25,000,000d), AirPods Pro (1, 5,000,000d, 5,000,000d); thanh toan: ky 1 da thanh toan, ky 2 da thanh toan, ky 3 chua thanh toan, ky 4 chua thanh toan |
| 13 | Nhan vien xem xong, click nut **Back** de tro ve danh sach hop dong, tiep tuc xem doi tac khac neu can |

### Ngoai le

| Ngoai le | Xu ly |
|----------|-------|
| E1: Khong nhap ngay bat dau hoac ngay ket thuc | He thong hien thi `"Vui long nhap day du ngay bat dau va ngay ket thuc"` |
| E2: Ngay bat dau lon hon ngay ket thuc | He thong hien thi `"Ngay bat dau phai nho hon ngay ket thuc"` |
| E3: Khong co doi tac nao co doanh so trong ky | He thong hien thi bang rong voi thong bao `"Khong co du lieu thong ke trong ky nay"` |
| E4: Doi tac khong co hop dong nao trong ky | He thong hien thi danh sach hop dong rong, thong bao `"Doi tac nay khong co hop dong nao trong ky"` |

---

## Cau 2: Entity Class Diagram

### Mo ta he thong bang ngon ngu tu nhien

He thong quan ly tra gop ghi nhan khach hang (Customer) mua san pham (Product) tu doi tac (Partner) theo hop dong (Contract). Moi hop dong co nhieu chi tiet san pham (ContractItem). Khach hang tra tien theo ky han (PaymentSchedule), moi ky co ban ghi thanh toan (Payment). Nhan vien (User) su dung he thong de xem thong ke doanh so doi tac.

### Trich xuat danh tu

| Danh tu | Phan loai | Ly do |
|---------|-----------|-------|
| Customer | Entity | Doi tuong khach hang, co thuoc tinh rieng |
| Partner | Entity | Doi tuong doi tac cua cong ty |
| Contract | Entity | Hop dong mua ban tra gop |
| ContractItem | Entity | Chi tiet san pham trong hop dong |
| Product | Entity | San pham duoc ban tra gop |
| PaymentSchedule | Entity | Lich thanh toan theo ky han |
| Payment | Entity | Ban ghi thanh toan tung ky |
| User | Entity | Nguoi dung he thong |
| partnerId, partnerName, address, phoneNumber, branch | Attribute | Thuoc tinh cua Partner |
| contractId, signingDate, totalLoanAmount, status | Attribute | Thuoc tinh cua Contract |
| periodNumber, dueDate, amount | Attribute | Thuoc tinh cua PaymentSchedule |
| paymentDate, amountPaid | Attribute | Thuoc tinh cua Payment |
| tong hoa don, tong ban hang, tong du no | Attribute | Thuoc tinh tinh toan (computed) |
| he thong, giao diem, bang thong ke, ky thoi gian | Rejected | Khong phai doi tuong nghiep vu |

### Quan he so luong

```
Customer 1 --- n Contract        (1 KH co nhieu hop dong)
Partner 1 --- n Contract         (1 doi tac co nhieu hop dong)
Contract 1 --- n ContractItem    (1 hop dong co nhieu chi tiet SP)
Product 1 --- n ContractItem     (1 san pham xuat hien nhieu chi tiet)
Contract 1 --- n PaymentSchedule (1 hop dong co nhieu ky thanh toan)
PaymentSchedule 1 --- n Payment  (1 ky co nhieu ban ghi thanh toan)
User 1 --- n Payment             (1 nhan vien xu ly nhieu thanh toan)
```

### Quan he doi tuong

```
Customer 1 --- n Contract        (association)
Partner 1 --- n Contract         (association)
Contract 1 --- * ContractItem    (composition — ContractItem ton tai khi Contract ton tai)
Product 1 --- n ContractItem     (association)
Contract 1 --- * PaymentSchedule (composition)
PaymentSchedule 1 --- * Payment  (composition)
User 1 --- n Payment             (association)
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
| tblUser | userId | — | username, password, fullName, role |

---

## Cau 3: Thiet ke tinh

### View classes

**StatPartnerFrm** — Giao diem thong ke doi tac theo doanh so:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| inStartDate | `txtStartDate` | TextBox (DatePicker) | O nhap ngay bat dau ky thong ke |
| inEndDate | `txtEndDate` | TextBox (DatePicker) | O nhap ngay ket thuc ky thong ke |
| subView | `btnView` | Button | Nut "Xem thong ke" |
| outPartnerStatTable | `dgvPartnerStat` | DataGridView | Bang thong ke doi tac: ma, ten, dia chi, tong hoa don, tong tien ban hang, tong du no. Sap xep theo doanh thu giam dan |
| outContractList | `dgvContractList` | DataGridView | Danh sach hop dong cua 1 doi tac: ma HĐ, ten KH, ngay ky, tong vay, tong da thanh toan, tong du no, tong qua han |
| outContractDetails | `pnlContractDetails` | Panel | Chi tiet hop dong: thong tin KH, san pham, thanh toan theo ky |
| subBack | `btnBack` | Button | Nut tro ve bang thong ke doi tac |

**HomeFrm** — Giao diem trang chu:

| Phan tu | Ten | Loai | Mo ta |
|---------|-----|------|-------|
| subStatPartner | `btnStatPartner` | Button | Nut mo giao diem thong ke doi tac |

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
| ContractItem | contractItemId | int (PK) |
| | contractId | int (FK) |
| | productId | int (FK) |
| | quantity | int |
| | unitPrice | double |
| | amount | double |
| PaymentSchedule | scheduleId | int (PK) |
| | contractId | int (FK) |
| | periodNumber | int |
| | dueDate | Date |
| | amount | double |
| | status | String |
| Payment | paymentId | int (PK) |
| | scheduleId | int (FK) |
| | paymentDate | Date |
| | amountPaid | double |
| | method | String |

### DAO classes

| DAO | Phuong thuc | Mo ta |
|-----|-------------|-------|
| PartnerDAO | `getPartnerStatistics(Date startDate, Date endDate): List<Partner>` | Lay danh sach thong ke doi tac kem tong hoa don, tong ban hang, tong du no trong ky, sap xep theo doanh thu giam dan |
| ContractDAO | `getContractsByPartner(int partnerId, Date startDate, Date endDate): List<Contract>` | Lay danh sach hop dong cua 1 doi tac trong ky thong ke |
| ContractItemDAO | `getItemsByContract(int contractId): List<ContractItem>` | Lay danh sach san pham trong 1 hop dong |
| PaymentScheduleDAO | `getScheduleByContract(int contractId): List<PaymentSchedule>` | Lay lich trinh thanh toan theo ky cua 1 hop dong |
| CustomerDAO | `getById(int customerId): Customer` | Lay thong tin khach hang theo ma |
| PaymentDAO | `getPaymentsBySchedule(int scheduleId): List<Payment>` | Lay danh sach thanh toan cua 1 ky |

### Huong dan Visual Paradigm

1. Tao package `view.statistics` chua `StatPartnerFrm`, `HomeFrm`
2. Tao package `dao` chua `PartnerDAO`, `ContractDAO`, `ContractItemDAO`, `PaymentScheduleDAO`, `CustomerDAO`, `PaymentDAO`
3. Tao package `model` chua cac entity classes
4. Ve dependency tu `StatPartnerFrm` den cac DAO classes
5. Ve dependency tu DAO classes den cac Entity classes

---

## Cau 4: Sequence Diagram

### Huong dan Visual Paradigm

1. Tao Sequence Diagram trong VP
2. Them lifelines: `:Staff` (actor), `:StatPartnerFrm` (boundary), `:PartnerDAO` (control), `:ContractDAO` (control), `:ContractItemDAO` (control), `:PaymentScheduleDAO` (control), `:Database` (entity)
3. Ve message flow theo bang duoi, dung `synchronous message` (mui ten dac) cho request va `return message` (mui ten dut) cho response
4. Them `alt` fragment cho truong hop ngay khong hop le (E1, E2) va khong co du lieu (E3)
5. Them `ref` fragment cho viec hien thi chi tiet hop dong

### Bang buoc sequence

| Buoc | Nguoi gui | Nguoi nhan | Message | Ghi chu |
|------|-----------|------------|---------|---------|
| 1 | Staff | StatPartnerFrm | `selectMenu("Statistics of partners")` | Nhan vien chon chuc nang |
| 2 | StatPartnerFrm | Staff | `showForm(inStartDate, inEndDate, subView)` | Hien thi form nhap ky thoi gian |
| 3 | Staff | StatPartnerFrm | `enter(inStartDate="01/01/2026", inEndDate="31/12/2026")` | Nhap ky thong ke |
| 4 | Staff | StatPartnerFrm | `click(subView)` | Nhan nut xem thong ke |
| 5 | StatPartnerFrm | PartnerDAO | `getPartnerStatistics(startDate, endDate)` | Goi DAO lay thong ke |
| 6 | PartnerDAO | Database | `SELECT p.partnerId, p.partnerName, p.address, COUNT(ct.contractId) AS totalInvoices, SUM(ct.totalLoanAmount) AS totalSales, SUM(CASE WHEN ps.status='Unpaid' THEN ps.amount ELSE 0 END) AS totalOutstanding FROM tblPartner p JOIN tblContract ct ON p.partnerId = ct.partnerId JOIN tblPaymentSchedule ps ON ct.contractId = ps.contractId WHERE ct.signingDate BETWEEN '2026-01-01' AND '2026-12-31' GROUP BY p.partnerId ORDER BY totalSales DESC` | Truy van thong ke |
| 7 | Database | PartnerDAO | `ResultSet` | Tra ket qua |
| 8 | PartnerDAO | StatPartnerFrm | `List<Partner>` | Tra ve danh sach doi tac |
| 9 | StatPartnerFrm | Staff | `show(outPartnerStatTable)` | Hien thi bang thong ke: FPT Shop (2 HĐ, 45,000,000d, du no 22,500,000d), TGDD (1 HĐ, 20,000,000d, du no 20,000,000d) |
| 10 | Staff | StatPartnerFrm | `click("FPT Shop")` | Nhan vien click vao doi tac |
| 11 | StatPartnerFrm | ContractDAO | `getContractsByPartner(P001, startDate, endDate)` | Lay hop dong cua doi tac |
| 12 | ContractDAO | Database | `SELECT * FROM tblContract WHERE partnerId = 'P001' AND signingDate BETWEEN '2026-01-01' AND '2026-12-31'` | Truy van hop dong |
| 13 | Database | ContractDAO | `ResultSet` | Tra ket qua |
| 14 | ContractDAO | StatPartnerFrm | `List<Contract>` | Tra ve danh sach HĐ |
| 15 | StatPartnerFrm | Staff | `show(outContractList)` | Hien thi: HD001 (Nguyen Van A, 15/03/2026, 30,000,000d), HD003 (Le Thi C, 10/05/2026, 15,000,000d) |
| 16 | Staff | StatPartnerFrm | `click("HD001")` | Nhan vien click vao HĐ |
| 17 | StatPartnerFrm | ContractItemDAO | `getItemsByContract(HD001)` | Lay san pham trong HĐ |
| 18 | ContractItemDAO | Database | `SELECT ci.*, p.productName FROM tblContractItem ci JOIN tblProduct p ON ci.productId = p.productId WHERE ci.contractId = 'HD001'` | Truy van san pham |
| 19 | Database | ContractItemDAO | `ResultSet` | Tra ket qua |
| 20 | ContractItemDAO | StatPartnerFrm | `List<ContractItem>` | Tra ve danh sach SP |
| 21 | StatPartnerFrm | PaymentScheduleDAO | `getScheduleByContract(HD001)` | Lay lich thanh toan |
| 22 | PaymentScheduleDAO | Database | `SELECT * FROM tblPaymentSchedule WHERE contractId = 'HD001' ORDER BY periodNumber` | Truy van lich thanh toan |
| 23 | Database | PaymentScheduleDAO | `ResultSet` | Tra ket qua |
| 24 | PaymentScheduleDAO | StatPartnerFrm | `List<PaymentSchedule>` | Tra ve lich thanh toan |
| 25 | StatPartnerFrm | Staff | `show(outContractDetails)` | Hien thi chi tiet: KH Nguyen Van A, SĐT 0901234567; SP: iPhone 15 (1, 25,000,000d), AirPods Pro (1, 5,000,000d); thanh toan: ky 1 da thanh toan, ky 2 da thanh toan, ky 3 chua thanh toan, ky 4 chua thanh toan |

---

## Cau 5: Blackbox Testcase

### Test Plan

| No. | Module | Test case |
|-----|--------|-----------|
| TC01 | Statistics of partners | Thong ke doi tac theo doanh so trong ky va xem chi tiet hop dong |
| TC02 | Statistics of partners | Nhap ngay bat dau lon hon ngay ket thuc |
| TC03 | Statistics of partners | Khong co doi tac nao co doanh so trong ky |

### TC01: Thong ke doi tac theo doanh so trong ky 01/01/2026 - 31/12/2026

**Muc dich:** Kiem tra luong chinh — nhap ky thong ke, xem bang thong ke doi tac, drill-down vao hop dong, xem chi tiet.

**Database truoc:**

tblUser:
| userId | username | password | fullName | role |
|--------|----------|----------|----------|------|
| U001 | staff01 | matkhau123 | Nguyen Van Staff | Staff |

tblPartner:
| partnerId | partnerName | address | phoneNumber | branch |
|-----------|-------------|---------|-------------|--------|
| P001 | FPT Shop | 123 Le Loi, HCM | 0901111111 | Chi nhanh 1 |
| P002 | TGDD | 456 Tran Hung Dao, HN | 0902222222 | Chi nhanh 2 |

tblCustomer:
| customerId | customerName | phoneNumber | address | idCard |
|-----------|-------------|-------------|---------|--------|
| C001 | Nguyen Van A | 0901234567 | Ha Noi | 012345678901 |
| C002 | Tran Van B | 0912345678 | HCM | 023456789012 |
| C003 | Le Thi C | 0923456789 | Da Nang | 034567890123 |

tblContract:
| contractId | customerId | partnerId | signingDate | totalLoanAmount | status |
|-----------|-----------|-----------|-------------|-----------------|--------|
| HD001 | C001 | P001 | 2026-03-15 | 30000000 | Active |
| HD002 | C002 | P002 | 2026-04-20 | 20000000 | Active |
| HD003 | C003 | P001 | 2026-05-10 | 15000000 | Active |

tblContractItem:
| contractItemId | contractId | productId | quantity | unitPrice | amount |
|----------------|-----------|-----------|----------|-----------|--------|
| CI001 | HD001 | I001 | 1 | 25000000 | 25000000 |
| CI002 | HD001 | I002 | 1 | 5000000 | 5000000 |
| CI003 | HD003 | I003 | 1 | 15000000 | 15000000 |

tblPaymentSchedule:
| scheduleId | contractId | periodNumber | dueDate | amount | status |
|-----------|-----------|-------------|---------|--------|--------|
| PS001 | HD001 | 1 | 2026-04-15 | 7500000 | Paid |
| PS002 | HD001 | 2 | 2026-05-15 | 7500000 | Paid |
| PS003 | HD001 | 3 | 2026-06-15 | 7500000 | Unpaid |
| PS004 | HD001 | 4 | 2026-07-15 | 7500000 | Unpaid |
| PS005 | HD003 | 1 | 2026-06-10 | 3750000 | Paid |
| PS006 | HD003 | 2 | 2026-07-10 | 3750000 | Unpaid |
| PS007 | HD003 | 3 | 2026-08-10 | 3750000 | Unpaid |
| PS008 | HD003 | 4 | 2026-09-10 | 3750000 | Unpaid |

tblPayment:
| paymentId | scheduleId | paymentDate | amountPaid | method |
|-----------|-----------|-------------|-----------|--------|
| PAY001 | PS001 | 2026-04-15 | 7500000 | Cash |
| PAY002 | PS002 | 2026-05-15 | 7500000 | Cash |
| PAY003 | PS005 | 2026-06-10 | 3750000 | Transfer |

**Kich ban va ket qua mong doi:**

| Buoc | Kich ban | Ket qua mong doi |
|------|----------|------------------|
| 1 | Nhan vien dang nhap voi `staff01` / `matkhau123` | Dang nhap thanh cong, hien thi trang chu |
| 2 | Nhan vien click **Statistics of partners** | Hien thi giao diem thong ke voi o nhap ngay bat dau, ngay ket thuc, nut Xem thong ke |
| 3 | Nhan vien nhap ngay bat dau "01/01/2026", ngay ket thuc "31/12/2026", nhan **Xem thong ke** | Hien thi bang thong ke doi tac: FPT Shop (123 Le Loi, HCM, 2 hoa don, 45,000,000d, du no 22,500,000d), TGDD (456 Tran Hung Dao, HN, 1 hoa don, 20,000,000d, du no 20,000,000d) — sap xep giam dan theo doanh thu |
| 4 | Nhan vien click dong **FPT Shop** | Hien thi danh sach HĐ: HD001 (Nguyen Van A, 15/03/2026, vay 30,000,000d, da tra 15,000,000d, du no 15,000,000d, qua han 0d), HD003 (Le Thi C, 10/05/2026, vay 15,000,000d, da tra 3,750,000d, du no 11,250,000d, qua han 0d) |
| 5 | Nhan vien click dong **HD001** | Hien thi chi tiet HĐ: KH Nguyen Van A, SĐT 0901234567; SP: iPhone 15 (1, 25,000,000d, 25,000,000d), AirPods Pro (1, 5,000,000d, 5,000,000d); thanh toan: ky 1 da thanh toan (7,500,000d), ky 2 da thanh toan (7,500,000d), ky 3 chua thanh toan (7,500,000d), ky 4 chua thanh toan (7,500,000d) |
| 6 | Nhan vien xem chi tiet, nut **Back** | Tro ve danh sach hop dong cua FPT Shop |
| 7 | Nhan vien nut **Back** lan nua | Tro ve bang thong ke doi tac |

**Database sau:**

Khong thay doi (chuc nang chi doc du lieu thong ke, khong co thao tac ghi).
