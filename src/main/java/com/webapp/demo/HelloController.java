package com.webapp.demo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class HelloController {
	
	@Value("classpath:static/*")
	private Resource[] files;

	@GetMapping("/{textId}")
	public String index(@PathVariable String textId, Model model) {
		//String file = "./src/main/resources/static/test.txt";
		try{
			//String data = readFileAsString(file);
			int numOfFiles=0;
			for(int i=0;i<files.length;i++){
				//System.out.println(files[i].getFilename());
				numOfFiles+=1;
			}
			int intTextId = Integer.parseInt(textId);
			String filepath = "";
			//System.out.println(files[1].getFilename());
			if(intTextId<=numOfFiles&&intTextId>0){
				filepath = "/static/"+files[intTextId-1].getFilename();
				//System.out.println(files[intTextId-1].getFilename());
			}
			else{
				filepath = "/static/"+files[0].getFilename();
			}
			
			String data = "";
			InputStream in = getClass().getResourceAsStream(filepath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String st = "";
			//Scanner sc = new Scanner(file1);
			//StringBuffer sb = new StringBuffer();
			while ((st=reader.readLine())!=null)
				//System.out.println(sc.nextLine());
				//sb.append(sc.nextLine()+"\n");
				data+=st+"\r\n";
			//sc.close();
			List<Links> links = new ArrayList<Links>();
			for(int i=0;i<numOfFiles;i++){
				links.add(new Links(i+1,files[i].getFilename()));
			}
			//System.out.println(links.toString());
			model.addAttribute("text", data);
			model.addAttribute("links", links);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}

		
		return "index";
	}
	@GetMapping("/")
	public String home(Model model) {
		//String file = "./src/main/resources/static/test.txt";
		try{
			//String data = readFileAsString(file);
			int numOfFiles=0;
			for(int i=0;i<files.length;i++){
				//System.out.println(files[i].getFilename());
				numOfFiles+=1;
			}
			
			List<Links> links = new ArrayList<Links>();
			for(int i=0;i<numOfFiles;i++){
				links.add(new Links(i+1,files[i].getFilename()));
			}
			model.addAttribute("links", links);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}

		
		return "index";
	}
}