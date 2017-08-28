package amysa.mobileproject.alar.alar;

import java.util.ArrayList;


public class data {
    private String studentName;
    private  ArrayList <String> answer=new ArrayList<String >();

    public data(String studentName){
        this.studentName = studentName;
    }

    public String getOneAnswer(int index){
        return answer.get(index);
    }

    public String getStudentName(){
        return  studentName;
    }

    public ArrayList<String> getAnswer(){
        return  answer;
    }

    public void setStudentName(String name){
        this.studentName = name;
    }

    public void setAnswer(ArrayList<String>answer){
        this.answer = answer;
    }

    public void addAnswer(int answerMark){
        switch (answerMark){
            case 65:
                answer.add("A");
                break;
            case 66:
                answer.add("B");
                break;
            case 67:
                answer.add("C");
                break;
            case 68:
                answer.add("D");
                break;
            case 69:
                answer.add("E");
                break;
        }
    }
}
