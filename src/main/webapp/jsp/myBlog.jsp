<%@ page language="java" import="cst.Message" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>我的博客</title>
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
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="myPageHeader.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("myBlog");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-34 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar-->
        <section>
            <div class="shell">
                <div class="range range-xs-center range-lg-left offset-top-50">
                    <div class="cell-sm-10 cell-md-8 cell-md-push-1" style="margin-left: 40px">
                        <!-- Post Classic-->
                        <div id="loadMyBlogDiv" class="offset-top-35">
                            <c:forEach items="${list}" var="blog" step="1" varStatus="i">
                                <div class="offset-top-35">
                                    <article class="post-classic text-left">
                                            <%--<div class="post-classic-img-wrap">
                                                <a href="singleBlog?blogID=${blog.blogId}"><img
                                                        src="images/blog/post-23-770x381.jpg" width="770"
                                                        height="381" alt="" class="img-responsive center-block">
                                                </a>
                                            </div>--%>
                                        <div class="post-classic-body offset-top-30">
                                            <div class="unit unit-sm unit-sm-inverse unit-left">
                                                <div class="unit-body">
                                                    <!-- Post Meta-->
                                                    <div class="post-meta">
                                                        <p class="text-spacing-0">${blog.publishTime}</p>
                                                    </div>
                                                    <!-- Post Title-->
                                                    <div class="post-title  list-inline">
                                                        <c:choose>
                                                            <c:when test="${blog.tfByIsLegal.tfOption!='T'}">
                                                                ${blog.blogTitle}(${blog.tfByIsLegal.tfOption})
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${blog.blogTitle}
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <p class="offset-top-10 font-passage text-bold text-spacing-50"
                                                           style="font-size:24px">
                                                            <a href="singleBlog?blogID=${blog.blogId}"
                                                               class="text-mine-shaft"></a>
                                                        </p>
                                                        <a href="postBlog?blogID=${blog.blogId}"
                                                           style="color: white">
                                                        <button class="btn btn-xxs btn-round btn-primary"
                                                                href="postBlog?blogID=${blog.blogId}">编辑</button></a>
                                                        <button class="btn btn-xxs btn-round btn-white"
                                                                onclick="deleteBlog(${blog.blogId})">
                                                            <c:choose>
                                                                <c:when test="${blog.tfByIsLegal.tfOption=='已经删除'}">
                                                                    恢复
                                                                </c:when>
                                                                <c:otherwise>
                                                                    删除
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </button>
                                                    </div>
                                                    <!-- Post Body-->
                                                        <%--<div class="post-body offset-top-15 offset-md-top-15">
                                                            <p class="text-spacing-0 font-passage">${blog.blogContent}</p>
                                                        </div>--%>
                                                    <div class="post-body offset-top-15 offset-md-top-15">
                                                        <a href="singleBlog?blogID=${blog.blogId}"
                                                           class="text-turquoise"
                                                           style="font-family:幼圆;font-size:18px; font-weight: bolder;">查看全文</a>
                                                        <div class="offset-top-15 offset-md-top-15">
                                                            <!-- List inline marked-->
                                                            <ul class="btn-group-smalltags list-inline">
                                                                <c:forEach items="${blog.blogTagsByBlogId}"
                                                                           var="tag"
                                                                           step="1">
                                                                    <c:if test="${tag.tagByTagIdNum.tagName=='C++'}">
                                                                        <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&search_key=CPP"
                                                                           class="text-turquoise">
                                                                            <button class="btn btn-xs btn-white">
                                                                                    ${tag.tagByTagIdNum.tagName}
                                                                            </button>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${tag.tagByTagIdNum.tagName=='C#'}">
                                                                        <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&search_key=Csh"
                                                                           class="text-turquoise">
                                                                            <button class="btn btn-xs btn-white">
                                                                                    ${tag.tagByTagIdNum.tagName}
                                                                            </button>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${tag.tagByTagIdNum.tagName=='数据库'}">
                                                                        <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&search_key=SQL"
                                                                           class="text-turquoise">
                                                                            <button class="btn btn-xs btn-white">
                                                                                    ${tag.tagByTagIdNum.tagName}
                                                                            </button>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${tag.tagByTagIdNum.tagName !='C#' and tag.tagByTagIdNum.tagName!='C++' and tag.tagByTagIdNum.tagName!='数据库'}">
                                                                        <a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Blog&search_key=${tag.tagByTagIdNum.tagName}"
                                                                           class="text-turquoise">
                                                                            <button class="btn btn-xs btn-white">
                                                                                    ${tag.tagByTagIdNum.tagName}
                                                                            </button>
                                                                        </a>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </article>
                                </div>
                                <div class="offset-top-30">
                                    <div class="hr bg-gray-lighter"></div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="offset-top-35">
                            <!-- Post Classic-->
                            <div class="offset-top-50">
                                <!-- Classic Pagination-->
                                <nav>
                                    <ul id="pageList"
                                        class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">

                                        <c:if test="${pagination.beginNumber>1}">
                                            <li id="before-pagination" class="text-regular">
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset-1}"
                                                   class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                                <span>...</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${pagination.beginNumber==1}">
                                            <li id="before-pagination" class="text-regular">
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset-1}"
                                                   class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                            </li>
                                        </c:if>
                                        <c:forEach var="i" begin="${pagination.beginNumber}"
                                                   end="${pagination.endNumber}">
                                            <li id="page${i}"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=${searchType.tagsString}&page_current=${i}"> ${i}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${pagination.endNumber<pagination.totalPageNumber}">
                                            <li id="after-pagination" class="text-regular">
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset+1}"
                                                   class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>
                                                <span>...</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${pagination.endNumber>=pagination.totalPageNumber}">
                                            <li id="after-pagination" class="text-regular">
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=${searchType.tagsString}&page_current=${pagination.pageOffset+1}"
                                                   class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>
                                            </li>
                                        </c:if>
                                    </ul>
                                    <script type="text/javascript">
                                        var num =${pagination.currentPageNumber};
                                        var div = document.getElementById("page" + num);
                                        var li = document.getElementById("before-pagination");
                                        if (${pagination.currentPageNumber==1}) {
                                            li.setAttribute("class", "active");
                                        }
                                        li = document.getElementById("after-pagination");
                                        if (${pagination.currentPageNumber==pagination.totalPageNumber}) {
                                            li.setAttribute("class", "active");
                                        }
                                        div.setAttribute("class", "active");
                                    </script>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-64 offset-sm-top-5">
                        <div class="range range-xs-center offset-top-0">
                            <div class="cell-sm-3 cell-md-12">
                                <div class="veil-sm reveal-md-block offset-top-0 offset-sm-top-0 offset-md-top-0"
                                     style="margin-top: -20px">
                                    <div class="hr bg-gray-lighter"></div>
                                </div>
                                <div
                                        class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-64 offset-md-top-0">
                                    <p
                                            class="font-accent text-bold text-spacing-50 text-mine-shaft"
                                            style="margin-top: 30px">搜索</p>
                                    <div class="offset-top-5">
                                        <!-- RD Search Form-->
                                        <form action="${pageContext.request.contextPath}/jsp/searchMyBlogs"
                                              method="post"
                                              class="form-search blog-form-search rd-search">
                                            <div class="form-group">
                                                <input type="text" id="search_key" name="search_key" autocomplete="off"
                                                       class="form-search-input form-control null">
                                            </div>
                                            <button type="submit" class="form-search-submit">
                                                <span class="icon icon-sm material-icons-ico material-icons-search"></span>
                                            </button>
                                        </form>
                                    </div>
                                    <div
                                            class="veil-sm reveal-md-block offset-top-30 offset-sm-top-20 offset-md-top-30">
                                        <div class="hr bg-gray-lighter"></div>
                                    </div>
                                    <div class="offset-top-30">
                                        <p style="font-family: 微软雅黑; font-size : 18px; font-weight: bold; color :black ;">
                                            按日期分类</p>
                                    </div>
                                    <div class="offset-top-25">
                                        <ul class="list-marked text-extra-small text-bold p text-left font-accent">
                                            <%--<li><a href="#" class="text-primary"
                                                   style="font-size: 16px;">2016.6</a></li>--%>
                                            <li class="offset-top-10"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?searchBySpecificDate=2017-03-01"
                                                    class="text-primary" style="font-size: 16px;">2017.03</a>
                                            </li>
                                            <li class="offset-top-10"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?searchBySpecificDate=2017-02-01"
                                                    class="text-primary" style="font-size: 16px;">2017.02</a>
                                            </li>
                                            <li class="offset-top-10"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?searchBySpecificDate=2017-01-01"
                                                    class="text-primary" style="font-size: 16px;">2017.01</a>
                                            </li>
                                            <li class="offset-top-10"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?searchBySpecificDate=2016-12-01"
                                                    class="text-primary" style="font-size: 16px;">2016.12</a>
                                            </li>
                                            <li class="offset-top-10"><a
                                                    href="${pageContext.request.contextPath}/jsp/searchMyBlogs?searchBySpecificDate=2016-11-01"
                                                    class="text-primary" style="font-size: 16px;">2016.11</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div
                                            class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
                                        <div class="hr bg-gray-lighter"></div>
                                    </div>
                                </div>
                                <div class="cell-sm-6 cell-md-12 offset-md-top-30">
                                    <p
                                            class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">
                                        按标签分类</p>
                                    <div class="offset-top-20">
                                        <!-- List inline marked-->
                                        <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25 text-left">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=Java"
                                                   class="text-turquoise">JAVA</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=Csh"
                                                   class="text-turquoise">C#</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=CPP"
                                                   class="text-turquoise">C++</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=Android"
                                                   class="text-turquoise">Android</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=CSS"
                                                   class="text-turquoise">CSS</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=HTML"
                                                   class="text-turquoise">HTML</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=IOS"
                                                   class="text-turquoise">IOS</a></li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/jsp/searchMyBlogs?search_key=Javascript"
                                                   class="text-turquoise">Javascript</a></li>
                                        </ul>
                                    </div>
                                    <div
                                            class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
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
<!--所有使用这个页头，并启用修改头像功能的页面必须包含下面这一句-->
<script type="text/javascript" src="js/Jcrop/jquery.Jcrop.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var search_tags = "${searchType.tagsString}".split(" ");
        var tags = "";
        for (var i = 0; i < search_tags.length; i++) {
            if (search_tags[i] == 'CPP')
                tags += " C++";
            else if (search_tags[i] == 'Csh')
                tags += " C#";
            else if (search_tags[i] == 'SQL')
                tags += " 数据库";
            else
                tags += search_tags[i] + " ";
        }
        document.getElementById("search_key").value = tags;
        var totalPageNum = "${pagination.totalPageNumber}";
        if (totalPageNum == "0") {
            var divText = "<div class='offset-top-100' align='center'><h3>您还未发表任何（该类）博客 :(</h3><div><div><br><a class=\"btn btn-xxs btn-round btn-primary\" style=\"color: white\" href=\"${pageContext.request.contextPath}/jsp/postBlog\">去上传</a></div>";
            $("#loadMyBlogDiv").empty().append(divText);
            $("#pageList").empty();
        }
    });
    function deleteBlog(blogId) {
        $.ajax({
            type: "post",//请求方式
            url: "blog/delete",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "blogId": blogId
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.retCode == "1") {
                    //alert("删除成功");
                } else if (data.retCode == "-1") {
                    //alert("恢复成功");
                }
                window.location.reload();
                //alert(data.yourName+"你输入的内容:"+data.yourContent);
            },
            //请求出错的处理
            error: function (data) {
                alert("请求出错了...");
            }
        });
    }
</script>
</body>
</html>
