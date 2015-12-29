<!DOCTYPE HTML>
<!--
	Prologue by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Docker My Admin</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="assets/js/jquery.min.js"></script>
    <!--[if lte IE 8]>
    <script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="assets/css/main.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ie8.css"/><![endif]-->
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ie9.css"/><![endif]-->
</head>
<!--alert msg-->
<div id="alert-message">
    <div class="alert alert-warning" style="display: none;">
        <span id="alert-warning-message">alert msg will pop up here.. in case of warning</span>
    </div>
    <div class="alert alert-success" style="display: none;">
        <span id="alert-success-message">alert msg will pop up here.. in case of success</span>
    </div>
    <div class="alert alert-info" style="display: none;">
        <span id="alert-info-message">alert msg will pop up here.. in case of info</span>
    </div>
    <div class="alert alert-danger" style="display: none;">
        <span id="alert-danger-message">alert msg will pop up here.. in case of danger</span>
    </div>
    <div class="alert alert-danger2" style="display: none;">
        <span id="alert-danger-message2">alert msg will pop up here.. in case of danger</span>
    </div>
</div>
<body>
<!-- Header -->
<div id="header">

    <div class="top">

        <!-- Logo -->
        <div id="logo">
            <span class="image avatar48"><img src="images/docker-icon.png" alt=""/></span>

            <h1 id="title">Docker My Admin</h1>

            <p>version 1.0</p>
        </div>

        <!-- Nav -->
        <nav id="nav">
            <!--

                Prologue's nav expects links in one of two formats:

                1. Hash link (scrolls to a different section within the page)

                   <li><a href="#foobar" id="foobar-link" class="icon fa-whatever-icon-you-want
                   skel-layers-ignoreHref"><span class="label">Foobar</span></a></li>

                2. Standard link (sends the user to another page/site)

                   <li><a href="http://foobar.tld" id="foobar-link" class="icon fa-whatever-icon-you-want"><span
                   class="label">Foobar</span></a></li>

            -->
            <ul>
                <li><a href="#top" id="top-link" onclick="gotoHomePage();" class="skel-layers-ignoreHref">
                    <span class="icon fa-home">Pull Docker Image</span>
                </a>
                </li>

                <li><a href="#top" id="utility" onclick="showUtilityDiv();"
                       class="skel-layers-ignoreHref"><span
                        class="icon fa-cogs">Utility Commands</span></a></li>

            <#list imageModelList as image>
                <li><a href="#${image.imageId}" id="${image.imageId}-link"
                       class="skel-layers-ignoreHref"><span
                        class="icon fa-th">${image.repository}:${image.tag?trim}</span></a></li>
            </#list>
            </ul>
        </nav>

    </div>

    <div class="bottom">

        <!-- Social Icons -->
        <ul class="icons">
            <li><a href="https://github.com/tanayuth/dockermyadmin" class="icon fa-github"><span
                    class="label">Github</span></a></li>
        </ul>

    </div>

</div>

<!-- Main -->
<div id="main">
    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">
            <div id="pullImageDiv">
                <header>
                    <h2 class="alt"><strong>Pull Docker Image here!!</strong></h2>

                    <p>Insert Docker Image name, please wait.......<br/></p>

                    <form method="post" id="pullImageForm">
                        <input type="text" id="pullImage" name="pullImage" placeholder="Ex. devops/apache-php"/>
                    </form>
                </header>

                <footer>
                    <a href="#" id="pullButton" class="button scrolly" onclick="pullImageAjaxCall();">Pull</a>
                </footer>
                <div id="loading" style="display: none"><img src="assets/css/images/waiting.gif">

                    <p>Polling docker image<br/></p>
                </div>
            </div>

            <div id="utilityCommandDiv" style="display: none">
                <header>
                    <h2 class="alt"><strong>Utility Commands</strong></h2>
                    <form method="post" id="utilityCommandForm">
                        <select id="utilityCommandDropdown">
                            <option value="0">Please select command</option>
                            <option value="1">Remove all unused containers (All stoped containers must be delete.)</option>
                            <option value="2">Remove none-ascii chars</option>
                            <option value="3">Remove all untaged images</option>
                        </select>
                    </form>
                </header>

                <footer>
                    <a href="#" id="utilityCommandButton" class="button scrolly" onclick="utilityCommandAjaxCall();">Execute Command</a>
                </footer>
                <div id="utilityLoading" style="display: none"><img src="assets/css/images/waiting.gif">
                    <p>Executing comand.<br/></p>
                </div>
            </div>

            <div id="createContainerDiv" style="display: none">
                <header>
                    <h2 class="alt"><strong>
                        <div id="containerDiv">Container Name</div>
                    </strong></h2>
                    <p>docker run -d (exclude image name/id)<br/></p>

                    <form method="post" id="createContainerForm" onsubmit="return false">
                        <input type="text" id="createContainer" name="createContainer"
                               placeholder="Ex. -p 9001:8080 -p 50000:50000 -v /var/application/:/var/application_home"/>
                    </form>
                </header>

                <footer>
                    <a href="#" id="createContainerButton" class="button scrolly" onclick="createContainerAjaxCall();">Create
                        Container</a>
                </footer>
                <div id="loading2" style="display: none">
                    <img src="assets/css/images/waiting.gif">

                    <p>Creating container, please wait.......<br/></p>
                </div>
            </div>

        </div>
    </section>

<#list imageModelList as image>
    <section id="${image.imageId}" class="two">
        <div class="container">

            <header>
                <div style="float: right;">
                    <a href="" title="remove image" onclick="deleteImage('${image.repository}:${image.tag?trim}')">
                        <span class="icon fa-times-circle-o"></span>
                    </a>
                </div>
                <h2>${image.repository}:${image.tag?trim}</h2>
            </header>

            <div class="row">
                <#if imageMap[image.imageId]??>
                    <#list imageMap[image.imageId] as container>
                        <div style="float: left; width: 20%">
                            <article class="item">
                                <a href="" title="remove container">
                                    <#if container.status?contains("Up")>
                                        <div class="fit" style="background-color:#58ff8e;padding-right: 10px"
                                             align="right">
                                            <span class="icon fa-times-circle-o"
                                                  onclick="deleteContainer('${container.id}')"></span>
                                        </div>
                                    <#else >
                                        <div class="fit" style="background-color:#ff919f;padding-right: 10px"
                                             align="right">
                                            <span class="icon fa-times-circle-o"
                                                  onclick="deleteContainer('${container.id}')"></span>
                                        </div>
                                    </#if>
                                </a>
                                <header style="padding-left: 10px; padding-right: 10px">
                                    <table style="text-align: left;">
                                        <tr>
                                            <td>Id:</td>
                                            <td> ${container.id}</td>
                                        </tr>
                                        <tr>
                                            <td>Name:</td>
                                            <td> ${container.name!"Not Found"}</td>
                                        </tr>
                                    </table>
                                    <#if container.status?contains("Up")>
                                        <button onclick="restartContainer('${container.id}')">restart</button>
                                        <button onclick="stopContainer('${container.id}')">stop</button>
                                    <#else>
                                        <button onclick="startContainer('${container.id}')">start</button>
                                    </#if>
                                </header>
                            </article>
                        </div>
                    </#list>
                </#if>
                <div style="float: left;">
                    <article class="item">
                        <a href="#top"
                           onclick="return createContainer('${image.imageId}', '${image.repository}:${image.tag?trim}');">
                            <div class="fit" style="background-color:#8cd8ff; padding-left: 20px; padding-right: 20px">
                                <span class="icon fa-plus-circle"> Create new container</span>
                            </div>
                        </a>
                    </article>
                </div>
            </div>
        </div>
    </section>
</#list>

    <div class="8u 12u$(mobile)" style="background-color:#ffffff; display: none">
        <form method="POST">
            <table>
                <tr>
                    <td>container name:</td>
                    <td><input type="text" value="container name"/></td>
                </tr>
                <tr>
                    <td>container configuration:</td>
                    <td><input type="text" value="configuration command"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button value="save"></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

</div>

<!-- Footer -->
<div id="footer">

    <!-- Copyright -->
    <ul class="copyright">
        <li>&copy; DockerMyAdmin. All rights reserved.</li>
        <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.scrollzer.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]>
<script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

<script>

    $('#pullImageForm').submit(function () {
        pullImageAjaxCall();
        return false;
    });

    function deleteContainer(containerId) {
        var endpoint = "ajax/container/delete/" + containerId;
        $.ajax({
            url: endpoint,
            type: "POST",
            success: function (msg) {
                $('#alert-success-message').text("Container id: " + msg.id + " deledted.");
                $('.alert-success').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500, function () {
                        window.location.reload(true);
                    });
                });
            },
            error: function (msg) {
                $('#alert-danger-message').text(msg.responseText);
                $('.alert-danger').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500);
                });
            }
        });
    }

    function utilityCommandAjaxCall() {
        var id = $('#utilityCommandDropdown').val();
        if (id == 0) {
            $('#alert-warning-message').text("Please select command !!!!");
            $('.alert-warning').fadeIn(500, function () {
                $(this).delay(3000).fadeOut(500);
            });
        } else {
            $('#utilityCommandButton').hide();
            $('#utilityLoading').show();
            var endpoint = "ajax/executecommand?id=" + id;
            $.ajax({
                url: endpoint,
                type: "POST",
                success: function (msg) {
                    $('#utilityCommandButton').show();
                    $('#utilityLoading').hide();
                    $('#alert-success-message').text(msg);
                    $('.alert-success').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500);
                    });

                },
                error: function (jqXHR) {
                    var message = (jqXHR.responseText != null && jqXHR.responseText != "") ? jqXHR.responseText :
                            jqXHR.statusText;
                    $('#utilityCommandButton').show();
                    $('#utilityLoading').hide();
                    $('#alert-danger-message').text(message);
                    $('.alert-danger').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500);
                    });
                }
            });
        }
    }

    function pullImageAjaxCall() {
        if ($('#pullImage').val().length == 0) {
            $('#alert-warning-message').text("Pleas input docker image name !!!!");
            $('.alert-warning').fadeIn(500, function () {
                $(this).delay(3000).fadeOut(500);
            });
        } else {
            $("#loading").show();
            $("#pullButton").hide();
            var endpoint = "ajax/pullimage?imagename=" + $('#pullImage').val();
            $.ajax({
                url: endpoint,
                type: "POST",
                success: function (msg) {
                    $("#pullButton").show();
                    $("#loading").hide();
                    $('#alert-success-message').text(msg);
                    $('.alert-success').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500);
                        location.reload();
                    });

                },
                error: function (jqXHR) {
                    var message = (jqXHR.responseText != null && jqXHR.responseText != "") ? jqXHR.responseText :
                            jqXHR.statusText;
                    $("#pullButton").show();
                    $("#loading").hide();
                    $('#alert-danger-message').text(message);
                    $('.alert-danger').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500);
                    });
                }
            });
        }

    }
    function stopContainer(containerId) {
        var endpoint = "ajax/container/stop?containerid=" + containerId;
        $.ajax({
            url: endpoint,
            type: "POST",
            success: function (msg) {
                $('#alert-success-message').text(msg);
                $('.alert-success').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500, function () {
                        window.location.reload(true);
                    });
                });
            },
            error: function (msg) {
                $('#alert-danger-message').text(msg);
                $('.alert-danger').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500);
                });
            }
        });
    }

    function restartContainer(containerId) {
        var endpoint = "ajax/container/restart/" + containerId;
        $.ajax({
            url: endpoint,
            type: "POST",
            success: function (msg) {
                $('#alert-success-message').text("Container id: " + msg.id + " restarted.");
                $('.alert-success').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500, function () {
                        window.location.reload(true);
                    });
                });
            },
            error: function (msg) {
                $('#alert-danger-message').text(msg.responseText);
                $('.alert-danger').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500);
                });
            }

        });
    }

    function startContainer(containerId) {
        var endpoint = "ajax/container/start/" + containerId;
        $.ajax({
            url: endpoint,
            type: "POST",
            success: function (msg) {
                $('#alert-success-message').text("Container id: " + msg.id + " started.");
                $('.alert-success').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500, function () {
                        window.location.reload(true);
                    });
                });
            },
            error: function (msg) {
                $('#alert-danger-message').text(msg.responseText);
                $('.alert-danger').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500);
                });
            }

        });
    }

    function deleteImage(imageName) {
        var endpoint = "ajax/image/delete?imagename=" + imageName;
        $.ajax({
            url: endpoint,
            type: "DELETE",
            success: function () {
            },
            error: function () {
                $('#alert-danger-message').text("Error: cannot delete image" + imageName);
                $('.alert-danger').fadeIn(500, function () {
                    $(this).delay(3000).fadeOut(500);
                });
            }
        });
    }

//    var autoRefresh = setTimeout(function() {location.reload()}, 60000);

    function createContainer(imageId, containerName) {
        globalImageId = imageId;
        $("#containerDiv").text("Create Docker Container : " + containerName);
        $("#createContainerDiv").show();
        $("#pullImageDiv").hide();
        $("#utilityCommandDiv").hide();
    }

    function gotoHomePage() {
        $("#createContainerDiv").hide();
        $("#utilityCommandDiv").hide();
        $("#pullImageDiv").show();
    }

    function showUtilityDiv() {
        $("#utilityCommandDiv").show();
        $("#createContainerDiv").hide();
        $("#pullImageDiv").hide();
    }

    function createContainerAjaxCall() {
        if ($('#createContainer').val().length == 0) {
            $('#alert-warning-message').text("Pleas input parameter !!!!");
            $('.alert-warning').fadeIn(500, function () {
                $(this).delay(3000).fadeOut(500);
            });
        } else {
            var endpoint = "ajax/createcontainer?imageid=" + globalImageId + "&parameter=" + $('#createContainer').val();
            $("#loading2").show();
            $("#createContainerButton").hide();
            $.ajax({
                url: endpoint,
                type: "POST",
                success: function (msg) {
                    $("#loading2").hide();
                    $("#createContainerButton").show();
                    $('#alert-success-message').text(msg);
                    $('.alert-success').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500, function () {
                            location.reload();
                        });
                    });
                },
                error: function (jqXHR) {
                    $("#loading2").hide();
                    $("#createContainerButton").show();
                    var message = (jqXHR.responseText != null && jqXHR.responseText != "") ? jqXHR.responseText : jqXHR.statusText;
                    $('#alert-danger-message').text(message);
                    $('.alert-danger').fadeIn(500, function () {
                        $(this).delay(3000).fadeOut(500);
                    });
                }
            });
        }
    }
</script>
</body>
</html>