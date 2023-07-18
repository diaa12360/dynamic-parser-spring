<!DOCTYPE html>
<html>
<head>
    <title>Read-only Text Field Example</title>
    <style>
    html, body {
      height: 100%;
      margin: 0;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .container {
      text-align: center;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="text"] {
      width: 200px;
      padding: 5px;
      font-size: 16px;
      border: 1px solid #ccc;
    }

    #result {
      margin-top: 10px;
      font-size: 18px;
    }
  </style>
</head>
<body>
<div class="container">
    <span for="textfield">Text Field:</span>
    <input type="text" id="textfield" name="" value="${requestScope.value}" readonly>
</div>
   <form action="utility" method="POST">
        <button class="button" name="button" value="BackFromResultPage">
            BacK
        </button>
   </form>
</body>
</html>