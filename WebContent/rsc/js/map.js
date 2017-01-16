//upload plików






//mapa
var map; 

function initMap() {
        var centerPoint = {lat: 52.0, lng: 19.0};
        	map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          disableDoubleClickZoom: true,
          center: centerPoint
        });
        
        
        map.addListener( 'mousemove', function (event) {
        	displayCoordinates(event.latLng);               
        });

        map.addListener( 'dblclick', function (event) {
//        		addMarker(event.latLng); 
        	getLocation(event.latLng); 
        });
        
     
}

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  $("#username").text(profile.getName());
	  $(".g-signin2").hide();
	  $("#signOut").show();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	  $("#avatar").attr("src", profile.getImageUrl());
  	$("#loginInfo").hide();
	$("#imgForm").show();
}

function signOut() {
	$("#loginInfo").show();
	$("#imgForm").hide();
	  $("#username").text("");
	  $("#avatar").attr("src", "");
	  $(".g-signin2").show();
	  $("#signOut").hide();
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }

function displayCoordinates(pnt) {
var lat = pnt.lat();
lat = lat.toFixed(4);
var lng = pnt.lng();
lng = lng.toFixed(4);
$("#coords").text("Latitude: " + lat + "  Longitude: " + lng);
}

var canAddMarker=false;

function AddPhotoToMap(){
	canAddMarker=true;
}
//wyłącz niepotrzebne już rzeczy po dodaniu markera
function finalizeAddingPhoto(){
    //wyłącz okna dodawania zdjęć
	$('#previewImg').attr('src',null);
    $("#addMarkerButton").hide();
    $('#previewImg').hide();
    canAddMarker=false;
}

function addMarker(pnt){  
	if(canAddMarker==true){
		var photoLink = $("#previewImg").attr("src");
	
	    var marker = new google.maps.Marker({
	      position: pnt,
	      map: map
	      });
	    var    infowindow = new google.maps.InfoWindow();
	    infowindow.setContent('<IMG BORDER="0" ALIGN="Left" SRC="'+photoLink+'">'+"<BR>LAT: "+pnt.lat().toFixed(2) + "\t LON: "+pnt.lng().toFixed(2));
	    marker.info =   infowindow;
	 	marker.info.open(map, marker);

	    marker.addListener('click', function() {
	    	//testowo ustawianie kontentu na długość i szerokość, w przyszłości obrazek
	    	marker.info.open(map, marker);
	      });
	}
	finalizeAddingPhoto();	
}

var lastMarker;

function getLocation(pnt){
	if(lastMarker!=null){		
		lastMarker.setMap(null);
	}
	   var marker = new google.maps.Marker({
		      position: pnt,
		      map: map
		      });
	   $("#lon").val(pnt.lng().toFixed(2));
	   $("#lat").val(pnt.lat().toFixed(2));
	   lastMarker = marker;

}

