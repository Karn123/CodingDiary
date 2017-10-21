<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <!-- Site Title-->
    <title>TA的主页</title>
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
        <!--私信对话框样式-->
    <link rel="stylesheet" href="css/chat.css">
    <style type="text/css">
        .user-logo-left { float: left; width: 50px; height: 50px; resize: none; border-radius: 50%;}
        .user-logo-left img {border-radius: 50%; width: 50px;}
        .user-logo-right { float: right; width: 50px; height: 50px; resize: none; border-radius: 50%;}
        .user-logo-right img {border-radius: 50%; width: 50px;}
        .btn-squaresize {
            width: 200px;
            height: 180px;
            padding: 100px 100px;
        }

        .btn-squaresize > h4 {
            font-family: 幼圆;
            font-weight: bolder;
            font-size: 40px;
            color: white;
            letter-spacing: 5px;
            align-content: center;
            padding-top: 32%;
        }

        .btn-blue1 {
            color: #41d0d9;
            background-color: #8ec2f5;
            border-color: #fff;
        }

        .btn-blue1:focus, .btn-blue1.focus, .btn-blue1:hover, .btn-blue1:active, .btn-blue1.active,
        .open > .btn-blue1.dropdown-toggle {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue1:active, .btn-blue1.active,
        .open > .btn-blue1.dropdown-toggle {
            -webkit-box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
        }

        .btn-blue1:active:hover, .btn-blue1:active:focus, .btn-blue1:active.focus, .btn-blue1.active:hover, .btn-blue1.active:focus, .btn-blue1.active.focus,
        .open > .btn-blue1.dropdown-toggle:hover,
        .open > .btn-blue1.dropdown-toggle:focus,
        .open > .btn-blue1.dropdown-toggle.focus {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue1.disabled, .btn-blue1[disabled],
        fieldset[disabled] .btn-blue1 {
            pointer-events: none;
            opacity: .5;
        }

        .btn-blue1 .badge {
            color: #fff;
            background-color: #41d0d9;
        }

        .btn-blue2 {
            color: #41d0d9;
            background-color: #3399ff;
            border-color: #fff;
        }

        .btn-blue2:focus, .btn-blue2.focus, .btn-blue2:hover, .btn-blue2:active, .btn-blue2.active,
        .open > .btn-blue2.dropdown-toggle {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue2:active, .btn-blue2.active,
        .open > .btn-blue2.dropdown-toggle {
            -webkit-box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
        }

        .btn-blue2:active:hover, .btn-blue2:active:focus, .btn-blue2:active.focus, .btn-blue2.active:hover, .btn-blue2.active:focus, .btn-blue2.active.focus,
        .open > .btn-blue2.dropdown-toggle:hover,
        .open > .btn-blue2.dropdown-toggle:focus,
        .open > .btn-blue2.dropdown-toggle.focus {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue2.disabled, .btn-blue2[disabled],
        fieldset[disabled] .btn-blue2 {
            pointer-events: none;
            opacity: .5;
        }

        .btn-blue2 .badge {
            color: #fff;
            background-color: #41d0d9;
        }

        .btn-blue3 {
            color: #41d0d9;
            background-color: #0066cc;
            border-color: #fff;
        }

        .btn-blue3:focus, .btn-blue3.focus, .btn-blue3:hover, .btn-blue3:active, .btn-blue3.active,
        .open > .btn-blue3.dropdown-toggle {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue3:active, .btn-blue3.active,
        .open > .btn-blue3.dropdown-toggle {
            -webkit-box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.15);
        }

        .btn-blue3:active:hover, .btn-blue3:active:focus, .btn-blue3:active.focus, .btn-blue3.active:hover, .btn-blue3.active:focus, .btn-blue3.active.focus,
        .open > .btn-blue3.dropdown-toggle:hover,
        .open > .btn-blue3.dropdown-toggle:focus,
        .open > .btn-blue3.dropdown-toggle.focus {
            color: #fff;
            background-color: #adadad;
            border-color: #adadad;
        }

        .btn-blue3.disabled, .btn-blue3[disabled],
        fieldset[disabled] .btn-blue3 {
            pointer-events: none;
            opacity: .5;
        }

        .btn-blue3 .badge {
            color: #fff;
            background-color: #41d0d9;
        }

    </style>

    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;">
        <a href="http://windows.microsoft.com/en-US/internet-explorer/"><img
                src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820"
                alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a>
    </div>
    <script src="js/html5shiv.min.js"></script>

    <![endif]-->
</head>
<body onKeyDown="keydown_enter()">
<%--<%--%>
<%--Object userinfo=session.getAttribute("userinfo");--%>
<%--pageContext.setAttribute("userinfo", userinfo);--%>
<%--%>--%>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="otherPageHeader.jsp"/>
    <script type="text/javascript">
        var div = document.getElementById("otherPage");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-60 section-md-bottom-125">
        <!-- Meet our team-->
        <section>
            <div class="shell">
                <h2 id="nickName" class="text-spacing-inverse-50" style="font-family: 微软雅黑">${userinfo.nickName}</h2>
                <div class="range range-xs-center offset-md-top-35">
                    <div class="cell-sm-10 cell-lg-8"><img id="userPortrait" src="${userinfo.headPortrait}" width="150px"
                                                           alt=""
                                                           class="img-circle img-responsive center-block">
                        <div class="range range-xs-center offset-top-35">
                            <div class="cell-sm-8 cell-md-6">
                                <div class="clearfix">
                                    <button id="follow-btn"
                                            class="btn btn-curious-blue btn-icon btn-icon-left btn-xs shadow-drop-md"
                                            style="font-family:微软雅黑; font-size: 16px" value="follow" onclick="change()">
                                        <span class="icon icon-xs material-icons-ico material-icons-person_outline"></span>${isFollowed}
                                    </button>
                                    <button id="chat-btn"
                                            class="btn btn-calypso btn-icon btn-icon-right btn-xs shadow-drop-md"
                                            style="font-family:微软雅黑; font-size: 16px"
                                            onclick="startConversation()"><span
                                            class="icon icon-xs material-icons-ico material-icons-person_outline"></span>私
                                        信
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="offset-top-25">
                            <svg width="135" height="4" viewBox="0 0 135 4">
                                <path style="fill:#efefef"
                                      d="M 2.3227821,2.0449621 -0.03800192,0.07659815 3.1569295,1.2434136 c 2.5401262,0.9276753 3.7227371,0.8843425 5.7702062,-0.2114297 1.9784883,-1.05885455 2.8486773,-1.10484315 3.7550683,-0.19845145 1.484362,1.48436175 5.031902,1.52768545 6.480446,0.079141 0.80087,-0.80087 2.091954,-0.7421935 4.740217,0.21543085 2.979173,1.0772827 4.014876,1.0816979 5.707987,0.024332 1.636053,-1.02173165 2.672814,-1.04140395 4.959784,-0.09411 2.271014,0.9406851 3.546951,0.926086 5.946995,-0.068045 2.294075,-0.95023691 3.340846,-0.9804738 4.2,-0.1213203 1.486272,1.48627181 5.021657,1.46707331 6.524159,-0.035429 0.906391,-0.9063917 1.77658,-0.8604031 3.755068,0.19845151 1.998288,1.0694512 3.235083,1.1291955 5.520207,0.2666579 2.160373,-0.81544991 3.729489,-0.81544991 5.889862,0 2.285124,0.8625376 3.521919,0.8027933 5.520207,-0.2666579 1.978488,-1.05885461 2.848677,-1.10484321 3.755068,-0.19845151 1.484362,1.48436181 5.031902,1.52768551 6.480446,0.079141 0.80087,-0.80087 2.071309,-0.7496588 4.664419,0.18802211 2.948695,1.0662618 4.004871,1.0526028 6.119554,-0.079141 1.963517,-1.05084213 2.82919,-1.09441383 3.735581,-0.18802223 1.484362,1.48436183 5.031906,1.52768553 6.480446,0.079141 0.80087,-0.80087 2.07131,-0.7496587 4.66442,0.18802203 2.90135,1.0491408 4.00935,1.0502085 5.96095,0.00574 1.95708,-1.04739773 2.78238,-1.04236583 4.49536,0.027409 1.72387,1.0765766 2.7384,1.078836 5.68684,0.012665 2.7988,-1.01205733 3.90688,-1.03357323 5.03447,-0.097755 0.7952,0.6599557 2.61547,0.976326 4.04505,0.703045 2.37877,-0.4547301 2.464,-0.3616463 1.00491,1.097441 -1.45908,1.4590874 -1.84642,1.4453923 -4.56661,-0.1614625 -2.74627,-1.6222625 -3.15865,-1.6336716 -5.42297,-0.1500332 -2.18024,1.4285487 -2.76388,1.4437811 -5.28891,0.1380357 -2.47017,-1.2773717 -3.19253,-1.2820786 -5.57019,-0.036295 -2.37022,1.241885 -3.09846,1.241885 -5.5,0 -2.40452,-1.2434263 -3.13028,-1.2416208 -5.52624,0.013748 C 99.763844,3.9190467 99.15734,3.9133435 97.292676,2.607281 95.404183,1.2845297 94.833305,1.2870756 92.236706,2.6298291 89.597447,3.9946429 89.064352,3.982798 86.815628,2.5093781 84.52735,1.0100401 84.120628,1.0069808 81.884292,2.4722843 79.735334,3.8803355 79.122431,3.8992836 76.704532,2.6324181 74.291896,1.3683104 73.641159,1.3851356 71.292806,2.7723415 68.845881,4.2177761 68.408458,4.2082649 66.042738,2.6581862 63.671614,1.1045661 63.264662,1.0965199 61.032448,2.5591227 58.880523,3.969118 58.285419,3.9846813 55.824584,2.69532 53.371101,1.4098101 52.768844,1.4213922 50.673394,2.7943834 48.512048,4.2105516 48.042674,4.1968405 45.408131,2.6405778 42.740652,1.0648591 42.325185,1.0568632 40.065069,2.5377482 37.884828,3.9662969 37.30119,3.9815293 34.776157,2.6757839 32.280231,1.3850906 31.599207,1.3855448 29.12951,2.6795501 26.631592,3.9883412 26.052244,3.9748646 23.889307,2.5576544 21.678633,1.1091646 21.182621,1.1064374 18.434276,2.5276615 15.749926,3.9157928 15.172991,3.9243054 13.287423,2.6036024 11.426177,1.2999346 10.817722,1.295134 8.3625636,2.5647457 4.8669338,4.3724061 5.142697,4.3961383 2.3227821,2.0449621 Z"/>
                            </svg>
                        </div>
                        <div class="offset-top-25">
                            <p class="text-spacing-0">个人简介：此处省略500字。。。</p>
                            <div class="offset-top-20">
                                <svg width="135" height="4" viewBox="0 0 135 4">
                                    <path style="fill:#efefef"
                                          d="M 2.3227821,2.0449621 -0.03800192,0.07659815 3.1569295,1.2434136 c 2.5401262,0.9276753 3.7227371,0.8843425 5.7702062,-0.2114297 1.9784883,-1.05885455 2.8486773,-1.10484315 3.7550683,-0.19845145 1.484362,1.48436175 5.031902,1.52768545 6.480446,0.079141 0.80087,-0.80087 2.091954,-0.7421935 4.740217,0.21543085 2.979173,1.0772827 4.014876,1.0816979 5.707987,0.024332 1.636053,-1.02173165 2.672814,-1.04140395 4.959784,-0.09411 2.271014,0.9406851 3.546951,0.926086 5.946995,-0.068045 2.294075,-0.95023691 3.340846,-0.9804738 4.2,-0.1213203 1.486272,1.48627181 5.021657,1.46707331 6.524159,-0.035429 0.906391,-0.9063917 1.77658,-0.8604031 3.755068,0.19845151 1.998288,1.0694512 3.235083,1.1291955 5.520207,0.2666579 2.160373,-0.81544991 3.729489,-0.81544991 5.889862,0 2.285124,0.8625376 3.521919,0.8027933 5.520207,-0.2666579 1.978488,-1.05885461 2.848677,-1.10484321 3.755068,-0.19845151 1.484362,1.48436181 5.031902,1.52768551 6.480446,0.079141 0.80087,-0.80087 2.071309,-0.7496588 4.664419,0.18802211 2.948695,1.0662618 4.004871,1.0526028 6.119554,-0.079141 1.963517,-1.05084213 2.82919,-1.09441383 3.735581,-0.18802223 1.484362,1.48436183 5.031906,1.52768553 6.480446,0.079141 0.80087,-0.80087 2.07131,-0.7496587 4.66442,0.18802203 2.90135,1.0491408 4.00935,1.0502085 5.96095,0.00574 1.95708,-1.04739773 2.78238,-1.04236583 4.49536,0.027409 1.72387,1.0765766 2.7384,1.078836 5.68684,0.012665 2.7988,-1.01205733 3.90688,-1.03357323 5.03447,-0.097755 0.7952,0.6599557 2.61547,0.976326 4.04505,0.703045 2.37877,-0.4547301 2.464,-0.3616463 1.00491,1.097441 -1.45908,1.4590874 -1.84642,1.4453923 -4.56661,-0.1614625 -2.74627,-1.6222625 -3.15865,-1.6336716 -5.42297,-0.1500332 -2.18024,1.4285487 -2.76388,1.4437811 -5.28891,0.1380357 -2.47017,-1.2773717 -3.19253,-1.2820786 -5.57019,-0.036295 -2.37022,1.241885 -3.09846,1.241885 -5.5,0 -2.40452,-1.2434263 -3.13028,-1.2416208 -5.52624,0.013748 C 99.763844,3.9190467 99.15734,3.9133435 97.292676,2.607281 95.404183,1.2845297 94.833305,1.2870756 92.236706,2.6298291 89.597447,3.9946429 89.064352,3.982798 86.815628,2.5093781 84.52735,1.0100401 84.120628,1.0069808 81.884292,2.4722843 79.735334,3.8803355 79.122431,3.8992836 76.704532,2.6324181 74.291896,1.3683104 73.641159,1.3851356 71.292806,2.7723415 68.845881,4.2177761 68.408458,4.2082649 66.042738,2.6581862 63.671614,1.1045661 63.264662,1.0965199 61.032448,2.5591227 58.880523,3.969118 58.285419,3.9846813 55.824584,2.69532 53.371101,1.4098101 52.768844,1.4213922 50.673394,2.7943834 48.512048,4.2105516 48.042674,4.1968405 45.408131,2.6405778 42.740652,1.0648591 42.325185,1.0568632 40.065069,2.5377482 37.884828,3.9662969 37.30119,3.9815293 34.776157,2.6757839 32.280231,1.3850906 31.599207,1.3855448 29.12951,2.6795501 26.631592,3.9883412 26.052244,3.9748646 23.889307,2.5576544 21.678633,1.1091646 21.182621,1.1064374 18.434276,2.5276615 15.749926,3.9157928 15.172991,3.9243054 13.287423,2.6036024 11.426177,1.2999346 10.817722,1.295134 8.3625636,2.5647457 4.8669338,4.3724061 5.142697,4.3961383 2.3227821,2.0449621 Z"/>
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="range range-lg-center offset-top-25">
                <div class="cell-lg-top cell-lg-top">
                    <a href="${pageContext.request.contextPath}/jsp/otherBlog?this_id=${sessionScope.get("this_id")}" class="btn btn-ellipse btn-blue3 btn-squaresize">
                        <h4>TA的博客</h4>
                    </a>
                    <a href="${pageContext.request.contextPath}/jsp/otherForumpost?this_id=${sessionScope.get("this_id")}" class="btn btn-ellipse btn-blue2 btn-squaresize">
                        <h4>TA的帖子</h4>
                    </a>
                    <a href="${pageContext.request.contextPath}/jsp/otherResource?this_id=${sessionScope.get("this_id")}" class="btn btn-ellipse btn-blue1 btn-squaresize">
                        <h4>TA的资源</h4>
                    </a>
                </div>
            </div>
        <!--私信窗口-->
            <div class="chatBox-cover" id="mask"></div>
                <div class="chatBox" id="dialog">
                    <div class="chatLeft">
                        <div class="chat01">
                            <div class="chat01_title" id="move_part">
                                <h5 class="title_name">${userinfo.nickName}</h5>
                                <a class="close" id="close" href="javascript:;" style="font-size: 18px;" onclick="killConversation()">×</a>
                                <a class="close" id="hide" href="javascript:;" style="font-size: 18px;" onclick="hideConversation()">—</a>
                            </div>
                            <div class="chat01_content" id="msg-content">

                            </div>
                        </div>
                        <div class="chat02">
                            <div class="chat02_title">
                            </div>
                            <div class="chat02_content">
                                <textarea id="textarea"></textarea>
                            </div>
                            <div class="chat02_bar">
                                <button onclick="send()" class="btn btn-curious-blue btn-xs shadow-drop-md">发送</button>
                            </div>
                        </div>
                    </div>
                    <div style="clear: both;">
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
<script src="js/jmessage-sdk-web.2.3.0.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/dateUtil.js"></script>
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>
<script type="text/javascript">
    var my_portrait = "${sessionScope.get("userHeadPortrait")}";
    var my_id = "${sessionScope.get("userID")}";
    var friend_id = "${this_user_id}";
    var str = "appkey=${appKey}&timestamp=${timestamp}&random_str=${randomStr}&key=${masterSecret}";
    var signature = md5(str);
    var msg_id_read = new Array();
    var datePattern = "yyyy-MM-dd hh:mm:ss";

    window.JIM = new JMessage({
        debug : false
    });

    while (my_id.length < 4){
        my_id = '0' + my_id;
    }

    while (friend_id.length < 4){
        friend_id = '0' + friend_id;
    }

    JIM.init({
        "appkey": "${appKey}",
        "random_str": "${randomStr}",
        "signature": signature,
        "timestamp": "${timestamp}",
        "flag": 1
    }).onSuccess(function(data) {
        console.log('init success');
    }).onFail(function(data) {
        console.log('init error:' + JSON.stringify(data))
    });

//    alert("myid: " + my_id + "   friendid:" + friend_id);

    function startConversation() {
        if (my_id == "0000"){
            alert("请先登录");
            window.location.href = "userLogin";
        }
        $("#mask").fadeIn(100);
        $("#dialog").fadeIn(100);

        /*   jiguangIM   */

        JIM.onDisconnect(function(){
            console.log("【disconnect】");
        });
        //异常断线监听
//        alert(JIM.isInit());
        if (JIM.isInit() == false){
            JGinitAndLogin();
        } else {
            JGlogin();
        }

        //从数据库获取离线未读消息并显示
        getInitialMsgs();

        /*   jiguangIM   */
    }

    function isInit(){
        console.log('isInit:'+JIM.isInit());
    }


    function JGinitAndLogin() {
        JIM.init({
            "appkey": "${appKey}",
            "random_str": "${randomStr}",
            "signature": signature,
            "timestamp": "${timestamp}",
            "flag": 1
        }).onSuccess(function(data) {
            console.log('init success');
        }).onFail(function(data) {
            console.log('init error:' + JSON.stringify(data))
        });

        JIM.login({
            'username' : my_id,
            'password': "password"
        }).onSuccess(function(data) {
            console.log('login success');

            JIM.onMsgReceive(function(data) {
                console.log('msg_receive:' + JSON.stringify(data));
                var k;
                for (k = 0; k < data.messages.length; k++){
                    showMsgReceived(data.messages[k].content.msg_body.text,new Date(data.messages[k].content.create_time).format(datePattern));
                    msg_id_read[msg_id_read.length] = data.messages[k].msg_id;
                }

            });

            JIM.onEventNotification(function(data) {
                console.log('event_receive: ' + JSON.stringify(data));
            });

            JIM.onSyncConversation(function(data) { //离线消息同步监听
                console.log('offline receive: ' + JSON.stringify(data));

            });

            JIM.onUserInfUpdate(function(data) {
                console.log('onUserInfUpdate : ' + JSON.stringify(data));
            });


        }).onFail(function(data) {
            console.log('error:' + JSON.stringify(data));
            if (data.code == "880103"){
                JGregister();
            }
            JGlogin();

        }).onTimeout(function(data) {
            console.log('timeout:' + JSON.stringify(data));
        });

    }


    function JGlogin() {
        JIM.login({
            'username' : my_id,
            'password': "password"
        }).onSuccess(function(data) {
            console.log('login success');

            JIM.onMsgReceive(function(data) {
                console.log('msg_receive:' + JSON.stringify(data));
                var k;
                for (k = 0; k < data.messages.length; k++){
                    showMsgReceived(data.messages[k].content.msg_body.text,new Date(data.messages[k].content.create_time).format(datePattern));
                    msg_id_read[msg_id_read.length] = data.messages[k].msg_id;
                }

            });

            JIM.onEventNotification(function(data) {
                console.log('event_receive: ' + JSON.stringify(data));
            });

            JIM.onSyncConversation(function(data) { //离线消息同步监听
                console.log('offline receive: ' + JSON.stringify(data));

            });

            JIM.onUserInfUpdate(function(data) {
                console.log('onUserInfUpdate : ' + JSON.stringify(data));
            });


        }).onFail(function(data) {
            console.log('error:' + JSON.stringify(data));
            if (data.code == "880103"){
                JGregister();
            }
            JGlogin();

        }).onTimeout(function(data) {
            console.log('timeout:' + JSON.stringify(data));
        });
    }

    function JGregister(){
        JIM.register({
            'username' : my_id,
            'password': "password"
        }).onSuccess(function(data) {
            console.log('success:' + JSON.stringify(data));
        }).onFail(function(data) {
            console.log('error:' + JSON.stringify(data))
        });
    }

    function JGloginOut(){
        JIM.loginOut();
        console.log('--- login out ---');
    }

        //关闭对话框（断开websocket连接）
    function killConversation(){
        JGloginOut();
        setAllMsgRead();
        $("#mask").fadeOut(100);
        $("#dialog").fadeOut(100);
    }

        //隐藏对话框（保持连接状态）
   function hideConversation(){
        $("#mask").fadeOut(100);
        $("#dialog").fadeOut(100);
   }



    //获取未读信息
    function getInitialMsgs(){
//        alert("开始获取未读信息");
        $.ajax({
            type: "post",//请求方式
            url: "getInitialMsgs",
            timeout: 800000,//超时时间：800秒
            dataType: "json",//设置返回数据的格式
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                //    alert(data.retCode);
                if (data.historyRetCode == "0000") {
                    if (data.historyList != null){
                        document.getElementById('msg-content').innerHTML += '<div style="text-align: center"><div class="tip" id="history-tip">一周历史消息</div></div>';
                        for (var i=0; i<data.historyList.length; i++){
                            if (data.historyList[i].fromUserId == my_id)
                                showMsgSent(data.historyList[i].msgContent,new Date(data.historyList[i].msgDate).format(datePattern));
                            else if (data.historyList[i].fromUserId == friend_id)
                                showMsgReceived(data.historyList[i].msgContent,new Date(data.historyList[i].msgDate).format(datePattern));
                        }
                    }
                }

                if (data.unreadRetCode == "0000") {
                    if (data.unreadList != null){
                        document.getElementById('msg-content').innerHTML += '<div style="text-align: center"><div class="tip" id="unread-tip">离线消息</div></div>';
                        for (var i=0; i<data.unreadList.length; i++){
                            showMsgReceived(data.unreadList[i].msgContent,new Date(data.unreadList[i].msgDate).format(datePattern));
                        }
                    }
                }
            },
            //请求出错的处理
            error: function () {
                //alert("请求出错");
            }
        });
    }

    //发送消息
    function send() {
        var sendText = document.getElementById('textarea').value;
        if(sendText.length != 0){
            JIM.sendSingleMsg({
                'target_username': friend_id,
                'content': sendText,
                'no_offline': true //不保存离线消息
            }).onSuccess(function (data) {
                var msgId = data.msg_id;
                saveMsg(msgId,sendText);
                console.log('sendMsg success:' + JSON.stringify(data));
                //document.getElementById('msg-content').innerHTML += '<div class="msg-box"> <div class="user-logo-right"><img src="'+ my_portrait + '"/></div><div class="send-right">' + sendText + '<span class="arrow-right"></span></div></div><br style="clear:both; display:none;"/>';
                showMsgSent(sendText,new Date().format(datePattern));
                document.getElementById('textarea').value = "";
            }).onFail(function (data) {
                console.log('sendMsg error:' + JSON.stringify(data));
            });

        }
        else alert("请先输入信息");
    }
    //显示接收到的消息
    function showMsgReceived(msg,time) {
        document.getElementById('msg-content').innerHTML += '<div class="msg-box-left"><div class="user-logo-left"><img src="${userinfo.headPortrait}"/></div><div class="send-left">'+msg+'<span class="arrow-left"></span></div><div class="msg-time">'+ time +'</div></div><br style="clear:both; display:none;"/>';
    }

    function showMsgSent(msg,time) {
        document.getElementById('msg-content').innerHTML += '<div class="msg-box-right"> <div class="user-logo-right"><img src="'+ my_portrait + '"/></div><div class="send-right">' + msg + '<span class="arrow-right"></span></div><div class="msg-time">'+ time +'</div></div><br style="clear:both; display:none;"/>';

    }
    //保存消息到数据库
    function saveMsg(id,content) {
//        alert("开始保存消息");
        if (content != null){
            $.ajax({
                type: "post",//请求方式
                url: "saveChatMsg",
                timeout: 80000,//超时时间：8秒
                dataType: "json",//设置返回数据的格式
                data:{
                    "msgId":id,
                    "msgContent":content
                },
                success: function () {
//                    alert("消息成功储存到数据库");
                },
                //请求出错的处理
                error: function () {
//                    alert("消息无法储存到数据库");
                }
            })
        }
    }

    function setAllMsgRead() {
        $.ajax({
            type: "post",//请求方式
            url: "setMsgsRead",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data:{
                "msgIdArray":JSON.stringify(msg_id_read)
            },
            success: function () {
//                    alert("消息成功储存到数据库");
            },
            //请求出错的处理
            error: function () {
//                alert("连接错误");
            }
        })
    }
    //敲回车发送消息
    function keydown_enter() {
        var e = window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode==13 && linked==true) {
            send();
        }
    }
    /* websocket调用的到的函数结束 */

    //关注，取关功能
    function change() {
        var btn = document.getElementById("follow-btn");
       // alert(btn.innerHTML);

        if (btn.innerHTML.indexOf("取消关注") >= 0){
            btn.value = "unfollow";
        }

        $.ajax({
            type: "post",//请求方式
            url: "otherPage/followAndUnfollow",
            timeout: 800000,//超时时间：800秒
            dataType: "json",//设置返回数据的格式
            data: {
                "action": btn.value,
            },
            //请求成功后的回调函数 data为json格式
            success: function (data) {
                //    alert(data.retCode);
                if (data.retCode == "0001") {
                    alert("关注成功");
                    btn.innerHTML = '<span class="icon icon-xs material-icons-ico material-icons-person_outline"></span>'+'取消关注';
                    btn.value = "unfollow";
                } else if (data.retCode == "0000") {
                    alert("取关成功");
                    btn.innerHTML = '<span class="icon icon-xs material-icons-ico material-icons-person_outline"></span>'+'关注';
                    btn.value = "follow";
                } else {
                    alert("还没登录");
                    window.location.href = "userLogIn";
                }

            },
            //请求出错的处理
            error: function () {
                alert("请求出错");
            },
        });
    }
</script>
</body>
</html>
</html>

