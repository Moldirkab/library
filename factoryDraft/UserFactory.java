package factoryDraft;

import modelsDraft.ReaderDraft;
import modelsDraft.StaffDraft;
import modelsDraft.UserDraft;

public class UserFactory {
    public static UserDraft createReader( String name, String surname,String email, String password, String login) {
        return new ReaderDraft.ReaderBuilder()
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setPassword(password)
                .setLogin(login)
                .build();

    }
    public static UserDraft createStaff( String name, String surname, String login, String password, int salary){
        return new StaffDraft.StaffBuilder()
                .setSalary(salary)
                .setName(name)
                .setSurname(surname)
                .setLogin(login)
                .setPassword(password)
                .build();

    }
}