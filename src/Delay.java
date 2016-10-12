/*
 * Created by ivanovcinnikov on 12.10.16.
 */

class Delay {
    void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
