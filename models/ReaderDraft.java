package modelsDraft;

public class ReaderDraft extends UserDraft {
    private final String email;

    private ReaderDraft(ReaderBuilder builder) {
        super(builder);
        this.email = builder.email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ", name: '" + getName() + "', surname: '" + getSurname();
    }

    public static class ReaderBuilder extends UserDraft.Builder {
        private String email;

        public ReaderBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public ReaderDraft build() {
            return new ReaderDraft(this);
        }
    }
}




