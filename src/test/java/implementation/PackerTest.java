package implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class PackerTest {

	 	
	
	@Test
	public void testPack() throws APIException {
		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFile2.txt";
		
		String result = Packer.pack(fileAbsPath); 
		
		System.out.println(result);
		
		assertNotNull(result);
		
	}
	
	
	@Test
	public void testPackError() throws APIException {
		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileError1.txt";
		
		String result=null;
		try {
			result = Packer.pack(fileAbsPath);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
		assertNull(result);
		
	}
	
	
 
}
