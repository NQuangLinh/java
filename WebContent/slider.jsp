	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
	<!-- Slider -->
	<section class="section-slide">
		<div class="wrap-slick1">
			<div class="slick1">
					<c:forEach items="${listProductAdvinSlider }" var="productAdv">
				<div class="item-slick1" style="background-image: url(<c:out value="${productAdv.fileNameAdv }"></c:out>);">
					<div class="container h-full">
						<div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
							<div class="layer-slick1 animated visible-false" data-appear="fadeInDown" data-delay="0">
								<span class="ltext-101 cl2 respon2">
									${productAdv.titleAdv }
								</span>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="fadeInUp" data-delay="800">
								<h2 class="ltext-201 cl2 p-t-19 p-b-43 respon1">
									${productAdv.describeAdv }
								</h2>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="zoomIn" data-delay="1600">
								<a href="ProductCustomer" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
									Shop Now
								</a>
							</div>
						</div>
					</div>
				</div>
</c:forEach>

			</div>
		</div>
	</section>