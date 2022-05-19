/*package in.ashokit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import in.ashokit.entity.UserAccountEnity;
import in.ashokit.service.UserMgmtServiceImpl;

public class Demo {
	public static void main(String[] args) {
		System.out.println(Demo.generateRandomPassword());
		Demo.readText("UNLOCK-ACCOUNT-EMAIL-BODY.txt");//FORGOT-PASSWORD.txt
		
		 * UserMgmtServiceImpl mgmtServiceImpl = new UserMgmtServiceImpl();
		 * mgmtServiceImpl.readDynamicTextEmailBody("UNLOCK-ACCOUNT-EMAIL-BODY.txt",
		 * null);
		 
	}

	public static String generateRandomPassword() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	public static String readText(String fileName) {
		String body = null;
		try {

			StringBuffer stringBuffer = new StringBuffer();

			FileReader reader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = bufferedReader.readLine();
			}
			body = stringBuffer.toString();

			System.out.println(body);
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
*/