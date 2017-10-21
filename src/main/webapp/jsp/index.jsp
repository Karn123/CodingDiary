<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Karn
  Date: 2016/11/25
  Time: 13:45
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <!-- Site Title-->
    <title>CodingDiary</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Stylesheets-->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400%7CQuicksand:400,700">
    <link rel="stylesheet" href="css/style.css">
    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("index");
        div.setAttribute("class", "active");
        div = document.getElementById("headerbackground");
        div.parentNode.removeChild(div);
    </script>
    <!-- Swiper-->
    <div data-height="" data-min-height="300px" data-simulate-touch="false" data-slide-effect="fade" class="swiper-container swiper-slider">
        <div class="swiper-wrapper">
            <div data-slide-bg="images/backgrounds/background-11-1920x900.jpg" class="swiper-slide"></div>
            <div data-slide-bg="images/backgrounds/background-12-1920x900.jpg" class="swiper-slide"></div>
        </div>
        <div class="swiper-caption-absolute">
            <div class="shell">
                <div class="range range-xs-center">
                    <div class="cell-sm-10 cell-lg-8">
                        <div>
                            <div class="text-extra-big font-accent text-bold text-spacing-inverse-50 text-capitalize text-white">
                                Coding Diary</div>
                        </div>

                        <div class="offset-top-15 offset-md-top-0">
                            <h5 class="text-spacing-75 text-white" style="font-family: 微软雅黑">——基于校园平台的程序员知识分享之家</h5>
                        </div>
                        <div class="btns-both-lines group group-sm offset-top-25">
                            <a href="${pageContext.request.contextPath}/jsp/myPage" style="min-width:205px; font-size: 20px;" class="btn btn-primary">我的主页</a>
                            <a href="${pageContext.request.contextPath}/jsp/schoolHome" style="min-width:205px;font-size: 20px;" class="btn btn-white">学院之家</a>
                        </div>


                    </div>
                </div>
            </div>
        </div>
        <div style="height:33px;" class="swiper-bottom-panel">
            <div class="swiper-bottom-panel-svg-wrap">
                <svg width="104" height="33">
                    <path style="fill:#ffffff;fill-opacity:1" d="m 0.01291655,16.605668 0,-16.62382923 5.25000005,0.439007 C 12.962198,1.0646618 18.007648,4.7929098 22.364223,13.057557 c 6.80539,12.91018 16.468604,19.152403 29.648693,19.152403 13.180088,0 22.843308,-6.242223 29.648698,-19.152403 4.35657,-8.2646472 9.40202,-11.9928952 17.1013,-12.63671123 l 5.250006,-0.439007 0,16.62382923 0,16.62383 -52.000004,0 -51.99999945,0 0,-16.62383 z"></path>
                </svg><span data-custom-scroll-to="sec" class="icon material-icons-ico material-icons-expand_more text-white"></span>
            </div>
        </div>
    </div>
    <!-- Page Content-->
    <main class="page-content">
        <!-- Sailing Yachts-->
        <section id="sec" class="section-75 section-md-top-60 section-md-bottom-60">
            <div class="shell">

            </div>
        </section>


        <div class="range range-xs-center list-index-lg offset-md-top-0" style="margin-top: -40px">
            <div class="cell-sm-10 cell-md-4">

                <div class="offset-top-20 offset-md-top-30"><img src="images/pages/blog.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                <div class="offset-top-25 offset-md-top-40">
                    <ul class="list-marked p text-center">
                        <c:forEach items="${hottestBlog}" var="blog">
                            <li class="offset-top-15"><a href="singleBlog?blogID=${blog.blogId}" class="text-primary">
                                <c:choose>
                                    <c:when test="${fn:length(blog.blogTitle)>20}">
                                        ${fn:substring(blog.blogTitle,0,18)}......
                                    </c:when>
                                    <c:otherwise>
                                        ${blog.blogTitle}
                                    </c:otherwise>
                                </c:choose>
                            </a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                <div class="offset-top-20">
                    <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                </div>
            </div>
            <div class="cell-sm-10 cell-md-4 offset-top-64 offset-md-top-0">

                <div class="offset-top-20 offset-md-top-30"><img src="images/pages/bbs.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                <div class="offset-top-25 offset-md-top-40">
                    <div class="offset-top-25 offset-md-top-40">
                        <ul class="list-marked p text-center">
                            <c:forEach items="${hottestForumpost}" var="forumpost">
                                <li class="offset-top-15"><a href="singleForumpost?forumpostID=${forumpost.forumpostId}" class="text-primary">
                                    <c:choose>
                                        <c:when test="${fn:length(forumpost.forumpostTitle)>20}">
                                            ${fn:substring(forumpost.forumpostTitle,0,18)}......
                                        </c:when>
                                        <c:otherwise>
                                            ${forumpost.forumpostTitle}
                                        </c:otherwise>
                                    </c:choose>
                                </a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Forumpost" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                <div class="offset-top-20">
                    <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                </div>
            </div>
            <div class="cell-sm-10 cell-md-4 offset-top-64 offset-md-top-0">

                <div class="offset-top-20 offset-md-top-30"><img src="images/pages/zl.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                <div class="offset-top-25 offset-md-top-40">
                    <div class="offset-top-25 offset-md-top-40">
                        <ul class="list-marked p text-center">
                            <c:forEach items="${hottestResource}" var="resource">
                                <li class="offset-top-15"><a href="singleResource?resourceID=${resource.resourceId}" class="text-primary">
                                    <c:choose>
                                        <c:when test="${fn:length(resource.resourceName)>20}">
                                            ${fn:substring(resource.resourceName,0,18)}......
                                        </c:when>
                                        <c:otherwise>
                                            ${resource.resourceName}
                                        </c:otherwise>
                                    </c:choose>
                                </a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                <div class="offset-top-20">
                    <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                </div>
            </div>
        </div>

        <!-- Charter Excellence-->
        <section class="section-75 section-md-top-95 section-md-bottom-110" style="margin-top:-20px">
            <div class="shell">
                <h2 class="text-spacing-inverse-25">什么是Coding Diary</h2>
                <div class="range range-xs-center offset-top-20">
                    <div class="cell-sm-10 cell-lg-8">
                        <p class="text-spacint-0">CodingDiary是一个为计算机与软件及相关专业的在校学生提供的学习交流平台，旨在通过营造一个像“家”一般温暖的氛围，让学生爱上学习，爱上分享知识。在这里，你可以————</p>
                    </div>
                </div>
                <div class="range range-xs-center offset-top-35 offset-lg-top-75">
                    <div class="cell-sm-10 cell-md-4">
                        <!-- Box Icon--><a href="/jsp/blogIndex" class="box-icon-link"><span class="icon icon-lg thin-ico thin-icon-user text-primary"></span><span class="reveal-block offset-top-20"><span class="reveal-block font-accent text-uppercase text-bold text-spacing-50 text-primary">博客学习</span></span><span class="reveal-block offset-top-15 offset-md-top-30 text-extra-small"><span class="p text-silver-chalice text-extra-small">记录平时学习过程中收获的新知识，养成学习习惯。在其他同学的博客中，了解未知的解决方法。</span></span><span class="box-icon-dots reveal-block offset-top-10"><span class="h5 text-spacing-20 text-silver-chalice">...</span></span></a>
                    </div>
                    <div class="cell-sm-10 cell-md-4 offset-top-30 offset-md-top-0">
                        <!-- Box Icon--><a href="/jsp/bbsIndex" class="box-icon-link"><span class="icon icon-lg thin-ico thin-icon-love text-primary"></span><span class="reveal-block offset-top-20"><span class="reveal-block font-accent text-uppercase text-bold text-spacing-50 text-primary">论坛交流</span></span><span class="reveal-block offset-top-15 offset-md-top-30 text-extra-small"><span class="p text-silver-chalice text-extra-small">通过论坛发表自己在学习过程中的疑问和想法或解答他人的疑惑，与同学们展开互动讨论，开阔视角，结交学习伙伴。</span></span><span class="box-icon-dots reveal-block offset-top-10"><span class="h5 text-spacing-20 text-silver-chalice">...</span></span></a>
                    </div>
                    <div class="cell-sm-10 cell-md-4 offset-top-30 offset-md-top-0">
                        <!-- Box Icon--><a href="/jsp/resourceIndex" class="box-icon-link"><span class="icon icon-lg thin-ico thin-icon-briefcase-2 text-primary"></span><span class="reveal-block offset-top-20"><span class="reveal-block font-accent text-uppercase text-bold text-spacing-50 text-primary">资源分享</span></span><span class="reveal-block offset-top-15 offset-md-top-30 text-extra-small"><span class="p text-silver-chalice text-extra-small">寻找自己需要的学习资料，发布资料造福他人，成为资源帝或分享大神，让优秀资源代代相传，让知识的稻穗播种心间。</span></span><span class="box-icon-dots reveal-block offset-top-10"><span class="h5 text-spacing-20 text-silver-chalice">...</span></span></a>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <!-- Page Footer-->
    <jsp:include page="footer.jsp"/>
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
            <div
                    class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
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