package com.example.config


import com.example.controllers.AuthController
import com.example.controllers.UserController
import com.example.dao.*
import com.example.repositories.AuthRepository
import com.example.repositories.AuthRepositoryImpl
import com.example.repositories.UserRepository
import com.example.repositories.UserRepositoryImpl
import com.example.services.AuthService
import com.example.services.UserService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                configModule(environment),
                appDaoModule(),
                authModule(),
                userModule()
            )
        )
    }
}

fun authModule() = module {
    single<AuthService> { AuthService(get()) }
    single<AuthController> { AuthController() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

fun userModule() = module {
    single<UserService> { UserService() }
    single<UserController> { UserController() }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

fun appDaoModule() = module {
    single<UserDao> { UserDaoImpl() }
    single<RegionDao> { RegionDaoImpl() }
    single<LocationDao> { LocationDaoImpl() }
    single<WarehouseDao> { WarehouseDaoImpl() }
    single<CustomerDao> { CustomerDaoImpl() }
    single<CategoryDao> { CategoryDaoImpl() }
    single<DeliveryOrderDao> { DeliveryOrderDaoImpl() }
    single<DeliveryOrderDetailDao> { DeliveryOrderDetailDaoImpl() }
    single<EmployeeDao> { EmployeeDaoImpl() }
    single<InventoryDao> { InventoryDaoImpl() }
    single<InventoryTransactionDao> { InventoryTransactionDaoImpl() }
    single<PurchasePaymentDao> { PurchasePaymentDaoImpl() }
    single<PurchaseOrderDao> { PurchaseOrderDaoImpl() }
    single<PurchaseOrderDetailDao> { PurchaseOrderDetailDaoImpl() }
    single<ReturnOrderDao> { ReturnOrderDaoImpl() }
    single<ReturnOrderDetailDao> { ReturnOrderDetailDaoImpl() }
    single<ProductDao> { ProductDaoImpl() }
    single<ProductImageDao> { ProductImageDaoImpl() }
    single<ProductLotDao> { ProductLotDaoImpl() }
    single<RoleDao> { RoleDaoImpl() }
    single<SalesOrderDao> { SalesOrderDaoImpl() }
    single<SalesOrderDetailDao> { SalesOrderDetailDaoImpl() }
    single<SalesPaymentDao> { SalesPaymentDaoImpl() }
    single<SupplierDao> { SupplierDaoImpl() }
    single<StockTransferDao> { StockTransferDaoImpl() }
}

fun configModule(applicationEnvironment: ApplicationEnvironment) = module {
    single<ApplicationEnvironment> { applicationEnvironment }
}
