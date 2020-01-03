package com.sonic.IO;

import java.io.*;
import java.net.URL;

/**
 * @author Sonic
 */
public class ConvertTest02 {

	public static void main(String[] args) {
		try(final BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new URL("http://www.baidu.com").openStream(), "UTF-8"));
			final BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("baidu.html"), "UTF-8"))) {

			String msg;
			while ((msg = br.readLine()) != null) {
				System.out.print(msg);
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
