//globals

 var profile;

//mapa
var map; 

function initMap() {
        var centerPoint = {lat: 52.0, lng: 19.0};
        	map = new google.maps.Map(document.getElementById('map'), {
          zoom: 15,
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
        
     

// HTML5 geolocation.
if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(function(position) {
    var pos = {
      lat: position.coords.latitude,
      lng: position.coords.longitude
    };

    map.setCenter(pos);
  }, function() {
   console.log("cannot obtain geolocation");
  });
} 
}


function onSignIn(googleUser) {
	  profile = googleUser.getBasicProfile();
	  $("#username").text(profile.getName());
	  $("#usernameForm").val(profile.getName());
	  LoadMap(profile.getName());
	  $(".g-signin2").hide();
	  $("#signOut").show();
//	  console.log('ID: ' + profile.getId()); 
//	  console.log('Name: ' + profile.getName());
//	  console.log('Image URL: ' + profile.getImageUrl());
//	  console.log('Email: ' + profile.getEmail());
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
           addMarker(point, data.path, data.id)
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

var clickedMarker;

function addMarker(pnt, src, id){  
	//	var photoLink = $("#previewImg").attr("src");
	var photoLink = src;
	    var marker = new google.maps.Marker({
	      position: pnt,
	      map: map
	      });
	    var    infowindow = new google.maps.InfoWindow();
	    infowindow.setContent(' <a href="'+photoLink+'"><IMG BORDER="0" ALIGN="Left" style="max-height:300px" SRC="'+photoLink+'"/></a>'+"</BR>LAT: "+pnt.lat.toFixed(4) + "</BR> LON: "+pnt.lng.toFixed(4)+"</BR><button data-id='"+id+"'class='deleteButton' onclick='DeletePhoto(this)'>DELETE</button>");
	    marker.info =   infowindow;
	 //	marker.info.open(map, marker);

	    markers.push(marker);
	    marker.addListener('click', function() {
	    	marker.info.open(map, marker);
	    	clickedMarker = marker;
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
	   $("#lon").val(pnt.lng().toFixed(4));
	   $("#lat").val(pnt.lat().toFixed(4));
	   lastMarker = marker;

}

function DeletePhoto(e){
	var photo = $(e).attr("data-id");
	$.ajax({
	      url:'delete',
	      data:{id:photo},
	      type:'get',
	      cache:false,
	      success:function(){
	    	  clickedMarker.setMap(null);
	    	  		console.log("deleted photo");
	      },
	      error:function(){
	        alert('error deleting photo');
	      }
	   }
		  );
}

	
	 