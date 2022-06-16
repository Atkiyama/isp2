package project.dbLover;

import java.util.ArrayList;
/**
 * データベースそのものを表すクラス
 * @author akiyama
 *@version 2020,12,1,20:48
 */

public class Database {
	private int height;
	private int width;
	private ArrayList<ArrayList<String>> db;
	private String name;
	private String csvName;
	/**
	 * コンストラクタ
	 * リストの初期化も行う
	 * @param csvName csvファイルの名前
	 */
	public Database(String csvName) {
		db= new ArrayList<>();
		this.csvName = csvName;
	}

	/**
	 * コンストラクタ
	 * リストの初期化も行う
	 * @param height
	 * @param width
	 */
	public Database(int height, int width) {
		db =new ArrayList<>();
		this.height = height;
		this.width = width;
	}
	/**
	 * csvファイルにdbの中身を書き込むクラス
	 */
	public int write() {
		return Write.write(db,name);
	}
	/**
	 * csvファイルを読み込むクラス
	 */

	public void read() {
		db=Read.read(csvName);
	}
	/**
	 * データをソートするメソッド
	 * @param order enumで定義された定数 詳しくはOrderクラスを参照
	 * @param column 何列目のデータに対してソートするかをきめる変数。あくまでdbの引数ではなく列の番号(1,2,3,4...)なので注意
	 */
	public void sort(Order order,int column) {
		db=Sort.sort(db,order,column);
	}
	/**
	 * データを検索するメソッド
	 * 検索結果に該当するものを複数表示する
	 * @param searchThingth 検索ワード
	 */

	public ArrayList<ArrayList<String>> search(String searchThingth) {
	    return Search.search(searchThingth, db);
	}

    public ArrayList<ArrayList<String>> getDb(){
	return db;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return width;
    }

    public void setDb(ArrayList<ArrayList<String>> db){
	this.db = db;
    }

    public void setName(String name){
	this.name = name;
    }

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
