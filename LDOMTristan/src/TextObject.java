
public class TextObject extends DocObject {

	private String text;
	
	public TextObject(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public long characterCount() {
		return text.replace(" ", "").length();
	}

	public boolean contains(String s) {
		if(text.indexOf(s) != -1) {
			return true;
		}
		return false;
	}
	
	public String generateHTML() {
		return this.text;
	}

	public void replace(String oldS, String newS) {
		this.text.replace(oldS, newS);
	}

}
