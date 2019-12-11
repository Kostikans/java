
import javax.print.DocFlavor;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class Main  {
    static Map<String,Vector<Data>> data = new TreeMap<>();
    static XmlParsing obj = new XmlParsing("KNP-173.14.33.58.dat.xml");

    public static void main(String[] args)
            throws Exception
    {
        readBinary("1802.cm1");
    }
    public static  void readBinary(String file_path)
                throws  IOException
    {
        try{
            FileInputStream fin = new FileInputStream(file_path);

            byte[] buff = new byte[16];
            int size;
            int length;
            fin.read(buff,0,16);
            fin.read(buff,0,16);
            while ((size = fin.read(buff, 0, 16)) > 0){
                if(buff[0] == -1 && buff[1] == -1)
                    continue;
                short num;
                long time;
                byte raz;
                byte type;

                num = (short)((buff[0]<<8 & 0xFF00) | (buff[1] & 0xFF));
                time = ((buff[2]<<24 & 0xFF000000) | (buff[3]<<16 & 0xFF0000) | (buff[4]<<8 & 0xFF00) | (buff[5] & 0xFF));
                raz = buff[6];
                type = buff[7];
                if ((buff[7] & 0xFF) == 0) {
                    int inf = ((buff[12]<<24 & 0xFF000000) | (buff[13]<<16 & 0xFF0000) | (buff[14]<<8 & 0xFF00) | (buff[15] & 0xFF));
                    Data temp = new LongInf(num, time, raz, type, inf);

                    if(obj.map.get((int)num) != null) {
                        if (data.get(obj.map.get((int) num)) == null) {
                            Vector<Data> vec = new Vector<>();
                            vec.add(temp);
                            data.put(obj.map.get((int) num), vec);
                        } else {
                              data.get(obj.map.get((int) num)).add(temp);
                          }
                      }
                      else {
                          if (data.get("UNDEFINED") == null) {
                              Vector<Data> vec = new Vector<>();
                              vec.add(temp);
                              data.put("UNDEFINED", vec);
                          } else {
                              data.get("UNDEFINED").add(temp);
                          }
                      }
                   } else if ((buff[7] & 0xFF) == 1) {
                       long j = ((buff[15] & 0xFFL) ) +
                               ((buff[14] & 0xFFL) << 8) +
                               ((buff[13] & 0xFFL) << 16) +
                               ((buff[12] & 0xFFL) << 24) +
                               ((buff[11] & 0xFFL) << 32) +
                               ((buff[10] & 0xFFL) << 40) +
                               ((buff[9] & 0xFFL) << 48) +
                               ((buff[8]) << 56);
                       double inf = Double.longBitsToDouble(j);
                       Data temp = new DoubleInf(num, time, raz, type, inf);
                       if(obj.map.get((int)num) != null) {
                           if (data.get(obj.map.get((int) num)) == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put(obj.map.get((int) num), vec);
                           } else {
                               data.get(obj.map.get((int) num)).add(temp);
                           }
                       }
                       else {
                           if (data.get("UNDEFINED") == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put("UNDEFINED", vec);
                           } else {
                               data.get("UNDEFINED").add(temp);
                           }
                       }
                   } else if ((buff[7] & 0xFF) == 2) {
                       short len = (short)((buff[10]<<8 & 0xFF00) | (buff[11] & 0xFF));
                       int inf = ((buff[12]<<24 & 0xFF000000) | (buff[13]<<16 & 0xFF0000) | (buff[14]<<8 & 0xFF00) | (buff[15] & 0xFF));
                       Data temp = new CodeInf(num, time, raz, type, len, inf);
                       if(obj.map.get((int)num) != null) {
                           if (data.get(obj.map.get((int) num)) == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put(obj.map.get((int) num), vec);
                           } else {
                               data.get(obj.map.get((int) num)).add(temp);
                           }
                       }
                       else {
                           if (data.get("UNDEFINED") == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put("UNDEFINED", vec);
                           } else {
                               data.get("UNDEFINED").add(temp);
                           }
                       }

                   } else if ((buff[7] & 0xFF) == 3) {
                       short len = (short)((buff[10]<<8 & 0xFF00) | (buff[11] & 0xFF));
                       Data temp = new LongInf(num, time, raz, type, len);
                       if(obj.map.get((int)num) != null) {
                           if (data.get(obj.map.get((int) num)) == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put(obj.map.get((int) num), vec);
                           } else {
                               data.get(obj.map.get((int) num)).add(temp);
                           }
                           len = (short) ((int) len - 4);
                           while ((len--) > 0) {
                               fin.read(buff, 0, 1);
                           }
                       }
                       else {
                           if (data.get("UNDEFINED") == null) {
                               Vector<Data> vec = new Vector<>();
                               vec.add(temp);
                               data.put("UNDEFINED", vec);
                           } else {
                               data.get("UNDEFINED").add(temp);
                           }
                           len = (short) ((int) len - 4);
                           while ((len--) > 0) {
                               fin.read(buff, 0, 1);
                           }
                       }
                   }

                }

        }
        catch(Exception e){

    }
        FileWriter writer = new FileWriter("out.txt");
        Vector<String> equal = new Vector<String>( Arrays.asList("Т40", "Т56", "Т57", "Т58",
                "ТSТR2ССD", "Т3АБ", "ТРАБ", "НН", "YАС"));
        for (java.util.Map.Entry<String, Vector<Data>> kv : data.entrySet())
        {
            for(String check : equal)
            {
                if(kv.getKey().equals(check)) {
                    for(Data temp : kv.getValue()) {

                        writer.write(String.valueOf(kv.getKey()) + " ");;
                        writer.write(temp.Print());
                        writer.write("\n");
                    }
                }
            }
        }

        int length = 0;
        for (java.util.Map.Entry<String, Vector<Data>> kv : data.entrySet())
        {
            length += kv.getValue().size();
        }
        writer.write(Integer.toString(length));
        writer.close();
    }

}
