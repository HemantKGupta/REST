import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Main {
	
	public static long filePointer;

	public static void main(String[] args) {
		try {
			loadFile();
			watchDir("/home/hemantg/Desktop",filePointer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void watchDir(String dir, long filePointer) throws IOException,
			InterruptedException {
		WatchService service = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(dir);
		path.register(service, StandardWatchEventKinds.ENTRY_MODIFY);
		while (true) {
			WatchKey key = service.take();
			for (WatchEvent event : key.pollEvents()) {
				System.out.println(event.kind());// + ": " + event.context());
				printChange();
			}
			boolean valid = key.reset();
			if (!valid) {
				break; 
			}
		}
	}

	public static void printChange() {

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("/home/hemantg/Desktop/test.txt", "r");
			raf.seek(filePointer);

			String line = null;
			while ((line = raf.readLine()) != null) {
				System.out.println(line);
			}
			filePointer = raf.getFilePointer();
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadFile() {

		RandomAccessFile raf = null;
		//long filePointer = 0;
		try {
			raf = new RandomAccessFile("/home/hemantg/Desktop/test.txt", "r");
			raf.seek(0);

			String line = null;
			while ((line = raf.readLine()) != null) {
				System.out.println(line);
			}
			filePointer = raf.getFilePointer();
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		//return filePointer;
	}

}
