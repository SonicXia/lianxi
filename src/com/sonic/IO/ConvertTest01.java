package com.sonic.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Sonic
 */
public class ConvertTest01 {

	public static void main(String[] args) {
		try(final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			String msg = "";
			while (!msg.equals("exit")) {
				msg = br.readLine();
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
