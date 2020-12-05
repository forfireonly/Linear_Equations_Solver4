import java.util.Arrays;

class Book {

    private String title;
    private int yearOfPublishing;
    private String[] authors;

    public Book(String title, int yearOfPublishing, String[] authors) {
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
        if (authors == null) {
            this.authors = new String[0];
        } else {
            this.authors = Arrays.copyOf(authors, authors.length);
        }
    }

    @Override
    public String toString() {
        StringBuilder authorsStringBuilder = new StringBuilder();
        String prefix = "";
        for (String serverId : authors) {
            authorsStringBuilder.append(prefix);
            prefix = ",";
            authorsStringBuilder.append(serverId);
        }
        return "title=" + title + ",yearOfPublishing=" + yearOfPublishing + ",authors=[" + authorsStringBuilder +
                "]";
    }
}