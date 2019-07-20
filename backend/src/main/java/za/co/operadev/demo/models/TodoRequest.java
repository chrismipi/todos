package za.co.operadev.demo.models;

public class TodoRequest {
    private String title;
    private Boolean complete;

    public TodoRequest(String title, Boolean complete) {
        this.title = title;
        this.complete = complete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
