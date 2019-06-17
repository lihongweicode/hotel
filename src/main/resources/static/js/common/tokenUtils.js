/**
 * @描述 :  token管理的工具类
 * @作者 :	lhw
 * @日期 :	2018/12/3
 * @时间 :	11:24
 */
layui.define([],function(exports){
    var tokenUtils = {
        setToken : function (accessToken) {
            var date = new Date();
            date.setTime(date.getTime()+25*60*1000);
            $.cookie("X-Token",accessToken,{expires: date});
        },
        getToken : function () {
            var token = $.cookie("X-Token");
            //如果token为空
            if (token == null || token.length <= 0) {
                window.location = "../../login.html";
            }else {
                return token;
            }
        },
        verify : function () {
            /** lhw 2018/12/3 11:09  验证token是否存在，如果不存在则跳转至登录页面 */
            var token = $.cookie("X-Token");
            //如果token为空
            if (token == null || token.length <= 0) {
                window.location = "../../login.html";
            }
        },
        //调用后台进行验证，当前后台token是保存在Redis中，不会出现项目重启后token过期现象；
        // 如果后期修改token保存规则，则可以调用该方法验证token
        requestVerify : function () {
            var token = $.cookie("X-Token");
            $.ajax({
                type: "POST",
                url: '/auth/tokenValid',
                data:{token: token},
                dataType: 'json',
                async: true,
                success: function (data) {
                    if (data.code == 400) {
                        $.removeCookie("X-Token");
                        //跳转页面
                        window.location = "login.html";
                    }
                }
            });
        }
    };

    //将tokenUtil设置为window对象方便引用
    exports('tokenUtils',tokenUtils);
});