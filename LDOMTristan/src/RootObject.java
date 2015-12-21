
public class RootObject extends DocObject {

	private String title;
	
	private String style = "<style>"
			+ "\n  #page_container { padding:0 10px; width:600px; "
			+ "border:1px solid #e5e5e5;"
			+ "background:#f5f5f5; color:#333; margin:30px auto; }"
			+ "\n</style>\n";
	
	public RootObject(String title, DocObject dObj) {
		this.title = title;
		this.children.add(dObj);
	}
	
	public boolean isRoot() { return true; }
	
	public String generateHTML() {
		String html = "<html>\n<head>\n" + this.style + "<title>" + this.title + "</title>\n</head>\n<body>\n<div id=\"page_container\">\n";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		html += "</div>\n</body>\n</html>";
		return html;
	}

}
