/**
 * Allows importing data from CSV in objects found in web
 * pages or selecting from dropdown lists based on CSV values.
 */

package nl.funda.uitests.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class CsvDataProvider {

	static Log logger = LogFactory.getLog(CsvDataProvider.class);

	@DataProvider(name = "CsvDataProvider")
	public static Iterator<Object[]> provideData(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		// class_method.csv
		String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data"
				+ File.separator + method.getDeclaringClass().getSimpleName() + "_" + method.getName() + ".csv";
		File file = new File(pathname);

		// Print the absolute path of the CSV input file
		// System.out.println("****** Test data file: '" + file.getAbsolutePath() + "';
		// exists: " + file.exists());

		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				while ((dataParts = reader.readNext()) != null) {
					Map<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i < keys.length; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					list.add(new Object[] { testData });
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					"File [" + file.getAbsolutePath() + "] was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException(
					"Could not read [" + file.getAbsolutePath() + "] file.\n" + e.getStackTrace().toString());
		}
		return list.iterator();

	}

}
