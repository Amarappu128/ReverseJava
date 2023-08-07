import java.util.Scanner;

//23jn0114
public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Reverse1 game = new Reverse1() ;
		game.my_stone = 1; 
		game.your_stone = 2;
		game.attack_stone = game.my_stone;//先行
		System.out.println("あなたの駒は"+game.my_stone+"です");
		System.out.println("相手の駒は"+game.your_stone+"です");
		int x;
		do {
			game.print();
			x = sc.nextInt();
			while(game.put_stone(x)) {
				//駒がおけなかった場合のループ
				x = sc.nextInt(); 
			}
			game.change_attack();
		}while(game.stone_chack());
		
}
}
