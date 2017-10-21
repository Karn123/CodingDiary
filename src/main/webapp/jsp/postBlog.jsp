<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
<head>
    <!-- Site Title-->
    <title>${htmlTitle}</title>
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
    <!--<script src="js/html5shiv.min.js"></script>-->
    <![endif]-->
</head>
<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"/>
    <!-- Page Content-->
    <main class="page-content">
        <section class="section-75 section-md-top-60 section-md-bottom-110">
            <div class="shell">
                <div class="range range-xs-center range-lg-center">
                    <div class="cell-xs-8 cell-sm-7 text-left">
                        <div class="offset-top-20">
                            <h4 class="text-mine-shaft text-spacing-75 text-center text-sm-left"
                                style="font-family: 微软雅黑">${htmlTitle}</h4>
                        </div>
                        <div class="offset-top-60">
                            <p class="font-accent text-bold text-mine-shaft text-spacing-30 text-center text-sm-left">
                                标题</p>
                            <form data-form-output="form-contact-me" data-form-type="contact" method="post"
                                  action="bat/rd-mailform.php" novalidate="novalidate"
                                  class="rd-mailform offset-top-15 offset-md-top-15 text-left">
                                <div class="form-group">
                                    <input id="blog-title"
                                           class="form-control form-control-has-validation form-control-last-child"
                                           type="text" value="${blog.blogTitle}">
                                </div>
                            </form>
                        </div>
                        <div class="offset-top-30">
                            <p class="font-accent text-bold text-mine-shaft text-spacing-30 text-center text-sm-left">
                                标签</p>
                            <div class="text-center text-sm-right btn-group offset-top-30">
                                <button class="btn btn-xs btn-ellipse btn-primary btn-icon" onclick="changeNewTags()">
                                    <span class="icon icon-xs material-icons-ico material-icons-add"></span>选择标签
                                </button>
                                <button class="btn btn-xs btn-ellipse btn-primary btn-icon" onclick="createText()"><span
                                        class="icon icon-xs material-icons-ico material-icons-add"></span>创建新标签
                                </button>
                            </div>
                            <div id="newTag" class="offset-top-15">

                            </div>
                            <font size="4px" color="gray">已选标签：</font>
                            <div id="chosen" class="form-group offse-top-5">

                            </div>
                            <div id="selectTag" class="offset-top-15">

                            </div>
                        </div>
                        <div class="offset-top-30">
                            <p class="font-accent text-bold text-mine-shaft text-spacing-30 text-center text-sm-left">
                                内容</p>
                            <form data-form-output="form-contact-me" data-form-type="contact" method="post"
                                  action="bat/rd-mailform.php" novalidate="novalidate"
                                  class="rd-mailform offset-top-15 offset-md-top-15 text-left">
                                <div class="form-group">
                                    <div id="main" role="main">
                                        <div id="customized-buttonpane" class="editor">
                                            ${blog.blogContentByBlogId.blogContent}
                                        </div>
                                        <%--<span class="form-validation"></span>--%>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- <div class="text-center text-sm-right btn-group offset-top-30">
                          <button class="btn btn-xs btn-ellipse btn-primary btn-icon" onclick="changeNewTags()"><span class="icon icon-xs material-icons-ico material-icons-add" ></span>选择标签</button>
                          <button class="btn btn-xs btn-ellipse btn-primary btn-icon" onclick="createText()"><span class="icon icon-xs material-icons-ico material-icons-add"></span>创建新标签</button>
                        </div>
                        <div id="newTag" class="offset-top-15">

                        </div>
                        <font size="4px" color="gray">已选标签：</font><div id="chosen" class="form-group offse-top-5">

                        </div>
                        <div id="selectTag" class="offset-top-15">

                        </div> -->
                        <div id="file_error" style="color: red"></div>
                        <div class="text-center text-sm-right offset-top-60">
                            <button type="button" class="btn btn-sm btn-primary" onclick="postBlog()">提交</button>
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
<script src="js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<%--<script src="trumbowyg/jquery.min.js"></script>--%>
<script src="trumbowyg/trumbowyg.min.js"></script>
<script src="trumbowyg/langs/zh_cn.min.js"></script>
<script src="trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
<script src="trumbowyg/plugins/base64/trumbowyg.base64.min.js"></script>
<script type="text/javascript">
    function singleBlog() {
        window.location.href="singleBlog?blogID=56";
    }
</script>
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
<script type="text/javascript" src="js/tags.js"></script>
<script type="text/javascript">
    function postBlog() {
        jQuery.fn.getTagList = function () {
            var tagsList = this.map(function () {
                return this.value;
            }).get();
            return tagsList.join(",");
        };
        var blogID=${blog.blogId};
        var tags_str = $("#chosen").children().getTagList();
        var title = document.getElementById("blog-title").value;
        var content = $('#customized-buttonpane').trumbowyg('html');
        if (title == "") {
            $("#file_error").empty().append("博客标题不能为空哦0.0");
            return;
        }
        if (tags_str == "") {
            $("#file_error").empty().append("博客标签不能为空哦0.0");
            return;
        }
        if (content == "") {
            $("#file_error").empty().append("博客内容不能为空哦0.0");
            return;
        }
        $.ajax({
            type: "post",//请求方式
            url: "blog/post",
            timeout: 8000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "blog_id":blogID,
                "blog_title": title,
                "blog_content": content,
                "blog_tags": tags_str
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                if (data.msg =="notLogIn") {
                    alert("<%=Message.NOTLOGIN%>");
                    window.location.href = "userLogIn";
                } else if (data.msg =="noAuthority") {
                    alert("<%=Message.NOAUTHORITY%>");
                }else {
                    if (data.retCode == "0") {
                        alert(data.msg);
                    }else{
                        window.location.href="singleBlog?blogID="+data.retCode;
                    }
                    //window.location.href="singleBlog?blogID="+data.retCode;
                }
                //window.location.href="skipToMyBlogAction_skip?type=userId&type_value=0";
            },
            //请求出错的处理
            error: function () {
                //alert("请求出错");
            }
        });
    }
</script>
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
