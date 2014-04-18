package com.hkg.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.websocket.Session;

public class WatchServiceUtility {
	
	public static long filePointer;
	
	public static String loadFile() {

		RandomAccessFile raf = null;
		StringBuffer sb = new StringBuffer();
		try {
			raf = new RandomAccessFile("/home/hemantg/Desktop/testdir/test.txt", "r");
			raf.seek(0);

			String line = null;
			while ((line = raf.readLine()) != null) {
				System.out.println(line);
				sb.append("\n");
				sb.append(line);
			}
			filePointer = raf.getFilePointer();
			System.out.println("filePointer: "+filePointer);
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	@SuppressWarnings("unchecked")
	public static void watchDir(String dir, Session session)
			throws IOException, InterruptedException {
		WatchService service = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(dir);
		path.register(service, StandardWatchEventKinds.ENTRY_MODIFY);
		while (true) {
			WatchKey key = service.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
		        Path filename = ev.context();
				System.out.println("kind: "+event.kind()+" and filename: "+filename);
				session.getBasicRemote().sendText(printChange());				
			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}

	public static String printChange() {
		System.out.println("Executing printchange.");
		RandomAccessFile raf = null;
		StringBuffer sb = new StringBuffer();
		try {
			raf = new RandomAccessFile("/home/hemantg/Desktop/testdir/test.txt", "r");
			raf.seek(filePointer);
			System.out.println("Reading from filePointer: "+filePointer );
			String line = null;
						
			while ((line = raf.readLine()) != null) {
				System.out.println(line);
				sb.append("\n");
				sb.append(line);
			}
			filePointer = raf.getFilePointer();
			System.out.println("Finished reading till filePointer: "+filePointer );
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}


}
