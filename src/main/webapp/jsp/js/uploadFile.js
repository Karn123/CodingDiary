var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var is_valid = false;
var filePath = "";

var totalFileLength, totalUploaded, fileCount, filesUploaded;

function get_file_name(target) {
    var file = $("#files").val();
    /*  var strFileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,
     "$1"); //正则表达式获取文件名，不带	后缀 */
    var strFileName = file.substring(0, file.lastIndexOf("."));
    strFileName = strFileName.substring(strFileName.lastIndexOf("\\") + 1);
    var FileExt = file.replace(/.+\./, ""); //正则表达式获取后缀
    document.getElementById("upload-doc-name").value = strFileName
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
            addHtml = "文件大小超过200M，请重新上传0.0";
        }
    }
    $("#file_error").empty().append(addHtml);
}

function debug(s) {
    var debug = document.getElementById('debug');
    if(debug){
        debug.innerHTML = debug.innerHTML + '<br/>'+s;
    }
}

function onUploadComplete(e) {
    totalUploaded += document.getElementById('files').files[filesUploaded].size;
    filesUploaded++;
    debug('complete ' + filesUploaded + " of " + fileCount);
    debug('totalUploaded:'+totalUploaded);
    if(filesUploaded < fileCount) {
        uploadNext();
    }else{
        var bar = document.getElementById('bar');
        bar.style.width = '100%';
        bar.innerHTML = '100% complete';
        alert('Finished uploading files(s)');
    }
}

function onFileSelect(e) {
    var files = e.target.files; //FileList Object
    var output = [];
    fileCount = files.length;
    totalFileLength = 0;
    for(var i=0;i<fileCount;i++){
        var file = files[i];
        output.push(file.name,' (',file.size,' bytes, ',file.lastModifiedDate.toLocaleDateString(), ')');
        output.push('<br/>');
        debug('add '+ file.size);
        totalFileLength += file.size;
    }
    document.getElementById('selectedFiles').innerHTML = output.join('');
    debug('totalFileLength: '+totalFileLength);
}

function onUploadProgress(e) {
    if(e.lengthComputable){
        var percentComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
        var bar = document.getElementById('bar');
        bar.style.width = percentComplete + '%';
        bar.innerHTML = percentComplete + ' % complete';
    }else{
        debug('unable to compute');
    }
}

function onUploadedFailed(e) {
    alert("Error uploading file");
}

function uploadNext() {
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    var file = document.getElementById('files').files[filesUploaded];
    fd.append("multipartFile",file);
    xhr.upload.addEventListener("progress", onUploadProgress, false);
    xhr.addEventListener("load", onUploadComplete, false);
    xhr.addEventListener("error", onUploadedFailed, false);
    xhr.open("POST", "test/file_upload");
    debug('uploading '+ file.name);
    xhr.send(fd);
}

function startUpload() {
    totalUploaded = filesUploaded = 0;
    uploadNext();
}

window.onload = function() {
    document.getElementById('files').addEventListener('change', onFileSelect, false);
    document.getElementById('uploadButton').addEventListener('click', startUpload, false);
}