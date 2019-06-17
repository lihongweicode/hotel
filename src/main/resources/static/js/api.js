/**
 * api接口列表
 * Created by gameloft9 on 2018/7/19.
 */
layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;// 拿到模块变量

    function successVal(req,successCallback) {
        var data = req;
        if(typeof data == "string"){
            data = eval('(' + data + ')');
        }
        if (data.code == 200) {
            successCallback(data);
        }else if(data.code == 430){
            if(data.msg == '权限认证失败，请重新登录！') {
                layer.alert(data.msg, {
                    icon: 6,
                    yes:function(){
                        window.location.href = "/login.html";
                    }
                });
            }else {
                layer.alert(data.msg, {
                    icon: 6,
                    yes:function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        if(index != null){
                            parent.layer.close(index)
                        }else{
                            window.location.reload();
                        }
                    }
                });
            }

        }
    }
    function errorVal(error) {
        layer.alert(data.msg, {
            icon: 6,
            yes:function(){
                var index = parent.layer.getFrameIndex(window.name);
                if(index != null){
                    parent.layer.close(index)
                }else{
                    window.location.reload();
                }
            }
        });
    }

    function successValGet(req,successCallback) {
        var data = req;
        if(typeof data == "string"){
            data = eval('(' + data + ')');
        }
        if (data.code == 200) {
            successCallback(data.data);
        }else if(data.code == 430){
            if(data.msg == '权限认证失败，请重新登录！') {
                layer.alert(data.msg, {
                    icon: 6,
                    yes:function(){
                        window.location.href = "/login.html";
                    }
                });
            }else {
                layer.alert(data.msg, {
                    icon: 6,
                    yes:function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        if(index != null){
                            parent.layer.close(index)
                        }else{
                            window.location.reload();
                        }
                    }
                });
            }
        }
    }

    // API列表,工程庞大臃肿后可以将API拆分到单独的模块中
    var API = {
        /**
         * 封装一个post
         * */
        doPost : function (url,req,successCallback,errorCallback) {
            $.ajax({
                url:url,
                data:req,
                method:"post",
                success:function (data) {
                    successVal(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            });
        },
        /**
         * 传输json的 post方法
         * @param url
         * @param req
         * @param successCallback
         */
        doPostJson : function (url,req,successCallback) {
            $.ajax({
                contentType:"application/json;charset=utf-8",
                type:"POST",
                traditional:true,
                dataType:"JSON",
                data:JSON.stringify(req),
                url:url,
                success:function(data){
                    successVal(data,successCallback);
                },
                error:function(error){
                    errorVal(error);
                }
            });
        },
        /**
         * 封装一个get
         * */
        doGet : function (url,req,successCallback,errorCallback) {
            $.ajax({
                url:url,
                data:req,
                method:"get",
                success:function (data) {
                    successValGet(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            });
        },
        /**
         * 同步请求
         * */
        doGetAsync : function (url,req,successCallback,async) {
            $.ajax({
                url:url,
                data:req,
                method:"get",
                async:async,
                success:function (data) {
                    successValGet(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                }
            });
        },
        /**
         * 封装一个支持更多参数的post
         * */
        doComplexPost : function (url,req,config,successCallback,errorCallback) {
            var defaultConfig = {
                url:url,
                data:req,
                method:"post",
                success:function (data) {
                    successVal(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            };
            var ajaxConfig = $.extend({},config,defaultConfig); // 合并参数
            $.ajax(ajaxConfig);
        },
        doPut : function (url,req,successCallback,errorCallback) {
            $.ajax({
                url:url,
                data:req,
                method:"put",
                success:function (data) {
                    successVal(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            });
        },
        doPatch : function (url,req,successCallback,errorCallback) {
            $.ajax({
                url:url,
                data:req,
                method:"patch",
                success:function (data) {
                    successVal(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            });
        },
        doDelete : function (url,req,successCallback,errorCallback) {
            $.ajax({
                url:url,
                data:req,
                method:"delete",
                success:function (data) {
                    successVal(data,successCallback);
                },
                error:function (error) {
                    errorVal(error);
                    // errorCallback(error);
                }
            });
        }
    };

    //输出扩展模块
    exports('$api', API);
});


