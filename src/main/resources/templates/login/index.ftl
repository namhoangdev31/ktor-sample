<#include "head.ftl">
<body>
<main class="content">
    <div class="container text-center login">
        <#if login_error?? && login_error?has_content>
            <div class="alert alert-danger">
                ${login_error}
            </div>
        <#else>
            <div class="alert alert-success">
                Please login to continue.
            </div>
        </#if>
    </div>
    <form action="/auth/login" method="post">
        <div class="container">
            <div class="form-group">
                <label for="username">User Name:</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required/>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required/>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>

</main>
<#include "../shared/footer.ftl">
<script>
    <#include "login.js">
</script>
</body>
</html>
