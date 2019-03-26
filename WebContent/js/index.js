/**
 * 初始化bootstrapValidator插件的参数，用于表单验证
 */
$(function () {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            uId: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '学号不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '学号只能包含数字'
                    }
                }
            },
            uPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码只能包含大小写英文字母、数字'
                    }
                }
            }
        }
    });
});