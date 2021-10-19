package main.java.project;

import java.util.ArrayList;
import java.util.List;

import main.java.project.exception.DelimitadorInvalidoException;

public class AnalysisParser {
    
	private String delimiter;
	
	

	public AnalysisParser(String userDelimiter, List<String> fileLines) throws DelimitadorInvalidoException{
		this.setDelimiter(userDelimiter);
		this.parseFile(fileLines, userDelimiter);
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) throws DelimitadorInvalidoException {
		char charAux = delimiter.charAt(0);
		if(delimiter.length()==1 || (delimiter.length()==2 && charAux == '\\')) {
			this.delimiter = delimiter;
		} else {
			throw new DelimitadorInvalidoException();
		}
	}
	
	public List<List<String>> parseFile(List<String> fileLines, String delimiter){
		List<List<String>> parseList = new ArrayList<List<String>>();
		List<String> evolutionList = new ArrayList<>();
		int evolutionIndex = 0;
		for(String line : fileLines)
        {	
			if(line.substring(0, 3).equals("----")){
				line = String.valueOf(evolutionIndex);
				evolutionList.add(line + delimiter);
				if(evolutionIndex != 0) {
					parseList.add(evolutionList);
				}
				evolutionIndex++;
			}
            evolutionList.add(line + delimiter);
        }
		return parseList;
	}

}
