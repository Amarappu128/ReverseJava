//import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	Reverse2 game = new Reverse2() ;
	game.my_stone = 1; 
	game.your_stone = 2;
	game.attack_stone = game.my_stone;//先行
	
	game.print(4);
	game.print(103);
	int roop_count=0; //置けなかったらカウント
	do{
		if (game.attack_stone == game.my_stone) {
			//自分のターン
			int[][] stone = game.stoneSearch();
			if(stone[0][0] == -1){//おけなかったら0,0に-1が返される
				game.print(403);
				game.changeAttack();
				roop_count = 1;
				continue;
			}
			game.print();
			game.print(901);//あなたのおける場所は
			for (int i = 0; i < stone.length; i++) {//置ける場所表示
				System.out.print(i+":"+"("+stone[i][0]+","+stone[i][1]+"),");
			}
			System.out.println();//改行
			game.print(512);//入力してください
			int input = game.input(stone.length); 
			if(input == -1){//helpが入力された
				game.print(413);
				game.print(1023);
				continue;
			}else if(input == -2){//強制終了が入力された
				game.print(408);
				break;
			}else if(input==-3){//駒数が見たい
				int[] a = game.stoneChack();
				game.print(a[0],a[1],a[2]);
				game.print(1023);
				continue;
			}else if(input==-4){//今の状態が見たい
				game.print();
				continue;
			}else if(input==-5){//置ける場所表示 continueしない 再入力
				for (int i = 0; i < stone.length; i++) {
					System.out.print(i+":"+"("+(stone[i][0])+","+(stone[i][1])+"),");
				}
				game.print(512);
				input = game.input(stone.length); 
			}
			game.bood[stone[input][1]][stone[input][0]] = game.attack_stone; 
			game.reverseStone(stone[input][0],stone[input][1]);//x,y
			game.changeAttack();
			roop_count = game.Count();

		} else if (game.attack_stone == game.your_stone) {//相手のターン
			int[][] stone = game.stoneSearch();
			if(stone[0][0] == -1){//おけなかったら0,0に-1が返される
				game.print(403);
				game.changeAttack();
				roop_count++;
				continue;
			}
			
			int input = game.bot(stone.length);
			game.print(902);
			System.out.println("("+(stone[input][0])+","+(stone[input][1])+")");
			game.reverseStone(stone[input][0],stone[input][1]);
			game.changeAttack();
			
			roop_count = game.Count();
		} else {
			roop_count++;
		}
	}while(roop_count < 2);
	game.exit();
	
	}
}

