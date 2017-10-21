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
    <link rel="stylesheet" href="css/uploadPortrait2.css">
    <link rel="stylesheet" href="css/jquery.Jcrop.css">
    <style type="text/css">
        /* Apply these styles only when #preview-pane has
           been placed within the Jcrop widget */
        #preview-pane {
            display: block;
            position: absolute;
            z-index: 2000;
            top: 92.8px;
            left: 410px;
            padding: 6px;
            border: 1px rgba(0,0,0,.4) solid;
            background-color: white;

            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;

            -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
            box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        }

        /* The Javascript code will set the aspect ratio of the crop
           area based on the size of the thumbnail preview,
           specified here */
        #preview-pane .preview-container {
            float: right;
            width: 200px;
            height: 200px;
            overflow: hidden;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, .25);
        }

        #theme-popover-mask {
            z-index: 9998;
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            background:#000;
            opacity:0.4;
            filter:alpha(opacity=40);
            display:none
        }

        #theme-popover {
            z-index:9999;
            position:fixed;
            top:50%;
            left:50%;
            width:660px;
            height:460px;
            margin:-180px 0 0 -330px;
            border-radius:5px;
            border:solid 2px #666;
            background-color:#fff;
            display:none;
            box-shadow: 0 0 10px #666;
        }

        #theme-poptit {
            border-bottom:1px solid #ddd;
            padding:12px;
            position: relative;
        }
        #theme-popbod {
            padding:20px 15px;
            color:#444;
            height: 148px;
        }

        #theme-poptit .close {
            float:right;
            color:#999;
            padding:5px;
            margin:-2px -5px -5px;
            font:bold 14px/14px simsun;
            text-shadow:0 1px 0 #ddd
        }
        #theme-poptit .close:hover {
            color:#444;
        }
    </style>
    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>

<body>
<header class="page-header slider-menu-position">
    <!-- RD Navbar-->
    <div class="rd-navbar-wrap">
        <nav data-md-device-layout="rd-navbar-fixed"
             data-lg-device-layout="rd-navbar-static"
             data-md-stick-up-offset="50px" data-lg-stick-up-offset="1px"
             data-layout="rd-navbar-fixed" data-sm-layout="rd-navbar-fixed"
             data-md-layout="rd-navbar-fixed" data-lg-layout="rd-navbar-fixed"
             class="rd-navbar rd-navbar-hamburger">
            <div class="rd-navbar-inner">
                <div class="rd-navbar-inner-top">
                    <!-- RD Navbar Brand-->
                    <div class="rd-navbar-brand veil reveal-lg-inline-block">
                        <a href="${pageContext.request.contextPath}/jsp/index" title="返回首页" class="brand-name"><img
                                style="margin-top: -10px;" width="164" height="29"
                                src="images/logo-164x29.png" alt="返回首页">
                        </a>
                    </div>
                </div>
                <!-- RD Navbar Panel-->
                <div class="rd-navbar-panel">
                    <!-- RD Navbar Toggle-->
                    <button data-rd-navbar-toggle=".rd-navbar-nav-wrap"
                            class="rd-navbar-toggle">
                        <span></span>
                    </button>
                    <div class="rd-navbar-brand veil-lg">
                        <a href="${pageContext.request.contextPath}/jsp/index" class="brand-name"><img
                                style="margin-top: -7px;" width="128" height="24"
                                src="images/logo-128x24.png" alt="">
                        </a>
                    </div>
                    <!-- RD Navbar Toggle-->
                    <button data-rd-navbar-toggle=".rd-navbar-collapse-wrap"
                            class="rd-navbar-collapse veil-lg">
                        <span></span>
                    </button>
                </div>
                <div class="rd-navbar-right-side">
                    <div class="rd-navbar-nav-wrap reveal-md-inline-block">
                        <ul class="rd-navbar-nav">
                            <!-- RD Navbar Nav-->
                            <li><a href="${pageContext.request.contextPath}/jsp/index">返回首页</a>
                            </li>
                            <li id="myPage"><a href="${pageContext.request.contextPath}/jsp/myPage">我的主页</a>
                            </li>
                            <li id="myBlog"><a href="${pageContext.request.contextPath}/jsp/myBlog">我的博客</a>
                            </li>
                            <li id="myForumpost"><a href="${pageContext.request.contextPath}/jsp/myForumpost">我的帖子</a>
                            </li>
                            <li id="myResource"><a href="${pageContext.request.contextPath}/jsp/myResource">我的学习资源</a>
                            </li>
                            <%--<li id="myNotifiMsg"><a href="${pageContext.request.contextPath}/jsp/myNotifiMsg">我的通知消息</a>--%>
                            <%--</li>--%>
                            <li id="myFollowers"><a href="${pageContext.request.contextPath}/jsp/myFollower">我的粉丝</a>
                            </li>
                            <li id="myFollowings"><a href="${pageContext.request.contextPath}/jsp/myFollowing">关注的人</a>
                            </li>
                            <li id="myCollection"><a href="${pageContext.request.contextPath}/jsp/collectedBlog">我的收藏</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <!-- Modern Breadcrumbs-->
    <section class="section-height-600 breadcrumb-modern rd-parallax context-dark">
        <div data-speed="0.2" data-type="media"
             data-url="images/backgrounds/background-04-1920x900.jpg"
             class="rd-parallax-layer"></div>
        <div data-speed="0" data-type="html" class="rd-parallax-layer">
            <div class="bg-overlay-chathams-blue">
                <div class="shell section-top-34 section-bottom-34 section-md-top-175 section-md-bottom-75 section-lg-top-158 section-lg-bottom-125 section-md-tablet-75">
                    <div class="offset-top-30">
                        <div class="cell-sm-10 cell-lg-8">
                            <img src="" width="125"  id="headPortrait" name="headPortrait"
                                 height="125" alt="" style="cursor:pointer;width:125px;height:125px"
                                 class="img-circle img-responsive center-block">
                        </div>
                    </div>
                    <div class="offset-top-35">
                        <ul class="list-inline list-marked-type-mid list-marked-type-2-dot-1 list-silver-chalice list-marked-silver-chalice"
                            style="font-family: 微软雅黑; font-size: 25px; letter-spacing: 2px; text-align:center">
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myPage"
                                                           target="_parent">主页</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myBlog"
                                                           target="_parent">博客</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myForumpost"
                                                           target="_parent">论坛帖</a>
                            </li>
                            <li class="text-spacing-50"><a href="${pageContext.request.contextPath}/jsp/myResource"
                                                           target="_parent">学习资源</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div id="theme-popover" style="position: fixed;">
        <div id="theme-poptit">
            <a href="javascript:;" title="关闭" class="close">×</a>
            <h6 style="font-size: 18px;font-family: 微软雅黑;letter-spacing: 1px; text-align: left">上传头像</h6>
        </div>
        <div id="theme-popbod">
            <div class="avatar-body block" id="portrait-area">
                <img src="" id="target" name="target" alt="" >
                <div id="preview-pane">
                    <div class="preview-container">
                        <img src="" class="jcrop-preview" id="preview-img" alt="" />
                    </div>
                </div>
            </div>
            <div class="group group-xs" style="padding-bottom: 30px; padding-right: 15px; float: right;">
                <form id="portraitForm" name="portraitForm" action="/jsp/PortraitUpload/PortraitUploadAction"
                      method="post" enctype="multipart/form-data" onsubmit="return confirmUpload()">
                    <button class="btn btn-xs btn-primary" type="button" id="portrait-choose" onclick="tmpClick()">选择图片</button>
                    <input id="imgUtil" name="imgUtil" type="file" onchange="checkPortrait(this)" style="display: none;">
                    <input type="hidden" id="x" name="x"/>
                    <input type="hidden" id="y" name="y"/>
                    <input type="hidden" id="w" name="w"/>
                    <input type="hidden" id="h" name="h"/>
                    <button class="btn btn-xs btn-primary" type="button" id="portrait-save" onclick="$('#portraitForm').submit();"> 保存修改</button>
                </form>
            </div>
        </div>
    </div>
    <div id="theme-popover-mask"></div>
</header>


<!-- Java script-->
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
<!--	<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="js/layer-v2.0/layer/layer.js"></script>
	<script type="text/javascript" src="js/WhwUtil.js"></script>-->
<script type="text/javascript" src="js/jquery.cookie.js"></script>

<script type="text/javascript" src="js/sitelogo.js"></script>
<script type="text/javascript" src="js/Jcrop/jquery.Jcrop.js"></script>
<script type="text/javascript" src="js/Jcrop/jquery.min.js"></script>

<script type="text/javascript">
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var is_valid = false;
    var filePath = "";
    var picUrl = "";
    var jcrop_api = null,
        boundx = 0,
        boundy = 0,
        // Grab some information about the preview pane
        $preview,
        $pcnt,
        $pimg,
        xsize = 0,
        ysize = 0;

    jQuery(document).ready(function($) {
        $('#headPortrait').click(function(){
            $('#theme-popover-mask').fadeIn(100);
            $('#theme-popover').slideDown(200);
            $preview = $('#preview-pane');
            $pcnt = $('#preview-pane .preview-container');
            $pimg = $('#preview-pane .preview-container img');
            xsize = $pcnt.width();
            ysize = $pcnt.height();
        })
        $('#theme-poptit .close').click(function(){
            $('#theme-popover-mask').fadeOut(100);
            $('#theme-popover').slideUp(200);
        })

    })

    function getWidthandHeight(){
        var tmpImg = document.createElement('img');
        tmpImg.src = picUrl;
        boundx = tmpImg.width;
        boundy = tmpImg.height;
    }

    function initJcrop(){
    //    alert("start initing");
        $('#target').Jcrop({
            onChange: updatePreview,
            onSelect: updatePreview,
            aspectRatio: 1,
            boxWidth: 300,
            boxHeight: 300
        },function(){
         //   alert("into jcrop()");
            if (picUrl != ""){
                jcrop_api = this;
                jcrop_api.setImage(picUrl);
                //    alert("set img:"+picUrl);
                var bounds = jcrop_api.getBounds();
                boundx = bounds[0];
                boundy = bounds[1];
                //    alert(boundx + "," + boundy);
                //解决bug，jcrop自带的函数getBounds()有的时候会失效也不知道为什么=-=
                if (isNaN(boundx) || isNaN(boundy)){
                    getWidthandHeight();
                    //        alert(boundx + "," + boundy);
                }

                // Move the preview into the jcrop container for css positioning
                $preview.appendTo(jcrop_api.ui.holder);
                //保持预览框位置
                $preview.css({
                    top: '0px',
                    left:'385px'
                });
                //导入预览框图片
                document.getElementById('preview-img').src = picUrl;
            }else{
                alert("图片打开失败");
            }

        });
    }

    function updatePreview(c) {
        if (parseInt(c.w) > 0)
        {
            // preview容器大小/选框大小
            var rx = xsize / c.w;
            var ry = ysize / c.h;

            //调整预览图显示位置
            $pimg.css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px'
            });

            //保存选框的左顶点坐标和宽高
            $("#x").val(c.x);
            $("#y").val(c.y);
            $("#w").val(c.w);
            $("#h").val(c.h);
        }
    };

    //做个下简易的验证  大小 格式
    function tmpClick() {
    //    alert("tmpclick");
        document.getElementById("imgUtil").click();
    }

    /*头像文件类型、大小校验*/
    function checkPortrait(input) {
    //    alert("checkPortrait");
        var file = $("#imgUtil").val();
        //  alert($("#imgUtil").val());
        /*  var strFileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,
         "$1"); //正则表达式获取文件名，不带 后缀 */
        var FileExt = file.replace(/.+\./, ""); //正则表达式获取后缀
        var addHtml = "";
        is_valid = true;
        if (FileExt != "jpg" && FileExt != "bmp" && FileExt != "png") {
            addHtml = "文件类型错误=-=";
            is_valid = false;
        } else {
            //GetFile方法报错说找不到
            is_valid = true;
//            var fileSize = 0;
//            if (isIE && !input.files) { // IE浏览器
//                filePath = input.value; // 获得上传文件的绝对路径
//                var fileSystem = new ActiveXObject(
//                    "Scripting.FileSystemObject");
//                // GetFile(path) 方法从磁盘获取一个文件并返回。
//                var file = fileSystem.GetFile(filePath);
//                fileSize = file.Size; // 文件大小，单位：b
//            } else { // 非IE浏览器
//                fileSize = target.files[0].size;
//            }
//            var size = fileSize / 1024 / 1024;
//            if (size > 3) {
//                addHtml = "头像大小超过3M，请重新上传0.0";
//            }
        }

        if (input.files && input.files[0] && addHtml == "") {
            var reader = new FileReader();
            reader.readAsDataURL(input.files[0]);
            reader.onload = function (e) {
                picUrl = e.target.result;
    //            alert("initial, picUrl:"+ e.target.result);
                if (jcrop_api != null){
    //                alert("destroy");
                    jcrop_api.destroy();
                }
                //初始Jcrop
                initJcrop();
            };
        }

        $("#file_error").empty().append(addHtml);
    }

    function check() {
        //document.getElementById("file_upload_div").style.color = "red";
        return is_valid;
    }
    //form的onsubmit是在button的onclick函数执行完之后再执行的
    function confirmUpload() {
    //    alert("confirm");
        var validInfo = check();
        if (validInfo == false) {
            alert("未选择图片或图片文件类型错误!");
            return false;
        }
        //   alert("confirm over");
    }

    /*更新头像，即当页面加载时从后台获取头像地址*/
    function refreshPortrait() {
    //    alert("refresh");
        $.ajax({
            url: "GetPortrait/GetPortraitAction",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.portraitUrl != null) {
                    document.getElementById("headPortrait").src = data.portraitUrl;
                    //  alert(data.portraitUrl);
                }
                else {
                    //默认头像
                    document.getElementById("headPortrait").src = "images/users/user-april-smith-169x169.jpg";
                }
                //    alert(document.getElementById("headPortrait").src + "::refresh success");
            },
            error: function () {
                document.getElementById("headPortrait").src = "images/users/user-april-smith-169x169.jpg";
                alert("系统忙，请稍后重试");
            }
        })
    }

    /*页面加载时更新头像*/
    window.addEventListener('load', refreshPortrait, false);

</script>
</body>
</html>
