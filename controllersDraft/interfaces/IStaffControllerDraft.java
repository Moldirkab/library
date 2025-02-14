package controllersDraft.interfaces;

import modelsDraft.StaffDraft;

public interface IStaffControllerDraft{


    StaffDraft addMember(StaffDraft staff);

    StaffDraft findMemberByLoginPassword(String login, String password);

    void showAllMembers();

    void deleteMemberByLogin(String login);
}