public class CodeInf extends Data {
    short len;
    int inf;
    public CodeInf(short _num, long _time, byte _size, byte _type,short _len,int _inf)
    {
        super(_num,_time,_size,_type);
        inf = _inf;
        len = _len;
    }

    String Print()
    {
        String temp =  super.Print();
        return temp + " " + len + " "+ inf;
    }
}
