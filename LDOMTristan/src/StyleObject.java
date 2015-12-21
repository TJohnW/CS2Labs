
public class StyleObject extends DocObject {

	private TextStyle style;
	
	public StyleObject(TextStyle style, DocObject dObj) {
		this.style = style;
		this.children.add(dObj);
	}
	
	public String generateHTML() {
		String tag = "";
		if(this.style == TextStyle.bold) tag = "b";
		else if(this.style == TextStyle.italic) tag = "i";
		return super.generateHTML(tag, false);
	}

}
