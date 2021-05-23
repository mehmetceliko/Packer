import com.mobiquity.exception.APIException;

public class StartPackerUse {
	
	
	
	public static void main(String[] args) throws APIException {
		
		System.out.println("start");
		String filePath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileError1.txt";
		
		
		
		try {
			String testResult = com.mobiquity.packer.Packer.pack(filePath);
			System.out.println(testResult);
		}catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("finish");
	}

}
