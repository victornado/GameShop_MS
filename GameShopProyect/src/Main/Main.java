package Main;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.SwingUtilities;

import Presentacion.Controller.Controller;
import Presentacion.View.InfoDB;

public class Main {
	
	public static final String applicationName = "GameShop";
	public static String database = "gameshopapplication";
	public static String user = "root";
	public static String password = "1234";


	public static void main(String[] args) {

		try
		{
			//Abrimos el archivo de audio
			AudioInputStream audioInputStream = 
					AudioSystem.getAudioInputStream(new File("resources/gameshop_intro.wav"));/*new File("GameShopProyect" + 
			System.getProperty("file.separator") + "resources/gameshop_intro.wav").getAbsoluteFile());*/
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip sound = (Clip)AudioSystem.getLine(info);
			sound.open(audioInputStream);
			
			//Inicializamos el programa, y ejecutamos el sonido
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					//new InfoDB();
					Controller.getInstance();
					sound.start();
				}
			});
			
		}
		catch(Exception e)
		{
			System.err.println("Error when loading the app");
		}
		
		
	}

}