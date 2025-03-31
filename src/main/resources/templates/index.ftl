<#include "shared/head.ftl">
<body>
<#include "shared/drawer.ftl">
<main class="content-auth">
    <#include "shared/header.ftl">
    <div class="flex-column d-flex justify-content-between align-items-center flex-sm-row">
        <!-- Sales Overview -->
        <div class="sales-overview col">
            <h2>Sales Overview</h2>
            <div class="overview-cards">
                <div class="overview-card">
                    <div class="icon">
                        <img src="/static/images/Sales.png" alt="Sales Icon" class="img-fluid"/>
                    </div>
                    <div class="row-flex">
                        <div class="value">₹ 832</div>
                        <div class="label">Sales</div>
                    </div>

                </div>
                <div class="overview-card">
                    <div class="icon">
                        <img src="/static/images/Revenue.png" alt="Revenue Icon" class="img-fluid"/>
                    </div>
                    <div class="row-flex">
                        <div class="value">₹ 18,300</div>
                        <div class="label">Revenue</div>
                    </div>
                </div>
                <div class="overview-card">
                    <div class="icon">
                        <img src="/static/images/Profit.png" alt="Profit Icon" class="img-fluid"/>
                    </div>
                    <div class="row-flex">
                        <div class="value">₹ 868</div>
                        <div class="label">Profit</div>
                    </div>

                </div>
                <div class="overview-card">
                    <div class="icon">
                        <img src="/static/images/Cost.png" alt="Cost Icon" class="img-fluid"/>
                    </div>
                    <div class="row-flex">
                        <div class="value">₹ 1,200</div>
                        <div class="label">Cost</div>
                    </div>
                </div>
            </div>
        </div>
        <!--Inventory Summary -->
        <div class="d-flex align-items-center flex-column inventory-summary">
            <h2>Inventory Summary</h2>
            <div class="d-flex flex-row gap-2">
                <div class="item-summary">
                    <img src="/static/images/Quantity.png" alt="Quantity Icon"/>
                    <h4>868</h4>
                    <p>Quantity in Hand</p>
                </div>
                <div class="item-summary">
                    <img src="/static/images/On%20the%20way.png" alt="Quantity Icon"/>
                    <h4>200</h4>
                    <p>To be received</p>
                </div>
            </div>
        </div>
    </div>
</main>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        // Kiểm tra xem đã kiểm tra lần nào chưa
        if (!sessionStorage.getItem("hasLoaded")) {
            // Kiểm tra token
            const token = localStorage.getItem("auth_token");
            if (token) {
                window.location.href = "/";
            } else {
                window.location.href = "/login";
            }

            const avatarUrl = localStorage.getItem("user_avatar");
            if (avatarUrl && avatarUrl.length > 0) {
                const userAvatarElement = document.getElementById("user-avatar");
                if (userAvatarElement) {
                    userAvatarElement.src = avatarUrl;
                }
            }

            sessionStorage.setItem("hasLoaded", "true");
        }
    });
</script>
</body>
</html>
