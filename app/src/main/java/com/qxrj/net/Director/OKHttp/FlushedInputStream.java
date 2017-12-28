package com.qxrj.net.Director.OKHttp;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/8/26.
 */
public class FlushedInputStream extends FilterInputStream {

    public FlushedInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public long skip(long byteCount) throws IOException {

        long totalBytesSkipped = 0L;
        while (totalBytesSkipped < byteCount) {
            long bytesSkipped = in.skip(byteCount - totalBytesSkipped);
            if (bytesSkipped == 0L) {
                int b = read();
                if (b < 0) {
                    break;  // we reached EOF
                } else {
                    bytesSkipped = 1; // we read one byte
                }
            }
            totalBytesSkipped += bytesSkipped;
        }
        return totalBytesSkipped;
        //return super.skip(byteCount);

    }
}
