<#include "head.ftl">
<body>
<main class="d-flex justify-content-center align-items-center min-vh-100">
    <form action="/v1/auth/register" method="post" class="p-4 border rounded shadow-sm bg-white" style="min-width: 400px;">
        <h4 class="text-center mb-4">Register User</h4>
        <div class="mb-3">
            <label for="username" class="form-label">User Name:</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required/>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required/>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" id="email" name="email" class="form-control" placeholder="Enter your email" required/>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="isAdmin" name="isAdmin">
            <label class="form-check-label" for="isAdmin">Admin</label>
        </div>
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
</main>
<#include "../shared/footer.ftl">
<script>
    <#include "register.js">
</script>
</body>
</html>
