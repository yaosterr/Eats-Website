<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script src="https://kit.fontawesome.com/3204349982.js"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="index.css">
</head>
<%
    //TODO perform search

    //TODO check if logged in
%>

<body>
<!-- TODO -->
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
  		<h1 id="results-header"><%= request.getAttribute("search")%></h1>
  		<div id="results-container">
  			<div class="result" id="detail">
				<a href="${restaurant.url}" target="_blank">
					<img src="${restaurant.image_url}" alt="Image of ${restaurant.name}" id="yelp-image">
				</a>
				<div class="result-info">
					<p>Address: ${restaurant.location}</p>
					<p>${restaurant.phone}</p>
					<p>Categories: 
						<c:forEach var="category" items="${restaurant.categories}" varStatus="loop">
							${category.title}<c:if test="${!loop.last}">, </c:if>
						</c:forEach>
					</p>
					<p>Price: ${restaurant.price}</p>
					<p>Rating: 
						<c:forEach begin="0" end="${restaurant.rating}" varStatus="loop">&#9733; </c:forEach> 
					</p>
				</div>
			</div>
			<form action="favorites" method="POST">
				<input type="hidden" name="id" value="${restaurant.id}">
				<%
		  			if (null != request.getSession().getAttribute("name")) {
		  				if (null != request.getAttribute("isFavorited")) {
		  					out.println();
					        out.println("<button type=\"submit\" class=\"favorite-button\" id=\"unfavorite\">");
					        out.println("<i class=\"fas fa-minus-circle\"></i> Remove from Favorites");
					        out.println("</button>");
		  				} else {
		  					out.println("<input type=\"hidden\" name=\"add\" value=\"true\">");
					        out.println("<button type=\"submit\" class=\"favorite-button\">");
					        out.println("<i class=\"fas fa-star\"></i> Add to Favorites");
					        out.println("</button>");
		  				}
				   	}
				%>
			</form>
		</div>
  	</main>
</body>
</html>