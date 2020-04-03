package com.word.service;

import javax.annotation.Resource;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordSearchServiceResource {
	@Resource
	private WordDao wordDao;
	
	
	
	public WordSearchServiceResource() {
	}

	public WordSearchServiceResource(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	public WordSet searchWord(String wordKey) {
		if(verify(wordKey)) {
			return wordDao.select(wordKey);
		} else {
			System.out.println("WordKey information is available.");
		}
		
		return null;
	}
	@Resource
	public boolean verify(String wordKey){
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet != null ? true : false;
	}
	
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
}