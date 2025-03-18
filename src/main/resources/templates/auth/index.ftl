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
            <form action="/v1/auth/login" method="post" class="">
                <h4 class="text-center mb-[20px] fw-semibold fs-5">Log in to your account</h4>
                <h2 class="text-center fw-lighter fs-6">Welcome back! Please enter your details.</h2>
                <div class="mb-3">
                    <label for="username" class="form-label">Email</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required/>
                </div>
                <div class="justify-content-between flex-row d-flex w-100 row-gap-md-4">
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="isAdmin" name="isAdmin">
                        <label class="form-check-label" for="isAdmin">Remember for 30 days</label>
                    </div>
                    <a href="/register" class="text-decoration-none">Forgot password</a>
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
                <div class="d-grid mt-4" data-type="standard">
                    <button class="google-btn">
                        <img src="https://img.icons8.com/color/48/000000/google-logo.png" alt="Google Logo">
                        <span>Continue with Google</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</main>
<#include "../shared/footer.ftl">
<script>
    <#include "login.js">
</script>
</body>
</html>
