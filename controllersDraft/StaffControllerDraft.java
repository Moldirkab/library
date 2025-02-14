package controllersDraft;

import controllersDraft.interfaces.IStaffControllerDraft;
import modelsDraft.StaffDraft;
import repositoriesDraft.interfaces.IStaffRepositoryDraft;

import java.util.List;

public class StaffControllerDraft implements IStaffControllerDraft {
    private final IStaffRepositoryDraft staffRepository;
    public StaffControllerDraft(IStaffRepositoryDraft repo) {
        this.staffRepository = repo;
    }
    @Override
    public StaffDraft addMember(StaffDraft staff) {
        return staffRepository.addMember(staff);
    }
    @Override
    public StaffDraft findMemberByLoginPassword(String login, String password) {
        StaffDraft staff = staffRepository.findMemberByLoginPassword(login, password);
        return staff;
    }
    @Override
    public void showAllMembers() {
        List<StaffDraft> staffList = staffRepository.showAllMembers();
        staffList.forEach(staff -> System.out.println(staff));

    }
    @Override
    public void deleteMemberByLogin(String login) {
        staffRepository.deleteMemberByLogin(login);
    }
}
