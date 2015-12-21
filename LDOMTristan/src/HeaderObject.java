
public class HeaderObject extends DocObject {

	private int level;
	
	public HeaderObject(int level, DocObject dObj) {
		this.level = level;
		this.children.add(dObj);
	}
	
	public String generateHTML() {
		String tag = "h" + this.level;
		return super.generateHTML(tag, false);
	}

}
