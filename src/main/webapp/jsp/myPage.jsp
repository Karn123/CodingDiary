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
    <title>我的主页</title>
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
    <style type="text/css">
    .table-mydata{
    padding: 12px;}
    .table-mydata tbody tr th, .table-mydata tbody tr td {
    padding: 12px;
    white-space: normal;
    word-wrap: break-word;
    word-break: break-all;}
    .table-mydata tbody tr th{
    width: 250px;
    }
    .table-mydata tbody tr td{
    width: 500px;
    line-height: 180%;
    }
    </style>
    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="myPageHeader.jsp"></jsp:include>
    <script type="text/javascript">
        var div = document.getElementById("myPage");
        div.setAttribute("class", "active");
    </script>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-34 section-md-bottom-95">
        <!-- Blog Classic Both Sidebar-->
        <section>
            <div class="shell">
                <div class="range range-xs-center range-lg-center offset-top-50">
                    <div class="cell-sm-8 cell-md-6">
                        <h3 style="font-family: 微软雅黑; letter-spacing: 5px;">个人资料
                            <a class="material-icons-create icon icon-sm ext-turquoise" id="correct-btn" style="cursor: pointer;visibility: visible"
                                onclick="correctUserInfo()"></a>
                        </h3>

                        <div class="offset-sm-top-20 offset-md-top-40">
                            <table align="center" style="align:center;font-size:21px"  class="table-mydata text-md-left text-gray">
                                <tr>
                                    <th>昵称：</th>
                                    <td id="nickName">${myinfo.nickName}</td>
                                </tr>
                                <tr>
                                    <th>性别：</th>
                                    <td id="sex">${myinfo.genderBySex.genderName}</td>
                                </tr>
                                <tr>
                                    <th>年龄：</th>
                                    <td id="age">${myinfo.age}</td>
                                </tr>
                                <tr>
                                    <th>学校：</th>
                                    <td id="universityName">${myinfo.universityName}</td>
                                </tr>
                                <tr>
                                    <th>学院：</th>
                                    <td id="instituteName">${myinfo.instituteName}</td>
                                </tr>
                                <tr>
                                    <th>入学年份：</th>
                                    <td id="entranceYear">${myinfo.entranceYear}</td>
                                </tr>
                                <tr>
                                    <th>邮箱：</th>
                                    <td id="email">${myinfo.email}</td>
                                </tr>
                                <tr>
                                    <th>手机：</th>
                                    <td id="teleNum">${myinfo.teleNum}</td>
                                </tr>
                            </table>
                            <c:if test="${myinfo.isCertified==0}">
                            <p>未完成学生认证！<p>
                            <a href="studentCertification" class="btn btn-primary">去认证</a>
                            </c:if>
                            <c:if test="${myinfo.isCertified==1}">
                            <p>已完成学生认证！<p>
                            </c:if>
                        </div>


                        <div class="offset-top-30">
                            <div class="btn btn-primary" id="saveUserInfo" style="text-align: center;visibility: hidden"
                                onclick="save()">保存修改</div>
                        </div>

                    </div>
                </div>
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
    var length = -1;
//    window.addEventListener('load', getInfo, false);
    function correctUserInfo() {
        //显示保存按钮
        document.getElementById('saveUserInfo').style.visibility = "visible";
        document.getElementById('correct-btn').style.visibility = "hidden";
        //将文字变为修改框
        var tds = document.getElementsByTagName('td');
        length = tds.length;
        for (var i=0; i<length; i++){
            var txt = tds.item(i).innerHTML;
            tds.item(i).innerHTML = "";
            var input = document.createElement('input');
            input.type = 'text';
            input.class = 'inputs';
            input.value = txt;
            input.id =  "input_" + tds.item(i).id;
            tds.item(i).appendChild(input);
        }
    }

    function save() {
        var tds = document.getElementsByTagName('td');
        $.ajax({
            type: "post",//请求方式
            url: "userInfo/saveUserInfoAction",
            timeout: 80000,//超时时间：8秒
            dataType: "json",//设置返回数据的格式
            data: {
                "nickName": document.getElementById("input_nickName").value,
                "sex": document.getElementById("input_sex").value,
                "age": document.getElementById("input_age").value,
                "universityName": document.getElementById("input_universityName").value,
                "instituteName": document.getElementById("input_instituteName").value,
                "entranceYear": document.getElementById("input_entranceYear").value,
                "email": document.getElementById("input_email").value,
                "teleNum": document.getElementById("input_teleNum").value,
            },
            success: function (data) {
                //将修改框变回文字
                if (data.returnCode == 0){
                    for (var i=0; i<length; i++){
                        var input = tds.item(i).firstChild;
                        var txt = input.value;
                        tds.item(i).removeChild(input);
                        tds[i].innerHTML = txt;
                    }
                    document.getElementById('saveUserInfo').style.visibility = "hidden";
                    document.getElementById('correct-btn').style.visibility = "visible";
                    alert("修改成功");
                }else if(data.returnCode == 1){
                    alert("请先登录或注册");
                }
            },
            //请求出错的处理
            error: function () {
                alert("系统忙，请稍后重试");
            }
        });
    }

//    function getInfo() {
//        $.ajax({
//            type: "post",//请求方式
//            url: "userInfo/getUserInfoAction",
//            timeout: 80000,//超时时间：8秒
//            dataType: "json",//设置返回数据的格式
//            success: function (data) {
//            //    alert("get::" + data.nickName);
////                document.getElementById("nickName").innerHTML =  data.nickName;
////                document.getElementById("sex").innerHTML = data.sex;
////                document.getElementById("age").innerHTML = data.age;
////                document.getElementById("universityName").innerHTML = data.universityName;
////                document.getElementById("instituteName").innerHTML = data.instituteName;
////                document.getElementById("entranceYear").innerHTML = data.entranceYear;
////                document.getElementById("email").innerHTML = data.email;
////                document.getElementById("teleNum").innerHTML = data.teleNum;
//            },
//            //请求出错的处理
//            error: function () {
//                alert("系统忙，请稍后重试");
//            }
//        })
//    }
</script>
</body>
</html>