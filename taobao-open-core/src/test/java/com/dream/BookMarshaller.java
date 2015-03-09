package com.dream;

import org.apache.commons.lang.StringEscapeUtils;

public class BookMarshaller {

	public static void main(String[] args)  {
//		try {
//			Book book = new Book("9780312347482", "Power Play", "Joseph Finder");
//			StringWriter writer = new StringWriter();
//			Marshaller mar = new Marshaller(writer);
//			Mapping mapping = new Mapping();
//			File file = new File("D:/Work/Frameworks/GitHub/taobao-in-action/taobao-open-core/src/test/java/com/dream/mapping.xml");
//			URL url = file.toURL();
//			mapping.loadMapping(url);
//			mar.setMapping(mapping );
//			mar.setSchemaLocation("http://www.bankofshanghai.com/BOSFX/2010/08 BOSFX2.0.xsd");
//			mar.marshal(book);
//			System.out.print(writer.toString());
//		} catch (Throwable e) {
//			System.err.println(e.getMessage());
//			e.printStackTrace(System.err);
//		}
		String ss = "身份证";
		
//		logger.debug(String.valueOf(ss));
		
		
		String u = StringEscapeUtils.escapeHtml(ss);
		
		System.out.print(u);
	}
}