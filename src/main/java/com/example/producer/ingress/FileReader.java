package com.example.producer.ingress;

import static com.example.producer.Util.getRandomNumber;
import static com.example.producer.ingress.FileUtil.isHeader;
import static com.example.producer.ingress.FileUtil.isValidRecord;
import static com.example.producer.ingress.FileUtil.scanDirecotry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.producer.Producer;
import com.example.producer.config.FileReaderConfig;
import com.example.producer.config.KafkaProducerConfig;

@Component
public class FileReader {

	private static final Logger log = LoggerFactory.getLogger(FileReader.class);

	@Autowired
	private Producer<String, String> producer;

	@Autowired
	private FileReaderConfig fileReaderConfig;

	@Autowired
	private KafkaProducerConfig kafkaProducerConfig;

	@Scheduled(fixedDelayString = "${file-reader.job.fixed-delay:1000}")
	public void readFile() {
		log.info("Reading File Started");
		List<File> files = scanDirecotry(fileReaderConfig.getDirectoryPath(), fileReaderConfig.getFileNameExtension());
		if (files.isEmpty()) {
			return;
		}
		// Process files one by one not parallel
		int processedFilesCount = 0;
		while (processedFilesCount < fileReaderConfig.getMaxFilesToProcess()) {
			File file = files.remove(0);

			try (Scanner sc = new Scanner(file)) {
				while (sc.hasNext()) {
					String line = sc.nextLine();
					if (isHeader(line)) {
						continue;
					}
					if (!isValidRecord(line)) {
						continue;
					}
					log.debug("Writing to kafka record::{}",line);
					producer.send(kafkaProducerConfig.getTopicName(), null, line);
					try {
						log.debug("Sleeping for a random time");
						Thread.sleep((long) getRandomNumber(fileReaderConfig.getRandowWaitMilliUpperLimit()));
						log.debug("Back from Sleep");
					} catch (InterruptedException e) {
						log.error("Thread InterruptedException", e);
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				log.error("Error reading file:{}", file.getName(), e);
			}
			archiveFile(file);
			processedFilesCount++;
		}

	}

	public void archiveFile(File file) {
		log.debug("moving file: {} to archive",file.getName());
		File archiveDir = new File(fileReaderConfig.getArchiveDir());
		if (!archiveDir.exists() && !archiveDir.mkdirs()) {
			log.error("Couldn't create archive direcotry: {}",archiveDir.getAbsoluteFile());
			return;
		}
		File target = new File(archiveDir, file.getName());
		file.renameTo(target);
	}

}
