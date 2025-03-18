<#include "shared/head.ftl">
<body>
<#include "shared/drawer.ftl">
<main class="content-auth">
   <#include "shared/header.ftl">
    <h2>${title}</h2>
</main>
<#include "shared/footer.ftl">
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const token = localStorage.getItem("auth_token");
        if (token) {
            window.location.href = "/";
        } else {
            window.location.href = "/login";
        }
    });
</script>

</body>
</html>
