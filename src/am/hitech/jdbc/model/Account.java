package am.hitech.jdbc.model;

public class Account {
    private int id;
    private String userName;
    private String password;
    private int balance;
    private int userId;

    public Account(int id, String userName, String password, int balance, int userId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.userId = userId;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
