<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<meta name="google-signin-client_id" content="264956101516-q0ur3n03vqjcr2re05roe2h06300u91e.apps.googleusercontent.com">
<script src="rsc/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="rsc/js/bootstrap.min.js" type="text/javascript"></script>
<script src="rsc/js/map.js" type="text/javascript"></script>
<script src="rsc/js/upload.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="rsc/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="rsc/css/map.css"/>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<title>Photo World!</title>
</head>
<body>
<link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<div class="hit-the-floor">Photo World</div>
  <div id="logowanieForm">
  <ul>
 <li> <img id="avatar"></img></li>
  <li><label id="username"></label></li>
 </ul>
</div>

  <div class="login" ><div class="g-signin2" data-onsuccess="onSignIn"></div></div>
 <div id="logout" ><button id="signOut" onclick="signOut()" style="display:none;">Sign out</button></div>

  <!-- widoczne dla niezalogowanych -->
  <div id="loginInfo">
<label> Login to add photos to map!</label>
</div> 
<!-- widoczne dla zalogowanych -->
<div id="photoAdder" class="panel panel-default adderSlider">
  <div class="panel-body">
<form id="imgForm" runat="server" method="post" action="servlet" enctype="multipart/form-data" accept-charset="utf-8">
      <label id="addPhotoLabel">Choose photo to add to the map!</label>
      </BR>
      <label for="uploadImg" class="custom-file-upload">
    <i class="fa fa-cloud-upload"></i> Choose photo
</label>
 <input id="uploadImg" type='file' name="photo"/>
	        <div id="loadedPhotoDiv" style="display:none;">
		        <label>Doubleclick on map to add coordinates to the photo!</label>
		           </BR>
		          <input id="lon" type="text" name="lon" value="0" class="form-control mx-sm-3" aria-describedby="lonHelpInline">
    <small id="lonHelpInline" class="text-muted">
      Longitude. 
    </small>
       </BR>
		            <input id="lat" type="text" name="lat" value="0"class="form-control mx-sm-3" aria-describedby="latHelpInline">
    <small id="latHelpInline" class="text-muted">
      Latitude
    </small>
       </BR>
		              <input id="usernameForm" type="text" name="username" value="anon" style="display:none;">
		          <img id="previewImg" src="#" alt="Upload image!" style="display:none;" />
		                 </BR>
		        <input id="savePhotoButton" class="custom-file-upload" type="submit" value="Save" style="display:none;">
	        </div>
        </form>

  <span id="arrow" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>

</div>
</div>
</div>

        

   <!-- 
   <button id="addMarkerButton" onclick="AddPhotoToMap();" style="display:none;"> Add this photo to map!</button>
     --> 
    <div id="map"></div>
    <footer class="footer">
      <div class="container">
      <label id="coords"></label>
        <p class="text-muted">Projekt na przedmiot Aplikacje webowe </BR>© Maja Trzepacz, Ewa Rożek </BR>(2017r).</p>
      </div>
    </footer>
    
    <script>
    $("#uploadImg").change(function(){
    	readURL(this);
    });

    
    //walidacja liczb w polach dla szerokości geograficznej
    $("#lon").keyup(function(){
    	if(!validate($(this).val())){
    		$(this).val(0.0);
    	}
    });
    $("#lat").keyup(function(){
    	if(!validate($(this).val())){
    		$(this).val(0.0);
    	}
    });
    function validate(s) {
        var rgx = /^[0-9]*\.?[0-9]*$/;
        return s.match(rgx);
    }
   
    
    </script>
    
  <script async defer  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDJAI8YZkwjydpg2u3WHsZR4G8CkYlZwbA&callback=initMap">
    </script>  
</body>
</html>