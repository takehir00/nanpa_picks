package admin.services.impl;


import admin.forms.article.ArticleForm;
import admin.forms.article.ArticleRegisterForm;
import admin.forms.article.ArticleUpdateForm;
import admin.util.PageUtil;
import db.entities.Article;
import db.repositories.ArticleRepository;
import admin.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EntityScan(basePackageClasses = Article.class)
@EnableJpaRepositories(basePackageClasses = ArticleRepository.class)
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getByOffsetAndLimit(int offset, int limit) {
        return articleRepository.findByOffsetAndLimit(offset, limit);
    }

    @Override
    public void create(ArticleRegisterForm articleForm) {
        ModelMapper modelMapper = new ModelMapper();
        Article articleModel = modelMapper.map(articleForm, Article.class);
        articleRepository.create(articleModel);
    }

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public ArticleUpdateForm updateForm(Long articleId) {
        Article article = articleRepository.findById(articleId);
        if (article != null) {
            return ArticleUpdateForm.builder()
                    .id(article.id)
                    .url(article.url)
                    .title(article.title)
                    .imageUrl(article.imageUrl)
                    .build();
        }
        return null;
    }

    @Override
    public int getPageCount(int limit) {
        return PageUtil.calculatePageCount(articleRepository.countAll(), limit);
    }

    @Override
    public void update(ArticleUpdateForm articleForm) {
        Article article = articleRepository.findById(articleForm.id);
        article.url = articleForm.url;
        article.title = articleForm.title;
        article.imageUrl = articleForm.imageUrl;
        articleRepository.update(article);
    }

    @Override
    public void delete(ArticleForm articleForm) {
        Article article = articleRepository.findById(articleForm.id);
        articleRepository.delete(article);
    }

}
