<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="index.css">
    <script src="https://kit.fontawesome.com/3204349982.js"
            crossorigin="anonymous"></script>

    <%
        //TODO perform search

        //TODO check if logged in
    %>
</head>
<body>
<!-- TODO -->
	<header>
  		<nav>
  			<a href="home.jsp" class="no-style" id="logo">SalEats!</a>
  			<span id="buttons">
	  			<a href="home.jsp" class="no-style" id="home">Home</a>
	  			<%
	  				if (null != request.getSession().getAttribute("name")) {
			        	out.println("<a href=\"favorites\" class=\"no-style\" id=\"favorites\">Favorites</a>");
			        	out.println("<a href=\"auth\" class=\"no-style\">Logout</a>");
			   		} else {
			        	out.println("<a href=\"login.jsp\" class=\"no-style\">Login / Register</a>");
			    	}
			  	%>
  			</span>
  		</nav>
  	</header>
  	<main>
  		<div>
	  		<form action="search" method="GET">
	  			<div class="form-container">
	  				<div class="column">
				  		<input type="text" placeholder="Restaurant Name" name="restaurant" class="textbox" required><br/>
				  		<input type="text" placeholder="Location" name="location" class="textbox" required>
			  		</div>
			  		<div class="column">
			  			<div>
				  			<button type="submit" id="search_button">
							    <i class="fas fa-search"></i>
							</button>
						</div>
						<div class="radio-buttons-container">
							<div>
						            <label class="radio-element">
						              <input type="radio" value="price" name="sort" checked="checked"/>
						              Price
						            </label>
						            <br/>
						            <label class="radio-element">
						              <input type="radio" value="review_count" name="sort" />
						              Review Count
						            </label>
						        </div>
					            <div>
						            <label class="radio-element">
						              <input type="radio" value="rating" name="sort" />
						              Rating
						            </label>
					            </div>
						</div>
					</div>
				</div>
	  		</form>
  		</div>
  		<h1 id="results-header">Results for <%= request.getAttribute("search")%></h1>
  		<div id="results-container">
			<c:forEach var="restaurant" items="${data}">
				<div class="result">
					<a href="details?id=${restaurant.id}">
						<img src="${restaurant.image_url}" alt="Image of ${restaurant.name}" id="yelp-image">
					</a>
					<div>
						<h2>${restaurant.name}</h2>
						<p>${restaurant.location}</p>
						<a href="${restaurant.url}" target="_blank" class="yelp-link" id="underline">Yelp Link</a>
					</div>
				</div>
			</c:forEach>
		</div>
  	</main>
</body>
</html>