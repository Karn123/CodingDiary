/**
 * Created by 19068 on 2017/2/15.
 */
src="js/core.min.js"
src="js/script.js"

var change_times = 1, num = 0;
var is_to_end = false;
var tags = new Array();
// 按逗号分隔多个值
function split(val) {
    return val.split(/,\s*/);
}
function AddElementInChoosing(id, tagID, tagName) {
    var btn = "<button class='btn btn-smalltags btn-white btn-icon btn-icon-right text-turquoise' style='font-family: 微软雅黑; font-size: 12px;padding: 4px 8px' id='";
    btn += id;
    btn += "' value='";
    btn += tagID;
    btn += "'><span class='icon icon-xs material-icons-ico material-icons-close' style='width: 15px;height: 15px;line-height: 21px;'></span>";
    btn += tagName;
    btn += "</button>";
    $("#choosing").append($(btn));
    $("#" + id).bind("click", function () {
        $("#" + id).remove();
        AddElementInChosen(id, tagID, tagName);
    });
}
function AddElementInChosen(id, tagID, tagName) {
    var btn = "<button class='btn btn-smalltags btn-white btn-icon btn-icon-right text-turquoise' style='font-family: 微软雅黑; font-size: 12px;padding: 4px 8px' id='";
    btn += id;
    btn += "' value='";
    btn += tagID;
    btn += "'><span class='icon icon-xs material-icons-ico material-icons-close' style='width: 15px;height: 15px;line-height: 21px;'></span>";
    btn += tagName;
    btn += "</button>";
    $("#chosen").append($(btn));
    $("#" + id).bind("click", function () {
        $("#" + id).remove();
        AddElementInChoosing(id, tagID, tagName);
    });
}
Array.prototype.remove = function (element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            for (var j = i; j < this.length - 1; j++) {
                this[i] = this[i + 1];
            }
            this.pop();
            break;
        }
    }
}
function changeNewTags() {
    if (is_to_end) {
        change_times = 1;
        is_to_end = false;
    }
    $("#selectTag").empty().append("<font size=\"4px\" color=\"gray\">选择标签：</font><div id=\"choosing\" class=\"form-group offse-top-5\">");

    $.ajax({
        type: "post",//请求方式
        url: "tag/getNewTag",
        timeout: 8000,//超时时间：8秒
        dataType: "json",//设置返回数据的格式
        data: {
            "change_times": change_times
        },
        //请求成功后的回调函数 data为json格式
        success: function (data) {
            if (data.retCode == "1") {
                is_to_end = true;
            }
            else {
                change_times++;
                //alert("请求成功");
            }
            var tagNameStr = data.tagNameList;
            var tagName = new Array();
            var tagIDStr = data.tagIDList;
            var tagID = new Array();
            tagName = tagNameStr.split(",");
            tagID = tagIDStr.split(",");
            //alert("请求成功");
            for (var i = 0; i < tagID.length; i++) {
                AddElementInChoosing("tag" + num, tagID[i], tagName[i]);
                num++;
            }
            //alert(data.yourName+"你输入的内容:"+data.yourContent);
        },
        //请求出错的处理
        error: function () {
            //alert("请求出错");
        }
    });

}
function createNewTags() {
    var new_tag = document.getElementById("tag").value;
    $.ajax({
        type: "post",//请求方式
        url: "tag/createNewTag",
        timeout: 8000,//超时时间：8秒
        dataType: "json",//设置返回数据的格式
        data: {
            "new_tag": new_tag
        },
        //请求成功后的回调函数 data为json格式
        success: function (data) {
            AddElementInChosen("tag" + num, data.retCode, new_tag);
            num++;
            //alert(data.retCode);
            //alert(data.yourName+"你输入的内容:"+data.yourContent);
        },
        //请求出错的处理
        error: function () {
            alert("请求出错");
        }
    });
}
function createText() {
    var btn = "<button class='btn btn-xs btn-ellipse btn-primary btn-icon' id='createTag'>";
    btn += "创建标签";
    btn += "</button><br><br>";
    var text = "<input id='tag' class='form-control form-control-has-validation form-control-last-child' type='text' style='height: 45px; width:70%'><br>";
    $("#newTag").empty().append($(text));
    $("#newTag").append($(btn));
    $("#createTag").bind("click", function () {
        if($("#tag").val()!="") {
            createNewTags();
            document.getElementById("tag").value = "";
        }
        else{
            alert("请填写标签哦");
        }
    });
}