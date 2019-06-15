 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">

					<div class="left-top-bar">
						The email being used is - ${accountname }
					</div>
					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							Help & FAQs
						</a>

						<a href="#" class="flex-c-m trans-04 p-lr-25">
							VN
						</a>
						
						<a href="AdminController?page=myAccount" class="flex-c-m trans-04 p-lr-25">
							My Account
						</a>
						<a href="SignUp.jsp" class="flex-c-m trans-04 p-lr-25">
							Sign Up
						</a>



						<a href="LoginController" class="flex-c-m trans-04 p-lr-25">
							Logout
						</a>
					</div>
				</div>
			</div>