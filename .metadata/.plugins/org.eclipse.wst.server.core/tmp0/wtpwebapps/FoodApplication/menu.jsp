<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.foodapp.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menu</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
	<link rel="stylesheet" href="./styles/menu.css">
	<style>
		
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css');

body {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
}

.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #2c3e50;
    padding: 15px 30px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    transition: all 0.3s ease;
}

.navbar:hover {
    box-shadow: 0 4px 20px rgba(0,0,0,0.2);
}

.navbar-logo .logo {
    height: 40px;
    transition: transform 0.3s ease;
}

.navbar-logo .logo:hover {
    transform: scale(1.05);
}

.navbar-search {
    display: flex;
    align-items: center;
    background-color: #34495e;
    border-radius: 25px;
    overflow: hidden;
    transition: all 0.3s ease;
}

.navbar-search:focus-within {
    box-shadow: 0 0 0 2px #3498db;
}

.navbar-search input {
    padding: 10px 15px;
    border: none;
    background-color: transparent;
    color: #ecf0f1;
    font-size: 14px;
    width: 200px;
    transition: width 0.3s ease;
}

.navbar-search input:focus {
    width: 250px;
    outline: none;
}

.navbar-search input::placeholder {
    color: #bdc3c7;
}

.navbar-search .search-btn {
    padding: 10px 15px;
    background-color: #3498db;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.navbar-search .search-btn:hover {
    background-color: #2980b9;
}

.navbar-actions {
    display: flex;
    align-items: center;
}

.navbar-actions button {
    margin-left: 15px;
    padding: 10px 15px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 25px;
    cursor: pointer;
    transition: all 0.3s ease;
    font-size: 14px;
}

.navbar-actions button:hover {
    background-color: #2980b9;
    transform: translateY(-2px);
}

.cart-btn, .profile-photo {
    background-color: transparent;
    border: none;
    color: #ecf0f1;
    font-size: 20px;
    cursor: pointer;
    transition: all 0.3s ease;
}

.cart-btn:hover, .profile-photo:hover {
    color: #3498db;
    transform: scale(1.1);
}

.profile-photo {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    overflow: hidden;
    margin-left: 15px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #34495e;
}

@keyframes fadeIn {
    from { opacity: 0; transform: translateY(-10px); }
    to { opacity: 1; transform: translateY(0); }
}

.navbar {
    animation: fadeIn 0.5s ease-out;
}
	</style>
	
</head>
<body>
    <nav class="navbar">
        <div class="navbar-logo">
            <a href="#"><img src="./logo/fast-food.png" alt="Foodie Hub Logo" class="logo"></a>
        </div>
        <div class="navbar-search">
            <input type="text" placeholder="Search Menu..." id="search-box">
            <button class="search-btn">
                <i class="fas fa-search"></i>
            </button>
        </div>
        <div class="navbar-actions">
            <button class="cart-btn">
                <a href="CartServlet"><i class="fas fa-shopping-cart"></i></a>
            </button>
            <div class="profile-photo">
                <i class="fas fa-user"></i>
            </div>
        </div>
    </nav>

    <%
        // Retrieve and cast the session attributes
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
    %>
    <h1><%= restaurant.getName() %></h1>
    <h3><%= restaurant.getAddress() %></h3>

    <div class="menu-container">
        <%
            ArrayList<Menu> menu = (ArrayList<Menu>) session.getAttribute("menu");
            for (Menu m : menu) {
        %>
        <div class="menu-item" data-name="<%= m.getName().toLowerCase() %>">
            <div class="menu-item-info">
                <div class="menu-item-name"><%= m.getName() %></div>
                <div class="menu-item-price">&#8377;<%= m.getPrice() %></div>
                <div class="menu-item-description"><%= m.getDescription() %></div>
                <div class="menu-item-status">
                    <%= m.isActive() ? "Available" : "Not Available" %>
                </div>
            </div>
            <div class="menu-item-image-container">
                <img src="<%= m.getImagePath() %>" alt="<%= m.getName() %>" class="menu-item-image">
                <form action="CartServlet" method="get">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
                    <input type="submit" class="add-to-cart" value="Add">
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div id="no-results" style="text-align: center; display: none;">No results found.</div>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const searchBox = document.getElementById("search-box");
            const menuItems = document.querySelectorAll(".menu-item");
            const noResults = document.getElementById("no-results");

            searchBox.addEventListener("input", () => {
                const query = searchBox.value.toLowerCase().trim();
                let matches = 0;

                menuItems.forEach(item => {
                    const name = item.getAttribute("data-name");
                    if (name.includes(query)) {
                        item.style.display = "flex"; // Show matching items
                        matches++;
                    } else {
                        item.style.display = "none"; // Hide non-matching items
                    }
                });

                // Show or hide the "No Results Found" message
                noResults.style.display = matches ? "none" : "block";
            });
        });
    </script>
</body>
</html>