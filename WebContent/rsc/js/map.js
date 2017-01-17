//globals

 var profile;

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
	  profile = googleUser.getBasicProfile();
	  $("#username").text(profile.getName());
	  $("#usernameForm").val(profile.getName());
	  LoadMap(profile.getName());
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
    deleteMarkers();
  }

function LoadMap(name){
	
	  $.ajax({
        url:'images',
        data:{name:name},
        type:'get',
        cache:false,
        success:function(photos){
        	$.each( photos, function( key, data) {
           console.log(data);
           var point={};
           point.lat = parseFloat(data.latitude);
           point.lng = parseFloat(data.longitude);
           addMarker(point, data.path)
        	});
        },
        error:function(){
          alert('error');
        }
     }
	  );

}

function displayCoordinates(pnt) {
var lat = pnt.lat();
lat = lat.toFixed(4);
var lng = pnt.lng();
lng = lng.toFixed(4);
$("#coords").text("Latitude: " + lat + "\nLongitude: " + lng);
}
//Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

//Deletes all markers in the array by removing references to them.
function deleteMarkers() {
  clearMarkers();
  markers = [];
}
//Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setMapOnAll(null);
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
var markers=[];

function addMarker(pnt, src){  
	//	var photoLink = $("#previewImg").attr("src");
	var photoLink = src;
	    var marker = new google.maps.Marker({
	      position: pnt,
	      map: map
	      });
	    var    infowindow = new google.maps.InfoWindow();
	    infowindow.setContent('<IMG BORDER="0" ALIGN="Left" SRC="'+photoLink+'">'+"<BR>LAT: "+pnt.lat.toFixed(2) + "\t LON: "+pnt.lng.toFixed(2));
	    marker.info =   infowindow;
	 //	marker.info.open(map, marker);

	    markers.push(marker);
	    marker.addListener('click', function() {
	    	//testowo ustawianie kontentu na długość i szerokość, w przyszłości obrazek
	    	marker.info.open(map, marker);
	      });
	//finalizeAddingPhoto();	
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

