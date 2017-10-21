<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cst.Constants" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!resourceTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>TA的学习资源</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
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
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="otherPageHeader.jsp"/>

    <script type="text/javascript">
        var div = document.getElementById("otherResource");
        div.setAttribute("class", "active");
    </script>

    <!-- Page Content-->
    <main
            class="page-content section-75 section-md-top-34 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar--> <section>
        <div class="shell">
            <div class="range range-xs-center range-lg-left offset-top-50">
                <div class="cell-sm-10 cell-md-8 cell-md-push-1">
                    <div id="loadMyResourcesDiv">
                    </div>
                    <div class="offset-top-0">
                        <div class="hr bg-gray-lighter"></div>
                    </div>
                    <div class="offset-top-10">
                        <!-- Classic Pagination-->
                        <nav>
                            <ul id="pageList" class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">
                            </ul>
                        </nav>
                    </div>
                </div>
                <div
                        class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-10 offset-sm-top-5">
                    <div class="range range-xs-center offset-top-0">
                        <div class="cell-sm-3 cell-md-12">
                            <div
                                    class="veil-sm reveal-md-block offset-top-0 offset-sm-top-0 offset-md-top-0"
                                    style="margin-top: -20px">
                                <div class="hr bg-gray-lighter"></div>
                            </div>
                            <div
                                    class="cell-sm-10 cell-md-4 cell-lg-3 text-sm-left offset-top-64 offset-md-top-0">
                                <p class="font-accent text-bold text-spacing-50 text-mine-shaft"
                                   style="margin-top: 30px">搜索TA的资源</p>
                                <div class="offset-top-5">
                                    <!-- RD Search Form-->
                                    <form method="post" class="form-search blog-form-search rd-search">
                                        <div class="form-group">
                                            <input type="text" id="searchContent" name="searchContent" autocomplete="off"
                                                   class="form-search-input form-control null">
                                        </div>
                                        <button type="button" onclick="AjaxGetCurrentPageData(1)" class="form-search-submit">
											<span
                                                    class="icon icon-sm material-icons-ico material-icons-search"></span>
                                        </button>
                                    </form>
                                </div>
                                <div
                                        class="veil-sm reveal-md-block offset-top-30 offset-sm-top-20 offset-md-top-30">
                                    <div class="hr bg-gray-lighter"></div>
                                </div>
                                <div class="offset-top-30">
                                    <p
                                            style="font-family: 微软雅黑; font-size : 18px; font-weight: bold; color :black ;">按日期分类</p>
                                </div>
                                <div class="offset-top-25 swiper-scrollbar" style="height: 300px;overflow:auto;">
                                    <ul
                                            class="list-marked text-extra-small text-bold p text-left font-accent">
                                        <li><a href="javascript:void(0)" class="text-primary" onclick="setDate('2017-02-01')" style="font-size: 16px;">2017.2</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2017-01-01')">2017.1</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-12-01')">2016.12</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-11-01')">2016.11</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-10-01')">2016.10</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-09-01')">2016.9</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-08-01')">2016.8</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-07-01')">2016.7</a></li>
                                        <li class="offset-top-10"><a href="javascript:void(0)"
                                                                     class="text-primary" style="font-size: 16px;" onclick="setDate('2016-06-01')">2016.6</a></li>
                                    </ul>
                                </div>
                                <div
                                        class="veil-sm reveal-md-block offset-top-30 offset-sm-top-0 offset-md-top-30">
                                    <div class="hr bg-gray-lighter"></div>
                                </div>
                            </div>
                            <div class="cell-sm-6 cell-md-12 offset-md-top-30">
                                <p
                                        class="font-accent text-bold text-spacing-50 text-mine-shaft text-uppercase">按标签分类</p>
                                <div class="offset-top-20">
                                    <!-- List inline marked-->
                                    <ul class="list-inline list-tags font-accent text-bold text-spacing-inverse-25 text-left">
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('Java')">JAVA</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('C#')">C#</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('C++')">C++</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('PHP')">PHP</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('CSS')">CSS</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('HTML')">HTML</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('IOS')">IOS</a></li>
                                        <li><a class="text-turquoise" href="javascript:void(0)" onclick="setTag('Javascript')">Javascript</a></li>
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
    <jsp:include page="footer.jsp" />
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
    var innerContent_1 = "<article class=\"post-classic text-left\"><div class=\"post-classic-body offset-top-10\"><div class=\"unit unit-sm unit-sm-horizontal unit-sm-inverse\"><div class=\"unit-body\"><div class=\"post-body offset-top-15 offset-md-top-25\"><div class=\"cell-xs-8 cell-sm-5\" style=\'margin-top: -55px;\'><a href=";
    var resourceHref = "singleResource?resourceID=";
    var innerContent_2 = " class=\"post-vacancy\"><span class=\"unit unit-xs unit-xs-middle unit-xs-horizontal unit-spacing-xs text-xs-left\"><span class=\"unit-left\"></span></span><span class=\"unit-body\">";

    var innerContent_3 = "<span class=\"post-meta text-spacing-0\">";
    //var uploadDateTime = "";
    var innerContent_4 = "</span><span class=\"post-title  reveal-block\"><span class=\"offset-top-5 font-accent text-bold text-spacing-50 text-uppercase\"><span class=\"text-mine-shaft\">";
    //var resourceName = "";
    var innerContent_5 = "</span></span></span><br><span class=\"text-spacing-0 reveal-block zhengwen\">";
    //var resourceTags = "";
    var innerContent_6 = "<span class=\"text-extra-small text-bold text-turquoise text-uppercase\">&nbsp;详情</span></span></span></a></div></div></div>";

    var userImage = "<div class=\"unit-right\" style=\"margin-top: 10px;\"><div class=\"unit unit-horizontal unit-middle unit-sm-vertical unit-spacing-xs\"> <div class=\"unit-left\"><img src=\"";
    var userImageSrc = "";
    var userImageHTML = "\" alt=\" \" class=\"img-circle\" height=\"70\" width=\"70\"></div><div class=\"unit-body text-center offset-top-4\"> <p class=\"text-extra-small text-spacing-0 text-bold\"> <cite class=\"text-normal\"><a href=\"javascript:void(0)\" class=\"text-turquoise\">";
    var userName = "";
    var final = "</a></cite> </p> </div> </div> </div> </div></div></article><br/><br/>";
    var pageIndex = 0; //页码
    var pageSize = 5; //每页显示的条目数
    var pageTotal = -1; //总页数
    var selectDate = "";
    var userID = "${this_id}";
    $(function(){
        pageIndex = 1;
        AjaxGetCurrentPageData(pageIndex);
    });

    function setClass() {
        for(var i = 1; i <= pageTotal; i++){
            if(i!=pageIndex){
                $("#page"+i).attr("class","");
            }
            else{
                $("#page"+i).attr("class","active");
            }
        }
    }

    function setPageList(){
        var page_previous = "<li class=\"text-regular\"><a href=\"javascript:void(0)\" id=\"prevPage\" onclick=\"AjaxPreviousPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_left\"></a></li>";
        var page_next="<li class=\"text-regular\"><a href=\"javascript:void(0)\" id=\"nextPage\" onclick=\"AjaxNextPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_right\"></a></li>";

        var from_index = 1;
        var to_index = pageTotal;
        if(pageTotal > 5)
        {
            if(pageIndex <= 3) {
                to_index = 5;
                page_next = "<li class=\"text-regular\"><span>...</span><a href=\"javascript:void(0)\" id=\"nextPage\" onclick=\"AjaxNextPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_right\"></a></li>";
            }
            else if(pageIndex > (pageTotal - 3)){
                from_index = pageTotal - 4;
                page_previous = "<li class=\"text-regular\"><a href=\"javascript:void(0)\" id=\"prevPage\" onclick=\"AjaxPreviousPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_left\"></a><span>...</span></li>";
            }
            else{
                from_index = pageIndex - 2;
                to_index = pageIndex + 2;
                page_previous = "<li class=\"text-regular\"><a href=\"javascript:void(0)\" id=\"prevPage\" onclick=\"AjaxPreviousPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_left\"></a><span>...</span></li>";
                page_next = "<li class=\"text-regular\"><span>...</span><a href=\"javascript:void(0)\" id=\"nextPage\" onclick=\"AjaxNextPageData()\" class=\"icon icon-sm icon-primary material-icons-ico material-icons-chevron_right\"></a></li>";
            }
        }
        var pageIndexesText = page_previous;
        for(var i = from_index; i <= to_index; i++)
        {
            var page_num = i;
            var page_style="<li id=\"page";
            var page_href="\"><a href=\"javascript:void(0)\" onclick=\"AjaxGetCurrentPageData("+ page_num + ")\">" +page_num+"</a>";
            pageIndexesText += page_style + page_num + page_href;
        }
        pageIndexesText += page_next;
        $("#pageList").empty().append(pageIndexesText);
    }

    function AjaxGetCurrentPageData(page_index) {
        pageIndex = page_index;
        var searchContent = $("#searchContent").val();
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/jsp/resource/loadResources/"+page_index,
            timeout: 800000,
            dataType: "json",
            data: {
                "userId" : userID,
                "pageSize" : pageSize,
                "searchContent": searchContent,
                "pageTotal":pageTotal,
                "selectDate":selectDate
            },
            success: function (data) {
                var jsonArray = data;
                var i = 1;
                if (pageTotal == -1 || searchContent != "" || selectDate != "") {
                    pageTotal = Math.ceil(jsonArray[0].resourceCount / pageSize);
                }
                var divText = "";
                if(jsonArray.length == 1){
                    if(searchContent==""&& selectDate=="") {
                        divText = "<div class='offset-top-100' align='center'><h3>TA还未上传任何资源哦 :)</h3><div>";
                    }
                    else{
                        divText = "<div class='offset-top-100' align='center'><h3>没找到您想要的搜索结果 :(</h3><div>";
                    }
                    $("#loadMyResourcesDiv").empty().append(divText);
                    $("#pageList").empty();
                }
                else {
                    for (; i < jsonArray.length; i++) {
                        var userResource = jsonArray[i];
                        var resourceID = userResource.resourceID;
                        var curHref = "\"" + resourceHref + resourceID + "\"";
                        var resourceName = userResource.resourceName;
                        var resourceTags = userResource.resourceTags;
                        var resourceUploadTime = userResource.resourceUploadTime.split(".")[0];
                        userName = userResource.resourceUploaderName;
                        userImageSrc = userResource.uploaderImageUrl;
                        divText += innerContent_1 + curHref
                            + innerContent_2 + innerContent_3
                            + resourceUploadTime + innerContent_4
                            + resourceName + innerContent_5
                            + resourceTags + innerContent_6 + userImage + userImageSrc
                            + userImageHTML + userName + final;
                    }
                    $("#loadMyResourcesDiv").empty().append(divText);
                    setPageList();
                    $("#page" + pageIndex).attr("class", "active");
                    setClass();
                }
            },
            //请求出错的处理
            error: function () {
                alert("出错了");
            }
        });
    }

    function AjaxPreviousPageData() {
        if(pageIndex > 1){
            pageIndex -= 1;
            AjaxGetCurrentPageData(pageIndex);
        }
    }
    function AjaxNextPageData() {
        if(pageIndex < pageTotal){
            pageIndex += 1;
            AjaxGetCurrentPageData(pageIndex);
        }
    }
    function setTag(tag){
        document.getElementById("searchContent").value = tag;
        AjaxGetCurrentPageData(1);
    }
    function setDate(searchDate) {
        document.getElementById("searchContent").value = "";
        selectDate = searchDate;
        AjaxGetCurrentPageData(1);
    }
</script>
</body>
</html>