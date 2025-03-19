-- ===================================================================
-- TẠO DATABASE AUTH MANAGEMENT
-- ===================================================================
CREATE DATABASE IF NOT EXISTS auth_management;
USE auth_management;

-- -------------------------------
-- BẢNG user_account: Thông tin người dùng
-- -------------------------------
CREATE TABLE user_account (
                              user_id INT AUTO_INCREMENT,
                              username VARCHAR(100) NOT NULL UNIQUE,
                              email VARCHAR(150) NOT NULL UNIQUE,
                              password_hash VARCHAR(255) NOT NULL,
                              full_name VARCHAR(150),
                              is_active BOOLEAN DEFAULT TRUE,
                              last_login DATETIME,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              CONSTRAINT pk_user_account PRIMARY KEY (user_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG role: Vai trò người dùng
-- -------------------------------
CREATE TABLE role (
                      role_id INT AUTO_INCREMENT,
                      role_name VARCHAR(50) NOT NULL UNIQUE,
                      description TEXT,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT pk_role PRIMARY KEY (role_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG user_role: Liên kết user và role (nhiều - nhiều)
-- -------------------------------
CREATE TABLE user_role (
                           user_id INT NOT NULL,
                           role_id INT NOT NULL,
                           assigned_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id),
                           CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user_account(user_id),
                           CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role(role_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG auth_token: Quản lý token đăng nhập
-- -------------------------------
CREATE TABLE auth_token (
                            token_id INT AUTO_INCREMENT,
                            user_id INT NOT NULL,
                            access_token VARCHAR(255) NOT NULL,
                            refresh_token VARCHAR(255),
                            issued_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            expires_at DATETIME NOT NULL,
                            is_revoked BOOLEAN DEFAULT FALSE,
                            CONSTRAINT pk_auth_token PRIMARY KEY (token_id),
                            CONSTRAINT fk_auth_token_user FOREIGN KEY (user_id) REFERENCES user_account(user_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG password_reset: Yêu cầu đặt lại mật khẩu
-- -------------------------------
CREATE TABLE password_reset (
                                reset_id INT AUTO_INCREMENT,
                                user_id INT NOT NULL,
                                reset_token VARCHAR(255) NOT NULL,
                                requested_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                expires_at DATETIME NOT NULL,
                                used BOOLEAN DEFAULT FALSE,
                                CONSTRAINT pk_password_reset PRIMARY KEY (reset_id),
                                CONSTRAINT fk_password_reset_user FOREIGN KEY (user_id) REFERENCES user_account(user_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG login_attempt: Lịch sử đăng nhập
-- -------------------------------
CREATE TABLE login_attempt (
                               attempt_id INT AUTO_INCREMENT,
                               user_id INT,
                               username VARCHAR(100),
                               attempt_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                               ip_address VARCHAR(45),
                               success BOOLEAN,
                               CONSTRAINT pk_login_attempt PRIMARY KEY (attempt_id)
) ENGINE=InnoDB;

-- -------------------------------
-- BẢNG user_session: Quản lý phiên làm việc
-- -------------------------------
CREATE TABLE user_session (
                              session_id INT AUTO_INCREMENT,
                              user_id INT NOT NULL,
                              session_token VARCHAR(255) NOT NULL,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              last_activity DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              expires_at DATETIME,
                              ip_address VARCHAR(45),
                              user_agent VARCHAR(255),
                              is_active BOOLEAN DEFAULT TRUE,
                              CONSTRAINT pk_user_session PRIMARY KEY (session_id),
                              CONSTRAINT fk_user_session_user FOREIGN KEY (user_id) REFERENCES user_account(user_id)
) ENGINE=InnoDB;
-- ===================================================================
-- TẠO DATABASE INVENTORY MANAGEMENT
-- ===================================================================
CREATE DATABASE IF NOT EXISTS inventory_management;
USE inventory_management;

-- -------------------------
-- BẢNG CẤU HÌNH ĐỊA LÝ & KHU VỰC
-- -------------------------
CREATE TABLE region (
                        region_id INT AUTO_INCREMENT,
                        region_name VARCHAR(100) NOT NULL,
                        description TEXT,
                        CONSTRAINT pk_region PRIMARY KEY (region_id)
) ENGINE=InnoDB;

CREATE TABLE location (
                          location_id INT AUTO_INCREMENT,
                          region_id INT NOT NULL,
                          location_name VARCHAR(150) NOT NULL,
                          address VARCHAR(255),
                          city VARCHAR(100),
                          country VARCHAR(100),
                          CONSTRAINT pk_location PRIMARY KEY (location_id),
                          CONSTRAINT fk_location_region FOREIGN KEY (region_id) REFERENCES region(region_id)
) ENGINE=InnoDB;

-- -------------------------
-- BẢNG KHO HÀNG – MỖI KHO THUỘC 1 VỊ TRÍ
-- -------------------------
CREATE TABLE warehouse (
                           warehouse_id INT AUTO_INCREMENT,
                           location_id INT NOT NULL,
                           warehouse_name VARCHAR(150) NOT NULL,
                           capacity INT,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT pk_warehouse PRIMARY KEY (warehouse_id),
                           CONSTRAINT fk_warehouse_location FOREIGN KEY (location_id) REFERENCES location(location_id)
) ENGINE=InnoDB;

-- -------------------------
-- BẢNG DANH MỤC, SẢN PHẨM & LÔ HÀNG
-- -------------------------
CREATE TABLE category (
                          category_id INT AUTO_INCREMENT,
                          category_name VARCHAR(100) NOT NULL,
                          description TEXT,
                          CONSTRAINT pk_category PRIMARY KEY (category_id)
) ENGINE=InnoDB;

CREATE TABLE product (
                         product_id INT AUTO_INCREMENT,
                         product_code VARCHAR(50) NOT NULL UNIQUE,
                         barcode VARCHAR(50),
                         product_name VARCHAR(150) NOT NULL,
                         description TEXT,
                         category_id INT,
                         unit_price DECIMAL(12,2) NOT NULL,
                         reorder_quantity INT DEFAULT 0,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT pk_product PRIMARY KEY (product_id),
                         CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
) ENGINE=InnoDB;

CREATE TABLE product_lot (
                             lot_id INT AUTO_INCREMENT,
                             product_id INT NOT NULL,
                             lot_number VARCHAR(50) NOT NULL,
                             quantity INT NOT NULL,
                             expiration_date DATE,
                             received_date DATE,
                             CONSTRAINT pk_product_lot PRIMARY KEY (lot_id),
                             CONSTRAINT fk_product_lot_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB;

-- -------------------------
-- BẢNG NHÀ CUNG CẤP & KHÁCH HÀNG
-- -------------------------
CREATE TABLE supplier (
                          supplier_id INT AUTO_INCREMENT,
                          supplier_name VARCHAR(150) NOT NULL,
                          contact_name VARCHAR(150),
                          address VARCHAR(255),
                          city VARCHAR(100),
                          phone VARCHAR(50),
                          email VARCHAR(100),
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT pk_supplier PRIMARY KEY (supplier_id)
) ENGINE=InnoDB;

CREATE TABLE customer (
                          customer_id INT AUTO_INCREMENT,
                          customer_name VARCHAR(150) NOT NULL,
                          contact_name VARCHAR(150),
                          address VARCHAR(255),
                          city VARCHAR(100),
                          phone VARCHAR(50),
                          email VARCHAR(100),
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT pk_customer PRIMARY KEY (customer_id)
) ENGINE=InnoDB;

-- -------------------------
-- BẢNG NHÂN SỰ & VAI TRÒ
-- -------------------------
CREATE TABLE role (
                      role_id INT AUTO_INCREMENT,
                      role_name VARCHAR(50) NOT NULL,
                      description TEXT,
                      CONSTRAINT pk_role PRIMARY KEY (role_id)
) ENGINE=InnoDB;

CREATE TABLE employee (
                          employee_id INT AUTO_INCREMENT,
                          employee_name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE,
                          phone VARCHAR(50),
                          role_id INT,
                          hire_date DATE,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT pk_employee PRIMARY KEY (employee_id),
                          CONSTRAINT fk_employee_role FOREIGN KEY (role_id) REFERENCES role(role_id)
) ENGINE=InnoDB;

-- -------------------------
-- BẢNG TỒN KHO
-- -------------------------
CREATE TABLE inventory (
                           inventory_id INT AUTO_INCREMENT,
                           product_id INT NOT NULL,
                           warehouse_id INT NOT NULL,
                           quantity INT NOT NULL,
                           minimum_level INT DEFAULT 0,
                           maximum_level INT DEFAULT 0,
                           last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           CONSTRAINT pk_inventory PRIMARY KEY (inventory_id),
                           CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES product(product_id),
                           CONSTRAINT fk_inventory_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id),
                           CONSTRAINT uq_inventory_product_warehouse UNIQUE (product_id, warehouse_id)
) ENGINE=InnoDB
    PARTITION BY HASH(warehouse_id)
        PARTITIONS 8;

-- -------------------------
-- BẢNG GIAO DỊCH TỒN KHO
-- -------------------------
CREATE TABLE inventory_transaction (
                                       transaction_id BIGINT AUTO_INCREMENT,
                                       transaction_type ENUM('purchase','sale','transfer','adjustment','return') NOT NULL,
                                       reference_id INT,
                                       product_id INT NOT NULL,
                                       warehouse_id INT NOT NULL,
                                       quantity INT NOT NULL,
                                       transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                       created_by INT,
                                       CONSTRAINT pk_inventory_transaction PRIMARY KEY (transaction_id),
                                       CONSTRAINT fk_it_product FOREIGN KEY (product_id) REFERENCES product(product_id),
                                       CONSTRAINT fk_it_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id),
                                       CONSTRAINT fk_it_employee FOREIGN KEY (created_by) REFERENCES employee(employee_id)
) ENGINE=InnoDB
    PARTITION BY RANGE (YEAR(transaction_date)) (
        PARTITION p2020 VALUES LESS THAN (2021),
        PARTITION p2021 VALUES LESS THAN (2022),
        PARTITION p2022 VALUES LESS THAN (2023),
        PARTITION p2023 VALUES LESS THAN (2024),
        PARTITION p_future VALUES LESS THAN MAXVALUE
        );

-- -------------------------
-- BẢNG ĐƠN HÀNG & THANH TOÁN
-- -------------------------
-- Purchase Order
CREATE TABLE purchase_order (
                                purchase_order_id INT AUTO_INCREMENT,
                                supplier_id INT NOT NULL,
                                order_date DATE NOT NULL,
                                expected_arrival DATE,
                                total_amount DECIMAL(12,2),
                                status VARCHAR(50) DEFAULT 'pending',
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT pk_purchase_order PRIMARY KEY (purchase_order_id),
                                CONSTRAINT fk_po_supplier FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
) ENGINE=InnoDB;

CREATE TABLE purchase_order_detail (
                                       po_detail_id INT AUTO_INCREMENT,
                                       purchase_order_id INT NOT NULL,
                                       product_id INT NOT NULL,
                                       quantity INT NOT NULL,
                                       unit_cost DECIMAL(12,2) NOT NULL,
                                       total DECIMAL(12,2) AS (quantity * unit_cost) STORED,
                                       CONSTRAINT pk_purchase_order_detail PRIMARY KEY (po_detail_id),
                                       CONSTRAINT fk_pod_po FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(purchase_order_id),
                                       CONSTRAINT fk_pod_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB;

CREATE TABLE purchase_payment (
                                  purchase_payment_id INT AUTO_INCREMENT,
                                  purchase_order_id INT NOT NULL,
                                  payment_date DATE NOT NULL,
                                  amount DECIMAL(12,2) NOT NULL,
                                  payment_method VARCHAR(50),
                                  status VARCHAR(50) DEFAULT 'completed',
                                  CONSTRAINT pk_purchase_payment PRIMARY KEY (purchase_payment_id),
                                  CONSTRAINT fk_pp_po FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(purchase_order_id)
) ENGINE=InnoDB;

-- Sales Order
CREATE TABLE sales_order (
                             sales_order_id INT AUTO_INCREMENT,
                             customer_id INT NOT NULL,
                             order_date DATE NOT NULL,
                             delivery_date DATE,
                             total_amount DECIMAL(12,2),
                             status VARCHAR(50) DEFAULT 'pending',
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT pk_sales_order PRIMARY KEY (sales_order_id),
                             CONSTRAINT fk_so_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
) ENGINE=InnoDB;

CREATE TABLE sales_order_detail (
                                    so_detail_id INT AUTO_INCREMENT,
                                    sales_order_id INT NOT NULL,
                                    product_id INT NOT NULL,
                                    quantity INT NOT NULL,
                                    unit_price DECIMAL(12,2) NOT NULL,
                                    total DECIMAL(12,2) AS (quantity * unit_price) STORED,
                                    CONSTRAINT pk_sales_order_detail PRIMARY KEY (so_detail_id),
                                    CONSTRAINT fk_sod_so FOREIGN KEY (sales_order_id) REFERENCES sales_order(sales_order_id),
                                    CONSTRAINT fk_sod_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB;

CREATE TABLE sales_payment (
                               sales_payment_id INT AUTO_INCREMENT,
                               sales_order_id INT NOT NULL,
                               payment_date DATE NOT NULL,
                               amount DECIMAL(12,2) NOT NULL,
                               payment_method VARCHAR(50),
                               status VARCHAR(50) DEFAULT 'completed',
                               CONSTRAINT pk_sales_payment PRIMARY KEY (sales_payment_id),
                               CONSTRAINT fk_sp_so FOREIGN KEY (sales_order_id) REFERENCES sales_order(sales_order_id)
) ENGINE=InnoDB;

-- Delivery Order
CREATE TABLE delivery_order (
                                delivery_order_id INT AUTO_INCREMENT,
                                sales_order_id INT NOT NULL,
                                delivery_date DATE,
                                delivery_address VARCHAR(255),
                                status VARCHAR(50) DEFAULT 'pending',
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT pk_delivery_order PRIMARY KEY (delivery_order_id),
                                CONSTRAINT fk_do_so FOREIGN KEY (sales_order_id) REFERENCES sales_order(sales_order_id)
) ENGINE=InnoDB;

CREATE TABLE delivery_order_detail (
                                       delivery_order_detail_id INT AUTO_INCREMENT,
                                       delivery_order_id INT NOT NULL,
                                       product_id INT NOT NULL,
                                       quantity INT NOT NULL,
                                       CONSTRAINT pk_delivery_order_detail PRIMARY KEY (delivery_order_detail_id),
                                       CONSTRAINT fk_dod_do FOREIGN KEY (delivery_order_id) REFERENCES delivery_order(delivery_order_id),
                                       CONSTRAINT fk_dod_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB;

-- Return Order
CREATE TABLE return_order (
                              return_order_id INT AUTO_INCREMENT,
                              sales_order_id INT,
                              customer_id INT,
                              return_date DATE NOT NULL,
                              reason TEXT,
                              status VARCHAR(50) DEFAULT 'pending',
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT pk_return_order PRIMARY KEY (return_order_id),
                              CONSTRAINT fk_ro_so FOREIGN KEY (sales_order_id) REFERENCES sales_order(sales_order_id),
                              CONSTRAINT fk_ro_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
) ENGINE=InnoDB;

CREATE TABLE return_order_detail (
                                     return_order_detail_id INT AUTO_INCREMENT,
                                     return_order_id INT NOT NULL,
                                     product_id INT NOT NULL,
                                     quantity INT NOT NULL,
                                     CONSTRAINT pk_return_order_detail PRIMARY KEY (return_order_detail_id),
                                     CONSTRAINT fk_rod_ro FOREIGN KEY (return_order_id) REFERENCES return_order(return_order_id),
                                     CONSTRAINT fk_rod_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB;

-- Stock Transfer
CREATE TABLE stock_transfer (
                                transfer_id INT AUTO_INCREMENT,
                                product_id INT NOT NULL,
                                from_warehouse_id INT NOT NULL,
                                to_warehouse_id INT NOT NULL,
                                transfer_date DATE NOT NULL,
                                quantity INT NOT NULL,
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT pk_stock_transfer PRIMARY KEY (transfer_id),
                                CONSTRAINT fk_st_product FOREIGN KEY (product_id) REFERENCES product(product_id),
                                CONSTRAINT fk_st_from_warehouse FOREIGN KEY (from_warehouse_id) REFERENCES warehouse(warehouse_id),
                                CONSTRAINT fk_st_to_warehouse FOREIGN KEY (to_warehouse_id) REFERENCES warehouse(warehouse_id)
) ENGINE=InnoDB;

-- -------------------------
-- Các bảng báo cáo, kế toán & tích hợp với MISA
-- -------------------------
-- Audit Log
CREATE TABLE audit_log (
                           audit_log_id BIGINT AUTO_INCREMENT,
                           table_name VARCHAR(100) NOT NULL,
                           record_id INT NOT NULL,
                           operation_type ENUM('insert','update','delete') NOT NULL,
                           old_data JSON,
                           new_data JSON,
                           changed_by INT,
                           change_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT pk_audit_log PRIMARY KEY (audit_log_id),
                           INDEX idx_audit_log_table_record (table_name, record_id),
                           CONSTRAINT fk_audit_log_employee FOREIGN KEY (changed_by) REFERENCES employee(employee_id)
) ENGINE=InnoDB;

-- Notification
CREATE TABLE notification (
                              notification_id INT AUTO_INCREMENT,
                              title VARCHAR(150) NOT NULL,
                              message TEXT,
                              recipient_type ENUM('employee','supplier','customer') NOT NULL,
                              recipient_id INT,
                              is_read BOOLEAN DEFAULT FALSE,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              processed_at DATETIME NULL,
                              CONSTRAINT pk_notification PRIMARY KEY (notification_id),
                              INDEX idx_notification_recipient (recipient_type, recipient_id)
) ENGINE=InnoDB;

-- Integration Event
CREATE TABLE integration_event (
                                   event_id BIGINT AUTO_INCREMENT,
                                   event_type VARCHAR(100) NOT NULL,
                                   aggregate_type VARCHAR(100) NOT NULL,
                                   aggregate_id INT NOT NULL,
                                   payload JSON,
                                   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   processed BOOLEAN DEFAULT FALSE,
                                   processed_at DATETIME NULL,
                                   CONSTRAINT pk_integration_event PRIMARY KEY (event_id),
                                   INDEX idx_integration_event_aggregate (aggregate_type, aggregate_id)
) ENGINE=InnoDB;

-- Warehouse Zone
CREATE TABLE warehouse_zone (
                                zone_id INT AUTO_INCREMENT,
                                warehouse_id INT NOT NULL,
                                zone_name VARCHAR(100) NOT NULL,
                                description TEXT,
                                CONSTRAINT pk_warehouse_zone PRIMARY KEY (zone_id),
                                CONSTRAINT fk_warehouse_zone_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id)
) ENGINE=InnoDB;

-- System Configuration
CREATE TABLE system_configuration (
                                      config_id INT AUTO_INCREMENT,
                                      config_key VARCHAR(100) NOT NULL UNIQUE,
                                      config_value VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      CONSTRAINT pk_system_configuration PRIMARY KEY (config_id)
) ENGINE=InnoDB;

-- Chart of Accounts
CREATE TABLE chart_of_accounts (
                                   account_id INT AUTO_INCREMENT,
                                   account_code VARCHAR(50) NOT NULL UNIQUE,
                                   account_name VARCHAR(150) NOT NULL,
                                   account_type ENUM('asset','liability','equity','revenue','expense') NOT NULL,
                                   description TEXT,
                                   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT pk_chart_of_accounts PRIMARY KEY (account_id)
) ENGINE=InnoDB;

-- Journal Entry & Detail
CREATE TABLE journal_entry (
                               journal_entry_id BIGINT AUTO_INCREMENT,
                               entry_date DATE NOT NULL,
                               description TEXT,
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT pk_journal_entry PRIMARY KEY (journal_entry_id)
) ENGINE=InnoDB;

CREATE TABLE journal_entry_detail (
                                      journal_entry_detail_id BIGINT AUTO_INCREMENT,
                                      journal_entry_id BIGINT NOT NULL,
                                      account_id INT NOT NULL,
                                      debit DECIMAL(12,2) DEFAULT 0,
                                      credit DECIMAL(12,2) DEFAULT 0,
                                      description TEXT,
                                      CONSTRAINT pk_journal_entry_detail PRIMARY KEY (journal_entry_detail_id),
                                      CONSTRAINT fk_jed_journal_entry FOREIGN KEY (journal_entry_id) REFERENCES journal_entry(journal_entry_id),
                                      CONSTRAINT fk_jed_account FOREIGN KEY (account_id) REFERENCES chart_of_accounts(account_id)
) ENGINE=InnoDB;

-- Accounting Period
CREATE TABLE accounting_period (
                                   period_id INT AUTO_INCREMENT,
                                   period_name VARCHAR(50) NOT NULL,
                                   start_date DATE NOT NULL,
                                   end_date DATE NOT NULL,
                                   status ENUM('open','closed') DEFAULT 'open',
                                   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT pk_accounting_period PRIMARY KEY (period_id)
) ENGINE=InnoDB;

-- Sales Invoice
CREATE TABLE sales_invoice (
                               invoice_id INT AUTO_INCREMENT,
                               sales_order_id INT NOT NULL,
                               invoice_number VARCHAR(50) NOT NULL UNIQUE,
                               invoice_date DATE NOT NULL,
                               total_amount DECIMAL(12,2) NOT NULL,
                               tax_amount DECIMAL(12,2) NOT NULL,
                               net_amount DECIMAL(12,2) NOT NULL,
                               accounting_period_id INT,
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT pk_sales_invoice PRIMARY KEY (invoice_id),
                               CONSTRAINT fk_si_sales_order FOREIGN KEY (sales_order_id) REFERENCES sales_order(sales_order_id),
                               CONSTRAINT fk_si_accounting_period FOREIGN KEY (accounting_period_id) REFERENCES accounting_period(period_id)
) ENGINE=InnoDB;

-- Purchase Invoice
CREATE TABLE purchase_invoice (
                                  invoice_id INT AUTO_INCREMENT,
                                  purchase_order_id INT NOT NULL,
                                  invoice_number VARCHAR(50) NOT NULL UNIQUE,
                                  invoice_date DATE NOT NULL,
                                  total_amount DECIMAL(12,2) NOT NULL,
                                  tax_amount DECIMAL(12,2) NOT NULL,
                                  net_amount DECIMAL(12,2) NOT NULL,
                                  accounting_period_id INT,
                                  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT pk_purchase_invoice PRIMARY KEY (invoice_id),
                                  CONSTRAINT fk_pi_purchase_order FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(purchase_order_id),
                                  CONSTRAINT fk_pi_accounting_period FOREIGN KEY (accounting_period_id) REFERENCES accounting_period(period_id)
) ENGINE=InnoDB;

-- Tax Rate
CREATE TABLE tax_rate (
                          tax_rate_id INT AUTO_INCREMENT,
                          tax_name VARCHAR(50) NOT NULL,
                          rate DECIMAL(5,2) NOT NULL,
                          effective_from DATE NOT NULL,
                          effective_to DATE,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT pk_tax_rate PRIMARY KEY (tax_rate_id)
) ENGINE=InnoDB;

-- Tax Detail
CREATE TABLE tax_detail (
                            tax_detail_id INT AUTO_INCREMENT,
                            invoice_id INT NOT NULL,
                            tax_rate_id INT NOT NULL,
                            tax_base DECIMAL(12,2) NOT NULL,
                            tax_amount DECIMAL(12,2) NOT NULL,
                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT pk_tax_detail PRIMARY KEY (tax_detail_id),
                            CONSTRAINT fk_td_tax_rate FOREIGN KEY (tax_rate_id) REFERENCES tax_rate(tax_rate_id)
) ENGINE=InnoDB;

-- Tax Report
CREATE TABLE tax_report (
                            report_id INT AUTO_INCREMENT,
                            accounting_period_id INT NOT NULL,
                            report_date DATE NOT NULL,
                            total_sales DECIMAL(12,2) NOT NULL,
                            total_purchase DECIMAL(12,2) NOT NULL,
                            total_tax_collected DECIMAL(12,2) NOT NULL,
                            total_tax_paid DECIMAL(12,2) NOT NULL,
                            net_tax DECIMAL(12,2) NOT NULL,
                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT pk_tax_report PRIMARY KEY (report_id),
                            CONSTRAINT fk_tr_accounting_period FOREIGN KEY (accounting_period_id) REFERENCES accounting_period(period_id)
) ENGINE=InnoDB;

-- MISA Integration Log
CREATE TABLE misa_integration_log (
                                      integration_id BIGINT AUTO_INCREMENT,
                                      operation VARCHAR(100) NOT NULL,
                                      entity_name VARCHAR(100) NOT NULL,
                                      entity_id INT NOT NULL,
                                      status ENUM('success','failure') DEFAULT 'success',
                                      message TEXT,
                                      integration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT pk_misa_integration_log PRIMARY KEY (integration_id),
                                      INDEX idx_misa_entity (entity_name, entity_id)
) ENGINE=InnoDB;

-- MISA Export
CREATE TABLE misa_export (
                             export_id BIGINT AUTO_INCREMENT,
                             export_type ENUM('sales_invoice','purchase_invoice','journal_entry') NOT NULL,
                             export_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                             export_file_path VARCHAR(255),
                             status ENUM('pending','exported','error') DEFAULT 'pending',
                             message TEXT,
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT pk_misa_export PRIMARY KEY (export_id)
) ENGINE=InnoDB;
