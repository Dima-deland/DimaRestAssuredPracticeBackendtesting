package dto;

import java.util.Objects;

public class Experience {
    private int id;
    private String title;
    private String company;
    private String location;
    private String from;
    private String to;
    private boolean current;
    private String description;

    // Getters and Setters
    public int getId() { return id; }

    public void setTitle(String title) { this.title = title; }

    public void setCompany(String company) { this.company = company; }

    public void setLocation(String location) { this.location = location; }

    public void setFrom(String from) { this.from = from; }

    public void setTo(String to) { this.to = to; }

    public void setCurrent(boolean current) { this.current = current; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        return
                ((id == 0 || that.id == 0) || id == that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(location, that.location) &&
                Objects.equals(from.split("T")[0], that.from.split("T")[0]) &&
                Objects.equals(to.split("T")[0], that.to.split("T")[0]) &&
                current==that.current &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, company, location, from, to, current, description);
    }
}
