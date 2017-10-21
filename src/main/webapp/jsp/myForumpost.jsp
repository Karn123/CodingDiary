<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>我的帖子</title>
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
    <jsp:include page="myPageHeader.jsp"></jsp:include>
    <script type="text/javascript">
        var div = document.getElementById("myForumpost");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-34 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar-->
        <section>
            <div class="shell">
                <div class="range range-xs-center range-lg-left offset-top-50">
                    <div class="cell-sm-9 cell-md-8 cell-md-push-1" style="margin-left: 40px">
                        <div id="loadMyForumpostDiv">
                            <c:forEach items="${list}" var="forumpost" step="1" varStatus="i">
                                <article class="post-classic text-left">
                                    <div class="post-classic-body offset-top-10">
                                        <div class="unit unit-sm unit-sm-horizontal unit-sm-inverse">
                                            <div class="unit-body">
                                                <!-- Post Body-->
                                                <div class="post-body offset-top-15 offset-md-top-25">
                                                    <div class="cell-xs-8 cell-sm-5" style='margin-top: -55px;'>
                                                        <!-- Post Vacancy-->
                                                        <a href="${pageContext.request.contextPath}/jsp/singleForumpost?forumpostID=${forumpost.forumpostId}"
                                                           class="post-vacancy">
                                                            <!-- Unit-->
                                                            <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left"><span
                                                                    class="unit-left"></span></span>
                                                            <span class="unit-body">
                                                                <span class="post-meta text-spacing-0">${forumpost.publishTime }</span>
                                                                <span class="post-title  reveal-block">
                                                                    <span class="offset-top-5 font-accent text-bold text-spacing-50 text-uppercase">
                                                                        <span class="text-mine-shaft">${forumpost.forumpostTitle}</span>
                                                                    </span>
                                                                </span>
                                                                <br>
                                                            <span class="text-spacing-0 reveal-block zhengwen">
                                                                <c:forEach items="${forumpost.forumpostTagsByForumpostId}" var="tag">
                                                                    <c:out value="${tag.tagByTagId.tagName}"/>&nbsp;&nbsp;
                                                                </c:forEach>
                                                                <span class="text-extra-small text-bold text-turquoise text-uppercase">更多</span>
                                                            </span>
                                                            </span>
                                                                <%--<span class="text-spacing-0 reveal-block zhengwen">--%>
                                                                    <%--<span class="text-extra-small text-bold text-turquoise text-uppercase">更多</span>--%>
                                                                <%--</span>--%>
                                                            <%--</span>--%>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="unit-right" style="margin-top: 10px;">
                                            <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${forumpost.userinfoByAuthorId.userId}">
                                                <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                                    <div class="unit-left">
                                                        <img src="${forumpost.userinfoByAuthorId.headPortrait}" alt=" "
                                                             class="img-circle" width="70" height="70">
                                                    </div>
                                                    <div class="unit-body text-center offset-top-4">
                                                        <p class="text-extra-small text-spacing-0 text-bold">
                                                            <cite class="text-normal text-turquoise">${forumpost.userinfoByAuthorId.nickName}</cite>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                        </div>
                                    </div>
                                </article>
                                <br>
                                <br>
                            </c:forEach>
                        </div>
                        <div class="offset-top-10">
                            <div class="hr bg-gray-lighter"></div>
                        </div>
                        <div class="offset-top-10">
                            <!-- Classic Pagination-->
                            <nav>
                                <ul id="pageList" class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">
                                    <c:if test="${pagination.beginNumber>1}">
                                    <li id="before-pagination" class="text-regular">
                                        <a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset-1}" class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                        <span>...</span>
                                    </li>
                                    </c:if>
                                    <c:if test="${pagination.beginNumber==1}">
                                        <li id="before-pagination" class="text-regular">
                                            <a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset-1}" class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                        </li>
                                    </c:if>
                                    <c:forEach var="i" begin="${pagination.beginNumber}" end="${pagination.endNumber}">
                                        <li id="page${i}"><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=${searchType.tagsString}&page_current=${i}"> ${i}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${pagination.endNumber<pagination.totalPageNumber}">
                                    <li id="after-pagination" class="text-regular">
                                        <span>...</span><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset+1}"
                                            class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>
                                    </li>
                                    </c:if>
                                    <c:if test="${pagination.endNumber >= pagination.totalPageNumber}">
                                    <li id="after-pagination" class="text-regular"><a
                                            href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset+1}"
                                            class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>
                                    </li>
                                    </c:if>
                                </ul>
                                <script type="text/javascript">
                                    var num ="${pagination.currentPageNumber}";
                                    var div = document.getElementById("page" + num);
                                    var li = document.getElementById("before-pagination");
                                    if (${pagination.pageOffset==0}) {
                                        li.setAttribute("class", "active");
                                    }
                                    li = document.getElementById("after-pagination");
                                    if (${pagination.endNumber==pagination.totalPageNumber}) {
                                        li.setAttribute("class", "active");
                                    }
                                    div.setAttribute("class", "active");
                                </script>
                            </nav>
                        </div>
                    </div>
                    <div class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-10 offset-sm-top-5">
                        <div class="range range-xs-center offset-top-0">
                            <div class="cell-sm-3 cell-md-12">
                                <div class="veil-sm reveal-md-block offset-top-0 offset-sm-top-0 offset-md-top-0"
                                     style="margin-top: -20px">
                                    <div class="hr bg-gray-lighter"></div>
                                </div>
                                <div class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-64 offset-md-top-0">
                                    <p class="font-accent text-bold text-spacing-50 text-mine-shaft"
                                       style="margin-top: 30px">搜索</p>
                                    <div class="offset-top-5">
                                        <!-- RD Search Form-->
                                        <form action="${pageContext.request.contextPath}/jsp/searchMyForumposts"
                                              method="post" class="form-search blog-form-search rd-search">
                                            <div class="form-group">
                                                <input type="text" id="search_key" name="search_key" autocomplete="off"
                                                       class="form-search-input form-control">
                                            </div>
                                            <button type="submit" class="form-search-submit"><span
                                                    class="icon icon-sm material-icons-ico material-icons-search"></span>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="veil-sm reveal-md-block offset-top-30 offset-sm-top-20 offset-md-top-30">
                                        <div class="hr bg-gray-lighter"></div>
                                    </div>
                                    <div class="offset-top-30">
                                        <p style="font-family: 微软雅黑; font-size : 18px; font-weight: bold; color :black ;">
                                            按日期分类</p>
                                    </div>
                                    <div class="offset-top-25">
                                        <ul class="list-marked text-extra-small text-bold p text-left font-accent">
                                            <li><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?searchBySpecificDate=2017-03-01" class="text-primary"
                                                                         style="font-size: 16px;">2017.03</a></li>
                                            <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?searchBySpecificDate=2017-02-01" class="text-primary"
                                                                         style="font-size: 16px;">2017.02</a></li>
                                            <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?searchBySpecificDate=2017-01-01" class="text-primary"
                                                                         style="font-size: 16px;">2017.01</a></li>
                                            <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?searchBySpecificDate=2016-12-01" class="text-primary"
                                                                         style="font-size: 16px;">2016.12</a></li>
                                            <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchMyForumposts?searchBySpecificDate=2016-11-01" class="text-primary"
                                                                         style="font-size: 16px;">2016.11</a></li>
                                        </ul>
                                    </div>
                                    <div class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
                                        <div class="hr bg-gray-lighter"></div>
                                    </div>
                                </div>
                                <div class="cell-sm-6 cell-md-12 offset-md-top-30">
                                    <p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">
                                        按标签分类</p>
                                    <div class="offset-top-20">
                                        <!-- List inline marked-->
                                        <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25 text-left">
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=JAVA">JAVA</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=Csh">C#</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=.NET">.NET</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=CPP">C++</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=PHP">PHP</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=CSS">CSS</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=HTML">HTML</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=IOS">IOS</a>
                                            </li>
                                            <li><a class="text-turquoise"
                                                   href="${pageContext.request.contextPath}/jsp/searchMyForumposts?search_key=Javascript">Javascript</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
                                        <div class="hr bg-gray-lighter"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
<!-- Java script-->
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var search_tags = "${searchType.tagsString}".split(" ");
        var tags = "";
        for(var i=0;i<search_tags.length;i++) {
            if (search_tags[i] == 'CPP')
                tags += " C++";
            else if (search_tags[i] == 'Csh')
                tags += " C#";
            else if (search_tags[i] == 'SQL')
                tags += " 数据库";
            else
                tags += search_tags[i]+" ";
        }
        document.getElementById("search_key").value = tags;
        var totalPageNum = "${pagination.totalPageNumber}";
        if(totalPageNum=="0"){
            var divText = "<div class='offset-top-100' align='center'><h3>您还未发表任何（该类）论坛帖 :(</h3><div><div><br><a class=\"btn btn-xxs btn-round btn-primary\" style=\"color: white\" href=\"${pageContext.request.contextPath}/jsp/postForumpost\">去上传</a></div>";
            $("#loadMyForumpostDiv").empty().append(divText);
            $("#pageList").empty();
        }
    });
</script>
</body>
</html>