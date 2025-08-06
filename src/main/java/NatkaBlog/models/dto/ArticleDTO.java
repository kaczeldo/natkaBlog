package NatkaBlog.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArticleDTO {
    private long articleId;

    @NotBlank(message = "Fill in title.")
    @Size(max = 100, message = "Title is too long.")
    private String title;

    @NotBlank(message = "Fill in description.")
    private String description;

    @NotBlank(message = "Fill in content.")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
