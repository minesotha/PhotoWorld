function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#previewImg').attr('src', e.target.result);
            $("#addMarkerButton").show();
            $('#previewImg').show();
            $('#savePhotoButton').show();
            $('#loadedPhotoDiv').show();
            $('#addPhotoLabel').hide();
            $("#photoAdder").removeClass("adderSlider");
            AddPhotoToMap();
        }        
        reader.readAsDataURL(input.files[0]);
    }
}

$("#uploadImg").change(function(){
    readURL(this);
});



