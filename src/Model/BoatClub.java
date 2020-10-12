package Model;

import java.util.ArrayList;

public class BoatClub {
    private ArrayList<Member> members=new ArrayList<>();
    private Registry registry = new Registry();
    private Member member;

    public BoatClub() {
    }

    public Member creatMember(String userName, String personalNumber){
        Member member = new Member(userName,personalNumber);
        //CompactListSave save = new CompactListSave();
        members.add(member);

       // save.saveFileOnCompactList(member);

        return member;
    }

   /* public void saveOnVerboseList(Member member){
      //  registry = new Registry();
        registry.saveFile(member);
    }*/


    public Member deleteMember(Member member){
        this.member = member;
    //    registry = new Registry();
    //   this.members = registry.loadForVerboseList(registry.verboseList("VerboseList.txt"));
        this.members.remove(member);
        //I used here to return that member who was deleted
        return this.member;
    }

    public void updateMemberInformation(Member member , String name , String personalNumber){
            member.setName(name);

            member.setPersonalNumber(personalNumber);
    }

    //we load all information with this method once we start the program
    public Iterable<Member> getAllMembersFromRegistry(){

        //registry = new Registry();
        this.members=registry.loadForVerboseList(registry.verboseList("VerboseList.txt"));
        return members;
    }

    //we save once we quit the program
    public void save(){
        Registry registry = new Registry();
        registry.updateRegistryFile(this);
    }


    public void loadAllInformationOfMembers(Registry list){
        this.members = list.loadForVerboseList(list.verboseList("VerboseList.txt"));
    }

    //enter a member name to get member
    public Member getMember(String memberName){
        for (Member m:members){
            if (memberName.equals(memberName)){
                return m;
            }
        }
        return null;
    }

    //other than first time we use this one to get information of arrayList
    public ArrayList<Member> getAllMembersLocally(){
        return this.members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
