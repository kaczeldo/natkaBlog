package NatkaBlog.controllers;

import NatkaBlog.models.dto.ArticleDTO;
import NatkaBlog.models.dto.mappers.ArticleMapper;
import NatkaBlog.models.exceptions.ArticleNotFoundException;
import NatkaBlog.models.services.ArticleService;
import NatkaBlog.models.services.ImageStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class ArticleController {
    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping
    public String renderIndex(Model model) {
        List<ArticleDTO> articles = articleService.getAll();
        model.addAttribute("articles", articles);

        return "pages/blog/index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute ArticleDTO article){
        return "pages/blog/create";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("create")
    public String createArticle(@Valid @ModelAttribute ArticleDTO article,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                @RequestParam("imageFile")MultipartFile imageFile)
    throws IOException {

        if (result.hasErrors()) {
            return renderCreateForm(article);
        }

        if(!imageFile.isEmpty()){
            String imgUrl = imageStorageService.store(imageFile);
            article.setImgUrl(imgUrl);
        }

        articleService.create(article);
        redirectAttributes.addFlashAttribute("success", "Article created.");

        return "redirect:/blog";
    }

    @GetMapping("{articleId}")
    public String renderDetail(
            @PathVariable long articleId,
            Model model
    ){
        ArticleDTO article = articleService.getById(articleId);
        model.addAttribute("article", article);

        return "pages/blog/detail";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{articleId}")
    public String renderEditForm(
            @PathVariable Long articleId,
            ArticleDTO article
    ) {
        ArticleDTO fetchedArticle = articleService.getById(articleId);
        articleMapper.updateArticleDTO(fetchedArticle, article);

        return "pages/blog/edit";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{articleId}")
    public String editArticle(
            @PathVariable long articleId,
            @Valid ArticleDTO article,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @RequestParam("imageFile")MultipartFile imageFile
    ) throws IOException {
        if (result.hasErrors()){
            return renderEditForm(articleId, article);
        }

        if(!imageFile.isEmpty()){
            String imgUrl = imageStorageService.store(imageFile);
            article.setImgUrl(imgUrl);
        }

        article.setArticleId(articleId);
        articleService.edit(article);

        redirectAttributes.addFlashAttribute("success", "Article edited.");

        return "redirect:/blog";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{articleId}")
    public String deleteArticle(@PathVariable long articleId, RedirectAttributes redirectAttributes){
        articleService.remove(articleId);
        redirectAttributes.addFlashAttribute("success", "Article removed.");

        return "redirect:/blog";
    }

    @ExceptionHandler({ArticleNotFoundException.class})
    public String handleArticleNotFoundException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Article not found.");

        return "redirect:/blog";
    }
}
