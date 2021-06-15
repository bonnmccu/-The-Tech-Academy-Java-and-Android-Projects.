
public class Converter_Main {

    
    public static void main(String[] args){
        for(int i = -5; i < 33; i++){  //loop
            System.out.println(i + ": " + toBinary(i));
            System.out.println(i);
            System.out.println(i + ": " + Integer.toBinaryString(i));
        }
    }
    
    
    public static String toBinary(int base10Num){ //returns a String with base10Num in base 2
       
        boolean isNeg = base10Num < 0;
        base10Num = Math.abs(base10Num);        
        String result = "";
        
        while(base10Num > 1){
            result = (base10Num % 2) + result;
            base10Num /= 2;
        }
        assert base10Num == 0 || base10Num == 1 : "value is not <= 1: " + base10Num;
        
        result = base10Num + result;
        assert all0sAnd1s(result);
        
        if( isNeg )
            result = "-" + result;
        return result;
    }
    
   
     
    public static boolean all0sAnd1s(String val){ // post: return true if val has only  1 and 0,or ret false 
        assert val != null : "Failed precondition all0sAnd1s. parameter cannot be null";
        boolean all = true;
        int i = 0;
        char c;
        
        while(all && i < val.length()){
            c = val.charAt(i);
            all = c == '0' || c == '1';
            i++;
        }
        return all;
    }
}
