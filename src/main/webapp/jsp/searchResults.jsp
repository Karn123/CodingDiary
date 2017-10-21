<%@ page import="cst.Constants" %><%--
  Created by IntelliJ IDEA.
  User: 86761
  Date: 2017/2/21
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>搜索结果</title>
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

    <script type="text/javascript" src="js/jquery.js"></script>

    <style type="text/css">
        @media screen and (max-width: 780px) {
            .unit-right {
                display: none;
            }
        }
    </style>
</head>

<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("search");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-0 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar-->
        <section>
            <div class="shell">
                <div class="veil-sm reveal-md-block  offset-sm-top-0 offset-md-top-30">

                </div>
                <div class="range range-xs-center range-lg-left" style="margin-top: 40px">
                    <div  class="cell-sm-10 cell-md-8 cell-md-push-1" style="margin-left :20px ">
                        <div class="tags cell-sm-8 cell-md-9 cell-lg-12" >
                            <div class="offset-top-0 text-left" >
                                <!-- List inline marked-->
                                <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25">
                                    <li><p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">选择分类</p></li>
                                    <c:if test="${searchType.tableName=='Blog'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-gray-light">博客</a></li>
                                    </c:if>
                                    <c:if test="${searchType.tableName !='Blog'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-turquoise">博客</a></li>
                                    </c:if>
                                    <c:if test="${searchType.tableName =='Forumpost'}">
                                    <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Forumpost&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-gray-light">论坛帖</a></li>
                                    </c:if>
                                    <c:if test="${searchType.tableName !='Forumpost'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Forumpost&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-turquoise">论坛帖</a></li>
                                    </c:if>
                                    <c:if test="${searchType.tableName =='Resource'}">
                                    <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-gray-light">学习资源</a></li>
                                    </c:if>
                                    <c:if test="${searchType.tableName !='Resource'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=${searchType.tagsString}" class="text-turquoise">学习资源</a></li>
                                    </c:if>
                                </ul>
                            </div>

                            <div class="offset-top-0 text-left" >
                                <!-- List inline marked-->
                                <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25 ">
                                    <li><p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">排序方式</p></li>
                                    <c:if test="${searchType.orderByHotness =='true'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=false&orderByHotness=true&search_key=${searchType.tagsString}" class="text-gray-light">按热度</a></li>
                                    </c:if>
                                    <c:if test="${searchType.orderByHotness =='false'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=false&orderByHotness=true&search_key=${searchType.tagsString}" class="text-turquoise">按热度</a></li>
                                    </c:if>
                                    <c:if test="${searchType.orderByDateDes =='true'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=true&orderByHotness=false&search_key=${searchType.tagsString}" class="text-gray-light">按时间</a></li>
                                    </c:if>
                                    <c:if test="${searchType.orderByDateDes =='false'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=true&orderByHotness=false&search_key=${searchType.tagsString}" class="text-turquoise">按时间</a></li>
                                    </c:if>

                                </ul>
                            </div>
                        </div>
                        <div id="loadSearchResultsDiv">
                        <!-- Post Classic-->
                        <c:if test="${searchType.tableName == 'Blog'}">
                            <c:forEach items="${blogs}" var="blog" step="1" varStatus="i">
                                <article class="post-classic text-left  offset-top-50">

                                    <div class="post-classic-body offset-top-30">
                                <div class="unit unit-sm unit-sm-horizontal unit-sm-inverse">
                                    <div class="unit-body">
                                        <!-- Post Body-->
                                        <div class="post-body offset-top-15 offset-md-top-25">
                                            <div class="cell-xs-8 cell-sm-5" style='margin-top: -55px;'>
                                                <!-- Post Vacancy-->
                                                <a href="${pageContext.request.contextPath}/jsp/singleBlog?blogID=${blog.blogId}" class="post-vacancy">
                                                <!-- Unit-->
                                                    <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left">
                                                        <span class="unit-left"></span><span class="unit-body">
                                                        <span class="post-meta">
                                                            <span class="text-spacing-0">${blog.publishTime}</span>
                                                        </span>
                                                        <span class="post-title  reveal-block">
                                                            <span class="offset-top-5 font-accent text-bold text-spacing-50 text-uppercase">
                                                                <span class="text-mine-shaft">${blog.blogTitle}</span>
                                                            </span>
                                                        </span>
                                                        <br>
                                                        <span class="text-spacing-0 reveal-block zhengwen">
                                                            <c:forEach items="${blog.blogTagsByBlogId}" var="blogTag">
                                                                <c:out value="${blogTag.tagByTagIdNum.tagName}"/>&nbsp;&nbsp;
                                                            </c:forEach>
                                                            <span class="text-extra-small text-bold text-turquoise text-uppercase">更多</span>
                                                        </span>
                                                    </span>
                                                    </span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="unit-right" style='margin-top: 10px'>
                                        <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                            <div class="unit-left">
                                                <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${blog.userinfoByAuthorId.userId}">
                                                    <img src="${blog.userinfoByAuthorId.headPortrait}" alt=" " width="70" height="70" class="img-circle">
                                                </a>
                                            </div>
                                            <div class="unit-body text-center offset-top-4">
                                                <p class="text-extra-small text-spacing-0 text-bold">
                                                    <cite class="text-normal">
                                                        <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${blog.userinfoByAuthorId.userId}" class="text-turquoise">
                                                        ${blog.userinfoByAuthorId.nickName}</a>
                                                    </cite>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                </article>
                            </c:forEach>
                        </c:if>
                        <c:if test="${searchType.tableName == 'Forumpost'}">
                            <c:forEach items="${forumposts}" var="forumpost" step="1" varStatus="i">
                                <article class="post-classic text-left  offset-top-50">
                                    <div class="post-classic-body offset-top-30">
                                        <div class="unit unit-sm unit-sm-horizontal unit-sm-inverse">
                                            <div class="unit-body">
                                                <!-- Post Body-->
                                                <div class="post-body offset-top-15 offset-md-top-25">
                                                    <div class="cell-xs-8 cell-sm-5" style='margin-top: -55px;'>
                                                        <!-- Post Vacancy-->
                                                        <a href="${pageContext.request.contextPath}/jsp/singleForumpost?forumpostID=${forumpost.forumpostId}" class="post-vacancy">
                                                            <!-- Unit-->
                                                            <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left">
                                                        <span class="unit-left"></span><span class="unit-body">
                                                        <span class="post-meta">
                                                            <span class="text-spacing-0">${forumpost.publishTime }</span>
                                                        </span>
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
                                                    </span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="unit-right" style='margin-top: 10px'>
                                                <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                                    <div class="unit-left">
                                                        <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${forumpost.userinfoByAuthorId.userId}">
                                                            <img src="${forumpost.userinfoByAuthorId.headPortrait}" alt=" " width="70" height="70" class="img-circle">
                                                        </a>
                                                    </div>
                                                    <div class="unit-body text-center offset-top-4">
                                                        <p class="text-extra-small text-spacing-0 text-bold">
                                                            <cite class="text-normal">
                                                                <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${forumpost.userinfoByAuthorId.userId}" class="text-turquoise">
                                                                        ${forumpost.userinfoByAuthorId.nickName}</a>
                                                            </cite>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                            </c:forEach>
                        </c:if>
                        <c:if test="${searchType.tableName == 'Resource'}">
                            <c:forEach items="${resources}" var="resource" step="1" varStatus="i">
                                <article class="post-classic text-left  offset-top-50">

                                    <div class="post-classic-body offset-top-30">
                                        <div class="unit unit-sm unit-sm-horizontal unit-sm-inverse">
                                            <div class="unit-body">
                                                <!-- Post Body-->
                                                <div class="post-body offset-top-15 offset-md-top-25">
                                                    <div class="cell-xs-8 cell-sm-5" style='margin-top: -55px;'>
                                                        <!-- Post Vacancy-->
                                                        <a href="${pageContext.request.contextPath}/jsp/singleResource?resourceID=${resource.resourceId}" class="post-vacancy">
                                                            <!-- Unit-->
                                                            <span class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left">
                                                        <span class="unit-left"></span><span class="unit-body">
                                                        <span class="post-meta">
                                                            <span class="text-spacing-0">${resource.uploadTime}</span>
                                                        </span>
                                                        <span class="post-title  reveal-block">
                                                            <span class="offset-top-5 font-accent text-bold text-spacing-50 text-uppercase">
                                                                <span class="text-mine-shaft">${resource.resourceName}</span>
                                                            </span>
                                                        </span>
                                                        <br>
                                                        <span class="text-spacing-0 reveal-block zhengwen">
                                                             <c:forEach items="${resource.resourceTagsByResourceId}" var="tag">
                                                                 <c:out value="${tag.tagByTagIdNum.tagName}"/>&nbsp;&nbsp;
                                                             </c:forEach>
                                                            <span class="text-extra-small text-bold text-turquoise text-uppercase">更多</span>
                                                        </span>
                                                    </span>
                                                    </span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="unit-right" style='margin-top: 10px'>
                                                <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                                    <div class="unit-left">
                                                        <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${resource.userinfoByUploaderId.userId}">
                                                            <img src="${resource.userinfoByUploaderId.headPortrait}" alt=" " width="70" height="70" class="img-circle">
                                                        </a>
                                                    </div>
                                                    <div class="unit-body text-center offset-top-4">
                                                        <p class="text-extra-small text-spacing-0 text-bold">
                                                            <cite class="text-normal">
                                                                <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${resource.userinfoByUploaderId.userId}" class="text-turquoise">
                                                                        ${resource.userinfoByUploaderId.nickName}</a>
                                                            </cite>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                            </c:forEach>
                        </c:if>
                        <div class="offset-top-20">
                            <div class="hr bg-gray-lighter"></div>
                        </div>
                        <div class="offset-top-10">
                            <!-- Post Classic-->
                            <div class="offset-top-10">
                                <!-- Classic Pagination-->
                                <nav>
                                    <ul id="pageList" class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">

                                        <c:if test="${pagination.beginNumber>1}">
                                            <li id="before-pagination" class="text-regular">
                                                <a id="prevPageHref" href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&page_current=${pagination.currentPageNumber-1}&search_key=${searchType.tagsString}" class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                                <span>...</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${pagination.beginNumber==1}">
                                            <li id="before-pagination" class="text-regular">
                                                <a id="prevPageHref" href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&page_current=${pagination.currentPageNumber-1}&search_key=${searchType.tagsString}" class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                            </li>
                                        </c:if>
                                        <c:forEach var="i" begin="${pagination.beginNumber}" end="${pagination.endNumber}">
                                            <li id="page${i}">
                                                <a id="curPageHref" href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&page_current=${i}&search_key=${searchType.tagsString}">
                                                ${i}
                                                </a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${pagination.endNumber<pagination.totalPageNumber}">
                                            <li id="after-pagination" class="text-regular">
                                            <span>...</span> <a id="nextPageHref" href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&page_current=${pagination.currentPageNumber+1}&search_key=${searchType.tagsString}"
                                                   class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right">
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pagination.endNumber >= pagination.totalPageNumber}">
                                            <li id="after-pagination" class="text-regular">
                                                <a id="nextPageHref" href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&page_current=${pagination.currentPageNumber+1}&search_key=${searchType.tagsString}"
                                                                    class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right">
                                            </a>
                                            </li>
                                        </c:if>

                                    </ul>
                                    <script type="text/javascript">
                                        var num = "${pagination.currentPageNumber}";
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
                        </div>
                    </div>
                    <div class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-md-top-0" >
                        <p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">搜索</p>
                        <div class="offset-top-0">
                            <!-- RD Search Form-->
                            <form action="${pageContext.request.contextPath}/jsp/searchResults" method="GET"
                                  class="form-search blog-form-search rd-search">
                                <div class="form-group">
                                    <input id="search_key" type="text" name="search_key" autocomplete="off"
                                           class="form-search-input form-control not-null">
                                </div>
                                <button type="submit" class="form-search-submit"><span
                                        class="icon icon-sm material-icons-ico material-icons-search"></span></button>
                            </form>
                        </div>
                        <div class="offset-top-35">

                        </div>
                        <div class="cell-sm-3 cell-md-12">
                            <p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase offset-top-55">
                                标签</p>
                            <div class="offset-top-10">
                                <ul class="list-marked text-bold p text-left font-accent">
                                    <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=CPP" class="text-primary">C++</a></li>
                                    <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=Java" class="text-primary">Java</a></li>
                                    <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=HTML" class="text-primary">HTML</a></li>
                                    <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=数据库" class="text-primary">数据库</a></li>
                                    <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=Android" class="text-primary">Android</a></li>
                                    <li class="offset-top-10"><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=${searchType.tableName}&orderByDateDes=${searchType.orderByDateDes}&orderByHotness=${searchType.orderByHotness}&search_key=IOS" class="text-primary">ios</a></li>
                                </ul>
                            </div>
                            <div class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
                            </div>
                        </div>
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
        document.getElementById("theme").innerHTML = tags;
        var totalPageNum = "${pagination.totalPageNumber}";
        if(totalPageNum == "0"){
            var divText = "<div class='offset-top-100' align='center'><h3>平台现在还没有任何（该类）资源 :(</h3><div><div><br><a class=\"btn btn-xxs btn-round btn-primary\" style=\"color: white\" href=\"${pageContext.request.contextPath}/jsp/postBlog\">去上传</a></div>";
            $("#loadSearchResultsDiv").empty().append(divText);
            $("#pageList").empty();
        }
    });

//    $("#select1").ready(function(){
//        $(".text-turquoise").click(function () {
//                $(".text-gray-light").removeClass("text-gray-light");
//                $(this).removeClass("text-turquoise");
//                $(this).addClass("text-gray-light");
//            })
//        });
</script>
</body>
</html>