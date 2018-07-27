package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class GlobalState extends Application {
    private static Integer sessionId;
    private static Customer customer;
    String filename = "inSureSavedData";
    FileInputStream inputStream;
    FileOutputStream outputStream;

    public static Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId){
        this.sessionId = sessionId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void writeFile(Customer customer) {
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            out.writeObject(customer);
            out.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("ListFiles", "FliesWrite: " + fileList()[0]);
    }

    public Customer readFile() {
        try {
            Customer customer = null;

            inputStream = openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            customer = (Customer) in.readObject();
            in.close();
            inputStream.close();

            this.setCustomer(customer);
            Log.d("ListFiles", "FliesRead: " + fileList()[0]);
            return customer;
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Customer class not found");
            c.printStackTrace();
        } return null;
    }
}
