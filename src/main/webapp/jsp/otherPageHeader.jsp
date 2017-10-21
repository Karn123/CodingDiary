<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>TA的主页</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
          content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Stylesheets-->
    <link rel="stylesheet" type="text/css"
          href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400%7CQuicksand:400,700">
    <link rel="stylesheet" href="css/style.css">
    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;">
        <a href="http://windows.microsoft.com/en-US/internet-explorer/"><img
                src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820"
                alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a>
    </div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>

<body>
<header class="page-header slider-menu-position">
    <!-- RD Navbar-->
    <div class="rd-navbar-wrap">
        <nav data-md-device-layout="rd-navbar-fixed"
             data-lg-device-layout="rd-navbar-static"
             data-md-stick-up-offset="50px" data-lg-stick-up-offset="1px"
             data-layout="rd-navbar-fixed" data-sm-layout="rd-navbar-fixed"
             data-md-layout="rd-navbar-fixed" data-lg-layout="rd-navbar-fixed"
             class="rd-navbar rd-navbar-hamburger">
            <div class="rd-navbar-inner">
                <div class="rd-navbar-inner-top">
                    <!-- RD Navbar Brand-->
                    <div class="rd-navbar-brand veil reveal-lg-inline-block">
                        <a href="${pageContext.request.contextPath}/jsp/index" class="brand-name"><img
                                style="margin-top: -10px;" width="164" height="29"
                                src="images/logo-164x29.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- RD Navbar Panel-->
                <div class="rd-navbar-panel">
                    <!-- RD Navbar Toggle-->
                    <button data-rd-navbar-toggle=".rd-navbar-nav-wrap"
                            class="rd-navbar-toggle">
                        <span></span>
                    </button>
                    <div class="rd-navbar-brand veil-lg">
                        <a href="${pageContext.request.contextPath}/jsp/index" class="brand-name"><img
                                style="margin-top: -7px;" width="128" height="24"
                                src="images/logo-128x24.png" alt="">
                        </a>
                    </div>
                    <!-- RD Navbar Toggle-->
                    <button data-rd-navbar-toggle=".rd-navbar-collapse-wrap"
                            class="rd-navbar-collapse veil-lg">
                        <span></span>
                    </button>
                </div>
                <div class="rd-navbar-right-side">
                    <div class="rd-navbar-nav-wrap reveal-md-inline-block">
                        <ul class="rd-navbar-nav">
                            <!-- RD Navbar Nav-->
                            <li><a href="${pageContext.request.contextPath}/jsp/index">返回首页</a>
                            </li>
                            <li id="otherPage"><a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${sessionScope.get("this_id")}">TA的主页</a>
                            </li>
                            <li id="otherBlog"><a href="${pageContext.request.contextPath}/jsp/otherBlog?this_id=${sessionScope.get("this_id")}">TA的博客</a>
                            </li>
                            <li id="otherForumpost"><a href="${pageContext.request.contextPath}/jsp/otherForumpost?this_id=${sessionScope.get("this_id")}">TA的帖子</a>
                            </li>
                            <li id="otherResource"><a href="${pageContext.request.contextPath}/jsp/otherResource?this_id=${sessionScope.get("this_id")}">TA的学习资源</a>
                            </li>
                            <li id="otherFollowers"><a href="${pageContext.request.contextPath}/jsp/otherFollower?this_id=${sessionScope.get("this_id")}">TA的粉丝</a>
                            </li>
                            <li id="otherFollowings"><a href="${pageContext.request.contextPath}/jsp/otherFollowing?this_id=${sessionScope.get("this_id")}">TA的关注</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <!-- Modern Breadcrumbs-->
    <section class="section-height-600 breadcrumb-modern rd-parallax context-dark">
        <div data-speed="0.2" data-type="media"
             data-url="images/backgrounds/background-04-1920x900.jpg"
             class="rd-parallax-layer"></div>
        <div data-speed="0" data-type="html" class="rd-parallax-layer">
            <div class="bg-overlay-chathams-blue">
                <div class="shell section-top-34 section-bottom-34 section-md-top-175 section-md-bottom-75 section-lg-top-158 section-lg-bottom-125 section-md-tablet-75">
                    <div class="offset-top-30">
                        <div class="cell-sm-10 cell-lg-8">
                            <img src="${sessionScope.get("this_portrait")}" width="125" id="headPortrait" name="headPortrait"
                                 height="125" alt="" style="cursor:pointer;width:125px;height:125px"
                                 class="img-circle img-responsive center-block">
                        </div>
                    </div>
                    <div class="offset-top-35">
                        <ul class="list-inline list-marke d-type-mid list-marked-type-2-dot-1 list-silver-chalice list-marked-silver-chalice"
                            style="font-family: 微软雅黑; font-size: 25px; letter-spacing: 2px; text-align:center">
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${sessionScope.get("this_id")}"
                                                           target="_parent">主页</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/otherBlog?this_id=${sessionScope.get("this_id")}"
                                                           target="_parent">博客</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/otherForumpost?this_id=${sessionScope.get("this_id")}"
                                                           target="_parent">论坛帖</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/otherResource?this_id=${sessionScope.get("this_id")}"
                                                           target="_parent">学习资源</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
</header>
</body>
</html>
<script type="text/javascript">
    var websocket = null;
    var linked = false;//判断websocket是否已连接
    var my_portrait = "${sessionScope.get("userHeadPortrait")}";

    jQuery(document).ready(function($) {

        /*
         * 目前私信功能的实现流程是：
         * 1、主用户进入某个联系人的otherPage，点击私信按钮打开对话框并建立连接到服务器
         * 2、服务器将主用户ID和对应客户端添加到webSocketSet（Map）中
         * 3、ajax从数据库中取出所有未读消息并显示（再websocket的onopen回调函数里）
         * 4、主用户发送一条消息M
         * 5、服务器接收消息M，并检查webSocketSet中是否有对应联系人的客户端
         *    若有，则转发M给对应联系人，显示在对应联系人对话框中，若无，则不转发
         * 6、服务器发送一条消息给主用户，告知主用户‘5’中消息是否转发给了对应联系人
         * 7、前台保存消息M到数据库，并根据上面这个返回来设置消息的TFid
         */
        /* websocket 主功能实现部分 */
        //“私信”按钮点击事件
        $('#chat-btn').click(function(){
            //显示私信对话框
            $("#mask").fadeIn(100);
            $("#dialog").fadeIn(100);
            //linked为false,代表未连接，则新建websocket连接到服务器
            if(linked == false){
                //判断当前浏览器是否支持WebSocket
                if ('WebSocket' in window) {
                    alert("ws://localhost:8080/WebSocket");
                    websocket = new WebSocket("ws://localhost:8080/WebSocket");
                    linked = true;
                    alert(websocket);
                }
                else {
                    alert('当前浏览器 Not support websocket')
                }

                //连接成功建立的回调方法
                websocket.onopen = function () {
                    alert("WebSocket连接成功");
                    //连接建立成功后获取后台的未读信息
                    //getUnreadMsg();
                };

                //连接发生错误的回调方法
                websocket.onerror = function () {
                    alert("WebSocket连接发生错误");
                };


                //接收到消息的回调方法
                websocket.onmessage = function (event) {
                    var msg = event.data;
                    alert("收到消息：" + event.data);
                    if (msg=="msg_has_been_read"){
                        alert("发送的消息已读");
                        saveMsg(true);
                    }else if (msg=="msg_has_not_been_read"){
                        alert("发送的消息未读");
                        saveMsg(false);
                    }else {
                        alert("收到新消息");
                        showMsgReceived(msg);
                    }

                };
                //连接关闭的回调方法
                websocket.onclose = function () {
                    alert("WebSocket连接关闭");
                };
                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function () {
                    closeWebSocket();
                };

                if(websocket != null)
                    getUnreadMsg();
            }
        });
        /* websocket 主功能实现部分结束 */


        /* 实现对话框可拖拽 */
        var mx = 0,my = 0;      //鼠标x、y轴坐标（相对于left，top）
        var dx = 0,dy = 0;      //对话框坐标（同上）
        var isDraging = false;      //不可拖动

        //鼠标按下
        $("#move_part").mousedown(function(e){
            e = e || window.event;
            mx = e.pageX;     //点击时鼠标X坐标
            my = e.pageY;     //点击时鼠标Y坐标
            dx = $("#dialog").offset().left;
            dy = $("#dialog").offset().top;
            isDraging = true;      //标记对话框可拖动
        });

        //鼠标移动更新窗口位置
        $(document).mousemove(function(e){
            var e = e || window.event;
            var x = e.pageX;      //移动时鼠标X坐标
            var y = e.pageY;      //移动时鼠标Y坐标
            if(isDraging){        //判断对话框能否拖动
                var moveX = dx + x - mx;      //移动后对话框新的left值
                var moveY = dy + y - my;      //移动后对话框新的top值
                //设置拖动范围
                var pageW = $(window).width();
                var pageH = $(window).height();
                var dialogW = $("#dialog").width();
                var dialogH = $("#dialog").height();
                var maxX = pageW - dialogW;       //X轴可拖动最大值
                var maxY = pageH - dialogH;       //Y轴可拖动最大值
                moveX = Math.min(Math.max(0,moveX),maxX);     //X轴可拖动范围
                moveY = Math.min(Math.max(0,moveY),maxY);     //Y轴可拖动范围
                //重新设置对话框的left、top
                $("#dialog").css({"left":moveX + 'px',"top":moveY + 'px'});
            }
        });

        //鼠标离开
        $("#move_part").mouseup(function(){
            isDraging = false;
        });

        //窗口大小改变时，对话框始终居中
        window.onresize = function(){
            var bodyW = $(window).width();
            var bodyH = $(window).height();
            var elW = $("#dialog").width();
            var elH = $("#dialog").height();
            $("#dialog").css({"left":(bodyW-elW)/2 + 'px',"top":(bodyH-elH)/2 + 'px'});
        };
        /* 实现对话框可拖拽结束 */

        //关闭对话框（断开websocket连接）
        $('#close').click(function(){
            if (websocket != null)
                closeWebSocket();
            $("#mask").fadeOut(100);
            $("#dialog").fadeOut(100);
        });

        //隐藏对话框（保持连接状态）
        $('#hide').click(function(){
            $("#mask").fadeOut(100);
            $("#dialog").fadeOut(100);
        });

    });

    /* websocket调用的到的函数 */
    //获取未读信息
    function getUnreadMsg(){
        alert("开始获取未读信息");
        $.ajax({
            type: "post",//请求方式
            url: "getUnreadMsg",
            timeout: 800000,//超时时间：800秒
            dataType: "json",//设置返回数据的格式
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                //    alert(data.retCode);
                if (data.retCode == "0000") {
                    if (data.size > 1){
                        var msgs = new Array();
                        msgs = data.unreadMsg.split("&_add_msg_&");
                        for (var i=0; i<msgs.length; i++){
                            showMsgReceived(msgs[i]);
                        }
                    }else{
                        showMsgReceived(data.unreadMsg);
                    }
                }
            },
            //请求出错的处理
            error: function () {
                //alert("请求出错");
            }
        });
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
        document.getElementById("msg-content").innerHTML = "";
        linked = false;
    }
    //发送消息
    function send() {
        msg = document.getElementById('textarea').value;
        if(msg.length != 0){
            alert(msg);
            websocket.send(msg);
            var msgboxHeight = 50;
            document.getElementById('msg-content').innerHTML += '<div class="msg-box"> <div class="user-logo-right"><img src="${myinfo.headPortrait}"/></div><div class="send-right">' + msg + '<span class="arrow-right"></span></div></div><br style="clear:both; display:none;"/>';
            document.getElementById('textarea').value = "";

        }
        else alert("请先输入信息");
    }
    //显示接收到的消息
    function showMsgReceived(msg) {
        document.getElementById('msg-content').innerHTML += '<div class="msg-box"><div class="user-logo-left"><img src="' + my_portrait + '"/></div><div class="send-left">'+msg+'<span class="arrow-left"></span></div></div><br style="clear:both; display:none;"/>';
    }
    //保存消息到数据库
    function saveMsg(isRead) {
        alert("开始保存消息");
        if (msg != null){
            $.ajax({
                type: "post",//请求方式
                url: "saveChatMsg",
                timeout: 80000,//超时时间：8秒
                dataType: "json",//设置返回数据的格式
                data:{
                    "msg":msg,
                    "isRead":isRead
                },
                success: function () {
                    alert("消息成功储存到数据库");
                },
                //请求出错的处理
                error: function () {
                    alert("消息无法储存到数据库");
                }
            })
        }
    }
    //敲回车发送消息
    function keydown_enter() {
        var e = window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode==13 && linked==true) {
            send();
        }
    }
    /* websocket调用的到的函数结束 */
</script>
