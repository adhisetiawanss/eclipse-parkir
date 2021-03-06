package paris;


import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import paris.motor;

public class ServerParis
{
	private static ServerSocket serverSocket;
	private static final int PORT = 1;
	
	public static void main(String[] args)
			throws IOException
			{
		
		try
		{
			serverSocket = new ServerSocket(PORT);
			System.out.println("MULTI CLIENT SERVER pada PORT:" + PORT);
		}
		catch (IOException ioEx)
		{
			System.out.println("\nTidak Dapat Membuka Port!");
			System.exit(1);
		}
		do
		{
			//Tunggu Koneksi
			System.out.println("Server Siap...");
			Socket client = serverSocket.accept();
			InetAddress ip = client.getInetAddress();
			System.out.println("\nClient Baru masuk..\n"
							+"Dengan IP : "+ip.toString()+ 
							"\nNama : "+ip.getHostName().toString()+"\n");
			//Create a thread to handle communication with
			//this client and pass the constructor for this
			//thread a reference to the relevant socket...
			ClientHandler handler =
					new ClientHandler(client);
			handler.start();//As usual, method calls run .
		}while (true);
			}
}
class ClientHandler extends Thread
{
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	private int ke = 0;
	private String gate;
	
	public ClientHandler(Socket socket)
	{
		//Set up reference to associated socket...
		client = socket;
		try
		{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(
					client.getOutputStream(),true);
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	public void run()
	{
		motor motor = new motor();
		String received = null;
		//biar tahu sebagia apa dia masuk
		received = input.nextLine();
		gate  = received;
		output.println("Selamat Datang di Server Parkir .. Anda Masuk Sebagai Gate : "+gate);
		if (gate.equalsIgnoreCase("IN"))
		{
			do
			{
					//menerima pesan dari client
					//melalui socket input stream..
					received = input.nextLine();
					if (!received.equalsIgnoreCase("CETAK") && !received.equalsIgnoreCase("QUIT"))
					{
						//memasukkan data ke array
						motor.masukkedatabase(received, ke, gate);
						ke++;
						
						
						//menampilkan yang tersimpan pada server (kalau tidak digunakan tinggal di comment)
						System.out.println(motor.getKet());
						output.println(motor.getKet());
					}
					else if (!received.equalsIgnoreCase("QUIT"))
					{
						motor.lihatData();
						output.println("data tercetak di server!");
					}
					else
					{
						output.println("Terimakasih");
					}
					
					//ulangi sampai QUIT
			}while (!received.equalsIgnoreCase("QUIT"));
		} 
		else if (gate.equalsIgnoreCase("OUT"))
		{
			do
			{
					//menerima pesan dari client
					//melalui socket input stream..
					received = input.nextLine();
					if (!received.equalsIgnoreCase("CETAK") && !received.equalsIgnoreCase("QUIT"))
					{
						//memasukkan data ke array
						motor.masukkedatabase(received, ke, gate);
						ke++;
						
						
						//menampilkan yang tersimpan pada server (kalau tidak digunakan tinggal di comment)
						System.out.println(motor.getKet());
						output.println(motor.getKet());
					}
					else if (!received.equalsIgnoreCase("QUIT"))
					{
						motor.lihatData();
						output.println("data tercetak di server!");
					}
					else
					{
						output.println("Terimakasih");
					}
					
					//ulangi sampai QUIT
			}while (!received.equalsIgnoreCase("QUIT"));
		}
		
		try
		{
			if (client!=null)
			{
				System.out.println(
						"Menutup koneksi...sukses..");
				client.close();
			}
		}
		catch(IOException ioEx)
		{
			System.out.println("Gagal menutup koneksi!");
		}
	}
}