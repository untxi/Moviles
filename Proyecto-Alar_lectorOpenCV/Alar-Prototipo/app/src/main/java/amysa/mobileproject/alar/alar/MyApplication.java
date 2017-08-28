package amysa.mobileproject.alar.alar;

import android.app.Application;

import java.util.ArrayList;


public class MyApplication extends Application {
    private ArrayList<data> students;

    public void setStudents (ArrayList<data> students ){
        this.students = students;
    }

    public ArrayList<data> getStudents(){
        return  students;
    }
}
