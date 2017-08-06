<html>
<head>
<title>N&G's Wedding</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
 
<link href="css/login.css" rel="stylesheet" />

</head>
<body>
    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="/login" method="post">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="text" name="user_name" id="inputUserName" class="form-control" placeholder="User Name" required autofocus>
                <input type="password" name ="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
            </form><!-- /form -->
<!--             <a href="#" class="forgot-password"> -->
<!--                 Forgot the password? -->
<!--             </a> -->
        </div><!-- /card-container -->
    </div><!-- /container -->
</body>
</html>