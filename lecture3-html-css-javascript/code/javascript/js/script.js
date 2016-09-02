//Functional expression. It's like calling myObject.method(), except myObject is a functional expression wrapped in (function(){ })();
//This creates its own scope so that the variables are not global.
(function() {

  var currentQuizIndex = 0;

  var quiz1 = {
    description: "What is 1+1?",
    options: [1, 2, 3, 4],
    correctAnswer: 1,
    imgLink: "quiz1.jpg"
  };

  var quiz2 = {
    description: "What is 2+2?",
    options: [2, 3, 4, 5],
    correctAnswer: 2,
    imgLink: "quiz2.png"
  };

  var quizProblems = [quiz1, quiz2];

  var createQuizHTMLString = function(quiz) {
    var contentToDisplay = "";
    contentToDisplay += "<p>" + quiz.description + "</p>";
    contentToDisplay += "<img src='images/" + quiz.imgLink + "'>";
    contentToDisplay += "<ul>";
    var options = quiz.options;
    options.forEach(function(optionText) {
      contentToDisplay += "<li>" + optionText + "</li>"
    });
    contentToDisplay += "</ul>"
    return contentToDisplay;
  };

  var loadQuiz = function(quizIndex){
    var topDiv = document.getElementById('top-div');
    var quizString = createQuizHTMLString(quizProblems[currentQuizIndex]);
    topDiv.innerHTML = quizString;
  };

  var switchProblemFunction = function() {
    loadQuiz(currentQuizIndex);
    //increment quiz index if not last quiz problem, reset to zero if reached last
    currentQuizIndex = currentQuizIndex == quizProblems.length-1 ? 0 : currentQuizIndex + 1;
  };

  //Listens to DOMContentLoaded event to make sure page is loaded before accessing DOM
  document.addEventListener('DOMContentLoaded', function() {
    var button = document.getElementById("switch-problem");
    button.addEventListener("click", switchProblemFunction, false);

    //load first quiz
    loadQuiz(currentQuizIndex);

  }, false);

})();
