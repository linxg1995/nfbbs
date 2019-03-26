$(document).ready(function () {
    /**
     * 初始化swiper轮播
     */
    var mySwiper = new Swiper('.swiper-container', {
        // 分页器
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        // 切换按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        // 循环
        loop: true,
        // 自动切换
        autoplay: {
            delay: 3000,
            stopOnLastSlide: false,
            disableOnInteraction: false,
        },
    })
    /**
     * 选取奇偶
     */
    $('#content-post ul:even>li:even').css("background-color", "#F2F2F2");
    $('#content-post ul:odd>li:odd').css("background-color", "#F2F2F2");
});