package implementation;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class PackerTest {

	 	
	
	@Test
	public void testPack() throws APIException {
		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFile1.txt";
		
		String result = Packer.pack(fileAbsPath); 
		
		System.out.println(result);
		
		assertNotNull(result);
		
	}
	
	
	
 
}
