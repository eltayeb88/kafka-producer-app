package com.example.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "file-reader")
public class FileReaderConfig {

	private String delimiter = ",";
	private String directoryPath = "./input";
	private String fileNameExtension = ".csv";
	private String archiveDir = "./archive";
	private Integer maxFilesToProcess = 1;
	private Integer RandowWaitMilliUpperLimit = 5000;

	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * @return the directoryPath
	 */
	public String getDirectoryPath() {
		return directoryPath;
	}

	/**
	 * @param directoryPath the directoryPath to set
	 */
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}


	/**
	 * @return the archiveDir
	 */
	public String getArchiveDir() {
		return archiveDir;
	}

	/**
	 * @param archiveDir the archiveDir to set
	 */
	public void setArchiveDir(String archiveDir) {
		this.archiveDir = archiveDir;
	}

	public String getFileNameExtension() {
		return fileNameExtension;
	}

	public void setFileNameExtension(String fileNameExtension) {
		this.fileNameExtension = fileNameExtension;
	}

	public Integer getMaxFilesToProcess() {
		return maxFilesToProcess;
	}

	public void setMaxFilesToProcess(Integer maxFilesToProcess) {
		this.maxFilesToProcess = maxFilesToProcess;
	}

	public Integer getRandowWaitMilliUpperLimit() {
		return RandowWaitMilliUpperLimit;
	}

	public void setRandowWaitMilliUpperLimit(Integer randowWaitMilliUpperLimit) {
		RandowWaitMilliUpperLimit = randowWaitMilliUpperLimit;
	}

}
