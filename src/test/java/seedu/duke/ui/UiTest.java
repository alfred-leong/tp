package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.user.UserModuleMapping;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testGreeting() {
        String expectedGreeting = "                        _____ ______ _____  \n"
                + "                       / ____|  ____|  __ \\ \n"
                + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
                + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
                + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
                + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
                + "                  __/ |                     \n"
                + "                 |___/                      \n"
                + "Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)\n"
                + "How may I help you today?\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGreeting, Ui.greetUser());
    }

    @Test
    public void testGoodbye() {
        String expectedGoodbye = "_____________________________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGoodbye, Ui.sayByeToUser());
    }

    @Test
    public void testGetUserInputWithWhiteSpaces() {
        String input = "   /create    ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("/create", Ui.getUserInput());
    }

    @Test
    public void testPrintCommands() {
        String expected = "_____________________________________________________________________________\n"
                + "     " + "COMMAND   " + "FORMAT                                  " + "PURPOSE\n"
                + "     " + "--------  " + "--------------------------------------  " + "-------\n"
                + "     " + "create    " + "/create u/UNIVERSITY                    "
                + "Creates an empty module list for the input university\n"
                + "     " + "favourite " + "/favourite /add u/UNIVERSITY            "
                + "Adds a university list to the user's favourites\n"
                + "     " + "favourite " + "/favourite /del u/UNIVERSITY            "
                + "Deletes a university list from the user's favourites\n"
                + "     " + "favourite " + "/favourite /view                        "
                + "View the user's favourite university lists\n"
                + "     " + "exit      " + "/exit\n"
                + "     " + "view      " + "/view LISTS                             "
                + "Displays all existing university lists that have been created by the user\n"
                + "     " + "view      " + "/view u/UNIVERSITY                      "
                + "Displays all the modules that have been added to the user's input university's list in the format:\n"
                + "                                                       "
                + "[Home University Module Code] [Home University Module Title] | "
                + "[Partner University Module Code] [Partner University Module Title] | [Equivalent NUS Credits]\n"
                + "     " + "view      " + "/view DELETE HISTORY                    "
                + "Displays up to 5 most recent modules that the user has deleted\n"
                + "     " + "list      " + "/list MODULES                           "
                + "Displays all existing university modules mappings that are approved in the format:\n"
                + "                                                       "
                + "[Partner University Module Code] [Partner University Module Title] "
                + "[Partner University Module Credits] | [NUS Module Code] | [NUS Module Title] | "
                + "[NUS Module Credits] in NUS\n"
                + "     " + "list      " + "/list UNIVERSITIES                      "
                + "Displays all universities with module mappings available in database\n"
                + "     " + "list      " + "/list m/MODULECODE                      "
                + "List all module mappings for NUS MODULECODE in database\n"
                + "     " + "list      " + "/list u/UNIVERSITY                      "
                + "List all module mappings offered by UNIVERSITY in database\n"
                + "     " + "add       " + "/add u/UNIVERSITY m/MODULECODE          "
                + "Add input Partner University module code to input university list                       \n"
                + "     " + "delete    " + "/delete u/UNIVERSITY m/MODULECODE       "
                + "Remove input Partner University module code from input university list                  \n"
                + "     " + "delete    " + "/delete u/UNIVERSITY                    "
                + "Delete input university list                        \n\n"
                + "     " + "Note: Words in UPPER_CASE are parameters that you should input as a user\n"
                + "     " + "Note: There should not be spaces in parameters, replace with underscore instead\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printCommands());
    }

    @Test
    public void testPrintModule() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
            + " | Partner University: " + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs";
        assertEquals(expected, Ui.printModule(dummy));
    }

    @Test
    public void testPrintModuleAddedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You added:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleAddedAcknowledgement(dummy));
    }

    @Test
    public void testPrintModuleUpdatedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        dummy.setComment("A+ or nothing");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You updated:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs" + "\n"
                + "With the following comment: " + dummy.getComment() + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleUpdatedAcknowledgement(dummy));

    }

    @Test
    public void testPrintModuleDeletedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You deleted:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: "
                + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleDeletedAcknowledgement(dummy));
    }

    @Test
    public void testPrintPuListCreatedAcknowledgement() {
        String expected = "_____________________________________________________________________________\n"
                + "Success! You have created a new list for " + "Stanford University" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListCreatedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintPuListDeletedAcknowledgement() {
        String expected = "_____________________________________________________________________________\n"
                + "Success! You deleted the list for " + "Stanford University" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListDeletedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintModulesInList() {
        ArrayList<UserModuleMapping> modules = new ArrayList<UserModuleMapping>();
        UserModuleMapping dummy1 = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        UserModuleMapping dummy2 = new UserModuleMapping("CPSC456", "ML",
                "CS3244", "Machine Learning", "4",
                "test", "test", "test");
        modules.add(dummy1);
        modules.add(dummy2);
        String expected = "_____________________________________________________________________________\n" + "1. "
                + "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
                + " | Partner University: " + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "2. " + "NUS: " + "CS3244" + " " + "Machine Learning"
                + " | Partner University: " + "CPSC456" + " " + "ML" + " | Equivalent NUS Credits: " +  "4 MCs" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModulesInUserList(modules));
    }
}
