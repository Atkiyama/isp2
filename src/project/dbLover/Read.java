package project.dbLover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * csvからデータを読み込むクラス
 * 実行するときはエクリプスではなくコマンドでdbLoverディレクトリにclassファイルを作成後、
 * srcにcsvファイルを配備し、srcをカレントディレクトリにしてjavaコマンドを実行すること
 * @author akiyama
 *@version 2020,12,1,20:48
 */
public class Read {
	public static ArrayList<ArrayList<String>> read(String csvName){
		//パスのクラス
		Path path = Paths.get(csvName);
		ArrayList<ArrayList<String>> db = new ArrayList<>();
		//データを保存するリスト
		try {
			List<String> lines = Files.readAllLines(path);
			//１行ごとのリストとしてインプット
			for(String line :lines) {
				ArrayList<String>  columns =new ArrayList<>();
				//１行ずつのデータを入れるリスト
				String[] column = line.split(",");
				for(String data:column) {
					//１行のデータを入れる
					columns.add(data);
				}
				//１行のデータを加える
				db.add(columns);
			}
		} catch(IOException e) {
			//エラー文の表示。うまく読み込めなかった場合はここに処理が飛ぶ
			System.out.print(e);
		}
		return db;

	}
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> test1 = Read.read("inputTest.csv");
		for(ArrayList<String> test1Line:test1) {
			for(String test1data:test1Line) {
				System.out.println(test1data);
			}
		}
	}
}
