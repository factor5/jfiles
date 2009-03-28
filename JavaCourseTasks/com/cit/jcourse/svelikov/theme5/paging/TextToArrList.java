/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.paging;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Separates a text file to list of lines.
 * @author  Svilen Velikov
 * @version 02 30.03.07
 */
public class TextToArrList {
	
	/**
	 * Read a text file given as parameter and separates it to
	 * list of lines.
	 * @param path The text file.
	 * @return	   The ArrayList.
	 * @throws IOException
	 */
	public List readTxtFile(String path) throws IOException {
		
		List arr = new ArrayList();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
		return arr;
	}
}
