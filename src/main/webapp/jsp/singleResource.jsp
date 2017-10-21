<%@ page import="cst.Message" %><%--
  Created by IntelliJ IDEA.
  User: Karn
  Date: 2017/2/1
  Time: 23:02
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
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
    <title>资源详情</title>
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
    <style type="text/css">
        @media screen and (min-width: 480px) {
            .unit-right {
                right: 40%;
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
        var div = document.getElementById("data");
        div.setAttribute("class", "active");
    </script>
    <!-- Single Post-->
    <section class="section-75 section-md-top-103 section-md-bottom-60">
        <div class="shell">
            <div class="range">
                <div class="cell-md-4">
                    <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${resourceMap.resourceUploaderID}"><img
                            src="${resourceMap.uploaderImageUrl}" width="271"
                            height="271" alt="" class="img-circle img-responsive center-block">
                    </a>
                    <div class="offset-top-25">
                        <h5 class="text-spacing-inverse-25 text-capitalize" id="username">${resourceMap.resourceUploaderName}</h5>
                    </div>
                </div>
                <div class="cell-md-8 text-left">
                    <div><span style="width: 30px;" class="text-middle icon icon-sm text-primary mdi mdi-clock text-center"></span>
                    <time id="uploadTime" class="text-sbold text-extra-small text-middle">${resourceMap.resourceUploadTime.substring(0,resourceMap.resourceUploadTime.indexOf('.'))}</time>
                    <a style="margin-left: 10px;" class="text-middle" title="浏览次数"><span class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-group text-right">&nbsp;</span>
                    </a><span class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.browseNum}</span>
                    </div>
                    <div class="offset-top-25 offset-md-top-5">
                        <div class="group">
                            <div>
                                <h3 class="text-spacing-inverse-50 text-capitalize text-center text-md-left">${resourceMap.resourceName}</h3>
                            </div>
                            <div class="bshare-custom">
                                <a title="分享到微信" class="bshare-weixin"></a>
                                <a title="分享到QQ空间" class="bshare-qzone"></a>
                                <a title="分享到QQ好友" class="bshare-qqim"></a>
                                <a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
                                <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
                            </div>
                                    <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=3&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
                        </div>
                        <div class="offset-top-10">
                        <p class="text-spacing-0" style="color:#27b7c1;">${resourceMap.resourceDescription}</p>
                        <div class="hr gray-lighter offset-top-10"></div>
                    </div>
                </div>

                        <div class="unit-right unit-body offset-top-25" style="float: right; position: relative; margin-right:20px;">
                            <a href="${pageContext.request.contextPath}/jsp/resource/download?resourceID=${resourceMap.resourceID}&uploaderID=${resourceMap.resourceUploaderID}&resourceName=${resourceMap.resourceName}"><button class="btn btn-circle btn-primary btn-xl wryh">下载</button></a></div>

                    <div class="unit unit-sm unit-sm-horizontal unit-spacing-sm text-left">
                    <div class="unit-left">
                        <div style="line-height:24px;" class="offset-top-20">
                            <dl class="text-bold text-spacing-50">
                                <dt class="reveal-inline-block">下载次数:</dt>
                                <dd class="reveal-inline-block text-mine-shaft">${resourceMap.downloadNum}</dd>
                            </dl>
                            <br/>
                            <dl class="text-bold text-spacing-50">
                                <dt class="reveal-inline-block">资源大小:</dt>
                                <dd class="reveal-inline-block text-mine-shaft">${resourceMap.resourceSize}</dd>
                            </dl>
                            <br/>
                            <dl class="text-bold text-spacing-50">
                                <dt class="reveal-inline-block">资源评分:</dt>
                                <dd id="resourceScore" class="reveal-inline-block text-mine-shaft">${resourceMap.resourceScore}</dd>
                            </dl>
                            <br/>
                        </div>
                    </div>
                    </div>
                            <div class="demo group">
                                <div id="point" class="target-demo"></div>
                                <div id="precision-hint" class="hint"></div>
                            </div>
                            <ul class="list-inline list-marked list-marked-type-2 list-marked-type-2-dot-1 list-silver-chalice list-marked-silver-chalice font-accent text-bold text-spacing-inverse-25 inset-sm-left-4 offset-top-10">
                                <c:forEach var="tag" items="${resourceMap.resourceTags.split(' ')}">
                                    <c:if test="${tag =='C++'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=CPP" class="text-turquoise">${tag}</a></li>
                                    </c:if>
                                    <c:if test="${tag =='C#'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=Csh" class="text-turquoise">${tag}</a></li>
                                    </c:if>
                                    <c:if test="${tag =='数据库'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=SQL" class="text-turquoise">${tag}</a></li>
                                    </c:if>
                                    <c:if test="${tag !='C#' and tag!='C++' and tag !='数据库'}">
                                        <li><a href="${pageContext.request.contextPath}/jsp/searchResults?table_name=Resource&search_key=${tag}" class="text-turquoise">${tag}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                             <ul class="post-controls list-inline list-inline-2 list-primary text-md-left offset-top-15">
                                <c:if test="${resourceMap.ifCollected =='false'}">
                                    <li><a href="javascript:void(0)" onclick="collectClick()" class="text-middle" title="收藏"><span id="collectSpan" class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center">&nbsp;</span></a>
                                        <span id="collectNum" class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.collectNum}</span>
                                    </li>
                                </c:if>
                                <c:if test="${resourceMap.ifCollected == 'true'}">
                                    <li><a href="javascript:void(0)" onclick="collectClick()" class="text-middle" title="收藏"><span id="collectSpan" class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star text-center">&nbsp;</span></a>
                                        <span id="collectNum" class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.collectNum}</span>
                                    </li>
                                </c:if>

                                <c:if test="${resourceMap.ifPraised == 'false'}">
                                    <li style="margin-left: 10px;"><a href="javascript:void(0)" onclick="praiseClick()" class="text-middle" title="点赞"><span id="praiseSpan" class="text-middle icon icon-sm text-turquoise mdi mdi-thumb-up-outline text-right">&nbsp;</span></a><span id="praiseNum" class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.praiseNum}</span></li>
                                </c:if>
                                <c:if test="${resourceMap.ifPraised == 'true'}">
                                    <li style="margin-left: 10px;"><a href="javascript:void(0)" onclick="praiseClick()" class="text-middle" title="点赞"><span id="praiseSpan" class="text-middle icon icon-sm text-turquoise mdi mdi-thumb-up text-right">&nbsp;</span></a><span id="praiseNum" class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.praiseNum}</span></li>
                                </c:if>

                                <li style="margin-left: 10px;"><a href="javascript:void(0)" onclick="forwardClick()" class="text-middle" title="转发"><span class="text-middle icon icon-sm text-turquoise mdi mdi-share text-right">&nbsp;</span>
                                </a><span class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.forwardNum}</span>
                                </li>
                                <li style="margin-left: 10px;"><a href="#comments" class="text-middle" title="评论"><span class="text-middle icon icon-sm text-turquoise mdi mdi-comment-multiple-outline text-center">&nbsp;</span></a><span id="commentNum" class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${resourceMap.commentNum}</span></li>
                                <%--<li style="margin-left: 10px;"><a href="javascript:void(0)" class="text-middle" title="分享"><span class="text-middle icon icon-sm text-turquoise mdi mdi-share-variant text-right">&nbsp;</span>--%>
                                <%--</a></li>--%>
                                <%--<li><a href="#" class="text-middle"><span class="text-middle icon icon-sm text-turquoise mdi mdi-heart-outline text-center"></span></a><span class="inset-left-5 text-sbold text-extra-small text-silver-chalice">542</span></li>--%>
                            </ul>



                </div>
            </div>
        </div>
    </section>
    <section class="section-75 section-md-top-60 section-md-bottom-110">
        <div class="shell">
            <div class="range range-xs-center range-lg-right">
                <div class="cell-xs-10 cell-sm-9 text-left">
                    <div class="offset-top-30">
                        <div class="hr gray-lighter"></div>
                    </div>
                    <div class="offset-top-55">
                        <p class="font-accent text-bold text-uppercase text-mine-shaft text-spacing-25 text-center text-sm-left">
                            你或许会感兴趣的资源</p>
                    </div>
                    <div class="range range-sm-center range-lg-left offset-top-25">
                        <div class="cell-sm-6 cell-lg-5">
                            <div class="inset-left-30 inset-right-30 inset-lg-left-0 inset-lg-right-0">
                                <!-- Unit-->
                                <c:forEach items="${recommendResources}" begin="0" end="1" var="recommendResource">
                                    <div class="unit unit-sm unit-sm-horizontal unit-spacing-xs text-center text-sm-left offset-top-20">
                                        <div class="unit-left">
                                            <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${recommendResource.userinfoByUploaderId.userId}">
                                            <img src="${recommendResource.userinfoByUploaderId.headPortrait}"
                                                 alt=" " width="70" height="70" class="img-circle"/>
                                            </a>
                                        </div>
                                        <div class="unit-body">
                                            <div>
                                                <p class="text-extra-small text-bold">${recommendResource.uploadTime}</p>
                                            </div>
                                            <div class="offset-top-5">
                                                <p class="font-accent text-extra-small text-spacing-25 text-uppercase text-bold">
                                                    <a href="singleResource?resourceID=${recommendResource.resourceId}"
                                                       class="text-turquoise">${recommendResource.resourceName}</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="offset-top-30">
                                        <div class="hr gray-lighter"></div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="cell-sm-6 offset-top-24 offset-sm-top-0">
                            <div
                                    class="inset-left-30 inset-right-30 inset-lg-left-30 inset-lg-right-25">
                                <!-- Unit-->
                                <c:forEach items="${recommendResources}" begin="2" end="3" var="recommendResource">
                                    <div class="unit unit-sm unit-sm-horizontal unit-spacing-xs text-center text-sm-left offset-top-20">
                                        <div class="unit-left">
                                            <img src="${recommendResource.userinfoByUploaderId.headPortrait}"
                                                 alt=" " width="70" height="70" class="img-circle">
                                        </div>
                                        <div class="unit-body">
                                            <div>
                                                <p class="text-extra-small text-bold">${recommendResource.uploadTime}</p>
                                            </div>
                                            <div class="offset-top-5">
                                                <p class="font-accent text-extra-small text-spacing-25 text-uppercase text-bold">
                                                    <a href="singleResource?resourceID=${recommendResource.resourceId}"
                                                       class="text-turquoise">${recommendResource.resourceName}</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="offset-top-30">
                                        <div class="hr gray-lighter"></div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <br> <br>
                    <div class="offset-top-55">
                        <p class="font-accent text-bold text-uppercase text-mine-shaft text-spacing-25">评论区</p>
                    </div>
                    <div id="comments" class="offset-top-30 inset-lg-right-100"></div>
                    <br> <br>
                    <div align="center">
                        <input ID="loadNextBtn" value="显示更多评论" type="button" class="btn btn-primary shadow-drop-md" onclick="loadNextPage()"/>
                    </div>
                    <div class="offset-top-60">
                        <p class="font-accent text-bold text-mine-shaft text-spacing-25 text-center text-sm-left">
                            写下你的评论吧~</p>
                        <form data-form-output="form-contact-me" data-form-type="contact"
                              method="post" novalidate="novalidate"
                              class="rd-mailform offset-top-15 offset-md-top-30 text-left">
                            <div class="form-group">
							<textarea id="myComment" style="height: 236px;"
                                      class="form-control" required></textarea>
                                <span class="form-validation"></span>
                            </div>
                            <div id="comment_remind" style="color: red"></div>
                            <div class="text-center text-sm-right offset-top-30">
                                <button type="submit" class="btn btn-sm btn-primary"
                                        onclick="submitComment()">提交
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
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
</body>

<!-- Java script-->
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>

<script type="text/javascript" src="js/raty-2.5.2/lib/jquery.raty.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
    var commenterImage = "<div class=\"box-comment text-left box-comment-boxed\"><div class=\"media\"><div class=\"media-left\"><img src=\"";
    var imageUrl = "";
    var imageFinal = "\" alt=\"\" width=\"70\" height=\"70\" class=\"img-circle box-comment-img\"></a></div><div class=\"media-body\"><header class=\"box-comment-header unit unit-vertical unit-spacing-xxs unit-md unit-md-horizontal unit-md-inverse unit-md-middle unit-md-align-right\"><div class=\"unit-left\"><ul class=\"box-comment-meta list-inline list-inline-sm text-dark\"><li><span class=\"box-comment-icon mdi mdi-clock text-middle\"></span><time datetime=\"\" class=\"text-middle\">";
    var commentTime = "";

    //commentPraise ="<li><a href=\"#like\"><span class=\"box-comment-icon mdi mdi-thumb-up-outline text-middle\"></span><span class=\"text-middle\">赞</span></a></li>";
    //commentComment = "<li><a href=\"#reply\"><span class=\"box-comment-icon mdi mdi-message-outline text-middle\"></span><span class=\"text-middle\">回复</span></a></li>";
    var commentPraise = "</time></li>";
    var commentComment = "</ul></div>";
    var commentContent_2 = "<div class=\"unit-body\"><div class=\"box-comment-title text-extra-small text-bold text-spacing-0 text-turquoise\">";
    var commenterName_ = "";
    var commentContent_3 = "</div></div></header>";
    var commentContent_4 = "<section class=\"box-comment-body\"><p class=\"text-extra-small text-spacing-0 text-silver\">";
    var userCommentContent = "";
    var commentContent_5 = "</p></section></div></div></div>";

    var flag = true;
    var comment_page_index = 0;
    $(document).ready(loadNextPage());

    function loadNextPage(){
        //加载评论
        $.ajax({
            type: "post",//请求方式
            url: "${pageContext.request.contextPath}/jsp/resource/loadComment",
            timeout: 8000,//超时时间：8s
            dataType: "json",//设置返回数据的格式
            data: {
                "resourceID": ${resourceMap.resourceID},
                "pageNum": comment_page_index
            },
            success: function (data) {
                if(data.length > 0 ) {
                    comment_page_index++;
                    for ( var i = 0; i < data.length; i++) {
                        flag = false;
                        var commentInfo = data[i];
                        var commenterID = commentInfo.commenterID;
                        commenterImage = "<div class=\"box-comment text-left box-comment-boxed\"><div class=\"media\"><div class=\"media-left\"><a href=\"${pageContext.request.contextPath}/jsp/otherPage?this_id="+commenterID+"\"><img src=\"";
                        var commenterName = "<a href=\"${pageContext.request.contextPath}/jsp/otherPage?this_id="+commenterID+"\">"+commentInfo.commenterName+"</a>";
                        var commentContent = "<span style='color:black;font-size:18px'>" + commentInfo.commentContent + "</span>";
                        //var praiseNum = commentInfo.praiseCommentsByCommentId.length;
                        var resourceCommentTime = commentInfo.commentTime.split(".")[0];
                        imageUrl = commentInfo.commenterImageUrl;
                        var divText = commenterImage + imageUrl + imageFinal
                            + resourceCommentTime + commentPraise + commentComment
                            + commentContent_2 + commenterName + commentContent_3
                            + commentContent_4 + commentContent + commentContent_5;
                        $("#comments").append(divText);
                    }
                }else{
                    if(flag) {
                        flag = false;
                        $("#loadNextBtn").attr("class","invisible");
                    }
                    else {
                        $("#loadNextBtn").attr("value","已经加载全部评论");
                        $("#loadNextBtn").attr("class","btn btn-primary shadow-drop-md disabled");
                    }
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            }
        });
    }

    function submitComment() {
        var commentText = $("#myComment").val();
        if (commentText == "") {
            $("#comment_remind").empty().append("评论内容不能为空哦0.0");
            return;
        }
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/jsp/resource/comment",
            timeout: 80000,
            dataType: "json",
            data: {
                "resourceID":  ${resourceMap.resourceID},
                "commentText": commentText
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                var map = data;
                var resourceCommentTime = map.commentTime.split(".")[0];
                var commenterID = map.commenterID;
                var commenterName = "<a href=\"${pageContext.request.contextPath}/jsp/otherPage?this_id="+commenterID+"\">"+map.commenterName+"</a>";
                commenterImage = "<div class=\"box-comment text-left box-comment-boxed\"><div class=\"media\"><div class=\"media-left\"><a href=\"${pageContext.request.contextPath}/jsp/otherPage?this_id="+commenterID+"\"><img src=\"";
                var commentText = "<span style='color:black;font-size:18px'>" + map.commentContent + "</span>";
                var addComment = "";
                var commentNum = map.commentNum;
                imageUrl = map.commenterImage;
                addComment += commenterImage + imageUrl + imageFinal + resourceCommentTime
                    + commentPraise + commentComment + commentContent_2 + commenterName
                    + commentContent_3 + commentContent_4 + commentText + commentContent_5;
                $("#comments").append(addComment);
                $("#commentNum").empty().append(commentNum);
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错了");
            }
        });
    }

    function praiseClick() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/jsp/resource/praise",
            timeout: 80000,
            dataType: "json",
            data: {
                "resourceID":  ${resourceMap.resourceID}
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    var retObj = data;
                    if (retObj.is_have_praised == "true") {
                        document.getElementById("praiseSpan").className = "text-middle icon icon-sm text-turquoise mdi mdi-thumb-up-outline text-right";
                    }
                    else {
                        document.getElementById("praiseSpan").className = "text-middle icon icon-sm text-turquoise mdi mdi-thumb-up text-right";
                    }
                    var praiseCount = retObj.praise_num;
                    $("#praiseNum").empty().append(praiseCount);
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            }
        });
    }

    function collectClick() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/jsp/resource/collect",
            timeout: 8000,
            dataType: "json",
            data: {
                "resourceID":  ${resourceMap.resourceID}
            },
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                } else {
                    var retObj = data;
                    if (retObj.is_have_collected == "true") {
                        document.getElementById("collectSpan").className = "text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center";
                    }
                    else {
                        document.getElementById("collectSpan").className = "text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star text-center";
                    }
                    var collectCount = retObj.collect_num;
                    $("#collectNum").empty().append(collectCount);
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            }
        });
    }
    function forwardClick(){
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/jsp/resource/forward",
            timeout: 8000,
            dataType: "json",
            data: {
                "resourceID":  ${resourceMap.resourceID}
            },
            success: function (data) {
                if (data.msg == "notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg == "noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                }
            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            }
        });
    }
    $("#point").raty(
        {
            path      : 'js/raty-2.5.2/demo/img',
            cancelOff : 'cancel-off-big.png',
            cancelOn  : 'cancel-on-big.png',
            size      :  24,
            starHalf  : 'star-half-big.png',
            starOff   : 'star-off-big.png',
            starOn    : 'star-on-big.png',
            target    : '#precision-hint',
            cancel    : false,
            targetKeep: true,
            precision : true,
            click: function(score, evt) {
                var score_ =  document.getElementById("precision-hint").innerText;
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/jsp/resource/evaluate",
                    timeout: 8000,
                    dataType: "json",
                    data: {
                        "resourceID":  ${resourceMap.resourceID},
                        "score": score_
                    },
                    success: function (data) {
                        if (data.msg == "notLogIn") {
                            alert("<%=Message.NOTLOGIN%>");
                            window.location.href = "userLogIn";
                        } else if (data.msg == "noAuthority") {
                            alert("<%=Message.NOAUTHORITY%>");
                        } else {
                            $("#point").raty(
                                {
                                    path: 'js/raty-2.5.2/demo/img',
                                    cancelOff: 'cancel-off-big.png',
                                    cancelOn: 'cancel-on-big.png',
                                    size: 24,
                                    starHalf: 'star-half-big.png',
                                    starOff: 'star-off-big.png',
                                    starOn: 'star-on-big.png',
                                    target: '#precision-hint',
                                    targetKeep: true,
                                    precision: true,
                                    readOnly: true,
                                    score: score_
                                });
                            $("#resourceScore").empty().append(data.resource_score);
                            $("#precision-hint").empty().append("感谢您的评价！");

                            //alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
                        }
                    },
                    //请求出错的处理
                    error: function () {
                        alert("请求出错");
                    }
                });
            }
        });
</script>
</html>