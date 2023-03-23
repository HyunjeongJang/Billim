$(document).ready(function () {
    $("#nav_menu ul.sub").hide();
    $("#nav_menu ul.menu_list li").click(function () {
        $("ul", this).slideToggle("fast");
    });
});