

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BackUpValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String date;
	double temp;
	double pres;
	int rev;
	ArrayList<BackUpValue> list = new ArrayList<>();

	@Override
	public String toString() {
		return "BackUpValue [date=" + date + ", temp=" + temp + ", pres=" + pres + ", rev=" + rev + "]";
	}

	public BackUpValue(String date, double temp, double pres, int rev) {
		super();
		this.date = date;
		this.temp = temp;
		this.pres = pres;
		this.rev = rev;
	}

	class CounterCommand implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {

					BackUpValue eins = new BackUpValue("1231", Math.random(), Math.random(),
							(int) (Math.random() * 1000));
					System.out.println("läuft durch");
					
					synchronized (list) {
						list.add(eins);
						writeFile(list);
					}

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class ReaderThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					synchronized (list) {
						System.out.println(list);

					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readFile();
			}
		}

	}

	public void writeFile(ArrayList<BackUpValue> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Backup_1.backup"));
			synchronized (list) {
				oos.writeObject(list);

			}
			// for (int i = 0; i < list.size(); i++) {
			// oos.writeObject(list.get(i));
			// }
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}

	}

	public void readFile() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Backup_1.backup"));
			ArrayList<BackUpValue> bV = (ArrayList<BackUpValue>) ois.readObject();
			System.out.println(bV);
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void runThread() {
		Thread th = new Thread(new CounterCommand());
		Thread th2 = new Thread(new ReaderThread());
		th.start();
		th2.start();
	}

}
