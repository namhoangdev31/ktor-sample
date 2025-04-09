<#include "../shared/head.ftl">
<body>
<#include "../shared/drawer.ftl">
<main class="content">
    <#include "../shared/header.ftl">
    <h2>User Detail</h2>
    <p>User ID: ${userId}</p>
    <h2>User Detail: ${userName ! "Unknown User"}</h2>
</main>
<#include "../shared/footer.ftl">
</body>
</html>
