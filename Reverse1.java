public class Reverse1 {

	int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }; //座標
	int[] bood = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; //基盤
	int my_stone;
	int your_stone;
	int attack_stone;
	
	
	boolean stone_chack() {
		//カウントの初期化
		int my_stone_count = 0;
		int your_stone_count = 0;
		int air_stone_count = 0;
		for (int i = 0; i < 10; i++) {
			if (bood[i] == my_stone) {
				my_stone_count++;
			} else if (bood[i] == your_stone) {
				your_stone_count++;
			} else {
				air_stone_count++;
			}
		}
		System.out.println("-----駒数-----");
		System.out.println("自分の駒:" + my_stone_count);
		System.out.println("相手の駒:" + your_stone_count);
		System.out.println("まだ置かれていない駒:" + air_stone_count);
		System.out.println("--------------");
		
		if (air_stone_count == 0) {
			if (my_stone_count > your_stone_count) {
				
				System.out.println("あなたの勝ちです");
			} else {
				System.out.println("相手の勝ちです");
			}
			return false;
		} else {
			
			return true;
		}
	}
	void change_attack() {
		if(attack_stone == my_stone) {
			attack_stone = your_stone;
		}else {
			attack_stone = my_stone;
		}
	}
	void reverse_stone(int x) {
		//ターゲット定義
		int target_stone;
		if(attack_stone == my_stone) {
			target_stone = your_stone;
		}else {
			target_stone = my_stone;
		}
		//置き換え検索
		for(int i=-1;i<=1;i++) {
			if(i==0) {
				continue; //真ん中コンテニュー
			}else if(x+i<0 || x+i>9) {
				continue; //場外コンテニュー
			}
			int direction_x = x+i;
			while(bood[direction_x] == target_stone) {
				direction_x += i;
				if(x+i<0 || x+i>9) {
					break; //場外のため抜ける
			}
			}
			if(bood[direction_x] == attack_stone ) {
				do {
					direction_x-=i;//-iすることでさっきとは逆になる
					bood[direction_x] = attack_stone;
				}while(x!=direction_x);
			}
			//i=-1(左側)に石があってひっくり返ってもi=1にひっくり返せる石があるかもしれない
		}
	}
	
	boolean put_stone(int x) {
		if (x >= 10 || x < 0) {
			System.out.println("0-9の間で入力して下さい");
			if (attack_stone == my_stone) {
				System.out.print("あなたのターンです-->");
			} else if (attack_stone == your_stone) {
				System.out.print("相手のターンです-->");
			} 
			return true;
		}
		if (bood[x] != 0) {
			System.out.println("駒がおかれています。");
			if (attack_stone == my_stone) {
				System.out.print("あなたのターンです-->");
			} else if (attack_stone == your_stone) {
				System.out.print("相手のターンです-->");
			} 
			return true;
		} else {
			bood[x] = attack_stone;
			reverse_stone(x);//ひっくり返す
			return false;

		}
	}

	void print() {
		for (int i = 0; i < 10; i++) {

			if (i == 9) {
				System.out.println(num[i]);
			} else {
				System.out.print(num[i]);
			}
		}
		for (int i = 0; i < 10; i++) {
			if (i == 9) {
				System.out.println(bood[i]);
			} else {
				System.out.print(bood[i]);
			}
		}

		if (attack_stone == my_stone) {
			System.out.print("あなたのターンです-->");
		} else if (attack_stone == your_stone) {
			System.out.print("相手のターンです-->");
		} else {
			System.out.print("先攻後攻が決められていません");
		}

	}
}
