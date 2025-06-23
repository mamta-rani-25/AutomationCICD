package mamtaranipkg.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.bytebuddy.jar.asm.TypeReference;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
		//Convert String content to HashMap Jackson databind
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readvalue(jsonContent, new TypeReference(List<HashMap<String, String>>>(){
				});
		return data;
		
		
		
	}

}
