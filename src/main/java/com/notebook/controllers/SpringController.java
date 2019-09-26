package com.notebook.controllers;

/**
 * @author jalals
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notebook.model.CommandRequest;
import com.notebook.model.CommandResponse;

@RestController
public class SpringController {
	
	//path of python.exe file
	@Value("${python_exe_path}")
	private String pythonExePath;
	
	//path of the command file to fire
	@Value("${python_temp_file_path}")
	private String pythonTempFilePath;

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public CommandResponse execute(@RequestBody CommandRequest request, HttpSession httpSession) {

		System.out.println(pythonExePath);
		System.out.println(pythonTempFilePath);
		CommandResponse response = new CommandResponse();
		try {
			File file = new File(pythonTempFilePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			String interPreter = "";
			String actualCommand = "";
			String sessionId = request.getSessionID() != null ? request.getSessionID() : httpSession.getId();
			
			String command = request.getCode();
			if (command != null && !command.isEmpty() && command.contains(" ")) {
				String[] strings = command.split(" ", 2);
				if (strings != null && strings.length== 2 && strings[0].contains("%")) {
					interPreter = strings[0].replace("%", "");
					actualCommand = strings[1];
				}
			}
			System.out.println(interPreter);
			System.out.println(actualCommand);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(actualCommand);
			out.close();
			
			ProcessBuilder pb = new ProcessBuilder(pythonExePath, pythonTempFilePath);
			Process p = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String result = in.readLine();
			response.setResult(result);
			response.setSessionID(sessionId);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping("/test")
	public String test() {
		return "Success...";
	}

}
