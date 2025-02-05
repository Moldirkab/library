package modelsDraft;

public class UserDraft {
    private int id;
    private String name;
    private String surname;
    private String password;

    protected UserDraft(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.password = builder.password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private int id;
        private String name;
        private String surname;
        private String password;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDraft build() {
            return new UserDraft(this);
        }
    }
}

