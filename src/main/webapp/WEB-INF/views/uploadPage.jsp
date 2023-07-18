<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .container{
            max-width: 600px;
            margin: auto;
            border-style: solid;
            border-width: 1px;
            border-color: #c7c5c5;
            box-shadow: 0px 5px 10px #888888;
            height: 90px;
        }
        .form {
            margin-left: 20px;
            margin-right: 20px;
            right: 0px;
            margin-top: 20px;
            margin-bottom: 20px;
            background-color: ;
        }
        .button{
            position: flexible;
            margin-left: auto;
            margin-right: 0px;
            right: 0px;
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-style: solid;
            border-width: 1px;
            border-color: #888888;
            box-shadow: 0px 1px 2px #888888;
        }
        .file{
            margin-right: 180px;
        }
        .mid{
            position:relative;
            height: 10em;
            margin: auto;
        }
        div.mid div {
            margin: 0;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%)
        }
    </style>
</head>
<body>
<div class="mid">
    <div class="container">
        <form class="form" action="/data" method="POST" enctype="multipart/form-data">
            <input class="file" type="file" name="file" id="file">
            <button class="button" name="button1" value="Upload">
            Upload</button>
        </form>
    </div>
</div>
</body>
</html>