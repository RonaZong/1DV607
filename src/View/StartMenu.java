package View;

import Model.Boat;
import Model.BoatClub;
import Model.Member;

import java.util.Scanner;

public class StartMenu {
    private Scanner sc;
    private int userInput;
    private boolean alreadyMember=false;

    public StartMenu() {
    }

    private int userIntInput(){
        sc = new Scanner(System.in);
        return sc.nextInt();
    }
    private String userStringInput(){
        sc =new Scanner(System.in);
        return sc.nextLine();
    }

    private boolean IWantToQuit(){
        return userInput==3;
    }

    public void welcomeMessage(BoatClub boatClub , Member member, Boat boat){

        while (!IWantToQuit()) {

                System.out.println("Welcome to Boat Club\n" +
                        "-----------------------\n" +
                        "Press 1 to add a new member\n" +
                        "Press 2 to see a compact list of members\n"+
                        "Press 3 to see a full detailed list of members\n"+
                        "Press 4 to delete a member\n"+
                        "Press 5 to update the information of a member\n"+
                        "Press 6 to see the information of a specific member\n"+
                        "Press 7 to register a new boat\n"+
                        "Press 8 to update a boat information\n"+
                        "Press 9 to delete a boat\n"+

                        //who can have access to a specific member's information?
                        // "press 3 to check member's information \n" +
                        "Press 10 to quit \n");
                // do we need to have an admin so can have access to member's information?
                userInput = userIntInput();

                actUponUserInputInMainMenu(userInput, boatClub,member,boat);


           // showMemberMenu();
           /* int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    createMemberMenu(boatClub);
                    break;
                case 2:
                    showList(boatClub);
                    break;

            }*/
        }
    }

   /* public void showMemberMenu(){
        System.out.println("----Members' menu----");
        System.out.println("For see a compact list of members press 4 ");
        System.out.println("For see a full detailed list of members press 5");
        System.out.println("For delete a member press 6");
        System.out.println("For update the information of a member press 7");
        System.out.println("For see the information of a specific member press 8");
        System.out.println("For register a new boat press 9");
        System.out.println("For update a boat information press 10");
        System.out.println("For delete a boat press 11");
        System.out.println("For quit press 3");
        int userInput = userIntInput();
    }*/

    public void actUponUserInputInMainMenu(int userInput , BoatClub boatClub , Member member, Boat boat) {
        switch (userInput) {
            case 1:
                createMemberMenu(boatClub);
                break;
            case 2:
                showCompactList(boatClub);
                break;
            case 3:
                showVerboseList(boatClub);
                break;
            case 4:
                boatClub.deleteMember(member);
                break;
            case 5:
                showUpdateMemberMenu(boatClub , member);
                break;
            case 6:
                showSpecificMemberData(boatClub);
                break;
            case 7:
                askForABoatDataToRegister(member,boat);
                break;
            case 8:
                showUpdateBoatMenu(member,boat);
                break;
            case 9:
                showDeleteMenu(member,boat);
                break;


        }
    }

    private void showDeleteMenu(Member member,Boat boat) {
        member.deleteBoat(boat);
    }

    private void showUpdateBoatMenu(Member member,Boat boat) {
        System.out.println("");
    }

    private void askForABoatDataToRegister(Member member,Boat boat) {
        System.out.println("enter boat type:" +
                "\n0 for Sailboat , 1 for Motor sailor , 2 for Kayak/Canoe, 3 for Others");
        int typeValue = userIntInput();
        System.out.println("Enter length of the boat");
        int length = userIntInput();
        member.registerNewBoat(Boat.BoatType.values()[typeValue] , length);
    }

    private void showSpecificMemberData(BoatClub boatClub) {

    }

    public void actUponUserInputInMemberMenu(int userInput , BoatClub boatClub , Member member){
        switch(userInput){
            case 4:
                showCompactList(boatClub);
                break;
            case 5:
                showVerboseList(boatClub);
                break;
            case 6:
                boatClub.deleteMember(member);
                break;
            case 7:
                showUpdateMemberMenu(boatClub , member);
                break;

        }
    }

    private void showUpdateMemberMenu(BoatClub boatClub , Member member) {
        System.out.println("What do you want to update?");
        System.out.println("If you want to update name enter your new name otherwise press enter");
        String name = userStringInput();
        //do we need to check for validate personal number??
        System.out.println("if you want to update your personal number enter your new personal" +
                "number otherwise press enter");
        String personalNumber = userStringInput();
        boatClub.updateMemberInformation(member , name , personalNumber);

    }

    public void showList(BoatClub boatClub){
        System.out.print("----- Show list of members -----\n"+
                " What kind of list, press 1 for compact list, press 2 for verbose list: ");
        int enter = sc.nextInt();

        if (enter==1){
            //showCompactList();
        }else if(enter==2){
            //showVerboseList();
        }else {
            //error
        }
    }

    public void createMemberMenu(BoatClub boatClub){
        System.out.println("----- Become a Member -----");

        System.out.println("In order to be a member you have to enter following information : ");
        System.out.print("Please enter user name: ");
        //String ch=sc.nextLine();????
        String name = userStringInput();
        System.out.print("Please enter personal number: ");
        String personalNumber = userStringInput();
        boatClub.creatMember(name,personalNumber);
       // this.alreadyMember = true;


    }

    public void showCompactList(BoatClub boatClub){
        for(Member member : boatClub.getAllMember()) {
            System.out.println("This member name is : " + member.getName() +
                    "\nwith memberID of : " + member.getMemberID() +
                    "\nwhich has " + member.boatsOwnedByMember().size() + "boats" +
                    "\n------------\n");
        }
    }

    public void showVerboseList(BoatClub boatClub){
        for(Member member : boatClub.getAllMember()){
        System.out.println("This member name is : " + member.getName() +
                           "\nwith personal number of " + member.getPersonalNumber() +
                            "\nwith memberID of " + member.getMemberID());
        //it might give a null exception
        System.out.println("This member has " + member.boatsOwnedByMember().size() + "boats");
        if(member.boatsOwnedByMember().size()> 0 ) {
            System.out.println("this member boat information is :");
            for (Boat boat : member.boatsOwnedByMember()) {
                System.out.println("Boat type :" + boat.getType() +
                        "\nBoat color : " + boat.getLength());
            }
        }
        }
    }


    }
