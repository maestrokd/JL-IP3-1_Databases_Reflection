package com.infoPulse.lessons.DaoObjects;

import com.infoPulse.lessons.DaoTools.ConnectionSql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoObject <T> {


    // Fields
    Class <T> classObject;

//    T objectT = null;

    String tableName = null;

    Annotation[] annotationsClass;
    Annotation[] annotationsField;
    Field[] fields;
    String nameFieldID =null;

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    // Constructors
    public DaoObject(Class <T> classObject) {
        this.connection = ConnectionSql.getInstance().getConnection();
        this.classObject = classObject;

        readTableName();
        readFields();
        getNameFieldID();

        createObject();

    }


    // Methods

    public T getForID(int index) {

        T objectT = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * from " + tableName + " where " + nameFieldID +"=?");
            preparedStatement.setInt(1,index);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            objectT = readResultSet(resultSet).get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return objectT;
    }


    public List<T> getAll() {

        List<T> tList = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * from " + tableName);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            tList = readResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return tList;
    }


    public void add(T otherObjectT) {

        // Read column names for add
        String columnNames = "";
        String values = "";
//        for (int i = 0; i < fields.length; i++) {
        for (Field field : fields) {

            annotationsField = field.getAnnotations();
            for (Annotation annotation : annotationsField) {
                if ("DaoField".equals(annotation.annotationType().getSimpleName()) && (annotation.toString().indexOf("id = true") < 1)) {
                if (columnNames.length() > 0) {
                    columnNames = columnNames.concat(", ");
                    values = values.concat(", ");
                }
                columnNames = columnNames.concat(field.getName().toString());
                values = values.concat("?");
                }
            }


            // Other method fo test
//            if (i == 0) {
//                continue;
//            }
//            if (i > 1) {
//                columnNames = columnNames.concat(", ");
//                values = values.concat(", ");
//            }
//            columnNames = columnNames.concat(fields[i].getName().toString());
//            values = values.concat("?");
        }
//        System.out.println(columnNames + "|" + values);


        // Add some fields to database
        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO " + tableName + " (" + columnNames + ")  VALUES (" + values + ")");
//            int countField = 1;
            int countValues = 1;
            for (Field field : fields) {
                annotationsField = field.getAnnotations();
                for (Annotation annotation : annotationsField) {
                    if ("DaoField".equals(annotation.annotationType().getSimpleName()) && (annotation.toString().indexOf("id = true") < 1)) {
                        field.setAccessible(true);
                        System.out.print(field.get(otherObjectT) + "|");
                        preparedStatement.setString(countValues, field.get(otherObjectT).toString());
                        countValues++;
                    }
                }


                // Other method fo test
//                String typeField = fields[i].getGenericType().getTypeName();
//                switch (typeField) {
//                    case "int" :
////                        field.setInt(objectT, resultSet.getInt(countField));
//                        preparedStatement.setInt(i, (int) fields[i].get(otherObjectT));
//                        break;
//                    case "java.lang.String" :
////                        field.set(objectT, resultSet.getString(countField));
//                        preparedStatement.setString(i, fields[i].get(otherObjectT).toString());
//                        break;
//                }

//                countField++;

            }
            preparedStatement.executeUpdate();

            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    // Read TableName of class
    private void readTableName() {
        int indexOfTableName;
        annotationsClass = classObject.getAnnotations();
//        System.out.println("Object annotation");
        for (Annotation annotation : annotationsClass) {
//            System.out.println("@" + annotation.annotationType().getSimpleName());
//            System.out.println(annotation.toString());
            indexOfTableName = (annotation.toString().indexOf("name") + 5);
//            System.out.println(indexOfTableName);
//            System.out.println(annotation.toString().substring(indexOfTableName));
            tableName = annotation.toString().substring(indexOfTableName);
            tableName = tableName.substring(0, (tableName.length() - 1));
            System.out.println("Name of table: " + tableName);
        }
    }


    // Read Fields of class
    private void readFields() {
        fields = classObject.getDeclaredFields();
        for (Field field : fields) {
//            System.out.println(field.getName() + "|" + field.getGenericType());
            annotationsClass = field.getDeclaredAnnotations();
            for (Annotation annotation : annotationsClass) {
//                System.out.println("@" + annotation.toString());
            }
        }
    }

    private T createObject() {
        try {
            Object object = classObject.newInstance();
            T objectT = (T) object;
            return objectT;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getNameFieldID() {
        for (Field field : fields) {
            annotationsField = field.getAnnotations();
            for (Annotation annotation : annotationsField) {
                if ("DaoField".equals(annotation.annotationType().getSimpleName()) && (annotation.toString().indexOf("id=true") >= 1)) {
                    nameFieldID = field.getName().toString();
                }
            }
        }
    }

    private List<T> readResultSet(ResultSet resultSet) throws SQLException, IllegalAccessException {

        T objectT;
        List<T> tList = new LinkedList<>();

        while (resultSet.next()) {
            objectT = createObject();
            int countField = 1;
            for (Field field : fields) {
                String typeField = field.getGenericType().getTypeName();
//                System.out.print(resultSet.getString(countField) + "|");
                field.setAccessible(true);
//                field.set(objectT, resultSet.getString(countField));

                switch (typeField) {
                    case "int":
                        field.setInt(objectT, resultSet.getInt(countField));
                        break;
                    case "java.lang.String":
                        field.set(objectT, resultSet.getString(countField));
                        break;
                }

                countField++;
            }
            tList.add(objectT);
//                System.out.println();
        }

        return tList;

    }

}
