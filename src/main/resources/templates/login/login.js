// login.js

document.addEventListener("DOMContentLoaded", () => {
    // Tìm form đăng nhập dựa trên action URL
    const loginForm = document.querySelector("form[action='/auth/login']");

    if (!loginForm) {
        console.warn("Không tìm thấy form đăng nhập");
        return;
    }

    loginForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const formData = new FormData(loginForm);

        try {
            const response = await fetch(loginForm.action, {
                method: "POST",
                body: formData,
                // credentials: "include"
            });

            if (response.ok) {
                // Xử lý thành công: ví dụ chuyển hướng, hiển thị thông báo, v.v.
                console.log("Đăng nhập thành công");
                window.location.href = "/user"; // chuyển hướng sau khi đăng nhập thành công
            } else {
                // Xử lý lỗi: hiển thị thông báo lỗi
                const errorText = await response.text();
                console.error("Đăng nhập thất bại:", errorText);
                // Bạn có thể hiển thị lỗi lên UI nếu cần
                const alertDiv = document.createElement("div");
                alertDiv.className = "alert alert-danger";
                alertDiv.textContent = "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.";
                loginForm.prepend(alertDiv);
            }
        } catch (error) {
            console.error("Lỗi khi gửi form đăng nhập:", error);
        }
    });
});
