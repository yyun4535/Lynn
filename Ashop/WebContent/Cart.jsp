<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zxx">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>장바구니</title>
<link rel="icon" href="img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- nice select CSS -->
<link rel="stylesheet" href="css/nice-select.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
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
											<a class="dropdown-item" href="EnterN.jsp"> Notice Write
											</a> <a class="dropdown-item" href="EnterP.jsp">Enter
												Products </a> <a class="dropdown-item"
												href="Basket.do?command=OrderList">OrderList </a> <a
												class="dropdown-item"
												href="Padd.do?command=AdminProductList">Product Order
												List </a>
										</div></li>
								</ul>
							</div>
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

	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>
								Home/Shop/<%=id%>님의 CartList
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================cart Area =================-->

	<section class="cart_area section_padding">
		<div class="container">
			<div class="cart_inner">
				<div class="table-responsive">
							<div class="checkout_btn_inner float-left">
								<a href="javascript:history.back();" class="btn_1">Back</a>
							</div>
					<form class="" action="Basket.do?command=CheckBoxChoice&id=<%=id%>"
						method="post">
						<div class="checkout_btn_inner float-right">
							<input type="submit" value="proceed to Checkout" class="btn_1">
						</div>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Choice</th>
									<th scope="col">Product</th>
									<th scope="col">Title</th>
									<th scope="col">Price</th>
									<th scope="col">Color</th>
									<th scope="col">Size</th>
									<th scope="col">Quantity</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<c:forEach var="List" items="${List}">
								<tbody>
									<tr>
										<td><input type="checkbox" name="choice"
											value="${List.num }"></td>
										</form>
										<td>
											<div class="media">
												<div class="d-flex">
													<img src="./img/${List.pictureurl }" />
												</div>
											</div>
										</td>
										<td>
											<h5>${List.title }</h5>
										</td>
										<td>
											<h5>${List.price }</h5>
										</td>
										<td>
											<h5>${List.color }</h5>
										</td>
										<td>
											<h5>${List.ssize }</h5>
										</td>
										<td>
											<form class=""
												action="Basket.do?command=CartUpdate&id=<%=id%>"
												method="post">
												<div class="card_area">
													<div class="product_count d-inline-block">
														<input type="hidden" name="num" value="${List.num }">
														<input type="hidden" name="pnum" value="${List.pnum }">
														<input class="" type="text" name="changecnt"
															value="${List.pcnt }" min="0" max="10"> <input
															type="submit" value="변경">
													</div>
												</div>
											</form> <c:set var="sum" value="${List.pcnt * List.price }"></c:set>
										</td>
										<td>
											<h5>
												<c:out value="${sum }"></c:out>
											</h5>

										</td>
									</tr>

									<tr>
										<td></td>
										<c:set var="totalsum" value="${totalsum + sum }" />
							</c:forEach>

							<tr>
								<td></td>
								<td></td>
								<td>
									<h3>SubTotal</h3>
								<td><h3>
										<c:out value="${totalsum}"></c:out>
										<input type="hidden" value="${totalsum }" name="total">
									</h3></td>
								<td>
							</tr>

							<tr class="shipping_area">

								<td>
									<h5>Shipping</h5>
								</td>
								<td>
									<div class="shipping_box">
										<ul class="list">
											<li>Free Shipping <input type="radio"
												aria-label="Radio button for following text input">
											</li>
										</ul>
									</div>
								</td>
							</tr>
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</section>

	<!--================End cart Area =================-->

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
	<script src="js/price_rangs.js"></script>
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