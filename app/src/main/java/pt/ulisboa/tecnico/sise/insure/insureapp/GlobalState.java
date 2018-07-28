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
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class GlobalState extends Application {
    private static Integer sessionId;
    private static Customer customer;
    private static List<ClaimItem> claimsList;
    String filenameCustomer = "inSureLogCustomer";
    String filenameClaimsList = "inSureLogClaimsList";
    FileInputStream inputStream;
    FileOutputStream outputStream;
    private GlobalState _globalState;

    public static Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId){
        this.sessionId = sessionId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public void setClaimsList(List<ClaimItem> claimsList) {
        this.claimsList = claimsList;
    }

    public void writeCustomerFile(Customer customer) {
        try {
            outputStream = openFileOutput(filenameCustomer, Context.MODE_PRIVATE);

            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            out.writeObject(customer);
            out.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("CustomerFiles", "CustomerFilesWrite: " + fileList()[0]);
    }

    public Customer readCustomerFile() {
        try {
            Customer customer = null;

            inputStream = openFileInput(filenameCustomer);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            customer = (Customer) in.readObject();
            in.close();
            inputStream.close();

            this.setCustomer(customer);
            Log.d("CustomerFiles", "CustomerFilesRead: " + fileList()[0]);
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

    public void writeListClaimsFile(List<ClaimItem> claimsList) {
        try {
            outputStream = openFileOutput(filenameClaimsList, Context.MODE_PRIVATE);

            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            out.writeObject(claimsList);
            out.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("ClaimsListFiles", "ClaimsListFilesWrite: " + fileList()[0]);
    }

    public List<ClaimItem> readListClaimsFile() {
        try {
            List<ClaimItem> claimsList = null;

            inputStream = openFileInput(filenameClaimsList);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            claimsList = (List<ClaimItem>) in.readObject();
            in.close();
            inputStream.close();

            this.setClaimsList(claimsList);
            Log.d("ClaimsListFiles", "ClaimsListFilesRead: " + fileList()[0]);
            return claimsList;
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("ClaimsList not found");
            c.printStackTrace();
        } return null;
    }
}
