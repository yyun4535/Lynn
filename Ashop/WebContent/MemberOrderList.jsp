<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!doctype html>
<html>
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
<link rel="stylesheet" href="css/nice-select.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/price_rangs.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
</head>

<body class="bg-white">
	<%
		String id = (String) session.getAttribute("id");
	%>
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
								<!-- notic 리스트로 가야하는데 -->
							</ul>


							<!-- 만약  admin 일때 -->
							<%
								if (id != null) {
							%>
							<%
								if (id.equals("admin")) {
							%>

							<ul class="navbar-nav">
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" id="navbarDropdown_3"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> Admin </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
										<a class="dropdown-item" href="EnterN.jsp"> Notice Write </a>
										<a class="dropdown-item" href="EnterP.jsp">Enter Products
										</a> <a class="dropdown-item" href="Basket.do?command=OrderList">OrderList
										</a><a class="dropdown-item" href="Padd.do?command=AdminProductList">Product Order List
										</a>
									</div></li>
							</ul>
						</div>
						<%
							}
						%>
							<div>


							<a href="Basket.do?command=MemberList&id=<%=id%>"> <%=id%>
							</a> 님, 환영합니다<span>&nbsp;&nbsp;&nbsp;</span>
							<%
								} else {
							%>
							<a class="Login_btn" href="Madd.do?command=Login">Login</a> <span>&nbsp;&nbsp;&nbsp;</span>
							<a class="Join_btn" href="Madd.do?command=Join">Join</a>
							<%
								}
							%>
						</div>

						<script>
							function check() {
								alert("로그인 후 이용해주세요");

							}
						</script>

						<%
							if (id == null) {
						%>
						<div class="hearer_icon d-flex">
							<div class="dropdown cart">
								<a href="#" onclick="check()" class="ti-bag"
									class="dropdown-toggle"> </a>
							</div>
							<a id="search_1" href="javascript:void(0)"><i
								class="ti-search"></i></a>

						</div>

						<%
							} else {
						%>

						<a class="Login_btn" href="Logout.jsp">logout<span>/</span>
						</a> <span>&nbsp;</span>

						<div class="hearer_icon d-flex">
							<div class="dropdown cart">
								<a class="ti-bag" class="dropdown-toggle"
									href="Basket.do?command=Cartlist&id=<%=id%>"><span>&nbsp;/&nbsp;</span></a>
							</div>
							<a id="search_1" href="javascript:void(0)" class="ti-search"></a>
						</div>



						<%
							}
						%>

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
							<p>Home / About</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================ confirmation part start =================-->
	<section class="confirmation_part section_padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="confirmation_tittle">
						<h4><span>Thank you. Your order has been received.</span></h4>
					</div>
				</div>
				<div class="col-lg-6 col-lx-4">
					<div class="single_confirmation_details">
						<h4>My Order Info</h4>
						<ul>
							<c:forEach var="MOList" items="${MOList}">
								<li>
									<p>order number</p> <span>: ${MOList.buynum}</span>
								</li>
								<%
									Date now = new Date();
										SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd/ E요일 a hh:mm");
										String today = sf.format(now);
								%>
								<li>
									<p>Date</p> <span>:<%=today%>
								</span>
								</li>
								<li>
									<p>ID</p> <span>: ${MOList.id}</span>
								</li>
								<li>
									<p>Name</p> <span>: ${MOList.name}</span>
								</li>
								<li>
									<p>Phone</p> <span>: ${MOList.phone}</span>
								</li>
								<li>
									<p>Email</p> <span>: ${MOList.email}</span>
								</li>
								<li>
									<p>OrderNote</p> <span>: ${MOList.ordernote}</span>
								</li>

								<div class="col-lg-6 col-lx-4">
									<div class="single_confirmation_details">
										<h4>Billing Address</h4>

										<p>Address</p>
										<span>: ${MOList.address }</span>
									</div>
								</div>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="order_details_iner">
						<h3>My Order Details</h3>
						<table class="table table-borderless">
							<thead>
								<tr>
									<th scope="col" colspan="2">Product</th>
									<th scope="col">Size</th>
									<th scope="col">Color</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<c:forEach var="MOList" items="${MOList}">
								<tbody>
									<tr>
										<th colspan="2"><span>${MOList.title}</span></th>
										<th><span>${MOList.ssize}</span></th>
										<th><span>${MOList.color}</span></th>
										<th><span>${MOList.price}</span></th>
										<th><span>x${MOList.pcnt}</span></th>
										<c:set var="sum" value="${MOList.pcnt * MOList.price }" />
										<th><span><c:out value="${sum }"></c:out></span></th>
									</tr>
									<c:set var="totalsum" value="${totalsum + sum }" />
							</c:forEach>
							<tr>
								<th colspan="3">shipping</th>
								<th><span>: free</span></th>
							</tr>
							<tr>
								<th colspan="3">Total</th>
								<th>><c:out value="${totalsum }"></c:out></th>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ confirmation part end =================-->

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
	<!-- jquery -->
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
	<script src="js/stellar.js"></script>
	<script src="js/price_rangs.js"></script>
	<!-- custom js -->
	<script src="js/custom.js"></script>
</body>

</html>
