<%--
  Created by IntelliJ IDEA.
  User: 86761
  Date: 2017/8/9
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <h1>学生认证</h1>
                </div>
                <div class="range range-xs-center offset-top-44">
                    <div class="cell-md-10 cell-lg-8">
                        <!-- RD Mailform-->
                        <form class="rd-mailform">
                            <div class="range range-xs-center">
                                <div class="cell-sm-6">
                                    <div class="form-group">
                                        <label for="mail-addr" class="form-label">输入你的邮箱</label>
                                        <input id="mail-addr" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="cell-sm-6 offset-top-15 offset-sm-top-0">
                                    <div class="form-group">
                                        <label for="verification" class="form-label">输入验证码</label>
                                        <input id="verification" type="text"  class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div id="file_error" style="color:red"></div>
                            <div class="offset-top-35">
                                <button type="submit" class="btn btn-primary" onclick="sendFunc();return false;">发送验证码
                                </button>
                                <button type="submit" class="btn btn-primary" onclick="verificateFunc();return false;">认证
                                </button>
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
    function sendFunc() {
        var mail_addr=$("#mail-addr").val();
        if(mail_addr==""){
            $("#file_error").empty().append("邮箱不能为空~");
        }
        else if(mail_addr.toString().substring(mail_addr.length-6)!="edu.cn"){
            $("#file_error").empty().append("请输入正确的学生邮箱（以edu.cn结尾）");
        }
        else{
            $.ajax({
                type: "post",//请求方式
                url: "userinfo/sendVerification",
                timeout: 80000,//超时时间：8秒
                data: {
                    "mail_addr": mail_addr
                },
                //请求成功后的回调函数 data为json格式
                success: function (data) {
                    y = data.verification;
                    alert("已发送");
                },
                error: function (data) {
                    alert(data);
                }
            });
        }
    }

    function verificateFunc() {
        var verification = $("#verification").val();
        if (verification != y) {
            $("#file_error").empty().append("验证码错误");
        }
        else{
            $.ajax({
                type: "post",//请求方式
                url: "userinfo/certified",
                timeout: 8000,//超时时间：8秒
                data: {
                },
                //请求成功后的回调函数 data为json格式
                success: function (data) {
                    if(data.msg=="认证成功") {
                        alert("认证成功！");
                        window.location.href = "myPage";
                    }
                },
                error: function (data) {
                    alert(data);
                }
            });
        }
    }
</script>
</body>
</html>