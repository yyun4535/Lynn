<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zxx">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Winter</title>
<link rel="icon" href="img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/lightslider.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {

		$("#qna").click(function() {

			var pwd = $("#pwd").val();
			var title = $("#title").val();
			var content = $("#content").val();

			if (title == "") {
				alert("Please, enter a title.");
			} else if (pwd == "") {
				alert("Please, enter a password.");
			} else if (content == "") {
				alert("Please, enter a content.");
			} else {
				alert("완료");
				document.form1.action = "${path}Qna.do?command=Qnaadd";
				document.form1.submit();
				alert("완료1");
			}
		});

		$("#checksize").click(function() {

			var height = $("#height").val();
			var weight = $("#weight").val();

			if (height == "") {
				alert("Please, enter a height.");
			} else if (weight == "") {
				alert("Please, enter a weight.");
			} else {
				alert("완료");
				document.form2.action = "${path}Review.do?command=chksize";
				document.form2.submit();
				alert("완료1");
			}
		});
	});
</script>
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
							</ul>


							<!-- 만약 admin 일때 -->
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
										</a> <a class="dropdown-item" href=Basket.do?command=OrderList">OrderList
										</a><a class="dropdown-item"
											href="Padd.do?command=AdminProductList">Product Order
											List </a>
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
	<c:forEach var="List" items="${List}">
		<c:forEach var="sumlist" items="${sumlist}">
			<section class="breadcrumb breadcrumb_bg">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-12">
							<div class="breadcrumb_iner">
								<div class="breadcrumb_iner_item">
									<p>Home/Shop/Detail</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- breadcrumb start-->

			<form name=detail action="Basket.do?command=Cartadd" method="post"
				novalidate="novalidate">
				<div class="product_image_area section_padding">
					<div class="container">
						<div class="row s_product_inner">
							<div class="col-lg-5">
								<div class="product_slider_img">
									<div id="vertical">
										<div data-thumb="./img/${List.pictureurl }">
											<img src="./img/${List.pictureurl }" /> <input type="hidden"
												name="pictureurl" value="${List.pictureurl }"> <input
												type="hidden" name="id" value="<%=id%>">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 offset-lg-1">
							<div class="s_product_text">
								<input type="hidden" name="pnum" value="${List.pnum }">
								<h3>${sumlist }</h3>
								<h3>${List.title }
									<input type="hidden" name="title" value="${List.title }">
								</h3>
								<!-- 상품이름 -->
								<h2>${List.price }
									<input type="hidden" name="price" value="${List.price }">
								</h2>
								<!-- 상품 가격 -->
								<ul class="list">
									<li><span>Category</span> : ${List.sort }</li>
									<li><span>Amount</span> : ${List.pcnt } <input
										type="hidden" name="pcnt" value="${List.pcnt }"></li>
									<li><span>Click</span> : ${List.click }</li>
									<li><span>Buy History</span> : ${List.history }</li>
								</ul>
								<p>${List.detail }</p>
								<div class="card_area">
									<div class="product_count d-inline-block">
										<span class="inumber-decrement"> <i class="ti-minus"></i></span>
										<input class="input-number" type="text" name="cnt" value="1"
											min="0" max="10"> <span class="number-increment">
											<i class="ti-plus"></i>
										</span>
									</div>
									<div class="card_area">
										<div class="product_count d-inline-block">
											<span>S </span><input class="input-size" type="radio"
												value="S" checked name="sml"> <span>M </span><input
												class="input-size" type="radio" value="M" name="sml">
											<span>L </span><input class="input-size" type="radio"
												value="L" name="sml">
										</div>
									</div>

									<div class="col-md-6 form-group p_star">
										<input type="text" class="form-control" name="color"
											value="${List.color }">

									</div>
									<c:set var="soldout" value="${sumlist}" />
									<c:if test="${soldout ne '★품절★'}">
										<div class="card_area">
											<input type="submit" value="Add to cart" class="btn_3">
										</div>
									</c:if>
									<div class="social_icon">
										<a href="#" class="fb"><i class="ti-facebook"></i></a> <a
											href="#" class="tw"><i class="ti-twitter-alt"></i></a> <a
											href="#" class="li"><i class="ti-linkedin"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>

			<!--================End Single Product Area =================-->

			<!--================Product Description Area =================-->
			<section class="product_description_area">
				<div class="container">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link" id="home-tab"
							data-toggle="tab" href="#home" role="tab" aria-controls="home"
							aria-selected="true">Description</a></li>
						<li class="nav-item"><a class="nav-link" id="contact-tab"
							data-toggle="tab" href="#contact" role="tab"
							aria-controls="contact" aria-selected="false">QnA</a></li>
						<li class="nav-item"><a class="nav-link active"
							id="review-tab" data-toggle="tab" href="#review" role="tab"
							aria-controls="review" aria-selected="false">Reviews</a></li>
					</ul>

					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<p>
								<img src="./img/${List.detailpic }" />
							</p>
						</div>

						<div class="tab-pane fade" id="contact" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="col-lg-6">
								<div class="review_box">
									<c:forEach var="QList" items="${QList}">
										<form name="Detail" action="Qna.do?command=password"
											method="post">
											<input type="hidden" name="qnum" value="${QList.qnum}">
											<input type="hidden" name="id" value=<%=id%>> <input
												type="hidden" name="pnum" value="${List.pnum }">

											<table class="table table-borderless">
												<thead>
													<tr>
														<th scope="col" colspan="2">ID</th>
														<th scope="col">Title</th>
														<th scope="col">Date</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<th colspan="2"><span> ${QList.id} </span></th>
														<th><span><p>
																	<input type="submit" value="${QList.title}" class="btn">
																</p></span></th>
														<th><span>${QList.qdate}</span></th>
													</tr>
												</tbody>
											</table>
										</form>
									</c:forEach>
								</div>

								<div class="col-lg-6">
									<div class="review_box">
										<h4>QnA</h4>
										<form class="row contact_form" id="form1" name="form1"
											method="post">
											<div class="col-md-12">
												<div class="form-group">
													<input type="hidden" name=id value=<%=id%> /> <input
														type="hidden" name=pnum value=${List.pnum } /> ID :
													<%=id%>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<input type="text" class="form-control" name="title"
														placeholder="Title" id="title" />
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<input type="password" class="form-control" name="pwd"
														placeholder="Password" id="pwd" />
												</div>
											</div>

											<div class="col-md-12">
												<div class="form-group">
													<textarea class="form-control" name="content" rows="4"
														placeholder="Content" id="content"></textarea>
												</div>
											</div>
											<input type="button" value="submit" id="qna">
										</form>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade show active" id="review" role="tabpanel"
							aria-labelledby="review-tab">
							<div class="row">
								<div class="col-lg-6">
									<div class="review_box">

										<h4>Review</h4>
										<form name="form2" id="form2" novalidate="novalidate"
											method="post">
											<h4>Suggest A size</h4>


											<div class="col-md-12">
												<div class="form-group">
													Size S :<span>${S }</span>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													Size M :<span>${M }</span>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													Size L :<span>${L }</span>
												</div>
											</div>

											<input type="hidden" name=pnum value=${List.pnum } />
											<div class="col-md-12 form-group p_star">
												Height : <input type="text" class="form-control"
													name="height" id="height" placeholder="Height">
											</div>
											<div class="col-md-12 form-group p_star">
												Weight : <input type="text" class="form-control"
													name="weight" id="weight" placeholder="Weight">
											</div>
											<input type="button" value="submit" id="checksize">
										</form>

										<c:forEach var="RList" items="${RList}">
											<p>${RList.title }</p>
											<div class="d-flex">
												<div class="form-group">
													<img src="./img/${RList.pictureurl }" />
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.id }</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.ssize }</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.color }</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.height }</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.weight }</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">${RList.content }</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:forEach>
	</c:forEach>
	<!--================End Product Description Area =================-->

	<!-- product_list part start-->
	<section class="product_list best_seller padding_bottom">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="section_tittle text-center">
						<h2>MD Pick! Product</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<c:forEach var="RandomList" items="${RandomList}">
					<div class="col-lg-3 col-sm-6">
						<div class="single_category_product">
							<div class="single_category_img">
								<img src="./img/${RandomList.pictureurl }" />
								<div class="category_product_text">
									<a href="Padd.do?command=Detail&pnum=${RandomList.pnum }">
										<p>MD pick!</p>
										<h5>${RandomList.title}</h5>
									</a>
									<h6>${RandomList.price}</h6>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- product_list part end-->

	<!-- free shipping here -->
	<section class="shipping_details section_padding border_top">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-sm-6">
					<div class="single_shopping_details">
						<img src="img/icon/icon_1.png" alt="">
						<h4>Free shipping</h4>
						<p>PostOffice delivery(1588-1300)</p>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="single_shopping_details">
						<img src="img/icon/icon_2.png" alt="">
						<h4>Return & Exchange</h4>
						<p>200, Green block, NewYork</p>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="single_shopping_details">
						<img src="img/icon/icon_3.png" alt="">
						<h4>Account Information</h4>
						<p>신한은행 : 110 386 053948</p>
						<p>예금주 : (주)Winter</p>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="single_shopping_details">
						<img src="img/icon/icon_4.png" alt="">
						<h4>Customer Center</h4>
						<p>041-1234-5678</p>
						<p>Mon-Fri 11:30AM-5:00PM</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- free shipping end -->

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
	<script src="js/lightslider.min.js"></script>
	<!-- swiper js -->
	<script src="js/mixitup.min.js"></script>
	<script src="js/lightslider.min.js"></script>
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