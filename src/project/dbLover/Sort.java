package project.dbLover;

import java.util.ArrayList;

/**
 * リストを降順、昇順にソートするクラス.
 * @author akiyama
 @version 2020,12,1,20:48 
 */
public class Sort {
	/**
	 * ソートするクラス
	 * compareToメソッドを利用したバブルソートを使っている
	 * compareToメソッドはdata.compareTo(data2)としたときにdata<data2なら1以上の数、同じなら0、data>data2なら-1を返す。
	 * 書き方はデータとアルゴリズム1のpdf準拠で書いている
	 * @param db ソートするデータ
	 * @param order 降順昇順を定める定数 詳しくはOrder.javaを参照
	 * @param colum何列目のデータに対してソートするかをきめる変数。あくまでdbの引数ではなく列の番号(1,2,3,4...)なので注意
	 * @return ソートされたデータ
	 */
	public static ArrayList<ArrayList<String>> sort(ArrayList<ArrayList< String > > db,Order order,int column){
		//ソート用データ格納庫
		ArrayList<ArrayList<String>> db2= new ArrayList<>();
		for(ArrayList<String> data:db)
			db2.add(data);
		int n= db2.size();
		for(int k=1;k<n;k++) {
			for(int i=0;i<n-k;i++) {
				if(order.equals(Order.ASCENDING)&&db2.get(i).get(column-1).compareTo(db2.get(i+1).get(column-1))>=1) {
					ArrayList<String> swap = db2.get(i);
					db2.set(i,db2.get(i+1));
					db2.set(i+1,swap);
				}else if(order.equals(Order.DESCENDING)&&db2.get(i+1).get(column-1).compareTo(db2.get(i).get(column-1))>=1) {
					ArrayList<String> swap = db2.get(i);
					db2.set(i,db2.get(i+1));
					db2.set(i+1,swap);
				}
			}
		}


		return db2;

	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//テストデータ
		ArrayList<String> data=new ArrayList<>();
		data.add("2");
		data.add("b");
		data.add("い");
		ArrayList<String> data2=new ArrayList<>();
		data2.add("3");
		data2.add("c");
		data2.add("う");
		ArrayList<String> data3=new ArrayList<>();
		data3.add("1");
		data3.add("a");
		data3.add("あ");
		ArrayList<ArrayList<String>> dataList =new ArrayList<>();
		dataList.add(data);
		dataList.add(data2);
		dataList.add(data3);
		//ここまででテスト用のデータを作成
		//ここ以降で一列目,二列目、三列目の順にそれぞれソート
		for(int i=1;i<=3;i++) {
			dataList =Sort.sort(dataList, Order.ASCENDING, 1);

			for(ArrayList<String> showdata:dataList) {
				System.out.println(showdata);
			}
			dataList =Sort.sort(dataList, Order.DESCENDING, 1);

			for(ArrayList<String> showdata:dataList) {
				System.out.println(showdata);
			}
		}

	}
}
