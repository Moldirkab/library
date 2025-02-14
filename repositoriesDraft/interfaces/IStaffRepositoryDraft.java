package repositoriesDraft.interfaces;
import modelsDraft.*;

import java.util.List;

public interface IStaffRepositoryDraft {
    StaffDraft addMember(StaffDraft staff);

    List<StaffDraft> showAllMembers();

    StaffDraft findMemberByLoginPassword(String login, String password);

    void deleteMemberByLogin(String login);
}
