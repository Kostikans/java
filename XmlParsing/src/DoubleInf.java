public class DoubleInf extends Data {
    double inf;
    public DoubleInf(short _num, long _time, byte _size, byte _type,double _inf)
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
