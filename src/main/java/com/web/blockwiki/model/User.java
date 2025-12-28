package com.web.blockwiki.model;
import org.springframework.data.neo4j.core.schema.*;

@Node
public class User {

    @Id @GeneratedValue
    private String id;                     // ID user
    private String username;               // Tên đăng nhập / hiển thị
    private boolean isAdmin;               // Quyền admin

    // Constructor mặc định
    public User() {}

    // Constructor đầy đủ
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }
}