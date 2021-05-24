package com.mobiquity.packer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PackItemLoader {

	public static ArrayList<PackageRow> loadItems(String path) throws Exception {

		ArrayList<PackageRow> listOfRows = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
			br.mark(1); // BOM fix
			if (br.read() != 0xFEFF)
				br.reset();

			String line;
			while ((line = br.readLine()) != null) {

				String[] maxWghtAndItems = line.split(":");

				BigDecimal packageRowMaxWeight = new BigDecimal(maxWghtAndItems[0].trim());
				String[] rowItemOptionsStr = maxWghtAndItems[1].replace("  ", " ").trim().split(" "); // double space in file can cause error
				ArrayList<Item> rowItems = new ArrayList<>();

				for (String opt : rowItemOptionsStr) {
					opt = opt.replace("(", "");
					opt = opt.replace(")", "");
					String[] itemStr = opt.split(",");
					int index = Integer.parseInt(itemStr[0]);
					BigDecimal wght = new BigDecimal(itemStr[1]);
					BigDecimal price = new BigDecimal(itemStr[2].substring(1));
					Item rowItem = new Item(index, wght, price);
					rowItems.add(rowItem);
				}

				PackageRow packageRow = new PackageRow(packageRowMaxWeight, rowItems);
				listOfRows.add(packageRow);
			}
		}

		ValidationResult vrPack = Validations.validatePackage(listOfRows);
		if (!vrPack.isValid)
			throw new Exception(vrPack.getDescription());

		return listOfRows;
	}

	public static void main(String[] args) throws Exception {
		String filePath = "C:\\workspaces\\dev\\mobiquity\\test1\\src\\test\\resources\\testFile1.txt";

		loadItems(filePath);

		System.out.println("end");
	}

}
