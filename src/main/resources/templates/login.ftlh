<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>Autorization</title>
</head>

<body>

<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand">Microgram</a>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <span id="user-info" class="text-white">
                     <span id="usrname"></span>
                     <button id="logout-button" class="btn btn-outline-light">Выход</button>
                 </span>
        </div>
    </nav>
</header>
<div id="splash-screen">
    <h1>Добро пожаловать!</h1>
    <form id="login-form">
        <label for="email">Имя пользователя:</label>
        <input type="text" id="email" name="email" required>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
        <button type="submit">Войти</button>
    </form>
</div>
<p id="auth-message" >Вы успешно авторизовались!</p>
</body>
<script>
const splashScreen = document.getElementById('splash-screen');
const userInfo = document.getElementById('user-info');
const authMessage = document.getElementById('auth-message');
const loginForm = document.getElementById('login-form');
const logoutButton = document.getElementById('logout-button');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const usernameDisplay = document.getElementById('usrname');


const user = JSON.parse(localStorage.getItem('user'));
if (user) {
  splashScreen.style.display = 'none';
  userInfo.style.display = 'flex';
  usernameDisplay.textContent = user.email;
  authMessage.style.display = 'flex';
} else {
  splashScreen.style.display = 'flex';
  userInfo.style.display = 'none';
  authMessage.style.display = 'none';
}


loginForm.addEventListener('submit', function (event) {
  event.preventDefault();
  const formData = new FormData(loginForm);
  const data = Object.fromEntries(formData);

  fetch('http://localhost:2727/user/login', {
    method: 'POST',
    body: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
    .then(response => {
      if (response.status === 200) {
        return response.json();
      } else {
        throw new Error('Неверный логин или пароль');
        alert('неверный логин и пароль')
      }
    })
    .then(data => {
      localStorage.setItem('user', JSON.stringify(data));
      splashScreen.style.display = 'none';
      userInfo.style.display = 'flex';
      usernameDisplay.textContent = data.email;
      authMessage.style.display = 'flex';
    })
    .catch(error => {
      console.error('Ошибка авторизации:', error);
      alert(error.message);
      splashScreen.style.display = 'flex';
    });
});


logoutButton.addEventListener('click', function() {
  localStorage.removeItem('user');
  userInfo.style.display = 'none';
  splashScreen.style.display = 'flex';
  emailInput.value = '';
  passwordInput.value = '';
  authMessage.style.display = 'none';
});

</script>
</html>