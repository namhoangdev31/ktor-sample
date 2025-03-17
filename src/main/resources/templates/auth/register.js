document.addEventListener("DOMContentLoaded", () => {
    const pathRegister = '/v1/auth/register';
    const registerForm = document.querySelector(`form[action='/v1/auth/register']`);

    if (!registerForm) {
        console.warn("Không tìm thấy form đăng ký");
        return;
    }

    registerForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const formData = new FormData(registerForm);

        try {
            const response = await fetch(registerForm.action, {
                method: "POST",
                body: formData,
            });

            if (response.ok) {
                console.log("Đăng ký thành công");
                window.location.href = "/user";
            } else {
                const errorResponse = await response.json();
                console.error("Đăng ký thất bại:", errorResponse.error || "Unknown error");
                alert("Đăng ký thất bại: " + (errorResponse.error || "Unknown error"));
            }
        } catch (error) {
            console.error("Lỗi khi gửi form đăng ký:", error);
        }
    });
});
