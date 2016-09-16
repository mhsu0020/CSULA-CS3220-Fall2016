
public class Quiz {
	
	String questionText;
	String[] options;
	int correctAnswerIndex;
	//From 1 to 10
	int difficultyRating;
	
	public Quiz(String questionText, String[] options, int correctAnswerIndex, int difficultyRating) {
		this.questionText = questionText;
		this.options = options;
		this.correctAnswerIndex = correctAnswerIndex;
		this.difficultyRating = difficultyRating;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}
	public void setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex;
	}
	public int getDifficultyRating() {
		return difficultyRating;
	}
	public void setDifficultyRating(int difficultyRating) {
		this.difficultyRating = difficultyRating;
	}

	
	

}
