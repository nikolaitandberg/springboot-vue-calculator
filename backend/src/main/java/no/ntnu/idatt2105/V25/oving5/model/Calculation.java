package no.ntnu.idatt2105.V25.oving5.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calculations")
public class Calculation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String expression;
    private String result;
    private LocalDateTime timestamp;
    
    @ManyToOne
    private User user;
    
    // Default constructor
    public Calculation() {
        this.timestamp = LocalDateTime.now();
    }
    
    public Calculation(String expression, String result, User user) {
        this.expression = expression;
        this.result = result;
        this.user = user;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}