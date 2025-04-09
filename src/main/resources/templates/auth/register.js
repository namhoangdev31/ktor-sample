document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.querySelector(`form[action='/v1/auth/register']`);

    if (!registerForm) {
        console.warn("Không tìm thấy form đăng ký");
        return;
    }

    registerForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const formData = new FormData(registerForm);
        const data = {};

        // Convert FormData to a plain object
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Convert isAdmin value to boolean (assuming checkbox returns "on" when checked)
        data.isAdmin = data.isAdmin === "on";

        try {
            const response = await fetch(registerForm.action, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
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
