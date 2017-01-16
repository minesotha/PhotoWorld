
function LoadMap(){
	
	  $.ajax({
          url:'images',
          data:{name:'helen'},
          type:'get',
          cache:false,
          success:function(data){
             console.log(data);
          },
          error:function(){
            alert('error');
          }
       }
	  );

}