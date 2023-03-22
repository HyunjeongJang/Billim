src="http://code.jquery.com/jquery-1.11.2.min.js"
$(document).ready(function () {
    $("#nav_menu ul.sub").hide();
    $("#nav_menu ul.menu_list li").click(function () {
        $("ul", this).slideToggle("fast");
    });
});

