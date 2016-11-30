//upload plików






//mapa
var map; 
var infowindow;

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
        	addMarker(event.latLng);               
        });
        
        infowindow = new google.maps.InfoWindow();
}

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	  $("#avatar").attr("src", profile.getImageUrl());
}

function signOut() {
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

function addMarker(pnt){  
    var marker = new google.maps.Marker({
      position: pnt,
      map: map
    });
    marker.addListener('click', function() {
    	//testowo ustawianie kontentu na długość i szerokość, w przyszłości obrazek
    	infowindow.setContent("LAT: "+marker.position.lat().toFixed(2) + "\t LON: "+marker.position.lng().toFixed(2));
        infowindow.open(map, marker);
      });
}

