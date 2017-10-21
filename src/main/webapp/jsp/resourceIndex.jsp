<%--
  Created by IntelliJ IDEA.
  User: Karn
  Date: 2017/2/21
  Time: 15:37
--%>
<%@ page language="java" import="java.util.List" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>学习资源区</title>
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
    <script type="text/javascript">
        var div = document.getElementById("data");
        div.setAttribute("class", "active");
    </script>

    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-0 section-md-bottom-110">
        <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35">
            <a href="#"
               class="h4 btn-link text-spacing-inverce-25 text-turquoise">热门资源</a>
        </div>
        <div class="range range-xs-center offset-top-53 text-sm-left">
            <div class="cell-sm-10 cell-lg-12">
                <!-- Owl Carousel-->
                <div data-items="1" data-lg-items="2" data-xs-stage-padding="5"
                     data-loop="true" data-margin="30" data-mouse-drag="true"
                     data-nav="true" data-dots="true"
                     class="owl-carousel owl-nav-primary">
                    <c:forEach items="${resourceEntities}" var="resource" step="1" varStatus="i">
                        <c:if test="${i.count%2!=0}">
                            <div class="owl-item">
                            <!-- Blockquote-->
                            <blockquote class="quote">
                            <!-- Media-->
                            <div class="media media-sm-custom">

                            <div class="media-body offset-top-15 offset-sm-top-0 text-middle">
                            <div class="cell-xs-8 cell-sm-5 offset-top-64 offset-xs-top-30 offset-sm-top-0">
                            <!-- Post Vacancy-->
                            <a href="${pageContext.request.contextPath}/jsp/singleResource?resourceID=${resource.resourceId}" class="post-vacancy">
                                <!-- Unit-->
                                <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left">
                                    <span class="unit-left">
                                        <span class="list-index-counter h2 text-silver-chalice text-spacing-inverse-75"></span>
                                    </span>
                                    <span class="unit-body">
                                        <span>
                                            <span class="h5 text-spacing-inverse-25 text-turquoise text-capitalize"
                                                  style="text-overflow:ellipsis; ">${resource.resourceName}</span>
                                        </span>
                                        <span class="offset-top-4 reveal-block">
                                            <span class="h6 font-default text-regular text-spacing-inverse-25 text-capitalize">
                                                    <%--${resource.resourceDescription}--%>
                                                <c:forEach items="${resource.resourceTagsByResourceId}" var="resourceTag">
                                                    <c:out value="${resourceTag.tagByTagIdNum.tagName}"/>&nbsp;&nbsp;
                                                </c:forEach>
                                            </span>
                                        </span>
                                    </span>
                                </span>
                            </a>
                        </c:if>
                        <c:if test="${i.count%2==0}">
                            <div class="offset-top-64 offset-xs-top-30">
                                <!-- Post Vacancy-->
                                <a href="singleResource?resourceID=${resource.resourceId}" class="post-vacancy">
                                    <!-- Unit-->
                                    <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left">
                                            <span class="unit-left">
                                                <span class="list-index-counter h2 text-silver-chalice text-spacing-inverse-75"></span>
                                            </span>
                                        <span class="unit-body">
                                            <span>
                                                <span class="h5 text-spacing-inverse-25 text-turquoise text-capitalize">${resource.resourceName}</span>
                                                </span>
                                            <span class="offset-top-4 reveal-block">
                                                <span class="h6 font-default text-regular text-spacing-inverse-25 text-capitalize">
                                                        <%--${resource.resourceDescription}--%>
                                                    <c:forEach items="${resource.resourceTagsByResourceId}" var="resourceTag" step="1" varStatus="i">
                                                        <c:out value="${resourceTag.tagByTagIdNum.tagName}"/>&nbsp;&nbsp;
                                                    </c:forEach>
                                                </span>
                                            </span>
                                        </span>
                                    </span>
                                </a>
                            </div>
                            </div>
                            </div>
                            </div>
                            </blockquote>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

<section>
    <div class="shell">
        <div class="offset-top-0">
            <svg width="135" height="4" viewBox="0 0 135 4">
                <path style="fill:#efefef"
                      d="M 2.3227821,2.0449621 -0.03800192,0.07659815 3.1569295,1.2434136 c 2.5401262,0.9276753 3.7227371,0.8843425 5.7702062,-0.2114297 1.9784883,-1.05885455 2.8486773,-1.10484315 3.7550683,-0.19845145 1.484362,1.48436175 5.031902,1.52768545 6.480446,0.079141 0.80087,-0.80087 2.091954,-0.7421935 4.740217,0.21543085 2.979173,1.0772827 4.014876,1.0816979 5.707987,0.024332 1.636053,-1.02173165 2.672814,-1.04140395 4.959784,-0.09411 2.271014,0.9406851 3.546951,0.926086 5.946995,-0.068045 2.294075,-0.95023691 3.340846,-0.9804738 4.2,-0.1213203 1.486272,1.48627181 5.021657,1.46707331 6.524159,-0.035429 0.906391,-0.9063917 1.77658,-0.8604031 3.755068,0.19845151 1.998288,1.0694512 3.235083,1.1291955 5.520207,0.2666579 2.160373,-0.81544991 3.729489,-0.81544991 5.889862,0 2.285124,0.8625376 3.521919,0.8027933 5.520207,-0.2666579 1.978488,-1.05885461 2.848677,-1.10484321 3.755068,-0.19845151 1.484362,1.48436181 5.031902,1.52768551 6.480446,0.079141 0.80087,-0.80087 2.071309,-0.7496588 4.664419,0.18802211 2.948695,1.0662618 4.004871,1.0526028 6.119554,-0.079141 1.963517,-1.05084213 2.82919,-1.09441383 3.735581,-0.18802223 1.484362,1.48436183 5.031906,1.52768553 6.480446,0.079141 0.80087,-0.80087 2.07131,-0.7496587 4.66442,0.18802203 2.90135,1.0491408 4.00935,1.0502085 5.96095,0.00574 1.95708,-1.04739773 2.78238,-1.04236583 4.49536,0.027409 1.72387,1.0765766 2.7384,1.078836 5.68684,0.012665 2.7988,-1.01205733 3.90688,-1.03357323 5.03447,-0.097755 0.7952,0.6599557 2.61547,0.976326 4.04505,0.703045 2.37877,-0.4547301 2.464,-0.3616463 1.00491,1.097441 -1.45908,1.4590874 -1.84642,1.4453923 -4.56661,-0.1614625 -2.74627,-1.6222625 -3.15865,-1.6336716 -5.42297,-0.1500332 -2.18024,1.4285487 -2.76388,1.4437811 -5.28891,0.1380357 -2.47017,-1.2773717 -3.19253,-1.2820786 -5.57019,-0.036295 -2.37022,1.241885 -3.09846,1.241885 -5.5,0 -2.40452,-1.2434263 -3.13028,-1.2416208 -5.52624,0.013748 C 99.763844,3.9190467 99.15734,3.9133435 97.292676,2.607281 95.404183,1.2845297 94.833305,1.2870756 92.236706,2.6298291 89.597447,3.9946429 89.064352,3.982798 86.815628,2.5093781 84.52735,1.0100401 84.120628,1.0069808 81.884292,2.4722843 79.735334,3.8803355 79.122431,3.8992836 76.704532,2.6324181 74.291896,1.3683104 73.641159,1.3851356 71.292806,2.7723415 68.845881,4.2177761 68.408458,4.2082649 66.042738,2.6581862 63.671614,1.1045661 63.264662,1.0965199 61.032448,2.5591227 58.880523,3.969118 58.285419,3.9846813 55.824584,2.69532 53.371101,1.4098101 52.768844,1.4213922 50.673394,2.7943834 48.512048,4.2105516 48.042674,4.1968405 45.408131,2.6405778 42.740652,1.0648591 42.325185,1.0568632 40.065069,2.5377482 37.884828,3.9662969 37.30119,3.9815293 34.776157,2.6757839 32.280231,1.3850906 31.599207,1.3855448 29.12951,2.6795501 26.631592,3.9883412 26.052244,3.9748646 23.889307,2.5576544 21.678633,1.1091646 21.182621,1.1064374 18.434276,2.5276615 15.749926,3.9157928 15.172991,3.9243054 13.287423,2.6036024 11.426177,1.2999346 10.817722,1.295134 8.3625636,2.5647457 4.8669338,4.3724061 5.142697,4.3961383 2.3227821,2.0449621 Z"/>
            </svg>
        </div>
        <div class="offset-top-40">
            <div class="range range-xs-center offset-top-44 offset-md-top-53 text-sm-left">
                <div class="cell-sm-6 cell-md-4" style='margin-top: -40px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/c++.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>C++</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=CPP"
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cell-sm-6 cell-md-4" style='margin-top: -40px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/Java.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>Java</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=Java"
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cell-sm-6 cell-md-4" style='margin-top: -40px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/SQL.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>数据库</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key="
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cell-sm-6 cell-md-4 offset-top-60"
                     style='margin-top: 30px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/css.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>CSS/HTML</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=HTML"
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cell-sm-6 cell-md-4 offset-top-60"
                     style='margin-top: 30px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/Csh.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>C#</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=Csh"
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cell-sm-6 cell-md-4 offset-top-60"
                     style='margin-top: 30px;'>
                    <div class="post-inspiration post-inspiration-img-auto-height">
                        <div class="post-inspiration-img-wrap">
                            <img src="images/blog/android.jpg" width="962"
                                 height="491" alt="">
                        </div>
                        <div class="post-inspiration-body">
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="veil reveal-sm-inline-block">
                                <span class="icon icon-lg thin-ico thin-icon-anchor text-white"></span>
                            </div>
                            <div style="margin-bottom: 20px;"
                                 class="veil reveal-sm-inline-block text-bottom">
                                <img src="images/divider-01-67x4.png" width="67" height="4"
                                     alt="" class="img-responsive center-block">
                            </div>
                            <div class="offset-top-20">
                                <h3>Android</h3>
                            </div>
                            <div style="max-width:200px;"
                                 class="btn-both-lines offset-top-30 center-block">
                                <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=Android"
                                   style="min-width: 100px; margin-top: 10px"
                                   class="btn btn-primary">进入</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="offset-top-44">
            <svg width="135" height="4" viewBox="0 0 135 4">
                <path style="fill:#efefef"
                      d="M 2.3227821,2.0449621 -0.03800192,0.07659815 3.1569295,1.2434136 c 2.5401262,0.9276753 3.7227371,0.8843425 5.7702062,-0.2114297 1.9784883,-1.05885455 2.8486773,-1.10484315 3.7550683,-0.19845145 1.484362,1.48436175 5.031902,1.52768545 6.480446,0.079141 0.80087,-0.80087 2.091954,-0.7421935 4.740217,0.21543085 2.979173,1.0772827 4.014876,1.0816979 5.707987,0.024332 1.636053,-1.02173165 2.672814,-1.04140395 4.959784,-0.09411 2.271014,0.9406851 3.546951,0.926086 5.946995,-0.068045 2.294075,-0.95023691 3.340846,-0.9804738 4.2,-0.1213203 1.486272,1.48627181 5.021657,1.46707331 6.524159,-0.035429 0.906391,-0.9063917 1.77658,-0.8604031 3.755068,0.19845151 1.998288,1.0694512 3.235083,1.1291955 5.520207,0.2666579 2.160373,-0.81544991 3.729489,-0.81544991 5.889862,0 2.285124,0.8625376 3.521919,0.8027933 5.520207,-0.2666579 1.978488,-1.05885461 2.848677,-1.10484321 3.755068,-0.19845151 1.484362,1.48436181 5.031902,1.52768551 6.480446,0.079141 0.80087,-0.80087 2.071309,-0.7496588 4.664419,0.18802211 2.948695,1.0662618 4.004871,1.0526028 6.119554,-0.079141 1.963517,-1.05084213 2.82919,-1.09441383 3.735581,-0.18802223 1.484362,1.48436183 5.031906,1.52768553 6.480446,0.079141 0.80087,-0.80087 2.07131,-0.7496587 4.66442,0.18802203 2.90135,1.0491408 4.00935,1.0502085 5.96095,0.00574 1.95708,-1.04739773 2.78238,-1.04236583 4.49536,0.027409 1.72387,1.0765766 2.7384,1.078836 5.68684,0.012665 2.7988,-1.01205733 3.90688,-1.03357323 5.03447,-0.097755 0.7952,0.6599557 2.61547,0.976326 4.04505,0.703045 2.37877,-0.4547301 2.464,-0.3616463 1.00491,1.097441 -1.45908,1.4590874 -1.84642,1.4453923 -4.56661,-0.1614625 -2.74627,-1.6222625 -3.15865,-1.6336716 -5.42297,-0.1500332 -2.18024,1.4285487 -2.76388,1.4437811 -5.28891,0.1380357 -2.47017,-1.2773717 -3.19253,-1.2820786 -5.57019,-0.036295 -2.37022,1.241885 -3.09846,1.241885 -5.5,0 -2.40452,-1.2434263 -3.13028,-1.2416208 -5.52624,0.013748 C 99.763844,3.9190467 99.15734,3.9133435 97.292676,2.607281 95.404183,1.2845297 94.833305,1.2870756 92.236706,2.6298291 89.597447,3.9946429 89.064352,3.982798 86.815628,2.5093781 84.52735,1.0100401 84.120628,1.0069808 81.884292,2.4722843 79.735334,3.8803355 79.122431,3.8992836 76.704532,2.6324181 74.291896,1.3683104 73.641159,1.3851356 71.292806,2.7723415 68.845881,4.2177761 68.408458,4.2082649 66.042738,2.6581862 63.671614,1.1045661 63.264662,1.0965199 61.032448,2.5591227 58.880523,3.969118 58.285419,3.9846813 55.824584,2.69532 53.371101,1.4098101 52.768844,1.4213922 50.673394,2.7943834 48.512048,4.2105516 48.042674,4.1968405 45.408131,2.6405778 42.740652,1.0648591 42.325185,1.0568632 40.065069,2.5377482 37.884828,3.9662969 37.30119,3.9815293 34.776157,2.6757839 32.280231,1.3850906 31.599207,1.3855448 29.12951,2.6795501 26.631592,3.9883412 26.052244,3.9748646 23.889307,2.5576544 21.678633,1.1091646 21.182621,1.1064374 18.434276,2.5276615 15.749926,3.9157928 15.172991,3.9243054 13.287423,2.6036024 11.426177,1.2999346 10.817722,1.295134 8.3625636,2.5647457 4.8669338,4.3724061 5.142697,4.3961383 2.3227821,2.0449621 Z"/>
            </svg>
        </div>
        <div class="text-md-center">
            <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource">
                <span class="text text-bold">更 多 》</span>
            </a>
        </div>
    </div>
</section>
</main>
<!-- Page Footer-->
<jsp:include page="footer.jsp"></jsp:include>
</div>
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
                <button title="Close (Esc)"
                        class="pswp__button pswp__button--close"></button>
                <button title="Share" class="pswp__button pswp__button--share"></button>
                <button title="Toggle fullscreen"
                        class="pswp__button pswp__button--fs"></button>
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
            <button title="Previous (arrow left)"
                    class="pswp__button pswp__button--arrow--left"></button>
            <button title="Next (arrow right)"
                    class="pswp__button pswp__button--arrow--right"></button>
            <div class="pswp__caption">
                <div class="pswp__caption__cent"></div>
            </div>
        </div>
    </div>
</div>
<!-- Java script-->
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>