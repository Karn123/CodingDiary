<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="cst.Page" %>
<%@ page import="cst.Constants" %>
<%@ page import="cst.Message" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>${blog.blogTitle}</title>
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
    <!-- Page Content-->
    <main class="page-content"> <!-- Single Post-->
        <section class="section-75 section-md-top-103 section-md-bottom-60">
            <div class="shell">
                <div class="range">
                    <div class="cell-md-3">
                        <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${blog.userinfoByAuthorId.userId}"><img
                                src="${blog.userinfoByAuthorId.headPortrait}" width="271"
                                height="271" alt=""
                                class="img-circle img-responsive center-block"> </a>
                        <div class="offset-top-25">
                            <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${blog.userinfoByAuthorId.userId}">
                                <h5 class="text-spacing-inverse-25 text-capitalize"
                                    id="username">${blog.userinfoByAuthorId.nickName}</h5>
                            </a>
                        </div>
                    </div>
                    <div class="cell-md-9 text-left">
                        <ul class="post-controls list-inline list-inline-2 list-primary text-center text-md-left">
                            <li><span style="width: 30px;"
                                      class="text-middle icon icon-sm text-primary mdi mdi-clock text-center"></span>
                                <time datetime="2016-01-01"
                                      class="text-sbold text-extra-small text-middle">${blog.publishTime}</time>
                            </li>
                            <li><a href="javascript:void(0)" class="text-middle"><span
                                    class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-group text-right"> </span>
                            </a><span
                                    class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${blog.browseNum}</span>
                            </li>
                            <li><a href="javascript:void(0)" onclick="collectBlog()" class="text-middle">
                                <c:choose>
                                    <c:when test="${isCollected}">
                                <span id="collectSpan"
                                      class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star text-center"></span>
                                    </c:when>
                                    <c:otherwise>
                                      <span id="collectSpan"
                                            class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                                <span id="collectNum"
                                      class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${blog.collectNum}</span>
                            </li>
                            <li style="margin-left: 14px;">
                                <a href="#comment"
                                   class="text-middle"><span
                                        class="text-middle icon icon-sm text-turquoise mdi mdi-comment-multiple-outline text-center"> </span>
                                </a><span id="commentNum"
                                          class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${blog.commentNum}</span>
                            </li>
                            <li><a href="javascript:void(0)" onclick="praiseBlog()" class="text-middle">
                                <c:choose>
                                    <c:when test="${isPraised}">
                                <span id="praiseSpan"
                                      class="text-middle icon icon-sm text-turquoise mdi mdi-thumb-up text-right"></span>
                                    </c:when>
                                    <c:otherwise>
                                      <span id="praiseSpan"
                                            class="text-middle icon icon-sm text-turquoise mdi mdi-thumb-up-outline text-right"></span>
                                    </c:otherwise>
                                </c:choose>

                            </a><span id="praiseNum"
                                      class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${blog.praiseNum}</span>
                            </li>
                            <li><a href="javascript:void(0)" class="text-middle"><span
                                    class="text-middle icon icon-sm text-turquoise mdi mdi-share text-right"> </span>
                            </a><span
                                    class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${blog.forwardNum}</span>
                            </li>
                        </ul>
                        <div class="offset-top-25 offset-md-top-5">
                            <h1
                                    class="text-spacing-inverse-50 text-capitalize text-center text-md-left">${blog.blogTitle}</h1>
                        </div>
                        <div class="offset-top-20 offset-md-top-44">${blog.blogContentByBlogId.blogContent}</div>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <!-- RD Parallax-->
            <div data-on="false" data-md-on="true" class="rd-parallax">
                <div data-speed="0.25" data-type="media"
                     data-url="images/backgrounds/background-08-1920x900.jpg"
                     class="rd-parallax-layer"></div>
                <div data-speed="0" data-type="html" data-md-fade="false"
                     class="rd-parallax-layer">
                    <section style="min-height: 160px; padding-bottom: 39.45%"></section>
                </div>
            </div>
        </section>
        <!-- Conclusion-->
        <section class="section-75 section-md-top-60 section-md-bottom-110">
            <div class="shell">
                <div class="range range-xs-center range-lg-right">
                    <div class="cell-xs-10 cell-sm-9 text-left">
                        <div class="offset-top-30">
                            <div class="hr gray-lighter"></div>
                        </div>
                        <div class="offset-top-55">
                            <p class="font-accent text-bold text-uppercase text-mine-shaft text-spacing-25 text-center text-sm-left">
                                Related Posts</p>
                        </div>
                        <div class="range range-sm-center range-lg-left offset-top-25">
                            <div class="cell-sm-6 cell-lg-5">
                                <div class="inset-left-30 inset-right-30 inset-lg-left-0 inset-lg-right-0">
                                    <!-- Unit-->
                                    <c:forEach items="${recommendBlogs}" begin="0" end="1" var="recommendBlog">
                                        <div
                                                class="unit unit-sm unit-sm-horizontal unit-spacing-xs text-center text-sm-left">
                                            <div class="unit-left">
                                                <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${recommendBlog.userinfoByAuthorId.userId}">
                                                <img src="${recommendBlog.userinfoByAuthorId.headPortrait}"
                                                     alt=" " width="70" height="70" class="img-circle">
                                                </a>
                                            </div>
                                            <div class="unit-body">
                                                <div>
                                                    <p class="text-extra-small text-bold">${recommendBlog.publishTime}</p>
                                                </div>
                                                <div class="offset-top-5">
                                                    <p class="font-accent text-extra-small text-spacing-25 text-uppercase text-bold">
                                                        <a href="singleBlog?blogID=${recommendBlog.blogId}"
                                                           class="text-turquoise">${recommendBlog.blogTitle}</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="offset-top-30">
                                            <div class="hr gray-lighter"></div>
                                        </div>
                                    </c:forEach>
                                    <div class="offset-top-30">
                                        <div class="hr gray-lighter"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="cell-sm-6 offset-top-24 offset-sm-top-0">
                                <div
                                        class="inset-left-30 inset-right-30 inset-lg-left-30 inset-lg-right-25">
                                    <!-- Unit-->
                                    <c:forEach items="${recommendBlogs}" begin="2" end="3" var="recommendBlog">
                                        <div
                                                class="unit unit-sm unit-sm-horizontal unit-spacing-xs text-center text-sm-left">
                                            <div class="unit-left">
                                                <img src="${recommendBlog.userinfoByAuthorId.headPortrait}"
                                                     alt=" " width="70" height="70" class="img-circle">
                                            </div>
                                            <div class="unit-body">
                                                <div>
                                                    <p class="text-extra-small text-bold">${recommendBlog.publishTime}</p>
                                                </div>
                                                <div class="offset-top-5">
                                                    <p class="font-accent text-extra-small text-spacing-25 text-uppercase text-bold">
                                                        <a href="singleBlog?blogID=${recommendBlog.blogId}"
                                                           class="text-turquoise">${recommendBlog.blogTitle}</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="offset-top-30">
                                            <div class="hr gray-lighter"></div>
                                        </div>
                                    </c:forEach>
                                    <div class="offset-top-30">
                                        <div class="hr gray-lighter"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="comment" class="offset-top-55">
                            <p
                                    class="font-accent text-bold text-uppercase text-mine-shaft text-spacing-25">评论区</p>
                        </div>
                        <div class="offset-top-30 inset-lg-right-100">

                            <!-- Box Comment-->
                            <c:forEach items="${blog.blogCommentsByBlogId}" var="comment" step="1"
                                       varStatus="i">
                                <div class="box-comment text-left box-comment-boxed">
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${comment.usercommentByCommentIdNum.userinfoByCommenterId.userId}">
                                                <img src="${comment.usercommentByCommentIdNum.userinfoByCommenterId.headPortrait}" alt=""
                                                     width="70" height="70" class="img-circle box-comment-img">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <header
                                                    class="box-comment-header unit unit-vertical unit-spacing-xxs unit-md unit-md-horizontal unit-md-inverse unit-md-middle unit-md-align-right">
                                                <div class="unit-left">
                                                    <ul
                                                            class="box-comment-meta list-inline list-inline-sm text-dark">
                                                        <li><span
                                                                class="box-comment-icon mdi mdi-clock text-middle"></span>
                                                            <time datetime="2016-01-01"
                                                                  class="text-middle">${comment.usercommentByCommentIdNum.commentTime}</time>
                                                        </li>
                                                        <li><a href="javascript:void(0)"
                                                               onclick="praiseComment(${comment.commentIdNum })"><span
                                                                class="box-comment-icon mdi mdi-thumb-up-outline text-middle"></span><span
                                                                class="text-middle">${comment.usercommentByCommentIdNum.praiseNum}</span>
                                                        </a>
                                                        </li>
                                                        <li><a href="#reply"><span
                                                                class="box-comment-icon mdi mdi-message-outline text-middle"></span><span
                                                                class="text-middle"> reply</span> </a></li>
                                                    </ul>
                                                </div>
                                                <div class="unit-body">
                                                    <div class="box-comment-title text-extra-small text-bold text-spacing-0 text-turquoise">
                                                        <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${comment.usercommentByCommentIdNum.userinfoByCommenterId.userId}">${comment.usercommentByCommentIdNum.userinfoByCommenterId.nickName}
                                                        </a>
                                                    </div>
                                                </div>
                                            </header>
                                            <section class="box-comment-body">
                                                <p class="text-extra-small text-spacing-0 text-silver">${comment.usercommentByCommentIdNum.commentContent}</p>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="offset-top-60">
                                <p
                                        class="font-accent text-bold text-mine-shaft text-spacing-25 text-center text-sm-left">
                                    评论</p>
                                <form data-form-output="form-contact-me"
                                      data-form-type="contact" method="post"
                                      action="bat/rd-mailform.php" novalidate="novalidate"
                                      class="rd-mailform offset-top-15 offset-md-top-30 text-left">
                                    <div class="form-group">
										<textarea id="comments" style="height: 236px;"
                                                  class="form-control"></textarea>
                                        <span class="form-validation"></span>
                                    </div>
                                    <div id="is_empty_error" style="color:red"></div>
                                    <div class="text-center text-sm-right offset-top-30">
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="comment()">提交
                                        </button>
                                    </div>
                                </form>
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
    function comment() {
        var content = document.getElementById("comments").value;
        if (content == "") {
            $("#is_empty_error").empty().append("评论内容不能为空哦0.0");
            return;
        }
        var blogId = ${blog.blogId};
        $.ajax({
            type: "post",//请求方式
            url: "blog/comment",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "blogId": blogId,
                "content": content
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    if (data.retCode == "0") {
                        alert(data.msg);
                    } else {
                        alert("评论成功");
                    }
                    //document.getElementById("commentNum").innerHTML++;
                    window.location.reload();
                }
                //alert(data.yourName+"你输入的内容:"+data.yourContent);
            },
            //请求出错的处理
            error: function (data) {
                alert("请求出错了...");
            }
        });
    }
    function praiseBlog() {
        var blogId = ${blog.blogId};
        $.ajax({
            type: "post",//请求方式
            url: "blog/praise",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "blogId": blogId
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    if (data.retCode == "-1") {
                        //alert("取消成功");
                        document.getElementById("praiseNum").innerHTML--;
                        document.getElementById("praiseSpan").className = "text-middle icon icon-sm text-turquoise mdi mdi-thumb-up-outline text-right";
                    } else if (data.retCode == "1") {
                        //alert("点赞成功");
                        document.getElementById("praiseNum").innerHTML++;
                        document.getElementById("praiseSpan").className = "text-middle icon icon-sm text-turquoise mdi mdi-thumb-up text-right";
                    }
                    //window.location.reload();
                }

                //alert(data.yourName+"你输入的内容:"+data.yourContent);
            },
            //请求出错的处理
            error: function (data) {
                alert("请求出错了...");
            }
        });
    }
    function praiseComment(commentId) {
        $.ajax({
            type: "post",//请求方式
            url: "comment/praise",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "commentId": commentId
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    if (data.retcode == "0000") {
                        //alert("点赞成功");
                    } else if (data.retcode == "0001") {
                        //alert("已经赞过");
                    }
                    window.location.reload();
                }

                //alert(data.yourName+"你输入的内容:"+data.yourContent);
            },
            //请求出错的处理
            error: function (data) {
                alert(data.msg);
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    alert("请求出错了...");
                }
            }
        });
    }
    function collectBlog() {
        var blogId = ${blog.blogId};
        $.ajax({
            type: "post",//请求方式
            url: "blog/collect",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "blogId": blogId
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    if (data.retCode == "-1") {
                        //alert("取消收藏");
                        document.getElementById("collectNum").innerHTML--;
                        document.getElementById("collectSpan").className = "text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center";
                    } else if (data.retCode == "1") {
                        //alert("收藏成功");
                        document.getElementById("collectNum").innerHTML++;
                        document.getElementById("collectSpan").className = "text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star text-center";
                    }
                    //alert(data.retCode);
                    //window.location.reload();
                }

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
