import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import javax.imageio.*;
public class Image {
	private String path;
	private BufferedImage img=null;
	int height;
    int width;
    
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public void openPath() {
		try{
			img=ImageIO.read(new File(path));
			height = img.getHeight();
		    width = img.getWidth();
	
		}
		catch(IOException e){
			System.out.println("Adresa invalida");
		}
		
	}
	public void adjust(double scaleFactor){
		BufferedImage aux=null;
		String pathDestinatie;
		String numeFisier;
		File file = null;
		int i,j;
		aux=img;
		int color, red, green, blue;
		for(i=0;i<height;i++) //pentru fiecare pixel
			for(j=0;j<width;j++){
				color=aux.getRGB(j,i); //primesc RGB
				blue = color & 0xff;
				green = (color & 0xff00) >> 8;
			    red = (color & 0xff0000) >> 16;
			    
			    blue=trunc(scaleFactor*blue*1.0); //aplic factorul de contras
			    red=trunc(scaleFactor*red*1.0);  //culoare_noua=culoare_veche*factor_de_contrast
			    green=trunc(scaleFactor*green*1.0); //daca noua culoare este >255 sau <0 ea va fi trunchiata
													//la 255 respectiv la 0
			    color = (red << 16 | green << 8 | blue); //refac culoarea din componentele RGB
			    aux.setRGB(j, i, color); //setez culoarea
			}
				
		System.out.println("Introduceti calea imaginii rezultat: (ex: C:/ )");
		Scanner scan = new Scanner(System.in);
		pathDestinatie=scan.nextLine();
		System.out.println("Introduceti numele imaginii rezultat: (ex: rez )");
			
		numeFisier=scan.nextLine()+".bmp";
		long startTime = System.currentTimeMillis();	//creez o variabila care contine timpul de dinaintea procesarii
		file = new File(pathDestinatie, numeFisier);
		try{
			ImageIO.write(aux, "bmp", file);
			System.out.println("Succes!");
			long stopTime = System.currentTimeMillis(); //timpul de dupa procesare
			long elapsedTime = stopTime - startTime; //durata procesarii este diferenta celor 2 timpi
			System.out.println("Transformare efectuata in "+elapsedTime + "ms");
		}
		catch(IOException e){
			System.out.println("Adresa invalida");
		}
		
	}
	private int trunc(double d) {
		if(d<0) return 0;
		if(d>255)return 255;
		return (int)d;
	}
	
	
}
