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

    <link rel="stylesheet" href="assets/css/styles.css"/>
    <script>
        function pullImageAjaxCall() {
            alert($('#pullImage').val().length);
            if ($('#pullImage').val().length == 0) {
                $('#alert-danger-message').text("Pleas input docker image name !!!!");
                $('.alert-danger').fadeIn(1000, function () {
                    $(this).delay(5000).fadeOut(1000);
                });
            } else {
                var endpoint = "ajax/pullimage?imagename=" + $('#pullImage').val();
                $.ajax({
                    url: endpoint,
                    type: "POST",
                    success: function (msg) {
                        $('#alert-success-message').text(msg);
                        $('.alert-success').fadeIn(1000, function () {
                            $(this).delay(5000).fadeOut(1000);
                        });
                    },
                    error: function (msg) {
                        alert('error');
                    }
                });
            }

        }
    </script>
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
</div>
<body>
<!-- Header -->
<div id="header">

    <div class="top">

        <!-- Logo -->
        <div id="logo">
            <span class="image avatar48"><img src="images/avatar.jpg" alt=""/></span>

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
                <li><a href="#top" id="top-link" class="skel-layers-ignoreHref">
                    <span class="icon fa-home">Pull
                    Docker Image</span>
                </a>
                </li>
                <li><a href="#dockerImage1" id="dockerImage1-link" class="skel-layers-ignoreHref"><span
                        class="icon fa-th">chiwa/apache-php</span></a></li>
                <li><a href="#dockerImage2" id="dockerImage1-link" class="skel-layers-ignoreHref"><span
                        class="icon fa-th">Stuff</span></a></li>
                <li><a href="#dockerImage3" id="dockerImage1-link" class="skel-layers-ignoreHref"><span
                        class="icon fa-th">Goods</span></a></li>
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
            <header>
                <h2 class="alt"><strong>Pull Docker Image here!!</strong></h2>

                <p>Insert Docker Image name<br/></p>

                <form method="post" action="">
                    <input type="text" id="pullImage" name="pullImage" placeholder="Ex. devops/apache-php"/>
                </form>
            </header>

            <footer>
                <a href="#" class="button scrolly" onclick="pullImageAjaxCall();">Pull</a>
            </footer>

        </div>
    </section>

    <!-- Docker image detail -->
    <section id="dockerImage1" class="two">
        <div class="container">

            <header>
                <div align="right">
                    <a href="" title="remove image">
                        <span class="icon fa-times-circle-o"></span>
                    </a>
                </div>
                <h2>chiwa/apache-php</h2>
            </header>

            <div class="row">
                <div style="float: left;">
                    <article class="item">
                        <a href="" title="remove container">
                        <div class="fit" style="background-color:#58ff8e;padding-right: 10px" align="right">
                            <span class="icon fa-times-circle-o"></span>
                        </div>
                            </a>
                        <header style="padding-left: 10px; padding-right: 10px">
                            <table style="text-align: left;">
                                <tr>
                                    <td>Id:</td>
                                    <td>z0x2c5v8</td>
                                </tr>
                                <tr>
                                    <td>Name:</td>
                                    <td>container 1</td>
                                </tr>
                                <tr>
                                    <td>Configuration:</td>
                                    <td>-d -i -p 1234:80</td>
                                </tr>
                            </table>
                            <button>stop</button>
                        </header>
                    </article>
                </div>
                <div style="float: left;">
                    <article class="item">
                        <a href="" title="remove container">
                        <div class="fit" style="background-color:#ff919f;padding-right: 10px" align="right">
                            <span class="icon fa-times-circle-o"></span>
                        </div>
                            </a>
                        <header style="padding-left: 10px; padding-right: 10px">
                            <table style="text-align: left;">
                                <tr>
                                    <td>Id:</td>
                                    <td>8a6b4c</td>
                                </tr>
                                <tr>
                                    <td>Name:</td>
                                    <td>container 2</td>
                                </tr>
                                <tr>
                                    <td>Configuration:</td>
                                    <td>-d -i -p 8889:80</td>
                                </tr>
                            </table>
                            <button>start</button>
                        </header>
                    </article>
                </div>
                <div style="float: left; ">
                    <article class="item">
                        <a href="" title="remove container">
                        <div class="fit" style="background-color:#58ff8e; padding-right: 10px" align="right">
                            <span class="icon fa-times-circle-o"></span>
                        </div>
                            </a>
                        <header style="padding-left: 10px; padding-right: 10px">
                            <table style="text-align: left;">
                                <tr>
                                    <td>Id:</td>
                                    <td>1a2b3c</td>
                                </tr>
                                <tr>
                                    <td>Name:</td>
                                    <td>container 3</td>
                                </tr>
                                <tr>
                                    <td>Configuration:</td>
                                    <td>-d -i -p 9999:80</td>
                                </tr>
                            </table>
                            <button>stop</button>
                        </header>
                    </article>
                </div>
                <div style="float: left;">
                    <article class="item">
                        <a href="">
                            <div class="fit" style="background-color:#8cd8ff; padding-left: 20px; padding-right: 20px">
                                <span class="icon fa-plus-circle"> Create new container</span>
                            </div>
                        </a>
                    </article>
                </div>
            </div>
        </div>
    </section>

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

</body>
</html>