package com.test.api.controller;

import java.io.*;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.api.common.CommonResponse;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class SocketController {
	
	//private static final String jsonDataTest = "{\"DEVICEID\":\"00000000001\",\"DEVICETYPE\" :\"P\",\"OPCODE\":\"119\",\"IN24V\":\"23\",\"INB24V\":\"23\",\"FT\":\"11\",\"D1\":\"00\",\"D2\":\"00\",\"D3\":\"00\",\"D4\":\"00\",\"D5\":\"00\",\"D6\":\"00\",\"D7\":\"00\",\"D8\":\"00\",\"D9\":\"00\",\"D10\":\"00\", \"D11\":\"00\",\"D12\":\"11\",\"D13\":\"00\",\"D14\":\"00\",\"D15\":\"00\",\"D16\":\"00\",\"D17\":\"00\",\"D18\":\"00\",\"D19\": \"00\",\"D20\":\"00\",\"D21\":\"00\",\"D22\":\"00\",\"D23\":\"00\",\"D24\":\"00\",\"D25\":\"00\",\"D26\":\"00\",\"D27\":\"00\",\" D28\":\"00\",\"D29\":\"00\",\"D30\":\"00\",\"D31\":\"00\",\"D32\":\"00\",\"D33\":\"00\",\"D34\":\"00\",\"D35\":\"00\",\"D36\":\" 00\",\"D37\":\"00\",\"D38\":\"00\",\"D39\":\"00\",\"D40\":\"00\",\"D41\":\"00\",\"D42\":\"00\",\"D43\":\"00\",\"D44\":\"00\",\" D45\":\"00\",\"D46\":\"00\",\"D47\":\"00\",\"D48\":\"00\",\"D49\":\"00\",\"D50\":\"00\",\"D51\":\"00\",\"D52\":\"00\",\"D53\":\" 00\",\"D54\":\"00\",\"D55\":\"00\",\"D56\":\"00\",\"D57\":\"00\",\"D58\":\"00\",\"D59\":\"00\",\"D60\":\"00\",\"D61\":\"00\",\" D62\":\"00\",\"D63\":\"00\",\"D64\":\"00\",\"D65\":\"00\",\"D66\":\"00\",\"D67\":\"00\",\"D68\":\"00\",\"D69\":\"00\",\"D70\":\" 00\",\"D71\":\"00\",\"D72\":\"00\",\"D73\":\"00\",\"D74\":\"00\",\"D75\":\"00\",\"D76\":\"00\",\"D77\":\"00\",\"D78\":\"00\",\" D79\":\"00\",\"D80\":\"00\"}\r\n";
	private static final String jsonDataTest = "SOCKET";


	public static Object getData = "";

	private PrintWriter pw;
	private BufferedReader br;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");
		
		return mav;
	}
	 
	@RequestMapping(value = "/connect", method = {RequestMethod.GET}) 
	public ResponseEntity<CommonResponse> connect(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("[client socket] 접속 test ");

		  Socket socket = null;
		  
		try{
			socket = new Socket();

			socket.connect(new InetSocketAddress("192.168.0.10", 9696));
			System.out.println("[client] server와 연결 성공 ");
			pw = new PrintWriter(socket.getOutputStream());
			pw.println(jsonDataTest);
			pw.flush();



			//서버로 부터 받음
			ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());

			getData = instream.readObject();

			socket.close();

			return ResponseEntity.ok( CommonResponse.builder()
					.data(getData)
					.status("SUCCESS")
					.msg("출력")
					.build());


		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok( CommonResponse.builder()
					.data("{}")
					.status("fail")
					.msg(e.getMessage())
					.build());
		} finally {
                try {
                	//if(socket != null) { socket.close(); }
        		//	if(br != null) { br.close(); }
        	       // if(pw != null) { pw.close(); }
                } catch(Exception e) {
					return ResponseEntity.ok( CommonResponse.builder()
							.data("{}")
							.status("fail")
							.msg(e.getMessage())
							.build());
                }
            }


	}
	
}
