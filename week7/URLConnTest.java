import java.io.BufferedReader;

import java.io.IOException;

import java.io.*;
import java.io.InputStreamReader;

import java.io.OutputStream;

import java.net.HttpURLConnection;

import java.net.URL;

  
/**
*Sample Code to show that you can use Java API's to open Connection
*to a Web server and get results for both GET and POST
*/
public class URLConnTest{



        private static final String USER_AGENT = "Mozilla/5.0";



        private static final String GET_URL = "http://flipacoinapi.com/txt";

        private static final String POST_URL = "http://testing-ground.scraping.pro/login";

        private static final String POST_PARAMS1 = "usr=admin";
        private static final String POST_PARAMS2 = "pwd=12345";



        public static void main(String[] args) throws Exception{



                sendGET();

//                sendPOST();



        }



        private static void sendGET() throws IOException {

            URL obj = new URL(GET_URL);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success

                BufferedReader in = new BufferedReader(new InputStreamReader(

                        con.getInputStream()));

                String inputLine;

                StringBuffer response = new StringBuffer();



                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);

                }

                in.close();



                // print result

                System.out.println(response.toString());

            } else {

                System.out.println("GET request not worked");

            }



        }



        private static void sendPOST() throws IOException {

            URL obj = new URL(POST_URL);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("User-Agent", USER_AGENT);



            // For POST only - START

            con.setDoOutput(true);

            //OutputStream os = con.getInputStream();
OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());

            os.write(POST_PARAMS1);
            os.write(POST_PARAMS2);

            os.flush();

            os.close();

            // For POST only - END



            int responseCode = con.getResponseCode();

            System.out.println("POST Response Code :: " + responseCode);



            if (responseCode == HttpURLConnection.HTTP_OK) { //success

                BufferedReader in = new BufferedReader(new InputStreamReader(

                        con.getInputStream()));

                String inputLine;

                StringBuffer response = new StringBuffer();



                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);

                }

                in.close();



                // print result

                System.out.println(response.toString());

            } else {

                System.out.println("POST request not worked");

            }

        }



}
