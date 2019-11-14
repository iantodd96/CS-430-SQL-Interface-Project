import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUI extends JFrame {
    static JTextArea contentArea = new JTextArea("Please log in using the buttons above.", 30, 60);
    static JLabel usernameEntryBoxLabel = new JLabel("Name:");
    static JTextArea usernameEntryBox = new JTextArea("", 1, 15);
    static JLabel ssnEntryBoxLabel = new JLabel("SSN:");
    static JTextArea ssnEntryBox = new JTextArea("", 1, 15);

    static String username;
    static String ssn;

    public static void setCredentials(String newUserName, String newSsn) {
        username = newUserName;
        ssn = newSsn;
    }

    public static String getUserName() {
        return username;
    }

    public static String getSsn() {
        return ssn;
    }

    public static Connection connect() {

        String sqlUsername = "jdbc-program";
        String sqlPassword = "password";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityDB", sqlUsername, sqlPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean sqlLoginIsValid(String itemType, String userType, String conditionCriteria) {

        Connection conn = connect();

        boolean isValid = false;
        Statement stmt = null;
        String query =
                ("SELECT " + itemType + " FROM " + userType
                        + " WHERE(" + itemType + "=\'" + conditionCriteria + "\');");
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String result = rs.getString(itemType);
                if (result.equals(conditionCriteria)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static void startProgram() {

        int WINDOW_WIDTH = 800;
        int WINDOW_HEIGHT = 600;

        JFrame main = new JFrame("University Data Manager");
        main.setDefaultCloseOperation(3);
        main.setVisible(true);
        main.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        String[] loginStrings = new String[]{"Log in as...", "Student", "Professor", "Administrator"};
        final JComboBox loginType = new JComboBox(loginStrings);
        String[] adminCommands = new String[]{"UNDER CONSTRUCTION"};
        String[] studentCommands = new String[]{"UNDER CONSTRUCTION"};
        loginType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (loginType.getSelectedIndex()) {
                    case 0:
                        GUI.contentArea.setText("Please make a valid login selection.");
                        break;
                    case 1:
                        GUI.contentArea.setText("You've chosen to log in as a student. \nPlease fill in the fields above and press the \"Log In\" button.");
                        break;
                    case 2:
                        GUI.contentArea.setText("You've chosen to log in as a professor. \nPlease fill in the fields above and press the \"Log In\" button.");
                        break;
                    case 3:
                        GUI.contentArea.setText("You've chosen to log in as an administrator. \nPlease fill in the fields above and press the \"Log In\" button.");
                        break;
                    default:
                        GUI.contentArea.setText("ERROR.");
                }
            }
        });

        JButton loginButton = new JButton("Log In");
        JButton logoutButton = new JButton("Log Out");
        JPanel loginArea = new JPanel();
        loginArea.add(loginType);
        loginArea.add(usernameEntryBoxLabel);
        loginArea.add(usernameEntryBox);
        loginArea.add(ssnEntryBoxLabel);
        loginArea.add(ssnEntryBox);
        loginArea.add(loginButton);

        JPanel contentPanel = new JPanel();
        contentPanel.add(contentArea);
        contentArea.setEditable(false);

        GroupLayout contentLayout = new GroupLayout(contentArea);
        contentLayout.setAutoCreateGaps(true);
        contentLayout.setAutoCreateContainerGaps(true);

        //Student action elements.
        String[] studOptionsStrings = {"Enroll in", "Drop", "Search for"};
        JComboBox studOptions = new JComboBox(studOptionsStrings);
        String[] studSearchOptionsStrings = {"Course ID", "Department"};
        JComboBox studSearchOptions = new JComboBox(studSearchOptionsStrings);
        JTextArea studTextInput = new JTextArea("", 1, 10);
        JButton studAccept = new JButton("Accept");

        JButton studListClasses = new JButton("List Your Classes");

        //Professor action elements.
        JLabel profLabel = new JLabel("Search for:");
        String[] profOptionsStrings = {"Student", "Professor", "Administrator", "Department", "Course"};
        JLabel profSearchOptionsLabel = new JLabel("by");
        JComboBox profOptions = new JComboBox(profOptionsStrings);
        String[] profStudentSearchStrings = {"Name", "Degree Type", "Major Department"};
        JComboBox profStudentSearchOptions = new JComboBox(profStudentSearchStrings);
        String[] profProfessorSearchStrings = {"Name", "Specialty"};
        JComboBox profProfessorSearchOptions = new JComboBox(profProfessorSearchStrings);
        String[] profAdminSearchStrings = {"Name"};
        JComboBox profAdminSearchOptions = new JComboBox(profAdminSearchStrings);
        String[] profDeptSearchStrings = {"Name", "Room ID"};
        JComboBox profDeptSearchOptions = new JComboBox(profDeptSearchStrings);
        String[] profCourseSearchStrings = {"Course ID"};
        JComboBox profCourseSearchOptions = new JComboBox(profCourseSearchStrings);
        JButton profListClasses = new JButton("List Your Classes");
        JTextArea profInput = new JTextArea("", 1, 10);
        JButton profAcceptInput = new JButton("Accept");

        //Administrator action elements. Many have been commented out and left unused due to time constraints.
        /*String[] adminOptionsStrings = {"Add", "Delete", "Alter", "Search for"};
        JComboBox adminOptions = new JComboBox(adminOptionsStrings);
        JTextArea adminInput1 = new JTextArea("", 1, 10);
        JLabel adminItemTypeLabel = new JLabel("of type");
        JTextArea adminInput2 = new JTextArea("", 1, 10);
        JLabel adminTableOptionsLabel = new JLabel("in table");
        String[] adminTableOptionsStrings = {"Students", "Professors", "Administrators", "Departments", "Courses", "Enrollment"};
        JComboBox adminTableOptions = new JComboBox(adminTableOptionsStrings);*/
        JTextArea adminInput = new JTextArea(3, 40);
        JButton adminAcceptButton = new JButton("Accept");

        JPanel actionPanel = new JPanel();

        main.add(loginArea, "First");
        main.add(contentPanel, "Center");
        main.add(actionPanel, "Last");
        main.pack();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                setCredentials(usernameEntryBox.getText(), ssnEntryBox.getText());

                switch (loginType.getSelectedIndex()) {
                    case 0:
                        contentArea.setText("Sorry, you need to make a valid login selection from the dropdown box.");
                        break;
                    case 1:
                        if (sqlLoginIsValid("name", "Students", getUserName())
                                && sqlLoginIsValid("stud_ssn", "Students", getSsn())) {
                            contentArea.setText("Login successful. Make a selection below.");
                            ssnEntryBox.setText("");
                            actionPanel.add(studOptions);
                            actionPanel.add(studSearchOptions);
                            studSearchOptions.setVisible(false);
                            actionPanel.add(studTextInput);
                            actionPanel.add(studAccept);
                            actionPanel.add(studListClasses);
                            actionPanel.add(logoutButton);
                        } else {
                            contentArea.setText("User not found. Please make sure your information is correct and try again.");
                        }
                        break;
                    case 2:
                        if (sqlLoginIsValid("name", "Professors", username) == true
                                && sqlLoginIsValid("prof_ssn", "Professors", getSsn()) == true) {
                            contentArea.setText("Login successful. Make a selection below.");
                            actionPanel.removeAll();
                            actionPanel.add(profLabel);
                            actionPanel.add(profOptions);
                            actionPanel.add(profSearchOptionsLabel);
                            actionPanel.add(profStudentSearchOptions);
                            profStudentSearchOptions.setVisible(true);
                            actionPanel.add(profProfessorSearchOptions);
                            profProfessorSearchOptions.setVisible(false);
                            actionPanel.add(profAdminSearchOptions);
                            profAdminSearchOptions.setVisible(false);
                            actionPanel.add(profDeptSearchOptions);
                            profDeptSearchOptions.setVisible(false);
                            actionPanel.add(profCourseSearchOptions);
                            profCourseSearchOptions.setVisible(false);
                            actionPanel.add(profInput);
                            actionPanel.add(profAcceptInput);
                        } else {
                            contentArea.setText("User not found. Please make sure your information is correct and try again.");
                        }
                        break;
                    case 3:
                        if (sqlLoginIsValid("name", "Administrators", username) == true
                                && sqlLoginIsValid("admin_ssn", "Administrators", getSsn()) == true) {
                            contentArea.setText("Login successful. Welcome, Administrator. Type your SQL command below. \n(Be sure to use proper MySQL syntax.)");
                            //This code is meant for administrator functions, but was never used because of time constraints.
                            /*actionPanel.add(adminOptions);
                            actionPanel.add(adminInput1);
                            actionPanel.add(adminItemTypeLabel);
                            actionPanel.add(adminInput2);
                            actionPanel.add(adminTableOptionsLabel);
                            adminTableOptionsLabel.setVisible(true);
                            actionPanel.add(adminTableOptions);*/
                            actionPanel.removeAll();
                            actionPanel.add(adminInput);
                            actionPanel.add(adminAcceptButton);
                            actionPanel.add(logoutButton);
                        } else {
                            contentArea.setText("User not found. Please make sure your information is correct and try again.");
                        }
                        break;
                    default:
                        contentArea.setText("ERROR. Contact the system administrator.");
                        break;
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.contentArea.setText("Logged out.");
                usernameEntryBox.setText("");
                ssnEntryBox.setText("");
                actionPanel.removeAll();
            }
        });

        //Student Action Listeners & SQL Statements.

        studAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (studOptions.getSelectedIndex()) {
                    case 0:
                        //Enroll
                        sqlStudentEnroll(studTextInput.getText(), getSsn());
                        break;
                    case 1:
                        sqlStudentDropCourse(studTextInput.getText(), getSsn());
                        break;
                    case 2:
                        switch (studSearchOptions.getSelectedIndex()) {
                            case 0:
                                //Student search by class ID
                                sqlStudentClassSearch("course_id", studTextInput.getText());
                                break;
                            default:
                                break;
                        }
                    default:
                        break;
                }
            }
        });

        studListClasses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sqlListStudentClasses(getSsn());
            }
        });

        studOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (studOptions.getSelectedIndex()) {
                    case 0:
                        //Enroll
                        studSearchOptions.setVisible(false);
                        break;
                    case 1:
                        //Drop
                        studSearchOptions.setVisible(false);
                        break;
                    case 2:
                        //Search for class
                        studSearchOptions.setVisible(false);
                        break;
                    default:
                        contentArea.setText("ERROR.");
                }
            }
        });

        //Professor Action Listeners
        profAcceptInput.addActionListener(new ActionListener() {
            String itemType;
            String tableName;
            int columns = 0;

            public void actionPerformed(ActionEvent e) {
                switch (profOptions.getSelectedIndex()) {
                    case 0:
                        tableName = "Students";
                        columns = 5;
                        switch (profStudentSearchOptions.getSelectedIndex()) {
                            case 0:
                                //Name
                                itemType = "name";
                                break;
                            case 1:
                                //Degree
                                itemType = "deg_prog";
                                break;
                            case 2:
                                //Major Dept
                                itemType = "major_dept";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        tableName = "Professors";
                        columns = 4;
                        switch (profProfessorSearchOptions.getSelectedIndex()) {
                            case 0:
                                //Name
                                itemType = "name";
                                break;
                            case 1:
                                //Specialty
                                itemType = "specialty";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        tableName = "Administrators";
                        columns = 2;
                        switch (profAdminSearchOptions.getSelectedIndex()) {
                            case 0:
                                //Name
                                itemType = "name";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        tableName = "Departments";
                        columns = 3;
                        switch (profDeptSearchOptions.getSelectedIndex()) {
                            case 0:
                                itemType = "dept_name";
                                //Name
                                break;
                            case 1:
                                //Room ID
                                itemType = "office";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        tableName = "Courses";
                        columns = 5;
                        switch (profCourseSearchOptions.getSelectedIndex()) {
                            case 0:
                                //Course ID
                                itemType = "course_id";
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                sqlProfessorSearch(itemType, tableName, profInput.getText(), columns);
            }
        });

        profOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (profOptions.getSelectedIndex()) {
                    case 0:
                        profStudentSearchOptions.setVisible(true);
                        profProfessorSearchOptions.setVisible(false);
                        profAdminSearchOptions.setVisible(false);
                        profDeptSearchOptions.setVisible(false);
                        profCourseSearchOptions.setVisible(false);
                        break;
                    case 1:
                        profStudentSearchOptions.setVisible(false);
                        profProfessorSearchOptions.setVisible(true);
                        profAdminSearchOptions.setVisible(false);
                        profDeptSearchOptions.setVisible(false);
                        profCourseSearchOptions.setVisible(false);
                        break;
                    case 2:
                        profStudentSearchOptions.setVisible(false);
                        profProfessorSearchOptions.setVisible(false);
                        profAdminSearchOptions.setVisible(true);
                        profDeptSearchOptions.setVisible(false);
                        profCourseSearchOptions.setVisible(false);
                        break;
                    case 3:
                        profStudentSearchOptions.setVisible(false);
                        profProfessorSearchOptions.setVisible(false);
                        profAdminSearchOptions.setVisible(false);
                        profDeptSearchOptions.setVisible(true);
                        profCourseSearchOptions.setVisible(false);
                        break;
                    case 4:
                        profStudentSearchOptions.setVisible(false);
                        profProfessorSearchOptions.setVisible(false);
                        profAdminSearchOptions.setVisible(false);
                        profDeptSearchOptions.setVisible(false);
                        profCourseSearchOptions.setVisible(true);
                        break;
                    default:
                        contentArea.setText("ERROR.");
                        break;
                }
            }
        });

        //Administrator Action Listeners

        adminAcceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sqlAdminCommandParser(adminInput.getText());
            }
        });

        //This action listener was never used due to time constraints.
        /*adminAcceptButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(adminOptions.getSelectedIndex()){
                    case 0:
                        //Add
                        switch(adminTableOptions.getSelectedIndex()){
                            case 0:
                                //Students
                                break;
                            case 1:
                                //Professors
                                break;
                            case 2:
                                //Administrators
                                break;
                            case 3:
                                //Departments
                                break;
                            case 4:
                                //Courses
                                break;
                            case 5:
                                //Enrollment
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        //Delete
                        switch(adminTableOptions.getSelectedIndex()){
                            case 0:
                                //Students
                                break;
                            case 1:
                                //Professors
                                break;
                            case 2:
                                //Administrators
                                break;
                            case 3:
                                //Departments
                                break;
                            case 4:
                                //Courses
                                break;
                            case 5:
                                //Enrollment
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        //Alter
                        switch(adminTableOptions.getSelectedIndex()){
                            case 0:
                                //Students
                                break;
                            case 1:
                                //Professors
                                break;
                            case 2:
                                //Administrators
                                break;
                            case 3:
                                //Departments
                                break;
                            case 4:
                                //Courses
                                break;
                            case 5:
                                //Enrollment
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        //Search for
                        switch(adminTableOptions.getSelectedIndex()){
                            case 0:
                                //Students
                                break;
                            case 1:
                                //Professors
                                break;
                            case 2:
                                //Administrators
                                break;
                            case 3:
                                //Departments
                                break;
                            case 4:
                                //Courses
                                break;
                            case 5:
                                //Enrollment
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        });*/

    }

    //Student SQL Methods
    public static int sqlQueryCurrentEnrollment(String courseID) {
        Connection conn = connect();
        int currentEnrollment = 0;

        Statement stmt = null;
        String query =
                ("SELECT current_enrollment FROM Courses" +
                        " WHERE ( course_id = \'" + courseID + "\');");
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                currentEnrollment = rs.getInt("current_enrollment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            contentArea.setText("ERROR. Check to see if the course ID is correct. (It's case sensitive!)");
        }

        return currentEnrollment;
    }

    public static int sqlQueryMaxEnrollment(String courseID) {
        Connection conn = connect();
        int maxEnrollment = 0;

        Statement stmt;
        String query =
                ("SELECT max_enrollment FROM Courses" +
                        " WHERE ( course_id = \'" + courseID + "\');");
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                maxEnrollment = rs.getInt("max_enrollment");
                if (maxEnrollment == 0) {
                    contentArea.setText("ERROR. Check to see if the course ID is correct. (It's case sensitive!)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            contentArea.setText("ERROR. Check to see if the course ID is correct. (It's case sensitive!)");
        }

        return maxEnrollment;
    }

    public static void sqlListStudentClasses(String stud_ssn) {

        contentArea.setText("");

        Connection conn = connect();

        Statement stmt;
        String queryCourseID =
                ("SELECT course_id FROM Enrollment" +
                        " WHERE ( stud_ssn = \'" + stud_ssn + "\');");
        System.out.println(queryCourseID);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryCourseID);
            while (rs.next()) {
                contentArea.append(" " + rs.getString("course_id") + "\n");
            }
        } catch (SQLException e) {
            contentArea.setText("Error. Failed to list classes.");
            e.printStackTrace();
        } finally {
        }
    }

    public static void sqlStudentClassSearch(String itemType, String criteria) {

        contentArea.setText("");

        Connection conn = connect();

        Statement stmt;
        String query =
                ("SELECT * FROM Courses" +
                        " WHERE ( " + itemType + " = \'" + criteria + "\');");
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    contentArea.append(" " + rs.getString(i) + " ");
                }
                contentArea.append("\n");
            }
        } catch (SQLException e) {
            contentArea.setText("Error. Search failed.");
            e.printStackTrace();
        } finally {
        }
    }

    public static void sqlStudentEnroll(String courseID, String stud_ssn) {

        Connection conn = connect();

        int maxEnrollment = sqlQueryMaxEnrollment(courseID);
        int currentEnrollment = sqlQueryCurrentEnrollment(courseID);
        System.out.println("Max enrollment: " + maxEnrollment + " Current enrollment: " + currentEnrollment);

        Statement stmt;
        String queryCourseID =
                ("SELECT course_id FROM Courses" +
                        " WHERE ( course_id = \'" + courseID + "\');");
        System.out.println(queryCourseID);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryCourseID);
            if (rs.next()) {
                String result = rs.getString("course_id");
                if (result.equals(courseID) && currentEnrollment < maxEnrollment) {
                    contentArea.setText("Successfully enrolled in " + rs.getString("course_id"));
                    String insert = ("INSERT INTO Enrollment " +
                            " VALUES( \'" + courseID + "\', \'" + stud_ssn + "\');");
                    String update = ("UPDATE Courses" +
                            " SET current_enrollment = " + Integer.toString(currentEnrollment + 1) +
                            " WHERE (course_id = " + "\'" + courseID + "\');");
                    int updateSuccess = stmt.executeUpdate(insert);
                    System.out.println(updateSuccess);
                    stmt.executeUpdate(update);
                    System.out.println(updateSuccess);
                }
                if (currentEnrollment == 0 && maxEnrollment == 0) {
                    contentArea.setText("That class wasn't found. Check to make sure the ID is correct. (It's case sensitive!)");
                }
                if (currentEnrollment >= maxEnrollment && maxEnrollment > 0) {
                    contentArea.setText("The class you requested is full.");
                }
            }
        } catch (SQLException e) {
            contentArea.setText("Error. Failed to drop the class.");
            e.printStackTrace();
        } finally {
        }
    }

    public static void sqlStudentDropCourse(String courseID, String stud_ssn) {

        Connection conn = connect();

        int maxEnrollment = sqlQueryMaxEnrollment(courseID);
        int currentEnrollment = sqlQueryCurrentEnrollment(courseID);
        System.out.println("Max enrollment: " + maxEnrollment + " Current enrollment: " + currentEnrollment);

        Statement stmt;
        String queryCourseID =
                ("SELECT course_id FROM Enrollment" +
                        " WHERE ( course_id = \'" + courseID + "\' AND stud_ssn = \'" + stud_ssn + "\');");
        System.out.println(queryCourseID);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryCourseID);
            if (rs.next()) {
                String result = rs.getString("course_id");
                if (result.equals(courseID)) {
                    contentArea.setText("Successfully dropped " + rs.getString("course_id"));
                    String delete = ("DELETE FROM Enrollment" +
                            " WHERE course_id = \'" + courseID + "\' AND stud_ssn = \'" + stud_ssn + "\'");
                    System.out.println(delete);
                    String update = ("UPDATE Courses" +
                            " SET current_enrollment = " + Integer.toString(currentEnrollment - 1) +
                            " WHERE (course_id = " + "\'" + courseID + "\');");
                    System.out.println(update);
                    int updateSuccess = stmt.executeUpdate(delete);
                    stmt.executeUpdate(update);
                    System.out.println(updateSuccess);
                }
                if (currentEnrollment == 0 && maxEnrollment == 0) {
                    contentArea.setText("That class wasn't found. Check to make sure the ID is correct. (It's case sensitive!)");
                }
            }
        } catch (SQLException e) {
            contentArea.setText("Error. Enrollment failed.");
            e.printStackTrace();
        } finally {
        }
    }

    //Professor SQL Action Listeners
    public static void sqlProfessorSearch(String itemType, String tableName, String criteria, int columns) {

        System.out.println("ITEM TYPE: " + itemType + " TABLE NAME: " + tableName + " CRITERIA: " + criteria);

        contentArea.setText("");

        Connection conn = connect();

        Statement stmt;
        String query =
                ("SELECT * FROM " + tableName +
                        " WHERE ( " + itemType + " = \'" + criteria + "\');");
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    contentArea.append(" " + rs.getString(i) + " ");
                }
                contentArea.append("\n");
            }
        } catch (SQLException e) {
            contentArea.setText("Error. Search failed.");
            e.printStackTrace();
        } finally {
        }
    }

    public static void sqlAdminCommandParser(String adminInput) {

        Connection conn = connect();

        Statement stmt;
        String command = adminInput;
        try {
            stmt = conn.createStatement();
            if (adminInput.contains("SELECT")) {
                ResultSet rs = stmt.executeQuery(command);
                contentArea.setText("");
                while(rs.next()) {
                    int columns = rs. getMetaData().getColumnCount();
                    for(int i = 1; i <= columns; i++){
                        contentArea.append(rs.getString(i) + " ");
                    }
                    contentArea.append("\n");
                    }
                }
            else{
                stmt.executeUpdate(command);
                contentArea.setText("The command was executed successfully.");
            }
            }catch (SQLException e) {
            contentArea.setText("Error. Please check your syntax and try again.");
            e.printStackTrace();
        } finally {
        }
    }


    public static void main(String[] args) {
        startProgram();
    }

}
