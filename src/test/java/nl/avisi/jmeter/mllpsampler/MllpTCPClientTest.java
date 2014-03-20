package nl.avisi.jmeter.mllpsampler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;


public class MllpTCPClientTest {
    private MllpTCPClient mllpTCPSampler;

    @Before
    public void setUp() throws Exception {
        mllpTCPSampler = new MllpTCPClient();
    }

    @Test
    public void testWriteWithString() throws Exception {
        OutputStream bufferedOutputStream = new FileOutputStream(new File("test.txt"));
       mllpTCPSampler.write(bufferedOutputStream, "");
    }

    @Test
    public void testRead() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("testhl7.hl7"));
        String result = mllpTCPSampler.read(bufferedInputStream);
        System.out.println(result);

    }
}
