<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fintech Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f5f5f5;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            background-color: #003366;
            color: #fff;
            overflow-y: auto;
        }
        .sidebar::-webkit-scrollbar {
            width: 2px;
        }
        .sidebar::-webkit-scrollbar-track {
            background: #002244;
        }
        .sidebar::-webkit-scrollbar-thumb {
            background-color: #00509e;
            border-radius: 3px;
        }
        .sidebar a {
            color: #fff;
            padding: 15px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #00509e;
        }
        .content {
            margin-left: 250px;
            /*padding: 20px;*/
        }
        .navbar {
            background-color: #003366;
        }
        .navbar-brand, .navbar-nav .nav-link {
            color: #fff;
        }
        .card {
            border-radius: 15px;
        }
        .promotion img {
            border-radius: 10px;
        }
        .footer {
            background-color: #003366;
            color: #fff;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
