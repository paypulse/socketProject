package com.test.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Server {
	
	 	//클라이언트 연결 받는 소켓.
		ServerSocket serverSocket;
		// 실제 통신 소켓
		Socket server;
		// 클라이언트 -> 서버로 온 데이터 읽기
		BufferedReader br;
		
		private static final String jsonDataTest = "{\"DEVICEID\":\"00000000001\",\"DEVICETYPE\" :\"P\",\"OPCODE\":\"119\",\"IN24V\":\"23\",\"INB24V\":\"23\",\"FT\":\"11\",\"D1\":\"00\",\"D2\":\"00\",\"D3\":\"00\",\"D4\":\"00\",\"D5\":\"00\",\"D6\":\"00\",\"D7\":\"00\",\"D8\":\"00\",\"D9\":\"00\",\"D10\":\"00\", \"D11\":\"00\",\"D12\":\"11\",\"D13\":\"00\",\"D14\":\"00\",\"D15\":\"00\",\"D16\":\"00\",\"D17\":\"00\",\"D18\":\"00\",\"D19\": \"00\",\"D20\":\"00\",\"D21\":\"00\",\"D22\":\"00\",\"D23\":\"00\",\"D24\":\"00\",\"D25\":\"00\",\"D26\":\"00\",\"D27\":\"00\",\" D28\":\"00\",\"D29\":\"00\",\"D30\":\"00\",\"D31\":\"00\",\"D32\":\"00\",\"D33\":\"00\",\"D34\":\"00\",\"D35\":\"00\",\"D36\":\" 00\",\"D37\":\"00\",\"D38\":\"00\",\"D39\":\"00\",\"D40\":\"00\",\"D41\":\"00\",\"D42\":\"00\",\"D43\":\"00\",\"D44\":\"00\",\" D45\":\"00\",\"D46\":\"00\",\"D47\":\"00\",\"D48\":\"00\",\"D49\":\"00\",\"D50\":\"00\",\"D51\":\"00\",\"D52\":\"00\",\"D53\":\" 00\",\"D54\":\"00\",\"D55\":\"00\",\"D56\":\"00\",\"D57\":\"00\",\"D58\":\"00\",\"D59\":\"00\",\"D60\":\"00\",\"D61\":\"00\",\" D62\":\"00\",\"D63\":\"00\",\"D64\":\"00\",\"D65\":\"00\",\"D66\":\"00\",\"D67\":\"00\",\"D68\":\"00\",\"D69\":\"00\",\"D70\":\" 00\",\"D71\":\"00\",\"D72\":\"00\",\"D73\":\"00\",\"D74\":\"00\",\"D75\":\"00\",\"D76\":\"00\",\"D77\":\"00\",\"D78\":\"00\",\" D79\":\"00\",\"D80\":\"00\"}" + "\r\n";
		
	    @EventListener(ApplicationReadyEvent.class)
	    public void init() {
	    	
	    	System.out.println("1. 서버 소켓 시작.");
	    	
	    	try {
	    		serverSocket = new ServerSocket(9696);
	    		System.out.println("2. 서버 소켓 생성 완료.");
	    		
	    		server = serverSocket.accept();
				SocketAddress clientHost = server.getLocalSocketAddress();
				System.out.println("*********** :" + clientHost);
	    		System.out.println("3. 클라이언트가 연결되었습니다. " + server.getPort());

				ObjectOutputStream outstream = new ObjectOutputStream(server.getOutputStream());


	    		try {
	    			br = new BufferedReader(new InputStreamReader(server.getInputStream()));
	        		System.out.println("4. 서버 버퍼 연결 완료.");
	        		
	        			String msg = br.readLine();
	        			if (msg != null) {
							outstream.writeObject("igotdata");
							outstream.flush();
	        				System.out.println("5. 클라이언트로부터 받은 메세지 출력 : " + msg);
	        			} else {
							outstream.writeObject("igotnodata");
							outstream.flush();
	        				System.out.println("들어온 데이터가 없습니다.");
	        			}
				} catch (Exception e) {
					e.printStackTrace();
					outstream.writeObject(e.getMessage());
					outstream.flush();
				} finally {
					server.close();
				}
	    	} catch (Exception e) {
				System.out.println("서버 소켓 에러 발생 : " + e.getMessage());
			} 
	    }
	    
}
