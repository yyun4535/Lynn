<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zxx">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>winter</title>
<link rel="icon" href="img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<link rel="stylesheet" href="css/nice-select.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {

		$("#join").click(function() {
			var id = $("#id").val();
			var pwd = $("#pwd").val();
			var emailid = $("#emailid").val();
			var address = $("#address").val();

			if (id == "") {
				alert("Please, enter a id.");
			} else if (pwd == "") {
				alert("Please, enter a pwd.");
			} else if (emailid == "") {
				alert("Please, enter a email.");
			} else if (address == "") {
				alert("Please, enter a address.");

			} else {
				alert("완료");
				document.Join.action = "${path}Madd.do?command=Join";
				document.Join.submit();
				alert("완료1");
			}
		});
	});
</script>
<body>

	<script>
		function letchange() {

			var frm = document.forms["Join"];

			var a = frm["id"].value;

			window.name = "parentForm";

			window.open("./Madd.do?command=Idchk&id=" + a, "chkForm",
					"width=300, height=300, resizable = no, scrollbars = no");

		}
	</script>



	<!--::header part start::-->
	<header class="main_menu home_menu">
		<div class="container-fluid">
			<div class="row align-items-center justify-content-center">
				<div class="col-lg-11">
					<nav class="navbar navbar-expand-lg navbar-light">
						<a class="navbar-brand" href="Madd.do?command=Index"> <img
							src="img/logo.png" alt="logo">
						</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="menu_icon"><i class="fas fa-bars"></i></span>
						</button>

						<div class="collapse navbar-collapse main-menu-item"
							id="navbarSupportedContent">

							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Madd.do?command=Index">Home</a></li>
							</ul>


							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Padd.do?command=Outer&sort=Outer">Outer</a></li>
							</ul>

							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Padd.do?command=Top&sort=Top">Top</a></li>
							</ul>

							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Padd.do?command=Bottom&sort=Bottom">Bottom</a></li>
							</ul>

							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Padd.do?command=Skirt&sort=Skirt">Skirt</a></li>
							</ul>

							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="Notice.do?command=Noticelist">Notice</a></li>
								<!-- notice 리스트로 가야하는데 -->
							</ul>
						</div>
						<script>
							function check() {
								alert("회원가입/로그인후 이용해주세요");

							}
						</script>
						<div class="hearer_icon d-flex">
							<div class="dropdown cart">
								<a class="ti-bag" class="dropdown-toggle" href="#"
									onclick="check()" id="navbarDropdown3"> </a>
								<!-- <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <div class="Detail">
    
                                    </div>
                                </div> -->
							</div>
							<a id="search_1" href="javascript:void(0)"><i
								class="ti-search"></i></a>
						</div>
					</nav>
				</div>
			</div>
		</div>
		<div class="search_input" id="search_input_box">
			<div class="container ">
				<form class="d-flex justify-content-between search-inner">
					<input type="text" class="form-control" id="search_input"
						placeholder="Search Here">
					<button type="submit" class="btn"></button>
					<span class="ti-close" id="close_search" title="Close Search"></span>
				</form>
			</div>
		</div>
	</header>
	<!-- Header part end-->

	<!--================Home Banner Area =================-->
	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>Home / Join</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================Checkout Area =================-->
	<section class="checkout_area section_padding">
		<div class="container">
			<div class="billing_details">
				<div class="row">
					<div class="col-lg-8">
						<h3>Join information</h3>
						<form name=Join class="row contact_form"
							action="Madd.do?command=Join" method="post"
							novalidate="novalidate">
							<div class="col-md-6 form-group p_star">
								<input type="text" id="id" class="form-control" name="id"
									maxlength=10 /> <span class="placeholder"
									data-placeholder="ID : 10글자 이내"></span> <input type="hidden"
									name="idDuplication" value=idUnCheck>
								<button class=" col-md-4 form-group	p_star" type="button"
									id="mybutton" onclick="letchange()">ID중복확인</button>
							</div>

							<div class="col-md-6 form-group p_star">
								<input type="text" class="form-control" name="pwd" id="pwd"
									maxlength=10> <span class="placeholder"
									data-placeholder="Password : 10글자 이내"></span>
							</div>


							<div class="col-md-6 form-group p_star">
								<input type="text" class="form-control" name="phone" /> <span
									class="placeholder" data-placeholder="Phone"></span>
							</div>


							<div class="col-md-7 form-group p_star">
								<input type="text" class="form-control" name="emailid" /> <span
									class="placeholder" data-placeholder="Email"></span>
							</div>
							<div>@</div>
							<div class="col-md-3 form-group p_star">
								<select name="emailaddress">
									<option>naver.com</option>
									<option>daum.net</option>
									<option>google.com</option>
									<option>hotmail.com</option>
									<option>yahoo.com</option>
									<option>winter.com</option>
								</select>
							</div>


							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control" name="address"
									id="address" /> <span class="placeholder"
									data-placeholder="Address"></span>
							</div>
							<div class="col-md-6 form-group p_star">
								<input type="button" value="Join" id="join">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Checkout Area =================-->

	<!-- subscribe_area part start-->
	<section class="instagram_photo">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="instagram_photo_iner">
						<div class="single_instgram_photo">
							<img src="img/instagram/inst_1.png" alt=""> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
						<div class="single_instgram_photo">
							<img src="img/instagram/inst_2.png" alt=""> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
						<div class="single_instgram_photo">
							<img src="img/instagram/inst_3.png" alt=""> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
						<div class="single_instgram_photo">
							<img src="img/instagram/inst_4.png" alt=""> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
						<div class="single_instgram_photo">
							<img src="img/instagram/inst_5.png" alt=""> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--::subscribe_area part end::-->

	<!--::footer_part start::-->
	<footer class="footer_part">
		<div class="container">
			<div class="row justify-content-between">
				<div class="col-sm-6 col-lg-2">
					<div class="single_footer_part">
						<h4>Category</h4>
						<ul class="list-unstyled">
							<li><a href="Padd.do?command=Outer">Outer</a></li>
							<li><a href="Padd.do?command=Top">Top</a></li>
							<li><a href="Padd.do?command=Bottom">Bottom</a></li>
							<li><a href="Padd.do?command=Skirt">Skirt</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6 col-lg-2">
					<div class="single_footer_part">
						<h4>Company</h4>
						<ul class="list-unstyled">
							<li><a href="">About</a></li>
							<li><a href="">News</a></li>
							<li><a href="">FAQ</a></li>
							<li><a href="">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6 col-lg-3">
					<div class="single_footer_part">
						<h4>Address</h4>
						<ul class="list-unstyled">
							<li><a href="#">200, Green block, NewYork</a></li>
							<li><a href="#">+10 456 267 1678</a></li>
							<li><span>contact89@winter.com</span></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6 col-lg-4">
					<div class="single_footer_part">
						<h4>Newsletter</h4>
						<div id="mc_embed_signup">
							<form target="_blank"
								action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
								method="get" class="subscribe_form relative mail_part">
								<input type="email" name="email" id="newsletter-form-email"
									placeholder="Email Address" class="placeholder hide-on-focus"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = ' Email Address '">
								<button type="submit" name="submit" id="newsletter-submit"
									class="email_icon newsletter-submit button-contactForm">subscribe</button>
								<div class="mt-10 info"></div>
							</form>
						</div>
						<div class="social_icon">
							<a href="#"><i class="ti-facebook"></i></a> <a href="#"><i
								class="ti-twitter-alt"></i></a> <a href="#"><i
								class="ti-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="copyright_text">
						<P>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="ti-heart" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</P>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!--::footer_part end::-->

	<!-- jquery plugins here-->
	<script src="js/jquery-1.12.1.min.js"></script>
	<!-- popper js -->
	<script src="js/popper.min.js"></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.min.js"></script>
	<!-- easing js -->
	<script src="js/jquery.magnific-popup.js"></script>
	<!-- swiper js -->
	<script src="js/swiper.min.js"></script>
	<!-- swiper js -->
	<script src="js/mixitup.min.js"></script>
	<!-- particles js -->
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<!-- slick js -->
	<script src="js/slick.min.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/contact.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/mail-script.js"></script>
	<!-- custom js -->
	<script src="js/custom.js"></script>
</body>

</html>