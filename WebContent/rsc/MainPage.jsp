<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
        <!-- zignorować bład xD -->
    <jsp:include page="/servlet" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/map.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/map.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id" content="264956101516-q0ur3n03vqjcr2re05roe2h06300u91e.apps.googleusercontent.com">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<title>Photo World!</title>
</head>
<body>
  <h3>Photo World Demo</h3> 
  <img id="avatar"></img>
  <label id="coords"></label>
  <div class="g-signin2" data-onsuccess="onSignIn"></div>
<a href="#" onclick="signOut()">Sign out</a>
    <div id="map"></div>
  
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDJAI8YZkwjydpg2u3WHsZR4G8CkYlZwbA&callback=initMap">
    </script>  
</body>
</html>