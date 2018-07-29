package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.Application;
import android.content.Context;
import android.net.Credentials;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class GlobalState extends Application {
    private static Customer customer;
    private static List<ClaimItem> claimsList;
    private static ArrayList<String> credentials;
    String filenameCustomer = "inSureLogCustomer";
    String filenameClaimsList = "inSureLogClaimsList";
    String filenameCredentials = "inSureLogCredentials";
    FileInputStream inputStream;
    FileOutputStream outputStream;
    public static HashMap inSureMsgPerClaim;
    public static HashMap diffInSureMsgPerClaim = new HashMap();

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public void setClaimsList(List<ClaimItem> claimsList) {
        this.claimsList = claimsList;
    }

    public void setCredentials(ArrayList<String> credentials) {
        this.credentials = credentials;
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

    public void writeCredentialsFile(ArrayList<String> credentials) {
        try {
            outputStream = openFileOutput(filenameCredentials, Context.MODE_PRIVATE);

            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            out.writeObject(credentials);
            out.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("ClaimsListFiles", "ClaimsListFilesWrite: " + fileList()[0]);
    }

    public ArrayList<String> readCredentialsFile() {
        try {
            ArrayList<String> credentials = null;

            inputStream = openFileInput(filenameCredentials);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            credentials = (ArrayList<String>) in.readObject();
            in.close();
            inputStream.close();

            this.setCredentials(credentials);
            Log.d("CredentialsFile", "CredentialFileRead: " + fileList()[0]);
            return credentials;
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Credentials not found");
            c.printStackTrace();
        } return null;
    }
}
