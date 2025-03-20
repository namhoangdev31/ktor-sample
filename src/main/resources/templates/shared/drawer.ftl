<!-- Sidebar -->
<div class="sidebar">
    <div class="sidebar-brand">
        <picture>
            <!-- Logo for screens wider than 768px -->
            <source
                    media="(min-width: 769px)"
                    srcset="/static/images/drawer_logo.png"
            />
            <!-- Logo for screens up to 768px -->
            <source
                    media="(max-width: 768px)"
                    srcset="/static/images/logo.png"
            />
            <!-- Fallback image if sources are not supported -->
            <img
                    src="/static/images/drawer_logo.png"
                    alt="Kanban Logo"
                    class="img-fluid"
            />
        </picture>
    </div>
    <div class="sidebar-menu">
        <a href="#" class="menu-item active">
            <i class="fas fa-home"></i>
            <span>Dashboard</span>
        </a>
        <a href="#" class="menu-item">
            <i class="fas fa-boxes"></i>
            <span>Inventory</span>
        </a>
        <a href="#" class="menu-item">
            <i class="fas fa-chart-bar"></i>
            <span>Reports</span>
        </a>
        <a href="#" class="menu-item">
            <i class="fas fa-users"></i>
            <span>Suppliers</span>
        </a>
        <a href="#" class="menu-item">
            <i class="fas fa-shopping-cart"></i>
            <span>Orders</span>
        </a>
        <a href="#" class="menu-item">
            <i class="fas fa-store"></i>
            <span>Manage Store</span>
        </a>
    </div>
    <a href="#" class="sidebar-footer">
        <i class="fas fa-cog"></i>
        <span>Settings</span>
    </a>
    <a href="#" class="sidebar-footer">
        <i class="fas fa-sign-out-alt"></i>
        <span>Log Out</span>
    </a>
</div>
