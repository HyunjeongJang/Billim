// nav 마이페이지 메뉴 접기
$(document).ready(function () {
    $("#nav_menu ul.sub").hide();
    $("#nav_menu ul.menu_list li").click(function () {
        $("ul", this).slideToggle("fast");
    });
});

