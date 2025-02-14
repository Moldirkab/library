package controllersDraft.interfaces;

import modelsDraft.StaffDraft;

public interface IStaffControllerDraft{


    StaffDraft addMember(StaffDraft staff);

    StaffDraft findMemberByIdPassword(int id, String password);

    void showAllMembers();

    void deleteMemberById(int id);
}