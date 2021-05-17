package ua.edu.sumdu.j2ee.pohorila.hotelsys.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class DAOInterfaceOracleImpl implements DAOInterfaceOracle{

    private static DAOInterfaceOracleImpl instance = new DAOInterfaceOracleImpl();

    private static DAOInterfaceOracleImpl getInstance(){
        return instance;
    }
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DataSource dataSource;
    private Context context;
    private Hashtable hashtable = new Hashtable();

    public DAOInterfaceOracleImpl(){
        super();
    }

    @Override
    public void connect(){
        try{
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup("DataSourceHotel");
            connection = dataSource.getConnection();
            if(!connection.isClosed()){
                System.out.println("Connection successful...");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect(){
        try{
            if(connection != null)
                connection.close();
            if(context != null)
                context.close();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }

}
