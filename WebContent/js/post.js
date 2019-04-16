function beforeSubmit() {
    var pContent = $('#content-post textarea').val();
    var des = pContent.replace(/\r\n/g, '<br/>').replace(/\n/g, '<br/>').replace(/\s/g, ' ');
    $('#content-post textarea').val(des);
}