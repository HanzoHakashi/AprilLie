<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet'
        type='text/css'>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
    <title>Autorization</title>
</head>
<body>

    <header>
        <div id="user-info">
            <span id="username"></span>
            <button id="logout-button">Выйти</button>
        </div>
    </header>
    <div id="splash-screen">
        <h1>Добро пожаловать!</h1>
        <form id="login-form">
            <label for="username">Имя пользователя:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Войти</button>
        </form>
    </div>
</body>
<script>
    const user = JSON.parse(localStorage.getItem('user'));


if (user) {
    document.getElementById('splash-screen').style.display = 'none';
}

document.getElementById('login-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:2727/user/login', {
            method: 'POST',
            body: JSON.stringify({
                username,
                password
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            localStorage.setItem('user', JSON.stringify(data));
            document.getElementById('splash-screen').style.display = 'none';
        })
        .catch(error => {
            console.error('Ошибка авторизации:', error);
        });
});
</script>
</html>