<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .chatbox{
            /*display: none;*/
        }
        .messages{
            background-color: cadetblue;
            width: 500px;
            padding: 15px;
        }
        .messages .msg{
            background-color: aliceblue;
            border-radius: 5px;
            margin-bottom: 5px;
            overflow: hidden;
        }
        .messages .msg .from{
            background-color: antiquewhite;
            line-height: 30px;
            text-align: center;
        }
        .messages .msg .text {
            padding: 10px;
        }
        textarea.msg{
            width: 530px;
            padding: 15px;
            resize: none;
            border: none;
            box-shadow: 2px 2px 5px 0 inset;
        }

    </style>
</head>
<body>
<h1>ChatBox</h1>
<div class="start">
    <input type="text" class="username" placeholder="Enter you name...">
    <button id="start"> start</button>
</div>

<div class="chatbox">
    <div class="messages">
        <div class="msg">
            <div class="from"> Vasia </div>
            <div class="text"> Hello </div>
        </div>

    </div>
    <textarea class="msg">

        </textarea>
</div>

</body>
</html>