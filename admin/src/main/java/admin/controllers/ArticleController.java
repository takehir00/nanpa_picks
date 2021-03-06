package admin.controllers;


import admin.common.FunctionNameAware;
import admin.forms.article.ArticleForm;
import admin.forms.article.ArticleRegisterForm;
import admin.forms.article.ArticleUpdateForm;
import admin.util.PageUtil;
import db.entities.Article;
import admin.services.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Controller
public class ArticleController implements FunctionNameAware {

    @Autowired
    ArticleService articleService;

    /**
     * 一覧画面
     *
     * @param mav
     * @return
     */
    @Transactional
    @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav, @RequestParam int page) {
        mav = setArticleTopModelAndView(null, page);
        return mav;
    }

    /**
     * 登録画面
     *
     * @return
     */
    @RequestMapping(value = "/admin/article/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(
            ModelAndView mav,
            @ModelAttribute("articleRegisterForm") ArticleRegisterForm articleRegisterForm) {
        mav.setViewName("articles/registerForm");
        return mav;
    }

    /**
     * 登録
     *
     * @param articleRegisterForm
     * @return
     */
    @Transactional
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST)
    public String register(
            @Validated @ModelAttribute("articleRegisterForm") ArticleRegisterForm articleRegisterForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError urlError =bindingResult.getFieldError("url");
            FieldError titleError =bindingResult.getFieldError("title");
            FieldError imageUrlError =bindingResult.getFieldError("imageUrl");
            attributes.addFlashAttribute("articleRegisterForm", articleRegisterForm);
            attributes.addFlashAttribute("urlError", urlError);
            attributes.addFlashAttribute("titleError", titleError);
            attributes.addFlashAttribute("imageUrlError", imageUrlError);
            return "redirect:/admin/article/registerForm";
        }
        articleService.create(articleRegisterForm);
        return "redirect:/admin/article?page=0";
    }

    /**
     * 更新画面
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/article/edit/{articleId}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView mav,
                             @PathVariable Long articleId) {
        ArticleUpdateForm form = articleService.updateForm(articleId);
        if (form != null) {
            mav.setViewName("articles/editForm");
            mav.addObject("articleUpdateForm", form);
            return mav;
        } else {
            int page = 0;
            String flash = "該当の記事はありません";
            mav = setArticleTopModelAndView(flash, page);
            return mav;
        }
    }

    /**
     * 更新
     *
     * @param articleUpdateForm
     * @return
     */
    @RequestMapping(value = "/admin/article/update", method = RequestMethod.POST)
    public String  update(
            @Validated @ModelAttribute("articleForm") ArticleUpdateForm articleUpdateForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError urlError =bindingResult.getFieldError("url");
            FieldError titleError =bindingResult.getFieldError("title");
            FieldError imageUrlError =bindingResult.getFieldError("imageUrl");
            attributes.addFlashAttribute("articleUpdateForm", articleUpdateForm);
            attributes.addFlashAttribute("urlError", urlError);
            attributes.addFlashAttribute("titleError", titleError);
            attributes.addFlashAttribute("imageUrlError", imageUrlError);
            return "redirect:/admin/article/edit/" + articleUpdateForm.getId();
        }
        articleService.update(articleUpdateForm);
        return "redirect:/admin/article?page=0";
    }

    /**
     * 削除画面
     *
     * @param mav
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/article/destroy/{id}", method = RequestMethod.GET)
    public ModelAndView destroy(ModelAndView mav,
                                   @PathVariable Long id) {

        Article article = articleService.getById(id);
        if (article != null) {
            mav.setViewName("articles/deleteForm");
            mav.addObject("article", article);
            return mav;
        } else {
            String flash = "該当の記事はありません";
            int page = 0;
            mav = setArticleTopModelAndView(flash, page);
            return mav;
        }
    }

    /**
     * 削除
     *
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/admin/article/delete", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("articleForm") ArticleForm articleForm) {
        articleService.delete(articleForm);
        return "redirect:/admin/article?page=0";
    }

    /**
     * 記事TOP画面のModelAndViewを生成
     *
     * @return
     */
    private ModelAndView setArticleTopModelAndView(String flash, int page) {
        int limit = 15;
        int offset = PageUtil.calculatePageOffset(page, limit);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("articles/top");
        mav.addObject("articles",
                articleService.getByOffsetAndLimit(offset, limit));
        mav.addObject("pageCount", articleService.getPageCount(limit));
        mav.addObject("flash",flash);
        return mav;
    }

    @Override
    public String getFunctionName() {
        return "Article Controller";
    }
}
