package implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import org.junit.Test;
import com.mobiquity.exception.APIException;
import com.mobiquity.packer.PackItemLoader;
import com.mobiquity.packer.PackageRow;
import com.mobiquity.packer.Packer;
import com.mobiquity.packer.ValidationResult;
import com.mobiquity.packer.Validations;

public class PackItemLoaderTest {

	@Test
	public void testLoadItems() throws Exception {

		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFile1.txt"; 
		ArrayList<PackageRow> pr =  PackItemLoader.loadItems(fileAbsPath);		
		assertNotNull("PackItemLoader.loadItems result is null", pr);
	}
	
	@Test(expected=Exception.class)
	public void testLoadItemsFailure() throws Exception {

		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileWithError.txt";
		ArrayList<PackageRow> pr = PackItemLoader.loadItems(fileAbsPath);
		assertNull("loaded file is not errornous. test failed.", pr);
	}
	
	
	@Test(expected=Exception.class)
	public void testLoadInvalidItemsCost() throws Exception {

		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileWithInvalidCost.txt";
		ArrayList<PackageRow> prList = PackItemLoader.loadItems(fileAbsPath);
		for (PackageRow packageRow : prList) {
			ValidationResult vr = Validations.validatePackageRowItemsCost(packageRow);
			if (!vr.isValid()) {
				System.out.println(vr.getDescription());
				assertFalse("package row line in not valid. test failed.", vr.isValid());
			}
		}

	}
	
	
	@Test(expected=Exception.class)
	public void testLoadInvalidItemsWeight() throws Exception {

		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileWithInvalidWeight.txt";

		ArrayList<PackageRow> prList = PackItemLoader.loadItems(fileAbsPath);
		for (PackageRow packageRow : prList) {
			ValidationResult vr = Validations.validatePackageRowItemsWeight(packageRow);		
			if (!vr.isValid()) {
				System.out.println(vr.getDescription());
				assertFalse("package row line in not valid. test failed.", vr.isValid());
			}
		}
 
	}
	
	
	@Test(expected=Exception.class)
	public void testLoadInvalidMaxPackageWeight() throws Exception {

		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFileWithInvalidPackageWeight.txt";

		ArrayList<PackageRow> prList = PackItemLoader.loadItems(fileAbsPath);

		for (PackageRow packageRow : prList) {
			ValidationResult vr = Validations.validatePackageRowWeight(packageRow);
			if (!vr.isValid()) {
				System.out.println(vr.getDescription());
				assertFalse("package row line in not valid. test failed.", vr.isValid());
			}
		}
	}
	
	
	@Test
	public void testPack() throws APIException {
		String fileAbsPath = "C:\\workspaces\\dev\\mobiquity\\implementation\\src\\test\\resources\\testFile1.txt";		
		String result = Packer.pack(fileAbsPath); 		
		System.out.println(result);		
		assertNotNull(result);		
	}
		
 
}
