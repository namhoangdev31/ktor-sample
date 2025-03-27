<#include "shared/head.ftl">
<body>
<#include "shared/drawer.ftl">
<main class="content-auth">
   <#include "shared/header.ftl">
    <h2>${title}</h2>
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

            // Kiểm tra avatar và cập nhật
            const avatarUrl = localStorage.getItem("user_avatar");
            if (avatarUrl && avatarUrl.length > 0) {
                const userAvatarElement = document.getElementById("user-avatar");
                if (userAvatarElement) {
                    userAvatarElement.src = avatarUrl;
                }
            }

            // Đánh dấu đã load một lần
            sessionStorage.setItem("hasLoaded", "true");
        }
    });

</script>

</body>
</html>
