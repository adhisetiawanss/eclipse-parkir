package paris;

import java.io.*;
import java.net.*;
import java.util.*;
public class ClientParis2
{
	private static InetAddress host;
	private static final int PORT = 1;
	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getByName("IP");
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID tidak ditemukan!\n");
			System.exit(1);
		}
		sendMessages();
	}
	private static void sendMessages()
	{
		Socket socket = null;
		try
		{
			socket = new Socket(host,PORT);
			Scanner networkInput =
					new Scanner(socket.getInputStream());
			PrintWriter networkOutput =
					new PrintWriter(
							socket.getOutputStream(),true);
			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			
			System.out.print(
					"Masuk Sebagai Gate (IN/OUT) : ");
			message = userEntry.nextLine();
			networkOutput.println(message.toUpperCase());
			response = networkInput.nextLine();
			System.out.println(response);
			
			do
			{
				System.out.print(
						"Tuliskan NOPOL Kendaraan : ");
				message = userEntry.nextLine();
				networkOutput.println(message.toUpperCase());
				response = networkInput.nextLine();
				System.out.println(response);
			}while (!message.equalsIgnoreCase("QUIT"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println(
						"\nPenutupan Koneksi...");
				socket.close();
			}
			catch(IOException ioEx)
			{
				System.out.println(
						"Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}