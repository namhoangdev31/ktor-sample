<#include "../shared/head.ftl">
<body>
<#include "../shared/drawer.ftl">
<main class="content">
    <#include "../shared/header.ftl">
    <h2>${title}</h2>
    <ul>
        <#if data?? && data.items??>
            <#list data.items as item>
                <li>
                    <a href="/user/detail?id=${item.id}&name=${item.name}">${item.name}</a>
                </li>
            </#list>
        <#else>
            <li>No users found.</li>
        </#if>
    </ul>
</main>
<#include "../shared/footer.ftl">
</body>
</html>
