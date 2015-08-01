import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Main {	
	private static String doMagic(String filePath) {
		String finalProduct = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "", sum = "";
			while ((line = br.readLine()) != null) {
				sum += line + "\n";
			}
			br.close();
			
			String newLineChar = System.getProperty("line.separator");

			String regex = "([0-9]+)(\n[0-9]+:[0-9]+:[0-9]+,[0-9]+ --> [0-9]+:[0-9]+:[0-9]+,[0-9]+\n(.+?\n)+)";
			Matcher m1 = Pattern.compile(regex).matcher(sum);
			int j = 1;
			while (m1.find()) {
				String temp = j + m1.group(2) + "\n";
				finalProduct += temp;
				++j;
			}
			finalProduct = finalProduct.replaceAll("\n", newLineChar);
			//System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalProduct;
	}
	private static void writeFile(String path, String s) {
		try {
			BufferedWriter br = Files.newBufferedWriter(Paths.get(path));
			br.write(s);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		System.setProperty("file.encoding","UTF-8");
		Field charset = Charset.class.getDeclaredField("defaultCharset");
		charset.setAccessible(true);
		charset.set(null,null);
		writeFile(args[1], doMagic(args[0]));

		//writeFile("C:/Users/Vootele/Downloads/BetterCallSaul6.txt", doMagic("C:/Users/Vootele/Downloads/BetterCallSaul.txt"));
	}
}
/*
 * 10
	00:02:55,094 --> 00:02:57,478
	<i>On see vast läänelik detail.</i>
 */