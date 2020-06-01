
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowel="aeiouAEIOU";
        int idx=vowel.indexOf(ch);
        if(idx!=-1){
            return true;
        }
        return false;
    }
    public String replaceVowel(String phrase,char ch){
        StringBuilder str=new StringBuilder("");
        for(int i=0;i<phrase.length();i++){
            char currChar=phrase.charAt(i);
            if(isVowel(currChar))
                str.append(ch);
            else
                str.append(currChar);
        }
        return str.toString();
    }
    public String emphasis(String phrase,char ch){
        StringBuilder str=new StringBuilder("");
        for(int i=0;i<phrase.length();i++){
            char currChar=phrase.charAt(i);
            if(currChar==Character.toLowerCase(ch) || currChar==Character.toUpperCase(ch))
                if(i%2==0)
                    str.append('*');
                else
                    str.append('+');
            else
                str.append(currChar);
        }
        return str.toString();
    }
}
