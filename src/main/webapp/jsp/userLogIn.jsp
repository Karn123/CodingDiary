<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <title>登录</title>
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
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-90 section-md-bottom-110">
        <!-- Log In-->
        <section>
            <div class="shell">
                <div>
                    <h1>登 录</h1>
                </div>
                <div class="range range-xs-center offset-top-44">
                    <div class="cell-md-10 cell-lg-8">
                        <!-- RD Mailform-->
                        <form class="rd-mailform">
                            <div class="range range-xs-center">
                                <div class="cell-sm-6">
                                    <div class="form-group">
                                        <label for="login-username" class="form-label">用户名/账号</label>
                                        <input id="login-username" type="text" name="username" class="form-control">
                                    </div>
                                </div>
                                <div class="cell-sm-6 offset-top-15 offset-sm-top-0">
                                    <div class="form-group">
                                        <label for="login-password" class="form-label">密码</label>
                                        <input id="login-password" type="password" name="password" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div id="file_error" style="color:red"></div>
                            <div class="offset-top-35">
                                <button type="submit" class="btn btn-primary" onclick="logInFunc();return false;">登录
                                </button>
                                <a href="register" class="btn btn-primary">注册</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <!-- Page Footer-->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Global Mailform Output-->
    <div id="form-output-global" class="snackbars"></div>
    <!-- PhotoSwipe Gallery-->
    <div tabindex="-1" role="dialog" aria-hidden="true" class="pswp">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                <div class="pswp__top-bar">
                    <div class="pswp__counter"></div>
                    <button title="Close (Esc)" class="pswp__button pswp__button--close"></button>
                    <button title="Share" class="pswp__button pswp__button--share"></button>
                    <button title="Toggle fullscreen" class="pswp__button pswp__button--fs"></button>
                    <button title="Zoom in/out" class="pswp__button pswp__button--zoom"></button>
                    <div class="pswp__preloader">
                        <div class="pswp__preloader__icn">
                            <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                    <div class="pswp__share-tooltip"></div>
                </div>
                <button title="Previous (arrow left)" class="pswp__button pswp__button--arrow--left"></button>
                <button title="Next (arrow right)" class="pswp__button pswp__button--arrow--right"></button>
                <div class="pswp__caption">
                    <div class="pswp__caption__cent"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Java script-->
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>

<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
    function logInFunc() {
        var login_account = $("#login-username").val();
        var login_password = $("#login-password").val();
        if (login_account == "") {
            $("#file_error").empty().append("账号不能为空哦0.0");
            return;
        }
        if (login_password == "") {
            $("#file_error").empty().append("密码不能为空哦0.0");
            return;
        }
        $.ajax({
            type: "post",//请求方式
            url: "user/logIn",
            timeout: 8000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "login_account": login_account,
                "login_password": login_password
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.returnCode == "0") {
                    alert("账号或密码错误");
                }
                else if (data.returnCode == "-1") {
                    alert("没有该用户");
                }
                else {
                    alert("登录成功！");
                    //alert(data.returnCode);
                    var prevLink = document.referrer;
                    if($.trim(prevLink)==''){
                        prevLink = '${pageContext.request.contextPath}/jsp/index';
                    }else{
//                        if(prevLink.indexOf('www.example.com')==-1){    //来自其它站点
//                            location.href = 'www.example.com/index.html';
//                        }
                        if(prevLink.indexOf('register')!=-1 || prevLink.indexOf('userLogIn') != -1 || prevLink.indexOf('userLogOut') != -1){      //来自注册/登录页面
                            prevLink = '${pageContext.request.contextPath}/jsp/index';
                        }
                        location.href = prevLink;
                    }
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            }
        });
    }
</script>
</body>
</html>