<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="index.css">
        <script src="https://kit.fontawesome.com/3204349982.js" crossorigin="anonymous"></script>
        <% //TODO iterate the cookie and check if user registered %>
    </head>

    <body>
        <!-- TODO -->
        <header>
        <div id="nav">
		<ul>
  			<a href="index.jsp" class="no-style" id="logo">SalEats!</a>
  			<span id="buttons">
	  			<a href="index.jsp" class="no-style" id="home">Home</a>
  			<%Cookie[] cookies= null; 
  			cookies = request.getCookies();
  			int idx = 0;
  			boolean found = false;
  			if(cookies != null) {
  				for(int i = 0; i < cookies.length; i++) {
  	  				if((cookies[i].getName()).trim().equals("ck_name")) {
  	  					found = true;
  	  					break;
  	  				}
  	  				idx++;
  	  			}
  			}
  			%>
  			<%if(!found) {%>
  				<a href="auth.jsp" class="no-style">Login / Register</a>
  			<%} %>
  			<%if(found) {%>
  				<li style="float:left"><div id="hi">Hi, <%out.print(cookies[idx].getValue()); %> </div></li>
  				<li>
				<form action="LogoutDispatcher" method="GET">
  				<button class="nav-button" name="log-out-button" type="submit">Logout</button>
  				</form>
  				</li>
			<%} %>
		</ul>
		</div>
		</header>
	  	<main>
	  		<img id="banner" src="images/banner.jpg">
	  		<div>
		  		<form action="SearchDispatcher" method="GET">
		  			<div class="form-container">
		  				<div class="column">
		  				<select name="question">
							<option value="Category">Category</option>	
							<option value="Name">Name</option>	
						</select>
					  		<input type="text" placeholder="Restaurant Name" name="restaurant" class="textbox" required><br/>
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
	  	</main>
    </body>

    </html>