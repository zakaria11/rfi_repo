<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="en">
	
	<jsp:include page="/includes/header.jsp"></jsp:include>

    <script></script>

    <script>
        (function($) {
            $.StartScreen = function(){
                var plugin = this;
                var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;

                plugin.init = function(){
                    setTilesAreaSize();
                    if (width > 640) addMouseWheel();
                };

                var setTilesAreaSize = function(){
                    var groups = $(".tile-group");
                    var tileAreaWidth = 80;
                    $.each(groups, function(i, t){
                        if (width <= 640) {
                            tileAreaWidth = width;
                        } else {
                            tileAreaWidth += $(t).outerWidth() + 80;
                        }
                    });
                    $(".tile-area").css({
                        width: tileAreaWidth
                    });
                };

                var addMouseWheel = function (){
                    $("body").mousewheel(function(event, delta, deltaX, deltaY){
                        var page = $(document);
                        var scroll_value = delta * 50;
                        page.scrollLeft(page.scrollLeft() - scroll_value);
                        return false;
                    });
                };

                plugin.init();
            }
        })(jQuery);

        $(function(){
            $.StartScreen();

            var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

            $.each(tiles, function(){
                var tile = $(this);
                setTimeout(function(){
                    tile.css({
                        opacity: 1,
                        "-webkit-transform": "scale(1)",
                        "transform": "scale(1)",
                        "-webkit-transition": ".3s",
                        "transition": ".3s"
                    });
                }, Math.floor(Math.random()*500));
            });

            $(".tile-group").animate({
                left: 0
            });
        });
    </script>
</head>
<body style="overflow-y: hidden;">
    <div data-role="charm" id="charmSearch">
        <h1 class="text-light">Search</h1>
        <hr class="thin"/>
        <br />
        <div class="input-control text full-size">
            <label>
                <span class="dropdown-toggle drop-marker-light">Anywhere</span>
                <ul class="d-menu" data-role="dropdown">
                    <li><a>Anywhere</a></li>
                    <li><a>Options</a></li>
                    <li><a>Files</a></li>
                    <li><a>Internet</a></li>
                </ul>
            </label>
            <input type="text">
            <button class="button"><span class="mif-search"></span></button>
        </div>
    </div>

    <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">
	
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
	
        <div class="tile-group double">
            <span class="tile-group-title">General</span>

            <div class="tile-container">

                <a href="<%=request.getContextPath()%>/event/choice" class="tile bg-indigo fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-calendar"></span>
                    </div>
                    <span class="tile-label">Evenements</span>
                </a>

<%--                 <a href="<%=request.getContextPath()%>/event/administration" class="tile bg-lime fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-cogs"></span>
                    </div>
                    <span class="tile-label">Reserver</span>
                </a> --%>
            </div>
        </div>

        <div class="tile-group double">
            <span class="tile-group-title">options</span>
            <div class="tile-container">
                <a href="<%=request.getContextPath()%>/admin/manageEvent.jsp" class="tile bg-teal fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-pencil"></span>
                    </div>
                    <span class="tile-label">Administration</span>
                </a>
                <a href="<%=request.getContextPath()%>/reporting/index.jsp" class="tile bg-darkGreen fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-chart-dots"></span>
                    </div>
                    <span class="tile-label">Reporting</span>
                </a>
            </div>
        </div>
    </div>


</body>
</html>