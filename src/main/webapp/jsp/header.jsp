<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="cst.Constants" %>
<%@ page import="oracle.jdbc.driver.Const" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <!-- Site Title-->
    <title>header</title>
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
<%
    Object userid = session.getAttribute(Constants.USER_Id);
    Object userHeadPortrait = session.getAttribute(Constants.USER_HEADPORTRAIT);
    int loginstate;
    if (userid == null) {
        loginstate = 0;
    } else {
        loginstate = 1;
        pageContext.setAttribute("userId", userid);
    }
    pageContext.setAttribute("loginstate", loginstate);
    pageContext.setAttribute("userImageUrl",userHeadPortrait);

%>
<script type="text/javascript">
    function detemineloginstate() {
        var login = "<div class='rd-navbar-collapse-wrap reveal-md-inline-block'>";
        login += "<ul class='list-inline list-inline-0 list-primary'>";
        login += "<li class='text-center label offset-left text-spacing-20'><a href='userLogIn' class='icon icon-xxs fa fa-user text-white'> 登录/注册</a>";
        login += "</li></ul></div>";
        //var havelogin = "<div class='rd-navbar-collapse-wrap reveal-md-inline-block'>";
        //havelogin += "<ul class='list-inline list-inline-0 list-primary'>";
        //havelogin += " <li class='text-center'><a href='#' class='icon icon-xxs fa fa-edit text-white'></a></li>";
        //havelogin += "<li class='text-center'><a href='#' class='icon icon-xxs fa fa-bell-o text-white'></a></li></ul></div> ";
        var havelogin = "<div class='rd-navbar-collapse-wrap reveal-md-inline-block'>";
        havelogin += "<a href='${pageContext.request.contextPath}/jsp/myPage'><img src='${userImageUrl}' alt='' width='40' height='40' class='img-circle box-comment-img'></a><a target='_self' href='userLogOut'style='color:white;font-size:15px;font-family: 微软雅黑'>&nbsp;注销</a></div> ";
        var loginstate =${loginstate};
        if (loginstate == "0") {
            $("#loginState").append($(login));
        } else if (loginstate == "1") {
            $("#loginState").append($(havelogin));
        }
    }

</script>
<body onload="detemineloginstate();">
<header class="page-header slider-menu-position"> <!-- RD Navbar-->
    <div class="rd-navbar-wrap">
        <nav data-md-device-layout="rd-navbar-fixed"
             data-lg-device-layout="rd-navbar-static"
             data-md-stick-up-offset="50px" data-lg-stick-up-offset="1px"
             class="rd-navbar" data-layout="rd-navbar-fixed"
             data-sm-layout="rd-navbar-fixed"
             data-md-layout="rd-navbar-fullwidth"
             data-lg-layout="rd-navbar-static">
            <div class="rd-navbar-inner">
                <!-- RD Navbar Panel-->
                <div class="rd-navbar-panel">
                    <!-- RD Navbar Toggle-->
                    <button data-rd-navbar-toggle=".rd-navbar-nav-wrap"
                            class="rd-navbar-toggle">
                        <span></span>
                    </button>
                    <!-- RD Navbar Brand-->
                    <div class="rd-navbar-brand veil reveal-md-block">
                        <a href="${pageContext.request.contextPath}/jsp/index" title="返回首页" class="brand-name"><img
                                style='margin-top: -10px;' width='164' height='29'
                                src='images/logo-164x29.png' alt='返回首页'/>
                        </a>
                    </div>
                    <div class="rd-navbar-brand veil-md reveal-tablet-md-inline-block">
                        <a href="${pageContext.request.contextPath}/jsp/index" class="brand-name"><img
                                style='margin-top: -7px;' width='128' height='24'
                                src='images/logo-128x24.png' alt=''/><a/>
                    </div>
                    <!-- RD Navbar Toggle-->
                    <a><button data-rd-navbar-toggle=".rd-navbar-collapse-wrap"
                            class="rd-navbar-collapse">
                        <span></span>
                    </button>
                    </a>
                </div>
                <div id="loginState" class="rd-navbar-right-side">
                    <div class="rd-navbar-nav-wrap reveal-md-inline-block">
                        <ul id="indexSearch" class="rd-navbar-nav">
                            <!-- RD Navbar Nav-->
                            <li id="index"><a href="${pageContext.request.contextPath}/jsp/index">首页</a>
                            </li>
                            <li id="blog"><a href="${pageContext.request.contextPath}/jsp/blogIndex" style="font-family: 微软雅黑">博客区</a>
                            </li>
                            <li id="post"><a href="${pageContext.request.contextPath}/jsp/bbsIndex"
                                             style="font-family: 微软雅黑">论坛讨论区</a>
                            </li>
                            <li id="data"><a
                                    href="${pageContext.request.contextPath}/jsp/resourceIndex"
                                    style="font-family: 微软雅黑">学习资源区</a>
                            </li>
                            <li id='search'><a href='${pageContext.request.contextPath}/jsp/search' style='font-family: 微软雅黑'>搜索</a> </li>
                            <li><a href="#" style="font-family: 微软雅黑">发表</a>
                                <ul class="rd-navbar-dropdown">
                                    <li><a href="${pageContext.request.contextPath}/jsp/postBlog">发表博客</a></li>
                                    <li><a href="${pageContext.request.contextPath}/jsp/postForumpost">发表帖子</a></li>
                                    <li><a href="${pageContext.request.contextPath}/jsp/uploadFile">上传文件</a></li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <!-- Modern Breadcrumbs-->
    <section id="headerbackground"
             class="section-height-800 breadcrumb-modern rd-parallax context-dark">
        <div data-speed="0.2" data-type="media"
             data-url="images/backgrounds/background-11-1920x900.jpg"
             class="rd-parallax-layer"></div>
        <div data-speed="0" data-type="html" class="rd-parallax-layer">
            <div class="bg-overlay-chathams-blue">
                <div class="shell section-top-34 section-bottom-34 section-md-top-175 section-md-bottom-75 section-lg-top-158 section-lg-bottom-125 section-md-tablet-75">
                    <h1 id="theme" class="h1"></h1></div>
            </div>
        </div>
    </section>
</header>
</body>
</html>
