package nl.avisi.jmeter.mllpsampler;

import jodd.io.StringOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class MllpTCPClientTest {
    private MllpTCPClient mllpTCPSampler;

    @Before
    public void setUp() throws Exception {
        mllpTCPSampler = new MllpTCPClient();
    }

    @Test
    public void testWriteWithString() throws Exception {
        OutputStream output = new StringOutputStream(UTF_8.displayName());
        OutputStream bufferedOutputStream = new ObjectOutputStream(output);
        mllpTCPSampler.write(bufferedOutputStream, "MSH|^~\\&|COOL APP|FOOBAR INC|SOME APP|SOME FACILITY|20140113224532||ADT^A01^ADT_A01|12345|P|2.5.1|\n" +
                "EVN|A01|20140113224544||\n" +
                "PID|1||1||DOE^JOHN^Y^MR||19830920|M||C|19/8 Foobar st^^New York City|IL|(123)456 7890|(123)456 7890||S||1|987654321||\n" +
                "PV1|1|I|0^1^2||||111^HOPKINS^DR^B|||||||ADM|A0|");
        assertTrue(output.toString().contains("MSH|^~\\&|COOL APP|FOOBAR INC|SOME APP|SOME FACILITY|20140113224532||ADT^A01^ADT_A01|12345|P|2.5.1|\r"));
    }

    @Test
    public void testRead() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("testhl7.hl7"));
        String result = mllpTCPSampler.read(bufferedInputStream);
        assertNotNull(result);

    }
}
