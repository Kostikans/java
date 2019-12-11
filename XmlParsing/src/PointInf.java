public class PointInf extends Data{
    short len;
    public  PointInf(short _num, long _time, byte _size, byte _type,short _len)
    {
        super(_num,_time,_size,_type);
        len = _len;
    }

    String Print()
    {
        String temp =  super.Print();
        return temp + " " + len;
    }
}
