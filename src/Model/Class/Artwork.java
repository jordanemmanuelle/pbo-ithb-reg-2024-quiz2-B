package Model.Class;

public class Artwork {
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private String userId;

    public Artwork(int id, String title, String description, String imagePath, String userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.userId = userId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
