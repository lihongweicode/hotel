    function openMsg(code) {
        if (code == 10000) {
            layer.msg("成功!", {icon: 1});
        } else if (code == 20000) {
            layer.msg("失败!", {icon: 2});
        } else if (code == null) {
            layer.msg("未知错误!", {icon: 7});
        }
    }

    /*关闭弹出框口*/
    function goBackList(){
        //window.parent.location.reload();
        window.parent.$("#sreach").trigger("click"); //数据刷新
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index)
    }

    /*处理时间类型函数*/
    function getLocalTime(nS,type) {
        var now = new Date(parseInt(nS));
        var   year=now.getFullYear();
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();

        if(type==1){
            //返回年月日时分秒
            return year + "-" + (month < 10 ? "0"+ month : month) + "-" + (date < 10 ? "0" + date : date) +" "
            + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
            + (second < 10 ? "0" + second : second);
        }
        if(type==2){
            //返回年月日
            return year + "-" + (month < 10 ? "0"+ month : month) + "-" + (date < 10 ? "0" + date : date);
        }
        if(type==3){
            //返回时分秒
            return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
                + (second < 10 ? "0" + second : second);
        }
    }
    /*弹出层*/
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    function x_admin_show(title, url, w, h, obj) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "404.html";
        }
        if (w == null || w == '') {
            w = 800;
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            btn: ['保存', '取消'], //只是为了演示
            title: title,
            content: url,
            yes: function (index, layero) {
                //调用子页面提交方法
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.subData();
            },
            btn2: function () {
                layer.close();
            },
            success: function (layero, index) {
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //初始化 传递参数
                iframeWin.init(obj);
            }
        });
    }


    /*关闭弹出框口*/
    function x_admin_close() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
