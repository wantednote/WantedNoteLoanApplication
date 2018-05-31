package com.wn.loanapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wn.loanapp. model.Article;
import com.wn.loanapp.repository.ArticleRepository;
import com.wn.loanapp.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Article getArticleById(long articleId) {
		Article obj = articleRepository.findById(articleId).get();
		return obj;
	}	
	@Override
	public List<Article> getAllArticles(){
		List<Article> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addArticle(Article article){
	   List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory()); 	
       if (list.size() > 0) {
    	   return false;
       } else {
    	   articleRepository.save(article);
    	   return true;
       }
	}
	@Override
	public void updateArticle(Article article) {
		articleRepository.save(article);
	}
	@Override
	public void deleteArticle(int articleId) {
		articleRepository.delete(getArticleById(articleId));
	}
	
	/*@SuppressWarnings("deprecation")
	@Override
	public List<Article> getPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, 5, Sort.Direction.ASC, "articleId");
		
		return articleRepository.findAll(request).getContent();
	}*/
}
