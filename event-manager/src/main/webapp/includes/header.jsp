<%@ page pageEncoding="UTF-8"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">

<link rel='shortcut icon' type='image/x-icon' href='../favicon.ico' />
<title>EventManager</title>

<link href="<%=request.getContextPath()%>/css/metro.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/metro-icons.css" rel="stylesheet">
<!--<link href="css/metro-responsive.css" rel="stylesheet">-->

<script src="<%=request.getContextPath()%>/js/jquery-2.1.3.js"></script>
<script src="<%=request.getContextPath()%>/js/metro.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>


<style>
    .tile-area-controls {
        position: fixed;
        right: 40px;
        top: 40px;
    }

    .tile-group {
        left: 100px;
    }

    .tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super {
        opacity: 0;
        -webkit-transform: scale(.8);
        transform: scale(.8);
    }

    #charmSettings .button {
        margin: 5px;
    }

    .schemeButtons {
        /*width: 300px;*/
    }

    @media screen and (max-width: 640px) {
        .tile-area {
            overflow-y: scroll;
        }
        .tile-area-controls {
            display: none;
        }
    }

    @media screen and (max-width: 320px) {
        .tile-area {
            overflow-y: scroll;
        }

        .tile-area-controls {
            display: none;
        }

    }
</style>

<!-- Fav and touch icons -->
<script>
var contextPath = '<%=request.getContextPath()%>';
var roomsList = null;
var adminSelectedItem = null;
</script>