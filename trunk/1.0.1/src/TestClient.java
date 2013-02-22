import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

import com.json.config.handlers.ValidationConfigType;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;


public class TestClient {
	@SuppressWarnings("rawtypes")
	public static void main(String... args) throws Exception
	{
		BufferedReader br=null;
		try{
			JsonParserFactory factory=JsonParserFactory.getInstance();
			JSONParser parser=factory.newJsonParser(ValidationConfigType.JSON);
			
			parser.initializeWithJson(JSONParser.class.getClassLoader().getResourceAsStream("com/json/config/json-config.json"));
			
			parser.setValidating(true);
			
			br=new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Putta's/Desktop/json-files/test.json"),Charset.forName("UTF-8")));
			String content=fetchContent(br);
			
			long currentTime=System.currentTimeMillis();			
			Map finalJsonData=parser.parseJson(content);
			System.out.println("Time Taken In Millis ::"+(System.currentTimeMillis()-currentTime));
			
			System.out.println("Final Map ------------------------------------------------------------------------------------");
			System.out.println(finalJsonData);
		}
		finally{
			if(br!=null)
			{
				try{
				br.close();}catch (Exception e) {
				}
			}
		}
	}

	private static String fetchContent(BufferedReader br) throws Exception
	{
		char[] charBuffer = new char[8192];
		StringBuilder builder=new StringBuilder();
		int offset=-1;
		while((offset=br.read(charBuffer))>-1)
		{
			builder.append(charBuffer,0,offset);
		}
		return builder.toString();
	}
}
