package com.word.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterServiceResource {

	@Resource
	private WordDao wordDao;
 
	//property나 메서드에 Resource를 쓰려면 반드시 default 생성자가 있어야 한다. 
	
	public WordRegisterServiceResource() { 
	}

	//생성자엔 Resource 생성 불가. 
	public WordRegisterServiceResource(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	// 단어등록 메서드 -> wordset에 저장시키는 것이다.
	// wordSet객체를 가져와서 비교한다. (같은 단어가 있으면 걸러내야하니까 )
	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if (verify(wordKey)) {
			wordDao.insert(wordSet); // wordSet에 insert한다.
		} else {

			System.out.println("The word has already registered");
		}
	}
	@Resource
	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey); // wordKey db에 넣기

		return wordSet == null ? true : false; // wordSet이 null이면(등록되지않은 단어) true로 단어를 등록한다.
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}