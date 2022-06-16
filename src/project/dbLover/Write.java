package project.dbLover;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Write {
	public static int write(ArrayList<ArrayList<String>> db, String csvName) {
		Path path = Paths.get(csvName+".csv");
                if(Files.exists(path)) return 1;
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (int i = 0; i < db.size(); i++) {
				for (int j = 0; j < db.get(0).size(); j++) {
					writer.write(db.get(i).get(j));
					if (j != db.get(0).size() - 1) {
						writer.write(",");
					}
				}
				writer.write("\n");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<String>> list = new ArrayList<>();
		ArrayList<String> list3 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ArrayList<String> list2 = new ArrayList<>();
			for (int j = 0; j < 11; j++) {
				list2.add(Integer.toString(j));
				list3.add("");
			}
			list.add(list2);
		}
		list.add(list3);
		write(list, "aaaaa");
	}
}
