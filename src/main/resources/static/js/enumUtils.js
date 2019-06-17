/** kit_admin-v1.1.0 MIT License By http://kit/zhengjinfan.cn e-mail:zheng_jinfan@126.com */
;/**
 * Name:enumUtils.js
 * Author:gaoj
 */
layui.define([], function (exports) {
    var $ = layui.jquery;
    var utils = {
        getEnum:function (enu) {
            return layui.sessionData(enu)[enu];
        },
        /**
         * 传入枚举和枚举值，获取枚举名称
         */
        getName: function (enu, dvalue) {
            var enums = this.getEnum(enu);
            for (var i = 0; i < enums.length; i++) {
                if (enums[i].value == dvalue + "") {
                    return enums[i].name
                }
            }
        },
        /**
         * 为select添加枚举下拉选项
         */
        getOptions: function (enu, element, form) {
            var enums = this.getEnum(enu);
            var html = "";
            for (var i = 0; i < enums.length; i++) {
                html += '<option value="' + enums[i].value + '">' + enums[i].name + '</option>'
            }
            $(element).append(html);
            form.render('select');
        }
    };
    exports('enumUtils', utils);
});