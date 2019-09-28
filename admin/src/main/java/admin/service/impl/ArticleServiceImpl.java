package admin.service.impl;


import admin.form.ArticleForm;
import admin.model.Article;
import admin.repositries.ArticleRepository;
import admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public void create(ArticleForm articleForm) {
        Article article = new Article();
        article.url = articleForm.url;
        article.title = articleForm.title;
        article.imageUrl = articleForm.imageUrl;
        articleRepository.save(article);
    }

    @Override
    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public void update(ArticleForm articleForm) {
        Article article = new Article();
        article.id = articleForm.id;
        article.url = articleForm.url;
        article.title = articleForm.title;
        article.imageUrl = articleForm.imageUrl;
        articleRepository.saveAndFlush(article);
    }

    @Override
    public void delete(ArticleForm articleForm) {
        Optional<Article> articleOpt = articleRepository.findById(articleForm.id);
        articleOpt.ifPresent(article -> {
            articleRepository.delete(articleOpt.get());
        });
    }
}
