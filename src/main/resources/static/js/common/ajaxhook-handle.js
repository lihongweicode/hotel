hookAjax(
    {
        send: function (arg, xhr) {
            xhr.setRequestHeader("X-Token", $.cookie('X-Token'));
        }
    }
);