package amysa.mobileproject.alar.alar;

/**
 * Created by moviles on 17/10/16.
 */

public class OpenCVNativeClass {
    public native  static int loadImages(long address,long address_A,long address_B,long address_C,long addresslong_D,long address_E);
    public native  static int getAnswer(int index);
    public native  static int convertGray(long matAddrRgba,long mattAddrGray);
}
