
public class Sequence extends DocObject {
	
	public Sequence() {}
	
	public void addChild(int before, DocObject dObj) {
		super.realAddChild(before, dObj);
	}
	
	public String generateHTML() {
		return super.generateHTMLLin();
	}
	
	public void replace(DocObject oldObj, DocObject newObj) {
		for(DocObject obj: children()) {
			if(oldObj.equals(obj)) {
				oldObj = newObj;
			}
		}
	}
	
	public void replace(String oldS, String newS) {
		for(DocObject obj: children()) {
			obj.replace(oldS, newS);
		}
	}

}
