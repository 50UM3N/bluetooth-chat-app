package com.example.localstorageaddressbook;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    private Context fileContext;
    private File file;
    private String FILENAME ="jsonFile";
    FileHelper(@Nullable Context c) throws IOException {
        this.fileContext = c;
        file = new File(fileContext.getFilesDir(),FILENAME);
        if (!file.exists()){
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[]");
            bufferedWriter.close();
            fileWriter.close();
        }
    }

    public String readAll() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
        return stringBuilder.toString();
    }
    public void addAddress(String name, String address, String email, String contact) throws IOException, JSONException {
        String fileData = readAll();
        JSONArray jArray = new JSONArray(fileData);
        int position = jArray.length()+1;
        JSONObject newData = new JSONObject();
        newData.put("id",position);
        newData.put("name",name);
        newData.put("address",address);
        newData.put("email",email);
        newData.put("contact", contact);
        jArray.put(newData);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(jArray.toString());
        bufferedWriter.close();
        fileWriter.close();
        Toast.makeText(fileContext,"Data Added Successfully!!!" ,Toast.LENGTH_SHORT).show();
    }
}
