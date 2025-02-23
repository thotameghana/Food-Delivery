<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.foodapp.model.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	<link rel="stylesheet" href="./styles/home.css?v=1">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-logo">
            <a href="#"><img src="./logo/fast-food.png" alt="Foodie Hub" class="logo"></a>
        </div>
        <div class="navbar-search">
            <input type="text" placeholder="Search restaurants..." id="search-box">
            <button class="search-btn" id="search-button">
                <i class="fas fa-search"></i>
            </button>
        </div>
        <div class="navbar-actions">
            <button class="cart-btn">
                <a href="CartServlet"><i class="fas fa-shopping-cart"></i></a>
            </button>
            <div class="profile-photo" id="profile-photo">
                <i class="fas fa-user"></i>
            </div>
             <div class="profile-dropdown" id="profile-dropdown">
                <p id="username-display"><%= ((User) session.getAttribute("user")).getUserName() %></p>
            </div>
        </div>
    </nav>
    <h1 id="welcome">Welcome
        <%= ((User) session.getAttribute("user")).getUserName() %>
    </h1>
    <div class="container">
        <div class="restaurant-grid" id="restaurant-grid">
            <%
                ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants");
            	if(restaurants!=null){
                for (Restaurant res : restaurants) {
            %>
            <div class="restaurant-card" data-name="<%= res.getName().toLowerCase() %>">
                <a href="MenuServlet?restaurantId=<%= res.getRestaurantId() %>" style="text-decoration:none">
                    <img src="<%=res.getImagePath() %>" alt="Restaurant" height="200px" width="200px">
                    <div class="restaurant-info">
                        <div class="restaurant-header">
                            <div class="restaurant-name"><%= res.getName() %></div>
                            <div class="restaurant-rating">&#9733; 4.5</div>
                        </div>
                        <div class="restaurant-details">
                            <div><%= res.getCuisineType() %></div>
                            <div><%= res.getDeliveryTime() %> mins</div>
                            <div class="restaurant-availability available">
                                <%= res.isActive() ? "Active" : "Closed" %>
                            </div>
                        </div>    
                    </div>
                </a>
            </div>
            <%
                }
            	}
            	else if (restaurants == null || restaurants.isEmpty()) {
            %>
                <div style="text-align: center; margin-top: 50px;">
                    <h1>Temporarily, the menu is not available</h1>
                    <p>We apologize for the inconvenience. Please check back later.</p>
                </div>
            <%
                }
            %>
        </div>
        <div id="no-results" style="text-align: center; display: none;">No results found.</div>
    </div>
	
	<footer class="footer">
        <div class="footer-content">
            <div class="footer-column">
                <h3>Company</h3>
                <ul>
                    <li><a href="#">About us</a></li>
                    <li><a href="#">Team</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Blog</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h3>Contact</h3>
                <ul>
                    <li><a href="#">Help & Support</a></li>
                    <li><a href="#">Partner with us</a></li>
                    <li><a href="#">Ride with us</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h3>Legal</h3>
                <ul>
                    <li><a href="#">Terms & Conditions</a></li>
                    <li><a href="#">Refund & Cancellation</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Cookie Policy</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h3>Download App</h3>
                <div class="app-links">
                    <a href="#"><img src="./logo/play-store.png" alt="Get it on Google Play"></a>
                    <a href="#"><img src="./logo/app-store.png" alt="Download on the App Store"></a>
                </div>
                <div class="social-links">
                    <a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                    <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            &copy; 2024 Foodie Hub. All rights reserved.
        </div>
    </footer>
    
    <script>
    document.addEventListener("DOMContentLoaded", () => {
    	 const searchBox = document.getElementById("search-box");
         const restaurantCards = document.querySelectorAll(".restaurant-card");
         const noResults = document.getElementById("no-results");
         const profilePhoto = document.getElementById("profile-photo");
         const profileDropdown = document.getElementById("profile-dropdown");

         // Show username on profile icon click
         profilePhoto.addEventListener("click", () => {
             const isVisible = profileDropdown.style.display === "block";
             profileDropdown.style.display = isVisible ? "none" : "block";
         });
         
         
        searchBox.addEventListener("input", () => {
            const query = searchBox.value.toLowerCase().trim();
            let matches = 0;

            restaurantCards.forEach(card => {
                const name = card.getAttribute("data-name");
                if (name.includes(query)) {
                    card.style.display = "block"; // Show matching items
                    matches++;
                } else {
                    card.style.display = "none"; // Hide non-matching items
                }
            });

            // Show or hide the "No Results Found" message
            noResults.style.display = matches ? "none" : "block";
        });
    });
</script>
</body>
</html>