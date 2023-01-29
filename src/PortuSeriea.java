import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class PortuSeriea {

	SerialPort activePort;
	SerialPort[] ports = SerialPort.getCommPorts();

	public void showAllPort() {

		int i = 0;

		for (SerialPort port : ports) {

			System.out.print(i + ". " + port.getDescriptivePortName() + " ");
			System.out.println(port.getSystemPortName());

			i++;

		}

	}

	public void setPort(int portIndex) {

		activePort = ports[portIndex];

		if (activePort.openPort()){
			System.out.println(activePort.getSystemPortName() + " port opened.");
		}

		activePort.addDataListener(new SerialPortDataListener() {

			@Override
			public void serialEvent(SerialPortEvent event) {

				int size = event.getSerialPort().bytesAvailable();
				byte[] buffer = new byte[size];
				
				event.getSerialPort().readBytes(buffer, size);

				for (byte b : buffer){
					System.out.print((char) b);
				}

			}

			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}

		});

	}

	public void start() {

		showAllPort();

		Scanner reader = new Scanner(System.in);
		System.out.print("Port? ");

		int p = reader.nextInt();
		reader.nextLine();

		setPort(p);
		idatzi(reader);

		reader.close();

	}

	private void idatzi(Scanner reader) {

		String p;

		System.out.println("Sartu 0 amaitzeko ");

		do {

			System.out.println("Idatzi bidaltzeko mezua: ");

			p = reader.nextLine();

			activePort.writeBytes(p.getBytes(), p.getBytes().length);

		} while (p.compareTo("0") != 0);

	}

	// public static void main(String[] args) {

	// 	PortuSeriea mainClass = new PortuSeriea();

	// 	mainClass.start();

	// }

}
