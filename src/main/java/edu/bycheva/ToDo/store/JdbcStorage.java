package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;
import edu.bycheva.ToDo.model.TaskPriority;
import edu.bycheva.ToDo.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcStorage implements Storage {

    private Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection =
                    DriverManager.getConnection(
                            settings.value("jdbc.url"),
                            settings.value("jdbc.user"),
                            settings.value("jdbc.password")
                    );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<Task> values() {
        final ArrayList<Task> tasks = new ArrayList<>();
        try(Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("select * from task")){
            while (rs.next()){
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        TaskPriority.valueOf(rs.getString("priority")),
                        rs.getString("description"),
                        new java.sql.Date(rs.getDate("termEnd").getTime())));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public int add(Task model) {
        try(PreparedStatement statement =
                    this.connection.prepareStatement(
                            "insert into tasks (name, priority, description, termEnd) values (?,?,?,?) ",
                            Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,model.getName());
            statement.setString(2,model.getTaskPriority().name());
            statement.setString(3,model.getDescription());
            statement.setDate(4, new java.sql.Date(model.getTermEnd().getTime()));
            statement.executeUpdate();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        throw new IllegalStateException("Could not create new task");
    }

    @Override
    public void edit(Task model) {
        try(final PreparedStatement statement = this.connection.prepareStatement("UPDATE tasks set name=(?), priority=(?), description=(?), termEnd=(?) where uid=(?)")){
            statement.setString(1, model.getName());
            statement.setString(2, model.getTaskPriority().name());
            statement.setString(3, model.getDescription());
            statement.setDate(4, new java.sql.Date(model.getTermEnd().getTime()));
            statement.setInt(5, model.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = this.connection.prepareStatement("DELETE from tasks where uid = (?)")){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task get(int id) {
        try(PreparedStatement statement = this.connection.prepareStatement("select * from tasks where uid=(?)");
        ){
            statement.setInt(1,id);
            statement.getUpdateCount();
            try(ResultSet rs = statement.executeQuery()){
                while(rs.next()) {
                    return new Task(
                            rs.getInt("id"),
                            rs.getString("name"),
                            TaskPriority.valueOf(rs.getString("priority")),
                            rs.getString("description"),
                            rs.getDate("termEnd")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException(String.format("task %s does not exit",id));
    }

    @Override
    public Task findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        throw new UnsupportedOperationException("Not realized generateId method");
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
