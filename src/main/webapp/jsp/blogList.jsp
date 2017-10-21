<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="cst.Constants" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI() + "?" + request.getQueryString();
%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>blog list</title>
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
    <jsp:include page="header.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("blog");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main
            class="page-content section-75 section-md-top-0 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar-->
        <section>
            <div class="shell">
                <div class="range range-xs-center range-lg-left offset-top-50">

                    <div class="cell-sm-10 cell-md-8 cell-md-push-1">

                        <!-- Post Classic-->
                        <c:forEach items="${list}" var="blog" step="1" varStatus="i">
                            <article class="post-classic text-left">

                                <div class="post-classic-body offset-top-30">
                                    <div class="unit unit-sm unit-sm-horizontal unit-sm-inverse">
                                        <div class="unit-body">
                                            <!-- Post Body-->
                                            <div class="post-body offset-top-15 offset-md-top-25">
                                                <div class="cell-xs-8 cell-sm-5" style='margin-top: -55px;'>
                                                    <!-- Post Vacancy-->
                                                    <a href="singleBlog?blogID=${blog.blogId }" class="post-vacancy">
                                                        <!-- Unit-->
                                                        <span
                                                                class="unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left"><span
                                                                class="unit-left"></span><span class="unit-body"> <span
                                                                class="post-meta"> <span
                                                                class="text-spacing-0">${blog.publishTime}</span>
														</span> <span class="post-title  reveal-block"> <span
                                                                class="offset-top-5 font-accent text-bold text-spacing-50 text-uppercase"><span
                                                                class="text-mine-shaft">${blog.blogTitle}</span> </span> </span>
															<br>
												</span>
                                                    </a>
                                                </div>

                                            </div>

                                        </div>
                                        <div class="unit-right" style='margin-top: 10px;'>
                                            <div
                                                    class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                                <div class="unit-left">
                                                    <img src="${blog.userinfoByAuthorId.headPortrait}" alt=" "
                                                         width="70" height="70" class="img-circle">
                                                </div>
                                                <div class="unit-body text-center offset-top-4">
                                                    <p class="text-extra-small text-spacing-0 text-bold">
                                                        <cite class="text-normal"><a
                                                                href="skipToMyPageAction_skipToOtherPage?userId=${blog.userinfoByAuthorId.userId}"
                                                                class="text-turquoise">${blog.userinfoByAuthorId.nickName}</a>
                                                        </cite>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <br>
                        </c:forEach>
                        <div class="offset-top-35">
                            <!-- Post Classic-->
                            <div class="offset-top-50">
                                <!-- Classic Pagination-->
                                <nav>
                                    <ul
                                            class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">
                                        <li id="before-pagination" class="text-regular"><a
                                                href="myBlog?page_current=${pagination.currentPageNumber-1}"
                                                class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>
                                        </li>
                                        <c:forEach var="i" begin="${pagination.beginNumber}"
                                                   end="${pagination.endNumber}">
                                            <li id="page${i}"><a href="myBlog?page_current=${i}"> ${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li id="after-pagination" class="text-regular"><a
                                                href="myBlog?page_current=${pagination.currentPageNumber+1}"
                                                class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>
                                        </li>
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
                    <div
                            class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-64 offset-md-top-0">
                        <p
                                class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase"
                                style="margin-top: 30px">搜索文章</p>
                        <div class="offset-top-10">
                            <!-- RD Search Form-->
                            <form action="skipToBlogListAction_searchBlog?type=${type }&type_value=CPP${isuserId}"
                                  method="post"
                                  class="form-search blog-form-search rd-search">
                                <div class="form-group">
                                    <input id="searchtext" type="text" name="searchtext" autocomplete="off"
                                           class="form-search-input form-control null">
                                </div>
                                <button type="submit" class="form-search-submit" onclick="search()">
									<span
                                            class="icon icon-sm material-icons-ico material-icons-search"></span>
                                </button>
                            </form>
                        </div>
                        <div class="offset-top-35">
                            <div class="hr bg-gray-lighter"></div>
                        </div>
                        <br>
                        <div class="range range-xs-center offset-top-55">
                            <div class="cell-sm-3 cell-md-12" style="margin-top: -120px">
                                <p class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase offset-top-55">
                                    博文分类</p>
                                <div class="offset-top-10">
                                    <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25 text-left">
                                        <li><a href="${pageContext.request.contextPath}/blog/search?type=${type}&type_value=CPP${isuserId}"
                                               class="text-primary">C++</a>
                                        </li>
                                        <li class="offset-top-10"><a
                                                href="${pageContext.request.contextPath}/blog/search?type=${type }&type_value=Java${isuserId}"
                                                class="text-primary">Java</a>
                                        </li>
                                        <li class="offset-top-10"><a
                                                href="${pageContext.request.contextPath}/blog/search?type=${type }&type_value=PHP${isuserId}"
                                                class="text-primary">PHP</a>
                                        </li>
                                        <li class="offset-top-10"><a
                                                href="${pageContext.request.contextPath}/blog/search?type=${type }&type_value=SQL${isuserId}"
                                                class="text-primary">数据库</a>
                                        </li>
                                        <li class="offset-top-10"><a
                                                href="${pageContext.request.contextPath}/blog/search?type=${type }&type_value=IOS${isuserId}"
                                                class="text-primary">IOS</a>
                                        </li>
                                        <%--<li class="offset-top-10"><a href="#" class="text-primary">其他</a>--%>
                                        <%--</li>--%>
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
<script type="text/javascript">
    function search() {
        var text = document.document.getElementById("searchtext").value;
        $.ajax({
            type: "post",//请求方式
            url: "${pageContext.request.contextPath}/searchBlogAction_searchBlog",
            timeout: 80000,//超时时间：80秒
            dataType: "json",//设置返回数据的格式
            data: {
                "typeValue": text
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.retcode == "0001") {
                    alert("已经存在");
                }
                else if (data.retcode == "0000") {
                    AddElementInChoosing("tag" + num, new_tag);
                    num++;
                } else {
                    alert("请求出错");
                }
                //alert(data.yourName+"你输入的内容:"+data.yourContent);
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