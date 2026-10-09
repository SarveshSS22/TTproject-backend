package TTproject.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String category; // e.g., Full Stack, Backend, Frontend
    private String tags;     // e.g., "Java, Spring Boot, React, MySQL"
    private String imageUrl;
    private String githubUrl;
    private String liveUrl;
    private boolean featured;

    public Project() {}

    public Project(String title, String description, String category, String tags, String imageUrl, String githubUrl, String liveUrl, boolean featured) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
        this.featured = featured;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String liveUrl) { this.liveUrl = liveUrl; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
}