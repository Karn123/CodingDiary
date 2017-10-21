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
    <title>我的主页</title>
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
                            <li id="myPage"><a href="${pageContext.request.contextPath}/jsp/myPage">我的主页</a>
                            </li>
                            <li id="myBlog"><a href="${pageContext.request.contextPath}/jsp/myBlog">我的博客</a>
                            </li>
                            <li id="myForumpost"><a href="${pageContext.request.contextPath}/jsp/myForumpost">我的帖子</a>
                            </li>
                            <li id="myResource"><a href="${pageContext.request.contextPath}/jsp/myResource">我的学习资源</a>
                            </li>
                            <li id="myNotifiMsg"><a href="${pageContext.request.contextPath}/jsp/myNotifiMsg">我的通知消息</a>
                            </li>
                            <li id="followers"><a href="skipToMyPageAction_skipToFollowers">我的粉丝</a>
                            </li>
                            <li id="myFollowers"><a href="skipToMyPageAction_skipToMyFollowers">关注的人</a>
                            </li>
                            <li id="myCollection"><a
                                    href="skipToMyCollectAction_skip?type=collect_userId&type_value=-1">我的收藏</a>
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
                            <img src="${userinfo.headPortrait}" width="125" id="headPortrait" name="headPortrait"
                                 height="125" alt="" style="cursor:pointer;width:125px;height:125px"
                                 class="img-circle img-responsive center-block">
                        </div>
                    </div>
                    <div class="offset-top-35">
                        <ul class="list-inline list-marked-type-mid list-marked-type-2-dot-1 list-silver-chalice list-marked-silver-chalice"
                            style="font-family: 微软雅黑; font-size: 25px; letter-spacing: 2px; text-align:center">
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myPage"
                                                           target="_parent">主页</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myBlog"
                                                           target="_parent">博客</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myForumpost"
                                                           target="_parent">论坛帖</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myResource"
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
