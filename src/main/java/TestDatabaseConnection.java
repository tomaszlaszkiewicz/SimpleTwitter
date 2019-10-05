import hibernate.util.HibernateUtil;
import model.User;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        User user = new User.UserBuilder()
                .buildLogin("Mark")
                .buildName("Marek")
                .buildLastName("Johns")
                .buildEmail("email@com")
                .buildPassword("pass")
                .buildUser();
        hibernateUtil.save(user);

    }
}
