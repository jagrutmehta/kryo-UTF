# CharsetSerializer - for some of the java.nio.charset.Charset classes which are not public - for example UTF-8, UTF-16, UTF-16BE, UTF-16LE, ISO_8859_1*.

Nowhere in kryo community I found this solution but lot of people had this issue. So providing quick code for people who get this issue. 

You are free to copy this code and use it whatever way you want. I have reasonably tested this code but use at your own way and test it. If you find issues, please let me know. 

For your reference: Below exception will be handled by this serializer. 

java.lang.IllegalAccessError: tried to access class sun.nio.cs.UTF_8 from class sun.nio.cs.UTF_8ConstructorAccess
    at sun.nio.cs.UTF_8ConstructorAccess.newInstance(Unknown Source)
    at com.esotericsoftware.kryo.Kryo$DefaultInstantiatorStrategy$1.newInstance(Kryo.java:1234)
    at com.esotericsoftware.kryo.Kryo.newInstance(Kryo.java:1086)
