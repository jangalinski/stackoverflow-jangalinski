import com.google.common.io.BaseEncoding;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class EncryptUtils {
    public static final String DEFAULT_ENCODING="UTF-8";
    static BASE64Encoder enc=new BASE64Encoder();
    static BASE64Decoder dec=new BASE64Decoder();

    public static String base64encode(String text){
        try {
            String rez = enc.encode( text.getBytes( DEFAULT_ENCODING ) );
            return rez;
        }
        catch ( UnsupportedEncodingException e ) {
            return null;
        }
    }//base64encode

    public static String base64decode(String text){

        try {
            return new String(dec.decodeBuffer( text ),DEFAULT_ENCODING);
        }
        catch ( IOException e ) {
            return null;
        }

    }//base64decode

    public static void main(String[] args){
        BaseEncoding baseEncoding = BaseEncoding.base64();
        String a = baseEncoding.encode("http://server.holisticon.de/crowd-o-mat-consulting/47f06e79-0ad5-48be-969a-9ce946e617c4/claim/zambrovski".getBytes());

        System.out.println(a + " - " + baseEncoding.decode(a));

        String txt="some text to be encrypted" ;
        String key="key phrase used for XOR-ing";
        System.out.println(UUID.randomUUID().toString());
        System.out.println(txt+" XOR-ed to: "+(txt=xorMessage( txt, key )));
        String encoded=base64encode( txt );
        System.out.println( " is encoded to: "+encoded+" and that is decoding to: "+ (txt=base64decode( encoded )));
        System.out.print( "XOR-ing back to original: "+xorMessage( txt, key ) );

    }

    public static String xorMessage(String message, String key){
        try {
            if (message==null || key==null ) return null;

            char[] keys=key.toCharArray();
            char[] mesg=message.toCharArray();

            int ml=mesg.length;
            int kl=keys.length;
            char[] newmsg=new char[ml];

            for (int i=0; i<ml; i++){
                newmsg[i]=(char)(mesg[i]^keys[i%kl]);
            }//for i
            mesg=null; keys=null;
            return new String(newmsg);
        }
        catch ( Exception e ) {
            return null;
        }
    }//xorMessage

}//class