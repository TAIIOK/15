package BarleyBreak.Bones;


public class Label {
    

    private Bone bone;

    private String number;


    public String getNumber()
    {
        return this.number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Bone bone(){
        return bone;
    }
    
    public void setBone(Bone b){
        bone=b;
    }



}
