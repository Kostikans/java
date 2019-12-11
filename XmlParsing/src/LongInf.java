public class LongInf extends Data{
    int inf;
    public LongInf(short _num, long _time, byte _size, byte _type,int _inf)
    {
        super(_num,_time,_size,_type);
        inf = _inf;
    }

    String Print()
    {
        String temp =  super.Print();
        return temp + " " + inf;
    }

}
