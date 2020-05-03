package com.example.producer.ingress;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
	private static final String HEADER = "TRANSACTION_ID,CUSTOMER_ID,TIME,PRODUCT_ID,COST";


	
	private FileUtil() {
		// TODO Auto-generated constructor stub
	}
	public static List<File> scanDirecotry(String dirName, String fileNameExtension) {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
			log.warn("Ingress direcotry not found, createing the direcotry");
			return Collections.emptyList();
		}
		return Arrays.stream(dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(fileNameExtension.toLowerCase());
			}
		})).filter(File::isFile).collect(Collectors.toList());
	}
	
	public static boolean isHeader(String line) {
		return line.equals(HEADER);
	}
	public static boolean isValidRecord(String line) {
		//TODO validation to be added
		return true;
	}
}
