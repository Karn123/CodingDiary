<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
<head>
    <title>上传资源</title>
    <meta name="format-detection" content="telephone=no">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Stylesheets-->

    <link rel="stylesheet" href="css/jquery.nice-file-input.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css">
    <!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;">
        <a href="http://windows.microsoft.com/en-US/internet-explorer/"><img
                src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820"
                alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a>
    </div>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
    <%--ajaxfileupload与core.min.js冲突--%>
</head>
<body>
<!-- Page-->
<div class="page text-center">
    <!-- Page Header-->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Page Content-->
    <main class="page-content section-75 section-md-top-90 section-md-bottom-110">
        <section>
            <form action="/jsp/uploadFile/file_upload" method="post" onsubmit="return confirmUpload()"
                  enctype="multipart/form-data">
                 <div class="shell">
                     <div class="range range-xs-center range-lg-center">
                         <div class="cell-xs-8 cell-sm-7 text-left">
                             <div class="offset-top-20">
                                 <h4 class="text-mine-shaft text-spacing-75 text-center text-sm-left"
                                     style="font-family: 微软雅黑">上传资源</h4>
                             </div>
                             <div class="offset-top-60">
                                 <input type="file" id="file" name="file" accept=".pdf,.doc,.docx,.ppt,.pptx,.zip,.rar" onchange="get_file_name(this)" class="nicefile" />
                                    <div id="file_upload_div">
                                            请选择pdf/doc/docx/ppt/pptx/.zip/.rar格式的文件，大小不超过200MB，文件数量1个。
                                    </div>
                             </div>
                             <output id="selectedFiles"></output>
                             <div class="offset-top-30">
                                    <p class="font-accent text-bold text-mine-shaft text-spacing-30 text-center text-sm-left">
                                        标签<span class="text-light">(可点击"选择标签"更新可选标签)</span></p>
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
                                         资源描述</p>
                                     <div class="form-group">
                                         <textarea id="doc_description" rows="5" name="doc_description" class="form-control"></textarea>
                                     </div>
                             </div>
                             <div id="file_error" style="color: red"></div>
                             <div class="text-center text-sm-right offset-top-30">
                                 <button id="uploadButton" type="submit" class="btn btn-primary">确认上传</button>
                             </div>
                         </div>

                    </div>
                 </div>
                 <div class="form-group invisible">
                     <!-- <label for="upload_doc_name" class="form-label">给资料取个醒目的名称吧~</label> -->
                     <input id="upload_doc_name" type="text" name="upload_doc_name" class="form-control">
                 </div>
                 <div class="invisible">
                     <!-- <label for="doc_tag_name" class="form-label">资料相关的标签和关键词</label> -->
                     <input id="doc_tag_name" type="text" name="doc_tag_name" class="form-control"/>
                 </div>
            </form>
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
<script src="js/dist/jquery.nice-file-input.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/tags.js"></script>
<script type="text/javascript">
//    document.getElementById("theme").innerHTML="上 传 资 源";
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var is_valid = false;
    var filePath = "";
    function get_file_name(target) {
        var file = $("#file").val();
        /*  var strFileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,
         "$1"); //正则表达式获取文件名，不带	后缀 */
        var strFileName = file.substring(0,file.lastIndexOf("."));
        strFileName = strFileName.substring(strFileName.lastIndexOf("\\")+1);
        var FileExt = file.replace(/.+\./, ""); //正则表达式获取后缀
        document.getElementById("upload_doc_name").value = strFileName
            + "." + FileExt;
        var addHtml = "";
        is_valid = true;
        if (FileExt != "pdf" && FileExt != "doc" && FileExt != "docx"
            && FileExt != "ppt" && FileExt != "pptx"
            && FileExt != "zip" && FileExt != "rar") {
            addHtml = "文件类型错误=-=";
            is_valid = false;
        } else {
            is_valid = true;
            var fileSize = 0;
            if (isIE && !target.files) { // IE浏览器
                filePath = target.value; // 获得上传文件的绝对路径
                var fileSystem = new ActiveXObject(
                    "Scripting.FileSystemObject");
                // GetFile(path) 方法从磁盘获取一个文件并返回。
                var file = fileSystem.GetFile(filePath);
                fileSize = file.Size; // 文件大小，单位：b
            } else { // 非IE浏览器
                fileSize = target.files[0].size;
            }
            var size = fileSize / 1024 / 1024;
            if (size > 200) {
                is_pdf_correct = false;
                addHtml = "文件大小超过200M，请重新上传0.0";
            }
        }
        $("#file_error").empty().append(addHtml);
    }
    function check() {
        //document.getElementById("file_upload_div").style.color = "red";
        setTimeout(
            "document.getElementById(\"file_upload_div\").style.color=\"\"",
            200);
        return is_valid;
    }
    //form的onsubmit是在button的onclick函数执行完之后再执行的
    function confirmUpload() {
        var validInfo = check();
        if (validInfo == false) {
            $("#file_error").empty().append("未选择文件或文件类型错误0.0");
            return false;
        }
        jQuery.fn.getTagList = function () {
            var tagsList = this.map(function () {
                return this.value;
            }).get();
            return tagsList.join(";");
        };
        var tags_str = $("#chosen").children().getTagList();
        document.getElementById("doc_tag_name").value = tags_str;
        //alert(document.getElementById("doc_tag_name").value);
        if(tags_str==""){
            $("#file_error").empty().append("标签不能为空哦0.0");
            return false;
        }
        if($("#doc_description").val()==""){
            $("#file_error").empty().append("资源描述不能为空哦0.0");
            return false;
        }
    }

    $('document').ready(function(){
        $(".nicefile").niceFileInput({
            'width'         : '700', //width of button - minimum 150
            'height'		  : '30',  //height of text
            'btnText'       : '选择文件', //text of the button
            'btnWidth'	  : '80' ,  // width of button
            'margin'        : '20',	// gap between textbox and button - minimum 14
        });

    });
</script>
</body>
</html>