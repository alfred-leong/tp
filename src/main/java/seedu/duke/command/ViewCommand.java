package seedu.duke.command;

public class ViewCommand extends Command {
    String viewOption;

    public ViewCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        if (parameters[1].startsWith("u/")) {
            this.universityName = parameters[1].substring(2);
            this.viewOption = "UNIVERSITY";
        } else {
            this.viewOption = parameters[1];
            if (parameters[1].equals("DATABASE")) {
                this.universityName = parameters[2].substring(2).replace("_"," ");
            }
        }
    }

    public String getViewOption() {
        return viewOption;
    }
}
