-- ===================================================================
-- Bảng Cấu hình Địa lý & Khu vực (Region, Location)
-- ===================================================================

CREATE TABLE Region (
                        RegionID INT AUTO_INCREMENT PRIMARY KEY,
                        RegionName VARCHAR(100) NOT NULL,
                        Description TEXT
) ENGINE=InnoDB;

CREATE TABLE Location (
                          LocationID INT AUTO_INCREMENT PRIMARY KEY,
                          RegionID INT NOT NULL,
                          LocationName VARCHAR(150) NOT NULL,
                          Address VARCHAR(255),
                          City VARCHAR(100),
                          Country VARCHAR(100),
                          CONSTRAINT FK_Location_Region FOREIGN KEY (RegionID) REFERENCES Region(RegionID)
) ENGINE=InnoDB;

-- ===================================================================
-- Bảng Kho hàng – Mỗi kho thuộc 1 vị trí cụ thể
-- ===================================================================

CREATE TABLE Warehouse (
                           WarehouseID INT AUTO_INCREMENT PRIMARY KEY,
                           LocationID INT NOT NULL,
                           WarehouseName VARCHAR(150) NOT NULL,
                           Capacity INT,
                           CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT FK_Warehouse_Location FOREIGN KEY (LocationID) REFERENCES Location(LocationID)
) ENGINE=InnoDB;

-- ===================================================================
-- Bảng Sản phẩm, Danh mục & Lô hàng (sử dụng Partitioning theo ProductID nếu cần)
-- ===================================================================

CREATE TABLE Category (
                          CategoryID INT AUTO_INCREMENT PRIMARY KEY,
                          CategoryName VARCHAR(100) NOT NULL,
                          Description TEXT
) ENGINE=InnoDB;

CREATE TABLE Product (
                         ProductID INT AUTO_INCREMENT PRIMARY KEY,
                         ProductCode VARCHAR(50) NOT NULL UNIQUE,
                         Barcode VARCHAR(50),
                         ProductName VARCHAR(150) NOT NULL,
                         Description TEXT,
                         CategoryID INT,
                         UnitPrice DECIMAL(12,2) NOT NULL,
                         ReorderQuantity INT DEFAULT 0,
                         CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                         UpdatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT FK_Product_Category FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
) ENGINE=InnoDB;

CREATE TABLE ProductLot (
                            LotID INT AUTO_INCREMENT PRIMARY KEY,
                            ProductID INT NOT NULL,
                            LotNumber VARCHAR(50) NOT NULL,
                            Quantity INT NOT NULL,
                            ExpirationDate DATE,
                            ReceivedDate DATE,
                            CONSTRAINT FK_PL_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
) ENGINE=InnoDB;

-- ===================================================================
-- Bảng Nhà cung cấp & Khách hàng (có thể mở rộng thêm chi tiết cho hệ thống lớn)
-- ===================================================================

CREATE TABLE Supplier (
                          SupplierID INT AUTO_INCREMENT PRIMARY KEY,
                          SupplierName VARCHAR(150) NOT NULL,
                          ContactName VARCHAR(150),
                          Address VARCHAR(255),
                          City VARCHAR(100),
                          Phone VARCHAR(50),
                          Email VARCHAR(100),
                          CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE Customer (
                          CustomerID INT AUTO_INCREMENT PRIMARY KEY,
                          CustomerName VARCHAR(150) NOT NULL,
                          ContactName VARCHAR(150),
                          Address VARCHAR(255),
                          City VARCHAR(100),
                          Phone VARCHAR(50),
                          Email VARCHAR(100),
                          CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ===================================================================
-- Bảng Nhân sự – Quản lý người điều hành hệ thống
-- ===================================================================

CREATE TABLE Role (
                      RoleID INT AUTO_INCREMENT PRIMARY KEY,
                      RoleName VARCHAR(50) NOT NULL,
                      Description TEXT
) ENGINE=InnoDB;

CREATE TABLE Employee (
                          EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
                          EmployeeName VARCHAR(100) NOT NULL,
                          Email VARCHAR(100) UNIQUE,
                          Phone VARCHAR(50),
                          RoleID INT,
                          HireDate DATE,
                          CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT FK_Employee_Role FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
) ENGINE=InnoDB;

-- ===================================================================
-- Bảng Tồn kho (Inventory)
-- ===================================================================
-- Bảng Inventory có thể được phân vùng theo WarehouseID để tối ưu truy vấn theo kho.
-- Ví dụ: PARTITION BY HASH(WarehouseID) PARTITIONS 8;

CREATE TABLE Inventory (
                           InventoryID INT AUTO_INCREMENT PRIMARY KEY,
                           ProductID INT NOT NULL,
                           WarehouseID INT NOT NULL,
                           Quantity INT NOT NULL,
                           MinimumLevel INT DEFAULT 0,
                           MaximumLevel INT DEFAULT 0,
                           LastUpdated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           CONSTRAINT FK_Inventory_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                           CONSTRAINT FK_Inventory_Warehouse FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID),
                           UNIQUE KEY UQ_ProductWarehouse (ProductID, WarehouseID)
) ENGINE=InnoDB
PARTITION BY HASH(WarehouseID)
PARTITIONS 8;

-- ===================================================================
-- Bảng Giao dịch tồn kho (InventoryTransaction)
-- ===================================================================
-- Lưu lại các giao dịch lớn (mua, bán, chuyển, điều chỉnh, trả hàng)
-- Có thể phân vùng theo TransactionDate (theo tháng hoặc năm) để tối ưu báo cáo lịch sử.
CREATE TABLE InventoryTransaction (
                                      TransactionID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      TransactionType ENUM('Purchase','Sale','Transfer','Adjustment','Return') NOT NULL,
                                      ReferenceID INT, -- tham chiếu đến ID của đơn hàng, chuyển kho, v.v.
                                      ProductID INT NOT NULL,
                                      WarehouseID INT NOT NULL,
                                      Quantity INT NOT NULL,
                                      TransactionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      CreatedBy INT,  -- EmployeeID thực hiện giao dịch
                                      CONSTRAINT FK_IT_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                                      CONSTRAINT FK_IT_Warehouse FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID),
                                      CONSTRAINT FK_IT_Employee FOREIGN KEY (CreatedBy) REFERENCES Employee(EmployeeID)
) ENGINE=InnoDB
PARTITION BY RANGE (YEAR(TransactionDate)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION pFuture VALUES LESS THAN MAXVALUE
);

-- ===================================================================
-- Các bảng Đơn hàng & Thanh toán (Mua, Bán, Giao, Trả)
-- ===================================================================

-- Đơn mua hàng (PurchaseOrder) và chi tiết
CREATE TABLE PurchaseOrder (
                               PurchaseOrderID INT AUTO_INCREMENT PRIMARY KEY,
                               SupplierID INT NOT NULL,
                               OrderDate DATE NOT NULL,
                               ExpectedArrival DATE,
                               TotalAmount DECIMAL(12,2),
                               Status VARCHAR(50) DEFAULT 'Pending',
                               CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT FK_PO_Supplier FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
) ENGINE=InnoDB;

CREATE TABLE PurchaseOrderDetail (
                                     PO_DetailID INT AUTO_INCREMENT PRIMARY KEY,
                                     PurchaseOrderID INT NOT NULL,
                                     ProductID INT NOT NULL,
                                     Quantity INT NOT NULL,
                                     UnitCost DECIMAL(12,2) NOT NULL,
                                     Total DECIMAL(12,2) AS (Quantity * UnitCost) STORED,
                                     CONSTRAINT FK_POD_PO FOREIGN KEY (PurchaseOrderID) REFERENCES PurchaseOrder(PurchaseOrderID),
                                     CONSTRAINT FK_POD_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
) ENGINE=InnoDB;

CREATE TABLE PurchasePayment (
                                 PurchasePaymentID INT AUTO_INCREMENT PRIMARY KEY,
                                 PurchaseOrderID INT NOT NULL,
                                 PaymentDate DATE NOT NULL,
                                 Amount DECIMAL(12,2) NOT NULL,
                                 PaymentMethod VARCHAR(50),
                                 Status VARCHAR(50) DEFAULT 'Completed',
                                 CONSTRAINT FK_PurchasePayment_PO FOREIGN KEY (PurchaseOrderID) REFERENCES PurchaseOrder(PurchaseOrderID)
) ENGINE=InnoDB;

-- Đơn bán hàng (SalesOrder) và chi tiết
CREATE TABLE SalesOrder (
                            SalesOrderID INT AUTO_INCREMENT PRIMARY KEY,
                            CustomerID INT NOT NULL,
                            OrderDate DATE NOT NULL,
                            DeliveryDate DATE,
                            TotalAmount DECIMAL(12,2),
                            Status VARCHAR(50) DEFAULT 'Pending',
                            CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT FK_SO_Customer FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
) ENGINE=InnoDB;

CREATE TABLE SalesOrderDetail (
                                  SO_DetailID INT AUTO_INCREMENT PRIMARY KEY,
                                  SalesOrderID INT NOT NULL,
                                  ProductID INT NOT NULL,
                                  Quantity INT NOT NULL,
                                  UnitPrice DECIMAL(12,2) NOT NULL,
                                  Total DECIMAL(12,2) AS (Quantity * UnitPrice) STORED,
                                  CONSTRAINT FK_SOD_SO FOREIGN KEY (SalesOrderID) REFERENCES SalesOrder(SalesOrderID),
                                  CONSTRAINT FK_SOD_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
) ENGINE=InnoDB;

CREATE TABLE SalesPayment (
                              SalesPaymentID INT AUTO_INCREMENT PRIMARY KEY,
                              SalesOrderID INT NOT NULL,
                              PaymentDate DATE NOT NULL,
                              Amount DECIMAL(12,2) NOT NULL,
                              PaymentMethod VARCHAR(50),
                              Status VARCHAR(50) DEFAULT 'Completed',
                              CONSTRAINT FK_SalesPayment_SO FOREIGN KEY (SalesOrderID) REFERENCES SalesOrder(SalesOrderID)
) ENGINE=InnoDB;

-- Đơn giao hàng (DeliveryOrder) và chi tiết
CREATE TABLE DeliveryOrder (
                               DeliveryOrderID INT AUTO_INCREMENT PRIMARY KEY,
                               SalesOrderID INT NOT NULL,
                               DeliveryDate DATE,
                               DeliveryAddress VARCHAR(255),
                               Status VARCHAR(50) DEFAULT 'Pending',
                               CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT FK_DO_SalesOrder FOREIGN KEY (SalesOrderID) REFERENCES SalesOrder(SalesOrderID)
) ENGINE=InnoDB;

CREATE TABLE DeliveryOrderDetail (
                                     DeliveryOrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
                                     DeliveryOrderID INT NOT NULL,
                                     ProductID INT NOT NULL,
                                     Quantity INT NOT NULL,
                                     CONSTRAINT FK_DOD_DO FOREIGN KEY (DeliveryOrderID) REFERENCES DeliveryOrder(DeliveryOrderID),
                                     CONSTRAINT FK_DOD_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
) ENGINE=InnoDB;

-- Đơn trả hàng (ReturnOrder) và chi tiết
CREATE TABLE ReturnOrder (
                             ReturnOrderID INT AUTO_INCREMENT PRIMARY KEY,
                             SalesOrderID INT,
                             CustomerID INT,
                             ReturnDate DATE NOT NULL,
                             Reason TEXT,
                             Status VARCHAR(50) DEFAULT 'Pending',
                             CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT FK_RO_SalesOrder FOREIGN KEY (SalesOrderID) REFERENCES SalesOrder(SalesOrderID),
                             CONSTRAINT FK_RO_Customer FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
) ENGINE=InnoDB;

CREATE TABLE ReturnOrderDetail (
                                   ReturnOrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
                                   ReturnOrderID INT NOT NULL,
                                   ProductID INT NOT NULL,
                                   Quantity INT NOT NULL,
                                   CONSTRAINT FK_ROD_RO FOREIGN KEY (ReturnOrderID) REFERENCES ReturnOrder(ReturnOrderID),
                                   CONSTRAINT FK_ROD_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
) ENGINE=InnoDB;

-- Đơn chuyển kho (StockTransfer)
CREATE TABLE StockTransfer (
                               TransferID INT AUTO_INCREMENT PRIMARY KEY,
                               ProductID INT NOT NULL,
                               FromWarehouseID INT NOT NULL,
                               ToWarehouseID INT NOT NULL,
                               TransferDate DATE NOT NULL,
                               Quantity INT NOT NULL,
                               CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT FK_ST_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                               CONSTRAINT FK_ST_FromWarehouse FOREIGN KEY (FromWarehouseID) REFERENCES Warehouse(WarehouseID),
                               CONSTRAINT FK_ST_ToWarehouse FOREIGN KEY (ToWarehouseID) REFERENCES Warehouse(WarehouseID)
) ENGINE=InnoDB;
-- ================================================================
-- BẢNG AUDIT LOG: Lưu lại các thay đổi trên dữ liệu của các bảng quan trọng
-- ================================================================
CREATE TABLE AuditLog (
                          AuditLogID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          TableName VARCHAR(100) NOT NULL,
                          RecordID INT NOT NULL,
                          OperationType ENUM('INSERT','UPDATE','DELETE') NOT NULL,
                          OldData JSON,
                          NewData JSON,
                          ChangedBy INT, -- EmployeeID thực hiện thay đổi
                          ChangeDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                          INDEX idx_Table_Record (TableName, RecordID),
                          CONSTRAINT FK_AuditLog_User FOREIGN KEY (ChangedBy) REFERENCES Employee(EmployeeID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG NOTIFICATION: Lưu trữ các thông báo hệ thống cho người dùng (nhân viên, khách hàng, nhà cung cấp)
-- ================================================================
CREATE TABLE Notification (
                              NotificationID INT AUTO_INCREMENT PRIMARY KEY,
                              Title VARCHAR(150) NOT NULL,
                              Message TEXT,
                              RecipientType ENUM('Employee','Supplier','Customer') NOT NULL,
                              RecipientID INT,  -- ID của đối tượng nhận
                              IsRead BOOLEAN DEFAULT FALSE,
                              CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                              ProcessedAt DATETIME NULL,
                              INDEX idx_Recipient (RecipientType, RecipientID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG INTEGRATION EVENT: Hỗ trợ mô hình event sourcing cho giao tiếp giữa các microservice
-- ================================================================
CREATE TABLE IntegrationEvent (
                                  EventID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  EventType VARCHAR(100) NOT NULL,
                                  AggregateType VARCHAR(100) NOT NULL,
                                  AggregateID INT NOT NULL,
                                  Payload JSON,  -- Dữ liệu sự kiện dạng JSON
                                  CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  Processed BOOLEAN DEFAULT FALSE,
                                  ProcessedAt DATETIME NULL,
                                  INDEX idx_Aggregate (AggregateType, AggregateID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG WAREHOUSE ZONE: Phân chia kho thành các khu vực hoặc zone để định vị hàng hóa chính xác hơn
-- ================================================================
CREATE TABLE WarehouseZone (
                               ZoneID INT AUTO_INCREMENT PRIMARY KEY,
                               WarehouseID INT NOT NULL,
                               ZoneName VARCHAR(100) NOT NULL,
                               Description TEXT,
                               CONSTRAINT FK_WarehouseZone_Warehouse FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG USER ACCOUNT: Quản lý tài khoản đăng nhập (nếu cần tách riêng so với bảng Employee)
-- ================================================================
CREATE TABLE UserAccount (
                             UserID INT AUTO_INCREMENT PRIMARY KEY,
                             Username VARCHAR(100) NOT NULL UNIQUE,
                             PasswordHash VARCHAR(255) NOT NULL,
                             EmployeeID INT,  -- Liên kết đến bảng Employee nếu người dùng là nhân viên
                             RoleID INT,      -- Quyền truy cập (có thể dùng chung với bảng Role)
                             IsActive BOOLEAN DEFAULT TRUE,
                             LastLogin DATETIME,
                             CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT FK_UserAccount_Employee FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
                             CONSTRAINT FK_UserAccount_Role FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG SCHEDULED JOB: Lưu trữ thông tin các tác vụ chạy định kỳ (batch job, báo cáo, …)
-- ================================================================
CREATE TABLE ScheduledJob (
                              JobID INT AUTO_INCREMENT PRIMARY KEY,
                              JobName VARCHAR(150) NOT NULL,
                              JobDescription TEXT,
                              ScheduleCron VARCHAR(50) NOT NULL, -- Biểu thức Cron
                              LastRun DATETIME,
                              NextRun DATETIME,
                              Status VARCHAR(50) DEFAULT 'Scheduled',
                              CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG SYSTEM CONFIGURATION: Lưu các tham số cấu hình hệ thống (có thể được cập nhật động)
-- ================================================================
CREATE TABLE SystemConfiguration (
                                     ConfigID INT AUTO_INCREMENT PRIMARY KEY,
                                     ConfigKey VARCHAR(100) NOT NULL UNIQUE,
                                     ConfigValue VARCHAR(255) NOT NULL,
                                     Description TEXT,
                                     UpdatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;
-- ================================================================
-- BẢNG CHART OF ACCOUNTS: Danh mục tài khoản kế toán (Account Structure)
-- ================================================================
CREATE TABLE ChartOfAccounts (
                                 AccountID INT AUTO_INCREMENT PRIMARY KEY,
                                 AccountCode VARCHAR(50) NOT NULL UNIQUE,
                                 AccountName VARCHAR(150) NOT NULL,
                                 AccountType ENUM('Asset','Liability','Equity','Revenue','Expense') NOT NULL,
                                 Description TEXT,
                                 CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG JOURNAL ENTRY: Ghi nhận bút toán kế toán
-- ================================================================
CREATE TABLE JournalEntry (
                              JournalEntryID BIGINT AUTO_INCREMENT PRIMARY KEY,
                              EntryDate DATE NOT NULL,
                              Description TEXT,
                              CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Bảng chi tiết bút toán
CREATE TABLE JournalEntryDetail (
                                    JournalEntryDetailID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    JournalEntryID BIGINT NOT NULL,
                                    AccountID INT NOT NULL,
                                    Debit DECIMAL(12,2) DEFAULT 0,
                                    Credit DECIMAL(12,2) DEFAULT 0,
                                    Description TEXT,
                                    CONSTRAINT FK_JED_JournalEntry FOREIGN KEY (JournalEntryID) REFERENCES JournalEntry(JournalEntryID),
                                    CONSTRAINT FK_JED_Account FOREIGN KEY (AccountID) REFERENCES ChartOfAccounts(AccountID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG ACCOUNTING PERIOD: Định nghĩa kỳ kế toán cho mục đích báo cáo thuế
-- ================================================================
CREATE TABLE AccountingPeriod (
                                  PeriodID INT AUTO_INCREMENT PRIMARY KEY,
                                  PeriodName VARCHAR(50) NOT NULL,  -- Ví dụ: "Tháng 1/2025", "Quý 1/2025"
                                  StartDate DATE NOT NULL,
                                  EndDate DATE NOT NULL,
                                  Status ENUM('Open','Closed') DEFAULT 'Open',
                                  CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG SALES INVOICE: Hóa đơn bán hàng phục vụ báo cáo thuế và tích hợp kế toán
-- ================================================================
CREATE TABLE SalesInvoice (
                              InvoiceID INT AUTO_INCREMENT PRIMARY KEY,
                              SalesOrderID INT NOT NULL,
                              InvoiceNumber VARCHAR(50) NOT NULL UNIQUE,
                              InvoiceDate DATE NOT NULL,
                              TotalAmount DECIMAL(12,2) NOT NULL,
                              TaxAmount DECIMAL(12,2) NOT NULL,
                              NetAmount DECIMAL(12,2) NOT NULL,
                              AccountingPeriodID INT,
                              CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT FK_SI_SalesOrder FOREIGN KEY (SalesOrderID) REFERENCES SalesOrder(SalesOrderID),
                              CONSTRAINT FK_SI_AccountingPeriod FOREIGN KEY (AccountingPeriodID) REFERENCES AccountingPeriod(PeriodID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG PURCHASE INVOICE: Hóa đơn mua hàng phục vụ báo cáo thuế
-- ================================================================
CREATE TABLE PurchaseInvoice (
                                 InvoiceID INT AUTO_INCREMENT PRIMARY KEY,
                                 PurchaseOrderID INT NOT NULL,
                                 InvoiceNumber VARCHAR(50) NOT NULL UNIQUE,
                                 InvoiceDate DATE NOT NULL,
                                 TotalAmount DECIMAL(12,2) NOT NULL,
                                 TaxAmount DECIMAL(12,2) NOT NULL,
                                 NetAmount DECIMAL(12,2) NOT NULL,
                                 AccountingPeriodID INT,
                                 CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 CONSTRAINT FK_PI_PurchaseOrder FOREIGN KEY (PurchaseOrderID) REFERENCES PurchaseOrder(PurchaseOrderID),
                                 CONSTRAINT FK_PI_AccountingPeriod FOREIGN KEY (AccountingPeriodID) REFERENCES AccountingPeriod(PeriodID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG TAX RATE: Quản lý thuế suất áp dụng (ví dụ: VAT 10%, Thuế xuất khẩu,…)
-- ================================================================
CREATE TABLE TaxRate (
                         TaxRateID INT AUTO_INCREMENT PRIMARY KEY,
                         TaxName VARCHAR(50) NOT NULL,
                         Rate DECIMAL(5,2) NOT NULL,   -- Ví dụ: 10.00 cho 10%
                         EffectiveFrom DATE NOT NULL,
                         EffectiveTo DATE,
                         CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG TAX DETAIL: Chi tiết thuế trên từng hóa đơn (bán hoặc mua)
-- ================================================================
CREATE TABLE TaxDetail (
                           TaxDetailID INT AUTO_INCREMENT PRIMARY KEY,
                           InvoiceID INT NOT NULL, -- có thể tham chiếu đến SalesInvoice hoặc PurchaseInvoice (xem lưu ý phía dưới)
                           TaxRateID INT NOT NULL,
                           TaxBase DECIMAL(12,2) NOT NULL,
                           TaxAmount DECIMAL(12,2) NOT NULL,
                           CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT FK_TD_TaxRate FOREIGN KEY (TaxRateID) REFERENCES TaxRate(TaxRateID)
    -- Lưu ý: Khi thực thi tích hợp, bạn sẽ xác định invoice thuộc loại nào và xử lý bên ngoài hoặc thông qua view tổng hợp
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG TAX REPORT: Báo cáo thuế tổng hợp theo kỳ (tháng, quý, năm)
-- ================================================================
CREATE TABLE TaxReport (
                           ReportID INT AUTO_INCREMENT PRIMARY KEY,
                           AccountingPeriodID INT NOT NULL,
                           ReportDate DATE NOT NULL,
                           TotalSales DECIMAL(12,2) NOT NULL,
                           TotalPurchase DECIMAL(12,2) NOT NULL,
                           TotalTaxCollected DECIMAL(12,2) NOT NULL,
                           TotalTaxPaid DECIMAL(12,2) NOT NULL,
                           NetTax DECIMAL(12,2) NOT NULL,
                           CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT FK_TR_AccountingPeriod FOREIGN KEY (AccountingPeriodID) REFERENCES AccountingPeriod(PeriodID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG MISA INTEGRATION LOG: Lưu lại các sự kiện tích hợp với phần mềm MISA
-- ================================================================
CREATE TABLE MISAIntegrationLog (
                                    IntegrationID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    Operation VARCHAR(100) NOT NULL,
                                    EntityName VARCHAR(100) NOT NULL,
                                    EntityID INT NOT NULL,
                                    Status ENUM('Success','Failure') DEFAULT 'Success',
                                    Message TEXT,
                                    IntegrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    INDEX idx_MISA_Entity (EntityName, EntityID)
) ENGINE=InnoDB;

-- ================================================================
-- BẢNG MISA EXPORT: Lưu trữ dữ liệu đã được xuất sang MISA (ví dụ: hóa đơn, bút toán, v.v.)
-- ================================================================
CREATE TABLE MISAExport (
                            ExportID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            ExportType ENUM('SalesInvoice','PurchaseInvoice','JournalEntry') NOT NULL,
                            ExportDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                            ExportFilePath VARCHAR(255),
                            Status ENUM('Pending','Exported','Error') DEFAULT 'Pending',
                            Message TEXT,
                            CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
