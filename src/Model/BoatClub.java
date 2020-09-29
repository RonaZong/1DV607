package Model;

import java.util.ArrayList;

public class BoatClub {
    private ArrayList<Member> members=new ArrayList<>();
    private CompactListSave compactSave;
    private VerboseListSave verboseListSave;

    public BoatClub() {
    }

    public Member creatMember(String userName, String personalNumber){
        Member member = new Member(userName,personalNumber);
        CompactListSave save = new CompactListSave();
        members.add(member);
        save.saveFileOnCompactList(member);
        verboseListSave = new VerboseListSave();
        verboseListSave.saveFileOnCompactList(member);

        return member;

    }

    public void deleteMember(Member member){
        members.remove(member);
    }



    public void updateMemberInformation(Member member , String name , String personalNumber){
        if(name.length() >0)
            member.setName(name);
        if(personalNumber.length() >0)
            member.setPersonalNumber(personalNumber);
    }

    public Iterable<Member> getAllMembers(){
        compactSave = new CompactListSave();

        return compactSave.readyToPrintForCompactList(compactSave.compactList("compactList.txt"));
    }

    public void loadFromCompactList(CompactListSave list){
        this.members= (ArrayList<Member>) list.readyToPrintForCompactList(list.compactList("CompactList.txt"));
    }
}
