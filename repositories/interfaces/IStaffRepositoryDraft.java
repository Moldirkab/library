package repositoriesDraft.interfaces;
import modelsDraft.*;

import java.util.List;

public interface IStaffRepositoryDraft {
    StaffDraft addMember(StaffDraft staff);

    List<StaffDraft> showAllMembers();

    StaffDraft findMemberByIdPassword(int id, String password);

    void deleteMemberById(int id);
}
