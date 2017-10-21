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
<%int k = 1;%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <!-- Site Title-->
    <title>单条帖子</title>
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
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("post");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-0 section-md-bottom-110">
        <!-- Single Post-->
        <section class="section-75 section-md-top-60 section-md-bottom-60">
            <div class="shell">
                <div class="range">
                    <h3 class="text-spacing-inverse-50 text-capitalize text-center text-md-left">${forumpost.forumpostTitle}</h3>
                    <div class="offset-top-10 inset-lg-right-100">
                        <!-- Box Comment-->
                        <div class="box-comment text-left box-comment-boxed">
                            <div class="media">
                                <div class="media-left">
                                    <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                        <div class="unit-left">
                                            <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${forumpost.userinfoByAuthorId.userId}">
                                                <img src="${forumpost.userinfoByAuthorId.headPortrait}"
                                                                    alt=" " width="70" height="70" class="img-circle">
                                        </div>
                                        <div class="unit-body text-center offset-top-4">
                                            <p class="text-extra-small text-spacing-0 text-bold">
                                                <cite class="text-normal"><a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${forumpost.userinfoByAuthorId.userId}"
                                                                             class="text-turquoise">${forumpost.forumpostFloorByFirstFloorId.userinfoByAuthorId.nickName}</a></cite>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="media-body">
                                    <header class="box-comment-header unit unit-vertical unit-spacing-xxs unit-md unit-md-horizontal unit-md-inverse unit-md-middle unit-md-align-right">
                                        <div class="unit-left">
                                            <ul class="box-comment-meta list-inline list-inline-sm text-dark">
                                                <li><span class="box-comment-icon mdi mdi-clock text-middle"></span>
                                                    <time datetime="2016-01-01"
                                                          class="text-middle">${forumpost.forumpostFloorByFirstFloorId.publishTime }</time>
                                                </li>
                                                <li><a class="text-middle"><span
                                                        class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-group text-right"></span><span
                                                        class="text-middle">浏览</span>
                                                </a><span
                                                        class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${forumpost.forumpostFloorByFirstFloorId.browseNum}</span>
                                                </li>
                                                <li><a href="javascript:void(0)" onclick="collectForumpost(${forumpost.forumpostId})" class="text-middle">
                                                    <span id="collectSpan" class="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center"></span><span
                                                       class="text-middle">收藏</span></a>

                                                    <span class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${forumpost.collectNum}</span>
                                                </li>
                                                <li><a href="javascript:void(0)" onclick="praiseFloor(${forumpost.forumpostFloorByFirstFloorId.floorId},0)"><span
                                                       id="praiseSpan0" class="box-comment-icon mdi mdi-thumb-up-outline text-middle"></span><span
                                                        class="text-middle">赞</span></a>
                                                    <span
                                                            class="inset-left-5 text-sbold text-extra-small text-silver-chalice">${forumpost.forumpostFloorByFirstFloorId.praiseNum}</span>
                                                </li>
                                                <li><a href="#reply" onclick="addreply(0)"><span
                                                        class="box-comment-icon mdi mdi-message-outline text-middle"></span><span
                                                        class="text-middle">回复</span></a></li>
                                            </ul>
                                        </div>
                                        <div class="unit-body">
                                            <div class="box-comment-title text-extra-small text-bold text-spacing-0 text-turquoise">
                                                楼主
                                            </div>
                                        </div>
                                    </header>
                                    <section class="box-comment-body">
                                        <p class="text-extra-small text-spacing-0 text-silver"> ${forumpost.forumpostFloorByFirstFloorId.floorContent } </p>
                                    </section>
                                </div>
                            </div>

                            <!-- Box Comment-->

                            <c:forEach items="${floorList}" var="floor" step="1"
                                       varStatus="i">

                                <div class="box-comment text-left box-comment-boxed">
                                    <div class="media">
                                        <div class="media-left">
                                            <div class="unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs">
                                                <div class="unit-left">
                                                    <a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${floor.userinfoByAuthorId.userId}">
                                                       <img
                                                        src="${floor.userinfoByAuthorId.headPortrait}" alt=" "
                                                        width="70" height="70" class="img-circle">
                                                    </a>
                                                </div>
                                                <div class="unit-body text-center offset-top-4">
                                                    <p class="text-extra-small text-spacing-0 text-bold">
                                                        <cite class="text-normal"><a href="${pageContext.request.contextPath}/jsp/otherPage?this_id=${floor.userinfoByAuthorId.userId}"
                                                                                     class="text-turquoise">${floor.userinfoByAuthorId.nickName}</a></cite>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="media-body">
                                            <header class="box-comment-header unit unit-vertical unit-spacing-xxs unit-md unit-md-horizontal unit-md-inverse unit-md-middle unit-md-align-right">
                                                <div class="unit-left">
                                                    <ul class="box-comment-meta list-inline list-inline-sm text-dark">
                                                        <li><span
                                                                class="box-comment-icon mdi mdi-clock text-middle"></span>
                                                            <time datetime="2016-01-01"
                                                                  class="text-middle">${floor.publishTime}</time>
                                                        </li>
                                                        <li><a href="javascript:void(0)" onclick="praiseFloor(${floor.floorId },${i.count})"><span
                                                               id="praiseSpan${i.count}" class="box-comment-icon mdi mdi-thumb-up-outline text-middle"></span><span
                                                                class="text-middle"> 赞${floor.praiseNum}</span></a>
                                                        </li>
                                                        <li><a href="#reply" onclick="addreply(<%=k%>)"><span
                                                                class="box-comment-icon mdi mdi-message-outline text-middle"></span><span
                                                                class="text-middle"> 回复</span></a></li>
                                                    </ul>
                                                </div>
                                                <div class="unit-body">
                                                    <div class="box-comment-title text-extra-small text-bold text-spacing-0 text-turquoise"><%=k++ %>
                                                        楼
                                                    </div>
                                                </div>
                                            </header>
                                            <section class="box-comment-body">
                                                <p class="text-extra-small text-spacing-0 text-silver">${floor.floorContent}</p>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="offset-top-60">
                                <p id="reply"
                                   class="font-accent text-bold text-mine-shaft text-spacing-25 text-center text-sm-left">
                                    回帖</p>
                                <form data-form-output="form-contact-me" data-form-type="contact" method="post"
                                      action="bat/rd-mailform.php" novalidate="novalidate"
                                      class="rd-mailform offset-top-15 offset-md-top-15 text-left">
                                    <div class="form-group">
                                        <div id="main" role="main">
                                            <div id="customized-buttonpane" class="editor">

                                            </div>
                                            <%--<span class="form-validation"></span>--%>
                                        </div>
                                    </div>
                                </form>
                                <form data-form-output="form-contact-me" data-form-type="contact" method="post"
                                      action="bat/rd-mailform.php" novalidate="novalidate"
                                      class="rd-mailform offset-top-15 offset-md-top-30 text-left">
                                <%--    <div class="form-group">
                                        <textarea id="comments" style="height: 236px;"
                                                  class="form-control"></textarea><span class="form-validation"></span>
                                    </div>--%>
                                    <div id="is_empty_error" style="color:red"></div>
                                    <div id="reply" class="text-center text-sm-center offset-top-30">
                                        <button type="submit" class="btn btn-sm btn-primary" onclick="comment(${forumpost.forumpostId})">提交
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
        </section>
        <section>
            <!-- RD Parallax-->
            <div data-on="false" data-md-on="true" class="rd-parallax">
                <div data-speed="0.25" data-type="media" data-url="images/backgrounds/background-08-1920x900.jpg"
                     class="rd-parallax-layer"></div>
                <div data-speed="0" data-type="html" data-md-fade="false" class="rd-parallax-layer">
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
    function comment(data) {
        var content = $('#customized-buttonpane').trumbowyg('html');
        if (content == "") {
            $("#is_empty_error").empty().append("评论内容不能为空哦0.0");
            return;
        }
        var forumpostId = data;
        $.ajax({
            type: "post",//请求方式
            url: "forumpost/comment",
            timeout: 8000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "forumpostId": forumpostId,
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
                        //alert(data.msg);
                    } else {
                        //alert("评论成功");
                    }
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
    function praiseFloor(floorID,i) {
         var floorId=floorID;
        $.ajax({
            type: "post",//请求方式
            url: "forumpost/praise",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "floorId": floorId
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
                        //alert("取消成功");
                        //document.getElementById("praiseFloorSpan"+i).className="box-comment-icon mdi mdi-thumb-up-outline text-middle";
                    } else if (data.retCode == "1") {
                        //alert("点赞成功");
                        //document.getElementById("praiseFloorSpan"+i).className="box-comment-icon mdi mdi-thumb text-middle";
                    }
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
    function  addreply(i) {
       // alert(2);
        if(i==0)
        {
            $('#customized-buttonpane').empty().append("@楼主");
            //alert(2);
        }
        else
        $('#customized-buttonpane').empty().append("@"+i+"楼");
    }
    function collectForumpost(postId){
        var forumpostId = postId;
        $.ajax({
            type: "post",//请求方式
            url: "forumpost/collect",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "forumpostId": forumpostId
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
                        //alert("取消收藏");
                        document.getElementById("collectSpan").className="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star_border text-center";
                    } else if (data.retCode == "1") {
                        //alert("收藏成功");
                        document.getElementById("collectSpan").className="text-middle icon icon-sm text-turquoise material-icons-ico material-icons-star text-center";
                    }
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
</script>
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
<script src="js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<%--<script src="trumbowyg/jquery.min.js"></script>--%>
<script src="trumbowyg/trumbowyg.min.js"></script>
<script src="trumbowyg/langs/zh_cn.min.js"></script>
<script src="trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
<script src="trumbowyg/plugins/base64/trumbowyg.base64.min.js"></script>
<link rel="stylesheet" href="trumbowyg/design/css/trumbowyg.css">
<style type="text/css">
    html, body {
        margin: 0;
        padding: 0;
        background-color: #F2F2F2;
        font-family: "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }

    header {
        text-align: center;
    }

    #main {
        max-width: 960px;
        margin: 0 auto;
    }
</style>

<script type="text/javascript">
    $.trumbowyg.btnsGrps.test = ['bold', 'link'];
    /* Add new words for customs btnsDef just below */
    $.extend(true, $.trumbowyg.langs, {
        zh_cn: {
            align: 'Alignement',
            image: 'Image'
        }
    });
    $('#customized-buttonpane').trumbowyg({
        lang: 'zh_cn',
        closable: true,
        fixedBtnPane: true,
        btnsDef: {
            // Customizables dropdowns
            align: {
                dropdown: ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
                ico: 'justifyLeft'
            },
            image: {
                dropdown: ['insertImage', 'upload'],
                ico: 'insertImage'
            }
        },
        btns: ['viewHTML', '|', 'formatting', '|', 'btnGrp-test', '|', 'align', '|', 'btnGrp-lists', '|', 'image']
    });
    $('.editor').on('dblclick', function (e) {
        $(this).trumbowyg({
            lang: 'zh_cn',
            closable: true,
            fixedBtnPane: true
        });
    });

    $(document).ready(onload());
    function onload() {
        <c:forEach items="${blog.blogTagsByBlogId}" var="tag">
        //alert("${tag.tagByTagIdNum.tagName}");
        AddElementInChosen("tag" + num,"${tag.tagByTagIdNum.tagId}","${tag.tagByTagIdNum.tagName}");
        num++;
        </c:forEach>
    }
</script>
</body>
</html>
