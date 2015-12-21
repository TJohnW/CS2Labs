
public class ParagraphObject extends DocObject {
	
	public ParagraphObject(DocObject dObj) {
		this.children.add(dObj);
	}
	
	public String generateHTML() {
		return super.generateHTML("p", false);
	}

}
