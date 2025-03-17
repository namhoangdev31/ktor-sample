
document.addEventListener("DOMContentLoaded", () => {
    const pathLogin = '/v1/auth/login';
    const loginForm = document.querySelector(`form[action='/v1/auth/login']`);

    if (!loginForm) {
        console.warn("Không tìm thấy form đăng nhập");
        return;
    }

    loginForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const formData = new FormData(loginForm);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        try {
            const response = await fetch(loginForm.action, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                console.log("Đăng nhập thành công");
                window.location.href = "/user";
            } else {
                const errorText = await response.json();
                console.error("Đăng nhập thất bại:", errorText.error || "Unknown error");
                alert("Đăng nhập thất bại: " + (errorText.error || "Unknown error"));
            }
        } catch (error) {
            console.error("Lỗi khi gửi form đăng nhập:", error);
        }
    });
});
