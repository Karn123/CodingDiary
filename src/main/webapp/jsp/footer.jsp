<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <!-- Site Title-->
    <title>footer</title>
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
<footer class="page-footer bg-gray-lighter section-75 section-md-top-103 section-md-bottom-55 text-md-left">
    <div class="shell">
        <div class="range range-xs-center offset-top-20">
            <div class="cell-sm-8 cell-md-4 offset-top-44 offset-md-top-50 range range-xs-center ">
                <a href="${pageContext.request.contextPath}/jsp/index" title="返回首页"><img src="images/logo-dark-164x29.png" alt="返回首页" width="164" height="29">
                </a>
            </div>

            <div class="cell-sm-8 cell-md-4 offset-top-44 offset-md-top-0">
                <div>
                    <p class="text-bold font-accent text-spacing-50 text-mine-shaft">关于我们</p>
                </div>
                <div class="offset-top-10 offset-md-top-20">
                    <p class="text-gray-light" style="line-height: 35px">
                        “CodingDiary”,是一个主要面向高校计算机方向学生的，集分享知识、学习互动、交流心得为一体的综合平台。构建以校园为单位的“校园博客圈”。</p>
                </div>
            </div>
            <div class="cell-sm-8 cell-md-4 offset-top-44 offset-md-top-0">
                <div>
                    <p class="text-uppercase text-bold font-accent text-spacing-50 text-mine-shaft">联系我们</p>
                </div>
                <div class="reveal-inline-block offset-top-10 offset-md-top-20">
                    <ul class="text-left">
                        <li class=" text-gray-light"
                            style="line-height:35px; font-size: 16px"><span
                                class="icon icon-sm icon-primary material-icons-ico material-icons-home"></span>
                            上海市 普陀区 华东师范大学 第五宿舍420室
                        </li>
                        <li class=" text-gray-light"
                            style="line-height:35px; font-size: 16px"><span
                                class="offset-top-10 icon icon-sm icon-primary material-icons-ico material-icons-call"></span>
                            15221532065
                        </li>

                        <li class=" text-gray-light"
                            style="line-height:35px; font-size: 16px"><span
                                class="offset-top-10 icon icon-sm icon-primary material-icons-ico material-icons-mail"></span>
                            1906817459@qq.com
                        </li>
                    </ul>
                </div>
            </div>

            <div class="offset-top-60">
                <div class="hr bg-mercury"></div>
            </div>
            <div class="cell-md-push-6 offset-top-50">
                <p class="text-extra-small text-gray-light text-center">
                    CodingDiary &#169; <span id="copyright-year"></span> <a
                        href="#" class="text-gray-light">All rights
                    reserved.</a>
                </p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>