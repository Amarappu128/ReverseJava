import java.util.Random;
import java.util.Scanner;

public class Reverse2 {

	int[][]bood = {
			{9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 0, 0, 0, 1, 2, 0, 0, 0, 9 },
			{9, 0, 0, 0, 2, 1, 0, 0, 0, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }
	};
	// int[][] bood = {//おける場所がない時の再現 一番下が相手がおけるとき 2回目自分が置けるようになる デバッグ用 二人とも置けなかった時に終了するテストも含まれる 戻すの注意
	// 		{9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
	// 		{9, 0, 0, 0, 0, 0, 0, 0, 2, 9 },
	// 		{9, 0, 0, 0, 0, 0, 0, 0, 2, 9 },
	// 		{9, 0, 0, 0, 0, 0, 0, 0, 2, 9 },
	// 		{9, 0, 0, 2, 2, 2, 2, 2, 2, 9 },
	// 		{9, 0, 0, 2, 2, 0, 0, 0, 2, 9 },
	// 		{9, 0, 0, 0, 0, 0, 0, 0, 2, 9 },
	// 		{9, 2, 2, 2, 2, 2, 0, 1, 1, 9 },
	// 		{9, 0, 2, 2, 0, 0, 0, 0, 2, 9 },
	// 		{9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }
	// };
	//bood 1-8 index
	//stone:1,2
	//仮置き:8
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	int my_stone;
	int your_stone;
	int attack_stone;

	int[] stoneChack() {
		//カウントの初期化
		int my_stone_count = 0;
		int your_stone_count = 0;
		int air_stone_count = 0;

		for (int i = 1; i < 9; i++) {
			for (int p = 1; p < 9; p++) {
				if (bood[i][p] == my_stone) {
					my_stone_count++;
				} else if (bood[i][p] == your_stone) {
					your_stone_count++;
				} else {
					air_stone_count++;
				}
			}
		}
		int[] out = {my_stone_count,your_stone_count,air_stone_count};
		
			return out;
	}
	int Count(){
		int[] count_stone = stoneChack();
		int out = 0;
		if (count_stone[0] == 0 || count_stone[1]==0 || count_stone[2] == 0) {
			if (count_stone[0] > count_stone[1]) {
				print(1);
				out = 2;
			} else if(count_stone[0]<count_stone[1]) {
				print(2);
				out = 2;
			}else{
				print(3);
			}
			print(count_stone[0], count_stone[1], count_stone[2]);
		}
		return out;
	}
	void changeAttack() {
		if (attack_stone == my_stone) {
			attack_stone = your_stone;
		} else {
			attack_stone = my_stone;
		}
	}
	int target() {
		int target_stone;
		if (attack_stone == my_stone) {
			target_stone = your_stone;
		} else {
			target_stone = my_stone;
		}
		return target_stone;
	}
	void reverseStone(int x, int y) {
		int target_stone = target();
		int direction_y = y;
		int direction_x = x;
		for (int yi = -1; yi <= 1; yi++) {//-1:左 0:真ん中 1:右
			for (int xi = -1; xi <= 1; xi++) {//-1:下 0:中段 1:上
				if (yi == 0 && xi == 0) {
					continue; //真ん中コンテニュー
				} else if (y + yi < 0 || y + yi > 9) {
					continue; //y版場外コンテニュー
				} else if (x + xi < 0 || x + xi > 9) {
					continue;//x版場外コンテニュー
				}
				do{
					direction_y += yi;
					direction_x += xi;
					
				}while(bood[direction_y][direction_x] == target_stone );
				if (bood[direction_y][direction_x] == attack_stone) {
					do {
						direction_y -= yi;
						direction_x -= xi;
						bood[direction_y][direction_x] = attack_stone;
					} while (x != direction_x || y != direction_y);
				}else{
					direction_x = x;
					direction_y = y;
				}
			}
		}

	}

	int[][] stoneSearch() {
		//おける場所座標が入った2次元配列が返ってくる
		//初期化など
		int target_stone = target();
		int count_stone = 0;
		int[][] xy;
		//ここから検索開始
		for (int y = 1; y < 9; y++) {
			for (int x = 1; x < 9; x++) {
				if (bood[y][x] == attack_stone) {//自分の駒を発見したら
					//検索上下左右斜めに
					for (int yi = -1; yi <= 1; yi++) {//-1:左 0:真ん中 1:右
						for (int xi = -1; xi <= 1; xi++) {//-1:下 0:中段 1:上
							if (yi == 0 && xi == 0) {
								continue; //真ん中コンテニュー
							} else if (y + yi < 1 || y + yi > 8) {
								continue; //y版場外コンテニュー
							} else if (x + xi < 1 || x + xi > 8) {
								continue;//x版場外コンテニュー
							}
							int direction_x = x + xi;
							int direction_y = y + yi;
							while (bood[direction_y][direction_x] == target_stone) {
								direction_x += xi;
								direction_y += yi;
								if (bood[direction_y][direction_x] == 0) {
									count_stone++;
									bood[direction_y][direction_x] = 8;//とりあえず8を入れる
									break;
								}
							}
							
						}
					}
				}
			}
		}
		if(count_stone==0){//おけない場合の処理,0,0に-1を返しておく
			System.out.println("------------------------------------------------------"+count_stone);//デバッグ用
			xy=new int[1][1];
			xy[0][0] = -1;
			return xy;
		}
		
		xy = new int[count_stone][2];//9が入った分(おける場所分の)配列を作る
		for (int iy = 1; iy < 9; iy++) {
			for (int ix = 1; ix < 9; ix++) {
				if (bood[iy][ix] == 8) {//置いた9を見つける
					bood[iy][ix] = 0; //0に戻す
					count_stone--;
					xy[count_stone][0] = ix;
					xy[count_stone][1] = iy;
					if (count_stone == 0) {
						return xy;
					}
				}
			}
		}
		xy = new int[0][0];
		return xy;
	}//ここまで
	int input(int max){
		//オート機能 デバッグ用
		// int num;
		// num = rand.nextInt(max);
		// System.out.println(num+"が入力されました。");
		// return num; 
		
		int num;
		num = sc.nextInt();
		while (num >= max || num<-5){
			print(421);
			num = sc.nextInt();
		}
		return num;
	}
	int bot(int max){
		return rand.nextInt(max);
	}
	
	void print() {//bood
			for (int i = 0; i < 10; i++) {
			if (i == 0) {
				System.out.println("12345678\n||||||||||||");
			}
			System.out.print("-");
			for (int p = 0; p < 10; p++) {
				if (p == 9) {
					System.out.print(bood[i][p]);
					System.out.println("-" + (i));
				} else {
					System.out.print(bood[i][p]);
				}
			}
			if (i == 9) {
				System.out.println("||||||||||||\n  12345678");
			}
		}
		
	}
	void print(int a){//config(表示 多言語化するにはここを変えてください)
		switch(a){
			case 0:
				//エラー
				System.out.println("おける場所がどちらもなかったためゲームを終了します。");
				break;
			case 1:
				System.out.println("あなたの勝ちです");
				break;
			case 2:
				System.out.println("相手の勝ちです");
				break;
			case 3:
				System.out.println("引き分けです");
			case 4:
				System.out.println("あなたの駒は"+my_stone+"です");
				System.out.println("相手の駒は"+your_stone+"です");
				break;
			case 413:
				//要件
				System.out.println("---ルールは以下のとおりです---");
				System.out.println("相手の駒を自分の駒ではさむように置くと\n間にある相手の駒が自分の駒になります。\n多くの駒をとったほうが勝ちです");
				System.out.println("---駒の説明---");
				System.out.println("あなたの駒:"+my_stone+",相手の駒:"+your_stone+",まだ置かれていない駒:0\n壁(ボードの範囲をわかりやすくするために置いています。):9");
				System.out.println("あなたの駒が置ける場所は(横,縦)で表示されます");
				System.out.println("---コマンド---");
				System.out.println("\"-1\"を入力するとルールと駒の説明が出てきます。\n\"-2\"で強制終了します。\n\"-3\"で駒を再カウントします。\n\"-4\"でboodを再表示します。\n\"-5\"で置ける場所を表示します。");
				break;
			case 103:
				//コマンド説明
				System.out.println("\"-1\"を入力するとルールと駒の説明が出てきます。");
				break;
			case 403:
				System.out.println("おける場所がなかったためスキップされました");
				break;
			case 404:
				System.out.print("先攻後攻が決められていません");
				break;
			case 408:
				System.out.println("強制終了します。");
				break;
			case 421:
				System.out.println("不明なコマンドです");
				System.out.print("再度入力してください-->");
				break;
			case 512:
				System.out.print("番号を入力-->");
				break;
			case 901:
				System.out.println("あなたのおける場所は");
				break;
			case 902:
				System.out.print("相手のターンです-->");
				break;
			case 1023:
				System.out.println("続行するには入力してエンターキーを押してください。");
				sc.next();
		}
	}
	void print(int my,int you,int air){
		System.out.println("-----駒数-----");
		System.out.println("自分の駒:" + my);
		System.out.println("相手の駒:" + you);
		System.out.println("まだ置かれていない駒:" + air);
		System.out.println("--------------");
	}
	void exit(){
		sc.close();
	}
}
