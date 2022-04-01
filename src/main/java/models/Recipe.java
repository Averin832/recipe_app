package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
/*    @NamedQuery(
    name = "getAllRecipes",
    query = "select m from Recipe as m order by m.id DESC"
    ),*/
@NamedQuery(
    name = "getRecipesCount",
    query = "select count(m) from Recipe as m"
    )
})

@Table(name = "recipes")

public class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 12, nullable = false)
    private String title;

    @Column(name = "ingredient", nullable = false)
    private String ingredient;

    @Column(name = "how_to", nullable = false)
    private String how_to;

    @Column(name = "word", length = 255, nullable = true)
    private String word;

    @ManyToOne
    @JoinColumn(name = "user_mail", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getHow_to() {
        return how_to;
    }

    public void setHow_to(String how_to) {
        this.how_to = how_to;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
