import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double contrast=0;
		Image img = new Image();
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("Introduceti calea spre imaginea sursa (ex: C:/img.bmp )");
			img.setPath(scan.nextLine());
			img.openPath();
			if(img.getImg()==null)
				System.out.println("Adresa invalida! Incercati in nou");
		}
		while(img.getImg()==null);
		do{
			System.out.println("Introduceti factorul de contrast (ex: 0,2 / 15)");
			System.out.println("Culoare noua=Culoare veche*factor de contrast");
			contrast = scan.nextDouble();
			img.adjust(contrast);
			 
		}
		while(contrast < 0 && contrast > 100);	
		
	}

}
