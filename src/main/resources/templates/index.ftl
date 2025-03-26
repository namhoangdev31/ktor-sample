<#include "shared/head.ftl">
<body>
<#include "shared/drawer.ftl">
<main class="content-auth">
   <#include "shared/header.ftl">
    <h2>${title}</h2>
</main>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const token = localStorage.getItem("auth_token");
        if (token) {
            window.location.href = "/";
        } else {
            window.location.href = "/login";
        }
        const avatarUrl = localStorage.getItem("user_avatar");
        if (avatarUrl && avatarUrl.length > 0) {
            document.getElementById("user-avatar").src = avatarUrl;
        }
    });
</script>

</body>
</html>
