<#include "../shared/head.ftl">
<body>
<main class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="row w-100">
        <!-- Image Section -->
        <div class="col-md-6 d-none d-md-block text-center">
            <img src="/static/images/backside.png" class="img-fluid max-width-img-70" alt="Login Illustration">
        </div>

        <!-- Login Form Section -->
        <div class="col-md-6 p-4 bg-white d-flex justify-content-center align-items-center flex-column">
            <img src="/static/images/logo.png"  class="img-fluid width-[250]" alt="Logo" />
            <form action="/v1/auth/register" method="post" class="p-4 bg-white" style="min-width: 400px;">
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
            <div>
                <p class="text-center mt-3 small align-items-center">Don't have an account? <a href="/register" class="text-decoration">Sign up</a></p>
            </div>
        </div>
    </div>
</main>
<script>
    <#include "register.js">
</script>
</body>
</html>
