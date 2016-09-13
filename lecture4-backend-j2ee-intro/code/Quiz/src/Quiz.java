
public class Quiz {
	
	String questionText;
	String[] options;
	int correctAnswerIndex;
	
	public Quiz(String questionText, String[] options, int correctAnswerIndex) {
		this.questionText = questionText;
		this.options = options;
		this.correctAnswerIndex = correctAnswerIndex;
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
	
	

}
