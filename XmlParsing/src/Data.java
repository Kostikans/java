public class Data {
    //String name;
    short num;
    long time;
    byte size;
    byte type;

    Data(){};
    Data(short _num, long _time, byte _size, byte _type)
    {
        num = _num;
        time = _time;
        size = _size;
        type = _type;
    }

    String Print()
    {
        return time + " " +size + " " +type;
    }

}
