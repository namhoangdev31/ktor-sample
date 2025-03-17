<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #ff416c, #ff4b2b);
            color: #fff;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Arial', sans-serif;
        }
        .container {
            text-align: center;
            padding: 40px;
            background: rgba(0, 0, 0, 0.7);
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.5);
            animation: fadeIn 2s ease-in-out;
        }
        h1 {
            font-size: 7rem;
            font-weight: bold;
            margin-bottom: 20px;
            animation: bounce 2s infinite;
        }
        p {
            font-size: 1.5rem;
            margin-bottom: 30px;
            animation: fadeIn 2s ease-in-out;
        }
        .btn-custom {
            padding: 12px 30px;
            font-size: 1.2rem;
            border-radius: 50px;
            background-color: #ff416c;
            border: none;
            transition: background 0.3s ease, transform 0.3s ease;
            animation: pulse 1.5s infinite;
        }
        .btn-custom:hover {
            background-color: #ff4b2b;
            transform: scale(1.1);
        }
        .image-container img {
            width: 200px;
            animation: float 3s ease-in-out infinite;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        @keyframes bounce {
            0%, 20%, 50%, 80%, 100% { transform: translateY(0); }
            40% { transform: translateY(-20px); }
            60% { transform: translateY(-10px); }
        }
        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }
        @keyframes float {
            0% { transform: translatey(0px); }
            50% { transform: translatey(-20px); }
            100% { transform: translatey(0px); }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="image-container mb-4">
        <img src="https://via.placeholder.com/200" alt="404 Image">
    </div>
    <h1>404</h1>
    <p>Oops! The page you're looking for doesn't exist or has been moved.</p>
    <a href="/" class="btn btn-custom">Back to Home</a>
</div>
</body>
</html>
