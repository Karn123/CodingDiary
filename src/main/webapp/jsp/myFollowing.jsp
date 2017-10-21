<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <!-- Site Title-->
    <title>我的关注</title>
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

<%--<%--%>
<%--List list=(List)request.getAttribute("userList");--%>
<%--pageContext.setAttribute("list", list);--%>
<%--int count=0; //总行数--%>
<%--int page_count=0;  //开始条数--%>
<%--int page_total=1;  //，总页码--%>
<%--int page_current= 1;  //首页--%>
<%--int page_size=18;//一页的行数--%>
<%--String page_cu = request.getParameter("page_current");--%>
<%--if(page_cu==null){--%>
<%--page_cu="1";--%>
<%--}--%>
<%--pageContext.setAttribute("page_num", page_cu);--%>
<%--page_current = Integer.parseInt(page_cu)-1;--%>
<%--if(page_current<0){--%>
<%--page_current = 1;--%>
<%--}--%>
<%--page_count=page_count + page_current*page_size;--%>
<%--%>--%>

<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="myPageHeader.jsp"></jsp:include>
    <script type="text/javascript">
        var div = document.getElementById("myFollowings");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-60 section-md-bottom-125">
        <!-- Testimonials-->
        <section>
            <div class="shell">
                <h3 style = "font-family: 微软雅黑; letter-spacing: 5px">我的关注</h3>
                <div id="show_follows" class="range range-xs-center offset-top-50 offset-md-top-50">
                    <%--<c:forEach items="${list}"  var="user"  step="1" varStatus="i"  begin="<%=page_count%>"  end="<%=page_size+page_count-1%>">--%>


                    <%--</c:forEach>--%>
                </div>

                <%--<div class="offset-top-75">--%>
                <%--<!-- Classic Pagination-->--%>
                <%--<nav>--%>
                <%--<ul class="list-marked list-marked-type-2 list-marked-type-2-dot-1 list-marked-silver-chalice pagination-classic">--%>
                <%--<li class="text-regular"><a href="#"--%>
                <%--class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_left"></a>--%>
                <%--</li>--%>
                <%--<li id="page1"><a href="skipToMyPageAction_skipToMyFollowers?page_current=1">01</a>--%>
                <%--</li>--%>
                <%--<li id="page2"><a href="skipToMyPageAction_skipToMyFollowers?page_current=2">02</a>--%>
                <%--</li>--%>
                <%--<li id="page3"><a href="skipToMyPageAction_skipToMyFollowers?page_current=3">03</a>--%>
                <%--</li >--%>
                <%--<li id="page4"><a href="skipToMyPageAction_skipToMyFollowers?page_current=4">04</a>--%>
                <%--</li>--%>
                <%--<li id="page5"><a href="skipToMyPageAction_skipToMyFollowers?page_current=5">05</a>--%>
                <%--</li>--%>
                <%--<script type="text/javascript">--%>
                <%--var num=${page_num};--%>
                <%--var div = document.getElementById("page"+num);--%>
                <%--div.setAttribute("class", "active");--%>
                <%--</script>--%>
                <%--<li class="text-regular"><a href="#"--%>
                <%--class="icon icon-sm icon-primary material-icons-ico material-icons-chevron_right"></a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</nav>--%>
                <%--</div>--%>

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
    var tmpImg = 'images/users/user-joanna-black-169x169.jpg';
    //载入数据
    window.onload = function () {
        //alert("start init follow infos");
        $.ajax({
            url: "getFollowInfo",
            type: "post",
            dataType: "json",
            data:{
                "action":"followings",
                "user":"me"
            },
            success: function (data) {
                var array = eval(data);
                for(var i in array){
                    //alert("name:" + array[i].nickName + "  portrait:" + array[i].portraitUrl);
                    document.getElementById('show_follows').innerHTML += '<div class="cell-sm-4 cell-md-2 offset-top-64 offset-md-top-20" style="float: left">' +
                        '<blockquote class="quote">' +
                        ' <a  href="otherPage?this_id=' +
                        array[i].id +
                        '"><img src="' + array[i].portraitUrl +
                        '" width="100" height="125" alt="" class="img-circle img-responsive center-block"></a>' +
                        ' <div class="offset-top-10 offset-md-top-20">' +
                        ' <p> ' +
                        '<cite class="font-accent text-bold text-spacing-inverse-25 text-mine-shaft "><a href="otherPage?this_id=' +
                        array[i].id +
                        '" class="text-mine-shaft">' +
                        array[i].nickName +
                        '</a></cite> </p> </div> </blockquote></div>';
                }
            },
            error: function () {
                alert("系统忙，请稍后重试");
            }
        })
    }
</script>
</body>
</html>