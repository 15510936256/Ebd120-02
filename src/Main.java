import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //https://ready.arl.noaa.gov/hypubout/157786_trj001.gif

    public static void main (String argus[]) throws ClientProtocolException, IOException, InterruptedException {

        String basic1 = "https://ready.arl.noaa.gov/hypubout/";
        String basic2 = "_trj001.gif";
        String year = "19" , month = "10" , day = "12" ,hour  = "02",  duration = "168";

        if(argus.length < 5 || argus.length > 5)
        {
            System.out.println("The number of imput arguments is wrong.");
        }
        else
        {

//            year = argus[0];
//            month = argus[1];
//            day = argus[2];
//            hour = argus[3];
//            duration = argus[4];
            List< List<String>>  data = GetCityInfor.getCityInfo();
            List<String> errorCity = new ArrayList<String>();
            int length = data.size();
            for( int i = 0; i< data.size() ; i++)
            {
                String city = data.get(i).get(0);
                String lon =  data.get(i).get(1);
                String lat = data.get(i).get(2);
                String number  =  NaooTest.getInstance().test(lat , lon, year, month, day, hour , duration);
                System.out.println("Begin to get picture.");
                if (number != "-1") {
                    System.out.println("Job number:" + number);
                } else {
                    System.out.println("Error. Cann't get job number.");
                    errorCity.add(city);

                }
                String url = basic1 + number + basic2;
                String storeUrl = "D:\\weatherPicture";
                String name = city + year + month + day +".gif";
                Thread.sleep(1000 * 10);   // 休眠10秒
                System.out.println("Picture url:" + url);
                DownloadPicture.download(url, storeUrl, name);
                System.out.println("Now/Total:" + (i+1)+"/"+length);
            }
            System.out.println("These cities don't download ok.");
            for( String s : errorCity)
            {
                System.out.println(s);
            }

        }

    }



}