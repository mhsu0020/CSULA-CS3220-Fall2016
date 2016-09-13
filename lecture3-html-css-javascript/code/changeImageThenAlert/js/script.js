//Function expression. It's like calling myObject.method(), except myObject is a function expression wrapped in (function(){ })();
//This creates its own scope so that the variables are not global.
(function() {
  //Listens to DOMContentLoaded event to make sure page is loaded before accessing DOM
  document.addEventListener('DOMContentLoaded', function() {
    var image = document.getElementById("quiz1");
    image.addEventListener("click", function(event){
      var clickedImage = event.target;
      clickedImage.src="images/quiz2.png";
      console.log(clickedImage);
      alert("click on image");

    }, false);
  }, false);

})();
