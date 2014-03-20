package nl.avisi.jmeter.mllpsampler;

import org.apache.jmeter.protocol.tcp.sampler.AbstractTCPClient;
import org.apache.jmeter.protocol.tcp.sampler.ReadException;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;


public class MllpTCPClient extends AbstractTCPClient{
    private static final Logger log = LoggingManager.getLoggerForClass();
    private static final byte MLLP_START = 0x0b;
    private static final byte MLLP_END1  = 0x1c;
    private static final byte MLLP_END2  = 0x0d;

    private int eolInt = JMeterUtils.getPropDefault("tcp.eolByte", 13); // $NON-NLS-1$

    public MllpTCPClient() {
            super();
            setEolByte(eolInt);
            if (useEolByte) {
                log.info("Using eolByte=" + eolByte);
            }
    }

    @Override
    public void write(OutputStream outputStream, InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException("Not used in JMeter");
    }

    @Override
    public void write(OutputStream outputStream, String s) throws IOException {
        log.info("Writing with string");
        log.debug("String contains:" + s);

        s = s.replace("\n", "\r");

        try {
            outputStream.write(MLLP_START);
            outputStream.write(s.getBytes(UTF_8));
            outputStream.write(MLLP_END1);
            outputStream.write(MLLP_END2);
            outputStream.flush();
        }
        catch (IOException e) {
            log.warn("Write error", e);
        }
        log.info("Wrote: " + s);
    }

    @Override
    public String read(InputStream inputStream) throws ReadException {
        log.info("Reading");
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream w = new ByteArrayOutputStream();
        int x;
        try {
            while ((x = inputStream.read(buffer)) > -1) {
                w.write(buffer, 0, x);
                if (useEolByte && (buffer[x - 1] == eolByte)) {
                    break;
                }
            }
        } catch (InterruptedIOException e) {
            // drop out to handle buffer
        } catch (IOException e) {
            log.warn("Read error:" + e);
            return "";
        }

        // do we need to close byte array (or flush it?)
        log.debug("Read: " + w.size() + "\n" + w.toString());
        return w.toString();
    }
}


