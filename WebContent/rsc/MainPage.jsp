<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="rsc/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="rsc/js/map.js" type="text/javascript"></script>
<script src="rsc/js/upload.js" type="text/javascript"></script>
<script src="rsc/js/loadMap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="rsc/css/map.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id" content="264956101516-q0ur3n03vqjcr2re05roe2h06300u91e.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<title>Photo World!</title>
</head>
<body>
  <h3>Photo World Demo</h3> 
  <div id="logowanieForm">
  <ul>
  <li><label id="username"></label></li>
 <li> <img id="avatar"></img></li>
  <li class="login" ><div class="g-signin2" data-onsuccess="onSignIn"></div></li>
 <li id="logout" ><button id="signOut" onclick="signOut()" style="display:none;">Sign out</button></li>
 </ul>
</div>

<label id="coords"></label>
 
<div id="loginInfo">
<label> Login to add photos to map!</label>
</div> 
<div id="photoAdder"></div>
<form id="imgForm" runat="server" method="post" action="servlet" enctype="multipart/form-data" accept-charset="utf-8">
      <label>Choose photo to add to map!</label>
        <input type='file' name="photo" id="uploadImg"/>
	        <div id="loadedPhotoDiv" style="display:none;">
		        <label>Choose place to addPhoto: </label>
		          <input id="lon" type="text" name="lon" value="0">
		            <input id="lat" type="text" name="lat" value="0">
		          <img id="previewImg" src="#" alt="Upload image!" style="display:none;" />
		        <input id="savePhotoButton" type="submit" value="Save" style="display:none;">
	        </div>
        </form>
</div>
        
<!-- 	
<c:forEach items="helen" var="photo">
    <img src="${pageContext.request.contextPath}/images/${photo}">
</c:forEach>
 -->
   <!-- 
   <button id="addMarkerButton" onclick="AddPhotoToMap();" style="display:none;"> Add this photo to map!</button>
     --> 
    <div id="map"></div>
    <script>
    $("#uploadImg").change(function(){
    	readURL(this);
    });
    
    $(document).ready(function(){
    	LoadMap();
    	

    });

    
    </script>
    
  <script async defer  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDJAI8YZkwjydpg2u3WHsZR4G8CkYlZwbA&callback=initMap">
    </script>  
</body>
</html>