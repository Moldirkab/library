package repositories;
import models.*;

import java.util.List;

public interface IStaffRepository {
    Staff addMember(Staff staff);

    List<Staff> showAllMembers();

    Staff findMemberByIdPassword(int id, String password);

    void deleteMemberById(int id);
}
