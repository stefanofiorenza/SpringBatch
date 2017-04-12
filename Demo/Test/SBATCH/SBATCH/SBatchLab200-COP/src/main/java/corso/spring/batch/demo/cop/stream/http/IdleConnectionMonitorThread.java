package corso.spring.batch.demo.cop.stream.http;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class IdleConnectionMonitorThread extends Thread{

	private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;
    private int idleConnTimeout;
    private int cleanInterval;
    
    public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr,int cleanInterval, int idleConnTimeout) {
        super();
        this.connMgr = connMgr;
        this.idleConnTimeout=idleConnTimeout;
        this.cleanInterval=cleanInterval;
    }
    
    
    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(this.cleanInterval);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(this.idleConnTimeout, TimeUnit.MILLISECONDS);
                }
            }
        } catch (InterruptedException ex) {
            shutdown();
        }
    }
    
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
