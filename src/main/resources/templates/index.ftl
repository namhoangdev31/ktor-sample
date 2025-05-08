<#include "shared/head.ftl">
<body>
<#--<#include "shared/drawer.ftl">-->
<main class="content-auth">
    <#--    <#include "shared/header.ftl">-->
    <!-- Navigation Bar -->

    <!-- Welcome Section -->
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-6 text-center">
                <h1 class="display-4 mb-3">Welcome to the Backend System</h1>
                <p class="lead mb-4">This is the central hub of your backend. From here, you can manage users, view
                    logs, and perform administrative tasks.</p>

                <div class="card text-dark bg-light shadow-sm">
                    <div class="card-body">
                        <h4 class="card-title">Quick Overview</h4>
                        <p class="card-text">Access the dashboard to get real-time data, manage the database, and
                            configure settings to optimize your workflow.</p>
                        <a href="#" class="btn btn-primary">Go to Dashboard</a>
                    </div>
                </div>

                <div class="mt-4">
                    <h4 class="mb-3">Recent Activity</h4>
                    <ul class="list-group">
                        <li class="list-group-item">User John Doe logged in at 11:30 AM</li>
                        <li class="list-group-item">Database backup completed successfully</li>
                        <li class="list-group-item">System update finished at 9:45 AM</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-4 mt-5">
        <p>&copy; 2025 Backend System. All Rights Reserved.</p>
    </footer>
</main>
<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>

<#--<div class="gap-3 d-flex flex-column">-->
<#--    <div class="flex-column d-flex justify-content-between align-items-start flex-lg-row px-3 gap-3">-->
<#--        <!-- Sales Overview &ndash;&gt;-->
<#--        <div class="sales-overview">-->
<#--            <h2>Sales Overview</h2>-->
<#--            <div class="overview-cards">-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Sales.png" alt="Sales Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">₹ 832</div>-->
<#--                        <div class="label">Sales</div>-->
<#--                    </div>-->

<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Revenue.png" alt="Revenue Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">₹ 18,300</div>-->
<#--                        <div class="label">Revenue</div>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Profit.png" alt="Profit Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">₹ 868</div>-->
<#--                        <div class="label">Profit</div>-->
<#--                    </div>-->

<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Cost.png" alt="Cost Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">₹ 1,200</div>-->
<#--                        <div class="label">Cost</div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--        <!--InventoryDao Summary &ndash;&gt;-->
<#--        <div class="d-flex flex-column inventory-summary">-->
<#--            <h2>Inventory Summary</h2>-->
<#--            <div class="d-flex flex-row gap-2">-->
<#--                <div class="item-summary">-->
<#--                    <img src="/static/images/Quantity.png" alt="Quantity Icon"/>-->
<#--                    <h4>868</h4>-->
<#--                    <p>Quantity in Hand</p>-->
<#--                </div>-->
<#--                <div class="item-summary">-->
<#--                    <img src="/static/images/On%20the%20way.png" alt="Quantity Icon"/>-->
<#--                    <h4>200</h4>-->
<#--                    <p>To be received</p>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="flex-column d-flex justify-content-between align-items-start flex-lg-row px-3 gap-3">-->
<#--        <!-- Sales Overview &ndash;&gt;-->
<#--        <div class="sales-overview">-->
<#--            <h2>Purchase Overview</h2>-->
<#--            <div class="overview-cards">-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Purchase.png" alt="Sales Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">832</div>-->
<#--                        <div class="label">Purchase</div>-->
<#--                    </div>-->

<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Cost.png" alt="Revenue Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">300</div>-->
<#--                        <div class="label">Cost</div>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Cancel.png" alt="Profit Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">8</div>-->
<#--                        <div class="label">Cancel</div>-->
<#--                    </div>-->

<#--                </div>-->
<#--                <div class="overview-card">-->
<#--                    <div class="icon">-->
<#--                        <img src="/static/images/Profit.png" alt="Cost Icon" class="img-fluid"/>-->
<#--                    </div>-->
<#--                    <div class="row-flex">-->
<#--                        <div class="value">200</div>-->
<#--                        <div class="label">Return</div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--        <!--InventoryDao Summary &ndash;&gt;-->
<#--        <div class="d-flex flex-column inventory-summary">-->
<#--            <h2>Product Summary</h2>-->
<#--            <div class="d-flex flex-row gap-2">-->
<#--                <div class="item-summary">-->
<#--                    <img src="/static/images/Suppliers.png" alt="Quantity Icon"/>-->
<#--                    <h4>868</h4>-->
<#--                    <p>Number of Suppliers</p>-->
<#--                </div>-->
<#--                <div class="item-summary">-->
<#--                    <img src="/static/images/Categories.png" alt="Quantity Icon"/>-->
<#--                    <h4>200</h4>-->
<#--                    <p>Number of Categories</p>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="flex-column d-flex justify-content-between align-items-start flex-lg-row px-3 gap-3">-->
<#--        <div class="chart-card">-->
<#--            <div class="chart-header">-->
<#--                <h2>Sales & Purchase</h2>-->
<#--                <button class="time-range-btn">Weekly</button>-->
<#--            </div>-->

<#--            <div class="chart-container">-->
<#--                <canvas id="myChart"></canvas>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="chart-card" style="flex: 1">-->
<#--            <div class="chart-header">-->
<#--                <h2>Order Summary</h2>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->

<#--</div>-->

<#--<script>-->
<#--    document.addEventListener("DOMContentLoaded", () => {-->
<#--        if (!sessionStorage.getItem("hasLoaded")) {-->
<#--            // Check for authentication token-->
<#--            const token = localStorage.getItem("auth_token");-->
<#--            if (token) {-->
<#--                window.location.href = "/";-->
<#--            } else {-->
<#--                window.location.href = "/login";-->
<#--            }-->

<#--            // Set user avatar if available-->
<#--            const avatarUrl = localStorage.getItem("user_avatar");-->
<#--            if (avatarUrl && avatarUrl.length > 0) {-->
<#--                const userAvatarElement = document.getElementById("user-avatar");-->
<#--                if (userAvatarElement) {-->
<#--                    userAvatarElement.src = avatarUrl;-->
<#--                }-->
<#--            }-->

<#--            sessionStorage.setItem("hasLoaded", "true");-->
<#--        }-->
<#--    });-->

<#--    <#assign defaultChartData = {-->
<#--      "labels": ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"],-->
<#--      "purchase": [200, 500, 400, 300, 450, 500, 500, 600, 450],-->
<#--      "sales": [250, 400, 450, 350, 400, 550, 500, 60, 450]-->
<#--    }>-->
<#--    <#assign chartData = chartData!defaultChartData>-->

<#--    const labels = [-->
<#--        <#list chartData.labels as label>-->
<#--        "${label}"<#if label_has_next>, </#if>-->
<#--        </#list>-->
<#--    ];-->

<#--    const purchaseData = [-->
<#--        <#list chartData.purchase as val>-->
<#--        ${val}<#if val_has_next>, </#if>-->
<#--        </#list>-->
<#--    ];-->

<#--    const salesData = [-->
<#--        <#list chartData.sales as val>-->
<#--        ${val}<#if val_has_next>, </#if>-->
<#--        </#list>-->
<#--    ];-->

<#--    const ctx = document.getElementById('myChart').getContext('2d');-->
<#--    const myChart = new Chart(ctx, {-->
<#--        type: 'bar',-->
<#--        data: {-->
<#--            labels: labels,-->
<#--            datasets: [-->
<#--                {-->
<#--                    label: 'Purchase',-->
<#--                    data: purchaseData,-->
<#--                    backgroundColor: '#6FC5C5',-->
<#--                    borderRadius: 4,-->
<#--                    barPercentage: 0.5-->
<#--                },-->
<#--                {-->
<#--                    label: 'Sales',-->
<#--                    data: salesData,-->
<#--                    backgroundColor: '#7FD081',-->
<#--                    borderRadius: 4,-->
<#--                    barPercentage: 0.5-->
<#--                }-->
<#--            ]-->
<#--        },-->
<#--        options: {-->
<#--            responsive: true,-->
<#--            maintainAspectRatio: false,-->
<#--            plugins: {-->
<#--                legend: {-->
<#--                    position: 'top',-->
<#--                    labels: {-->
<#--                        color: '#444',-->
<#--                        font: {-->
<#--                            size: 14,-->
<#--                            weight: 'bold'-->
<#--                        }-->
<#--                    }-->
<#--                },-->
<#--                tooltip: {-->
<#--                    callbacks: {-->
<#--                        label: function(context) {-->
<#--                            return context.dataset.label + ': ' + context.parsed.y.toLocaleString();-->
<#--                        }-->
<#--                    }-->
<#--                }-->
<#--            },-->
<#--            scales: {-->
<#--                x: {-->
<#--                    ticks: {-->
<#--                        color: '#666'-->
<#--                    },-->
<#--                    grid: {-->
<#--                        display: false-->
<#--                    }-->
<#--                },-->
<#--                y: {-->
<#--                    beginAtZero: true,-->
<#--                    ticks: {-->
<#--                        color: '#666',-->
<#--                        callback: function(value) {-->
<#--                            return value.toLocaleString();-->
<#--                        }-->
<#--                    },-->
<#--                    grid: {-->
<#--                        color: '#eee'-->
<#--                    }-->
<#--                }-->
<#--            }-->
<#--        }-->
<#--    });-->
<#--</script>-->

<#--    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">-->
<#--        <div class="container-fluid">-->
<#--            <a class="navbar-brand" href="#">Backend System</a>-->
<#--            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"-->
<#--                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">-->
<#--                <span class="navbar-toggler-icon"></span>-->
<#--            </button>-->
<#--            <div class="collapse navbar-collapse" id="navbarNav">-->
<#--                <ul class="navbar-nav ms-auto">-->
<#--                    <li class="nav-item">-->
<#--                        <a class="nav-link active" aria-current="page" href="#">Home</a>-->
<#--                    </li>-->
<#--                    <li class="nav-item">-->
<#--                        <a class="nav-link" href="#">Settings</a>-->
<#--                    </li>-->
<#--                    <li class="nav-item">-->
<#--                        <a class="nav-link" href="#">Logout</a>-->
<#--                    </li>-->
<#--                </ul>-->
<#--            </div>-->
<#--        </div>-->
<#--    </nav>-->