/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballdatabaseproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://10.98.98.61:5432/group32";
// VeritabanÄ± kullanÄ±cÄ± adÄ± ve ÅŸifresi
    static final String USER = "group32";
    static final String PASS = "FRPX*9HYj%2dZ9uK";

    public static void main(String[] args) {
        String selection = " ", id, password;
        String exitInput;
        /*int CurrentCoachIdValue;
        int CurrentPlayerIdValue;
        int CurrentTrainerIdValue;
        int CurrentSportiveDirectorIdValue;*/
        int attemptnumber = 0;

        Scanner scan = new Scanner(System.in);
        //IF YOU WANT TO ACTIVE THE ENTER ANIMATONS DECOMMENT THESE LINES.
        //threadsleep();
        //enterance(); 

        System.out.println("");
        System.out.println("Welcome to The Fenerbahce S.K. Database Management System ");
        System.out.println("Please Enter Your User ID and Password:");
        System.out.println("User ID: ");
        id = scan.next();
        System.out.println("Password: ");
        password = scan.next();
        if (checkpassword(id, password)) {
            action();
            selection = scan.next();

        } else {
            do {
                if (attemptnumber == 2) {
                    System.out.println("You have no chance left.");
                    System.exit(0);
                    //selection = "U";

                } else {
                    System.out.println("Wrong ID or Password You have " + (2 - attemptnumber) + " remaining.");
                    System.out.println("Enter your ID and Password again:");
                    System.out.println("User ID: ");
                    id = scan.next();
                    System.out.println("Password: ");
                    password = scan.next();
                    attemptnumber++;
                }

            } while (!checkpassword(id, password));
            if (checkpassword(id, password)) {
                action();
                selection = scan.next();

            }

        }
        do {
            switch (selection) {
                case "C":
                case "c":
                    // System.out.println("Please Enter Your User ID and Password:");
                    //id = scan.next();
                    id = "group32";
                    password = "FRPX*9HYj%2dZ9uK";
                    //password = scan.next();
                    if (checkpassword(id, password)) {
                        CoachUI();
                        System.out.println("Select an opeation you want to do? ");
                        int operation1 = scan.nextInt();
                        switch (operation1) {

                            case 1:
                                int squadcounter = 0;
                                int firs11counter = 0;
                                int reservescounter = 0;
                                // BY DEFAULT WE HAVE MAX 18 PLAYER IN SQUAD (FIRS11 + RESERVES)****EXPLICITLY-> 11 FIRST11**** 7 RESERVES
                                while (squadcounter < 18 && firs11counter < 11 && reservescounter < 7) {
                                    System.out.println("Choose Position that you want to see");
                                    positions();
                                    String position;
                                    int selectedposition = scan.nextInt();
                                    if (selectedposition == 1) {
                                        position = "Goalkeeper";
                                    } else if (selectedposition == 2) {
                                        position = "Defence";
                                    } else if (selectedposition == 3) {
                                        position = "Midfielder";
                                    } else {
                                        position = "Forward";
                                    }
                                    viewPlayerCurrentSquad(position); //IT JUST SHOW THE PLAYERS IN THAT POSITION AND ALSO NOT IN THE CURRENT SQUAD
                                    // System.out.println(" ");
                                    //viewCurrentSquad();
                                    System.out.println("Write Player ID and Squad Status to add current roaster:");
                                    String P_id = scan.next();
                                    System.out.println("First XI or reserves?(Write 1 or 2)");
                                    String status_11_or_reserves = scan.next();
                                    if (status_11_or_reserves.equals("1")) {
                                        firs11counter++;
                                        squadcounter++;
                                    }
                                    if (status_11_or_reserves.equals("2")) {
                                        reservescounter++;
                                        squadcounter++;
                                    }
                                    addPlayerToCurrentSquad(P_id, status_11_or_reserves); // status 1 ise player name ini firs 11 e yaz 2 ise reserves e yaz.
                                    System.out.println("Player added to the Current Squad Successfully.");
                                    System.out.println("Now, Current Squad:");
                                    viewCurrentSquad();
                                    System.out.println("Do you want to continue add new Players to the Current Squad?(Yes or No)");
                                    String decision = scan.next();
                                    if (!decision.equalsIgnoreCase("Yes")) {
                                        break;
                                    }
                                }
                                break;
                            
                    
                            case 2:
                                makeDecisionAboutSuggestedPlayers();
                                break;
                            case 3:
                                // viewCoaches();
                                System.out.println("Which Information you want to Update?(Please write the ID of the Coach that you want to change:)");
                                System.out.println("1)Adress");
                                System.out.println("2)Phone Number");
                                System.out.println("3)Return to the main page");
                                int answer = scan.nextInt();
                                if (answer == 3) {
                                    continue;
                                } else if (answer == 1) {
                                    viewCoaches();
                                    System.out.println("Enter the new Address: ");
                                    String newAddress = scan.next();
                                    System.out.println("Now write your id :");
                                    int coach_id = scan.nextInt();
                                    changeAddressforCoach(newAddress, coach_id);
                                    System.out.println("Address is Changed Successfully.");

                                } else if (answer == 2) {
                                    viewCoaches();
                                    System.out.println("Enter the new PhoneNumber: ");
                                    String newPhoneNumber = scan.next();
                                    System.out.println("Now write your id: ");
                                    int coach_id = scan.nextInt();
                                    changePhoneNumberforCoach(newPhoneNumber, coach_id);
                                    System.out.println("PhoneNumber is Changed Successfully.");
                                }
                                break;

                            default:
                                System.out.println("Please enter valid input.");
                                CoachUI();
                                System.out.println("Select an opeation you want to do? ");
                                operation1 = scan.nextInt();

                                break;
                        }

                    }
                    /*else {
                            do {
                                if (attemptnumber == 2) {
                                    System.out.println("You have no chance left. You can only enter as Unauthorized to view information.");
                                    selection = "U";
                                    break;
                                }
                                System.out.println("Wrong ID or Password You have " + (2 - attemptnumber) + " remaining.");
                                System.out.println("Enter your ID and Password again:");
                                id = scan.next();
                                password = scan.next();
                                attemptnumber++;

                            } while (checkpassword(id, password));

                        }*/
                    break;
                case "M":
                case "m":
                    // System.out.println("Enter your user ID and Password:");
                    // id = scan.next();
                    // password = scan.next();
                    id = "group32";
                    password = "FRPX*9HYj%2dZ9uK";
                    if (checkpassword(id, password)) {
                        ManagerUI();

                        System.out.println("Select an opeation you want to do? ");
                        int operation2 = scan.nextInt();
                        switch (operation2) {

                            case 1:
                                addPlayerContract();
                                break;
                            case 2:
                                deletePlayerContract();
                                break;
                            case 3:
                                addCoachContract();
                                break;
                            case 4:
                                deleteCoachContract();
                                break;
                            case 5:
                                addTrainerContract();
                                break;
                            case 6:
                                deleteTrainerContract();
                                break;
                            case 7:
                                addSportiveDirectorContract();
                                break;
                            case 8:
                                deleteSportiveDirectorContract();
                                break;
                            case 9:
                                System.out.println("Which information do you want to update?");
                                System.out.println("1)Adress");
                                System.out.println("2)Phone Number");
                                System.out.println("3)Return to the main page");
                                int answer = scan.nextInt();
                                if (answer == 3) {
                                    continue;
                                } else if (answer == 1) {
                                    
                                	//viewManagers();
                                    System.out.println("Enter the new Address: ");
                                    String newAddress = scan.next();
                                    System.out.println("Now write your id: ");
                                    int manager_id = scan.nextInt();
                                    changeAddressforManager(newAddress, manager_id);
                                    System.out.println("Address is Changed Successfully.");

                                } else if (answer == 2) {
                                    //viewManagers();
                                    System.out.println("Enter the new PhoneNumber: ");
                                    String newPhoneNumber = scan.next();
                                    System.out.println("Now write your id: ");
                                    int manager_id = scan.nextInt();
                                    changePhoneNumberforManager(newPhoneNumber, manager_id);
                                    System.out.println("PhoneNumber is Changed Successfully.");

                                }
                                break;

                            default:
                                System.out.println("Please enter valid input.");
                                ManagerUI();
                                System.out.println("Select an opeation you want to do? ");
                                operation2 = scan.nextInt();
                                break;

                        }

                    }
                    break;
                case "T":
                case "t":
                    //id = scan.next();
                    // password = scan.next();
                    id = "group32";
                    password = "FRPX*9HYj%2dZ9uK";
                    if (checkpassword(id, password)) {
                        TrainerUI();
                    }
                    System.out.println("Select an opeation you want to do? ");
                    int operation = scan.nextInt();
                    switch (operation) {
                        case 1:
                            addTrainerToPLayer();
                            break;
                        case 2:
                            changeTrainerOfaPlayer();
                            break;
                        case 3:
                            System.out.println("Which information do you want to update?");
                            System.out.println("1)Adress");
                            System.out.println("2)Phone Number");
                            System.out.println("3)Return to the main page");
                            int answer = scan.nextInt();
                            if (answer == 3) {
                                continue;
                            } else if (answer == 1) {
                                viewTrainers();
                                System.out.println("Enter the new Address: ");
                                String newAddress = scan.next();
                                System.out.println("Now write your id: ");
                                int trainer_id = scan.nextInt();
                                changeAddressforTrainer(newAddress, trainer_id);
                                System.out.println("Address is Changed Successfully.");

                            } else if (answer == 2) {
                                viewTrainers();
                                System.out.println("Enter the new PhoneNumber: ");
                                String newPhoneNumber = scan.next();
                                System.out.println("Now write your id: ");
                                int trainer_id = scan.nextInt();
                                changePhoneNumberforTrainer(newPhoneNumber, trainer_id);
                                System.out.println("PhoneNumber is Changed Successfully.");

                            }
                            break;

                    }
                    break;
                case "E":
                case "e":
                    exit();
                    System.exit(0);
                    break;
                case "S":
                case "s":
                	id = "group32";
                    password = "FRPX*9HYj%2dZ9uK";
                    if (checkpassword(id, password)) {
                        SportiveDirectorUI();
                    }
                    System.out.println("Select an opeation you want to do? ");
                    int operation3 = scan.nextInt();
                    switch (operation3) {
                        case 1:
                            addTalentedPlayerFromU19();
                            break;
                        case 2:
                            suggestTalentedPlayertoCoaches();
                            break;
                        case 3:
                            System.out.println("Which information do you want to update?");
                            System.out.println("1)Adress");
                            System.out.println("2)Phone Number");
                            System.out.println("3)Return to the main page");
                            int answer = scan.nextInt();
                            if (answer == 3) {
                                continue;
                            } else if (answer == 1) {
                                viewSportiveDirectors();
                                System.out.println("Enter the new Address: ");
                                String newAddress = scan.next();
                                System.out.println("Now write your id: ");
                                int sd_id = scan.nextInt();
                                changeAddressforSportiveDirector(newAddress, sd_id);
                                System.out.println("Address is Changed Successfully.");

                            } else if (answer == 2) {
                                viewSportiveDirectors();
                                System.out.println("Enter the new PhoneNumber: ");
                                String newPhoneNumber = scan.next();
                                System.out.println("Now write your id: ");
                                int sd_id = scan.nextInt();
                                changePhoneNumberforSportiveDirector(newPhoneNumber, sd_id);
                                System.out.println("PhoneNumber is Changed Successfully.");

                            }
                            break;

                    }
                    break;
                case "U":
                case "u":
                	id = "group32";
                    password = "FRPX*9HYj%2dZ9uK";
                    if (checkpassword(id, password)) {
                        UnauthorizedUI();
                    }
                    System.out.println("Select an opeation you want to do? ");
                    int operation4 = scan.nextInt();
                    switch (operation4) {
                        case 1:
                            String position;
                            positions();
                            System.out.println("Please Enter a  player position that you want to see");
                            int selectedposition = scan.nextInt();
                            if (selectedposition == 1) {
                                position = "Goalkeeper";
                            } else if (selectedposition == 2) {
                                position = "Defence";
                            } else if (selectedposition == 3) {
                                position = "Midfielder";
                            } else {
                                position = "Forward";
                            }
                            System.out.println("Players play in the " + position + "position: ");
                            viewPlayer(position);

                            break;
                        case 2:
                            viewCurrentSquad();
                            break;
                        case 3:
                            viewCoaches();
                            break;
                        case 4:
                            viewTrainers();
                            break;
                        case 5:
                            viewSportiveDirectors();
                            break;
                        case 6:
                            viewManagers();
                            break;
                        default:
                            System.out.println("Please enter valid input: Try again");
                            UnauthorizedUI();
                            operation4 = scan.nextInt();
                            break;
                    }
                    //write ready only codes.
                    break;
            }
            System.out.println("If you want to exit from the system press 'X'.");
            System.out.println("If you want to change the User Mode press 'U'");
            System.out.println("If you want to resume the same User press 'R'");
            exitInput = scan.next();
            if (exitInput.equals("X") || exitInput.equals("x")) {
                System.out.println("DBMS Closing...");
                exit();
                break;
            } else if (exitInput.equals("U") || exitInput.equals("u")) {
                action();
                selection = scan.next();
            } else if (exitInput.equals("R") || exitInput.equals("r")) {
                if (selection == "C") {

                }
            } else {
                System.out.println("Unknown command, Bye.");
                exit();
                System.exit(1);
            }

        } while (true);

    }

    private static void action() {
        System.out.println("Please Choose your user type:");
        System.out.println("[M]anager");
        System.out.println("[C]oach");
        System.out.println("[T]rainer");
        System.out.println("[S]portive Director");
        System.out.println("[U]nauthorized");
        System.out.println("[E]xit");
    }

    private static boolean checkpassword(String id, String password) {
        return (id.equals("group32") && password.equals("FRPX*9HYj%2dZ9uK"));
    }

    private static void CoachUI() {
        System.out.println("1)Selection for match squad: ");
        System.out.println("2)Make decision about suggested players from Sportive Directors: ");
        System.out.println("3)Information update: ");
    }

    private static void UnauthorizedUI() {
        System.out.println("1)View Players: ");
        System.out.println("2)View Current First 11: ");
        System.out.println("3)View Coaches: ");
        System.out.println("4)View Trainers: ");
        System.out.println("5)View Sportive Directors: ");
        System.out.println("6)View Manager: ");
    }

    private static void ManagerUI() {
        System.out.println("1)Signed contract with a Player:");
        System.out.println("2)End contract with a Player:");
        System.out.println("3)Signed Contract with a Coach:");
        System.out.println("4)End Contract with a Coach:");
        System.out.println("5)Signed Contract with a Trainer:");
        System.out.println("6)End Contract with a Trainer:");
        System.out.println("7)Signed Contract with a Sportive Director:");
        System.out.println("8)End Contract with a Sportive Director:");
        System.out.println("9)Information update: ");
    }

    private static void TrainerUI() {
        System.out.println("1)Assign a trainer to a player:");
        System.out.println("2)Change trainer of a player:");
        System.out.println("3)Information update: ");
    }

    private static void SportiveDirectorUI() {
        System.out.println("1)Determine The Talented U-19 Players:"); // add and update
        //System.out.println("1)Update The Talented U-19 Players:");
        System.out.println("2)Suggest a Talented Player to Coaches:");
        System.out.println("3)Information update:");
    }

    private static int viewPlayer(String position) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int playeridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"Players\" WHERE p_position = " + "'" + position + "'");
            while (resultSet.next()) {
                int id = resultSet.getInt("player_id");
                String name = resultSet.getString("p_name");
                int age = resultSet.getInt("p_age");
                int uniformnum = resultSet.getInt("uniform_num");
                String clubname = resultSet.getString("club_name");
                int trainerid = resultSet.getInt("trainer_id");
                String playerstatus = resultSet.getString("player_status");
                String nation = resultSet.getString("p_nation");

                System.out.println("ID " + id);
                System.out.println("Name " + name);
                System.out.println("Age " + age);
                System.out.println("Uniformnum " + uniformnum);
                System.out.println("Clubname " + clubname);
                System.out.println("Trainerid " + trainerid);
                System.out.println("Playerstatus " + playerstatus);
                System.out.println("Nation " + nation);
                System.out.println("");
                playeridcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playeridcounter;
    }

    private static void addPlayerToCurrentSquad(String P_id, String status_11_or_reserves) {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        String reserves;
        String first11;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            if (status_11_or_reserves.equals("1")) {
                status_11_or_reserves = "Y";
                reserves = "N";
                String sql = "INSERT INTO \"FootballClub\".\"Current Squad\" "
                        + "VALUES ('" + status_11_or_reserves + "' ,'" + reserves + "' ," + P_id + ")";
                stmt.executeUpdate(sql);

            } else if (status_11_or_reserves.equals("2")) {
                status_11_or_reserves = "N";
                first11 = "Y";
                String sql = "INSERT INTO \"FootballClub\".\"Current Squad\" "
                        + "VALUES ('" + status_11_or_reserves + "' ,'" + first11 + "' ," + P_id + ")";
                stmt.executeUpdate(sql);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void viewCurrentSquad() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();

            resultSet = statement.executeQuery("select p.p_name,p.uniform_num ,p.p_position ,first11 from \"FootballClub\".\"Players\" p inner join \"FootballClub\".\"Current Squad\" cs on p.player_id = cs.p_id  \n"
                    + "where first11 = 'Y' ");
            while (resultSet.next()) {
                String name = resultSet.getString("p_name");
                String position = resultSet.getString("p_position");
                int uniformnum = resultSet.getInt("uniform_num");

                System.out.println("name " + name);
                System.out.println("uniformnum " + uniformnum);
                System.out.println("position " + position);
                System.out.println("");
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addPlayerContract() {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int playercounter;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String startDate, endDate, contractStatus, position;
            String auto = "";
            int salary, playerID;
            System.out.println("What is the position of the player you want to add a contract to?");

            positions();
            int selectedposition = scan.nextInt();
            if (selectedposition == 1) {
                position = "Goalkeeper";
            } else if (selectedposition == 2) {
                position = "Defence";
            } else if (selectedposition == 3) {
                position = "Midfielder";
            } else {
                position = "Forward";
            }
            System.out.println("Current Players without contract are:");
            playercounter = viewPlayerDonotHaveContract(position); // sadece contractÄ± olmayanlarÄ± gÃ¶stericek ÅŸekilde ayarla*****
            System.out.println("");
            System.out.println("Now please enter the Player's contract informations and Player's ID that you want to add:");
            System.out.println("Player ID:");
            playerID = scan.nextInt();
            System.out.println("Contract StartDate:");
            startDate = scan.next();
            System.out.println("Contract EndDate:");
            endDate = scan.next();
            System.out.println("Salary:");
            salary = scan.nextInt();
            System.out.println("Contract Status:");
            contractStatus = scan.next();

            String sql = "INSERT INTO \"FootballClub\".\"Player Contract\" "
                    + "VALUES (" + playercounter + ", '" + startDate + "', '" + endDate + "'," + salary + ",'" + contractStatus + "'," + playerID + " )"; //Player counter??
            stmt.executeUpdate(sql);
            System.out.println("Player Contract will successfully added.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void deletePlayerContract() { //OKEY
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int playercounter;
        String position;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int playerID;

            System.out.println("Current Players are:");
            playercounter = viewPlayerContracts();
            System.out.println("");
            System.out.println("Now please enter the Player Contract ID that you want to terminate: ");
            System.out.println("Player ID:");
            playerID = scan.nextInt();
            String sql = "DELETE FROM  \"FootballClub\".\"Player Contract\" "
                    + " WHERE p_id= " + playerID;
            stmt.executeUpdate(sql);
            System.out.println("Contract will successfully deleted.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void addCoachContract() {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int coachcounter;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String startDate, endDate, contractStatus;
            String auto = "";
            int salary, coachID;
            System.out.println("Current Coaches without contract are: ");
            coachcounter = viewCoachesDonotHaveContract();
            System.out.println("");
            System.out.println("Now please enter the Coach's contract informations and Coach's ID that you want to add:");
            System.out.println("Coach ID:");
            coachID = scan.nextInt();
            System.out.println("Contract StartDate:");
            startDate = scan.next();
            System.out.println("Contract EndDate:");
            endDate = scan.next();
            System.out.println("Salary:");
            salary = scan.nextInt();
            System.out.println("Contract Status:");
            contractStatus = scan.next();

            String sql = "INSERT INTO \"FootballClub\".\"Coach Contract\" "
                    + "VALUES ( " + coachID + " , '" + startDate + "', '" + endDate + "'," + salary + ",'" + contractStatus + "'," + coachID + " )";
            stmt.executeUpdate(sql);
            System.out.println("Coaches Contract will successfully added.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void deleteCoachContract() {
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int coachcounter;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int coachID;
            System.out.println("Current Coaches are:");
            coachcounter = viewCoachesContracts();
            System.out.println("");
            System.out.println("Now please enter the Coach Contract ID that you want to terminate: ");
            System.out.println("Coach Contract ID:");
            coachID = scan.nextInt();
            String sql = "DELETE FROM  \"FootballClub\".\"Coach Contract\" "
                    + " WHERE coachid = " + coachID;
            stmt.executeUpdate(sql);
            System.out.println("Contract will successfully deleted.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void addTrainerContract() {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int trcount;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String startDate, endDate, contractStatus;
            //String auto = "";
            int salary, trainerID;
            System.out.println("Current Trainers without contract are: ");
            trcount = viewTrainersDoNotHaveContract();
            System.out.println("");
            System.out.println("Now please enter the Trainer's contract informations and Trainer's ID that you want to add:");
            System.out.println("Trainer ID:");
            trainerID = scan.nextInt();
            System.out.println("Contract StartDate:");
            startDate = scan.next();
            System.out.println("Contract EndDate:");
            endDate = scan.next();
            System.out.println("Salary:");
            salary = scan.nextInt();
            System.out.println("Contract Status:");
            contractStatus = scan.next();

            String sql = "INSERT INTO \"FootballClub\".\"Trainer Contract\" "
                    + "VALUES (" + trainerID + ",'" + startDate + "', '" + endDate + "'," + salary + ",'" + contractStatus + "'," + trainerID + " )";
            stmt.executeUpdate(sql);
            System.out.println("Player Trainer will successfully added.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void deleteTrainerContract() {
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int trainercounter;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int trainerID;
            System.out.println("Current Trainers are:");
            trainercounter = viewTrainersContracts();
            System.out.println("");
            System.out.println("Now please enter the Trainer Contract ID that you want to terminate: ");
            System.out.println("Trainer ID:");
            trainerID = scan.nextInt();
            String sql = "DELETE FROM  \"FootballClub\".\"Trainer Contract\" "
                    + " WHERE t_id = " + trainerID;
            stmt.executeUpdate(sql);
            System.out.println("Contract will successfully deleted.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void addSportiveDirectorContract() {
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int sdcount;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String startDate, endDate, contractStatus;
            String auto = "";
            int salary, sportiveDirectorID;
            System.out.println("Current Sportive Directors without contract are:");
            sdcount = viewSportiveDirectorsDoNotHaveContract();
            System.out.println("");
            System.out.println("Now please enter the Sportive Director's contract informations and Sportive Director's ID that you want to add:");
            System.out.println("Sportive Director ID:");
            sportiveDirectorID = scan.nextInt();
            System.out.println("Contract StartDate:");
            startDate = scan.next();
            System.out.println("Contract EndDate:");
            endDate = scan.next();
            System.out.println("Salary:");
            salary = scan.nextInt();
            System.out.println("Contract Status:");
            contractStatus = scan.next();

            String sql = "INSERT INTO \"FootballClub\".\"Sportive Director Contract\" "
                    + "VALUES ( " + sportiveDirectorID + ",'" + startDate + "', '" + endDate + "'," + salary + ",'" + contractStatus + "'," + sportiveDirectorID + " )";
            stmt.executeUpdate(sql);
            System.out.println("Sportive Director Contract will successfully added.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static int viewCoaches() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"coach\"");
            while (resultSet.next()) {
                int id = resultSet.getInt("coach_id");
                String name = resultSet.getString("c_name");
                int age = resultSet.getInt("c_age");
                String address = resultSet.getString("c_address");
                String phonenumber = resultSet.getString("c_phonenumber");
                String nation = resultSet.getString("c_nation");

                System.out.println("ID: " + id);
                System.out.println("name: " + name);
                System.out.println("age: " + age);
                System.out.println("nation: " + nation);
                System.out.println("address :" + address);
                System.out.println("phonenumber: " + phonenumber);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;
    }

    private static void deleteSportiveDirectorContract() {
        Scanner scan = new Scanner(System.in);
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        int sdcounter;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int sportiveDirectorID;
            System.out.println("Sportive Director ID:");
            sdcounter = viewSportiveDirectorsContracts();
            System.out.println("");
            System.out.println("Now please enter the Sportive Director ID that you want to terminate: ");
            System.out.println("Sportive Director ID:");
            sportiveDirectorID = scan.nextInt();
            String sql = "DELETE FROM  \"FootballClub\".\"Sportive Director Contract\" "
                    + " WHERE sportivedirector_id= " + sportiveDirectorID;
            stmt.executeUpdate(sql);
            System.out.println("Contract will successfully deleted.");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void changeAddressforCoach(String newAddress, int coach_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"coach\" set c_address = '" + newAddress + "' WHERE coach_id = '" + coach_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void changePhoneNumberforCoach(String newphonenumber, int coach_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"coach\" set c_phonenumber = '" + newphonenumber + "' WHERE coach_id = '" + coach_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changeAddressforManager(String newAddress, int manager_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Managers\" set  m_address = '" + newAddress + "' WHERE \"Managers_id\" =  '" + manager_id + "' ");
            System.out.println("Updated Succesfully");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void changePhoneNumberforManager(String newPhoneNumber, int manager_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Managers\" set  m_phonenumber = '" + newPhoneNumber + "' WHERE \"Managers_id\" = '" + manager_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTrainerToPLayer() {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        String position;
        Scanner scan = new Scanner(System.in);
        int playerID, trainerID;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("What is the position of the player you want to assign a trainer to:");
            positions();
            int selectedposition = scan.nextInt();
            if (selectedposition == 1) {
                position = "Goalkeeper";
            } else if (selectedposition == 2) {
                position = "Defence";
            } else if (selectedposition == 3) {
                position = "Midfielder";
            } else {
                position = "Forward";
            }
            viewPlayer(position);
            System.out.println("Now write your Player ID that you want to get trained from a Trainer: ");
            playerID = scan.nextInt();
            System.out.println("Extracting data's about the assignable Trainers(same position with Player)");
            viewTrainers(position);
            System.out.println("Now write your Trainer ID that you want to add to a Player: ");
            trainerID = scan.nextInt();
            // now write SQL update statement onto Players table.
            String sql = "UPDATE \"FootballClub\".\"Players\" "
                    + "SET trainer_id = " + trainerID + "WHERE player_id=" + playerID + "";
            stmt.executeUpdate(sql);
            System.out.println("Trainer Added to Player Successfully.");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void changeAddressforTrainer(String newAddress, int trainer_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Trainers\" set  t_address = '" + newAddress + "' WHERE \"trainer_id\" =  '" + trainer_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changePhoneNumberforTrainer(String newPhoneNumber, int trainer_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Trainers\" set t_phonenumber = '" + newPhoneNumber + "' WHERE trainer_id = '" + trainer_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTalentedPlayerFromU19() {

        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        Scanner scan = new Scanner(System.in);
        String position;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("What is the position of the player you want to assign as Talented:");
            positions();
            int selectedposition = scan.nextInt();
            if (selectedposition == 1) {
                position = "Goalkeeper";
            } else if (selectedposition == 2) {
                position = "Defence";
            } else if (selectedposition == 3) {
                position = "Midfielder";
            } else {
                position = "Forward";
            };
            viewU19PlayerNotInSelectTalented(position); //Should be Which are not in the select_talented Table
            System.out.println("Now write your U-19 Player ID that you want to add as Talented: ");
            int talentedID = scan.nextInt();
            System.out.println("Please enter Your ID:"); //You should know Sportive Director ID
            int sdID = scan.nextInt();

            String sql = "INSERT INTO \"FootballClub\".\"select_talented\" "
                    + "VALUES (" + sdID + "," + talentedID + ")";
            stmt.executeUpdate(sql);
            System.out.println("Player Added as Talented Successfully.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void suggestTalentedPlayertoCoaches() {

        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        Scanner scan = new Scanner(System.in);
        String position;
        int sportiveDirectorID;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            viewSportiveDirectors();//DAHA Ã–NCEDEN SEÃ‡Ä°LMÄ°ÅžLERÄ° GÃ–STERELÄ°M MÄ° GÃ–STERMEYELÄ°M MÄ°?
            System.out.println("Please Write Your ID to see Talented Players that you already choosed:");
            sportiveDirectorID = scan.nextInt();
            viewSDTalentedPlayers(sportiveDirectorID); // with ID's
            System.out.println("Now write your Talented Player ID that you want to suggest to Coaches: ");
            int talentedID = scan.nextInt();
            System.out.println("Finally choose which Coach that you want to suggest a Player: ");
            viewCoaches();
            int CoachID = scan.nextInt();
            String sql = "INSERT INTO \"FootballClub\".\"suggest\" "
                    + "VALUES ("  +sportiveDirectorID+"," + CoachID  +"," + "'NULL'," + talentedID + ")";
            stmt.executeUpdate(sql);
            System.out.println("Player Suggested Succesfully");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void changePhoneNumberforSportiveDirector(String newPhoneNumber, int sd_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Sportive Director\" set sd_phonenumber = '" + newPhoneNumber + "' WHERE sd_id = '" + sd_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changeAddressforSportiveDirector(String newAddress, int sd_id) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            statement.executeUpdate("Update \"FootballClub\".\"Sportive Director\" set  sd_address = '" + newAddress + "' WHERE \"sd_id\" =  '" + sd_id + "' ");

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void positions() {
        System.out.println("[1]Goalkeeper");
        System.out.println("[2]Defence");
        System.out.println("[3]Midfielder");
        System.out.println("[4]Forward");
    }

    private static int viewTrainers() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int traineridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"Trainers\"");
            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String name = resultSet.getString("t_name");
                int age = resultSet.getInt("t_age");
                String phonenumber = resultSet.getString("t_phonenumber");
                String address = resultSet.getString("t_address");

                System.out.println("ID " + id);
                System.out.println("name " + name);
                System.out.println("age " + age);
                System.out.println("address " + address);
                System.out.println("phonenumber " + phonenumber);
                System.out.println("");
                traineridcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traineridcounter;
    }

    private static int viewManagers() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int manageridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"Managers\"");
            while (resultSet.next()) {
                int id = resultSet.getInt("Managers_id");
                String name = resultSet.getString("M-name");
                String positon = resultSet.getString("M-position");
                String address = resultSet.getString("m_address");
                String phonenumber = resultSet.getString("m_phonenumber");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Positon: " + positon);
                System.out.println("Address :" + address);
                System.out.println("PhoneNumber: " + phonenumber);
                System.out.println("");
                manageridcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manageridcounter;

    }

    private static int viewSportiveDirectors() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int sdidcounter = 1;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"Sportive Director\"");
            while (resultSet.next()) {
                int id = resultSet.getInt("sd_id");
                String name = resultSet.getString("sd_name");
                int age = resultSet.getInt("sd_age");
                String address = resultSet.getString("sd_address");
                String phonenumber = resultSet.getString("sd_phonenumber");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Address :" + address);
                System.out.println("PhoneNumber: " + phonenumber);
                System.out.println("");
                sdidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdidcounter;
    }

    private static void makeDecisionAboutSuggestedPlayers() {
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        Scanner scan = new Scanner(System.in);
        String position, decision;
        int coachID;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            viewCoaches();
            System.out.println("");
            System.out.println("Please Write Your ID to recieve suggested Players from Sportive Directors:");
            coachID = scan.nextInt();
            viewCoachTalentedPlayers(coachID);
            System.out.println("Now write your Talented Player ID that you want to make decision: ");
            int talentedID = scan.nextInt();
            System.out.println("What is your decision about this player?: ");
            System.out.println("[1] Accept");
            System.out.println("[2] Decline");
            int selection = scan.nextInt();
            if (selection == 1) {
                decision = "Accept";
            } else {
                decision = "Decline";
            }
            //viewCoaches();
            String sql = "Update \"FootballClub\".\"suggest\" set  decision = '" + decision + "' WHERE \"talented_id\" =  '" + talentedID + "' ";
            stmt.executeUpdate(sql);
            System.out.println("Update Decision Successfully.");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private static void changeTrainerOfaPlayer() {
        //if status_11_or_reserves 1 ise first 11 2 ise reserves olarak tabloya ekleme iÅŸlemini yap.
        Connection conn = null; // BaÄŸlantÄ±
        Statement stmt = null;  //
        String position;
        Scanner scan = new Scanner(System.in);
        int playerID, trainerID;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("What is the position of the player you want to change its trainer: ");
            positions();
            int selectedposition = scan.nextInt();
            if (selectedposition == 1) {
                position = "Goalkeeper";
            } else if (selectedposition == 2) {
                position = "Defence";
            } else if (selectedposition == 3) {
                position = "Midfielder";
            } else {
                position = "Forward";
            }
            viewPlayer(position);
            System.out.println("Now write your Player ID that you want to get trained from a Trainer: ");
            playerID = scan.nextInt();
            System.out.println("Extracting data's about the assignable Trainers(same position with Player)");
            viewTrainers(position);
            System.out.println("Now write your Trainer ID that you want to add to a Player: ");
            trainerID = scan.nextInt();
            // now write SQL update statement onto Players table.
            String sql = "UPDATE \"FootballClub\".\"Players\" "
                    + "SET trainer_id = " + trainerID + "WHERE player_id=" + playerID + "";
            stmt.executeUpdate(sql);
            System.out.println("Trainer has been changed successfully");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static int viewU19PlayerNotInSelectTalented(String position) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int playerU19idcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(" select * from \"FootballClub\".\"Players\" WHERE p_position = '" + position + "' AND  "
                    + "player_status= 'U-19' and player_id not in (select p_id from \"FootballClub\".\"select_talented\")");
            while (resultSet.next()) {
                int id = resultSet.getInt("player_id");
                String name = resultSet.getString("p_name");
                //String position = resultSet.getString("p_position");
                int age = resultSet.getInt("p_age");
                int uniformnum = resultSet.getInt("uniform_num");
                // String clubname = resultSet.getString("club_name");
                int trainerid = resultSet.getInt("trainer_id");
                String playerstatus = resultSet.getString("player_status");
                String nation = resultSet.getString("p_nation");

                System.out.println("ID " + id);
                System.out.println("Name " + name);
                System.out.println("Age " + age);
                System.out.println("Uniform Number " + uniformnum);
                // System.out.println("Clubname " + clubname);
                System.out.println("Trainerid " + trainerid);
                System.out.println("Playerstatus " + playerstatus);
                System.out.println("Nation " + nation);
                System.out.println("");
                playerU19idcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerU19idcounter;
    }

    public static void threadsleep() {
        int i = 0;

        try {
            while (i < 9) {
                Thread.sleep(400);
                if (i == 0) {
                    //System.out.print(" __       __ \n/  |  _  /  |\n$$ | / \\ $$ |\n$$ |/$  \\$$ |\n$$ /$$$  $$ |\n$$ $$/$$ $$ |\n$$$$/  $$$$ |\n$$$/    $$$ |\n$$/      $$/ ");//w
                    System.out.print(" __       __            __                                                      __                      ________ _______         _______   _______   __       __   ______  \n");
                } else if (i == 1) {
                    //System.out.print("          \n  ______  \n /      \\ \n/$$$$$$  |\n$$    $$ |\n$$$$$$$$/ \n$$       |\n $$$$$$$/  ");//e
                    System.out.print("/  |  _  /  |          /  |                                                    /  |                    /        /       \\       /       \\ /       \\ /  \\     /  | /      \\ \n");
                } else if (i == 2) {
                    //System.out.print(" __ \n/  |\n$$ |\n$$ |\n$$ |\n$$ |\n$$ |\n$$ |\n$$/ ");        //l
                    System.out.print("$$ | / \\ $$ |  ______  $$ |  _______   ______   _____  ____    ______         _$$ |_     ______        $$$$$$$$/$$$$$$$  |      $$$$$$$  |$$$$$$$  |$$  \\   /$$ |/$$$$$$  |\n");
                } else if (i == 3) {
                    //System.out.print("  _______ \n /       |\n/$$$$$$$/ \n$$ |      \n$$ \\_____ \n$$       |\n $$$$$$$/ ");//c
                    System.out.print("$$ |/$  \\$$ | /      \\ $$ | /       | /      \\ /     \\/    \\  /      \\       / $$   |   /      \\       $$ |__   $$ |__$$ |      $$ |  $$ |$$ |__$$ |$$$  \\ /$$$ |$$ \\__$$/ \n");
                } else if (i == 4) {
                    //System.out.print("  ______  \n /      \\ \n/$$$$$$  |\n$$ |  $$ |\n$$ \\__$$ |\n$$    $$/ \n $$$$$$/  ");//o
                    System.out.print("$$ /$$$  $$ |/$$$$$$  |$$ |/$$$$$$$/ /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |      $$$$$$/   /$$$$$$  |      $$    |  $$    $$<       $$ |  $$ |$$    $$< $$$$  /$$$$ |$$      \\ \n");
                } else if (i == 4) {
                    System.out.println("$$ $$/$$ $$ |$$    $$ |$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$    $$ |        $$ | __ $$ |  $$ |      $$$$$/   $$$$$$$  |      $$ |  $$ |$$$$$$$  |$$ $$ $$/$$ | $$$$$$  |");
                } else if (i == 5) {
                    //System.out.print(" _____  ____  \n/     \\/    \\ \n$$$$$$ $$$$  |\n$$ | $$ | $$ |\n$$ | $$ | $$ |\n$$ | $$ | $$ |\n$$/  $$/  $$/ \n");//m
                    System.out.print("$$ $$/$$ $$ |$$    $$ |$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$    $$ |        $$ | __ $$ |  $$ |      $$$$$/   $$$$$$$  |      $$ |  $$ |$$$$$$$  |$$ $$ $$/$$ | $$$$$$  |\n");
                } else if (i == 6) {
                    //System.out.print("          \n  ______  \n /      \\ \n/$$$$$$  |\n$$    $$ |\n$$$$$$$$/ \n$$       |\n $$$$$$$/  ");//e
                    System.out.print("$$$$/  $$$$ |$$$$$$$$/ $$ |$$ \\_____ $$ \\__$$ |$$ | $$ | $$ |$$$$$$$$/         $$ |/  |$$ \\__$$ |      $$ |     $$ |__$$ |      $$ |__$$ |$$ |__$$ |$$ |$$$/ $$ |/  \\__$$ |\n");
                } else if (i == 7) {
                    //System.out.print("      \n      \n      \n      \n      \n      "); //boÅŸluk
                    System.out.print("$$$/    $$$ |$$       |$$ |$$       |$$    $$/ $$ | $$ | $$ |$$       |        $$  $$/ $$    $$/       $$ |     $$    $$/       $$    $$/ $$    $$/ $$ | $/  $$ |$$    $$/ \n");
                } else if (i == 8) {
                    //System.out.print("   __     \n  /  |    \n _$$ |_   \n/ $$   |  \n$$$$$$/   \n  $$ | __ \n  $$ |/  |\n  $$  $$/ \n   $$$$/  ");//t
                    System.out.print("$$/      $$/  $$$$$$$/ $$/  $$$$$$$/  $$$$$$/  $$/  $$/  $$/  $$$$$$$/          $$$$/   $$$$$$/        $$/      $$$$$$$/        $$$$$$$/  $$$$$$$/  $$/      $$/  $$$$$$/  \n");
                } else if (i == 99) {
                    //System.out.print("  ______  \n /      \\ \n/$$$$$$  |\n$$ |  $$ |\n$$ \\__$$ |\n$$    $$/ \n $$$$$$/  ");//o
                    System.out.println("$$/      $$/  $$$$$$$/ $$/  $$$$$$$/  $$$$$$/  $$/  $$/  $$/  $$$$$$$/          $$$$/   $$$$$$/        $$/      $$$$$$$/        $$$$$$$/  $$$$$$$/  $$/      $$/  $$$$$$/  \n");
                }
                /*else if (i == 9) {
                    System.out.print("      \n      \n      \n      \n      \n      \n      \n      ");//boÅŸluk
                } else if (i == 10) {
                    System.out.print(" ________\n/        \n$$$$$$$$/\n$$ |__   \n$$    |  \n$$$$$/   \n$$ |     \n$$ |     \n$$/      ");//F
                } else if (i == 11) {
                    System.out.print(" _______  \n/       \\ \n$$$$$$$  |\n$$ |__$$ |\n$$    $$< \n$$$$$$$  |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//B
                } else if (i == 12) {
                    System.out.print("      \n      \n      \n      \n      \n      \n      \n      ");//boÅŸluk

                } else if (i == 13) {
                    System.out.print(" _______  \n/       \\ \n$$$$$$$  |\n$$ |  $$ |\n$$ |  $$ |\n$$ |  $$ |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//D
                } else if (i == 14) {
                    System.out.print(" _______  \n/       \\ \n$$$$$$$  |\n$$ |__$$ |\n$$    $$< \n$$$$$$$  |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//B
                } else if (i == 15) {
                    System.out.print(" __       __ \n/  \\     /  |\n$$  \\   /$$ |\n$$$  \\ /$$$ |\n$$$$  /$$$$ |\n$$ $$ $$/$$ |\n$$ |$$$/ $$ |\n$$ | $/  $$ |\n$$/      $$/ ");//M
                } else if (i == 16) {
                    System.out.print("  ______  \n /      \\ \n/$$$$$$  |\n$$ \\__$$/ \n$$      \\ \n $$$$$$  |\n/  \\__$$ |\n$$    $$/ \n $$$$$$/  ");//S
                }*/

                i++;
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void enterance() {
        System.out.println("");
        System.out.println("                          -------------------\n"
                + "                                                                     \n"
                + "                                                                      \n"
                + "\n"
                + "                                                                 ,/)\n"
                + "                                                                 |_|\n"
                + "           _          _          _          _          _         ].[\n"
                + "          |.|        |.|        |.|        |.|        |.|      /~`-'~\\\n"
                + "          ]^[        ]^[        ]^[        ]^[        ]^[     (<|%  |>)\n"
                + "        /~`-'~\\    /~`-'~\\    /~`-'~\\    /~`-'~\\    /~`-'~\\    \\|___|/\n"
                + "       {<|%  |>}  {<|%  |>}  {<|%  |>}  {<|%  |>}  {<|%  |>}   {/   \\}\n"
                + "        \\|___|/    \\|___|/    \\|___|/    \\|___|/    \\|___|/    /__|__\\\n"
                + "       /\\    \\      /   \\      /   \\      /   \\      /   \\     | / \\ |\n"
                + "       |/>/|__\\    /__|__\\    /__|__\\    /__|__\\    /__|__\\    |/   \\|\n"
                + "      _|)   \\ |    | / \\ |    | / \\ |    | / \\ |    | / \\ |    {}   {}\n"
                + "     (_,|    \\)    (/   \\)    (/   \\)    (/   \\)    (/   \\)    |)   (|\n"
                + "     / \\     (|_  _|)   (|_  _|)   (|_  _|)   (|_  _|)   (|_  _||   ||_\n"
                + "  .,.\\_/,...,|,_)(_,|,.,|,_)(_,|,.,|,_)(_,|,.,|,_)(_,|,.,|,_)(_,|.,.|,_).,.0");

        System.out.println("             ________________________________________________\n"
                + "            /                                                \\\n"
                + "           |    _________________________________________     |\n"
                + "           |   |                                         |    |\n"
                + "           |   |  C:\\>FENERBAHCE DBMS SYSTEM             |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |                                         |    |\n"
                + "           |   |_________________________________________|    |\n"
                + "           |                                                  |\n"
                + "            \\_________________________________________________/\n"
                + "                   \\___________________________________/\n"
                + "                ___________________________________________\n"
                + "             _-'    .-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.  --- `-_\n"
                + "          _-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--.  .-.-.`-_\n"
                + "       _-'.-.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-`__`. .-.-.-.`-_\n"
                + "    _-'.-.-.-.-. .-----.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-----. .-.-.-.-.`-_\n"
                + " _-'.-.-.-.-.-. .---.-. .-------------------------. .-.---. .---.-.-.-.`-_\n"
                + ":-------------------------------------------------------------------------:\n"
                + "`---._.-------------------------------------------------------------._.---'");

        /* System.out.println(" __       __            __                                                      __                      ________ _______         _______   _______   __       __   ______  \n"
                + "/  |  _  /  |          /  |                                                    /  |                    /        /       \\       /       \\ /       \\ /  \\     /  | /      \\ \n"
                + "$$ | / \\ $$ |  ______  $$ |  _______   ______   _____  ____    ______         _$$ |_     ______        $$$$$$$$/$$$$$$$  |      $$$$$$$  |$$$$$$$  |$$  \\   /$$ |/$$$$$$  |\n"
                + "$$ |/$  \\$$ | /      \\ $$ | /       | /      \\ /     \\/    \\  /      \\       / $$   |   /      \\       $$ |__   $$ |__$$ |      $$ |  $$ |$$ |__$$ |$$$  \\ /$$$ |$$ \\__$$/ \n"
                + "$$ /$$$  $$ |/$$$$$$  |$$ |/$$$$$$$/ /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |      $$$$$$/   /$$$$$$  |      $$    |  $$    $$<       $$ |  $$ |$$    $$< $$$$  /$$$$ |$$      \\ \n"
                + "$$ $$/$$ $$ |$$    $$ |$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$    $$ |        $$ | __ $$ |  $$ |      $$$$$/   $$$$$$$  |      $$ |  $$ |$$$$$$$  |$$ $$ $$/$$ | $$$$$$  |\n"
                + "$$$$/  $$$$ |$$$$$$$$/ $$ |$$ \\_____ $$ \\__$$ |$$ | $$ | $$ |$$$$$$$$/         $$ |/  |$$ \\__$$ |      $$ |     $$ |__$$ |      $$ |__$$ |$$ |__$$ |$$ |$$$/ $$ |/  \\__$$ |\n"
                + "$$$/    $$$ |$$       |$$ |$$       |$$    $$/ $$ | $$ | $$ |$$       |        $$  $$/ $$    $$/       $$ |     $$    $$/       $$    $$/ $$    $$/ $$ | $/  $$ |$$    $$/ \n"
                + "$$/      $$/  $$$$$$$/ $$/  $$$$$$$/  $$$$$$/  $$/  $$/  $$/  $$$$$$$/          $$$$/   $$$$$$/        $$/      $$$$$$$/        $$$$$$$/  $$$$$$$/  $$/      $$/  $$$$$$/  \n"
                + "                                                                                                                                                                           \n"
                + "                                                                                                                                                                           \n"
                + "                                                                                                                                                                            ");
/*
        System.out.println(" __       __ \n/  |  _  /  |\n$$ | / \\ $$ |\n$$ |/$  \\$$ |\n$$ /$$$  $$ |\n$$ $$/$$ $$ |\n$$$$/  $$$$ |\n$$$/    $$$ |\n$$/      $$/ ");//w
        System.out.println("          \n  ______  \n /      \\ \n/$$$$$$  |\n$$    $$ |\n$$$$$$$$/ \n$$       |\n $$$$$$$/  ");//e
        System.out.println(" __ \n/  |\n$$ |\n$$ |\n$$ |\n$$ |\n$$ |\n$$ |\n$$/ ");        //l
        System.out.println("  _______ \n /       |\n/$$$$$$$/ \n$$ |      \n$$ \\_____ \n$$       |\n $$$$$$$/ ");//c
        System.out.println("  ______  \n /      \\ \n/$$$$$$  |\n$$ |  $$ |\n$$ \\__$$ |\n$$    $$/ \n $$$$$$/  ");//o
        System.out.println(" _____  ____  \n/     \\/    \\ \n$$$$$$ $$$$  |\n$$ | $$ | $$ |\n$$ | $$ | $$ |\n$$ | $$ | $$ |\n$$/  $$/  $$/ \n");//m
        System.out.println("          \n  ______  \n /      \\ \n/$$$$$$  |\n$$    $$ |\n$$$$$$$$/ \n$$       |\n $$$$$$$/  ");//e
        System.out.println("      \n      \n      \n      \n      \n      "); //boÅŸluk
        System.out.println("   __     \n  /  |    \n _$$ |_   \n/ $$   |  \n$$$$$$/   \n  $$ | __ \n  $$ |/  |\n  $$  $$/ \n   $$$$/  ");//t
        System.out.println("  ______  \n /      \\ \n/$$$$$$  |\n$$ |  $$ |\n$$ \\__$$ |\n$$    $$/ \n $$$$$$/  ");//o
        System.out.println("      \n      \n      \n      \n      \n      \n      \n      ");//boÅŸluk
        System.out.println(" ________\n/        \n$$$$$$$$/\n$$ |__   \n$$    |  \n$$$$$/   \n$$ |     \n$$ |     \n$$/      ");//F
        System.out.println(" _______  \n/       \\ \n$$$$$$$  |\n$$ |__$$ |\n$$    $$< \n$$$$$$$  |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//B
        System.out.println("      \n      \n      \n      \n      \n      \n      \n      ");//boÅŸluk
        System.out.println(" _______  \n/       \\ \n$$$$$$$  |\n$$ |  $$ |\n$$ |  $$ |\n$$ |  $$ |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//D
        System.out.println(" _______  \n/       \\ \n$$$$$$$  |\n$$ |__$$ |\n$$    $$< \n$$$$$$$  |\n$$ |__$$ |\n$$    $$/ \n$$$$$$$/  ");//B
        System.out.println(" __       __ \n/  \\     /  |\n$$  \\   /$$ |\n$$$  \\ /$$$ |\n$$$$  /$$$$ |\n$$ $$ $$/$$ |\n$$ |$$$/ $$ |\n$$ | $/  $$ |\n$$/      $$/ ");//M
        System.out.println("  ______  \n /      \\ \n/$$$$$$  |\n$$ \\__$$/ \n$$      \\ \n $$$$$$  |\n/  \\__$$ |\n$$    $$/ \n $$$$$$/  ");//S*/
    }

    public static void exit() {

        System.out.println(" _                \n"
                + "| |               \n"
                + "| |__  _   _  ___ \n"
                + "| '_ \\| | | |/ _ \\\n"
                + "| |_) | |_| |  __/\n"
                + "|_.__/ \\__, |\\___|\n"
                + "        __/ |     \n"
                + "       |___/      ");
    }

    private static int viewPlayerContracts() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select  p_name, player_id as contractid ,startdate,enddate,salary from \"FootballClub\".\"Player Contract\" cc inner join\n"
                    + "\"FootballClub\".\"Players\" c on p_id = player_id  ");
            while (resultSet.next()) {
                int id = resultSet.getInt("contractid");
                String name = resultSet.getString("p_name");
                String startDate = resultSet.getString("startdate");
                String endDate = resultSet.getString("enddate");
                int salary = resultSet.getInt("salary");

                System.out.println("Contract ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Start date :" + startDate);
                System.out.println("End date: " + endDate);
                System.out.println("Salary: " + salary);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;
    }

    private static int viewCoachesContracts() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select c_name, coachid as contractid ,startdate,enddate,salary from \"FootballClub\".\"Coach Contract\" cc inner join\n"
                    + "\"FootballClub\".\"coach\" c on coachid = coach_id ");
            while (resultSet.next()) {
                int id = resultSet.getInt("contractid");
                String name = resultSet.getString("c_name");
                String startDate = resultSet.getString("startdate");
                String endDate = resultSet.getString("enddate");
                int salary = resultSet.getInt("salary");

                System.out.println("Contract ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Start date :" + startDate);
                System.out.println("End date: " + endDate);
                System.out.println("Salary: " + salary);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;
    }

    private static int viewTrainersContracts() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select  t_name, trainer_id as contractid ,startdate,enddate,salary from \"FootballClub\".\"Trainer Contract\" cc inner join\n"
                    + "\"FootballClub\".\"Trainers\" c on t_id = trainer_id ");
            while (resultSet.next()) {
                int id = resultSet.getInt("contractid");
                String name = resultSet.getString("t_name");
                String startDate = resultSet.getString("startdate");
                String endDate = resultSet.getString("enddate");
                int salary = resultSet.getInt("salary");

                System.out.println("Contract ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Start date :" + startDate);
                System.out.println("End date: " + endDate);
                System.out.println("Salary: " + salary);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;

    }

    private static int viewSportiveDirectorsContracts() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select  sd_name, sd_id as contractid ,startdate,enddate,salary from \"FootballClub\".\"Sportive Director Contract\" cc inner join\n"
                    + "\"FootballClub\".\"Sportive Director\" c on sd_id = sportivedirector_id ");
            while (resultSet.next()) {
                int id = resultSet.getInt("contractid");
                String name = resultSet.getString("sd_name");
                String startDate = resultSet.getString("startdate");
                String endDate = resultSet.getString("enddate");
                int salary = resultSet.getInt("salary");

                System.out.println("Contract ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Start date :" + startDate);
                System.out.println("End date: " + endDate);
                System.out.println("Salary: " + salary);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;
    }

    private static void viewTrainers(String position) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //int traineridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from \"FootballClub\".\"Trainers\" WHERE t_position= '" + position + "'");
            System.out.println("Trainers which their positions are" + position);
            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String name = resultSet.getString("t_name");
                int age = resultSet.getInt("t_age");
                String phonenumber = resultSet.getString("t_phonenumber");
                String address = resultSet.getString("t_address");

                System.out.println("ID " + id);
                System.out.println("name " + name);
                System.out.println("age " + age);
                System.out.println("address " + address);
                System.out.println("phonenumber " + phonenumber);
                System.out.println("");
                //traineridcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void viewSDTalentedPlayers(int sportiveDirectorID) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //int sdidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select sd.sd_name, sd.sd_id,  p.player_id ,p.p_name,p.uniform_num ,p.p_position  "
            		+ "from \"FootballClub\".\"select_talented\" st inner join \"FootballClub\".\"Sportive Director\" sd on  st.sd_id = sd.sd_id inner join\n"
                    + "\"FootballClub\".\"Players\" p   on st.p_id = p.player_id  where st.sd_id = " + sportiveDirectorID + "");
            /*  int id = resultSet.getInt("sd_id");
            String name = resultSet.getString("sd_name");
            System.out.println("Your ID: " + id);
            System.out.println("Your Name: " + name);*/
            while (resultSet.next()) {
                int playerID = resultSet.getInt("player_id");
                String playerName = resultSet.getString("p_name");
                String position = resultSet.getString("p_position");
                System.out.println("Talented Player ID: " + playerID);
                System.out.println("Talented Player Name :" + playerName);
                System.out.println("Position : " + position);
                System.out.println("");
                //sdidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void viewPlayerCurrentSquad(String position) {
        // TODO Auto-generated method stub
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int playeridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select *from \"FootballClub\".\"Players\" p where p.p_position = '" + position + "'"
                    + "and player_id not in (select p_id from \"FootballClub\".\"Current Squad\" cs )");
            while (resultSet.next()) {
                int id = resultSet.getInt("player_id");
                String name = resultSet.getString("p_name");
                int age = resultSet.getInt("p_age");
                int uniformnum = resultSet.getInt("uniform_num");
                String clubname = resultSet.getString("club_name");
                int trainerid = resultSet.getInt("trainer_id");
                String playerstatus = resultSet.getString("player_status");
                String nation = resultSet.getString("p_nation");

                System.out.println("ID " + id);
                System.out.println("name " + name);
                System.out.println("age " + age);
                System.out.println("uniformnum " + uniformnum);
                System.out.println("clubname " + clubname);
                System.out.println("trainerid " + trainerid);
                System.out.println("playerstatus " + playerstatus);
                System.out.println("nation " + nation);
                System.out.println("");
                playeridcounter++;
            }
            resultSet.close();

            statement.close();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void viewCoachTalentedPlayers(int coachID) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //int sdidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select c.c_name, c.coach_id,  p.player_id ,p.p_name,p.uniform_num ,p.p_position  from \"FootballClub\".\"suggest\" s inner join \"FootballClub\".\"coach\" c on  s.c_id = c.coach_id inner join\n"
                    + "\"FootballClub\".\"Players\" p   on s.talented_id = p.player_id  where c.coach_id = " + coachID + "");
            /*int id = resultSet.getInt("coach_id");
            String name = resultSet.getString("c_name");
            System.out.println("Your ID: " + id);
            System.out.println("Your Name: " + name);*/
            while (resultSet.next()) {
                int playerID = resultSet.getInt("player_id");
                String playerName = resultSet.getString("p_name");
                String position = resultSet.getString("p_position");
                System.out.println("Talented Player ID: " + playerID);
                System.out.println("Talented Player Name :" + playerName);
                System.out.println("Position : " + position);
                System.out.println("");
                //sdidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int viewPlayerDonotHaveContract(String position) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(" Select  c.p_name, c.player_id ,c.p_nation, c.uniform_num from  \"FootballClub\".\"Players\" c "
                    + "where c.p_position ='" + position + "'and c.player_id not in (select pc.p_id from \"FootballClub\".\"Player Contract\" pc )");
            while (resultSet.next()) {
                int id = resultSet.getInt("player_id");
                String name = resultSet.getString("p_name");
                String nation = resultSet.getString("p_nation");
                int uniformnum = resultSet.getInt("uniform_num");

                System.out.println("Player ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Nation: " + nation);
                System.out.println("Uniform Number: " + uniformnum);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;

    }

    private static int viewCoachesDonotHaveContract() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int coachidcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select  c.c_name, c.coach_id ,c.c_nation , c.c_age from  \"FootballClub\".\"coach\" c "
                    + "where c.coach_id not in (select pc.c_id from \"FootballClub\".\"Coach Contract\" pc )");
            while (resultSet.next()) {
                int id = resultSet.getInt("coach_id");
                String name = resultSet.getString("c_name");
                String nation = resultSet.getString("c_nation");
                int age = resultSet.getInt("c_age");

                System.out.println("Coach ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Nation :" + nation);
                System.out.println("Age: " + age);
                System.out.println("");
                coachidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachidcounter;
    }

    private static int viewTrainersDoNotHaveContract() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int traineridcounter = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select  t.t_name, t.trainer_id ,t.t_age  from  \"FootballClub\".\"Trainers\" t "
                    + "where t.trainer_id not in (select tc.t_id from \"FootballClub\".\"Trainer Contract\" tc )");
            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String name = resultSet.getString("t_name");
                int age = resultSet.getInt("t_age");

                System.out.println("ID " + id);
                System.out.println("name " + name);
                System.out.println("age " + age);
                System.out.println("");
                traineridcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traineridcounter;

    }

    private static int viewSportiveDirectorsDoNotHaveContract() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int sdidcounter = 1;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select  sd.sd_id, sd.sd_name ,sd.sd_age  from  \"FootballClub\".\"Sportive Director\" sd "
                    + "where sd.sd_id not in (select sdc.sportivedirector_id from \"FootballClub\".\"Sportive Director Contract\" sdc )");
            while (resultSet.next()) {
                int id = resultSet.getInt("sd_id");
                String name = resultSet.getString("sd_name");
                int age = resultSet.getInt("sd_age");

                System.out.println("Sportive Director ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("");
                sdidcounter++;
            }
            resultSet.close();

            statement.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdidcounter;

    }

}
