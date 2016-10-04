
public class Quiz {
	int id;
	String questionText;
	String[] options;
	int correctAnswerIndex;
	//From 1 to 10
	int difficultyRating;
	
	Category category;

	public Quiz(int id, String questionText, String[] options, int correctAnswerIndex, int difficultyRating, Category category) {
		this.id = id;
		this.questionText = questionText;
		this.options = options;
		this.correctAnswerIndex = correctAnswerIndex;
		this.difficultyRating = difficultyRating;
		this.category = category;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
