package jdbc.com.connectionpool.spring;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import java.sql.Driver;
//import com.mysql.jdbc.Connection;

public class ConnectionPoolSpring {

    private List<Connection> l;// how about using Deque!
    // private Deque<Connection> d;

    private int connections;

    DataSource driverManagerDataSource;


    public ConnectionPoolSpring(DataSource driverManagerDataSource, int connections) {
        this.driverManagerDataSource = driverManagerDataSource;
        this.connections = connections;

        l = new ArrayList<Connection>(connections);

        int i = this.connections;
        while (i-- != 0) {
            try {
                l.add(driverManagerDataSource.getConnection());
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }
        }
    }

    public int getSize() {
        return this.connections;
    }

    private boolean isEmpty() {
        if (this.connections == 0) {
            return true;
        }

        return false;
    }

    // public synchronized Connection pullConnection() {
    public Connection pullConnection() {

        synchronized (l) {

            System.out.println("Entering.." + Thread.currentThread().toString());

            while (isEmpty()) {

                try {
                    System.out.println("Waiting for a connection! "
                            + Thread.currentThread().toString());
                    // wait();
                    l.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Granted.." + Thread.currentThread().toString());

            this.connections--;
            return l.remove(0);
        }
    }

    //	public synchronized void pushConnection(Connection c) {
    public void pushConnection(Connection c) {
        synchronized (l) {

            this.connections++;
            l.add(c);
            // notifyAll();
            l.notifyAll();
        }
    }

    public void shutdown(Connection c) throws SQLException {
        l.remove(c);
        c.close();
        this.connections--;
    }

    public void shutdownAll() throws SQLException {

        while (this.connections-- != 0) {
            Connection c = l.remove(this.connections);
            c.close();
        }
    }

    /*
     * this must not be exposed to outside world
     */
    public List<Connection> getConnectionList() {
        return this.l;
    }

}
