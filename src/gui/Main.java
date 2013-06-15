package gui;

import java.util.Scanner;

import client.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Beging");
		Client client = new Client("186.15.92.57", 1234);
		Scanner scan = new Scanner(System.in);
		String message = scan.nextLine();
		while (!message.equals("exit")) {
			client.initClient();
			client.sendMessage(message);
			System.out.println(client.recibeAnswer());
			client.closeClient();
			message = scan.nextLine();
		}
		scan.close();
	}

}
