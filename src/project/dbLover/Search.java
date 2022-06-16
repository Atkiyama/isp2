package project.dbLover;

import java.util.ArrayList;

public class Search {

	public static ArrayList<ArrayList<String>> search(String searchThings, ArrayList<ArrayList<String>> db) {
		ArrayList<ArrayList<String>> ret = new ArrayList<>();//返り値

		for (int i = 0; i < db.size(); i++) {
			for (int j = 0; j < db.get(0).size(); j++) {
				if(db.get(i).get(j).equals(searchThings)) {//文字列一致の場合
					ret.add(db.get(i));
					break;
				}
			}
		}
		return ret;
	}
	//こっからテスト
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> lists = new ArrayList<>();
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		ArrayList<String> list3 = new ArrayList<>();
		ArrayList<String> list4 = new ArrayList<>();
		list1.add("A");
		list1.add("A");
		list1.add("A");

		list2.add("A");
		list2.add("B");
		list2.add("C");

		list3.add("B");
		list3.add("B");
		list3.add("");

		list4.add("");
		list4.add("");
		list4.add("");

		lists.add(list1);
		lists.add(list2);
		lists.add(list3);
		lists.add(list4);


		ArrayList<ArrayList<String>> retA = search("A",lists);
		ArrayList<ArrayList<String>> retB = search("B",lists);
		ArrayList<ArrayList<String>> retC = search("C",lists);
		ArrayList<ArrayList<String>> retSpace = search("",lists);

		Write.write(retA,"searchTestA");
		Write.write(retB,"searchTestB");
		Write.write(retC,"searchTestC");
		Write.write(retSpace,"searchTestSpace");
	}


}
