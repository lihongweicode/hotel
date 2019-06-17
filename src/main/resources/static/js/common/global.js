/**
 * @描述 :  token管理的工具类
 * @作者 :	lhw
 * @日期 :	2018/12/3
 * @时间 :	11:24
 */
layui.define([],function(exports){
    var global = {
        getUploadFtpUrl : function () {
            return "http://192.168.14.204";
        },
    };

    //将tokenUtil设置为window对象方便引用
    exports('global',global);
});