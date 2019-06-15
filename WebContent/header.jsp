 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">
				<c:if test="${idCustomer == null}">
					<div class="left-top-bar">
						Free shipping for standard order over $100
					</div>
</c:if>
				<c:if test="${idCustomer != null}">
					<div class="left-top-bar">
						The email being used is - ${accountname }
					</div>
</c:if>
					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							Help & FAQs
						</a>

						<a href="#" class="flex-c-m trans-04 p-lr-25">
							VN
						</a>
<c:if test="${idCustomer == null}">
						<a href="SignUp.jsp" class="flex-c-m trans-04 p-lr-25">
							Sign Up
						</a>

						<a href="Login.jsp" class="flex-c-m trans-04 p-lr-25">
							Login
						</a>
</c:if>

<c:if test="${idCustomer != null}">
						<a href="MyAccount" class="flex-c-m trans-04 p-lr-25">
							My Account
						</a>

						<a href="LoginController" class="flex-c-m trans-04 p-lr-25">
							Logout
						</a>
</c:if>
					</div>
				</div>
			</div>