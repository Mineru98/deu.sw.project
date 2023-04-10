$(function () {
    const pathname = window.location.pathname;
    if (pathname.split("/").length > 0) {
        let navBarId = 0;
        switch (pathname.split("/")[1]) {
            case "company":
                navBarId = 1;
                break;
            case "team":
                navBarId = 2;
                break;
            case "rank":
                navBarId = 3;
                break;
            case "allowance":
                navBarId = 4;
                break;
            case "user":
                navBarId = 5;
                break;
            case "schedule":
                navBarId = 6;
                break;
            case "statement":
                navBarId = 7;
                break;
            case "health":
                navBarId = 8;
                break;
            case "account":
                navBarId = 9;
                break;
        }
        $("#navBar-" + navBarId).removeClass("bg-slate-200").addClass("bg-slate-100").addClass("on");
    }

    // 메뉴바 제어 클릭
    $("#menuControl").on("click", function () {
        if ($("#navBar").hasClass("visible")) {
            $("#navBar").fadeOut(200);
            $("#navBar").removeClass("visible");
        } else {
            $("#navBar").fadeIn(200);
            $("#navBar").addClass("visible");
        }
    });

    // 네비게이션바 클릭
    $("[id^=navBar-]").on("click", function () {
        if (!$(this).hasClass("on")) {
            $(this).siblings().removeClass("bg-slate-100").addClass("bg-slate-200").removeClass("on");
            $(this).removeClass("bg-slate-200").addClass("bg-slate-100").addClass("on");
            switch ($(this).attr("id")) {
                case "navBar-1":
                    window.location.href = "/company/detailView";
                    break;
                case "navBar-2":
                    window.location.href = "/team/list";
                    break;
                case "navBar-3":
                    window.location.href = "/rank/list";
                    break;
                case "navBar-4":
                    window.location.href = "/allowance/list";
                    break;
                case "navBar-5":
                    window.location.href = "/user/list";
                    break;
                case "navBar-6":
                    window.location.href = "/schedule/list";
                    break;
                case "navBar-7":
                    window.location.href = "/statement/list";
                    break;
                case "navBar-8":
                    window.location.href = "/health/list";
                    break;
                case "navBar-9":
                    window.location.href = "/account/list";
                    break;
            }
        }
    });
});
