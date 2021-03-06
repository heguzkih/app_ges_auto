package preferencias;
import android.content.SharedPreferences;
import com.example.ges_auto.modelo.Token;

public class MisPreferencias {

    public static  final String SHARED_PREFERENCES =  "SHARED_PREFERENCES";
    private static  final String SHARED_PREFERENCES_TOKEN =  "SHARED_PREFERENCES_TOKEN";
   private static  final String  SHARED_PREFERENCES_SERVIDOR= "SHARED_PREFERENCES_SERVIDOR";
    private  static MisPreferencias INSTANCE = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private MisPreferencias(SharedPreferences sharedPreferences){
        this.sharedPreferences=sharedPreferences;
        this.editor=sharedPreferences.edit();
    }

    public static synchronized MisPreferencias getInstance(SharedPreferences sharedPreferences){

        if (INSTANCE == null) {
            INSTANCE = new MisPreferencias(sharedPreferences);
        }
        return  INSTANCE;
    }


    public  void  saveToken (Token token){
        editor.putString(SHARED_PREFERENCES_TOKEN,token.getSucces());
        editor.apply();
    }

    public void saveServidor(String servidor){

        editor.putString(SHARED_PREFERENCES_SERVIDOR,servidor);
        editor.commit();
    }


    public Token getToken(){
        Token token = new Token();
        token.setSucces(sharedPreferences.getString(SHARED_PREFERENCES_TOKEN, null));
        return token;

    }

    public String getServidor(){
        String servidor = sharedPreferences.getString(SHARED_PREFERENCES_SERVIDOR,null);
        return  servidor;
    }





}
