	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<!-- Cart -->
	<div class="wrap-header-cart js-panel-cart">
		<div class="s-full js-hide-cart"></div>

		<div class="header-cart flex-col-l p-l-65 p-r-25">
			<div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Your Cart
				</span>

				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>
			
			<div class="header-cart-content flex-w js-pscroll">
				<ul class="header-cart-wrapitem w-full">
				<c:forEach items="${listProductinCart }" var="product">
					<li class="header-cart-item flex-w flex-t m-b-12">
						<div onclick="location.href='DeleteProductinCart?idProductinCart=${product.idProductinCart }'" class="header-cart-item-img">
							<img  src="<c:out value="${product.fileName }"></c:out>" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="ProductDetail?idProduct=${product.idproduct }" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								${product.nameproduct }
							</a>

							<span class="header-cart-item-info">
								${product.countproduct } x $${product.price }
							</span>
						</div>
					</li>
					</c:forEach>	
				</ul>
				
				<div class="w-full">
					<div class="header-cart-total w-full p-tb-40">
						Total: $${cartTotals }
					</div>

					<div class="header-cart-buttons flex-w w-full">
						<a href="MyCart" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart
						</a>

						<a href="ProductCustomer" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
							Check Out
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>