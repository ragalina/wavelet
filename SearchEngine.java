import java.util.ArrayList;
import java.net.URI;
import java.io.IOException;

class Handler implements URLHandler {
    ArrayList<String> strings = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strings.add(parameters[1]);
                return (String.format(parameters[1] + " has been added to the ArrayList!"));
            }
            return "";
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            String returnSearch = "This search returns: ";
            if (parameters[0].equals("s")) {
                String term = parameters[1];
                for (String str : strings) {
                    if (str.contains(term)) {
                        returnSearch += str + ", ";
                    }
                }
            }
            return returnSearch;
        } else {
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
