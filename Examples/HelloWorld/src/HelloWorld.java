import com.box.boxjavalibv2.*;
import com.box.boxjavalibv2.dao.*;
import com.box.boxjavalibv2.exceptions.*;
import com.box.boxjavalibv2.requests.requestobjects.*;
import com.box.restclientv2.exceptions.*;
import java.io.*;
import java.awt.Desktop;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HelloWorld {

    public static final int PORT = 4000;
    public static final String key = "xlsgvmnjqap64k7is2dktpt56d0ok5gi";
    public static final String secret = "DDAJqvTnAmRXiy2DH01UgZmg5q22pbqL";

    public static void main(String[] args) throws AuthFatalFailureException, BoxServerException, BoxRestException, InterruptedException {

        if (key.equals("YOUR API KEY HERE")) {
            System.out.println("Before this sample app will work, you will need to change the");
            System.out.println("'key' and 'secret' values in the source code.");
            return;
        }

        String code = "";
        String url = "https://www.box.com/api/oauth2/authorize?response_type=code&client_id=" + key;
        try {
            Desktop.getDesktop().browse(java.net.URI.create(url));
            code = getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BoxClient client = getAuthenticatedClient(code);

            // delete the "test.txt" file if it already exists
            for (BoxTypedObject item: client.getFoldersManager().getFolderItems("0", null).getEntries()) {
                if (((BoxItem)item).getName().equals("test.txt")) {
                    client.getFilesManager().deleteFile(item.getId(), null);
                    break;
                }
            }

            for (int i=0; i<25; i++) {
                System.out.println((i+1) + ":");

                // upload file "test.txt"
                System.out.println("  upload");
                BoxFileUploadRequestObject uploadReq = BoxFileUploadRequestObject.uploadFileRequestObject("0", "test.txt", new File("/etc/shells"));
                BoxFile bFile = client.getFilesManager().uploadFile(uploadReq);

                // download file "test.txt"
                System.out.println("  download");
                InputStream fileToDownload = client.getFilesManager().downloadFile(bFile.getId(), null);
                fileToDownload.close();

                // delete file "test.txt"
                System.out.print("  delete");
                client.getFilesManager().deleteFile(bFile.getId(), null);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static BoxClient getAuthenticatedClient(String code) throws BoxRestException,     BoxServerException, AuthFatalFailureException {
        BoxClient client = new BoxClient(key, secret);
        BoxOAuthRequestObject obj = BoxOAuthRequestObject.createOAuthRequestObject(code, key, secret, "http://localhost:" + PORT);
        BoxOAuthToken bt =  client.getOAuthManager().createOAuth(obj);
        client.authenticate(bt);
        return client;
    }


    private static String getCode() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
        while (true)
        {
            String code = "";
            try
            {
                BufferedWriter out = new BufferedWriter (new OutputStreamWriter (socket.getOutputStream ()));
                out.write("HTTP/1.1 200 OK\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("\r\n");

                code = in.readLine ();
                System.out.println (code);
                String match = "code";
                int loc = code.indexOf(match);

                if( loc >0 ) {
                    int httpstr = code.indexOf("HTTP")-1;
                    code = code.substring(code.indexOf(match), httpstr);
                    String parts[] = code.split("=");
                    code=parts[1];
                    out.write("Now return to command line to see the output of the HelloWorld sample app.");
                } else {
                    // It doesn't have a code
                    out.write("Code not found in the URL!");
                }

                out.close();

                return code;
            }
            catch (IOException e)
            {
                //error ("System: " + "Connection to server lost!");
                System.exit (1);
                break;
            }
        }
        return "";
    }

}
